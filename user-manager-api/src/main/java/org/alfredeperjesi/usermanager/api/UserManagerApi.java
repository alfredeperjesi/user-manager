package org.alfredeperjesi.usermanager.api;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/UserManager")
@Api(value = "/UserManager", description = "Manage users")
public interface UserManagerApi {

    @Path("/users")
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(
            value = "Create user",
            response = UserResource.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "User has already created, the stored user resource (idempotency)", response = UserResource.class),
            @ApiResponse(code = 201, message = "User successfully created", response = UserResource.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResource.class),
            @ApiResponse(code = 401, message = "User not authorised", response = ErrorResource.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorResource.class)
    })
    Response create(@ApiParam(value = "Service user id") @HeaderParam("serviceUserId") Integer serviceUserId, @ApiParam CreateUserResource createUserResource);

    @Path("/users/{id}")
    @GET
    @Produces(APPLICATION_JSON)
    @ApiOperation(
            value = "Get user",
            response = UserResource.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found", response = UserResource.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResource.class),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 401, message = "User not authorised", response = ErrorResource.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorResource.class)
    })
    Response get(@ApiParam(value = "Service user id", required = true) @HeaderParam("serviceUserId") Integer serviceUserId, @ApiParam(value = "User id", required = true) @PathParam("id") Integer id);

    @Path("/users")
    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(
            value = "Update user",
            response = UserResource.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "User successfully updated", response = UserResource.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResource.class),
            @ApiResponse(code = 401, message = "User not authorised", response = ErrorResource.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorResource.class)
    })
    Response update(@ApiParam(value = "Service user id") @HeaderParam("serviceUserId") Integer serviceUserId, @ApiParam UserResource userResource);

    @Path("/users/{id}")
    @DELETE
    @Produces(APPLICATION_JSON)
    @ApiOperation(
            value = "Delete user"
    )
    @ApiResponses({
            @ApiResponse(code = 204, message = "User deleted"),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResource.class),
            @ApiResponse(code = 401, message = "User not authorised", response = ErrorResource.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorResource.class)
    })
    Response delete(@ApiParam(value = "Service user id", required = true) @HeaderParam("serviceUserId") Integer serviceUserId, @ApiParam(value = "User id", required = true) @PathParam("id") Integer id);

}
