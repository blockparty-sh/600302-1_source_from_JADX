package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset;

import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import com.google.android.gms.actions.SearchIntents;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\b"}, mo37405d2 = {"com/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SelectSendingAssetView$onViewCreated$1", "Landroidx/appcompat/widget/SearchView$OnQueryTextListener;", "onQueryTextChange", "", "newText", "", "onQueryTextSubmit", "query", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectSendingAssetView.kt */
public final class SelectSendingAssetView$onViewCreated$1 implements OnQueryTextListener {
    final /* synthetic */ SelectSendingAssetAdapter $adapter;

    SelectSendingAssetView$onViewCreated$1(SelectSendingAssetAdapter selectSendingAssetAdapter) {
        this.$adapter = selectSendingAssetAdapter;
    }

    public boolean onQueryTextChange(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "newText");
        this.$adapter.getFilter().filter(str);
        return false;
    }

    public boolean onQueryTextSubmit(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, SearchIntents.EXTRA_QUERY);
        this.$adapter.getFilter().filter(str);
        return false;
    }
}
