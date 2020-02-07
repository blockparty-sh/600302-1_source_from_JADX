package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

/* renamed from: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$2 */
/* compiled from: signatureEnhancement.kt */
final class C3026x4a76798a extends Lambda implements Function2<T, T, T> {
    public static final C3026x4a76798a INSTANCE = new C3026x4a76798a();

    C3026x4a76798a() {
        super(2);
    }

    @Nullable
    public final <T> T invoke(@Nullable T t, @Nullable T t2) {
        if (t == null || t2 == null || Intrinsics.areEqual((Object) t, (Object) t2)) {
            return t != null ? t : t2;
        }
        return null;
    }
}
