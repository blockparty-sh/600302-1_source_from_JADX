package kotlin.reflect.full;

import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0001\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* compiled from: KClasses.kt */
final class KClasses$allSupertypes$1$1$1$1 extends Lambda implements Function0 {
    public static final KClasses$allSupertypes$1$1$1$1 INSTANCE = new KClasses$allSupertypes$1$1$1$1();

    KClasses$allSupertypes$1$1$1$1() {
        super(0);
    }

    @NotNull
    public final Void invoke() {
        StringBuilder sb = new StringBuilder();
        sb.append("An operation is not implemented: ");
        sb.append("Java type for supertype");
        throw new NotImplementedError(sb.toString());
    }
}
