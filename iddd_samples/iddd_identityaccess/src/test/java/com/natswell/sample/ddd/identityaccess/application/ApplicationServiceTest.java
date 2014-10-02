package com.natswell.sample.ddd.identityaccess.application;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.CleanupFailureDataAccessException;

import com.natswell.sample.ddd.common.event.EventStore;
import com.natswell.sample.ddd.common.persistence.CleanableStore;
import com.natswell.sample.ddd.identityaccess.domain.model.DomainRegistry;
import com.natswell.sample.ddd.identityaccess.domain.model.access.Role;
import com.natswell.sample.ddd.identityaccess.domain.model.identity.ContactInformation;
import com.natswell.sample.ddd.identityaccess.domain.model.identity.EmailAddress;
import com.natswell.sample.ddd.identityaccess.domain.model.identity.Enablement;
import com.natswell.sample.ddd.identityaccess.domain.model.identity.FullName;
import com.natswell.sample.ddd.identityaccess.domain.model.identity.Group;
import com.natswell.sample.ddd.identityaccess.domain.model.identity.Person;
import com.natswell.sample.ddd.identityaccess.domain.model.identity.PostalAddress;
import com.natswell.sample.ddd.identityaccess.domain.model.identity.RegistrationInvitation;
import com.natswell.sample.ddd.identityaccess.domain.model.identity.Telephone;
import com.natswell.sample.ddd.identityaccess.domain.model.identity.Tenant;
import com.natswell.sample.ddd.identityaccess.domain.model.identity.User;

public abstract class ApplicationServiceTest {
    
    protected static final String FIXTURE_GROUP_NAME = "Test Group";
    protected static final String FIXTURE_PASSWORD = "SecretPassword";
    protected static final String FIXTURE_ROLE_NAME = "Test Role";
    protected static final String FIXTURE_TENANT_DESCRIPTION = "This is a test tenant.";
    protected static final String FIXTURE_TENANT_NAME = "Test Tenant";
    protected static final String FIXTURE_USER_EMAIL_ADDRESS = "jdoe@natswell.com";
    protected static final String FIXTURE_USER_EMAIL_ADDRESS2 = "zdoe@natswell.com";
    protected static final String FIXTURE_USERNAME = "jdoe";
    protected static final String FIXTURE_USERNAME2 = "zdoe";

    protected Tenant activeTenant;
    protected ApplicationContext applicationContext;
    protected EventStore eventStore;
    
    public ApplicationServiceTest() {
        super();
    }
    
//    protected Group group1Aggregate() {
//        return this.tenantAggregate()
//                .provisionGroup(FIXTURE_GROUP_NAME + " 1", "A test group 1.");
//    }
//    
//    protected Group group2Aggregate() {
//        return this.tenantAggregate()
//                .provisionGroup(FIXTURE_GROUP_NAME + " 2", "A test group 2.");
//    }
//    
//    protected Role roleAggregate() {
//        return this.tenantAggregate()
//                .provisionRole(FIXTURE_ROLE_NAME, "A test role.", true);
//    }
    
    protected Tenant tenantAggregate() {
        if (activeTenant == null) {
            activeTenant =
                    DomainRegistry
                        .tenantProvisioningService()
                        .provisionTenant(
                                FIXTURE_TENANT_NAME,
                                FIXTURE_TENANT_DESCRIPTION,
                                new FullName("John", "Doe"),
                                new EmailAddress(FIXTURE_USER_EMAIL_ADDRESS),
                                new PostalAddress(
                                        "123 Pearl Street",
                                        "Boulder",
                                        "CO",
                                        "80301",
                                        "US"),
                                new Telephone("303-555-1210"),
                                new Telephone("303-555-1212"));
        }
        
        return activeTenant;
    }

    protected User userAggregate() {
        Tenant tenant = this.tenantAggregate();
        
        RegistrationInvitation invitation = 
                tenant.offerRegistrationInvitation("open-ended").openEnded();
        
        User user = 
                tenant.registerUser(
                        invitation.invitationId(),
                        "jdoe",
                        FIXTURE_PASSWORD,
                        Enablement.indefiniteEnablement(),
                        new Person(
                                tenant.tenantId(),
                                new FullName("John", "Doe"),
                                new ContactInformation(
                                        new EmailAddress(FIXTURE_USER_EMAIL_ADDRESS),
                                        new PostalAddress(
                                                "123 Pearl Street",
                                                "Boulder",
                                                "CO",
                                                "80301",
                                                "US"),
                                        new Telephone("303-555-1210"),
                                        new Telephone("303-555-1212"))));
        
        return user;
    }
    
    @Before
    public void setUp() throws Exception {
    }
    
    
    @After
    public void tearDown() throws Exception {
        
    }
    
    private void clean(CleanableStore aCleanableStore) {
        aCleanableStore.clean();
    }
}
