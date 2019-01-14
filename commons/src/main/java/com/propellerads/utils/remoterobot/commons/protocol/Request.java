package com.propellerads.utils.remoterobot.commons.protocol;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Request implements Serializable {

    private Operation command;
    private List<Object> operands;

    public Request(Operation command, Object... operands) {
        this.command = command;
        this.operands = Arrays.asList(operands);
    }

    public Operation getCommand() {
        return command;
    }

    public List<Object> getOperands() {
        return operands;
    }
}
