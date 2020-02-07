package com.bitcoin.mwallet.app.components.customheader;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.ComponentView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0012H\u0016J\b\u0010\u0016\u001a\u00020\u0012H\u0016J\u000e\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019R\u001a\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/customheader/CustomHeaderView;", "Lcom/bitcoin/mwallet/app/viper/ComponentView;", "Lcom/bitcoin/mwallet/app/components/customheader/CustomHeaderBuilder;", "Lcom/bitcoin/mwallet/app/components/customheader/CustomHeaderPresenter;", "Lcom/bitcoin/mwallet/app/components/customheader/CustomHeaderListener;", "()V", "headerText", "", "getHeaderText", "()Ljava/lang/String;", "setHeaderText", "(Ljava/lang/String;)V", "listenerCustom", "getListenerCustom", "()Lcom/bitcoin/mwallet/app/components/customheader/CustomHeaderListener;", "setListenerCustom", "(Lcom/bitcoin/mwallet/app/components/customheader/CustomHeaderListener;)V", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "onClosePressed", "onLeftButtonPressed", "setCloseButtonVisibiity", "visibility", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CustomHeaderView.kt */
public final class CustomHeaderView extends ComponentView<CustomHeaderBuilder, CustomHeaderPresenter> implements CustomHeaderListener {
    private HashMap _$_findViewCache;
    @NotNull
    public String headerText;
    @NotNull
    public CustomHeaderListener listenerCustom;

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

    public CustomHeaderView() {
        super(C1018R.layout.fragment_component_customheader, Reflection.getOrCreateKotlinClass(CustomHeaderBuilder.class));
    }

    @NotNull
    public final CustomHeaderListener getListenerCustom() {
        CustomHeaderListener customHeaderListener = this.listenerCustom;
        if (customHeaderListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("listenerCustom");
        }
        return customHeaderListener;
    }

    public final void setListenerCustom(@NotNull CustomHeaderListener customHeaderListener) {
        Intrinsics.checkParameterIsNotNull(customHeaderListener, "<set-?>");
        this.listenerCustom = customHeaderListener;
    }

    @NotNull
    public final String getHeaderText() {
        String str = this.headerText;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("headerText");
        }
        return str;
    }

    public final void setHeaderText(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.headerText = str;
    }

    public void onLeftButtonPressed() {
        CustomHeaderListener customHeaderListener = this.listenerCustom;
        if (customHeaderListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("listenerCustom");
        }
        customHeaderListener.onLeftButtonPressed();
    }

    public void onClosePressed() {
        CustomHeaderListener customHeaderListener = this.listenerCustom;
        if (customHeaderListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("listenerCustom");
        }
        customHeaderListener.onClosePressed();
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            this.listenerCustom = (CustomHeaderListener) parentFragment;
            if (this.headerText != null) {
                TextView textView = (TextView) _$_findCachedViewById(C1018R.C1021id.header);
                Intrinsics.checkExpressionValueIsNotNull(textView, "header");
                String str = this.headerText;
                if (str == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("headerText");
                }
                textView.setText(str);
            }
            ((ImageView) _$_findCachedViewById(C1018R.C1021id.pageCloseImageView)).setOnClickListener(new CustomHeaderView$onActivityCreated$3(this));
            ((ImageView) _$_findCachedViewById(C1018R.C1021id.backImageView)).setOnClickListener(new CustomHeaderView$onActivityCreated$4(this));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.app.components.customheader.CustomHeaderListener");
    }

    public final void setCloseButtonVisibiity(int i) {
        View view = getView();
        if (view != null) {
            ImageView imageView = (ImageView) view.findViewById(C1018R.C1021id.pageCloseImageView);
            if (imageView != null) {
                imageView.setVisibility(i);
            }
        }
    }
}
