package com.natswell.sample.ddd.identityaccess.domain.model.access;

import java.util.Collection;

import com.natswell.sample.ddd.identityaccess.domain.model.identity.TenantId;

public interface RoleRepository {

    public void add(Role aRole);
    
    public Collection<Role> allRoles(TenantId aTenetId);
    
    public void remove(Role aRole);
    
    public Role roleNamed(TenantId aTentId, String aRoleName);
}
