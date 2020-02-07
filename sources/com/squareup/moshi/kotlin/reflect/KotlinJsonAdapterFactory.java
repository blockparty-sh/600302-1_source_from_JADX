package com.squareup.moshi.kotlin.reflect;

import com.squareup.moshi.JsonAdapter.Factory;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J.\u0010\u0003\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, mo37405d2 = {"Lcom/squareup/moshi/kotlin/reflect/KotlinJsonAdapterFactory;", "Lcom/squareup/moshi/JsonAdapter$Factory;", "()V", "create", "Lcom/squareup/moshi/JsonAdapter;", "type", "Ljava/lang/reflect/Type;", "annotations", "", "", "moshi", "Lcom/squareup/moshi/Moshi;", "moshi-kotlin"}, mo37406k = 1, mo37407mv = {1, 1, 11})
/* compiled from: KotlinJsonAdapter.kt */
public final class KotlinJsonAdapterFactory implements Factory {
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01d4, code lost:
        if (r3 != null) goto L_0x01db;
     */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.squareup.moshi.JsonAdapter<?> create(@org.jetbrains.annotations.NotNull java.lang.reflect.Type r18, @org.jetbrains.annotations.NotNull java.util.Set<? extends java.lang.annotation.Annotation> r19, @org.jetbrains.annotations.NotNull com.squareup.moshi.Moshi r20) {
        /*
            r17 = this;
            r1 = r18
            r2 = r20
            java.lang.String r0 = "type"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r1, r0)
            java.lang.String r0 = "annotations"
            r3 = r19
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            java.lang.String r0 = "moshi"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            boolean r0 = r19.isEmpty()
            r3 = 0
            if (r0 != 0) goto L_0x001d
            return r3
        L_0x001d:
            java.lang.Class r4 = com.squareup.moshi.Types.getRawType(r18)
            java.lang.String r0 = "rawType"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r0)
            boolean r0 = r4.isInterface()
            if (r0 == 0) goto L_0x002d
            return r3
        L_0x002d:
            boolean r0 = r4.isEnum()
            if (r0 == 0) goto L_0x0034
            return r3
        L_0x0034:
            java.lang.Class r0 = com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterKt.KOTLIN_METADATA
            boolean r0 = r4.isAnnotationPresent(r0)
            if (r0 != 0) goto L_0x003f
            return r3
        L_0x003f:
            boolean r0 = com.squareup.moshi.internal.Util.isPlatformType(r4)
            if (r0 == 0) goto L_0x0046
            return r3
        L_0x0046:
            com.squareup.moshi.JsonAdapter r0 = com.squareup.moshi.internal.Util.generatedAdapter(r2, r1, r4)     // Catch:{ RuntimeException -> 0x004d }
            if (r0 == 0) goto L_0x0057
            return r0
        L_0x004d:
            r0 = move-exception
            r5 = r0
            java.lang.Throwable r0 = r5.getCause()
            boolean r0 = r0 instanceof java.lang.ClassNotFoundException
            if (r0 == 0) goto L_0x0368
        L_0x0057:
            boolean r0 = r4.isLocalClass()
            if (r0 != 0) goto L_0x034b
            kotlin.reflect.KClass r0 = kotlin.jvm.JvmClassMappingKt.getKotlinClass(r4)
            boolean r5 = r0.isAbstract()
            if (r5 != 0) goto L_0x032e
            boolean r5 = r0.isInner()
            if (r5 != 0) goto L_0x0311
            java.lang.Object r5 = r0.getObjectInstance()
            if (r5 != 0) goto L_0x02f4
            kotlin.reflect.KFunction r5 = kotlin.reflect.full.KClasses.getPrimaryConstructor(r0)
            if (r5 == 0) goto L_0x02f2
            java.util.List r6 = r5.getParameters()
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            r7 = 10
            int r8 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r6, r7)
            int r8 = kotlin.collections.MapsKt.mapCapacity(r8)
            r9 = 16
            int r8 = kotlin.ranges.RangesKt.coerceAtLeast(r8, r9)
            java.util.LinkedHashMap r9 = new java.util.LinkedHashMap
            r9.<init>(r8)
            java.util.Map r9 = (java.util.Map) r9
            java.util.Iterator r6 = r6.iterator()
        L_0x009a:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto L_0x00af
            java.lang.Object r8 = r6.next()
            r10 = r8
            kotlin.reflect.KParameter r10 = (kotlin.reflect.KParameter) r10
            java.lang.String r10 = r10.getName()
            r9.put(r10, r8)
            goto L_0x009a
        L_0x00af:
            r6 = r5
            kotlin.reflect.KCallable r6 = (kotlin.reflect.KCallable) r6
            r8 = 1
            kotlin.reflect.jvm.KCallablesJvm.setAccessible(r6, r8)
            java.util.LinkedHashMap r6 = new java.util.LinkedHashMap
            r6.<init>()
            java.util.Collection r0 = kotlin.reflect.full.KClasses.getMemberProperties(r0)
            java.util.Iterator r0 = r0.iterator()
        L_0x00c3:
            boolean r10 = r0.hasNext()
            java.lang.String r11 = "null cannot be cast to non-null type kotlin.Array<T>"
            r12 = 0
            if (r10 == 0) goto L_0x0231
            java.lang.Object r10 = r0.next()
            kotlin.reflect.KProperty1 r10 = (kotlin.reflect.KProperty1) r10
            java.lang.String r13 = r10.getName()
            java.lang.Object r13 = r9.get(r13)
            kotlin.reflect.KParameter r13 = (kotlin.reflect.KParameter) r13
            r14 = r10
            kotlin.reflect.KProperty r14 = (kotlin.reflect.KProperty) r14
            java.lang.reflect.Field r14 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaField(r14)
            if (r14 == 0) goto L_0x00ea
            int r14 = r14.getModifiers()
            goto L_0x00eb
        L_0x00ea:
            r14 = 0
        L_0x00eb:
            boolean r14 = java.lang.reflect.Modifier.isTransient(r14)
            if (r14 == 0) goto L_0x0114
            if (r13 == 0) goto L_0x0217
            boolean r10 = r13.isOptional()
            if (r10 == 0) goto L_0x00fb
            goto L_0x0217
        L_0x00fb:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "No default value for transient constructor "
            r1.append(r2)
            r1.append(r13)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x0114:
            if (r13 == 0) goto L_0x0160
            kotlin.reflect.KType r14 = r13.getType()
            kotlin.reflect.KType r15 = r10.getReturnType()
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual(r14, r15)
            r14 = r14 ^ r8
            if (r14 != 0) goto L_0x0126
            goto L_0x0160
        L_0x0126:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = 39
            r1.append(r2)
            java.lang.String r2 = r10.getName()
            r1.append(r2)
            java.lang.String r2 = "' has a constructor parameter of type "
            r1.append(r2)
            kotlin.reflect.KType r2 = r13.getType()
            r1.append(r2)
            java.lang.String r2 = " but a property of type "
            r1.append(r2)
            kotlin.reflect.KType r2 = r10.getReturnType()
            r1.append(r2)
            r2 = 46
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x0160:
            boolean r14 = r10 instanceof kotlin.reflect.KMutableProperty1
            if (r14 != 0) goto L_0x0168
            if (r13 != 0) goto L_0x0168
            goto L_0x0217
        L_0x0168:
            r14 = r10
            kotlin.reflect.KCallable r14 = (kotlin.reflect.KCallable) r14
            kotlin.reflect.jvm.KCallablesJvm.setAccessible(r14, r8)
            java.util.List r14 = r10.getAnnotations()
            r15 = r10
            kotlin.reflect.KAnnotatedElement r15 = (kotlin.reflect.KAnnotatedElement) r15
            java.util.List r15 = r15.getAnnotations()
            java.lang.Iterable r15 = (java.lang.Iterable) r15
            java.util.Iterator r15 = r15.iterator()
        L_0x017f:
            boolean r16 = r15.hasNext()
            if (r16 == 0) goto L_0x0194
            java.lang.Object r16 = r15.next()
            r8 = r16
            java.lang.annotation.Annotation r8 = (java.lang.annotation.Annotation) r8
            boolean r8 = r8 instanceof com.squareup.moshi.Json
            if (r8 == 0) goto L_0x0192
            goto L_0x0196
        L_0x0192:
            r8 = 1
            goto L_0x017f
        L_0x0194:
            r16 = r3
        L_0x0196:
            com.squareup.moshi.Json r16 = (com.squareup.moshi.Json) r16
            if (r13 == 0) goto L_0x01ce
            java.util.Collection r14 = (java.util.Collection) r14
            java.util.List r8 = r13.getAnnotations()
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.List r14 = kotlin.collections.CollectionsKt.plus(r14, r8)
            if (r16 != 0) goto L_0x01ce
            r8 = r13
            kotlin.reflect.KAnnotatedElement r8 = (kotlin.reflect.KAnnotatedElement) r8
            java.util.List r8 = r8.getAnnotations()
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.Iterator r8 = r8.iterator()
        L_0x01b5:
            boolean r15 = r8.hasNext()
            if (r15 == 0) goto L_0x01c9
            java.lang.Object r15 = r8.next()
            r3 = r15
            java.lang.annotation.Annotation r3 = (java.lang.annotation.Annotation) r3
            boolean r3 = r3 instanceof com.squareup.moshi.Json
            if (r3 == 0) goto L_0x01c7
            goto L_0x01ca
        L_0x01c7:
            r3 = 0
            goto L_0x01b5
        L_0x01c9:
            r15 = 0
        L_0x01ca:
            r16 = r15
            com.squareup.moshi.Json r16 = (com.squareup.moshi.Json) r16
        L_0x01ce:
            if (r16 == 0) goto L_0x01d7
            java.lang.String r3 = r16.name()
            if (r3 == 0) goto L_0x01d7
            goto L_0x01db
        L_0x01d7:
            java.lang.String r3 = r10.getName()
        L_0x01db:
            kotlin.reflect.KType r8 = r10.getReturnType()
            java.lang.reflect.Type r8 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaType(r8)
            java.lang.reflect.Type r8 = com.squareup.moshi.internal.Util.resolve(r1, r4, r8)
            java.util.Collection r14 = (java.util.Collection) r14
            if (r14 == 0) goto L_0x0229
            java.lang.annotation.Annotation[] r12 = new java.lang.annotation.Annotation[r12]
            java.lang.Object[] r12 = r14.toArray(r12)
            if (r12 == 0) goto L_0x0223
            java.lang.annotation.Annotation[] r12 = (java.lang.annotation.Annotation[]) r12
            java.util.Set r11 = com.squareup.moshi.internal.Util.jsonAnnotations(r12)
            java.lang.String r12 = r10.getName()
            com.squareup.moshi.JsonAdapter r8 = r2.adapter(r8, r11, r12)
            r11 = r6
            java.util.Map r11 = (java.util.Map) r11
            java.lang.String r12 = r10.getName()
            com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter$Binding r14 = new com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter$Binding
            java.lang.String r15 = "adapter"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r15)
            if (r10 == 0) goto L_0x021b
            r14.<init>(r3, r8, r10, r13)
            r11.put(r12, r14)
        L_0x0217:
            r3 = 0
            r8 = 1
            goto L_0x00c3
        L_0x021b:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.reflect.KProperty1<kotlin.Any, kotlin.Any?>"
            r0.<init>(r1)
            throw r0
        L_0x0223:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            r0.<init>(r11)
            throw r0
        L_0x0229:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type java.util.Collection<T>"
            r0.<init>(r1)
            throw r0
        L_0x0231:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List r1 = r5.getParameters()
            java.util.Iterator r1 = r1.iterator()
        L_0x023e:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0284
            java.lang.Object r2 = r1.next()
            kotlin.reflect.KParameter r2 = (kotlin.reflect.KParameter) r2
            r3 = r6
            java.util.Map r3 = (java.util.Map) r3
            java.lang.String r4 = r2.getName()
            java.util.Map r3 = kotlin.jvm.internal.TypeIntrinsics.asMutableMap(r3)
            java.lang.Object r3 = r3.remove(r4)
            com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter$Binding r3 = (com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter.Binding) r3
            if (r3 != 0) goto L_0x027d
            boolean r4 = r2.isOptional()
            if (r4 == 0) goto L_0x0264
            goto L_0x027d
        L_0x0264:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "No property for required constructor "
            r1.append(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x027d:
            r2 = r0
            java.util.Collection r2 = (java.util.Collection) r2
            r2.add(r3)
            goto L_0x023e
        L_0x0284:
            r1 = r0
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.Collection r2 = r6.values()
            java.lang.String r3 = "bindingsByName.values"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            kotlin.collections.CollectionsKt.addAll(r1, r2)
            r1 = r0
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r2 = new java.util.ArrayList
            int r3 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r1, r7)
            r2.<init>(r3)
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.Iterator r1 = r1.iterator()
        L_0x02a7:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x02c2
            java.lang.Object r3 = r1.next()
            com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter$Binding r3 = (com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter.Binding) r3
            if (r3 == 0) goto L_0x02bc
            java.lang.String r3 = r3.getName()
            if (r3 == 0) goto L_0x02bc
            goto L_0x02be
        L_0x02bc:
            java.lang.String r3 = "\u0000"
        L_0x02be:
            r2.add(r3)
            goto L_0x02a7
        L_0x02c2:
            java.util.List r2 = (java.util.List) r2
            java.util.Collection r2 = (java.util.Collection) r2
            java.lang.String[] r1 = new java.lang.String[r12]
            java.lang.Object[] r1 = r2.toArray(r1)
            if (r1 == 0) goto L_0x02ec
            java.lang.String[] r1 = (java.lang.String[]) r1
            int r2 = r1.length
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r2)
            java.lang.String[] r1 = (java.lang.String[]) r1
            com.squareup.moshi.JsonReader$Options r1 = com.squareup.moshi.JsonReader.Options.m288of(r1)
            com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter r2 = new com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter
            java.util.List r0 = (java.util.List) r0
            java.lang.String r3 = "options"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r3)
            r2.<init>(r5, r0, r1)
            com.squareup.moshi.JsonAdapter r0 = r2.nullSafe()
            return r0
        L_0x02ec:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            r0.<init>(r11)
            throw r0
        L_0x02f2:
            r1 = r3
            return r1
        L_0x02f4:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Cannot serialize object declaration "
            r1.append(r2)
            java.lang.String r2 = r4.getName()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x0311:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Cannot serialize inner class "
            r1.append(r2)
            java.lang.String r2 = r4.getName()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x032e:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Cannot serialize abstract class "
            r1.append(r2)
            java.lang.String r2 = r4.getName()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x034b:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Cannot serialize local class or object expression "
            r1.append(r2)
            java.lang.String r2 = r4.getName()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x0368:
            r0 = r5
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory.create(java.lang.reflect.Type, java.util.Set, com.squareup.moshi.Moshi):com.squareup.moshi.JsonAdapter");
    }
}
