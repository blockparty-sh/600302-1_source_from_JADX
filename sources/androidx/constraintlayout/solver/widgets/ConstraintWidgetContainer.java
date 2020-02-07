package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure.Measurer;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyGraph;
import java.util.ArrayList;
import java.util.Arrays;

public class ConstraintWidgetContainer extends WidgetContainer {
    private static final boolean DEBUG = false;
    static final boolean DEBUG_GRAPH = false;
    private static final boolean DEBUG_LAYOUT = false;
    private static final int MAX_ITERATIONS = 8;
    BasicMeasure mBasicMeasureSolver = new BasicMeasure(this);
    int mDebugSolverPassCount = 0;
    public DependencyGraph mDependencyGraph = new DependencyGraph(this);
    public boolean mGroupsWrapOptimized = false;
    private boolean mHeightMeasuredTooSmall = false;
    ChainHead[] mHorizontalChainsArray = new ChainHead[4];
    int mHorizontalChainsSize = 0;
    public boolean mHorizontalWrapOptimized = false;
    private boolean mIsRtl = false;
    private Measurer mMeasurer = null;
    private int mOptimizationLevel = 7;
    int mPaddingBottom;
    int mPaddingLeft;
    int mPaddingRight;
    int mPaddingTop;
    public boolean mSkipSolver = false;
    protected LinearSystem mSystem = new LinearSystem();
    ChainHead[] mVerticalChainsArray = new ChainHead[4];
    int mVerticalChainsSize = 0;
    public boolean mVerticalWrapOptimized = false;
    private boolean mWidthMeasuredTooSmall = false;
    public int mWrapFixedHeight = 0;
    public int mWrapFixedWidth = 0;

    public String getType() {
        return "ConstraintLayout";
    }

    public boolean handlesInternalConstraints() {
        return false;
    }

    public void invalidateGraph() {
        this.mDependencyGraph.invalidateGraph();
    }

    public void invalidateMeasures() {
        this.mDependencyGraph.invalidateMeasures();
    }

    public boolean directMeasure(boolean z) {
        return this.mDependencyGraph.directMeasure(z);
    }

    public boolean directMeasureSetup(boolean z) {
        return this.mDependencyGraph.directMeasureSetup(z);
    }

    public boolean directMeasureWithOrientation(boolean z, int i) {
        return this.mDependencyGraph.directMeasureWithOrientation(z, i);
    }

    public void defineTerminalWidgets() {
        this.mDependencyGraph.defineTerminalWidgets(getHorizontalDimensionBehaviour(), getVerticalDimensionBehaviour());
    }

    public void measure(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        int i10 = i8;
        this.mPaddingLeft = i10;
        int i11 = i9;
        this.mPaddingTop = i11;
        this.mBasicMeasureSolver.solverMeasure(this, i, i10, i11, i2, i3, i4, i5, i6, i7);
    }

    public void updateHierarchy() {
        this.mBasicMeasureSolver.updateHierarchy(this);
    }

    public void setMeasurer(Measurer measurer) {
        this.mMeasurer = measurer;
        this.mDependencyGraph.setMeasurer(measurer);
    }

    public Measurer getMeasurer() {
        return this.mMeasurer;
    }

    public void fillMetrics(Metrics metrics) {
        this.mSystem.fillMetrics(metrics);
    }

    public ConstraintWidgetContainer() {
    }

    public ConstraintWidgetContainer(int i, int i2, int i3, int i4) {
        super(i, i2, i3, i4);
    }

    public ConstraintWidgetContainer(int i, int i2) {
        super(i, i2);
    }

    public void setOptimizationLevel(int i) {
        this.mOptimizationLevel = i;
    }

    public int getOptimizationLevel() {
        return this.mOptimizationLevel;
    }

    public boolean optimizeFor(int i) {
        return (this.mOptimizationLevel & i) == i;
    }

