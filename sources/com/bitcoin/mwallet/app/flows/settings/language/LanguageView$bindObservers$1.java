package com.bitcoin.mwallet.app.flows.settings.language;

import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.core.preferences.Language;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u000122\u0010\u0002\u001a.\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004 \u0007*\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\b"}, mo37405d2 = {"<anonymous>", "", "it", "", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/preferences/Language;", "", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: LanguageView.kt */
final class LanguageView$bindObservers$1<T> implements Observer<List<? extends Pair<? extends Language, ? extends Boolean>>> {
    final /* synthetic */ LanguagePreferenceAdapter $adapter;

    LanguageView$bindObservers$1(LanguagePreferenceAdapter languagePreferenceAdapter) {
        this.$adapter = languagePreferenceAdapter;
    }

    public final void onChanged(List<? extends Pair<? extends Language, Boolean>> list) {
        this.$adapter.submitList(list);
    }
}
