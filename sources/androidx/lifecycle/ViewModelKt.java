package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0003*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo37405d2 = {"JOB_KEY", "", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "Landroidx/lifecycle/ViewModel;", "getViewModelScope", "(Landroidx/lifecycle/ViewModel;)Lkotlinx/coroutines/CoroutineScope;", "lifecycle-viewmodel-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: ViewModel.kt */
public final class ViewModelKt {
    private static final String JOB_KEY = "androidx.lifecycle.ViewModelCoroutineScope.JOB_KEY";

    @NotNull
    public static final CoroutineScope getViewModelScope(@NotNull ViewModel viewModel) {
        Intrinsics.checkParameterIsNotNull(viewModel, "$this$viewModelScope");
        String str = JOB_KEY;
        CoroutineScope coroutineScope = (CoroutineScope) viewModel.getTag(str);
        if (coroutineScope != null) {
            return coroutineScope;
        }
        Object tagIfAbsent = viewModel.setTagIfAbsent(str, new CloseableCoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain())));
        Intrinsics.checkExpressionValueIsNotNull(tagIfAbsent, "setTagIfAbsent(JOB_KEY,\n…ob() + Dispatchers.Main))");
        return (CoroutineScope) tagIfAbsent;
    }
}
