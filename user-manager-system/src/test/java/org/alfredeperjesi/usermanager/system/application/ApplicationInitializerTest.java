package org.alfredeperjesi.usermanager.system.application;

import org.alfredeperjesi.usermanager.system.domain.User;
import org.alfredeperjesi.usermanager.system.domain.UserRepository;
import org.alfredeperjesi.usermanager.system.infrastructure.rest.UserIdGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.alfredeperjesi.usermanager.system.Fixtures.ID;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationInitializerTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserIdGenerator userIdGenerator;

    @InjectMocks
    private ApplicationInitializer applicationInitializer = new ApplicationInitializer();

    @Test
    public void initPersistsTheSuperUser() {
        when(userIdGenerator.generate()).thenReturn(ID);

        applicationInitializer.init();

        verify(userRepository).persist(any(User.class));
    }
}
