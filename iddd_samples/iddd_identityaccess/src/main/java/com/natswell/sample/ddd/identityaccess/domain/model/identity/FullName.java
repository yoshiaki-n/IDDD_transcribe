package com.natswell.sample.ddd.identityaccess.domain.model.identity;

import java.io.Serializable;
import java.util.regex.Pattern;

import com.natswell.sample.ddd.common.AssertionConcern;

public class FullName extends AssertionConcern implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    
    public FullName(String aFirstName, String aLastName) {
        super();
        
        this.setFiestName(aFirstName);
        this.setLastName(aLastName);
    }
    
//    public FullName(FullName aFullName) {
//        this(aFullName.firstName(), aFullName.lastName());
//    }
//    
//    public String asFormattedname() {
//        return this.firstName() + " " + this.lastName();
//    }
//    
//    public String firstName() {
//        return this.firstName;
//    }
//    
//    public String lastName() {
//        return this.lastName;
//    }
//    
//    public FullName withChangedFirstName(String aFirstName) {
//        return new FullName(aFirstName, this.lastName());
//    }
//    
//    public FullName withChangedLastName(String aLastName) {
//        return new FullName(this.firstName(), aLastName);
//    }
//    
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
//    protected FullName() {
//        super();
//    }
    
    private void setFiestName(String aFirstName) {
        this.assertArgumentNotEmpty(aFirstName, "First name is required.");
        this.assertArgumentLength(aFirstName, 1, 50, "First name must be 50 characters or less.");
        this.assertArgumentTrue(Pattern.matches("[A-Z][a-z]*", aFirstName), 
                "First name must be at least one character in length, starting with a capital letter.");
        
        this.firstName = aFirstName;
    }
    
    private void setLastName(String aLastName) {
        this.assertArgumentNotEmpty(aLastName, "The last name is required.");
        this.assertArgumentLength(aLastName, 1, 50, "The last name must be 50 characters or less.");
        this.assertArgumentTrue(
                Pattern.matches("^[a-zA-Z'][ a-zA-Z'-]*[a-zA-Z']?", aLastName),
                "Last name must be at least one character in length.");

        this.lastName = aLastName;
    }
}
