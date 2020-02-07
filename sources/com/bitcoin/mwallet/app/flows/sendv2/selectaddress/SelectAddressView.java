package com.bitcoin.mwallet.app.flows.sendv2.selectaddress;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentActivity;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.SendV2Activity;
import com.bitcoin.mwallet.app.viper.ScreenView;
import com.bitcoin.mwallet.core.models.BitcoinUriContent;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u0006\u0010\u0011\u001a\u00020\u000fJ\b\u0010\u0012\u001a\u00020\u000fH\u0002J\b\u0010\u0013\u001a\u00020\u000fH\u0002J\u0012\u0010\u0014\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\"\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u000fH\u0016J\b\u0010\u001e\u001a\u00020\u000fH\u0016J\b\u0010\u001f\u001a\u00020\u000fH\u0002R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\n¨\u0006 "}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/selectaddress/SelectAddressView;", "Lcom/bitcoin/mwallet/app/viper/ScreenView;", "Lcom/bitcoin/mwallet/app/flows/sendv2/selectaddress/SelectAddressBuilder;", "Lcom/bitcoin/mwallet/app/flows/sendv2/selectaddress/SelectAddressPresenter;", "()V", "prevAddress", "", "getPrevAddress", "()Ljava/lang/String;", "setPrevAddress", "(Ljava/lang/String;)V", "prevUrl", "getPrevUrl", "setPrevUrl", "bindButtons", "", "bindEditText", "bindObservers", "disableContinue", "enableContinue", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onPause", "onResume", "viberate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectAddressView.kt */
public final class SelectAddressView extends ScreenView<SelectAddressBuilder, SelectAddressPresenter> {
    private HashMap _$_findViewCache;
    @Nullable
    private String prevAddress;
    @Nullable
    private String prevUrl;

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

    public SelectAddressView() {
        super(C1018R.layout.fragment_screen_sendv2_selectaddress, Reflection.getOrCreateKotlinClass(SelectAddressBuilder.class));
    }

    @Nullable
    public final String getPrevAddress() {
        return this.prevAddress;
    }

    public final void setPrevAddress(@Nullable String str) {
        this.prevAddress = str;
    }

    @Nullable
    public final String getPrevUrl() {
        return this.prevUrl;
    }

