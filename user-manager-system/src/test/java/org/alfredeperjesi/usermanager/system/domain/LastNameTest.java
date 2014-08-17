package org.alfredeperjesi.usermanager.system.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class LastNameTest {
    private static final String LAST_NAME = "last";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void lastNameThrowsExceptionWhenValueIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Value cannot be null!");

        new LastName(null);
    }

    @Test
    public void lastNameConstructsProperlyWhenValueIsNotNull() {
        LastName lastName = new LastName(LAST_NAME);

        assertThat(lastName.getValue(), equalTo(LAST_NAME));
    }
}
