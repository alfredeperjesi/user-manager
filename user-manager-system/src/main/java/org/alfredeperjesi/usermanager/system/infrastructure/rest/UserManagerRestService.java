package org.alfredeperjesi.usermanager.system.infrastructure.rest;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import javax.ws.rs.core.Response;

import org.alfredeperjesi.usermanager.api.CreateUserResource;
import org.alfredeperjesi.usermanager.api.UserManagerApi;
import org.alfredeperjesi.usermanager.api.UserResource;
import org.alfredeperjesi.usermanager.system.application.MethodNotAllowedException;
import org.alfredeperjesi.usermanager.system.application.UserAlreadyExistsException;
import org.alfredeperjesi.usermanager.system.application.UserManagerService;
import org.alfredeperjesi.usermanager.system.domain.User;
import org.alfredeperjesi.usermanager.system.domain.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;

@Component
public class UserManagerRestService implements UserManagerApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagerRestService.class);

    private final UserManagerService userManagerService;

    private final UserAssembler userAssembler;

    @Autowired
    public UserManagerRestService(UserManagerService userManagerService, UserAssembler userAssembler) {
        this.userManagerService = userManagerService;
        this.userAssembler = userAssembler;
    }

    @Override
    public Response create(Integer serviceUserId, CreateUserResource createUserResource) {
        LOGGER.info("Creating user resource by service user id {}, user resource {}", serviceUserId, createUserResource);

        User user = userAssembler.assemble(createUserResource);
        Optional<UserId> optionalServiceUserId = userAssembler.assembleOptionalUserId(serviceUserId);
        try {
            userManagerService.create(optionalServiceUserId, user);
            UserResource userResource = userAssembler.deassemble(user);

            LOGGER.info("User resource created by service user id {}, user resource {}", serviceUserId, userResource);

            return Response.status(CREATED).entity(userResource).build();
        } catch (MethodNotAllowedException e) {
            LOGGER.info("User resource creation is not allowed for service user id {}, user resource {}", serviceUserId, createUserResource);

            return Response.status(UNAUTHORIZED).build();
        } catch (UserAlreadyExistsException e) {
            UserResource userResource = userAssembler.deassemble(e.getUser());
            LOGGER.info("User resource already created for service user id {}, user resource {}", serviceUserId, createUserResource);

            return Response.ok().entity(userResource).build();
        }
    }

    @Override
    public Response get(Integer serviceUserId, Integer id) {
        LOGGER.info("Getting user resource by service user id {}, user resource id {}", serviceUserId, id);

        Optional<UserId> optionalServiceUserId = userAssembler.assembleOptionalUserId(serviceUserId);
        UserId userId = userAssembler.assembleUserId(id);
        try {
            Optional<User> user = userManagerService.get(optionalServiceUserId, userId);
            if (user.isPresent()) {
                UserResource userResource = userAssembler.deassemble(user.get());

                LOGGER.info("User resource found by service user id {}, user resource id {}", serviceUserId, id);

                return Response.ok().entity(userResource).build();
            } else {
                LOGGER.info("User resource not found by service user id {}, user resource id {}", serviceUserId, id);

                return Response.status(NOT_FOUND).build();
            }
        } catch (MethodNotAllowedException e) {
            LOGGER.info("User resource retrieval is not allowed for service user id {}, user resource id {}", serviceUserId, id);

            return Response.status(UNAUTHORIZED).build();
        }
    }

    @Override
    public Response update(Integer serviceUserId, UserResource userResource) {
        LOGGER.info("Updating user resource by service user id {}, user resource {}", serviceUserId, userResource);

        Optional<UserId> optionalServiceUserId = userAssembler.assembleOptionalUserId(serviceUserId);
        User user = userAssembler.assemble(userResource);
        try {
            userManagerService.update(optionalServiceUserId, user);

            LOGGER.info("User resource updated by service user id {}, user resource {}", serviceUserId, userResource);

            return Response.ok().entity(userResource).build();
        } catch (MethodNotAllowedException e) {
            LOGGER.info("User resource update is not allowed for service user id {}, user resource {}", serviceUserId, userResource);

            return Response.status(UNAUTHORIZED).build();
        }
    }

    @Override
    public Response delete(Integer serviceUserId, Integer id) {
        LOGGER.info("Deleting user resource by service user id {}, user resource id {}", serviceUserId, id);

        Optional<UserId> optionalServiceUserId = userAssembler.assembleOptionalUserId(serviceUserId);
        UserId userId = userAssembler.assembleUserId(id);
        try {
            userManagerService.delete(optionalServiceUserId, userId);

            LOGGER.info("User resource deleted by service user id {}, user resource id {}", serviceUserId, id);

            return Response.noContent().build();
        } catch (MethodNotAllowedException e) {
            LOGGER.info("User resource delete not authorised {}", id);

            return Response.status(UNAUTHORIZED).build();
        }
    }
}
