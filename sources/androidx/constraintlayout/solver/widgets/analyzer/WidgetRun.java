package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour;

public abstract class WidgetRun implements Dependency {
    DimensionDependency dimension = new DimensionDependency(this);
    protected DimensionBehaviour dimensionBehavior;
    public DependencyNode end = new DependencyNode(this);
    protected RunType mRunType = RunType.NONE;
    public int matchConstraintsType;
    public int orientation = 0;
    boolean resolved = false;
    RunGroup runGroup;
    public DependencyNode start = new DependencyNode(this);
    ConstraintWidget widget;

    /* renamed from: androidx.constraintlayout.solver.widgets.analyzer.WidgetRun$1 */
    static /* synthetic */ class C02081 {

        /* renamed from: $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type */
        static final /* synthetic */ int[] f45x4c44d048 = new int[C0202Type.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type[] r0 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f45x4c44d048 = r0
                int[] r0 = f45x4c44d048     // Catch:{ NoSuchFieldError -> 0x0014 }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.LEFT     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f45x4c44d048     // Catch:{ NoSuchFieldError -> 0x001f }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.RIGHT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f45x4c44d048     // Catch:{ NoSuchFieldError -> 0x002a }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.TOP     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f45x4c44d048     // Catch:{ NoSuchFieldError -> 0x0035 }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.BASELINE     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f45x4c44d048     // Catch:{ NoSuchFieldError -> 0x0040 }
                androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type.BOTTOM     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.WidgetRun.C02081.<clinit>():void");
        }
    }

    enum RunType {
        NONE,
        START,
        END,
        CENTER
    }

    /* access modifiers changed from: 0000 */
    public abstract void apply();

    /* access modifiers changed from: 0000 */
    public abstract void applyToWidget();

    /* access modifiers changed from: 0000 */
    public abstract void clear();

    /* access modifiers changed from: 0000 */
    public abstract void reset();

    /* access modifiers changed from: 0000 */
    public abstract boolean supportsWrapComputation();

    public void update(Dependency dependency) {
    }

    /* access modifiers changed from: protected */
    public void updateRunEnd(Dependency dependency) {
    }

    /* access modifiers changed from: protected */
    public void updateRunStart(Dependency dependency) {
    }

    public WidgetRun(ConstraintWidget constraintWidget) {
        this.widget = constraintWidget;
    }

    public boolean isDimensionResolved() {
        return this.dimension.resolved;
    }

