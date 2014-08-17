package org.alfredeperjesi.usermanager.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "Create user resource", description = "Resource which used for creation only")
public class CreateUserResource {
    @ApiModelProperty(value = "Type", required = true)
    private UserTypeResource type;
    @ApiModelProperty(value = "First name", required = true)
    private String firstName;
    @ApiModelProperty(value = "Last name", required = true)
    private String lastName;
    @ApiModelProperty(value = "Title", required = true)
    private String title;
    @ApiModelProperty(value = "Date of birth", required = true, notes = "Cannot be in the future.")
    private Date dateOfBirth;
    @ApiModelProperty(value = "Email address", required = true, notes = "Must be valid.")
    private String emailAddress;
    @ApiModelProperty(value = "Password", required = true, notes = "At least 5 length")
    private String password;
    @ApiModelProperty(value = "Home address. Required only for subscribers")
    private String homeAddress;
    @ApiModelProperty(value = "Billing address. Required only for subscribers")
    private String billingAddress;

    @JsonCreator
    private CreateUserResource() {
    }

    public CreateUserResource(UserTypeResource type, String firstName, String lastName, String title, Date dateOfBirth, String emailAddress, String password, String homeAddress, String billingAddress) {
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.password = password;
        this.homeAddress = homeAddress;
        this.billingAddress = billingAddress;
    }

    @Override
    public String toString() {
        return "CreateUserResource{" +
                "type=" + type +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", emailAddress='" + emailAddress + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                '}';
    }

    public UserTypeResource getType() {
        return type;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getBillingAddress() {
        return billingAddress;
    }
}
