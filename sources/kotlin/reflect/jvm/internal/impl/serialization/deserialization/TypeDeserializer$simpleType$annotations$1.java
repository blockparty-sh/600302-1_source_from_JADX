package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationWithTarget;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.C3063Type;
import org.jetbrains.annotations.NotNull;

/* compiled from: TypeDeserializer.kt */
final class TypeDeserializer$simpleType$annotations$1 extends Lambda implements Function0<List<? extends AnnotationWithTarget>> {
    final /* synthetic */ Annotations $additionalAnnotations;
    final /* synthetic */ C3063Type $proto;
    final /* synthetic */ TypeDeserializer this$0;

    TypeDeserializer$simpleType$annotations$1(TypeDeserializer typeDeserializer, C3063Type type, Annotations annotations) {
        this.this$0 = typeDeserializer;
        this.$proto = type;
        this.$additionalAnnotations = annotations;
        super(0);
    }

    @NotNull
    public final List<AnnotationWithTarget> invoke() {
        Iterable<AnnotationDescriptor> loadTypeAnnotations = this.this$0.f721c.getComponents().getAnnotationAndConstantLoader().loadTypeAnnotations(this.$proto, this.this$0.f721c.getNameResolver());
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(loadTypeAnnotations, 10));
        for (AnnotationDescriptor annotationWithTarget : loadTypeAnnotations) {
            arrayList.add(new AnnotationWithTarget(annotationWithTarget, null));
        }
        return CollectionsKt.toList(CollectionsKt.plus((Collection<? extends T>) (List) arrayList, (Iterable<? extends T>) this.$additionalAnnotations.getAllAnnotations()));
    }
}
