package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.BitcoinUriContent;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.views.FullScreenRoundedBottomSheetDialogFragment;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ&\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u001a\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0003\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001d"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SelectSendingAssetView;", "Lcom/bitcoin/mwallet/core/views/FullScreenRoundedBottomSheetDialogFragment;", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/OnSendingAssetSelectedListener;", "listener", "bitcoinUriContent", "Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "preferredWalletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "(Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/OnSendingAssetSelectedListener;Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "getBitcoinUriContent", "()Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "getListener", "()Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/OnSendingAssetSelectedListener;", "getPreferredWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onSendingAssetSelected", "", "sendableAssetModel", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SendableAssetModel;", "onViewCreated", "view", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectSendingAssetView.kt */
public final class SelectSendingAssetView extends FullScreenRoundedBottomSheetDialogFragment implements OnSendingAssetSelectedListener {
    private HashMap _$_findViewCache;
    @NotNull
    private final BitcoinUriContent bitcoinUriContent;
    @NotNull
    private final OnSendingAssetSelectedListener listener;
    @Nullable
    private final WalletId preferredWalletId;

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
    public final OnSendingAssetSelectedListener getListener() {
        return this.listener;
    }

    @NotNull
    public final BitcoinUriContent getBitcoinUriContent() {
        return this.bitcoinUriContent;
    }

    @Nullable
    public final WalletId getPreferredWalletId() {
        return this.preferredWalletId;
    }

    public SelectSendingAssetView(@NotNull OnSendingAssetSelectedListener onSendingAssetSelectedListener, @NotNull BitcoinUriContent bitcoinUriContent2, @Nullable WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(onSendingAssetSelectedListener, CastExtraArgs.LISTENER);
        Intrinsics.checkParameterIsNotNull(bitcoinUriContent2, "bitcoinUriContent");
        this.listener = onSendingAssetSelectedListener;
        this.bitcoinUriContent = bitcoinUriContent2;
        this.preferredWalletId = walletId;
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return getLayoutInflater().inflate(C1018R.layout.fragment_screen_sendv2_selectsendingasset, null);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        ViewModel viewModel = ViewModelProviders.m16of((Fragment) this).get(SelectSendingAssetBuilder.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…AssetBuilder::class.java)");
        SelectSendingAssetPresenter presenter = ((SelectSendingAssetBuilder) viewModel).getPresenter();
        SelectSendingAssetAdapter selectSendingAssetAdapter = new SelectSendingAssetAdapter(this, presenter.getVerifiedAssets(), presenter.getContext(), presenter.getVerifiedTokenInteractor());
        if (this.bitcoinUriContent.isSlp()) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.assetSelectTitle);
            if (textView != null) {
                textView.setText(C1018R.string.send_asset_token_title);
            }
        } else {
            TextView textView2 = (TextView) view.findViewById(C1018R.C1021id.assetSelectTitle);
            if (textView2 != null) {
                textView2.setText(C1018R.string.send_asset_coin_title);
            }
        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(C1018R.C1021id.sendingAssetListView);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "view?.findViewById<Recyc….id.sendingAssetListView)");
        recyclerView.setAdapter(selectSendingAssetAdapter);
        SearchView searchView = (SearchView) view.findViewById(C1018R.C1021id.keywordSearchView);
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SelectSendingAssetView$onViewCreated$1(selectSendingAssetAdapter));
        }
        List asset = presenter.getAsset(this.bitcoinUriContent, this.preferredWalletId);
        if (asset.isEmpty()) {
            LinearLayout linearLayout = (LinearLayout) view.findViewById(C1018R.C1021id.sendingAssetNoTokenLayout);
            if (linearLayout != null) {
                linearLayout.setVisibility(0);
            }
            RecyclerView recyclerView2 = (RecyclerView) view.findViewById(C1018R.C1021id.sendingAssetListView);
            if (recyclerView2 != null) {
                recyclerView2.setVisibility(8);
                return;
            }
            return;
        }
        selectSendingAssetAdapter.submitList(asset);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(C1018R.C1021id.sendingAssetNoTokenLayout);
        if (linearLayout2 != null) {
            linearLayout2.setVisibility(8);
        }
        RecyclerView recyclerView3 = (RecyclerView) view.findViewById(C1018R.C1021id.sendingAssetListView);
        if (recyclerView3 != null) {
            recyclerView3.setVisibility(0);
        }
    }

    public void onSendingAssetSelected(@NotNull SendableAssetModel sendableAssetModel) {
        Intrinsics.checkParameterIsNotNull(sendableAssetModel, "sendableAssetModel");
        this.listener.onSendingAssetSelected(sendableAssetModel);
        dismiss();
    }
}
