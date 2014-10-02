package com.natswell.sample.ddd.identityaccess.domain.model.identity;

import com.natswell.sample.ddd.common.domain.model.DomainEventPublisher;
import com.natswell.sample.ddd.identityaccess.domain.model.access.RoleRepository;

public class TenantProvisioningService {

    private RoleRepository roleRepository;
    private TenantRepository tenantRepository;
    private UserRepository userRepository;
    
//    public TenantProvisioningService(
//            TenantRepository aTenantRepository,
//            UserRepository aUserRepository,
//            RoleRepository aRoleRepository) {
//        
//        super();
//        
//        this.roleRepository = aRoleRepository;
//        this.tentantRepository = aTenantRepository;
//        this.userRepository = aUserRepository;
//    }
    
    public Tenant provisionTenant(
            String aTenantName,
            String aTenantDescription,
            FullName anAdministorName,
            EmailAddress anEmailAddress,
            PostalAddress aPostalAddress,
            Telephone aPrimaryTelephone,
            Telephone aSecondaryTelephone) {
        
        try {
            Tenant tenant = new Tenant(
                    this.tenantRepository().nextIdentity(),
                    aTenantName,
                    aTenantDescription,
                    true); // must be active to register admin
            
            this.tenantRepository().add(tenant);
            
            this.registerAdministratorFor(
                    tenant, 
                    anAdministorName, 
                    anEmailAddress, 
                    aPostalAddress, 
                    aPrimaryTelephone, 
                    aSecondaryTelephone);
            
            DomainEventPublisher
                .instance()
                .publish(new TenantProvisioned(
                        tenant.tenantId()));
            
            return tenant;
        } catch (Throwable t) {
            throw new IllegalStateException(
                    "Cannot provision tenant because: "
                    + t.getMessage());
        }
    }
    
    private void registerAdministratorFor(
            Tenant aTenant,
            FullName anAdministorName,
            EmailAddress anEmailAddress,
            PostalAddress aPostalAddress,
            Telephone aPrimaryTelephone,
            Telephone aSecondaryTelephone) {
        // TODO
    }

//    private RoleRepository roleRepository() {
//        return this.roleRepository;
//    }
    
    private TenantRepository tenantRepository() {
        return this.tenantRepository;
    }
    
//    private UserRepository userRepository() {
//        return this.userRepository;
//    }
//    
}
