package com.google.android.flexbox;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.RecyclerView.LayoutManager.Properties;
import androidx.recyclerview.widget.RecyclerView.Recycler;
import androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider;
import androidx.recyclerview.widget.RecyclerView.State;
import java.util.ArrayList;
import java.util.List;

public class FlexboxLayoutManager extends LayoutManager implements FlexContainer, ScrollVectorProvider {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean DEBUG = false;
    private static final String TAG = "FlexboxLayoutManager";
    private static final Rect TEMP_RECT = new Rect();
    private int mAlignItems;
    private AnchorInfo mAnchorInfo;
    private final Context mContext;
    private int mDirtyPosition;
    /* access modifiers changed from: private */
    public int mFlexDirection;
    /* access modifiers changed from: private */
    public List<FlexLine> mFlexLines;
    private FlexLinesResult mFlexLinesResult;
    /* access modifiers changed from: private */
    public int mFlexWrap;
    /* access modifiers changed from: private */
    public final FlexboxHelper mFlexboxHelper;
    private boolean mFromBottomToTop;
    /* access modifiers changed from: private */
    public boolean mIsRtl;
    private int mJustifyContent;
    private int mLastHeight;
    private int mLastWidth;
    private LayoutState mLayoutState;
    private int mMaxLine;
    /* access modifiers changed from: private */
    public OrientationHelper mOrientationHelper;
    private View mParent;
    private SavedState mPendingSavedState;
    private int mPendingScrollPosition;
    private int mPendingScrollPositionOffset;
    private boolean mRecycleChildrenOnDetach;
    private Recycler mRecycler;
    private State mState;
    private OrientationHelper mSubOrientationHelper;
    private SparseArray<View> mViewCache;

    private class AnchorInfo {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        /* access modifiers changed from: private */
        public boolean mAssignedFromSavedState;
        /* access modifiers changed from: private */
        public int mCoordinate;
        /* access modifiers changed from: private */
        public int mFlexLinePosition;
        /* access modifiers changed from: private */
        public boolean mLayoutFromEnd;
        /* access modifiers changed from: private */
        public int mPerpendicularCoordinate;
        /* access modifiers changed from: private */
        public int mPosition;
        /* access modifiers changed from: private */
        public boolean mValid;

        static {
            Class<FlexboxLayoutManager> cls = FlexboxLayoutManager.class;
        }

        private AnchorInfo() {
            this.mPerpendicularCoordinate = 0;
        }

        /* access modifiers changed from: private */
        public void reset() {
            this.mPosition = -1;
            this.mFlexLinePosition = -1;
            this.mCoordinate = Integer.MIN_VALUE;
            boolean z = false;
            this.mValid = false;
            this.mAssignedFromSavedState = false;
            if (FlexboxLayoutManager.this.isMainAxisDirectionHorizontal()) {
                if (FlexboxLayoutManager.this.mFlexWrap == 0) {
                    if (FlexboxLayoutManager.this.mFlexDirection == 1) {
                        z = true;
                    }
                    this.mLayoutFromEnd = z;
                    return;
                }
                if (FlexboxLayoutManager.this.mFlexWrap == 2) {
                    z = true;
                }
                this.mLayoutFromEnd = z;
            } else if (FlexboxLayoutManager.this.mFlexWrap == 0) {
                if (FlexboxLayoutManager.this.mFlexDirection == 3) {
                    z = true;
                }
                this.mLayoutFromEnd = z;
            } else {
                if (FlexboxLayoutManager.this.mFlexWrap == 2) {
                    z = true;
                }
                this.mLayoutFromEnd = z;
            }
        }

        /* access modifiers changed from: private */
        public void assignCoordinateFromPadding() {
            int i;
            int i2;
            if (FlexboxLayoutManager.this.isMainAxisDirectionHorizontal() || !FlexboxLayoutManager.this.mIsRtl) {
                if (this.mLayoutFromEnd) {
                    i = FlexboxLayoutManager.this.mOrientationHelper.getEndAfterPadding();
                } else {
                    i = FlexboxLayoutManager.this.mOrientationHelper.getStartAfterPadding();
                }
                this.mCoordinate = i;
                return;
            }
            if (this.mLayoutFromEnd) {
                i2 = FlexboxLayoutManager.this.mOrientationHelper.getEndAfterPadding();
            } else {
                i2 = FlexboxLayoutManager.this.getWidth() - FlexboxLayoutManager.this.mOrientationHelper.getStartAfterPadding();
            }
            this.mCoordinate = i2;
        }

        /* access modifiers changed from: private */
        public void assignFromView(View view) {
            if (FlexboxLayoutManager.this.isMainAxisDirectionHorizontal() || !FlexboxLayoutManager.this.mIsRtl) {
                if (this.mLayoutFromEnd) {
                    this.mCoordinate = FlexboxLayoutManager.this.mOrientationHelper.getDecoratedEnd(view) + FlexboxLayoutManager.this.mOrientationHelper.getTotalSpaceChange();
                } else {
                    this.mCoordinate = FlexboxLayoutManager.this.mOrientationHelper.getDecoratedStart(view);
                }
            } else if (this.mLayoutFromEnd) {
                this.mCoordinate = FlexboxLayoutManager.this.mOrientationHelper.getDecoratedStart(view) + FlexboxLayoutManager.this.mOrientationHelper.getTotalSpaceChange();
            } else {
                this.mCoordinate = FlexboxLayoutManager.this.mOrientationHelper.getDecoratedEnd(view);
            }
            this.mPosition = FlexboxLayoutManager.this.getPosition(view);
            int i = 0;
            this.mAssignedFromSavedState = false;
            int[] iArr = FlexboxLayoutManager.this.mFlexboxHelper.mIndexToFlexLine;
            int i2 = this.mPosition;
            if (i2 == -1) {
                i2 = 0;
            }
            int i3 = iArr[i2];
            if (i3 != -1) {
                i = i3;
            }
            this.mFlexLinePosition = i;
            if (FlexboxLayoutManager.this.mFlexLines.size() > this.mFlexLinePosition) {
                this.mPosition = ((FlexLine) FlexboxLayoutManager.this.mFlexLines.get(this.mFlexLinePosition)).mFirstIndex;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("AnchorInfo{mPosition=");
            sb.append(this.mPosition);
            sb.append(", mFlexLinePosition=");
            sb.append(this.mFlexLinePosition);
            sb.append(", mCoordinate=");
            sb.append(this.mCoordinate);
            sb.append(", mPerpendicularCoordinate=");
            sb.append(this.mPerpendicularCoordinate);
            sb.append(", mLayoutFromEnd=");
            sb.append(this.mLayoutFromEnd);
            sb.append(", mValid=");
            sb.append(this.mValid);
            sb.append(", mAssignedFromSavedState=");
            sb.append(this.mAssignedFromSavedState);
            sb.append('}');
            return sb.toString();
        }
    }

    public static class LayoutParams extends androidx.recyclerview.widget.RecyclerView.LayoutParams implements FlexItem {
        public static final Creator<LayoutParams> CREATOR = new Creator<LayoutParams>() {
            public LayoutParams createFromParcel(Parcel parcel) {
                return new LayoutParams(parcel);
            }

            public LayoutParams[] newArray(int i) {
                return new LayoutParams[i];
            }
        };
        private int mAlignSelf = -1;
        private float mFlexBasisPercent = -1.0f;
        private float mFlexGrow = 0.0f;
        private float mFlexShrink = 1.0f;
        private int mMaxHeight = 16777215;
        private int mMaxWidth = 16777215;
        private int mMinHeight;
        private int mMinWidth;
        private boolean mWrapBefore;

        public int describeContents() {
            return 0;
        }

        public int getOrder() {
            return 1;
        }

        public int getWidth() {
            return this.width;
        }

        public void setWidth(int i) {
            this.width = i;
        }

        public int getHeight() {
            return this.height;
        }

        public void setHeight(int i) {
            this.height = i;
        }

        public float getFlexGrow() {
            return this.mFlexGrow;
        }

        public void setFlexGrow(float f) {
            this.mFlexGrow = f;
        }

        public float getFlexShrink() {
            return this.mFlexShrink;
        }

        public void setFlexShrink(float f) {
            this.mFlexShrink = f;
        }

        public int getAlignSelf() {
            return this.mAlignSelf;
        }

        public void setAlignSelf(int i) {
            this.mAlignSelf = i;
        }

        public int getMinWidth() {
            return this.mMinWidth;
        }

        public void setMinWidth(int i) {
            this.mMinWidth = i;
        }

        public int getMinHeight() {
            return this.mMinHeight;
        }

        public void setMinHeight(int i) {
            this.mMinHeight = i;
        }

        public int getMaxWidth() {
            return this.mMaxWidth;
        }

        public void setMaxWidth(int i) {
            this.mMaxWidth = i;
        }

        public int getMaxHeight() {
            return this.mMaxHeight;
        }

        public void setMaxHeight(int i) {
            this.mMaxHeight = i;
        }

        public boolean isWrapBefore() {
            return this.mWrapBefore;
        }

        public void setWrapBefore(boolean z) {
            this.mWrapBefore = z;
        }

        public float getFlexBasisPercent() {
            return this.mFlexBasisPercent;
        }

        public void setFlexBasisPercent(float f) {
            this.mFlexBasisPercent = f;
        }

        public int getMarginLeft() {
            return this.leftMargin;
        }

        public int getMarginTop() {
            return this.topMargin;
        }

        public int getMarginRight() {
            return this.rightMargin;
        }

        public int getMarginBottom() {
            return this.bottomMargin;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(androidx.recyclerview.widget.RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((androidx.recyclerview.widget.RecyclerView.LayoutParams) layoutParams);
            this.mFlexGrow = layoutParams.mFlexGrow;
            this.mFlexShrink = layoutParams.mFlexShrink;
            this.mAlignSelf = layoutParams.mAlignSelf;
            this.mFlexBasisPercent = layoutParams.mFlexBasisPercent;
            this.mMinWidth = layoutParams.mMinWidth;
            this.mMinHeight = layoutParams.mMinHeight;
            this.mMaxWidth = layoutParams.mMaxWidth;
            this.mMaxHeight = layoutParams.mMaxHeight;
            this.mWrapBefore = layoutParams.mWrapBefore;
        }

        public void setOrder(int i) {
            throw new UnsupportedOperationException("Setting the order in the FlexboxLayoutManager is not supported. Use FlexboxLayout if you need to reorder using the attribute.");
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(this.mFlexGrow);
            parcel.writeFloat(this.mFlexShrink);
            parcel.writeInt(this.mAlignSelf);
            parcel.writeFloat(this.mFlexBasisPercent);
            parcel.writeInt(this.mMinWidth);
            parcel.writeInt(this.mMinHeight);
            parcel.writeInt(this.mMaxWidth);
            parcel.writeInt(this.mMaxHeight);
            parcel.writeByte(this.mWrapBefore ? (byte) 1 : 0);
            parcel.writeInt(this.bottomMargin);
            parcel.writeInt(this.leftMargin);
            parcel.writeInt(this.rightMargin);
            parcel.writeInt(this.topMargin);
            parcel.writeInt(this.height);
            parcel.writeInt(this.width);
        }

        protected LayoutParams(Parcel parcel) {
            super(-2, -2);
            this.mFlexGrow = parcel.readFloat();
            this.mFlexShrink = parcel.readFloat();
            this.mAlignSelf = parcel.readInt();
            this.mFlexBasisPercent = parcel.readFloat();
            this.mMinWidth = parcel.readInt();
            this.mMinHeight = parcel.readInt();
            this.mMaxWidth = parcel.readInt();
            this.mMaxHeight = parcel.readInt();
            this.mWrapBefore = parcel.readByte() != 0;
            this.bottomMargin = parcel.readInt();
            this.leftMargin = parcel.readInt();
            this.rightMargin = parcel.readInt();
            this.topMargin = parcel.readInt();
            this.height = parcel.readInt();
            this.width = parcel.readInt();
        }
    }

