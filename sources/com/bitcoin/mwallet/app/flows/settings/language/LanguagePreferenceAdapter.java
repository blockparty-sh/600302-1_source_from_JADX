package com.bitcoin.mwallet.app.flows.settings.language;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.preferences.Language;
import com.leanplum.internal.Constants.Params;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00142\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0004\u0012\u00020\u00050\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000fH\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/language/LanguagePreferenceAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/preferences/Language;", "", "Lcom/bitcoin/mwallet/app/flows/settings/language/LanguagePreferenceViewHolder;", "activity", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "getActivity", "()Landroidx/fragment/app/FragmentActivity;", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: LanguageAdapter.kt */
public final class LanguagePreferenceAdapter extends ListAdapter<Pair<? extends Language, ? extends Boolean>, LanguagePreferenceViewHolder> {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    @NotNull
    public static final ItemCallback<Pair<Language, Boolean>> DIFF_CALLBACK = new LanguagePreferenceAdapter$Companion$DIFF_CALLBACK$1();
    @NotNull
    private final FragmentActivity activity;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R#\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/language/LanguagePreferenceAdapter$Companion;", "", "()V", "DIFF_CALLBACK", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/preferences/Language;", "", "getDIFF_CALLBACK", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: LanguageAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ItemCallback<Pair<Language, Boolean>> getDIFF_CALLBACK() {
            return LanguagePreferenceAdapter.DIFF_CALLBACK;
        }
    }

    @NotNull
    public final FragmentActivity getActivity() {
        return this.activity;
    }

    public LanguagePreferenceAdapter(@NotNull FragmentActivity fragmentActivity) {
        Intrinsics.checkParameterIsNotNull(fragmentActivity, "activity");
        super(DIFF_CALLBACK);
        this.activity = fragmentActivity;
    }

    public void onBindViewHolder(@NotNull LanguagePreferenceViewHolder languagePreferenceViewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(languagePreferenceViewHolder, "holder");
        Pair pair = (Pair) getItem(i);
        Intrinsics.checkExpressionValueIsNotNull(pair, Params.IAP_ITEM);
        languagePreferenceViewHolder.bind(pair);
    }

    @NotNull
    public LanguagePreferenceViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1018R.layout.layout_select_currency_cell, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "view");
        return new LanguagePreferenceViewHolder(inflate, this.activity);
    }
}
