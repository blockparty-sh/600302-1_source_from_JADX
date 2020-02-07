package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.Cache;
import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type;
import androidx.constraintlayout.solver.widgets.analyzer.ChainRun;
import androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun;
import androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ConstraintWidget {
    public static final int ANCHOR_BASELINE = 4;
    public static final int ANCHOR_BOTTOM = 3;
    public static final int ANCHOR_LEFT = 0;
    public static final int ANCHOR_RIGHT = 1;
    public static final int ANCHOR_TOP = 2;
    private static final boolean AUTOTAG_CENTER = false;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    static final int DIMENSION_HORIZONTAL = 0;
    static final int DIMENSION_VERTICAL = 1;
    protected static final int DIRECT = 2;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_RATIO = 3;
    public static final int MATCH_CONSTRAINT_RATIO_RESOLVED = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    protected static final int SOLVER = 1;
    public static final int UNKNOWN = -1;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    private static final int WRAP = -2;
    private boolean hasBaseline;
    public ChainRun horizontalChainRun;
    public HorizontalWidgetRun horizontalRun;
    private boolean inPlaceholder;
    public boolean[] isTerminalWidget;
    protected ArrayList<ConstraintAnchor> mAnchors;
    ConstraintAnchor mBaseline;
    int mBaselineDistance;
    public ConstraintAnchor mBottom;
    boolean mBottomHasCentered;
    ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX;
    ConstraintAnchor mCenterY;
    private float mCircleConstraintAngle;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    public float mDimensionRatio;
    protected int mDimensionRatioSide;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    boolean mGroupsToSolver;
    int mHeight;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution;
    boolean mHorizontalWrapVisited;
    private boolean mInVirtuaLayout;
    public boolean mIsHeightWrapContent;
    public boolean mIsWidthWrapContent;
    public ConstraintAnchor mLeft;
    boolean mLeftHasCentered;
    public ConstraintAnchor[] mListAnchors;
    public DimensionBehaviour[] mListDimensionBehaviors;
    protected ConstraintWidget[] mListNextMatchConstraintsWidget;
    public int mMatchConstraintDefaultHeight;
    public int mMatchConstraintDefaultWidth;
    public int mMatchConstraintMaxHeight;
    public int mMatchConstraintMaxWidth;
    public int mMatchConstraintMinHeight;
    public int mMatchConstraintMinWidth;
    public float mMatchConstraintPercentHeight;
    public float mMatchConstraintPercentWidth;
    private int[] mMaxDimension;
    protected int mMinHeight;
    protected int mMinWidth;
    protected ConstraintWidget[] mNextChainWidget;
    protected int mOffsetX;
    protected int mOffsetY;
    boolean mOptimizerMeasurable;
    public ConstraintWidget mParent;
    int mRelX;
    int mRelY;
    float mResolvedDimensionRatio;
    int mResolvedDimensionRatioSide;
    public int[] mResolvedMatchConstraintDefault;
    public ConstraintAnchor mRight;
    boolean mRightHasCentered;
    public ConstraintAnchor mTop;
    boolean mTopHasCentered;
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    public float[] mWeight;
    int mWidth;

    /* renamed from: mX */
    protected int f36mX;

    /* renamed from: mY */
    protected int f37mY;
    public boolean measured;
    public WidgetRun[] run;
    public ChainRun verticalChainRun;
    public VerticalWidgetRun verticalRun;
    public int[] wrapMeasure;

    /* renamed from: androidx.constraintlayout.solver.widgets.ConstraintWidget$1 */
    static /* synthetic */ class C02031 {

        /* renamed from: $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour */
        static final /* synthetic */ int[] f39xdde91696 = new int[DimensionBehaviour.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|(3:33|34|36)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|(3:33|34|36)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(31:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0052 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0066 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0071 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x007c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0087 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0093 */
        static {
            /*
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f39xdde91696 = r0
                r0 = 1
                int[] r1 = f39xdde91696     // Catch:{ NoSuchFieldError -> 0x0014 }
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = f39xdde91696     // Catch:{ NoSuchFieldError -> 0x001f }
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = f39xdde91696     // Catch:{ NoSuchFieldError -> 0x002a }
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                r3 = 4
                int[] r4 = f39xdde91696     // Catch:{ NoSuchFieldError -> 0x0035 }
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type[] r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                f38x4c44d048 = r4
                int[] r4 = f38x4c44d048     // Catch:{ NoSuchFieldError -> 0x0048 }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r5 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.LEFT     // Catch:{ NoSuchFieldError -> 0x0048 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0048 }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x0048 }
            L_0x0048:
                int[] r0 = f38x4c44d048     // Catch:{ NoSuchFieldError -> 0x0052 }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.TOP     // Catch:{ NoSuchFieldError -> 0x0052 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0052 }
                r0[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0052 }
            L_0x0052:
                int[] r0 = f38x4c44d048     // Catch:{ NoSuchFieldError -> 0x005c }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.RIGHT     // Catch:{ NoSuchFieldError -> 0x005c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005c }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005c }
            L_0x005c:
                int[] r0 = f38x4c44d048     // Catch:{ NoSuchFieldError -> 0x0066 }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.BOTTOM     // Catch:{ NoSuchFieldError -> 0x0066 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0066 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0066 }
            L_0x0066:
                int[] r0 = f38x4c44d048     // Catch:{ NoSuchFieldError -> 0x0071 }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.BASELINE     // Catch:{ NoSuchFieldError -> 0x0071 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0071 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0071 }
            L_0x0071:
                int[] r0 = f38x4c44d048     // Catch:{ NoSuchFieldError -> 0x007c }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.CENTER     // Catch:{ NoSuchFieldError -> 0x007c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007c }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007c }
            L_0x007c:
                int[] r0 = f38x4c44d048     // Catch:{ NoSuchFieldError -> 0x0087 }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.CENTER_X     // Catch:{ NoSuchFieldError -> 0x0087 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0087 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0087 }
            L_0x0087:
                int[] r0 = f38x4c44d048     // Catch:{ NoSuchFieldError -> 0x0093 }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.CENTER_Y     // Catch:{ NoSuchFieldError -> 0x0093 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0093 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0093 }
            L_0x0093:
                int[] r0 = f38x4c44d048     // Catch:{ NoSuchFieldError -> 0x009f }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.NONE     // Catch:{ NoSuchFieldError -> 0x009f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009f }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009f }
            L_0x009f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.C02031.<clinit>():void");
        }
    }

    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public WidgetRun getRun(int i) {
        if (i == 0) {
            return this.horizontalRun;
        }
        if (i == 1) {
            return this.verticalRun;
        }
        return null;
    }

    public boolean isInVirtualLayout() {
        return this.mInVirtuaLayout;
    }

    public void setInVirtualLayout(boolean z) {
        this.mInVirtuaLayout = z;
    }

    public int getMaxHeight() {
        return this.mMaxDimension[1];
    }

    public int getMaxWidth() {
        return this.mMaxDimension[0];
    }

    public void setMaxWidth(int i) {
        this.mMaxDimension[0] = i;
    }

    public void setMaxHeight(int i) {
        this.mMaxDimension[1] = i;
    }

    public boolean isSpreadWidth() {
        return this.mMatchConstraintDefaultWidth == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMaxWidth == 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isSpreadHeight() {
        return this.mMatchConstraintDefaultHeight == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinHeight == 0 && this.mMatchConstraintMaxHeight == 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public void setHasBaseline(boolean z) {
        this.hasBaseline = z;
    }

    public boolean getHasBaseline() {
        return this.hasBaseline;
    }

    public boolean isInPlaceholder() {
        return this.inPlaceholder;
    }

    public void setInPlaceholder(boolean z) {
        this.inPlaceholder = z;
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = 0.0f;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.f36mX = 0;
        this.f37mY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mListDimensionBehaviors[0] = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors[1] = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        float[] fArr = this.mWeight;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        int[] iArr = this.mMaxDimension;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
        this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mOptimizerMeasurable = false;
        this.mGroupsToSolver = false;
        boolean[] zArr = this.isTerminalWidget;
        zArr[0] = true;
        zArr[1] = true;
        this.mInVirtuaLayout = false;
    }

    public ConstraintWidget() {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = new HorizontalWidgetRun(this);
        this.verticalRun = new VerticalWidgetRun(this);
        this.isTerminalWidget = new boolean[]{true, true};
        this.wrapMeasure = new int[]{0, 0};
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.hasBaseline = false;
        this.mInVirtuaLayout = false;
        this.mLeft = new ConstraintAnchor(this, C0202Type.LEFT);
        this.mTop = new ConstraintAnchor(this, C0202Type.TOP);
        this.mRight = new ConstraintAnchor(this, C0202Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, C0202Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, C0202Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, C0202Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, C0202Type.CENTER_Y);
        this.mCenter = new ConstraintAnchor(this, C0202Type.CENTER);
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, this.mCenter};
        this.mAnchors = new ArrayList<>();
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.f36mX = 0;
        this.f37mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mOptimizerMeasurable = false;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        addAnchors();
    }

    public ConstraintWidget(int i, int i2, int i3, int i4) {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = new HorizontalWidgetRun(this);
        this.verticalRun = new VerticalWidgetRun(this);
        this.isTerminalWidget = new boolean[]{true, true};
        this.wrapMeasure = new int[]{0, 0};
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.hasBaseline = false;
        this.mInVirtuaLayout = false;
        this.mLeft = new ConstraintAnchor(this, C0202Type.LEFT);
        this.mTop = new ConstraintAnchor(this, C0202Type.TOP);
        this.mRight = new ConstraintAnchor(this, C0202Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, C0202Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, C0202Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, C0202Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, C0202Type.CENTER_Y);
        this.mCenter = new ConstraintAnchor(this, C0202Type.CENTER);
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, this.mCenter};
        this.mAnchors = new ArrayList<>();
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.f36mX = 0;
        this.f37mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mOptimizerMeasurable = false;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.f36mX = i;
        this.f37mY = i2;
        this.mWidth = i3;
        this.mHeight = i4;
        addAnchors();
    }

    public ConstraintWidget(int i, int i2) {
        this(0, 0, i, i2);
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mCenter);
        this.mAnchors.add(this.mBaseline);
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public void setParent(ConstraintWidget constraintWidget) {
        this.mParent = constraintWidget;
    }

    public void setWidthWrapContent(boolean z) {
        this.mIsWidthWrapContent = z;
    }

    public boolean isWidthWrapContent() {
        return this.mIsWidthWrapContent;
    }

    public void setHeightWrapContent(boolean z) {
        this.mIsHeightWrapContent = z;
    }

    public boolean isHeightWrapContent() {
        return this.mIsHeightWrapContent;
    }

    public void connectCircularConstraint(ConstraintWidget constraintWidget, float f, int i) {
        immediateConnect(C0202Type.CENTER, constraintWidget, C0202Type.CENTER, i, 0);
        this.mCircleConstraintAngle = f;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setVisibility(int i) {
        this.mVisibility = i;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public void setDebugName(String str) {
        this.mDebugName = str;
    }

    public void setDebugSolverName(LinearSystem linearSystem, String str) {
        this.mDebugName = str;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.mLeft);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(this.mTop);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(this.mRight);
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(this.mBottom);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(".left");
        createObjectVariable.setName(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(".top");
        createObjectVariable2.setName(sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str);
        sb3.append(".right");
        createObjectVariable3.setName(sb3.toString());
        StringBuilder sb4 = new StringBuilder();
        sb4.append(str);
        sb4.append(".bottom");
        createObjectVariable4.setName(sb4.toString());
        if (this.mBaselineDistance > 0) {
            SolverVariable createObjectVariable5 = linearSystem.createObjectVariable(this.mBaseline);
            StringBuilder sb5 = new StringBuilder();
            sb5.append(str);
            sb5.append(".baseline");
            createObjectVariable5.setName(sb5.toString());
        }
    }

    public void createObjectVariables(LinearSystem linearSystem) {
        linearSystem.createObjectVariable(this.mLeft);
        linearSystem.createObjectVariable(this.mTop);
        linearSystem.createObjectVariable(this.mRight);
        linearSystem.createObjectVariable(this.mBottom);
        if (this.mBaselineDistance > 0) {
            linearSystem.createObjectVariable(this.mBaseline);
        }
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        String str2 = " ";
        String str3 = "";
        if (this.mType != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("type: ");
            sb2.append(this.mType);
            sb2.append(str2);
            str = sb2.toString();
        } else {
            str = str3;
        }
        sb.append(str);
        if (this.mDebugName != null) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("id: ");
            sb3.append(this.mDebugName);
            sb3.append(str2);
            str3 = sb3.toString();
        }
        sb.append(str3);
        sb.append("(");
        sb.append(this.f36mX);
        sb.append(", ");
        sb.append(this.f37mY);
        sb.append(") - (");
        sb.append(this.mWidth);
        sb.append(" x ");
        sb.append(this.mHeight);
        sb.append(")");
        return sb.toString();
    }

    public int getX() {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget == null || !(constraintWidget instanceof ConstraintWidgetContainer)) {
            return this.f36mX;
        }
        return ((ConstraintWidgetContainer) constraintWidget).mPaddingLeft + this.f36mX;
    }

    public int getY() {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget == null || !(constraintWidget instanceof ConstraintWidgetContainer)) {
            return this.f37mY;
        }
        return ((ConstraintWidgetContainer) constraintWidget).mPaddingTop + this.f37mY;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getOptimizerWrapWidth() {
        int i;
        int i2 = this.mWidth;
        if (this.mListDimensionBehaviors[0] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i2;
        }
        if (this.mMatchConstraintDefaultWidth == 1) {
            i = Math.max(this.mMatchConstraintMinWidth, i2);
        } else {
            i = this.mMatchConstraintMinWidth;
            if (i > 0) {
                this.mWidth = i;
            } else {
                i = 0;
            }
        }
        int i3 = this.mMatchConstraintMaxWidth;
        return (i3 <= 0 || i3 >= i) ? i : i3;
    }

    public int getOptimizerWrapHeight() {
        int i;
        int i2 = this.mHeight;
        if (this.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i2;
        }
        if (this.mMatchConstraintDefaultHeight == 1) {
            i = Math.max(this.mMatchConstraintMinHeight, i2);
        } else {
            i = this.mMatchConstraintMinHeight;
            if (i > 0) {
                this.mHeight = i;
            } else {
                i = 0;
            }
        }
        int i3 = this.mMatchConstraintMaxHeight;
        return (i3 <= 0 || i3 >= i) ? i : i3;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public int getLength(int i) {
        if (i == 0) {
            return getWidth();
        }
        if (i == 1) {
            return getHeight();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getRootX() {
        return this.f36mX + this.mOffsetX;
    }

    /* access modifiers changed from: protected */
    public int getRootY() {
        return this.f37mY + this.mOffsetY;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getLeft() {
        return getX();
    }

    public int getTop() {
        return getY();
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public int getHorizontalMargin() {
        ConstraintAnchor constraintAnchor = this.mLeft;
        int i = 0;
        if (constraintAnchor != null) {
            i = 0 + constraintAnchor.mMargin;
        }
        ConstraintAnchor constraintAnchor2 = this.mRight;
        return constraintAnchor2 != null ? i + constraintAnchor2.mMargin : i;
    }

    public int getVerticalMargin() {
        int i = 0;
        if (this.mLeft != null) {
            i = 0 + this.mTop.mMargin;
        }
        return this.mRight != null ? i + this.mBottom.mMargin : i;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public float getVerticalBiasPercent() {
        return this.mVerticalBiasPercent;
    }

    public float getBiasPercent(int i) {
        if (i == 0) {
            return this.mHorizontalBiasPercent;
        }
        if (i == 1) {
            return this.mVerticalBiasPercent;
        }
        return -1.0f;
    }

    public boolean hasBaseline() {
        return this.hasBaseline;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setX(int i) {
        this.f36mX = i;
    }

    public void setY(int i) {
        this.f37mY = i;
    }

    public void setOrigin(int i, int i2) {
        this.f36mX = i;
        this.f37mY = i2;
    }

    public void setOffset(int i, int i2) {
        this.mOffsetX = i;
        this.mOffsetY = i2;
    }

    public void setGoneMargin(C0202Type type, int i) {
        int i2 = C02031.f38x4c44d048[type.ordinal()];
        if (i2 == 1) {
            this.mLeft.mGoneMargin = i;
        } else if (i2 == 2) {
            this.mTop.mGoneMargin = i;
        } else if (i2 == 3) {
            this.mRight.mGoneMargin = i;
        } else if (i2 == 4) {
            this.mBottom.mGoneMargin = i;
        }
    }

    public void setWidth(int i) {
        this.mWidth = i;
        int i2 = this.mWidth;
        int i3 = this.mMinWidth;
        if (i2 < i3) {
            this.mWidth = i3;
        }
    }

    public void setHeight(int i) {
        this.mHeight = i;
        int i2 = this.mHeight;
        int i3 = this.mMinHeight;
        if (i2 < i3) {
            this.mHeight = i3;
        }
    }

    public void setLength(int i, int i2) {
        if (i2 == 0) {
            setWidth(i);
        } else if (i2 == 1) {
            setHeight(i);
        }
    }

    public void setHorizontalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultWidth = i;
        this.mMatchConstraintMinWidth = i2;
        this.mMatchConstraintMaxWidth = i3;
        this.mMatchConstraintPercentWidth = f;
        if (f < 1.0f && this.mMatchConstraintDefaultWidth == 0) {
            this.mMatchConstraintDefaultWidth = 2;
        }
    }

    public void setVerticalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultHeight = i;
        this.mMatchConstraintMinHeight = i2;
        this.mMatchConstraintMaxHeight = i3;
        this.mMatchConstraintPercentHeight = f;
        if (f < 1.0f && this.mMatchConstraintDefaultHeight == 0) {
            this.mMatchConstraintDefaultHeight = 2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0089  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDimensionRatio(java.lang.String r9) {
        /*
            r8 = this;
            r0 = 0
            if (r9 == 0) goto L_0x008e
            int r1 = r9.length()
            if (r1 != 0) goto L_0x000b
            goto L_0x008e
        L_0x000b:
            r1 = -1
            int r2 = r9.length()
            r3 = 44
            int r3 = r9.indexOf(r3)
            r4 = 0
            r5 = 1
            if (r3 <= 0) goto L_0x0037
            int r6 = r2 + -1
            if (r3 >= r6) goto L_0x0037
            java.lang.String r6 = r9.substring(r4, r3)
            java.lang.String r7 = "W"
            boolean r7 = r6.equalsIgnoreCase(r7)
            if (r7 == 0) goto L_0x002c
            r1 = 0
            goto L_0x0035
        L_0x002c:
            java.lang.String r4 = "H"
            boolean r4 = r6.equalsIgnoreCase(r4)
            if (r4 == 0) goto L_0x0035
            r1 = 1
        L_0x0035:
            int r4 = r3 + 1
        L_0x0037:
            r3 = 58
            int r3 = r9.indexOf(r3)
            if (r3 < 0) goto L_0x0075
            int r2 = r2 - r5
            if (r3 >= r2) goto L_0x0075
            java.lang.String r2 = r9.substring(r4, r3)
            int r3 = r3 + r5
            java.lang.String r9 = r9.substring(r3)
            int r3 = r2.length()
            if (r3 <= 0) goto L_0x0084
            int r3 = r9.length()
            if (r3 <= 0) goto L_0x0084
            float r2 = java.lang.Float.parseFloat(r2)     // Catch:{ NumberFormatException -> 0x0084 }
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x0084 }
            int r3 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r3 <= 0) goto L_0x0084
            int r3 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r3 <= 0) goto L_0x0084
            if (r1 != r5) goto L_0x006f
            float r9 = r9 / r2
            float r9 = java.lang.Math.abs(r9)     // Catch:{ NumberFormatException -> 0x0084 }
            goto L_0x0085
        L_0x006f:
            float r2 = r2 / r9
            float r9 = java.lang.Math.abs(r2)     // Catch:{ NumberFormatException -> 0x0084 }
            goto L_0x0085
        L_0x0075:
            java.lang.String r9 = r9.substring(r4)
            int r2 = r9.length()
            if (r2 <= 0) goto L_0x0084
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x0084 }
            goto L_0x0085
        L_0x0084:
            r9 = 0
        L_0x0085:
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x008d
            r8.mDimensionRatio = r9
            r8.mDimensionRatioSide = r1
        L_0x008d:
            return
        L_0x008e:
            r8.mDimensionRatio = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.setDimensionRatio(java.lang.String):void");
    }

    public void setDimensionRatio(float f, int i) {
        this.mDimensionRatio = f;
        this.mDimensionRatioSide = i;
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.mDimensionRatioSide;
    }

    public void setHorizontalBiasPercent(float f) {
        this.mHorizontalBiasPercent = f;
    }

    public void setVerticalBiasPercent(float f) {
        this.mVerticalBiasPercent = f;
    }

    public void setMinWidth(int i) {
        if (i < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = i;
        }
    }

    public void setMinHeight(int i) {
        if (i < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = i;
        }
    }

    public void setDimension(int i, int i2) {
        this.mWidth = i;
        int i3 = this.mWidth;
        int i4 = this.mMinWidth;
        if (i3 < i4) {
            this.mWidth = i4;
        }
        this.mHeight = i2;
        int i5 = this.mHeight;
        int i6 = this.mMinHeight;
        if (i5 < i6) {
            this.mHeight = i6;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002b, code lost:
        if (r5 < r3) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001c, code lost:
        if (r4 < r2) goto L_0x0020;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setFrame(int r2, int r3, int r4, int r5) {
        /*
            r1 = this;
            int r4 = r4 - r2
            int r5 = r5 - r3
            r1.f36mX = r2
            r1.f37mY = r3
            int r2 = r1.mVisibility
            r3 = 0
            r0 = 8
            if (r2 != r0) goto L_0x0012
            r1.mWidth = r3
            r1.mHeight = r3
            return
        L_0x0012:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r1.mListDimensionBehaviors
            r2 = r2[r3]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r2 != r3) goto L_0x001f
            int r2 = r1.mWidth
            if (r4 >= r2) goto L_0x001f
            goto L_0x0020
        L_0x001f:
            r2 = r4
        L_0x0020:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r3 = r1.mListDimensionBehaviors
            r4 = 1
            r3 = r3[r4]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r3 != r4) goto L_0x002e
            int r3 = r1.mHeight
            if (r5 >= r3) goto L_0x002e
            goto L_0x002f
        L_0x002e:
            r3 = r5
        L_0x002f:
            r1.mWidth = r2
            r1.mHeight = r3
            int r2 = r1.mHeight
            int r3 = r1.mMinHeight
            if (r2 >= r3) goto L_0x003b
            r1.mHeight = r3
        L_0x003b:
            int r2 = r1.mWidth
            int r3 = r1.mMinWidth
            if (r2 >= r3) goto L_0x0043
            r1.mWidth = r3
        L_0x0043:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.setFrame(int, int, int, int):void");
    }

    public void setFrame(int i, int i2, int i3) {
        if (i3 == 0) {
            setHorizontalDimension(i, i2);
        } else if (i3 == 1) {
            setVerticalDimension(i, i2);
        }
    }

    public void setHorizontalDimension(int i, int i2) {
        this.f36mX = i;
        this.mWidth = i2 - i;
        int i3 = this.mWidth;
        int i4 = this.mMinWidth;
        if (i3 < i4) {
            this.mWidth = i4;
        }
    }

    public void setVerticalDimension(int i, int i2) {
        this.f37mY = i;
        this.mHeight = i2 - i;
        int i3 = this.mHeight;
        int i4 = this.mMinHeight;
        if (i3 < i4) {
            this.mHeight = i4;
        }
    }

    /* access modifiers changed from: 0000 */
    public int getRelativePositioning(int i) {
        if (i == 0) {
            return this.mRelX;
        }
        if (i == 1) {
            return this.mRelY;
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public void setRelativePositioning(int i, int i2) {
        if (i2 == 0) {
            this.mRelX = i;
        } else if (i2 == 1) {
            this.mRelY = i;
        }
    }

    public void setBaselineDistance(int i) {
        this.mBaselineDistance = i;
    }

    public void setCompanionWidget(Object obj) {
        this.mCompanionWidget = obj;
    }

    public void setContainerItemSkip(int i) {
        if (i >= 0) {
            this.mContainerItemSkip = i;
        } else {
            this.mContainerItemSkip = 0;
        }
    }

    public int getContainerItemSkip() {
        return this.mContainerItemSkip;
    }

    public void setHorizontalWeight(float f) {
        this.mWeight[0] = f;
    }

    public void setVerticalWeight(float f) {
        this.mWeight[1] = f;
    }

    public void setHorizontalChainStyle(int i) {
        this.mHorizontalChainStyle = i;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public void setVerticalChainStyle(int i) {
        this.mVerticalChainStyle = i;
    }

    public int getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public boolean allowedInBarrier() {
        return this.mVisibility != 8;
    }

    public void immediateConnect(C0202Type type, ConstraintWidget constraintWidget, C0202Type type2, int i, int i2) {
        getAnchor(type).connect(constraintWidget.getAnchor(type2), i, i2, true);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i) {
        if (constraintAnchor.getOwner() == this) {
            connect(constraintAnchor.getType(), constraintAnchor2.getOwner(), constraintAnchor2.getType(), i);
        }
    }

    public void connect(C0202Type type, ConstraintWidget constraintWidget, C0202Type type2) {
        connect(type, constraintWidget, type2, 0);
    }

    public void connect(C0202Type type, ConstraintWidget constraintWidget, C0202Type type2, int i) {
        boolean z;
        if (type == C0202Type.CENTER) {
            if (type2 == C0202Type.CENTER) {
                ConstraintAnchor anchor = getAnchor(C0202Type.LEFT);
                ConstraintAnchor anchor2 = getAnchor(C0202Type.RIGHT);
                ConstraintAnchor anchor3 = getAnchor(C0202Type.TOP);
                ConstraintAnchor anchor4 = getAnchor(C0202Type.BOTTOM);
                boolean z2 = true;
                if ((anchor == null || !anchor.isConnected()) && (anchor2 == null || !anchor2.isConnected())) {
                    connect(C0202Type.LEFT, constraintWidget, C0202Type.LEFT, 0);
                    connect(C0202Type.RIGHT, constraintWidget, C0202Type.RIGHT, 0);
                    z = true;
                } else {
                    z = false;
                }
                if ((anchor3 == null || !anchor3.isConnected()) && (anchor4 == null || !anchor4.isConnected())) {
                    connect(C0202Type.TOP, constraintWidget, C0202Type.TOP, 0);
                    connect(C0202Type.BOTTOM, constraintWidget, C0202Type.BOTTOM, 0);
                } else {
                    z2 = false;
                }
                if (z && z2) {
                    getAnchor(C0202Type.CENTER).connect(constraintWidget.getAnchor(C0202Type.CENTER), 0);
                } else if (z) {
                    getAnchor(C0202Type.CENTER_X).connect(constraintWidget.getAnchor(C0202Type.CENTER_X), 0);
                } else if (z2) {
                    getAnchor(C0202Type.CENTER_Y).connect(constraintWidget.getAnchor(C0202Type.CENTER_Y), 0);
                }
            } else if (type2 == C0202Type.LEFT || type2 == C0202Type.RIGHT) {
                connect(C0202Type.LEFT, constraintWidget, type2, 0);
                connect(C0202Type.RIGHT, constraintWidget, type2, 0);
                getAnchor(C0202Type.CENTER).connect(constraintWidget.getAnchor(type2), 0);
            } else if (type2 == C0202Type.TOP || type2 == C0202Type.BOTTOM) {
                connect(C0202Type.TOP, constraintWidget, type2, 0);
                connect(C0202Type.BOTTOM, constraintWidget, type2, 0);
                getAnchor(C0202Type.CENTER).connect(constraintWidget.getAnchor(type2), 0);
            }
        } else if (type == C0202Type.CENTER_X && (type2 == C0202Type.LEFT || type2 == C0202Type.RIGHT)) {
            ConstraintAnchor anchor5 = getAnchor(C0202Type.LEFT);
            ConstraintAnchor anchor6 = constraintWidget.getAnchor(type2);
            ConstraintAnchor anchor7 = getAnchor(C0202Type.RIGHT);
            anchor5.connect(anchor6, 0);
            anchor7.connect(anchor6, 0);
            getAnchor(C0202Type.CENTER_X).connect(anchor6, 0);
        } else if (type == C0202Type.CENTER_Y && (type2 == C0202Type.TOP || type2 == C0202Type.BOTTOM)) {
            ConstraintAnchor anchor8 = constraintWidget.getAnchor(type2);
            getAnchor(C0202Type.TOP).connect(anchor8, 0);
            getAnchor(C0202Type.BOTTOM).connect(anchor8, 0);
            getAnchor(C0202Type.CENTER_Y).connect(anchor8, 0);
        } else if (type == C0202Type.CENTER_X && type2 == C0202Type.CENTER_X) {
            getAnchor(C0202Type.LEFT).connect(constraintWidget.getAnchor(C0202Type.LEFT), 0);
            getAnchor(C0202Type.RIGHT).connect(constraintWidget.getAnchor(C0202Type.RIGHT), 0);
            getAnchor(C0202Type.CENTER_X).connect(constraintWidget.getAnchor(type2), 0);
        } else if (type == C0202Type.CENTER_Y && type2 == C0202Type.CENTER_Y) {
            getAnchor(C0202Type.TOP).connect(constraintWidget.getAnchor(C0202Type.TOP), 0);
            getAnchor(C0202Type.BOTTOM).connect(constraintWidget.getAnchor(C0202Type.BOTTOM), 0);
            getAnchor(C0202Type.CENTER_Y).connect(constraintWidget.getAnchor(type2), 0);
        } else {
            ConstraintAnchor anchor9 = getAnchor(type);
            ConstraintAnchor anchor10 = constraintWidget.getAnchor(type2);
            if (anchor9.isValidConnection(anchor10)) {
                if (type == C0202Type.BASELINE) {
                    ConstraintAnchor anchor11 = getAnchor(C0202Type.TOP);
                    ConstraintAnchor anchor12 = getAnchor(C0202Type.BOTTOM);
                    if (anchor11 != null) {
                        anchor11.reset();
                    }
                    if (anchor12 != null) {
                        anchor12.reset();
                    }
                    i = 0;
                } else if (type == C0202Type.TOP || type == C0202Type.BOTTOM) {
                    ConstraintAnchor anchor13 = getAnchor(C0202Type.BASELINE);
                    if (anchor13 != null) {
                        anchor13.reset();
                    }
                    ConstraintAnchor anchor14 = getAnchor(C0202Type.CENTER);
                    if (anchor14.getTarget() != anchor10) {
                        anchor14.reset();
                    }
                    ConstraintAnchor opposite = getAnchor(type).getOpposite();
                    ConstraintAnchor anchor15 = getAnchor(C0202Type.CENTER_Y);
                    if (anchor15.isConnected()) {
                        opposite.reset();
                        anchor15.reset();
                    }
                } else if (type == C0202Type.LEFT || type == C0202Type.RIGHT) {
                    ConstraintAnchor anchor16 = getAnchor(C0202Type.CENTER);
                    if (anchor16.getTarget() != anchor10) {
                        anchor16.reset();
                    }
                    ConstraintAnchor opposite2 = getAnchor(type).getOpposite();
                    ConstraintAnchor anchor17 = getAnchor(C0202Type.CENTER_X);
                    if (anchor17.isConnected()) {
                        opposite2.reset();
                        anchor17.reset();
                    }
                }
                anchor9.connect(anchor10, i);
            }
        }
    }

    public void resetAllConstraints() {
        resetAnchors();
        setVerticalBiasPercent(DEFAULT_BIAS);
        setHorizontalBiasPercent(DEFAULT_BIAS);
    }

    public void resetAnchor(ConstraintAnchor constraintAnchor) {
        if (getParent() == null || !(getParent() instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            ConstraintAnchor anchor = getAnchor(C0202Type.LEFT);
            ConstraintAnchor anchor2 = getAnchor(C0202Type.RIGHT);
            ConstraintAnchor anchor3 = getAnchor(C0202Type.TOP);
            ConstraintAnchor anchor4 = getAnchor(C0202Type.BOTTOM);
            ConstraintAnchor anchor5 = getAnchor(C0202Type.CENTER);
            ConstraintAnchor anchor6 = getAnchor(C0202Type.CENTER_X);
            ConstraintAnchor anchor7 = getAnchor(C0202Type.CENTER_Y);
            if (constraintAnchor == anchor5) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                    anchor.reset();
                    anchor2.reset();
                }
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                    anchor3.reset();
                    anchor4.reset();
                }
                this.mHorizontalBiasPercent = 0.5f;
                this.mVerticalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor6) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget().getOwner() == anchor2.getTarget().getOwner()) {
                    anchor.reset();
                    anchor2.reset();
                }
                this.mHorizontalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor7) {
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget().getOwner() == anchor4.getTarget().getOwner()) {
                    anchor3.reset();
                    anchor4.reset();
                }
                this.mVerticalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor || constraintAnchor == anchor2) {
                if (anchor.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                    anchor5.reset();
                }
            } else if ((constraintAnchor == anchor3 || constraintAnchor == anchor4) && anchor3.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                anchor5.reset();
            }
            constraintAnchor.reset();
        }
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int size = this.mAnchors.size();
            for (int i = 0; i < size; i++) {
                ((ConstraintAnchor) this.mAnchors.get(i)).reset();
            }
        }
    }

    public ConstraintAnchor getAnchor(C0202Type type) {
        switch (type) {
            case LEFT:
                return this.mLeft;
            case TOP:
                return this.mTop;
            case RIGHT:
                return this.mRight;
            case BOTTOM:
                return this.mBottom;
            case BASELINE:
                return this.mBaseline;
            case CENTER:
                return this.mCenter;
            case CENTER_X:
                return this.mCenterX;
            case CENTER_Y:
                return this.mCenterY;
            case NONE:
                return null;
            default:
                throw new AssertionError(type.name());
        }
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public DimensionBehaviour getDimensionBehaviour(int i) {
        if (i == 0) {
            return getHorizontalDimensionBehaviour();
        }
        if (i == 1) {
            return getVerticalDimensionBehaviour();
        }
        return null;
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[0] = dimensionBehaviour;
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[1] = dimensionBehaviour;
    }

    public boolean isInHorizontalChain() {
        return (this.mLeft.mTarget != null && this.mLeft.mTarget.mTarget == this.mLeft) || (this.mRight.mTarget != null && this.mRight.mTarget.mTarget == this.mRight);
    }

    public ConstraintWidget getPreviousChainMember(int i) {
        if (i == 0) {
            if (this.mLeft.mTarget != null) {
                ConstraintAnchor constraintAnchor = this.mLeft.mTarget.mTarget;
                ConstraintAnchor constraintAnchor2 = this.mLeft;
                if (constraintAnchor == constraintAnchor2) {
                    return constraintAnchor2.mTarget.mOwner;
                }
            }
        } else if (i == 1 && this.mTop.mTarget != null) {
            ConstraintAnchor constraintAnchor3 = this.mTop.mTarget.mTarget;
            ConstraintAnchor constraintAnchor4 = this.mTop;
            if (constraintAnchor3 == constraintAnchor4) {
                return constraintAnchor4.mTarget.mOwner;
            }
        }
        return null;
    }

    public ConstraintWidget getNextChainMember(int i) {
        if (i == 0) {
            if (this.mRight.mTarget != null) {
                ConstraintAnchor constraintAnchor = this.mRight.mTarget.mTarget;
                ConstraintAnchor constraintAnchor2 = this.mRight;
                if (constraintAnchor == constraintAnchor2) {
                    return constraintAnchor2.mTarget.mOwner;
                }
            }
        } else if (i == 1 && this.mBottom.mTarget != null) {
            ConstraintAnchor constraintAnchor3 = this.mBottom.mTarget.mTarget;
            ConstraintAnchor constraintAnchor4 = this.mBottom;
            if (constraintAnchor3 == constraintAnchor4) {
                return constraintAnchor4.mTarget.mOwner;
            }
        }
        return null;
    }

    public ConstraintWidget getHorizontalChainControlWidget() {
        ConstraintAnchor constraintAnchor;
        ConstraintWidget constraintWidget;
        ConstraintAnchor constraintAnchor2;
        if (!isInHorizontalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget2 = this;
        ConstraintWidget constraintWidget3 = null;
        while (constraintWidget3 == null && constraintWidget2 != null) {
            ConstraintAnchor anchor = constraintWidget2.getAnchor(C0202Type.LEFT);
            if (anchor == null) {
                constraintAnchor = null;
            } else {
                constraintAnchor = anchor.getTarget();
            }
            if (constraintAnchor == null) {
                constraintWidget = null;
            } else {
                constraintWidget = constraintAnchor.getOwner();
            }
            if (constraintWidget == getParent()) {
                return constraintWidget2;
            }
            if (constraintWidget == null) {
                constraintAnchor2 = null;
            } else {
                constraintAnchor2 = constraintWidget.getAnchor(C0202Type.RIGHT).getTarget();
            }
            if (constraintAnchor2 == null || constraintAnchor2.getOwner() == constraintWidget2) {
                constraintWidget2 = constraintWidget;
            } else {
                constraintWidget3 = constraintWidget2;
            }
        }
        return constraintWidget3;
    }

    public boolean isInVerticalChain() {
        return (this.mTop.mTarget != null && this.mTop.mTarget.mTarget == this.mTop) || (this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == this.mBottom);
    }

    public ConstraintWidget getVerticalChainControlWidget() {
        ConstraintAnchor constraintAnchor;
        ConstraintWidget constraintWidget;
        ConstraintAnchor constraintAnchor2;
        if (!isInVerticalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget2 = this;
        ConstraintWidget constraintWidget3 = null;
        while (constraintWidget3 == null && constraintWidget2 != null) {
            ConstraintAnchor anchor = constraintWidget2.getAnchor(C0202Type.TOP);
            if (anchor == null) {
                constraintAnchor = null;
            } else {
                constraintAnchor = anchor.getTarget();
            }
            if (constraintAnchor == null) {
                constraintWidget = null;
            } else {
                constraintWidget = constraintAnchor.getOwner();
            }
            if (constraintWidget == getParent()) {
                return constraintWidget2;
            }
            if (constraintWidget == null) {
                constraintAnchor2 = null;
            } else {
                constraintAnchor2 = constraintWidget.getAnchor(C0202Type.BOTTOM).getTarget();
            }
            if (constraintAnchor2 == null || constraintAnchor2.getOwner() == constraintWidget2) {
                constraintWidget2 = constraintWidget;
            } else {
                constraintWidget3 = constraintWidget2;
            }
        }
        return constraintWidget3;
    }

    private boolean isChainHead(int i) {
        int i2 = i * 2;
        if (this.mListAnchors[i2].mTarget != null) {
            ConstraintAnchor constraintAnchor = this.mListAnchors[i2].mTarget.mTarget;
            ConstraintAnchor[] constraintAnchorArr = this.mListAnchors;
            if (constraintAnchor != constraintAnchorArr[i2]) {
                int i3 = i2 + 1;
                if (constraintAnchorArr[i3].mTarget != null && this.mListAnchors[i3].mTarget.mTarget == this.mListAnchors[i3]) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0284, code lost:
        if (r1 == -1) goto L_0x0288;
     */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x027f  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x028b  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x0297  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x02b0  */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x0378  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x038e  */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x03ed  */
    /* JADX WARNING: Removed duplicated region for block: B:202:0x03f0  */
    /* JADX WARNING: Removed duplicated region for block: B:217:0x0413  */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x042e  */
    /* JADX WARNING: Removed duplicated region for block: B:226:0x043d  */
    /* JADX WARNING: Removed duplicated region for block: B:227:0x0446  */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x044c  */
    /* JADX WARNING: Removed duplicated region for block: B:232:0x0494  */
    /* JADX WARNING: Removed duplicated region for block: B:234:0x049a  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x04c3  */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x04cd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addToSolver(androidx.constraintlayout.solver.LinearSystem r41) {
        /*
            r40 = this;
            r15 = r40
            r11 = r41
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.mLeft
            androidx.constraintlayout.solver.SolverVariable r7 = r11.createObjectVariable(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.mRight
            androidx.constraintlayout.solver.SolverVariable r5 = r11.createObjectVariable(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.mTop
            androidx.constraintlayout.solver.SolverVariable r4 = r11.createObjectVariable(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.mBottom
            androidx.constraintlayout.solver.SolverVariable r2 = r11.createObjectVariable(r0)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.mBaseline
            androidx.constraintlayout.solver.SolverVariable r1 = r11.createObjectVariable(r0)
            androidx.constraintlayout.solver.Metrics r0 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            r8 = 1
            if (r0 == 0) goto L_0x002f
            androidx.constraintlayout.solver.Metrics r0 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            long r12 = r0.widgets
            long r12 = r12 + r8
            r0.widgets = r12
        L_0x002f:
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r0 = r15.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.start
            boolean r0 = r0.resolved
            r12 = 0
            if (r0 == 0) goto L_0x00df
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r0 = r15.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.end
            boolean r0 = r0.resolved
            if (r0 == 0) goto L_0x00df
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r15.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.start
            boolean r0 = r0.resolved
            if (r0 == 0) goto L_0x00df
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r15.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.end
            boolean r0 = r0.resolved
            if (r0 == 0) goto L_0x00df
            androidx.constraintlayout.solver.Metrics r0 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            if (r0 == 0) goto L_0x005b
            androidx.constraintlayout.solver.Metrics r0 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            long r13 = r0.graphSolved
            long r13 = r13 + r8
            r0.graphSolved = r13
        L_0x005b:
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r0 = r15.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.start
            int r0 = r0.value
            r11.addEquality(r7, r0)
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r0 = r15.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.end
            int r0 = r0.value
            r11.addEquality(r5, r0)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r15.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.start
            int r0 = r0.value
            r11.addEquality(r4, r0)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r15.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.end
            int r0 = r0.value
            r11.addEquality(r2, r0)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r15.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.baseline
            int r0 = r0.value
            r11.addEquality(r1, r0)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x00de
            if (r0 == 0) goto L_0x0098
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r0 = r0[r12]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r1) goto L_0x0098
            r0 = 1
            goto L_0x0099
        L_0x0098:
            r0 = 0
        L_0x0099:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r15.mParent
            if (r1 == 0) goto L_0x00a8
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r1.mListDimensionBehaviors
            r3 = 1
            r1 = r1[r3]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r1 != r3) goto L_0x00a8
            r1 = 1
            goto L_0x00a9
        L_0x00a8:
            r1 = 0
        L_0x00a9:
            if (r0 == 0) goto L_0x00c3
            boolean[] r0 = r15.isTerminalWidget
            boolean r0 = r0[r12]
            if (r0 == 0) goto L_0x00c3
            boolean r0 = r40.isInHorizontalChain()
            if (r0 != 0) goto L_0x00c3
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r15.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.mRight
            androidx.constraintlayout.solver.SolverVariable r0 = r11.createObjectVariable(r0)
            r3 = 6
            r11.addGreaterThan(r0, r5, r12, r3)
        L_0x00c3:
            if (r1 == 0) goto L_0x00de
            boolean[] r0 = r15.isTerminalWidget
            r1 = 1
            boolean r0 = r0[r1]
            if (r0 == 0) goto L_0x00de
            boolean r0 = r40.isInVerticalChain()
            if (r0 != 0) goto L_0x00de
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r15.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.mBottom
            androidx.constraintlayout.solver.SolverVariable r0 = r11.createObjectVariable(r0)
            r1 = 6
            r11.addGreaterThan(r0, r2, r12, r1)
        L_0x00de:
            return
        L_0x00df:
            androidx.constraintlayout.solver.Metrics r0 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            if (r0 == 0) goto L_0x00ea
            androidx.constraintlayout.solver.Metrics r0 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            long r13 = r0.linearSolved
            long r13 = r13 + r8
            r0.linearSolved = r13
        L_0x00ea:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r15.mParent
            r14 = 8
            if (r0 == 0) goto L_0x017f
            if (r0 == 0) goto L_0x00fc
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r0 = r0[r12]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r3) goto L_0x00fc
            r0 = 1
            goto L_0x00fd
        L_0x00fc:
            r0 = 0
        L_0x00fd:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = r15.mParent
            if (r3 == 0) goto L_0x010c
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r3 = r3.mListDimensionBehaviors
            r6 = 1
            r3 = r3[r6]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r3 != r6) goto L_0x010c
            r3 = 1
            goto L_0x010d
        L_0x010c:
            r3 = 0
        L_0x010d:
            boolean r6 = r15.isChainHead(r12)
            if (r6 == 0) goto L_0x011d
            androidx.constraintlayout.solver.widgets.ConstraintWidget r6 = r15.mParent
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r6 = (androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer) r6
            r6.addChain(r15, r12)
            r6 = 1
            r13 = 1
            goto L_0x0122
        L_0x011d:
            boolean r13 = r40.isInHorizontalChain()
            r6 = 1
        L_0x0122:
            boolean r8 = r15.isChainHead(r6)
            if (r8 == 0) goto L_0x0131
            androidx.constraintlayout.solver.widgets.ConstraintWidget r8 = r15.mParent
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r8 = (androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer) r8
            r8.addChain(r15, r6)
            r6 = 1
            goto L_0x0135
        L_0x0131:
            boolean r6 = r40.isInVerticalChain()
        L_0x0135:
            if (r13 != 0) goto L_0x0155
            if (r0 == 0) goto L_0x0155
            int r8 = r15.mVisibility
            if (r8 == r14) goto L_0x0155
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.mLeft
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            if (r8 != 0) goto L_0x0155
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.mRight
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            if (r8 != 0) goto L_0x0155
            androidx.constraintlayout.solver.widgets.ConstraintWidget r8 = r15.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.mRight
            androidx.constraintlayout.solver.SolverVariable r8 = r11.createObjectVariable(r8)
            r9 = 1
            r11.addGreaterThan(r8, r5, r12, r9)
        L_0x0155:
            if (r6 != 0) goto L_0x0179
            if (r3 == 0) goto L_0x0179
            int r8 = r15.mVisibility
            if (r8 == r14) goto L_0x0179
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.mTop
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            if (r8 != 0) goto L_0x0179
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.mBottom
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            if (r8 != 0) goto L_0x0179
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.mBaseline
            if (r8 != 0) goto L_0x0179
            androidx.constraintlayout.solver.widgets.ConstraintWidget r8 = r15.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r8.mBottom
            androidx.constraintlayout.solver.SolverVariable r8 = r11.createObjectVariable(r8)
            r9 = 1
            r11.addGreaterThan(r8, r2, r12, r9)
        L_0x0179:
            r23 = r6
            r18 = r13
            r13 = r3
            goto L_0x0185
        L_0x017f:
            r0 = 0
            r13 = 0
            r18 = 0
            r23 = 0
        L_0x0185:
            int r3 = r15.mWidth
            int r6 = r15.mMinWidth
            if (r3 >= r6) goto L_0x018c
            r3 = r6
        L_0x018c:
            int r6 = r15.mHeight
            int r8 = r15.mMinHeight
            if (r6 >= r8) goto L_0x0193
            r6 = r8
        L_0x0193:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r8 = r15.mListDimensionBehaviors
            r8 = r8[r12]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r8 == r9) goto L_0x019d
            r8 = 1
            goto L_0x019e
        L_0x019d:
            r8 = 0
        L_0x019e:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r9 = r15.mListDimensionBehaviors
            r10 = 1
            r9 = r9[r10]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r9 == r10) goto L_0x01a9
            r9 = 1
            goto L_0x01aa
        L_0x01a9:
            r9 = 0
        L_0x01aa:
            int r10 = r15.mDimensionRatioSide
            r15.mResolvedDimensionRatioSide = r10
            float r10 = r15.mDimensionRatio
            r15.mResolvedDimensionRatio = r10
            int r12 = r15.mMatchConstraintDefaultWidth
            int r14 = r15.mMatchConstraintDefaultHeight
            r21 = 0
            r22 = 4
            r24 = r4
            int r10 = (r10 > r21 ? 1 : (r10 == r21 ? 0 : -1))
            if (r10 <= 0) goto L_0x0269
            int r10 = r15.mVisibility
            r4 = 8
            if (r10 == r4) goto L_0x0269
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r10 = r15.mListDimensionBehaviors
            r19 = 0
            r10 = r10[r19]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r26 = r1
            r1 = 3
            if (r10 != r4) goto L_0x01d6
            if (r12 != 0) goto L_0x01d6
            r12 = 3
        L_0x01d6:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r15.mListDimensionBehaviors
            r10 = 1
            r4 = r4[r10]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r4 != r10) goto L_0x01e2
            if (r14 != 0) goto L_0x01e2
            r14 = 3
        L_0x01e2:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r15.mListDimensionBehaviors
            r10 = 0
            r4 = r4[r10]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r4 != r10) goto L_0x01fc
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r15.mListDimensionBehaviors
            r10 = 1
            r4 = r4[r10]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r4 != r10) goto L_0x01fc
            if (r12 != r1) goto L_0x01fc
            if (r14 != r1) goto L_0x01fc
            r15.setupDimensionRatio(r0, r13, r8, r9)
            goto L_0x025e
        L_0x01fc:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r15.mListDimensionBehaviors
            r8 = 0
            r4 = r4[r8]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r4 != r9) goto L_0x0226
            if (r12 != r1) goto L_0x0226
            r15.mResolvedDimensionRatioSide = r8
            float r1 = r15.mResolvedDimensionRatio
            int r3 = r15.mHeight
            float r3 = (float) r3
            float r1 = r1 * r3
            int r1 = (int) r1
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r3 = r15.mListDimensionBehaviors
            r4 = 1
            r3 = r3[r4]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r3 == r8) goto L_0x0223
            r22 = r1
            r30 = r6
            r28 = r14
            r27 = 4
            goto L_0x0273
        L_0x0223:
            r22 = r1
            goto L_0x0260
        L_0x0226:
            r4 = 1
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r8 = r15.mListDimensionBehaviors
            r8 = r8[r4]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r8 != r9) goto L_0x025e
            if (r14 != r1) goto L_0x025e
            r15.mResolvedDimensionRatioSide = r4
            int r1 = r15.mDimensionRatioSide
            r4 = -1
            if (r1 != r4) goto L_0x023f
            r1 = 1065353216(0x3f800000, float:1.0)
            float r4 = r15.mResolvedDimensionRatio
            float r1 = r1 / r4
            r15.mResolvedDimensionRatio = r1
        L_0x023f:
            float r1 = r15.mResolvedDimensionRatio
            int r4 = r15.mWidth
            float r4 = (float) r4
            float r1 = r1 * r4
            int r1 = (int) r1
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r15.mListDimensionBehaviors
            r6 = 0
            r4 = r4[r6]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r4 == r6) goto L_0x0259
            r30 = r1
            r22 = r3
            r27 = r12
            r28 = 4
            goto L_0x0273
        L_0x0259:
            r30 = r1
            r22 = r3
            goto L_0x0262
        L_0x025e:
            r22 = r3
        L_0x0260:
            r30 = r6
        L_0x0262:
            r27 = r12
            r28 = r14
            r29 = 1
            goto L_0x0275
        L_0x0269:
            r26 = r1
            r22 = r3
            r30 = r6
            r27 = r12
            r28 = r14
        L_0x0273:
            r29 = 0
        L_0x0275:
            int[] r1 = r15.mResolvedMatchConstraintDefault
            r3 = 0
            r1[r3] = r27
            r3 = 1
            r1[r3] = r28
            if (r29 == 0) goto L_0x028b
            int r1 = r15.mResolvedDimensionRatioSide
            if (r1 == 0) goto L_0x0287
            r4 = -1
            if (r1 != r4) goto L_0x028c
            goto L_0x0288
        L_0x0287:
            r4 = -1
        L_0x0288:
            r25 = 1
            goto L_0x028e
        L_0x028b:
            r4 = -1
        L_0x028c:
            r25 = 0
        L_0x028e:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.mListDimensionBehaviors
            r3 = 0
            r1 = r1[r3]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r1 != r3) goto L_0x029e
            boolean r1 = r15 instanceof androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer
            if (r1 == 0) goto L_0x029e
            r31 = 1
            goto L_0x02a0
        L_0x029e:
            r31 = 0
        L_0x02a0:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r15.mCenter
            boolean r1 = r1.isConnected()
            r14 = 1
            r32 = r1 ^ 1
            int r1 = r15.mHorizontalResolution
            r12 = 2
            r33 = 0
            if (r1 == r12) goto L_0x0378
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r1 = r15.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r1.start
            boolean r1 = r1.resolved
            if (r1 == 0) goto L_0x0300
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r1 = r15.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r1.end
            boolean r1 = r1.resolved
            if (r1 != 0) goto L_0x02c1
            goto L_0x0300
        L_0x02c1:
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r1 = r15.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r1.start
            int r1 = r1.value
            r11.addEquality(r7, r1)
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r1 = r15.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r1.end
            int r1 = r1.value
            r11.addEquality(r5, r1)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r15.mParent
            if (r1 == 0) goto L_0x02f2
            if (r0 == 0) goto L_0x02f2
            boolean[] r0 = r15.isTerminalWidget
            r1 = 0
            boolean r0 = r0[r1]
            if (r0 == 0) goto L_0x02f2
            boolean r0 = r40.isInHorizontalChain()
            if (r0 != 0) goto L_0x02f2
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r15.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.mRight
            androidx.constraintlayout.solver.SolverVariable r0 = r11.createObjectVariable(r0)
            r10 = 6
            r11.addGreaterThan(r0, r5, r1, r10)
        L_0x02f2:
            r38 = r2
            r35 = r13
            r39 = r24
            r37 = r26
            r24 = r5
            r26 = r7
            goto L_0x0386
        L_0x0300:
            r10 = 6
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r15.mParent
            if (r1 == 0) goto L_0x030e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.mRight
            androidx.constraintlayout.solver.SolverVariable r1 = r11.createObjectVariable(r1)
            r16 = r1
            goto L_0x0310
        L_0x030e:
            r16 = r33
        L_0x0310:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = r15.mParent
            if (r1 == 0) goto L_0x031d
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.mLeft
            androidx.constraintlayout.solver.SolverVariable r1 = r11.createObjectVariable(r1)
            r17 = r1
            goto L_0x031f
        L_0x031d:
            r17 = r33
        L_0x031f:
            boolean[] r1 = r15.isTerminalWidget
            r19 = 0
            boolean r3 = r1[r19]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.mListDimensionBehaviors
            r6 = r1[r19]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.mLeft
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r15.mRight
            int r1 = r15.f36mX
            r21 = 6
            r10 = r1
            int r1 = r15.mMinWidth
            r34 = r3
            r3 = 0
            r12 = r1
            int[] r1 = r15.mMaxDimension
            r1 = r1[r3]
            r35 = r13
            r13 = r1
            float r1 = r15.mHorizontalBiasPercent
            r14 = r1
            int r1 = r15.mMatchConstraintMinWidth
            r19 = r1
            int r1 = r15.mMatchConstraintMaxWidth
            r20 = r1
            float r1 = r15.mMatchConstraintPercentWidth
            r21 = r1
            r36 = r0
            r0 = r40
            r37 = r26
            r1 = r41
            r38 = r2
            r2 = r36
            r39 = r24
            r4 = r17
            r24 = r5
            r5 = r16
            r26 = r7
            r7 = r31
            r11 = r22
            r15 = r25
            r16 = r18
            r17 = r27
            r18 = r28
            r22 = r32
            r3 = r34
            r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            goto L_0x0384
        L_0x0378:
            r38 = r2
            r35 = r13
            r39 = r24
            r37 = r26
            r24 = r5
            r26 = r7
        L_0x0384:
            r15 = r40
        L_0x0386:
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r15.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.start
            boolean r0 = r0.resolved
            if (r0 == 0) goto L_0x03dc
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r15.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.end
            boolean r0 = r0.resolved
            if (r0 == 0) goto L_0x03dc
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r15.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.start
            int r0 = r0.value
            r11 = r41
            r7 = r39
            r11.addEquality(r7, r0)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r15.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.end
            int r0 = r0.value
            r5 = r38
            r11.addEquality(r5, r0)
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r15.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.baseline
            int r0 = r0.value
            r1 = r37
            r11.addEquality(r1, r0)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x03d7
            if (r23 != 0) goto L_0x03d7
            if (r35 == 0) goto L_0x03d7
            boolean[] r2 = r15.isTerminalWidget
            r4 = 1
            boolean r2 = r2[r4]
            if (r2 == 0) goto L_0x03d4
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.mBottom
            androidx.constraintlayout.solver.SolverVariable r0 = r11.createObjectVariable(r0)
            r2 = 6
            r3 = 0
            r11.addGreaterThan(r0, r5, r3, r2)
            goto L_0x03da
        L_0x03d4:
            r2 = 6
            r3 = 0
            goto L_0x03da
        L_0x03d7:
            r2 = 6
            r3 = 0
            r4 = 1
        L_0x03da:
            r12 = 0
            goto L_0x03e8
        L_0x03dc:
            r11 = r41
            r1 = r37
            r5 = r38
            r7 = r39
            r2 = 6
            r3 = 0
            r4 = 1
            r12 = 1
        L_0x03e8:
            int r0 = r15.mVerticalResolution
            r6 = 2
            if (r0 != r6) goto L_0x03ee
            r12 = 0
        L_0x03ee:
            if (r12 == 0) goto L_0x0494
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r15.mListDimensionBehaviors
            r0 = r0[r4]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r6) goto L_0x03ff
            boolean r0 = r15 instanceof androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer
            if (r0 == 0) goto L_0x03ff
            r16 = 1
            goto L_0x0401
        L_0x03ff:
            r16 = 0
        L_0x0401:
            if (r29 == 0) goto L_0x040d
            int r0 = r15.mResolvedDimensionRatioSide
            if (r0 == r4) goto L_0x040a
            r6 = -1
            if (r0 != r6) goto L_0x040d
        L_0x040a:
            r17 = 1
            goto L_0x040f
        L_0x040d:
            r17 = 0
        L_0x040f:
            int r0 = r15.mBaselineDistance
            if (r0 <= 0) goto L_0x042e
            int r0 = r40.getBaselineDistance()
            r11.addEquality(r1, r7, r0, r2)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.mBaseline
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            if (r0 == 0) goto L_0x0437
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r15.mBaseline
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            androidx.constraintlayout.solver.SolverVariable r0 = r11.createObjectVariable(r0)
            r11.addEquality(r1, r0, r3, r2)
            r22 = 0
            goto L_0x0439
        L_0x042e:
            int r0 = r15.mVisibility
            r6 = 8
            if (r0 != r6) goto L_0x0437
            r11.addEquality(r1, r7, r3, r2)
        L_0x0437:
            r22 = r32
        L_0x0439:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x0446
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.mBottom
            androidx.constraintlayout.solver.SolverVariable r0 = r11.createObjectVariable(r0)
            r18 = r0
            goto L_0x0448
        L_0x0446:
            r18 = r33
        L_0x0448:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x0454
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.mTop
            androidx.constraintlayout.solver.SolverVariable r0 = r11.createObjectVariable(r0)
            r33 = r0
        L_0x0454:
            boolean[] r0 = r15.isTerminalWidget
            boolean r3 = r0[r4]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r15.mListDimensionBehaviors
            r6 = r0[r4]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r15.mTop
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r15.mBottom
            int r10 = r15.f37mY
            int r12 = r15.mMinHeight
            int[] r0 = r15.mMaxDimension
            r13 = r0[r4]
            float r14 = r15.mVerticalBiasPercent
            int r0 = r15.mMatchConstraintMinHeight
            r19 = r0
            int r0 = r15.mMatchConstraintMaxHeight
            r20 = r0
            float r0 = r15.mMatchConstraintPercentHeight
            r21 = r0
            r0 = r40
            r1 = r41
            r2 = r35
            r4 = r33
            r25 = r5
            r5 = r18
            r31 = r7
            r7 = r16
            r11 = r30
            r15 = r17
            r16 = r23
            r17 = r28
            r18 = r27
            r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            goto L_0x0498
        L_0x0494:
            r25 = r5
            r31 = r7
        L_0x0498:
            if (r29 == 0) goto L_0x04c3
            r6 = 6
            r7 = r40
            int r0 = r7.mResolvedDimensionRatioSide
            r1 = 1
            if (r0 != r1) goto L_0x04b2
            float r5 = r7.mResolvedDimensionRatio
            r0 = r41
            r1 = r25
            r2 = r31
            r3 = r24
            r4 = r26
            r0.addRatio(r1, r2, r3, r4, r5, r6)
            goto L_0x04c5
        L_0x04b2:
            float r5 = r7.mResolvedDimensionRatio
            r6 = 6
            r0 = r41
            r1 = r24
            r2 = r26
            r3 = r25
            r4 = r31
            r0.addRatio(r1, r2, r3, r4, r5, r6)
            goto L_0x04c5
        L_0x04c3:
            r7 = r40
        L_0x04c5:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r7.mCenter
            boolean r0 = r0.isConnected()
            if (r0 == 0) goto L_0x04ed
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r7.mCenter
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.getTarget()
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r0.getOwner()
            float r1 = r7.mCircleConstraintAngle
            r2 = 1119092736(0x42b40000, float:90.0)
            float r1 = r1 + r2
            double r1 = (double) r1
            double r1 = java.lang.Math.toRadians(r1)
            float r1 = (float) r1
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r7.mCenter
            int r2 = r2.getMargin()
            r3 = r41
            r3.addCenterPoint(r7, r0, r1, r2)
        L_0x04ed:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.addToSolver(androidx.constraintlayout.solver.LinearSystem):void");
    }

    public void setupDimensionRatio(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z3 && !z4) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z3 && z4) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
            }
        }
        if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.mResolvedDimensionRatioSide = 1;
        } else if (this.mResolvedDimensionRatioSide == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
            this.mResolvedDimensionRatioSide = 0;
        }
        if (this.mResolvedDimensionRatioSide == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z && !z2) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z && z2) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            if (this.mMatchConstraintMinWidth > 0 && this.mMatchConstraintMinHeight == 0) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMinHeight > 0) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1 && z && z2) {
            this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
            this.mResolvedDimensionRatioSide = 1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:106:0x01ec A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x02b2  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x02f5  */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x0305  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x0324  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00dc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void applyConstraints(androidx.constraintlayout.solver.LinearSystem r28, boolean r29, boolean r30, androidx.constraintlayout.solver.SolverVariable r31, androidx.constraintlayout.solver.SolverVariable r32, androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour r33, boolean r34, androidx.constraintlayout.solver.widgets.ConstraintAnchor r35, androidx.constraintlayout.solver.widgets.ConstraintAnchor r36, int r37, int r38, int r39, int r40, float r41, boolean r42, boolean r43, int r44, int r45, int r46, int r47, float r48, boolean r49) {
        /*
            r27 = this;
            r0 = r27
            r10 = r28
            r11 = r31
            r12 = r32
            r13 = r35
            r14 = r36
            r1 = r39
            r2 = r40
            r3 = r45
            androidx.constraintlayout.solver.SolverVariable r15 = r10.createObjectVariable(r13)
            androidx.constraintlayout.solver.SolverVariable r9 = r10.createObjectVariable(r14)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r35.getTarget()
            androidx.constraintlayout.solver.SolverVariable r8 = r10.createObjectVariable(r4)
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r36.getTarget()
            androidx.constraintlayout.solver.SolverVariable r7 = r10.createObjectVariable(r4)
            androidx.constraintlayout.solver.Metrics r4 = androidx.constraintlayout.solver.LinearSystem.getMetrics()
            if (r4 == 0) goto L_0x003c
            androidx.constraintlayout.solver.Metrics r4 = androidx.constraintlayout.solver.LinearSystem.getMetrics()
            long r5 = r4.nonresolvedWidgets
            r16 = 1
            long r5 = r5 + r16
            r4.nonresolvedWidgets = r5
        L_0x003c:
            boolean r16 = r35.isConnected()
            boolean r17 = r36.isConnected()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r0.mCenter
            boolean r18 = r4.isConnected()
            r5 = 1
            if (r16 == 0) goto L_0x004f
            r4 = 1
            goto L_0x0050
        L_0x004f:
            r4 = 0
        L_0x0050:
            if (r17 == 0) goto L_0x0054
            int r4 = r4 + 1
        L_0x0054:
            if (r18 == 0) goto L_0x0058
            int r4 = r4 + 1
        L_0x0058:
            if (r42 == 0) goto L_0x005d
            r20 = 3
            goto L_0x005f
        L_0x005d:
            r20 = r44
        L_0x005f:
            int[] r21 = androidx.constraintlayout.solver.widgets.ConstraintWidget.C02031.f39xdde91696
            int r22 = r33.ordinal()
            r6 = r21[r22]
            r12 = 2
            if (r6 == r5) goto L_0x0072
            if (r6 == r12) goto L_0x0072
            r12 = 3
            if (r6 == r12) goto L_0x0072
            r12 = 4
            if (r6 == r12) goto L_0x0076
        L_0x0072:
            r6 = r20
        L_0x0074:
            r12 = 0
            goto L_0x007c
        L_0x0076:
            r6 = r20
            if (r6 != r12) goto L_0x007b
            goto L_0x0074
        L_0x007b:
            r12 = 1
        L_0x007c:
            int r5 = r0.mVisibility
            r21 = r4
            r4 = 8
            if (r5 != r4) goto L_0x0087
            r4 = 0
            r12 = 0
            goto L_0x0089
        L_0x0087:
            r4 = r38
        L_0x0089:
            if (r49 == 0) goto L_0x00a6
            if (r16 != 0) goto L_0x0097
            if (r17 != 0) goto L_0x0097
            if (r18 != 0) goto L_0x0097
            r5 = r37
            r10.addEquality(r15, r5)
            goto L_0x00a6
        L_0x0097:
            if (r16 == 0) goto L_0x00a6
            if (r17 != 0) goto L_0x00a6
            int r5 = r35.getMargin()
            r22 = r7
            r7 = 6
            r10.addEquality(r15, r8, r5, r7)
            goto L_0x00a9
        L_0x00a6:
            r22 = r7
            r7 = 6
        L_0x00a9:
            if (r12 != 0) goto L_0x00dc
            if (r34 == 0) goto L_0x00c3
            r5 = 3
            r7 = 0
            r10.addEquality(r9, r15, r7, r5)
            if (r1 <= 0) goto L_0x00b9
            r4 = 6
            r10.addGreaterThan(r9, r15, r1, r4)
            goto L_0x00ba
        L_0x00b9:
            r4 = 6
        L_0x00ba:
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r2 >= r5) goto L_0x00c8
            r10.addLowerThan(r9, r15, r2, r4)
            goto L_0x00c8
        L_0x00c3:
            r2 = 6
            r7 = 0
            r10.addEquality(r9, r15, r4, r2)
        L_0x00c8:
            r4 = r46
            r2 = r47
            r23 = r6
            r25 = r8
            r0 = r9
            r13 = r12
            r14 = r21
            r24 = r22
            r20 = 4
            r12 = r30
            goto L_0x01ea
        L_0x00dc:
            r7 = 0
            r2 = -2
            r5 = r46
            if (r5 != r2) goto L_0x00e8
            r5 = r47
            r19 = r12
            r12 = r4
            goto L_0x00ed
        L_0x00e8:
            r19 = r12
            r12 = r5
            r5 = r47
        L_0x00ed:
            if (r5 != r2) goto L_0x00f1
            r2 = r4
            goto L_0x00f2
        L_0x00f1:
            r2 = r5
        L_0x00f2:
            if (r12 <= 0) goto L_0x0106
            if (r29 == 0) goto L_0x00fb
            r5 = 1
            if (r6 != r5) goto L_0x00fb
            r5 = 0
            goto L_0x00fc
        L_0x00fb:
            r5 = 1
        L_0x00fc:
            if (r5 == 0) goto L_0x0102
            r5 = 6
            r10.addGreaterThan(r9, r15, r12, r5)
        L_0x0102:
            int r4 = java.lang.Math.max(r4, r12)
        L_0x0106:
            if (r2 <= 0) goto L_0x011d
            if (r29 == 0) goto L_0x010f
            r5 = 1
            if (r6 != r5) goto L_0x010f
            r5 = 0
            goto L_0x0110
        L_0x010f:
            r5 = 1
        L_0x0110:
            if (r5 == 0) goto L_0x0117
            r5 = 6
            r10.addLowerThan(r9, r15, r2, r5)
            goto L_0x0118
        L_0x0117:
            r5 = 6
        L_0x0118:
            int r4 = java.lang.Math.min(r4, r2)
            goto L_0x011e
        L_0x011d:
            r5 = 6
        L_0x011e:
            r7 = 1
            if (r6 != r7) goto L_0x0148
            if (r29 == 0) goto L_0x0129
            r10.addEquality(r9, r15, r4, r5)
            r5 = 1
            r7 = 4
            goto L_0x0136
        L_0x0129:
            if (r43 == 0) goto L_0x0131
            r7 = 4
            r10.addEquality(r9, r15, r4, r7)
            r5 = 1
            goto L_0x0136
        L_0x0131:
            r5 = 1
            r7 = 4
            r10.addEquality(r9, r15, r4, r5)
        L_0x0136:
            r13 = r4
            r23 = r6
            r25 = r8
            r0 = r9
            r6 = r19
            r14 = r21
            r24 = r22
            r20 = 4
            r4 = r30
            goto L_0x01cd
        L_0x0148:
            r5 = 2
            r7 = 4
            if (r6 != r5) goto L_0x01be
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r5 = r35.getType()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r7 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.TOP
            if (r5 == r7) goto L_0x017b
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r5 = r35.getType()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r7 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.BOTTOM
            if (r5 != r7) goto L_0x015d
            goto L_0x017b
        L_0x015d:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = r0.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r7 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.LEFT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r5.getAnchor(r7)
            androidx.constraintlayout.solver.SolverVariable r5 = r10.createObjectVariable(r5)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r7 = r0.mParent
            r37 = r4
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.RIGHT
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r7.getAnchor(r4)
            androidx.constraintlayout.solver.SolverVariable r4 = r10.createObjectVariable(r4)
            r7 = r4
            r19 = r5
            goto L_0x0198
        L_0x017b:
            r37 = r4
            androidx.constraintlayout.solver.widgets.ConstraintWidget r4 = r0.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r5 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.TOP
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r4.getAnchor(r5)
            androidx.constraintlayout.solver.SolverVariable r4 = r10.createObjectVariable(r4)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = r0.mParent
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r7 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.BOTTOM
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r5.getAnchor(r7)
            androidx.constraintlayout.solver.SolverVariable r5 = r10.createObjectVariable(r5)
            r19 = r4
            r7 = r5
        L_0x0198:
            androidx.constraintlayout.solver.ArrayRow r4 = r28.createRow()
            r5 = r37
            r14 = r21
            r13 = r5
            r0 = 6
            r21 = 3
            r5 = r9
            r23 = r6
            r6 = r15
            r24 = r22
            r20 = 4
            r25 = r8
            r8 = r19
            r0 = r9
            r9 = r48
            androidx.constraintlayout.solver.ArrayRow r4 = r4.createRowDimensionRatio(r5, r6, r7, r8, r9)
            r10.addConstraint(r4)
            r4 = r30
            r6 = 0
            goto L_0x01cd
        L_0x01be:
            r13 = r4
            r23 = r6
            r25 = r8
            r0 = r9
            r14 = r21
            r24 = r22
            r20 = 4
            r6 = r19
            r4 = 1
        L_0x01cd:
            if (r6 == 0) goto L_0x01e4
            r5 = 2
            if (r14 == r5) goto L_0x01e4
            if (r42 != 0) goto L_0x01e4
            int r5 = java.lang.Math.max(r12, r13)
            if (r2 <= 0) goto L_0x01de
            int r5 = java.lang.Math.min(r2, r5)
        L_0x01de:
            r6 = 6
            r10.addEquality(r0, r15, r5, r6)
            r13 = 0
            goto L_0x01e5
        L_0x01e4:
            r13 = r6
        L_0x01e5:
            r26 = r12
            r12 = r4
            r4 = r26
        L_0x01ea:
            if (r49 == 0) goto L_0x0338
            if (r43 == 0) goto L_0x01f0
            goto L_0x0338
        L_0x01f0:
            if (r16 != 0) goto L_0x01f8
            if (r17 != 0) goto L_0x01f8
            if (r18 != 0) goto L_0x01f8
            goto L_0x032a
        L_0x01f8:
            if (r16 == 0) goto L_0x01fe
            if (r17 != 0) goto L_0x01fe
            goto L_0x032a
        L_0x01fe:
            r14 = 5
            if (r16 != 0) goto L_0x0216
            if (r17 == 0) goto L_0x0216
            int r1 = r36.getMargin()
            int r1 = -r1
            r9 = r24
            r2 = 6
            r10.addEquality(r0, r9, r1, r2)
            if (r29 == 0) goto L_0x032a
            r8 = 0
            r10.addGreaterThan(r15, r11, r8, r14)
            goto L_0x032a
        L_0x0216:
            r9 = r24
            r8 = 0
            if (r16 == 0) goto L_0x032a
            if (r17 == 0) goto L_0x032a
            if (r13 == 0) goto L_0x02a5
            if (r29 == 0) goto L_0x0228
            if (r1 != 0) goto L_0x0228
            r6 = 6
            r10.addGreaterThan(r0, r15, r8, r6)
            goto L_0x0229
        L_0x0228:
            r6 = 6
        L_0x0229:
            r1 = r23
            if (r1 != 0) goto L_0x0257
            if (r2 > 0) goto L_0x0235
            if (r4 <= 0) goto L_0x0232
            goto L_0x0235
        L_0x0232:
            r1 = 6
            r3 = 0
            goto L_0x0237
        L_0x0235:
            r1 = 4
            r3 = 1
        L_0x0237:
            int r5 = r35.getMargin()
            r7 = r25
            r10.addEquality(r15, r7, r5, r1)
            int r5 = r36.getMargin()
            int r5 = -r5
            r10.addEquality(r0, r9, r5, r1)
            if (r2 > 0) goto L_0x024f
            if (r4 <= 0) goto L_0x024d
            goto L_0x024f
        L_0x024d:
            r1 = 0
            goto L_0x0250
        L_0x024f:
            r1 = 1
        L_0x0250:
            r17 = r3
            r4 = 6
            r5 = 1
            r16 = 5
            goto L_0x0262
        L_0x0257:
            r7 = r25
            r5 = 1
            if (r1 != r5) goto L_0x0265
            r1 = 1
            r4 = 6
            r16 = 6
            r17 = 1
        L_0x0262:
            r6 = r27
            goto L_0x02b0
        L_0x0265:
            r4 = 3
            if (r1 != r4) goto L_0x02a0
            r1 = 2
            if (r3 == r1) goto L_0x0270
            if (r3 != r5) goto L_0x026e
            goto L_0x0270
        L_0x026e:
            r1 = 0
            goto L_0x0271
        L_0x0270:
            r1 = 1
        L_0x0271:
            if (r1 != 0) goto L_0x0296
            if (r42 != 0) goto L_0x0281
            r4 = 6
            r6 = r27
            int r1 = r6.mResolvedDimensionRatioSide
            r3 = -1
            if (r1 == r3) goto L_0x0284
            if (r2 > 0) goto L_0x0284
            r1 = 6
            goto L_0x0285
        L_0x0281:
            r4 = 6
            r6 = r27
        L_0x0284:
            r1 = 4
        L_0x0285:
            int r2 = r35.getMargin()
            r10.addEquality(r15, r7, r2, r1)
            int r2 = r36.getMargin()
            int r2 = -r2
            r10.addEquality(r0, r9, r2, r1)
            r1 = 1
            goto L_0x029a
        L_0x0296:
            r4 = 6
            r6 = r27
            r1 = 0
        L_0x029a:
            r17 = r1
            r1 = 1
            r16 = 5
            goto L_0x02b0
        L_0x02a0:
            r4 = 6
            r6 = r27
            r1 = 0
            goto L_0x02ac
        L_0x02a5:
            r7 = r25
            r4 = 6
            r5 = 1
            r6 = r27
            r1 = 1
        L_0x02ac:
            r16 = 5
            r17 = 0
        L_0x02b0:
            if (r1 == 0) goto L_0x02f5
            int r18 = r35.getMargin()
            int r19 = r36.getMargin()
            r1 = r28
            r2 = r15
            r3 = r7
            r14 = 6
            r4 = r18
            r18 = 1
            r5 = r41
            r6 = r9
            r14 = r7
            r7 = r0
            r33 = r12
            r12 = 0
            r8 = r19
            r12 = r9
            r9 = r16
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            r1 = r35
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r1.mTarget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r2.mOwner
            boolean r2 = r2 instanceof androidx.constraintlayout.solver.widgets.Barrier
            r3 = r36
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r3.mTarget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r4 = r4.mOwner
            boolean r4 = r4 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r2 == 0) goto L_0x02ec
            if (r4 != 0) goto L_0x02ec
            r18 = r29
            r2 = 1
            r4 = 6
            goto L_0x0302
        L_0x02ec:
            if (r2 != 0) goto L_0x02fd
            if (r4 == 0) goto L_0x02fd
            r2 = r29
            r4 = 5
            r5 = 6
            goto L_0x0303
        L_0x02f5:
            r1 = r35
            r3 = r36
            r14 = r7
            r33 = r12
            r12 = r9
        L_0x02fd:
            r2 = r29
            r18 = r2
            r4 = 5
        L_0x0302:
            r5 = 5
        L_0x0303:
            if (r17 == 0) goto L_0x0307
            r4 = 6
            r5 = 6
        L_0x0307:
            if (r13 != 0) goto L_0x030b
            if (r18 != 0) goto L_0x030d
        L_0x030b:
            if (r17 == 0) goto L_0x0314
        L_0x030d:
            int r1 = r35.getMargin()
            r10.addGreaterThan(r15, r14, r1, r5)
        L_0x0314:
            if (r13 != 0) goto L_0x0318
            if (r2 != 0) goto L_0x031a
        L_0x0318:
            if (r17 == 0) goto L_0x0322
        L_0x031a:
            int r1 = r36.getMargin()
            int r1 = -r1
            r10.addLowerThan(r0, r12, r1, r4)
        L_0x0322:
            if (r29 == 0) goto L_0x032c
            r1 = 6
            r2 = 0
            r10.addGreaterThan(r15, r11, r2, r1)
            goto L_0x032e
        L_0x032a:
            r33 = r12
        L_0x032c:
            r1 = 6
            r2 = 0
        L_0x032e:
            if (r29 == 0) goto L_0x0337
            if (r33 == 0) goto L_0x0337
            r3 = r32
            r10.addGreaterThan(r3, r0, r2, r1)
        L_0x0337:
            return
        L_0x0338:
            r3 = r32
            r33 = r12
            r4 = r14
            r1 = 6
            r2 = 0
            r5 = 2
            if (r4 >= r5) goto L_0x034c
            if (r29 == 0) goto L_0x034c
            if (r33 == 0) goto L_0x034c
            r10.addGreaterThan(r15, r11, r2, r1)
            r10.addGreaterThan(r3, r0, r2, r1)
        L_0x034c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.applyConstraints(androidx.constraintlayout.solver.LinearSystem, boolean, boolean, androidx.constraintlayout.solver.SolverVariable, androidx.constraintlayout.solver.SolverVariable, androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour, boolean, androidx.constraintlayout.solver.widgets.ConstraintAnchor, androidx.constraintlayout.solver.widgets.ConstraintAnchor, int, int, int, int, float, boolean, boolean, int, int, int, int, float, boolean):void");
    }

    public void updateFromSolver(LinearSystem linearSystem) {
        int objectVariableValue = linearSystem.getObjectVariableValue(this.mLeft);
        int objectVariableValue2 = linearSystem.getObjectVariableValue(this.mTop);
        int objectVariableValue3 = linearSystem.getObjectVariableValue(this.mRight);
        int objectVariableValue4 = linearSystem.getObjectVariableValue(this.mBottom);
        if (this.horizontalRun.start.resolved && this.horizontalRun.end.resolved) {
            objectVariableValue = this.horizontalRun.start.value;
            objectVariableValue3 = this.horizontalRun.end.value;
        }
        if (this.verticalRun.start.resolved && this.verticalRun.end.resolved) {
            objectVariableValue2 = this.verticalRun.start.value;
            objectVariableValue4 = this.verticalRun.end.value;
        }
        int i = objectVariableValue4 - objectVariableValue2;
        if (objectVariableValue3 - objectVariableValue < 0 || i < 0 || objectVariableValue == Integer.MIN_VALUE || objectVariableValue == Integer.MAX_VALUE || objectVariableValue2 == Integer.MIN_VALUE || objectVariableValue2 == Integer.MAX_VALUE || objectVariableValue3 == Integer.MIN_VALUE || objectVariableValue3 == Integer.MAX_VALUE || objectVariableValue4 == Integer.MIN_VALUE || objectVariableValue4 == Integer.MAX_VALUE) {
            objectVariableValue4 = 0;
            objectVariableValue = 0;
            objectVariableValue2 = 0;
            objectVariableValue3 = 0;
        }
        setFrame(objectVariableValue, objectVariableValue2, objectVariableValue3, objectVariableValue4);
    }

    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> hashMap) {
        this.mHorizontalResolution = constraintWidget.mHorizontalResolution;
        this.mVerticalResolution = constraintWidget.mVerticalResolution;
        this.mMatchConstraintDefaultWidth = constraintWidget.mMatchConstraintDefaultWidth;
        this.mMatchConstraintDefaultHeight = constraintWidget.mMatchConstraintDefaultHeight;
        int[] iArr = this.mResolvedMatchConstraintDefault;
        int[] iArr2 = constraintWidget.mResolvedMatchConstraintDefault;
        iArr[0] = iArr2[0];
        iArr[1] = iArr2[1];
        this.mMatchConstraintMinWidth = constraintWidget.mMatchConstraintMinWidth;
        this.mMatchConstraintMaxWidth = constraintWidget.mMatchConstraintMaxWidth;
        this.mMatchConstraintMinHeight = constraintWidget.mMatchConstraintMinHeight;
        this.mMatchConstraintMaxHeight = constraintWidget.mMatchConstraintMaxHeight;
        this.mMatchConstraintPercentHeight = constraintWidget.mMatchConstraintPercentHeight;
        this.mIsWidthWrapContent = constraintWidget.mIsWidthWrapContent;
        this.mIsHeightWrapContent = constraintWidget.mIsHeightWrapContent;
        this.mResolvedDimensionRatioSide = constraintWidget.mResolvedDimensionRatioSide;
        this.mResolvedDimensionRatio = constraintWidget.mResolvedDimensionRatio;
        int[] iArr3 = constraintWidget.mMaxDimension;
        this.mMaxDimension = Arrays.copyOf(iArr3, iArr3.length);
        this.mCircleConstraintAngle = constraintWidget.mCircleConstraintAngle;
        this.hasBaseline = constraintWidget.hasBaseline;
        this.inPlaceholder = constraintWidget.inPlaceholder;
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mListDimensionBehaviors = (DimensionBehaviour[]) Arrays.copyOf(this.mListDimensionBehaviors, 2);
        ConstraintWidget constraintWidget2 = null;
        this.mParent = this.mParent == null ? null : (ConstraintWidget) hashMap.get(constraintWidget.mParent);
        this.mWidth = constraintWidget.mWidth;
        this.mHeight = constraintWidget.mHeight;
        this.mDimensionRatio = constraintWidget.mDimensionRatio;
        this.mDimensionRatioSide = constraintWidget.mDimensionRatioSide;
        this.f36mX = constraintWidget.f36mX;
        this.f37mY = constraintWidget.f37mY;
        this.mRelX = constraintWidget.mRelX;
        this.mRelY = constraintWidget.mRelY;
        this.mOffsetX = constraintWidget.mOffsetX;
        this.mOffsetY = constraintWidget.mOffsetY;
        this.mBaselineDistance = constraintWidget.mBaselineDistance;
        this.mMinWidth = constraintWidget.mMinWidth;
        this.mMinHeight = constraintWidget.mMinHeight;
        this.mHorizontalBiasPercent = constraintWidget.mHorizontalBiasPercent;
        this.mVerticalBiasPercent = constraintWidget.mVerticalBiasPercent;
        this.mCompanionWidget = constraintWidget.mCompanionWidget;
        this.mContainerItemSkip = constraintWidget.mContainerItemSkip;
        this.mVisibility = constraintWidget.mVisibility;
        this.mDebugName = constraintWidget.mDebugName;
        this.mType = constraintWidget.mType;
        this.mDistToTop = constraintWidget.mDistToTop;
        this.mDistToLeft = constraintWidget.mDistToLeft;
        this.mDistToRight = constraintWidget.mDistToRight;
        this.mDistToBottom = constraintWidget.mDistToBottom;
        this.mLeftHasCentered = constraintWidget.mLeftHasCentered;
        this.mRightHasCentered = constraintWidget.mRightHasCentered;
        this.mTopHasCentered = constraintWidget.mTopHasCentered;
        this.mBottomHasCentered = constraintWidget.mBottomHasCentered;
        this.mHorizontalWrapVisited = constraintWidget.mHorizontalWrapVisited;
        this.mVerticalWrapVisited = constraintWidget.mVerticalWrapVisited;
        this.mOptimizerMeasurable = constraintWidget.mOptimizerMeasurable;
        this.mGroupsToSolver = constraintWidget.mGroupsToSolver;
        this.mHorizontalChainStyle = constraintWidget.mHorizontalChainStyle;
        this.mVerticalChainStyle = constraintWidget.mVerticalChainStyle;
        this.mHorizontalChainFixedPosition = constraintWidget.mHorizontalChainFixedPosition;
        this.mVerticalChainFixedPosition = constraintWidget.mVerticalChainFixedPosition;
        float[] fArr = this.mWeight;
        float[] fArr2 = constraintWidget.mWeight;
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[1];
        ConstraintWidget[] constraintWidgetArr = this.mListNextMatchConstraintsWidget;
        ConstraintWidget[] constraintWidgetArr2 = constraintWidget.mListNextMatchConstraintsWidget;
        constraintWidgetArr[0] = constraintWidgetArr2[0];
        constraintWidgetArr[1] = constraintWidgetArr2[1];
        ConstraintWidget[] constraintWidgetArr3 = this.mNextChainWidget;
        ConstraintWidget[] constraintWidgetArr4 = constraintWidget.mNextChainWidget;
        constraintWidgetArr3[0] = constraintWidgetArr4[0];
        constraintWidgetArr3[1] = constraintWidgetArr4[1];
        ConstraintWidget constraintWidget3 = constraintWidget.mHorizontalNextWidget;
        this.mHorizontalNextWidget = constraintWidget3 == null ? null : (ConstraintWidget) hashMap.get(constraintWidget3);
        ConstraintWidget constraintWidget4 = constraintWidget.mVerticalNextWidget;
        if (constraintWidget4 != null) {
            constraintWidget2 = (ConstraintWidget) hashMap.get(constraintWidget4);
        }
        this.mVerticalNextWidget = constraintWidget2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0069, code lost:
        if (r2 < r8) goto L_0x006d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0084, code lost:
        if (r3 < r8) goto L_0x0088;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateFromRuns(boolean r8, boolean r9) {
        /*
            r7 = this;
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r0 = r7.horizontalRun
            boolean r0 = r0.isResolved()
            r8 = r8 & r0
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r0 = r7.verticalRun
            boolean r0 = r0.isResolved()
            r9 = r9 & r0
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r0 = r7.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r0.start
            int r0 = r0.value
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r1 = r7.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r1.start
            int r1 = r1.value
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r2 = r7.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r2.end
            int r2 = r2.value
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r3 = r7.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r3 = r3.end
            int r3 = r3.value
            int r4 = r2 - r0
            int r5 = r3 - r1
            r6 = 0
            if (r4 < 0) goto L_0x0044
            if (r5 < 0) goto L_0x0044
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 == r4) goto L_0x0044
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r0 == r5) goto L_0x0044
            if (r1 == r4) goto L_0x0044
            if (r1 == r5) goto L_0x0044
            if (r2 == r4) goto L_0x0044
            if (r2 == r5) goto L_0x0044
            if (r3 == r4) goto L_0x0044
            if (r3 != r5) goto L_0x0048
        L_0x0044:
            r0 = 0
            r1 = 0
            r2 = 0
            r3 = 0
        L_0x0048:
            int r2 = r2 - r0
            int r3 = r3 - r1
            if (r8 == 0) goto L_0x004e
            r7.f36mX = r0
        L_0x004e:
            if (r9 == 0) goto L_0x0052
            r7.f37mY = r1
        L_0x0052:
            int r0 = r7.mVisibility
            r1 = 8
            if (r0 != r1) goto L_0x005d
            r7.mWidth = r6
            r7.mHeight = r6
            return
        L_0x005d:
            if (r8 == 0) goto L_0x0077
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r8 = r7.mListDimensionBehaviors
            r8 = r8[r6]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r8 != r0) goto L_0x006c
            int r8 = r7.mWidth
            if (r2 >= r8) goto L_0x006c
            goto L_0x006d
        L_0x006c:
            r8 = r2
        L_0x006d:
            r7.mWidth = r8
            int r8 = r7.mWidth
            int r0 = r7.mMinWidth
            if (r8 >= r0) goto L_0x0077
            r7.mWidth = r0
        L_0x0077:
            if (r9 == 0) goto L_0x0092
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r8 = r7.mListDimensionBehaviors
            r9 = 1
            r8 = r8[r9]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r8 != r9) goto L_0x0087
            int r8 = r7.mHeight
            if (r3 >= r8) goto L_0x0087
            goto L_0x0088
        L_0x0087:
            r8 = r3
        L_0x0088:
            r7.mHeight = r8
            int r8 = r7.mHeight
            int r9 = r7.mMinHeight
            if (r8 >= r9) goto L_0x0092
            r7.mHeight = r9
        L_0x0092:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.updateFromRuns(boolean, boolean):void");
    }
}
