package com.natswell.sample.ddd.identityaccess.resource;

import static org.junit.Assert.*;

import org.jboss.resteasy.client.ClientRequest;
import org.junit.Test;

import com.natswell.sample.ddd.common.media.RepresentationReader;
import com.natswell.sample.ddd.identityaccess.domain.model.DomainRegistry;
import com.natswell.sample.ddd.identityaccess.domain.model.identity.User;

public class UserResourceTest extends ResourceTestCase {

    public UserResourceTest() {
        super();
    }

    @Test
    public void testGetAuthenticUser() throws Exception {
        User user = this.userAggregate();
        DomainRegistry.userRepository().add(user);
        
        String url = "http://localhost:" + PORT + "/tenants/{tenantId}/users/{username}/autenticatedWith/{password}";

        System.out.println(">>> GET: " + url);
        ClientRequest request = new ClientRequest(url);
        request.pathParameter("tenantId", user.tenantId().id());
        request.pathParameter("username", user.username());
        request.pathParameter("password", FIXTURE_PASSWORD);
        
        String output = request.getTarget(String.class);
        System.out.println(output);
        
        RepresentationReader reader = new RepresentationReader(output);
        
        assertEquals(user.tenantId().id(), reader.stringValue("tenantId.id"));
        assertEquals(user.username(), reader.stringValue("username"));
        assertEquals(user.person().emailAddress().address(), reader.stringValue("emailAddress"));
    }

    @Test
    public void testGetAuthenticUserWrong() throws Exception {

    }
    
    @Test
    public void testGetUser() throws Exception {
        
    }
    
    @Test
    public void testGetNonExistingUser() throws Exception {
        
    }
    
    @Test
    public void testIsUserInRole() throws Exception {
        
    }
    
    @Test
    public void testIsUserNotInRole() throws Exception {
        
    }
}
