package kotlin.reflect.jvm.internal.impl.types;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationWithTarget;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinType.kt */
public abstract class SimpleType extends UnwrappedType {
    @NotNull
    public abstract SimpleType makeNullableAsSpecified(boolean z);

    @NotNull
    public abstract SimpleType replaceAnnotations(@NotNull Annotations annotations);

    public SimpleType() {
        super(null);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (AnnotationWithTarget annotationWithTarget : getAnnotations().getAllAnnotations()) {
            StringsKt.append(sb, "[", DescriptorRenderer.DEBUG_TEXT.renderAnnotation(annotationWithTarget.component1(), annotationWithTarget.component2()), "] ");
        }
        sb.append(getConstructor());
        if (!getArguments().isEmpty()) {
            CollectionsKt.joinTo$default(getArguments(), sb, ", ", "<", ">", 0, null, null, 112, null);
        }
        if (isMarkedNullable()) {
            sb.append("?");
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
