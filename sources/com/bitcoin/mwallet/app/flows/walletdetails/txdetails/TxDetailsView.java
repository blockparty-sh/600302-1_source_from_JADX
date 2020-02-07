package com.bitcoin.mwallet.app.flows.walletdetails.txdetails;

import android.graphics.Bitmap;
import android.view.View;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.navigation.NavArgsLazy;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.ScreenView;
import com.bitcoin.mwallet.core.models.p009tx.TxAction;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0012\u0010\u0011\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\fH\u0016R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0015"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/txdetails/TxDetailsView;", "Lcom/bitcoin/mwallet/app/viper/ScreenView;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/txdetails/TxDetailsBuilder;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/txdetails/TxDetailsPresenter;", "()V", "txDetailsNavArgs", "Lcom/bitcoin/mwallet/app/flows/walletdetails/txdetails/TxDetailsViewArgs;", "getTxDetailsNavArgs", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/txdetails/TxDetailsViewArgs;", "txDetailsNavArgs$delegate", "Landroidx/navigation/NavArgsLazy;", "bindObservers", "", "getImageDrawable", "Landroidx/core/graphics/drawable/RoundedBitmapDrawable;", "imageBitmap", "Landroid/graphics/Bitmap;", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: TxDetailsView.kt */
public final class TxDetailsView extends ScreenView<TxDetailsBuilder, TxDetailsPresenter> {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TxDetailsView.class), "txDetailsNavArgs", "getTxDetailsNavArgs()Lcom/bitcoin/mwallet/app/flows/walletdetails/txdetails/TxDetailsViewArgs;"))};
    private HashMap _$_findViewCache;
    @NotNull
    private final NavArgsLazy txDetailsNavArgs$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(TxDetailsViewArgs.class), new TxDetailsView$$special$$inlined$navArgs$1(this));

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TxAction.values().length];

        static {
            $EnumSwitchMapping$0[TxAction.SENT.ordinal()] = 1;
        }
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

    @NotNull
    public final TxDetailsViewArgs getTxDetailsNavArgs() {
        Lazy lazy = this.txDetailsNavArgs$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (TxDetailsViewArgs) lazy.getValue();
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public TxDetailsView() {
        super(C1018R.layout.fragment_screen_walletdetails_txdetails, Reflection.getOrCreateKotlinClass(TxDetailsBuilder.class));
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x001a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityCreated(@org.jetbrains.annotations.Nullable android.os.Bundle r4) {
        /*
            r3 = this;
            super.onActivityCreated(r4)
            androidx.fragment.app.FragmentActivity r4 = r3.getActivity()
            java.lang.String r0 = "txid"
            r1 = 0
            if (r4 == 0) goto L_0x0017
            android.content.Intent r4 = r4.getIntent()
            if (r4 == 0) goto L_0x0017
            java.lang.String r4 = r4.getStringExtra(r0)
            goto L_0x0018
        L_0x0017:
            r4 = r1
        L_0x0018:
            if (r4 == 0) goto L_0x0067
            com.bitcoin.mwallet.app.viper.ScreenPresenter r4 = r3.getPresenter()
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter r4 = (com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter) r4
            androidx.fragment.app.FragmentActivity r2 = r3.getActivity()
            if (r2 == 0) goto L_0x0031
            android.content.Intent r2 = r2.getIntent()
            if (r2 == 0) goto L_0x0031
            java.lang.String r0 = r2.getStringExtra(r0)
            goto L_0x0032
        L_0x0031:
            r0 = r1
        L_0x0032:
            if (r0 == 0) goto L_0x005f
            r4.setTxId(r0)
            com.bitcoin.mwallet.app.viper.ScreenPresenter r4 = r3.getPresenter()
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter r4 = (com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter) r4
            androidx.fragment.app.FragmentActivity r0 = r3.getActivity()
            if (r0 == 0) goto L_0x004f
            android.content.Intent r0 = r0.getIntent()
            if (r0 == 0) goto L_0x004f
            java.lang.String r1 = "walletId"
            java.io.Serializable r1 = r0.getSerializableExtra(r1)
        L_0x004f:
            if (r1 == 0) goto L_0x0057
            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r1
            r4.setWalletId(r1)
            goto L_0x0089
        L_0x0057:
            kotlin.TypeCastException r4 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type com.bitcoin.mwallet.core.models.wallet.WalletId"
            r4.<init>(r0)
            throw r4
        L_0x005f:
            kotlin.TypeCastException r4 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type kotlin.String"
            r4.<init>(r0)
            throw r4
        L_0x0067:
            com.bitcoin.mwallet.app.viper.ScreenPresenter r4 = r3.getPresenter()
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter r4 = (com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter) r4
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsViewArgs r0 = r3.getTxDetailsNavArgs()
            java.lang.String r0 = r0.getTxid()
            r4.setTxId(r0)
            com.bitcoin.mwallet.app.viper.ScreenPresenter r4 = r3.getPresenter()
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter r4 = (com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter) r4
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsViewArgs r0 = r3.getTxDetailsNavArgs()
            com.bitcoin.mwallet.core.models.wallet.WalletId r0 = r0.getWalletId()
            r4.setWalletId(r0)
        L_0x0089:
            r3.bindObservers()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView.onActivityCreated(android.os.Bundle):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPause() {
        /*
            r2 = this;
            android.view.View r0 = r2.getView()
            if (r0 == 0) goto L_0x0016
            r1 = 2131362246(0x7f0a01c6, float:1.8344267E38)
            android.view.View r0 = r0.findViewById(r1)
            android.widget.EditText r0 = (android.widget.EditText) r0
            if (r0 == 0) goto L_0x0016
            android.text.Editable r0 = r0.getText()
            goto L_0x0017
        L_0x0016:
            r0 = 0
        L_0x0017:
            java.lang.String r0 = java.lang.String.valueOf(r0)
            if (r0 == 0) goto L_0x0046
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            java.lang.CharSequence r0 = kotlin.text.StringsKt.trim(r0)
            java.lang.String r0 = r0.toString()
            com.bitcoin.mwallet.app.viper.ScreenPresenter r1 = r2.getPresenter()
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter r1 = (com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter) r1
            java.lang.String r1 = r1.getOriginalNoteText()
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
            r1 = r1 ^ 1
            if (r1 == 0) goto L_0x0042
            com.bitcoin.mwallet.app.viper.ScreenPresenter r1 = r2.getPresenter()
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter r1 = (com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter) r1
            r1.setNoteDetails(r0)
        L_0x0042:
            super.onPause()
            return
        L_0x0046:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.CharSequence"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView.onPause():void");
    }

    public final void bindObservers() {
        ((TxDetailsPresenter) getPresenter()).getTransactionDetails().observe(getViewLifecycleOwner(), new TxDetailsView$bindObservers$1(this));
    }

    @NotNull
    public final RoundedBitmapDrawable getImageDrawable(@NotNull Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "imageBitmap");
        View view = getView();
        if (view == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(view, "view!!");
        RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(view.getResources(), bitmap);
        Intrinsics.checkExpressionValueIsNotNull(create, "RoundedBitmapDrawableFac…!.resources, imageBitmap)");
        create.setCircular(true);
        create.setCornerRadius(((float) bitmap.getHeight()) / 2.0f);
        return create;
    }
}
