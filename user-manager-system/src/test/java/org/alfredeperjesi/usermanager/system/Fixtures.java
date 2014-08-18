package org.alfredeperjesi.usermanager.system;

import static org.alfredeperjesi.usermanager.system.domain.User.userBuilder;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.alfredeperjesi.usermanager.api.CreateUserResource;
import org.alfredeperjesi.usermanager.api.UserResource;
import org.alfredeperjesi.usermanager.api.UserTypeResource;
import org.alfredeperjesi.usermanager.system.domain.User;
import org.alfredeperjesi.usermanager.system.domain.UserType;

public class Fixtures {

    public static final String FIRST_NAME = "first";

    public static final String LAST_NAME = "last";

    public static final String TITLE = "title";

    public static final Date DATE_OF_BIRTH = getDate(-1);

    public static final String PASSWORD = "password";

    public static final String EMAIL_ADDRESS = "email@email.com";

    public static final String BILLING_ADDRESS = "billing";

    public static final String HOME_ADDRESS = "home";

    public static final int ID = 1;

    public static final String SUBSCRIBER = UserType.SUBSCRIBER.name();

    public static final String ADMINISTRATOR = UserType.ADMINISTRATOR.name();

    public static final String SUPER_USER = UserType.SUPER_USER.name();

    public static User USER = userBuilder()
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

    public static User USER_UPDATED = userBuilder()
            .withId(ID)
            .withType(ADMINISTRATOR)
            .withFirstName(FIRST_NAME + "a")
            .withLastName(LAST_NAME)
            .withTitle(TITLE)
            .withDateOfBirth(DATE_OF_BIRTH)
            .withPassword(PASSWORD)
            .withEmailAddress(EMAIL_ADDRESS)
            .withBillingAddress(BILLING_ADDRESS)
            .build();

    public static final CreateUserResource CREATE_USER_RESOURCE = new CreateUserResource(
            UserTypeResource.ADMINISTRATOR,
            FIRST_NAME,
            LAST_NAME,
            TITLE,
            DATE_OF_BIRTH,
            EMAIL_ADDRESS,
            PASSWORD,
            HOME_ADDRESS,
            BILLING_ADDRESS);

    public static final UserResource USER_RESOURCE = new UserResource(
            ID,
            UserTypeResource.ADMINISTRATOR,
            FIRST_NAME,
            LAST_NAME,
            TITLE,
            DATE_OF_BIRTH,
            EMAIL_ADDRESS,
            PASSWORD,
            HOME_ADDRESS,
            BILLING_ADDRESS);

    private Fixtures() {
    }

    public static User user(Integer id, String type) {
        return userBuilder()
                .withId(id)
                .withType(type)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withTitle(TITLE)
                .withDateOfBirth(DATE_OF_BIRTH)
                .withPassword(PASSWORD)
                .withEmailAddress(EMAIL_ADDRESS)
                .withHomeAddress(HOME_ADDRESS)
                .withBillingAddress(BILLING_ADDRESS)
                .build();
    }

    public static Date getDate(int year) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(GregorianCalendar.YEAR, year);
        return calendar.getTime();
    }

}
