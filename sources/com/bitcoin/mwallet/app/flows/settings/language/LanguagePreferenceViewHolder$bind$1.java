package com.bitcoin.mwallet.app.flows.settings.language;

import android.view.View;
import android.view.View.OnClickListener;
import androidx.preference.PreferenceManager;
import com.bitcoin.mwallet.core.preferences.Language;
import com.bitcoin.mwallet.core.preferences.SharedPreference;
import kotlin.Metadata;
import kotlin.Pair;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: LanguageAdapter.kt */
final class LanguagePreferenceViewHolder$bind$1 implements OnClickListener {
    final /* synthetic */ Pair $item;
    final /* synthetic */ LanguagePreferenceViewHolder this$0;

    LanguagePreferenceViewHolder$bind$1(LanguagePreferenceViewHolder languagePreferenceViewHolder, Pair pair) {
        this.this$0 = languagePreferenceViewHolder;
        this.$item = pair;
    }

    public final void onClick(View view) {
        PreferenceManager.getDefaultSharedPreferences(this.this$0.getView().getContext()).edit().putString(SharedPreference.LANGUAGE.getKey(), ((Language) this.$item.getFirst()).name()).apply();
    }
}
