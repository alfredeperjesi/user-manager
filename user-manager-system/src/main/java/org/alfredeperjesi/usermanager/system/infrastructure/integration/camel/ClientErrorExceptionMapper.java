package org.alfredeperjesi.usermanager.system.infrastructure.integration.camel;

import com.fasterxml.jackson.core.JsonParseException;
import org.alfredeperjesi.usermanager.api.ErrorResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.status;

@Provider
@Component
public class ClientErrorExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientErrorExceptionMapper.class);

    @Override
    public Response toResponse(Exception e) {
        LOGGER.error("", e);

        if (e instanceof JsonParseException) {
            return status(BAD_REQUEST).entity(new ErrorResource("The json input is not appropriate!")).build();
        } else if (e instanceof NullPointerException || e instanceof IllegalArgumentException) {
            return status(BAD_REQUEST).entity(new ErrorResource(e.getMessage())).build();
        } else {
            return status(INTERNAL_SERVER_ERROR).entity(new ErrorResource(e.getMessage())).build();
        }
    }
} 