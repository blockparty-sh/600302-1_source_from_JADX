package kotlin.reflect.jvm.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KParameter;
import kotlin.reflect.KParameter.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0006\b\u0000\u0010\u0003 \u0001H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "Ljava/util/ArrayList;", "Lkotlin/reflect/KParameter;", "R", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* compiled from: KCallableImpl.kt */
final class KCallableImpl$_parameters$1 extends Lambda implements Function0<ArrayList<KParameter>> {
    final /* synthetic */ KCallableImpl this$0;

    KCallableImpl$_parameters$1(KCallableImpl kCallableImpl) {
        this.this$0 = kCallableImpl;
        super(0);
    }

    @NotNull
    public final ArrayList<KParameter> invoke() {
        int i;
        final CallableMemberDescriptor descriptor = this.this$0.getDescriptor();
        ArrayList<KParameter> arrayList = new ArrayList<>();
        final int i2 = 0;
        if (descriptor.getDispatchReceiverParameter() == null || this.this$0.isBound()) {
            i = 0;
        } else {
            arrayList.add(new KParameterImpl(this.this$0, 0, Kind.INSTANCE, new Function0<ReceiverParameterDescriptor>() {
                @NotNull
                public final ReceiverParameterDescriptor invoke() {
                    ReceiverParameterDescriptor dispatchReceiverParameter = descriptor.getDispatchReceiverParameter();
                    if (dispatchReceiverParameter == null) {
                        Intrinsics.throwNpe();
                    }
                    return dispatchReceiverParameter;
                }
            }));
            i = 1;
        }
        if (descriptor.getExtensionReceiverParameter() != null && !this.this$0.isBound()) {
            int i3 = i + 1;
            arrayList.add(new KParameterImpl(this.this$0, i, Kind.EXTENSION_RECEIVER, new Function0<ReceiverParameterDescriptor>() {
                @NotNull
                public final ReceiverParameterDescriptor invoke() {
                    ReceiverParameterDescriptor extensionReceiverParameter = descriptor.getExtensionReceiverParameter();
                    if (extensionReceiverParameter == null) {
                        Intrinsics.throwNpe();
                    }
                    return extensionReceiverParameter;
                }
            }));
            i = i3;
        }
        List valueParameters = descriptor.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters, "descriptor.valueParameters");
        int size = valueParameters.size();
        while (i2 < size) {
            int i4 = i + 1;
            arrayList.add(new KParameterImpl(this.this$0, i, Kind.VALUE, new Function0<ValueParameterDescriptor>() {
                public final ValueParameterDescriptor invoke() {
                    Object obj = descriptor.getValueParameters().get(i2);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "descriptor.valueParameters[i]");
                    return (ValueParameterDescriptor) obj;
                }
            }));
            i2++;
            i = i4;
        }
        if (this.this$0.isAnnotationConstructor() && (descriptor instanceof JavaCallableMemberDescriptor)) {
            List list = arrayList;
            if (list.size() > 1) {
                CollectionsKt.sortWith(list, new KCallableImpl$_parameters$1$$special$$inlined$sortBy$1());
            }
        }
        arrayList.trimToSize();
        return arrayList;
    }
}