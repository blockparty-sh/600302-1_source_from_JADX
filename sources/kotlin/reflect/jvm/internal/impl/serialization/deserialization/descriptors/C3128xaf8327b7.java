package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EnumEntrySyntheticClassDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* renamed from: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$EnumEntryClassDescriptors$enumEntryByName$1 */
/* compiled from: DeserializedClassDescriptor.kt */
final class C3128xaf8327b7 extends Lambda implements Function1<Name, EnumEntrySyntheticClassDescriptor> {
    final /* synthetic */ EnumEntryClassDescriptors this$0;

    C3128xaf8327b7(EnumEntryClassDescriptors enumEntryClassDescriptors) {
        this.this$0 = enumEntryClassDescriptors;
        super(1);
    }

    @Nullable
    public final EnumEntrySyntheticClassDescriptor invoke(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        EnumEntry enumEntry = (EnumEntry) this.this$0.enumEntryProtos.get(name);
        if (enumEntry == null) {
            return null;
        }
        return EnumEntrySyntheticClassDescriptor.create(DeserializedClassDescriptor.this.getC().getStorageManager(), DeserializedClassDescriptor.this, name, this.this$0.enumMemberNames, new DeserializedAnnotations(DeserializedClassDescriptor.this.getC().getStorageManager(), new C3123x7c5aab78(enumEntry, this, name)), SourceElement.NO_SOURCE);
    }
}