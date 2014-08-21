package com.natswell.sample.ddd.common.domain.model;

public interface DomainEventSubscriber<T> {

    public void handleEvent(final T aDoaminEvent);
    
    public Class<T> subscribedToEventType();
}
