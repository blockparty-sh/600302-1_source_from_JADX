package kotlinx.coroutines.internal;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"kotlinx/coroutines/internal/SystemPropsKt__SystemPropsKt", "kotlinx/coroutines/internal/SystemPropsKt__SystemProps_commonKt"}, mo37406k = 4, mo37407mv = {1, 1, 15})
public final class SystemPropsKt {
    public static final int getAVAILABLE_PROCESSORS() {
        return SystemPropsKt__SystemPropsKt.getAVAILABLE_PROCESSORS();
    }

    public static final int systemProp(@NotNull String str, int i, int i2, int i3) {
        return SystemPropsKt__SystemProps_commonKt.systemProp(str, i, i2, i3);
    }

    public static final long systemProp(@NotNull String str, long j, long j2, long j3) {
        return SystemPropsKt__SystemProps_commonKt.systemProp(str, j, j2, j3);
    }

    @Nullable
    public static final String systemProp(@NotNull String str) {
        return SystemPropsKt__SystemPropsKt.systemProp(str);
    }

    public static final boolean systemProp(@NotNull String str, boolean z) {
        return SystemPropsKt__SystemProps_commonKt.systemProp(str, z);
    }
}
