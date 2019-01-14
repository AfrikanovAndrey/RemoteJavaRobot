package com.propellerads.utils.remoterobot.commons.protocol;

import java.io.Serializable;

public class Response implements Serializable {

    private Operation command;
    private boolean success;
    private String message;
    private Object resultObject;

    public Response(Operation command) {
        this.command = command;
    }

    public Response withMessage(final String message) {
        this.message = message;
        return this;
    }

    public Response withResultObject(final Object resultObject) {
        this.resultObject = resultObject;
        return this;
    }

    public Response withSuccess(final boolean successFlag) {
        this.success = successFlag;
        return this;
    }

    public Operation getCommand() {
        return command;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getResultObject() {
        return resultObject;
    }
}
