package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: utils.kt */
final class UtilsKt$debugInfo$1$1 extends Lambda implements Function1<String, StringBuilder> {
    final /* synthetic */ StringBuilder receiver$0;

    UtilsKt$debugInfo$1$1(StringBuilder sb) {
        this.receiver$0 = sb;
        super(1);
    }

    @NotNull
    public final StringBuilder invoke(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        StringBuilder sb = this.receiver$0;
        sb.append(str);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return StringsKt.appendln(sb);
    }
}
