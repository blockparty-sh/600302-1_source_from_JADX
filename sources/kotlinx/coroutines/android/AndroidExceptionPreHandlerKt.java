package kotlinx.coroutines.android;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0002"}, mo37405d2 = {"getter", "Ljava/lang/reflect/Method;", "kotlinx-coroutines-android"}, mo37406k = 2, mo37407mv = {1, 1, 13})
/* compiled from: AndroidExceptionPreHandler.kt */
public final class AndroidExceptionPreHandlerKt {
    /* access modifiers changed from: private */
    public static final Method getter;

    static {
        Method method = null;
        boolean z = false;
        try {
            Method declaredMethod = Thread.class.getDeclaredMethod("getUncaughtExceptionPreHandler", new Class[0]);
            Intrinsics.checkExpressionValueIsNotNull(declaredMethod, "it");
            if (Modifier.isPublic(declaredMethod.getModifiers()) && Modifier.isStatic(declaredMethod.getModifiers())) {
                z = true;
            }
            if (z) {
                method = declaredMethod;
            }
        } catch (Throwable unused) {
        }
        getter = method;
    }
}
