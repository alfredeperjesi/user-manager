package org.alfredeperjesi.usermanager.system.application;

import com.google.common.base.Optional;
import org.alfredeperjesi.usermanager.system.domain.User;
import org.alfredeperjesi.usermanager.system.domain.UserId;
import org.alfredeperjesi.usermanager.system.domain.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.alfredeperjesi.usermanager.system.Fixtures.USER;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserManagerServiceTest {
    private static final Optional<UserId> SERVICE_USER_ID = Optional.<UserId>of(new UserId(1));
    public static final Optional<User> USER_ABSENT = Optional.<User>absent();
    public static final Optional<User> USER_PRESENT = Optional.of(USER);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthorisationHandler authorisationHandler;

    private UserManagerService userManagerService;

    @Before
    public void setUp() {
        userManagerService = new UserManagerService(userRepository, authorisationHandler);
    }

    @Test
    public void createPersistsWhenServiceUserIdIsAbsent() throws MethodNotAllowedException, UserAlreadyExistsException {
        when(authorisationHandler.isCreateAllowed(USER, USER_ABSENT)).thenReturn(true);
        when(userRepository.find(USER.getEmailAddress())).thenReturn(USER_ABSENT);

        userManagerService.create(Optional.<UserId>absent(), USER);

        verify(userRepository).persist(USER);
    }

    @Test
    public void createPersistsWhenServiceUserIsAuthorised() throws MethodNotAllowedException, UserAlreadyExistsException {
        when(userRepository.get(SERVICE_USER_ID.get())).thenReturn(USER_PRESENT);
        when(authorisationHandler.isCreateAllowed(USER, USER_PRESENT)).thenReturn(true);
        when(userRepository.find(USER.getEmailAddress())).thenReturn(USER_ABSENT);

        userManagerService.create(SERVICE_USER_ID, USER);

        verify(userRepository).persist(USER);
    }

    @Test
    public void createDoesNotPersistWhenServiceUserIsAuthorised() throws MethodNotAllowedException, UserAlreadyExistsException {
        expectedException.expect(UserAlreadyExistsException.class);

        when(userRepository.get(SERVICE_USER_ID.get())).thenReturn(USER_PRESENT);
        when(authorisationHandler.isCreateAllowed(USER, USER_PRESENT)).thenReturn(true);
        when(userRepository.find(USER.getEmailAddress())).thenReturn(USER_PRESENT);

        userManagerService.create(SERVICE_USER_ID, USER);

        verify(userRepository, never()).persist(USER);
    }

    @Test
    public void createThrowsExceptionWhenServiceUserIsUnAuthorised() throws MethodNotAllowedException, UserAlreadyExistsException {
        expectedException.expect(MethodNotAllowedException.class);
        expectedException.expectMessage("Create not allowed");

        when(userRepository.get(SERVICE_USER_ID.get())).thenReturn(USER_PRESENT);
        when(authorisationHandler.isCreateAllowed(USER, USER_PRESENT)).thenReturn(false);

        userManagerService.create(SERVICE_USER_ID, USER);

        verify(userRepository, never()).persist(USER);
    }

    @Test
    public void getDelegatesToRepositoryWhenServiceUserIsAuthorised() throws MethodNotAllowedException {
        when(userRepository.get(SERVICE_USER_ID.get())).thenReturn(USER_PRESENT);
        when(userRepository.get(USER.getId())).thenReturn(USER_PRESENT);
        when(authorisationHandler.isGetAllowed(USER, USER_PRESENT)).thenReturn(true);

        Optional<User> user = userManagerService.get(SERVICE_USER_ID, USER.getId());

        assertThat(user.get(), equalTo(USER));
    }

    @Test
    public void getThrowsExceptionWhenServiceUserIsUnAuthorised() throws MethodNotAllowedException {
        expectedException.expect(MethodNotAllowedException.class);
        expectedException.expectMessage("Get not allowed");

        when(userRepository.get(SERVICE_USER_ID.get())).thenReturn(USER_PRESENT);
        when(authorisationHandler.isGetAllowed(USER, USER_PRESENT)).thenReturn(false);

        userManagerService.get(SERVICE_USER_ID, USER.getId());
    }

    @Test
    public void updateDelegatesToRepositoryWhenServiceUserIsAuthorised() throws MethodNotAllowedException {
        when(userRepository.get(SERVICE_USER_ID.get())).thenReturn(USER_PRESENT);
        when(userRepository.get(USER.getId())).thenReturn(USER_PRESENT);
        when(authorisationHandler.isUpdateAllowed(USER, USER_PRESENT)).thenReturn(true);

        userManagerService.update(SERVICE_USER_ID, USER);

        verify(userRepository).update(USER);
    }

    @Test
    public void updateThrowsExceptionWhenServiceUserIsUnAuthorised() throws MethodNotAllowedException {
        expectedException.expect(MethodNotAllowedException.class);
        expectedException.expectMessage("Update not allowed");

        when(userRepository.get(SERVICE_USER_ID.get())).thenReturn(USER_PRESENT);
        when(authorisationHandler.isUpdateAllowed(USER, USER_PRESENT)).thenReturn(false);

        userManagerService.update(SERVICE_USER_ID, USER);
    }

    @Test
    public void deleteDelegatesToRepositoryWhenServiceUserIsAuthorised() throws MethodNotAllowedException {
        when(userRepository.get(SERVICE_USER_ID.get())).thenReturn(USER_PRESENT);
        when(authorisationHandler.isDeleteAllowed(USER, USER_PRESENT)).thenReturn(true);

        userManagerService.delete(SERVICE_USER_ID, USER.getId());

        verify(userRepository).delete(USER.getId());
    }

    @Test
    public void deleteThrowsExceptionWhenServiceUserIsUnAuthorised() throws MethodNotAllowedException {
        expectedException.expect(MethodNotAllowedException.class);
        expectedException.expectMessage("Delete not allowed");

        when(userRepository.get(SERVICE_USER_ID.get())).thenReturn(USER_PRESENT);
        when(authorisationHandler.isDeleteAllowed(USER, USER_PRESENT)).thenReturn(false);

        userManagerService.delete(SERVICE_USER_ID, USER.getId());
    }
}
