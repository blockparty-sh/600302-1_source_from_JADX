package androidx.viewpager2.widget;

import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;
import java.util.Locale;

final class ScrollEventAdapter extends OnScrollListener {
    private static final int NO_POSITION = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_IN_PROGRESS_FAKE_DRAG = 4;
    private static final int STATE_IN_PROGRESS_IMMEDIATE_SCROLL = 3;
    private static final int STATE_IN_PROGRESS_MANUAL_DRAG = 1;
    private static final int STATE_IN_PROGRESS_SMOOTH_SCROLL = 2;
    private static final MarginLayoutParams ZERO_MARGIN_LAYOUT_PARAMS = new MarginLayoutParams(-1, -1);
    private int mAdapterState;
    private OnPageChangeCallback mCallback;
    private boolean mDispatchSelected;
    private int mDragStartPosition;
    private boolean mFakeDragging;
    @NonNull
    private final LinearLayoutManager mLayoutManager;
    private boolean mScrollHappened;
    private int mScrollState;
    private ScrollEventValues mScrollValues = new ScrollEventValues();
    private int mTarget;

    private static final class ScrollEventValues {
        float mOffset;
        int mOffsetPx;
        int mPosition;

        ScrollEventValues() {
        }

        /* access modifiers changed from: 0000 */
        public void reset() {
            this.mPosition = -1;
            this.mOffset = 0.0f;
            this.mOffsetPx = 0;
        }
    }

    static {
        ZERO_MARGIN_LAYOUT_PARAMS.setMargins(0, 0, 0, 0);
    }

    ScrollEventAdapter(@NonNull LinearLayoutManager linearLayoutManager) {
        this.mLayoutManager = linearLayoutManager;
        resetState();
    }

