package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: specialBuiltinMembers.kt */
public final class BuiltinMethodsWithDifferentJvmName {
    public static final BuiltinMethodsWithDifferentJvmName INSTANCE = new BuiltinMethodsWithDifferentJvmName();
    private static final Map<Name, List<Name>> JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP;
    private static final Map<NameAndSignature, Name> NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP;
    @NotNull
    private static final List<Name> ORIGINAL_SHORT_NAMES;
    private static final NameAndSignature REMOVE_AT_NAME_AND_SIGNATURE;
    /* access modifiers changed from: private */
    public static final Map<String, Name> SIGNATURE_TO_JVM_REPRESENTATION_NAME;

    static {
        String desc = JvmPrimitiveType.INT.getDesc();
        String str = "JvmPrimitiveType.INT.desc";
        Intrinsics.checkExpressionValueIsNotNull(desc, str);
        REMOVE_AT_NAME_AND_SIGNATURE = SpecialBuiltinMembers.method("java/util/List", "removeAt", desc, "Ljava/lang/Object;");
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        String str2 = "Number";
        String javaLang = signatureBuildingComponents.javaLang(str2);
        String desc2 = JvmPrimitiveType.BYTE.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc2, "JvmPrimitiveType.BYTE.desc");
        String str3 = "";
        String javaLang2 = signatureBuildingComponents.javaLang(str2);
        String desc3 = JvmPrimitiveType.SHORT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc3, "JvmPrimitiveType.SHORT.desc");
        String javaLang3 = signatureBuildingComponents.javaLang(str2);
        String desc4 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc4, str);
        String javaLang4 = signatureBuildingComponents.javaLang(str2);
        String desc5 = JvmPrimitiveType.LONG.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc5, "JvmPrimitiveType.LONG.desc");
        String javaLang5 = signatureBuildingComponents.javaLang(str2);
        String desc6 = JvmPrimitiveType.FLOAT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc6, "JvmPrimitiveType.FLOAT.desc");
        String javaLang6 = signatureBuildingComponents.javaLang(str2);
        String desc7 = JvmPrimitiveType.DOUBLE.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc7, "JvmPrimitiveType.DOUBLE.desc");
        String javaLang7 = signatureBuildingComponents.javaLang("CharSequence");
        String desc8 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc8, str);
        String desc9 = JvmPrimitiveType.CHAR.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc9, "JvmPrimitiveType.CHAR.desc");
        NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP = MapsKt.mapOf(TuplesKt.m309to(SpecialBuiltinMembers.method(javaLang, "toByte", str3, desc2), Name.identifier("byteValue")), TuplesKt.m309to(SpecialBuiltinMembers.method(javaLang2, "toShort", str3, desc3), Name.identifier("shortValue")), TuplesKt.m309to(SpecialBuiltinMembers.method(javaLang3, "toInt", str3, desc4), Name.identifier("intValue")), TuplesKt.m309to(SpecialBuiltinMembers.method(javaLang4, "toLong", str3, desc5), Name.identifier("longValue")), TuplesKt.m309to(SpecialBuiltinMembers.method(javaLang5, "toFloat", str3, desc6), Name.identifier("floatValue")), TuplesKt.m309to(SpecialBuiltinMembers.method(javaLang6, "toDouble", str3, desc7), Name.identifier("doubleValue")), TuplesKt.m309to(REMOVE_AT_NAME_AND_SIGNATURE, Name.identifier("remove")), TuplesKt.m309to(SpecialBuiltinMembers.method(javaLang7, "get", desc8, desc9), Name.identifier("charAt")));
        Map<NameAndSignature, Name> map = NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP;
        Map<String, Name> linkedHashMap = new LinkedHashMap<>(MapsKt.mapCapacity(map.size()));
        for (Entry entry : map.entrySet()) {
            linkedHashMap.put(((NameAndSignature) entry.getKey()).getSignature(), entry.getValue());
        }
        SIGNATURE_TO_JVM_REPRESENTATION_NAME = linkedHashMap;
        Iterable<NameAndSignature> keySet = NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP.keySet();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(keySet, 10));
        for (NameAndSignature name : keySet) {
            arrayList.add(name.getName());
        }
        ORIGINAL_SHORT_NAMES = (List) arrayList;
        Iterable<Entry> entrySet = NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP.entrySet();
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(entrySet, 10));
        for (Entry entry2 : entrySet) {
            arrayList2.add(new Pair(((NameAndSignature) entry2.getKey()).getName(), entry2.getValue()));
        }
        Iterable<Pair> iterable = (List) arrayList2;
        Map<Name, List<Name>> linkedHashMap2 = new LinkedHashMap<>();
        for (Pair pair : iterable) {
            Name name2 = (Name) pair.getSecond();
            Object obj = linkedHashMap2.get(name2);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap2.put(name2, obj);
            }
            ((List) obj).add((Name) pair.getFirst());
        }
        JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP = linkedHashMap2;
    }

    private BuiltinMethodsWithDifferentJvmName() {
    }

    @NotNull
    public final List<Name> getORIGINAL_SHORT_NAMES() {
        return ORIGINAL_SHORT_NAMES;
    }

    public final boolean getSameAsRenamedInJvmBuiltin(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "$receiver");
        return ORIGINAL_SHORT_NAMES.contains(name);
    }

    @Nullable
    public final Name getJvmName(@NotNull SimpleFunctionDescriptor simpleFunctionDescriptor) {
        Intrinsics.checkParameterIsNotNull(simpleFunctionDescriptor, "functionDescriptor");
        Map<String, Name> map = SIGNATURE_TO_JVM_REPRESENTATION_NAME;
        String computeJvmSignature = MethodSignatureMappingKt.computeJvmSignature(simpleFunctionDescriptor);
        if (computeJvmSignature != null) {
            return (Name) map.get(computeJvmSignature);
        }
        return null;
    }

    public final boolean isBuiltinFunctionWithDifferentNameInJvm(@NotNull SimpleFunctionDescriptor simpleFunctionDescriptor) {
        Intrinsics.checkParameterIsNotNull(simpleFunctionDescriptor, "functionDescriptor");
        return KotlinBuiltIns.isBuiltIn(simpleFunctionDescriptor) && DescriptorUtilsKt.firstOverridden$default(simpleFunctionDescriptor, false, new C2978x6ce919c0(simpleFunctionDescriptor), 1, null) != null;
    }

    @NotNull
    public final List<Name> getBuiltinFunctionNamesByJvmName(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        List<Name> list = (List) JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP.get(name);
        return list != null ? list : CollectionsKt.emptyList();
    }

    public final boolean isRemoveAtByIndex(@NotNull SimpleFunctionDescriptor simpleFunctionDescriptor) {
        Intrinsics.checkParameterIsNotNull(simpleFunctionDescriptor, "$receiver");
        return Intrinsics.areEqual((Object) simpleFunctionDescriptor.getName().asString(), (Object) "removeAt") && Intrinsics.areEqual((Object) MethodSignatureMappingKt.computeJvmSignature(simpleFunctionDescriptor), (Object) REMOVE_AT_NAME_AND_SIGNATURE.getSignature());
    }
}
