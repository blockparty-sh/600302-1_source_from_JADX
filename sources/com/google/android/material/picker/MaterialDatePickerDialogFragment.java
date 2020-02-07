package com.google.android.material.picker;

import android.os.Bundle;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import com.google.android.material.C1435R;
import java.util.Calendar;

@RestrictTo({Scope.LIBRARY_GROUP})
public class MaterialDatePickerDialogFragment extends MaterialPickerDialogFragment<Calendar> {
    public static MaterialDatePickerDialogFragment newInstance() {
        return newInstance(0);
    }

    public static MaterialDatePickerDialogFragment newInstance(int i) {
        return newInstance(i, MaterialPickerDialogFragment.DEFAULT_BOUNDS);
    }

    public static MaterialDatePickerDialogFragment newInstance(CalendarBounds calendarBounds) {
        return newInstance(0, calendarBounds);
    }

    public static MaterialDatePickerDialogFragment newInstance(int i, CalendarBounds calendarBounds) {
        MaterialDatePickerDialogFragment materialDatePickerDialogFragment = new MaterialDatePickerDialogFragment();
        Bundle bundle = new Bundle();
        addArgsToBundle(bundle, i, calendarBounds, C1435R.string.mtrl_picker_date_header_title);
        materialDatePickerDialogFragment.setArguments(bundle);
        return materialDatePickerDialogFragment;
    }

    /* access modifiers changed from: protected */
    public int getDefaultThemeAttr() {
        return C1435R.attr.materialCalendarTheme;
    }

    /* access modifiers changed from: protected */
    public DateGridSelector createGridSelector() {
        return new DateGridSelector();
    }

    /* access modifiers changed from: protected */
    public String getHeaderText(Calendar calendar) {
        if (calendar == null) {
            return getContext().getResources().getString(C1435R.string.mtrl_picker_date_header_unselected);
        }
        String format = getSimpleDateFormat().format(calendar.getTime());
        return getContext().getResources().getString(C1435R.string.mtrl_picker_date_header_selected, new Object[]{format});
    }
}
