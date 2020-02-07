package kotlin.reflect.jvm.internal.impl.load.kotlin;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* renamed from: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader$loadClassAnnotations$1 */
/* compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
public final class C3029xc0ff27f9 implements AnnotationVisitor {
    final /* synthetic */ ArrayList $result;
    final /* synthetic */ AbstractBinaryClassAnnotationAndConstantLoader this$0;

    public void visitEnd() {
    }

    C3029xc0ff27f9(AbstractBinaryClassAnnotationAndConstantLoader abstractBinaryClassAnnotationAndConstantLoader, ArrayList arrayList) {
        this.this$0 = abstractBinaryClassAnnotationAndConstantLoader;
        this.$result = arrayList;
    }

    @Nullable
    public AnnotationArgumentVisitor visitAnnotation(@NotNull ClassId classId, @NotNull SourceElement sourceElement) {
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        Intrinsics.checkParameterIsNotNull(sourceElement, Param.SOURCE);
        return this.this$0.loadAnnotationIfNotSpecial(classId, sourceElement, this.$result);
    }
}
