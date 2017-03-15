package io.primeval.common.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.Set;

import io.primeval.common.classloading.CompositeClassLoader;

public class TypeTag<T> {

    public final Type type;
    // non-volatile, world doesn't end if it's written/computed twice.
    private ClassLoader classLoader;

    protected TypeTag() {
        this.type = capture();
    }

    private TypeTag(Type type) {
        this.type = type;
    }

    public static <T> TypeTag<T> of(Class<T> clazz) {
        return new TypeTag<>(clazz);
    }

    public static <T> TypeTag<T> of(Type type) {
        return new TypeTag<>(type);
    }

    public boolean isGeneric() {
        return type instanceof ParameterizedType;
    }

    final Type capture() {
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            return ((ParameterizedType) superclass).getActualTypeArguments()[0];
        }
        throw new AssertionError();

    }

    public Class<T> rawType() {
        return getRawType(type);
    }

    public ClassLoader getClassLoader() {
        if (classLoader == null) {
            setClassLoader();
        }
        return classLoader;
    }

    private void setClassLoader() {
        if (type instanceof Class<?>) {
            classLoader = ((Class<?>) type).getClassLoader();
        } else if (type instanceof ParameterizedType) {
            Set<ClassLoader> cls = new LinkedHashSet<>();
            findClassLoaders(type, cls);
            if (cls.size() == 1) {
                classLoader = cls.iterator().next();
            } else {
                classLoader = new CompositeClassLoader(cls.toArray(new ClassLoader[0]));
            }
        } else {
            throw new IllegalArgumentException("Unsupported type " + type);
        }
    }

    private void findClassLoaders(Type type, /* out */ Set<ClassLoader> cls) {
        if (type instanceof Class<?>) {
            cls.add(((Class<?>) type).getClassLoader());
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            cls.add(((Class<?>) parameterizedType.getRawType()).getClassLoader());
            for (Type t : parameterizedType.getActualTypeArguments()) {
                findClassLoaders(t, cls);
            }
        } else {
            throw new IllegalArgumentException("Unsupported type " + type);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getRawType(Type type) {
        if (type instanceof Class<?>) {
            return (Class<T>) type;
        } else if (type instanceof ParameterizedType) {
            return (Class<T>) ((ParameterizedType) type).getRawType();
        }
        throw new IllegalArgumentException("Unsupported type " + type);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TypeTag other = (TypeTag) obj;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return type.toString();
    }

}