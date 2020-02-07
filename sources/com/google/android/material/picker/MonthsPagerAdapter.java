package com.google.android.material.picker;

import android.database.DataSetObserver;
import android.util.SparseArray;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

class MonthsPagerAdapter extends FragmentStatePagerAdapter {
    private final Month firstPage;
    private final GridSelector<?> gridSelector;
    private final Month lastPage;
    private final SparseArray<DataSetObserver> observingFragments = new SparseArray<>();
    private final OnDayClickListener onDayClickListener;
    private final int startIndex;

    MonthsPagerAdapter(FragmentManager fragmentManager, GridSelector<?> gridSelector2, Month month, Month month2, Month month3, OnDayClickListener onDayClickListener2) {
        super(fragmentManager);
        if (month.compareTo(month3) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after startPage");
        } else if (month3.compareTo(month2) <= 0) {
            this.firstPage = month;
            this.lastPage = month2;
            this.startIndex = month.monthsUntil(month3);
            this.gridSelector = gridSelector2;
            this.onDayClickListener = onDayClickListener2;
        } else {
            throw new IllegalArgumentException("startPage cannot be after lastPage");
        }
    }

    public int getCount() {
        return this.firstPage.monthsUntil(this.lastPage) + 1;
    }

    @NonNull
    public Fragment instantiateItem(@NonNull ViewGroup viewGroup, int i) {
        MonthFragment monthFragment = (MonthFragment) super.instantiateItem(viewGroup, i);
        monthFragment.setOnDayClickListener(this.onDayClickListener);
        return monthFragment;
    }

    public MonthFragment getItem(int i) {
        final MonthFragment newInstance = MonthFragment.newInstance(this.firstPage.monthsLater(i), this.gridSelector);
        C15191 r1 = new DataSetObserver() {
            public void onChanged() {
                newInstance.notifyDataSetChanged();
            }
        };
        registerDataSetObserver(r1);
        this.observingFragments.put(i, r1);
        return newInstance;
    }

    public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
        DataSetObserver dataSetObserver = (DataSetObserver) this.observingFragments.get(i);
        if (dataSetObserver != null) {
            this.observingFragments.remove(i);
            unregisterDataSetObserver(dataSetObserver);
        }
        super.destroyItem(viewGroup, i, obj);
    }

    /* access modifiers changed from: 0000 */
    public int getStartPosition() {
        return this.startIndex;
    }

    @NonNull
    public CharSequence getPageTitle(int i) {
        return getPageMonth(i).getLongName();
    }

    /* access modifiers changed from: 0000 */
    public Month getPageMonth(int i) {
        return this.firstPage.monthsLater(i);
    }
}
