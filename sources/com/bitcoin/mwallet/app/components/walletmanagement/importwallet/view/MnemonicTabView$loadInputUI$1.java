package com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view;

import com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter;
import com.google.android.material.chip.ChipGroup;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"addTag", "", "sendingText", "", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: MnemonicTabView.kt */
final class MnemonicTabView$loadInputUI$1 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ ChipGroup $chipGroup;
    final /* synthetic */ MnemonicTabView this$0;

    MnemonicTabView$loadInputUI$1(MnemonicTabView mnemonicTabView, ChipGroup chipGroup) {
        this.this$0 = mnemonicTabView;
        this.$chipGroup = chipGroup;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((String) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "sendingText");
        for (String str2 : CollectionsKt.toMutableList((Collection) StringsKt.split$default((CharSequence) StringsKt.trim((CharSequence) str).toString(), new String[]{" "}, false, 0, 6, (Object) null))) {
            if (str2 != null) {
                String lowerCase = str2.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
                if (lowerCase.length() == 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid tag: ");
                    sb.append(lowerCase);
                    Timber.m426d(sb.toString(), new Object[0]);
                } else if (this.$chipGroup.getChildCount() < 12) {
                    ImportWalletPresenter presenter = this.this$0.getPresenter();
                    if (presenter != null) {
                        presenter.onAddMnemonic(lowerCase);
                    }
                    this.this$0.addChipToGroup(lowerCase, this.$chipGroup);
                } else {
                    return;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }
    }
}
