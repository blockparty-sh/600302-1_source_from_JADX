package kotlin.reflect.jvm.internal;

import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a \u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u0001*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002¨\u0006\u0005"}, mo37405d2 = {"computeCallerForAccessor", "Lkotlin/reflect/jvm/internal/FunctionCaller;", "Lkotlin/reflect/jvm/internal/KPropertyImpl$Accessor;", "isGetter", "", "kotlin-reflect-api"}, mo37406k = 2, mo37407mv = {1, 1, 11})
/* compiled from: KPropertyImpl.kt */
public final class KPropertyImplKt {
    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ce  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final kotlin.reflect.jvm.internal.FunctionCaller<?> computeCallerForAccessor(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.KPropertyImpl.Accessor<?, ?> r9, boolean r10) {
        /*
            kotlin.reflect.jvm.internal.KDeclarationContainerImpl$Companion r0 = kotlin.reflect.jvm.internal.KDeclarationContainerImpl.Companion
            kotlin.text.Regex r0 = r0.getLOCAL_PROPERTY_SIGNATURE$kotlin_reflect_api()
            kotlin.reflect.jvm.internal.KPropertyImpl r1 = r9.getProperty()
            java.lang.String r1 = r1.getSignature()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            boolean r0 = r0.matches(r1)
            if (r0 == 0) goto L_0x001b
            kotlin.reflect.jvm.internal.FunctionCaller$ThrowingCaller r9 = kotlin.reflect.jvm.internal.FunctionCaller.ThrowingCaller.INSTANCE
            kotlin.reflect.jvm.internal.FunctionCaller r9 = (kotlin.reflect.jvm.internal.FunctionCaller) r9
            return r9
        L_0x001b:
            kotlin.reflect.jvm.internal.KPropertyImplKt$computeCallerForAccessor$1 r2 = new kotlin.reflect.jvm.internal.KPropertyImplKt$computeCallerForAccessor$1
            r2.<init>(r9)
            kotlin.reflect.jvm.internal.KPropertyImplKt$computeCallerForAccessor$2 r0 = new kotlin.reflect.jvm.internal.KPropertyImplKt$computeCallerForAccessor$2
            r0.<init>(r9)
            kotlin.reflect.jvm.internal.KPropertyImplKt$computeCallerForAccessor$3 r3 = new kotlin.reflect.jvm.internal.KPropertyImplKt$computeCallerForAccessor$3
            r3.<init>(r9, r0)
            kotlin.reflect.jvm.internal.KPropertyImplKt$computeCallerForAccessor$4 r7 = new kotlin.reflect.jvm.internal.KPropertyImplKt$computeCallerForAccessor$4
            r7.<init>(r9)
            kotlin.reflect.jvm.internal.KPropertyImplKt$computeCallerForAccessor$5 r5 = new kotlin.reflect.jvm.internal.KPropertyImplKt$computeCallerForAccessor$5
            r5.<init>(r9)
            kotlin.reflect.jvm.internal.KPropertyImplKt$computeCallerForAccessor$6 r8 = new kotlin.reflect.jvm.internal.KPropertyImplKt$computeCallerForAccessor$6
            r0 = r8
            r1 = r9
            r4 = r10
            r6 = r7
            r0.<init>(r1, r2, r3, r4, r5, r6)
            kotlin.reflect.jvm.internal.RuntimeTypeMapper r0 = kotlin.reflect.jvm.internal.RuntimeTypeMapper.INSTANCE
            kotlin.reflect.jvm.internal.KPropertyImpl r1 = r9.getProperty()
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r1 = r1.getDescriptor()
            kotlin.reflect.jvm.internal.JvmPropertySignature r0 = r0.mapPropertySignature(r1)
            boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.JvmPropertySignature.KotlinProperty
            if (r1 == 0) goto L_0x013b
            kotlin.reflect.jvm.internal.JvmPropertySignature$KotlinProperty r0 = (kotlin.reflect.jvm.internal.JvmPropertySignature.KotlinProperty) r0
            kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf$JvmPropertySignature r1 = r0.getSignature()
            r2 = 0
            if (r10 == 0) goto L_0x0063
            boolean r10 = r1.hasGetter()
            if (r10 == 0) goto L_0x006e
            kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf$JvmMethodSignature r10 = r1.getGetter()
            goto L_0x006f
        L_0x0063:
            boolean r10 = r1.hasSetter()
            if (r10 == 0) goto L_0x006e
            kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf$JvmMethodSignature r10 = r1.getSetter()
            goto L_0x006f
        L_0x006e:
            r10 = r2
        L_0x006f:
            if (r10 == 0) goto L_0x009f
            kotlin.reflect.jvm.internal.KPropertyImpl r1 = r9.getProperty()
            kotlin.reflect.jvm.internal.KDeclarationContainerImpl r1 = r1.getContainer()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r2 = r0.getNameResolver()
            int r3 = r10.getName()
            java.lang.String r2 = r2.getString(r3)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r0 = r0.getNameResolver()
            int r10 = r10.getDesc()
            java.lang.String r10 = r0.getString(r10)
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor r0 = r9.getDescriptor()
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor) r0
            boolean r0 = kotlin.reflect.jvm.internal.UtilKt.isPublicInBytecode(r0)
            java.lang.reflect.Method r2 = r1.findMethodBySignature(r2, r10, r0)
        L_0x009f:
            if (r2 != 0) goto L_0x00ce
            kotlin.reflect.jvm.internal.KPropertyImpl r10 = r9.getProperty()
            java.lang.reflect.Field r10 = r10.getJavaField()
            if (r10 == 0) goto L_0x00b1
            kotlin.reflect.jvm.internal.FunctionCaller r9 = r8.invoke(r10)
            goto L_0x0180
        L_0x00b1:
            kotlin.reflect.jvm.internal.KotlinReflectionInternalError r10 = new kotlin.reflect.jvm.internal.KotlinReflectionInternalError
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "No accessors or field is found for property "
            r0.append(r1)
            kotlin.reflect.jvm.internal.KPropertyImpl r9 = r9.getProperty()
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            r10.<init>(r9)
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            throw r10
        L_0x00ce:
            int r10 = r2.getModifiers()
            boolean r10 = java.lang.reflect.Modifier.isStatic(r10)
            if (r10 != 0) goto L_0x00fb
            boolean r10 = r9.isBound()
            if (r10 == 0) goto L_0x00ee
            kotlin.reflect.jvm.internal.FunctionCaller$BoundInstanceMethod r10 = new kotlin.reflect.jvm.internal.FunctionCaller$BoundInstanceMethod
            kotlin.reflect.jvm.internal.KPropertyImpl r9 = r9.getProperty()
            java.lang.Object r9 = r9.getBoundReceiver()
            r10.<init>(r2, r9)
            kotlin.reflect.jvm.internal.FunctionCaller$Method r10 = (kotlin.reflect.jvm.internal.FunctionCaller.Method) r10
            goto L_0x00f6
        L_0x00ee:
            kotlin.reflect.jvm.internal.FunctionCaller$InstanceMethod r9 = new kotlin.reflect.jvm.internal.FunctionCaller$InstanceMethod
            r9.<init>(r2)
            r10 = r9
            kotlin.reflect.jvm.internal.FunctionCaller$Method r10 = (kotlin.reflect.jvm.internal.FunctionCaller.Method) r10
        L_0x00f6:
            r9 = r10
            kotlin.reflect.jvm.internal.FunctionCaller r9 = (kotlin.reflect.jvm.internal.FunctionCaller) r9
            goto L_0x0180
        L_0x00fb:
            boolean r10 = r7.invoke()
            if (r10 == 0) goto L_0x0119
            boolean r9 = r9.isBound()
            if (r9 == 0) goto L_0x010f
            kotlin.reflect.jvm.internal.FunctionCaller$BoundJvmStaticInObject r9 = new kotlin.reflect.jvm.internal.FunctionCaller$BoundJvmStaticInObject
            r9.<init>(r2)
            kotlin.reflect.jvm.internal.FunctionCaller$Method r9 = (kotlin.reflect.jvm.internal.FunctionCaller.Method) r9
            goto L_0x0116
        L_0x010f:
            kotlin.reflect.jvm.internal.FunctionCaller$JvmStaticInObject r9 = new kotlin.reflect.jvm.internal.FunctionCaller$JvmStaticInObject
            r9.<init>(r2)
            kotlin.reflect.jvm.internal.FunctionCaller$Method r9 = (kotlin.reflect.jvm.internal.FunctionCaller.Method) r9
        L_0x0116:
            kotlin.reflect.jvm.internal.FunctionCaller r9 = (kotlin.reflect.jvm.internal.FunctionCaller) r9
            goto L_0x0180
        L_0x0119:
            boolean r10 = r9.isBound()
            if (r10 == 0) goto L_0x012f
            kotlin.reflect.jvm.internal.FunctionCaller$BoundStaticMethod r10 = new kotlin.reflect.jvm.internal.FunctionCaller$BoundStaticMethod
            kotlin.reflect.jvm.internal.KPropertyImpl r9 = r9.getProperty()
            java.lang.Object r9 = r9.getBoundReceiver()
            r10.<init>(r2, r9)
            kotlin.reflect.jvm.internal.FunctionCaller$Method r10 = (kotlin.reflect.jvm.internal.FunctionCaller.Method) r10
            goto L_0x0137
        L_0x012f:
            kotlin.reflect.jvm.internal.FunctionCaller$StaticMethod r9 = new kotlin.reflect.jvm.internal.FunctionCaller$StaticMethod
            r9.<init>(r2)
            r10 = r9
            kotlin.reflect.jvm.internal.FunctionCaller$Method r10 = (kotlin.reflect.jvm.internal.FunctionCaller.Method) r10
        L_0x0137:
            r9 = r10
            kotlin.reflect.jvm.internal.FunctionCaller r9 = (kotlin.reflect.jvm.internal.FunctionCaller) r9
            goto L_0x0180
        L_0x013b:
            boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.JvmPropertySignature.JavaField
            if (r1 == 0) goto L_0x014a
            kotlin.reflect.jvm.internal.JvmPropertySignature$JavaField r0 = (kotlin.reflect.jvm.internal.JvmPropertySignature.JavaField) r0
            java.lang.reflect.Field r9 = r0.getField()
            kotlin.reflect.jvm.internal.FunctionCaller r9 = r8.invoke(r9)
            goto L_0x0180
        L_0x014a:
            boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.JvmPropertySignature.JavaMethodProperty
            if (r1 == 0) goto L_0x019e
            if (r10 == 0) goto L_0x0157
            kotlin.reflect.jvm.internal.JvmPropertySignature$JavaMethodProperty r0 = (kotlin.reflect.jvm.internal.JvmPropertySignature.JavaMethodProperty) r0
            java.lang.reflect.Method r10 = r0.getGetterMethod()
            goto L_0x015f
        L_0x0157:
            kotlin.reflect.jvm.internal.JvmPropertySignature$JavaMethodProperty r0 = (kotlin.reflect.jvm.internal.JvmPropertySignature.JavaMethodProperty) r0
            java.lang.reflect.Method r10 = r0.getSetterMethod()
            if (r10 == 0) goto L_0x0181
        L_0x015f:
            boolean r0 = r9.isBound()
            if (r0 == 0) goto L_0x0175
            kotlin.reflect.jvm.internal.FunctionCaller$BoundInstanceMethod r0 = new kotlin.reflect.jvm.internal.FunctionCaller$BoundInstanceMethod
            kotlin.reflect.jvm.internal.KPropertyImpl r9 = r9.getProperty()
            java.lang.Object r9 = r9.getBoundReceiver()
            r0.<init>(r10, r9)
            kotlin.reflect.jvm.internal.FunctionCaller$Method r0 = (kotlin.reflect.jvm.internal.FunctionCaller.Method) r0
            goto L_0x017d
        L_0x0175:
            kotlin.reflect.jvm.internal.FunctionCaller$InstanceMethod r9 = new kotlin.reflect.jvm.internal.FunctionCaller$InstanceMethod
            r9.<init>(r10)
            r0 = r9
            kotlin.reflect.jvm.internal.FunctionCaller$Method r0 = (kotlin.reflect.jvm.internal.FunctionCaller.Method) r0
        L_0x017d:
            r9 = r0
            kotlin.reflect.jvm.internal.FunctionCaller r9 = (kotlin.reflect.jvm.internal.FunctionCaller) r9
        L_0x0180:
            return r9
        L_0x0181:
            kotlin.reflect.jvm.internal.KotlinReflectionInternalError r9 = new kotlin.reflect.jvm.internal.KotlinReflectionInternalError
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r1 = "No source found for setter of Java method property: "
            r10.append(r1)
            java.lang.reflect.Method r0 = r0.getGetterMethod()
            r10.append(r0)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            throw r9
        L_0x019e:
            boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.JvmPropertySignature.MappedKotlinProperty
            if (r1 == 0) goto L_0x025a
            if (r10 == 0) goto L_0x01ab
            kotlin.reflect.jvm.internal.JvmPropertySignature$MappedKotlinProperty r0 = (kotlin.reflect.jvm.internal.JvmPropertySignature.MappedKotlinProperty) r0
            kotlin.reflect.jvm.internal.JvmFunctionSignature$KotlinFunction r10 = r0.getGetterSignature()
            goto L_0x01b3
        L_0x01ab:
            kotlin.reflect.jvm.internal.JvmPropertySignature$MappedKotlinProperty r0 = (kotlin.reflect.jvm.internal.JvmPropertySignature.MappedKotlinProperty) r0
            kotlin.reflect.jvm.internal.JvmFunctionSignature$KotlinFunction r10 = r0.getSetterSignature()
            if (r10 == 0) goto L_0x023d
        L_0x01b3:
            kotlin.reflect.jvm.internal.KPropertyImpl r0 = r9.getProperty()
            kotlin.reflect.jvm.internal.KDeclarationContainerImpl r0 = r0.getContainer()
            java.lang.String r1 = r10.getMethodName()
            java.lang.String r10 = r10.getMethodDesc()
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor r2 = r9.getDescriptor()
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r2 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor) r2
            boolean r2 = kotlin.reflect.jvm.internal.UtilKt.isPublicInBytecode(r2)
            java.lang.reflect.Method r10 = r0.findMethodBySignature(r1, r10, r2)
            if (r10 == 0) goto L_0x0220
            int r0 = r10.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isStatic(r0)
            r0 = r0 ^ 1
            boolean r1 = kotlin._Assertions.ENABLED
            if (r1 == 0) goto L_0x0201
            if (r0 == 0) goto L_0x01e4
            goto L_0x0201
        L_0x01e4:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r0 = "Mapped property cannot have a static accessor: "
            r10.append(r0)
            kotlin.reflect.jvm.internal.KPropertyImpl r9 = r9.getProperty()
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            java.lang.AssertionError r10 = new java.lang.AssertionError
            r10.<init>(r9)
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            throw r10
        L_0x0201:
            boolean r0 = r9.isBound()
            if (r0 == 0) goto L_0x0217
            kotlin.reflect.jvm.internal.FunctionCaller$BoundInstanceMethod r0 = new kotlin.reflect.jvm.internal.FunctionCaller$BoundInstanceMethod
            kotlin.reflect.jvm.internal.KPropertyImpl r9 = r9.getProperty()
            java.lang.Object r9 = r9.getBoundReceiver()
            r0.<init>(r10, r9)
            kotlin.reflect.jvm.internal.FunctionCaller r0 = (kotlin.reflect.jvm.internal.FunctionCaller) r0
            goto L_0x021f
        L_0x0217:
            kotlin.reflect.jvm.internal.FunctionCaller$InstanceMethod r9 = new kotlin.reflect.jvm.internal.FunctionCaller$InstanceMethod
            r9.<init>(r10)
            r0 = r9
            kotlin.reflect.jvm.internal.FunctionCaller r0 = (kotlin.reflect.jvm.internal.FunctionCaller) r0
        L_0x021f:
            return r0
        L_0x0220:
            kotlin.reflect.jvm.internal.KotlinReflectionInternalError r10 = new kotlin.reflect.jvm.internal.KotlinReflectionInternalError
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "No accessor found for property "
            r0.append(r1)
            kotlin.reflect.jvm.internal.KPropertyImpl r9 = r9.getProperty()
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            r10.<init>(r9)
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            throw r10
        L_0x023d:
            kotlin.reflect.jvm.internal.KotlinReflectionInternalError r10 = new kotlin.reflect.jvm.internal.KotlinReflectionInternalError
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "No setter found for property "
            r0.append(r1)
            kotlin.reflect.jvm.internal.KPropertyImpl r9 = r9.getProperty()
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            r10.<init>(r9)
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            throw r10
        L_0x025a:
            kotlin.NoWhenBranchMatchedException r9 = new kotlin.NoWhenBranchMatchedException
            r9.<init>()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.KPropertyImplKt.computeCallerForAccessor(kotlin.reflect.jvm.internal.KPropertyImpl$Accessor, boolean):kotlin.reflect.jvm.internal.FunctionCaller");
    }
}
