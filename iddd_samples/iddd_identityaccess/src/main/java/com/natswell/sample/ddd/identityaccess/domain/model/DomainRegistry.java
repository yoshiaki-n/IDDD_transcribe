package com.natswell.sample.ddd.identityaccess.domain.model;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.natswell.sample.ddd.identityaccess.domain.model.identity.UserRepository;

public class DomainRegistry implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    
//    public static AuthenticationService authenticationService() {
//    }
//
//    public static AuthorizationService authorizationService() {
//    }
//
//    public static EncryptionService encryptionService() {
//    }
//
//    public static GroupMemberService groupMemberService() {
//    }
//
//    public static GroupRepository groupRepository() {
//    }
//
//    public static PasswordService passwordService() {
//    }
//
//    public static RoleRepository roleRepository() {
//    }
//
//    public static TenantProvisioningService tenantProvisioningService() {
//    }
//
//    public static TenantRepository tenantRepository() {
//    }

    public static UserRepository userRepository() {
        return (UserRepository) applicationContext.getBean("userRepository");
    }

    @Override
    public synchronized void setApplicationContext(ApplicationContext anApplicationContext)
            throws BeansException {
        if (DomainRegistry.applicationContext == null) {
            DomainRegistry.applicationContext = anApplicationContext;
        }
    }

}
