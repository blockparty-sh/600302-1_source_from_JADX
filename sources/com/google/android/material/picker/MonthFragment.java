package com.google.android.material.picker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.fragment.app.Fragment;
import com.google.android.material.C1435R;

@RestrictTo({Scope.LIBRARY_GROUP})
public class MonthFragment extends Fragment {
    private static final String GRID_SELECTOR_KEY = "GRID_SELECTOR_KEY";
    private static final String MONTH_KEY = "MONTH_KEY";
    private Month month;
    /* access modifiers changed from: private */
    public MonthAdapter monthAdapter;
    /* access modifiers changed from: private */
    @Nullable
    public OnDayClickListener onDayClickListener;

    public void setOnDayClickListener(@Nullable OnDayClickListener onDayClickListener2) {
        this.onDayClickListener = onDayClickListener2;
    }

    public static MonthFragment newInstance(Month month2, GridSelector<?> gridSelector) {
        MonthFragment monthFragment = new MonthFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(MONTH_KEY, month2);
        bundle.putParcelable(GRID_SELECTOR_KEY, gridSelector);
        monthFragment.setArguments(bundle);
        return monthFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.month = (Month) getArguments().getParcelable(MONTH_KEY);
        this.monthAdapter = new MonthAdapter(getContext(), this.month, (GridSelector) getArguments().getParcelable(GRID_SELECTOR_KEY));
    }

    public GridView onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) LayoutInflater.from(viewGroup.getContext()).inflate(C1435R.layout.mtrl_month_grid, viewGroup, false).findViewById(C1435R.C1438id.month_grid);
        materialCalendarGridView.setNumColumns(this.month.daysInWeek);
        materialCalendarGridView.setAdapter((ListAdapter) this.monthAdapter);
        materialCalendarGridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (MonthFragment.this.onDayClickListener != null && MonthFragment.this.monthAdapter.withinMonth(i)) {
                    MonthFragment.this.onDayClickListener.onDayClick(MonthFragment.this.monthAdapter.getItem(i));
                }
            }
        });
        return materialCalendarGridView;
    }

    /* access modifiers changed from: 0000 */
    public void notifyDataSetChanged() {
        this.monthAdapter.notifyDataSetChanged();
    }
}
