package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.constraintlayout.solver.widgets.Helper;
import androidx.constraintlayout.solver.widgets.Optimizer;
import androidx.constraintlayout.solver.widgets.VirtualLayout;
import java.util.ArrayList;
import java.util.Iterator;

public class BasicMeasure {
    public static final int AT_MOST = Integer.MIN_VALUE;
    private static final boolean DEBUG = false;
    public static final int EXACTLY = 1073741824;
    public static final int FIXED = -3;
    public static final int MATCH_PARENT = -1;
    private static final int MODE_SHIFT = 30;
    public static final int UNSPECIFIED = 0;
    public static final int WRAP_CONTENT = -2;
    private ConstraintWidgetContainer constraintWidgetContainer;
    private Measure mMeasure = new Measure();
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<>();

    public static class Measure {
        public DimensionBehaviour horizontalBehavior;
        public int horizontalDimension;
        public int measuredBaseline;
        public boolean measuredHasBaseline;
        public int measuredHeight;
        public boolean measuredNeedsSolverPass;
        public int measuredWidth;
        public boolean useDeprecated;
        public DimensionBehaviour verticalBehavior;
        public int verticalDimension;
    }

    public enum MeasureType {
    }

    public interface Measurer {
        void didMeasures();

        void measure(ConstraintWidget constraintWidget, Measure measure);
    }

