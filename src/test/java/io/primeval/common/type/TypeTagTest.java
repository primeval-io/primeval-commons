package io.primeval.common.type;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.junit.Test;

import io.primeval.common.type.TypeTag;

public class TypeTagTest {

    @Test
    public void rawClassTest() {
        TypeTag<String> tr1 = TypeTag.of(String.class);
        TypeTag<String> tr2 = new TypeTag<String>() {};
        assertThat(tr1.type).isEqualTo(tr2.type);
        assertThat(tr1.isGeneric()).isFalse();
    }

    @Test
    public void genericClassTest() {
        TypeTag<List<String>> tr = new TypeTag<List<String>>() {};

        ParameterizedType pr = new ParameterizedType() {

            @Override
            public Type getRawType() {
                return List.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }

            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] { String.class };
            }
        };
        assertThat(tr.type).isEqualTo(pr);
        assertThat(tr.isGeneric()).isTrue();

    }

}
