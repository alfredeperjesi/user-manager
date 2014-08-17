package org.alfredeperjesi.usermanager.api;

import java.io.Serializable;

public class ErrorResource implements Serializable {
    private String errorMessage;

    public ErrorResource(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
