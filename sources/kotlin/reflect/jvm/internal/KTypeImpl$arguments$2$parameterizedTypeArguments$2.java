package kotlin.reflect.jvm.internal;

import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.structure.ReflectClassUtilKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, mo37405d2 = {"<anonymous>", "", "Ljava/lang/reflect/Type;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* compiled from: KTypeImpl.kt */
final class KTypeImpl$arguments$2$parameterizedTypeArguments$2 extends Lambda implements Function0<List<? extends Type>> {
    final /* synthetic */ KTypeImpl$arguments$2 this$0;

    KTypeImpl$arguments$2$parameterizedTypeArguments$2(KTypeImpl$arguments$2 kTypeImpl$arguments$2) {
        this.this$0 = kTypeImpl$arguments$2;
        super(0);
    }

    @NotNull
    public final List<Type> invoke() {
        return ReflectClassUtilKt.getParameterizedTypeArguments(this.this$0.this$0.getJavaType$kotlin_reflect_api());
    }
}
