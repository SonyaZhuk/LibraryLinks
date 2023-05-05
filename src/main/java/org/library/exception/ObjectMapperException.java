package org.library.exception;

import lombok.NonNull;

public class ObjectMapperException extends AbstractFormattedException {

    public ObjectMapperException(@NonNull String message, Object... params) {
        super(message, params);
    }
}
