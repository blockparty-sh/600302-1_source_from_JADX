package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* renamed from: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtilsKt$retainMostSpecificInEachOverridableGroup$newResult$1 */
/* compiled from: overridingUtils.kt */
final class C3120x48e71be5 extends Lambda implements Function1<D, D> {
    public static final C3120x48e71be5 INSTANCE = new C3120x48e71be5();

    C3120x48e71be5() {
        super(1);
    }

    @NotNull
    public final D invoke(@NotNull D d) {
        Intrinsics.checkParameterIsNotNull(d, "$receiver");
        return d;
    }
}
