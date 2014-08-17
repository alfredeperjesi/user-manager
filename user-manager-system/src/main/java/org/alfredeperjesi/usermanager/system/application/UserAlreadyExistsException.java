package org.alfredeperjesi.usermanager.system.application;

import org.alfredeperjesi.usermanager.system.domain.User;

public class UserAlreadyExistsException extends Throwable {
    private final User user;

    public UserAlreadyExistsException(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
