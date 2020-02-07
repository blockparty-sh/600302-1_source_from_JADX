package com.bitcoin.mwallet.core.views.walletsummary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00102\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0010\u0011B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/views/walletsummary/WalletSummaryListAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/bitcoin/mwallet/core/views/walletsummary/WalletSummaryView;", "Lcom/bitcoin/mwallet/core/views/walletsummary/WalletSummaryViewHolder;", "clickedListener", "Lcom/bitcoin/mwallet/core/views/walletsummary/WalletSummaryListAdapter$OnWalletSummaryClickedListener;", "(Lcom/bitcoin/mwallet/core/views/walletsummary/WalletSummaryListAdapter$OnWalletSummaryClickedListener;)V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "OnWalletSummaryClickedListener", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletSummaryListAdapter.kt */
public final class WalletSummaryListAdapter extends ListAdapter<WalletSummaryView, WalletSummaryViewHolder> {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    @NotNull
    public static final ItemCallback<WalletSummaryView> DIFF_CALLBACK = new WalletSummaryListAdapter$Companion$DIFF_CALLBACK$1();
    private final OnWalletSummaryClickedListener clickedListener;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/views/walletsummary/WalletSummaryListAdapter$Companion;", "", "()V", "DIFF_CALLBACK", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/bitcoin/mwallet/core/views/walletsummary/WalletSummaryView;", "getDIFF_CALLBACK", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: WalletSummaryListAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ItemCallback<WalletSummaryView> getDIFF_CALLBACK() {
            return WalletSummaryListAdapter.DIFF_CALLBACK;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/views/walletsummary/WalletSummaryListAdapter$OnWalletSummaryClickedListener;", "", "onWalletSummaryClicked", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "isMultiSig", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: WalletSummaryListAdapter.kt */
    public interface OnWalletSummaryClickedListener {
        void onWalletSummaryClicked(@NotNull WalletId walletId, boolean z);
    }

    public WalletSummaryListAdapter(@NotNull OnWalletSummaryClickedListener onWalletSummaryClickedListener) {
        Intrinsics.checkParameterIsNotNull(onWalletSummaryClickedListener, "clickedListener");
        super(DIFF_CALLBACK);
        this.clickedListener = onWalletSummaryClickedListener;
    }

    @NotNull
    public WalletSummaryViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1018R.layout.component_wallet_summary_item, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(pare…mary_item, parent, false)");
        return new WalletSummaryViewHolder(inflate);
    }

    public void onBindViewHolder(@NotNull WalletSummaryViewHolder walletSummaryViewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(walletSummaryViewHolder, "holder");
        Object item = getItem(i);
        Intrinsics.checkExpressionValueIsNotNull(item, "getItem(position)");
        WalletSummaryView walletSummaryView = (WalletSummaryView) item;
        walletSummaryViewHolder.bind(walletSummaryView);
        walletSummaryViewHolder.getView().setTag(walletSummaryView);
        walletSummaryViewHolder.getView().setOnClickListener(WalletSummaryListAdapter$onBindViewHolder$1.INSTANCE);
    }
}
