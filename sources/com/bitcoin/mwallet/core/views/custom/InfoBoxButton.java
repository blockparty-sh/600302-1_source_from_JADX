package com.bitcoin.mwallet.core.views.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bitcoin.mwallet.C1018R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/views/custom/InfoBoxButton;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: InfoBoxButton.kt */
public final class InfoBoxButton extends LinearLayout {
    private HashMap _$_findViewCache;

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
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public InfoBoxButton(@NotNull Context context, @NotNull AttributeSet attributeSet) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attributeSet");
        super(context, attributeSet);
        LinearLayout.inflate(context, C1018R.layout.custom_infobox_button, this);
        View findViewById = findViewById(C1018R.C1021id.infoBoxButtonIcon);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.infoBoxButtonIcon)");
        ImageView imageView = (ImageView) findViewById;
        View findViewById2 = findViewById(C1018R.C1021id.infoBoxButtonTitle);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById(R.id.infoBoxButtonTitle)");
        TextView textView = (TextView) findViewById2;
        View findViewById3 = findViewById(C1018R.C1021id.infoBoxButtonBody);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "findViewById(R.id.infoBoxButtonBody)");
        TextView textView2 = (TextView) findViewById3;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1018R.styleable.InfoBoxButton);
        Intrinsics.checkExpressionValueIsNotNull(obtainStyledAttributes, "context.obtainStyledAttr….styleable.InfoBoxButton)");
        imageView.setImageDrawable(obtainStyledAttributes.getDrawable(1));
        textView.setText(obtainStyledAttributes.getString(2));
        textView2.setText(obtainStyledAttributes.getString(0));
        obtainStyledAttributes.recycle();
    }
}
