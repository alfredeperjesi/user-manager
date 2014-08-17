package org.alfredeperjesi.usermanager.system.domain;

import com.google.common.base.Optional;

public enum UserType {
    SUBSCRIBER {
        @Override
        public boolean validate(Optional<Address> homeAddress, Optional<Address> billingAddress) {
            return homeAddress.isPresent() && billingAddress.isPresent();
        }
    },
    ADMINISTRATOR,
    SUPER_USER;

    public boolean validate(Optional<Address> homeAddress, Optional<Address> billingAddress) {
        return true;
    }

    public boolean isSubscriber() {
        return this == SUBSCRIBER;
    }

    public boolean isAdministrator() {
        return this == ADMINISTRATOR;
    }

    public boolean isSuperUser() {
        return this == SUPER_USER;
    }
}
