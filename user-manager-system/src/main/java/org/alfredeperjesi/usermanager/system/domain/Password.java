package org.alfredeperjesi.usermanager.system.domain;

import org.alfredeperjesi.usermanager.system.domain.core.StringValueObject;
import org.springframework.util.Assert;

public class Password extends StringValueObject {
    public Password(String value) {
        super(value);
        Assert.isTrue(value.length() > 5, "Password must be at least 5 length!");
    }
}