    private static class LayoutState {
        private static final int ITEM_DIRECTION_TAIL = 1;
        private static final int LAYOUT_END = 1;
        private static final int LAYOUT_START = -1;
        private static final int SCROLLING_OFFSET_NaN = Integer.MIN_VALUE;
        /* access modifiers changed from: private */
        public int mAvailable;
        /* access modifiers changed from: private */
        public int mFlexLinePosition;
        /* access modifiers changed from: private */
        public boolean mInfinite;
        /* access modifiers changed from: private */
        public int mItemDirection;
        /* access modifiers changed from: private */
        public int mLastScrollDelta;
        /* access modifiers changed from: private */
        public int mLayoutDirection;
        /* access modifiers changed from: private */
        public int mOffset;
        /* access modifiers changed from: private */
        public int mPosition;
        /* access modifiers changed from: private */
        public int mScrollingOffset;
        /* access modifiers changed from: private */
        public boolean mShouldRecycle;

        private LayoutState() {
            this.mItemDirection = 1;
            this.mLayoutDirection = 1;
        }

        /* access modifiers changed from: private */
        public boolean hasMore(State state, List<FlexLine> list) {
            int i = this.mPosition;
            if (i >= 0 && i < state.getItemCount()) {
                int i2 = this.mFlexLinePosition;
                if (i2 >= 0 && i2 < list.size()) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("LayoutState{mAvailable=");
            sb.append(this.mAvailable);
            sb.append(", mFlexLinePosition=");
            sb.append(this.mFlexLinePosition);
            sb.append(", mPosition=");
            sb.append(this.mPosition);
            sb.append(", mOffset=");
            sb.append(this.mOffset);
            sb.append(", mScrollingOffset=");
            sb.append(this.mScrollingOffset);
            sb.append(", mLastScrollDelta=");
            sb.append(this.mLastScrollDelta);
            sb.append(", mItemDirection=");
            sb.append(this.mItemDirection);
            sb.append(", mLayoutDirection=");
            sb.append(this.mLayoutDirection);
            sb.append('}');
            return sb.toString();
        }
    }

    private static class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        /* access modifiers changed from: private */
        public int mAnchorOffset;
        /* access modifiers changed from: private */
        public int mAnchorPosition;

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mAnchorOffset);
        }

        SavedState() {
        }

        private SavedState(Parcel parcel) {
            this.mAnchorPosition = parcel.readInt();
            this.mAnchorOffset = parcel.readInt();
        }

        private SavedState(SavedState savedState) {
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.mAnchorOffset = savedState.mAnchorOffset;
        }

        /* access modifiers changed from: private */
        public void invalidateAnchor() {
            this.mAnchorPosition = -1;
        }

        /* access modifiers changed from: private */
        public boolean hasValidAnchor(int i) {
            int i2 = this.mAnchorPosition;
            return i2 >= 0 && i2 < i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("SavedState{mAnchorPosition=");
            sb.append(this.mAnchorPosition);
            sb.append(", mAnchorOffset=");
            sb.append(this.mAnchorOffset);
            sb.append('}');
            return sb.toString();
        }
    }

    public int getAlignContent() {
        return 5;
    }

    public void onNewFlexLineAdded(FlexLine flexLine) {
    }

    public FlexboxLayoutManager(Context context) {
        this(context, 0, 1);
    }

    public FlexboxLayoutManager(Context context, int i) {
        this(context, i, 1);
    }

    public FlexboxLayoutManager(Context context, int i, int i2) {
        this.mMaxLine = -1;
        this.mFlexLines = new ArrayList();
        this.mFlexboxHelper = new FlexboxHelper(this);
        this.mAnchorInfo = new AnchorInfo();
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mLastWidth = Integer.MIN_VALUE;
        this.mLastHeight = Integer.MIN_VALUE;
        this.mViewCache = new SparseArray<>();
        this.mDirtyPosition = -1;
        this.mFlexLinesResult = new FlexLinesResult();
        setFlexDirection(i);
        setFlexWrap(i2);
        setAlignItems(4);
        setAutoMeasureEnabled(true);
        this.mContext = context;
    }

