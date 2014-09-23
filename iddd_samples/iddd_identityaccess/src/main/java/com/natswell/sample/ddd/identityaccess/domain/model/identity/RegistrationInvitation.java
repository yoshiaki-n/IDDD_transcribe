package com.natswell.sample.ddd.identityaccess.domain.model.identity;

import java.util.Date;

import com.natswell.sample.ddd.common.domain.model.ConcurrencySafeEntity;

/**
 * entity
 * @author yoshiaki-n
 *
 */
public class RegistrationInvitation extends ConcurrencySafeEntity {

    private static final long serialVersionUID = 1L;
    
    private String description;
    private String invitationId;
    private Date startingOn;
    private TenantId tenantId;
    private Date until;

//    public String description() {
//        
//    }
    
    public String invitationId() {
        return this.invitationId;
    }
    
//    public boolean isAvailable() {
//        
//    }
//    
//    public boolean isIdentifiedBy(String anInvitationIdentifier) {
//        
//    }
    
    public RegistrationInvitation openEnded() {
        this.setStartingOn(null);
        this.setUntil(null);
        return this;
    }
    
//    public RegistrationInvitation redefineAs() {
//        
//    }
    
    public Date startingOn() {
        return this.startingOn;
    }
    
//    public RegistrationInvitation startingOn(Date aDate) {
//        
//    }
//    
//    public TenantId tenantId() {
//        
//    }
//    
//    public InvitationDescriptor toDescriptor() {
//        
//    }
    
    public Date until() {
        return this.until;
    }
    
//    public RegistrationInvitation until(Date aDate) {
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
    
    protected RegistrationInvitation(
            TenantId aTenantId,
            String anInvitationId,
            String aDescription) {
        this();
        
        this.setDescription(aDescription);
        this.setInvitationId(anInvitationId);
        this.setTenantId(aTenantId);
        
        this.assertValidInvitationDates();
    }
    
    protected RegistrationInvitation() {
        super();
    }
    
    protected void assertValidInvitationDates() {
        // either both dates must be null, or both dates must be set
        if (this.startingOn() == null && this.until() == null) {
            ; // valid
        } else if (this.startingOn() == null || this.until() == null && this.startingOn() != this.until()) {
            throw new IllegalStateException("This is an invalid open-ended invitation.");
        } else if (this.startingOn().after(this.until())) {
            throw new IllegalStateException("The starting date and time must be before the until date and time.");
        }
    }
    
    protected void setDescription(String aDescription) {
        this.assertArgumentNotEmpty(aDescription, "The invitation description is required");
        this.assertArgumentLength(aDescription, 1, 100, "The invitation description must be 100 characters or less.");
        
        this.description = aDescription;
    }
    
    protected void setInvitationId(String anInvitationId) {
        this.assertArgumentNotEmpty(anInvitationId, "The invitation description is required");
        this.assertArgumentLength(anInvitationId, 1, 100, "The invitation description must be 36 characters or less.");
        
        this.invitationId = anInvitationId;
    }
    
    protected void setStartingOn(Date aStartingOn) {
        this.startingOn = aStartingOn;
    }
    
    protected void setTenantId(TenantId aTenantId) {
        this.assertArgumentNotNull(aTenantId, "The tenantId is required.");
        
        this.tenantId = aTenantId;
    }
    
    protected void setUntil(Date anUntil) {
        this.until = anUntil;
    }
}
