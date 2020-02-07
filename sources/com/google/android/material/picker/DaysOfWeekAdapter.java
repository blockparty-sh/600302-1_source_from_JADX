package com.google.android.material.picker;

import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Locale;

class DaysOfWeekAdapter extends BaseAdapter {
    private static final int CALENDAR_DAY_STYLE = (VERSION.SDK_INT >= 26 ? 4 : 1);
    private static final int NARROW_FORMAT = 4;
    private final Calendar calendar = Calendar.getInstance();
    private final int daysInWeek;
    private final int firstDayOfWeek;

    public long getItemId(int i) {
        return 0;
    }

    public DaysOfWeekAdapter() {
        this.calendar.clear();
        this.daysInWeek = this.calendar.getMaximum(7);
        this.firstDayOfWeek = this.calendar.getFirstDayOfWeek();
    }

    public Integer getItem(int i) {
        if (i >= this.daysInWeek) {
            return null;
        }
        return Integer.valueOf(positionToDayOfWeek(i));
    }

    public int getCount() {
        return this.daysInWeek;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = new TextView(viewGroup.getContext());
        }
        this.calendar.set(7, positionToDayOfWeek(i));
        textView.setText(this.calendar.getDisplayName(7, CALENDAR_DAY_STYLE, Locale.getDefault()));
        textView.setGravity(17);
        return textView;
    }

    private int positionToDayOfWeek(int i) {
        int i2 = i + this.firstDayOfWeek;
        int i3 = this.daysInWeek;
        return i2 > i3 ? i2 - i3 : i2;
    }
}
