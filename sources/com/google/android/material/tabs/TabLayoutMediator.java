package com.google.android.material.tabs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
import com.google.android.material.tabs.TabLayout.Tab;
import java.lang.ref.WeakReference;

public final class TabLayoutMediator {
    private Adapter<?> adapter;
    private boolean attached;
    private final boolean autoRefresh;
    private final OnConfigureTabCallback onConfigureTabCallback;
    private TabLayoutOnPageChangeCallback onPageChangeCallback;
    private OnTabSelectedListener onTabSelectedListener;
    private AdapterDataObserver pagerAdapterObserver;
    @NonNull
    private final TabLayout tabLayout;
    @NonNull
    private final ViewPager2 viewPager;

    public interface OnConfigureTabCallback {
        void onConfigureTab(@NonNull Tab tab, int i);
    }

    private class PagerAdapterObserver extends AdapterDataObserver {
        PagerAdapterObserver() {
        }

        public void onChanged() {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        public void onItemRangeChanged(int i, int i2) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        public void onItemRangeChanged(int i, int i2, @Nullable Object obj) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        public void onItemRangeInserted(int i, int i2) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        public void onItemRangeRemoved(int i, int i2) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        public void onItemRangeMoved(int i, int i2, int i3) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }
    }

    private static class TabLayoutOnPageChangeCallback extends OnPageChangeCallback {
        private int previousScrollState;
        private int scrollState;
        private final WeakReference<TabLayout> tabLayoutRef;

        TabLayoutOnPageChangeCallback(TabLayout tabLayout) {
            this.tabLayoutRef = new WeakReference<>(tabLayout);
            reset();
        }

        public void onPageScrollStateChanged(int i) {
            this.previousScrollState = this.scrollState;
            this.scrollState = i;
        }

        public void onPageScrolled(int i, float f, int i2) {
            TabLayout tabLayout = (TabLayout) this.tabLayoutRef.get();
            if (tabLayout != null) {
                boolean z = false;
                boolean z2 = this.scrollState != 2 || this.previousScrollState == 1;
                if (!(this.scrollState == 2 && this.previousScrollState == 0)) {
                    z = true;
                }
                tabLayout.setScrollPosition(i, f, z2, z);
            }
        }

        public void onPageSelected(int i) {
            TabLayout tabLayout = (TabLayout) this.tabLayoutRef.get();
            if (tabLayout != null && tabLayout.getSelectedTabPosition() != i && i < tabLayout.getTabCount()) {
                int i2 = this.scrollState;
                tabLayout.selectTab(tabLayout.getTabAt(i), i2 == 0 || (i2 == 2 && this.previousScrollState == 0));
            }
        }

        /* access modifiers changed from: 0000 */
        public void reset() {
            this.scrollState = 0;
            this.previousScrollState = 0;
        }
    }

    private static class ViewPagerOnTabSelectedListener implements OnTabSelectedListener {
        private final ViewPager2 viewPager;

        public void onTabReselected(Tab tab) {
        }

        public void onTabUnselected(Tab tab) {
        }

        ViewPagerOnTabSelectedListener(ViewPager2 viewPager2) {
            this.viewPager = viewPager2;
        }

        public void onTabSelected(Tab tab) {
            this.viewPager.setCurrentItem(tab.getPosition(), true);
        }
    }

    public TabLayoutMediator(@NonNull TabLayout tabLayout2, @NonNull ViewPager2 viewPager2, @NonNull OnConfigureTabCallback onConfigureTabCallback2) {
        this(tabLayout2, viewPager2, true, onConfigureTabCallback2);
    }

    public TabLayoutMediator(@NonNull TabLayout tabLayout2, @NonNull ViewPager2 viewPager2, boolean z, @NonNull OnConfigureTabCallback onConfigureTabCallback2) {
        this.tabLayout = tabLayout2;
        this.viewPager = viewPager2;
        this.autoRefresh = z;
        this.onConfigureTabCallback = onConfigureTabCallback2;
    }

    public void attach() {
        if (!this.attached) {
            this.adapter = this.viewPager.getAdapter();
            if (this.adapter != null) {
                this.attached = true;
                this.onPageChangeCallback = new TabLayoutOnPageChangeCallback(this.tabLayout);
                this.viewPager.registerOnPageChangeCallback(this.onPageChangeCallback);
                this.onTabSelectedListener = new ViewPagerOnTabSelectedListener(this.viewPager);
                this.tabLayout.addOnTabSelectedListener(this.onTabSelectedListener);
                if (this.autoRefresh) {
                    this.pagerAdapterObserver = new PagerAdapterObserver();
                    this.adapter.registerAdapterDataObserver(this.pagerAdapterObserver);
                }
                populateTabsFromPagerAdapter();
                this.tabLayout.setScrollPosition(this.viewPager.getCurrentItem(), 0.0f, true);
                return;
            }
            throw new IllegalStateException("TabLayoutMediator attached before ViewPager2 has an adapter");
        }
        throw new IllegalStateException("TabLayoutMediator is already attached");
    }

    public void detach() {
        this.adapter.unregisterAdapterDataObserver(this.pagerAdapterObserver);
        this.tabLayout.removeOnTabSelectedListener(this.onTabSelectedListener);
        this.viewPager.unregisterOnPageChangeCallback(this.onPageChangeCallback);
        this.pagerAdapterObserver = null;
        this.onTabSelectedListener = null;
        this.onPageChangeCallback = null;
        this.attached = false;
    }

    /* access modifiers changed from: 0000 */
    public void populateTabsFromPagerAdapter() {
        this.tabLayout.removeAllTabs();
        Adapter<?> adapter2 = this.adapter;
        if (adapter2 != null) {
            int itemCount = adapter2.getItemCount();
            for (int i = 0; i < itemCount; i++) {
                Tab newTab = this.tabLayout.newTab();
                this.onConfigureTabCallback.onConfigureTab(newTab, i);
                this.tabLayout.addTab(newTab, false);
            }
            if (itemCount > 0) {
                int currentItem = this.viewPager.getCurrentItem();
                if (currentItem != this.tabLayout.getSelectedTabPosition()) {
                    this.tabLayout.getTabAt(currentItem).select();
                }
            }
        }
    }
}
