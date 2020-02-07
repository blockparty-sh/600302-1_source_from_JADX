package com.bitcoin.mwallet.app.flows.onboarding.createuser;

import android.view.View;
import android.view.View.OnClickListener;
import androidx.fragment.app.FragmentManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: CreateUserView.kt */
final class CreateUserView$bindButtonActions$1 implements OnClickListener {
    final /* synthetic */ CreateUserView this$0;

    CreateUserView$bindButtonActions$1(CreateUserView createUserView) {
        this.this$0 = createUserView;
    }

    public final void onClick(View view) {
        CreateUserPresenter createUserPresenter = (CreateUserPresenter) this.this$0.getPresenter();
        FragmentManager childFragmentManager = this.this$0.getChildFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(childFragmentManager, "childFragmentManager");
        createUserPresenter.toImportExistingWallet(childFragmentManager);
    }
}
