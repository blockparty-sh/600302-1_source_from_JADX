package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.C3063Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.BooleanFlagField;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Method;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableMessage;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.GeneratedExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JvmProtoBufUtil.kt */
public final class JvmProtoBufUtil {
    @NotNull
    private static final ExtensionRegistryLite EXTENSION_REGISTRY;
    public static final JvmProtoBufUtil INSTANCE = new JvmProtoBufUtil();

    static {
        ExtensionRegistryLite newInstance = ExtensionRegistryLite.newInstance();
        JvmProtoBuf.registerAllExtensions(newInstance);
        Intrinsics.checkExpressionValueIsNotNull(newInstance, "ExtensionRegistryLite.ne…f::registerAllExtensions)");
        EXTENSION_REGISTRY = newInstance;
    }

    private JvmProtoBufUtil() {
    }

    @NotNull
    public final ExtensionRegistryLite getEXTENSION_REGISTRY() {
        return EXTENSION_REGISTRY;
    }

    @JvmStatic
    @NotNull
    public static final Pair<JvmNameResolver, Class> readClassDataFrom(@NotNull String[] strArr, @NotNull String[] strArr2) {
        Intrinsics.checkParameterIsNotNull(strArr, "data");
        Intrinsics.checkParameterIsNotNull(strArr2, "strings");
        byte[] decodeBytes = BitEncoding.decodeBytes(strArr);
        Intrinsics.checkExpressionValueIsNotNull(decodeBytes, "BitEncoding.decodeBytes(data)");
        return readClassDataFrom(decodeBytes, strArr2);
    }

