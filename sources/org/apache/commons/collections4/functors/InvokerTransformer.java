package org.apache.commons.collections4.functors;

import java.lang.reflect.InvocationTargetException;
import org.apache.commons.collections4.FunctorException;
import org.apache.commons.collections4.Transformer;

public class InvokerTransformer<I, O> implements Transformer<I, O> {
    private final Object[] iArgs;
    private final String iMethodName;
    private final Class<?>[] iParamTypes;

    public static <I, O> Transformer<I, O> invokerTransformer(String str) {
        if (str != null) {
            return new InvokerTransformer(str);
        }
        throw new NullPointerException("The method to invoke must not be null");
    }

    public static <I, O> Transformer<I, O> invokerTransformer(String str, Class<?>[] clsArr, Object[] objArr) {
        if (str == null) {
            throw new NullPointerException("The method to invoke must not be null");
        } else if ((clsArr == null && objArr != null) || ((clsArr != null && objArr == null) || (clsArr != null && objArr != null && clsArr.length != objArr.length))) {
            throw new IllegalArgumentException("The parameter types must match the arguments");
        } else if (clsArr == null || clsArr.length == 0) {
            return new InvokerTransformer(str);
        } else {
            return new InvokerTransformer(str, clsArr, objArr);
        }
    }

    private InvokerTransformer(String str) {
        this.iMethodName = str;
        this.iParamTypes = null;
        this.iArgs = null;
    }

    public InvokerTransformer(String str, Class<?>[] clsArr, Object[] objArr) {
        this.iMethodName = str;
        Object[] objArr2 = null;
        this.iParamTypes = clsArr != null ? (Class[]) clsArr.clone() : null;
        if (objArr != null) {
            objArr2 = (Object[]) objArr.clone();
        }
        this.iArgs = objArr2;
    }

    public O transform(Object obj) {
        String str = "' on '";
        String str2 = "InvokerTransformer: The method '";
        if (obj == null) {
            return null;
        }
        try {
            return obj.getClass().getMethod(this.iMethodName, this.iParamTypes).invoke(obj, this.iArgs);
        } catch (NoSuchMethodException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(this.iMethodName);
            sb.append(str);
            sb.append(obj.getClass());
            sb.append("' does not exist");
            throw new FunctorException(sb.toString());
        } catch (IllegalAccessException unused2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str2);
            sb2.append(this.iMethodName);
            sb2.append(str);
            sb2.append(obj.getClass());
            sb2.append("' cannot be accessed");
            throw new FunctorException(sb2.toString());
        } catch (InvocationTargetException e) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str2);
            sb3.append(this.iMethodName);
            sb3.append(str);
            sb3.append(obj.getClass());
            sb3.append("' threw an exception");
            throw new FunctorException(sb3.toString(), e);
        }
    }
}
