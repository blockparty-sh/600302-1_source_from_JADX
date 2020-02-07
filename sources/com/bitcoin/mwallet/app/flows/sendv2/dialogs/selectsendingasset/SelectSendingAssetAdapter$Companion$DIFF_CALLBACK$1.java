package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset;

import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¨\u0006\b"}, mo37405d2 = {"com/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SelectSendingAssetAdapter$Companion$DIFF_CALLBACK$1", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SendableAssetModel;", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectSendingAssetAdapter.kt */
public final class SelectSendingAssetAdapter$Companion$DIFF_CALLBACK$1 extends ItemCallback<SendableAssetModel> {
    SelectSendingAssetAdapter$Companion$DIFF_CALLBACK$1() {
    }

    public boolean areItemsTheSame(@NotNull SendableAssetModel sendableAssetModel, @NotNull SendableAssetModel sendableAssetModel2) {
        Intrinsics.checkParameterIsNotNull(sendableAssetModel, "oldItem");
        Intrinsics.checkParameterIsNotNull(sendableAssetModel2, "newItem");
        return Intrinsics.areEqual((Object) sendableAssetModel.getName(), (Object) sendableAssetModel.getName()) && Intrinsics.areEqual((Object) sendableAssetModel.getTicker(), (Object) sendableAssetModel.getTicker());
    }

    public boolean areContentsTheSame(@NotNull SendableAssetModel sendableAssetModel, @NotNull SendableAssetModel sendableAssetModel2) {
        Intrinsics.checkParameterIsNotNull(sendableAssetModel, "oldItem");
        Intrinsics.checkParameterIsNotNull(sendableAssetModel2, "newItem");
        return Intrinsics.areEqual((Object) sendableAssetModel.getName(), (Object) sendableAssetModel.getName()) && Intrinsics.areEqual((Object) sendableAssetModel.getTicker(), (Object) sendableAssetModel.getTicker());
    }
}
