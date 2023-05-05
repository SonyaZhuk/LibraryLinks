package org.library.exception;

import lombok.NonNull;

public class ElasticClientException extends AbstractFormattedException {
    public ElasticClientException(@NonNull String message, Object... params) {
        super(message, params);
    }
}
