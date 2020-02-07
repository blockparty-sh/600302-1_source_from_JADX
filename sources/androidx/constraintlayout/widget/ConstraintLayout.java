package androidx.constraintlayout.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.core.internal.view.SupportMenu;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.HashMap;

public class ConstraintLayout extends ViewGroup {
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_DRAW_CONSTRAINTS = false;
    public static final int DESIGN_INFO_ID = 0;
    private static final String TAG = "ConstraintLayout";
    private static final boolean USE_CONSTRAINTS_HELPER = true;
    public static final String VERSION = "ConstraintLayout-2.0-beta3";
    SparseArray<View> mChildrenByIds = new SparseArray<>();
    /* access modifiers changed from: private */
    public ArrayList<ConstraintHelper> mConstraintHelpers = new ArrayList<>(4);
    protected ConstraintLayoutStates mConstraintLayoutSpec = null;
    private ConstraintSet mConstraintSet = null;
    private int mConstraintSetId = -1;
    private ConstraintsChangedListener mConstraintsChangedListener;
    private HashMap<String, Integer> mDesignIds = new HashMap<>();
    protected boolean mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
    private int mLastMeasureHeight = -1;
    int mLastMeasureHeightMode = 0;
    int mLastMeasureHeightSize = -1;
    private int mLastMeasureWidth = -1;
    int mLastMeasureWidthMode = 0;
    int mLastMeasureWidthSize = -1;
    /* access modifiers changed from: protected */
    public ConstraintWidgetContainer mLayoutWidget = new ConstraintWidgetContainer();
    private int mMaxHeight = Integer.MAX_VALUE;
    private int mMaxWidth = Integer.MAX_VALUE;
    Measurer mMeasurer = new Measurer(this);
    private Metrics mMetrics;
    private int mMinHeight = 0;
    private int mMinWidth = 0;
    /* access modifiers changed from: private */
    public int mOnMeasureHeightMeasureSpec = 0;
    /* access modifiers changed from: private */
    public int mOnMeasureWidthMeasureSpec = 0;
    private int mOptimizationLevel = 7;
    private SparseArray<ConstraintWidget> mTempMapIdToWidget = new SparseArray<>();
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<>(100);

    /* renamed from: androidx.constraintlayout.widget.ConstraintLayout$1 */
    static /* synthetic */ class C02141 {

        /* renamed from: $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour */
        static final /* synthetic */ int[] f48xdde91696 = new int[DimensionBehaviour.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f48xdde91696 = r0
                int[] r0 = f48xdde91696     // Catch:{ NoSuchFieldError -> 0x0014 }
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f48xdde91696     // Catch:{ NoSuchFieldError -> 0x001f }
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f48xdde91696     // Catch:{ NoSuchFieldError -> 0x002a }
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f48xdde91696     // Catch:{ NoSuchFieldError -> 0x0035 }
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.C02141.<clinit>():void");
        }
    }

    public static class LayoutParams extends MarginLayoutParams {
        public static final int BASELINE = 5;
        public static final int BOTTOM = 4;
        public static final int CHAIN_PACKED = 2;
        public static final int CHAIN_SPREAD = 0;
        public static final int CHAIN_SPREAD_INSIDE = 1;
        public static final int END = 7;
        public static final int HORIZONTAL = 0;
        public static final int LEFT = 1;
        public static final int MATCH_CONSTRAINT = 0;
        public static final int MATCH_CONSTRAINT_PERCENT = 2;
        public static final int MATCH_CONSTRAINT_SPREAD = 0;
        public static final int MATCH_CONSTRAINT_WRAP = 1;
        public static final int PARENT_ID = 0;
        public static final int RIGHT = 2;
        public static final int START = 6;
        public static final int TOP = 3;
        public static final int UNSET = -1;
        public static final int VERTICAL = 1;
        public int baselineToBaseline = -1;
        public int bottomToBottom = -1;
        public int bottomToTop = -1;
        public float circleAngle = 0.0f;
        public int circleConstraint = -1;
        public int circleRadius = 0;
        public boolean constrainedHeight = false;
        public boolean constrainedWidth = false;
        public String constraintTag = null;
        public String dimensionRatio = null;
        int dimensionRatioSide = 1;
        float dimensionRatioValue = 0.0f;
        public int editorAbsoluteX = -1;
        public int editorAbsoluteY = -1;
        public int endToEnd = -1;
        public int endToStart = -1;
        public int goneBottomMargin = -1;
        public int goneEndMargin = -1;
        public int goneLeftMargin = -1;
        public int goneRightMargin = -1;
        public int goneStartMargin = -1;
        public int goneTopMargin = -1;
        public int guideBegin = -1;
        public int guideEnd = -1;
        public float guidePercent = -1.0f;
        public boolean helped = false;
        public float horizontalBias = 0.5f;
        public int horizontalChainStyle = 0;
        boolean horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
        public float horizontalWeight = -1.0f;
        boolean isGuideline = false;
        boolean isHelper = false;
        boolean isInPlaceholder = false;
        boolean isVirtualGroup = false;
        public int leftToLeft = -1;
        public int leftToRight = -1;
        public int matchConstraintDefaultHeight = 0;
        public int matchConstraintDefaultWidth = 0;
        public int matchConstraintMaxHeight = 0;
        public int matchConstraintMaxWidth = 0;
        public int matchConstraintMinHeight = 0;
        public int matchConstraintMinWidth = 0;
        public float matchConstraintPercentHeight = 1.0f;
        public float matchConstraintPercentWidth = 1.0f;
        boolean needsBaseline = false;
        public int orientation = -1;
        int resolveGoneLeftMargin = -1;
        int resolveGoneRightMargin = -1;
        int resolvedGuideBegin;
        int resolvedGuideEnd;
        float resolvedGuidePercent;
        float resolvedHorizontalBias = 0.5f;
        int resolvedLeftToLeft = -1;
        int resolvedLeftToRight = -1;
        int resolvedRightToLeft = -1;
        int resolvedRightToRight = -1;
        public int rightToLeft = -1;
        public int rightToRight = -1;
        public int startToEnd = -1;
        public int startToStart = -1;
        public int topToBottom = -1;
        public int topToTop = -1;
        public float verticalBias = 0.5f;
        public int verticalChainStyle = 0;
        boolean verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
        public float verticalWeight = -1.0f;
        ConstraintWidget widget = new ConstraintWidget();

        private static class Table {
            public static final int ANDROID_ORIENTATION = 1;
            public static final int LAYOUT_CONSTRAINED_HEIGHT = 28;
            public static final int LAYOUT_CONSTRAINED_WIDTH = 27;
            public static final int LAYOUT_CONSTRAINT_BASELINE_CREATOR = 43;
            public static final int LAYOUT_CONSTRAINT_BASELINE_TO_BASELINE_OF = 16;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_CREATOR = 42;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_BOTTOM_OF = 15;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_TOP_OF = 14;
            public static final int LAYOUT_CONSTRAINT_CIRCLE = 2;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_ANGLE = 4;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_RADIUS = 3;
            public static final int LAYOUT_CONSTRAINT_DIMENSION_RATIO = 44;
            public static final int LAYOUT_CONSTRAINT_END_TO_END_OF = 20;
            public static final int LAYOUT_CONSTRAINT_END_TO_START_OF = 19;
            public static final int LAYOUT_CONSTRAINT_GUIDE_BEGIN = 5;
            public static final int LAYOUT_CONSTRAINT_GUIDE_END = 6;
            public static final int LAYOUT_CONSTRAINT_GUIDE_PERCENT = 7;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_DEFAULT = 32;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MAX = 37;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MIN = 36;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_PERCENT = 38;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_BIAS = 29;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_CHAINSTYLE = 47;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_WEIGHT = 45;
            public static final int LAYOUT_CONSTRAINT_LEFT_CREATOR = 39;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_LEFT_OF = 8;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_RIGHT_OF = 9;
            public static final int LAYOUT_CONSTRAINT_RIGHT_CREATOR = 41;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_LEFT_OF = 10;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_RIGHT_OF = 11;
            public static final int LAYOUT_CONSTRAINT_START_TO_END_OF = 17;
            public static final int LAYOUT_CONSTRAINT_START_TO_START_OF = 18;
            public static final int LAYOUT_CONSTRAINT_TAG = 51;
            public static final int LAYOUT_CONSTRAINT_TOP_CREATOR = 40;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_BOTTOM_OF = 13;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_TOP_OF = 12;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_BIAS = 30;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE = 48;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_WEIGHT = 46;
            public static final int LAYOUT_CONSTRAINT_WIDTH_DEFAULT = 31;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MAX = 34;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MIN = 33;
            public static final int LAYOUT_CONSTRAINT_WIDTH_PERCENT = 35;
            public static final int LAYOUT_EDITOR_ABSOLUTEX = 49;
            public static final int LAYOUT_EDITOR_ABSOLUTEY = 50;
            public static final int LAYOUT_GONE_MARGIN_BOTTOM = 24;
            public static final int LAYOUT_GONE_MARGIN_END = 26;
            public static final int LAYOUT_GONE_MARGIN_LEFT = 21;
            public static final int LAYOUT_GONE_MARGIN_RIGHT = 23;
            public static final int LAYOUT_GONE_MARGIN_START = 25;
            public static final int LAYOUT_GONE_MARGIN_TOP = 22;
            public static final int UNUSED = 0;
            public static final SparseIntArray map = new SparseIntArray();

            private Table() {
            }

            static {
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                map.append(C0215R.styleable.ConstraintLayout_Layout_android_orientation, 1);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
                map.append(C0215R.styleable.ConstraintLayout_Layout_layout_constraintTag, 51);
            }
        }

        public ConstraintWidget getConstraintWidget() {
            return this.widget;
        }

        public void setWidgetDebugName(String str) {
            this.widget.setDebugName(str);
        }

