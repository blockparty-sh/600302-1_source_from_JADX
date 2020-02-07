package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: specialBuiltinMembers.kt */
public final class BuiltinMethodsWithSpecialGenericSignature {
    @NotNull
    private static final List<String> ERASED_COLLECTION_PARAMETER_NAMES;
    private static final List<NameAndSignature> ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES;
    private static final List<String> ERASED_COLLECTION_PARAMETER_SIGNATURES;
    private static final Set<Name> ERASED_VALUE_PARAMETERS_SHORT_NAMES;
    @NotNull
    private static final Set<String> ERASED_VALUE_PARAMETERS_SIGNATURES;
    private static final Map<NameAndSignature, TypeSafeBarrierDescription> GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP;
    public static final BuiltinMethodsWithSpecialGenericSignature INSTANCE = new BuiltinMethodsWithSpecialGenericSignature();
    private static final Map<String, TypeSafeBarrierDescription> SIGNATURE_TO_DEFAULT_VALUES_MAP;

    /* compiled from: specialBuiltinMembers.kt */
    public enum SpecialSignatureInfo {
        ONE_COLLECTION_PARAMETER("Ljava/util/Collection<+Ljava/lang/Object;>;", false),
        OBJECT_PARAMETER_NON_GENERIC(null, true),
        OBJECT_PARAMETER_GENERIC("Ljava/lang/Object;", true);
        
        private final boolean isObjectReplacedWithTypeParameter;
        @Nullable
        private final String valueParametersSignature;

        protected SpecialSignatureInfo(String str, boolean z) {
            this.valueParametersSignature = str;
            this.isObjectReplacedWithTypeParameter = z;
        }
    }

    /* compiled from: specialBuiltinMembers.kt */
    public enum TypeSafeBarrierDescription {
        NULL(null),
        INDEX(Integer.valueOf(-1)),
        FALSE(Boolean.valueOf(false));
        
        @Nullable
        private final Object defaultValue;

        /* compiled from: specialBuiltinMembers.kt */
        static final class MAP_GET_OR_DEFAULT extends TypeSafeBarrierDescription {
            MAP_GET_OR_DEFAULT(String str, int i) {
                super(str, i, null);
            }
        }

        protected TypeSafeBarrierDescription(Object obj) {
            this.defaultValue = obj;
        }
    }

