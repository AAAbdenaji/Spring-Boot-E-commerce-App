package com.misc.sandboxproj.execeptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String Message)
    {
        super(Message);
    }
}
