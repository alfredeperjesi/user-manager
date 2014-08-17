package org.alfredeperjesi.usermanager.system.infrastructure.rest;

import com.google.common.base.Optional;
import org.alfredeperjesi.usermanager.api.CreateUserResource;
import org.alfredeperjesi.usermanager.api.UserResource;
import org.alfredeperjesi.usermanager.api.UserTypeResource;
import org.alfredeperjesi.usermanager.system.domain.Address;
import org.alfredeperjesi.usermanager.system.domain.User;
import org.alfredeperjesi.usermanager.system.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

import static org.alfredeperjesi.usermanager.system.domain.User.userBuilder;

@Component
public class UserAssembler {
    private UserIdGenerator userIdGenerator;

    @Autowired
    public UserAssembler(UserIdGenerator userIdGenerator) {
        this.userIdGenerator = userIdGenerator;
    }

    public User assemble(CreateUserResource createUserResource) {
        return userBuilder()
                .withId(userIdGenerator.generate())
                .withType(createUserResource.getType().name())
                .withFirstName(createUserResource.getFirstName())
                .withLastName(createUserResource.getLastName())
                .withTitle(createUserResource.getTitle())
                .withPassword(createUserResource.getPassword())
                .withDateOfBirth(createUserResource.getDateOfBirth())
                .withEmailAddress(createUserResource.getEmailAddress())
                .withBillingAddress(createUserResource.getBillingAddress())
                .withHomeAddress(createUserResource.getHomeAddress())
                .build();
    }

    public UserResource deassemble(User user) {
        return new UserResource(user.getId().getValue(),
                UserTypeResource.valueOf(user.getType().name()),
                user.getFirstName().getValue(),
                user.getLastName().getValue(),
                user.getTitle().getValue(),
                user.getDateOfBirth().getValue(),
                user.getEmailAddress().getValue(),
                user.getPassword().getValue(),
                getAddress(user.getHomeAddress()),
                getAddress(user.getBillingAddress()));
    }

    public User assemble(UserResource userResource) {
        return userBuilder()
                .withId(userResource.getId())
                .withType(userResource.getType().name())
                .withFirstName(userResource.getFirstName())
                .withLastName(userResource.getLastName())
                .withTitle(userResource.getTitle())
                .withPassword(userResource.getPassword())
                .withDateOfBirth(userResource.getDateOfBirth())
                .withEmailAddress(userResource.getEmailAddress())
                .withBillingAddress(userResource.getBillingAddress())
                .withHomeAddress(userResource.getHomeAddress())
                .build();
    }

    private String getAddress(Optional<Address> address) {
        return address.isPresent() ? address.get().getValue() : null;
    }

    public Optional<UserId> assembleOptionalUserId(Integer userId) {
        return userId != null?Optional.of(assembleUserId(userId)):Optional.<UserId>absent();
    }

    public UserId assembleUserId(Integer userId) {
        return new UserId(userId);
    }
}
