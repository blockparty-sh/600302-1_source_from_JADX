package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import java.util.List;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.TypeParameterResolver.EMPTY;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageFragment;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.CacheWithNotNullValues;
import org.jetbrains.annotations.NotNull;

/* compiled from: LazyJavaPackageFragmentProvider.kt */
public final class LazyJavaPackageFragmentProvider implements PackageFragmentProvider {
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final LazyJavaResolverContext f698c;
    private final CacheWithNotNullValues<FqName, LazyJavaPackageFragment> packageFragments = this.f698c.getStorageManager().createCacheWithNotNullValues();

    public LazyJavaPackageFragmentProvider(@NotNull JavaResolverComponents javaResolverComponents) {
        Intrinsics.checkParameterIsNotNull(javaResolverComponents, "components");
        this.f698c = new LazyJavaResolverContext(javaResolverComponents, EMPTY.INSTANCE, LazyKt.lazyOf(null));
    }

    private final LazyJavaPackageFragment getPackageFragment(FqName fqName) {
        JavaPackage findPackage = this.f698c.getComponents().getFinder().findPackage(fqName);
        if (findPackage != null) {
            return (LazyJavaPackageFragment) this.packageFragments.computeIfAbsent(fqName, new LazyJavaPackageFragmentProvider$getPackageFragment$1(this, findPackage));
        }
        return null;
    }

    @NotNull
    public List<LazyJavaPackageFragment> getPackageFragments(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return CollectionsKt.listOfNotNull(getPackageFragment(fqName));
    }

    @NotNull
    public List<FqName> getSubPackagesOf(@NotNull FqName fqName, @NotNull Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
        LazyJavaPackageFragment packageFragment = getPackageFragment(fqName);
        List<FqName> subPackageFqNames$descriptors_jvm = packageFragment != null ? packageFragment.getSubPackageFqNames$descriptors_jvm() : null;
        return subPackageFqNames$descriptors_jvm != null ? subPackageFqNames$descriptors_jvm : CollectionsKt.emptyList();
    }
}
