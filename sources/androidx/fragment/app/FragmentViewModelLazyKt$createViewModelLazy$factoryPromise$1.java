package androidx.fragment.app;

import android.app.Application;
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "Landroidx/lifecycle/ViewModelProvider$AndroidViewModelFactory;", "VM", "Landroidx/lifecycle/ViewModel;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: FragmentViewModelLazy.kt */
final class FragmentViewModelLazyKt$createViewModelLazy$factoryPromise$1 extends Lambda implements Function0<AndroidViewModelFactory> {
    final /* synthetic */ Fragment $this_createViewModelLazy;

    FragmentViewModelLazyKt$createViewModelLazy$factoryPromise$1(Fragment fragment) {
        this.$this_createViewModelLazy = fragment;
        super(0);
    }

    @NotNull
    public final AndroidViewModelFactory invoke() {
        FragmentActivity activity = this.$this_createViewModelLazy.getActivity();
        if (activity != null) {
            Application application = activity.getApplication();
            if (application != null) {
                AndroidViewModelFactory instance = AndroidViewModelFactory.getInstance(application);
                Intrinsics.checkExpressionValueIsNotNull(instance, "AndroidViewModelFactory.getInstance(application)");
                return instance;
            }
        }
        throw new IllegalStateException("ViewModel can be accessed only when Fragment is attached");
    }
}
