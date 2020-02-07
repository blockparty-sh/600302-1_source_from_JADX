package androidx.navigation;

import androidx.annotation.IdRes;
import androidx.annotation.MainThread;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a;\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0010\b\n\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\b¨\u0006\n"}, mo37405d2 = {"navGraphViewModels", "Lkotlin/Lazy;", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/fragment/app/Fragment;", "navGraphId", "", "factoryProducer", "Lkotlin/Function0;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "navigation-fragment-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: NavGraphViewModelLazy.kt */
public final class NavGraphViewModelLazyKt {
    public static /* synthetic */ Lazy navGraphViewModels$default(Fragment fragment, int i, Function0 function0, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            function0 = null;
        }
        Intrinsics.checkParameterIsNotNull(fragment, "$this$navGraphViewModels");
        Function0 navGraphViewModelLazyKt$navGraphViewModels$storeProducer$1 = new NavGraphViewModelLazyKt$navGraphViewModels$storeProducer$1(fragment, i);
        Intrinsics.reifiedOperationMarker(4, "VM");
        return FragmentViewModelLazyKt.createViewModelLazy(fragment, Reflection.getOrCreateKotlinClass(ViewModel.class), navGraphViewModelLazyKt$navGraphViewModels$storeProducer$1, function0);
    }

    @NotNull
    @MainThread
    public static final /* synthetic */ <VM extends ViewModel> Lazy<VM> navGraphViewModels(@NotNull Fragment fragment, @IdRes int i, @Nullable Function0<? extends Factory> function0) {
        Intrinsics.checkParameterIsNotNull(fragment, "$this$navGraphViewModels");
        Function0 navGraphViewModelLazyKt$navGraphViewModels$storeProducer$1 = new NavGraphViewModelLazyKt$navGraphViewModels$storeProducer$1(fragment, i);
        Intrinsics.reifiedOperationMarker(4, "VM");
        return FragmentViewModelLazyKt.createViewModelLazy(fragment, Reflection.getOrCreateKotlinClass(ViewModel.class), navGraphViewModelLazyKt$navGraphViewModels$storeProducer$1, function0);
    }
}
