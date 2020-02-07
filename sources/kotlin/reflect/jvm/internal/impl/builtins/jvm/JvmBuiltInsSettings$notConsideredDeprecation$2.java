package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationsImpl;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmBuiltInsSettings.kt */
final class JvmBuiltInsSettings$notConsideredDeprecation$2 extends Lambda implements Function0<AnnotationsImpl> {
    final /* synthetic */ JvmBuiltInsSettings this$0;

    JvmBuiltInsSettings$notConsideredDeprecation$2(JvmBuiltInsSettings jvmBuiltInsSettings) {
        this.this$0 = jvmBuiltInsSettings;
        super(0);
    }

    @NotNull
    public final AnnotationsImpl invoke() {
        return new AnnotationsImpl(CollectionsKt.listOf(AnnotationUtilKt.createDeprecatedAnnotation$default(this.this$0.moduleDescriptor.getBuiltIns(), "This member is not fully supported by Kotlin compiler, so it may be absent or have different signature in next major version", null, null, 6, null)));
    }
}