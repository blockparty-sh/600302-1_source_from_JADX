package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationWithTarget;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import org.jetbrains.annotations.NotNull;

/* compiled from: MemberDeserializer.kt */
final class MemberDeserializer$getAnnotations$1 extends Lambda implements Function0<List<? extends AnnotationWithTarget>> {
    final /* synthetic */ AnnotatedCallableKind $kind;
    final /* synthetic */ MessageLite $proto;
    final /* synthetic */ MemberDeserializer this$0;

    MemberDeserializer$getAnnotations$1(MemberDeserializer memberDeserializer, MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind) {
        this.this$0 = memberDeserializer;
        this.$proto = messageLite;
        this.$kind = annotatedCallableKind;
        super(0);
    }

    @NotNull
    public final List<AnnotationWithTarget> invoke() {
        MemberDeserializer memberDeserializer = this.this$0;
        ProtoContainer access$asProtoContainer = memberDeserializer.asProtoContainer(memberDeserializer.f719c.getContainingDeclaration());
        List<AnnotationWithTarget> list = access$asProtoContainer != null ? CollectionsKt.toList(this.this$0.f719c.getComponents().getAnnotationAndConstantLoader().loadCallableAnnotations(access$asProtoContainer, this.$proto, this.$kind)) : null;
        return list != null ? list : CollectionsKt.emptyList();
    }
}
