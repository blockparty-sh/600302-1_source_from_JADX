package com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.BiometricPrompt.AuthenticationCallback;
import androidx.biometric.BiometricPrompt.PromptInfo;
import androidx.biometric.BiometricPrompt.PromptInfo.Builder;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.Bip70PaymentInfoRequestPresenter.PaymentState;
import com.bitcoin.mwallet.core.extensions.BigDecimalKt;
import com.bitcoin.mwallet.core.models.BitcoinUriContent;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentInfo;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.views.RoundedBottomSheetDialogFragment;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import com.ncorti.slidetoact.SlideToActView;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0017\u001a\u00020\u0018J\u0012\u0010\u0019\u001a\u00020\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J&\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\"\u001a\u00020\u0018H\u0002J\u0006\u0010#\u001a\u00020\u0018J\u000e\u0010$\u001a\u00020\u00182\u0006\u0010%\u001a\u00020&J\b\u0010'\u001a\u00020\u0018H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006("}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/Bip70PaymentInfoRequestView;", "Lcom/bitcoin/mwallet/core/views/RoundedBottomSheetDialogFragment;", "bitcoinUriContent", "Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "listener", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/OnPaymentSuccessListener;", "preferredWalletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "(Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/OnPaymentSuccessListener;Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "getBitcoinUriContent", "()Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "executor", "Ljava/util/concurrent/Executor;", "getListener", "()Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/OnPaymentSuccessListener;", "getPreferredWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "presenter", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/Bip70PaymentInfoRequestPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/Bip70PaymentInfoRequestPresenter;", "setPresenter", "(Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/Bip70PaymentInfoRequestPresenter;)V", "bindData", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "setMerchantInformation", "setNoWalletInformation", "setPaymentStateView", "paymentState", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/Bip70PaymentInfoRequestPresenter$PaymentState;", "showBiometricPrompt", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Bip70PaymentInfoRequestView.kt */
public final class Bip70PaymentInfoRequestView extends RoundedBottomSheetDialogFragment {
    private HashMap _$_findViewCache;
    @NotNull
    private final BitcoinUriContent bitcoinUriContent;
    private final Executor executor;
    @NotNull
    private final OnPaymentSuccessListener listener;
    @Nullable
    private final WalletId preferredWalletId;
    @NotNull
    public Bip70PaymentInfoRequestPresenter presenter;

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

    @NotNull
    public final BitcoinUriContent getBitcoinUriContent() {
        return this.bitcoinUriContent;
    }

    @NotNull
    public final OnPaymentSuccessListener getListener() {
        return this.listener;
    }

