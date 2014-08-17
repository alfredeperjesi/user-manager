package org.alfredeperjesi.usermanager.system.infrastructure.persistence.inmemory;

import com.google.common.base.Optional;
import org.alfredeperjesi.usermanager.system.domain.EmailAddress;
import org.alfredeperjesi.usermanager.system.domain.User;
import org.alfredeperjesi.usermanager.system.domain.UserId;
import org.alfredeperjesi.usermanager.system.domain.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryUserRepository implements UserRepository {
    private final Map<UserId, User> users = new ConcurrentHashMap<UserId, User>();

    @Override
    public void persist(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public Optional<User> get(UserId id) {
        return Optional.fromNullable(users.get(id));
    }

    @Override
    public void update(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void delete(UserId userId) {
        users.remove(userId);
    }

    @Override
    public Optional<User> find(EmailAddress emailAddress) {
        for (User user : users.values()) {
            if (user.getEmailAddress().equals(emailAddress)) {
                return Optional.of(user);
            }
        }
        return Optional.absent();
    }
}
