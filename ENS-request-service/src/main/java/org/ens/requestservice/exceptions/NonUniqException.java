package org.ens.requestservice.exceptions;

public class NonUniqException extends RuntimeException {
    public NonUniqException (String fieldName, String fieldValue) {
        super("Value " + fieldValue + " cannot be inserted in field " + fieldName + " cause value is not uniq");
    }
}
