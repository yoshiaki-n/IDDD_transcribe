package com.natswell.sample.ddd.common.domain.model;

public class ConcurrencySafeEntity extends Entity {

    private static final long serialVersionUID = 1L;

    private int concurrencyVersion;
    
    public ConcurrencySafeEntity() {
        super();
    }
    
    public int concurrencyVersion() {
        return this.concurrencyVersion;
    }
    
    public void setConCurrencyVersion(int aVersion) {
        this.failWhenConcurrencyViolation(aVersion);
        this.concurrencyVersion = aVersion;
    }
    
    public void failWhenConcurrencyViolation(int aVersion) {
        if(aVersion != this.concurrencyVersion()) {
            throw new IllegalStateException(
                    "Concurrency Violation: State data detected. Entity was already modified.");
        }
    }
}
