package kotlin.reflect.jvm.internal;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u000e\u0010\u0002\u001a\n \u0003*\u0004\u0018\u00010\u00010\u00012\u000e\u0010\u0004\u001a\n \u0003*\u0004\u0018\u00010\u00050\u00052,\u0010\u0006\u001a(\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00010\u0001 \u0003*\u0014\u0012\u000e\b\u0001\u0012\n \u0003*\u0004\u0018\u00010\u00010\u0001\u0018\u00010\u00070\u0007H\n¢\u0006\u0004\b\b\u0010\t"}, mo37405d2 = {"<anonymous>", "", "<anonymous parameter 0>", "kotlin.jvm.PlatformType", "method", "Ljava/lang/reflect/Method;", "args", "", "invoke", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* compiled from: AnnotationConstructorCaller.kt */
final class AnnotationConstructorCallerKt$createAnnotationInstance$2 implements InvocationHandler {
    final /* synthetic */ Class $annotationClass;
    final /* synthetic */ AnnotationConstructorCallerKt$createAnnotationInstance$1 $equals$1;
    final /* synthetic */ Lazy $hashCode;
    final /* synthetic */ KProperty $hashCode$metadata;
    final /* synthetic */ Lazy $toString;
    final /* synthetic */ KProperty $toString$metadata;
    final /* synthetic */ Map $values;

    AnnotationConstructorCallerKt$createAnnotationInstance$2(Class cls, Lazy lazy, KProperty kProperty, Lazy lazy2, KProperty kProperty2, AnnotationConstructorCallerKt$createAnnotationInstance$1 annotationConstructorCallerKt$createAnnotationInstance$1, Map map) {
        this.$annotationClass = cls;
        this.$toString = lazy;
        this.$toString$metadata = kProperty;
        this.$hashCode = lazy2;
        this.$hashCode$metadata = kProperty2;
        this.$equals$1 = annotationConstructorCallerKt$createAnnotationInstance$1;
        this.$values = map;
    }

    @Nullable
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        Intrinsics.checkExpressionValueIsNotNull(method, Param.METHOD);
        String name = method.getName();
        if (name != null) {
            int hashCode = name.hashCode();
            if (hashCode != -1776922004) {
                if (hashCode != 147696667) {
                    if (hashCode == 1444986633 && name.equals("annotationType")) {
                        return this.$annotationClass;
                    }
                } else if (name.equals("hashCode")) {
                    Lazy lazy = this.$hashCode;
                    KProperty kProperty = this.$hashCode$metadata;
                    return lazy.getValue();
                }
            } else if (name.equals("toString")) {
                Lazy lazy2 = this.$toString;
                KProperty kProperty2 = this.$toString$metadata;
                return lazy2.getValue();
            }
        }
        if (Intrinsics.areEqual((Object) name, (Object) "equals") && objArr != null && objArr.length == 1) {
            return Boolean.valueOf(this.$equals$1.invoke(ArraysKt.single(objArr)));
        }
        if (this.$values.containsKey(name)) {
            return this.$values.get(name);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Method is not supported: ");
        sb.append(method);
        sb.append(" (args: ");
        if (objArr == null) {
            objArr = new Object[0];
        }
        sb.append(ArraysKt.toList(objArr));
        sb.append(')');
        throw new KotlinReflectionInternalError(sb.toString());
    }
}
