package androidx.viewpager2.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.C0045Px;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener;
import androidx.recyclerview.widget.RecyclerView.Recycler;
import androidx.recyclerview.widget.RecyclerView.State;
import androidx.viewpager2.C0744R;
import androidx.viewpager2.adapter.StatefulAdapter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ViewPager2 extends ViewGroup {
    public static final int OFFSCREEN_PAGE_LIMIT_DEFAULT = 0;
    public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_VERTICAL = 1;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    int mCurrentItem;
    private CompositeOnPageChangeCallback mExternalPageChangeCallbacks = new CompositeOnPageChangeCallback(3);
    private FakeDrag mFakeDragger;
    private LinearLayoutManager mLayoutManager;
    private int mOffscreenPageLimit = 0;
    private PageTransformerAdapter mPageTransformerAdapter;
    private PagerSnapHelper mPagerSnapHelper;
    private Parcelable mPendingAdapterState;
    private int mPendingCurrentItem = -1;
    private RecyclerView mRecyclerView;
    private ScrollEventAdapter mScrollEventAdapter;
    private final Rect mTmpChildRect = new Rect();
    private final Rect mTmpContainerRect = new Rect();
    private boolean mUserInputEnabled = true;

    private class LinearLayoutManagerImpl extends LinearLayoutManager {
        LinearLayoutManagerImpl(Context context) {
            super(context);
        }

        public boolean performAccessibilityAction(@NonNull Recycler recycler, @NonNull State state, int i, @Nullable Bundle bundle) {
            if ((i == 4096 || i == 8192) && !ViewPager2.this.isUserInputEnabled()) {
                return false;
            }
            return super.performAccessibilityAction(recycler, state, i, bundle);
        }

        public void onInitializeAccessibilityNodeInfo(@NonNull Recycler recycler, @NonNull State state, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(recycler, state, accessibilityNodeInfoCompat);
            if (!ViewPager2.this.isUserInputEnabled()) {
                accessibilityNodeInfoCompat.removeAction(AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                accessibilityNodeInfoCompat.removeAction(AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
                accessibilityNodeInfoCompat.setScrollable(false);
            }
        }

        /* access modifiers changed from: protected */
        public void calculateExtraLayoutSpace(@NonNull State state, @NonNull int[] iArr) {
            int offscreenPageLimit = ViewPager2.this.getOffscreenPageLimit();
            if (offscreenPageLimit == 0) {
                super.calculateExtraLayoutSpace(state, iArr);
                return;
            }
            int pageSize = ViewPager2.this.getPageSize() * offscreenPageLimit;
            iArr[0] = pageSize;
            iArr[1] = pageSize;
        }
    }

    public static abstract class OnPageChangeCallback {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, @C0045Px int i2) {
        }

        public void onPageSelected(int i) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Orientation {
    }

    public interface PageTransformer {
        void transformPage(@NonNull View view, float f);
    }

    private class PagerSnapHelperImpl extends PagerSnapHelper {
        PagerSnapHelperImpl() {
        }

        @Nullable
        public View findSnapView(LayoutManager layoutManager) {
            if (ViewPager2.this.isFakeDragging()) {
                return null;
            }
            return super.findSnapView(layoutManager);
        }
    }

    private class RecyclerViewImpl extends RecyclerView {
        public CharSequence getAccessibilityClassName() {
            return "androidx.viewpager.widget.ViewPager";
        }

        RecyclerViewImpl(@NonNull Context context) {
            super(context);
        }

        public void onInitializeAccessibilityEvent(@NonNull AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setFromIndex(ViewPager2.this.mCurrentItem);
            accessibilityEvent.setToIndex(ViewPager2.this.mCurrentItem);
        }

        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent motionEvent) {
            return ViewPager2.this.isUserInputEnabled() && super.onTouchEvent(motionEvent);
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            return ViewPager2.this.isUserInputEnabled() && super.onInterceptTouchEvent(motionEvent);
        }
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new ClassLoaderCreator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return VERSION.SDK_INT >= 24 ? new SavedState(parcel, classLoader) : new SavedState(parcel);
            }

            public SavedState createFromParcel(Parcel parcel) {
                return createFromParcel(parcel, (ClassLoader) null);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        Parcelable mAdapterState;
        int mCurrentItem;
        int mRecyclerViewId;

        @RequiresApi(24)
        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            readValues(parcel, classLoader);
        }

        SavedState(Parcel parcel) {
            super(parcel);
            readValues(parcel, null);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private void readValues(Parcel parcel, ClassLoader classLoader) {
            this.mRecyclerViewId = parcel.readInt();
            this.mCurrentItem = parcel.readInt();
            this.mAdapterState = parcel.readParcelable(classLoader);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.mRecyclerViewId);
            parcel.writeInt(this.mCurrentItem);
            parcel.writeParcelable(this.mAdapterState, i);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ScrollState {
    }

    private static class SmoothScrollToPosition implements Runnable {
        private final int mPosition;
        private final RecyclerView mRecyclerView;

        SmoothScrollToPosition(int i, RecyclerView recyclerView) {
            this.mPosition = i;
            this.mRecyclerView = recyclerView;
        }

        public void run() {
            this.mRecyclerView.smoothScrollToPosition(this.mPosition);
        }
    }

    public ViewPager2(@NonNull Context context) {
        super(context);
        initialize(context, null);
    }

    public ViewPager2(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(context, attributeSet);
    }

    public ViewPager2(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(context, attributeSet);
    }

    @RequiresApi(21)
    public ViewPager2(@NonNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initialize(context, attributeSet);
    }

    private void initialize(Context context, AttributeSet attributeSet) {
        this.mRecyclerView = new RecyclerViewImpl(context);
        this.mRecyclerView.setId(ViewCompat.generateViewId());
        this.mLayoutManager = new LinearLayoutManagerImpl(context);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        setOrientation(context, attributeSet);
        this.mRecyclerView.setLayoutParams(new LayoutParams(-1, -1));
        this.mRecyclerView.addOnChildAttachStateChangeListener(enforceChildFillListener());
        this.mScrollEventAdapter = new ScrollEventAdapter(this.mLayoutManager);
        this.mFakeDragger = new FakeDrag(this, this.mScrollEventAdapter, this.mRecyclerView);
        this.mPagerSnapHelper = new PagerSnapHelperImpl();
        this.mPagerSnapHelper.attachToRecyclerView(this.mRecyclerView);
        this.mRecyclerView.addOnScrollListener(this.mScrollEventAdapter);
        CompositeOnPageChangeCallback compositeOnPageChangeCallback = new CompositeOnPageChangeCallback(3);
        this.mScrollEventAdapter.setOnPageChangeCallback(compositeOnPageChangeCallback);
        compositeOnPageChangeCallback.addOnPageChangeCallback(new OnPageChangeCallback() {
            public void onPageSelected(int i) {
                ViewPager2.this.mCurrentItem = i;
            }
        });
        compositeOnPageChangeCallback.addOnPageChangeCallback(this.mExternalPageChangeCallbacks);
        this.mPageTransformerAdapter = new PageTransformerAdapter(this.mLayoutManager);
        compositeOnPageChangeCallback.addOnPageChangeCallback(this.mPageTransformerAdapter);
        RecyclerView recyclerView = this.mRecyclerView;
        attachViewToParent(recyclerView, 0, recyclerView.getLayoutParams());
    }

    private OnChildAttachStateChangeListener enforceChildFillListener() {
        return new OnChildAttachStateChangeListener() {
            public void onChildViewDetachedFromWindow(@NonNull View view) {
            }

            public void onChildViewAttachedToWindow(@NonNull View view) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                if (layoutParams.width != -1 || layoutParams.height != -1) {
                    throw new IllegalStateException("Pages must fill the whole ViewPager2 (use match_parent)");
                }
            }
        };
    }

    private void setOrientation(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0744R.styleable.ViewPager2);
        try {
            setOrientation(obtainStyledAttributes.getInt(C0744R.styleable.ViewPager2_android_orientation, 0));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.mRecyclerViewId = this.mRecyclerView.getId();
        int i = this.mPendingCurrentItem;
        if (i == -1) {
            i = this.mCurrentItem;
        }
        savedState.mCurrentItem = i;
        Parcelable parcelable = this.mPendingAdapterState;
        if (parcelable != null) {
            savedState.mAdapterState = parcelable;
        } else {
            Adapter adapter = this.mRecyclerView.getAdapter();
            if (adapter instanceof StatefulAdapter) {
                savedState.mAdapterState = ((StatefulAdapter) adapter).saveState();
            }
        }
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mPendingCurrentItem = savedState.mCurrentItem;
        this.mPendingAdapterState = savedState.mAdapterState;
    }

    private void restorePendingState() {
        if (this.mPendingCurrentItem != -1) {
            Adapter adapter = getAdapter();
            if (adapter != null) {
                Parcelable parcelable = this.mPendingAdapterState;
                if (parcelable != null) {
                    if (adapter instanceof StatefulAdapter) {
                        ((StatefulAdapter) adapter).restoreState(parcelable);
                    }
                    this.mPendingAdapterState = null;
                }
                this.mCurrentItem = Math.max(0, Math.min(this.mPendingCurrentItem, adapter.getItemCount() - 1));
                this.mPendingCurrentItem = -1;
                this.mRecyclerView.scrollToPosition(this.mCurrentItem);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        Parcelable parcelable = (Parcelable) sparseArray.get(getId());
        if (parcelable instanceof SavedState) {
            int i = ((SavedState) parcelable).mRecyclerViewId;
            sparseArray.put(this.mRecyclerView.getId(), sparseArray.get(i));
            sparseArray.remove(i);
        }
        super.dispatchRestoreInstanceState(sparseArray);
        restorePendingState();
    }

    public void setAdapter(@Nullable Adapter adapter) {
        this.mRecyclerView.setAdapter(adapter);
        restorePendingState();
    }

    @Nullable
    public Adapter getAdapter() {
        return this.mRecyclerView.getAdapter();
    }

    public void onViewAdded(View view) {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" does not support direct child views");
        throw new IllegalStateException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        measureChild(this.mRecyclerView, i, i2);
        int measuredWidth = this.mRecyclerView.getMeasuredWidth();
        int measuredHeight = this.mRecyclerView.getMeasuredHeight();
        int measuredState = this.mRecyclerView.getMeasuredState();
        int paddingTop = measuredHeight + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(resolveSizeAndState(Math.max(measuredWidth + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i, measuredState), resolveSizeAndState(Math.max(paddingTop, getSuggestedMinimumHeight()), i2, measuredState << 16));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = this.mRecyclerView.getMeasuredWidth();
        int measuredHeight = this.mRecyclerView.getMeasuredHeight();
        this.mTmpContainerRect.left = getPaddingLeft();
        this.mTmpContainerRect.right = (i3 - i) - getPaddingRight();
        this.mTmpContainerRect.top = getPaddingTop();
        this.mTmpContainerRect.bottom = (i4 - i2) - getPaddingBottom();
        Gravity.apply(8388659, measuredWidth, measuredHeight, this.mTmpContainerRect, this.mTmpChildRect);
        this.mRecyclerView.layout(this.mTmpChildRect.left, this.mTmpChildRect.top, this.mTmpChildRect.right, this.mTmpChildRect.bottom);
    }

    /* access modifiers changed from: 0000 */
    public int getPageSize() {
        int i;
        int i2;
        if (getOrientation() == 0) {
            i2 = getWidth() - getPaddingLeft();
            i = getPaddingRight();
        } else {
            i2 = getHeight() - getPaddingTop();
            i = getPaddingBottom();
        }
        return i2 - i;
    }

    public void setOrientation(int i) {
        this.mLayoutManager.setOrientation(i);
    }

    public int getOrientation() {
        return this.mLayoutManager.getOrientation();
    }

    public void setCurrentItem(int i) {
        setCurrentItem(i, true);
    }

    public void setCurrentItem(int i, boolean z) {
        if (!isFakeDragging()) {
            Adapter adapter = getAdapter();
            if (adapter == null) {
                if (this.mPendingCurrentItem != -1) {
                    this.mPendingCurrentItem = Math.max(i, 0);
                }
            } else if (adapter.getItemCount() > 0) {
                int min = Math.min(Math.max(i, 0), adapter.getItemCount() - 1);
                if (min == this.mCurrentItem && this.mScrollEventAdapter.isIdle()) {
                    return;
                }
                if (min != this.mCurrentItem || !z) {
                    float f = (float) this.mCurrentItem;
                    this.mCurrentItem = min;
                    if (!this.mScrollEventAdapter.isIdle()) {
                        f = this.mScrollEventAdapter.getRelativeScrollPosition();
                    }
                    this.mScrollEventAdapter.notifyProgrammaticScroll(min, z);
                    if (!z) {
                        this.mRecyclerView.scrollToPosition(min);
                        return;
                    }
                    float f2 = (float) min;
                    if (Math.abs(f2 - f) > 3.0f) {
                        this.mRecyclerView.scrollToPosition(f2 > f ? min - 3 : min + 3);
                        RecyclerView recyclerView = this.mRecyclerView;
                        recyclerView.post(new SmoothScrollToPosition(min, recyclerView));
                    } else {
                        this.mRecyclerView.smoothScrollToPosition(min);
                    }
                }
            }
        } else {
            throw new IllegalStateException("Cannot change current item when ViewPager2 is fake dragging");
        }
    }

    public int getCurrentItem() {
        return this.mCurrentItem;
    }

    public int getScrollState() {
        return this.mScrollEventAdapter.getScrollState();
    }

    public boolean beginFakeDrag() {
        return this.mFakeDragger.beginFakeDrag();
    }

    public boolean fakeDragBy(float f) {
        return this.mFakeDragger.fakeDragBy(f);
    }

    public boolean endFakeDrag() {
        return this.mFakeDragger.endFakeDrag();
    }

    public boolean isFakeDragging() {
        return this.mFakeDragger.isFakeDragging();
    }

    /* access modifiers changed from: 0000 */
    public void snapToPage() {
        View findSnapView = this.mPagerSnapHelper.findSnapView(this.mLayoutManager);
        if (findSnapView != null) {
            int[] calculateDistanceToFinalSnap = this.mPagerSnapHelper.calculateDistanceToFinalSnap(this.mLayoutManager, findSnapView);
            if (!(calculateDistanceToFinalSnap[0] == 0 && calculateDistanceToFinalSnap[1] == 0)) {
                this.mRecyclerView.smoothScrollBy(calculateDistanceToFinalSnap[0], calculateDistanceToFinalSnap[1]);
            }
        }
    }

    public void setUserInputEnabled(boolean z) {
        this.mUserInputEnabled = z;
    }

    public boolean isUserInputEnabled() {
        return this.mUserInputEnabled;
    }

    public void setOffscreenPageLimit(int i) {
        if (i >= 1 || i == 0) {
            this.mOffscreenPageLimit = i;
            this.mRecyclerView.requestLayout();
            return;
        }
        throw new IllegalArgumentException("Offscreen page limit must be OFFSCREEN_PAGE_LIMIT_DEFAULT or a number > 0");
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public void registerOnPageChangeCallback(@NonNull OnPageChangeCallback onPageChangeCallback) {
        this.mExternalPageChangeCallbacks.addOnPageChangeCallback(onPageChangeCallback);
    }

    public void unregisterOnPageChangeCallback(@NonNull OnPageChangeCallback onPageChangeCallback) {
        this.mExternalPageChangeCallbacks.removeOnPageChangeCallback(onPageChangeCallback);
    }

    public void setPageTransformer(@Nullable PageTransformer pageTransformer) {
        if (pageTransformer != this.mPageTransformerAdapter.getPageTransformer()) {
            this.mPageTransformerAdapter.setPageTransformer(pageTransformer);
            requestTransform();
        }
    }

    public void requestTransform() {
        if (this.mPageTransformerAdapter.getPageTransformer() != null) {
            float relativeScrollPosition = this.mScrollEventAdapter.getRelativeScrollPosition();
            int i = (int) relativeScrollPosition;
            float f = relativeScrollPosition - ((float) i);
            this.mPageTransformerAdapter.onPageScrolled(i, f, Math.round(((float) getPageSize()) * f));
        }
    }
}
