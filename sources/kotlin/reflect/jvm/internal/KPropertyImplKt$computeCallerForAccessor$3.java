package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KPropertyImpl.Accessor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"isInsideInterfaceCompanionObjectWithJvmField", "", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* compiled from: KPropertyImpl.kt */
final class KPropertyImplKt$computeCallerForAccessor$3 extends Lambda implements Function0<Boolean> {
    final /* synthetic */ KPropertyImplKt$computeCallerForAccessor$2 $isInsideJvmInterfaceCompanionObject$2;
    final /* synthetic */ Accessor receiver$0;

    KPropertyImplKt$computeCallerForAccessor$3(Accessor accessor, KPropertyImplKt$computeCallerForAccessor$2 kPropertyImplKt$computeCallerForAccessor$2) {
        this.receiver$0 = accessor;
        this.$isInsideJvmInterfaceCompanionObject$2 = kPropertyImplKt$computeCallerForAccessor$2;
        super(0);
    }

    public final boolean invoke() {
        PropertyDescriptor descriptor = this.receiver$0.getProperty().getDescriptor();
        if (!(descriptor instanceof DeserializedPropertyDescriptor) || !this.$isInsideJvmInterfaceCompanionObject$2.invoke()) {
            return false;
        }
        return JvmProtoBufUtil.isMovedFromInterfaceCompanion(((DeserializedPropertyDescriptor) descriptor).getProto());
    }
}
