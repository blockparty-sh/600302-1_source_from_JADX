package com.bitcoin.mwallet.app.flows.settings.language;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.preferences.Language;
import com.leanplum.internal.Constants.Params;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001a\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/language/LanguagePreferenceViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "activity", "Landroidx/fragment/app/FragmentActivity;", "(Landroid/view/View;Landroidx/fragment/app/FragmentActivity;)V", "getActivity", "()Landroidx/fragment/app/FragmentActivity;", "getView", "()Landroid/view/View;", "bind", "", "item", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/preferences/Language;", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: LanguageAdapter.kt */
public final class LanguagePreferenceViewHolder extends ViewHolder {
    @NotNull
    private final FragmentActivity activity;
    @NotNull
    private final View view;

    @NotNull
    public final FragmentActivity getActivity() {
        return this.activity;
    }

    @NotNull
    public final View getView() {
        return this.view;
    }

    public LanguagePreferenceViewHolder(@NotNull View view2, @NotNull FragmentActivity fragmentActivity) {
        Intrinsics.checkParameterIsNotNull(view2, "view");
        Intrinsics.checkParameterIsNotNull(fragmentActivity, "activity");
        super(view2);
        this.view = view2;
        this.activity = fragmentActivity;
    }

    public final void bind(@NotNull Pair<? extends Language, Boolean> pair) {
        Intrinsics.checkParameterIsNotNull(pair, Params.IAP_ITEM);
        View findViewById = this.view.findViewById(C1018R.C1021id.currencyTextView);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById<TextView>(R.id.currencyTextView)");
        ((TextView) findViewById).setText(((Language) pair.getFirst()).name());
        View findViewById2 = this.view.findViewById(C1018R.C1021id.currencyCodeTextView);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById<TextVi….id.currencyCodeTextView)");
        ((TextView) findViewById2).setText("");
        String str = "view.findViewById<ImageV…urrencySelectedImageView)";
        if (((Boolean) pair.getSecond()).booleanValue()) {
            View findViewById3 = this.view.findViewById(C1018R.C1021id.currencySelectedImageView);
            Intrinsics.checkExpressionValueIsNotNull(findViewById3, str);
            ((ImageView) findViewById3).setVisibility(0);
        } else {
            View findViewById4 = this.view.findViewById(C1018R.C1021id.currencySelectedImageView);
            Intrinsics.checkExpressionValueIsNotNull(findViewById4, str);
            ((ImageView) findViewById4).setVisibility(4);
        }
        this.view.setOnClickListener(new LanguagePreferenceViewHolder$bind$1(this, pair));
    }
}
