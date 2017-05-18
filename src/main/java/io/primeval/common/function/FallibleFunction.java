package io.primeval.common.function;

@FunctionalInterface
public interface FallibleFunction<T, R> {

    R apply(T t) throws Exception;
}
