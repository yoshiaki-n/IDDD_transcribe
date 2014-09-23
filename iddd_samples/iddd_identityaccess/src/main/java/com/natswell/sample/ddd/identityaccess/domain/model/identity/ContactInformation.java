package com.natswell.sample.ddd.identityaccess.domain.model.identity;

import java.io.Serializable;

import com.natswell.sample.ddd.common.AssertionConcern;

/**
 * value object
 * @author yoshiaki-n
 *
 */
public class ContactInformation extends AssertionConcern implements Serializable {

    private static final long serialVersionUID = 1L;

    private EmailAddress emailAddress;
    private PostalAddress postalAddress;
    private Telephone primaryTelephone;
    private Telephone secondaryTelephone;

    public ContactInformation(
            EmailAddress anEmailAddress,
            PostalAddress aPostalAddress,
            Telephone aPrimaryTelephone,
            Telephone aSecondaryTelephone) {

        super();

        this.setEmailAddress(anEmailAddress);
        this.setPostalAddress(aPostalAddress);
        this.setPrimaryTelephone(aPrimaryTelephone);
        this.setSecondaryTelephone(aSecondaryTelephone);
    }

//    public ContactInformation(ContactInformation aContactInformation) {
//        this(aContactInformation.emailAddress(),
//             aContactInformation.postalAddress(),
//             aContactInformation.primaryTelephone(),
//             aContactInformation.secondaryTelephone());
//    }
//
//    public ContactInformation changeEmailAddress(EmailAddress anEmailAddress) {
//    }
//
//    public ContactInformation changePostalAddress(PostalAddress aPostalAddress) {
//    }
//
//    public ContactInformation changePrimaryTelephone(Telephone aTelephone) {
//    }
//
//    public ContactInformation changeSecondaryTelephone(Telephone aTelephone) {
//    }

    public EmailAddress emailAddress() {
        return this.emailAddress;
    }

//    public PostalAddress postalAddress() {
//    }
//
//    public Telephone primaryTelephone() {
//    }
//
//    public Telephone secondaryTelephone() {
//    }
//
//    @Override
//    public boolean equals(Object anObject) {
//    }
//
//    @Override
//    public int hashCode() {
//    }
//
//    @Override
//    public String toString() {
//    }
//
//    protected ContactInformation() {
//        super();
//    }

    private void setEmailAddress(EmailAddress anEmailAddress) {
        this.assertArgumentNotNull(anEmailAddress, "The email address is required.");

        this.emailAddress = anEmailAddress;
    }

    private void setPostalAddress(PostalAddress aPostalAddress) {
        this.assertArgumentNotNull(aPostalAddress, "The postal address is required.");

        this.postalAddress = aPostalAddress;
    }

    private void setPrimaryTelephone(Telephone aPrimaryTelephone) {
        this.assertArgumentNotNull(aPrimaryTelephone, "The primary telephone is required.");
        this.primaryTelephone = aPrimaryTelephone;
    }

    private void setSecondaryTelephone(Telephone aSecondaryTelephone) {
        this.secondaryTelephone = aSecondaryTelephone;
    }
}
