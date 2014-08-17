package org.alfredeperjesi.usermanager.system.domain.core;

import org.springframework.util.Assert;

public class StringValueObject extends ValueObject<String> {
    public StringValueObject(String value) {
        super(value);
        Assert.hasLength(value, "Value cannot be empty string!");
    }
}
