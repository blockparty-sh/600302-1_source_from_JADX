package com.bitcoin.mwallet.app.flows.receive.receiveamountselection;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, mo37405d2 = {"<anonymous>", "Landroid/os/Bundle;", "Args", "Landroidx/navigation/NavArgs;", "invoke", "androidx/navigation/fragment/FragmentNavArgsLazyKt$navArgs$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: FragmentNavArgsLazy.kt */
public final class ReceiveAmountSelectionView$$special$$inlined$navArgs$1 extends Lambda implements Function0<Bundle> {
    final /* synthetic */ Fragment $this_navArgs;

    public ReceiveAmountSelectionView$$special$$inlined$navArgs$1(Fragment fragment) {
        this.$this_navArgs = fragment;
        super(0);
    }

    @NotNull
    public final Bundle invoke() {
        Bundle arguments = this.$this_navArgs.getArguments();
        if (arguments != null) {
            return arguments;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Fragment ");
        sb.append(this.$this_navArgs);
        sb.append(" has null arguments");
        throw new IllegalStateException(sb.toString());
    }
}
