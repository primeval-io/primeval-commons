package io.primeval.common.property;

import java.lang.reflect.Array;
import java.util.Map;

public class PropertyHelper {

    public static final <T> T[] getProperty(String propertyName, Class<T> propertyType, Map<String, ?> propertyMap) {
        return getProperty(propertyName, propertyType, true, true, propertyMap);
    }

    @SuppressWarnings("unchecked")
    public static final <T> T[] getProperty(String propertyName, Class<T> propertyType, boolean tryToConvert, boolean ignoreErrors,
            Map<String, ?> propertyMap) {
        Object prop = propertyMap.get(propertyName);
        if (prop == null) {
            return (T[]) Array.newInstance(propertyType, 0);
        }
        if (propertyType == String.class) {
            return (T[]) asString(prop, tryToConvert, ignoreErrors);
        } else if (propertyType == Integer.class) {
            return (T[]) asInteger(prop, tryToConvert, ignoreErrors);
        } else if (propertyType == Long.class) {
            return (T[]) asLong(prop, tryToConvert, ignoreErrors);
        } else if (propertyType == Short.class) {
            return (T[]) asShort(prop, tryToConvert, ignoreErrors);
        } else if (propertyType == Double.class) {
            return (T[]) asDouble(prop, tryToConvert, ignoreErrors);
        } else if (propertyType == Float.class) {
            return (T[]) asFloat(prop, tryToConvert, ignoreErrors);
        } else if (propertyType == Boolean.class) {
            return (T[]) asBoolean(prop, tryToConvert, ignoreErrors);
        }
        // let Character & Byte for some other time..
        throw new IllegalArgumentException("Unsupported/illegal propertyType: " + propertyType.getSimpleName());
    }

    public static String[] asString(Object propObj, boolean tryToConvert, boolean ignoreErrors) {
        if (propObj == null) {
            return new String[0];
        } else if (propObj instanceof String[]) {
            return (String[]) propObj;
        } else if (propObj instanceof String) {
            return new String[] { (String) propObj };
        } else {
            if (tryToConvert) {
                if (propObj.getClass().isArray()) {
                    Object[] nn = (Object[]) propObj;
                    String[] res = new String[nn.length];
                    for (int i = 0; i < res.length; i++) {
                        res[i] = nn[i].toString();
                    }
                    return res;
                } else {
                    return new String[] { propObj.toString() };
                }
            } else {
                if (ignoreErrors) {
                    return new String[0];
                } else {
                    throw failConvert(String.class);
                }
            }
        }
    }

    public static Long[] asLong(Object propObj, boolean tryToConvert, boolean ignoreErrors) {
        if (propObj == null) {
            return new Long[0];
        } else if (propObj instanceof Long[]) {
            return (Long[]) propObj;
        } else if (propObj instanceof Long) {
            return new Long[] { (Long) propObj };
        } else {
            if (tryToConvert) {
                if (propObj.getClass().isArray()) {
                    Class<?> componentType = propObj.getClass().getComponentType();
                    if (Number.class.isAssignableFrom(componentType)) {
                        Number[] nn = (Number[]) propObj;
                        Long[] res = new Long[nn.length];
                        for (int i = 0; i < res.length; i++) {
                            res[i] = nn[i].longValue();
                        }
                        return res;
                    } else {
                        throw failConvert(Long.class);
                    }
                } else {
                    if (Number.class.isAssignableFrom(propObj.getClass())) {
                        return new Long[] { ((Number) propObj).longValue() };
                    } else {
                        throw failConvert(Long.class);
                    }
                }
            } else {
                if (ignoreErrors) {
                    return new Long[0];
                } else {
                    throw failConvert(Long.class);
                }
            }

        }
    }

    public static Integer[] asInteger(Object propObj, boolean tryToConvert, boolean ignoreErrors) {
        if (propObj == null) {
            return new Integer[0];
        } else if (propObj instanceof Integer[]) {
            return (Integer[]) propObj;
        } else if (propObj instanceof Integer) {
            return new Integer[] { (Integer) propObj };
        } else {
            if (tryToConvert) {
                if (propObj.getClass().isArray()) {
                    Class<?> componentType = propObj.getClass().getComponentType();
                    if (Number.class.isAssignableFrom(componentType)) {
                        Number[] nn = (Number[]) propObj;
                        Integer[] res = new Integer[nn.length];
                        for (int i = 0; i < res.length; i++) {
                            res[i] = nn[i].intValue();
                        }
                        return res;
                    } else {
                        throw failConvert(Integer.class);
                    }
                } else {
                    if (Number.class.isAssignableFrom(propObj.getClass())) {
                        return new Integer[] { ((Number) propObj).intValue() };
                    } else {
                        throw failConvert(Integer.class);
                    }
                }
            } else {
                if (ignoreErrors) {
                    return new Integer[0];
                } else {
                    throw failConvert(Integer.class);
                }
            }

        }
    }

