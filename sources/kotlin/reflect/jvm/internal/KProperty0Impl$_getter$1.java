package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KProperty0Impl.Getter;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002 \u0001H\n¢\u0006\u0002\b\u0003"}, mo37405d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/KProperty0Impl$Getter;", "R", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* compiled from: KProperty0Impl.kt */
final class KProperty0Impl$_getter$1 extends Lambda implements Function0<Getter<? extends R>> {
    final /* synthetic */ KProperty0Impl this$0;

    KProperty0Impl$_getter$1(KProperty0Impl kProperty0Impl) {
        this.this$0 = kProperty0Impl;
        super(0);
    }

    @NotNull
    public final Getter<R> invoke() {
        return new Getter<>(this.this$0);
    }
}
