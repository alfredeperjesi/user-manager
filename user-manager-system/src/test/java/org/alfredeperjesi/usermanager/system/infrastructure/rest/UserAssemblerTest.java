package org.alfredeperjesi.usermanager.system.infrastructure.rest;

import com.google.common.base.Optional;
import org.alfredeperjesi.usermanager.api.UserResource;
import org.alfredeperjesi.usermanager.system.domain.User;
import org.alfredeperjesi.usermanager.system.domain.UserId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.alfredeperjesi.usermanager.system.Fixtures.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserAssemblerTest {

    @Mock
    private UserIdGenerator userIdGenerator;

    private UserAssembler userAssembler;

    @Before
    public void setUp() {
        userAssembler = new UserAssembler(userIdGenerator);
    }

    @Test
    public void assembleFromCreateUserResourceConstructsTheProperUser() {
        when(userIdGenerator.generate()).thenReturn(ID);

        User user = userAssembler.assemble(CREATE_USER_RESOURCE);

        verifyUser(user);
    }

    @Test
    public void assembleFromUserResourceConstructsTheProperUser() {
        User user = userAssembler.assemble(USER_RESOURCE);

        verifyUser(user);
    }

    @Test
    public void deassembleConstructsTheProperUserResource() {
        UserResource userResource = userAssembler.deassemble(USER);

        assertThat(userResource.getId(), equalTo(ID));
        assertThat(userResource.getType().name(), equalTo(ADMINISTRATOR));
        assertThat(userResource.getFirstName(), equalTo(FIRST_NAME));
        assertThat(userResource.getLastName(), equalTo(LAST_NAME));
        assertThat(userResource.getTitle(), equalTo(TITLE));
        assertThat(userResource.getDateOfBirth(), equalTo(DATE_OF_BIRTH));
        assertThat(userResource.getPassword(), equalTo(PASSWORD));
        assertThat(userResource.getEmailAddress(), equalTo(EMAIL_ADDRESS));
    }

    @Test
    public void assembleOptionalUserIdConstructsAbsentUserIdWhenUserIdValueIsNull() {
        Optional<UserId> userId = userAssembler.assembleOptionalUserId(null);

        assertThat(userId.isPresent(), equalTo(false));
    }

    @Test
    public void assembleOptionalUserIdConstructsProperUserIdWhenUserIdValueIsNotNull() {
        Optional<UserId> userId = userAssembler.assembleOptionalUserId(ID);

        assertThat(userId.get().getValue(), equalTo(ID));
    }

    private void verifyUser(User user) {
        assertThat(user.getId().getValue(), equalTo(ID));
        assertThat(user.getType().name(), equalTo(ADMINISTRATOR));
        assertThat(user.getFirstName().getValue(), equalTo(FIRST_NAME));
        assertThat(user.getLastName().getValue(), equalTo(LAST_NAME));
        assertThat(user.getTitle().getValue(), equalTo(TITLE));
        assertThat(user.getDateOfBirth().getValue(), equalTo(DATE_OF_BIRTH));
        assertThat(user.getPassword().getValue(), equalTo(PASSWORD));
        assertThat(user.getEmailAddress().getValue(), equalTo(EMAIL_ADDRESS));
    }
}
