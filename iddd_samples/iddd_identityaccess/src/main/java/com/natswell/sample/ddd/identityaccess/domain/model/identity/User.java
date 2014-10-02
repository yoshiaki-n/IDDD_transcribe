package com.natswell.sample.ddd.identityaccess.domain.model.identity;

import com.natswell.sample.ddd.common.domain.model.ConcurrencySafeEntity;
import com.natswell.sample.ddd.common.domain.model.DomainEventPublisher;
import com.natswell.sample.ddd.identityaccess.domain.model.DomainRegistry;

/**
 * aggregate root
 * @author yoshiaki-n
 *
 */
public class User extends ConcurrencySafeEntity {

    private static final long serialVersionUID = 1L;

    private Enablement enablement;
    private String password;
    private Person person;
    private TenantId tenantId;
    private String username;
    
//    public void changePassword(String aCurrentPassword, String aChangedPassword) {
//        
//    }
//    
//    public void changePersonalContactInformation(ContactInformation aCOntaContactInformation) {
//        
//    }
//    
//    public void defineEnablement(Enablement anEnablement) {
//        
//    }
//    
//    public boolean isENabled() {
//        // TODO
//        return false;
//    }
    
    public Person person() {
        return this.person;
    }
    
    public TenantId tenantId() {
        return this.tenantId;
    }
    
//    public UserDescriptor userDescriptor() {
//        // TODO
//    }
    
    public String username() {
        return this.username;
    }
    
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
//    
    protected User(
            TenantId aTenantId,
            String aUsername,
            String aPassword,
            Enablement anEnablement,
            Person aPerson) {
        this();

        this.setEnablement(anEnablement);
        this.setPerson(aPerson);
        this.setTenantId(aTenantId);
        this.setUsername(aUsername);

        this.protectPassword("", aPassword);

        aPerson.internalOnlySetUser(this);

        DomainEventPublisher
            .instance()
            .publish(new UserRegistered(
                    this.tenantId(),
                    aUsername,
                    aPerson.name(),
                    aPerson.contactInformation().emailAddress()));
    }
    
    protected User() {
        super();
    }
    
    protected String asEncryptedValue(String aPlainTextPassword) {
    String encryptedValue =
            DomainRegistry
                .encryptionService()
                .encryptedValue(aPlainTextPassword);

        return encryptedValue;
    }
    
    protected void assertPasswordsNotSame(String aCurrentPassword, String aChangedPassword) {
        this.assertArgumentNotEquals(
                aCurrentPassword,
                aChangedPassword,
                "The password is unchanged.");
    }
    
    protected void assertPasswordNotWeak(String aPlainTextPassword) {
        this.assertArgumentFalse(
                DomainRegistry.passwordService().isWeak(aPlainTextPassword),
                "The password must be stronger.");
    }
    
    protected void assertUsernamePasswordNotSame(String aPlainTextPassword) {
    this.assertArgumentNotEquals(
            this.username(),
            aPlainTextPassword,
            "The username and password must not be the same.");
    }
//    
//    protected Enablement enablement() {
//        
//    }
//    
    protected void setEnablement(Enablement anEnablement) {
        this.assertArgumentNotNull(anEnablement, "The enablement is required.");

        this.enablement = anEnablement;
    }

//    public String internalAccessOnlyEncryptedPassword() {
//        
//    }
//    
//    protected String password() {
//        
//    }
    protected void setPassword(String aPassword) {
        this.password = aPassword;
    }

    
    protected void setPerson(Person aPerson) {
        this.assertArgumentNotNull(aPerson, "The person is required.");
        
        this.person = aPerson;
    }
    
    protected void protectPassword(String aCurrentPassword, String aChangedPassword) {
        this.assertPasswordsNotSame(aCurrentPassword, aChangedPassword);

        this.assertPasswordNotWeak(aChangedPassword);

        this.assertUsernamePasswordNotSame(aChangedPassword);

        this.setPassword(this.asEncryptedValue(aChangedPassword));
    }

    protected void setTenantId(TenantId aTenantId) {
        this.assertArgumentNotNull(aTenantId, "The tenantId is required.");

        this.tenantId = aTenantId;
    }

//    protected GroupMember toGroupMember() {
//        
//    }
//    
    protected void setUsername(String aUsername) {
        this.assertArgumentNotEmpty(aUsername, "The username is required.");
        this.assertArgumentLength(aUsername, 3, 250, "The username must be 3 to 250 characters.");

        this.username = aUsername;
    }
}
