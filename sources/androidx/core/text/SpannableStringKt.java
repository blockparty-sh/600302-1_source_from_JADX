package androidx.core.text;

import android.annotation.SuppressLint;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\b\u001a%\u0010\u0003\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\n\u001a\u001d\u0010\u0003\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\n\u001a\r\u0010\u000b\u001a\u00020\u0002*\u00020\fH\b¨\u0006\r"}, mo37405d2 = {"clearSpans", "", "Landroid/text/Spannable;", "set", "start", "", "end", "span", "", "range", "Lkotlin/ranges/IntRange;", "toSpannable", "", "core-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: SpannableString.kt */
public final class SpannableStringKt {
    @NotNull
    public static final Spannable toSpannable(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$this$toSpannable");
        SpannableString valueOf = SpannableString.valueOf(charSequence);
        Intrinsics.checkExpressionValueIsNotNull(valueOf, "SpannableString.valueOf(this)");
        return valueOf;
    }

    @SuppressLint({"SyntheticAccessor"})
    public static final void clearSpans(@NotNull Spannable spannable) {
        Intrinsics.checkParameterIsNotNull(spannable, "$this$clearSpans");
        Spanned spanned = spannable;
        Object[] spans = spanned.getSpans(0, spanned.length(), Object.class);
        Intrinsics.checkExpressionValueIsNotNull(spans, "getSpans(start, end, T::class.java)");
        for (Object removeSpan : spans) {
            spannable.removeSpan(removeSpan);
        }
    }

    public static final void set(@NotNull Spannable spannable, int i, int i2, @NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(spannable, "$this$set");
        Intrinsics.checkParameterIsNotNull(obj, "span");
        spannable.setSpan(obj, i, i2, 17);
    }

    public static final void set(@NotNull Spannable spannable, @NotNull IntRange intRange, @NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(spannable, "$this$set");
        Intrinsics.checkParameterIsNotNull(intRange, "range");
        Intrinsics.checkParameterIsNotNull(obj, "span");
        spannable.setSpan(obj, intRange.getStart().intValue(), intRange.getEndInclusive().intValue(), 17);
    }
}
