package com.natswell.sample.ddd.identityaccess.domain.model.identity;

import java.io.Serializable;
import java.util.regex.Pattern;

import com.natswell.sample.ddd.common.AssertionConcern;

public class EmailAddress extends AssertionConcern implements Serializable {

    private static final long serialVersionUID = 1L;

    private String address;
    
    public EmailAddress(String anAddress) {
        super();
        
        this.setAddress(anAddress);
    }
//
//    public EmailAddress(EmailAddress anEmailAddress) {
//        
//    }

    public String address() {
        return this.address;
    }
    
//    @Override
//    public boolean equals(Object obj) {
//        // TODO Auto-generated method stub
//        return super.equals(obj);
//    }
//    
//    @Override
//    public int hashCode() {
//        // TODO Auto-generated method stub
//        return super.hashCode();
//    }
//    
//    @Override
//    public String toString() {
//        // TODO Auto-generated method stub
//        return super.toString();
//    }
//    
//    protected EmailAddress() {
//        super();
//    }
//
    private void setAddress(String anAddress) {
        this.assertArgumentNotEmpty(anAddress, "The email address is required.");
        this.assertArgumentLength(anAddress, 1, 100, "Email address must be 100 characters or less.");
        this.assertArgumentTrue(
                Pattern.matches("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", anAddress),
                "Email address format is invalid.");

        this.address = anAddress;
    }
}
