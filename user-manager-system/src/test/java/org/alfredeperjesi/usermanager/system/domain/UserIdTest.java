package org.alfredeperjesi.usermanager.system.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class UserIdTest {

    private static final int NEGATIVE_ID = -1;
    private static final int VALID_VALUE = 1;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void userIdThrowsExceptionWhenValueIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Value cannot be null!");

        new UserId(null);
    }

    @Test
    public void userIdThrowsExceptionWhenValueIsNegative() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("User id must be positive!");

        new UserId(NEGATIVE_ID);
    }

    @Test
    public void userIdConstructsProperlyWhenValueIsValid() {
        UserId userId = new UserId(VALID_VALUE);

        assertThat(userId.getValue(), equalTo(VALID_VALUE));
    }
}
