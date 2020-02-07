package com.google.android.material.picker;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.core.util.Pair;
import com.google.android.material.C1435R;
import java.util.Calendar;

@RestrictTo({Scope.LIBRARY_GROUP})
public class MaterialDateRangePickerDialogFragment extends MaterialPickerDialogFragment<Pair<Calendar, Calendar>> {
    public static MaterialDateRangePickerDialogFragment newInstance() {
        return newInstance(0);
    }

    public static MaterialDateRangePickerDialogFragment newInstance(int i) {
        return newInstance(i, MaterialPickerDialogFragment.DEFAULT_BOUNDS);
    }

    public static MaterialDateRangePickerDialogFragment newInstance(CalendarBounds calendarBounds) {
        return newInstance(0, calendarBounds);
    }

    public static MaterialDateRangePickerDialogFragment newInstance(int i, CalendarBounds calendarBounds) {
        MaterialDateRangePickerDialogFragment materialDateRangePickerDialogFragment = new MaterialDateRangePickerDialogFragment();
        Bundle bundle = new Bundle();
        addArgsToBundle(bundle, i, calendarBounds, C1435R.string.mtrl_picker_range_header_title);
        materialDateRangePickerDialogFragment.setArguments(bundle);
        return materialDateRangePickerDialogFragment;
    }

    /* access modifiers changed from: protected */
    public int getDefaultThemeAttr() {
        return C1435R.attr.materialCalendarTheme;
    }

    /* access modifiers changed from: protected */
    public GridSelector<Pair<Calendar, Calendar>> createGridSelector() {
        return new DateRangeGridSelector();
    }

    /* access modifiers changed from: protected */
    public String getHeaderText(Pair<Calendar, Calendar> pair) {
        if (pair == null) {
            return getContext().getResources().getString(C1435R.string.mtrl_picker_range_header_unselected);
        }
        String format = getSimpleDateFormat().format(((Calendar) pair.first).getTime());
        String format2 = getSimpleDateFormat().format(((Calendar) pair.second).getTime());
        return getContext().getResources().getString(C1435R.string.mtrl_picker_range_header_selected, new Object[]{format, format2});
    }

    @Nullable
    public Calendar getStart() {
        if (getSelection() == null) {
            return null;
        }
        return (Calendar) ((Pair) getSelection()).first;
    }

    @Nullable
    public Calendar getEnd() {
        if (getSelection() == null) {
            return null;
        }
        return (Calendar) ((Pair) getSelection()).second;
    }
}
