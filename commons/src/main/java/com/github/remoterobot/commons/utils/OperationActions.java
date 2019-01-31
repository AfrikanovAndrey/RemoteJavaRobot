package com.github.remoterobot.commons.utils;

@FunctionalInterface
public interface OperationActions {

    /*
     Execute actions that can return some result object
     */
    Object execute() throws Exception;
}
