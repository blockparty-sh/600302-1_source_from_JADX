package androidx.navigation;

import android.app.Activity;
import androidx.annotation.IdRes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, mo37405d2 = {"findNavController", "Landroidx/navigation/NavController;", "Landroid/app/Activity;", "viewId", "", "navigation-runtime-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: Activity.kt */
public final class ActivityKt {
    @NotNull
    public static final NavController findNavController(@NotNull Activity activity, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(activity, "$this$findNavController");
        NavController findNavController = Navigation.findNavController(activity, i);
        Intrinsics.checkExpressionValueIsNotNull(findNavController, "Navigation.findNavController(this, viewId)");
        return findNavController;
    }
}
