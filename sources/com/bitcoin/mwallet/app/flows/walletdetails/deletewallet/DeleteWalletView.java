package com.bitcoin.mwallet.app.flows.walletdetails.deletewallet;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.navigation.NavArgsLazy;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.ScreenView;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fJ\u0012\u0010\u000e\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/deletewallet/DeleteWalletView;", "Lcom/bitcoin/mwallet/app/viper/ScreenView;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/deletewallet/deletewalletBuilder;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/deletewallet/DeletewalletPresenter;", "()V", "navArgs", "Lcom/bitcoin/mwallet/app/flows/walletdetails/deletewallet/DeleteWalletViewArgs;", "getNavArgs", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/deletewallet/DeleteWalletViewArgs;", "navArgs$delegate", "Landroidx/navigation/NavArgsLazy;", "bindActions", "", "bindObservers", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: DeleteWalletView.kt */
public final class DeleteWalletView extends ScreenView<deletewalletBuilder, DeletewalletPresenter> {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DeleteWalletView.class), "navArgs", "getNavArgs()Lcom/bitcoin/mwallet/app/flows/walletdetails/deletewallet/DeleteWalletViewArgs;"))};
    private HashMap _$_findViewCache;
    private final NavArgsLazy navArgs$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(DeleteWalletViewArgs.class), new DeleteWalletView$$special$$inlined$navArgs$1(this));

    private final DeleteWalletViewArgs getNavArgs() {
        Lazy lazy = this.navArgs$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (DeleteWalletViewArgs) lazy.getValue();
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

    public DeleteWalletView() {
        super(C1018R.layout.fragment_screen_walletdetails_deletewallet, Reflection.getOrCreateKotlinClass(deletewalletBuilder.class));
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x001b  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityCreated(@org.jetbrains.annotations.Nullable android.os.Bundle r2) {
        /*
            r1 = this;
            super.onActivityCreated(r2)
            androidx.fragment.app.FragmentActivity r2 = r1.getActivity()
            if (r2 == 0) goto L_0x0016
            android.content.Intent r2 = r2.getIntent()
            if (r2 == 0) goto L_0x0016
            java.lang.String r0 = "walletId"
            java.io.Serializable r2 = r2.getSerializableExtra(r0)
            goto L_0x0017
        L_0x0016:
            r2 = 0
        L_0x0017:
            com.bitcoin.mwallet.core.models.wallet.WalletId r2 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r2
            if (r2 == 0) goto L_0x0025
            com.bitcoin.mwallet.app.viper.ScreenPresenter r0 = r1.getPresenter()
            com.bitcoin.mwallet.app.flows.walletdetails.deletewallet.DeletewalletPresenter r0 = (com.bitcoin.mwallet.app.flows.walletdetails.deletewallet.DeletewalletPresenter) r0
            r0.initializeWalletId(r2)
            goto L_0x0036
        L_0x0025:
            com.bitcoin.mwallet.app.viper.ScreenPresenter r2 = r1.getPresenter()
            com.bitcoin.mwallet.app.flows.walletdetails.deletewallet.DeletewalletPresenter r2 = (com.bitcoin.mwallet.app.flows.walletdetails.deletewallet.DeletewalletPresenter) r2
            com.bitcoin.mwallet.app.flows.walletdetails.deletewallet.DeleteWalletViewArgs r0 = r1.getNavArgs()
            com.bitcoin.mwallet.core.models.wallet.WalletId r0 = r0.getWalletId()
            r2.initializeWalletId(r0)
        L_0x0036:
            r1.bindActions()
            r1.bindObservers()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.walletdetails.deletewallet.DeleteWalletView.onActivityCreated(android.os.Bundle):void");
    }

    public final void bindObservers() {
        ((DeletewalletPresenter) getPresenter()).getWalletDeleted().observe(getViewLifecycleOwner(), new DeleteWalletView$bindObservers$1(this));
        ((DeletewalletPresenter) getPresenter()).getButtonIsEnabled().observe(getViewLifecycleOwner(), new DeleteWalletView$bindObservers$2(this));
        ((DeletewalletPresenter) getPresenter()).getWallet().observe(getViewLifecycleOwner(), new DeleteWalletView$bindObservers$3(this));
    }

    public final void bindActions() {
        View view = getView();
        if (view != null) {
            EditText editText = (EditText) view.findViewById(C1018R.C1021id.deleteWalletNameEditText);
            if (editText != null) {
                editText.addTextChangedListener(new DeleteWalletView$bindActions$$inlined$addTextChangedListener$1(this));
            }
        }
        View view2 = getView();
        if (view2 != null) {
            Button button = (Button) view2.findViewById(C1018R.C1021id.deleteWalletButton);
            if (button != null) {
                button.setOnClickListener(new DeleteWalletView$bindActions$2(this));
            }
        }
    }
}
