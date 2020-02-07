package androidx.navigation.p005ui;

import androidx.navigation.p005ui.AppBarConfiguration.OnNavigateUpListener;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* renamed from: androidx.navigation.ui.AppBarConfigurationKt$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0 */
/* compiled from: AppBarConfiguration.kt */
public final class C0503x56421ee5 implements OnNavigateUpListener {
    private final /* synthetic */ Function0 function;

    public C0503x56421ee5(Function0 function0) {
        this.function = function0;
    }

    public final /* synthetic */ boolean onNavigateUp() {
        Object invoke = this.function.invoke();
        Intrinsics.checkExpressionValueIsNotNull(invoke, "invoke(...)");
        return ((Boolean) invoke).booleanValue();
    }
}
