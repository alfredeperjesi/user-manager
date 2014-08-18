package org.alfredeperjesi.usermanager.system.infrastructure.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class InMemoryUserIdGeneratorTest {
    private static final int USER_ID = 1;

    private UserIdGenerator userIdGenerator;

    @Before
    public void setUp() {
        userIdGenerator = new InMemoryUserIdGenerator();
    }

    @Test
    public void generateGeneratesTheNext() {
        Integer useId = userIdGenerator.generate();

        assertThat(useId, equalTo(USER_ID));
    }
}
