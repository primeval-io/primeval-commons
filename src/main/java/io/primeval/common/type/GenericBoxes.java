package io.primeval.common.type;

import java.lang.reflect.ParameterizedType;

// Generic Types with 1 typeParameter
public final class GenericBoxes {

    private GenericBoxes() {
    }
    
    public static final <T, U> TypeTag<U> typeParameter(TypeTag<T> boxTypeTag) {
        @SuppressWarnings("unchecked")
        TypeTag<U> insideType = (TypeTag<U>) TypeTag.of(Object.class);
        if (boxTypeTag.type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) boxTypeTag.type;
            insideType = TypeTag.of(parameterizedType.getActualTypeArguments()[0]);
        }
        return insideType;
    }
}
