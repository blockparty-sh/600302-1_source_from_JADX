package com.bitcoin.mwallet.app.components.walletselector;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.views.walletsummary.WalletSummaryView;
import com.leanplum.internal.Constants.Params;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00112\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\fH\u0016R\u000e\u0010\u0007\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/bitcoin/mwallet/core/views/walletsummary/WalletSummaryView;", "Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorViewHolder;", "walletSelectorListener", "Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorListener;", "(Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorListener;)V", "listener", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletSelectorAdapter.kt */
public final class WalletSelectorAdapter extends ListAdapter<WalletSummaryView, WalletSelectorViewHolder> {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    @NotNull
    public static final ItemCallback<WalletSummaryView> DIFF_CALLBACK = new WalletSelectorAdapter$Companion$DIFF_CALLBACK$1();
    private WalletSelectorListener listener;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorAdapter$Companion;", "", "()V", "DIFF_CALLBACK", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/bitcoin/mwallet/core/views/walletsummary/WalletSummaryView;", "getDIFF_CALLBACK", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: WalletSelectorAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ItemCallback<WalletSummaryView> getDIFF_CALLBACK() {
            return WalletSelectorAdapter.DIFF_CALLBACK;
        }
    }

    public WalletSelectorAdapter(@NotNull WalletSelectorListener walletSelectorListener) {
        Intrinsics.checkParameterIsNotNull(walletSelectorListener, "walletSelectorListener");
        super(DIFF_CALLBACK);
        this.listener = walletSelectorListener;
    }

    public void onBindViewHolder(@NotNull WalletSelectorViewHolder walletSelectorViewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(walletSelectorViewHolder, "holder");
        WalletSummaryView walletSummaryView = (WalletSummaryView) getItem(i);
        Intrinsics.checkExpressionValueIsNotNull(walletSummaryView, Params.IAP_ITEM);
        walletSelectorViewHolder.bind(walletSummaryView);
    }

    @NotNull
    public WalletSelectorViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1018R.layout.fragment_component_walletselectorholder, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "view");
        return new WalletSelectorViewHolder(inflate, this.listener);
    }
}
