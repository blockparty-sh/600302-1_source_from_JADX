package com.bitcoin.mwallet.core.views;

import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.view.View;
import android.widget.FrameLayout;
import com.bitcoin.mwallet.C1018R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "dialog", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "onShow"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: RoundedBottomSheetDialogFragment.kt */
final class FullScreenRoundedBottomSheetDialogFragment$onViewCreated$1 implements OnShowListener {
    final /* synthetic */ FullScreenRoundedBottomSheetDialogFragment this$0;

    FullScreenRoundedBottomSheetDialogFragment$onViewCreated$1(FullScreenRoundedBottomSheetDialogFragment fullScreenRoundedBottomSheetDialogFragment) {
        this.this$0 = fullScreenRoundedBottomSheetDialogFragment;
    }

    public final void onShow(DialogInterface dialogInterface) {
        if (dialogInterface != null) {
            View findViewById = ((BottomSheetDialog) dialogInterface).findViewById(C1018R.C1021id.design_bottom_sheet);
            if (findViewById != null) {
                FrameLayout frameLayout = (FrameLayout) findViewById;
                frameLayout.getLayoutParams();
                frameLayout.getLayoutParams().height = -1;
                BottomSheetBehavior from = BottomSheetBehavior.from(frameLayout);
                Intrinsics.checkExpressionValueIsNotNull(from, "bottomSheetBehavior");
                from.setSkipCollapsed(true);
                from.setBottomSheetCallback(new BottomSheetCallback(this) {
                    final /* synthetic */ FullScreenRoundedBottomSheetDialogFragment$onViewCreated$1 this$0;

                    public void onSlide(@NotNull View view, float f) {
                        Intrinsics.checkParameterIsNotNull(view, "p0");
                    }

                    {
                        this.this$0 = r1;
                    }

                    public void onStateChanged(@NotNull View view, int i) {
                        Intrinsics.checkParameterIsNotNull(view, "view");
                        if (i == 5 || i == 4) {
                            this.this$0.this$0.dismiss();
                        }
                    }
                });
                from.setState(3);
                frameLayout.requestLayout();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.widget.FrameLayout");
        }
        throw new TypeCastException("null cannot be cast to non-null type com.google.android.material.bottomsheet.BottomSheetDialog");
    }
}
