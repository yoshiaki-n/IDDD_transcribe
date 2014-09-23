package com.natswell.sample.ddd.identityaccess.domain.model.identity;

import static org.hamcrest.CoreMatchers.nullValue;

import com.natswell.sample.ddd.common.domain.model.ConcurrencySafeEntity;

/**
 * entity
 * @author yoshiaki-n
 *
 */
public class Person extends ConcurrencySafeEntity {

    private static final long serialVersionUID = 1L;
    
    private ContactInformation contactInformation;
    private FullName name;
    private TenantId tenantId;
    private User user;
    
    public Person(
            TenantId aTenantId,
            FullName aName, 
            ContactInformation aContactInformation) {
        
        this();
        
        this.setContactInformation(aContactInformation);
        this.setName(aName);
        this.setTenantId(aTenantId);
    }
//
//    public void changeContactInformation(ContactInformation aContactInformation) {
//
//    }
//
//    public void changeName(FullName aName) {
//        
//    }
//
    public ContactInformation contactInformation() {
        return this.contactInformation;
    }

    public EmailAddress emailAddress() {
        return this.contactInformation().emailAddress();
    }
//
//    public FullName name() {
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
    
    protected Person() {
        super();
    }

    protected void setContactInformation(ContactInformation aContactInformation) {
        this.assertArgumentNotNull(aContactInformation, "The person contact information is required.");

        this.contactInformation = aContactInformation;
    }

    protected void setName(FullName aName) {
        this.assertArgumentNotNull(aName, "The person name is required.");

        this.name = aName;
    }

//    protected TenantId tenantId() {
//        
//    }

    protected void setTenantId(TenantId aTenantId) {
        this.assertArgumentNotNull(aTenantId, "The tenantId is required.");

        this.tenantId = aTenantId;
    }

//    protected User user() {
//        
//    }
//
//    public void internalOnlySetUser(User aUser) {
//        
//    }
}
