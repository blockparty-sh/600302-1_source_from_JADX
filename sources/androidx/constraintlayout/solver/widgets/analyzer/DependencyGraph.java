package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.Barrier;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.constraintlayout.solver.widgets.HelperWidget;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure.Measure;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure.Measurer;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class DependencyGraph {
    private static final boolean USE_GROUPS = true;
    private ConstraintWidgetContainer container;
    private ConstraintWidgetContainer mContainer;
    ArrayList<RunGroup> mGroups = new ArrayList<>();
    private Measure mMeasure = new Measure();
    private Measurer mMeasurer = null;
    private boolean mNeedBuildGraph = USE_GROUPS;
    private boolean mNeedRedoMeasures = USE_GROUPS;
    private ArrayList<WidgetRun> mRuns = new ArrayList<>();
    private ArrayList<RunGroup> runGroups = new ArrayList<>();

    public DependencyGraph(ConstraintWidgetContainer constraintWidgetContainer) {
        this.container = constraintWidgetContainer;
        this.mContainer = constraintWidgetContainer;
    }

    public void setMeasurer(Measurer measurer) {
        this.mMeasurer = measurer;
    }

    private int computeWrap(ConstraintWidgetContainer constraintWidgetContainer, int i) {
        int size = this.mGroups.size();
        long j = 0;
        for (int i2 = 0; i2 < size; i2++) {
            j = Math.max(j, ((RunGroup) this.mGroups.get(i2)).computeWrapSize(constraintWidgetContainer, i));
        }
        return (int) j;
    }

    public void defineTerminalWidgets(DimensionBehaviour dimensionBehaviour, DimensionBehaviour dimensionBehaviour2) {
        if (this.mNeedBuildGraph) {
            buildGraph();
            Iterator it = this.container.mChildren.iterator();
            boolean z = false;
            while (it.hasNext()) {
                ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
                constraintWidget.isTerminalWidget[0] = USE_GROUPS;
                constraintWidget.isTerminalWidget[1] = USE_GROUPS;
                if (constraintWidget instanceof Barrier) {
                    z = USE_GROUPS;
                }
            }
            if (!z) {
                Iterator it2 = this.mGroups.iterator();
                while (it2.hasNext()) {
                    ((RunGroup) it2.next()).defineTerminalWidgets(dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT ? USE_GROUPS : false, dimensionBehaviour2 == DimensionBehaviour.WRAP_CONTENT ? USE_GROUPS : false);
                }
            }
        }
    }

    public boolean directMeasure(boolean z) {
        boolean z2;
        boolean z3 = z & USE_GROUPS;
        boolean z4 = false;
        if (this.mNeedBuildGraph || this.mNeedRedoMeasures) {
            Iterator it = this.container.mChildren.iterator();
            while (it.hasNext()) {
                ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
                constraintWidget.measured = false;
                constraintWidget.horizontalRun.dimension.resolved = false;
                constraintWidget.verticalRun.dimension.resolved = false;
                constraintWidget.horizontalRun.resolved = false;
                constraintWidget.verticalRun.resolved = false;
                constraintWidget.horizontalRun.reset();
                constraintWidget.verticalRun.reset();
            }
            ConstraintWidgetContainer constraintWidgetContainer = this.container;
            constraintWidgetContainer.measured = false;
            constraintWidgetContainer.horizontalRun.dimension.resolved = false;
            this.container.verticalRun.dimension.resolved = false;
            this.container.horizontalRun.resolved = false;
            this.container.verticalRun.resolved = false;
            this.container.horizontalRun.reset();
            this.container.verticalRun.reset();
            this.mNeedRedoMeasures = false;
        }
        if (basicMeasureWidgets(this.mContainer)) {
            return false;
        }
        this.container.setX(0);
        this.container.setY(0);
        DimensionBehaviour dimensionBehaviour = this.container.getDimensionBehaviour(0);
        DimensionBehaviour dimensionBehaviour2 = this.container.getDimensionBehaviour(1);
        if (this.mNeedBuildGraph) {
            buildGraph();
        }
        int x = this.container.getX();
        int y = this.container.getY();
        this.container.horizontalRun.start.resolve(x);
        this.container.verticalRun.start.resolve(y);
        measureWidgets();
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour2 == DimensionBehaviour.WRAP_CONTENT) {
            if (z3) {
                Iterator it2 = this.mRuns.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (!((WidgetRun) it2.next()).supportsWrapComputation()) {
                            z3 = false;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (z3 && dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
                this.container.setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer2 = this.container;
                constraintWidgetContainer2.setWidth(computeWrap(constraintWidgetContainer2, 0));
                this.container.horizontalRun.dimension.resolve(this.container.getWidth());
            }
            if (z3 && dimensionBehaviour2 == DimensionBehaviour.WRAP_CONTENT) {
                this.container.setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer3 = this.container;
                constraintWidgetContainer3.setHeight(computeWrap(constraintWidgetContainer3, 1));
                this.container.verticalRun.dimension.resolve(this.container.getHeight());
            }
        }
        if (this.container.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED || this.container.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_PARENT) {
            int width = this.container.getWidth() + x;
            this.container.horizontalRun.end.resolve(width);
            this.container.horizontalRun.dimension.resolve(width - x);
            measureWidgets();
            if (this.container.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED || this.container.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_PARENT) {
                int height = this.container.getHeight() + y;
                this.container.verticalRun.end.resolve(height);
                this.container.verticalRun.dimension.resolve(height - y);
            }
            measureWidgets();
            z2 = USE_GROUPS;
        } else {
            z2 = false;
        }
        Iterator it3 = this.mRuns.iterator();
        while (it3.hasNext()) {
            WidgetRun widgetRun = (WidgetRun) it3.next();
            if (widgetRun.widget != this.container || widgetRun.resolved) {
                widgetRun.applyToWidget();
            }
        }
        Iterator it4 = this.mRuns.iterator();
        while (true) {
            if (!it4.hasNext()) {
                z4 = USE_GROUPS;
                break;
            }
            WidgetRun widgetRun2 = (WidgetRun) it4.next();
            if ((z2 || widgetRun2.widget != this.container) && (!widgetRun2.start.resolved || ((!widgetRun2.end.resolved && !(widgetRun2 instanceof GuidelineReference)) || (!widgetRun2.dimension.resolved && !(widgetRun2 instanceof ChainRun) && !(widgetRun2 instanceof GuidelineReference))))) {
                break;
            }
        }
        this.container.setHorizontalDimensionBehaviour(dimensionBehaviour);
        this.container.setVerticalDimensionBehaviour(dimensionBehaviour2);
        return z4;
    }

    public boolean directMeasureSetup(boolean z) {
        if (this.mNeedBuildGraph) {
            Iterator it = this.container.mChildren.iterator();
            while (it.hasNext()) {
                ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
                constraintWidget.measured = false;
                constraintWidget.horizontalRun.dimension.resolved = false;
                constraintWidget.horizontalRun.resolved = false;
                constraintWidget.horizontalRun.reset();
                constraintWidget.verticalRun.dimension.resolved = false;
                constraintWidget.verticalRun.resolved = false;
                constraintWidget.verticalRun.reset();
            }
            ConstraintWidgetContainer constraintWidgetContainer = this.container;
            constraintWidgetContainer.measured = false;
            constraintWidgetContainer.horizontalRun.dimension.resolved = false;
            this.container.horizontalRun.resolved = false;
            this.container.horizontalRun.reset();
            this.container.verticalRun.dimension.resolved = false;
            this.container.verticalRun.resolved = false;
            this.container.verticalRun.reset();
            buildGraph();
        }
        if (basicMeasureWidgets(this.mContainer)) {
            return false;
        }
        this.container.setX(0);
        this.container.setY(0);
        this.container.horizontalRun.start.resolve(0);
        this.container.verticalRun.start.resolve(0);
        return USE_GROUPS;
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0154 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean directMeasureWithOrientation(boolean r10, int r11) {
        /*
            r9 = this;
            r0 = 1
            r10 = r10 & r0
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r1 = r9.container
            r2 = 0
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = r1.getDimensionBehaviour(r2)
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r3 = r9.container
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = r3.getDimensionBehaviour(r0)
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r4 = r9.container
            int r4 = r4.getX()
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r5 = r9.container
            int r5 = r5.getY()
            if (r10 == 0) goto L_0x008f
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r1 == r6) goto L_0x0025
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r3 != r6) goto L_0x008f
        L_0x0025:
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.analyzer.WidgetRun> r6 = r9.mRuns
            java.util.Iterator r6 = r6.iterator()
        L_0x002b:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0042
            java.lang.Object r7 = r6.next()
            androidx.constraintlayout.solver.widgets.analyzer.WidgetRun r7 = (androidx.constraintlayout.solver.widgets.analyzer.WidgetRun) r7
            int r8 = r7.orientation
            if (r8 != r11) goto L_0x002b
            boolean r7 = r7.supportsWrapComputation()
            if (r7 != 0) goto L_0x002b
            r10 = 0
        L_0x0042:
            if (r11 != 0) goto L_0x006a
            if (r10 == 0) goto L_0x008f
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r1 != r10) goto L_0x008f
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r10.setHorizontalDimensionBehaviour(r6)
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            int r6 = r9.computeWrap(r10, r2)
            r10.setWidth(r6)
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r10 = r10.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r10 = r10.dimension
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r6 = r9.container
            int r6 = r6.getWidth()
            r10.resolve(r6)
            goto L_0x008f
        L_0x006a:
            if (r10 == 0) goto L_0x008f
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r3 != r10) goto L_0x008f
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r10.setVerticalDimensionBehaviour(r6)
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            int r6 = r9.computeWrap(r10, r0)
            r10.setHeight(r6)
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r10 = r10.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r10 = r10.dimension
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r6 = r9.container
            int r6 = r6.getHeight()
            r10.resolve(r6)
        L_0x008f:
            if (r11 != 0) goto L_0x00c0
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r10 = r10.mListDimensionBehaviors
            r10 = r10[r2]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r10 == r5) goto L_0x00a5
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r10 = r10.mListDimensionBehaviors
            r10 = r10[r2]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
            if (r10 != r5) goto L_0x00d5
        L_0x00a5:
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            int r10 = r10.getWidth()
            int r10 = r10 + r4
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r5 = r9.container
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r5 = r5.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r5 = r5.end
            r5.resolve(r10)
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r5 = r9.container
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r5 = r5.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r5 = r5.dimension
            int r10 = r10 - r4
            r5.resolve(r10)
            goto L_0x00f1
        L_0x00c0:
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r10 = r10.mListDimensionBehaviors
            r10 = r10[r0]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r10 == r4) goto L_0x00d7
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r10 = r10.mListDimensionBehaviors
            r10 = r10[r0]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
            if (r10 != r4) goto L_0x00d5
            goto L_0x00d7
        L_0x00d5:
            r10 = 0
            goto L_0x00f2
        L_0x00d7:
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            int r10 = r10.getHeight()
            int r10 = r10 + r5
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r4 = r9.container
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r4 = r4.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r4 = r4.end
            r4.resolve(r10)
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r4 = r9.container
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r4 = r4.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r4 = r4.dimension
            int r10 = r10 - r5
            r4.resolve(r10)
        L_0x00f1:
            r10 = 1
        L_0x00f2:
            r9.measureWidgets()
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.analyzer.WidgetRun> r4 = r9.mRuns
            java.util.Iterator r4 = r4.iterator()
        L_0x00fb:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x011b
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.solver.widgets.analyzer.WidgetRun r5 = (androidx.constraintlayout.solver.widgets.analyzer.WidgetRun) r5
            int r6 = r5.orientation
            if (r6 == r11) goto L_0x010c
            goto L_0x00fb
        L_0x010c:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r6 = r5.widget
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r7 = r9.container
            if (r6 != r7) goto L_0x0117
            boolean r6 = r5.resolved
            if (r6 != 0) goto L_0x0117
            goto L_0x00fb
        L_0x0117:
            r5.applyToWidget()
            goto L_0x00fb
        L_0x011b:
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.analyzer.WidgetRun> r4 = r9.mRuns
            java.util.Iterator r4 = r4.iterator()
        L_0x0121:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0154
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.solver.widgets.analyzer.WidgetRun r5 = (androidx.constraintlayout.solver.widgets.analyzer.WidgetRun) r5
            int r6 = r5.orientation
            if (r6 == r11) goto L_0x0132
            goto L_0x0121
        L_0x0132:
            if (r10 != 0) goto L_0x013b
            androidx.constraintlayout.solver.widgets.ConstraintWidget r6 = r5.widget
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r7 = r9.container
            if (r6 != r7) goto L_0x013b
            goto L_0x0121
        L_0x013b:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r6 = r5.start
            boolean r6 = r6.resolved
            if (r6 != 0) goto L_0x0142
            goto L_0x0155
        L_0x0142:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r6 = r5.end
            boolean r6 = r6.resolved
            if (r6 != 0) goto L_0x0149
            goto L_0x0155
        L_0x0149:
            boolean r6 = r5 instanceof androidx.constraintlayout.solver.widgets.analyzer.ChainRun
            if (r6 != 0) goto L_0x0121
            androidx.constraintlayout.solver.widgets.analyzer.DimensionDependency r5 = r5.dimension
            boolean r5 = r5.resolved
            if (r5 != 0) goto L_0x0121
            goto L_0x0155
        L_0x0154:
            r2 = 1
        L_0x0155:
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            r10.setHorizontalDimensionBehaviour(r1)
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r10 = r9.container
            r10.setVerticalDimensionBehaviour(r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.DependencyGraph.directMeasureWithOrientation(boolean, int):boolean");
    }

    private void measure(ConstraintWidget constraintWidget, DimensionBehaviour dimensionBehaviour, int i, DimensionBehaviour dimensionBehaviour2, int i2) {
        Measure measure = this.mMeasure;
        measure.horizontalBehavior = dimensionBehaviour;
        measure.verticalBehavior = dimensionBehaviour2;
        measure.horizontalDimension = i;
        measure.verticalDimension = i2;
        this.mMeasurer.measure(constraintWidget, measure);
        constraintWidget.setWidth(this.mMeasure.measuredWidth);
        constraintWidget.setHeight(this.mMeasure.measuredHeight);
        constraintWidget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        constraintWidget.setBaselineDistance(this.mMeasure.measuredBaseline);
    }

    private boolean basicMeasureWidgets(ConstraintWidgetContainer constraintWidgetContainer) {
        int i;
        int i2;
        Iterator it = constraintWidgetContainer.mChildren.iterator();
        while (it.hasNext()) {
            ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
            DimensionBehaviour dimensionBehaviour = constraintWidget.mListDimensionBehaviors[0];
            DimensionBehaviour dimensionBehaviour2 = constraintWidget.mListDimensionBehaviors[1];
            if (constraintWidget.getVisibility() == 8) {
                constraintWidget.measured = USE_GROUPS;
            } else {
                if (constraintWidget.mMatchConstraintPercentWidth < 1.0f && dimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                    constraintWidget.mMatchConstraintDefaultWidth = 2;
                }
                if (constraintWidget.mMatchConstraintPercentHeight < 1.0f && dimensionBehaviour2 == DimensionBehaviour.MATCH_CONSTRAINT) {
                    constraintWidget.mMatchConstraintDefaultHeight = 2;
                }
                if (constraintWidget.getDimensionRatio() > 0.0f) {
                    if (dimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT && (dimensionBehaviour2 == DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour2 == DimensionBehaviour.FIXED)) {
                        constraintWidget.mMatchConstraintDefaultWidth = 3;
                    } else if (dimensionBehaviour2 == DimensionBehaviour.MATCH_CONSTRAINT && (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour == DimensionBehaviour.FIXED)) {
                        constraintWidget.mMatchConstraintDefaultHeight = 3;
                    } else if (dimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT && dimensionBehaviour2 == DimensionBehaviour.MATCH_CONSTRAINT) {
                        if (constraintWidget.mMatchConstraintDefaultWidth == 0) {
                            constraintWidget.mMatchConstraintDefaultWidth = 3;
                        }
                        if (constraintWidget.mMatchConstraintDefaultHeight == 0) {
                            constraintWidget.mMatchConstraintDefaultHeight = 3;
                        }
                    }
                }
                if (dimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultWidth == 1 && (constraintWidget.mLeft.mTarget == null || constraintWidget.mRight.mTarget == null)) {
                    dimensionBehaviour = DimensionBehaviour.WRAP_CONTENT;
                }
                DimensionBehaviour dimensionBehaviour3 = dimensionBehaviour;
                DimensionBehaviour dimensionBehaviour4 = (dimensionBehaviour2 == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultHeight == 1 && (constraintWidget.mTop.mTarget == null || constraintWidget.mBottom.mTarget == null)) ? DimensionBehaviour.WRAP_CONTENT : dimensionBehaviour2;
                constraintWidget.horizontalRun.dimensionBehavior = dimensionBehaviour3;
                constraintWidget.horizontalRun.matchConstraintsType = constraintWidget.mMatchConstraintDefaultWidth;
                constraintWidget.verticalRun.dimensionBehavior = dimensionBehaviour4;
                constraintWidget.verticalRun.matchConstraintsType = constraintWidget.mMatchConstraintDefaultHeight;
                if ((dimensionBehaviour3 == DimensionBehaviour.MATCH_PARENT || dimensionBehaviour3 == DimensionBehaviour.FIXED || dimensionBehaviour3 == DimensionBehaviour.WRAP_CONTENT) && (dimensionBehaviour4 == DimensionBehaviour.MATCH_PARENT || dimensionBehaviour4 == DimensionBehaviour.FIXED || dimensionBehaviour4 == DimensionBehaviour.WRAP_CONTENT)) {
                    int width = constraintWidget.getWidth();
                    if (dimensionBehaviour3 == DimensionBehaviour.MATCH_PARENT) {
                        i = (constraintWidgetContainer.getWidth() - constraintWidget.mLeft.mMargin) - constraintWidget.mRight.mMargin;
                        dimensionBehaviour3 = DimensionBehaviour.FIXED;
                    } else {
                        i = width;
                    }
                    int height = constraintWidget.getHeight();
                    if (dimensionBehaviour4 == DimensionBehaviour.MATCH_PARENT) {
                        i2 = (constraintWidgetContainer.getHeight() - constraintWidget.mTop.mMargin) - constraintWidget.mBottom.mMargin;
                        dimensionBehaviour4 = DimensionBehaviour.FIXED;
                    } else {
                        i2 = height;
                    }
                    measure(constraintWidget, dimensionBehaviour3, i, dimensionBehaviour4, i2);
                    constraintWidget.horizontalRun.dimension.resolve(constraintWidget.getWidth());
                    constraintWidget.verticalRun.dimension.resolve(constraintWidget.getHeight());
                    constraintWidget.measured = USE_GROUPS;
                } else {
                    if (dimensionBehaviour3 == DimensionBehaviour.MATCH_CONSTRAINT && (dimensionBehaviour4 == DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour4 == DimensionBehaviour.FIXED)) {
                        if (constraintWidget.mMatchConstraintDefaultWidth == 3) {
                            if (dimensionBehaviour4 == DimensionBehaviour.WRAP_CONTENT) {
                                measure(constraintWidget, DimensionBehaviour.WRAP_CONTENT, 0, DimensionBehaviour.WRAP_CONTENT, 0);
                            }
                            int height2 = constraintWidget.getHeight();
                            measure(constraintWidget, DimensionBehaviour.FIXED, (int) ((((float) height2) * constraintWidget.mDimensionRatio) + 0.5f), DimensionBehaviour.FIXED, height2);
                            constraintWidget.horizontalRun.dimension.resolve(constraintWidget.getWidth());
                            constraintWidget.verticalRun.dimension.resolve(constraintWidget.getHeight());
                            constraintWidget.measured = USE_GROUPS;
                        } else if (constraintWidget.mMatchConstraintDefaultWidth == 1) {
                            measure(constraintWidget, DimensionBehaviour.WRAP_CONTENT, 0, dimensionBehaviour4, 0);
                            constraintWidget.horizontalRun.dimension.wrapValue = constraintWidget.getWidth();
                        } else if (constraintWidget.mMatchConstraintDefaultWidth == 2) {
                            if (constraintWidgetContainer.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED || constraintWidgetContainer.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_PARENT) {
                                measure(constraintWidget, DimensionBehaviour.FIXED, (int) ((constraintWidget.mMatchConstraintPercentWidth * ((float) constraintWidgetContainer.getWidth())) + 0.5f), dimensionBehaviour4, constraintWidget.getHeight());
                                constraintWidget.horizontalRun.dimension.resolve(constraintWidget.getWidth());
                                constraintWidget.verticalRun.dimension.resolve(constraintWidget.getHeight());
                                constraintWidget.measured = USE_GROUPS;
                            }
                        } else if (constraintWidget.mListAnchors[0].mTarget == null || constraintWidget.mListAnchors[1].mTarget == null) {
                            measure(constraintWidget, DimensionBehaviour.WRAP_CONTENT, 0, dimensionBehaviour4, 0);
                            constraintWidget.horizontalRun.dimension.resolve(constraintWidget.getWidth());
                            constraintWidget.verticalRun.dimension.resolve(constraintWidget.getHeight());
                            constraintWidget.measured = USE_GROUPS;
                        }
                    }
                    if (dimensionBehaviour4 == DimensionBehaviour.MATCH_CONSTRAINT && (dimensionBehaviour3 == DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour3 == DimensionBehaviour.FIXED)) {
                        if (constraintWidget.mMatchConstraintDefaultHeight == 3) {
                            if (dimensionBehaviour3 == DimensionBehaviour.WRAP_CONTENT) {
                                measure(constraintWidget, DimensionBehaviour.WRAP_CONTENT, 0, DimensionBehaviour.WRAP_CONTENT, 0);
                            }
                            int width2 = constraintWidget.getWidth();
                            float f = constraintWidget.mDimensionRatio;
                            if (constraintWidget.getDimensionRatioSide() == -1) {
                                f = 1.0f / f;
                            }
                            measure(constraintWidget, DimensionBehaviour.FIXED, width2, DimensionBehaviour.FIXED, (int) ((((float) width2) * f) + 0.5f));
                            constraintWidget.horizontalRun.dimension.resolve(constraintWidget.getWidth());
                            constraintWidget.verticalRun.dimension.resolve(constraintWidget.getHeight());
                            constraintWidget.measured = USE_GROUPS;
                        } else if (constraintWidget.mMatchConstraintDefaultHeight == 1) {
                            measure(constraintWidget, dimensionBehaviour3, 0, DimensionBehaviour.WRAP_CONTENT, 0);
                            constraintWidget.verticalRun.dimension.wrapValue = constraintWidget.getHeight();
                        } else if (constraintWidget.mMatchConstraintDefaultHeight == 2) {
                            if (constraintWidgetContainer.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED || constraintWidgetContainer.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_PARENT) {
                                ConstraintWidget constraintWidget2 = constraintWidget;
                                measure(constraintWidget2, dimensionBehaviour3, constraintWidget.getWidth(), DimensionBehaviour.FIXED, (int) ((constraintWidget.mMatchConstraintPercentHeight * ((float) constraintWidgetContainer.getHeight())) + 0.5f));
                                constraintWidget.horizontalRun.dimension.resolve(constraintWidget.getWidth());
                                constraintWidget.verticalRun.dimension.resolve(constraintWidget.getHeight());
                                constraintWidget.measured = USE_GROUPS;
                            }
                        } else if (constraintWidget.mListAnchors[2].mTarget == null || constraintWidget.mListAnchors[3].mTarget == null) {
                            measure(constraintWidget, DimensionBehaviour.WRAP_CONTENT, 0, dimensionBehaviour4, 0);
                            constraintWidget.horizontalRun.dimension.resolve(constraintWidget.getWidth());
                            constraintWidget.verticalRun.dimension.resolve(constraintWidget.getHeight());
                            constraintWidget.measured = USE_GROUPS;
                        }
                    }
                    if (dimensionBehaviour3 == DimensionBehaviour.MATCH_CONSTRAINT && dimensionBehaviour4 == DimensionBehaviour.MATCH_CONSTRAINT) {
                        if (constraintWidget.mMatchConstraintDefaultWidth == 1 || constraintWidget.mMatchConstraintDefaultHeight == 1) {
                            measure(constraintWidget, DimensionBehaviour.WRAP_CONTENT, 0, DimensionBehaviour.WRAP_CONTENT, 0);
                            constraintWidget.horizontalRun.dimension.wrapValue = constraintWidget.getWidth();
                            constraintWidget.verticalRun.dimension.wrapValue = constraintWidget.getHeight();
                        } else if (constraintWidget.mMatchConstraintDefaultHeight == 2 && constraintWidget.mMatchConstraintDefaultWidth == 2) {
                            if ((constraintWidgetContainer.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED || constraintWidgetContainer.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED) && (constraintWidgetContainer.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED || constraintWidgetContainer.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED)) {
                                int height3 = (int) ((constraintWidget.mMatchConstraintPercentHeight * ((float) constraintWidgetContainer.getHeight())) + 0.5f);
                                ConstraintWidget constraintWidget3 = constraintWidget;
                                measure(constraintWidget3, DimensionBehaviour.FIXED, (int) ((constraintWidget.mMatchConstraintPercentWidth * ((float) constraintWidgetContainer.getWidth())) + 0.5f), DimensionBehaviour.FIXED, height3);
                                constraintWidget.horizontalRun.dimension.resolve(constraintWidget.getWidth());
                                constraintWidget.verticalRun.dimension.resolve(constraintWidget.getHeight());
                                constraintWidget.measured = USE_GROUPS;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void measureWidgets() {
        Iterator it = this.container.mChildren.iterator();
        while (it.hasNext()) {
            ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
            if (!constraintWidget.measured) {
                boolean z = false;
                DimensionBehaviour dimensionBehaviour = constraintWidget.mListDimensionBehaviors[0];
                DimensionBehaviour dimensionBehaviour2 = constraintWidget.mListDimensionBehaviors[1];
                int i = constraintWidget.mMatchConstraintDefaultWidth;
                int i2 = constraintWidget.mMatchConstraintDefaultHeight;
                boolean z2 = (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT || (dimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT && i == 1)) ? USE_GROUPS : false;
                if (dimensionBehaviour2 == DimensionBehaviour.WRAP_CONTENT || (dimensionBehaviour2 == DimensionBehaviour.MATCH_CONSTRAINT && i2 == 1)) {
                    z = USE_GROUPS;
                }
                boolean z3 = constraintWidget.horizontalRun.dimension.resolved;
                boolean z4 = constraintWidget.verticalRun.dimension.resolved;
                if (z3 && z4) {
                    measure(constraintWidget, DimensionBehaviour.FIXED, constraintWidget.horizontalRun.dimension.value, DimensionBehaviour.FIXED, constraintWidget.verticalRun.dimension.value);
                    constraintWidget.measured = USE_GROUPS;
                } else if (z3 && z) {
                    measure(constraintWidget, DimensionBehaviour.FIXED, constraintWidget.horizontalRun.dimension.value, DimensionBehaviour.WRAP_CONTENT, constraintWidget.verticalRun.dimension.value);
                    if (dimensionBehaviour2 == DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.verticalRun.dimension.wrapValue = constraintWidget.getHeight();
                    } else {
                        constraintWidget.verticalRun.dimension.resolve(constraintWidget.getHeight());
                        constraintWidget.measured = USE_GROUPS;
                    }
                } else if (z4 && z2) {
                    measure(constraintWidget, DimensionBehaviour.WRAP_CONTENT, constraintWidget.horizontalRun.dimension.value, DimensionBehaviour.FIXED, constraintWidget.verticalRun.dimension.value);
                    if (dimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.horizontalRun.dimension.wrapValue = constraintWidget.getWidth();
                    } else {
                        constraintWidget.horizontalRun.dimension.resolve(constraintWidget.getWidth());
                        constraintWidget.measured = USE_GROUPS;
                    }
                }
                if (constraintWidget.measured && constraintWidget.verticalRun.baselineDimension != null) {
                    constraintWidget.verticalRun.baselineDimension.resolve(constraintWidget.getBaselineDistance());
                }
            }
        }
    }

    public void invalidateGraph() {
        this.mNeedBuildGraph = USE_GROUPS;
    }

    public void invalidateMeasures() {
        this.mNeedRedoMeasures = USE_GROUPS;
    }

    public void buildGraph() {
        buildGraph(this.mRuns);
        this.mGroups.clear();
        RunGroup.index = 0;
        findGroup(this.container.horizontalRun, 0, this.mGroups);
        findGroup(this.container.verticalRun, 1, this.mGroups);
        this.mNeedBuildGraph = false;
    }

    public void buildGraph(ArrayList<WidgetRun> arrayList) {
        arrayList.clear();
        this.mContainer.horizontalRun.clear();
        this.mContainer.verticalRun.clear();
        arrayList.add(this.mContainer.horizontalRun);
        arrayList.add(this.mContainer.verticalRun);
        Iterator it = this.mContainer.mChildren.iterator();
        HashSet hashSet = null;
        while (it.hasNext()) {
            ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
            if (constraintWidget instanceof Guideline) {
                arrayList.add(new GuidelineReference(constraintWidget));
            } else {
                if (constraintWidget.isInHorizontalChain()) {
                    if (constraintWidget.horizontalChainRun == null) {
                        constraintWidget.horizontalChainRun = new ChainRun(constraintWidget, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.horizontalChainRun);
                } else {
                    arrayList.add(constraintWidget.horizontalRun);
                }
                if (constraintWidget.isInVerticalChain()) {
                    if (constraintWidget.verticalChainRun == null) {
                        constraintWidget.verticalChainRun = new ChainRun(constraintWidget, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.verticalChainRun);
                } else {
                    arrayList.add(constraintWidget.verticalRun);
                }
                if (constraintWidget instanceof HelperWidget) {
                    arrayList.add(new HelperReferences(constraintWidget));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ((WidgetRun) it2.next()).clear();
        }
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            WidgetRun widgetRun = (WidgetRun) it3.next();
            if (widgetRun.widget != this.mContainer) {
                widgetRun.apply();
            }
        }
    }

    private void displayGraph() {
        Iterator it = this.mRuns.iterator();
        String str = "digraph {\n";
        while (it.hasNext()) {
            str = generateDisplayGraph((WidgetRun) it.next(), str);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("\n}\n");
        String sb2 = sb.toString();
        PrintStream printStream = System.out;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("content:<<\n");
        sb3.append(sb2);
        sb3.append("\n>>");
        printStream.println(sb3.toString());
    }

    private void applyGroup(DependencyNode dependencyNode, int i, int i2, DependencyNode dependencyNode2, ArrayList<RunGroup> arrayList, RunGroup runGroup) {
        WidgetRun widgetRun = dependencyNode.run;
        if (widgetRun.runGroup == null && widgetRun != this.container.horizontalRun && widgetRun != this.container.verticalRun) {
            if (runGroup == null) {
                runGroup = new RunGroup(widgetRun, i2);
                arrayList.add(runGroup);
            }
            widgetRun.runGroup = runGroup;
            runGroup.add(widgetRun);
            for (Dependency dependency : widgetRun.start.dependencies) {
                if (dependency instanceof DependencyNode) {
                    applyGroup((DependencyNode) dependency, i, 0, dependencyNode2, arrayList, runGroup);
                }
            }
            for (Dependency dependency2 : widgetRun.end.dependencies) {
                if (dependency2 instanceof DependencyNode) {
                    applyGroup((DependencyNode) dependency2, i, 1, dependencyNode2, arrayList, runGroup);
                }
            }
            if (i == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).baseline.dependencies) {
                    if (dependency3 instanceof DependencyNode) {
                        applyGroup((DependencyNode) dependency3, i, 2, dependencyNode2, arrayList, runGroup);
                    }
                }
            }
            for (DependencyNode dependencyNode3 : widgetRun.start.targets) {
                if (dependencyNode3 == dependencyNode2) {
                    runGroup.dual = USE_GROUPS;
                }
                applyGroup(dependencyNode3, i, 0, dependencyNode2, arrayList, runGroup);
            }
            for (DependencyNode dependencyNode4 : widgetRun.end.targets) {
                if (dependencyNode4 == dependencyNode2) {
                    runGroup.dual = USE_GROUPS;
                }
                applyGroup(dependencyNode4, i, 1, dependencyNode2, arrayList, runGroup);
            }
            if (i == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                for (DependencyNode applyGroup : ((VerticalWidgetRun) widgetRun).baseline.targets) {
                    applyGroup(applyGroup, i, 2, dependencyNode2, arrayList, runGroup);
                }
            }
        }
    }

    private void findGroup(WidgetRun widgetRun, int i, ArrayList<RunGroup> arrayList) {
        for (Dependency dependency : widgetRun.start.dependencies) {
            if (dependency instanceof DependencyNode) {
                applyGroup((DependencyNode) dependency, i, 0, widgetRun.end, arrayList, null);
            } else if (dependency instanceof WidgetRun) {
                applyGroup(((WidgetRun) dependency).start, i, 0, widgetRun.end, arrayList, null);
            }
        }
        for (Dependency dependency2 : widgetRun.end.dependencies) {
            if (dependency2 instanceof DependencyNode) {
                applyGroup((DependencyNode) dependency2, i, 1, widgetRun.start, arrayList, null);
            } else if (dependency2 instanceof WidgetRun) {
                applyGroup(((WidgetRun) dependency2).end, i, 1, widgetRun.start, arrayList, null);
            }
        }
        if (i == 1) {
            for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).baseline.dependencies) {
                if (dependency3 instanceof DependencyNode) {
                    applyGroup((DependencyNode) dependency3, i, 2, null, arrayList, null);
                }
            }
        }
    }

    private String generateDisplayNode(DependencyNode dependencyNode, boolean z, String str) {
        for (DependencyNode dependencyNode2 : dependencyNode.targets) {
            StringBuilder sb = new StringBuilder();
            String str2 = "\n";
            sb.append(str2);
            sb.append(dependencyNode.name());
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb2);
            sb3.append(" -> ");
            sb3.append(dependencyNode2.name());
            String sb4 = sb3.toString();
            if (dependencyNode.margin > 0 || z || (dependencyNode.run instanceof HelperReferences)) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(sb4);
                sb5.append("[");
                String sb6 = sb5.toString();
                if (dependencyNode.margin > 0) {
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append(sb6);
                    sb7.append("label=\"");
                    sb7.append(dependencyNode.margin);
                    sb7.append("\"");
                    sb6 = sb7.toString();
                    if (z) {
                        StringBuilder sb8 = new StringBuilder();
                        sb8.append(sb6);
                        sb8.append(",");
                        sb6 = sb8.toString();
                    }
                }
                if (z) {
                    StringBuilder sb9 = new StringBuilder();
                    sb9.append(sb6);
                    sb9.append(" style=dashed ");
                    sb6 = sb9.toString();
                }
                if (dependencyNode.run instanceof HelperReferences) {
                    StringBuilder sb10 = new StringBuilder();
                    sb10.append(sb6);
                    sb10.append(" style=bold,color=gray ");
                    sb6 = sb10.toString();
                }
                StringBuilder sb11 = new StringBuilder();
                sb11.append(sb6);
                sb11.append("]");
                sb4 = sb11.toString();
            }
            StringBuilder sb12 = new StringBuilder();
            sb12.append(sb4);
            sb12.append(str2);
            String sb13 = sb12.toString();
            StringBuilder sb14 = new StringBuilder();
            sb14.append(str);
            sb14.append(sb13);
            str = sb14.toString();
        }
        return str;
    }

    private String nodeDefinition(WidgetRun widgetRun) {
        DimensionBehaviour dimensionBehaviour;
        String str;
        String str2;
        String str3;
        String str4;
        boolean z = widgetRun instanceof VerticalWidgetRun;
        boolean z2 = z ? USE_GROUPS : false;
        String debugName = widgetRun.widget.getDebugName();
        if (!z2) {
            dimensionBehaviour = widgetRun.widget.getHorizontalDimensionBehaviour();
        } else {
            dimensionBehaviour = widgetRun.widget.getVerticalDimensionBehaviour();
        }
        RunGroup runGroup = widgetRun.runGroup;
        if (!z2) {
            StringBuilder sb = new StringBuilder();
            sb.append(debugName);
            sb.append("_HORIZONTAL");
            str = sb.toString();
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(debugName);
            sb2.append("_VERTICAL");
            str = sb2.toString();
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str);
        sb3.append(" [shape=none, label=<");
        String sb4 = sb3.toString();
        StringBuilder sb5 = new StringBuilder();
        sb5.append(sb4);
        sb5.append("<TABLE BORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"2\">");
        String sb6 = sb5.toString();
        StringBuilder sb7 = new StringBuilder();
        sb7.append(sb6);
        sb7.append("  <TR>");
        String sb8 = sb7.toString();
        String str5 = " BGCOLOR=\"green\"";
        String str6 = "    <TD ";
        if (!z2) {
            StringBuilder sb9 = new StringBuilder();
            sb9.append(sb8);
            sb9.append(str6);
            String sb10 = sb9.toString();
            if (widgetRun.start.resolved) {
                StringBuilder sb11 = new StringBuilder();
                sb11.append(sb10);
                sb11.append(str5);
                sb10 = sb11.toString();
            }
            StringBuilder sb12 = new StringBuilder();
            sb12.append(sb10);
            sb12.append(" PORT=\"LEFT\" BORDER=\"1\">L</TD>");
            str2 = sb12.toString();
        } else {
            StringBuilder sb13 = new StringBuilder();
            sb13.append(sb8);
            sb13.append(str6);
            String sb14 = sb13.toString();
            if (widgetRun.start.resolved) {
                StringBuilder sb15 = new StringBuilder();
                sb15.append(sb14);
                sb15.append(str5);
                sb14 = sb15.toString();
            }
            StringBuilder sb16 = new StringBuilder();
            sb16.append(sb14);
            sb16.append(" PORT=\"TOP\" BORDER=\"1\">T</TD>");
            str2 = sb16.toString();
        }
        StringBuilder sb17 = new StringBuilder();
        sb17.append(str2);
        sb17.append("    <TD BORDER=\"1\" ");
        String sb18 = sb17.toString();
        if (widgetRun.dimension.resolved && !widgetRun.widget.measured) {
            StringBuilder sb19 = new StringBuilder();
            sb19.append(sb18);
            sb19.append(" BGCOLOR=\"green\" ");
            sb18 = sb19.toString();
        } else if (widgetRun.dimension.resolved && widgetRun.widget.measured) {
            StringBuilder sb20 = new StringBuilder();
            sb20.append(sb18);
            sb20.append(" BGCOLOR=\"lightgray\" ");
            sb18 = sb20.toString();
        } else if (!widgetRun.dimension.resolved && widgetRun.widget.measured) {
            StringBuilder sb21 = new StringBuilder();
            sb21.append(sb18);
            sb21.append(" BGCOLOR=\"yellow\" ");
            sb18 = sb21.toString();
        }
        if (dimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
            StringBuilder sb22 = new StringBuilder();
            sb22.append(sb18);
            sb22.append("style=\"dashed\"");
            sb18 = sb22.toString();
        }
        if (runGroup != null) {
            StringBuilder sb23 = new StringBuilder();
            sb23.append(" [");
            sb23.append(runGroup.groupIndex + 1);
            sb23.append("/");
            sb23.append(RunGroup.index);
            sb23.append("]");
            str3 = sb23.toString();
        } else {
            str3 = "";
        }
        StringBuilder sb24 = new StringBuilder();
        sb24.append(sb18);
        sb24.append(">");
        sb24.append(debugName);
        sb24.append(str3);
        sb24.append(" </TD>");
        String sb25 = sb24.toString();
        if (!z2) {
            StringBuilder sb26 = new StringBuilder();
            sb26.append(sb25);
            sb26.append(str6);
            String sb27 = sb26.toString();
            if (widgetRun.end.resolved) {
                StringBuilder sb28 = new StringBuilder();
                sb28.append(sb27);
                sb28.append(str5);
                sb27 = sb28.toString();
            }
            StringBuilder sb29 = new StringBuilder();
            sb29.append(sb27);
            sb29.append(" PORT=\"RIGHT\" BORDER=\"1\">R</TD>");
            str4 = sb29.toString();
        } else {
            StringBuilder sb30 = new StringBuilder();
            sb30.append(sb25);
            sb30.append(str6);
            String sb31 = sb30.toString();
            if (z && ((VerticalWidgetRun) widgetRun).baseline.resolved) {
                StringBuilder sb32 = new StringBuilder();
                sb32.append(sb31);
                sb32.append(str5);
                sb31 = sb32.toString();
            }
            StringBuilder sb33 = new StringBuilder();
            sb33.append(sb31);
            sb33.append(" PORT=\"BASELINE\" BORDER=\"1\">b</TD>");
            String sb34 = sb33.toString();
            StringBuilder sb35 = new StringBuilder();
            sb35.append(sb34);
            sb35.append(str6);
            String sb36 = sb35.toString();
            if (widgetRun.end.resolved) {
                StringBuilder sb37 = new StringBuilder();
                sb37.append(sb36);
                sb37.append(str5);
                sb36 = sb37.toString();
            }
            StringBuilder sb38 = new StringBuilder();
            sb38.append(sb36);
            sb38.append(" PORT=\"BOTTOM\" BORDER=\"1\">B</TD>");
            str4 = sb38.toString();
        }
        StringBuilder sb39 = new StringBuilder();
        sb39.append(str4);
        sb39.append("  </TR></TABLE>");
        String sb40 = sb39.toString();
        StringBuilder sb41 = new StringBuilder();
        sb41.append(sb40);
        sb41.append(">];\n");
        return sb41.toString();
    }

    private String generateChainDisplayGraph(ChainRun chainRun, String str) {
        String str2;
        String str3;
        int i = chainRun.orientation;
        StringBuilder sb = new StringBuilder();
        sb.append("cluster_");
        sb.append(chainRun.widget.getDebugName());
        String sb2 = sb.toString();
        if (i == 0) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb2);
            sb3.append("_h");
            str2 = sb3.toString();
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(sb2);
            sb4.append("_v");
            str2 = sb4.toString();
        }
        StringBuilder sb5 = new StringBuilder();
        sb5.append("subgraph ");
        sb5.append(str2);
        sb5.append(" {\n");
        String sb6 = sb5.toString();
        Iterator it = chainRun.widgets.iterator();
        String str4 = "";
        while (it.hasNext()) {
            WidgetRun widgetRun = (WidgetRun) it.next();
            String debugName = widgetRun.widget.getDebugName();
            if (i == 0) {
                StringBuilder sb7 = new StringBuilder();
                sb7.append(debugName);
                sb7.append("_HORIZONTAL");
                str3 = sb7.toString();
            } else {
                StringBuilder sb8 = new StringBuilder();
                sb8.append(debugName);
                sb8.append("_VERTICAL");
                str3 = sb8.toString();
            }
            StringBuilder sb9 = new StringBuilder();
            sb9.append(sb6);
            sb9.append(str3);
            sb9.append(";\n");
            sb6 = sb9.toString();
            str4 = generateDisplayGraph(widgetRun, str4);
        }
        StringBuilder sb10 = new StringBuilder();
        sb10.append(sb6);
        sb10.append("}\n");
        String sb11 = sb10.toString();
        StringBuilder sb12 = new StringBuilder();
        sb12.append(str);
        sb12.append(str4);
        sb12.append(sb11);
        return sb12.toString();
    }

    private boolean isCenteredConnection(DependencyNode dependencyNode, DependencyNode dependencyNode2) {
        int i = 0;
        for (DependencyNode dependencyNode3 : dependencyNode.targets) {
            if (dependencyNode3 != dependencyNode2) {
                i++;
            }
        }
        int i2 = 0;
        for (DependencyNode dependencyNode4 : dependencyNode2.targets) {
            if (dependencyNode4 != dependencyNode) {
                i2++;
            }
        }
        if (i <= 0 || i2 <= 0) {
            return false;
        }
        return USE_GROUPS;
    }

    /* JADX WARNING: Removed duplicated region for block: B:62:0x01fb  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0202 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String generateDisplayGraph(androidx.constraintlayout.solver.widgets.analyzer.WidgetRun r9, java.lang.String r10) {
        /*
            r8 = this;
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r9.start
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r9.end
            boolean r2 = r9 instanceof androidx.constraintlayout.solver.widgets.analyzer.HelperReferences
            if (r2 != 0) goto L_0x0028
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.Dependency> r2 = r0.dependencies
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x0028
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.Dependency> r2 = r1.dependencies
            boolean r2 = r2.isEmpty()
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r3 = r0.targets
            boolean r3 = r3.isEmpty()
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0028
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r2 = r1.targets
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x0028
            return r10
        L_0x0028:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r10)
            java.lang.String r10 = r8.nodeDefinition(r9)
            r2.append(r10)
            java.lang.String r10 = r2.toString()
            boolean r2 = r8.isCenteredConnection(r0, r1)
            java.lang.String r10 = r8.generateDisplayNode(r0, r2, r10)
            java.lang.String r10 = r8.generateDisplayNode(r1, r2, r10)
            boolean r3 = r9 instanceof androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun
            if (r3 == 0) goto L_0x0054
            r4 = r9
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r4 = (androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun) r4
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r4 = r4.baseline
            java.lang.String r10 = r8.generateDisplayNode(r4, r2, r10)
        L_0x0054:
            boolean r2 = r9 instanceof androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun
            r4 = 0
            java.lang.String r5 = " -> "
            java.lang.String r6 = "\n"
            if (r2 != 0) goto L_0x0138
            boolean r2 = r9 instanceof androidx.constraintlayout.solver.widgets.analyzer.ChainRun
            if (r2 == 0) goto L_0x006a
            r7 = r9
            androidx.constraintlayout.solver.widgets.analyzer.ChainRun r7 = (androidx.constraintlayout.solver.widgets.analyzer.ChainRun) r7
            int r7 = r7.orientation
            if (r7 != 0) goto L_0x006a
            goto L_0x0138
        L_0x006a:
            if (r3 != 0) goto L_0x0076
            if (r2 == 0) goto L_0x01f7
            r2 = r9
            androidx.constraintlayout.solver.widgets.analyzer.ChainRun r2 = (androidx.constraintlayout.solver.widgets.analyzer.ChainRun) r2
            int r2 = r2.orientation
            r3 = 1
            if (r2 != r3) goto L_0x01f7
        L_0x0076:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r9.widget
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = r2.getVerticalDimensionBehaviour()
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r2 == r3) goto L_0x00b6
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r2 != r3) goto L_0x0085
            goto L_0x00b6
        L_0x0085:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r0) goto L_0x01f7
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r9.widget
            float r0 = r0.getDimensionRatio()
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x01f7
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r9.widget
            java.lang.String r0 = r0.getDebugName()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r6)
            r1.append(r0)
            java.lang.String r2 = "_VERTICAL -> "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = "_HORIZONTAL;\n"
            r1.append(r0)
            r1.toString()
            goto L_0x01f7
        L_0x00b6:
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r2 = r0.targets
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x00f7
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r2 = r1.targets
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x00f7
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r6)
            java.lang.String r1 = r1.name()
            r2.append(r1)
            r2.append(r5)
            java.lang.String r0 = r0.name()
            r2.append(r0)
            r2.append(r6)
            java.lang.String r0 = r2.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r10)
            r1.append(r0)
            java.lang.String r10 = r1.toString()
            goto L_0x01f7
        L_0x00f7:
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r2 = r0.targets
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x01f7
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r2 = r1.targets
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x01f7
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r6)
            java.lang.String r0 = r0.name()
            r2.append(r0)
            r2.append(r5)
            java.lang.String r0 = r1.name()
            r2.append(r0)
            r2.append(r6)
            java.lang.String r0 = r2.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r10)
            r1.append(r0)
            java.lang.String r10 = r1.toString()
            goto L_0x01f7
        L_0x0138:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r9.widget
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = r2.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r2 == r3) goto L_0x0178
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r2 != r3) goto L_0x0147
            goto L_0x0178
        L_0x0147:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r0) goto L_0x01f7
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r9.widget
            float r0 = r0.getDimensionRatio()
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x01f7
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r9.widget
            java.lang.String r0 = r0.getDebugName()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r6)
            r1.append(r0)
            java.lang.String r2 = "_HORIZONTAL -> "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = "_VERTICAL;\n"
            r1.append(r0)
            r1.toString()
            goto L_0x01f7
        L_0x0178:
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r2 = r0.targets
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x01b8
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r2 = r1.targets
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x01b8
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r6)
            java.lang.String r1 = r1.name()
            r2.append(r1)
            r2.append(r5)
            java.lang.String r0 = r0.name()
            r2.append(r0)
            r2.append(r6)
            java.lang.String r0 = r2.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r10)
            r1.append(r0)
            java.lang.String r10 = r1.toString()
            goto L_0x01f7
        L_0x01b8:
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r2 = r0.targets
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x01f7
            java.util.List<androidx.constraintlayout.solver.widgets.analyzer.DependencyNode> r2 = r1.targets
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x01f7
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r6)
            java.lang.String r0 = r0.name()
            r2.append(r0)
            r2.append(r5)
            java.lang.String r0 = r1.name()
            r2.append(r0)
            r2.append(r6)
            java.lang.String r0 = r2.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r10)
            r1.append(r0)
            java.lang.String r10 = r1.toString()
        L_0x01f7:
            boolean r0 = r9 instanceof androidx.constraintlayout.solver.widgets.analyzer.ChainRun
            if (r0 == 0) goto L_0x0202
            androidx.constraintlayout.solver.widgets.analyzer.ChainRun r9 = (androidx.constraintlayout.solver.widgets.analyzer.ChainRun) r9
            java.lang.String r9 = r8.generateChainDisplayGraph(r9, r10)
            return r9
        L_0x0202:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.DependencyGraph.generateDisplayGraph(androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, java.lang.String):java.lang.String");
    }
}
