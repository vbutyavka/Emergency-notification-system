package org.ens.sending.service.exception;

import java.util.UUID;

public class WrongUriException extends RuntimeException {

    public WrongUriException(UUID uuid, String message) {
        super("[" + uuid + "] WrongUriException: " + message);
    }
}
