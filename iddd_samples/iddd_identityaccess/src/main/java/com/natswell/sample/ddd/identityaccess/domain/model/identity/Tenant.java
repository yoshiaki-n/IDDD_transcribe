package com.natswell.sample.ddd.identityaccess.domain.model.identity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.natswell.sample.ddd.common.domain.model.ConcurrencySafeEntity;
import com.natswell.sample.ddd.common.domain.model.DomainEventPublisher;
import com.natswell.sample.ddd.identityaccess.domain.model.access.Role;
import com.natswell.sample.ddd.identityaccess.domain.model.access.RoleProvisioned;

/**
 * aggregate root
 * @author yoshiaki-n
 *
 */
public class Tenant extends ConcurrencySafeEntity {

    private static final long serialVersionUID = 1L;
    
    private boolean active;
    private String description;
    private String name;
    private Set<RegistrationInvitation> registrationInvitations;
    private TenantId tenantId;
    
    public Tenant(TenantId aTenantId, String aName, String aDescription, boolean anActive) {
        this();
        
        this.setActive(anActive);
        this.setDescription(aDescription);
        this.setName(aName);
        this.setTenantId(aTenantId);
    }
    
//    public void activate() {
//        
//    }
//    
//    public Collection<InvitationDescriptor> allAvailableRegistrationInvitations() {
//        return null;
//    }
//    
//    public Collection<InvitationDescriptor> allUnavailableRegistrationInvitations() {
//        return null;
//    }
//    
//    public void deacivate() {
//        
//    }
//    
//    public String description() {
//        return null;
//    }
//    
    public boolean isActive() {
        return this.active;
    }
    
    public boolean isRegistrationAvailableThrough(String anInvitationIdentifier) {
        this.assertStateTrue(this.isActive(), "Tenant is not active.");
        
        RegistrationInvitation invitation =
                this.invitation(anInvitationIdentifier);
        return invitation == null ? false : invitation.isAvailable();
    }
    
//    public String name() {
//        return this.name;
//    }
    
    public RegistrationInvitation offerRegistrationInvitation(String aDescription) {
        this.assertStateTrue(this.isActive(), "Tenant is not active.");
        
        this.assertStateFalse(
                this.isRegistrationAvailableThrough(aDescription), 
                "Invitation already exists.");
        
        RegistrationInvitation invitation =
                new RegistrationInvitation(
                        this.tenantId(),
                        UUID.randomUUID().toString().toUpperCase(),
                        aDescription);
        
        boolean added = this.registrationInvitations().add(invitation);
        
        this.assertStateTrue(added, "The invitation should have been added.");
        
        return invitation;
    }
    
    public Group provisionGroup(String aName, String aDescription) {
        this.assertStateTrue(this.isActive(), "Tenant is not active.");
        
        Group group = new Group(this.tenantId(), aName, aDescription);
        
        DomainEventPublisher
            .instance()
            .publish(
                    new GroupProvisioned(this.tenantId(), aName));
        return group;
    }
    
    public Role provisionRole(String aName, String aDescription) {
        return this.provisionRole(aName, aDescription, false);
    }

    public Role provisionRole(String aName, String aDescription, boolean aSupportsNesting) {
        this.assertStateTrue(this.isActive(), "Tenant is not active.");
        
        Role role = new Role(this.tenantId(), aName, aDescription, aSupportsNesting);
        
        DomainEventPublisher
            .instance()
            .publish(
                    new RoleProvisioned(this.tenantId(), aName));
        return role;
    }
//    
//    public RegistrationInvitation redefineRegistrationInvirationAs(String anInvitationIdentifier) {
//        return null;
//    }
    
    public User registerUser(
            String anIvitationIdentifier,
            String aUsername,
            String aPassword,
            Enablement anEnablement,
            Person aPerson) {
        this.assertStateTrue(this.isActive(), "Tenant is not active.");
        
        User user = null;
        
        if (this.isRegistrationAvailableThrough(anIvitationIdentifier)) {
            // ensure same tenant
            aPerson.setTenantId(this.tenantId());
            
            user = new User(
                    this.tenantId(),
                    aUsername,
                    aPassword,
                    anEnablement,
                    aPerson);
            
        }
        
        return user;
    }
    
    public TenantId tenantId() {
        return this.tenantId;
    }
//    
//    public void withdrawInvitation(String anInvitationIdentifier) {
//        
//    }
//    
//    @Override
//    public boolean equals(Object obj) {
//        // TODO Auto-generated method stub
//        return super.equals(obj);
//    }
//    
//    @Override
//    public int hashCode() {
//        // TODO Auto-generated method stub
//        return super.hashCode();
//    }
//    
//    @Override
//    public String toString() {
//        // TODO Auto-generated method stub
//        return super.toString();
//    }
    
    protected Tenant() {
        super();
        
        this.setRegistrationInvitations(new HashSet<RegistrationInvitation>(0));
    }
    
    protected void setActive(boolean anActive) {
        this.active = anActive;
    }
    
//    protected Collection<InvitationDescriptor> allRegistrationInvitationsFor(boolean isAvailable) {
//        return null;
//    }
    
    protected void setDescription(String aDescription) {
        this.assertArgumentNotEmpty(aDescription, "The tenant description is required.");
        this.assertArgumentLength(aDescription, 1, 100, "The tenant description must be 100 character or less.");
        
        this.description = aDescription;
    }
    
    protected RegistrationInvitation invitation(String anInvitationIdentifier) {
        for (RegistrationInvitation invitation : this.registrationInvitations()) {
            return invitation;
        }
        return null;
    }
    
    protected void setName(String aName) {
        this.assertArgumentNotEmpty(aName, "The tenant name is required.");
        this.assertArgumentLength(aName, 1, 100, "The name must be 100 characters or less.");
        
        this.name = aName;
    }
    
    protected Set<RegistrationInvitation> registrationInvitations() {
        return this.registrationInvitations;
    }
    
    protected void setRegistrationInvitations(Set<RegistrationInvitation> aRegistrationInvitations) {
        this.registrationInvitations = aRegistrationInvitations;
    }
    
    protected void setTenantId(TenantId aTenantId) {
        this.assertArgumentNotNull(aTenantId, "TenantId is required.");
        
        this.tenantId = aTenantId;
    }
}
