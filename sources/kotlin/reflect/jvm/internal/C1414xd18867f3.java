package kotlin.reflect.jvm.internal;

import java.util.Arrays;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010&\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "entry", "", "", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* renamed from: kotlin.reflect.jvm.internal.AnnotationConstructorCallerKt$createAnnotationInstance$toString$2$1$1 */
/* compiled from: AnnotationConstructorCaller.kt */
final class C1414xd18867f3 extends Lambda implements Function1<Entry<? extends String, ? extends Object>, String> {
    public static final C1414xd18867f3 INSTANCE = new C1414xd18867f3();

    C1414xd18867f3() {
        super(1);
    }

    @NotNull
    public final String invoke(@NotNull Entry<String, ? extends Object> entry) {
        String str;
        Intrinsics.checkParameterIsNotNull(entry, "entry");
        String str2 = (String) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof boolean[]) {
            str = Arrays.toString((boolean[]) value);
        } else if (value instanceof char[]) {
            str = Arrays.toString((char[]) value);
        } else if (value instanceof byte[]) {
            str = Arrays.toString((byte[]) value);
        } else if (value instanceof short[]) {
            str = Arrays.toString((short[]) value);
        } else if (value instanceof int[]) {
            str = Arrays.toString((int[]) value);
        } else if (value instanceof float[]) {
            str = Arrays.toString((float[]) value);
        } else if (value instanceof long[]) {
            str = Arrays.toString((long[]) value);
        } else if (value instanceof double[]) {
            str = Arrays.toString((double[]) value);
        } else if (value instanceof Object[]) {
            str = Arrays.toString((Object[]) value);
        } else {
            str = value.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append('=');
        sb.append(str);
        return sb.toString();
    }
}
