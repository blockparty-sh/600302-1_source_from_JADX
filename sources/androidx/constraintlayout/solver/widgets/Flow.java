package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour;
import java.util.ArrayList;
import java.util.HashMap;

public class Flow extends VirtualLayout {
    public static final int HORIZONTAL_ALIGN_CENTER = 2;
    public static final int HORIZONTAL_ALIGN_END = 1;
    public static final int HORIZONTAL_ALIGN_START = 0;
    public static final int VERTICAL_ALIGN_BASELINE = 3;
    public static final int VERTICAL_ALIGN_BOTTOM = 1;
    public static final int VERTICAL_ALIGN_CENTER = 2;
    public static final int VERTICAL_ALIGN_TOP = 0;
    public static final int WRAP_ALIGNED = 2;
    public static final int WRAP_CHAIN = 1;
    public static final int WRAP_NONE = 0;
    private ConstraintWidget[] mAlignedBiggestElementsInCols = null;
    private ConstraintWidget[] mAlignedBiggestElementsInRows = null;
    private int[] mAlignedDimensions = null;
    private ArrayList<WidgetsList> mChainList = new ArrayList<>();
    /* access modifiers changed from: private */
    public float mFirstHorizontalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mFirstHorizontalStyle = -1;
    /* access modifiers changed from: private */
    public float mFirstVerticalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mFirstVerticalStyle = -1;
    /* access modifiers changed from: private */
    public int mHorizontalAlign = 2;
    /* access modifiers changed from: private */
    public float mHorizontalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mHorizontalGap = 0;
    /* access modifiers changed from: private */
    public int mHorizontalStyle = -1;
    /* access modifiers changed from: private */
    public float mLastHorizontalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mLastHorizontalStyle = -1;
    /* access modifiers changed from: private */
    public float mLastVerticalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mLastVerticalStyle = -1;
    private int mMaxElementsWrap = -1;
    private int mOrientation = 0;
    /* access modifiers changed from: private */
    public int mVerticalAlign = 2;
    /* access modifiers changed from: private */
    public float mVerticalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mVerticalGap = 0;
    /* access modifiers changed from: private */
    public int mVerticalStyle = -1;
    private int mWrapMode = 0;

    private class WidgetsList {
        /* access modifiers changed from: private */
        public ConstraintWidget biggest = null;
        int biggestDimension = 0;
        private ConstraintAnchor mBottom;
        private int mCount = 0;
        private int mHeight = 0;
        private ConstraintAnchor mLeft;
        private int mOrientation = 0;
        private int mPaddingBottom = 0;
        private int mPaddingLeft = 0;
        private int mPaddingRight = 0;
        private int mPaddingTop = 0;
        private ConstraintAnchor mRight;
        private int mStartIndex = 0;
        private ConstraintAnchor mTop;
        private int mWidth = 0;

        public WidgetsList(int i, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4) {
            this.mOrientation = i;
            this.mLeft = constraintAnchor;
            this.mTop = constraintAnchor2;
            this.mRight = constraintAnchor3;
            this.mBottom = constraintAnchor4;
            this.mPaddingLeft = Flow.this.getPaddingLeft();
            this.mPaddingTop = Flow.this.getPaddingTop();
            this.mPaddingRight = Flow.this.getPaddingRight();
            this.mPaddingBottom = Flow.this.getPaddingBottom();
        }

        public void setup(int i, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i2, int i3, int i4, int i5) {
            this.mOrientation = i;
            this.mLeft = constraintAnchor;
            this.mTop = constraintAnchor2;
            this.mRight = constraintAnchor3;
            this.mBottom = constraintAnchor4;
            this.mPaddingLeft = i2;
            this.mPaddingTop = i3;
            this.mPaddingRight = i4;
            this.mPaddingBottom = i5;
        }

        public void clear() {
            this.biggestDimension = 0;
            this.biggest = null;
            this.mWidth = 0;
            this.mHeight = 0;
            this.mStartIndex = 0;
            this.mCount = 0;
        }

        public void setStartIndex(int i) {
            this.mStartIndex = i;
        }

        public int getWidth() {
            if (this.mOrientation == 0) {
                return this.mWidth - Flow.this.mHorizontalGap;
            }
            return this.mWidth;
        }

        public int getHeight() {
            if (this.mOrientation == 1) {
                return this.mHeight - Flow.this.mVerticalGap;
            }
            return this.mHeight;
        }

        public void add(ConstraintWidget constraintWidget) {
            int i = 0;
            if (this.mOrientation == 0) {
                int access$200 = Flow.this.getWidgetWidth(constraintWidget);
                int access$000 = Flow.this.mHorizontalGap;
                if (constraintWidget.getVisibility() != 8) {
                    i = access$000;
                }
                this.mWidth += access$200 + i;
                int access$300 = Flow.this.getWidgetHeight(constraintWidget);
                if (this.biggest == null || this.biggestDimension < access$300) {
                    this.biggest = constraintWidget;
                    this.biggestDimension = access$300;
                    this.mHeight = access$300;
                }
            } else {
                int access$2002 = Flow.this.getWidgetWidth(constraintWidget);
                int access$3002 = Flow.this.getWidgetHeight(constraintWidget);
                int access$100 = Flow.this.mVerticalGap;
                if (constraintWidget.getVisibility() != 8) {
                    i = access$100;
                }
                this.mHeight += access$3002 + i;
                if (this.biggest == null || this.biggestDimension < access$2002) {
                    this.biggest = constraintWidget;
                    this.biggestDimension = access$2002;
                    this.mWidth = access$2002;
                }
            }
            this.mCount++;
        }

