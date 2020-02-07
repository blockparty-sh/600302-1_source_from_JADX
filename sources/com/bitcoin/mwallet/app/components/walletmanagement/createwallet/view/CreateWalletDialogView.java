package com.bitcoin.mwallet.app.components.walletmanagement.createwallet.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import com.airbnb.lottie.LottieAnimationView;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.walletmanagement.createwallet.builder.CreateWalletBuilder;
import com.bitcoin.mwallet.app.components.walletmanagement.createwallet.presenter.CreateWalletPresenter;
import com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.views.FullScreenRoundedBottomSheetDialogFragment;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0014\u001a\u00020\u0015J&\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u001a\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0006\u0010 \u001a\u00020\u0015R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u0004R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006!"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletmanagement/createwallet/view/CreateWalletDialogView;", "Lcom/bitcoin/mwallet/core/views/FullScreenRoundedBottomSheetDialogFragment;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "(Lcom/bitcoin/mwallet/core/models/Coin;)V", "builder", "Lcom/bitcoin/mwallet/app/components/walletmanagement/createwallet/builder/CreateWalletBuilder;", "getBuilder", "()Lcom/bitcoin/mwallet/app/components/walletmanagement/createwallet/builder/CreateWalletBuilder;", "setBuilder", "(Lcom/bitcoin/mwallet/app/components/walletmanagement/createwallet/builder/CreateWalletBuilder;)V", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "setCoin", "presenter", "Lcom/bitcoin/mwallet/app/components/walletmanagement/createwallet/presenter/CreateWalletPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/walletmanagement/createwallet/presenter/CreateWalletPresenter;", "setPresenter", "(Lcom/bitcoin/mwallet/app/components/walletmanagement/createwallet/presenter/CreateWalletPresenter;)V", "hideLoading", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "showLoading", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CreateWalletDialogView.kt */
public final class CreateWalletDialogView extends FullScreenRoundedBottomSheetDialogFragment {
    private HashMap _$_findViewCache;
    @Nullable
    private CreateWalletBuilder builder;
    @Nullable
    private Coin coin;
    @Nullable
    private CreateWalletPresenter presenter;

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[CreateWalletResult.values().length];

        static {
            $EnumSwitchMapping$0[CreateWalletResult.SUCCESS.ordinal()] = 1;
            $EnumSwitchMapping$0[CreateWalletResult.FAILED_ZION_MAX.ordinal()] = 2;
        }
    }

    public CreateWalletDialogView() {
        this(null, 1, null);
    }

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

    public /* synthetic */ CreateWalletDialogView(Coin coin2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            coin2 = null;
        }
        this(coin2);
    }

    @Nullable
    public final Coin getCoin() {
        return this.coin;
    }

    public final void setCoin(@Nullable Coin coin2) {
        this.coin = coin2;
    }

    public CreateWalletDialogView(@Nullable Coin coin2) {
        this.coin = coin2;
    }

    @Nullable
    public final CreateWalletBuilder getBuilder() {
        return this.builder;
    }

    public final void setBuilder(@Nullable CreateWalletBuilder createWalletBuilder) {
        this.builder = createWalletBuilder;
    }

    @Nullable
    public final CreateWalletPresenter getPresenter() {
        return this.presenter;
    }

    public final void setPresenter(@Nullable CreateWalletPresenter createWalletPresenter) {
        this.presenter = createWalletPresenter;
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        CreateWalletPresenter createWalletPresenter = null;
        View inflate = getLayoutInflater().inflate(C1018R.layout.fragment_component_createwallet, null);
        this.builder = (CreateWalletBuilder) ViewModelProviders.m16of((Fragment) this).get(CreateWalletBuilder.class);
        CreateWalletBuilder createWalletBuilder = this.builder;
        if (createWalletBuilder != null) {
            createWalletPresenter = createWalletBuilder.getPresenter();
        }
        this.presenter = createWalletPresenter;
        RadioButton radioButton = (RadioButton) inflate.findViewById(C1018R.C1021id.bitcoinCashSelectionButton);
        RadioButton radioButton2 = (RadioButton) inflate.findViewById(C1018R.C1021id.bitcoinSelectionButton);
        CreateWalletPresenter createWalletPresenter2 = this.presenter;
        if (createWalletPresenter2 != null) {
            LiveData coin2 = createWalletPresenter2.getCoin();
            if (coin2 != null) {
                coin2.observe(this, new CreateWalletDialogView$onCreateView$1(radioButton, radioButton2));
            }
        }
        CreateWalletPresenter createWalletPresenter3 = this.presenter;
        if (createWalletPresenter3 != null) {
            Coin coin3 = this.coin;
            if (coin3 == null) {
                coin3 = Coin.BCH;
            } else if (coin3 == null) {
                Intrinsics.throwNpe();
            }
            createWalletPresenter3.onCoinSelected(coin3);
        }
        CreateWalletPresenter createWalletPresenter4 = this.presenter;
        if (createWalletPresenter4 != null) {
            LiveData processResult = createWalletPresenter4.getProcessResult();
            if (processResult != null) {
                processResult.observe(getViewLifecycleOwner(), new CreateWalletDialogView$onCreateView$2(this));
            }
        }
        return inflate;
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        ((RadioButton) view.findViewById(C1018R.C1021id.bitcoinCashSelectionButton)).setOnCheckedChangeListener(new CreateWalletDialogView$onViewCreated$1(this));
        ((RadioButton) view.findViewById(C1018R.C1021id.bitcoinSelectionButton)).setOnCheckedChangeListener(new CreateWalletDialogView$onViewCreated$2(this));
        TextView textView = (TextView) view.findViewById(C1018R.C1021id.createWalletButton);
        if (textView != null) {
            textView.setOnClickListener(new CreateWalletDialogView$onViewCreated$3(this, view));
        }
        EditText editText = (EditText) view.findViewById(C1018R.C1021id.newWalletNameEditText);
        Intrinsics.checkExpressionValueIsNotNull(editText, "name");
        editText.addTextChangedListener(new C1085x558a0e02(view));
        editText.requestFocus();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                window.setSoftInputMode(16);
            }
        }
    }

    public final void hideLoading() {
        View view = getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.createWalletButton);
            if (textView != null) {
                textView.setVisibility(0);
            }
        }
        View view2 = getView();
        if (view2 != null) {
            LottieAnimationView lottieAnimationView = (LottieAnimationView) view2.findViewById(C1018R.C1021id.loadingAnimation);
            if (lottieAnimationView != null) {
                lottieAnimationView.setVisibility(8);
            }
        }
    }

    public final void showLoading() {
        View view = getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.createWalletButton);
            if (textView != null) {
                textView.setVisibility(8);
            }
        }
        View view2 = getView();
        if (view2 != null) {
            LottieAnimationView lottieAnimationView = (LottieAnimationView) view2.findViewById(C1018R.C1021id.loadingAnimation);
            if (lottieAnimationView != null) {
                lottieAnimationView.setVisibility(0);
            }
        }
    }
}
