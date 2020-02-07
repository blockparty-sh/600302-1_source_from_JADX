package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageScope.KotlinClassLookupResult.Found;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageScope.KotlinClassLookupResult.NotFound;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageScope.KotlinClassLookupResult.SyntheticClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.LightClassOriginKind;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LazyJavaPackageScope.kt */
final class LazyJavaPackageScope$classes$1 extends Lambda implements Function1<FindClassRequest, ClassDescriptor> {

    /* renamed from: $c */
    final /* synthetic */ LazyJavaResolverContext f706$c;
    final /* synthetic */ LazyJavaPackageScope this$0;

    LazyJavaPackageScope$classes$1(LazyJavaPackageScope lazyJavaPackageScope, LazyJavaResolverContext lazyJavaResolverContext) {
        this.this$0 = lazyJavaPackageScope;
        this.f706$c = lazyJavaResolverContext;
        super(1);
    }

    @Nullable
    public final ClassDescriptor invoke(@NotNull FindClassRequest findClassRequest) {
        KotlinJvmBinaryClass kotlinJvmBinaryClass;
        LazyJavaClassDescriptor lazyJavaClassDescriptor;
        Intrinsics.checkParameterIsNotNull(findClassRequest, "request");
        ClassId classId = new ClassId(this.this$0.getOwnerDescriptor().getFqName(), findClassRequest.getName());
        if (findClassRequest.getJavaClass() != null) {
            kotlinJvmBinaryClass = this.f706$c.getComponents().getKotlinClassFinder().findKotlinClass(findClassRequest.getJavaClass());
        } else {
            kotlinJvmBinaryClass = this.f706$c.getComponents().getKotlinClassFinder().findKotlinClass(classId);
        }
        ClassDescriptor classDescriptor = null;
        ClassId classId2 = kotlinJvmBinaryClass != null ? kotlinJvmBinaryClass.getClassId() : null;
        if (classId2 != null && (classId2.isNestedClass() || classId2.isLocal())) {
            return null;
        }
        KotlinClassLookupResult access$resolveKotlinBinaryClass = this.this$0.resolveKotlinBinaryClass(kotlinJvmBinaryClass);
        if (access$resolveKotlinBinaryClass instanceof Found) {
            classDescriptor = ((Found) access$resolveKotlinBinaryClass).getDescriptor();
        } else if (!(access$resolveKotlinBinaryClass instanceof SyntheticClass)) {
            if (access$resolveKotlinBinaryClass instanceof NotFound) {
                JavaClass javaClass = findClassRequest.getJavaClass();
                if (javaClass == null) {
                    javaClass = this.f706$c.getComponents().getFinder().findClass(classId);
                }
                JavaClass javaClass2 = javaClass;
                if ((javaClass2 != null ? javaClass2.getLightClassOriginKind() : null) != LightClassOriginKind.BINARY) {
                    FqName fqName = javaClass2 != null ? javaClass2.getFqName() : null;
                    if (fqName == null || fqName.isRoot() || (!Intrinsics.areEqual((Object) fqName.parent(), (Object) this.this$0.getOwnerDescriptor().getFqName()))) {
                        lazyJavaClassDescriptor = null;
                    } else {
                        lazyJavaClassDescriptor = new LazyJavaClassDescriptor(this.f706$c, this.this$0.getOwnerDescriptor(), javaClass2, null, 8, null);
                        this.f706$c.getComponents().getJavaClassesTracker().reportClass(lazyJavaClassDescriptor);
                    }
                    classDescriptor = lazyJavaClassDescriptor;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Couldn't find kotlin binary class for light class created by kotlin binary file\n");
                    sb.append("JavaClass: ");
                    sb.append(javaClass2);
                    sb.append(10);
                    sb.append("ClassId: ");
                    sb.append(classId);
                    sb.append(10);
                    sb.append("findKotlinClass(JavaClass) = ");
                    sb.append(this.f706$c.getComponents().getKotlinClassFinder().findKotlinClass(javaClass2));
                    sb.append(10);
                    sb.append("findKotlinClass(ClassId) = ");
                    sb.append(this.f706$c.getComponents().getKotlinClassFinder().findKotlinClass(classId));
                    sb.append(10);
                    throw new IllegalStateException(sb.toString());
                }
            } else {
                throw new NoWhenBranchMatchedException();
            }
        }
        return classDescriptor;
    }
}
