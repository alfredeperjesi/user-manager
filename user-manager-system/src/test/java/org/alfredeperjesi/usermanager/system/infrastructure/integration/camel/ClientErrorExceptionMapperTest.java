package org.alfredeperjesi.usermanager.system.infrastructure.integration.camel;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import org.alfredeperjesi.usermanager.api.ErrorResource;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ClientErrorExceptionMapperTest {
    public static final String ERROR = "Error";
    public static final String INVALID_JSON_MESSAGE = "The json input is not appropriate!";
    private ClientErrorExceptionMapper clientErrorExceptionMapper;

    @Before
    public void setUp() {
        clientErrorExceptionMapper = new ClientErrorExceptionMapper();
    }

    @Test
    public void toResponseGeneratedBadRequestStatusWithTheErrorMessageWhenJsonParseException() {
        Response response = clientErrorExceptionMapper.toResponse(new JsonParseException(ERROR, JsonLocation.NA));

        assertThat(response.getStatus(), equalTo(BAD_REQUEST.getStatusCode()));
        assertThat(response.readEntity(ErrorResource.class).getErrorMessage(), equalTo(INVALID_JSON_MESSAGE));
    }

    @Test
    public void toResponseGeneratedBadRequestStatusWithTheErrorMessageWhenNullPointerException() {
        Response response = clientErrorExceptionMapper.toResponse(new NullPointerException(ERROR));

        assertThat(response.getStatus(), equalTo(BAD_REQUEST.getStatusCode()));
        assertThat(response.readEntity(ErrorResource.class).getErrorMessage(), equalTo(ERROR));
    }

    @Test
    public void toResponseGeneratedBadRequestStatusWithTheErrorMessageWhenIllegalArgumentException() {
        Response response = clientErrorExceptionMapper.toResponse(new IllegalArgumentException(ERROR));

        assertThat(response.getStatus(), equalTo(BAD_REQUEST.getStatusCode()));
        assertThat(response.readEntity(ErrorResource.class).getErrorMessage(), equalTo(ERROR));
    }

    @Test
    public void toResponseGeneratedInternalServerErrorStatusWithTheErrorMessageWhenNotKnownException() {
        Response response = clientErrorExceptionMapper.toResponse(new Exception(ERROR));

        assertThat(response.getStatus(), equalTo(INTERNAL_SERVER_ERROR.getStatusCode()));
        assertThat(response.readEntity(ErrorResource.class).getErrorMessage(), equalTo(ERROR));
    }
}
