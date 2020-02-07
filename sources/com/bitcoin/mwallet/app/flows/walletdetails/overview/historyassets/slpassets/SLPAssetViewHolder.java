package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.bitmap.BitmapUtils;
import com.bitcoin.mwallet.core.bitmap.BitmapUtils.Companion;
import com.bitcoin.mwallet.core.extensions.BigDecimalKt;
import com.bitcoin.mwallet.core.interactors.VerifiedTokenInteractor;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/slpassets/SLPAssetViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "context", "Landroid/content/Context;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "verifiedTokenInteractor", "Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "(Landroid/view/View;Landroid/content/Context;Landroidx/fragment/app/FragmentManager;Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;)V", "getContext", "()Landroid/content/Context;", "getFragmentManager", "()Landroidx/fragment/app/FragmentManager;", "getVerifiedTokenInteractor", "()Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "getView", "()Landroid/view/View;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "bind", "", "assetRow", "Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/slpassets/SLPAssetRow;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SLPAssetsAdapter.kt */
public final class SLPAssetViewHolder extends ViewHolder {
    @Nullable
    private final Context context;
    @NotNull
    private final FragmentManager fragmentManager;
    @NotNull
    private final VerifiedTokenInteractor verifiedTokenInteractor;
    @NotNull
    private final View view;
    @NotNull
    private final WalletId walletId;

    @NotNull
    public final View getView() {
        return this.view;
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

    public SLPAssetViewHolder(@NotNull View view2, @Nullable Context context2, @NotNull FragmentManager fragmentManager2, @NotNull WalletId walletId2, @NotNull VerifiedTokenInteractor verifiedTokenInteractor2) {
        Intrinsics.checkParameterIsNotNull(view2, "view");
        Intrinsics.checkParameterIsNotNull(fragmentManager2, "fragmentManager");
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        Intrinsics.checkParameterIsNotNull(verifiedTokenInteractor2, "verifiedTokenInteractor");
        super(view2);
        this.view = view2;
        this.context = context2;
        this.fragmentManager = fragmentManager2;
        this.walletId = walletId2;
        this.verifiedTokenInteractor = verifiedTokenInteractor2;
    }

    public final void bind(@NotNull SLPAssetRow sLPAssetRow) {
        Intrinsics.checkParameterIsNotNull(sLPAssetRow, "assetRow");
        View findViewById = this.view.findViewById(C1018R.C1021id.slpTokenTicker);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById<TextView>(R.id.slpTokenTicker)");
        ((TextView) findViewById).setText(sLPAssetRow.getToken().getTicker());
        View findViewById2 = this.view.findViewById(C1018R.C1021id.slpTokenName);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById<TextView>(R.id.slpTokenName)");
        ((TextView) findViewById2).setText(sLPAssetRow.getToken().getName());
        View findViewById3 = this.view.findViewById(C1018R.C1021id.slpTokenBalance);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "view.findViewById<TextView>(R.id.slpTokenBalance)");
        ((TextView) findViewById3).setText(String.valueOf(BigDecimalKt.toPlainGroupedString(sLPAssetRow.getBalance())));
        this.view.setOnClickListener(new SLPAssetViewHolder$bind$1(this, sLPAssetRow));
        ImageView imageView = (ImageView) this.view.findViewById(C1018R.C1021id.slpTokenImage);
        if (Intrinsics.areEqual((Object) sLPAssetRow.getImage(), (Object) "")) {
            imageView.setImageResource(C1018R.C1020drawable.ic_slp_default);
            Companion companion = BitmapUtils.Companion;
            Context context2 = this.context;
            if (context2 == null) {
                Intrinsics.throwNpe();
            }
            Drawable drawable = context2.getDrawable(C1018R.C1020drawable.ic_slp_default);
            if (drawable == null) {
                Intrinsics.throwNpe();
            }
            Bitmap drawableToBitmap = companion.drawableToBitmap(drawable);
            RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(this.view.getResources(), drawableToBitmap);
            Intrinsics.checkExpressionValueIsNotNull(create, "RoundedBitmapDrawableFac…w.resources, imageBitmap)");
            create.setCircular(true);
            create.setCornerRadius(((float) drawableToBitmap.getHeight()) / 2.0f);
            imageView.setImageDrawable(create);
            return;
        }
        VerifiedTokenInteractor verifiedTokenInteractor2 = this.verifiedTokenInteractor;
        Resources resources = this.view.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "view.resources");
        Intrinsics.checkExpressionValueIsNotNull(imageView, "logoImage");
        verifiedTokenInteractor2.trySetImage(resources, imageView, sLPAssetRow.getImage());
    }
}
