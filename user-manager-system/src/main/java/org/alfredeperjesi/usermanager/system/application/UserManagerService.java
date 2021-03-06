package org.alfredeperjesi.usermanager.system.application;

import org.alfredeperjesi.usermanager.system.domain.User;
import org.alfredeperjesi.usermanager.system.domain.UserId;
import org.alfredeperjesi.usermanager.system.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;

@Component
public class UserManagerService {
    private final UserRepository userRepository;

    private final AuthorisationHandler authorisationHandler;

    @Autowired
    public UserManagerService(UserRepository userRepository, AuthorisationHandler authorisationHandler) {
        this.userRepository = userRepository;
        this.authorisationHandler = authorisationHandler;
    }

    public void create(Optional<UserId> serviceUserId, User user) throws MethodNotAllowedException, UserAlreadyExistsException {
        Optional<User> serviceUser = findServiceUser(serviceUserId);
        if (authorisationHandler.isCreateAllowed(user, serviceUser)) {
            Optional<User> storedUser = userRepository.find(user.getEmailAddress());
            if (storedUser.isPresent()) {
                throw new UserAlreadyExistsException(storedUser.get());
            }
            userRepository.persist(user);
        } else {
            throw new MethodNotAllowedException("Create not allowed");
        }
    }

    public Optional<User> get(Optional<UserId> serviceUserId, UserId id) throws MethodNotAllowedException {
        Optional<User> serviceUser = findServiceUser(serviceUserId);
        Optional<User> user = userRepository.get(id);
        if (!user.isPresent() || authorisationHandler.isGetAllowed(user.get(), serviceUser)) {
            return user;
        } else {
            throw new MethodNotAllowedException("Get not allowed");
        }
    }

    public void update(Optional<UserId> serviceUserId, User user) throws MethodNotAllowedException {
        Optional<User> serviceUser = findServiceUser(serviceUserId);
        if (authorisationHandler.isUpdateAllowed(user, serviceUser)) {
            userRepository.update(user);
        } else {
            throw new MethodNotAllowedException("Update not allowed");
        }
    }

    public void delete(Optional<UserId> serviceUserId, UserId userId) throws MethodNotAllowedException {
        Optional<User> serviceUser = findServiceUser(serviceUserId);
        Optional<User> user = userRepository.get(userId);
        if (authorisationHandler.isDeleteAllowed(user.get(), serviceUser)) {
            userRepository.delete(userId);
        } else {
            throw new MethodNotAllowedException("Delete not allowed");
        }
    }

    private Optional<User> findServiceUser(Optional<UserId> serviceUserId) {
        return serviceUserId.isPresent() ? findServiceUser(serviceUserId.get()) : Optional.<User>absent();
    }

    private Optional<User> findServiceUser(UserId serviceUserId) {
        return userRepository.get(serviceUserId);
    }
}
