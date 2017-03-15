package io.primeval.common.serdes;

import io.primeval.common.type.TypeTag;

public class SerializationException extends RuntimeException {

    public final TypeTag<?> typeTag;
    public final String mimeType;

    public SerializationException(TypeTag<?> typeTag, String mimeType) {
        this(typeTag, mimeType, null);
    }

    public SerializationException(TypeTag<?> typeTag, String mimeType, Throwable cause) {
        super("Cannot serialize " + typeTag + " as " + mimeType, cause);
        this.typeTag = typeTag;
        this.mimeType = mimeType;
    }

}
