package kotlin.reflect.jvm.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.FunctionCaller.BoundClassCompanionFieldGetter;
import kotlin.reflect.jvm.internal.FunctionCaller.BoundClassCompanionFieldSetter;
import kotlin.reflect.jvm.internal.FunctionCaller.BoundInstanceFieldGetter;
import kotlin.reflect.jvm.internal.FunctionCaller.BoundInstanceFieldSetter;
import kotlin.reflect.jvm.internal.FunctionCaller.BoundJvmStaticInObjectFieldGetter;
import kotlin.reflect.jvm.internal.FunctionCaller.BoundJvmStaticInObjectFieldSetter;
import kotlin.reflect.jvm.internal.FunctionCaller.ClassCompanionFieldGetter;
import kotlin.reflect.jvm.internal.FunctionCaller.ClassCompanionFieldSetter;
import kotlin.reflect.jvm.internal.FunctionCaller.FieldGetter;
import kotlin.reflect.jvm.internal.FunctionCaller.FieldSetter;
import kotlin.reflect.jvm.internal.FunctionCaller.InstanceFieldGetter;
import kotlin.reflect.jvm.internal.FunctionCaller.InstanceFieldSetter;
import kotlin.reflect.jvm.internal.FunctionCaller.JvmStaticInObjectFieldGetter;
import kotlin.reflect.jvm.internal.FunctionCaller.JvmStaticInObjectFieldSetter;
import kotlin.reflect.jvm.internal.FunctionCaller.StaticFieldGetter;
import kotlin.reflect.jvm.internal.FunctionCaller.StaticFieldSetter;
import kotlin.reflect.jvm.internal.KPropertyImpl.Accessor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"computeFieldCaller", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "Ljava/lang/reflect/Field;", "field", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 11})
/* compiled from: KPropertyImpl.kt */
final class KPropertyImplKt$computeCallerForAccessor$6 extends Lambda implements Function1<Field, FunctionCaller<? extends Field>> {
    final /* synthetic */ boolean $isGetter;
    final /* synthetic */ KPropertyImplKt$computeCallerForAccessor$1 $isInsideClassCompanionObject$1;
    final /* synthetic */ KPropertyImplKt$computeCallerForAccessor$3 $isInsideInterfaceCompanionObjectWithJvmField$3;
    final /* synthetic */ KPropertyImplKt$computeCallerForAccessor$4 $isJvmStaticProperty$4;
    final /* synthetic */ KPropertyImplKt$computeCallerForAccessor$5 $isNotNullProperty$5;
    final /* synthetic */ Accessor receiver$0;

    KPropertyImplKt$computeCallerForAccessor$6(Accessor accessor, KPropertyImplKt$computeCallerForAccessor$1 kPropertyImplKt$computeCallerForAccessor$1, KPropertyImplKt$computeCallerForAccessor$3 kPropertyImplKt$computeCallerForAccessor$3, boolean z, KPropertyImplKt$computeCallerForAccessor$5 kPropertyImplKt$computeCallerForAccessor$5, KPropertyImplKt$computeCallerForAccessor$4 kPropertyImplKt$computeCallerForAccessor$4) {
        this.receiver$0 = accessor;
        this.$isInsideClassCompanionObject$1 = kPropertyImplKt$computeCallerForAccessor$1;
        this.$isInsideInterfaceCompanionObjectWithJvmField$3 = kPropertyImplKt$computeCallerForAccessor$3;
        this.$isGetter = z;
        this.$isNotNullProperty$5 = kPropertyImplKt$computeCallerForAccessor$5;
        this.$isJvmStaticProperty$4 = kPropertyImplKt$computeCallerForAccessor$4;
        super(1);
    }

    @NotNull
    public final FunctionCaller<Field> invoke(@NotNull Field field) {
        FunctionCaller<Field> functionCaller;
        FieldSetter fieldSetter;
        FieldGetter fieldGetter;
        FieldSetter fieldSetter2;
        FieldGetter fieldGetter2;
        Intrinsics.checkParameterIsNotNull(field, "field");
        if (this.$isInsideClassCompanionObject$1.invoke() || this.$isInsideInterfaceCompanionObjectWithJvmField$3.invoke()) {
            DeclarationDescriptor containingDeclaration = this.receiver$0.getDescriptor().getContainingDeclaration();
            if (containingDeclaration != null) {
                Class javaClass = UtilKt.toJavaClass((ClassDescriptor) containingDeclaration);
                if (javaClass == null) {
                    Intrinsics.throwNpe();
                }
                if (!this.$isGetter) {
                    if (this.receiver$0.isBound()) {
                        functionCaller = new BoundClassCompanionFieldSetter<>(field, javaClass);
                    } else {
                        functionCaller = new ClassCompanionFieldSetter<>(field, javaClass);
                    }
                    return functionCaller;
                } else if (this.receiver$0.isBound()) {
                    return new BoundClassCompanionFieldGetter<>(field, javaClass);
                } else {
                    return new ClassCompanionFieldGetter<>(field, javaClass);
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            }
        } else if (!Modifier.isStatic(field.getModifiers())) {
            if (this.$isGetter) {
                if (this.receiver$0.isBound()) {
                    fieldGetter2 = new BoundInstanceFieldGetter(field, this.receiver$0.getProperty().getBoundReceiver());
                } else {
                    fieldGetter2 = new InstanceFieldGetter(field);
                }
                return fieldGetter2;
            }
            if (this.receiver$0.isBound()) {
                fieldSetter2 = new BoundInstanceFieldSetter(field, this.$isNotNullProperty$5.invoke(), this.receiver$0.getProperty().getBoundReceiver());
            } else {
                fieldSetter2 = new InstanceFieldSetter(field, this.$isNotNullProperty$5.invoke());
            }
            return fieldSetter2;
        } else if (this.$isJvmStaticProperty$4.invoke()) {
            if (this.$isGetter) {
                if (this.receiver$0.isBound()) {
                    fieldGetter = new BoundJvmStaticInObjectFieldGetter(field);
                } else {
                    fieldGetter = new JvmStaticInObjectFieldGetter(field);
                }
                return fieldGetter;
            }
            if (this.receiver$0.isBound()) {
                fieldSetter = new BoundJvmStaticInObjectFieldSetter(field, this.$isNotNullProperty$5.invoke());
            } else {
                fieldSetter = new JvmStaticInObjectFieldSetter(field, this.$isNotNullProperty$5.invoke());
            }
            return fieldSetter;
        } else if (this.$isGetter) {
            return new StaticFieldGetter<>(field);
        } else {
            return new StaticFieldSetter<>(field, this.$isNotNullProperty$5.invoke());
        }
    }
}
