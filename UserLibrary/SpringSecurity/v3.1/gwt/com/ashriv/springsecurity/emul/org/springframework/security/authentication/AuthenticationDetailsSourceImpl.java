package org.springframework.security.authentication;

import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;

/**
 * Base implementation of {@link AuthenticationDetailsSource}.
 * <p>
 * By default will create an instance of <code>AuthenticationDetails</code>.
 * Any object that accepts an <code>Object</code> as its sole constructor can
 * be used instead of this default.
 * </p>
 *
 * @author Ruud Senden
 * @since 2.0
 * @deprecated Write an implementation of AuthenticationDetailsSource which returns the desired type directly.
 */
@Deprecated
public class AuthenticationDetailsSourceImpl implements AuthenticationDetailsSource<Object, Object> {
    //~ Instance fields ================================================================================================

    private Class<?> clazz = AuthenticationDetails.class;

    //~ Methods ========================================================================================================

    public Object buildDetails(Object context) {
        Object result = null;
        try {
            Constructor<?> constructor = getFirstMatchingConstructor(context);
            result = constructor.newInstance(context);
        } catch (Exception ex) {
            ReflectionUtils.handleReflectionException(ex);
        }

        return result;
    }

    /**
     * Return the first matching constructor that can take the given object
     * as an argument. Please note that we cannot use
     * getDeclaredConstructor(new Class[]{object.getClass()})
     * as this will only match if the constructor argument type matches
     * the object type exactly (instead of checking whether it is assignable)
     *
     * @param object the object for which to find a matching constructor
     * @return a matching constructor for the given object
     * @throws NoSuchMethodException if no matching constructor can be found
     */
    private Constructor<?> getFirstMatchingConstructor(Object object) throws NoSuchMethodException {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        Constructor<?> constructor = null;
        for (Constructor<?> tryMe : constructors) {
            Class<?>[] parameterTypes = tryMe.getParameterTypes();
            if (parameterTypes.length == 1 && (object == null || parameterTypes[0].isInstance(object))) {
                constructor = tryMe;
                break;
            }
        }

        if (constructor == null) {
            if (object == null) {
                throw new NoSuchMethodException("No constructor found that can take a single argument");
            } else {
                throw new NoSuchMethodException("No constructor found that can take a single argument of type " + object.getClass());
            }
        }
        return constructor;
    }

    public void setClazz(Class<?> clazz) {
        Assert.notNull(clazz, "Class required");
        this.clazz = clazz;
    }
}
