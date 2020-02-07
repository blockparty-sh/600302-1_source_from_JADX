package com.google.android.material.picker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.format.DateUtils;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import com.google.android.material.C1435R;
import com.google.android.material.resources.MaterialAttributes;
import java.util.Calendar;

@RestrictTo({Scope.LIBRARY_GROUP})
public class DateGridSelector implements GridSelector<Calendar> {
    public static final Creator<DateGridSelector> CREATOR = new Creator<DateGridSelector>() {
        public DateGridSelector createFromParcel(Parcel parcel) {
            DateGridSelector dateGridSelector = new DateGridSelector();
            dateGridSelector.selectedItem = (Calendar) parcel.readSerializable();
            return dateGridSelector;
        }

        public DateGridSelector[] newArray(int i) {
            return new DateGridSelector[i];
        }
    };
    /* access modifiers changed from: private */
    public Calendar selectedItem;

    public int describeContents() {
        return 0;
    }

    public void onCalendarMonthDraw(Canvas canvas, MaterialCalendarGridView materialCalendarGridView) {
    }

    public void select(Calendar calendar) {
        this.selectedItem = calendar;
    }

    public void drawCell(TextView textView, Calendar calendar) {
        int i;
        Context context = textView.getContext();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(MaterialAttributes.resolveOrThrow(context, C1435R.attr.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()), C1435R.styleable.MaterialCalendar);
        if (calendar.equals(this.selectedItem)) {
            i = obtainStyledAttributes.getResourceId(C1435R.styleable.MaterialCalendar_daySelectedStyle, 0);
        } else if (DateUtils.isToday(calendar.getTimeInMillis())) {
            i = obtainStyledAttributes.getResourceId(C1435R.styleable.MaterialCalendar_dayTodayStyle, 0);
        } else {
            i = obtainStyledAttributes.getResourceId(C1435R.styleable.MaterialCalendar_dayStyle, 0);
        }
        obtainStyledAttributes.recycle();
        CalendarGridSelectors.colorCell(textView, i);
    }

    @Nullable
    public Calendar getSelection() {
        return this.selectedItem;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.selectedItem);
    }
}
