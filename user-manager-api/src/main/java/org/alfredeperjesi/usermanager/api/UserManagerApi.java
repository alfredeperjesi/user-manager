package org.alfredeperjesi.usermanager.api;

import com.wordnik.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/UserManager/")
@Api(value = "/UserManager", description = "Manage users")
public interface UserManagerApi {

    @Path("users")
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(
            value = "Create user",
            response = UserResource.class
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "User successfully created", response = UserResource.class),
            @ApiResponse(code = 200, message = "User has already created, the stored user resource", response = UserResource.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResource.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorResource.class),
            @ApiResponse(code = 401, message = "User not authorised", response = ErrorResource.class)
    })
    Response create(@ApiParam(value = "Service user id") @HeaderParam("serviceUserId") Integer serviceUserId, @ApiParam CreateUserResource createUserResource);

    @Path("users/{id}")
    @GET
    @Produces(APPLICATION_JSON)
    @ApiOperation(
            value = "Get user",
            response = UserResource.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found", response = UserResource.class),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResource.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorResource.class),
            @ApiResponse(code = 401, message = "User not authorised", response = ErrorResource.class)
    })
    Response get(@ApiParam(value = "Service user id", required = true) @HeaderParam("serviceUserId") Integer serviceUserId, @ApiParam(value = "User id", required = true) @PathParam("id") Integer id);

    @Path("users")
    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(
            value = "Create user",
            response = UserResource.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "User successfully updated", response = UserResource.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResource.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorResource.class),
            @ApiResponse(code = 401, message = "User not authorised", response = ErrorResource.class)
    })
    Response update(@ApiParam(value = "Service user id") @HeaderParam("serviceUserId") Integer serviceUserId, @ApiParam UserResource userResource);

    @Path("users/{id}")
    @DELETE
    @Produces(APPLICATION_JSON)
    @ApiOperation(
            value = "Delete user"
    )
    @ApiResponses({
            @ApiResponse(code = 204, message = "User deleted"),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResource.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorResource.class),
            @ApiResponse(code = 401, message = "User not authorised", response = ErrorResource.class)
    })
    Response delete(@ApiParam(value = "Service user id", required = true) @HeaderParam("serviceUserId") Integer serviceUserId, @ApiParam(value = "User id", required = true) @PathParam("id") Integer id);

}
