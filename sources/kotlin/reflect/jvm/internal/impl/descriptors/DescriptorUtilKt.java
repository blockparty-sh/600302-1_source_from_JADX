package kotlin.reflect.jvm.internal.impl.descriptors;

/* compiled from: descriptorUtil.kt */
public final class DescriptorUtilKt {
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0063  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor resolveClassByFqName(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor r5, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.name.FqName r6, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation r7) {
        /*
            java.lang.String r0 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            java.lang.String r0 = "fqName"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            java.lang.String r0 = "lookupLocation"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            boolean r0 = r6.isRoot()
            r1 = 0
            if (r0 == 0) goto L_0x0017
            return r1
        L_0x0017:
            kotlin.reflect.jvm.internal.impl.name.FqName r0 = r6.parent()
            java.lang.String r2 = "fqName.parent()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor r0 = r5.getPackage(r0)
            kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope r0 = r0.getMemberScope()
            kotlin.reflect.jvm.internal.impl.name.Name r3 = r6.shortName()
            java.lang.String r4 = "fqName.shortName()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor r0 = r0.getContributedClassifier(r3, r7)
            boolean r3 = r0 instanceof kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
            if (r3 != 0) goto L_0x003a
            r0 = r1
        L_0x003a:
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r0
            if (r0 == 0) goto L_0x003f
            return r0
        L_0x003f:
            kotlin.reflect.jvm.internal.impl.name.FqName r0 = r6.parent()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r5 = resolveClassByFqName(r5, r0, r7)
            if (r5 == 0) goto L_0x005e
            kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope r5 = r5.getUnsubstitutedInnerClassesScope()
            if (r5 == 0) goto L_0x005e
            kotlin.reflect.jvm.internal.impl.name.Name r6 = r6.shortName()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r4)
            kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor r5 = r5.getContributedClassifier(r6, r7)
            goto L_0x005f
        L_0x005e:
            r5 = r1
        L_0x005f:
            boolean r6 = r5 instanceof kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
            if (r6 != 0) goto L_0x0064
            r5 = r1
        L_0x0064:
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r5 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.descriptors.DescriptorUtilKt.resolveClassByFqName(kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor, kotlin.reflect.jvm.internal.impl.name.FqName, kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation):kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor");
    }
}
