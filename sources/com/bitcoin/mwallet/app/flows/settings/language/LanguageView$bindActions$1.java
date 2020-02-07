package com.bitcoin.mwallet.app.flows.settings.language;

import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import com.google.android.gms.actions.SearchIntents;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\b"}, mo37405d2 = {"com/bitcoin/mwallet/app/flows/settings/language/LanguageView$bindActions$1", "Landroidx/appcompat/widget/SearchView$OnQueryTextListener;", "onQueryTextChange", "", "newText", "", "onQueryTextSubmit", "query", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: LanguageView.kt */
public final class LanguageView$bindActions$1 implements OnQueryTextListener {
    final /* synthetic */ LanguageView this$0;

    LanguageView$bindActions$1(LanguageView languageView) {
        this.this$0 = languageView;
    }

    public boolean onQueryTextChange(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "newText");
        ((LanguagePresenter) this.this$0.getPresenter()).setQuery(str);
        return false;
    }

    public boolean onQueryTextSubmit(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, SearchIntents.EXTRA_QUERY);
        ((LanguagePresenter) this.this$0.getPresenter()).setQuery(str);
        return false;
    }
}
