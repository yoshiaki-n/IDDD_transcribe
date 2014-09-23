package com.natswell.sample.ddd.identityaccess.domain.model.identity;

import java.io.Serializable;
import java.util.Date;

import com.natswell.sample.ddd.common.AssertionConcern;

/**
 * value object
 * @author yoshiaki-n
 *
 */
public class Enablement extends AssertionConcern implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean enabled;
    private Date endDate;
    private Date startDate;
    
    public static Enablement indefiniteEnablement() {
        return new Enablement(true ,null ,null);
    }
    
    public Enablement(boolean anEnabled, Date aStartDate, Date anEndDate) {
        super();
        
        if (aStartDate != null || anEndDate != null) {
            this.assertArgumentNotNull(aStartDate, "The start date must be provided.");
            this.assertArgumentNotNull(anEndDate, "The end date must be provided.");
            this.assertArgumentFalse(aStartDate.after(anEndDate), "Enablement start and/or end date is invalid.");
        }
        
        this.setEnabled(anEnabled);
        this.setEndDate(anEndDate);
        this.setStartDate(aStartDate);
    }
    
    // TODO
    
    
    protected Enablement() {
        super();
    }

    private void setEnabled(boolean anEnabled) {
        this.enabled = anEnabled;
    }

    private void setEndDate(Date anEndDate) {
        this.endDate = anEndDate;
    }

    private void setStartDate(Date aStartDate) {
        this.startDate = aStartDate;
    }
}

