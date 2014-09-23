package com.natswell.sample.ddd.identityaccess.domain.model.access;

import java.util.Date;

import com.natswell.sample.ddd.common.domain.model.DomainEvent;
import com.natswell.sample.ddd.identityaccess.domain.model.identity.TenantId;

/**
 * Role エンティティのストック
 * @author yoshiaki-n
 *
 */
public class RoleProvisioned implements DomainEvent {

    private int eventVersion;
    private String name;
    private Date occurredOn;
    private TenantId tenantId;
    
    public RoleProvisioned(TenantId aTenantId, String aName) {
        super();
        
        this.eventVersion = 1;
        this.name = aName;
        this.occurredOn = new Date();
        this.tenantId = aTenantId;
    }
    
    @Override
    public int eventVersion() {
        return this.eventVersion;
    }

    public String name() {
        return this.name;
    }
    @Override
    public Date occurredOn() {
        return this.occurredOn;
    }

    public TenantId tenantId() {
        return this.tenantId;
    }
}
