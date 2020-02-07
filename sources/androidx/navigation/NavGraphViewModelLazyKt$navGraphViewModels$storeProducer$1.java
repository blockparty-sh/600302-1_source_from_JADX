package androidx.navigation;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.fragment.FragmentKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "Landroidx/lifecycle/ViewModelStore;", "VM", "Landroidx/lifecycle/ViewModel;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: NavGraphViewModelLazy.kt */
public final class NavGraphViewModelLazyKt$navGraphViewModels$storeProducer$1 extends Lambda implements Function0<ViewModelStore> {
    final /* synthetic */ int $navGraphId;
    final /* synthetic */ Fragment $this_navGraphViewModels;

    public NavGraphViewModelLazyKt$navGraphViewModels$storeProducer$1(Fragment fragment, int i) {
        this.$this_navGraphViewModels = fragment;
        this.$navGraphId = i;
        super(0);
    }

    @NotNull
    public final ViewModelStore invoke() {
        ViewModelStoreOwner viewModelStoreOwner = FragmentKt.findNavController(this.$this_navGraphViewModels).getViewModelStoreOwner(this.$navGraphId);
        Intrinsics.checkExpressionValueIsNotNull(viewModelStoreOwner, "findNavController().getV…delStoreOwner(navGraphId)");
        ViewModelStore viewModelStore = viewModelStoreOwner.getViewModelStore();
        Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "findNavController().getV…avGraphId).viewModelStore");
        return viewModelStore;
    }
}
