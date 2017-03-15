package io.primeval.common.classloading;

public final class CompositeClassLoader extends ClassLoader {

    private final ClassLoader[] classLoaders;

    public CompositeClassLoader(ClassLoader[] classLoaders) {
        this.classLoaders = classLoaders;
    }

    @Override
    protected Class<?> loadClass(String className, boolean resolve) throws ClassNotFoundException {
        for (int i = 0; i < classLoaders.length; i++) {
            try {
                return classLoaders[i].loadClass(className);
            } catch (ClassNotFoundException cnfe) {
                // continue
            }
        }

        throw new ClassNotFoundException(className);
    }

}