        /* JADX WARNING: Removed duplicated region for block: B:46:0x00b4  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void createConstraints(boolean r17, int r18, boolean r19) {
            /*
                r16 = this;
                r0 = r16
                int r1 = r0.mCount
                r2 = 0
                r3 = 0
            L_0x0006:
                if (r3 >= r1) goto L_0x0017
                androidx.constraintlayout.solver.widgets.Flow r4 = androidx.constraintlayout.solver.widgets.Flow.this
                androidx.constraintlayout.solver.widgets.ConstraintWidget[] r4 = r4.mWidgets
                int r5 = r0.mStartIndex
                int r5 = r5 + r3
                r4 = r4[r5]
                r4.resetAnchors()
                int r3 = r3 + 1
                goto L_0x0006
            L_0x0017:
                if (r1 == 0) goto L_0x0314
                androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = r0.biggest
                if (r3 != 0) goto L_0x001f
                goto L_0x0314
            L_0x001f:
                if (r19 == 0) goto L_0x0025
                if (r18 != 0) goto L_0x0025
                r4 = 1
                goto L_0x0026
            L_0x0025:
                r4 = 0
            L_0x0026:
                r5 = -1
                r6 = 0
                r7 = -1
                r8 = -1
            L_0x002a:
                if (r6 >= r1) goto L_0x0049
                if (r17 == 0) goto L_0x0032
                int r9 = r1 + -1
                int r9 = r9 - r6
                goto L_0x0033
            L_0x0032:
                r9 = r6
            L_0x0033:
                androidx.constraintlayout.solver.widgets.Flow r10 = androidx.constraintlayout.solver.widgets.Flow.this
                androidx.constraintlayout.solver.widgets.ConstraintWidget[] r10 = r10.mWidgets
                int r11 = r0.mStartIndex
                int r11 = r11 + r9
                r9 = r10[r11]
                int r9 = r9.getVisibility()
                if (r9 != 0) goto L_0x0046
                if (r7 != r5) goto L_0x0045
                r7 = r6
            L_0x0045:
                r8 = r6
            L_0x0046:
                int r6 = r6 + 1
                goto L_0x002a
            L_0x0049:
                r6 = 0
                int r9 = r0.mOrientation
                if (r9 != 0) goto L_0x01b1
                androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r0.biggest
                androidx.constraintlayout.solver.widgets.Flow r10 = androidx.constraintlayout.solver.widgets.Flow.this
                int r10 = r10.mVerticalStyle
                r9.setVerticalChainStyle(r10)
                int r10 = r0.mPaddingTop
                if (r18 <= 0) goto L_0x0064
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mVerticalGap
                int r10 = r10 + r11
            L_0x0064:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r9.mTop
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r12 = r0.mTop
                r11.connect(r12, r10)
                if (r19 == 0) goto L_0x0076
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r9.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mBottom
                int r12 = r0.mPaddingBottom
                r10.connect(r11, r12)
            L_0x0076:
                if (r18 <= 0) goto L_0x0083
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r0.mTop
                androidx.constraintlayout.solver.widgets.ConstraintWidget r10 = r10.mOwner
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r10.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r9.mTop
                r10.connect(r11, r2)
            L_0x0083:
                androidx.constraintlayout.solver.widgets.Flow r10 = androidx.constraintlayout.solver.widgets.Flow.this
                int r10 = r10.mVerticalAlign
                r11 = 3
                if (r10 != r11) goto L_0x00af
                boolean r10 = r9.hasBaseline()
                if (r10 != 0) goto L_0x00af
                r10 = 0
            L_0x0093:
                if (r10 >= r1) goto L_0x00af
                if (r17 == 0) goto L_0x009b
                int r12 = r1 + -1
                int r12 = r12 - r10
                goto L_0x009c
            L_0x009b:
                r12 = r10
            L_0x009c:
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                androidx.constraintlayout.solver.widgets.ConstraintWidget[] r13 = r13.mWidgets
                int r14 = r0.mStartIndex
                int r14 = r14 + r12
                r12 = r13[r14]
                boolean r13 = r12.hasBaseline()
                if (r13 == 0) goto L_0x00ac
                goto L_0x00b0
            L_0x00ac:
                int r10 = r10 + 1
                goto L_0x0093
            L_0x00af:
                r12 = r9
            L_0x00b0:
                r10 = r6
                r6 = 0
            L_0x00b2:
                if (r6 >= r1) goto L_0x0314
                if (r17 == 0) goto L_0x00ba
                int r13 = r1 + -1
                int r13 = r13 - r6
                goto L_0x00bb
            L_0x00ba:
                r13 = r6
            L_0x00bb:
                androidx.constraintlayout.solver.widgets.Flow r14 = androidx.constraintlayout.solver.widgets.Flow.this
                androidx.constraintlayout.solver.widgets.ConstraintWidget[] r14 = r14.mWidgets
                int r15 = r0.mStartIndex
                int r15 = r15 + r13
                r14 = r14[r15]
                if (r6 != 0) goto L_0x00cf
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r15 = r14.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mLeft
                int r3 = r0.mPaddingLeft
                r14.connect(r15, r11, r3)
            L_0x00cf:
                if (r13 != 0) goto L_0x0112
                androidx.constraintlayout.solver.widgets.Flow r3 = androidx.constraintlayout.solver.widgets.Flow.this
                int r3 = r3.mHorizontalStyle
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                float r11 = r11.mHorizontalBias
                int r13 = r0.mStartIndex
                if (r13 != 0) goto L_0x00f6
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                int r13 = r13.mFirstHorizontalStyle
                if (r13 == r5) goto L_0x00f6
                androidx.constraintlayout.solver.widgets.Flow r3 = androidx.constraintlayout.solver.widgets.Flow.this
                int r3 = r3.mFirstHorizontalStyle
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                float r11 = r11.mFirstHorizontalBias
                goto L_0x010c
            L_0x00f6:
                if (r19 == 0) goto L_0x010c
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                int r13 = r13.mLastHorizontalStyle
                if (r13 == r5) goto L_0x010c
                androidx.constraintlayout.solver.widgets.Flow r3 = androidx.constraintlayout.solver.widgets.Flow.this
                int r3 = r3.mLastHorizontalStyle
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                float r11 = r11.mLastHorizontalBias
            L_0x010c:
                r14.setHorizontalChainStyle(r3)
                r14.setHorizontalBiasPercent(r11)
            L_0x0112:
                int r3 = r1 + -1
                if (r6 != r3) goto L_0x011f
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mRight
                int r13 = r0.mPaddingRight
                r14.connect(r3, r11, r13)
            L_0x011f:
                if (r10 == 0) goto L_0x014a
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r10.mRight
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                int r13 = r13.mHorizontalGap
                r3.connect(r11, r13)
                if (r6 != r7) goto L_0x0137
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mLeft
                int r11 = r0.mPaddingLeft
                r3.setGoneMargin(r11)
            L_0x0137:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r10.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r14.mLeft
                r3.connect(r11, r2)
                r3 = 1
                int r11 = r8 + 1
                if (r6 != r11) goto L_0x014a
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r10.mRight
                int r10 = r0.mPaddingRight
                r3.setGoneMargin(r10)
            L_0x014a:
                if (r14 == r9) goto L_0x01aa
                androidx.constraintlayout.solver.widgets.Flow r3 = androidx.constraintlayout.solver.widgets.Flow.this
                int r3 = r3.mVerticalAlign
                r10 = 3
                if (r3 != r10) goto L_0x016b
                boolean r3 = r12.hasBaseline()
                if (r3 == 0) goto L_0x016b
                if (r14 == r12) goto L_0x016b
                boolean r3 = r14.hasBaseline()
                if (r3 == 0) goto L_0x016b
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mBaseline
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r12.mBaseline
                r3.connect(r11, r2)
                goto L_0x01ab
            L_0x016b:
                androidx.constraintlayout.solver.widgets.Flow r3 = androidx.constraintlayout.solver.widgets.Flow.this
                int r3 = r3.mVerticalAlign
                if (r3 == 0) goto L_0x01a2
                r11 = 1
                if (r3 == r11) goto L_0x019a
                if (r4 == 0) goto L_0x018b
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mTop
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mTop
                int r13 = r0.mPaddingTop
                r3.connect(r11, r13)
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mBottom
                int r13 = r0.mPaddingBottom
                r3.connect(r11, r13)
                goto L_0x01ab
            L_0x018b:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mTop
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r9.mTop
                r3.connect(r11, r2)
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r9.mBottom
                r3.connect(r11, r2)
                goto L_0x01ab
            L_0x019a:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r9.mBottom
                r3.connect(r11, r2)
                goto L_0x01ab
            L_0x01a2:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mTop
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r9.mTop
                r3.connect(r11, r2)
                goto L_0x01ab
            L_0x01aa:
                r10 = 3
            L_0x01ab:
                int r6 = r6 + 1
                r10 = r14
                r11 = 3
                goto L_0x00b2
            L_0x01b1:
                androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = r0.biggest
                androidx.constraintlayout.solver.widgets.Flow r9 = androidx.constraintlayout.solver.widgets.Flow.this
                int r9 = r9.mVerticalStyle
                r3.setHorizontalChainStyle(r9)
                int r9 = r0.mPaddingLeft
                if (r18 <= 0) goto L_0x01c7
                androidx.constraintlayout.solver.widgets.Flow r10 = androidx.constraintlayout.solver.widgets.Flow.this
                int r10 = r10.mHorizontalGap
                int r9 = r9 + r10
            L_0x01c7:
                if (r17 == 0) goto L_0x01e9
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r3.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mRight
                r10.connect(r11, r9)
                if (r19 == 0) goto L_0x01db
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r3.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r0.mLeft
                int r11 = r0.mPaddingRight
                r9.connect(r10, r11)
            L_0x01db:
                if (r18 <= 0) goto L_0x0208
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r0.mRight
                androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r9.mOwner
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r3.mRight
                r9.connect(r10, r2)
                goto L_0x0208
            L_0x01e9:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r3.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mLeft
                r10.connect(r11, r9)
                if (r19 == 0) goto L_0x01fb
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r3.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r0.mRight
                int r11 = r0.mPaddingRight
                r9.connect(r10, r11)
            L_0x01fb:
                if (r18 <= 0) goto L_0x0208
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r0.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r9.mOwner
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r3.mLeft
                r9.connect(r10, r2)
            L_0x0208:
                r9 = r6
                r6 = 0
            L_0x020a:
                if (r6 >= r1) goto L_0x0314
                androidx.constraintlayout.solver.widgets.Flow r10 = androidx.constraintlayout.solver.widgets.Flow.this
                androidx.constraintlayout.solver.widgets.ConstraintWidget[] r10 = r10.mWidgets
                int r11 = r0.mStartIndex
                int r11 = r11 + r6
                r10 = r10[r11]
                if (r6 != 0) goto L_0x0261
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r10.mTop
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r12 = r0.mTop
                int r13 = r0.mPaddingTop
                r10.connect(r11, r12, r13)
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mVerticalStyle
                androidx.constraintlayout.solver.widgets.Flow r12 = androidx.constraintlayout.solver.widgets.Flow.this
                float r12 = r12.mVerticalBias
                int r13 = r0.mStartIndex
                if (r13 != 0) goto L_0x0245
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                int r13 = r13.mFirstVerticalStyle
                if (r13 == r5) goto L_0x0245
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mFirstVerticalStyle
                androidx.constraintlayout.solver.widgets.Flow r12 = androidx.constraintlayout.solver.widgets.Flow.this
                float r12 = r12.mFirstVerticalBias
                goto L_0x025b
            L_0x0245:
                if (r19 == 0) goto L_0x025b
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                int r13 = r13.mLastVerticalStyle
                if (r13 == r5) goto L_0x025b
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mLastVerticalStyle
                androidx.constraintlayout.solver.widgets.Flow r12 = androidx.constraintlayout.solver.widgets.Flow.this
                float r12 = r12.mLastVerticalBias
            L_0x025b:
                r10.setVerticalChainStyle(r11)
                r10.setVerticalBiasPercent(r12)
            L_0x0261:
                int r11 = r1 + -1
                if (r6 != r11) goto L_0x026e
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r10.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r12 = r0.mBottom
                int r13 = r0.mPaddingBottom
                r10.connect(r11, r12, r13)
            L_0x026e:
                if (r9 == 0) goto L_0x0299
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r10.mTop
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r12 = r9.mBottom
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                int r13 = r13.mVerticalGap
                r11.connect(r12, r13)
                if (r6 != r7) goto L_0x0286
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r10.mTop
                int r12 = r0.mPaddingTop
                r11.setGoneMargin(r12)
            L_0x0286:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r9.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r12 = r10.mTop
                r11.connect(r12, r2)
                r11 = 1
                int r12 = r8 + 1
                if (r6 != r12) goto L_0x0299
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.mBottom
                int r11 = r0.mPaddingBottom
                r9.setGoneMargin(r11)
            L_0x0299:
                if (r10 == r3) goto L_0x030e
                r9 = 2
                if (r17 == 0) goto L_0x02cb
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mHorizontalAlign
                if (r11 == 0) goto L_0x02c3
                r12 = 1
                if (r11 == r12) goto L_0x02bb
                if (r11 == r9) goto L_0x02ac
                goto L_0x030e
            L_0x02ac:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mLeft
                r9.connect(r11, r2)
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mRight
                r9.connect(r11, r2)
                goto L_0x030e
            L_0x02bb:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mLeft
                r9.connect(r11, r2)
                goto L_0x030e
            L_0x02c3:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mRight
                r9.connect(r11, r2)
                goto L_0x030e
            L_0x02cb:
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mHorizontalAlign
                if (r11 == 0) goto L_0x0305
                r12 = 1
                if (r11 == r12) goto L_0x02fd
                if (r11 == r9) goto L_0x02d9
                goto L_0x030f
            L_0x02d9:
                if (r4 == 0) goto L_0x02ee
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mLeft
                int r13 = r0.mPaddingLeft
                r9.connect(r11, r13)
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mRight
                int r13 = r0.mPaddingRight
                r9.connect(r11, r13)
                goto L_0x030f
            L_0x02ee:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mLeft
                r9.connect(r11, r2)
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mRight
                r9.connect(r11, r2)
                goto L_0x030f
            L_0x02fd:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mRight
                r9.connect(r11, r2)
                goto L_0x030f
            L_0x0305:
                r12 = 1
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mLeft
                r9.connect(r11, r2)
                goto L_0x030f
            L_0x030e:
                r12 = 1
            L_0x030f:
                int r6 = r6 + 1
                r9 = r10
                goto L_0x020a
            L_0x0314:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Flow.WidgetsList.createConstraints(boolean, int, boolean):void");
        }
    }

    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> hashMap) {
        super.copy(constraintWidget, hashMap);
        Flow flow = (Flow) constraintWidget;
        this.mHorizontalStyle = flow.mHorizontalStyle;
        this.mVerticalStyle = flow.mVerticalStyle;
        this.mFirstHorizontalStyle = flow.mFirstHorizontalStyle;
        this.mFirstVerticalStyle = flow.mFirstVerticalStyle;
        this.mLastHorizontalStyle = flow.mLastHorizontalStyle;
        this.mLastVerticalStyle = flow.mLastVerticalStyle;
        this.mHorizontalBias = flow.mHorizontalBias;
        this.mVerticalBias = flow.mVerticalBias;
        this.mFirstHorizontalBias = flow.mFirstHorizontalBias;
        this.mFirstVerticalBias = flow.mFirstVerticalBias;
        this.mLastHorizontalBias = flow.mLastHorizontalBias;
        this.mLastVerticalBias = flow.mLastVerticalBias;
        this.mHorizontalGap = flow.mHorizontalGap;
        this.mVerticalGap = flow.mVerticalGap;
        this.mHorizontalAlign = flow.mHorizontalAlign;
        this.mVerticalAlign = flow.mVerticalAlign;
        this.mWrapMode = flow.mWrapMode;
        this.mMaxElementsWrap = flow.mMaxElementsWrap;
        this.mOrientation = flow.mOrientation;
    }

    public void setOrientation(int i) {
        this.mOrientation = i;
    }

    public void setFirstHorizontalStyle(int i) {
        this.mFirstHorizontalStyle = i;
    }

    public void setFirstVerticalStyle(int i) {
        this.mFirstVerticalStyle = i;
    }

    public void setLastHorizontalStyle(int i) {
        this.mLastHorizontalStyle = i;
    }

    public void setLastVerticalStyle(int i) {
        this.mLastVerticalStyle = i;
    }

    public void setHorizontalStyle(int i) {
        this.mHorizontalStyle = i;
    }

    public void setVerticalStyle(int i) {
        this.mVerticalStyle = i;
    }

    public void setHorizontalBias(float f) {
        this.mHorizontalBias = f;
    }

    public void setVerticalBias(float f) {
        this.mVerticalBias = f;
    }

    public void setFirstHorizontalBias(float f) {
        this.mFirstHorizontalBias = f;
    }

    public void setFirstVerticalBias(float f) {
        this.mFirstVerticalBias = f;
    }

    public void setLastHorizontalBias(float f) {
        this.mLastHorizontalBias = f;
    }

    public void setLastVerticalBias(float f) {
        this.mLastVerticalBias = f;
    }

    public void setHorizontalAlign(int i) {
        this.mHorizontalAlign = i;
    }

    public void setVerticalAlign(int i) {
        this.mVerticalAlign = i;
    }

    public void setWrapMode(int i) {
        this.mWrapMode = i;
    }

    public void setHorizontalGap(int i) {
        this.mHorizontalGap = i;
    }

    public void setVerticalGap(int i) {
        this.mVerticalGap = i;
    }

    public void setMaxElementsWrap(int i) {
        this.mMaxElementsWrap = i;
    }

    /* access modifiers changed from: private */
    public final int getWidgetWidth(ConstraintWidget constraintWidget) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultWidth == 0) {
            return 0;
        }
        return constraintWidget.getWidth();
    }

    /* access modifiers changed from: private */
    public final int getWidgetHeight(ConstraintWidget constraintWidget) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultHeight == 0) {
            return 0;
        }
        return constraintWidget.getHeight();
    }

    public void measure(int i, int i2, int i3, int i4) {
        boolean z = false;
        if (this.mWidgetsCount <= 0 || measureChildren()) {
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            int[] iArr = new int[2];
            int i5 = (i2 - paddingLeft) - paddingRight;
            if (this.mOrientation == 1) {
                i5 = (i4 - paddingTop) - paddingBottom;
            }
            if (this.mOrientation == 0) {
                if (this.mHorizontalStyle == -1) {
                    this.mHorizontalStyle = 0;
                }
                if (this.mVerticalStyle == -1) {
                    this.mVerticalStyle = 0;
                }
            } else {
                if (this.mHorizontalStyle == -1) {
                    this.mHorizontalStyle = 0;
                }
                if (this.mVerticalStyle == -1) {
                    this.mVerticalStyle = 0;
                }
            }
            int i6 = this.mWrapMode;
            if (i6 == 0) {
                measureNoWrap(this.mWidgets, this.mOrientation, i5, iArr);
            } else if (i6 == 1) {
                measureChainWrap(this.mWidgets, this.mOrientation, i5, iArr);
            } else if (i6 == 2) {
                measureAligned(this.mWidgets, this.mOrientation, i5, iArr);
            }
            int i7 = iArr[0] + paddingLeft + paddingRight;
            int i8 = iArr[1] + paddingTop + paddingBottom;
            if (i != 1073741824) {
                i2 = i == Integer.MIN_VALUE ? Math.min(i7, i2) : i == 0 ? i7 : 0;
            }
            if (i3 != 1073741824) {
                i4 = i3 == Integer.MIN_VALUE ? Math.min(i8, i4) : i3 == 0 ? i8 : 0;
            }
            setMeasure(i2, i4);
            if (this.mWidgetsCount > 0) {
                z = true;
            }
            needsCallbackFromSolver(z);
            return;
        }
        setMeasure(0, 0);
        needsCallbackFromSolver(false);
    }

    private void measureChainWrap(ConstraintWidget[] constraintWidgetArr, int i, int i2, int[] iArr) {
        int i3;
        int i4;
        int i5 = i2;
        int i6 = this.mWidgetsCount;
        if (i6 != 0) {
            this.mChainList.clear();
            WidgetsList widgetsList = new WidgetsList(i, this.mLeft, this.mTop, this.mRight, this.mBottom);
            this.mChainList.add(widgetsList);
            if (i == 0) {
                int i7 = this.mHorizontalGap * 2;
                WidgetsList widgetsList2 = widgetsList;
                for (int i8 = 0; i8 < i6; i8++) {
                    ConstraintWidget constraintWidget = constraintWidgetArr[i8];
                    int widgetWidth = getWidgetWidth(constraintWidget);
                    boolean z = (i7 + widgetWidth) + this.mHorizontalGap > i5 && widgetsList2.biggest != null;
                    if (!z && i8 > 0) {
                        int i9 = this.mMaxElementsWrap;
                        if (i9 > 0 && i8 % i9 == 0) {
                            z = true;
                        }
                    }
                    if (z) {
                        i4 = this.mHorizontalGap * 2;
                        WidgetsList widgetsList3 = new WidgetsList(i, this.mLeft, this.mTop, this.mRight, this.mBottom);
                        widgetsList3.setStartIndex(i8);
                        this.mChainList.add(widgetsList3);
                        widgetsList2 = widgetsList3;
                    } else {
                        i4 = i7;
                    }
                    i7 = i4 + widgetWidth + this.mHorizontalGap;
                    widgetsList2.add(constraintWidget);
                }
            } else {
                int i10 = this.mVerticalGap * 2;
                WidgetsList widgetsList4 = widgetsList;
                for (int i11 = 0; i11 < i6; i11++) {
                    ConstraintWidget constraintWidget2 = constraintWidgetArr[i11];
                    int widgetHeight = getWidgetHeight(constraintWidget2);
                    boolean z2 = (i10 + widgetHeight) + this.mVerticalGap > i5 && widgetsList4.biggest != null;
                    if (!z2 && i11 > 0) {
                        int i12 = this.mMaxElementsWrap;
                        if (i12 > 0 && i11 % i12 == 0) {
                            z2 = true;
                        }
                    }
                    if (z2) {
                        i3 = this.mVerticalGap * 2;
                        WidgetsList widgetsList5 = new WidgetsList(i, this.mLeft, this.mTop, this.mRight, this.mBottom);
                        widgetsList5.setStartIndex(i11);
                        this.mChainList.add(widgetsList5);
                        widgetsList4 = widgetsList5;
                    } else {
                        i3 = i10;
                    }
                    i10 = i3 + widgetHeight;
                    widgetsList4.add(constraintWidget2);
                }
            }
            int size = this.mChainList.size();
            ConstraintAnchor constraintAnchor = this.mLeft;
            ConstraintAnchor constraintAnchor2 = this.mTop;
            ConstraintAnchor constraintAnchor3 = this.mRight;
            ConstraintAnchor constraintAnchor4 = this.mBottom;
            ConstraintAnchor constraintAnchor5 = constraintAnchor3;
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int paddingRight = getPaddingRight();
            int paddingBottom = getPaddingBottom();
            int i13 = 0;
            ConstraintAnchor constraintAnchor6 = constraintAnchor2;
            ConstraintAnchor constraintAnchor7 = constraintAnchor4;
            int i14 = 0;
            ConstraintAnchor constraintAnchor8 = constraintAnchor;
            for (int i15 = 0; i15 < size; i15++) {
                WidgetsList widgetsList6 = (WidgetsList) this.mChainList.get(i15);
                if (i == 0) {
                    if (i15 < size - 1) {
                        constraintAnchor7 = ((WidgetsList) this.mChainList.get(i15 + 1)).biggest.mTop;
                        paddingBottom = 0;
                    } else {
                        constraintAnchor7 = this.mBottom;
                        paddingBottom = getPaddingBottom();
                    }
                    constraintAnchor6 = widgetsList6.biggest.mBottom;
                    widgetsList6.setup(i, constraintAnchor8, constraintAnchor6, constraintAnchor5, constraintAnchor7, paddingLeft, paddingTop, paddingRight, paddingBottom);
                    i14 = Math.max(i14, widgetsList6.getWidth());
                    i13 += widgetsList6.getHeight();
                    if (i15 > 0) {
                        i13 += this.mVerticalGap;
                    }
                    paddingTop = 0;
                } else {
                    if (i15 < size - 1) {
                        constraintAnchor5 = ((WidgetsList) this.mChainList.get(i15 + 1)).biggest.mLeft;
                        paddingRight = 0;
                    } else {
                        constraintAnchor5 = this.mRight;
                        paddingRight = getPaddingRight();
                    }
                    constraintAnchor8 = widgetsList6.biggest.mRight;
                    widgetsList6.setup(i, constraintAnchor8, constraintAnchor6, constraintAnchor5, constraintAnchor7, paddingLeft, paddingTop, paddingRight, paddingBottom);
                    i14 += widgetsList6.getWidth();
                    i13 = Math.max(i13, widgetsList6.getHeight());
                    if (i15 > 0) {
                        i14 += this.mHorizontalGap;
                    }
                    paddingLeft = 0;
                }
            }
            iArr[0] = i14;
            iArr[1] = i13;
        }
    }

    private void measureNoWrap(ConstraintWidget[] constraintWidgetArr, int i, int i2, int[] iArr) {
        WidgetsList widgetsList;
        int i3 = this.mWidgetsCount;
        if (i3 != 0) {
            if (this.mChainList.size() == 0) {
                WidgetsList widgetsList2 = new WidgetsList(i, this.mLeft, this.mTop, this.mRight, this.mBottom);
                this.mChainList.add(widgetsList2);
                widgetsList = widgetsList2;
            } else {
                widgetsList = (WidgetsList) this.mChainList.get(0);
                widgetsList.clear();
                widgetsList.setup(i, this.mLeft, this.mTop, this.mRight, this.mBottom, getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
            }
            for (int i4 = 0; i4 < i3; i4++) {
                widgetsList.add(constraintWidgetArr[i4]);
            }
            iArr[0] = widgetsList.getWidth();
            iArr[1] = widgetsList.getHeight();
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Regions count limit reached
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
        */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x012c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0065  */
    private void measureAligned(androidx.constraintlayout.solver.widgets.ConstraintWidget[] r12, int r13, int r14, int[] r15) {
        /*
            r11 = this;
            r0 = 0
            if (r13 != 0) goto L_0x0028
            int r1 = r11.mMaxElementsWrap
            if (r1 > 0) goto L_0x0025
            r1 = 0
            r2 = 0
            r3 = 0
        L_0x000a:
            int r4 = r11.mWidgetsCount
            if (r1 >= r4) goto L_0x0026
            if (r1 <= 0) goto L_0x0013
            int r4 = r11.mHorizontalGap
            int r2 = r2 + r4
        L_0x0013:
            r4 = r12[r1]
            if (r4 != 0) goto L_0x0018
            goto L_0x0022
        L_0x0018:
            int r4 = r11.getWidgetWidth(r4)
            int r2 = r2 + r4
            if (r2 <= r14) goto L_0x0020
            goto L_0x0026
        L_0x0020:
            int r3 = r3 + 1
        L_0x0022:
            int r1 = r1 + 1
            goto L_0x000a
        L_0x0025:
            r3 = r1
        L_0x0026:
            r1 = 0
            goto L_0x004c
        L_0x0028:
            int r1 = r11.mMaxElementsWrap
            if (r1 > 0) goto L_0x004b
            r1 = 0
            r2 = 0
            r3 = 0
        L_0x002f:
            int r4 = r11.mWidgetsCount
            if (r1 >= r4) goto L_0x004a
            if (r1 <= 0) goto L_0x0038
            int r4 = r11.mVerticalGap
            int r2 = r2 + r4
        L_0x0038:
            r4 = r12[r1]
            if (r4 != 0) goto L_0x003d
            goto L_0x0047
        L_0x003d:
            int r4 = r11.getWidgetHeight(r4)
            int r2 = r2 + r4
            if (r2 <= r14) goto L_0x0045
            goto L_0x004a
        L_0x0045:
            int r3 = r3 + 1
        L_0x0047:
            int r1 = r1 + 1
            goto L_0x002f
        L_0x004a:
            r1 = r3
        L_0x004b:
            r3 = 0
        L_0x004c:
            int[] r2 = r11.mAlignedDimensions
            if (r2 != 0) goto L_0x0055
            r2 = 2
            int[] r2 = new int[r2]
            r11.mAlignedDimensions = r2
        L_0x0055:
            r2 = 1
            if (r1 != 0) goto L_0x005a
            if (r13 == r2) goto L_0x005e
        L_0x005a:
            if (r3 != 0) goto L_0x0061
            if (r13 != 0) goto L_0x0061
        L_0x005e:
            r4 = r1
        L_0x005f:
            r1 = 1
            goto L_0x0063
        L_0x0061:
            r4 = r1
            r1 = 0
        L_0x0063:
            if (r1 != 0) goto L_0x012c
            if (r13 != 0) goto L_0x0073
            int r4 = r11.mWidgetsCount
            float r4 = (float) r4
            float r5 = (float) r3
            float r4 = r4 / r5
            double r4 = (double) r4
            double r4 = java.lang.Math.ceil(r4)
            int r4 = (int) r4
            goto L_0x007e
        L_0x0073:
            int r3 = r11.mWidgetsCount
            float r3 = (float) r3
            float r5 = (float) r4
            float r3 = r3 / r5
            double r5 = (double) r3
            double r5 = java.lang.Math.ceil(r5)
            int r3 = (int) r5
        L_0x007e:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r5 = r11.mAlignedBiggestElementsInCols
            r6 = 0
            if (r5 == 0) goto L_0x008b
            int r7 = r5.length
            if (r7 >= r3) goto L_0x0087
            goto L_0x008b
        L_0x0087:
            java.util.Arrays.fill(r5, r6)
            goto L_0x008f
        L_0x008b:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r5 = new androidx.constraintlayout.solver.widgets.ConstraintWidget[r3]
            r11.mAlignedBiggestElementsInCols = r5
        L_0x008f:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r5 = r11.mAlignedBiggestElementsInRows
            if (r5 == 0) goto L_0x009b
            int r7 = r5.length
            if (r7 >= r4) goto L_0x0097
            goto L_0x009b
        L_0x0097:
            java.util.Arrays.fill(r5, r6)
            goto L_0x009f
        L_0x009b:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r5 = new androidx.constraintlayout.solver.widgets.ConstraintWidget[r4]
            r11.mAlignedBiggestElementsInRows = r5
        L_0x009f:
            r5 = 0
        L_0x00a0:
            if (r5 >= r3) goto L_0x00e8
            r6 = 0
        L_0x00a3:
            if (r6 >= r4) goto L_0x00e5
            int r7 = r6 * r3
            int r7 = r7 + r5
            if (r13 != r2) goto L_0x00ad
            int r7 = r5 * r4
            int r7 = r7 + r6
        L_0x00ad:
            int r8 = r12.length
            if (r7 < r8) goto L_0x00b1
            goto L_0x00e2
        L_0x00b1:
            r7 = r12[r7]
            if (r7 != 0) goto L_0x00b6
            goto L_0x00e2
        L_0x00b6:
            int r8 = r11.getWidgetWidth(r7)
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r9 = r11.mAlignedBiggestElementsInCols
            r10 = r9[r5]
            if (r10 == 0) goto L_0x00c8
            r9 = r9[r5]
            int r9 = r9.getWidth()
            if (r9 >= r8) goto L_0x00cc
        L_0x00c8:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r8 = r11.mAlignedBiggestElementsInCols
            r8[r5] = r7
        L_0x00cc:
            int r8 = r11.getWidgetHeight(r7)
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r9 = r11.mAlignedBiggestElementsInRows
            r10 = r9[r6]
            if (r10 == 0) goto L_0x00de
            r9 = r9[r6]
            int r9 = r9.getHeight()
            if (r9 >= r8) goto L_0x00e2
        L_0x00de:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r8 = r11.mAlignedBiggestElementsInRows
            r8[r6] = r7
        L_0x00e2:
            int r6 = r6 + 1
            goto L_0x00a3
        L_0x00e5:
            int r5 = r5 + 1
            goto L_0x00a0
        L_0x00e8:
            r5 = 0
            r6 = 0
        L_0x00ea:
            if (r5 >= r3) goto L_0x00ff
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r7 = r11.mAlignedBiggestElementsInCols
            r7 = r7[r5]
            if (r7 == 0) goto L_0x00fc
            if (r5 <= 0) goto L_0x00f7
            int r8 = r11.mHorizontalGap
            int r6 = r6 + r8
        L_0x00f7:
            int r7 = r11.getWidgetWidth(r7)
            int r6 = r6 + r7
        L_0x00fc:
            int r5 = r5 + 1
            goto L_0x00ea
        L_0x00ff:
            r5 = 0
            r7 = 0
        L_0x0101:
            if (r5 >= r4) goto L_0x0116
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r8 = r11.mAlignedBiggestElementsInRows
            r8 = r8[r5]
            if (r8 == 0) goto L_0x0113
            if (r5 <= 0) goto L_0x010e
            int r9 = r11.mVerticalGap
            int r7 = r7 + r9
        L_0x010e:
            int r8 = r11.getWidgetHeight(r8)
            int r7 = r7 + r8
        L_0x0113:
            int r5 = r5 + 1
            goto L_0x0101
        L_0x0116:
            r15[r0] = r6
            r15[r2] = r7
            if (r13 != 0) goto L_0x0124
            if (r6 <= r14) goto L_0x005f
            if (r3 <= r2) goto L_0x005f
            int r3 = r3 + -1
            goto L_0x0063
        L_0x0124:
            if (r7 <= r14) goto L_0x005f
            if (r4 <= r2) goto L_0x005f
            int r4 = r4 + -1
            goto L_0x0063
        L_0x012c:
            int[] r12 = r11.mAlignedDimensions
            r12[r0] = r3
            r12[r2] = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Flow.measureAligned(androidx.constraintlayout.solver.widgets.ConstraintWidget[], int, int, int[]):void");
    }

    private void createAlignedConstraints(boolean z) {
        if (this.mAlignedDimensions != null && this.mAlignedBiggestElementsInCols != null && this.mAlignedBiggestElementsInRows != null) {
            for (int i = 0; i < this.mWidgetsCount; i++) {
                this.mWidgets[i].resetAnchors();
            }
            int[] iArr = this.mAlignedDimensions;
            int i2 = iArr[0];
            int i3 = iArr[1];
            ConstraintWidget constraintWidget = null;
            for (int i4 = 0; i4 < i2; i4++) {
                ConstraintWidget constraintWidget2 = this.mAlignedBiggestElementsInCols[z ? (i2 - i4) - 1 : i4];
                if (constraintWidget2 != null) {
                    if (i4 == 0) {
                        constraintWidget2.connect(constraintWidget2.mLeft, this.mLeft, getPaddingLeft());
                        constraintWidget2.setHorizontalChainStyle(this.mHorizontalStyle);
                        constraintWidget2.setHorizontalBiasPercent(this.mHorizontalBias);
                    }
                    if (i4 == i2 - 1) {
                        constraintWidget2.connect(constraintWidget2.mRight, this.mRight, getPaddingRight());
                    }
                    if (i4 > 0) {
                        constraintWidget2.connect(constraintWidget2.mLeft, constraintWidget.mRight, this.mHorizontalGap);
                        constraintWidget.connect(constraintWidget.mRight, constraintWidget2.mLeft, 0);
                    }
                    constraintWidget = constraintWidget2;
                }
            }
            for (int i5 = 0; i5 < i3; i5++) {
                ConstraintWidget constraintWidget3 = this.mAlignedBiggestElementsInRows[i5];
                if (constraintWidget3 != null) {
                    if (i5 == 0) {
                        constraintWidget3.connect(constraintWidget3.mTop, this.mTop, getPaddingTop());
                        constraintWidget3.setVerticalChainStyle(this.mVerticalStyle);
                        constraintWidget3.setVerticalBiasPercent(this.mVerticalBias);
                    }
                    if (i5 == i3 - 1) {
                        constraintWidget3.connect(constraintWidget3.mBottom, this.mBottom, getPaddingBottom());
                    }
                    if (i5 > 0) {
                        constraintWidget3.connect(constraintWidget3.mTop, constraintWidget.mBottom, this.mVerticalGap);
                        constraintWidget.connect(constraintWidget.mBottom, constraintWidget3.mTop, 0);
                    }
                    constraintWidget = constraintWidget3;
                }
            }
            for (int i6 = 0; i6 < i2; i6++) {
                for (int i7 = 0; i7 < i3; i7++) {
                    int i8 = (i7 * i2) + i6;
                    if (this.mOrientation == 1) {
                        i8 = (i6 * i3) + i7;
                    }
                    if (i8 < this.mWidgets.length) {
                        ConstraintWidget constraintWidget4 = this.mWidgets[i8];
                        if (constraintWidget4 != null) {
                            ConstraintWidget constraintWidget5 = this.mAlignedBiggestElementsInCols[i6];
                            ConstraintWidget constraintWidget6 = this.mAlignedBiggestElementsInRows[i7];
                            if (constraintWidget4 != constraintWidget5) {
                                constraintWidget4.connect(constraintWidget4.mLeft, constraintWidget5.mLeft, 0);
                                constraintWidget4.connect(constraintWidget4.mRight, constraintWidget5.mRight, 0);
                            }
                            if (constraintWidget4 != constraintWidget6) {
                                constraintWidget4.connect(constraintWidget4.mTop, constraintWidget6.mTop, 0);
                                constraintWidget4.connect(constraintWidget4.mBottom, constraintWidget6.mBottom, 0);
                            }
                        }
                    }
                }
            }
        }
    }

    public void addToSolver(LinearSystem linearSystem) {
        super.addToSolver(linearSystem);
        boolean isRtl = getParent() != null ? ((ConstraintWidgetContainer) getParent()).isRtl() : false;
        int i = this.mWrapMode;
        if (i != 0) {
            if (i == 1) {
                int size = this.mChainList.size();
                int i2 = 0;
                while (i2 < size) {
                    ((WidgetsList) this.mChainList.get(i2)).createConstraints(isRtl, i2, i2 == size + -1);
                    i2++;
                }
            } else if (i == 2) {
                createAlignedConstraints(isRtl);
            }
        } else if (this.mChainList.size() > 0) {
            ((WidgetsList) this.mChainList.get(0)).createConstraints(isRtl, 0, true);
        }
        needsCallbackFromSolver(false);
    }
}