    public final void setPrevUrl(@Nullable String str) {
        this.prevUrl = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityCreated(@org.jetbrains.annotations.Nullable android.os.Bundle r4) {
        /*
            r3 = this;
            super.onActivityCreated(r4)
            com.bitcoin.mwallet.app.viper.ScreenPresenter r4 = r3.getPresenter()
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r4 = (com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter) r4
            androidx.fragment.app.FragmentActivity r0 = r3.getActivity()
            r1 = 0
            if (r0 == 0) goto L_0x001d
            android.content.Intent r0 = r0.getIntent()
            if (r0 == 0) goto L_0x001d
            java.lang.String r2 = "wallet_id"
            java.io.Serializable r0 = r0.getSerializableExtra(r2)
            goto L_0x001e
        L_0x001d:
            r0 = r1
        L_0x001e:
            boolean r2 = r0 instanceof com.bitcoin.mwallet.core.models.wallet.WalletId
            if (r2 != 0) goto L_0x0023
            r0 = r1
        L_0x0023:
            com.bitcoin.mwallet.core.models.wallet.WalletId r0 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r0
            r4.setPreferredWalletId(r0)
            com.bitcoin.mwallet.app.viper.ScreenPresenter r4 = r3.getPresenter()
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r4 = (com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter) r4
            androidx.fragment.app.FragmentActivity r0 = r3.getActivity()
            if (r0 == 0) goto L_0x0041
            android.content.Intent r0 = r0.getIntent()
            if (r0 == 0) goto L_0x0041
            java.lang.String r2 = "token_id"
            java.io.Serializable r0 = r0.getSerializableExtra(r2)
            goto L_0x0042
        L_0x0041:
            r0 = r1
        L_0x0042:
            boolean r2 = r0 instanceof com.bitcoin.mwallet.core.models.slp.SlpId
            if (r2 != 0) goto L_0x0047
            r0 = r1
        L_0x0047:
            com.bitcoin.mwallet.core.models.slp.SlpId r0 = (com.bitcoin.mwallet.core.models.slp.SlpId) r0
            r4.setPreferredTokenId(r0)
            com.bitcoin.mwallet.app.viper.ScreenPresenter r4 = r3.getPresenter()
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r4 = (com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter) r4
            androidx.fragment.app.FragmentManager r0 = r3.getChildFragmentManager()
            r4.setViewFragmentManager(r0)
            android.content.Context r4 = r3.getContext()
            if (r4 != 0) goto L_0x0062
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0062:
            java.lang.String r0 = "android.permission.CAMERA"
            int r4 = androidx.core.content.ContextCompat.checkSelfPermission(r4, r0)
            if (r4 == 0) goto L_0x007d
            androidx.fragment.app.FragmentActivity r4 = r3.getActivity()
            if (r4 != 0) goto L_0x0073
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0073:
            android.app.Activity r4 = (android.app.Activity) r4
            java.lang.String[] r0 = new java.lang.String[]{r0}
            r2 = 1
            androidx.core.app.ActivityCompat.requestPermissions(r4, r0, r2)
        L_0x007d:
            android.view.View r4 = r3.getView()
            if (r4 == 0) goto L_0x008d
            r0 = 2131362813(0x7f0a03fd, float:1.8345417E38)
            android.view.View r4 = r4.findViewById(r0)
            r1 = r4
            com.journeyapps.barcodescanner.DecoratedBarcodeView r1 = (com.journeyapps.barcodescanner.DecoratedBarcodeView) r1
        L_0x008d:
            if (r1 == 0) goto L_0x009f
            com.journeyapps.barcodescanner.BarcodeView r4 = r1.getBarcodeView()
            if (r4 == 0) goto L_0x009f
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressView$onActivityCreated$1 r0 = new com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressView$onActivityCreated$1
            r0.<init>(r3)
            com.journeyapps.barcodescanner.BarcodeCallback r0 = (com.journeyapps.barcodescanner.BarcodeCallback) r0
            r4.decodeContinuous(r0)
        L_0x009f:
            r3.bindButtons()
            r3.bindObservers()
            r3.bindEditText()
            androidx.fragment.app.FragmentActivity r4 = r3.getActivity()
            if (r4 == 0) goto L_0x00c8
            com.bitcoin.mwallet.SendV2Activity r4 = (com.bitcoin.mwallet.SendV2Activity) r4
            android.net.Uri r4 = r4.getData()
            if (r4 == 0) goto L_0x00c7
            com.bitcoin.mwallet.app.viper.ScreenPresenter r0 = r3.getPresenter()
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r0 = (com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter) r0
            androidx.lifecycle.MutableLiveData r0 = r0.getRawInput()
            java.lang.String r4 = r4.toString()
            r0.postValue(r4)
        L_0x00c7:
            return
        L_0x00c8:
            kotlin.TypeCastException r4 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type com.bitcoin.mwallet.SendV2Activity"
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressView.onActivityCreated(android.os.Bundle):void");
    }

    public final void bindButtons() {
        View view = getView();
        if (view != null) {
            Button button = (Button) view.findViewById(C1018R.C1021id.continueButton);
            if (button != null) {
                button.setOnClickListener(new SelectAddressView$bindButtons$1(this));
            }
        }
        View view2 = getView();
        if (view2 != null) {
            LinearLayout linearLayout = (LinearLayout) view2.findViewById(C1018R.C1021id.walletsButton);
            if (linearLayout != null) {
                linearLayout.setOnClickListener(new SelectAddressView$bindButtons$2(this));
            }
        }
        View view3 = getView();
        if (view3 != null) {
            LinearLayout linearLayout2 = (LinearLayout) view3.findViewById(C1018R.C1021id.contactsButton);
            if (linearLayout2 != null) {
                linearLayout2.setOnClickListener(new SelectAddressView$bindButtons$3(this));
            }
        }
        View view4 = getView();
        if (view4 != null) {
            LinearLayout linearLayout3 = (LinearLayout) view4.findViewById(C1018R.C1021id.pasteButton);
            if (linearLayout3 != null) {
                linearLayout3.setOnClickListener(new SelectAddressView$bindButtons$4(this));
            }
        }
        View view5 = getView();
        if (view5 != null) {
            ImageView imageView = (ImageView) view5.findViewById(C1018R.C1021id.rawInputValidImageView);
            if (imageView != null) {
                imageView.setOnClickListener(new SelectAddressView$bindButtons$5(this));
            }
        }
    }

    public final void bindEditText() {
        View view = getView();
        EditText editText = view != null ? (EditText) view.findViewById(C1018R.C1021id.recipientEditText) : null;
        if (editText != null) {
            editText.addTextChangedListener(new SelectAddressView$bindEditText$$inlined$doAfterTextChanged$1(this));
        }
    }

    public final void bindObservers() {
        ((SelectAddressPresenter) getPresenter()).getSelectedAddress().observe(getViewLifecycleOwner(), SelectAddressView$bindObservers$1.INSTANCE);
        ((SelectAddressPresenter) getPresenter()).getRawInput().observe(getViewLifecycleOwner(), new SelectAddressView$bindObservers$2(this));
        ((SelectAddressPresenter) getPresenter()).getRawInputIsValid().observe(getViewLifecycleOwner(), new SelectAddressView$bindObservers$3(this));
    }

    /* access modifiers changed from: private */
    public final void viberate() {
        FragmentActivity activity = getActivity();
        Object systemService = activity != null ? activity.getSystemService("vibrator") : null;
        if (systemService != null) {
            Vibrator vibrator = (Vibrator) systemService;
            if (VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, -1));
            } else {
                vibrator.vibrate(500);
            }
        } else {
            throw new TypeCastException("null cannot be cast to non-null type android.os.Vibrator");
        }
    }

