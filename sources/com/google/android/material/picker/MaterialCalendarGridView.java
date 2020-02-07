package com.google.android.material.picker;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.ListAdapter;

final class MaterialCalendarGridView extends GridView {
    public MaterialCalendarGridView(Context context) {
        this(context, null);
    }

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public MonthAdapter getAdapter() {
        return (MonthAdapter) super.getAdapter();
    }

    public final void setAdapter(ListAdapter listAdapter) {
        if (listAdapter instanceof MonthAdapter) {
            super.setAdapter(listAdapter);
        } else {
            throw new IllegalArgumentException(String.format("%1$s must have its Adapter set to a %2$s", new Object[]{MaterialCalendarGridView.class.getCanonicalName(), MonthAdapter.class.getCanonicalName()}));
        }
    }

    /* access modifiers changed from: protected */
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getAdapter().gridSelector.onCalendarMonthDraw(canvas, this);
    }
}
