package io.primeval.common.function;

@FunctionalInterface
public interface FallibleFunction<T, R, E extends Throwable> {

    R apply(T t) throws E;
}
