package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure.Measure;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure.Measurer;

public class VirtualLayout extends HelperWidget {
    protected Measure mMeasure = new Measure();
    private int mMeasuredHeight = 0;
    private int mMeasuredWidth = 0;
    private boolean mNeedsCallFromSolver = false;
    private int mPaddingBottom = 0;
    private int mPaddingLeft = 0;
    private int mPaddingRight = 0;
    private int mPaddingTop = 0;

    public void measure(int i, int i2, int i3, int i4) {
    }

    public void setPadding(int i) {
        this.mPaddingLeft = i;
        this.mPaddingTop = i;
        this.mPaddingRight = i;
        this.mPaddingBottom = i;
    }

    public void setPaddingLeft(int i) {
        this.mPaddingLeft = i;
    }

    public void setPaddingTop(int i) {
        this.mPaddingTop = i;
    }

    public void setPaddingRight(int i) {
        this.mPaddingRight = i;
    }

    public void setPaddingBottom(int i) {
        this.mPaddingBottom = i;
    }

    public int getPaddingTop() {
        return this.mPaddingTop;
    }

    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public int getPaddingLeft() {
        return this.mPaddingLeft;
    }

    public int getPaddingRight() {
        return this.mPaddingRight;
    }

    /* access modifiers changed from: protected */
    public void needsCallbackFromSolver(boolean z) {
        this.mNeedsCallFromSolver = z;
    }

    public boolean needSolverPass() {
        return this.mNeedsCallFromSolver;
    }

    public void updateConstraints(ConstraintWidgetContainer constraintWidgetContainer) {
        captureWidgets();
    }

    public void captureWidgets() {
        for (int i = 0; i < this.mWidgetsCount; i++) {
            ConstraintWidget constraintWidget = this.mWidgets[i];
            if (constraintWidget != null) {
                constraintWidget.setInVirtualLayout(true);
            }
        }
    }

    public int getMeasuredWidth() {
        return this.mMeasuredWidth;
    }

    public int getMeasuredHeight() {
        return this.mMeasuredHeight;
    }

    public void setMeasure(int i, int i2) {
        this.mMeasuredWidth = i;
        this.mMeasuredHeight = i2;
    }

    /* access modifiers changed from: protected */
    public boolean measureChildren() {
        Measurer measurer = this.mParent != null ? ((ConstraintWidgetContainer) this.mParent).getMeasurer() : null;
        if (measurer == null) {
            return false;
        }
        int i = 0;
        while (true) {
            boolean z = true;
            if (i >= this.mWidgetsCount) {
                return true;
            }
            ConstraintWidget constraintWidget = this.mWidgets[i];
            if (constraintWidget != null && !(constraintWidget instanceof Guideline)) {
                DimensionBehaviour dimensionBehaviour = constraintWidget.getDimensionBehaviour(0);
                DimensionBehaviour dimensionBehaviour2 = constraintWidget.getDimensionBehaviour(1);
                if (dimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.mMatchConstraintDefaultWidth == 1 || dimensionBehaviour2 != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.mMatchConstraintDefaultHeight == 1) {
                    z = false;
                }
                if (!z) {
                    if (dimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                        dimensionBehaviour = DimensionBehaviour.WRAP_CONTENT;
                    }
                    if (dimensionBehaviour2 == DimensionBehaviour.MATCH_CONSTRAINT) {
                        dimensionBehaviour2 = DimensionBehaviour.WRAP_CONTENT;
                    }
                    Measure measure = this.mMeasure;
                    measure.horizontalBehavior = dimensionBehaviour;
                    measure.verticalBehavior = dimensionBehaviour2;
                    measure.horizontalDimension = constraintWidget.getWidth();
                    this.mMeasure.verticalDimension = constraintWidget.getHeight();
                    measurer.measure(constraintWidget, this.mMeasure);
                    constraintWidget.setWidth(this.mMeasure.measuredWidth);
                    constraintWidget.setHeight(this.mMeasure.measuredHeight);
                    constraintWidget.setBaselineDistance(this.mMeasure.measuredBaseline);
                }
            }
            i++;
        }
    }
}
