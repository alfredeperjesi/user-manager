package org.alfredeperjesi.usermanager.api;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel(value = "Error resource", description = "Resource which used for errors")
public class ErrorResource implements Serializable {
    private String errorMessage;

    public ErrorResource(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