    public boolean isCenterConnection() {
        int size = this.start.targets.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            if (((DependencyNode) this.start.targets.get(i2)).run != this) {
                i++;
            }
        }
        int size2 = this.end.targets.size();
        for (int i3 = 0; i3 < size2; i3++) {
            if (((DependencyNode) this.end.targets.get(i3)).run != this) {
                i++;
            }
        }
        if (i >= 2) {
            return true;
        }
        return false;
    }

    public long wrapSize(int i) {
        long j;
        int i2;
        if (!this.dimension.resolved) {
            return 0;
        }
        long j2 = (long) this.dimension.value;
        if (isCenterConnection()) {
            i2 = this.start.margin - this.end.margin;
        } else if (i == 0) {
            i2 = this.start.margin;
        } else {
            j = j2 - ((long) this.end.margin);
            return j;
        }
        j = j2 + ((long) i2);
        return j;
    }

    /* access modifiers changed from: protected */
    public final DependencyNode getTarget(ConstraintAnchor constraintAnchor) {
        DependencyNode dependencyNode = null;
        if (constraintAnchor.mTarget == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor.mTarget.mOwner;
        int i = C02081.f45x4c44d048[constraintAnchor.mTarget.mType.ordinal()];
        if (i == 1) {
            dependencyNode = constraintWidget.horizontalRun.start;
        } else if (i == 2) {
            dependencyNode = constraintWidget.horizontalRun.end;
        } else if (i == 3) {
            dependencyNode = constraintWidget.verticalRun.start;
        } else if (i == 4) {
            dependencyNode = constraintWidget.verticalRun.baseline;
        } else if (i == 5) {
            dependencyNode = constraintWidget.verticalRun.end;
        }
        return dependencyNode;
    }

    /* access modifiers changed from: protected */
    public void updateRunCenter(Dependency dependency, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i) {
        float f;
        DependencyNode target = getTarget(constraintAnchor);
        DependencyNode target2 = getTarget(constraintAnchor2);
        if (target.resolved && target2.resolved) {
            int margin = target.value + constraintAnchor.getMargin();
            int margin2 = target2.value - constraintAnchor2.getMargin();
            int i2 = margin2 - margin;
            if (!this.dimension.resolved && this.dimensionBehavior == DimensionBehaviour.MATCH_CONSTRAINT) {
                resolveDimension(i, i2);
            }
            if (this.dimension.resolved) {
                if (this.dimension.value == i2) {
                    this.start.resolve(margin);
                    this.end.resolve(margin2);
                    return;
                }
                if (i == 0) {
                    f = this.widget.getHorizontalBiasPercent();
                } else {
                    f = this.widget.getVerticalBiasPercent();
                }
                if (target == target2) {
                    margin = target.value;
                    margin2 = target2.value;
                    f = 0.5f;
                }
                this.start.resolve((int) (((float) margin) + 0.5f + (((float) ((margin2 - margin) - this.dimension.value)) * f)));
                this.end.resolve(this.start.value + this.dimension.value);
            }
        }
    }

    private void resolveDimension(int i, int i2) {
        int i3;
        int i4 = this.matchConstraintsType;
        if (i4 == 0) {
            this.dimension.resolve(getLimitedDimension(i2, i));
        } else if (i4 == 1) {
            this.dimension.resolve(Math.min(getLimitedDimension(this.dimension.wrapValue, i), i2));
        } else if (i4 == 2) {
            ConstraintWidget parent = this.widget.getParent();
            if (parent != null) {
                WidgetRun widgetRun = i == 0 ? parent.horizontalRun : parent.verticalRun;
                if (widgetRun.dimension.resolved) {
                    this.dimension.resolve(getLimitedDimension((int) ((((float) widgetRun.dimension.value) * (i == 0 ? this.widget.mMatchConstraintPercentWidth : this.widget.mMatchConstraintPercentHeight)) + 0.5f), i));
                }
            }
        } else if (i4 == 3) {
            if (this.widget.horizontalRun.dimensionBehavior != DimensionBehaviour.MATCH_CONSTRAINT || this.widget.horizontalRun.matchConstraintsType != 3 || this.widget.verticalRun.dimensionBehavior != DimensionBehaviour.MATCH_CONSTRAINT || this.widget.verticalRun.matchConstraintsType != 3) {
                WidgetRun widgetRun2 = i == 0 ? this.widget.verticalRun : this.widget.horizontalRun;
                if (widgetRun2.dimension.resolved) {
                    float dimensionRatio = this.widget.getDimensionRatio();
                    if (i == 1) {
                        i3 = (int) ((((float) widgetRun2.dimension.value) / dimensionRatio) + 0.5f);
                    } else {
                        i3 = (int) ((dimensionRatio * ((float) widgetRun2.dimension.value)) + 0.5f);
                    }
                    this.dimension.resolve(i3);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final int getLimitedDimension(int i, int i2) {
        if (i2 == 0) {
            int i3 = this.widget.mMatchConstraintMaxWidth;
            int max = Math.max(this.widget.mMatchConstraintMinWidth, i);
            if (i3 > 0) {
                max = Math.min(i3, i);
            }
            if (max != i) {
                return max;
            }
            return i;
        }
        int i4 = this.widget.mMatchConstraintMaxHeight;
        int min = i4 > 0 ? Math.min(i4, i) : Math.max(this.widget.mMatchConstraintMinHeight, i);
        return min != i ? min : i;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0027, code lost:
        if (r3 != 5) goto L_0x002f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final androidx.constraintlayout.solver.widgets.analyzer.DependencyNode getTarget(androidx.constraintlayout.solver.widgets.ConstraintAnchor r3, int r4) {
        /*
            r2 = this;
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r3.mTarget
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r3.mTarget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r0.mOwner
            if (r4 != 0) goto L_0x000f
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r4 = r0.horizontalRun
            goto L_0x0011
        L_0x000f:
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r4 = r0.verticalRun
        L_0x0011:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = r3.mType
            int[] r0 = androidx.constraintlayout.solver.widgets.analyzer.WidgetRun.C02081.f45x4c44d048
            int r3 = r3.ordinal()
            r3 = r0[r3]
            r0 = 1
            if (r3 == r0) goto L_0x002d
            r0 = 2
            if (r3 == r0) goto L_0x002a
            r0 = 3
            if (r3 == r0) goto L_0x002d
            r0 = 5
            if (r3 == r0) goto L_0x002a
            goto L_0x002f
        L_0x002a:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r4.end
            goto L_0x002f
        L_0x002d:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r4.start
        L_0x002f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.WidgetRun.getTarget(androidx.constraintlayout.solver.widgets.ConstraintAnchor, int):androidx.constraintlayout.solver.widgets.analyzer.DependencyNode");
    }

    /* access modifiers changed from: protected */
    public final void addTarget(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i) {
        dependencyNode.targets.add(dependencyNode2);
        dependencyNode.margin = i;
        dependencyNode2.dependencies.add(dependencyNode);
    }

    /* access modifiers changed from: protected */
    public final void addTarget(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i, DimensionDependency dimensionDependency) {
        dependencyNode.targets.add(dependencyNode2);
        dependencyNode.targets.add(this.dimension);
        dependencyNode.marginFactor = i;
        dependencyNode.marginDependency = dimensionDependency;
        dependencyNode2.dependencies.add(dependencyNode);
        dimensionDependency.dependencies.add(dependencyNode);
    }

    public long getWrapDimension() {
        if (this.dimension.resolved) {
            return (long) this.dimension.value;
        }
        return 0;
    }

    public boolean isResolved() {
        return this.resolved;
    }
}
