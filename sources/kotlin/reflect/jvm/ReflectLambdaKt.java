package kotlin.reflect.jvm;

import java.util.Arrays;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlin.reflect.jvm.internal.EmptyContainerForLocal;
import kotlin.reflect.jvm.internal.KFunctionImpl;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003¨\u0006\u0004"}, mo37405d2 = {"reflect", "Lkotlin/reflect/KFunction;", "R", "Lkotlin/Function;", "kotlin-reflect-api"}, mo37406k = 2, mo37407mv = {1, 1, 11})
/* compiled from: reflectLambda.kt */
public final class ReflectLambdaKt {
    @Nullable
    public static final <R> KFunction<R> reflect(@NotNull Function<? extends R> function) {
        Intrinsics.checkParameterIsNotNull(function, "$receiver");
        Metadata metadata = (Metadata) function.getClass().getAnnotation(Metadata.class);
        if (metadata != null) {
            String[] d1 = metadata.mo37404d1();
            if (d1.length == 0) {
                d1 = null;
            }
            if (d1 != null) {
                Pair readFunctionDataFrom = JvmProtoBufUtil.readFunctionDataFrom(d1, metadata.mo37405d2());
                JvmNameResolver jvmNameResolver = (JvmNameResolver) readFunctionDataFrom.component1();
                ProtoBuf.Function function2 = (ProtoBuf.Function) readFunctionDataFrom.component2();
                int[] mv = metadata.mo37407mv();
                JvmMetadataVersion jvmMetadataVersion = new JvmMetadataVersion(Arrays.copyOf(mv, mv.length));
                Class cls = function.getClass();
                MessageLite messageLite = function2;
                NameResolver nameResolver = jvmNameResolver;
                TypeTable typeTable = function2.getTypeTable();
                Intrinsics.checkExpressionValueIsNotNull(typeTable, "proto.typeTable");
                SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) UtilKt.deserializeToDescriptor(cls, messageLite, nameResolver, new kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable(typeTable), jvmMetadataVersion, ReflectLambdaKt$reflect$descriptor$1.INSTANCE);
                if (simpleFunctionDescriptor != null) {
                    return new KFunctionImpl<>(EmptyContainerForLocal.INSTANCE, simpleFunctionDescriptor);
                }
            }
        }
        return null;
    }
}
