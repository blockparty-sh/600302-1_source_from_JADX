package com.google.android.material.picker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.android.material.C1435R;
import java.util.Calendar;

class MonthAdapter extends BaseAdapter {
    static final int MAXIMUM_WEEKS = Calendar.getInstance().getMaximum(4);
    final GridSelector<?> gridSelector;
    private final Month month;

    MonthAdapter(Context context, Month month2, GridSelector<?> gridSelector2) {
        this.month = month2;
        this.gridSelector = gridSelector2;
    }

    @Nullable
    public Calendar getItem(int i) {
        if (i < this.month.daysFromStartOfWeekToFirstOfMonth() || i > lastPositionInMonth()) {
            return null;
        }
        return this.month.getDay(positionToDay(i));
    }

    public long getItemId(int i) {
        return (long) (i / this.month.daysInWeek);
    }

    public int getCount() {
        return this.month.daysInWeek * MAXIMUM_WEEKS;
    }

    public TextView getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(C1435R.layout.mtrl_calendar_day, viewGroup, false);
        }
        int firstPositionInMonth = i - firstPositionInMonth();
        if (firstPositionInMonth < 0 || firstPositionInMonth >= this.month.daysInMonth) {
            textView.setVisibility(4);
        } else {
            textView.setText(String.valueOf(firstPositionInMonth + 1));
            textView.setTag(this.month);
            textView.setVisibility(0);
        }
        Calendar item = getItem(i);
        if (item != null) {
            this.gridSelector.drawCell(textView, item);
        }
        return textView;
    }

    /* access modifiers changed from: 0000 */
    public int firstPositionInMonth() {
        return this.month.daysFromStartOfWeekToFirstOfMonth();
    }

    /* access modifiers changed from: 0000 */
    public int lastPositionInMonth() {
        return (this.month.daysFromStartOfWeekToFirstOfMonth() + this.month.daysInMonth) - 1;
    }

    /* access modifiers changed from: 0000 */
    public int positionToDay(int i) {
        return (i - this.month.daysFromStartOfWeekToFirstOfMonth()) + 1;
    }

    /* access modifiers changed from: 0000 */
    public int dayToPosition(int i) {
        return firstPositionInMonth() + (i - 1);
    }

    /* access modifiers changed from: 0000 */
    public boolean withinMonth(int i) {
        return i >= firstPositionInMonth() && i <= lastPositionInMonth();
    }
}