    /* access modifiers changed from: private */
    public final void disableContinue() {
        View view = getView();
        ImageView imageView = null;
        Button button = view != null ? (Button) view.findViewById(C1018R.C1021id.continueButton) : null;
        View view2 = getView();
        if (view2 != null) {
            imageView = (ImageView) view2.findViewById(C1018R.C1021id.rawInputValidImageView);
        }
        if (button != null) {
            button.setEnabled(false);
        }
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        if (imageView != null) {
            imageView.setImageResource(C1018R.C1020drawable.ic_invalid_address);
        }
    }

    /* access modifiers changed from: private */
    public final void enableContinue() {
        View view = getView();
        ImageView imageView = null;
        Button button = view != null ? (Button) view.findViewById(C1018R.C1021id.continueButton) : null;
        View view2 = getView();
        if (view2 != null) {
            imageView = (ImageView) view2.findViewById(C1018R.C1021id.rawInputValidImageView);
        }
        if (button != null) {
            button.setEnabled(true);
        }
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        if (imageView != null) {
            imageView.setImageResource(C1018R.C1020drawable.ic_tick);
        }
    }

    public void onPause() {
        super.onPause();
        View view = getView();
        DecoratedBarcodeView decoratedBarcodeView = view != null ? (DecoratedBarcodeView) view.findViewById(C1018R.C1021id.zxing_barcode_scanner) : null;
        if (decoratedBarcodeView != null) {
            decoratedBarcodeView.pauseAndWait();
        }
    }

    public void onResume() {
        super.onResume();
        View view = getView();
        DecoratedBarcodeView decoratedBarcodeView = view != null ? (DecoratedBarcodeView) view.findViewById(C1018R.C1021id.zxing_barcode_scanner) : null;
        if (decoratedBarcodeView != null) {
            decoratedBarcodeView.resume();
        }
        String str = "";
        ((SelectAddressPresenter) getPresenter()).pasteTapped(str);
        String str2 = null;
        this.prevAddress = str2;
        this.prevUrl = str2;
        ((SelectAddressPresenter) getPresenter()).setBitcoinUriContent(BitcoinUriContent.CREATOR.parse(str));
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((SendV2Activity) activity).setData(null);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.SendV2Activity");
    }

    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        IntentResult parseActivityResult = IntentIntegrator.parseActivityResult(i, i2, intent);
        if (parseActivityResult != null && parseActivityResult.getContents() != null) {
            SelectAddressPresenter selectAddressPresenter = (SelectAddressPresenter) getPresenter();
            String contents = parseActivityResult.getContents();
            Intrinsics.checkExpressionValueIsNotNull(contents, "result.contents");
            selectAddressPresenter.pasteTapped(contents);
        }
    }
}
