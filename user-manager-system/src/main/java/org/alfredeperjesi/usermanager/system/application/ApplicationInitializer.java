package org.alfredeperjesi.usermanager.system.application;

import org.alfredeperjesi.usermanager.system.domain.User;
import org.alfredeperjesi.usermanager.system.domain.UserRepository;
import org.alfredeperjesi.usermanager.system.domain.UserType;
import org.alfredeperjesi.usermanager.system.infrastructure.rest.UserIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ApplicationInitializer {
    static final String SUPER_USER = UserType.SUPER_USER.name();
    static final String FIRST_NAME = "User";
    static final String LAST_NAME = "Super";
    static final Date DATE_OF_BIRTH = new Date();
    static final String EMAIL_EMAIL_COM = "email@email.com";
    static final String PASSWORD = "password";
    static final String TITLE = "Super User";

    @Autowired
    private UserIdGenerator userIdGenerator;

    @Autowired
    private UserRepository userRepository;

    public void init() {
        userRepository.persist(User.userBuilder()
                .withId(userIdGenerator.generate())
                .withType(SUPER_USER)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withDateOfBirth(DATE_OF_BIRTH)
                .withEmailAddress(EMAIL_EMAIL_COM)
                .withPassword(PASSWORD)
                .withTitle(TITLE)
                .build());
    }
}
