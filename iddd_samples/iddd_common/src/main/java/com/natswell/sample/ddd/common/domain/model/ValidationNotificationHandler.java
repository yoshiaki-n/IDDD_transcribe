package com.natswell.sample.ddd.common.domain.model;

public interface ValidationNotificationHandler {

    public void handleError(String aNotificationMessage);
    
    public void handleError(String aNotification, Object anObject);
    
    public void handleInfo(String aNotificationMessage);
    
    public void handleInfo(String aNotificationMessage, Object anObject);
    
    public void handleWarning(String aNotificationMessage);
    
    public void handleWarning(String aNotificationMessage, Object anObject);
}
