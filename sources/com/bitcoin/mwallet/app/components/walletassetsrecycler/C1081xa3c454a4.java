package com.bitcoin.mwallet.app.components.walletassetsrecycler;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.State;
import com.bitcoin.mwallet.app.components.walletassetsrecycler.WalletAssetsRecyclerAdapter.MultipleWalletsAssetsViewHolder;
import com.leanplum.internal.Constants.Params;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, mo37405d2 = {"com/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerAdapter$MultipleWalletsAssetsViewHolder$bind$itemDecorator$1", "Landroidx/recyclerview/widget/DividerItemDecoration;", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.app.components.walletassetsrecycler.WalletAssetsRecyclerAdapter$MultipleWalletsAssetsViewHolder$bind$itemDecorator$1 */
/* compiled from: WalletAssetsRecyclerAdapter.kt */
public final class C1081xa3c454a4 extends DividerItemDecoration {
    final /* synthetic */ MultipleWalletsAssetsViewHolder this$0;

    C1081xa3c454a4(MultipleWalletsAssetsViewHolder multipleWalletsAssetsViewHolder, Context context, int i) {
        this.this$0 = multipleWalletsAssetsViewHolder;
        super(context, i);
    }

    public void getItemOffsets(@NotNull Rect rect, @NotNull View view, @NotNull RecyclerView recyclerView, @NotNull State state) {
        Intrinsics.checkParameterIsNotNull(rect, "outRect");
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(recyclerView, "parent");
        Intrinsics.checkParameterIsNotNull(state, Params.STATE);
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(adapter, "parent.adapter!!");
        if (childAdapterPosition == adapter.getItemCount() - 1) {
            rect.setEmpty();
        } else {
            super.getItemOffsets(rect, view, recyclerView, state);
        }
    }
}