        public void reset() {
            ConstraintWidget constraintWidget = this.widget;
            if (constraintWidget != null) {
                constraintWidget.reset();
            }
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.guideBegin = layoutParams.guideBegin;
            this.guideEnd = layoutParams.guideEnd;
            this.guidePercent = layoutParams.guidePercent;
            this.leftToLeft = layoutParams.leftToLeft;
            this.leftToRight = layoutParams.leftToRight;
            this.rightToLeft = layoutParams.rightToLeft;
            this.rightToRight = layoutParams.rightToRight;
            this.topToTop = layoutParams.topToTop;
            this.topToBottom = layoutParams.topToBottom;
            this.bottomToTop = layoutParams.bottomToTop;
            this.bottomToBottom = layoutParams.bottomToBottom;
            this.baselineToBaseline = layoutParams.baselineToBaseline;
            this.circleConstraint = layoutParams.circleConstraint;
            this.circleRadius = layoutParams.circleRadius;
            this.circleAngle = layoutParams.circleAngle;
            this.startToEnd = layoutParams.startToEnd;
            this.startToStart = layoutParams.startToStart;
            this.endToStart = layoutParams.endToStart;
            this.endToEnd = layoutParams.endToEnd;
            this.goneLeftMargin = layoutParams.goneLeftMargin;
            this.goneTopMargin = layoutParams.goneTopMargin;
            this.goneRightMargin = layoutParams.goneRightMargin;
            this.goneBottomMargin = layoutParams.goneBottomMargin;
            this.goneStartMargin = layoutParams.goneStartMargin;
            this.goneEndMargin = layoutParams.goneEndMargin;
            this.horizontalBias = layoutParams.horizontalBias;
            this.verticalBias = layoutParams.verticalBias;
            this.dimensionRatio = layoutParams.dimensionRatio;
            this.dimensionRatioValue = layoutParams.dimensionRatioValue;
            this.dimensionRatioSide = layoutParams.dimensionRatioSide;
            this.horizontalWeight = layoutParams.horizontalWeight;
            this.verticalWeight = layoutParams.verticalWeight;
            this.horizontalChainStyle = layoutParams.horizontalChainStyle;
            this.verticalChainStyle = layoutParams.verticalChainStyle;
            this.constrainedWidth = layoutParams.constrainedWidth;
            this.constrainedHeight = layoutParams.constrainedHeight;
            this.matchConstraintDefaultWidth = layoutParams.matchConstraintDefaultWidth;
            this.matchConstraintDefaultHeight = layoutParams.matchConstraintDefaultHeight;
            this.matchConstraintMinWidth = layoutParams.matchConstraintMinWidth;
            this.matchConstraintMaxWidth = layoutParams.matchConstraintMaxWidth;
            this.matchConstraintMinHeight = layoutParams.matchConstraintMinHeight;
            this.matchConstraintMaxHeight = layoutParams.matchConstraintMaxHeight;
            this.matchConstraintPercentWidth = layoutParams.matchConstraintPercentWidth;
            this.matchConstraintPercentHeight = layoutParams.matchConstraintPercentHeight;
            this.editorAbsoluteX = layoutParams.editorAbsoluteX;
            this.editorAbsoluteY = layoutParams.editorAbsoluteY;
            this.orientation = layoutParams.orientation;
            this.horizontalDimensionFixed = layoutParams.horizontalDimensionFixed;
            this.verticalDimensionFixed = layoutParams.verticalDimensionFixed;
            this.needsBaseline = layoutParams.needsBaseline;
            this.isGuideline = layoutParams.isGuideline;
            this.resolvedLeftToLeft = layoutParams.resolvedLeftToLeft;
            this.resolvedLeftToRight = layoutParams.resolvedLeftToRight;
            this.resolvedRightToLeft = layoutParams.resolvedRightToLeft;
            this.resolvedRightToRight = layoutParams.resolvedRightToRight;
            this.resolveGoneLeftMargin = layoutParams.resolveGoneLeftMargin;
            this.resolveGoneRightMargin = layoutParams.resolveGoneRightMargin;
            this.resolvedHorizontalBias = layoutParams.resolvedHorizontalBias;
            this.constraintTag = layoutParams.constraintTag;
            this.widget = layoutParams.widget;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            int i;
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0215R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                int i3 = Table.map.get(index);
                String str = ConstraintLayout.TAG;
                switch (i3) {
                    case 1:
                        this.orientation = obtainStyledAttributes.getInt(index, this.orientation);
                        break;
                    case 2:
                        this.circleConstraint = obtainStyledAttributes.getResourceId(index, this.circleConstraint);
                        if (this.circleConstraint != -1) {
                            break;
                        } else {
                            this.circleConstraint = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 3:
                        this.circleRadius = obtainStyledAttributes.getDimensionPixelSize(index, this.circleRadius);
                        break;
                    case 4:
                        this.circleAngle = obtainStyledAttributes.getFloat(index, this.circleAngle) % 360.0f;
                        float f = this.circleAngle;
                        if (f >= 0.0f) {
                            break;
                        } else {
                            this.circleAngle = (360.0f - f) % 360.0f;
                            break;
                        }
                    case 5:
                        this.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideBegin);
                        break;
                    case 6:
                        this.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideEnd);
                        break;
                    case 7:
                        this.guidePercent = obtainStyledAttributes.getFloat(index, this.guidePercent);
                        break;
                    case 8:
                        this.leftToLeft = obtainStyledAttributes.getResourceId(index, this.leftToLeft);
                        if (this.leftToLeft != -1) {
                            break;
                        } else {
                            this.leftToLeft = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 9:
                        this.leftToRight = obtainStyledAttributes.getResourceId(index, this.leftToRight);
                        if (this.leftToRight != -1) {
                            break;
                        } else {
                            this.leftToRight = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 10:
                        this.rightToLeft = obtainStyledAttributes.getResourceId(index, this.rightToLeft);
                        if (this.rightToLeft != -1) {
                            break;
                        } else {
                            this.rightToLeft = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 11:
                        this.rightToRight = obtainStyledAttributes.getResourceId(index, this.rightToRight);
                        if (this.rightToRight != -1) {
                            break;
                        } else {
                            this.rightToRight = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 12:
                        this.topToTop = obtainStyledAttributes.getResourceId(index, this.topToTop);
                        if (this.topToTop != -1) {
                            break;
                        } else {
                            this.topToTop = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 13:
                        this.topToBottom = obtainStyledAttributes.getResourceId(index, this.topToBottom);
                        if (this.topToBottom != -1) {
                            break;
                        } else {
                            this.topToBottom = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 14:
                        this.bottomToTop = obtainStyledAttributes.getResourceId(index, this.bottomToTop);
                        if (this.bottomToTop != -1) {
                            break;
                        } else {
                            this.bottomToTop = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 15:
                        this.bottomToBottom = obtainStyledAttributes.getResourceId(index, this.bottomToBottom);
                        if (this.bottomToBottom != -1) {
                            break;
                        } else {
                            this.bottomToBottom = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 16:
                        this.baselineToBaseline = obtainStyledAttributes.getResourceId(index, this.baselineToBaseline);
                        if (this.baselineToBaseline != -1) {
                            break;
                        } else {
                            this.baselineToBaseline = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 17:
                        this.startToEnd = obtainStyledAttributes.getResourceId(index, this.startToEnd);
                        if (this.startToEnd != -1) {
                            break;
                        } else {
                            this.startToEnd = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 18:
                        this.startToStart = obtainStyledAttributes.getResourceId(index, this.startToStart);
                        if (this.startToStart != -1) {
                            break;
                        } else {
                            this.startToStart = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 19:
                        this.endToStart = obtainStyledAttributes.getResourceId(index, this.endToStart);
                        if (this.endToStart != -1) {
                            break;
                        } else {
                            this.endToStart = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 20:
                        this.endToEnd = obtainStyledAttributes.getResourceId(index, this.endToEnd);
                        if (this.endToEnd != -1) {
                            break;
                        } else {
                            this.endToEnd = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 21:
                        this.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneLeftMargin);
                        break;
                    case 22:
                        this.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneTopMargin);
                        break;
                    case 23:
                        this.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneRightMargin);
                        break;
                    case 24:
                        this.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBottomMargin);
                        break;
                    case 25:
                        this.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneStartMargin);
                        break;
                    case 26:
                        this.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneEndMargin);
                        break;
                    case 27:
                        this.constrainedWidth = obtainStyledAttributes.getBoolean(index, this.constrainedWidth);
                        break;
                    case 28:
                        this.constrainedHeight = obtainStyledAttributes.getBoolean(index, this.constrainedHeight);
                        break;
                    case 29:
                        this.horizontalBias = obtainStyledAttributes.getFloat(index, this.horizontalBias);
                        break;
                    case 30:
                        this.verticalBias = obtainStyledAttributes.getFloat(index, this.verticalBias);
                        break;
                    case 31:
                        this.matchConstraintDefaultWidth = obtainStyledAttributes.getInt(index, 0);
                        if (this.matchConstraintDefaultWidth != 1) {
                            break;
                        } else {
                            Log.e(str, "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                            break;
                        }
                    case 32:
                        this.matchConstraintDefaultHeight = obtainStyledAttributes.getInt(index, 0);
                        if (this.matchConstraintDefaultHeight != 1) {
                            break;
                        } else {
                            Log.e(str, "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                            break;
                        }
                    case 33:
                        try {
                            this.matchConstraintMinWidth = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMinWidth);
                            break;
                        } catch (Exception unused) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMinWidth) != -2) {
                                break;
                            } else {
                                this.matchConstraintMinWidth = -2;
                                break;
                            }
                        }
                    case 34:
                        try {
                            this.matchConstraintMaxWidth = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMaxWidth);
                            break;
                        } catch (Exception unused2) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMaxWidth) != -2) {
                                break;
                            } else {
                                this.matchConstraintMaxWidth = -2;
                                break;
                            }
                        }
                    case 35:
                        this.matchConstraintPercentWidth = Math.max(0.0f, obtainStyledAttributes.getFloat(index, this.matchConstraintPercentWidth));
                        break;
                    case 36:
                        try {
                            this.matchConstraintMinHeight = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMinHeight);
                            break;
                        } catch (Exception unused3) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMinHeight) != -2) {
                                break;
                            } else {
                                this.matchConstraintMinHeight = -2;
                                break;
                            }
                        }
                    case 37:
                        try {
                            this.matchConstraintMaxHeight = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMaxHeight);
                            break;
                        } catch (Exception unused4) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMaxHeight) != -2) {
                                break;
                            } else {
                                this.matchConstraintMaxHeight = -2;
                                break;
                            }
                        }
                    case 38:
                        this.matchConstraintPercentHeight = Math.max(0.0f, obtainStyledAttributes.getFloat(index, this.matchConstraintPercentHeight));
                        break;
                    case 44:
                        this.dimensionRatio = obtainStyledAttributes.getString(index);
                        this.dimensionRatioValue = Float.NaN;
                        this.dimensionRatioSide = -1;
                        String str2 = this.dimensionRatio;
                        if (str2 == null) {
                            break;
                        } else {
                            int length = str2.length();
                            int indexOf = this.dimensionRatio.indexOf(44);
                            if (indexOf <= 0 || indexOf >= length - 1) {
                                i = 0;
                            } else {
                                String substring = this.dimensionRatio.substring(0, indexOf);
                                if (substring.equalsIgnoreCase(ExifInterface.LONGITUDE_WEST)) {
                                    this.dimensionRatioSide = 0;
                                } else if (substring.equalsIgnoreCase("H")) {
                                    this.dimensionRatioSide = 1;
                                }
                                i = indexOf + 1;
                            }
                            int indexOf2 = this.dimensionRatio.indexOf(58);
                            if (indexOf2 >= 0 && indexOf2 < length - 1) {
                                String substring2 = this.dimensionRatio.substring(i, indexOf2);
                                String substring3 = this.dimensionRatio.substring(indexOf2 + 1);
                                if (substring2.length() > 0 && substring3.length() > 0) {
                                    try {
                                        float parseFloat = Float.parseFloat(substring2);
                                        float parseFloat2 = Float.parseFloat(substring3);
                                        if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                                            if (this.dimensionRatioSide != 1) {
                                                this.dimensionRatioValue = Math.abs(parseFloat / parseFloat2);
                                                break;
                                            } else {
                                                this.dimensionRatioValue = Math.abs(parseFloat2 / parseFloat);
                                                break;
                                            }
                                        }
                                    } catch (NumberFormatException unused5) {
                                        break;
                                    }
                                }
                            } else {
                                String substring4 = this.dimensionRatio.substring(i);
                                if (substring4.length() <= 0) {
                                    break;
                                } else {
                                    this.dimensionRatioValue = Float.parseFloat(substring4);
                                    break;
                                }
                            }
                        }
                        break;
                    case 45:
                        this.horizontalWeight = obtainStyledAttributes.getFloat(index, this.horizontalWeight);
                        break;
                    case 46:
                        this.verticalWeight = obtainStyledAttributes.getFloat(index, this.verticalWeight);
                        break;
                    case 47:
                        this.horizontalChainStyle = obtainStyledAttributes.getInt(index, 0);
                        break;
                    case 48:
                        this.verticalChainStyle = obtainStyledAttributes.getInt(index, 0);
                        break;
                    case 49:
                        this.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteX);
                        break;
                    case 50:
                        this.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteY);
                        break;
                    case 51:
                        this.constraintTag = obtainStyledAttributes.getString(index);
                        break;
                }
            }
            obtainStyledAttributes.recycle();
            validate();
        }

        public void validate() {
            this.isGuideline = false;
            this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            if (this.width == -2 && this.constrainedWidth) {
                this.horizontalDimensionFixed = false;
                if (this.matchConstraintDefaultWidth == 0) {
                    this.matchConstraintDefaultWidth = 1;
                }
            }
            if (this.height == -2 && this.constrainedHeight) {
                this.verticalDimensionFixed = false;
                if (this.matchConstraintDefaultHeight == 0) {
                    this.matchConstraintDefaultHeight = 1;
                }
            }
            if (this.width == 0 || this.width == -1) {
                this.horizontalDimensionFixed = false;
                if (this.width == 0 && this.matchConstraintDefaultWidth == 1) {
                    this.width = -2;
                    this.constrainedWidth = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
            }
            if (this.height == 0 || this.height == -1) {
                this.verticalDimensionFixed = false;
                if (this.height == 0 && this.matchConstraintDefaultHeight == 1) {
                    this.height = -2;
                    this.constrainedHeight = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
            }
            if (this.guidePercent != -1.0f || this.guideBegin != -1 || this.guideEnd != -1) {
                this.isGuideline = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                if (!(this.widget instanceof Guideline)) {
                    this.widget = new Guideline();
                }
                ((Guideline) this.widget).setOrientation(this.orientation);
            }
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x0052  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x0059  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x0060  */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x0066  */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x006c  */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x0082  */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x008a  */
        @android.annotation.TargetApi(17)
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void resolveLayoutDirection(int r7) {
            /*
                r6 = this;
                int r0 = r6.leftMargin
                int r1 = r6.rightMargin
                int r2 = android.os.Build.VERSION.SDK_INT
                r3 = 17
                if (r2 < r3) goto L_0x000d
                super.resolveLayoutDirection(r7)
            L_0x000d:
                r7 = -1
                r6.resolvedRightToLeft = r7
                r6.resolvedRightToRight = r7
                r6.resolvedLeftToLeft = r7
                r6.resolvedLeftToRight = r7
                r6.resolveGoneLeftMargin = r7
                r6.resolveGoneRightMargin = r7
                int r2 = r6.goneLeftMargin
                r6.resolveGoneLeftMargin = r2
                int r2 = r6.goneRightMargin
                r6.resolveGoneRightMargin = r2
                float r2 = r6.horizontalBias
                r6.resolvedHorizontalBias = r2
                int r2 = r6.guideBegin
                r6.resolvedGuideBegin = r2
                int r2 = r6.guideEnd
                r6.resolvedGuideEnd = r2
                float r2 = r6.guidePercent
                r6.resolvedGuidePercent = r2
                int r2 = r6.getLayoutDirection()
                r3 = 0
                r4 = 1
                if (r4 != r2) goto L_0x003c
                r2 = 1
                goto L_0x003d
            L_0x003c:
                r2 = 0
            L_0x003d:
                if (r2 == 0) goto L_0x00a0
                int r2 = r6.startToEnd
                if (r2 == r7) goto L_0x0047
                r6.resolvedRightToLeft = r2
            L_0x0045:
                r3 = 1
                goto L_0x004e
            L_0x0047:
                int r2 = r6.startToStart
                if (r2 == r7) goto L_0x004e
                r6.resolvedRightToRight = r2
                goto L_0x0045
            L_0x004e:
                int r2 = r6.endToStart
                if (r2 == r7) goto L_0x0055
                r6.resolvedLeftToRight = r2
                r3 = 1
            L_0x0055:
                int r2 = r6.endToEnd
                if (r2 == r7) goto L_0x005c
                r6.resolvedLeftToLeft = r2
                r3 = 1
            L_0x005c:
                int r2 = r6.goneStartMargin
                if (r2 == r7) goto L_0x0062
                r6.resolveGoneRightMargin = r2
            L_0x0062:
                int r2 = r6.goneEndMargin
                if (r2 == r7) goto L_0x0068
                r6.resolveGoneLeftMargin = r2
            L_0x0068:
                r2 = 1065353216(0x3f800000, float:1.0)
                if (r3 == 0) goto L_0x0072
                float r3 = r6.horizontalBias
                float r3 = r2 - r3
                r6.resolvedHorizontalBias = r3
            L_0x0072:
                boolean r3 = r6.isGuideline
                if (r3 == 0) goto L_0x00c4
                int r3 = r6.orientation
                if (r3 != r4) goto L_0x00c4
                float r3 = r6.guidePercent
                r4 = -1082130432(0xffffffffbf800000, float:-1.0)
                int r5 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
                if (r5 == 0) goto L_0x008a
                float r2 = r2 - r3
                r6.resolvedGuidePercent = r2
                r6.resolvedGuideBegin = r7
                r6.resolvedGuideEnd = r7
                goto L_0x00c4
            L_0x008a:
                int r2 = r6.guideBegin
                if (r2 == r7) goto L_0x0095
                r6.resolvedGuideEnd = r2
                r6.resolvedGuideBegin = r7
                r6.resolvedGuidePercent = r4
                goto L_0x00c4
            L_0x0095:
                int r2 = r6.guideEnd
                if (r2 == r7) goto L_0x00c4
                r6.resolvedGuideBegin = r2
                r6.resolvedGuideEnd = r7
                r6.resolvedGuidePercent = r4
                goto L_0x00c4
            L_0x00a0:
                int r2 = r6.startToEnd
                if (r2 == r7) goto L_0x00a6
                r6.resolvedLeftToRight = r2
            L_0x00a6:
                int r2 = r6.startToStart
                if (r2 == r7) goto L_0x00ac
                r6.resolvedLeftToLeft = r2
            L_0x00ac:
                int r2 = r6.endToStart
                if (r2 == r7) goto L_0x00b2
                r6.resolvedRightToLeft = r2
            L_0x00b2:
                int r2 = r6.endToEnd
                if (r2 == r7) goto L_0x00b8
                r6.resolvedRightToRight = r2
            L_0x00b8:
                int r2 = r6.goneStartMargin
                if (r2 == r7) goto L_0x00be
                r6.resolveGoneLeftMargin = r2
            L_0x00be:
                int r2 = r6.goneEndMargin
                if (r2 == r7) goto L_0x00c4
                r6.resolveGoneRightMargin = r2
            L_0x00c4:
                int r2 = r6.endToStart
                if (r2 != r7) goto L_0x010e
                int r2 = r6.endToEnd
                if (r2 != r7) goto L_0x010e
                int r2 = r6.startToStart
                if (r2 != r7) goto L_0x010e
                int r2 = r6.startToEnd
                if (r2 != r7) goto L_0x010e
                int r2 = r6.rightToLeft
                if (r2 == r7) goto L_0x00e3
                r6.resolvedRightToLeft = r2
                int r2 = r6.rightMargin
                if (r2 > 0) goto L_0x00f1
                if (r1 <= 0) goto L_0x00f1
                r6.rightMargin = r1
                goto L_0x00f1
            L_0x00e3:
                int r2 = r6.rightToRight
                if (r2 == r7) goto L_0x00f1
                r6.resolvedRightToRight = r2
                int r2 = r6.rightMargin
                if (r2 > 0) goto L_0x00f1
                if (r1 <= 0) goto L_0x00f1
                r6.rightMargin = r1
            L_0x00f1:
                int r1 = r6.leftToLeft
                if (r1 == r7) goto L_0x0100
                r6.resolvedLeftToLeft = r1
                int r7 = r6.leftMargin
                if (r7 > 0) goto L_0x010e
                if (r0 <= 0) goto L_0x010e
                r6.leftMargin = r0
                goto L_0x010e
            L_0x0100:
                int r1 = r6.leftToRight
                if (r1 == r7) goto L_0x010e
                r6.resolvedLeftToRight = r1
                int r7 = r6.leftMargin
                if (r7 > 0) goto L_0x010e
                if (r0 <= 0) goto L_0x010e
                r6.leftMargin = r0
            L_0x010e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.resolveLayoutDirection(int):void");
        }

        public String getConstraintTag() {
            return this.constraintTag;
        }
    }

    class Measurer implements androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure.Measurer {
        ConstraintLayout layout;

        public Measurer(ConstraintLayout constraintLayout) {
            this.layout = constraintLayout;
        }

        /* JADX WARNING: Removed duplicated region for block: B:100:0x0198  */
        /* JADX WARNING: Removed duplicated region for block: B:102:0x019c  */
        /* JADX WARNING: Removed duplicated region for block: B:105:0x01a5  */
        /* JADX WARNING: Removed duplicated region for block: B:106:0x01ac  */
        /* JADX WARNING: Removed duplicated region for block: B:109:0x01b1  */
        /* JADX WARNING: Removed duplicated region for block: B:112:0x01bb  */
        /* JADX WARNING: Removed duplicated region for block: B:113:0x01c2  */
        /* JADX WARNING: Removed duplicated region for block: B:116:0x01c7  */
        /* JADX WARNING: Removed duplicated region for block: B:119:0x01d1 A[ADDED_TO_REGION] */
        /* JADX WARNING: Removed duplicated region for block: B:125:0x01e9 A[ADDED_TO_REGION] */
        /* JADX WARNING: Removed duplicated region for block: B:129:0x01f3  */
        /* JADX WARNING: Removed duplicated region for block: B:130:0x01fa  */
        /* JADX WARNING: Removed duplicated region for block: B:132:0x01fe  */
        /* JADX WARNING: Removed duplicated region for block: B:135:0x0214  */
        /* JADX WARNING: Removed duplicated region for block: B:136:0x0216  */
        /* JADX WARNING: Removed duplicated region for block: B:139:0x021b  */
        /* JADX WARNING: Removed duplicated region for block: B:145:0x0229  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x00af  */
        /* JADX WARNING: Removed duplicated region for block: B:50:0x0103  */
        /* JADX WARNING: Removed duplicated region for block: B:53:0x010e  */
        /* JADX WARNING: Removed duplicated region for block: B:54:0x0110  */
        /* JADX WARNING: Removed duplicated region for block: B:57:0x0115  */
        /* JADX WARNING: Removed duplicated region for block: B:58:0x0117  */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x011c  */
        /* JADX WARNING: Removed duplicated region for block: B:67:0x0128  */
        /* JADX WARNING: Removed duplicated region for block: B:73:0x0133  */
        /* JADX WARNING: Removed duplicated region for block: B:78:0x013e  */
        /* JADX WARNING: Removed duplicated region for block: B:84:0x0159 A[ADDED_TO_REGION] */
        /* JADX WARNING: Removed duplicated region for block: B:93:0x0172  */
        /* JADX WARNING: Removed duplicated region for block: B:99:0x0191  */
        @android.annotation.SuppressLint({"WrongCall"})
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void measure(androidx.constraintlayout.solver.widgets.ConstraintWidget r18, androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure.Measure r19) {
            /*
                r17 = this;
                r0 = r17
                r1 = r18
                r2 = r19
                if (r1 != 0) goto L_0x0009
                return
            L_0x0009:
                int r3 = r18.getVisibility()
                r4 = 8
                r5 = 0
                if (r3 != r4) goto L_0x0019
                r2.measuredWidth = r5
                r2.measuredHeight = r5
                r2.measuredBaseline = r5
                return
            L_0x0019:
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = r2.horizontalBehavior
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = r2.verticalBehavior
                int r6 = r2.horizontalDimension
                int r7 = r2.verticalDimension
                androidx.constraintlayout.widget.ConstraintLayout r8 = r0.layout
                int r8 = r8.getPaddingTop()
                androidx.constraintlayout.widget.ConstraintLayout r9 = r0.layout
                int r9 = r9.getPaddingBottom()
                int r8 = r8 + r9
                androidx.constraintlayout.widget.ConstraintLayout r9 = r0.layout
                int r9 = r9.getPaddingLeft()
                androidx.constraintlayout.widget.ConstraintLayout r10 = r0.layout
                int r10 = r10.getPaddingRight()
                int r9 = r9 + r10
                int[] r10 = androidx.constraintlayout.widget.ConstraintLayout.C02141.f48xdde91696
                int r11 = r3.ordinal()
                r10 = r10[r11]
                r11 = 4
                r12 = 3
                r13 = 2
                r15 = -2
                r14 = 1
                if (r10 == r14) goto L_0x009e
                if (r10 == r13) goto L_0x0092
                if (r10 == r12) goto L_0x0081
                if (r10 == r11) goto L_0x0053
                r6 = 0
            L_0x0051:
                r9 = 0
                goto L_0x00a5
            L_0x0053:
                androidx.constraintlayout.widget.ConstraintLayout r6 = r0.layout
                int r6 = r6.mOnMeasureWidthMeasureSpec
                int r6 = android.view.ViewGroup.getChildMeasureSpec(r6, r9, r15)
                int r9 = r1.mMatchConstraintDefaultWidth
                if (r9 != r14) goto L_0x0063
                r9 = 1
                goto L_0x0064
            L_0x0063:
                r9 = 0
            L_0x0064:
                boolean r10 = r2.useDeprecated
                if (r10 == 0) goto L_0x009c
                if (r9 == 0) goto L_0x0076
                if (r9 == 0) goto L_0x009c
                int[] r9 = r1.wrapMeasure
                r9 = r9[r5]
                int r10 = r18.getWidth()
                if (r9 == r10) goto L_0x009c
            L_0x0076:
                int r6 = r18.getWidth()
                r9 = 1073741824(0x40000000, float:2.0)
                int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r6, r9)
                goto L_0x0051
            L_0x0081:
                androidx.constraintlayout.widget.ConstraintLayout r6 = r0.layout
                int r6 = r6.mOnMeasureWidthMeasureSpec
                int r10 = r18.getHorizontalMargin()
                int r9 = r9 + r10
                r10 = -1
                int r6 = android.view.ViewGroup.getChildMeasureSpec(r6, r9, r10)
                goto L_0x0051
            L_0x0092:
                androidx.constraintlayout.widget.ConstraintLayout r6 = r0.layout
                int r6 = r6.mOnMeasureWidthMeasureSpec
                int r6 = android.view.ViewGroup.getChildMeasureSpec(r6, r9, r15)
            L_0x009c:
                r9 = 1
                goto L_0x00a5
            L_0x009e:
                r9 = 1073741824(0x40000000, float:2.0)
                int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r6, r9)
                goto L_0x0051
            L_0x00a5:
                int[] r10 = androidx.constraintlayout.widget.ConstraintLayout.C02141.f48xdde91696
                int r16 = r4.ordinal()
                r10 = r10[r16]
                if (r10 == r14) goto L_0x0103
                if (r10 == r13) goto L_0x00f7
                if (r10 == r12) goto L_0x00e6
                if (r10 == r11) goto L_0x00b8
                r7 = 0
            L_0x00b6:
                r8 = 0
                goto L_0x010a
            L_0x00b8:
                androidx.constraintlayout.widget.ConstraintLayout r7 = r0.layout
                int r7 = r7.mOnMeasureHeightMeasureSpec
                int r7 = android.view.ViewGroup.getChildMeasureSpec(r7, r8, r15)
                int r8 = r1.mMatchConstraintDefaultHeight
                if (r8 != r14) goto L_0x00c8
                r8 = 1
                goto L_0x00c9
            L_0x00c8:
                r8 = 0
            L_0x00c9:
                boolean r10 = r2.useDeprecated
                if (r10 == 0) goto L_0x0101
                if (r8 == 0) goto L_0x00db
                if (r8 == 0) goto L_0x0101
                int[] r8 = r1.wrapMeasure
                r8 = r8[r14]
                int r10 = r18.getHeight()
                if (r8 == r10) goto L_0x0101
            L_0x00db:
                int r7 = r18.getHeight()
                r8 = 1073741824(0x40000000, float:2.0)
                int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r8)
                goto L_0x00b6
            L_0x00e6:
                androidx.constraintlayout.widget.ConstraintLayout r7 = r0.layout
                int r7 = r7.mOnMeasureHeightMeasureSpec
                int r10 = r18.getVerticalMargin()
                int r8 = r8 + r10
                r10 = -1
                int r7 = android.view.ViewGroup.getChildMeasureSpec(r7, r8, r10)
                goto L_0x00b6
            L_0x00f7:
                androidx.constraintlayout.widget.ConstraintLayout r7 = r0.layout
                int r7 = r7.mOnMeasureHeightMeasureSpec
                int r7 = android.view.ViewGroup.getChildMeasureSpec(r7, r8, r15)
            L_0x0101:
                r8 = 1
                goto L_0x010a
            L_0x0103:
                r8 = 1073741824(0x40000000, float:2.0)
                int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r8)
                goto L_0x00b6
            L_0x010a:
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
                if (r3 != r10) goto L_0x0110
                r10 = 1
                goto L_0x0111
            L_0x0110:
                r10 = 0
            L_0x0111:
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
                if (r4 != r11) goto L_0x0117
                r11 = 1
                goto L_0x0118
            L_0x0117:
                r11 = 0
            L_0x0118:
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
                if (r4 == r12) goto L_0x0123
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
                if (r4 != r12) goto L_0x0121
                goto L_0x0123
            L_0x0121:
                r4 = 0
                goto L_0x0124
            L_0x0123:
                r4 = 1
            L_0x0124:
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
                if (r3 == r12) goto L_0x012f
                androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
                if (r3 != r12) goto L_0x012d
                goto L_0x012f
            L_0x012d:
                r3 = 0
                goto L_0x0130
            L_0x012f:
                r3 = 1
            L_0x0130:
                r12 = 0
                if (r10 == 0) goto L_0x013b
                float r13 = r1.mDimensionRatio
                int r13 = (r13 > r12 ? 1 : (r13 == r12 ? 0 : -1))
                if (r13 <= 0) goto L_0x013b
                r13 = 1
                goto L_0x013c
            L_0x013b:
                r13 = 0
            L_0x013c:
                if (r11 == 0) goto L_0x0146
                float r15 = r1.mDimensionRatio
                int r12 = (r15 > r12 ? 1 : (r15 == r12 ? 0 : -1))
                if (r12 <= 0) goto L_0x0146
                r12 = 1
                goto L_0x0147
            L_0x0146:
                r12 = 0
            L_0x0147:
                java.lang.Object r15 = r18.getCompanionWidget()
                android.view.View r15 = (android.view.View) r15
                android.view.ViewGroup$LayoutParams r16 = r15.getLayoutParams()
                r14 = r16
                androidx.constraintlayout.widget.ConstraintLayout$LayoutParams r14 = (androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) r14
                boolean r5 = r2.useDeprecated
                if (r5 != 0) goto L_0x016e
                if (r10 == 0) goto L_0x016e
                int r5 = r1.mMatchConstraintDefaultWidth
                if (r5 != 0) goto L_0x016e
                if (r11 == 0) goto L_0x016e
                int r5 = r1.mMatchConstraintDefaultHeight
                if (r5 == 0) goto L_0x0166
                goto L_0x016e
            L_0x0166:
                r0 = 0
                r3 = 0
                r4 = -1
                r5 = 0
                r16 = 0
                goto L_0x0212
            L_0x016e:
                boolean r5 = r15 instanceof androidx.constraintlayout.widget.VirtualLayout
                if (r5 == 0) goto L_0x0180
                boolean r5 = r1 instanceof androidx.constraintlayout.solver.widgets.VirtualLayout
                if (r5 == 0) goto L_0x0180
                r5 = r1
                androidx.constraintlayout.solver.widgets.VirtualLayout r5 = (androidx.constraintlayout.solver.widgets.VirtualLayout) r5
                r10 = r15
                androidx.constraintlayout.widget.VirtualLayout r10 = (androidx.constraintlayout.widget.VirtualLayout) r10
                r10.onMeasure(r5, r6, r7)
                goto L_0x0183
            L_0x0180:
                r15.measure(r6, r7)
            L_0x0183:
                int r5 = r15.getMeasuredWidth()
                int r10 = r15.getMeasuredHeight()
                int r11 = r15.getBaseline()
                if (r9 == 0) goto L_0x0198
                int[] r9 = r1.wrapMeasure
                r16 = 0
                r9[r16] = r5
                goto L_0x019a
            L_0x0198:
                r16 = 0
            L_0x019a:
                if (r8 == 0) goto L_0x01a1
                int[] r8 = r1.wrapMeasure
                r9 = 1
                r8[r9] = r10
            L_0x01a1:
                int r8 = r1.mMatchConstraintMinWidth
                if (r8 <= 0) goto L_0x01ac
                int r8 = r1.mMatchConstraintMinWidth
                int r8 = java.lang.Math.max(r8, r5)
                goto L_0x01ad
            L_0x01ac:
                r8 = r5
            L_0x01ad:
                int r9 = r1.mMatchConstraintMaxWidth
                if (r9 <= 0) goto L_0x01b7
                int r9 = r1.mMatchConstraintMaxWidth
                int r8 = java.lang.Math.min(r9, r8)
            L_0x01b7:
                int r9 = r1.mMatchConstraintMinHeight
                if (r9 <= 0) goto L_0x01c2
                int r9 = r1.mMatchConstraintMinHeight
                int r9 = java.lang.Math.max(r9, r10)
                goto L_0x01c3
            L_0x01c2:
                r9 = r10
            L_0x01c3:
                int r0 = r1.mMatchConstraintMaxHeight
                if (r0 <= 0) goto L_0x01cd
                int r0 = r1.mMatchConstraintMaxHeight
                int r9 = java.lang.Math.min(r0, r9)
            L_0x01cd:
                r0 = 1056964608(0x3f000000, float:0.5)
                if (r13 == 0) goto L_0x01dc
                if (r4 == 0) goto L_0x01dc
                float r3 = r1.mDimensionRatio
                float r4 = (float) r9
                float r4 = r4 * r3
                float r4 = r4 + r0
                int r0 = (int) r4
                r8 = r0
                goto L_0x01e7
            L_0x01dc:
                if (r12 == 0) goto L_0x01e7
                if (r3 == 0) goto L_0x01e7
                float r3 = r1.mDimensionRatio
                float r4 = (float) r8
                float r4 = r4 / r3
                float r4 = r4 + r0
                int r0 = (int) r4
                r9 = r0
            L_0x01e7:
                if (r5 != r8) goto L_0x01f1
                if (r10 == r9) goto L_0x01ec
                goto L_0x01f1
            L_0x01ec:
                r5 = r8
                r0 = r9
                r3 = r11
            L_0x01ef:
                r4 = -1
                goto L_0x0212
            L_0x01f1:
                if (r5 == r8) goto L_0x01fa
                r0 = 1073741824(0x40000000, float:2.0)
                int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r0)
                goto L_0x01fc
            L_0x01fa:
                r0 = 1073741824(0x40000000, float:2.0)
            L_0x01fc:
                if (r10 == r9) goto L_0x0202
                int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r9, r0)
            L_0x0202:
                r15.measure(r6, r7)
                int r5 = r15.getMeasuredWidth()
                int r0 = r15.getMeasuredHeight()
                int r3 = r15.getBaseline()
                goto L_0x01ef
            L_0x0212:
                if (r3 == r4) goto L_0x0216
                r4 = 1
                goto L_0x0217
            L_0x0216:
                r4 = 0
            L_0x0217:
                int r6 = r2.horizontalDimension
                if (r5 != r6) goto L_0x0222
                int r6 = r2.verticalDimension
                if (r0 == r6) goto L_0x0220
                goto L_0x0222
            L_0x0220:
                r6 = 0
                goto L_0x0223
            L_0x0222:
                r6 = 1
            L_0x0223:
                r2.measuredNeedsSolverPass = r6
                boolean r6 = r14.needsBaseline
                if (r6 == 0) goto L_0x022a
                r4 = 1
            L_0x022a:
                if (r4 == 0) goto L_0x0238
                r6 = -1
                if (r3 == r6) goto L_0x0238
                int r1 = r18.getBaselineDistance()
                if (r1 == r3) goto L_0x0238
                r1 = 1
                r2.measuredNeedsSolverPass = r1
            L_0x0238:
                r2.measuredWidth = r5
                r2.measuredHeight = r0
                r2.measuredHasBaseline = r4
                r2.measuredBaseline = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.Measurer.measure(androidx.constraintlayout.solver.widgets.ConstraintWidget, androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure$Measure):void");
        }

        public final void didMeasures() {
            int childCount = this.layout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = this.layout.getChildAt(i);
                if (childAt instanceof Placeholder) {
                    ((Placeholder) childAt).updatePostMeasure(this.layout);
                }
            }
            int size = this.layout.mConstraintHelpers.size();
            if (size > 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    ((ConstraintHelper) this.layout.mConstraintHelpers.get(i2)).updatePostMeasure(this.layout);
                }
            }
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public void setDesignInformation(int i, Object obj, Object obj2) {
        if (i == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.mDesignIds == null) {
                this.mDesignIds = new HashMap<>();
            }
            String str = (String) obj;
            int indexOf = str.indexOf("/");
            if (indexOf != -1) {
                str = str.substring(indexOf + 1);
            }
            this.mDesignIds.put(str, Integer.valueOf(((Integer) obj2).intValue()));
        }
    }

    public Object getDesignInformation(int i, Object obj) {
        if (i == 0 && (obj instanceof String)) {
            String str = (String) obj;
            HashMap<String, Integer> hashMap = this.mDesignIds;
            if (hashMap != null && hashMap.containsKey(str)) {
                return this.mDesignIds.get(str);
            }
        }
        return null;
    }

    public ConstraintLayout(Context context) {
        super(context);
        init(null, 0, 0);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0, 0);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i, 0);
    }

    @TargetApi(21)
    public ConstraintLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet, i, i2);
    }

    public void setId(int i) {
        this.mChildrenByIds.remove(getId());
        super.setId(i);
        this.mChildrenByIds.put(getId(), this);
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        this.mLayoutWidget.setCompanionWidget(this);
        this.mLayoutWidget.setMeasurer(this.mMeasurer);
        this.mChildrenByIds.put(getId(), this);
        this.mConstraintSet = null;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C0215R.styleable.ConstraintLayout_Layout, i, i2);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = obtainStyledAttributes.getIndex(i3);
                if (index == C0215R.styleable.ConstraintLayout_Layout_android_minWidth) {
                    this.mMinWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinWidth);
                } else if (index == C0215R.styleable.ConstraintLayout_Layout_android_minHeight) {
                    this.mMinHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinHeight);
                } else if (index == C0215R.styleable.ConstraintLayout_Layout_android_maxWidth) {
                    this.mMaxWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxWidth);
                } else if (index == C0215R.styleable.ConstraintLayout_Layout_android_maxHeight) {
                    this.mMaxHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxHeight);
                } else if (index == C0215R.styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.mOptimizationLevel = obtainStyledAttributes.getInt(index, this.mOptimizationLevel);
                } else if (index == C0215R.styleable.ConstraintLayout_Layout_layoutDescription) {
                    int resourceId = obtainStyledAttributes.getResourceId(index, 0);
                    if (resourceId != 0) {
                        try {
                            parseLayoutDescription(resourceId);
                        } catch (NotFoundException unused) {
                            this.mConstraintLayoutSpec = null;
                        }
                    }
                } else if (index == C0215R.styleable.ConstraintLayout_Layout_constraintSet) {
                    int resourceId2 = obtainStyledAttributes.getResourceId(index, 0);
                    try {
                        this.mConstraintSet = new ConstraintSet();
                        this.mConstraintSet.load(getContext(), resourceId2);
                    } catch (NotFoundException unused2) {
                        this.mConstraintSet = null;
                    }
                    this.mConstraintSetId = resourceId2;
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.mLayoutWidget.setOptimizationLevel(this.mOptimizationLevel);
    }

    /* access modifiers changed from: protected */
    public void parseLayoutDescription(int i) {
        this.mConstraintLayoutSpec = new ConstraintLayoutStates(getContext(), this, i);
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (VERSION.SDK_INT < 14) {
            onViewAdded(view);
        }
    }

    public void removeView(View view) {
        super.removeView(view);
        if (VERSION.SDK_INT < 14) {
            onViewRemoved(view);
        }
    }

    public void onViewAdded(View view) {
        if (VERSION.SDK_INT >= 14) {
            super.onViewAdded(view);
        }
        ConstraintWidget viewWidget = getViewWidget(view);
        if ((view instanceof Guideline) && !(viewWidget instanceof Guideline)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.widget = new Guideline();
            layoutParams.isGuideline = USE_CONSTRAINTS_HELPER;
            ((Guideline) layoutParams.widget).setOrientation(layoutParams.orientation);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper constraintHelper = (ConstraintHelper) view;
            constraintHelper.validateParams();
            ((LayoutParams) view.getLayoutParams()).isHelper = USE_CONSTRAINTS_HELPER;
            if (!this.mConstraintHelpers.contains(constraintHelper)) {
                this.mConstraintHelpers.add(constraintHelper);
            }
        }
        this.mChildrenByIds.put(view.getId(), view);
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
    }

    public void onViewRemoved(View view) {
        if (VERSION.SDK_INT >= 14) {
            super.onViewRemoved(view);
        }
        this.mChildrenByIds.remove(view.getId());
        ConstraintWidget viewWidget = getViewWidget(view);
        this.mLayoutWidget.remove(viewWidget);
        this.mConstraintHelpers.remove(view);
        this.mVariableDimensionsWidgets.remove(viewWidget);
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
    }

    public void setMinWidth(int i) {
        if (i != this.mMinWidth) {
            this.mMinWidth = i;
            requestLayout();
        }
    }

    public void setMinHeight(int i) {
        if (i != this.mMinHeight) {
            this.mMinHeight = i;
            requestLayout();
        }
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public void setMaxWidth(int i) {
        if (i != this.mMaxWidth) {
            this.mMaxWidth = i;
            requestLayout();
        }
    }

    public void setMaxHeight(int i) {
        if (i != this.mMaxHeight) {
            this.mMaxHeight = i;
            requestLayout();
        }
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    private boolean updateHierarchy() {
        int childCount = getChildCount();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= childCount) {
                break;
            } else if (getChildAt(i).isLayoutRequested()) {
                z = USE_CONSTRAINTS_HELPER;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            this.mVariableDimensionsWidgets.clear();
            setChildrenConstraints();
        }
        return z;
    }

    private void setChildrenConstraints() {
        boolean isInEditMode = isInEditMode();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ConstraintWidget viewWidget = getViewWidget(getChildAt(i));
            if (viewWidget != null) {
                viewWidget.reset();
            }
        }
        if (isInEditMode) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                try {
                    String resourceName = getResources().getResourceName(childAt.getId());
                    setDesignInformation(0, resourceName, Integer.valueOf(childAt.getId()));
                    int indexOf = resourceName.indexOf(47);
                    if (indexOf != -1) {
                        resourceName = resourceName.substring(indexOf + 1);
                    }
                    getTargetWidget(childAt.getId()).setDebugName(resourceName);
                } catch (NotFoundException unused) {
                }
            }
        }
        if (this.mConstraintSetId != -1) {
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt2 = getChildAt(i3);
                if (childAt2.getId() == this.mConstraintSetId && (childAt2 instanceof Constraints)) {
                    this.mConstraintSet = ((Constraints) childAt2).getConstraintSet();
                }
            }
        }
        ConstraintSet constraintSet = this.mConstraintSet;
        if (constraintSet != null) {
            constraintSet.applyToInternal(this, USE_CONSTRAINTS_HELPER);
        }
        this.mLayoutWidget.removeAllChildren();
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i4 = 0; i4 < size; i4++) {
                ((ConstraintHelper) this.mConstraintHelpers.get(i4)).updatePreLayout(this);
            }
        }
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt3 = getChildAt(i5);
            if (childAt3 instanceof Placeholder) {
                ((Placeholder) childAt3).updatePreLayout(this);
            }
        }
        this.mTempMapIdToWidget.clear();
        this.mTempMapIdToWidget.put(0, this.mLayoutWidget);
        this.mTempMapIdToWidget.put(getId(), this.mLayoutWidget);
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt4 = getChildAt(i6);
            this.mTempMapIdToWidget.put(childAt4.getId(), getViewWidget(childAt4));
        }
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt5 = getChildAt(i7);
            ConstraintWidget viewWidget2 = getViewWidget(childAt5);
            if (viewWidget2 != null) {
                LayoutParams layoutParams = (LayoutParams) childAt5.getLayoutParams();
                this.mLayoutWidget.add(viewWidget2);
                applyConstraintsFromLayoutParams(isInEditMode, childAt5, viewWidget2, layoutParams, this.mTempMapIdToWidget);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void applyConstraintsFromLayoutParams(boolean z, View view, ConstraintWidget constraintWidget, LayoutParams layoutParams, SparseArray<ConstraintWidget> sparseArray) {
        int i;
        int i2;
        int i3;
        float f;
        View view2 = view;
        ConstraintWidget constraintWidget2 = constraintWidget;
        LayoutParams layoutParams2 = layoutParams;
        SparseArray<ConstraintWidget> sparseArray2 = sparseArray;
        layoutParams.validate();
        layoutParams2.helped = false;
        constraintWidget2.setVisibility(view.getVisibility());
        if (layoutParams2.isInPlaceholder) {
            constraintWidget2.setInPlaceholder(USE_CONSTRAINTS_HELPER);
            constraintWidget2.setVisibility(8);
        }
        constraintWidget2.setCompanionWidget(view2);
        if (!layoutParams2.verticalDimensionFixed || !layoutParams2.horizontalDimensionFixed) {
            this.mVariableDimensionsWidgets.add(constraintWidget2);
        }
        if (view2 instanceof ConstraintHelper) {
            ((ConstraintHelper) view2).resolveRtl(constraintWidget2, this.mLayoutWidget.isRtl());
        }
        if (layoutParams2.isGuideline) {
            Guideline guideline = (Guideline) constraintWidget2;
            int i4 = layoutParams2.resolvedGuideBegin;
            int i5 = layoutParams2.resolvedGuideEnd;
            float f2 = layoutParams2.resolvedGuidePercent;
            if (VERSION.SDK_INT < 17) {
                i4 = layoutParams2.guideBegin;
                i5 = layoutParams2.guideEnd;
                f2 = layoutParams2.guidePercent;
            }
            if (f2 != -1.0f) {
                guideline.setGuidePercent(f2);
            } else if (i4 != -1) {
                guideline.setGuideBegin(i4);
            } else if (i5 != -1) {
                guideline.setGuideEnd(i5);
            }
        } else {
            int i6 = layoutParams2.resolvedLeftToLeft;
            int i7 = layoutParams2.resolvedLeftToRight;
            int i8 = layoutParams2.resolvedRightToLeft;
            int i9 = layoutParams2.resolvedRightToRight;
            int i10 = layoutParams2.resolveGoneLeftMargin;
            int i11 = layoutParams2.resolveGoneRightMargin;
            float f3 = layoutParams2.resolvedHorizontalBias;
            if (VERSION.SDK_INT < 17) {
                i6 = layoutParams2.leftToLeft;
                i3 = layoutParams2.leftToRight;
                int i12 = layoutParams2.rightToLeft;
                i9 = layoutParams2.rightToRight;
                int i13 = layoutParams2.goneLeftMargin;
                int i14 = layoutParams2.goneRightMargin;
                f3 = layoutParams2.horizontalBias;
                if (i6 == -1 && i3 == -1) {
                    if (layoutParams2.startToStart != -1) {
                        i6 = layoutParams2.startToStart;
                    } else if (layoutParams2.startToEnd != -1) {
                        i3 = layoutParams2.startToEnd;
                    }
                }
                if (i12 == -1 && i9 == -1) {
                    if (layoutParams2.endToStart != -1) {
                        i12 = layoutParams2.endToStart;
                    } else if (layoutParams2.endToEnd != -1) {
                        i9 = layoutParams2.endToEnd;
                    }
                }
                i2 = i12;
                i = i14;
                i10 = i13;
            } else {
                i3 = i7;
                i = i11;
                i2 = i8;
            }
            float f4 = f3;
            int i15 = i9;
            float f5 = f4;
            if (layoutParams2.circleConstraint != -1) {
                ConstraintWidget constraintWidget3 = (ConstraintWidget) sparseArray2.get(layoutParams2.circleConstraint);
                if (constraintWidget3 != null) {
                    constraintWidget2.connectCircularConstraint(constraintWidget3, layoutParams2.circleAngle, layoutParams2.circleRadius);
                }
            } else {
                if (i6 != -1) {
                    ConstraintWidget constraintWidget4 = (ConstraintWidget) sparseArray2.get(i6);
                    if (constraintWidget4 != null) {
                        f = f5;
                        constraintWidget.immediateConnect(C0202Type.LEFT, constraintWidget4, C0202Type.LEFT, layoutParams2.leftMargin, i10);
                    } else {
                        f = f5;
                    }
                } else {
                    f = f5;
                    if (i3 != -1) {
                        ConstraintWidget constraintWidget5 = (ConstraintWidget) sparseArray2.get(i3);
                        if (constraintWidget5 != null) {
                            constraintWidget.immediateConnect(C0202Type.LEFT, constraintWidget5, C0202Type.RIGHT, layoutParams2.leftMargin, i10);
                        }
                    }
                }
                if (i2 != -1) {
                    ConstraintWidget constraintWidget6 = (ConstraintWidget) sparseArray2.get(i2);
                    if (constraintWidget6 != null) {
                        constraintWidget.immediateConnect(C0202Type.RIGHT, constraintWidget6, C0202Type.LEFT, layoutParams2.rightMargin, i);
                    }
                } else if (i15 != -1) {
                    ConstraintWidget constraintWidget7 = (ConstraintWidget) sparseArray2.get(i15);
                    if (constraintWidget7 != null) {
                        constraintWidget.immediateConnect(C0202Type.RIGHT, constraintWidget7, C0202Type.RIGHT, layoutParams2.rightMargin, i);
                    }
                }
                if (layoutParams2.topToTop != -1) {
                    ConstraintWidget constraintWidget8 = (ConstraintWidget) sparseArray2.get(layoutParams2.topToTop);
                    if (constraintWidget8 != null) {
                        constraintWidget.immediateConnect(C0202Type.TOP, constraintWidget8, C0202Type.TOP, layoutParams2.topMargin, layoutParams2.goneTopMargin);
                    }
                } else if (layoutParams2.topToBottom != -1) {
                    ConstraintWidget constraintWidget9 = (ConstraintWidget) sparseArray2.get(layoutParams2.topToBottom);
                    if (constraintWidget9 != null) {
                        constraintWidget.immediateConnect(C0202Type.TOP, constraintWidget9, C0202Type.BOTTOM, layoutParams2.topMargin, layoutParams2.goneTopMargin);
                    }
                }
                if (layoutParams2.bottomToTop != -1) {
                    ConstraintWidget constraintWidget10 = (ConstraintWidget) sparseArray2.get(layoutParams2.bottomToTop);
                    if (constraintWidget10 != null) {
                        constraintWidget.immediateConnect(C0202Type.BOTTOM, constraintWidget10, C0202Type.TOP, layoutParams2.bottomMargin, layoutParams2.goneBottomMargin);
                    }
                } else if (layoutParams2.bottomToBottom != -1) {
                    ConstraintWidget constraintWidget11 = (ConstraintWidget) sparseArray2.get(layoutParams2.bottomToBottom);
                    if (constraintWidget11 != null) {
                        constraintWidget.immediateConnect(C0202Type.BOTTOM, constraintWidget11, C0202Type.BOTTOM, layoutParams2.bottomMargin, layoutParams2.goneBottomMargin);
                    }
                }
                if (layoutParams2.baselineToBaseline != -1) {
                    View view3 = (View) this.mChildrenByIds.get(layoutParams2.baselineToBaseline);
                    ConstraintWidget constraintWidget12 = (ConstraintWidget) sparseArray2.get(layoutParams2.baselineToBaseline);
                    if (!(constraintWidget12 == null || view3 == null || !(view3.getLayoutParams() instanceof LayoutParams))) {
                        LayoutParams layoutParams3 = (LayoutParams) view3.getLayoutParams();
                        layoutParams2.needsBaseline = USE_CONSTRAINTS_HELPER;
                        layoutParams3.needsBaseline = USE_CONSTRAINTS_HELPER;
                        constraintWidget2.getAnchor(C0202Type.BASELINE).connect(constraintWidget12.getAnchor(C0202Type.BASELINE), 0, -1, USE_CONSTRAINTS_HELPER);
                        constraintWidget2.setHasBaseline(USE_CONSTRAINTS_HELPER);
                        layoutParams3.widget.setHasBaseline(USE_CONSTRAINTS_HELPER);
                        constraintWidget2.getAnchor(C0202Type.TOP).reset();
                        constraintWidget2.getAnchor(C0202Type.BOTTOM).reset();
                    }
                }
                float f6 = f;
                if (f6 >= 0.0f) {
                    constraintWidget2.setHorizontalBiasPercent(f6);
                }
                if (layoutParams2.verticalBias >= 0.0f) {
                    constraintWidget2.setVerticalBiasPercent(layoutParams2.verticalBias);
                }
            }
            if (z && !(layoutParams2.editorAbsoluteX == -1 && layoutParams2.editorAbsoluteY == -1)) {
                constraintWidget2.setOrigin(layoutParams2.editorAbsoluteX, layoutParams2.editorAbsoluteY);
            }
            if (layoutParams2.horizontalDimensionFixed) {
                constraintWidget2.setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
                constraintWidget2.setWidth(layoutParams2.width);
                if (layoutParams2.width == -2) {
                    constraintWidget2.setHorizontalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                }
            } else if (layoutParams2.width == -1) {
                constraintWidget2.setHorizontalDimensionBehaviour(DimensionBehaviour.MATCH_PARENT);
                constraintWidget2.getAnchor(C0202Type.LEFT).mMargin = layoutParams2.leftMargin;
                constraintWidget2.getAnchor(C0202Type.RIGHT).mMargin = layoutParams2.rightMargin;
            } else {
                constraintWidget2.setHorizontalDimensionBehaviour(DimensionBehaviour.MATCH_CONSTRAINT);
                constraintWidget2.setWidth(0);
            }
            if (layoutParams2.verticalDimensionFixed) {
                constraintWidget2.setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
                constraintWidget2.setHeight(layoutParams2.height);
                if (layoutParams2.height == -2) {
                    constraintWidget2.setVerticalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                }
            } else if (layoutParams2.height == -1) {
                constraintWidget2.setVerticalDimensionBehaviour(DimensionBehaviour.MATCH_PARENT);
                constraintWidget2.getAnchor(C0202Type.TOP).mMargin = layoutParams2.topMargin;
                constraintWidget2.getAnchor(C0202Type.BOTTOM).mMargin = layoutParams2.bottomMargin;
            } else {
                constraintWidget2.setVerticalDimensionBehaviour(DimensionBehaviour.MATCH_CONSTRAINT);
                constraintWidget2.setHeight(0);
            }
            if (layoutParams2.dimensionRatio != null) {
                constraintWidget2.setDimensionRatio(layoutParams2.dimensionRatio);
            }
            constraintWidget2.setHorizontalWeight(layoutParams2.horizontalWeight);
            constraintWidget2.setVerticalWeight(layoutParams2.verticalWeight);
            constraintWidget2.setHorizontalChainStyle(layoutParams2.horizontalChainStyle);
            constraintWidget2.setVerticalChainStyle(layoutParams2.verticalChainStyle);
            constraintWidget2.setHorizontalMatchStyle(layoutParams2.matchConstraintDefaultWidth, layoutParams2.matchConstraintMinWidth, layoutParams2.matchConstraintMaxWidth, layoutParams2.matchConstraintPercentWidth);
            constraintWidget2.setVerticalMatchStyle(layoutParams2.matchConstraintDefaultHeight, layoutParams2.matchConstraintMinHeight, layoutParams2.matchConstraintMaxHeight, layoutParams2.matchConstraintPercentHeight);
        }
    }

    private final ConstraintWidget getTargetWidget(int i) {
        ConstraintWidget constraintWidget;
        if (i == 0) {
            return this.mLayoutWidget;
        }
        View view = (View) this.mChildrenByIds.get(i);
        if (view == null) {
            view = findViewById(i);
            if (!(view == null || view == this || view.getParent() != this)) {
                onViewAdded(view);
            }
        }
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            constraintWidget = null;
        } else {
            constraintWidget = ((LayoutParams) view.getLayoutParams()).widget;
        }
        return constraintWidget;
    }

    public final ConstraintWidget getViewWidget(View view) {
        ConstraintWidget constraintWidget;
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            constraintWidget = null;
        } else {
            constraintWidget = ((LayoutParams) view.getLayoutParams()).widget;
        }
        return constraintWidget;
    }

    public void fillMetrics(Metrics metrics) {
        this.mMetrics = metrics;
        this.mLayoutWidget.fillMetrics(metrics);
    }

    /* access modifiers changed from: protected */
    public void resolveSystem(ConstraintWidgetContainer constraintWidgetContainer, int i, int i2, int i3) {
        int i4;
        int i5;
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i3);
        int size2 = MeasureSpec.getSize(i3);
        int paddingTop = getPaddingTop();
        int paddingBottom = paddingTop + getPaddingBottom();
        int i6 = 0;
        if (VERSION.SDK_INT >= 17) {
            i6 = getPaddingStart();
            i4 = getPaddingEnd() + i6;
        } else {
            i4 = 0;
        }
        if (i4 == 0) {
            int paddingLeft = getPaddingLeft();
            i5 = paddingLeft;
            i4 = getPaddingRight() + paddingLeft;
        } else {
            i5 = i6;
        }
        int i7 = size - i4;
        int i8 = size2 - paddingBottom;
        setSelfDimensionBehaviour(constraintWidgetContainer, mode, i7, mode2, i8);
        constraintWidgetContainer.measure(i, mode, i7, mode2, i8, this.mLastMeasureWidth, this.mLastMeasureHeight, i5, paddingTop);
    }

    /* access modifiers changed from: protected */
    public void resolveMeasuredDimension(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        int paddingLeft = i3 + getPaddingLeft() + getPaddingRight();
        int paddingTop = i4 + getPaddingTop() + getPaddingBottom();
        if (VERSION.SDK_INT >= 11) {
            int resolveSizeAndState = resolveSizeAndState(paddingTop, i2, 0) & 16777215;
            int min = Math.min(this.mMaxWidth, resolveSizeAndState(paddingLeft, i, 0) & 16777215);
            int min2 = Math.min(this.mMaxHeight, resolveSizeAndState);
            if (z) {
                min |= 16777216;
            }
            if (z2) {
                min2 |= 16777216;
            }
            setMeasuredDimension(min, min2);
            this.mLastMeasureWidth = min;
            this.mLastMeasureHeight = min2;
            return;
        }
        setMeasuredDimension(paddingLeft, paddingTop);
        this.mLastMeasureWidth = paddingLeft;
        this.mLastMeasureHeight = paddingTop;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        this.mOnMeasureWidthMeasureSpec = i;
        this.mOnMeasureHeightMeasureSpec = i2;
        if (VERSION.SDK_INT >= 17) {
            int i3 = getContext().getApplicationInfo().flags & 4194304;
            boolean z = USE_CONSTRAINTS_HELPER;
            if (!(i3 != 0 ? USE_CONSTRAINTS_HELPER : false) || 1 != getLayoutDirection()) {
                z = false;
            }
            this.mLayoutWidget.setRtl(z);
        }
        if (this.mDirtyHierarchy) {
            this.mDirtyHierarchy = false;
            if (updateHierarchy()) {
                this.mLayoutWidget.updateHierarchy();
            }
        }
        resolveSystem(this.mLayoutWidget, this.mOptimizationLevel, i, i2);
        resolveMeasuredDimension(i, i2, this.mLayoutWidget.getWidth(), this.mLayoutWidget.getHeight(), this.mLayoutWidget.isWidthMeasuredTooSmall(), this.mLayoutWidget.isHeightMeasuredTooSmall());
    }

    /* access modifiers changed from: protected */
    public void setSelfDimensionBehaviour(ConstraintWidgetContainer constraintWidgetContainer, int i, int i2, int i3, int i4) {
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        DimensionBehaviour dimensionBehaviour2 = DimensionBehaviour.FIXED;
        if (i != Integer.MIN_VALUE) {
            if (i == 0) {
                dimensionBehaviour = DimensionBehaviour.WRAP_CONTENT;
            } else if (i == 1073741824) {
                i2 = Math.min(this.mMaxWidth, i2);
            }
            i2 = 0;
        } else {
            dimensionBehaviour = DimensionBehaviour.WRAP_CONTENT;
        }
        if (i3 != Integer.MIN_VALUE) {
            if (i3 == 0) {
                dimensionBehaviour2 = DimensionBehaviour.WRAP_CONTENT;
            } else if (i3 == 1073741824) {
                i4 = Math.min(this.mMaxHeight, i4);
            }
            i4 = 0;
        } else {
            dimensionBehaviour2 = DimensionBehaviour.WRAP_CONTENT;
        }
        if (!(i2 == constraintWidgetContainer.getWidth() && i4 == constraintWidgetContainer.getHeight())) {
            constraintWidgetContainer.invalidateMeasures();
        }
        constraintWidgetContainer.setX(0);
        constraintWidgetContainer.setY(0);
        constraintWidgetContainer.setMaxWidth(this.mMaxWidth);
        constraintWidgetContainer.setMaxHeight(this.mMaxHeight);
        constraintWidgetContainer.setMinWidth(0);
        constraintWidgetContainer.setMinHeight(0);
        constraintWidgetContainer.setHorizontalDimensionBehaviour(dimensionBehaviour);
        constraintWidgetContainer.setWidth(i2);
        constraintWidgetContainer.setVerticalDimensionBehaviour(dimensionBehaviour2);
        constraintWidgetContainer.setHeight(i4);
        constraintWidgetContainer.setMinWidth(this.mMinWidth - paddingLeft);
        constraintWidgetContainer.setMinHeight(this.mMinHeight - paddingTop);
    }

    public void setState(int i, int i2, int i3) {
        ConstraintLayoutStates constraintLayoutStates = this.mConstraintLayoutSpec;
        if (constraintLayoutStates != null) {
            constraintLayoutStates.updateConstraints(i, (float) i2, (float) i3);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            ConstraintWidget constraintWidget = layoutParams.widget;
            if ((childAt.getVisibility() != 8 || layoutParams.isGuideline || layoutParams.isHelper || layoutParams.isVirtualGroup || isInEditMode) && !layoutParams.isInPlaceholder) {
                int x = constraintWidget.getX();
                int y = constraintWidget.getY();
                int width = constraintWidget.getWidth() + x;
                int height = constraintWidget.getHeight() + y;
                childAt.layout(x, y, width, height);
                if (childAt instanceof Placeholder) {
                    View content = ((Placeholder) childAt).getContent();
                    if (content != null) {
                        content.setVisibility(0);
                        content.layout(x, y, width, height);
                    }
                }
            }
        }
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i6 = 0; i6 < size; i6++) {
                ((ConstraintHelper) this.mConstraintHelpers.get(i6)).updatePostLayout(this);
            }
        }
    }

    public void setOptimizationLevel(int i) {
        this.mOptimizationLevel = i;
        this.mLayoutWidget.setOptimizationLevel(i);
    }

    public int getOptimizationLevel() {
        return this.mLayoutWidget.getOptimizationLevel();
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    /* access modifiers changed from: protected */
    public android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void setConstraintSet(ConstraintSet constraintSet) {
        this.mConstraintSet = constraintSet;
    }

    public View getViewById(int i) {
        return (View) this.mChildrenByIds.get(i);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            int childCount = getChildCount();
            float width = (float) getWidth();
            float height = (float) getHeight();
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                if (childAt.getVisibility() != 8) {
                    Object tag = childAt.getTag();
                    if (tag != null && (tag instanceof String)) {
                        String[] split = ((String) tag).split(",");
                        if (split.length == 4) {
                            int parseInt = Integer.parseInt(split[0]);
                            int parseInt2 = Integer.parseInt(split[1]);
                            int i2 = (int) ((((float) parseInt) / 1080.0f) * width);
                            int i3 = (int) ((((float) parseInt2) / 1920.0f) * height);
                            int parseInt3 = (int) ((((float) Integer.parseInt(split[2])) / 1080.0f) * width);
                            int parseInt4 = (int) ((((float) Integer.parseInt(split[3])) / 1920.0f) * height);
                            Paint paint = new Paint();
                            paint.setColor(SupportMenu.CATEGORY_MASK);
                            float f = (float) i2;
                            float f2 = (float) (i2 + parseInt3);
                            Canvas canvas2 = canvas;
                            float f3 = (float) i3;
                            float f4 = f;
                            float f5 = f;
                            float f6 = f3;
                            Paint paint2 = paint;
                            float f7 = f2;
                            Paint paint3 = paint2;
                            canvas2.drawLine(f4, f6, f7, f3, paint3);
                            float f8 = (float) (i3 + parseInt4);
                            float f9 = f2;
                            float f10 = f8;
                            canvas2.drawLine(f9, f6, f7, f10, paint3);
                            float f11 = f8;
                            float f12 = f5;
                            canvas2.drawLine(f9, f11, f12, f10, paint3);
                            float f13 = f5;
                            canvas2.drawLine(f13, f11, f12, f3, paint3);
                            Paint paint4 = paint2;
                            paint4.setColor(-16711936);
                            Paint paint5 = paint4;
                            float f14 = f2;
                            Paint paint6 = paint5;
                            canvas2.drawLine(f13, f3, f14, f8, paint6);
                            canvas2.drawLine(f13, f8, f14, f3, paint6);
                        }
                    }
                }
            }
        }
    }

    public void setOnConstraintsChanged(ConstraintsChangedListener constraintsChangedListener) {
        this.mConstraintsChangedListener = constraintsChangedListener;
        ConstraintLayoutStates constraintLayoutStates = this.mConstraintLayoutSpec;
        if (constraintLayoutStates != null) {
            constraintLayoutStates.setOnConstraintsChanged(constraintsChangedListener);
        }
    }

    public void loadLayoutDescription(int i) {
        if (i != 0) {
            try {
                this.mConstraintLayoutSpec = new ConstraintLayoutStates(getContext(), this, i);
            } catch (NotFoundException unused) {
                this.mConstraintLayoutSpec = null;
            }
        } else {
            this.mConstraintLayoutSpec = null;
        }
    }

    public void requestLayout() {
        super.requestLayout();
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
    }
}
