package com.bitcoin.mwallet.app.flows.onboarding.createuser;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwner;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.PresenterWithCloseOnBackHandler;
import com.bitcoin.mwallet.app.viper.ScreenView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0006J\u0012\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/createuser/CreateUserView;", "Lcom/bitcoin/mwallet/app/viper/ScreenView;", "Lcom/bitcoin/mwallet/app/flows/onboarding/createuser/CreateUserBuilder;", "Lcom/bitcoin/mwallet/app/flows/onboarding/createuser/CreateUserPresenter;", "()V", "bindButtonActions", "", "bindDataObservers", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CreateUserView.kt */
public final class CreateUserView extends ScreenView<CreateUserBuilder, CreateUserPresenter> {
    private HashMap _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public CreateUserView() {
        super(C1018R.layout.fragment_screen_onboarding_createuser, Reflection.getOrCreateKotlinClass(CreateUserBuilder.class));
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        bindDataObservers();
        bindButtonActions();
        setUpCloseOnBackHandler((PresenterWithCloseOnBackHandler) getPresenter());
    }

    public final void bindDataObservers() {
        ((CreateUserPresenter) getPresenter()).getUsers().observe(getViewLifecycleOwner(), new CreateUserView$bindDataObservers$1(this));
        LifecycleOwner lifecycleOwner = this;
        ((CreateUserPresenter) getPresenter()).getDisplayZionCreateAndRestore().observe(lifecycleOwner, new CreateUserView$bindDataObservers$2(this));
        View view = getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.loadingMessage);
            if (textView != null) {
                textView.setText(getText(C1018R.string.createuser_creating_wallet));
            }
        }
        ((CreateUserPresenter) getPresenter()).getSearchingZion().observe(lifecycleOwner, new CreateUserView$bindDataObservers$3(this));
    }

    public final void bindButtonActions() {
        View view = getView();
        if (view != null) {
            LinearLayout linearLayout = (LinearLayout) view.findViewById(C1018R.C1021id.loadingStatus);
            if (linearLayout != null) {
                linearLayout.setVisibility(0);
            }
        }
        View view2 = getView();
        if (view2 != null) {
            TextView textView = (TextView) view2.findViewById(C1018R.C1021id.restoreWalletButton);
            if (textView != null) {
                textView.setOnClickListener(new CreateUserView$bindButtonActions$1(this));
            }
        }
        View view3 = getView();
        if (view3 != null) {
            Button button = (Button) view3.findViewById(C1018R.C1021id.createZionWalletButton);
            if (button != null) {
                button.setOnClickListener(new CreateUserView$bindButtonActions$2(this));
            }
        }
    }
}
