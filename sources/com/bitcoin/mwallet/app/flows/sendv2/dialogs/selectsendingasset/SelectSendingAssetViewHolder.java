package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.bitmap.BitmapUtils;
import com.bitcoin.mwallet.core.bitmap.BitmapUtils.Companion;
import com.bitcoin.mwallet.core.interactors.VerifiedTokenInteractor;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import com.leanplum.internal.Constants.Params;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001c"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SelectSendingAssetViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "listener", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/OnSendingAssetSelectedListener;", "verifiedAsset", "", "Lcom/bitcoin/mwallet/core/models/verifiedtoken/VerifiedToken;", "context", "Landroid/content/Context;", "verifiedTokenInteractor", "Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "(Landroid/view/View;Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/OnSendingAssetSelectedListener;Ljava/util/List;Landroid/content/Context;Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;)V", "getContext", "()Landroid/content/Context;", "getListener", "()Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/OnSendingAssetSelectedListener;", "getVerifiedAsset", "()Ljava/util/List;", "getVerifiedTokenInteractor", "()Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "getView", "()Landroid/view/View;", "bind", "", "item", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SendableAssetModel;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectSendingAssetViewHolder.kt */
public final class SelectSendingAssetViewHolder extends ViewHolder {
    @Nullable
    private final Context context;
    @NotNull
    private final OnSendingAssetSelectedListener listener;
    @NotNull
    private final List<VerifiedToken> verifiedAsset;
    @NotNull
    private final VerifiedTokenInteractor verifiedTokenInteractor;
    @NotNull
    private final View view;

    @NotNull
    public final View getView() {
        return this.view;
    }

    @NotNull
    public final OnSendingAssetSelectedListener getListener() {
        return this.listener;
    }

    @NotNull
    public final List<VerifiedToken> getVerifiedAsset() {
        return this.verifiedAsset;
    }

    @Nullable
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final VerifiedTokenInteractor getVerifiedTokenInteractor() {
        return this.verifiedTokenInteractor;
    }

    public SelectSendingAssetViewHolder(@NotNull View view2, @NotNull OnSendingAssetSelectedListener onSendingAssetSelectedListener, @NotNull List<VerifiedToken> list, @Nullable Context context2, @NotNull VerifiedTokenInteractor verifiedTokenInteractor2) {
        Intrinsics.checkParameterIsNotNull(view2, "view");
        Intrinsics.checkParameterIsNotNull(onSendingAssetSelectedListener, CastExtraArgs.LISTENER);
        Intrinsics.checkParameterIsNotNull(list, "verifiedAsset");
        Intrinsics.checkParameterIsNotNull(verifiedTokenInteractor2, "verifiedTokenInteractor");
        super(view2);
        this.view = view2;
        this.listener = onSendingAssetSelectedListener;
        this.verifiedAsset = list;
        this.context = context2;
        this.verifiedTokenInteractor = verifiedTokenInteractor2;
    }

    public final void bind(@NotNull SendableAssetModel sendableAssetModel) {
        Intrinsics.checkParameterIsNotNull(sendableAssetModel, Params.IAP_ITEM);
        SelectSendingAssetViewHolder$bind$1 selectSendingAssetViewHolder$bind$1 = new SelectSendingAssetViewHolder$bind$1(this);
        ImageView imageView = (ImageView) this.view.findViewById(C1018R.C1021id.sendingAssetImageView);
        TextView textView = (TextView) this.view.findViewById(C1018R.C1021id.sendingAssetPrimaryName);
        TextView textView2 = (TextView) this.view.findViewById(C1018R.C1021id.sendingAssetSecondaryName);
        String str = "assetNamePrimary";
        if (sendableAssetModel.getTokenId() != null) {
            VerifiedTokenInteractor verifiedTokenInteractor2 = this.verifiedTokenInteractor;
            Resources resources = this.view.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "view.resources");
            Intrinsics.checkExpressionValueIsNotNull(imageView, "assetIcon");
            if (!verifiedTokenInteractor2.trySetVerifiedTokenImage(resources, imageView, sendableAssetModel.getTokenId())) {
                imageView.setImageResource(C1018R.C1020drawable.ic_slp_default);
                Companion companion = BitmapUtils.Companion;
                Context context2 = this.context;
                Drawable drawable = context2 != null ? context2.getDrawable(C1018R.C1020drawable.ic_slp_default) : null;
                if (drawable == null) {
                    Intrinsics.throwNpe();
                }
                imageView.setImageDrawable(selectSendingAssetViewHolder$bind$1.invoke(companion.drawableToBitmap(drawable)));
            }
            Intrinsics.checkExpressionValueIsNotNull(textView, str);
            textView.setText(StringsKt.replace$default(sendableAssetModel.getTicker(), "\n", "", false, 4, (Object) null));
            Intrinsics.checkExpressionValueIsNotNull(textView2, "assetNameSecondary");
            textView2.setText(StringsKt.replace$default(sendableAssetModel.getName(), "\n", "", false, 4, (Object) null));
        } else if (sendableAssetModel.getCoin() == Coin.BCH) {
            imageView.setImageResource(C1018R.C1020drawable.logo_bch_green);
            textView2.setText(C1018R.string.general_bitcoin_cash);
            Intrinsics.checkExpressionValueIsNotNull(textView, str);
            textView.setText(sendableAssetModel.getCoin().getTicker());
        } else if (sendableAssetModel.getCoin() == Coin.BTC) {
            imageView.setImageResource(C1018R.C1020drawable.logo_btc);
            textView2.setText(C1018R.string.general_bitcoin_core);
            Intrinsics.checkExpressionValueIsNotNull(textView, str);
            textView.setText(sendableAssetModel.getCoin().getTicker());
        }
        this.view.setOnClickListener(new SelectSendingAssetViewHolder$bind$2(this, sendableAssetModel));
    }
}
