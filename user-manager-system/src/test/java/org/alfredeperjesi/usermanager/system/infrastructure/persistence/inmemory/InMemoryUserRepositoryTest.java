package org.alfredeperjesi.usermanager.system.infrastructure.persistence.inmemory;

import static org.alfredeperjesi.usermanager.system.Fixtures.USER;
import static org.alfredeperjesi.usermanager.system.Fixtures.USER_UPDATED;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.alfredeperjesi.usermanager.system.domain.EmailAddress;
import org.alfredeperjesi.usermanager.system.domain.User;
import org.alfredeperjesi.usermanager.system.domain.UserId;
import org.alfredeperjesi.usermanager.system.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Optional;

public class InMemoryUserRepositoryTest {

    private static final UserId ID = new UserId(1);

    private static final EmailAddress UNKNOWN_EMAIL_ADDRESS = new EmailAddress("email@email.com1");

    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository = new InMemoryUserRepository();
    }

    @Test
    public void getReturnsWithAbsentWhenTheUserIsNotStored() {
        Optional<User> user = userRepository.get(ID);

        assertThat(user.isPresent(), equalTo(false));
    }

    @Test
    public void getReturnsWithUserWhenTheUserIsStored() {
        userRepository.persist(USER);
        Optional<User> user = userRepository.get(ID);

        assertThat(user.get(), equalTo(USER));
    }

    @Test
    public void updateStoresTheUserWhenTheUserNotStored() {
        userRepository.update(USER_UPDATED);
        Optional<User> user = userRepository.get(ID);

        assertThat(user.get(), equalTo(USER_UPDATED));
    }

    @Test
    public void updateReplacesTheUserWhenTheUserStored() {
        userRepository.persist(USER);
        userRepository.update(USER_UPDATED);
        Optional<User> user = userRepository.get(ID);

        assertThat(user.get(), equalTo(USER_UPDATED));
    }

    @Test
    public void deleteDeletesTheUserWhenTheUserStored() {
        userRepository.persist(USER);
        userRepository.delete(ID);
        Optional<User> user = userRepository.get(ID);

        assertThat(user.isPresent(), equalTo(false));
    }

    @Test
    public void findReturnsWithAbsentWhenTheUserIsNotStoredWithTheEmailAddress() {
        userRepository.persist(USER);

        Optional<User> user = userRepository.find(UNKNOWN_EMAIL_ADDRESS);

        assertThat(user.isPresent(), equalTo(false));
    }

    @Test
    public void findReturnsWithProperUserWhenTheUserIsStoredWithTheEmailAddress() {
        userRepository.persist(USER);

        Optional<User> user = userRepository.find(USER.getEmailAddress());

        assertThat(user.get(), equalTo(USER));
    }
}
