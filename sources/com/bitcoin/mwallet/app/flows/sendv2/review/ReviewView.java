package com.bitcoin.mwallet.app.flows.sendv2.review;

import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.BiometricPrompt.AuthenticationCallback;
import androidx.biometric.BiometricPrompt.PromptInfo;
import androidx.biometric.BiometricPrompt.PromptInfo.Builder;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavArgsLazy;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.customheader.CustomHeaderListener;
import com.bitcoin.mwallet.app.components.customheader.CustomHeaderView;
import com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendWhatModel;
import com.bitcoin.mwallet.app.viper.ScreenView;
import com.bitcoin.mwallet.app.viper.ScreenView.Companion;
import com.bitcoin.mwallet.core.extensions.BigDecimalKt;
import com.bitcoin.mwallet.core.models.BitcoinUriContent;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.address.AddressAndOriginalText;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.ncorti.slidetoact.SlideToActView;
import com.ncorti.slidetoact.SlideToActView.OnSlideCompleteListener;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0011J\u0006\u0010\u0013\u001a\u00020\u0011J\u0006\u0010\u0014\u001a\u00020\u0015J\u0012\u0010\u0016\u001a\u00020\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0011H\u0016J\b\u0010\u001a\u001a\u00020\u0011H\u0016J\b\u0010\u001b\u001a\u00020\u0011H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u0006\u001c"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/review/ReviewView;", "Lcom/bitcoin/mwallet/app/viper/ScreenView;", "Lcom/bitcoin/mwallet/app/flows/sendv2/review/ReviewBuilder;", "Lcom/bitcoin/mwallet/app/flows/sendv2/review/ReviewPresenter;", "Lcom/bitcoin/mwallet/app/components/customheader/CustomHeaderListener;", "()V", "executor", "Ljava/util/concurrent/Executor;", "handler", "Landroid/os/Handler;", "navArgs", "Lcom/bitcoin/mwallet/app/flows/sendv2/review/ReviewViewArgs;", "getNavArgs", "()Lcom/bitcoin/mwallet/app/flows/sendv2/review/ReviewViewArgs;", "navArgs$delegate", "Landroidx/navigation/NavArgsLazy;", "bindButtonActions", "", "bindInitialArgs", "bindObservers", "customizeAddressText", "Landroid/text/SpannableStringBuilder;", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onClosePressed", "onLeftButtonPressed", "showBiometricPrompt", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReviewView.kt */
public final class ReviewView extends ScreenView<ReviewBuilder, ReviewPresenter> implements CustomHeaderListener {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReviewView.class), "navArgs", "getNavArgs()Lcom/bitcoin/mwallet/app/flows/sendv2/review/ReviewViewArgs;"))};
    private HashMap _$_findViewCache;
    private final Executor executor = new ReviewView$executor$1(this);
    /* access modifiers changed from: private */
    public final Handler handler = new Handler();
    private final NavArgsLazy navArgs$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(ReviewViewArgs.class), new ReviewView$$special$$inlined$navArgs$1(this));

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Coin.values().length];

        static {
            $EnumSwitchMapping$0[Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$0[Coin.BTC.ordinal()] = 2;
        }
    }

    private final ReviewViewArgs getNavArgs() {
        Lazy lazy = this.navArgs$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (ReviewViewArgs) lazy.getValue();
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

    public ReviewView() {
        super(C1018R.layout.fragment_screen_sendv2_review, Reflection.getOrCreateKotlinClass(ReviewBuilder.class));
    }

    public void onLeftButtonPressed() {
        ((ReviewPresenter) getPresenter()).onBackPressed();
    }

    public void onClosePressed() {
        ReviewPresenter reviewPresenter = (ReviewPresenter) getPresenter();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            reviewPresenter.onClosePressed(activity);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        Object obj;
        super.onActivityCreated(bundle);
        bindInitialArgs();
        bindButtonActions();
        bindObservers();
        Companion companion = ScreenView.Companion;
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(childFragmentManager, "fragment.childFragmentManager");
        List fragments = childFragmentManager.getFragments();
        Intrinsics.checkExpressionValueIsNotNull(fragments, "fragment.childFragmentManager.fragments");
        Iterator it = fragments.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((Fragment) obj) instanceof CustomHeaderView) {
                break;
            }
        }
        if (!(obj instanceof CustomHeaderView)) {
            obj = null;
        }
        CustomHeaderView customHeaderView = (CustomHeaderView) obj;
        if (customHeaderView != null) {
            String string = getResources().getString(C1018R.string.send_review_header);
            Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(R.string.send_review_header)");
            customHeaderView.setHeaderText(string);
        }
    }

    public final void bindButtonActions() {
        View view = getView();
        if (view != null) {
            SlideToActView slideToActView = (SlideToActView) view.findViewById(C1018R.C1021id.sendButton);
            if (slideToActView != null) {
                slideToActView.setOnSlideCompleteListener((OnSlideCompleteListener) getPresenter());
            }
        }
        View view2 = getView();
        if (view2 != null) {
            LinearLayout linearLayout = (LinearLayout) view2.findViewById(C1018R.C1021id.walletSelectContainer);
            if (linearLayout != null) {
                linearLayout.setOnClickListener(new ReviewView$bindButtonActions$1(this));
            }
        }
    }

    public final void bindInitialArgs() {
        int i;
        SendWhatModel sendWhatModel = getNavArgs().getSendWhatModel();
        View view = getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.reviewErrorRetry);
            if (textView != null) {
                textView.setOnClickListener(new ReviewView$bindInitialArgs$1(this));
            }
        }
        ((ReviewPresenter) getPresenter()).initSendData(sendWhatModel);
        int i2 = WhenMappings.$EnumSwitchMapping$0[((ReviewPresenter) getPresenter()).getCoin().ordinal()];
        if (i2 == 1) {
            i = C1018R.C1020drawable.logo_bch_green;
        } else if (i2 == 2) {
            i = C1018R.C1020drawable.logo_btc;
        } else {
            throw new NoWhenBranchMatchedException();
        }
        String str = "coinTypeAddressLabel";
        if (((ReviewPresenter) getPresenter()).getTokenId() != null) {
            TextView textView2 = (TextView) _$_findCachedViewById(C1018R.C1021id.coinTypeAddressLabel);
            Intrinsics.checkExpressionValueIsNotNull(textView2, str);
            textView2.setText("simpleledger:");
        } else if (((ReviewPresenter) getPresenter()).getCoin() == Coin.BCH) {
            TextView textView3 = (TextView) _$_findCachedViewById(C1018R.C1021id.coinTypeAddressLabel);
            Intrinsics.checkExpressionValueIsNotNull(textView3, str);
            textView3.setText(BitcoinUriContent.PREFIX_BITCOIN_CASH);
        } else if (((ReviewPresenter) getPresenter()).getCoin() == Coin.BTC) {
            TextView textView4 = (TextView) _$_findCachedViewById(C1018R.C1021id.coinTypeAddressLabel);
            Intrinsics.checkExpressionValueIsNotNull(textView4, str);
            textView4.setText(BitcoinUriContent.PREFIX_BITCOIN);
        }
        View view2 = getView();
        String str2 = null;
        ImageView imageView = view2 != null ? (ImageView) view2.findViewById(C1018R.C1021id.coinLogo) : null;
        if (imageView != null) {
            imageView.setImageResource(i);
        }
        ((ReviewPresenter) getPresenter()).setDestinationAddress(getNavArgs().getSendWhatModel().getUri());
        View view3 = getView();
        if (view3 != null) {
            TextView textView5 = (TextView) view3.findViewById(C1018R.C1021id.destinationAddresssTextView);
            if (textView5 != null) {
                textView5.setText(customizeAddressText());
            }
        }
        if (((ReviewPresenter) getPresenter()).getTokenAmount() != null && ((ReviewPresenter) getPresenter()).getTokenId() != null) {
            View view4 = getView();
            if (view4 != null) {
                TextView textView6 = (TextView) view4.findViewById(C1018R.C1021id.primaryAmountTextView);
                if (textView6 != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(((ReviewPresenter) getPresenter()).getTokenAmount());
                    sb.append(' ');
                    Slp token = ((ReviewPresenter) getPresenter()).getToken();
                    if (token != null) {
                        str2 = token.getTicker();
                    }
                    sb.append(str2);
                    textView6.setText(sb.toString());
                }
            }
            View view5 = getView();
            if (view5 != null) {
                TextView textView7 = (TextView) view5.findViewById(C1018R.C1021id.secondaryAmountTextView);
                if (textView7 != null) {
                    textView7.setVisibility(4);
                }
            }
            if (imageView != null) {
                imageView.setVisibility(4);
            }
        } else if (((ReviewPresenter) getPresenter()).isSendingFiat()) {
            BuildersKt__BuildersKt.runBlocking$default(null, new ReviewView$bindInitialArgs$2(this, sendWhatModel, null), 1, null);
            View view6 = getView();
            if (view6 != null) {
                TextView textView8 = (TextView) view6.findViewById(C1018R.C1021id.primaryAmountTextView);
                if (textView8 != null) {
                    BigDecimal fiatAmount = getNavArgs().getSendWhatModel().getFiatAmount();
                    Currency currency = ((ReviewPresenter) getPresenter()).getCurrency();
                    Intrinsics.checkExpressionValueIsNotNull(currency, "presenter.currency");
                    textView8.setText(BigDecimalKt.toFiatString$default(fiatAmount, currency, false, 2, null));
                }
            }
        } else {
            BuildersKt__BuildersKt.runBlocking$default(null, new ReviewView$bindInitialArgs$3(this, sendWhatModel, null), 1, null);
            View view7 = getView();
            if (view7 != null) {
                TextView textView9 = (TextView) view7.findViewById(C1018R.C1021id.primaryAmountTextView);
                if (textView9 != null) {
                    BigDecimal cryptoAmount = getNavArgs().getSendWhatModel().getCryptoAmount();
                    if (cryptoAmount != null) {
                        str2 = BigDecimalKt.toCoinString(cryptoAmount, ((ReviewPresenter) getPresenter()).getCoin().getTicker());
                    }
                    textView9.setText(str2);
                }
            }
            if (imageView != null) {
                imageView.setVisibility(4);
            }
        }
    }

    @NotNull
    public final SpannableStringBuilder customizeAddressText() {
        AddressAndOriginalText address = ((ReviewPresenter) getPresenter()).getDestinationAddress().getAddress();
        Integer num = null;
        String originalText = address != null ? address.getOriginalText() : null;
        if (originalText != null) {
            num = Integer.valueOf(originalText.length());
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(originalText);
        StyleSpan styleSpan = new StyleSpan(1);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ViewCompat.MEASURED_STATE_MASK);
        StyleSpan styleSpan2 = new StyleSpan(1);
        ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(ViewCompat.MEASURED_STATE_MASK);
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, 5, 18);
        spannableStringBuilder.setSpan(styleSpan, 0, 5, 18);
        if (num != null) {
            spannableStringBuilder.setSpan(foregroundColorSpan2, num.intValue() - 5, num.intValue(), 18);
            spannableStringBuilder.setSpan(styleSpan2, num.intValue() - 5, num.intValue(), 18);
        }
        return spannableStringBuilder;
    }

    public final void bindObservers() {
        LifecycleOwner lifecycleOwner = this;
        ((ReviewPresenter) getPresenter()).getSecondaryAmountValue().observe(lifecycleOwner, new ReviewView$bindObservers$1(this));
        ((ReviewPresenter) getPresenter()).getWallet().observe(lifecycleOwner, new ReviewView$bindObservers$2(this));
        ((ReviewPresenter) getPresenter()).getBalance().observe(lifecycleOwner, new ReviewView$bindObservers$3(this));
        ((ReviewPresenter) getPresenter()).getNetworkFeeText().observe(lifecycleOwner, new ReviewView$bindObservers$4(this));
        ((ReviewPresenter) getPresenter()).getError().observe(lifecycleOwner, new ReviewView$bindObservers$5(this));
        ((ReviewPresenter) getPresenter()).getNeedsBiometricForSend().observe(getViewLifecycleOwner(), new ReviewView$bindObservers$6(this));
        ((ReviewPresenter) getPresenter()).isSendingToken().observe(lifecycleOwner, new ReviewView$bindObservers$7(this));
    }

    /* access modifiers changed from: private */
    public final void showBiometricPrompt() {
        PromptInfo build = new Builder().setTitle("Authenticate to Send").setDeviceCredentialAllowed(true).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "BiometricPrompt.PromptIn…rue)\n            .build()");
        new BiometricPrompt((Fragment) this, this.executor, (AuthenticationCallback) new ReviewView$showBiometricPrompt$biometricPrompt$1(this)).authenticate(build);
    }
}
