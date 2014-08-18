package org.alfredeperjesi.usermanager.system.infrastructure.rest;

import static org.alfredeperjesi.usermanager.system.Fixtures.CREATE_USER_RESOURCE;
import static org.alfredeperjesi.usermanager.system.Fixtures.ID;
import static org.alfredeperjesi.usermanager.system.Fixtures.USER;
import static org.alfredeperjesi.usermanager.system.Fixtures.USER_RESOURCE;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;

import org.alfredeperjesi.usermanager.api.UserManagerApi;
import org.alfredeperjesi.usermanager.api.UserResource;
import org.alfredeperjesi.usermanager.system.application.MethodNotAllowedException;
import org.alfredeperjesi.usermanager.system.application.UserAlreadyExistsException;
import org.alfredeperjesi.usermanager.system.application.UserManagerService;
import org.alfredeperjesi.usermanager.system.domain.User;
import org.alfredeperjesi.usermanager.system.domain.UserId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserManagerRestServiceTest {
    public static final UserId USER_ID = new UserId(ID);

    public static final Optional<UserId> USER_ID_OPTIONAL = Optional.of(USER_ID);

    public static final MethodNotAllowedException METHOD_NOT_ALLOWED_EXCEPTION = new MethodNotAllowedException("Test");

    @Mock
    private UserAssembler userAssembler;

    @Mock
    private UserManagerService userManagerService;

    private UserManagerApi userManagerRestService;

    @Before
    public void setUp() {
        userManagerRestService = new UserManagerRestService(userManagerService, userAssembler);
    }

    @Test
    public void createReturnsTheCreatedUserResourceAndStatusCreatedWhenAuthorised() throws MethodNotAllowedException, UserAlreadyExistsException {
        when(userAssembler.assemble(CREATE_USER_RESOURCE)).thenReturn(USER);
        when(userAssembler.assembleOptionalUserId(ID)).thenReturn(USER_ID_OPTIONAL);
        when(userAssembler.deassemble(USER)).thenReturn(USER_RESOURCE);

        Response response = userManagerRestService.create(ID, CREATE_USER_RESOURCE);

        verify(userManagerService).create(USER_ID_OPTIONAL, USER);

        assertThat(response.getStatus(), equalTo(Response.Status.CREATED.getStatusCode()));
        assertThat(response.readEntity(UserResource.class), equalTo(USER_RESOURCE));
    }

    @Test
    public void createReturnsTheFoundUserResourceAndStatusOkWhenAuthorisedAndUserIsAlreadyStored() throws MethodNotAllowedException, UserAlreadyExistsException {
        when(userAssembler.assemble(CREATE_USER_RESOURCE)).thenReturn(USER);
        when(userAssembler.assembleOptionalUserId(ID)).thenReturn(USER_ID_OPTIONAL);
        when(userAssembler.deassemble(USER)).thenReturn(USER_RESOURCE);

        doThrow(new UserAlreadyExistsException(USER)).when(userManagerService).create(USER_ID_OPTIONAL, USER);

        Response response = userManagerRestService.create(ID, CREATE_USER_RESOURCE);

        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
        assertThat(response.readEntity(UserResource.class), equalTo(USER_RESOURCE));
    }

    @Test
    public void createReturnsStatusUnauthorisedWhenUnauthorised() throws MethodNotAllowedException, UserAlreadyExistsException {
        when(userAssembler.assemble(CREATE_USER_RESOURCE)).thenReturn(USER);
        when(userAssembler.assembleOptionalUserId(ID)).thenReturn(USER_ID_OPTIONAL);

        doThrow(METHOD_NOT_ALLOWED_EXCEPTION).when(userManagerService).create(USER_ID_OPTIONAL, USER);

        Response response = userManagerRestService.create(ID, CREATE_USER_RESOURCE);

        assertThat(response.getStatus(), equalTo(Response.Status.UNAUTHORIZED.getStatusCode()));
    }

    @Test
    public void getReturnsTheUserResourceAndStatusOkWhenAuthorisedAndFound() throws MethodNotAllowedException {
        when(userAssembler.assembleOptionalUserId(ID)).thenReturn(USER_ID_OPTIONAL);
        when(userAssembler.assembleUserId(ID)).thenReturn(USER_ID);
        when(userAssembler.deassemble(USER)).thenReturn(USER_RESOURCE);
        when(userManagerService.get(USER_ID_OPTIONAL, USER_ID)).thenReturn(Optional.of(USER));

        Response response = userManagerRestService.get(ID, ID);

        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
        assertThat(response.readEntity(UserResource.class), equalTo(USER_RESOURCE));
    }

    @Test
    public void getReturnsStatusNotFoundWhenAuthorisedAndNotFound() throws MethodNotAllowedException {
        when(userAssembler.assembleOptionalUserId(ID)).thenReturn(USER_ID_OPTIONAL);
        when(userAssembler.assembleUserId(ID)).thenReturn(USER_ID);
        when(userAssembler.deassemble(USER)).thenReturn(USER_RESOURCE);
        when(userManagerService.get(USER_ID_OPTIONAL, USER_ID)).thenReturn(Optional.<User>absent());

        Response response = userManagerRestService.get(ID, ID);

        assertThat(response.getStatus(), equalTo(Response.Status.NOT_FOUND.getStatusCode()));
    }

    @Test
    public void getReturnsStatusUnauthorisedWhenUnauthorised() throws MethodNotAllowedException {
        when(userAssembler.assembleOptionalUserId(ID)).thenReturn(USER_ID_OPTIONAL);
        when(userAssembler.assembleUserId(ID)).thenReturn(USER_ID);

        when(userManagerService.get(USER_ID_OPTIONAL, USER_ID)).thenThrow(METHOD_NOT_ALLOWED_EXCEPTION);

        Response response = userManagerRestService.get(ID, ID);

        assertThat(response.getStatus(), equalTo(Response.Status.UNAUTHORIZED.getStatusCode()));
    }

    @Test
    public void updateReturnsTheCreatedUserResourceAndStatusOkWhenAuthorised() throws MethodNotAllowedException {
        when(userAssembler.assembleOptionalUserId(ID)).thenReturn(USER_ID_OPTIONAL);
        when(userAssembler.assemble(USER_RESOURCE)).thenReturn(USER);

        Response response = userManagerRestService.update(ID, USER_RESOURCE);

        verify(userManagerService).update(USER_ID_OPTIONAL, USER);

        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
        assertThat(response.readEntity(UserResource.class), equalTo(USER_RESOURCE));
    }

    @Test
    public void updateReturnsStatusUnauthorisedWhenUnauthorised() throws MethodNotAllowedException {
        when(userAssembler.assembleOptionalUserId(ID)).thenReturn(USER_ID_OPTIONAL);
        when(userAssembler.assemble(USER_RESOURCE)).thenReturn(USER);

        doThrow(METHOD_NOT_ALLOWED_EXCEPTION).when(userManagerService).update(USER_ID_OPTIONAL, USER);

        Response response = userManagerRestService.update(ID, USER_RESOURCE);

        assertThat(response.getStatus(), equalTo(Response.Status.UNAUTHORIZED.getStatusCode()));
    }

    @Test
    public void deleteReturnsStatusNoContentWhenAuthorised() throws MethodNotAllowedException {
        when(userAssembler.assembleOptionalUserId(ID)).thenReturn(USER_ID_OPTIONAL);
        when(userAssembler.assembleUserId(ID)).thenReturn(USER_ID);

        Response response = userManagerRestService.delete(ID, ID);

        verify(userManagerService).delete(USER_ID_OPTIONAL, USER_ID);

        assertThat(response.getStatus(), equalTo(Response.Status.NO_CONTENT.getStatusCode()));
    }

    @Test
    public void deleteReturnsStatusUnauthorisedWhenUnauthorised() throws MethodNotAllowedException {
        when(userAssembler.assembleOptionalUserId(ID)).thenReturn(USER_ID_OPTIONAL);
        when(userAssembler.assembleUserId(ID)).thenReturn(USER_ID);

        doThrow(METHOD_NOT_ALLOWED_EXCEPTION).when(userManagerService).delete(USER_ID_OPTIONAL, USER_ID);

        Response response = userManagerRestService.delete(ID, ID);

        assertThat(response.getStatus(), equalTo(Response.Status.UNAUTHORIZED.getStatusCode()));
    }
}