    @JvmStatic
    @NotNull
    public static final Pair<JvmNameResolver, Class> readClassDataFrom(@NotNull byte[] bArr, @NotNull String[] strArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "bytes");
        Intrinsics.checkParameterIsNotNull(strArr, "strings");
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        return new Pair<>(INSTANCE.readNameResolver(byteArrayInputStream, strArr), Class.parseFrom(byteArrayInputStream, EXTENSION_REGISTRY));
    }

    @JvmStatic
    @NotNull
    public static final Pair<JvmNameResolver, Package> readPackageDataFrom(@NotNull String[] strArr, @NotNull String[] strArr2) {
        Intrinsics.checkParameterIsNotNull(strArr, "data");
        Intrinsics.checkParameterIsNotNull(strArr2, "strings");
        byte[] decodeBytes = BitEncoding.decodeBytes(strArr);
        Intrinsics.checkExpressionValueIsNotNull(decodeBytes, "BitEncoding.decodeBytes(data)");
        return readPackageDataFrom(decodeBytes, strArr2);
    }

    @JvmStatic
    @NotNull
    public static final Pair<JvmNameResolver, Package> readPackageDataFrom(@NotNull byte[] bArr, @NotNull String[] strArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "bytes");
        Intrinsics.checkParameterIsNotNull(strArr, "strings");
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        return new Pair<>(INSTANCE.readNameResolver(byteArrayInputStream, strArr), Package.parseFrom(byteArrayInputStream, EXTENSION_REGISTRY));
    }

    @JvmStatic
    @NotNull
    public static final Pair<JvmNameResolver, Function> readFunctionDataFrom(@NotNull String[] strArr, @NotNull String[] strArr2) {
        Intrinsics.checkParameterIsNotNull(strArr, "data");
        Intrinsics.checkParameterIsNotNull(strArr2, "strings");
        InputStream byteArrayInputStream = new ByteArrayInputStream(BitEncoding.decodeBytes(strArr));
        return new Pair<>(INSTANCE.readNameResolver(byteArrayInputStream, strArr2), Function.parseFrom(byteArrayInputStream, EXTENSION_REGISTRY));
    }

    private final JvmNameResolver readNameResolver(@NotNull InputStream inputStream, String[] strArr) {
        StringTableTypes parseDelimitedFrom = StringTableTypes.parseDelimitedFrom(inputStream, EXTENSION_REGISTRY);
        Intrinsics.checkExpressionValueIsNotNull(parseDelimitedFrom, "JvmProtoBuf.StringTableT…this, EXTENSION_REGISTRY)");
        return new JvmNameResolver(parseDelimitedFrom, strArr);
    }

    @Nullable
    public final Method getJvmMethodSignature(@NotNull Function function, @NotNull NameResolver nameResolver, @NotNull TypeTable typeTable) {
        String str;
        Intrinsics.checkParameterIsNotNull(function, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        ExtendableMessage extendableMessage = function;
        GeneratedExtension<Function, JvmMethodSignature> generatedExtension = JvmProtoBuf.methodSignature;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension, "JvmProtoBuf.methodSignature");
        JvmMethodSignature jvmMethodSignature = (JvmMethodSignature) ProtoBufUtilKt.getExtensionOrNull(extendableMessage, generatedExtension);
        int name = (jvmMethodSignature == null || !jvmMethodSignature.hasName()) ? function.getName() : jvmMethodSignature.getName();
        if (jvmMethodSignature == null || !jvmMethodSignature.hasDesc()) {
            Collection listOfNotNull = CollectionsKt.listOfNotNull(ProtoTypeTableUtilKt.receiverType(function, typeTable));
            List valueParameterList = function.getValueParameterList();
            Intrinsics.checkExpressionValueIsNotNull(valueParameterList, "proto.valueParameterList");
            Iterable<ValueParameter> iterable = valueParameterList;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (ValueParameter valueParameter : iterable) {
                Intrinsics.checkExpressionValueIsNotNull(valueParameter, "it");
                arrayList.add(ProtoTypeTableUtilKt.type(valueParameter, typeTable));
            }
            Iterable<C3063Type> plus = CollectionsKt.plus(listOfNotNull, (Iterable<? extends T>) (List) arrayList);
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(plus, 10));
            for (C3063Type mapTypeDefault : plus) {
                String mapTypeDefault2 = INSTANCE.mapTypeDefault(mapTypeDefault, nameResolver);
                if (mapTypeDefault2 == null) {
                    return null;
                }
                arrayList2.add(mapTypeDefault2);
            }
            List list = (List) arrayList2;
            String mapTypeDefault3 = mapTypeDefault(ProtoTypeTableUtilKt.returnType(function, typeTable), nameResolver);
            if (mapTypeDefault3 == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(CollectionsKt.joinToString$default(list, "", "(", ")", 0, null, null, 56, null));
            sb.append(mapTypeDefault3);
            str = sb.toString();
        } else {
            str = nameResolver.getString(jvmMethodSignature.getDesc());
        }
        return new Method(nameResolver.getString(name), str);
    }

    @Nullable
    public final Method getJvmConstructorSignature(@NotNull Constructor constructor, @NotNull NameResolver nameResolver, @NotNull TypeTable typeTable) {
        String str;
        Intrinsics.checkParameterIsNotNull(constructor, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        ExtendableMessage extendableMessage = constructor;
        GeneratedExtension<Constructor, JvmMethodSignature> generatedExtension = JvmProtoBuf.constructorSignature;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension, "JvmProtoBuf.constructorSignature");
        JvmMethodSignature jvmMethodSignature = (JvmMethodSignature) ProtoBufUtilKt.getExtensionOrNull(extendableMessage, generatedExtension);
        if (jvmMethodSignature == null || !jvmMethodSignature.hasDesc()) {
            List valueParameterList = constructor.getValueParameterList();
            Intrinsics.checkExpressionValueIsNotNull(valueParameterList, "proto.valueParameterList");
            Iterable<ValueParameter> iterable = valueParameterList;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (ValueParameter valueParameter : iterable) {
                JvmProtoBufUtil jvmProtoBufUtil = INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(valueParameter, "it");
                String mapTypeDefault = jvmProtoBufUtil.mapTypeDefault(ProtoTypeTableUtilKt.type(valueParameter, typeTable), nameResolver);
                if (mapTypeDefault == null) {
                    return null;
                }
                arrayList.add(mapTypeDefault);
            }
            str = CollectionsKt.joinToString$default((List) arrayList, "", "(", ")V", 0, null, null, 56, null);
        } else {
            str = nameResolver.getString(jvmMethodSignature.getDesc());
        }
        return new Method("<init>", str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0058, code lost:
        if (r5 != null) goto L_0x005a;
     */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Field getJvmFieldSignature(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property r5, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r6, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r7) {
        /*
            r4 = this;
            java.lang.String r0 = "proto"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            java.lang.String r0 = "nameResolver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            java.lang.String r0 = "typeTable"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            r0 = r5
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableMessage r0 = (kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableMessage) r0
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$GeneratedExtension<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property, kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf$JvmPropertySignature> r1 = kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.propertySignature
            java.lang.String r2 = "JvmProtoBuf.propertySignature"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            java.lang.Object r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt.getExtensionOrNull(r0, r1)
            kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf$JvmPropertySignature r0 = (kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature) r0
            r1 = 0
            if (r0 == 0) goto L_0x0064
            boolean r2 = r0.hasField()
            if (r2 == 0) goto L_0x002d
            kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf$JvmFieldSignature r0 = r0.getField()
            goto L_0x002e
        L_0x002d:
            r0 = r1
        L_0x002e:
            if (r0 == 0) goto L_0x003b
            boolean r2 = r0.hasName()
            if (r2 == 0) goto L_0x003b
            int r2 = r0.getName()
            goto L_0x003f
        L_0x003b:
            int r2 = r5.getName()
        L_0x003f:
            if (r0 == 0) goto L_0x0050
            boolean r3 = r0.hasDesc()
            if (r3 == 0) goto L_0x0050
            int r5 = r0.getDesc()
            java.lang.String r5 = r6.getString(r5)
            goto L_0x005a
        L_0x0050:
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type r5 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt.returnType(r5, r7)
            java.lang.String r5 = r4.mapTypeDefault(r5, r6)
            if (r5 == 0) goto L_0x0064
        L_0x005a:
            kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature$Field r7 = new kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature$Field
            java.lang.String r6 = r6.getString(r2)
            r7.<init>(r6, r5)
            return r7
        L_0x0064:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil.getJvmFieldSignature(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property, kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver, kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable):kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature$Field");
    }

    private final String mapTypeDefault(C3063Type type, NameResolver nameResolver) {
        if (type.hasClassName()) {
            return ClassMapperLite.mapClass(nameResolver.getQualifiedClassName(type.getClassName()));
        }
        return null;
    }

    @JvmStatic
    public static final boolean isMovedFromInterfaceCompanion(@NotNull Property property) {
        Intrinsics.checkParameterIsNotNull(property, "proto");
        BooleanFlagField is_moved_from_interface_companion = JvmFlags.INSTANCE.getIS_MOVED_FROM_INTERFACE_COMPANION();
        Object extension = property.getExtension(JvmProtoBuf.flags);
        Intrinsics.checkExpressionValueIsNotNull(extension, "proto.getExtension(JvmProtoBuf.flags)");
        Boolean bool = is_moved_from_interface_companion.get(((Number) extension).intValue());
        Intrinsics.checkExpressionValueIsNotNull(bool, "JvmFlags.IS_MOVED_FROM_I…nsion(JvmProtoBuf.flags))");
        return bool.booleanValue();
    }
}