    public void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        this.mSkipSolver = false;
        super.reset();
    }

    public boolean isWidthMeasuredTooSmall() {
        return this.mWidthMeasuredTooSmall;
    }

    public boolean isHeightMeasuredTooSmall() {
        return this.mHeightMeasuredTooSmall;
    }

    public boolean addChildrenToSolver(LinearSystem linearSystem) {
        addToSolver(linearSystem);
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            if (constraintWidget instanceof VirtualLayout) {
                constraintWidget.addToSolver(linearSystem);
            }
        }
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget2 = (ConstraintWidget) this.mChildren.get(i2);
            if (constraintWidget2 instanceof ConstraintWidgetContainer) {
                DimensionBehaviour dimensionBehaviour = constraintWidget2.mListDimensionBehaviors[0];
                DimensionBehaviour dimensionBehaviour2 = constraintWidget2.mListDimensionBehaviors[1];
                if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget2.setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
                }
                if (dimensionBehaviour2 == DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget2.setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
                }
                constraintWidget2.addToSolver(linearSystem);
                if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget2.setHorizontalDimensionBehaviour(dimensionBehaviour);
                }
                if (dimensionBehaviour2 == DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget2.setVerticalDimensionBehaviour(dimensionBehaviour2);
                }
            } else {
                Optimizer.checkMatchParent(this, linearSystem, constraintWidget2);
                if (!(constraintWidget2 instanceof VirtualLayout)) {
                    constraintWidget2.addToSolver(linearSystem);
                }
            }
        }
        if (this.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(this, linearSystem, 0);
        }
        if (this.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(this, linearSystem, 1);
        }
        return true;
    }

    public void updateChildrenFromSolver(LinearSystem linearSystem, boolean[] zArr) {
        zArr[2] = false;
        updateFromSolver(linearSystem);
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ((ConstraintWidget) this.mChildren.get(i)).updateFromSolver(linearSystem);
        }
    }

    public void updateFromRuns(boolean z, boolean z2) {
        super.updateFromRuns(z, z2);
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ((ConstraintWidget) this.mChildren.get(i)).updateFromRuns(z, z2);
        }
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.mPaddingLeft = i;
        this.mPaddingTop = i2;
        this.mPaddingRight = i3;
        this.mPaddingBottom = i4;
    }

    public void setRtl(boolean z) {
        this.mIsRtl = z;
    }

    public boolean isRtl() {
        return this.mIsRtl;
    }

    /* JADX WARNING: type inference failed for: r11v7, types: [boolean] */
    /* JADX WARNING: type inference failed for: r11v11 */
    /* JADX WARNING: type inference failed for: r11v12 */
    /* JADX WARNING: type inference failed for: r11v27 */
    /* JADX WARNING: type inference failed for: r11v28 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r11v7, types: [boolean]
      assigns: []
      uses: [?[int, short, byte, char], boolean]
      mth insns count: 212
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
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
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void layout() {
        /*
            r17 = this;
            r1 = r17
            r2 = 0
            r1.f36mX = r2
            r1.f37mY = r2
            int r0 = r17.getWidth()
            int r3 = java.lang.Math.max(r2, r0)
            int r0 = r17.getHeight()
            int r4 = java.lang.Math.max(r2, r0)
            r1.mWidthMeasuredTooSmall = r2
            r1.mHeightMeasuredTooSmall = r2
            r0 = 64
            boolean r0 = r1.optimizeFor(r0)
            r5 = 1
            if (r0 != 0) goto L_0x002f
            r0 = 128(0x80, float:1.794E-43)
            boolean r0 = r1.optimizeFor(r0)
            if (r0 == 0) goto L_0x002d
            goto L_0x002f
        L_0x002d:
            r0 = 0
            goto L_0x0030
        L_0x002f:
            r0 = 1
        L_0x0030:
            androidx.constraintlayout.solver.LinearSystem r6 = r1.mSystem
            r6.graphOptimizer = r2
            r6.newgraphOptimizer = r2
            int r7 = r1.mOptimizationLevel
            if (r7 == 0) goto L_0x003e
            if (r0 == 0) goto L_0x003e
            r6.newgraphOptimizer = r5
        L_0x003e:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r6 = r0[r5]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r7 = r0[r2]
            r17.resetChains()
            java.util.ArrayList r8 = r1.mChildren
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = r17.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 == r9) goto L_0x005e
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = r17.getVerticalDimensionBehaviour()
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r9) goto L_0x005c
            goto L_0x005e
        L_0x005c:
            r9 = 0
            goto L_0x005f
        L_0x005e:
            r9 = 1
        L_0x005f:
            r17.resetChains()
            java.util.ArrayList r0 = r1.mChildren
            int r10 = r0.size()
            r0 = 0
        L_0x0069:
            if (r0 >= r10) goto L_0x007f
            java.util.ArrayList r11 = r1.mChildren
            java.lang.Object r11 = r11.get(r0)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r11 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r11
            boolean r12 = r11 instanceof androidx.constraintlayout.solver.widgets.WidgetContainer
            if (r12 == 0) goto L_0x007c
            androidx.constraintlayout.solver.widgets.WidgetContainer r11 = (androidx.constraintlayout.solver.widgets.WidgetContainer) r11
            r11.layout()
        L_0x007c:
            int r0 = r0 + 1
            goto L_0x0069
        L_0x007f:
            r0 = 0
            r11 = 1
            r12 = 0
        L_0x0082:
            if (r11 == 0) goto L_0x01d9
            int r13 = r0 + 1
            androidx.constraintlayout.solver.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x00b4 }
            r0.reset()     // Catch:{ Exception -> 0x00b4 }
            r17.resetChains()     // Catch:{ Exception -> 0x00b4 }
            androidx.constraintlayout.solver.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x00b4 }
            r1.createObjectVariables(r0)     // Catch:{ Exception -> 0x00b4 }
            r0 = 0
        L_0x0094:
            if (r0 >= r10) goto L_0x00a6
            java.util.ArrayList r14 = r1.mChildren     // Catch:{ Exception -> 0x00b4 }
            java.lang.Object r14 = r14.get(r0)     // Catch:{ Exception -> 0x00b4 }
            androidx.constraintlayout.solver.widgets.ConstraintWidget r14 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r14     // Catch:{ Exception -> 0x00b4 }
            androidx.constraintlayout.solver.LinearSystem r15 = r1.mSystem     // Catch:{ Exception -> 0x00b4 }
            r14.createObjectVariables(r15)     // Catch:{ Exception -> 0x00b4 }
            int r0 = r0 + 1
            goto L_0x0094
        L_0x00a6:
            androidx.constraintlayout.solver.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x00b4 }
            boolean r11 = r1.addChildrenToSolver(r0)     // Catch:{ Exception -> 0x00b4 }
            if (r11 == 0) goto L_0x00ce
            androidx.constraintlayout.solver.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x00b4 }
            r0.minimize()     // Catch:{ Exception -> 0x00b4 }
            goto L_0x00ce
        L_0x00b4:
            r0 = move-exception
            r0.printStackTrace()
            java.io.PrintStream r14 = java.lang.System.out
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            java.lang.String r5 = "EXCEPTION : "
            r15.append(r5)
            r15.append(r0)
            java.lang.String r0 = r15.toString()
            r14.println(r0)
        L_0x00ce:
            if (r11 == 0) goto L_0x00d8
            androidx.constraintlayout.solver.LinearSystem r0 = r1.mSystem
            boolean[] r5 = androidx.constraintlayout.solver.widgets.Optimizer.flags
            r1.updateChildrenFromSolver(r0, r5)
            goto L_0x00eb
        L_0x00d8:
            androidx.constraintlayout.solver.LinearSystem r0 = r1.mSystem
            r1.updateFromSolver(r0)
            r0 = 0
        L_0x00de:
            if (r0 >= r10) goto L_0x00eb
            java.util.ArrayList r5 = r1.mChildren
            java.lang.Object r5 = r5.get(r0)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r5 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r5
            int r0 = r0 + 1
            goto L_0x00de
        L_0x00eb:
            if (r9 == 0) goto L_0x015b
            r0 = 8
            if (r13 >= r0) goto L_0x015b
            boolean[] r0 = androidx.constraintlayout.solver.widgets.Optimizer.flags
            r5 = 2
            boolean r0 = r0[r5]
            if (r0 == 0) goto L_0x015b
            r0 = 0
            r5 = 0
            r11 = 0
        L_0x00fb:
            if (r0 >= r10) goto L_0x011f
            java.util.ArrayList r14 = r1.mChildren
            java.lang.Object r14 = r14.get(r0)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r14 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r14
            int r15 = r14.f36mX
            int r16 = r14.getWidth()
            int r15 = r15 + r16
            int r5 = java.lang.Math.max(r5, r15)
            int r15 = r14.f37mY
            int r14 = r14.getHeight()
            int r15 = r15 + r14
            int r11 = java.lang.Math.max(r11, r15)
            int r0 = r0 + 1
            goto L_0x00fb
        L_0x011f:
            int r0 = r1.mMinWidth
            int r0 = java.lang.Math.max(r0, r5)
            int r5 = r1.mMinHeight
            int r5 = java.lang.Math.max(r5, r11)
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r7 != r11) goto L_0x0141
            int r11 = r17.getWidth()
            if (r11 >= r0) goto L_0x0141
            r1.setWidth(r0)
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r0[r2] = r11
            r0 = 1
            r12 = 1
            goto L_0x0142
        L_0x0141:
            r0 = 0
        L_0x0142:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r6 != r11) goto L_0x0159
            int r11 = r17.getHeight()
            if (r11 >= r5) goto L_0x0159
            r1.setHeight(r5)
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r11 = 1
            r0[r11] = r5
            r0 = 1
            r5 = 1
            goto L_0x015d
        L_0x0159:
            r5 = r12
            goto L_0x015d
        L_0x015b:
            r5 = r12
            r0 = 0
        L_0x015d:
            int r11 = r1.mMinWidth
            int r12 = r17.getWidth()
            int r11 = java.lang.Math.max(r11, r12)
            int r12 = r17.getWidth()
            if (r11 <= r12) goto L_0x0178
            r1.setWidth(r11)
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0[r2] = r5
            r0 = 1
            r5 = 1
        L_0x0178:
            int r11 = r1.mMinHeight
            int r12 = r17.getHeight()
            int r11 = java.lang.Math.max(r11, r12)
            int r12 = r17.getHeight()
            if (r11 <= r12) goto L_0x0195
            r1.setHeight(r11)
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r11 = 1
            r0[r11] = r5
            r0 = 1
            r5 = 1
            goto L_0x0196
        L_0x0195:
            r11 = 1
        L_0x0196:
            if (r5 != 0) goto L_0x01d3
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r12 = r1.mListDimensionBehaviors
            r12 = r12[r2]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r14 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r12 != r14) goto L_0x01b5
            if (r3 <= 0) goto L_0x01b5
            int r12 = r17.getWidth()
            if (r12 <= r3) goto L_0x01b5
            r1.mWidthMeasuredTooSmall = r11
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0[r2] = r5
            r1.setWidth(r3)
            r0 = 1
            r5 = 1
        L_0x01b5:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r12 = r1.mListDimensionBehaviors
            r12 = r12[r11]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r14 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r12 != r14) goto L_0x01d3
            if (r4 <= 0) goto L_0x01d3
            int r12 = r17.getHeight()
            if (r12 <= r4) goto L_0x01d3
            r1.mHeightMeasuredTooSmall = r11
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0[r11] = r5
            r1.setHeight(r4)
            r11 = 1
            r12 = 1
            goto L_0x01d5
        L_0x01d3:
            r11 = r0
            r12 = r5
        L_0x01d5:
            r0 = r13
            r5 = 1
            goto L_0x0082
        L_0x01d9:
            java.util.ArrayList r8 = (java.util.ArrayList) r8
            r1.mChildren = r8
            if (r12 == 0) goto L_0x01e8
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r0[r2] = r7
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r2 = 1
            r0[r2] = r6
        L_0x01e8:
            androidx.constraintlayout.solver.LinearSystem r0 = r1.mSystem
            androidx.constraintlayout.solver.Cache r0 = r0.getCache()
            r1.resetSolverVariables(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer.layout():void");
    }

    public ArrayList<Guideline> getVerticalGuidelines() {
        ArrayList<Guideline> arrayList = new ArrayList<>();
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 1) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<Guideline> getHorizontalGuidelines() {
        ArrayList<Guideline> arrayList = new ArrayList<>();
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 0) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public LinearSystem getSystem() {
        return this.mSystem;
    }

    private void resetChains() {
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
    }

    /* access modifiers changed from: 0000 */
    public void addChain(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            addHorizontalChain(constraintWidget);
        } else if (i == 1) {
            addVerticalChain(constraintWidget);
        }
    }

    private void addHorizontalChain(ConstraintWidget constraintWidget) {
        int i = this.mHorizontalChainsSize + 1;
        ChainHead[] chainHeadArr = this.mHorizontalChainsArray;
        if (i >= chainHeadArr.length) {
            this.mHorizontalChainsArray = (ChainHead[]) Arrays.copyOf(chainHeadArr, chainHeadArr.length * 2);
        }
        this.mHorizontalChainsArray[this.mHorizontalChainsSize] = new ChainHead(constraintWidget, 0, isRtl());
        this.mHorizontalChainsSize++;
    }

    private void addVerticalChain(ConstraintWidget constraintWidget) {
        int i = this.mVerticalChainsSize + 1;
        ChainHead[] chainHeadArr = this.mVerticalChainsArray;
        if (i >= chainHeadArr.length) {
            this.mVerticalChainsArray = (ChainHead[]) Arrays.copyOf(chainHeadArr, chainHeadArr.length * 2);
        }
        this.mVerticalChainsArray[this.mVerticalChainsSize] = new ChainHead(constraintWidget, 1, isRtl());
        this.mVerticalChainsSize++;
    }
}
