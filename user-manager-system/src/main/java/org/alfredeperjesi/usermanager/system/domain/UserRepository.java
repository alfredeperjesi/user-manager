package org.alfredeperjesi.usermanager.system.domain;

import com.google.common.base.Optional;

public interface UserRepository {
    void persist(User user);

    Optional<User> get(UserId id);

    void update(User user);

    void delete(UserId userId);

    Optional<User> find(EmailAddress emailAddress);
}
