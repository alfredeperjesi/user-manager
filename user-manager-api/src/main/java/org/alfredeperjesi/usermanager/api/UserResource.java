package org.alfredeperjesi.usermanager.api;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;
import java.util.Date;

public class UserResource implements Serializable {
    private Integer id;
    private UserTypeResource type;
    private String firstName;
    private String lastName;
    private String title;
    private Date dateOfBirth;
    private String emailAddress;
    private String homeAddress;
    private String billingAddress;
    private String password;

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
