package org.alfredeperjesi.usermanager.system.application;

import static org.alfredeperjesi.usermanager.system.Fixtures.ADMINISTRATOR;
import static org.alfredeperjesi.usermanager.system.Fixtures.SUBSCRIBER;
import static org.alfredeperjesi.usermanager.system.Fixtures.SUPER_USER;
import static org.alfredeperjesi.usermanager.system.Fixtures.user;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.alfredeperjesi.usermanager.system.domain.User;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Optional;

public class AuthorisationHandlerTest {
    private static final User SUBSRIBER_USER = user(1, SUBSCRIBER);

    private static final User SUBSRIBER_USER_2 = user(2, SUBSCRIBER);

    private static final User ADMINISTRATOR_USER = user(3, ADMINISTRATOR);

    private static final User ADMINISTRATOR_USER_2 = user(4, ADMINISTRATOR);

    private static final User SUPER_USER_USER = user(5, SUPER_USER);

    private static final User SUPER_USER_USER_2 = user(6, SUPER_USER);

    private AuthorisationHandler authorisationHandler;

    @Before
    public void setUp() {
        authorisationHandler = new AuthorisationHandler();
    }

    @Test
    public void isCreateAllowedReturnsTrueWhenServiceUserIsAbsentAndUserIsSubscriber() {
        boolean result = authorisationHandler.isCreateAllowed(SUBSRIBER_USER, Optional.<User>absent());

        assertThat(result, equalTo(true));
    }

    @Test
    public void isCreateAllowedReturnsFalseWhenServiceUserIsAbsentAndUserIsAdministrator() {
        boolean result = authorisationHandler.isCreateAllowed(ADMINISTRATOR_USER, Optional.<User>absent());

        assertThat(result, equalTo(false));
    }

    @Test
    public void isCreateAllowedReturnsFalseWhenServiceUserIsAbsentAndUserIsSuperUser() {
        boolean result = authorisationHandler.isCreateAllowed(SUPER_USER_USER, Optional.<User>absent());

        assertThat(result, equalTo(false));
    }

