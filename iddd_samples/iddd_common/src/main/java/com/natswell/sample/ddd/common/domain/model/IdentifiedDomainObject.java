package com.natswell.sample.ddd.common.domain.model;

import java.io.Serializable;

import com.natswell.sample.ddd.common.AssertionConcern;

public class IdentifiedDomainObject extends AssertionConcern implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private long id;
    
    protected IdentifiedDomainObject() {
        super();
        this.setId(-1);
    }
    
    protected long id() {
        return this.id;
    }
    
    private void setId(long anId) {
        this.id = anId;
    }
}
