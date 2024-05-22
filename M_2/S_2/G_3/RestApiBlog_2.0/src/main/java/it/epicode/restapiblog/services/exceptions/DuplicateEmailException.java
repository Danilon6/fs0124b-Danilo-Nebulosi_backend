package it.epicode.restapiblog.services.exceptions;

import java.io.Serial;

public class DuplicateEmailException extends AppException{

    @Serial
    private static final long serialVersionUID = 1L;

    public final String duplicateKey;

    public DuplicateEmailException(String key, String message) {
        super(message);
        this.duplicateKey = key;
    }

    public DuplicateEmailException(String key) {
        this(key, "Chiave duplicata");
    }
}
