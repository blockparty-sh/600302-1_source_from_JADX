package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KMutableProperty2Impl.Setter;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/KMutableProperty2Impl$Setter;", "D", "E", "R", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* compiled from: KProperty2Impl.kt */
final class KMutableProperty2Impl$_setter$1 extends Lambda implements Function0<Setter<D, E, R>> {
    final /* synthetic */ KMutableProperty2Impl this$0;

    KMutableProperty2Impl$_setter$1(KMutableProperty2Impl kMutableProperty2Impl) {
        this.this$0 = kMutableProperty2Impl;
        super(0);
    }

    @NotNull
    public final Setter<D, E, R> invoke() {
        return new Setter<>(this.this$0);
    }
}