    public /* synthetic */ Bip70PaymentInfoRequestView(BitcoinUriContent bitcoinUriContent2, OnPaymentSuccessListener onPaymentSuccessListener, WalletId walletId, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 4) != 0) {
            walletId = null;
        }
        this(bitcoinUriContent2, onPaymentSuccessListener, walletId);
    }

    @Nullable
    public final WalletId getPreferredWalletId() {
        return this.preferredWalletId;
    }

    public Bip70PaymentInfoRequestView(@NotNull BitcoinUriContent bitcoinUriContent2, @NotNull OnPaymentSuccessListener onPaymentSuccessListener, @Nullable WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(bitcoinUriContent2, "bitcoinUriContent");
        Intrinsics.checkParameterIsNotNull(onPaymentSuccessListener, CastExtraArgs.LISTENER);
        this.bitcoinUriContent = bitcoinUriContent2;
        this.listener = onPaymentSuccessListener;
        this.preferredWalletId = walletId;
        this.executor = Bip70PaymentInfoRequestView$executor$1.INSTANCE;
    }

    @NotNull
    public final Bip70PaymentInfoRequestPresenter getPresenter() {
        Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter = this.presenter;
        if (bip70PaymentInfoRequestPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return bip70PaymentInfoRequestPresenter;
    }

    public final void setPresenter(@NotNull Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter) {
        Intrinsics.checkParameterIsNotNull(bip70PaymentInfoRequestPresenter, "<set-?>");
        this.presenter = bip70PaymentInfoRequestPresenter;
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return getLayoutInflater().inflate(C1018R.layout.fragment_screen_sendv2_bip70requestdialog, null);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        ViewModel viewModel = ViewModelProviders.m16of((Fragment) this).get(Bip70PaymentInfoRequestBuilder.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…questBuilder::class.java)");
        this.presenter = ((Bip70PaymentInfoRequestBuilder) viewModel).getPresenter();
        Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter = this.presenter;
        if (bip70PaymentInfoRequestPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        bip70PaymentInfoRequestPresenter.init(this.bitcoinUriContent, this.listener, this.preferredWalletId);
        bindData();
    }

    /* access modifiers changed from: private */
    public final void setMerchantInformation() {
        View view = getView();
        String str = null;
        TextView textView = view != null ? (TextView) view.findViewById(C1018R.C1021id.merchantName) : null;
        View view2 = getView();
        TextView textView2 = view2 != null ? (TextView) view2.findViewById(C1018R.C1021id.merchantDescription) : null;
        Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter = this.presenter;
        String str2 = "presenter";
        if (bip70PaymentInfoRequestPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str2);
        }
        Bip70PaymentInfo paymentInfo = bip70PaymentInfoRequestPresenter.getPaymentInfo();
        CharSequence merchantName = paymentInfo != null ? paymentInfo.getMerchantName() : null;
        if (merchantName == null || merchantName.length() == 0) {
            if (textView != null) {
                textView.setVisibility(8);
            }
        } else if (textView != null) {
            Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter2 = this.presenter;
            if (bip70PaymentInfoRequestPresenter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str2);
            }
            Bip70PaymentInfo paymentInfo2 = bip70PaymentInfoRequestPresenter2.getPaymentInfo();
            textView.setText(paymentInfo2 != null ? paymentInfo2.getMerchantName() : null);
        }
        Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter3 = this.presenter;
        if (bip70PaymentInfoRequestPresenter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str2);
        }
        Bip70PaymentInfo paymentInfo3 = bip70PaymentInfoRequestPresenter3.getPaymentInfo();
        CharSequence itemDescription = paymentInfo3 != null ? paymentInfo3.getItemDescription() : null;
        if (itemDescription == null || itemDescription.length() == 0) {
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
        } else if (textView2 != null) {
            Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter4 = this.presenter;
            if (bip70PaymentInfoRequestPresenter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str2);
            }
            Bip70PaymentInfo paymentInfo4 = bip70PaymentInfoRequestPresenter4.getPaymentInfo();
            textView2.setText(paymentInfo4 != null ? paymentInfo4.getItemDescription() : null);
        }
        View view3 = getView();
        if (view3 != null) {
            TextView textView3 = (TextView) view3.findViewById(C1018R.C1021id.invoiceFiatAmount);
            if (textView3 != null) {
                Context context = getContext();
                if (context != null) {
                    Object[] objArr = new Object[2];
                    Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter5 = this.presenter;
                    if (bip70PaymentInfoRequestPresenter5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(str2);
                    }
                    objArr[0] = bip70PaymentInfoRequestPresenter5.getDefaultCurrency().getSymbol();
                    Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter6 = this.presenter;
                    if (bip70PaymentInfoRequestPresenter6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(str2);
                    }
                    BigDecimal invoiceFiatAmount = bip70PaymentInfoRequestPresenter6.getInvoiceFiatAmount();
                    if (invoiceFiatAmount != null) {
                        BigDecimal scale = invoiceFiatAmount.setScale(2, 4);
                        if (scale != null) {
                            str = BigDecimalKt.toPlainGroupedString(scale);
                        }
                    }
                    objArr[1] = str;
                    str = context.getString(C1018R.string.send_amount_wallet_balance, objArr);
                }
                textView3.setText(str);
            }
        }
        View view4 = getView();
        if (view4 != null) {
            TextView textView4 = (TextView) view4.findViewById(C1018R.C1021id.invoiceCoinAmount);
            if (textView4 != null) {
                Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter7 = this.presenter;
                if (bip70PaymentInfoRequestPresenter7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(str2);
                }
                textView4.setText(BigDecimalKt.toCoinString(bip70PaymentInfoRequestPresenter7.getInvoiceCoinAmount().getCoins(), String.valueOf(this.bitcoinUriContent.getCoin())));
            }
        }
        View view5 = getView();
        if (view5 != null) {
            TextView textView5 = (TextView) view5.findViewById(C1018R.C1021id.cancelButton);
            if (textView5 != null) {
                textView5.setOnClickListener(new Bip70PaymentInfoRequestView$setMerchantInformation$1(this));
            }
        }
        View view6 = getView();
        if (view6 != null) {
            LinearLayout linearLayout = (LinearLayout) view6.findViewById(C1018R.C1021id.walletSelectContainer);
            if (linearLayout != null) {
                linearLayout.setOnClickListener(new Bip70PaymentInfoRequestView$setMerchantInformation$2(this));
            }
        }
    }

    public final void setNoWalletInformation() {
        View view = getView();
        String str = null;
        String str2 = "presenter";
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.noWalletDescription);
            if (textView != null) {
                Object[] objArr = new Object[1];
                Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter = this.presenter;
                if (bip70PaymentInfoRequestPresenter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(str2);
                }
                Coin coin = bip70PaymentInfoRequestPresenter.getCoin();
                objArr[0] = coin != null ? coin.getTicker() : null;
                textView.setText(getString(C1018R.string.wallet_selector_empty_description, objArr));
            }
        }
        View view2 = getView();
        if (view2 != null) {
            TextView textView2 = (TextView) view2.findViewById(C1018R.C1021id.noWalletTitle);
            if (textView2 != null) {
                Object[] objArr2 = new Object[1];
                Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter2 = this.presenter;
                if (bip70PaymentInfoRequestPresenter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(str2);
                }
                Coin coin2 = bip70PaymentInfoRequestPresenter2.getCoin();
                objArr2[0] = coin2 != null ? coin2.getTicker() : null;
                textView2.setText(getString(C1018R.string.wallet_selector_empty_title, objArr2));
            }
        }
        View view3 = getView();
        if (view3 != null) {
            TextView textView3 = (TextView) view3.findViewById(C1018R.C1021id.addWalletButton);
            if (textView3 != null) {
                Object[] objArr3 = new Object[1];
                Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter3 = this.presenter;
                if (bip70PaymentInfoRequestPresenter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(str2);
                }
                Coin coin3 = bip70PaymentInfoRequestPresenter3.getCoin();
                if (coin3 != null) {
                    str = coin3.getTicker();
                }
                objArr3[0] = str;
                textView3.setText(getString(C1018R.string.wallet_selector_empty_add_button, objArr3));
            }
        }
        View view4 = getView();
        if (view4 != null) {
            TextView textView4 = (TextView) view4.findViewById(C1018R.C1021id.addWalletButton);
            if (textView4 != null) {
                textView4.setOnClickListener(new Bip70PaymentInfoRequestView$setNoWalletInformation$1(this));
            }
        }
    }

    public final void setPaymentStateView(@NotNull PaymentState paymentState) {
        Intrinsics.checkParameterIsNotNull(paymentState, "paymentState");
        PaymentState[] values = PaymentState.values();
        Collection arrayList = new ArrayList();
        int length = values.length;
        for (int i = 0; i < length; i++) {
            PaymentState paymentState2 = values[i];
            if (paymentState2 != paymentState) {
                arrayList.add(paymentState2);
            }
        }
        for (PaymentState paymentState3 : (List) arrayList) {
            View view = getView();
            if (view != null) {
                LinearLayout linearLayout = (LinearLayout) view.findViewById(paymentState3.getLayout());
                if (linearLayout != null) {
                    linearLayout.setVisibility(8);
                }
            }
        }
        if (paymentState == PaymentState.INVALID || paymentState == PaymentState.EXPIRED || paymentState == PaymentState.NO_WALLET) {
            View view2 = getView();
            if (view2 != null) {
                LinearLayout linearLayout2 = (LinearLayout) view2.findViewById(C1018R.C1021id.errorLayout);
                if (linearLayout2 != null) {
                    linearLayout2.setVisibility(0);
                }
            }
        } else {
            View view3 = getView();
            if (view3 != null) {
                LinearLayout linearLayout3 = (LinearLayout) view3.findViewById(C1018R.C1021id.errorLayout);
                if (linearLayout3 != null) {
                    linearLayout3.setVisibility(8);
                }
            }
        }
        if (paymentState == PaymentState.NO_WALLET) {
            setNoWalletInformation();
        }
        if (paymentState == PaymentState.BROADCAST_FAILED) {
            View view4 = getView();
            if (view4 != null) {
                SlideToActView slideToActView = (SlideToActView) view4.findViewById(C1018R.C1021id.sendButton);
                if (slideToActView != null) {
                    slideToActView.resetSlider();
                }
            }
            Context context = getContext();
            Context context2 = getContext();
            Toast.makeText(context, context2 != null ? context2.getString(C1018R.string.broadcast_general_fail) : null, 0).show();
        }
        View view5 = getView();
        if (view5 != null) {
            LinearLayout linearLayout4 = (LinearLayout) view5.findViewById(paymentState.getLayout());
            if (linearLayout4 != null) {
                linearLayout4.setVisibility(0);
            }
        }
    }

    public final void bindData() {
        View view = getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.closeButton);
            if (textView != null) {
                textView.setOnClickListener(new Bip70PaymentInfoRequestView$bindData$1(this));
            }
        }
        View view2 = getView();
        String str = "presenter";
        if (view2 != null) {
            SlideToActView slideToActView = (SlideToActView) view2.findViewById(C1018R.C1021id.sendButton);
            if (slideToActView != null) {
                Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter = this.presenter;
                if (bip70PaymentInfoRequestPresenter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(str);
                }
                slideToActView.setOnSlideCompleteListener(bip70PaymentInfoRequestPresenter);
            }
        }
        Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter2 = this.presenter;
        if (bip70PaymentInfoRequestPresenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
        }
        bip70PaymentInfoRequestPresenter2.getExpiryTimer().observe(getViewLifecycleOwner(), new Bip70PaymentInfoRequestView$bindData$2(this));
        Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter3 = this.presenter;
        if (bip70PaymentInfoRequestPresenter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
        }
        bip70PaymentInfoRequestPresenter3.getPaymentState().observe(getViewLifecycleOwner(), new Bip70PaymentInfoRequestView$bindData$3(this));
        Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter4 = this.presenter;
        if (bip70PaymentInfoRequestPresenter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
        }
        bip70PaymentInfoRequestPresenter4.getWalletBalance().observe(getViewLifecycleOwner(), new Bip70PaymentInfoRequestView$bindData$4(this));
        Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter5 = this.presenter;
        if (bip70PaymentInfoRequestPresenter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
        }
        bip70PaymentInfoRequestPresenter5.getValidationError().observe(getViewLifecycleOwner(), new Bip70PaymentInfoRequestView$bindData$5(this));
        Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter6 = this.presenter;
        if (bip70PaymentInfoRequestPresenter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
        }
        bip70PaymentInfoRequestPresenter6.getNeedsBiometricForSend().observe(getViewLifecycleOwner(), new Bip70PaymentInfoRequestView$bindData$6(this));
        Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter7 = this.presenter;
        if (bip70PaymentInfoRequestPresenter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
        }
        bip70PaymentInfoRequestPresenter7.getSelectedWalletId().observe(getViewLifecycleOwner(), new Bip70PaymentInfoRequestView$bindData$7(this));
    }

    /* access modifiers changed from: private */
    public final void showBiometricPrompt() {
        PromptInfo build = new Builder().setTitle("Authenticate to Send").setDeviceCredentialAllowed(true).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "BiometricPrompt.PromptIn…rue)\n            .build()");
        new BiometricPrompt((Fragment) this, this.executor, (AuthenticationCallback) new C1100x1c859242(this)).authenticate(build);
    }
}
