package org.alfredeperjesi.usermanager.system.domain;

import static org.alfredeperjesi.usermanager.system.Fixtures.ADMINISTRATOR;
import static org.alfredeperjesi.usermanager.system.Fixtures.BILLING_ADDRESS;
import static org.alfredeperjesi.usermanager.system.Fixtures.DATE_OF_BIRTH;
import static org.alfredeperjesi.usermanager.system.Fixtures.EMAIL_ADDRESS;
import static org.alfredeperjesi.usermanager.system.Fixtures.FIRST_NAME;
import static org.alfredeperjesi.usermanager.system.Fixtures.HOME_ADDRESS;
import static org.alfredeperjesi.usermanager.system.Fixtures.ID;
import static org.alfredeperjesi.usermanager.system.Fixtures.LAST_NAME;
import static org.alfredeperjesi.usermanager.system.Fixtures.PASSWORD;
import static org.alfredeperjesi.usermanager.system.Fixtures.SUBSCRIBER;
import static org.alfredeperjesi.usermanager.system.Fixtures.SUPER_USER;
import static org.alfredeperjesi.usermanager.system.Fixtures.TITLE;
import static org.alfredeperjesi.usermanager.system.domain.User.userBuilder;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void userThrowsExceptionWhenIdIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Id cannot be null!");

        userBuilder().build();
    }

    @Test
    public void userThrowsExceptionWhenTypeIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Type cannot be null!");

        userBuilder()
                .withId(ID)
                .build();
    }

    @Test
    public void userThrowsExceptionWhenFirstNameIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("First name cannot be null!");

        userBuilder()
                .withId(ID)
                .withType(SUBSCRIBER)
                .build();
    }

    @Test
    public void userThrowsExceptionWhenLastNameIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Last name cannot be null!");

        userBuilder()
                .withId(ID)
                .withType(SUBSCRIBER)
                .withFirstName(FIRST_NAME)
                .build();
    }

    @Test
    public void userThrowsExceptionWhenTitleIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Title cannot be null!");

        userBuilder()
                .withId(ID)
                .withType(SUBSCRIBER)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .build();
    }

    @Test
    public void userThrowsExceptionWhenDayOfBirthIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Date of birth cannot be null!");

        userBuilder()
                .withId(ID)
                .withType(SUBSCRIBER)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withTitle(TITLE)
                .build();
    }

    @Test
    public void userThrowsExceptionWhenPasswordIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Password cannot be null!");

        userBuilder()
                .withId(ID)
                .withType(SUBSCRIBER)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withTitle(TITLE)
                .withDateOfBirth(DATE_OF_BIRTH)
                .build();
    }

    @Test
    public void userThrowsExceptionWhenEmailAddressIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Email address cannot be null!");

        userBuilder()
                .withId(ID)
                .withType(SUBSCRIBER)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withTitle(TITLE)
                .withDateOfBirth(DATE_OF_BIRTH)
                .withPassword(PASSWORD)
                .build();
    }

    @Test
    public void userThrowsExceptionWhenSubscriberAndHomeAddressIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Home address and billing address cannot be null!");

        userBuilder()
                .withId(ID)
                .withType(SUBSCRIBER)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withTitle(TITLE)
                .withDateOfBirth(DATE_OF_BIRTH)
                .withPassword(PASSWORD)
                .withEmailAddress(EMAIL_ADDRESS)
                .build();
    }

    @Test
    public void userThrowsExceptionWhenSubscriberAndBillingAddressIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Home address and billing address cannot be null!");

        userBuilder()
                .withId(ID)
                .withType(SUBSCRIBER)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withTitle(TITLE)
                .withDateOfBirth(DATE_OF_BIRTH)
                .withPassword(PASSWORD)
                .withEmailAddress(EMAIL_ADDRESS)
                .withHomeAddress(HOME_ADDRESS)
                .build();
    }

    @Test
    public void userConstructWhenSubscriberAndAllTheFieldsAreGiven() {
        User user = userBuilder()
                .withId(ID)
                .withType(SUBSCRIBER)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withTitle(TITLE)
                .withDateOfBirth(DATE_OF_BIRTH)
                .withPassword(PASSWORD)
                .withEmailAddress(EMAIL_ADDRESS)
                .withHomeAddress(HOME_ADDRESS)
                .withBillingAddress(BILLING_ADDRESS)
                .build();

        verifyUser(user, SUBSCRIBER);
        assertThat(user.getHomeAddress().get().getValue(), equalTo(HOME_ADDRESS));
        assertThat(user.getBillingAddress().get().getValue(), equalTo(BILLING_ADDRESS));
    }

    @Test
    public void userConstructWhenAdministratorAndAllTheFieldsAreGivenExceptHomeAddress() {
        User user = userBuilder()
                .withId(ID)
                .withType(ADMINISTRATOR)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withTitle(TITLE)
                .withDateOfBirth(DATE_OF_BIRTH)
                .withPassword(PASSWORD)
                .withEmailAddress(EMAIL_ADDRESS)
                .withBillingAddress(BILLING_ADDRESS)
                .build();

        verifyUser(user, ADMINISTRATOR);
        assertThat(user.getHomeAddress().isPresent(), equalTo(false));
        assertThat(user.getBillingAddress().get().getValue(), equalTo(BILLING_ADDRESS));
    }

    @Test
    public void userConstructWhenAdministratorAndAllTheFieldsAreGivenExceptBillingAddress() {
        User user = userBuilder()
                .withId(ID)
                .withType(ADMINISTRATOR)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withTitle(TITLE)
                .withDateOfBirth(DATE_OF_BIRTH)
                .withPassword(PASSWORD)
                .withEmailAddress(EMAIL_ADDRESS)
                .withHomeAddress(HOME_ADDRESS)
                .build();

        verifyUser(user, ADMINISTRATOR);
        assertThat(user.getHomeAddress().get().getValue(), equalTo(HOME_ADDRESS));
        assertThat(user.getBillingAddress().isPresent(), equalTo(false));
    }

    @Test
    public void userConstructWhenSuperUserAndAllTheFieldsAreGivenExceptHomeAddress() {
        User user = userBuilder()
                .withId(ID)
                .withType(SUPER_USER)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withTitle(TITLE)
                .withDateOfBirth(DATE_OF_BIRTH)
                .withPassword(PASSWORD)
                .withEmailAddress(EMAIL_ADDRESS)
                .withBillingAddress(BILLING_ADDRESS)
                .build();

        verifyUser(user, SUPER_USER);
        assertThat(user.getHomeAddress().isPresent(), equalTo(false));
        assertThat(user.getBillingAddress().get().getValue(), equalTo(BILLING_ADDRESS));
    }

    @Test
    public void userConstructWhenSuperUserAndAllTheFieldsAreGivenExceptBillingAddress() {
        User user = userBuilder()
                .withId(ID)
                .withType(SUPER_USER)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withTitle(TITLE)
                .withDateOfBirth(DATE_OF_BIRTH)
                .withPassword(PASSWORD)
                .withEmailAddress(EMAIL_ADDRESS)
                .withHomeAddress(HOME_ADDRESS)
                .build();

        verifyUser(user, SUPER_USER);
        assertThat(user.getHomeAddress().get().getValue(), equalTo(HOME_ADDRESS));
        assertThat(user.getBillingAddress().isPresent(), equalTo(false));
    }

    private void verifyUser(User user, String expectedType) {
        assertThat(user.getId().getValue(), equalTo(ID));
        assertThat(user.getType().name(), equalTo(expectedType));
        assertThat(user.getFirstName().getValue(), equalTo(FIRST_NAME));
        assertThat(user.getLastName().getValue(), equalTo(LAST_NAME));
        assertThat(user.getTitle().getValue(), equalTo(TITLE));
        assertThat(user.getDateOfBirth().getValue(), equalTo(DATE_OF_BIRTH));
        assertThat(user.getPassword().getValue(), equalTo(PASSWORD));
        assertThat(user.getEmailAddress().getValue(), equalTo(EMAIL_ADDRESS));
    }
}
