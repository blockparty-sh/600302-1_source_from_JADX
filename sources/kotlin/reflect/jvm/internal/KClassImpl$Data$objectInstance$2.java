package kotlin.reflect.jvm.internal;

import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KClassImpl.Data;
import kotlin.reflect.jvm.internal.impl.builtins.CompanionObjectMapping;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "T", "", "invoke", "()Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* compiled from: KClassImpl.kt */
final class KClassImpl$Data$objectInstance$2 extends Lambda implements Function0<T> {
    final /* synthetic */ Data this$0;

    KClassImpl$Data$objectInstance$2(Data data) {
        this.this$0 = data;
        super(0);
    }

    @Nullable
    public final T invoke() {
        Field field;
        ClassDescriptor descriptor = this.this$0.getDescriptor();
        if (descriptor.getKind() != ClassKind.OBJECT) {
            return null;
        }
        if (!descriptor.isCompanionObject() || CompanionObjectMapping.INSTANCE.isMappedIntrinsicCompanionObject(descriptor)) {
            field = KClassImpl.this.getJClass().getDeclaredField("INSTANCE");
        } else {
            field = KClassImpl.this.getJClass().getEnclosingClass().getDeclaredField(descriptor.getName().asString());
        }
        T t = field.get(null);
        if (t != null) {
            return t;
        }
        throw new TypeCastException("null cannot be cast to non-null type T");
    }
}
