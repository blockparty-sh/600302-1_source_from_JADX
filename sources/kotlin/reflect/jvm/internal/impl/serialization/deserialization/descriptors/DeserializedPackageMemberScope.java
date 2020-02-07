package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.incremental.UtilsKt;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable.Companion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DeserializedPackageMemberScope.kt */
public class DeserializedPackageMemberScope extends DeserializedMemberScope {
    private final PackageFragmentDescriptor packageDescriptor;
    private final FqName packageFqName = this.packageDescriptor.getFqName();

    /* access modifiers changed from: protected */
    public void addEnumEntryDescriptors(@NotNull Collection<DeclarationDescriptor> collection, @NotNull Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(collection, "result");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
    }

    public DeserializedPackageMemberScope(@NotNull PackageFragmentDescriptor packageFragmentDescriptor, @NotNull Package packageR, @NotNull NameResolver nameResolver, @NotNull BinaryVersion binaryVersion, @Nullable DeserializedContainerSource deserializedContainerSource, @NotNull DeserializationComponents deserializationComponents, @NotNull Function0<? extends Collection<Name>> function0) {
        PackageFragmentDescriptor packageFragmentDescriptor2 = packageFragmentDescriptor;
        Intrinsics.checkParameterIsNotNull(packageFragmentDescriptor2, "packageDescriptor");
        Intrinsics.checkParameterIsNotNull(packageR, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        Intrinsics.checkParameterIsNotNull(binaryVersion, "metadataVersion");
        Intrinsics.checkParameterIsNotNull(deserializationComponents, "components");
        Function0<? extends Collection<Name>> function02 = function0;
        Intrinsics.checkParameterIsNotNull(function02, "classNames");
        TypeTable typeTable = packageR.getTypeTable();
        Intrinsics.checkExpressionValueIsNotNull(typeTable, "proto.typeTable");
        kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable typeTable2 = new kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable(typeTable);
        Companion companion = VersionRequirementTable.Companion;
        ProtoBuf.VersionRequirementTable versionRequirementTable = packageR.getVersionRequirementTable();
        Intrinsics.checkExpressionValueIsNotNull(versionRequirementTable, "proto.versionRequirementTable");
        DeserializationContext createContext = deserializationComponents.createContext(packageFragmentDescriptor, nameResolver, typeTable2, companion.create(versionRequirementTable), binaryVersion, deserializedContainerSource);
        List functionList = packageR.getFunctionList();
        Intrinsics.checkExpressionValueIsNotNull(functionList, "proto.functionList");
        Collection collection = functionList;
        List propertyList = packageR.getPropertyList();
        Intrinsics.checkExpressionValueIsNotNull(propertyList, "proto.propertyList");
        Collection collection2 = propertyList;
        List typeAliasList = packageR.getTypeAliasList();
        Intrinsics.checkExpressionValueIsNotNull(typeAliasList, "proto.typeAliasList");
        super(createContext, collection, collection2, typeAliasList, function02);
        this.packageDescriptor = packageFragmentDescriptor2;
    }

    @NotNull
    public List<DeclarationDescriptor> getContributedDescriptors(@NotNull DescriptorKindFilter descriptorKindFilter, @NotNull Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
        Collection computeDescriptors = computeDescriptors(descriptorKindFilter, function1, NoLookupLocation.WHEN_GET_ALL_DESCRIPTORS);
        Iterable<ClassDescriptorFactory> fictitiousClassDescriptorFactories = getC().getComponents().getFictitiousClassDescriptorFactories();
        Collection arrayList = new ArrayList();
        for (ClassDescriptorFactory allContributedClassesIfPossible : fictitiousClassDescriptorFactories) {
            CollectionsKt.addAll(arrayList, (Iterable<? extends T>) allContributedClassesIfPossible.getAllContributedClassesIfPossible(this.packageFqName));
        }
        return CollectionsKt.plus(computeDescriptors, (Iterable<? extends T>) (List) arrayList);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasClass(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.name.Name r6) {
        /*
            r5 = this;
            java.lang.String r0 = "name"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            boolean r0 = super.hasClass(r6)
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0045
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r0 = r5.getC()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents r0 = r0.getComponents()
            java.lang.Iterable r0 = r0.getFictitiousClassDescriptorFactories()
            boolean r3 = r0 instanceof java.util.Collection
            if (r3 == 0) goto L_0x0028
            r3 = r0
            java.util.Collection r3 = (java.util.Collection) r3
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x0028
        L_0x0026:
            r6 = 0
            goto L_0x0041
        L_0x0028:
            java.util.Iterator r0 = r0.iterator()
        L_0x002c:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0026
            java.lang.Object r3 = r0.next()
            kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory r3 = (kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory) r3
            kotlin.reflect.jvm.internal.impl.name.FqName r4 = r5.packageFqName
            boolean r3 = r3.shouldCreateClass(r4, r6)
            if (r3 == 0) goto L_0x002c
            r6 = 1
        L_0x0041:
            if (r6 == 0) goto L_0x0044
            goto L_0x0045
        L_0x0044:
            r1 = 0
        L_0x0045:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPackageMemberScope.hasClass(kotlin.reflect.jvm.internal.impl.name.Name):boolean");
    }

    /* access modifiers changed from: protected */
    @NotNull
    public ClassId createClassId(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new ClassId(this.packageFqName, name);
    }

    @Nullable
    public ClassifierDescriptor getContributedClassifier(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        recordLookup(name, lookupLocation);
        return super.getContributedClassifier(name, lookupLocation);
    }

    public void recordLookup(@NotNull Name name, @NotNull LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "location");
        UtilsKt.record(getC().getComponents().getLookupTracker(), lookupLocation, this.packageDescriptor, name);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Set<Name> getNonDeclaredFunctionNames() {
        return SetsKt.emptySet();
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Set<Name> getNonDeclaredVariableNames() {
        return SetsKt.emptySet();
    }
}
