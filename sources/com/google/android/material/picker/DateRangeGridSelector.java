package com.google.android.material.picker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.StyleRes;
import androidx.core.util.Pair;
import com.google.android.material.C1435R;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.resources.MaterialResources;
import java.util.Calendar;

@RestrictTo({Scope.LIBRARY_GROUP})
public class DateRangeGridSelector implements GridSelector<Pair<Calendar, Calendar>> {
    public static final Creator<DateRangeGridSelector> CREATOR = new Creator<DateRangeGridSelector>() {
        public DateRangeGridSelector createFromParcel(Parcel parcel) {
            DateRangeGridSelector dateRangeGridSelector = new DateRangeGridSelector();
            dateRangeGridSelector.selectedStartItem = (Calendar) parcel.readSerializable();
            dateRangeGridSelector.selectedEndItem = (Calendar) parcel.readSerializable();
            dateRangeGridSelector.stylesInitialized = ((Boolean) parcel.readValue(null)).booleanValue();
            dateRangeGridSelector.rangeFillColor = parcel.readInt();
            dateRangeGridSelector.dayStyle = parcel.readInt();
            dateRangeGridSelector.selectedStyle = parcel.readInt();
            dateRangeGridSelector.todayStyle = parcel.readInt();
            return dateRangeGridSelector;
        }

        public DateRangeGridSelector[] newArray(int i) {
            return new DateRangeGridSelector[i];
        }
    };
    /* access modifiers changed from: private */
    @StyleRes
    public int dayStyle;
    /* access modifiers changed from: private */
    @ColorInt
    public int rangeFillColor;
    private Paint rangeFillPaint;
    /* access modifiers changed from: private */
    public Calendar selectedEndItem = null;
    /* access modifiers changed from: private */
    public Calendar selectedStartItem = null;
    /* access modifiers changed from: private */
    @StyleRes
    public int selectedStyle;
    /* access modifiers changed from: private */
    public boolean stylesInitialized = false;
    /* access modifiers changed from: private */
    @StyleRes
    public int todayStyle;

    public int describeContents() {
        return 0;
    }

    private void initializeStyles(Context context) {
        if (!this.stylesInitialized) {
            this.stylesInitialized = true;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(MaterialAttributes.resolveOrThrow(context, C1435R.attr.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()), C1435R.styleable.MaterialCalendar);
            ColorStateList colorStateList = MaterialResources.getColorStateList(context, obtainStyledAttributes, C1435R.styleable.MaterialCalendar_rangeFillColor);
            this.dayStyle = obtainStyledAttributes.getResourceId(C1435R.styleable.MaterialCalendar_dayStyle, 0);
            this.selectedStyle = obtainStyledAttributes.getResourceId(C1435R.styleable.MaterialCalendar_daySelectedStyle, 0);
            this.todayStyle = obtainStyledAttributes.getResourceId(C1435R.styleable.MaterialCalendar_dayTodayStyle, 0);
            this.rangeFillColor = colorStateList.getDefaultColor();
            obtainStyledAttributes.recycle();
        }
    }

    public void select(Calendar calendar) {
        Calendar calendar2 = this.selectedStartItem;
        if (calendar2 == null) {
            this.selectedStartItem = calendar;
        } else if (this.selectedEndItem != null || (!calendar.after(calendar2) && !calendar.equals(this.selectedStartItem))) {
            this.selectedEndItem = null;
            this.selectedStartItem = calendar;
        } else {
            this.selectedEndItem = calendar;
        }
    }

    public void drawCell(TextView textView, Calendar calendar) {
        int i;
        initializeStyles(textView.getContext());
        if (calendar.equals(this.selectedStartItem) || calendar.equals(this.selectedEndItem)) {
            i = this.selectedStyle;
        } else if (DateUtils.isToday(calendar.getTimeInMillis())) {
            i = this.todayStyle;
        } else {
            i = this.dayStyle;
        }
        CalendarGridSelectors.colorCell(textView, i);
    }

