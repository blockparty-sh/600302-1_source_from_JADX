package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AnnotationsImpl.kt */
public final class AnnotationsImpl implements Annotations {
    public static final Companion Companion = new Companion(null);
    private final List<AnnotationDescriptor> annotations;
    private final List<AnnotationWithTarget> targetedAnnotations;

    /* compiled from: AnnotationsImpl.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Nullable
    public AnnotationDescriptor findAnnotation(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return DefaultImpls.findAnnotation(this, fqName);
    }

    public boolean hasAnnotation(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return DefaultImpls.hasAnnotation(this, fqName);
    }

    public AnnotationsImpl(@NotNull List<? extends AnnotationDescriptor> list) {
        Intrinsics.checkParameterIsNotNull(list, "annotations");
        this.annotations = list;
        Iterable<AnnotationDescriptor> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (AnnotationDescriptor annotationWithTarget : iterable) {
            arrayList.add(new AnnotationWithTarget(annotationWithTarget, null));
        }
        this.targetedAnnotations = (List) arrayList;
    }

    public boolean isEmpty() {
        return this.targetedAnnotations.isEmpty();
    }

    @NotNull
    public List<AnnotationWithTarget> getUseSiteTargetedAnnotations() {
        Iterable iterable = this.targetedAnnotations;
        Collection arrayList = new ArrayList();
        for (Object next : iterable) {
            if (((AnnotationWithTarget) next).getTarget() != null) {
                arrayList.add(next);
            }
        }
        Iterable<AnnotationWithTarget> iterable2 = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
        for (AnnotationWithTarget annotationWithTarget : iterable2) {
            AnnotationDescriptor annotation = annotationWithTarget.getAnnotation();
            AnnotationUseSiteTarget target = annotationWithTarget.getTarget();
            if (target == null) {
                Intrinsics.throwNpe();
            }
            arrayList2.add(new AnnotationWithTarget(annotation, target));
        }
        return (List) arrayList2;
    }

    @NotNull
    public List<AnnotationWithTarget> getAllAnnotations() {
        return this.targetedAnnotations;
    }

    @NotNull
    public Iterator<AnnotationDescriptor> iterator() {
        return this.annotations.iterator();
    }

    @NotNull
    public String toString() {
        return this.annotations.toString();
    }
}
