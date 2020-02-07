package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Annotations.kt */
public interface Annotations extends Iterable<AnnotationDescriptor>, KMappedMarker {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: Annotations.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        @NotNull
        private static final Annotations EMPTY = new Annotations$Companion$EMPTY$1();

        private Companion() {
        }

        @NotNull
        public final Annotations getEMPTY() {
            return EMPTY;
        }

        @Nullable
        public final AnnotationDescriptor findUseSiteTargetedAnnotation(@NotNull Annotations annotations, @NotNull AnnotationUseSiteTarget annotationUseSiteTarget, @NotNull FqName fqName) {
            Object obj;
            Intrinsics.checkParameterIsNotNull(annotations, "annotations");
            Intrinsics.checkParameterIsNotNull(annotationUseSiteTarget, "target");
            Intrinsics.checkParameterIsNotNull(fqName, "fqName");
            Iterator it = getUseSiteTargetedAnnotations(annotations, annotationUseSiteTarget).iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual((Object) ((AnnotationDescriptor) obj).getFqName(), (Object) fqName)) {
                    break;
                }
            }
            return (AnnotationDescriptor) obj;
        }

        private final List<AnnotationDescriptor> getUseSiteTargetedAnnotations(Annotations annotations, AnnotationUseSiteTarget annotationUseSiteTarget) {
            Iterable<AnnotationWithTarget> useSiteTargetedAnnotations = annotations.getUseSiteTargetedAnnotations();
            Collection arrayList = new ArrayList();
            for (AnnotationWithTarget annotationWithTarget : useSiteTargetedAnnotations) {
                AnnotationDescriptor component1 = annotationWithTarget.component1();
                if (!(annotationUseSiteTarget == annotationWithTarget.component2())) {
                    component1 = null;
                }
                if (component1 != null) {
                    arrayList.add(component1);
                }
            }
            return (List) arrayList;
        }
    }

    /* compiled from: Annotations.kt */
    public static final class DefaultImpls {
        @Nullable
        public static AnnotationDescriptor findAnnotation(Annotations annotations, @NotNull FqName fqName) {
            Object obj;
            Intrinsics.checkParameterIsNotNull(fqName, "fqName");
            Iterator it = annotations.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual((Object) ((AnnotationDescriptor) obj).getFqName(), (Object) fqName)) {
                    break;
                }
            }
            return (AnnotationDescriptor) obj;
        }

        public static boolean hasAnnotation(Annotations annotations, @NotNull FqName fqName) {
            Intrinsics.checkParameterIsNotNull(fqName, "fqName");
            return annotations.findAnnotation(fqName) != null;
        }
    }

    @Nullable
    AnnotationDescriptor findAnnotation(@NotNull FqName fqName);

    @NotNull
    List<AnnotationWithTarget> getAllAnnotations();

    @NotNull
    List<AnnotationWithTarget> getUseSiteTargetedAnnotations();

    boolean hasAnnotation(@NotNull FqName fqName);

    boolean isEmpty();
}