    private void resetState() {
        this.mAdapterState = 0;
        this.mScrollState = 0;
        this.mScrollValues.reset();
        this.mDragStartPosition = -1;
        this.mTarget = -1;
        this.mDispatchSelected = false;
        this.mScrollHappened = false;
        this.mFakeDragging = false;
    }

    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
        boolean z = true;
        if (this.mAdapterState != 1 && i == 1) {
            startDrag(false);
        } else if (!isInAnyDraggingState() || i != 2) {
            if (isInAnyDraggingState() && i == 0) {
                updateScrollEventValues();
                if (!this.mScrollHappened) {
                    if (this.mScrollValues.mPosition != -1) {
                        dispatchScrolled(this.mScrollValues.mPosition, 0.0f, 0);
                    }
                } else if (this.mScrollValues.mOffsetPx != 0) {
                    z = false;
                } else if (this.mDragStartPosition != this.mScrollValues.mPosition) {
                    dispatchSelected(this.mScrollValues.mPosition);
                }
                if (z) {
                    dispatchStateChanged(0);
                    resetState();
                }
            }
        } else {
            if (this.mScrollHappened) {
                dispatchStateChanged(2);
                this.mDispatchSelected = true;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        if ((r4 < 0) == isLayoutRTL()) goto L_0x001f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onScrolled(@androidx.annotation.NonNull androidx.recyclerview.widget.RecyclerView r3, int r4, int r5) {
        /*
            r2 = this;
            r3 = 1
            r2.mScrollHappened = r3
            r2.updateScrollEventValues()
            boolean r0 = r2.mDispatchSelected
            r1 = 0
            if (r0 == 0) goto L_0x003e
            r2.mDispatchSelected = r1
            if (r5 > 0) goto L_0x001f
            if (r5 != 0) goto L_0x001d
            if (r4 >= 0) goto L_0x0015
            r4 = 1
            goto L_0x0016
        L_0x0015:
            r4 = 0
        L_0x0016:
            boolean r5 = r2.isLayoutRTL()
            if (r4 != r5) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r4 = 0
            goto L_0x0020
        L_0x001f:
            r4 = 1
        L_0x0020:
            if (r4 == 0) goto L_0x002e
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r4 = r2.mScrollValues
            int r4 = r4.mOffsetPx
            if (r4 == 0) goto L_0x002e
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r4 = r2.mScrollValues
            int r4 = r4.mPosition
            int r4 = r4 + r3
            goto L_0x0032
        L_0x002e:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r4 = r2.mScrollValues
            int r4 = r4.mPosition
        L_0x0032:
            r2.mTarget = r4
            int r4 = r2.mDragStartPosition
            int r5 = r2.mTarget
            if (r4 == r5) goto L_0x0049
            r2.dispatchSelected(r5)
            goto L_0x0049
        L_0x003e:
            int r4 = r2.mAdapterState
            if (r4 != 0) goto L_0x0049
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r4 = r2.mScrollValues
            int r4 = r4.mPosition
            r2.dispatchSelected(r4)
        L_0x0049:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r4 = r2.mScrollValues
            int r4 = r4.mPosition
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r5 = r2.mScrollValues
            float r5 = r5.mOffset
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r0 = r2.mScrollValues
            int r0 = r0.mOffsetPx
            r2.dispatchScrolled(r4, r5, r0)
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r4 = r2.mScrollValues
            int r4 = r4.mPosition
            int r5 = r2.mTarget
            if (r4 == r5) goto L_0x0063
            r4 = -1
            if (r5 != r4) goto L_0x0073
        L_0x0063:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r4 = r2.mScrollValues
            int r4 = r4.mOffsetPx
            if (r4 != 0) goto L_0x0073
            int r4 = r2.mScrollState
            if (r4 == r3) goto L_0x0073
            r2.dispatchStateChanged(r1)
            r2.resetState()
        L_0x0073:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager2.widget.ScrollEventAdapter.onScrolled(androidx.recyclerview.widget.RecyclerView, int, int):void");
    }

    private void updateScrollEventValues() {
        int i;
        int i2;
        int i3;
        float f;
        ScrollEventValues scrollEventValues = this.mScrollValues;
        scrollEventValues.mPosition = this.mLayoutManager.findFirstVisibleItemPosition();
        if (scrollEventValues.mPosition == -1) {
            scrollEventValues.reset();
            return;
        }
        View findViewByPosition = this.mLayoutManager.findViewByPosition(scrollEventValues.mPosition);
        if (findViewByPosition == null) {
            scrollEventValues.reset();
            return;
        }
        MarginLayoutParams marginLayoutParams = findViewByPosition.getLayoutParams() instanceof MarginLayoutParams ? (MarginLayoutParams) findViewByPosition.getLayoutParams() : ZERO_MARGIN_LAYOUT_PARAMS;
        if (this.mLayoutManager.getOrientation() == 0) {
            i = findViewByPosition.getWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            if (!isLayoutRTL()) {
                i3 = findViewByPosition.getLeft();
                i2 = marginLayoutParams.leftMargin;
            } else {
                i3 = i - findViewByPosition.getRight();
                i2 = marginLayoutParams.rightMargin;
            }
        } else {
            i = findViewByPosition.getHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            i3 = findViewByPosition.getTop();
            i2 = marginLayoutParams.topMargin;
        }
        scrollEventValues.mOffsetPx = -(i3 - i2);
        if (scrollEventValues.mOffsetPx >= 0) {
            if (i == 0) {
                f = 0.0f;
            } else {
                f = ((float) scrollEventValues.mOffsetPx) / ((float) i);
            }
            scrollEventValues.mOffset = f;
        } else if (new AnimateLayoutChangeDetector(this.mLayoutManager).mayHaveInterferingAnimations()) {
            throw new IllegalStateException("Page(s) contain a ViewGroup with a LayoutTransition (or animateLayoutChanges=\"true\"), which interferes with the scrolling animation. Make sure to call getLayoutTransition().setAnimateParentHierarchy(false) on all ViewGroups with a LayoutTransition before an animation is started.");
        } else {
            throw new IllegalStateException(String.format(Locale.US, "Page can only be offset by a positive amount, not by %d", new Object[]{Integer.valueOf(scrollEventValues.mOffsetPx)}));
        }
    }

    private void startDrag(boolean z) {
        this.mFakeDragging = z;
        this.mAdapterState = z ? 4 : 1;
        int i = this.mTarget;
        if (i != -1) {
            this.mDragStartPosition = i;
            this.mTarget = -1;
        } else if (this.mDragStartPosition == -1) {
            this.mDragStartPosition = getPosition();
        }
        dispatchStateChanged(1);
    }

    /* access modifiers changed from: 0000 */
    public void notifyProgrammaticScroll(int i, boolean z) {
        this.mAdapterState = z ? 2 : 3;
        boolean z2 = this.mTarget != i;
        this.mTarget = i;
        dispatchStateChanged(2);
        if (z2) {
            dispatchSelected(i);
        }
    }

    /* access modifiers changed from: 0000 */
    public void notifyBeginFakeDrag() {
        this.mAdapterState = 4;
        startDrag(true);
    }

    /* access modifiers changed from: 0000 */
    public void notifyEndFakeDrag() {
        if (!isDragging() || this.mFakeDragging) {
            this.mFakeDragging = false;
            updateScrollEventValues();
            if (this.mScrollValues.mOffsetPx == 0) {
                if (this.mScrollValues.mPosition != this.mDragStartPosition) {
                    dispatchSelected(this.mScrollValues.mPosition);
                }
                dispatchStateChanged(0);
                resetState();
            } else {
                dispatchStateChanged(2);
            }
        }
    }

    private boolean isLayoutRTL() {
        return this.mLayoutManager.getLayoutDirection() == 1;
    }

    /* access modifiers changed from: 0000 */
    public void setOnPageChangeCallback(OnPageChangeCallback onPageChangeCallback) {
        this.mCallback = onPageChangeCallback;
    }

    /* access modifiers changed from: 0000 */
    public int getScrollState() {
        return this.mScrollState;
    }

    /* access modifiers changed from: 0000 */
    public boolean isIdle() {
        return this.mScrollState == 0;
    }

    /* access modifiers changed from: 0000 */
    public boolean isDragging() {
        return this.mScrollState == 1;
    }

    /* access modifiers changed from: 0000 */
    public boolean isFakeDragging() {
        return this.mFakeDragging;
    }

    private boolean isInAnyDraggingState() {
        int i = this.mAdapterState;
        return i == 1 || i == 4;
    }

    /* access modifiers changed from: 0000 */
    public float getRelativeScrollPosition() {
        updateScrollEventValues();
        return ((float) this.mScrollValues.mPosition) + this.mScrollValues.mOffset;
    }

    private void dispatchStateChanged(int i) {
        if ((this.mAdapterState != 3 || this.mScrollState != 0) && this.mScrollState != i) {
            this.mScrollState = i;
            OnPageChangeCallback onPageChangeCallback = this.mCallback;
            if (onPageChangeCallback != null) {
                onPageChangeCallback.onPageScrollStateChanged(i);
            }
        }
    }

    private void dispatchSelected(int i) {
        OnPageChangeCallback onPageChangeCallback = this.mCallback;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.onPageSelected(i);
        }
    }

    private void dispatchScrolled(int i, float f, int i2) {
        OnPageChangeCallback onPageChangeCallback = this.mCallback;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.onPageScrolled(i, f, i2);
        }
    }

    private int getPosition() {
        return this.mLayoutManager.findFirstVisibleItemPosition();
    }
}
