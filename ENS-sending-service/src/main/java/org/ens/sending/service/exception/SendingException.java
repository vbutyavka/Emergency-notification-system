package org.ens.sending.service.exception;

import java.util.UUID;

public class SendingException extends RuntimeException {

    public SendingException(UUID uuid, String message) {
        super("[" + uuid + "] SendingException: " + message);
    }
}
