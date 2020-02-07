package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KPropertyImpl.Setter;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0006\b\u0001\u0010\u0002 \u0001H\n¢\u0006\u0002\b\u0003"}, mo37405d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertySetterDescriptor;", "R", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* compiled from: KPropertyImpl.kt */
final class KPropertyImpl$Setter$descriptor$2 extends Lambda implements Function0<PropertySetterDescriptor> {
    final /* synthetic */ Setter this$0;

    KPropertyImpl$Setter$descriptor$2(Setter setter) {
        this.this$0 = setter;
        super(0);
    }

    @NotNull
    public final PropertySetterDescriptor invoke() {
        PropertySetterDescriptor setter = this.this$0.getProperty().getDescriptor().getSetter();
        return setter != null ? setter : DescriptorFactory.createDefaultSetter(this.this$0.getProperty().getDescriptor(), Annotations.Companion.getEMPTY());
    }
}
