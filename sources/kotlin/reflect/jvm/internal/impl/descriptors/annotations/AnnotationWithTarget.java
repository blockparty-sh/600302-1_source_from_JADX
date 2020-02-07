package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AnnotationWithTarget.kt */
public final class AnnotationWithTarget {
    @NotNull
    private final AnnotationDescriptor annotation;
    @Nullable
    private final AnnotationUseSiteTarget target;

    @NotNull
    public final AnnotationDescriptor component1() {
        return this.annotation;
    }

    @Nullable
    public final AnnotationUseSiteTarget component2() {
        return this.target;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.target, (java.lang.Object) r3.target) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationWithTarget
            if (r0 == 0) goto L_0x001d
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationWithTarget r3 = (kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationWithTarget) r3
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor r0 = r2.annotation
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor r1 = r3.annotation
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUseSiteTarget r0 = r2.target
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUseSiteTarget r3 = r3.target
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r3 = 0
            return r3
        L_0x001f:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationWithTarget.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        AnnotationDescriptor annotationDescriptor = this.annotation;
        int i = 0;
        int hashCode = (annotationDescriptor != null ? annotationDescriptor.hashCode() : 0) * 31;
        AnnotationUseSiteTarget annotationUseSiteTarget = this.target;
        if (annotationUseSiteTarget != null) {
            i = annotationUseSiteTarget.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AnnotationWithTarget(annotation=");
        sb.append(this.annotation);
        sb.append(", target=");
        sb.append(this.target);
        sb.append(")");
        return sb.toString();
    }

    public AnnotationWithTarget(@NotNull AnnotationDescriptor annotationDescriptor, @Nullable AnnotationUseSiteTarget annotationUseSiteTarget) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotation");
        this.annotation = annotationDescriptor;
        this.target = annotationUseSiteTarget;
    }

    @NotNull
    public final AnnotationDescriptor getAnnotation() {
        return this.annotation;
    }

    @Nullable
    public final AnnotationUseSiteTarget getTarget() {
        return this.target;
    }
}
