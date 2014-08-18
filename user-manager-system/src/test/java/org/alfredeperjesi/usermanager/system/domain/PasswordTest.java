package org.alfredeperjesi.usermanager.system.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PasswordTest {

    private static final String SHORT_PASSWORD = "pass";

    private static final String VALID_PASSWORD = "password";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void passwordThrowsExceptionWhenValueIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Value cannot be null!");

        new Password(null);
    }

    @Test
    public void passwordThrowsExceptionWhenValueLengthIsLessThanFive() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Password must be at least 5 length!");

        new Password(SHORT_PASSWORD);
    }

    @Test
    public void passwordConstructsProperlyWhenValueIsValid() {
        Password password = new Password(VALID_PASSWORD);

        assertThat(password.getValue(), equalTo(VALID_PASSWORD));
    }
}
