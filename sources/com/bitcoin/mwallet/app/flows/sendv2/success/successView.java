package com.bitcoin.mwallet.app.flows.sendv2.success;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.navigation.NavArgsLazy;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.PresenterWithFinishOnBackHandler;
import com.bitcoin.mwallet.app.viper.ScreenView;
import com.bitcoin.mwallet.core.extensions.BigDecimalKt;
import com.bitcoin.mwallet.core.models.Coin;
import java.math.BigDecimal;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fJ\u0012\u0010\u000e\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/success/successView;", "Lcom/bitcoin/mwallet/app/viper/ScreenView;", "Lcom/bitcoin/mwallet/app/flows/sendv2/success/successBuilder;", "Lcom/bitcoin/mwallet/app/flows/sendv2/success/successPresenter;", "()V", "navArgs", "Lcom/bitcoin/mwallet/app/flows/sendv2/success/successViewArgs;", "getNavArgs", "()Lcom/bitcoin/mwallet/app/flows/sendv2/success/successViewArgs;", "navArgs$delegate", "Landroidx/navigation/NavArgsLazy;", "bindActions", "", "bindValues", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: successView.kt */
public final class successView extends ScreenView<successBuilder, successPresenter> {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(successView.class), "navArgs", "getNavArgs()Lcom/bitcoin/mwallet/app/flows/sendv2/success/successViewArgs;"))};
    private HashMap _$_findViewCache;
    private final NavArgsLazy navArgs$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(successViewArgs.class), new successView$$special$$inlined$navArgs$1(this));

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Coin.values().length];

        static {
            $EnumSwitchMapping$0[Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$0[Coin.BTC.ordinal()] = 2;
        }
    }

    private final successViewArgs getNavArgs() {
        Lazy lazy = this.navArgs$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (successViewArgs) lazy.getValue();
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

    public successView() {
        super(C1018R.layout.fragment_screen_sendv2_success, Reflection.getOrCreateKotlinClass(successBuilder.class));
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        setUpFinishOnBackHandler((PresenterWithFinishOnBackHandler) getPresenter());
        ((successPresenter) getPresenter()).setInitialArgs(getNavArgs());
        bindActions();
        bindValues();
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Context context = getContext();
        if (context != null) {
            context.setTheme(C1018R.C1026style.SentSuccessScreenTheme);
        }
    }

    public final void bindActions() {
        if (((successPresenter) getPresenter()).getMemo().length() > 0) {
            ((EditText) _$_findCachedViewById(C1018R.C1021id.memoEditText)).setText(((successPresenter) getPresenter()).getMemo());
        }
        View view = getView();
        if (view != null) {
            Button button = (Button) view.findViewById(C1018R.C1021id.finishButton);
            if (button != null) {
                button.setOnClickListener(new successView$bindActions$1(this));
            }
        }
        View view2 = getView();
        if (view2 != null) {
            LinearLayout linearLayout = (LinearLayout) view2.findViewById(C1018R.C1021id.viewDetailsButton);
            if (linearLayout != null) {
                linearLayout.setOnClickListener(new successView$bindActions$2(this));
            }
        }
        View view3 = getView();
        if (view3 != null) {
            LinearLayout linearLayout2 = (LinearLayout) view3.findViewById(C1018R.C1021id.shareButton);
            if (linearLayout2 != null) {
                linearLayout2.setOnClickListener(new successView$bindActions$3(this));
            }
        }
    }

    public final void bindValues() {
        int i;
        View view = getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.primarySendAmountTextView);
            if (textView != null) {
                textView.setText(((successPresenter) getPresenter()).getDisplayAmount());
            }
        }
        View view2 = getView();
        if (view2 != null) {
            TextView textView2 = (TextView) view2.findViewById(C1018R.C1021id.secondarySendAmountTextView);
            if (textView2 != null) {
                BigDecimal cryptoAmount = ((successPresenter) getPresenter()).getSendWhatData().getCryptoAmount();
                textView2.setText(cryptoAmount != null ? BigDecimalKt.toCoinString(cryptoAmount, ((successPresenter) getPresenter()).getSendWhatData().getCoin().getTicker()) : null);
            }
        }
        View view3 = getView();
        if (view3 != null) {
            LinearLayout linearLayout = (LinearLayout) view3.findViewById(C1018R.C1021id.secondaryPriceLayout);
            if (linearLayout != null) {
                linearLayout.setVisibility(((successPresenter) getPresenter()).getSendWhatData().getUri().isSlp() ? 4 : 0);
            }
        }
        int i2 = WhenMappings.$EnumSwitchMapping$0[((successPresenter) getPresenter()).getSendWhatData().getCoin().ordinal()];
        if (i2 == 1) {
            i = C1018R.C1020drawable.ic_qr_bch;
        } else if (i2 == 2) {
            i = C1018R.C1020drawable.ic_qr_btc;
        } else {
            throw new NoWhenBranchMatchedException();
        }
        View view4 = getView();
        if (view4 != null) {
            ImageView imageView = (ImageView) view4.findViewById(C1018R.C1021id.coinLogo);
            if (imageView != null) {
                imageView.setImageResource(i);
            }
        }
    }
}
