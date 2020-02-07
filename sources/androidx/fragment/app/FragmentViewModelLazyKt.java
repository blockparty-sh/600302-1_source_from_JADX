package androidx.fragment.app;

import androidx.annotation.MainThread;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a1\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0010\b\n\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\b\u001aJ\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0007\u001aA\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u000e\b\n\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00062\u0010\b\n\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\b¨\u0006\u0010"}, mo37405d2 = {"activityViewModels", "Lkotlin/Lazy;", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/fragment/app/Fragment;", "factoryProducer", "Lkotlin/Function0;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "createViewModelLazy", "viewModelClass", "Lkotlin/reflect/KClass;", "storeProducer", "Landroidx/lifecycle/ViewModelStore;", "viewModels", "ownerProducer", "Landroidx/lifecycle/ViewModelStoreOwner;", "fragment-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: FragmentViewModelLazy.kt */
public final class FragmentViewModelLazyKt {
    public static /* synthetic */ Lazy viewModels$default(Fragment fragment, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = new FragmentViewModelLazyKt$viewModels$1(fragment);
        }
        if ((i & 2) != 0) {
            function02 = null;
        }
        Intrinsics.checkParameterIsNotNull(fragment, "$this$viewModels");
        Intrinsics.checkParameterIsNotNull(function0, "ownerProducer");
        Intrinsics.reifiedOperationMarker(4, "VM");
        return createViewModelLazy(fragment, Reflection.getOrCreateKotlinClass(ViewModel.class), new FragmentViewModelLazyKt$viewModels$2(function0), function02);
    }

    @NotNull
    @MainThread
    public static final /* synthetic */ <VM extends ViewModel> Lazy<VM> viewModels(@NotNull Fragment fragment, @NotNull Function0<? extends ViewModelStoreOwner> function0, @Nullable Function0<? extends Factory> function02) {
        Intrinsics.checkParameterIsNotNull(fragment, "$this$viewModels");
        Intrinsics.checkParameterIsNotNull(function0, "ownerProducer");
        Intrinsics.reifiedOperationMarker(4, "VM");
        return createViewModelLazy(fragment, Reflection.getOrCreateKotlinClass(ViewModel.class), new FragmentViewModelLazyKt$viewModels$2(function0), function02);
    }

    public static /* synthetic */ Lazy activityViewModels$default(Fragment fragment, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        Intrinsics.checkParameterIsNotNull(fragment, "$this$activityViewModels");
        Intrinsics.reifiedOperationMarker(4, "VM");
        return createViewModelLazy(fragment, Reflection.getOrCreateKotlinClass(ViewModel.class), new FragmentViewModelLazyKt$activityViewModels$1(fragment), function0);
    }

    @NotNull
    @MainThread
    public static final /* synthetic */ <VM extends ViewModel> Lazy<VM> activityViewModels(@NotNull Fragment fragment, @Nullable Function0<? extends Factory> function0) {
        Intrinsics.checkParameterIsNotNull(fragment, "$this$activityViewModels");
        Intrinsics.reifiedOperationMarker(4, "VM");
        return createViewModelLazy(fragment, Reflection.getOrCreateKotlinClass(ViewModel.class), new FragmentViewModelLazyKt$activityViewModels$1(fragment), function0);
    }

    public static /* synthetic */ Lazy createViewModelLazy$default(Fragment fragment, KClass kClass, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 4) != 0) {
            function02 = null;
        }
        return createViewModelLazy(fragment, kClass, function0, function02);
    }

    @NotNull
    @MainThread
    public static final <VM extends ViewModel> Lazy<VM> createViewModelLazy(@NotNull Fragment fragment, @NotNull KClass<VM> kClass, @NotNull Function0<? extends ViewModelStore> function0, @Nullable Function0<? extends Factory> function02) {
        Intrinsics.checkParameterIsNotNull(fragment, "$this$createViewModelLazy");
        Intrinsics.checkParameterIsNotNull(kClass, "viewModelClass");
        Intrinsics.checkParameterIsNotNull(function0, "storeProducer");
        if (function02 == null) {
            function02 = new FragmentViewModelLazyKt$createViewModelLazy$factoryPromise$1<>(fragment);
        }
        return new ViewModelLazy<>(kClass, function0, function02);
    }
}
