package kotlin.reflect.jvm.internal;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* renamed from: kotlin.reflect.jvm.internal.AnnotationConstructorCallerKt$createAnnotationInstance$toString$2 */
/* compiled from: AnnotationConstructorCaller.kt */
final class C1413x4fc4299 extends Lambda implements Function0<String> {
    final /* synthetic */ Class $annotationClass;
    final /* synthetic */ Map $values;

    C1413x4fc4299(Class cls, Map map) {
        this.$annotationClass = cls;
        this.$values = map;
        super(0);
    }

    @NotNull
    public final String invoke() {
        StringBuilder sb = new StringBuilder();
        sb.append('@');
        sb.append(this.$annotationClass.getCanonicalName());
        CollectionsKt.joinTo$default(this.$values.entrySet(), sb, ", ", "(", ")", 0, null, C1414xd18867f3.INSTANCE, 48, null);
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
