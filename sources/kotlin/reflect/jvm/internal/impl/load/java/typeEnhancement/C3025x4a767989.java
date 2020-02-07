package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* renamed from: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$1 */
/* compiled from: signatureEnhancement.kt */
final class C3025x4a767989 extends Lambda implements Function2<List<? extends FqName>, T, T> {
    final /* synthetic */ Annotations $composedAnnotation;

    C3025x4a767989(Annotations annotations) {
        this.$composedAnnotation = annotations;
        super(2);
    }

    @Nullable
    public final <T> T invoke(@NotNull List<FqName> list, @NotNull T t) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(list, "$receiver");
        Intrinsics.checkParameterIsNotNull(t, "qualifier");
        Iterable iterable = list;
        boolean z2 = false;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (this.$composedAnnotation.findAnnotation((FqName) it.next()) != null) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    z2 = true;
                    break;
                }
            }
        }
        if (z2) {
            return t;
        }
        return null;
    }
}
