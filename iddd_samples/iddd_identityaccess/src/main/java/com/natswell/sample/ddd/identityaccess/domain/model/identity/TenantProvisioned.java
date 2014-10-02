package com.natswell.sample.ddd.identityaccess.domain.model.identity;

import java.util.Date;

import com.natswell.sample.ddd.common.domain.model.DomainEventPublisher;
import com.natswell.sample.ddd.common.domain.model.DomainEvent;

/**
 * domain event
 * @author yoshiaki-n
 *
 */
public class TenantProvisioned implements DomainEvent {
    
    private int eventVersion;
    private Date occurredOn;
    private TenantId tenantId;

    public TenantProvisioned(TenantId aTenantId) {
        super();

        this.eventVersion = 1;
        this.occurredOn = new Date();
        this.tenantId = aTenantId;
    }

    @Override
    public int eventVersion() {
        return this.eventVersion;
    }

    @Override
    public Date occurredOn() {
        return this.occurredOn;
    }

    public TenantId tenantId() {
        return this.tenantId;
    }
}
