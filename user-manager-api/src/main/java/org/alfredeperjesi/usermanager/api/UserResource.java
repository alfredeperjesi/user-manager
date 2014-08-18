package org.alfredeperjesi.usermanager.api;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User resource", description = "Resource which has identifier")
public class UserResource implements Serializable {
    @ApiModelProperty(value = "Id", required = true)
    private Integer id;

    @ApiModelProperty(value = "Type, values: SUBSCRIBER, ADMINISTRATOR, SUPER_USER", required = true)
    private UserTypeResource type;

    @ApiModelProperty(value = "First name", required = true)
    private String firstName;

    @ApiModelProperty(value = "Last name", required = true)
    private String lastName;

    @ApiModelProperty(value = "Title", required = true)
    private String title;

    @ApiModelProperty(value = "Date of birth, cannot be in the future", required = true)
    private Date dateOfBirth;

    @ApiModelProperty(value = "Email address, must be valid", required = true)
    private String emailAddress;

    @ApiModelProperty(value = "Password, at least 5 length", required = true)
    private String password;

    @ApiModelProperty(value = "Home address. Required only for subscribers")
    private String homeAddress;

    @ApiModelProperty(value = "Billing address. Required only for subscribers")
    private String billingAddress;

    @JsonCreator
    public UserResource() {
    }

    public UserResource(Integer id, UserTypeResource type, String firstName, String lastName, String title, Date dateOfBirth, String emailAddress, String password, String homeAddress, String billingAddress) {
        this.id = id;
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
        return "UserResource{" +
                "id=" + id +
                ", type=" + type +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", emailAddress='" + emailAddress + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
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

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
