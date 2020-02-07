package com.bitcoin.mwallet.app.components.receivesendbuttons.view;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.receivesendbuttons.builder.ReceiveSendButtonsBuilder;
import com.bitcoin.mwallet.app.components.receivesendbuttons.presenter.ReceiveSendButtonsPresenter;
import com.bitcoin.mwallet.app.viper.ComponentView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\n"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/receivesendbuttons/view/ReceiveSendButtonsView;", "Lcom/bitcoin/mwallet/app/viper/ComponentView;", "Lcom/bitcoin/mwallet/app/components/receivesendbuttons/builder/ReceiveSendButtonsBuilder;", "Lcom/bitcoin/mwallet/app/components/receivesendbuttons/presenter/ReceiveSendButtonsPresenter;", "()V", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "OnReceiveSendButtonsListener", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveSendButtonsView.kt */
public final class ReceiveSendButtonsView extends ComponentView<ReceiveSendButtonsBuilder, ReceiveSendButtonsPresenter> {
    private HashMap _$_findViewCache;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/receivesendbuttons/view/ReceiveSendButtonsView$OnReceiveSendButtonsListener;", "", "onReceive", "", "onSend", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ReceiveSendButtonsView.kt */
    public interface OnReceiveSendButtonsListener {
        void onReceive();

        void onSend();
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public static final /* synthetic */ ReceiveSendButtonsPresenter access$getPresenter$p(ReceiveSendButtonsView receiveSendButtonsView) {
        return (ReceiveSendButtonsPresenter) receiveSendButtonsView.getPresenter();
    }

    public ReceiveSendButtonsView() {
        super(C1018R.layout.fragment_component_receivesendbuttons, Reflection.getOrCreateKotlinClass(ReceiveSendButtonsBuilder.class));
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            ((ReceiveSendButtonsPresenter) getPresenter()).setOnClickListener((OnReceiveSendButtonsListener) parentFragment);
            View view = getView();
            if (view != null) {
                LinearLayout linearLayout = (LinearLayout) view.findViewById(C1018R.C1021id.receiveButton);
                if (linearLayout != null) {
                    linearLayout.setOnClickListener(new ReceiveSendButtonsView$onActivityCreated$1(this));
                }
            }
            View view2 = getView();
            if (view2 != null) {
                LinearLayout linearLayout2 = (LinearLayout) view2.findViewById(C1018R.C1021id.sendButton);
                if (linearLayout2 != null) {
                    linearLayout2.setOnClickListener(new ReceiveSendButtonsView$onActivityCreated$2(this));
                    return;
                }
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.app.components.receivesendbuttons.view.ReceiveSendButtonsView.OnReceiveSendButtonsListener");
    }
}
