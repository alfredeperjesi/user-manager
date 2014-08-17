package org.alfredeperjesi.usermanager.system.domain;

import org.alfredeperjesi.usermanager.system.domain.core.StringValueObject;
import org.springframework.util.Assert;

public class EmailAddress extends StringValueObject {
    public EmailAddress(String value) {
        super(value);
        Assert.isTrue(value.matches("([\\w.]+)@([\\w.])*"), "Invalid email format!");
    }
}
