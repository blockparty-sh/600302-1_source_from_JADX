package com.bitcoin.mwallet.core.views;

import androidx.recyclerview.widget.DiffUtil.Callback;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B!\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/views/ListDiffCallback;", "T", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "oldItems", "", "newItems", "(Ljava/util/List;Ljava/util/List;)V", "areContentsTheSame", "", "oldItemPosition", "", "newItemPosition", "areItemsTheSame", "getNewListSize", "getOldListSize", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ListDiffCallback.kt */
public final class ListDiffCallback<T> extends Callback {
    private final List<T> newItems;
    private final List<T> oldItems;

    public ListDiffCallback(@NotNull List<? extends T> list, @NotNull List<? extends T> list2) {
        Intrinsics.checkParameterIsNotNull(list, "oldItems");
        Intrinsics.checkParameterIsNotNull(list2, "newItems");
        this.oldItems = list;
        this.newItems = list2;
    }

    public int getOldListSize() {
        return this.oldItems.size();
    }

    public int getNewListSize() {
        return this.newItems.size();
    }

    public boolean areItemsTheSame(int i, int i2) {
        return areContentsTheSame(i, i2);
    }

    public boolean areContentsTheSame(int i, int i2) {
        return Intrinsics.areEqual(this.oldItems.get(i), this.newItems.get(i2));
    }
}
