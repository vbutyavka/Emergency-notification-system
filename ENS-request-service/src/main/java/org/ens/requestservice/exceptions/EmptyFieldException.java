package org.ens.requestservice.exceptions;

public class EmptyFieldException extends RuntimeException {
    public EmptyFieldException(String fiendName) {
        super("Field " + fiendName + " could not be empty");
    }
}
