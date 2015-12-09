package com.peter.annota.processor.core.edm.util;

public class AnnotationRuntimeException extends RuntimeException {

    /** Manual generated */
    private static final long serialVersionUID = 42L;

    public AnnotationRuntimeException(final String message) {
        super(message);
    }

    public AnnotationRuntimeException(final Throwable cause) {
        super(cause);
    }

    public AnnotationRuntimeException(final String message,
            final Throwable cause) {
        super(message, cause);
    }
}
