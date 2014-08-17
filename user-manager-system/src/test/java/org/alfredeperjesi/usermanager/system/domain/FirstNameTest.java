package org.alfredeperjesi.usermanager.system.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class FirstNameTest {
    private static final String FIRST_NAME = "first";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void firstNameThrowsExceptionWhenValueIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Value cannot be null!");

        new FirstName(null);
    }

    @Test
    public void firstNameConstructsProperlyWhenValueIsNotNull() {
        FirstName firstName = new FirstName(FIRST_NAME);

        assertThat(firstName.getValue(), equalTo(FIRST_NAME));
    }
}
