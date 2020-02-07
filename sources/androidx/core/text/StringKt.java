package androidx.core.text;

import android.text.TextUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\b¨\u0006\u0002"}, mo37405d2 = {"htmlEncode", "", "core-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: String.kt */
public final class StringKt {
    @NotNull
    public static final String htmlEncode(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$this$htmlEncode");
        String htmlEncode = TextUtils.htmlEncode(str);
        Intrinsics.checkExpressionValueIsNotNull(htmlEncode, "TextUtils.htmlEncode(this)");
        return htmlEncode;
    }
}
