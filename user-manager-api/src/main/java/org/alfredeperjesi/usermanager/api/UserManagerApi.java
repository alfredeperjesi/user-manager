package org.alfredeperjesi.usermanager.api;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/UserManager/")
public interface UserManagerApi {

    @Path("users")
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    Response create(@HeaderParam("serviceUserId") Integer serviceUserId, CreateUserResource createUserResource);

    @Path("users/{id}")
    @GET
    @Produces(APPLICATION_JSON)
    Response get(@HeaderParam("serviceUserId") Integer serviceUserId, @PathParam("id") Integer id);

    @Path("users")
    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    Response update(@HeaderParam("serviceUserId") Integer serviceUserId, UserResource userResource);

    @Path("users/{id}")
    @DELETE
    @Produces(APPLICATION_JSON)
    Response delete(@HeaderParam("serviceUserId") Integer serviceUserId, @PathParam("id") Integer id);

}