    @Test
    public void isCreateAllowedReturnsFalseWhenServiceUserIsSubscriberAndUserIsSubscriber() {
        boolean result = authorisationHandler.isCreateAllowed(SUBSRIBER_USER, Optional.of(SUBSRIBER_USER));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isCreateAllowedReturnsFalseWhenServiceUserIsSubscriberAndUserIsAdministrator() {
        boolean result = authorisationHandler.isCreateAllowed(ADMINISTRATOR_USER, Optional.of(SUBSRIBER_USER));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isCreateAllowedReturnsFalseWhenServiceUserIsSubscriberAndUserIsSuperUser() {
        boolean result = authorisationHandler.isCreateAllowed(SUPER_USER_USER, Optional.of(SUBSRIBER_USER));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isCreateAllowedReturnsTrueWhenServiceUserIsAdministratorAndUserIsSubscriber() {
        boolean result = authorisationHandler.isCreateAllowed(SUBSRIBER_USER, Optional.of(ADMINISTRATOR_USER));

        assertThat(result, equalTo(true));
    }

    @Test
    public void isCreateAllowedReturnsFalseWhenServiceUserIsAdministratorAndUserIsAdministrator() {
        boolean result = authorisationHandler.isCreateAllowed(ADMINISTRATOR_USER, Optional.of(ADMINISTRATOR_USER));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isCreateAllowedReturnsFalseWhenServiceUserIsAdministratorAndUserIsSuperUser() {
        boolean result = authorisationHandler.isCreateAllowed(SUPER_USER_USER, Optional.of(ADMINISTRATOR_USER));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isCreateAllowedReturnsTrueWhenServiceUserIsSuperUserAndUserIsSubscriber() {
        boolean result = authorisationHandler.isCreateAllowed(SUBSRIBER_USER, Optional.of(SUPER_USER_USER));

        assertThat(result, equalTo(true));
    }

    @Test
    public void isCreateAllowedReturnsTrueWhenServiceUserIsSuperUserAndUserIsAdministrator() {
        boolean result = authorisationHandler.isCreateAllowed(ADMINISTRATOR_USER, Optional.of(SUPER_USER_USER));

        assertThat(result, equalTo(true));
    }

    @Test
    public void isCreateAllowedReturnsTrueWhenServiceUserIsSuperUserAndUserIsSuperUser() {
        boolean result = authorisationHandler.isCreateAllowed(SUPER_USER_USER, Optional.of(SUPER_USER_USER));

        assertThat(result, equalTo(true));
    }

    //GET
    @Test
    public void isGetAllowedReturnsTrueWhenServiceUserIsSubscriberAndUserTheSame() {
        boolean result = authorisationHandler.isGetAllowed(SUBSRIBER_USER, Optional.of(SUBSRIBER_USER));

        assertThat(result, equalTo(true));
    }

    @Test
    public void isGetAllowedReturnsFalseWhenServiceUserIsSubscriberAndUserIsSubscriber() {
        boolean result = authorisationHandler.isGetAllowed(SUBSRIBER_USER, Optional.of(SUBSRIBER_USER_2));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isGetAllowedReturnsFalseWhenServiceUserIsSubscriberAndUserIsAdministrator() {
        boolean result = authorisationHandler.isGetAllowed(ADMINISTRATOR_USER, Optional.of(SUBSRIBER_USER));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isGetAllowedReturnsFalseWhenServiceUserIsSubscriberAndUserIsSuperUser() {
        boolean result = authorisationHandler.isGetAllowed(SUPER_USER_USER, Optional.of(SUBSRIBER_USER));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isGetAllowedReturnsTrueWhenServiceUserIsAdministratorAndUserIsSubscriber() {
        boolean result = authorisationHandler.isGetAllowed(SUBSRIBER_USER, Optional.of(ADMINISTRATOR_USER));

        assertThat(result, equalTo(true));
    }

    @Test
    public void isGetAllowedReturnsTrueWhenServiceUserIsAdministratorAndUserIsTheSame() {
        boolean result = authorisationHandler.isGetAllowed(ADMINISTRATOR_USER, Optional.of(ADMINISTRATOR_USER));

        assertThat(result, equalTo(true));
    }

    @Test
    public void isGetAllowedReturnsFalseWhenServiceUserIsAdministratorAndUserIsAdministrator() {
        boolean result = authorisationHandler.isGetAllowed(ADMINISTRATOR_USER, Optional.of(ADMINISTRATOR_USER_2));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isGetAllowedReturnsFalseWhenServiceUserIsAdministratorAndUserIsSuperUser() {
        boolean result = authorisationHandler.isGetAllowed(SUPER_USER_USER, Optional.of(ADMINISTRATOR_USER));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isGetAllowedReturnsTrueWhenServiceUserIsSuperUserAndUserIsSubscriber() {
        boolean result = authorisationHandler.isGetAllowed(SUBSRIBER_USER, Optional.of(SUPER_USER_USER));

        assertThat(result, equalTo(true));
    }

    @Test
    public void isGetAllowedReturnsTrueWhenServiceUserIsSuperUserAndUserIsAdministrator() {
        boolean result = authorisationHandler.isGetAllowed(ADMINISTRATOR_USER, Optional.of(SUPER_USER_USER));

        assertThat(result, equalTo(true));
    }

    @Test
    public void isGetAllowedReturnsTrueWhenServiceUserIsSuperUserAndUserIsTheSame() {
        boolean result = authorisationHandler.isGetAllowed(SUPER_USER_USER, Optional.of(SUPER_USER_USER));

        assertThat(result, equalTo(true));
    }

    @Test
    public void isGetAllowedReturnsTrueWhenServiceUserIsSuperUserAndUserIsSuperUser() {
        boolean result = authorisationHandler.isGetAllowed(SUPER_USER_USER, Optional.of(SUPER_USER_USER_2));

        assertThat(result, equalTo(true));
    }

    //Update
    @Test
    public void isUpdateAllowedReturnsFalseWhenServiceUserIsSubscriberAndUserTheSame() {
        boolean result = authorisationHandler.isUpdateAllowed(SUBSRIBER_USER, Optional.of(SUBSRIBER_USER));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isUpdateAllowedReturnsFalseWhenServiceUserIsSubscriberAndUserIsSubscriber() {
        boolean result = authorisationHandler.isUpdateAllowed(SUBSRIBER_USER, Optional.of(SUBSRIBER_USER_2));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isUpdateAllowedReturnsFalseWhenServiceUserIsSubscriberAndUserIsAdministrator() {
        boolean result = authorisationHandler.isUpdateAllowed(ADMINISTRATOR_USER, Optional.of(SUBSRIBER_USER));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isUpdateAllowedReturnsFalseWhenServiceUserIsSubscriberAndUserIsSuperUser() {
        boolean result = authorisationHandler.isUpdateAllowed(SUPER_USER_USER, Optional.of(SUBSRIBER_USER));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isUpdateAllowedReturnsTrueWhenServiceUserIsAdministratorAndUserIsSubscriber() {
        boolean result = authorisationHandler.isUpdateAllowed(SUBSRIBER_USER, Optional.of(ADMINISTRATOR_USER));

        assertThat(result, equalTo(true));
    }

    @Test
    public void isUpdateAllowedReturnsFalseWhenServiceUserIsAdministratorAndUserIsTheSame() {
        boolean result = authorisationHandler.isUpdateAllowed(ADMINISTRATOR_USER, Optional.of(ADMINISTRATOR_USER));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isUpdateAllowedReturnsFalseWhenServiceUserIsAdministratorAndUserIsAdministrator() {
        boolean result = authorisationHandler.isUpdateAllowed(ADMINISTRATOR_USER, Optional.of(ADMINISTRATOR_USER_2));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isUpdateAllowedReturnsFalseWhenServiceUserIsAdministratorAndUserIsSuperUser() {
        boolean result = authorisationHandler.isUpdateAllowed(SUPER_USER_USER, Optional.of(ADMINISTRATOR_USER));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isUpdateAllowedReturnsTrueWhenServiceUserIsSuperUserAndUserIsSubscriber() {
        boolean result = authorisationHandler.isUpdateAllowed(SUBSRIBER_USER, Optional.of(SUPER_USER_USER));

        assertThat(result, equalTo(true));
    }

    @Test
    public void isUpdateAllowedReturnsTrueWhenServiceUserIsSuperUserAndUserIsAdministrator() {
        boolean result = authorisationHandler.isUpdateAllowed(ADMINISTRATOR_USER, Optional.of(SUPER_USER_USER));

        assertThat(result, equalTo(true));
    }

    @Test
    public void isUpdateAllowedReturnsTrueWhenServiceUserIsSuperUserAndUserIsTheSame() {
        boolean result = authorisationHandler.isUpdateAllowed(SUPER_USER_USER, Optional.of(SUPER_USER_USER));

        assertThat(result, equalTo(true));
    }

    @Test
    public void isUpdateAllowedReturnsTrueWhenServiceUserIsSuperUserAndUserIsSuperUser() {
        boolean result = authorisationHandler.isUpdateAllowed(SUPER_USER_USER, Optional.of(SUPER_USER_USER_2));

        assertThat(result, equalTo(true));
    }

    //Delete
    @Test
    public void isDeleteAllowedReturnsFalseWhenServiceUserIsSubscriberAndUserTheSame() {
        boolean result = authorisationHandler.isDeleteAllowed(SUBSRIBER_USER, Optional.of(SUBSRIBER_USER));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isDeleteAllowedReturnsFalseWhenServiceUserIsSubscriberAndUserIsSubscriber() {
        boolean result = authorisationHandler.isDeleteAllowed(SUBSRIBER_USER, Optional.of(SUBSRIBER_USER_2));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isDeleteAllowedReturnsFalseWhenServiceUserIsSubscriberAndUserIsAdministrator() {
        boolean result = authorisationHandler.isDeleteAllowed(ADMINISTRATOR_USER, Optional.of(SUBSRIBER_USER));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isDeleteAllowedReturnsFalseWhenServiceUserIsSubscriberAndUserIsSuperUser() {
        boolean result = authorisationHandler.isDeleteAllowed(SUPER_USER_USER, Optional.of(SUBSRIBER_USER));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isDeleteAllowedReturnsFalseWhenServiceUserIsAdministratorAndUserIsSubscriber() {
        boolean result = authorisationHandler.isDeleteAllowed(SUBSRIBER_USER, Optional.of(ADMINISTRATOR_USER));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isDeleteAllowedReturnsFalseWhenServiceUserIsAdministratorAndUserIsTheSame() {
        boolean result = authorisationHandler.isDeleteAllowed(ADMINISTRATOR_USER, Optional.of(ADMINISTRATOR_USER));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isDeleteAllowedReturnsFalseWhenServiceUserIsAdministratorAndUserIsAdministrator() {
        boolean result = authorisationHandler.isDeleteAllowed(ADMINISTRATOR_USER, Optional.of(ADMINISTRATOR_USER_2));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isDeleteAllowedReturnsFalseWhenServiceUserIsAdministratorAndUserIsSuperUser() {
        boolean result = authorisationHandler.isDeleteAllowed(SUPER_USER_USER, Optional.of(ADMINISTRATOR_USER));

        assertThat(result, equalTo(false));
    }

    @Test
    public void isDeleteAllowedReturnsTrueWhenServiceUserIsSuperUserAndUserIsSubscriber() {
        boolean result = authorisationHandler.isDeleteAllowed(SUBSRIBER_USER, Optional.of(SUPER_USER_USER));

        assertThat(result, equalTo(true));
    }

    @Test
    public void isDeleteAllowedReturnsTrueWhenServiceUserIsSuperUserAndUserIsAdministrator() {
        boolean result = authorisationHandler.isDeleteAllowed(ADMINISTRATOR_USER, Optional.of(SUPER_USER_USER));

        assertThat(result, equalTo(true));
    }

    @Test
    public void isDeleteAllowedReturnsTrueWhenServiceUserIsSuperUserAndUserIsTheSame() {
        boolean result = authorisationHandler.isDeleteAllowed(SUPER_USER_USER, Optional.of(SUPER_USER_USER));

        assertThat(result, equalTo(true));
    }

    @Test
    public void isDeleteAllowedReturnsTrueWhenServiceUserIsSuperUserAndUserIsSuperUser() {
        boolean result = authorisationHandler.isDeleteAllowed(SUPER_USER_USER, Optional.of(SUPER_USER_USER_2));

        assertThat(result, equalTo(true));
    }

}
