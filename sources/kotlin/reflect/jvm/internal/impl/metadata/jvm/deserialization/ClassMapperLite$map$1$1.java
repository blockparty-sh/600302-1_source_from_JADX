package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.spongycastle.pqc.math.linearalgebra.Matrix;

/* compiled from: ClassMapperLite.kt */
final class ClassMapperLite$map$1$1 extends Lambda implements Function2<String, String, Unit> {
    final /* synthetic */ Map receiver$0;

    ClassMapperLite$map$1$1(Map map) {
        this.receiver$0 = map;
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((String) obj, (String) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "kotlinSimpleName");
        Intrinsics.checkParameterIsNotNull(str2, "javaInternalName");
        Map map = this.receiver$0;
        StringBuilder sb = new StringBuilder();
        sb.append("kotlin/");
        sb.append(str);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(Matrix.MATRIX_TYPE_RANDOM_LT);
        sb3.append(str2);
        sb3.append(';');
        map.put(sb2, sb3.toString());
    }
}
