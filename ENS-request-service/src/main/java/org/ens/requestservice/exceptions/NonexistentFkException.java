package org.ens.requestservice.exceptions;

public class NonexistentFkException extends RuntimeException {
    public NonexistentFkException(String fieldName, String fieldValue, String tableName) {
        super("Field " + fieldName + " could not contain value " + fieldValue + " cause there is no such value in " + tableName);
    }
}
