package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* compiled from: KFunctionImpl.kt */
final class KFunctionImpl$descriptor$2 extends Lambda implements Function0<FunctionDescriptor> {
    final /* synthetic */ String $name;
    final /* synthetic */ KFunctionImpl this$0;

    KFunctionImpl$descriptor$2(KFunctionImpl kFunctionImpl, String str) {
        this.this$0 = kFunctionImpl;
        this.$name = str;
        super(0);
    }

    @NotNull
    public final FunctionDescriptor invoke() {
        return this.this$0.getContainer().findFunctionDescriptor(this.$name, this.this$0.signature);
    }
}
