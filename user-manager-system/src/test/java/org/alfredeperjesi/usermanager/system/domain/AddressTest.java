package org.alfredeperjesi.usermanager.system.domain;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class AddressTest {
    private static final String ADDRESS_VALUE = "address";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void addressThrowsExceptionWhenValueIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Value cannot be null!");

        new Address(null);
    }
    
    @Test
    public void addressConstructsProperlyWhenValueIsNotNull() {
        Address address = new Address(ADDRESS_VALUE);
        
        assertThat(address.getValue(), equalTo(ADDRESS_VALUE));
    }
}
