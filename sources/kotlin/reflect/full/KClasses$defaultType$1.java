package kotlin.reflect.full;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KClass;
import kotlin.reflect.jvm.internal.KClassImpl;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, mo37405d2 = {"<anonymous>", "Ljava/lang/Class;", "", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* compiled from: KClasses.kt */
final class KClasses$defaultType$1 extends Lambda implements Function0<Class<? extends Object>> {
    final /* synthetic */ KClass receiver$0;

    KClasses$defaultType$1(KClass kClass) {
        this.receiver$0 = kClass;
        super(0);
    }

    @NotNull
    public final Class<? extends Object> invoke() {
        return ((KClassImpl) this.receiver$0).getJClass();
    }
}
