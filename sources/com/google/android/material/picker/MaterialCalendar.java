package com.google.android.material.picker;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout.LayoutParams;
import androidx.annotation.C0045Px;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener;
import com.google.android.material.C1435R;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashSet;

@RestrictTo({Scope.LIBRARY_GROUP})
public final class MaterialCalendar<S> extends Fragment {
    private static final String CALENDAR_BOUNDS_KEY = "CALENDAR_BOUNDS_KEY";
    private static final String GRID_SELECTOR_KEY = "GRID_SELECTOR_KEY";
    private static final String THEME_RES_ID_KEY = "THEME_RES_ID_KEY";
    @VisibleForTesting
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static final Object VIEW_PAGER_TAG = "VIEW_PAGER_TAG";
    /* access modifiers changed from: private */
    public CalendarBounds calendarBounds;
    /* access modifiers changed from: private */
    public GridSelector<S> gridSelector;
    /* access modifiers changed from: private */
    public MonthsPagerAdapter monthsPagerAdapter;
    /* access modifiers changed from: private */
    public final LinkedHashSet<OnSelectionChangedListener<S>> onSelectionChangedListeners = new LinkedHashSet<>();
    private int themeResId;

    interface OnDayClickListener {
        void onDayClick(Calendar calendar);
    }

    interface OnSelectionChangedListener<S> {
        void onSelectionChanged(S s);
    }

    public static <T> MaterialCalendar<T> newInstance(GridSelector<T> gridSelector2, int i, CalendarBounds calendarBounds2) {
        MaterialCalendar<T> materialCalendar = new MaterialCalendar<>();
        Bundle bundle = new Bundle();
        bundle.putInt(THEME_RES_ID_KEY, i);
        bundle.putParcelable(GRID_SELECTOR_KEY, gridSelector2);
        bundle.putParcelable(CALENDAR_BOUNDS_KEY, calendarBounds2);
        materialCalendar.setArguments(bundle);
        return materialCalendar;
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(THEME_RES_ID_KEY, this.themeResId);
        bundle.putParcelable(GRID_SELECTOR_KEY, this.gridSelector);
        bundle.putParcelable(CALENDAR_BOUNDS_KEY, this.calendarBounds);
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.themeResId = bundle.getInt(THEME_RES_ID_KEY);
        this.gridSelector = (GridSelector) bundle.getParcelable(GRID_SELECTOR_KEY);
        this.calendarBounds = (CalendarBounds) bundle.getParcelable(CALENDAR_BOUNDS_KEY);
    }

    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(new ContextThemeWrapper(getContext(), this.themeResId));
        Month start = this.calendarBounds.getStart();
        Month end = this.calendarBounds.getEnd();
        Month current = this.calendarBounds.getCurrent();
        View inflate = cloneInContext.inflate(C1435R.layout.mtrl_calendar, viewGroup, false);
        GridView gridView = (GridView) inflate.findViewById(C1435R.C1438id.calendar_days_header);
        gridView.setAdapter(new DaysOfWeekAdapter());
        gridView.setNumColumns(start.daysInWeek);
        ViewPager viewPager = (ViewPager) inflate.findViewById(C1435R.C1438id.month_pager);
        viewPager.setTag(VIEW_PAGER_TAG);
        viewPager.setLayoutParams(new LayoutParams(-1, (MonthAdapter.MAXIMUM_WEEKS * getDayHeight(getContext())) + ((MonthAdapter.MAXIMUM_WEEKS - 1) * getResources().getDimensionPixelSize(C1435R.dimen.mtrl_calendar_day_spacing_vertical))));
        MonthsPagerAdapter monthsPagerAdapter2 = new MonthsPagerAdapter(getChildFragmentManager(), this.gridSelector, start, end, current, new OnDayClickListener() {
            public void onDayClick(Calendar calendar) {
                MaterialCalendar.this.gridSelector.select(calendar);
                MaterialCalendar.this.monthsPagerAdapter.notifyDataSetChanged();
                Iterator it = MaterialCalendar.this.onSelectionChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnSelectionChangedListener) it.next()).onSelectionChanged(MaterialCalendar.this.gridSelector.getSelection());
                }
            }
        });
        this.monthsPagerAdapter = monthsPagerAdapter2;
        viewPager.setAdapter(this.monthsPagerAdapter);
        viewPager.setCurrentItem(this.monthsPagerAdapter.getStartPosition());
        addMonthChangeListeners(inflate);
        return inflate;
    }

    public final S getSelection() {
        return this.gridSelector.getSelection();
    }

    /* access modifiers changed from: 0000 */
    public boolean addOnSelectionChangedListener(OnSelectionChangedListener<S> onSelectionChangedListener) {
        return this.onSelectionChangedListeners.add(onSelectionChangedListener);
    }

    /* access modifiers changed from: 0000 */
    public boolean removeOnSelectionChangedListener(OnSelectionChangedListener<S> onSelectionChangedListener) {
        return this.onSelectionChangedListeners.remove(onSelectionChangedListener);
    }

    /* access modifiers changed from: 0000 */
    public void clearOnSelectionChangedListeners() {
        this.onSelectionChangedListeners.clear();
    }

    @C0045Px
    static int getDayHeight(Context context) {
        return (int) context.getResources().getDimension(C1435R.dimen.mtrl_calendar_day_size);
    }

    private void addMonthChangeListeners(View view) {
        final ViewPager viewPager = (ViewPager) view.findViewById(C1435R.C1438id.month_pager);
        final MaterialButton materialButton = (MaterialButton) view.findViewById(C1435R.C1438id.month_drop_select);
        materialButton.setText(viewPager.getAdapter().getPageTitle(viewPager.getCurrentItem()));
        MaterialButton materialButton2 = (MaterialButton) view.findViewById(C1435R.C1438id.month_previous);
        MaterialButton materialButton3 = (MaterialButton) view.findViewById(C1435R.C1438id.month_next);
        viewPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            public void onPageSelected(int i) {
                MaterialCalendar materialCalendar = MaterialCalendar.this;
                materialCalendar.calendarBounds = CalendarBounds.create(materialCalendar.calendarBounds.getStart(), MaterialCalendar.this.calendarBounds.getEnd(), MaterialCalendar.this.monthsPagerAdapter.getPageMonth(i));
                materialButton.setText(MaterialCalendar.this.monthsPagerAdapter.getPageTitle(i));
            }
        });
        materialButton3.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (viewPager.getCurrentItem() + 1 < viewPager.getAdapter().getCount()) {
                    ViewPager viewPager = viewPager;
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        });
        materialButton2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (viewPager.getCurrentItem() - 1 >= 0) {
                    ViewPager viewPager = viewPager;
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                }
            }
        });
    }
}
