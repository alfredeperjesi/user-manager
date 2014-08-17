package org.alfredeperjesi.usermanager.system.domain;

import org.alfredeperjesi.usermanager.system.domain.core.DateValueObject;
import org.springframework.util.Assert;

import java.util.Date;

public class DateOfBirth extends DateValueObject {
    public DateOfBirth(Date value) {
        super(value);
        Assert.isTrue(value.before(new Date()), "Date of birth cannot be in the future!");
    }
}
