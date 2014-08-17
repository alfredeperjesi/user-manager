package org.alfredeperjesi.usermanager.system.domain;

import org.alfredeperjesi.usermanager.system.domain.core.IntegerValueObject;
import org.springframework.util.Assert;

public class UserId extends IntegerValueObject {
    public UserId(Integer value) {
        super(value);
        Assert.isTrue(value > 0, "User id must be positive!");
    }
}
