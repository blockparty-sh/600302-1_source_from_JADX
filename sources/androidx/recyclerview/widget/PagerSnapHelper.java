package androidx.recyclerview.widget;

import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.RecyclerView.SmoothScroller.Action;
import androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider;
import androidx.recyclerview.widget.RecyclerView.State;

public class PagerSnapHelper extends SnapHelper {
    private static final int MAX_SCROLL_ON_FLING_DURATION = 100;
    @Nullable
    private OrientationHelper mHorizontalHelper;
    @Nullable
    private OrientationHelper mVerticalHelper;

    @Nullable
    public int[] calculateDistanceToFinalSnap(@NonNull LayoutManager layoutManager, @NonNull View view) {
        int[] iArr = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            iArr[0] = distanceToCenter(layoutManager, view, getHorizontalHelper(layoutManager));
        } else {
            iArr[0] = 0;
        }
        if (layoutManager.canScrollVertically()) {
            iArr[1] = distanceToCenter(layoutManager, view, getVerticalHelper(layoutManager));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Nullable
    public View findSnapView(LayoutManager layoutManager) {
        if (layoutManager.canScrollVertically()) {
            return findCenterView(layoutManager, getVerticalHelper(layoutManager));
        }
        if (layoutManager.canScrollHorizontally()) {
            return findCenterView(layoutManager, getHorizontalHelper(layoutManager));
        }
        return null;
    }

    public int findTargetSnapPosition(LayoutManager layoutManager, int i, int i2) {
        int itemCount = layoutManager.getItemCount();
        if (itemCount == 0) {
            return -1;
        }
        OrientationHelper orientationHelper = getOrientationHelper(layoutManager);
        if (orientationHelper == null) {
            return -1;
        }
        int childCount = layoutManager.getChildCount();
        View view = null;
        View view2 = null;
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MAX_VALUE;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = layoutManager.getChildAt(i5);
            if (childAt != null) {
                int distanceToCenter = distanceToCenter(layoutManager, childAt, orientationHelper);
                if (distanceToCenter <= 0 && distanceToCenter > i3) {
                    view2 = childAt;
                    i3 = distanceToCenter;
                }
                if (distanceToCenter >= 0 && distanceToCenter < i4) {
                    view = childAt;
                    i4 = distanceToCenter;
                }
            }
        }
        boolean isForwardFling = isForwardFling(layoutManager, i, i2);
        if (isForwardFling && view != null) {
            return layoutManager.getPosition(view);
        }
        if (!isForwardFling && view2 != null) {
            return layoutManager.getPosition(view2);
        }
        if (!isForwardFling) {
            view2 = view;
        }
        if (view2 == null) {
            return -1;
        }
        int position = layoutManager.getPosition(view2) + (isReverseLayout(layoutManager) == isForwardFling ? -1 : 1);
        if (position < 0 || position >= itemCount) {
            return -1;
        }
        return position;
    }

    private boolean isForwardFling(LayoutManager layoutManager, int i, int i2) {
        boolean z = true;
        if (layoutManager.canScrollHorizontally()) {
            if (i <= 0) {
                z = false;
            }
            return z;
        }
        if (i2 <= 0) {
            z = false;
        }
        return z;
    }

    private boolean isReverseLayout(LayoutManager layoutManager) {
        int itemCount = layoutManager.getItemCount();
        if (layoutManager instanceof ScrollVectorProvider) {
            boolean z = true;
            PointF computeScrollVectorForPosition = ((ScrollVectorProvider) layoutManager).computeScrollVectorForPosition(itemCount - 1);
            if (computeScrollVectorForPosition != null) {
                if (computeScrollVectorForPosition.x >= 0.0f && computeScrollVectorForPosition.y >= 0.0f) {
                    z = false;
                }
                return z;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public LinearSmoothScroller createSnapScroller(LayoutManager layoutManager) {
        if (!(layoutManager instanceof ScrollVectorProvider)) {
            return null;
        }
        return new LinearSmoothScroller(this.mRecyclerView.getContext()) {
            /* access modifiers changed from: protected */
            public void onTargetFound(View view, State state, Action action) {
                PagerSnapHelper pagerSnapHelper = PagerSnapHelper.this;
                int[] calculateDistanceToFinalSnap = pagerSnapHelper.calculateDistanceToFinalSnap(pagerSnapHelper.mRecyclerView.getLayoutManager(), view);
                int i = calculateDistanceToFinalSnap[0];
                int i2 = calculateDistanceToFinalSnap[1];
                int calculateTimeForDeceleration = calculateTimeForDeceleration(Math.max(Math.abs(i), Math.abs(i2)));
                if (calculateTimeForDeceleration > 0) {
                    action.update(i, i2, calculateTimeForDeceleration, this.mDecelerateInterpolator);
                }
            }

            /* access modifiers changed from: protected */
            public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return 100.0f / ((float) displayMetrics.densityDpi);
            }

            /* access modifiers changed from: protected */
            public int calculateTimeForScrolling(int i) {
                return Math.min(100, super.calculateTimeForScrolling(i));
            }
        };
    }

    private int distanceToCenter(@NonNull LayoutManager layoutManager, @NonNull View view, OrientationHelper orientationHelper) {
        int i;
        int decoratedStart = orientationHelper.getDecoratedStart(view) + (orientationHelper.getDecoratedMeasurement(view) / 2);
        if (layoutManager.getClipToPadding()) {
            i = orientationHelper.getStartAfterPadding() + (orientationHelper.getTotalSpace() / 2);
        } else {
            i = orientationHelper.getEnd() / 2;
        }
        return decoratedStart - i;
    }

    @Nullable
    private View findCenterView(LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int i;
        int childCount = layoutManager.getChildCount();
        View view = null;
        if (childCount == 0) {
            return null;
        }
        if (layoutManager.getClipToPadding()) {
            i = orientationHelper.getStartAfterPadding() + (orientationHelper.getTotalSpace() / 2);
        } else {
            i = orientationHelper.getEnd() / 2;
        }
        int i2 = Integer.MAX_VALUE;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = layoutManager.getChildAt(i3);
            int abs = Math.abs((orientationHelper.getDecoratedStart(childAt) + (orientationHelper.getDecoratedMeasurement(childAt) / 2)) - i);
            if (abs < i2) {
                view = childAt;
                i2 = abs;
            }
        }
        return view;
    }

    @Nullable
    private OrientationHelper getOrientationHelper(LayoutManager layoutManager) {
        if (layoutManager.canScrollVertically()) {
            return getVerticalHelper(layoutManager);
        }
        if (layoutManager.canScrollHorizontally()) {
            return getHorizontalHelper(layoutManager);
        }
        return null;
    }

    @NonNull
    private OrientationHelper getVerticalHelper(@NonNull LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.mVerticalHelper;
        if (orientationHelper == null || orientationHelper.mLayoutManager != layoutManager) {
            this.mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return this.mVerticalHelper;
    }

    @NonNull
    private OrientationHelper getHorizontalHelper(@NonNull LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.mHorizontalHelper;
        if (orientationHelper == null || orientationHelper.mLayoutManager != layoutManager) {
            this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return this.mHorizontalHelper;
    }
}
