package io.primeval.common.serdes;

import io.primeval.common.type.TypeTag;

public class DeserializationException extends RuntimeException {
    public final TypeTag<?> typeTag;
    public final String mimeType;

    public DeserializationException(TypeTag<?> typeTag, String mimeType) {
        this(typeTag, mimeType, null);
    }

    public DeserializationException(TypeTag<?> typeTag) {
        this(typeTag, (Throwable) null);
    }

    public DeserializationException(TypeTag<?> typeTag, String mimeType, Throwable cause) {
        super("Cannot deserialize content of type " + mimeType + " as " + typeTag, cause);
        this.typeTag = typeTag;
        this.mimeType = mimeType;
    }

    public DeserializationException(TypeTag<?> typeTag, Throwable cause) {
        super("Cannot deserialize content of type " + typeTag, cause);
        this.typeTag = typeTag;
        this.mimeType = null;
    }

}
