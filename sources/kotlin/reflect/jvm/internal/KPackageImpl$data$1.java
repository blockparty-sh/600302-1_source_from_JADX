package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00060\u0001R\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, mo37405d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/KPackageImpl$Data;", "Lkotlin/reflect/jvm/internal/KPackageImpl;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* compiled from: KPackageImpl.kt */
final class KPackageImpl$data$1 extends Lambda implements Function0<Data> {
    final /* synthetic */ KPackageImpl this$0;

    KPackageImpl$data$1(KPackageImpl kPackageImpl) {
        this.this$0 = kPackageImpl;
        super(0);
    }

    @NotNull
    public final Data invoke() {
        return new Data();
    }
}