package com.google.android.material.picker;

import android.graphics.Canvas;
import android.os.Parcelable;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import java.util.Calendar;

@RestrictTo({Scope.LIBRARY_GROUP})
public interface GridSelector<S> extends Parcelable {
    void drawCell(TextView textView, Calendar calendar);

    @Nullable
    S getSelection();

    void onCalendarMonthDraw(Canvas canvas, MaterialCalendarGridView materialCalendarGridView);

    void select(Calendar calendar);
}