    public static Float[] asFloat(Object propObj, boolean tryToConvert, boolean ignoreErrors) {
        if (propObj == null) {
            return new Float[0];
        } else if (propObj instanceof Float[]) {
            return (Float[]) propObj;
        } else if (propObj instanceof Float) {
            return new Float[] { (Float) propObj };
        } else {
            if (tryToConvert) {
                if (propObj.getClass().isArray()) {
                    Class<?> componentType = propObj.getClass().getComponentType();
                    if (Number.class.isAssignableFrom(componentType)) {
                        Number[] nn = (Number[]) propObj;
                        Float[] res = new Float[nn.length];
                        for (int i = 0; i < res.length; i++) {
                            res[i] = nn[i].floatValue();
                        }
                        return res;
                    } else {
                        throw failConvert(Float.class);
                    }
                } else {
                    if (Number.class.isAssignableFrom(propObj.getClass())) {
                        return new Float[] { ((Number) propObj).floatValue() };
                    } else {
                        throw failConvert(Float.class);
                    }
                }
            } else {
                if (ignoreErrors) {
                    return new Float[0];
                } else {
                    throw failConvert(Float.class);
                }
            }
        }
    }

    public static Double[] asDouble(Object propObj, boolean tryToConvert, boolean ignoreErrors) {
        if (propObj == null) {
            return new Double[0];
        } else if (propObj instanceof Double[]) {
            return (Double[]) propObj;
        } else if (propObj instanceof Double) {
            return new Double[] { (Double) propObj };
        } else {
            if (tryToConvert) {
                if (propObj.getClass().isArray()) {
                    Class<?> componentType = propObj.getClass().getComponentType();
                    if (Number.class.isAssignableFrom(componentType)) {
                        Number[] nn = (Number[]) propObj;
                        Double[] res = new Double[nn.length];
                        for (int i = 0; i < res.length; i++) {
                            res[i] = nn[i].doubleValue();
                        }
                        return res;
                    } else {
                        throw failConvert(Double.class);
                    }
                } else {
                    if (Number.class.isAssignableFrom(propObj.getClass())) {
                        return new Double[] { ((Number) propObj).doubleValue() };
                    } else {
                        throw failConvert(Double.class);
                    }
                }
            } else {
                if (ignoreErrors) {
                    return new Double[0];
                } else {
                    throw failConvert(Double.class);
                }
            }

        }
    }

    public static Short[] asShort(Object propObj, boolean tryToConvert, boolean ignoreErrors) {
        if (propObj == null) {
            return new Short[0];
        } else if (propObj instanceof Short[]) {
            return (Short[]) propObj;
        } else if (propObj instanceof Short) {
            return new Short[] { (Short) propObj };
        } else {
            if (tryToConvert) {
                if (propObj.getClass().isArray()) {
                    Class<?> componentType = propObj.getClass().getComponentType();
                    if (Number.class.isAssignableFrom(componentType)) {
                        Number[] nn = (Number[]) propObj;
                        Short[] res = new Short[nn.length];
                        for (int i = 0; i < res.length; i++) {
                            res[i] = nn[i].shortValue();
                        }
                        return res;
                    } else {
                        throw failConvert(Short.class);
                    }
                } else {
                    if (Number.class.isAssignableFrom(propObj.getClass())) {
                        return new Short[] { ((Number) propObj).shortValue() };
                    } else {
                        throw failConvert(Short.class);
                    }
                }
            } else {
                if (ignoreErrors) {
                    return new Short[0];
                } else {
                    throw failConvert(Short.class);
                }
            }

        }
    }

    public static Boolean[] asBoolean(Object propObj, boolean tryToConvert, boolean ignoreErrors) {
        if (propObj == null) {
            return new Boolean[0];
        } else if (propObj instanceof Boolean[]) {
            return (Boolean[]) propObj;
        } else if (propObj instanceof Boolean) {
            return new Boolean[] { (Boolean) propObj };
        } else if (ignoreErrors) {
            return new Boolean[0];
        }
        throw failConvert(Boolean.class);
    }

    private static RuntimeException failConvert(Class<?> targetClass) {
        throw new IllegalArgumentException("Cannot convert property to " + targetClass.getSimpleName());
    }
}
