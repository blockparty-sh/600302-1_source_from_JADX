package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationWithTarget;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: typeEnhancement.kt */
final class EnhancedTypeAnnotations implements Annotations {
    private final FqName fqNameToMatch;

    public boolean isEmpty() {
        return false;
    }

    public EnhancedTypeAnnotations(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqNameToMatch");
        this.fqNameToMatch = fqName;
    }

    public boolean hasAnnotation(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return DefaultImpls.hasAnnotation(this, fqName);
    }

    @Nullable
    public EnhancedTypeAnnotationDescriptor findAnnotation(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        if (Intrinsics.areEqual((Object) fqName, (Object) this.fqNameToMatch)) {
            return EnhancedTypeAnnotationDescriptor.INSTANCE;
        }
        return null;
    }

    @NotNull
    public List<AnnotationWithTarget> getAllAnnotations() {
        Iterable<AnnotationDescriptor> iterable = this;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (AnnotationDescriptor annotationWithTarget : iterable) {
            arrayList.add(new AnnotationWithTarget(annotationWithTarget, null));
        }
        return (List) arrayList;
    }

    @NotNull
    public List<AnnotationWithTarget> getUseSiteTargetedAnnotations() {
        return CollectionsKt.emptyList();
    }

    @NotNull
    public Iterator<AnnotationDescriptor> iterator() {
        return CollectionsKt.emptyList().iterator();
    }
}
