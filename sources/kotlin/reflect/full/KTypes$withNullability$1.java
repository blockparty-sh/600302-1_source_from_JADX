package kotlin.reflect.full;

import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.internal.KTypeImpl;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "Ljava/lang/reflect/Type;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* compiled from: KTypes.kt */
final class KTypes$withNullability$1 extends Lambda implements Function0<Type> {
    final /* synthetic */ KType receiver$0;

    KTypes$withNullability$1(KType kType) {
        this.receiver$0 = kType;
        super(0);
    }

    @NotNull
    public final Type invoke() {
        return ((KTypeImpl) this.receiver$0).getJavaType$kotlin_reflect_api();
    }
}
