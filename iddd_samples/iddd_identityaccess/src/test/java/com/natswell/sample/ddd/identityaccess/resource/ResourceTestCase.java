package com.natswell.sample.ddd.identityaccess.resource;

import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;
import org.junit.After;
import org.junit.Before;

import com.natswell.sample.ddd.identityaccess.application.ApplicationServiceTest;

public abstract class ResourceTestCase extends ApplicationServiceTest {
    
    protected final static int PORT = 8081;
    
    // RESTEasy の JAX-RS組み込みサーバー
    private TJWSEmbeddedJaxrsServer server;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    
    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        
    }
}
