package com.natswell.sample.ddd.common.domain.model;

import java.util.Date;

public interface DomainEvent {

    public int eventVersion();
    
    /**
     * イベント発生時間
     * @return
     */
    public Date occurredOn();
}
