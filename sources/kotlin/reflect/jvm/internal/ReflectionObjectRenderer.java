package kotlin.reflect.jvm.internal;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KParameter.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\nJ\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017J\u001a\u0010\u0018\u001a\u00020\u0019*\u00060\u001aj\u0002`\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002J\u0018\u0010\u001e\u001a\u00020\u0019*\u00060\u001aj\u0002`\u001b2\u0006\u0010\u001f\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, mo37405d2 = {"Lkotlin/reflect/jvm/internal/ReflectionObjectRenderer;", "", "()V", "renderer", "Lkotlin/reflect/jvm/internal/impl/renderer/DescriptorRenderer;", "renderCallable", "", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableDescriptor;", "renderFunction", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "renderLambda", "invoke", "renderParameter", "parameter", "Lkotlin/reflect/jvm/internal/KParameterImpl;", "renderProperty", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "renderType", "type", "Lkotlin/reflect/jvm/internal/impl/types/KotlinType;", "renderTypeParameter", "typeParameter", "Lkotlin/reflect/jvm/internal/impl/descriptors/TypeParameterDescriptor;", "appendReceiverType", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "receiver", "Lkotlin/reflect/jvm/internal/impl/descriptors/ReceiverParameterDescriptor;", "appendReceivers", "callable", "kotlin-reflect-api"}, mo37406k = 1, mo37407mv = {1, 1, 11})
/* compiled from: ReflectionObjectRenderer.kt */
public final class ReflectionObjectRenderer {
    public static final ReflectionObjectRenderer INSTANCE = new ReflectionObjectRenderer();
    private static final DescriptorRenderer renderer = DescriptorRenderer.FQ_NAMES_IN_TYPES;

    @Metadata(mo37403bv = {1, 0, 2}, mo37406k = 3, mo37407mv = {1, 1, 11})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Kind.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[Variance.values().length];

        static {
            $EnumSwitchMapping$0[Kind.EXTENSION_RECEIVER.ordinal()] = 1;
            $EnumSwitchMapping$0[Kind.INSTANCE.ordinal()] = 2;
            $EnumSwitchMapping$0[Kind.VALUE.ordinal()] = 3;
            $EnumSwitchMapping$1[Variance.INVARIANT.ordinal()] = 1;
            $EnumSwitchMapping$1[Variance.IN_VARIANCE.ordinal()] = 2;
            $EnumSwitchMapping$1[Variance.OUT_VARIANCE.ordinal()] = 3;
        }
    }

    private ReflectionObjectRenderer() {
    }

    private final void appendReceiverType(@NotNull StringBuilder sb, ReceiverParameterDescriptor receiverParameterDescriptor) {
        if (receiverParameterDescriptor != null) {
            KotlinType type = receiverParameterDescriptor.getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "receiver.type");
            sb.append(renderType(type));
            sb.append(".");
        }
    }

    private final void appendReceivers(@NotNull StringBuilder sb, CallableDescriptor callableDescriptor) {
        ReceiverParameterDescriptor dispatchReceiverParameter = callableDescriptor.getDispatchReceiverParameter();
        ReceiverParameterDescriptor extensionReceiverParameter = callableDescriptor.getExtensionReceiverParameter();
        appendReceiverType(sb, dispatchReceiverParameter);
        boolean z = (dispatchReceiverParameter == null || extensionReceiverParameter == null) ? false : true;
        if (z) {
            sb.append("(");
        }
        appendReceiverType(sb, extensionReceiverParameter);
        if (z) {
            sb.append(")");
        }
    }

    private final String renderCallable(CallableDescriptor callableDescriptor) {
        if (callableDescriptor instanceof PropertyDescriptor) {
            return renderProperty((PropertyDescriptor) callableDescriptor);
        }
        if (callableDescriptor instanceof FunctionDescriptor) {
            return renderFunction((FunctionDescriptor) callableDescriptor);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Illegal callable: ");
        sb.append(callableDescriptor);
        throw new IllegalStateException(sb.toString().toString());
    }

    @NotNull
    public final String renderProperty(@NotNull PropertyDescriptor propertyDescriptor) {
        Intrinsics.checkParameterIsNotNull(propertyDescriptor, "descriptor");
        StringBuilder sb = new StringBuilder();
        sb.append(propertyDescriptor.isVar() ? "var " : "val ");
        INSTANCE.appendReceivers(sb, propertyDescriptor);
        DescriptorRenderer descriptorRenderer = renderer;
        Name name = propertyDescriptor.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "descriptor.name");
        sb.append(descriptorRenderer.renderName(name, true));
        sb.append(": ");
        ReflectionObjectRenderer reflectionObjectRenderer = INSTANCE;
        KotlinType type = propertyDescriptor.getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "descriptor.type");
        sb.append(reflectionObjectRenderer.renderType(type));
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @NotNull
    public final String renderFunction(@NotNull FunctionDescriptor functionDescriptor) {
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "descriptor");
        StringBuilder sb = new StringBuilder();
        sb.append("fun ");
        INSTANCE.appendReceivers(sb, functionDescriptor);
        DescriptorRenderer descriptorRenderer = renderer;
        Name name = functionDescriptor.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "descriptor.name");
        sb.append(descriptorRenderer.renderName(name, true));
        List valueParameters = functionDescriptor.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters, "descriptor.valueParameters");
        CollectionsKt.joinTo$default(valueParameters, sb, ", ", "(", ")", 0, null, ReflectionObjectRenderer$renderFunction$1$1.INSTANCE, 48, null);
        sb.append(": ");
        ReflectionObjectRenderer reflectionObjectRenderer = INSTANCE;
        KotlinType returnType = functionDescriptor.getReturnType();
        if (returnType == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(returnType, "descriptor.returnType!!");
        sb.append(reflectionObjectRenderer.renderType(returnType));
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @NotNull
    public final String renderLambda(@NotNull FunctionDescriptor functionDescriptor) {
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "invoke");
        StringBuilder sb = new StringBuilder();
        INSTANCE.appendReceivers(sb, functionDescriptor);
        List valueParameters = functionDescriptor.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters, "invoke.valueParameters");
        CollectionsKt.joinTo$default(valueParameters, sb, ", ", "(", ")", 0, null, ReflectionObjectRenderer$renderLambda$1$1.INSTANCE, 48, null);
        sb.append(" -> ");
        ReflectionObjectRenderer reflectionObjectRenderer = INSTANCE;
        KotlinType returnType = functionDescriptor.getReturnType();
        if (returnType == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(returnType, "invoke.returnType!!");
        sb.append(reflectionObjectRenderer.renderType(returnType));
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @NotNull
    public final String renderParameter(@NotNull KParameterImpl kParameterImpl) {
        Intrinsics.checkParameterIsNotNull(kParameterImpl, "parameter");
        StringBuilder sb = new StringBuilder();
        int i = WhenMappings.$EnumSwitchMapping$0[kParameterImpl.getKind().ordinal()];
        if (i == 1) {
            sb.append("extension receiver");
        } else if (i == 2) {
            sb.append("instance");
        } else if (i == 3) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("parameter #");
            sb2.append(kParameterImpl.getIndex());
            sb2.append(' ');
            sb2.append(kParameterImpl.getName());
            sb.append(sb2.toString());
        }
        sb.append(" of ");
        sb.append(INSTANCE.renderCallable(kParameterImpl.getCallable().getDescriptor()));
        String sb3 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb3, "StringBuilder().apply(builderAction).toString()");
        return sb3;
    }

    @NotNull
    public final String renderTypeParameter(@NotNull TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkParameterIsNotNull(typeParameterDescriptor, "typeParameter");
        StringBuilder sb = new StringBuilder();
        int i = WhenMappings.$EnumSwitchMapping$1[typeParameterDescriptor.getVariance().ordinal()];
        if (i != 1) {
            if (i == 2) {
                sb.append("in ");
            } else if (i == 3) {
                sb.append("out ");
            }
        }
        sb.append(typeParameterDescriptor.getName());
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @NotNull
    public final String renderType(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
        return renderer.renderType(kotlinType);
    }
}
