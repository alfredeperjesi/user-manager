package org.alfredeperjesi.usermanager.system.domain.core;

import org.springframework.util.Assert;

public class ValueObject<T> {
    private final T value;

    public ValueObject(T value) {
        Assert.notNull(value, "Value cannot be null!");
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValueObject that = (ValueObject) o;

        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public T getValue() {
        return value;
    }
}
