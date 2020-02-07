package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;

/* renamed from: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$createMockJavaIoSerializableType$superTypes$1 */
/* compiled from: JvmBuiltInsSettings.kt */
final class C2944xf01ceaf8 extends Lambda implements Function0<SimpleType> {
    final /* synthetic */ JvmBuiltInsSettings this$0;

    C2944xf01ceaf8(JvmBuiltInsSettings jvmBuiltInsSettings) {
        this.this$0 = jvmBuiltInsSettings;
        super(0);
    }

    @NotNull
    public final SimpleType invoke() {
        SimpleType anyType = this.this$0.moduleDescriptor.getBuiltIns().getAnyType();
        Intrinsics.checkExpressionValueIsNotNull(anyType, "moduleDescriptor.builtIns.anyType");
        return anyType;
    }
}
