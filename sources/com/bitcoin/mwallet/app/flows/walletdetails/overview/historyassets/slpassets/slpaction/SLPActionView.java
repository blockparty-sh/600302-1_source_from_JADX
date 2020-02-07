package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.slpaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.views.RoundedBottomSheetDialogFragment;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J&\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/slpassets/slpaction/SLPActionView;", "Lcom/bitcoin/mwallet/core/views/RoundedBottomSheetDialogFragment;", "tokenId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "(Lcom/bitcoin/mwallet/core/models/slp/SlpId;Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "getTokenId", "()Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SLPActionView.kt */
public final class SLPActionView extends RoundedBottomSheetDialogFragment {
    private HashMap _$_findViewCache;
    @NotNull
    private final SlpId tokenId;
    @NotNull
    private final WalletId walletId;

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

    public SLPActionView(@NotNull SlpId slpId, @NotNull WalletId walletId2) {
        Intrinsics.checkParameterIsNotNull(slpId, "tokenId");
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        this.tokenId = slpId;
        this.walletId = walletId2;
    }

    @NotNull
    public final SlpId getTokenId() {
        return this.tokenId;
    }

    @NotNull
    public final WalletId getWalletId() {
        return this.walletId;
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = getLayoutInflater().inflate(C1018R.layout.fragment_slpassets_slpaction, null);
        if (inflate != null) {
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(C1018R.C1021id.sendTokenButton);
            if (linearLayout != null) {
                linearLayout.setOnClickListener(new SLPActionView$onCreateView$1(this));
            }
        }
        if (inflate != null) {
            LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(C1018R.C1021id.viewExplorerButton);
            if (linearLayout2 != null) {
                linearLayout2.setOnClickListener(new SLPActionView$onCreateView$2(this, inflate));
            }
        }
        if (inflate != null) {
            LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(C1018R.C1021id.cancelButton);
            if (linearLayout3 != null) {
                linearLayout3.setOnClickListener(new SLPActionView$onCreateView$3(this));
            }
        }
        return inflate;
    }
}
