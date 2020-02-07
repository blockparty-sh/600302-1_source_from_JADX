package com.bitcoin.mwallet.app.components.pinpad;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.ComponentView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u0012\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/pinpad/pinpadView;", "Lcom/bitcoin/mwallet/app/viper/ComponentView;", "Lcom/bitcoin/mwallet/app/components/pinpad/pinpadBuilder;", "Lcom/bitcoin/mwallet/app/components/pinpad/pinpadPresenter;", "()V", "listener", "Lcom/bitcoin/mwallet/app/components/pinpad/OnPinPadItemClickedListener;", "bindPinPadButtons", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "setPinpadListener", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: pinpadView.kt */
public final class pinpadView extends ComponentView<pinpadBuilder, pinpadPresenter> {
    private HashMap _$_findViewCache;
    private OnPinPadItemClickedListener listener;

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

    public static final /* synthetic */ pinpadPresenter access$getPresenter$p(pinpadView pinpadview) {
        return (pinpadPresenter) pinpadview.getPresenter();
    }

    public pinpadView() {
        super(C1018R.layout.fragment_component_pinpad, Reflection.getOrCreateKotlinClass(pinpadBuilder.class));
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        ((pinpadPresenter) getPresenter()).setListener(this.listener);
        bindPinPadButtons();
    }

    public final void setPinpadListener(@NotNull OnPinPadItemClickedListener onPinPadItemClickedListener) {
        Intrinsics.checkParameterIsNotNull(onPinPadItemClickedListener, CastExtraArgs.LISTENER);
        this.listener = onPinPadItemClickedListener;
    }

    public final void bindPinPadButtons() {
        View view = getView();
        if (view != null) {
            Button button = (Button) view.findViewById(C1018R.C1021id.pinPadButton0);
            if (button != null) {
                button.setOnClickListener(new pinpadView$bindPinPadButtons$1(this));
            }
        }
        View view2 = getView();
        if (view2 != null) {
            Button button2 = (Button) view2.findViewById(C1018R.C1021id.pinPadButton1);
            if (button2 != null) {
                button2.setOnClickListener(new pinpadView$bindPinPadButtons$2(this));
            }
        }
        View view3 = getView();
        if (view3 != null) {
            Button button3 = (Button) view3.findViewById(C1018R.C1021id.pinPadButton2);
            if (button3 != null) {
                button3.setOnClickListener(new pinpadView$bindPinPadButtons$3(this));
            }
        }
        View view4 = getView();
        if (view4 != null) {
            Button button4 = (Button) view4.findViewById(C1018R.C1021id.pinPadButton3);
            if (button4 != null) {
                button4.setOnClickListener(new pinpadView$bindPinPadButtons$4(this));
            }
        }
        View view5 = getView();
        if (view5 != null) {
            Button button5 = (Button) view5.findViewById(C1018R.C1021id.pinPadButton4);
            if (button5 != null) {
                button5.setOnClickListener(new pinpadView$bindPinPadButtons$5(this));
            }
        }
        View view6 = getView();
        if (view6 != null) {
            Button button6 = (Button) view6.findViewById(C1018R.C1021id.pinPadButton5);
            if (button6 != null) {
                button6.setOnClickListener(new pinpadView$bindPinPadButtons$6(this));
            }
        }
        View view7 = getView();
        if (view7 != null) {
            Button button7 = (Button) view7.findViewById(C1018R.C1021id.pinPadButton6);
            if (button7 != null) {
                button7.setOnClickListener(new pinpadView$bindPinPadButtons$7(this));
            }
        }
        View view8 = getView();
        if (view8 != null) {
            Button button8 = (Button) view8.findViewById(C1018R.C1021id.pinPadButton7);
            if (button8 != null) {
                button8.setOnClickListener(new pinpadView$bindPinPadButtons$8(this));
            }
        }
        View view9 = getView();
        if (view9 != null) {
            Button button9 = (Button) view9.findViewById(C1018R.C1021id.pinPadButton8);
            if (button9 != null) {
                button9.setOnClickListener(new pinpadView$bindPinPadButtons$9(this));
            }
        }
        View view10 = getView();
        if (view10 != null) {
            Button button10 = (Button) view10.findViewById(C1018R.C1021id.pinPadButton9);
            if (button10 != null) {
                button10.setOnClickListener(new pinpadView$bindPinPadButtons$10(this));
            }
        }
        View view11 = getView();
        if (view11 != null) {
            Button button11 = (Button) view11.findViewById(C1018R.C1021id.pinPadButtonDecimal);
            if (button11 != null) {
                DecimalFormatSymbols decimalFormatSymbols = new DecimalFormat().getDecimalFormatSymbols();
                Intrinsics.checkExpressionValueIsNotNull(decimalFormatSymbols, "DecimalFormat().decimalFormatSymbols");
                button11.setText(String.valueOf(decimalFormatSymbols.getDecimalSeparator()));
            }
        }
        View view12 = getView();
        if (view12 != null) {
            Button button12 = (Button) view12.findViewById(C1018R.C1021id.pinPadButtonDecimal);
            if (button12 != null) {
                button12.setOnClickListener(new pinpadView$bindPinPadButtons$11(this));
            }
        }
        View view13 = getView();
        if (view13 != null) {
            ImageButton imageButton = (ImageButton) view13.findViewById(C1018R.C1021id.pinPadButtonBackspace);
            if (imageButton != null) {
                imageButton.setOnClickListener(new pinpadView$bindPinPadButtons$12(this));
            }
        }
    }
}
