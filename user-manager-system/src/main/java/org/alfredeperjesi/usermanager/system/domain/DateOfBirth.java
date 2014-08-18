package org.alfredeperjesi.usermanager.system.domain;

import java.util.Date;

import org.alfredeperjesi.usermanager.system.domain.core.DateValueObject;
import org.springframework.util.Assert;

public class DateOfBirth extends DateValueObject {
    public DateOfBirth(Date value) {
        super(value);
        Assert.isTrue(value.before(new Date()), "Date of birth cannot be in the future!");
    }
}
