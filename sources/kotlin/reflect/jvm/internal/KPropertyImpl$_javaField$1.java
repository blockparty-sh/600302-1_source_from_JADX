package kotlin.reflect.jvm.internal;

import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.JvmPropertySignature.JavaField;
import kotlin.reflect.jvm.internal.JvmPropertySignature.JavaMethodProperty;
import kotlin.reflect.jvm.internal.JvmPropertySignature.KotlinProperty;
import kotlin.reflect.jvm.internal.JvmPropertySignature.MappedKotlinProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAbi;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002 \u0001H\n¢\u0006\u0002\b\u0003"}, mo37405d2 = {"<anonymous>", "Ljava/lang/reflect/Field;", "R", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* compiled from: KPropertyImpl.kt */
final class KPropertyImpl$_javaField$1 extends Lambda implements Function0<Field> {
    final /* synthetic */ KPropertyImpl this$0;

    KPropertyImpl$_javaField$1(KPropertyImpl kPropertyImpl) {
        this.this$0 = kPropertyImpl;
        super(0);
    }

    @Nullable
    public final Field invoke() {
        Class cls;
        JvmPropertySignature mapPropertySignature = RuntimeTypeMapper.INSTANCE.mapPropertySignature(this.this$0.getDescriptor());
        if (mapPropertySignature instanceof KotlinProperty) {
            KotlinProperty kotlinProperty = (KotlinProperty) mapPropertySignature;
            PropertyDescriptor descriptor = kotlinProperty.getDescriptor();
            JvmMemberSignature.Field jvmFieldSignature = JvmProtoBufUtil.INSTANCE.getJvmFieldSignature(kotlinProperty.getProto(), kotlinProperty.getNameResolver(), kotlinProperty.getTypeTable());
            if (jvmFieldSignature == null) {
                return null;
            }
            if (JvmAbi.isPropertyWithBackingFieldInOuterClass(descriptor) || JvmProtoBufUtil.isMovedFromInterfaceCompanion(kotlinProperty.getProto())) {
                cls = this.this$0.getContainer().getJClass().getEnclosingClass();
            } else {
                DeclarationDescriptor containingDeclaration = descriptor.getContainingDeclaration();
                if (containingDeclaration instanceof ClassDescriptor) {
                    cls = UtilKt.toJavaClass((ClassDescriptor) containingDeclaration);
                } else {
                    cls = this.this$0.getContainer().getJClass();
                }
            }
            if (cls == null) {
                return null;
            }
            try {
                return cls.getDeclaredField(jvmFieldSignature.getName());
            } catch (NoSuchFieldException unused) {
                return null;
            }
        } else if (mapPropertySignature instanceof JavaField) {
            return ((JavaField) mapPropertySignature).getField();
        } else {
            if ((mapPropertySignature instanceof JavaMethodProperty) || (mapPropertySignature instanceof MappedKotlinProperty)) {
                return null;
            }
            throw new NoWhenBranchMatchedException();
        }
    }
}