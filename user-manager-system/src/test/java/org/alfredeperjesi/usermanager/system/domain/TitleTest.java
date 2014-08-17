package org.alfredeperjesi.usermanager.system.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class TitleTest {
    private static final String TITLE = "title";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void titleThrowsExceptionWhenValueIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Value cannot be null!");

        new Title(null);
    }
    
    @Test
    public void titleConstructsProperlyWhenValueIsNotNull() {
        Title title = new Title(TITLE);
        
        assertThat(title.getValue(), equalTo(TITLE));
    }
}
