package org.apache.commons.collections4.functors;

import java.lang.reflect.InvocationTargetException;
import org.apache.commons.collections4.FunctorException;
import org.apache.commons.collections4.Transformer;

public class InstantiateTransformer<T> implements Transformer<Class<? extends T>, T> {
    private static final Transformer NO_ARG_INSTANCE = new InstantiateTransformer();
    private final Object[] iArgs;
    private final Class<?>[] iParamTypes;

    public static <T> Transformer<Class<? extends T>, T> instantiateTransformer() {
        return NO_ARG_INSTANCE;
    }

    public static <T> Transformer<Class<? extends T>, T> instantiateTransformer(Class<?>[] clsArr, Object[] objArr) {
        if ((clsArr == null && objArr != null) || ((clsArr != null && objArr == null) || (clsArr != null && objArr != null && clsArr.length != objArr.length))) {
            throw new IllegalArgumentException("Parameter types must match the arguments");
        } else if (clsArr == null || clsArr.length == 0) {
            return new InstantiateTransformer();
        } else {
            return new InstantiateTransformer(clsArr, objArr);
        }
    }

    private InstantiateTransformer() {
        this.iParamTypes = null;
        this.iArgs = null;
    }

    public InstantiateTransformer(Class<?>[] clsArr, Object[] objArr) {
        Object[] objArr2 = null;
        this.iParamTypes = clsArr != null ? (Class[]) clsArr.clone() : null;
        if (objArr != null) {
            objArr2 = (Object[]) objArr.clone();
        }
        this.iArgs = objArr2;
    }

    public T transform(Class<? extends T> cls) {
        if (cls != null) {
            try {
                return cls.getConstructor(this.iParamTypes).newInstance(this.iArgs);
            } catch (NoSuchMethodException unused) {
                throw new FunctorException("InstantiateTransformer: The constructor must exist and be public ");
            } catch (InstantiationException e) {
                throw new FunctorException("InstantiateTransformer: InstantiationException", e);
            } catch (IllegalAccessException e2) {
                throw new FunctorException("InstantiateTransformer: Constructor must be public", e2);
            } catch (InvocationTargetException e3) {
                throw new FunctorException("InstantiateTransformer: Constructor threw an exception", e3);
            }
        } else {
            throw new FunctorException("InstantiateTransformer: Input object was not an instanceof Class, it was a null object");
        }
    }
}
