package org.alfredeperjesi.usermanager.system.domain;

import static org.alfredeperjesi.usermanager.system.Fixtures.getDate;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DateOfBirthTest {
    private static final int FUTURE = 1;

    private static final int PAST = -1;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void dateOfBirthThrowsExceptionWhenValueIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Value cannot be null!");

        new DateOfBirth(null);
    }

    @Test
    public void dateOfBirthThrowsExceptionWhenValueIsInTheFuture() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Date of birth cannot be in the future!");

        Date futureDate = getDate(FUTURE);
        new DateOfBirth(futureDate);
    }

    @Test
    public void dateOfBirthConstructsProperlyWhenValueIsInThePast() {
        Date pastDate = getDate(PAST);
        DateOfBirth dateOfBirth = new DateOfBirth(pastDate);

        assertThat(dateOfBirth.getValue(), equalTo(pastDate));
    }
}
