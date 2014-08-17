package org.alfredeperjesi.usermanager.system.application;

import com.google.common.base.Optional;
import org.alfredeperjesi.usermanager.system.domain.User;
import org.springframework.stereotype.Component;

@Component
public class AuthorisationHandler {
    boolean isCreateAllowed(User user, Optional<User> serviceUser) {
        return isAllowedForSubscriber(user, serviceUser) || isAllowedForAdministrator(user, serviceUser) || isAllowedForSuperUser(serviceUser);
    }

    private boolean isAllowedForSubscriber(User user, Optional<User> serviceUser) {
        return !serviceUser.isPresent() && user.isSubscriber();
    }

    private boolean isAllowedForAdministrator(User user, Optional<User> serviceUser) {
        return serviceUser.isPresent() && serviceUser.get().isAdministrator() && user.isSubscriber();
    }

    private boolean isAllowedForSuperUser(Optional<User> serviceUser) {
        return serviceUser.isPresent() && serviceUser.get().isSuperUser();
    }

    boolean isGetAllowed(User user, Optional<User> serviceUser) {
        return (serviceUser.isPresent() && serviceUser.get().getId().equals(user.getId())) || isAllowedForAdministrator(user, serviceUser) || isAllowedForSuperUser(serviceUser);
    }

    boolean isUpdateAllowed(User user, Optional<User> serviceUser) {
        return isAllowedForAdministrator(user, serviceUser) || isAllowedForSuperUser(serviceUser);
    }

    boolean isDeleteAllowed(User user, Optional<User> serviceUser) {
        return isAllowedForSuperUser(serviceUser);
    }

}
