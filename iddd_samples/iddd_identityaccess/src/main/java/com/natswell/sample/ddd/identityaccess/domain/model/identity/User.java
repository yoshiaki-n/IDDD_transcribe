package com.natswell.sample.ddd.identityaccess.domain.model.identity;

import com.natswell.sample.ddd.common.domain.model.ConcurrencySafeEntity;

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
//    protected User(
//            TenantId aTenantId,
//            String aUsername,
//            String aPassword,
//            Enablement anEnablement,
//            Person aPerson) {
//        
//        this();
//        
//        // TODO
//        
//    }
//    
//    protected User() {
//        super();
//    }
//    
//    protected String asEncryptedValue(String aPlainTextPassword) {
//        
//    }
//    
//    protected void assertPasswordsNotSame(String aCurrentPassword, String aChangePassword) {
//        
//    }
//    
//    protected void assertPasswordNotWeak(String aPlainTextPassword) {
//        
//    }
//    
//    protected void assertUsernamePasswordNotSame(String aPlainTextPassword) {
//        
//    }
//    
//    protected Enablement enablement() {
//        
//    }
//    
//    protected void setEnablement(Enablement anEnablement) {
//        
//    }
//    
//    public String internalAccessOnlyEncryptedPassword() {
//        
//    }
//    
//    protected String password() {
//        
//    }
//    
//    protected void setPassword(String aPassword) {
//        
//    }
//    
//    protected void protectPassword(String aCurrentPassword, String aChangePassword) {
//        
//    }
//    
//    protected void setTenantId(TenantId aTenantId) {
//        
//    }
//    
//    protected GroupMember toGroupMember() {
//        
//    }
//    
//    protected void setUsername(String aUsername) {
//        
//    }
}