    public FlexboxLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mMaxLine = -1;
        this.mFlexLines = new ArrayList();
        this.mFlexboxHelper = new FlexboxHelper(this);
        this.mAnchorInfo = new AnchorInfo();
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mLastWidth = Integer.MIN_VALUE;
        this.mLastHeight = Integer.MIN_VALUE;
        this.mViewCache = new SparseArray<>();
        this.mDirtyPosition = -1;
        this.mFlexLinesResult = new FlexLinesResult();
        Properties properties = getProperties(context, attributeSet, i, i2);
        int i3 = properties.orientation;
        if (i3 != 0) {
            if (i3 == 1) {
                if (properties.reverseLayout) {
                    setFlexDirection(3);
                } else {
                    setFlexDirection(2);
                }
            }
        } else if (properties.reverseLayout) {
            setFlexDirection(1);
        } else {
            setFlexDirection(0);
        }
        setFlexWrap(1);
        setAlignItems(4);
        setAutoMeasureEnabled(true);
        this.mContext = context;
    }

    public int getFlexDirection() {
        return this.mFlexDirection;
    }

    public void setFlexDirection(int i) {
        if (this.mFlexDirection != i) {
            removeAllViews();
            this.mFlexDirection = i;
            this.mOrientationHelper = null;
            this.mSubOrientationHelper = null;
            clearFlexLines();
            requestLayout();
        }
    }

    public int getFlexWrap() {
        return this.mFlexWrap;
    }

    public void setFlexWrap(int i) {
        if (i != 2) {
            int i2 = this.mFlexWrap;
            if (i2 != i) {
                if (i2 == 0 || i == 0) {
                    removeAllViews();
                    clearFlexLines();
                }
                this.mFlexWrap = i;
                this.mOrientationHelper = null;
                this.mSubOrientationHelper = null;
                requestLayout();
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("wrap_reverse is not supported in FlexboxLayoutManager");
    }

    public int getJustifyContent() {
        return this.mJustifyContent;
    }

    public void setJustifyContent(int i) {
        if (this.mJustifyContent != i) {
            this.mJustifyContent = i;
            requestLayout();
        }
    }

    public int getAlignItems() {
        return this.mAlignItems;
    }

    public void setAlignItems(int i) {
        int i2 = this.mAlignItems;
        if (i2 != i) {
            if (i2 == 4 || i == 4) {
                removeAllViews();
                clearFlexLines();
            }
            this.mAlignItems = i;
            requestLayout();
        }
    }

    public void setAlignContent(int i) {
        throw new UnsupportedOperationException("Setting the alignContent in the FlexboxLayoutManager is not supported. Use FlexboxLayout if you need to use this attribute.");
    }

    public int getMaxLine() {
        return this.mMaxLine;
    }

    public void setMaxLine(int i) {
        if (this.mMaxLine != i) {
            this.mMaxLine = i;
            requestLayout();
        }
    }

    public List<FlexLine> getFlexLines() {
        ArrayList arrayList = new ArrayList(this.mFlexLines.size());
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(i);
            if (flexLine.getItemCount() != 0) {
                arrayList.add(flexLine);
            }
        }
        return arrayList;
    }

    public int getDecorationLengthMainAxis(View view, int i, int i2) {
        int topDecorationHeight;
        int bottomDecorationHeight;
        if (isMainAxisDirectionHorizontal()) {
            topDecorationHeight = getLeftDecorationWidth(view);
            bottomDecorationHeight = getRightDecorationWidth(view);
        } else {
            topDecorationHeight = getTopDecorationHeight(view);
            bottomDecorationHeight = getBottomDecorationHeight(view);
        }
        return topDecorationHeight + bottomDecorationHeight;
    }

    public int getDecorationLengthCrossAxis(View view) {
        int leftDecorationWidth;
        int rightDecorationWidth;
        if (isMainAxisDirectionHorizontal()) {
            leftDecorationWidth = getTopDecorationHeight(view);
            rightDecorationWidth = getBottomDecorationHeight(view);
        } else {
            leftDecorationWidth = getLeftDecorationWidth(view);
            rightDecorationWidth = getRightDecorationWidth(view);
        }
        return leftDecorationWidth + rightDecorationWidth;
    }

    public void onNewFlexItemAdded(View view, int i, int i2, FlexLine flexLine) {
        calculateItemDecorationsForChild(view, TEMP_RECT);
        if (isMainAxisDirectionHorizontal()) {
            int leftDecorationWidth = getLeftDecorationWidth(view) + getRightDecorationWidth(view);
            flexLine.mMainSize += leftDecorationWidth;
            flexLine.mDividerLengthInMainSize += leftDecorationWidth;
            return;
        }
        int topDecorationHeight = getTopDecorationHeight(view) + getBottomDecorationHeight(view);
        flexLine.mMainSize += topDecorationHeight;
        flexLine.mDividerLengthInMainSize += topDecorationHeight;
    }

    public int getFlexItemCount() {
        return this.mState.getItemCount();
    }

    public View getFlexItemAt(int i) {
        View view = (View) this.mViewCache.get(i);
        if (view != null) {
            return view;
        }
        return this.mRecycler.getViewForPosition(i);
    }

    public View getReorderedFlexItemAt(int i) {
        return getFlexItemAt(i);
    }

    public int getChildWidthMeasureSpec(int i, int i2, int i3) {
        return getChildMeasureSpec(getWidth(), getWidthMode(), i2, i3, canScrollHorizontally());
    }

    public int getChildHeightMeasureSpec(int i, int i2, int i3) {
        return getChildMeasureSpec(getHeight(), getHeightMode(), i2, i3, canScrollVertically());
    }

    public int getLargestMainSize() {
        if (this.mFlexLines.size() == 0) {
            return 0;
        }
        int i = Integer.MIN_VALUE;
        int size = this.mFlexLines.size();
        for (int i2 = 0; i2 < size; i2++) {
            i = Math.max(i, ((FlexLine) this.mFlexLines.get(i2)).mMainSize);
        }
        return i;
    }

    public int getSumOfCrossSize() {
        int i = 0;
        for (int i2 = 0; i2 < this.mFlexLines.size(); i2++) {
            i += ((FlexLine) this.mFlexLines.get(i2)).mCrossSize;
        }
        return i;
    }

    public void setFlexLines(List<FlexLine> list) {
        this.mFlexLines = list;
    }

    public List<FlexLine> getFlexLinesInternal() {
        return this.mFlexLines;
    }

    public void updateViewCache(int i, View view) {
        this.mViewCache.put(i, view);
    }

    public PointF computeScrollVectorForPosition(int i) {
        if (getChildCount() == 0) {
            return null;
        }
        int i2 = i < getPosition(getChildAt(0)) ? -1 : 1;
        if (isMainAxisDirectionHorizontal()) {
            return new PointF(0.0f, (float) i2);
        }
        return new PointF((float) i2, 0.0f);
    }

    public androidx.recyclerview.widget.RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public androidx.recyclerview.widget.RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    public boolean checkLayoutParams(androidx.recyclerview.widget.RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void onAdapterChanged(Adapter adapter, Adapter adapter2) {
        removeAllViews();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            return new SavedState(savedState);
        }
        SavedState savedState2 = new SavedState();
        if (getChildCount() > 0) {
            View childClosestToStart = getChildClosestToStart();
            savedState2.mAnchorPosition = getPosition(childClosestToStart);
            savedState2.mAnchorOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart) - this.mOrientationHelper.getStartAfterPadding();
        } else {
            savedState2.invalidateAnchor();
        }
        return savedState2;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.mPendingSavedState = (SavedState) parcelable;
            requestLayout();
        }
    }

    public void onItemsAdded(@NonNull RecyclerView recyclerView, int i, int i2) {
        super.onItemsAdded(recyclerView, i, i2);
        updateDirtyPosition(i);
    }

    public void onItemsUpdated(@NonNull RecyclerView recyclerView, int i, int i2, Object obj) {
        super.onItemsUpdated(recyclerView, i, i2, obj);
        updateDirtyPosition(i);
    }

    public void onItemsUpdated(@NonNull RecyclerView recyclerView, int i, int i2) {
        super.onItemsUpdated(recyclerView, i, i2);
        updateDirtyPosition(i);
    }

    public void onItemsRemoved(@NonNull RecyclerView recyclerView, int i, int i2) {
        super.onItemsRemoved(recyclerView, i, i2);
        updateDirtyPosition(i);
    }

    public void onItemsMoved(@NonNull RecyclerView recyclerView, int i, int i2, int i3) {
        super.onItemsMoved(recyclerView, i, i2, i3);
        updateDirtyPosition(Math.min(i, i2));
    }

    private void updateDirtyPosition(int i) {
        if (i < findLastVisibleItemPosition()) {
            int childCount = getChildCount();
            this.mFlexboxHelper.ensureMeasureSpecCache(childCount);
            this.mFlexboxHelper.ensureMeasuredSizeCache(childCount);
            this.mFlexboxHelper.ensureIndexToFlexLine(childCount);
            if (i < this.mFlexboxHelper.mIndexToFlexLine.length) {
                this.mDirtyPosition = i;
                View childClosestToStart = getChildClosestToStart();
                if (childClosestToStart != null) {
                    this.mPendingScrollPosition = getPosition(childClosestToStart);
                    if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
                        this.mPendingScrollPositionOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart) - this.mOrientationHelper.getStartAfterPadding();
                    } else {
                        this.mPendingScrollPositionOffset = this.mOrientationHelper.getDecoratedEnd(childClosestToStart) + this.mOrientationHelper.getEndPadding();
                    }
                }
            }
        }
    }

    public void onLayoutChildren(Recycler recycler, State state) {
        int i;
        int i2;
        this.mRecycler = recycler;
        this.mState = state;
        int itemCount = state.getItemCount();
        if (itemCount != 0 || !state.isPreLayout()) {
            resolveLayoutDirection();
            ensureOrientationHelper();
            ensureLayoutState();
            this.mFlexboxHelper.ensureMeasureSpecCache(itemCount);
            this.mFlexboxHelper.ensureMeasuredSizeCache(itemCount);
            this.mFlexboxHelper.ensureIndexToFlexLine(itemCount);
            this.mLayoutState.mShouldRecycle = false;
            SavedState savedState = this.mPendingSavedState;
            if (savedState != null && savedState.hasValidAnchor(itemCount)) {
                this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
            }
            if (!(this.mAnchorInfo.mValid && this.mPendingScrollPosition == -1 && this.mPendingSavedState == null)) {
                this.mAnchorInfo.reset();
                updateAnchorInfoForLayout(state, this.mAnchorInfo);
                this.mAnchorInfo.mValid = true;
            }
            detachAndScrapAttachedViews(recycler);
            if (this.mAnchorInfo.mLayoutFromEnd) {
                updateLayoutStateToFillStart(this.mAnchorInfo, false, true);
            } else {
                updateLayoutStateToFillEnd(this.mAnchorInfo, false, true);
            }
            updateFlexLines(itemCount);
            if (this.mAnchorInfo.mLayoutFromEnd) {
                fill(recycler, state, this.mLayoutState);
                i2 = this.mLayoutState.mOffset;
                updateLayoutStateToFillEnd(this.mAnchorInfo, true, false);
                fill(recycler, state, this.mLayoutState);
                i = this.mLayoutState.mOffset;
            } else {
                fill(recycler, state, this.mLayoutState);
                i = this.mLayoutState.mOffset;
                updateLayoutStateToFillStart(this.mAnchorInfo, true, false);
                fill(recycler, state, this.mLayoutState);
                i2 = this.mLayoutState.mOffset;
            }
            if (getChildCount() > 0) {
                if (this.mAnchorInfo.mLayoutFromEnd) {
                    fixLayoutStartGap(i2 + fixLayoutEndGap(i, recycler, state, true), recycler, state, false);
                } else {
                    fixLayoutEndGap(i + fixLayoutStartGap(i2, recycler, state, true), recycler, state, false);
                }
            }
        }
    }

    private int fixLayoutStartGap(int i, Recycler recycler, State state, boolean z) {
        int i2;
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            int startAfterPadding = i - this.mOrientationHelper.getStartAfterPadding();
            if (startAfterPadding <= 0) {
                return 0;
            }
            i2 = -handleScrollingCrossAxis(startAfterPadding, recycler, state);
        } else {
            int endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - i;
            if (endAfterPadding <= 0) {
                return 0;
            }
            i2 = handleScrollingCrossAxis(-endAfterPadding, recycler, state);
        }
        int i3 = i + i2;
        if (z) {
            int startAfterPadding2 = i3 - this.mOrientationHelper.getStartAfterPadding();
            if (startAfterPadding2 > 0) {
                this.mOrientationHelper.offsetChildren(-startAfterPadding2);
                i2 -= startAfterPadding2;
            }
        }
        return i2;
    }

    private int fixLayoutEndGap(int i, Recycler recycler, State state, boolean z) {
        int i2;
        if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
            int startAfterPadding = i - this.mOrientationHelper.getStartAfterPadding();
            if (startAfterPadding <= 0) {
                return 0;
            }
            i2 = handleScrollingCrossAxis(startAfterPadding, recycler, state);
        } else {
            int endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - i;
            if (endAfterPadding <= 0) {
                return 0;
            }
            i2 = -handleScrollingCrossAxis(-endAfterPadding, recycler, state);
        }
        int i3 = i + i2;
        if (z) {
            int endAfterPadding2 = this.mOrientationHelper.getEndAfterPadding() - i3;
            if (endAfterPadding2 > 0) {
                this.mOrientationHelper.offsetChildren(endAfterPadding2);
                return endAfterPadding2 + i2;
            }
        }
        return i2;
    }

    private void updateFlexLines(int i) {
        int i2;
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
        int width = getWidth();
        int height = getHeight();
        boolean z = true;
        if (isMainAxisDirectionHorizontal()) {
            int i3 = this.mLastWidth;
            if (i3 == Integer.MIN_VALUE || i3 == width) {
                z = false;
            }
            if (this.mLayoutState.mInfinite) {
                i2 = this.mContext.getResources().getDisplayMetrics().heightPixels;
            } else {
                i2 = this.mLayoutState.mAvailable;
            }
        } else {
            int i4 = this.mLastHeight;
            if (i4 == Integer.MIN_VALUE || i4 == height) {
                z = false;
            }
            if (this.mLayoutState.mInfinite) {
                i2 = this.mContext.getResources().getDisplayMetrics().widthPixels;
            } else {
                i2 = this.mLayoutState.mAvailable;
            }
        }
        int i5 = i2;
        this.mLastWidth = width;
        this.mLastHeight = height;
        if (this.mDirtyPosition != -1 || (this.mPendingScrollPosition == -1 && !z)) {
            int i6 = this.mDirtyPosition;
            int min = i6 != -1 ? Math.min(i6, this.mAnchorInfo.mPosition) : this.mAnchorInfo.mPosition;
            this.mFlexLinesResult.reset();
            if (isMainAxisDirectionHorizontal()) {
                if (this.mFlexLines.size() > 0) {
                    this.mFlexboxHelper.clearFlexLines(this.mFlexLines, min);
                    this.mFlexboxHelper.calculateFlexLines(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, i5, min, this.mAnchorInfo.mPosition, this.mFlexLines);
                } else {
                    this.mFlexboxHelper.ensureIndexToFlexLine(i);
                    this.mFlexboxHelper.calculateHorizontalFlexLines(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, i5, 0, this.mFlexLines);
                }
            } else if (this.mFlexLines.size() > 0) {
                this.mFlexboxHelper.clearFlexLines(this.mFlexLines, min);
                this.mFlexboxHelper.calculateFlexLines(this.mFlexLinesResult, makeMeasureSpec2, makeMeasureSpec, i5, min, this.mAnchorInfo.mPosition, this.mFlexLines);
            } else {
                this.mFlexboxHelper.ensureIndexToFlexLine(i);
                this.mFlexboxHelper.calculateVerticalFlexLines(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, i5, 0, this.mFlexLines);
            }
            this.mFlexLines = this.mFlexLinesResult.mFlexLines;
            this.mFlexboxHelper.determineMainSize(makeMeasureSpec, makeMeasureSpec2, min);
            this.mFlexboxHelper.stretchViews(min);
        } else if (!this.mAnchorInfo.mLayoutFromEnd) {
            this.mFlexLines.clear();
            this.mFlexLinesResult.reset();
            if (isMainAxisDirectionHorizontal()) {
                this.mFlexboxHelper.calculateHorizontalFlexLinesToIndex(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, i5, this.mAnchorInfo.mPosition, this.mFlexLines);
            } else {
                this.mFlexboxHelper.calculateVerticalFlexLinesToIndex(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, i5, this.mAnchorInfo.mPosition, this.mFlexLines);
            }
            this.mFlexLines = this.mFlexLinesResult.mFlexLines;
            this.mFlexboxHelper.determineMainSize(makeMeasureSpec, makeMeasureSpec2);
            this.mFlexboxHelper.stretchViews();
            this.mAnchorInfo.mFlexLinePosition = this.mFlexboxHelper.mIndexToFlexLine[this.mAnchorInfo.mPosition];
            this.mLayoutState.mFlexLinePosition = this.mAnchorInfo.mFlexLinePosition;
        }
    }

    public void onLayoutCompleted(State state) {
        super.onLayoutCompleted(state);
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mDirtyPosition = -1;
        this.mAnchorInfo.reset();
        this.mViewCache.clear();
    }

    /* access modifiers changed from: 0000 */
    public boolean isLayoutRtl() {
        return this.mIsRtl;
    }

    private void resolveLayoutDirection() {
        int layoutDirection = getLayoutDirection();
        int i = this.mFlexDirection;
        boolean z = false;
        if (i == 0) {
            this.mIsRtl = layoutDirection == 1;
            if (this.mFlexWrap == 2) {
                z = true;
            }
            this.mFromBottomToTop = z;
        } else if (i == 1) {
            this.mIsRtl = layoutDirection != 1;
            if (this.mFlexWrap == 2) {
                z = true;
            }
            this.mFromBottomToTop = z;
        } else if (i == 2) {
            this.mIsRtl = layoutDirection == 1;
            if (this.mFlexWrap == 2) {
                this.mIsRtl = !this.mIsRtl;
            }
            this.mFromBottomToTop = false;
        } else if (i != 3) {
            this.mIsRtl = false;
            this.mFromBottomToTop = false;
        } else {
            if (layoutDirection == 1) {
                z = true;
            }
            this.mIsRtl = z;
            if (this.mFlexWrap == 2) {
                this.mIsRtl = !this.mIsRtl;
            }
            this.mFromBottomToTop = true;
        }
    }

    private void updateAnchorInfoForLayout(State state, AnchorInfo anchorInfo) {
        if (!updateAnchorFromPendingState(state, anchorInfo, this.mPendingSavedState) && !updateAnchorFromChildren(state, anchorInfo)) {
            anchorInfo.assignCoordinateFromPadding();
            anchorInfo.mPosition = 0;
            anchorInfo.mFlexLinePosition = 0;
        }
    }

    private boolean updateAnchorFromPendingState(State state, AnchorInfo anchorInfo, SavedState savedState) {
        int i;
        boolean z = false;
        if (!state.isPreLayout()) {
            int i2 = this.mPendingScrollPosition;
            if (i2 != -1) {
                if (i2 < 0 || i2 >= state.getItemCount()) {
                    this.mPendingScrollPosition = -1;
                    this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
                } else {
                    anchorInfo.mPosition = this.mPendingScrollPosition;
                    anchorInfo.mFlexLinePosition = this.mFlexboxHelper.mIndexToFlexLine[anchorInfo.mPosition];
                    SavedState savedState2 = this.mPendingSavedState;
                    if (savedState2 != null && savedState2.hasValidAnchor(state.getItemCount())) {
                        anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + savedState.mAnchorOffset;
                        anchorInfo.mAssignedFromSavedState = true;
                        anchorInfo.mFlexLinePosition = -1;
                        return true;
                    } else if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
                        View findViewByPosition = findViewByPosition(this.mPendingScrollPosition);
                        if (findViewByPosition == null) {
                            if (getChildCount() > 0) {
                                if (this.mPendingScrollPosition < getPosition(getChildAt(0))) {
                                    z = true;
                                }
                                anchorInfo.mLayoutFromEnd = z;
                            }
                            anchorInfo.assignCoordinateFromPadding();
                        } else if (this.mOrientationHelper.getDecoratedMeasurement(findViewByPosition) > this.mOrientationHelper.getTotalSpace()) {
                            anchorInfo.assignCoordinateFromPadding();
                            return true;
                        } else if (this.mOrientationHelper.getDecoratedStart(findViewByPosition) - this.mOrientationHelper.getStartAfterPadding() < 0) {
                            anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding();
                            anchorInfo.mLayoutFromEnd = false;
                            return true;
                        } else if (this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(findViewByPosition) < 0) {
                            anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding();
                            anchorInfo.mLayoutFromEnd = true;
                            return true;
                        } else {
                            if (anchorInfo.mLayoutFromEnd) {
                                i = this.mOrientationHelper.getDecoratedEnd(findViewByPosition) + this.mOrientationHelper.getTotalSpaceChange();
                            } else {
                                i = this.mOrientationHelper.getDecoratedStart(findViewByPosition);
                            }
                            anchorInfo.mCoordinate = i;
                        }
                        return true;
                    } else {
                        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
                            anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingScrollPositionOffset;
                        } else {
                            anchorInfo.mCoordinate = this.mPendingScrollPositionOffset - this.mOrientationHelper.getEndPadding();
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean updateAnchorFromChildren(State state, AnchorInfo anchorInfo) {
        View view;
        int i;
        boolean z = false;
        if (getChildCount() == 0) {
            return false;
        }
        if (anchorInfo.mLayoutFromEnd) {
            view = findLastReferenceChild(state.getItemCount());
        } else {
            view = findFirstReferenceChild(state.getItemCount());
        }
        if (view == null) {
            return false;
        }
        anchorInfo.assignFromView(view);
        if (!state.isPreLayout() && supportsPredictiveItemAnimations()) {
            if (this.mOrientationHelper.getDecoratedStart(view) >= this.mOrientationHelper.getEndAfterPadding() || this.mOrientationHelper.getDecoratedEnd(view) < this.mOrientationHelper.getStartAfterPadding()) {
                z = true;
            }
            if (z) {
                if (anchorInfo.mLayoutFromEnd) {
                    i = this.mOrientationHelper.getEndAfterPadding();
                } else {
                    i = this.mOrientationHelper.getStartAfterPadding();
                }
                anchorInfo.mCoordinate = i;
            }
        }
        return true;
    }

    private View findFirstReferenceChild(int i) {
        View findReferenceChild = findReferenceChild(0, getChildCount(), i);
        if (findReferenceChild == null) {
            return null;
        }
        int i2 = this.mFlexboxHelper.mIndexToFlexLine[getPosition(findReferenceChild)];
        if (i2 == -1) {
            return null;
        }
        return findFirstReferenceViewInLine(findReferenceChild, (FlexLine) this.mFlexLines.get(i2));
    }

    private View findLastReferenceChild(int i) {
        View findReferenceChild = findReferenceChild(getChildCount() - 1, -1, i);
        if (findReferenceChild == null) {
            return null;
        }
        return findLastReferenceViewInLine(findReferenceChild, (FlexLine) this.mFlexLines.get(this.mFlexboxHelper.mIndexToFlexLine[getPosition(findReferenceChild)]));
    }

    private View findReferenceChild(int i, int i2, int i3) {
        ensureOrientationHelper();
        ensureLayoutState();
        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View childAt = getChildAt(i);
            int position = getPosition(childAt);
            if (position >= 0 && position < i3) {
                if (((androidx.recyclerview.widget.RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else if (this.mOrientationHelper.getDecoratedStart(childAt) >= startAfterPadding && this.mOrientationHelper.getDecoratedEnd(childAt) <= endAfterPadding) {
                    return childAt;
                } else {
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i += i4;
        }
        if (view == null) {
            view = view2;
        }
        return view;
    }

    private View getChildClosestToStart() {
        return getChildAt(0);
    }

    private int fill(Recycler recycler, State state, LayoutState layoutState) {
        if (layoutState.mScrollingOffset != Integer.MIN_VALUE) {
            if (layoutState.mAvailable < 0) {
                layoutState.mScrollingOffset = layoutState.mScrollingOffset + layoutState.mAvailable;
            }
            recycleByLayoutState(recycler, layoutState);
        }
        int access$1200 = layoutState.mAvailable;
        int access$12002 = layoutState.mAvailable;
        int i = 0;
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        while (true) {
            if ((access$12002 > 0 || this.mLayoutState.mInfinite) && layoutState.hasMore(state, this.mFlexLines)) {
                FlexLine flexLine = (FlexLine) this.mFlexLines.get(layoutState.mFlexLinePosition);
                layoutState.mPosition = flexLine.mFirstIndex;
                i += layoutFlexLine(flexLine, layoutState);
                if (isMainAxisDirectionHorizontal || !this.mIsRtl) {
                    layoutState.mOffset = layoutState.mOffset + (flexLine.getCrossSize() * layoutState.mLayoutDirection);
                } else {
                    layoutState.mOffset = layoutState.mOffset - (flexLine.getCrossSize() * layoutState.mLayoutDirection);
                }
                access$12002 -= flexLine.getCrossSize();
            }
        }
        layoutState.mAvailable = layoutState.mAvailable - i;
        if (layoutState.mScrollingOffset != Integer.MIN_VALUE) {
            layoutState.mScrollingOffset = layoutState.mScrollingOffset + i;
            if (layoutState.mAvailable < 0) {
                layoutState.mScrollingOffset = layoutState.mScrollingOffset + layoutState.mAvailable;
            }
            recycleByLayoutState(recycler, layoutState);
        }
        return access$1200 - layoutState.mAvailable;
    }

    private void recycleByLayoutState(Recycler recycler, LayoutState layoutState) {
        if (layoutState.mShouldRecycle) {
            if (layoutState.mLayoutDirection == -1) {
                recycleFlexLinesFromEnd(recycler, layoutState);
            } else {
                recycleFlexLinesFromStart(recycler, layoutState);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0060, code lost:
        r2 = r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void recycleFlexLinesFromStart(androidx.recyclerview.widget.RecyclerView.Recycler r9, com.google.android.flexbox.FlexboxLayoutManager.LayoutState r10) {
        /*
            r8 = this;
            int r0 = r10.mScrollingOffset
            if (r0 >= 0) goto L_0x0007
            return
        L_0x0007:
            int r0 = r8.getChildCount()
            if (r0 != 0) goto L_0x000e
            return
        L_0x000e:
            r1 = 0
            android.view.View r2 = r8.getChildAt(r1)
            com.google.android.flexbox.FlexboxHelper r3 = r8.mFlexboxHelper
            int[] r3 = r3.mIndexToFlexLine
            int r2 = r8.getPosition(r2)
            r2 = r3[r2]
            r3 = -1
            if (r2 != r3) goto L_0x0021
            return
        L_0x0021:
            java.util.List<com.google.android.flexbox.FlexLine> r4 = r8.mFlexLines
            java.lang.Object r4 = r4.get(r2)
            com.google.android.flexbox.FlexLine r4 = (com.google.android.flexbox.FlexLine) r4
            r3 = r2
            r2 = 0
            r5 = -1
        L_0x002c:
            if (r2 >= r0) goto L_0x0060
            android.view.View r6 = r8.getChildAt(r2)
            int r7 = r10.mScrollingOffset
            boolean r7 = r8.canViewBeRecycledFromStart(r6, r7)
            if (r7 == 0) goto L_0x0060
            int r7 = r4.mLastIndex
            int r6 = r8.getPosition(r6)
            if (r7 != r6) goto L_0x005d
            java.util.List<com.google.android.flexbox.FlexLine> r4 = r8.mFlexLines
            int r4 = r4.size()
            int r4 = r4 + -1
            if (r3 < r4) goto L_0x004f
            goto L_0x0061
        L_0x004f:
            int r4 = r10.mLayoutDirection
            int r3 = r3 + r4
            java.util.List<com.google.android.flexbox.FlexLine> r4 = r8.mFlexLines
            java.lang.Object r4 = r4.get(r3)
            com.google.android.flexbox.FlexLine r4 = (com.google.android.flexbox.FlexLine) r4
            r5 = r2
        L_0x005d:
            int r2 = r2 + 1
            goto L_0x002c
        L_0x0060:
            r2 = r5
        L_0x0061:
            r8.recycleChildren(r9, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.recycleFlexLinesFromStart(androidx.recyclerview.widget.RecyclerView$Recycler, com.google.android.flexbox.FlexboxLayoutManager$LayoutState):void");
    }

    private boolean canViewBeRecycledFromStart(View view, int i) {
        boolean z = true;
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            if (this.mOrientationHelper.getDecoratedEnd(view) > i) {
                z = false;
            }
            return z;
        }
        if (this.mOrientationHelper.getEnd() - this.mOrientationHelper.getDecoratedStart(view) > i) {
            z = false;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0060, code lost:
        r0 = r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void recycleFlexLinesFromEnd(androidx.recyclerview.widget.RecyclerView.Recycler r8, com.google.android.flexbox.FlexboxLayoutManager.LayoutState r9) {
        /*
            r7 = this;
            int r0 = r9.mScrollingOffset
            if (r0 >= 0) goto L_0x0007
            return
        L_0x0007:
            androidx.recyclerview.widget.OrientationHelper r0 = r7.mOrientationHelper
            r0.getEnd()
            r9.mScrollingOffset
            int r0 = r7.getChildCount()
            if (r0 != 0) goto L_0x0016
            return
        L_0x0016:
            int r1 = r0 + -1
            android.view.View r2 = r7.getChildAt(r1)
            com.google.android.flexbox.FlexboxHelper r3 = r7.mFlexboxHelper
            int[] r3 = r3.mIndexToFlexLine
            int r2 = r7.getPosition(r2)
            r2 = r3[r2]
            r3 = -1
            if (r2 != r3) goto L_0x002a
            return
        L_0x002a:
            java.util.List<com.google.android.flexbox.FlexLine> r3 = r7.mFlexLines
            java.lang.Object r3 = r3.get(r2)
            com.google.android.flexbox.FlexLine r3 = (com.google.android.flexbox.FlexLine) r3
            r4 = r0
            r0 = r1
        L_0x0034:
            if (r0 < 0) goto L_0x0060
            android.view.View r5 = r7.getChildAt(r0)
            int r6 = r9.mScrollingOffset
            boolean r6 = r7.canViewBeRecycledFromEnd(r5, r6)
            if (r6 == 0) goto L_0x0060
            int r6 = r3.mFirstIndex
            int r5 = r7.getPosition(r5)
            if (r6 != r5) goto L_0x005d
            if (r2 > 0) goto L_0x004f
            goto L_0x0061
        L_0x004f:
            int r3 = r9.mLayoutDirection
            int r2 = r2 + r3
            java.util.List<com.google.android.flexbox.FlexLine> r3 = r7.mFlexLines
            java.lang.Object r3 = r3.get(r2)
            com.google.android.flexbox.FlexLine r3 = (com.google.android.flexbox.FlexLine) r3
            r4 = r0
        L_0x005d:
            int r0 = r0 + -1
            goto L_0x0034
        L_0x0060:
            r0 = r4
        L_0x0061:
            r7.recycleChildren(r8, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.recycleFlexLinesFromEnd(androidx.recyclerview.widget.RecyclerView$Recycler, com.google.android.flexbox.FlexboxLayoutManager$LayoutState):void");
    }

    private boolean canViewBeRecycledFromEnd(View view, int i) {
        boolean z = true;
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            if (this.mOrientationHelper.getDecoratedStart(view) < this.mOrientationHelper.getEnd() - i) {
                z = false;
            }
            return z;
        }
        if (this.mOrientationHelper.getDecoratedEnd(view) > i) {
            z = false;
        }
        return z;
    }

    private void recycleChildren(Recycler recycler, int i, int i2) {
        while (i2 >= i) {
            removeAndRecycleViewAt(i2, recycler);
            i2--;
        }
    }

    private int layoutFlexLine(FlexLine flexLine, LayoutState layoutState) {
        if (isMainAxisDirectionHorizontal()) {
            return layoutFlexLineMainAxisHorizontal(flexLine, layoutState);
        }
        return layoutFlexLineMainAxisVertical(flexLine, layoutState);
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00da  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int layoutFlexLineMainAxisHorizontal(com.google.android.flexbox.FlexLine r22, com.google.android.flexbox.FlexboxLayoutManager.LayoutState r23) {
        /*
            r21 = this;
            r0 = r21
            r8 = r22
            int r1 = r21.getPaddingLeft()
            int r2 = r21.getPaddingRight()
            int r3 = r21.getWidth()
            int r4 = r23.mOffset
            int r5 = r23.mLayoutDirection
            r6 = -1
            if (r5 != r6) goto L_0x001e
            int r5 = r8.mCrossSize
            int r4 = r4 - r5
        L_0x001e:
            r9 = r4
            int r10 = r23.mPosition
            int r4 = r0.mJustifyContent
            r5 = 0
            r11 = 1
            if (r4 == 0) goto L_0x00b8
            if (r4 == r11) goto L_0x00a8
            r6 = 2
            r7 = 1073741824(0x40000000, float:2.0)
            if (r4 == r6) goto L_0x0096
            r6 = 3
            if (r4 == r6) goto L_0x0080
            r6 = 4
            if (r4 == r6) goto L_0x0069
            r6 = 5
            if (r4 != r6) goto L_0x0050
            int r4 = r8.mItemCount
            if (r4 == 0) goto L_0x0048
            int r4 = r8.mMainSize
            int r4 = r3 - r4
            float r4 = (float) r4
            int r6 = r8.mItemCount
            int r6 = r6 + r11
            float r6 = (float) r6
            float r4 = r4 / r6
            goto L_0x0049
        L_0x0048:
            r4 = 0
        L_0x0049:
            float r1 = (float) r1
            float r1 = r1 + r4
            int r3 = r3 - r2
            float r2 = (float) r3
            float r2 = r2 - r4
            goto L_0x00bc
        L_0x0050:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Invalid justifyContent is set: "
            r2.append(r3)
            int r3 = r0.mJustifyContent
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x0069:
            int r4 = r8.mItemCount
            if (r4 == 0) goto L_0x0077
            int r4 = r8.mMainSize
            int r4 = r3 - r4
            float r4 = (float) r4
            int r6 = r8.mItemCount
            float r6 = (float) r6
            float r4 = r4 / r6
            goto L_0x0078
        L_0x0077:
            r4 = 0
        L_0x0078:
            float r1 = (float) r1
            float r6 = r4 / r7
            float r1 = r1 + r6
            int r3 = r3 - r2
            float r2 = (float) r3
            float r2 = r2 - r6
            goto L_0x00bc
        L_0x0080:
            float r1 = (float) r1
            int r4 = r8.mItemCount
            if (r4 == r11) goto L_0x008a
            int r4 = r8.mItemCount
            int r4 = r4 - r11
            float r4 = (float) r4
            goto L_0x008c
        L_0x008a:
            r4 = 1065353216(0x3f800000, float:1.0)
        L_0x008c:
            int r6 = r8.mMainSize
            int r6 = r3 - r6
            float r6 = (float) r6
            float r4 = r6 / r4
            int r3 = r3 - r2
            float r2 = (float) r3
            goto L_0x00bc
        L_0x0096:
            float r1 = (float) r1
            int r4 = r8.mMainSize
            int r4 = r3 - r4
            float r4 = (float) r4
            float r4 = r4 / r7
            float r1 = r1 + r4
            int r2 = r3 - r2
            float r2 = (float) r2
            int r4 = r8.mMainSize
            int r3 = r3 - r4
            float r3 = (float) r3
            float r3 = r3 / r7
            float r2 = r2 - r3
            goto L_0x00bb
        L_0x00a8:
            int r4 = r8.mMainSize
            int r3 = r3 - r4
            int r3 = r3 + r2
            float r2 = (float) r3
            int r3 = r8.mMainSize
            int r3 = r3 - r1
            float r1 = (float) r3
            r4 = 0
            r20 = r2
            r2 = r1
            r1 = r20
            goto L_0x00bc
        L_0x00b8:
            float r1 = (float) r1
            int r3 = r3 - r2
            float r2 = (float) r3
        L_0x00bb:
            r4 = 0
        L_0x00bc:
            com.google.android.flexbox.FlexboxLayoutManager$AnchorInfo r3 = r0.mAnchorInfo
            int r3 = r3.mPerpendicularCoordinate
            float r3 = (float) r3
            float r1 = r1 - r3
            com.google.android.flexbox.FlexboxLayoutManager$AnchorInfo r3 = r0.mAnchorInfo
            int r3 = r3.mPerpendicularCoordinate
            float r3 = (float) r3
            float r2 = r2 - r3
            float r12 = java.lang.Math.max(r4, r5)
            r3 = 0
            int r13 = r22.getItemCount()
            r14 = r10
        L_0x00d6:
            int r4 = r10 + r13
            if (r14 >= r4) goto L_0x01a5
            android.view.View r15 = r0.getFlexItemAt(r14)
            if (r15 != 0) goto L_0x00e2
            goto L_0x01a0
        L_0x00e2:
            int r4 = r23.mLayoutDirection
            if (r4 != r11) goto L_0x00f1
            android.graphics.Rect r4 = TEMP_RECT
            r0.calculateItemDecorationsForChild(r15, r4)
            r0.addView(r15)
            goto L_0x00fb
        L_0x00f1:
            android.graphics.Rect r4 = TEMP_RECT
            r0.calculateItemDecorationsForChild(r15, r4)
            r0.addView(r15, r3)
            int r3 = r3 + 1
        L_0x00fb:
            r16 = r3
            com.google.android.flexbox.FlexboxHelper r3 = r0.mFlexboxHelper
            long[] r3 = r3.mMeasureSpecCache
            r4 = r3[r14]
            com.google.android.flexbox.FlexboxHelper r3 = r0.mFlexboxHelper
            int r3 = r3.extractLowerInt(r4)
            com.google.android.flexbox.FlexboxHelper r6 = r0.mFlexboxHelper
            int r4 = r6.extractHigherInt(r4)
            android.view.ViewGroup$LayoutParams r5 = r15.getLayoutParams()
            r7 = r5
            com.google.android.flexbox.FlexboxLayoutManager$LayoutParams r7 = (com.google.android.flexbox.FlexboxLayoutManager.LayoutParams) r7
            boolean r5 = r0.shouldMeasureChild(r15, r3, r4, r7)
            if (r5 == 0) goto L_0x011f
            r15.measure(r3, r4)
        L_0x011f:
            int r3 = r7.leftMargin
            int r4 = r0.getLeftDecorationWidth(r15)
            int r3 = r3 + r4
            float r3 = (float) r3
            float r17 = r1 + r3
            int r1 = r7.rightMargin
            int r3 = r0.getRightDecorationWidth(r15)
            int r1 = r1 + r3
            float r1 = (float) r1
            float r18 = r2 - r1
            int r1 = r0.getTopDecorationHeight(r15)
            int r5 = r9 + r1
            boolean r1 = r0.mIsRtl
            if (r1 == 0) goto L_0x015d
            com.google.android.flexbox.FlexboxHelper r1 = r0.mFlexboxHelper
            int r2 = java.lang.Math.round(r18)
            int r3 = r15.getMeasuredWidth()
            int r4 = r2 - r3
            int r6 = java.lang.Math.round(r18)
            int r2 = r15.getMeasuredHeight()
            int r19 = r5 + r2
            r2 = r15
            r3 = r22
            r11 = r7
            r7 = r19
            r1.layoutSingleChildHorizontal(r2, r3, r4, r5, r6, r7)
            goto L_0x017a
        L_0x015d:
            r11 = r7
            com.google.android.flexbox.FlexboxHelper r1 = r0.mFlexboxHelper
            int r4 = java.lang.Math.round(r17)
            int r2 = java.lang.Math.round(r17)
            int r3 = r15.getMeasuredWidth()
            int r6 = r2 + r3
            int r2 = r15.getMeasuredHeight()
            int r7 = r5 + r2
            r2 = r15
            r3 = r22
            r1.layoutSingleChildHorizontal(r2, r3, r4, r5, r6, r7)
        L_0x017a:
            int r1 = r15.getMeasuredWidth()
            int r2 = r11.rightMargin
            int r1 = r1 + r2
            int r2 = r0.getRightDecorationWidth(r15)
            int r1 = r1 + r2
            float r1 = (float) r1
            float r1 = r1 + r12
            float r17 = r17 + r1
            int r1 = r15.getMeasuredWidth()
            int r2 = r11.leftMargin
            int r1 = r1 + r2
            int r2 = r0.getLeftDecorationWidth(r15)
            int r1 = r1 + r2
            float r1 = (float) r1
            float r1 = r1 + r12
            float r18 = r18 - r1
            r3 = r16
            r1 = r17
            r2 = r18
        L_0x01a0:
            int r14 = r14 + 1
            r11 = 1
            goto L_0x00d6
        L_0x01a5:
            int r1 = r23.mFlexLinePosition
            com.google.android.flexbox.FlexboxLayoutManager$LayoutState r2 = r0.mLayoutState
            int r2 = r2.mLayoutDirection
            int r1 = r1 + r2
            r2 = r23
            r2.mFlexLinePosition = r1
            int r1 = r22.getCrossSize()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.layoutFlexLineMainAxisHorizontal(com.google.android.flexbox.FlexLine, com.google.android.flexbox.FlexboxLayoutManager$LayoutState):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int layoutFlexLineMainAxisVertical(com.google.android.flexbox.FlexLine r26, com.google.android.flexbox.FlexboxLayoutManager.LayoutState r27) {
        /*
            r25 = this;
            r0 = r25
            r9 = r26
            int r1 = r25.getPaddingTop()
            int r2 = r25.getPaddingBottom()
            int r3 = r25.getHeight()
            int r4 = r27.mOffset
            int r5 = r27.mOffset
            int r6 = r27.mLayoutDirection
            r7 = -1
            if (r6 != r7) goto L_0x0025
            int r6 = r9.mCrossSize
            int r4 = r4 - r6
            int r6 = r9.mCrossSize
            int r5 = r5 + r6
        L_0x0025:
            r10 = r4
            r11 = r5
            int r12 = r27.mPosition
            int r4 = r0.mJustifyContent
            r5 = 0
            r13 = 1
            if (r4 == 0) goto L_0x00c0
            if (r4 == r13) goto L_0x00b0
            r6 = 2
            r7 = 1073741824(0x40000000, float:2.0)
            if (r4 == r6) goto L_0x009e
            r6 = 3
            if (r4 == r6) goto L_0x0088
            r6 = 4
            if (r4 == r6) goto L_0x0071
            r6 = 5
            if (r4 != r6) goto L_0x0058
            int r4 = r9.mItemCount
            if (r4 == 0) goto L_0x0050
            int r4 = r9.mMainSize
            int r4 = r3 - r4
            float r4 = (float) r4
            int r6 = r9.mItemCount
            int r6 = r6 + r13
            float r6 = (float) r6
            float r4 = r4 / r6
            goto L_0x0051
        L_0x0050:
            r4 = 0
        L_0x0051:
            float r1 = (float) r1
            float r1 = r1 + r4
            int r3 = r3 - r2
            float r2 = (float) r3
            float r2 = r2 - r4
            goto L_0x00c4
        L_0x0058:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Invalid justifyContent is set: "
            r2.append(r3)
            int r3 = r0.mJustifyContent
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x0071:
            int r4 = r9.mItemCount
            if (r4 == 0) goto L_0x007f
            int r4 = r9.mMainSize
            int r4 = r3 - r4
            float r4 = (float) r4
            int r6 = r9.mItemCount
            float r6 = (float) r6
            float r4 = r4 / r6
            goto L_0x0080
        L_0x007f:
            r4 = 0
        L_0x0080:
            float r1 = (float) r1
            float r6 = r4 / r7
            float r1 = r1 + r6
            int r3 = r3 - r2
            float r2 = (float) r3
            float r2 = r2 - r6
            goto L_0x00c4
        L_0x0088:
            float r1 = (float) r1
            int r4 = r9.mItemCount
            if (r4 == r13) goto L_0x0092
            int r4 = r9.mItemCount
            int r4 = r4 - r13
            float r4 = (float) r4
            goto L_0x0094
        L_0x0092:
            r4 = 1065353216(0x3f800000, float:1.0)
        L_0x0094:
            int r6 = r9.mMainSize
            int r6 = r3 - r6
            float r6 = (float) r6
            float r4 = r6 / r4
            int r3 = r3 - r2
            float r2 = (float) r3
            goto L_0x00c4
        L_0x009e:
            float r1 = (float) r1
            int r4 = r9.mMainSize
            int r4 = r3 - r4
            float r4 = (float) r4
            float r4 = r4 / r7
            float r1 = r1 + r4
            int r2 = r3 - r2
            float r2 = (float) r2
            int r4 = r9.mMainSize
            int r3 = r3 - r4
            float r3 = (float) r3
            float r3 = r3 / r7
            float r2 = r2 - r3
            goto L_0x00c3
        L_0x00b0:
            int r4 = r9.mMainSize
            int r3 = r3 - r4
            int r3 = r3 + r2
            float r2 = (float) r3
            int r3 = r9.mMainSize
            int r3 = r3 - r1
            float r1 = (float) r3
            r4 = 0
            r24 = r2
            r2 = r1
            r1 = r24
            goto L_0x00c4
        L_0x00c0:
            float r1 = (float) r1
            int r3 = r3 - r2
            float r2 = (float) r3
        L_0x00c3:
            r4 = 0
        L_0x00c4:
            com.google.android.flexbox.FlexboxLayoutManager$AnchorInfo r3 = r0.mAnchorInfo
            int r3 = r3.mPerpendicularCoordinate
            float r3 = (float) r3
            float r1 = r1 - r3
            com.google.android.flexbox.FlexboxLayoutManager$AnchorInfo r3 = r0.mAnchorInfo
            int r3 = r3.mPerpendicularCoordinate
            float r3 = (float) r3
            float r2 = r2 - r3
            float r14 = java.lang.Math.max(r4, r5)
            r3 = 0
            int r15 = r26.getItemCount()
            r8 = r12
        L_0x00de:
            int r4 = r12 + r15
            if (r8 >= r4) goto L_0x0215
            android.view.View r7 = r0.getFlexItemAt(r8)
            if (r7 != 0) goto L_0x00ee
            r23 = r8
            r22 = 1
            goto L_0x0210
        L_0x00ee:
            com.google.android.flexbox.FlexboxHelper r4 = r0.mFlexboxHelper
            long[] r4 = r4.mMeasureSpecCache
            r5 = r4[r8]
            com.google.android.flexbox.FlexboxHelper r4 = r0.mFlexboxHelper
            int r4 = r4.extractLowerInt(r5)
            com.google.android.flexbox.FlexboxHelper r13 = r0.mFlexboxHelper
            int r5 = r13.extractHigherInt(r5)
            android.view.ViewGroup$LayoutParams r6 = r7.getLayoutParams()
            r13 = r6
            com.google.android.flexbox.FlexboxLayoutManager$LayoutParams r13 = (com.google.android.flexbox.FlexboxLayoutManager.LayoutParams) r13
            boolean r6 = r0.shouldMeasureChild(r7, r4, r5, r13)
            if (r6 == 0) goto L_0x0110
            r7.measure(r4, r5)
        L_0x0110:
            int r4 = r13.topMargin
            int r5 = r0.getTopDecorationHeight(r7)
            int r4 = r4 + r5
            float r4 = (float) r4
            float r17 = r1 + r4
            int r1 = r13.rightMargin
            int r4 = r0.getBottomDecorationHeight(r7)
            int r1 = r1 + r4
            float r1 = (float) r1
            float r18 = r2 - r1
            int r1 = r27.mLayoutDirection
            r6 = 1
            if (r1 != r6) goto L_0x0134
            android.graphics.Rect r1 = TEMP_RECT
            r0.calculateItemDecorationsForChild(r7, r1)
            r0.addView(r7)
            goto L_0x013e
        L_0x0134:
            android.graphics.Rect r1 = TEMP_RECT
            r0.calculateItemDecorationsForChild(r7, r1)
            r0.addView(r7, r3)
            int r3 = r3 + 1
        L_0x013e:
            r16 = r3
            int r1 = r0.getLeftDecorationWidth(r7)
            int r5 = r10 + r1
            int r1 = r0.getRightDecorationWidth(r7)
            int r19 = r11 - r1
            boolean r4 = r0.mIsRtl
            if (r4 == 0) goto L_0x01a3
            boolean r1 = r0.mFromBottomToTop
            if (r1 == 0) goto L_0x017d
            com.google.android.flexbox.FlexboxHelper r1 = r0.mFlexboxHelper
            int r2 = r7.getMeasuredWidth()
            int r5 = r19 - r2
            int r2 = java.lang.Math.round(r18)
            int r3 = r7.getMeasuredHeight()
            int r20 = r2 - r3
            int r21 = java.lang.Math.round(r18)
            r2 = r7
            r3 = r26
            r22 = 1
            r6 = r20
            r20 = r7
            r7 = r19
            r23 = r8
            r8 = r21
            r1.layoutSingleChildVertical(r2, r3, r4, r5, r6, r7, r8)
            goto L_0x01e8
        L_0x017d:
            r20 = r7
            r23 = r8
            r22 = 1
            com.google.android.flexbox.FlexboxHelper r1 = r0.mFlexboxHelper
            int r2 = r20.getMeasuredWidth()
            int r5 = r19 - r2
            int r6 = java.lang.Math.round(r17)
            int r2 = java.lang.Math.round(r17)
            int r3 = r20.getMeasuredHeight()
            int r8 = r2 + r3
            r2 = r20
            r3 = r26
            r7 = r19
            r1.layoutSingleChildVertical(r2, r3, r4, r5, r6, r7, r8)
            goto L_0x01e8
        L_0x01a3:
            r20 = r7
            r23 = r8
            r22 = 1
            boolean r1 = r0.mFromBottomToTop
            if (r1 == 0) goto L_0x01cb
            com.google.android.flexbox.FlexboxHelper r1 = r0.mFlexboxHelper
            int r2 = java.lang.Math.round(r18)
            int r3 = r20.getMeasuredHeight()
            int r6 = r2 - r3
            int r2 = r20.getMeasuredWidth()
            int r7 = r5 + r2
            int r8 = java.lang.Math.round(r18)
            r2 = r20
            r3 = r26
            r1.layoutSingleChildVertical(r2, r3, r4, r5, r6, r7, r8)
            goto L_0x01e8
        L_0x01cb:
            com.google.android.flexbox.FlexboxHelper r1 = r0.mFlexboxHelper
            int r6 = java.lang.Math.round(r17)
            int r2 = r20.getMeasuredWidth()
            int r7 = r5 + r2
            int r2 = java.lang.Math.round(r17)
            int r3 = r20.getMeasuredHeight()
            int r8 = r2 + r3
            r2 = r20
            r3 = r26
            r1.layoutSingleChildVertical(r2, r3, r4, r5, r6, r7, r8)
        L_0x01e8:
            int r1 = r20.getMeasuredHeight()
            int r2 = r13.topMargin
            int r1 = r1 + r2
            r2 = r20
            int r3 = r0.getBottomDecorationHeight(r2)
            int r1 = r1 + r3
            float r1 = (float) r1
            float r1 = r1 + r14
            float r17 = r17 + r1
            int r1 = r2.getMeasuredHeight()
            int r3 = r13.bottomMargin
            int r1 = r1 + r3
            int r2 = r0.getTopDecorationHeight(r2)
            int r1 = r1 + r2
            float r1 = (float) r1
            float r1 = r1 + r14
            float r18 = r18 - r1
            r3 = r16
            r1 = r17
            r2 = r18
        L_0x0210:
            int r8 = r23 + 1
            r13 = 1
            goto L_0x00de
        L_0x0215:
            int r1 = r27.mFlexLinePosition
            com.google.android.flexbox.FlexboxLayoutManager$LayoutState r2 = r0.mLayoutState
            int r2 = r2.mLayoutDirection
            int r1 = r1 + r2
            r2 = r27
            r2.mFlexLinePosition = r1
            int r1 = r26.getCrossSize()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.layoutFlexLineMainAxisVertical(com.google.android.flexbox.FlexLine, com.google.android.flexbox.FlexboxLayoutManager$LayoutState):int");
    }

    public boolean isMainAxisDirectionHorizontal() {
        int i = this.mFlexDirection;
        return i == 0 || i == 1;
    }

    private void updateLayoutStateToFillEnd(AnchorInfo anchorInfo, boolean z, boolean z2) {
        if (z2) {
            resolveInfiniteAmount();
        } else {
            this.mLayoutState.mInfinite = false;
        }
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            this.mLayoutState.mAvailable = this.mOrientationHelper.getEndAfterPadding() - anchorInfo.mCoordinate;
        } else {
            this.mLayoutState.mAvailable = anchorInfo.mCoordinate - getPaddingRight();
        }
        this.mLayoutState.mPosition = anchorInfo.mPosition;
        this.mLayoutState.mItemDirection = 1;
        this.mLayoutState.mLayoutDirection = 1;
        this.mLayoutState.mOffset = anchorInfo.mCoordinate;
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
        this.mLayoutState.mFlexLinePosition = anchorInfo.mFlexLinePosition;
        if (z && this.mFlexLines.size() > 1 && anchorInfo.mFlexLinePosition >= 0 && anchorInfo.mFlexLinePosition < this.mFlexLines.size() - 1) {
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(anchorInfo.mFlexLinePosition);
            this.mLayoutState.mFlexLinePosition = this.mLayoutState.mFlexLinePosition + 1;
            LayoutState layoutState = this.mLayoutState;
            layoutState.mPosition = layoutState.mPosition + flexLine.getItemCount();
        }
    }

    private void updateLayoutStateToFillStart(AnchorInfo anchorInfo, boolean z, boolean z2) {
        if (z2) {
            resolveInfiniteAmount();
        } else {
            this.mLayoutState.mInfinite = false;
        }
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            this.mLayoutState.mAvailable = anchorInfo.mCoordinate - this.mOrientationHelper.getStartAfterPadding();
        } else {
            this.mLayoutState.mAvailable = (this.mParent.getWidth() - anchorInfo.mCoordinate) - this.mOrientationHelper.getStartAfterPadding();
        }
        this.mLayoutState.mPosition = anchorInfo.mPosition;
        this.mLayoutState.mItemDirection = 1;
        this.mLayoutState.mLayoutDirection = -1;
        this.mLayoutState.mOffset = anchorInfo.mCoordinate;
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
        this.mLayoutState.mFlexLinePosition = anchorInfo.mFlexLinePosition;
        if (z && anchorInfo.mFlexLinePosition > 0 && this.mFlexLines.size() > anchorInfo.mFlexLinePosition) {
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(anchorInfo.mFlexLinePosition);
            this.mLayoutState.mFlexLinePosition = this.mLayoutState.mFlexLinePosition - 1;
            LayoutState layoutState = this.mLayoutState;
            layoutState.mPosition = layoutState.mPosition - flexLine.getItemCount();
        }
    }

    private void resolveInfiniteAmount() {
        int i;
        if (isMainAxisDirectionHorizontal()) {
            i = getHeightMode();
        } else {
            i = getWidthMode();
        }
        this.mLayoutState.mInfinite = i == 0 || i == Integer.MIN_VALUE;
    }

    private void ensureOrientationHelper() {
        if (this.mOrientationHelper == null) {
            if (isMainAxisDirectionHorizontal()) {
                if (this.mFlexWrap == 0) {
                    this.mOrientationHelper = OrientationHelper.createHorizontalHelper(this);
                    this.mSubOrientationHelper = OrientationHelper.createVerticalHelper(this);
                } else {
                    this.mOrientationHelper = OrientationHelper.createVerticalHelper(this);
                    this.mSubOrientationHelper = OrientationHelper.createHorizontalHelper(this);
                }
            } else if (this.mFlexWrap == 0) {
                this.mOrientationHelper = OrientationHelper.createVerticalHelper(this);
                this.mSubOrientationHelper = OrientationHelper.createHorizontalHelper(this);
            } else {
                this.mOrientationHelper = OrientationHelper.createHorizontalHelper(this);
                this.mSubOrientationHelper = OrientationHelper.createVerticalHelper(this);
            }
        }
    }

    private void ensureLayoutState() {
        if (this.mLayoutState == null) {
            this.mLayoutState = new LayoutState();
        }
    }

    public void scrollToPosition(int i) {
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.invalidateAnchor();
        }
        requestLayout();
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, State state, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(linearSmoothScroller);
    }

    public boolean getRecycleChildrenOnDetach() {
        return this.mRecycleChildrenOnDetach;
    }

    public void setRecycleChildrenOnDetach(boolean z) {
        this.mRecycleChildrenOnDetach = z;
    }

    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.mParent = (View) recyclerView.getParent();
    }

    public void onDetachedFromWindow(RecyclerView recyclerView, Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (this.mRecycleChildrenOnDetach) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    public boolean canScrollHorizontally() {
        return !isMainAxisDirectionHorizontal() || getWidth() > this.mParent.getWidth();
    }

    public boolean canScrollVertically() {
        return isMainAxisDirectionHorizontal() || getHeight() > this.mParent.getHeight();
    }

    public int scrollHorizontallyBy(int i, Recycler recycler, State state) {
        if (!isMainAxisDirectionHorizontal()) {
            int handleScrollingCrossAxis = handleScrollingCrossAxis(i, recycler, state);
            this.mViewCache.clear();
            return handleScrollingCrossAxis;
        }
        int handleScrollingMainAxis = handleScrollingMainAxis(i);
        AnchorInfo anchorInfo = this.mAnchorInfo;
        anchorInfo.mPerpendicularCoordinate = anchorInfo.mPerpendicularCoordinate + handleScrollingMainAxis;
        this.mSubOrientationHelper.offsetChildren(-handleScrollingMainAxis);
        return handleScrollingMainAxis;
    }

    public int scrollVerticallyBy(int i, Recycler recycler, State state) {
        if (isMainAxisDirectionHorizontal()) {
            int handleScrollingCrossAxis = handleScrollingCrossAxis(i, recycler, state);
            this.mViewCache.clear();
            return handleScrollingCrossAxis;
        }
        int handleScrollingMainAxis = handleScrollingMainAxis(i);
        AnchorInfo anchorInfo = this.mAnchorInfo;
        anchorInfo.mPerpendicularCoordinate = anchorInfo.mPerpendicularCoordinate + handleScrollingMainAxis;
        this.mSubOrientationHelper.offsetChildren(-handleScrollingMainAxis);
        return handleScrollingMainAxis;
    }

    private int handleScrollingCrossAxis(int i, Recycler recycler, State state) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        ensureOrientationHelper();
        int i2 = 1;
        this.mLayoutState.mShouldRecycle = true;
        boolean z = !isMainAxisDirectionHorizontal() && this.mIsRtl;
        if (!z ? i <= 0 : i >= 0) {
            i2 = -1;
        }
        int abs = Math.abs(i);
        updateLayoutState(i2, abs);
        int access$2000 = this.mLayoutState.mScrollingOffset + fill(recycler, state, this.mLayoutState);
        if (access$2000 < 0) {
            return 0;
        }
        if (z) {
            if (abs > access$2000) {
                i = (-i2) * access$2000;
            }
        } else if (abs > access$2000) {
            i = i2 * access$2000;
        }
        this.mOrientationHelper.offsetChildren(-i);
        this.mLayoutState.mLastScrollDelta = i;
        return i;
    }

    private int handleScrollingMainAxis(int i) {
        int access$2400;
        boolean z = false;
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        ensureOrientationHelper();
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int width = isMainAxisDirectionHorizontal ? this.mParent.getWidth() : this.mParent.getHeight();
        int width2 = isMainAxisDirectionHorizontal ? getWidth() : getHeight();
        if (getLayoutDirection() == 1) {
            z = true;
        }
        if (z) {
            int abs = Math.abs(i);
            if (i < 0) {
                access$2400 = Math.min((width2 + this.mAnchorInfo.mPerpendicularCoordinate) - width, abs);
            } else {
                if (this.mAnchorInfo.mPerpendicularCoordinate + i > 0) {
                    access$2400 = this.mAnchorInfo.mPerpendicularCoordinate;
                }
                return i;
            }
        } else {
            if (i > 0) {
                i = Math.min((width2 - this.mAnchorInfo.mPerpendicularCoordinate) - width, i);
            } else if (this.mAnchorInfo.mPerpendicularCoordinate + i < 0) {
                access$2400 = this.mAnchorInfo.mPerpendicularCoordinate;
            }
            return i;
        }
        i = -access$2400;
        return i;
    }

    private void updateLayoutState(int i, int i2) {
        this.mLayoutState.mLayoutDirection = i;
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
        int i3 = 0;
        boolean z = !isMainAxisDirectionHorizontal && this.mIsRtl;
        if (i == 1) {
            View childAt = getChildAt(getChildCount() - 1);
            this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedEnd(childAt);
            int position = getPosition(childAt);
            View findLastReferenceViewInLine = findLastReferenceViewInLine(childAt, (FlexLine) this.mFlexLines.get(this.mFlexboxHelper.mIndexToFlexLine[position]));
            this.mLayoutState.mItemDirection = 1;
            LayoutState layoutState = this.mLayoutState;
            layoutState.mPosition = position + layoutState.mItemDirection;
            if (this.mFlexboxHelper.mIndexToFlexLine.length <= this.mLayoutState.mPosition) {
                this.mLayoutState.mFlexLinePosition = -1;
            } else {
                this.mLayoutState.mFlexLinePosition = this.mFlexboxHelper.mIndexToFlexLine[this.mLayoutState.mPosition];
            }
            if (z) {
                this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedStart(findLastReferenceViewInLine);
                this.mLayoutState.mScrollingOffset = (-this.mOrientationHelper.getDecoratedStart(findLastReferenceViewInLine)) + this.mOrientationHelper.getStartAfterPadding();
                LayoutState layoutState2 = this.mLayoutState;
                if (layoutState2.mScrollingOffset >= 0) {
                    i3 = this.mLayoutState.mScrollingOffset;
                }
                layoutState2.mScrollingOffset = i3;
            } else {
                this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedEnd(findLastReferenceViewInLine);
                this.mLayoutState.mScrollingOffset = this.mOrientationHelper.getDecoratedEnd(findLastReferenceViewInLine) - this.mOrientationHelper.getEndAfterPadding();
            }
            if ((this.mLayoutState.mFlexLinePosition == -1 || this.mLayoutState.mFlexLinePosition > this.mFlexLines.size() - 1) && this.mLayoutState.mPosition <= getFlexItemCount()) {
                int access$2000 = i2 - this.mLayoutState.mScrollingOffset;
                this.mFlexLinesResult.reset();
                if (access$2000 > 0) {
                    if (isMainAxisDirectionHorizontal) {
                        this.mFlexboxHelper.calculateHorizontalFlexLines(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, access$2000, this.mLayoutState.mPosition, this.mFlexLines);
                    } else {
                        this.mFlexboxHelper.calculateVerticalFlexLines(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, access$2000, this.mLayoutState.mPosition, this.mFlexLines);
                    }
                    this.mFlexboxHelper.determineMainSize(makeMeasureSpec, makeMeasureSpec2, this.mLayoutState.mPosition);
                    this.mFlexboxHelper.stretchViews(this.mLayoutState.mPosition);
                }
            }
        } else {
            View childAt2 = getChildAt(0);
            this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedStart(childAt2);
            int position2 = getPosition(childAt2);
            View findFirstReferenceViewInLine = findFirstReferenceViewInLine(childAt2, (FlexLine) this.mFlexLines.get(this.mFlexboxHelper.mIndexToFlexLine[position2]));
            this.mLayoutState.mItemDirection = 1;
            int i4 = this.mFlexboxHelper.mIndexToFlexLine[position2];
            if (i4 == -1) {
                i4 = 0;
            }
            if (i4 > 0) {
                this.mLayoutState.mPosition = position2 - ((FlexLine) this.mFlexLines.get(i4 - 1)).getItemCount();
            } else {
                this.mLayoutState.mPosition = -1;
            }
            this.mLayoutState.mFlexLinePosition = i4 > 0 ? i4 - 1 : 0;
            if (z) {
                this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedEnd(findFirstReferenceViewInLine);
                this.mLayoutState.mScrollingOffset = this.mOrientationHelper.getDecoratedEnd(findFirstReferenceViewInLine) - this.mOrientationHelper.getEndAfterPadding();
                LayoutState layoutState3 = this.mLayoutState;
                if (layoutState3.mScrollingOffset >= 0) {
                    i3 = this.mLayoutState.mScrollingOffset;
                }
                layoutState3.mScrollingOffset = i3;
            } else {
                this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedStart(findFirstReferenceViewInLine);
                this.mLayoutState.mScrollingOffset = (-this.mOrientationHelper.getDecoratedStart(findFirstReferenceViewInLine)) + this.mOrientationHelper.getStartAfterPadding();
            }
        }
        LayoutState layoutState4 = this.mLayoutState;
        layoutState4.mAvailable = i2 - layoutState4.mScrollingOffset;
    }

    private View findFirstReferenceViewInLine(View view, FlexLine flexLine) {
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int i = flexLine.mItemCount;
        for (int i2 = 1; i2 < i; i2++) {
            View childAt = getChildAt(i2);
            if (!(childAt == null || childAt.getVisibility() == 8)) {
                if (!this.mIsRtl || isMainAxisDirectionHorizontal) {
                    if (this.mOrientationHelper.getDecoratedStart(view) <= this.mOrientationHelper.getDecoratedStart(childAt)) {
                    }
                } else if (this.mOrientationHelper.getDecoratedEnd(view) >= this.mOrientationHelper.getDecoratedEnd(childAt)) {
                }
                view = childAt;
            }
        }
        return view;
    }

    private View findLastReferenceViewInLine(View view, FlexLine flexLine) {
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int childCount = (getChildCount() - flexLine.mItemCount) - 1;
        for (int childCount2 = getChildCount() - 2; childCount2 > childCount; childCount2--) {
            View childAt = getChildAt(childCount2);
            if (!(childAt == null || childAt.getVisibility() == 8)) {
                if (!this.mIsRtl || isMainAxisDirectionHorizontal) {
                    if (this.mOrientationHelper.getDecoratedEnd(view) >= this.mOrientationHelper.getDecoratedEnd(childAt)) {
                    }
                } else if (this.mOrientationHelper.getDecoratedStart(view) <= this.mOrientationHelper.getDecoratedStart(childAt)) {
                }
                view = childAt;
            }
        }
        return view;
    }

    public int computeHorizontalScrollExtent(State state) {
        return computeScrollExtent(state);
    }

    public int computeVerticalScrollExtent(State state) {
        return computeScrollExtent(state);
    }

    private int computeScrollExtent(State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        ensureOrientationHelper();
        View findFirstReferenceChild = findFirstReferenceChild(itemCount);
        View findLastReferenceChild = findLastReferenceChild(itemCount);
        if (state.getItemCount() == 0 || findFirstReferenceChild == null || findLastReferenceChild == null) {
            return 0;
        }
        return Math.min(this.mOrientationHelper.getTotalSpace(), this.mOrientationHelper.getDecoratedEnd(findLastReferenceChild) - this.mOrientationHelper.getDecoratedStart(findFirstReferenceChild));
    }

    public int computeHorizontalScrollOffset(State state) {
        return computeScrollOffset(state);
    }

    public int computeVerticalScrollOffset(State state) {
        return computeScrollOffset(state);
    }

    private int computeScrollOffset(State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View findFirstReferenceChild = findFirstReferenceChild(itemCount);
        View findLastReferenceChild = findLastReferenceChild(itemCount);
        if (!(state.getItemCount() == 0 || findFirstReferenceChild == null || findLastReferenceChild == null)) {
            int position = getPosition(findFirstReferenceChild);
            int position2 = getPosition(findLastReferenceChild);
            int abs = Math.abs(this.mOrientationHelper.getDecoratedEnd(findLastReferenceChild) - this.mOrientationHelper.getDecoratedStart(findFirstReferenceChild));
            int i = this.mFlexboxHelper.mIndexToFlexLine[position];
            if (!(i == 0 || i == -1)) {
                return Math.round((((float) i) * (((float) abs) / ((float) ((this.mFlexboxHelper.mIndexToFlexLine[position2] - i) + 1)))) + ((float) (this.mOrientationHelper.getStartAfterPadding() - this.mOrientationHelper.getDecoratedStart(findFirstReferenceChild))));
            }
        }
        return 0;
    }

    public int computeHorizontalScrollRange(State state) {
        return computeScrollRange(state);
    }

    public int computeVerticalScrollRange(State state) {
        return computeScrollRange(state);
    }

    private int computeScrollRange(State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View findFirstReferenceChild = findFirstReferenceChild(itemCount);
        View findLastReferenceChild = findLastReferenceChild(itemCount);
        if (state.getItemCount() == 0 || findFirstReferenceChild == null || findLastReferenceChild == null) {
            return 0;
        }
        int findFirstVisibleItemPosition = findFirstVisibleItemPosition();
        return (int) ((((float) Math.abs(this.mOrientationHelper.getDecoratedEnd(findLastReferenceChild) - this.mOrientationHelper.getDecoratedStart(findFirstReferenceChild))) / ((float) ((findLastVisibleItemPosition() - findFirstVisibleItemPosition) + 1))) * ((float) state.getItemCount()));
    }

    private boolean shouldMeasureChild(View view, int i, int i2, androidx.recyclerview.widget.RecyclerView.LayoutParams layoutParams) {
        return view.isLayoutRequested() || !isMeasurementCacheEnabled() || !isMeasurementUpToDate(view.getWidth(), i, layoutParams.width) || !isMeasurementUpToDate(view.getHeight(), i2, layoutParams.height);
    }

    private static boolean isMeasurementUpToDate(int i, int i2, int i3) {
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i2);
        boolean z = false;
        if (i3 > 0 && i != i3) {
            return false;
        }
        if (mode == Integer.MIN_VALUE) {
            if (size >= i) {
                z = true;
            }
            return z;
        } else if (mode == 0) {
            return true;
        } else {
            if (mode != 1073741824) {
                return false;
            }
            if (size == i) {
                z = true;
            }
            return z;
        }
    }

    private void clearFlexLines() {
        this.mFlexLines.clear();
        this.mAnchorInfo.reset();
        this.mAnchorInfo.mPerpendicularCoordinate = 0;
    }

    private int getChildLeft(View view) {
        return getDecoratedLeft(view) - ((androidx.recyclerview.widget.RecyclerView.LayoutParams) view.getLayoutParams()).leftMargin;
    }

    private int getChildRight(View view) {
        return getDecoratedRight(view) + ((androidx.recyclerview.widget.RecyclerView.LayoutParams) view.getLayoutParams()).rightMargin;
    }

    private int getChildTop(View view) {
        return getDecoratedTop(view) - ((androidx.recyclerview.widget.RecyclerView.LayoutParams) view.getLayoutParams()).topMargin;
    }

    private int getChildBottom(View view) {
        return getDecoratedBottom(view) + ((androidx.recyclerview.widget.RecyclerView.LayoutParams) view.getLayoutParams()).bottomMargin;
    }

    private boolean isViewVisible(View view, boolean z) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int width = getWidth() - getPaddingRight();
        int height = getHeight() - getPaddingBottom();
        int childLeft = getChildLeft(view);
        int childTop = getChildTop(view);
        int childRight = getChildRight(view);
        int childBottom = getChildBottom(view);
        boolean z2 = true;
        boolean z3 = paddingLeft <= childLeft && width >= childRight;
        boolean z4 = childLeft >= width || childRight >= paddingLeft;
        boolean z5 = paddingTop <= childTop && height >= childBottom;
        boolean z6 = childTop >= height || childBottom >= paddingTop;
        if (z) {
            if (!z3 || !z5) {
                z2 = false;
            }
            return z2;
        }
        if (!z4 || !z6) {
            z2 = false;
        }
        return z2;
    }

    public int findFirstVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(0, getChildCount(), false);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(0, getChildCount(), true);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    public int findLastVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(getChildCount() - 1, -1, false);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    public int findLastCompletelyVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(getChildCount() - 1, -1, true);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    private View findOneVisibleChild(int i, int i2, boolean z) {
        int i3 = i2 > i ? 1 : -1;
        while (i != i2) {
            View childAt = getChildAt(i);
            if (isViewVisible(childAt, z)) {
                return childAt;
            }
            i += i3;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public int getPositionToFlexLineIndex(int i) {
        return this.mFlexboxHelper.mIndexToFlexLine[i];
    }
}