    public void updateHierarchy(ConstraintWidgetContainer constraintWidgetContainer2) {
        this.mVariableDimensionsWidgets.clear();
        int size = constraintWidgetContainer2.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) constraintWidgetContainer2.mChildren.get(i);
            if (constraintWidget.getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_PARENT || constraintWidget.getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_PARENT) {
                this.mVariableDimensionsWidgets.add(constraintWidget);
            }
        }
        constraintWidgetContainer2.invalidateGraph();
    }

    public BasicMeasure(ConstraintWidgetContainer constraintWidgetContainer2) {
        this.constraintWidgetContainer = constraintWidgetContainer2;
    }

    private void solveLinearSystem(String str) {
        this.constraintWidgetContainer.layout();
    }

    private void measureChildren(ConstraintWidgetContainer constraintWidgetContainer2) {
        int size = constraintWidgetContainer2.mChildren.size();
        Measurer measurer = constraintWidgetContainer2.getMeasurer();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) constraintWidgetContainer2.mChildren.get(i);
            if (!(constraintWidget instanceof Guideline) && (!constraintWidget.horizontalRun.dimension.resolved || !constraintWidget.verticalRun.dimension.resolved)) {
                DimensionBehaviour dimensionBehaviour = constraintWidget.getDimensionBehaviour(0);
                boolean z = true;
                DimensionBehaviour dimensionBehaviour2 = constraintWidget.getDimensionBehaviour(1);
                if (dimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.mMatchConstraintDefaultWidth == 1 || dimensionBehaviour2 != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.mMatchConstraintDefaultHeight == 1) {
                    z = false;
                }
                if (!z) {
                    measure(measurer, constraintWidget, false);
                }
            }
        }
        measurer.didMeasures();
    }

    public void solverMeasure(ConstraintWidgetContainer constraintWidgetContainer2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        int i10;
        boolean z;
        boolean z2;
        boolean z3;
        Measurer measurer;
        String str;
        int i11;
        int i12;
        int i13;
        boolean z4;
        ConstraintWidgetContainer constraintWidgetContainer3 = constraintWidgetContainer2;
        int i14 = i;
        int i15 = i4;
        int i16 = i6;
        Measurer measurer2 = constraintWidgetContainer2.getMeasurer();
        int size = constraintWidgetContainer3.mChildren.size();
        int width = constraintWidgetContainer2.getWidth();
        int height = constraintWidgetContainer2.getHeight();
        boolean enabled = Optimizer.enabled(i14, 128);
        boolean z5 = enabled || Optimizer.enabled(i14, 64);
        if (z5) {
            int i17 = 0;
            while (true) {
                if (i17 >= size) {
                    break;
                }
                ConstraintWidget constraintWidget = (ConstraintWidget) constraintWidgetContainer3.mChildren.get(i17);
                boolean z6 = (constraintWidget.getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) && (constraintWidget.getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) && constraintWidget.getDimensionRatio() > 0.0f;
                if ((!constraintWidget.isInHorizontalChain() || !z6) && ((!constraintWidget.isInVerticalChain() || !z6) && !(constraintWidget instanceof VirtualLayout))) {
                    i17++;
                }
            }
            z5 = false;
        }
        if (z5 && LinearSystem.sMetrics != null) {
            Metrics metrics = LinearSystem.sMetrics;
            metrics.measures++;
        }
        if (z5 && (i15 == 1073741824 && i16 == 1073741824)) {
            int min = Math.min(constraintWidgetContainer2.getMaxWidth(), i5);
            int min2 = Math.min(constraintWidgetContainer2.getMaxHeight(), i7);
            if (i15 == 1073741824 && constraintWidgetContainer2.getWidth() != min) {
                constraintWidgetContainer3.setWidth(min);
                constraintWidgetContainer2.invalidateGraph();
            }
            if (i16 == 1073741824 && constraintWidgetContainer2.getHeight() != min2) {
                constraintWidgetContainer3.setHeight(min2);
                constraintWidgetContainer2.invalidateGraph();
            }
            if (i15 == 1073741824 && i16 == 1073741824) {
                z = constraintWidgetContainer3.directMeasure(enabled);
                i10 = 2;
            } else {
                z = constraintWidgetContainer3.directMeasureSetup(enabled);
                if (i15 == 1073741824) {
                    z &= constraintWidgetContainer3.directMeasureWithOrientation(enabled, 0);
                    i10 = 1;
                } else {
                    i10 = 0;
                }
                if (i16 == 1073741824) {
                    z &= constraintWidgetContainer3.directMeasureWithOrientation(enabled, 1);
                    i10++;
                }
            }
            if (z) {
                constraintWidgetContainer3.updateFromRuns(i15 == 1073741824, i16 == 1073741824);
            }
        } else {
            constraintWidgetContainer3.horizontalRun.clear();
            constraintWidgetContainer3.verticalRun.clear();
            Iterator it = constraintWidgetContainer2.getChildren().iterator();
            while (it.hasNext()) {
                ConstraintWidget constraintWidget2 = (ConstraintWidget) it.next();
                constraintWidget2.horizontalRun.clear();
                constraintWidget2.verticalRun.clear();
            }
            z = false;
            i10 = 0;
        }
        if (!z || i10 != 2) {
            if (size > 0) {
                measureChildren(constraintWidgetContainer2);
            }
            int optimizationLevel = constraintWidgetContainer2.getOptimizationLevel();
            int size2 = this.mVariableDimensionsWidgets.size();
            if (size > 0) {
                solveLinearSystem("First pass");
            }
            if (size2 > 0) {
                boolean z7 = constraintWidgetContainer2.getHorizontalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT;
                boolean z8 = constraintWidgetContainer2.getVerticalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT;
                int max = Math.max(constraintWidgetContainer2.getWidth(), this.constraintWidgetContainer.getMinWidth());
                int max2 = Math.max(constraintWidgetContainer2.getHeight(), this.constraintWidgetContainer.getMinHeight());
                int i18 = 0;
                boolean z9 = false;
                while (i18 < size2) {
                    ConstraintWidget constraintWidget3 = (ConstraintWidget) this.mVariableDimensionsWidgets.get(i18);
                    if (!(constraintWidget3 instanceof VirtualLayout)) {
                        i13 = optimizationLevel;
                    } else {
                        int width2 = constraintWidget3.getWidth();
                        int height2 = constraintWidget3.getHeight();
                        i13 = optimizationLevel;
                        boolean measure = z9 | measure(measurer2, constraintWidget3, true);
                        int width3 = constraintWidget3.getWidth();
                        boolean z10 = measure;
                        int height3 = constraintWidget3.getHeight();
                        if (width3 != width2) {
                            constraintWidget3.setWidth(width3);
                            if (z7 && constraintWidget3.getRight() > max) {
                                max = Math.max(max, constraintWidget3.getRight() + constraintWidget3.getAnchor(C0202Type.RIGHT).getMargin());
                            }
                            z4 = true;
                        } else {
                            z4 = z10;
                        }
                        if (height3 != height2) {
                            constraintWidget3.setHeight(height3);
                            if (z8 && constraintWidget3.getBottom() > max2) {
                                max2 = Math.max(max2, constraintWidget3.getBottom() + constraintWidget3.getAnchor(C0202Type.BOTTOM).getMargin());
                            }
                            z4 = true;
                        }
                        z9 = z4 | ((VirtualLayout) constraintWidget3).needSolverPass();
                    }
                    i18++;
                    optimizationLevel = i13;
                }
                int i19 = optimizationLevel;
                String str2 = "2nd pass";
                if (z9) {
                    constraintWidgetContainer3.setWidth(width);
                    constraintWidgetContainer3.setHeight(height);
                    solveLinearSystem(str2);
                    z9 = false;
                }
                int i20 = 0;
                while (i20 < 2) {
                    boolean z11 = z2;
                    int i21 = 0;
                    while (i21 < size2) {
                        ConstraintWidget constraintWidget4 = (ConstraintWidget) this.mVariableDimensionsWidgets.get(i21);
                        if ((!(constraintWidget4 instanceof Helper) || (constraintWidget4 instanceof VirtualLayout)) && !(constraintWidget4 instanceof Guideline)) {
                            i11 = size2;
                            if (constraintWidget4.getVisibility() != 8 && ((!constraintWidget4.horizontalRun.dimension.resolved || !constraintWidget4.verticalRun.dimension.resolved) && !(constraintWidget4 instanceof VirtualLayout))) {
                                int width4 = constraintWidget4.getWidth();
                                int height4 = constraintWidget4.getHeight();
                                str = str2;
                                int baselineDistance = constraintWidget4.getBaselineDistance();
                                i12 = i20;
                                z11 |= measure(measurer2, constraintWidget4, true);
                                int width5 = constraintWidget4.getWidth();
                                measurer = measurer2;
                                int height5 = constraintWidget4.getHeight();
                                if (width5 != width4) {
                                    constraintWidget4.setWidth(width5);
                                    if (z7 && constraintWidget4.getRight() > max) {
                                        max = Math.max(max, constraintWidget4.getRight() + constraintWidget4.getAnchor(C0202Type.RIGHT).getMargin());
                                    }
                                    z11 = true;
                                }
                                if (height5 != height4) {
                                    constraintWidget4.setHeight(height5);
                                    if (z8 && constraintWidget4.getBottom() > max2) {
                                        max2 = Math.max(max2, constraintWidget4.getBottom() + constraintWidget4.getAnchor(C0202Type.BOTTOM).getMargin());
                                    }
                                    z11 = true;
                                }
                                if (constraintWidget4.hasBaseline() && baselineDistance != constraintWidget4.getBaselineDistance()) {
                                    z11 = true;
                                }
                                i21++;
                                size2 = i11;
                                str2 = str;
                                measurer2 = measurer;
                                i20 = i12;
                            }
                        } else {
                            i11 = size2;
                        }
                        str = str2;
                        measurer = measurer2;
                        i12 = i20;
                        i21++;
                        size2 = i11;
                        str2 = str;
                        measurer2 = measurer;
                        i20 = i12;
                    }
                    String str3 = str2;
                    int i22 = size2;
                    Measurer measurer3 = measurer2;
                    int i23 = i20;
                    if (z11) {
                        constraintWidgetContainer3.setWidth(width);
                        constraintWidgetContainer3.setHeight(height);
                        solveLinearSystem("intermediate pass");
                        z2 = false;
                    } else {
                        z2 = z11;
                    }
                    i20 = i23 + 1;
                    size2 = i22;
                    str2 = str3;
                    measurer2 = measurer3;
                }
                String str4 = str2;
                if (z2) {
                    constraintWidgetContainer3.setWidth(width);
                    constraintWidgetContainer3.setHeight(height);
                    solveLinearSystem(str4);
                    if (constraintWidgetContainer2.getWidth() < max) {
                        constraintWidgetContainer3.setWidth(max);
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (constraintWidgetContainer2.getHeight() < max2) {
                        constraintWidgetContainer3.setHeight(max2);
                        z3 = true;
                    }
                    if (z3) {
                        solveLinearSystem("3rd pass");
                    }
                }
                optimizationLevel = i19;
            }
            constraintWidgetContainer3.setOptimizationLevel(optimizationLevel);
        }
    }

    private boolean measure(Measurer measurer, ConstraintWidget constraintWidget, boolean z) {
        this.mMeasure.horizontalBehavior = constraintWidget.getHorizontalDimensionBehaviour();
        this.mMeasure.verticalBehavior = constraintWidget.getVerticalDimensionBehaviour();
        this.mMeasure.horizontalDimension = constraintWidget.getWidth();
        this.mMeasure.verticalDimension = constraintWidget.getHeight();
        Measure measure = this.mMeasure;
        measure.measuredNeedsSolverPass = false;
        measure.useDeprecated = z;
        measurer.measure(constraintWidget, measure);
        constraintWidget.setWidth(this.mMeasure.measuredWidth);
        constraintWidget.setHeight(this.mMeasure.measuredHeight);
        constraintWidget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        constraintWidget.setBaselineDistance(this.mMeasure.measuredBaseline);
        Measure measure2 = this.mMeasure;
        measure2.useDeprecated = false;
        return measure2.measuredNeedsSolverPass;
    }
}