    public void onCalendarMonthDraw(Canvas canvas, MaterialCalendarGridView materialCalendarGridView) {
        int i;
        int i2;
        int i3;
        int i4;
        MaterialCalendarGridView materialCalendarGridView2 = materialCalendarGridView;
        initializeStyles(materialCalendarGridView.getContext());
        if (this.rangeFillPaint == null) {
            this.rangeFillPaint = new Paint();
            this.rangeFillPaint.setColor(this.rangeFillColor);
        }
        MonthAdapter adapter = materialCalendarGridView.getAdapter();
        Calendar item = adapter.getItem(adapter.firstPositionInMonth());
        Calendar item2 = adapter.getItem(adapter.lastPositionInMonth());
        if (!skipMonth(item, item2, this.selectedStartItem, this.selectedEndItem)) {
            if (this.selectedStartItem.before(item)) {
                i2 = adapter.firstPositionInMonth();
                if (i2 == 0) {
                    i = 0;
                } else {
                    i = materialCalendarGridView2.getChildAt(i2 - 1).getRight();
                }
            } else {
                i2 = adapter.dayToPosition(this.selectedStartItem.get(5));
                i = horizontalMidPoint(materialCalendarGridView2.getChildAt(i2));
            }
            if (this.selectedEndItem.after(item2)) {
                i4 = adapter.lastPositionInMonth();
                if (i4 == materialCalendarGridView.getCount() - 1) {
                    i3 = materialCalendarGridView.getWidth();
                } else {
                    i3 = materialCalendarGridView2.getChildAt(i4 + 1).getLeft();
                }
            } else {
                i4 = adapter.dayToPosition(this.selectedEndItem.get(5));
                i3 = horizontalMidPoint(materialCalendarGridView2.getChildAt(i4));
            }
            int itemId = (int) adapter.getItemId(i4);
            for (int itemId2 = (int) adapter.getItemId(i2); itemId2 <= itemId; itemId2++) {
                int numColumns = materialCalendarGridView.getNumColumns() * itemId2;
                int numColumns2 = (materialCalendarGridView.getNumColumns() + numColumns) - 1;
                View childAt = materialCalendarGridView2.getChildAt(numColumns);
                canvas.drawRect((float) (numColumns > i2 ? 0 : i), (float) childAt.getTop(), (float) (i4 > numColumns2 ? materialCalendarGridView.getWidth() : i3), (float) childAt.getBottom(), this.rangeFillPaint);
            }
        }
    }

    @Nullable
    public Pair<Calendar, Calendar> getSelection() {
        Calendar calendar = this.selectedStartItem;
        if (calendar != null) {
            Calendar calendar2 = this.selectedEndItem;
            if (calendar2 != null) {
                return new Pair<>(calendar, calendar2);
            }
        }
        return null;
    }

    @Nullable
    public Calendar getStart() {
        Calendar calendar = this.selectedStartItem;
        if (calendar == null || this.selectedEndItem == null) {
            return null;
        }
        return calendar;
    }

    @Nullable
    public Calendar getEnd() {
        if (this.selectedStartItem != null) {
            Calendar calendar = this.selectedEndItem;
            if (calendar != null) {
                return calendar;
            }
        }
        return null;
    }

    private boolean skipMonth(Calendar calendar, Calendar calendar2, Calendar calendar3, Calendar calendar4) {
        return calendar3 == null || calendar4 == null || calendar3.after(calendar2) || calendar4.before(calendar);
    }

    private int horizontalMidPoint(View view) {
        return view.getLeft() + (view.getWidth() / 2);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.selectedStartItem);
        parcel.writeSerializable(this.selectedEndItem);
        parcel.writeValue(Boolean.valueOf(this.stylesInitialized));
        parcel.writeInt(this.rangeFillColor);
        parcel.writeInt(this.dayStyle);
        parcel.writeInt(this.selectedStyle);
        parcel.writeInt(this.todayStyle);
    }
}
