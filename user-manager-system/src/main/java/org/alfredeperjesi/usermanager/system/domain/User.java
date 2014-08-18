package org.alfredeperjesi.usermanager.system.domain;

import java.util.Date;

import org.springframework.util.Assert;

import com.google.common.base.Optional;

public class User {
    private UserId id;

    private UserType type;

    private FirstName firstName;

    private LastName lastName;

    private Title title;

    private DateOfBirth dateOfBirth;

    private EmailAddress emailAddress;

    private Password password;

    private Optional<Address> homeAddress;

    private Optional<Address> billingAddress;

    private User(UserId id, UserType type, FirstName firstName, LastName lastName, Title title, DateOfBirth dateOfBirth, EmailAddress emailAddress, Password password, Optional<Address> homeAddress, Optional<Address> billingAddress) {
        Assert.notNull(id, "Id cannot be null!");
        Assert.notNull(type, "Type cannot be null!");
        Assert.notNull(firstName, "First name cannot be null!");
        Assert.notNull(lastName, "Last name cannot be null!");
        Assert.notNull(title, "Title cannot be null!");
        Assert.notNull(dateOfBirth, "Date of birth cannot be null!");
        Assert.notNull(password, "Password cannot be null!");
        Assert.notNull(emailAddress, "Email address cannot be null!");
        Assert.isTrue(type.validate(homeAddress, billingAddress), "Home address and billing address cannot be null!");

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
        return "User{" +
                "id=" + id +
                ", type=" + type +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", title=" + title +
                ", dateOfBirth=" + dateOfBirth +
                ", emailAddress=" + emailAddress +
                ", homeAddress=" + homeAddress +
                ", billingAddress=" + billingAddress +
                '}';
    }

    public UserId getId() {
        return id;
    }

    public UserType getType() {
        return type;
    }

    public FirstName getFirstName() {
        return firstName;
    }

    public LastName getLastName() {
        return lastName;
    }

    public Title getTitle() {
        return title;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public Password getPassword() {
        return password;
    }

    public Optional<Address> getHomeAddress() {
        return homeAddress;
    }

    public Optional<Address> getBillingAddress() {
        return billingAddress;
    }

    public static Builder userBuilder() {
        return new Builder();
    }

    public boolean isSubscriber() {
        return type.isSubscriber();
    }

    public boolean isAdministrator() {
        return type.isAdministrator();
    }

    public boolean isSuperUser() {
        return type.isSuperUser();
    }

    public static class Builder {
        private UserId id;

        private UserType type;

        private FirstName firstName;

        private LastName lastName;

        private Title title;

        private DateOfBirth dateOfBirth;

        private EmailAddress emailAddress;

        private Password password;

        private Optional<Address> homeAddress = Optional.absent();

        private Optional<Address> billingAddress = Optional.absent();

        public Builder withId(Integer id) {
            this.id = new UserId(id);
            return this;
        }

        public Builder withType(String type) {
            this.type = UserType.valueOf(type);
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = new FirstName(firstName);
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = new LastName(lastName);
            return this;
        }

        public Builder withTitle(String title) {
            this.title = new Title(title);
            return this;
        }

        public Builder withDateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = new DateOfBirth(dateOfBirth);
            return this;
        }

        public Builder withEmailAddress(String emailAddress) {
            this.emailAddress = new EmailAddress(emailAddress);
            return this;
        }

        public Builder withPassword(String password) {
            this.password = new Password(password);
            return this;
        }

        public Builder withHomeAddress(String homeAddress) {
            if (homeAddress != null) {
                this.homeAddress = Optional.of(new Address(homeAddress));
            }
            return this;
        }

        public Builder withBillingAddress(String billingAddress) {
            if (billingAddress != null) {
                this.billingAddress = Optional.of(new Address(billingAddress));
            }
            return this;
        }

        public User build() {
            return new User(id, type, firstName, lastName, title, dateOfBirth, emailAddress, password, homeAddress, billingAddress);
        }
    }
}
