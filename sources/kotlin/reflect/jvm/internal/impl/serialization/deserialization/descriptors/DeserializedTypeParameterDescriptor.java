package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker.EMPTY;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractLazyTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.C3063Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Variance;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;

/* compiled from: DeserializedTypeParameterDescriptor.kt */
public final class DeserializedTypeParameterDescriptor extends AbstractLazyTypeParameterDescriptor {
    @NotNull
    private final DeserializedAnnotations annotations = new DeserializedAnnotations(this.f724c.getStorageManager(), new DeserializedTypeParameterDescriptor$annotations$1(this));
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final DeserializationContext f724c;
    /* access modifiers changed from: private */
    public final TypeParameter proto;

    public DeserializedTypeParameterDescriptor(@NotNull DeserializationContext deserializationContext, @NotNull TypeParameter typeParameter, int i) {
        Intrinsics.checkParameterIsNotNull(deserializationContext, "c");
        Intrinsics.checkParameterIsNotNull(typeParameter, "proto");
        StorageManager storageManager = deserializationContext.getStorageManager();
        DeclarationDescriptor containingDeclaration = deserializationContext.getContainingDeclaration();
        Name name = NameResolverUtilKt.getName(deserializationContext.getNameResolver(), typeParameter.getName());
        ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
        Variance variance = typeParameter.getVariance();
        Intrinsics.checkExpressionValueIsNotNull(variance, "proto.variance");
        super(storageManager, containingDeclaration, name, protoEnumFlags.variance(variance), typeParameter.getReified(), i, SourceElement.NO_SOURCE, EMPTY.INSTANCE);
        this.f724c = deserializationContext;
        this.proto = typeParameter;
    }

    @NotNull
    public DeserializedAnnotations getAnnotations() {
        return this.annotations;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public List<KotlinType> resolveUpperBounds() {
        List upperBounds = ProtoTypeTableUtilKt.upperBounds(this.proto, this.f724c.getTypeTable());
        if (upperBounds.isEmpty()) {
            return CollectionsKt.listOf(DescriptorUtilsKt.getBuiltIns(this).getDefaultBound());
        }
        Iterable<C3063Type> iterable = upperBounds;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (C3063Type type : iterable) {
            arrayList.add(this.f724c.getTypeDeserializer().type(type, Annotations.Companion.getEMPTY()));
        }
        return (List) arrayList;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Void reportSupertypeLoopError(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
        StringBuilder sb = new StringBuilder();
        sb.append("There should be no cycles for deserialized type parameters, but found for: ");
        sb.append(this);
        throw new IllegalStateException(sb.toString());
    }
}
