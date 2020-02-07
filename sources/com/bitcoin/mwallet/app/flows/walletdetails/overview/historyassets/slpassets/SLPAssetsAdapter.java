package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.interactors.VerifiedTokenInteractor;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.leanplum.internal.Constants.Params;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 $2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001$B'\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0018\u0010 \u001a\u00020\u00032\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001fH\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006%"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/slpassets/SLPAssetsAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/slpassets/SLPAssetRow;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/slpassets/SLPAssetViewHolder;", "context", "Landroid/content/Context;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "verifiedTokenInteractor", "Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "(Landroid/content/Context;Landroidx/fragment/app/FragmentManager;Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;)V", "getContext", "()Landroid/content/Context;", "getFragmentManager", "()Landroidx/fragment/app/FragmentManager;", "tokens", "", "getTokens", "()Ljava/util/List;", "setTokens", "(Ljava/util/List;)V", "getVerifiedTokenInteractor", "()Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SLPAssetsAdapter.kt */
public final class SLPAssetsAdapter extends ListAdapter<SLPAssetRow, SLPAssetViewHolder> {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    @NotNull
    public static final ItemCallback<SLPAssetRow> DIFF_CALLBACK = new SLPAssetsAdapter$Companion$DIFF_CALLBACK$1();
    @Nullable
    private final Context context;
    @NotNull
    private final FragmentManager fragmentManager;
    @NotNull
    private List<SLPAssetRow> tokens = CollectionsKt.emptyList();
    @NotNull
    private final VerifiedTokenInteractor verifiedTokenInteractor;
    @NotNull
    private final WalletId walletId;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/slpassets/SLPAssetsAdapter$Companion;", "", "()V", "DIFF_CALLBACK", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/slpassets/SLPAssetRow;", "getDIFF_CALLBACK", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: SLPAssetsAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ItemCallback<SLPAssetRow> getDIFF_CALLBACK() {
            return SLPAssetsAdapter.DIFF_CALLBACK;
        }
    }

    @Nullable
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final FragmentManager getFragmentManager() {
        return this.fragmentManager;
    }

    @NotNull
    public final WalletId getWalletId() {
        return this.walletId;
    }

    @NotNull
    public final VerifiedTokenInteractor getVerifiedTokenInteractor() {
        return this.verifiedTokenInteractor;
    }

    public SLPAssetsAdapter(@Nullable Context context2, @NotNull FragmentManager fragmentManager2, @NotNull WalletId walletId2, @NotNull VerifiedTokenInteractor verifiedTokenInteractor2) {
        Intrinsics.checkParameterIsNotNull(fragmentManager2, "fragmentManager");
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        Intrinsics.checkParameterIsNotNull(verifiedTokenInteractor2, "verifiedTokenInteractor");
        super(DIFF_CALLBACK);
        this.context = context2;
        this.fragmentManager = fragmentManager2;
        this.walletId = walletId2;
        this.verifiedTokenInteractor = verifiedTokenInteractor2;
    }

    @NotNull
    public final List<SLPAssetRow> getTokens() {
        return this.tokens;
    }

    public final void setTokens(@NotNull List<SLPAssetRow> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.tokens = list;
    }

    public void onBindViewHolder(@NotNull SLPAssetViewHolder sLPAssetViewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(sLPAssetViewHolder, "holder");
        SLPAssetRow sLPAssetRow = (SLPAssetRow) getItem(i);
        Intrinsics.checkExpressionValueIsNotNull(sLPAssetRow, Params.IAP_ITEM);
        sLPAssetViewHolder.bind(sLPAssetRow);
    }

    @NotNull
    public SLPAssetViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1018R.layout.layout_slp_asset_cell, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "view");
        SLPAssetViewHolder sLPAssetViewHolder = new SLPAssetViewHolder(inflate, this.context, this.fragmentManager, this.walletId, this.verifiedTokenInteractor);
        return sLPAssetViewHolder;
    }
}