    static {
        String str;
        Iterable of = SetsKt.setOf("containsAll", "removeAll", "retainAll");
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(of, 10));
        Iterator it = of.iterator();
        while (true) {
            str = "JvmPrimitiveType.BOOLEAN.desc";
            if (!it.hasNext()) {
                break;
            }
            String str2 = (String) it.next();
            String desc = JvmPrimitiveType.BOOLEAN.getDesc();
            Intrinsics.checkExpressionValueIsNotNull(desc, str);
            arrayList.add(SpecialBuiltinMembers.method("java/util/Collection", str2, "Ljava/util/Collection;", desc));
        }
        ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES = (List) arrayList;
        Iterable<NameAndSignature> iterable = ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (NameAndSignature signature : iterable) {
            arrayList2.add(signature.getSignature());
        }
        ERASED_COLLECTION_PARAMETER_SIGNATURES = (List) arrayList2;
        Iterable<NameAndSignature> iterable2 = ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES;
        Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
        for (NameAndSignature name : iterable2) {
            arrayList3.add(name.getName().asString());
        }
        ERASED_COLLECTION_PARAMETER_NAMES = (List) arrayList3;
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        String str3 = "Collection";
        String javaUtil = signatureBuildingComponents.javaUtil(str3);
        String desc2 = JvmPrimitiveType.BOOLEAN.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc2, str);
        String str4 = "Ljava/lang/Object;";
        String javaUtil2 = signatureBuildingComponents.javaUtil(str3);
        String desc3 = JvmPrimitiveType.BOOLEAN.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc3, str);
        String str5 = "remove";
        String str6 = "Map";
        String javaUtil3 = signatureBuildingComponents.javaUtil(str6);
        String desc4 = JvmPrimitiveType.BOOLEAN.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc4, str);
        String javaUtil4 = signatureBuildingComponents.javaUtil(str6);
        String desc5 = JvmPrimitiveType.BOOLEAN.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc5, str);
        String javaUtil5 = signatureBuildingComponents.javaUtil(str6);
        String desc6 = JvmPrimitiveType.BOOLEAN.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc6, str);
        String str7 = "Ljava/lang/Object;Ljava/lang/Object;";
        String str8 = "List";
        String javaUtil6 = signatureBuildingComponents.javaUtil(str8);
        String desc7 = JvmPrimitiveType.INT.getDesc();
        String str9 = "JvmPrimitiveType.INT.desc";
        Intrinsics.checkExpressionValueIsNotNull(desc7, str9);
        String javaUtil7 = signatureBuildingComponents.javaUtil(str8);
        String desc8 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc8, str9);
        GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP = MapsKt.mapOf(TuplesKt.m309to(SpecialBuiltinMembers.method(javaUtil, "contains", str4, desc2), TypeSafeBarrierDescription.FALSE), TuplesKt.m309to(SpecialBuiltinMembers.method(javaUtil2, str5, str4, desc3), TypeSafeBarrierDescription.FALSE), TuplesKt.m309to(SpecialBuiltinMembers.method(javaUtil3, "containsKey", str4, desc4), TypeSafeBarrierDescription.FALSE), TuplesKt.m309to(SpecialBuiltinMembers.method(javaUtil4, "containsValue", str4, desc5), TypeSafeBarrierDescription.FALSE), TuplesKt.m309to(SpecialBuiltinMembers.method(javaUtil5, str5, str7, desc6), TypeSafeBarrierDescription.FALSE), TuplesKt.m309to(SpecialBuiltinMembers.method(signatureBuildingComponents.javaUtil(str6), "getOrDefault", str7, str4), TypeSafeBarrierDescription.MAP_GET_OR_DEFAULT), TuplesKt.m309to(SpecialBuiltinMembers.method(signatureBuildingComponents.javaUtil(str6), "get", str4, str4), TypeSafeBarrierDescription.NULL), TuplesKt.m309to(SpecialBuiltinMembers.method(signatureBuildingComponents.javaUtil(str6), str5, str4, str4), TypeSafeBarrierDescription.NULL), TuplesKt.m309to(SpecialBuiltinMembers.method(javaUtil6, "indexOf", str4, desc7), TypeSafeBarrierDescription.INDEX), TuplesKt.m309to(SpecialBuiltinMembers.method(javaUtil7, "lastIndexOf", str4, desc8), TypeSafeBarrierDescription.INDEX));
        Map<NameAndSignature, TypeSafeBarrierDescription> map = GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP;
        Map<String, TypeSafeBarrierDescription> linkedHashMap = new LinkedHashMap<>(MapsKt.mapCapacity(map.size()));
        for (Entry entry : map.entrySet()) {
            linkedHashMap.put(((NameAndSignature) entry.getKey()).getSignature(), entry.getValue());
        }
        SIGNATURE_TO_DEFAULT_VALUES_MAP = linkedHashMap;
        Iterable<NameAndSignature> plus = SetsKt.plus(GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP.keySet(), (Iterable<? extends T>) ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES);
        Collection arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(plus, 10));
        for (NameAndSignature name2 : plus) {
            arrayList4.add(name2.getName());
        }
        ERASED_VALUE_PARAMETERS_SHORT_NAMES = CollectionsKt.toSet((List) arrayList4);
        Collection arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(plus, 10));
        for (NameAndSignature signature2 : plus) {
            arrayList5.add(signature2.getSignature());
        }
        ERASED_VALUE_PARAMETERS_SIGNATURES = CollectionsKt.toSet((List) arrayList5);
    }

    private BuiltinMethodsWithSpecialGenericSignature() {
    }

    /* access modifiers changed from: private */
    public final boolean getHasErasedValueParametersInJava(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        return CollectionsKt.contains(ERASED_VALUE_PARAMETERS_SIGNATURES, MethodSignatureMappingKt.computeJvmSignature(callableMemberDescriptor));
    }

    @JvmStatic
    @Nullable
    public static final FunctionDescriptor getOverriddenBuiltinFunctionWithErasedValueParametersInJava(@NotNull FunctionDescriptor functionDescriptor) {
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
        BuiltinMethodsWithSpecialGenericSignature builtinMethodsWithSpecialGenericSignature = INSTANCE;
        Name name = functionDescriptor.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "functionDescriptor.name");
        if (!builtinMethodsWithSpecialGenericSignature.getSameAsBuiltinMethodWithErasedValueParameters(name)) {
            return null;
        }
        return (FunctionDescriptor) DescriptorUtilsKt.firstOverridden$default(functionDescriptor, false, C2979x3e04c33b.INSTANCE, 1, null);
    }

    public final boolean getSameAsBuiltinMethodWithErasedValueParameters(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "$receiver");
        return ERASED_VALUE_PARAMETERS_SHORT_NAMES.contains(name);
    }

    @JvmStatic
    @Nullable
    public static final SpecialSignatureInfo getSpecialSignatureInfo(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        SpecialSignatureInfo specialSignatureInfo;
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "$receiver");
        if (!ERASED_VALUE_PARAMETERS_SHORT_NAMES.contains(callableMemberDescriptor.getName())) {
            return null;
        }
        CallableMemberDescriptor firstOverridden$default = DescriptorUtilsKt.firstOverridden$default(callableMemberDescriptor, false, C2980xdb572182.INSTANCE, 1, null);
        if (firstOverridden$default != null) {
            String computeJvmSignature = MethodSignatureMappingKt.computeJvmSignature(firstOverridden$default);
            if (computeJvmSignature != null) {
                if (ERASED_COLLECTION_PARAMETER_SIGNATURES.contains(computeJvmSignature)) {
                    return SpecialSignatureInfo.ONE_COLLECTION_PARAMETER;
                }
                Object obj = SIGNATURE_TO_DEFAULT_VALUES_MAP.get(computeJvmSignature);
                if (obj == null) {
                    Intrinsics.throwNpe();
                }
                if (((TypeSafeBarrierDescription) obj) == TypeSafeBarrierDescription.NULL) {
                    specialSignatureInfo = SpecialSignatureInfo.OBJECT_PARAMETER_GENERIC;
                } else {
                    specialSignatureInfo = SpecialSignatureInfo.OBJECT_PARAMETER_NON_GENERIC;
                }
                return specialSignatureInfo;
            }
        }
        return null;
    }
}
