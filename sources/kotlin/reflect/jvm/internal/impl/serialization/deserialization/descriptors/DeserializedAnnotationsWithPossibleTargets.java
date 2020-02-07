package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationWithTarget;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DeserializedAnnotations.kt */
public class DeserializedAnnotationsWithPossibleTargets implements Annotations {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DeserializedAnnotationsWithPossibleTargets.class), "annotations", "getAnnotations()Ljava/util/List;"))};
    private final NotNullLazyValue annotations$delegate;

    private final List<AnnotationWithTarget> getAnnotations() {
        return (List) StorageKt.getValue(this.annotations$delegate, (Object) this, $$delegatedProperties[0]);
    }

    public DeserializedAnnotationsWithPossibleTargets(@NotNull StorageManager storageManager, @NotNull Function0<? extends List<AnnotationWithTarget>> function0) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(function0, "compute");
        this.annotations$delegate = storageManager.createLazyValue(function0);
    }

    public boolean hasAnnotation(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return DefaultImpls.hasAnnotation(this, fqName);
    }

    public boolean isEmpty() {
        return getAnnotations().isEmpty();
    }

    @Nullable
    public AnnotationDescriptor findAnnotation(@NotNull FqName fqName) {
        Object obj;
        boolean z;
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Iterator it = getAnnotations().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            AnnotationWithTarget annotationWithTarget = (AnnotationWithTarget) obj;
            AnnotationDescriptor component1 = annotationWithTarget.component1();
            if (annotationWithTarget.component2() != null || !Intrinsics.areEqual((Object) component1.getFqName(), (Object) fqName)) {
                z = false;
                continue;
            } else {
                z = true;
                continue;
            }
            if (z) {
                break;
            }
        }
        AnnotationWithTarget annotationWithTarget2 = (AnnotationWithTarget) obj;
        if (annotationWithTarget2 != null) {
            return annotationWithTarget2.getAnnotation();
        }
        return null;
    }

    @NotNull
    public List<AnnotationWithTarget> getUseSiteTargetedAnnotations() {
        Iterable annotations = getAnnotations();
        Collection arrayList = new ArrayList();
        for (Object next : annotations) {
            if (((AnnotationWithTarget) next).getTarget() != null) {
                arrayList.add(next);
            }
        }
        return (List) arrayList;
    }

    @NotNull
    public List<AnnotationWithTarget> getAllAnnotations() {
        return getAnnotations();
    }

    @NotNull
    public Iterator<AnnotationDescriptor> iterator() {
        return SequencesKt.map(SequencesKt.filter(CollectionsKt.asSequence(getAnnotations()), DeserializedAnnotationsWithPossibleTargets$iterator$1.INSTANCE), DeserializedAnnotationsWithPossibleTargets$iterator$2.INSTANCE).iterator();
    }
}
