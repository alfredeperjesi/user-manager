package org.alfredeperjesi.usermanager.system.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class EmailAddressTest {
    private static final String INVALID_EMAIL = "email.com";

    private static final String VALID_EMAIL = "user@email.com";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void emailAddressThrowsExceptionWhenValueIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Value cannot be null!");

        new EmailAddress(null);
    }

    @Test
    public void emailAddressThrowsExceptionWhenValueIsNotAValidEmailAddress() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid email format!");

        new EmailAddress(INVALID_EMAIL);
    }

    @Test
    public void emailAddressConstructsProperlyWhenValueIsValid() {
        EmailAddress emailAddress = new EmailAddress(VALID_EMAIL);

        assertThat(emailAddress.getValue(), equalTo(VALID_EMAIL));
    }
}
