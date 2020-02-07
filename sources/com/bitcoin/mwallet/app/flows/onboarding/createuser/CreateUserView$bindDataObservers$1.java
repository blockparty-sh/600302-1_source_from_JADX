package com.bitcoin.mwallet.app.flows.onboarding.createuser;

import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.core.models.user.User;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "user", "Lcom/bitcoin/mwallet/core/models/user/User;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: CreateUserView.kt */
final class CreateUserView$bindDataObservers$1<T> implements Observer<User> {
    final /* synthetic */ CreateUserView this$0;

    CreateUserView$bindDataObservers$1(CreateUserView createUserView) {
        this.this$0 = createUserView;
    }

    public final void onChanged(User user) {
        if (user != null) {
            ((CreateUserPresenter) this.this$0.getPresenter()).goToHome();
        }
    }
}
