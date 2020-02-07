package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUseSiteTarget;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationWithTarget;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import org.jetbrains.annotations.NotNull;

/* compiled from: MemberDeserializer.kt */
final class MemberDeserializer$getReceiverParameterAnnotations$1 extends Lambda implements Function0<List<? extends AnnotationWithTarget>> {
    final /* synthetic */ MessageLite $proto;
    final /* synthetic */ AnnotatedCallableKind $receiverTargetedKind;
    final /* synthetic */ MemberDeserializer this$0;

    MemberDeserializer$getReceiverParameterAnnotations$1(MemberDeserializer memberDeserializer, MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind) {
        this.this$0 = memberDeserializer;
        this.$proto = messageLite;
        this.$receiverTargetedKind = annotatedCallableKind;
        super(0);
    }

    @NotNull
    public final List<AnnotationWithTarget> invoke() {
        List<AnnotationWithTarget> list;
        MemberDeserializer memberDeserializer = this.this$0;
        ProtoContainer access$asProtoContainer = memberDeserializer.asProtoContainer(memberDeserializer.f719c.getContainingDeclaration());
        if (access$asProtoContainer != null) {
            Iterable<AnnotationDescriptor> loadExtensionReceiverParameterAnnotations = this.this$0.f719c.getComponents().getAnnotationAndConstantLoader().loadExtensionReceiverParameterAnnotations(access$asProtoContainer, this.$proto, this.$receiverTargetedKind);
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(loadExtensionReceiverParameterAnnotations, 10));
            for (AnnotationDescriptor annotationWithTarget : loadExtensionReceiverParameterAnnotations) {
                arrayList.add(new AnnotationWithTarget(annotationWithTarget, AnnotationUseSiteTarget.RECEIVER));
            }
            list = CollectionsKt.toList((List) arrayList);
        } else {
            list = null;
        }
        return list != null ? list : CollectionsKt.emptyList();
    }
}
