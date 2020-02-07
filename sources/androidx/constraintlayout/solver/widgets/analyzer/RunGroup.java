package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;

class RunGroup {
    public static final int BASELINE = 2;
    public static final int END = 1;
    public static final int START = 0;
    public static int index;
    int direction;
    public boolean dual = false;
    WidgetRun firstRun = null;
    int groupIndex = 0;
    WidgetRun lastRun = null;
    public int position = 0;
    ArrayList<WidgetRun> runs = new ArrayList<>();

    public RunGroup(WidgetRun widgetRun, int i) {
        int i2 = index;
        this.groupIndex = i2;
        index = i2 + 1;
        this.firstRun = widgetRun;
        this.lastRun = widgetRun;
        this.direction = i;
    }

    public void add(WidgetRun widgetRun) {
        this.runs.add(widgetRun);
        this.lastRun = widgetRun;
    }

    private long traverseStart(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.run;
        if (widgetRun instanceof HelperReferences) {
            return j;
        }
        int size = dependencyNode.dependencies.size();
        long j2 = j;
        for (int i = 0; i < size; i++) {
            Dependency dependency = (Dependency) dependencyNode.dependencies.get(i);
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                if (dependencyNode2.run != widgetRun) {
                    j2 = Math.max(j2, traverseStart(dependencyNode2, ((long) dependencyNode2.margin) + j));
                }
            }
        }
        if (dependencyNode == widgetRun.start) {
            long wrapDimension = j + widgetRun.getWrapDimension();
            j2 = Math.max(Math.max(j2, traverseStart(widgetRun.end, wrapDimension)), wrapDimension - ((long) widgetRun.end.margin));
        }
        return j2;
    }

    private long traverseEnd(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.run;
        if (widgetRun instanceof HelperReferences) {
            return j;
        }
        int size = dependencyNode.dependencies.size();
        long j2 = j;
        for (int i = 0; i < size; i++) {
            Dependency dependency = (Dependency) dependencyNode.dependencies.get(i);
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                if (dependencyNode2.run != widgetRun) {
                    j2 = Math.min(j2, traverseEnd(dependencyNode2, ((long) dependencyNode2.margin) + j));
                }
            }
        }
        if (dependencyNode == widgetRun.end) {
            long wrapDimension = j - widgetRun.getWrapDimension();
            j2 = Math.min(Math.min(j2, traverseEnd(widgetRun.start, wrapDimension)), wrapDimension - ((long) widgetRun.start.margin));
        }
        return j2;
    }

    public long computeWrapSize(ConstraintWidgetContainer constraintWidgetContainer, int i) {
        long j;
        long j2;
        int i2;
        WidgetRun widgetRun = this.firstRun;
        long j3 = 0;
        if (widgetRun instanceof ChainRun) {
            if (((ChainRun) widgetRun).orientation != i) {
                return 0;
            }
        } else if (i == 0) {
            if (!(widgetRun instanceof HorizontalWidgetRun)) {
                return 0;
            }
        } else if (!(widgetRun instanceof VerticalWidgetRun)) {
            return 0;
        }
        DependencyNode dependencyNode = i == 0 ? constraintWidgetContainer.horizontalRun.start : constraintWidgetContainer.verticalRun.start;
        DependencyNode dependencyNode2 = i == 0 ? constraintWidgetContainer.horizontalRun.end : constraintWidgetContainer.verticalRun.end;
        boolean contains = this.firstRun.start.targets.contains(dependencyNode);
        boolean contains2 = this.firstRun.end.targets.contains(dependencyNode2);
        long wrapDimension = this.firstRun.getWrapDimension();
        if (!contains || !contains2) {
            if (contains) {
                j = Math.max(traverseStart(this.firstRun.start, (long) this.firstRun.start.margin), ((long) this.firstRun.start.margin) + wrapDimension);
            } else if (contains2) {
                j = Math.max(-traverseEnd(this.firstRun.end, (long) this.firstRun.end.margin), ((long) (-this.firstRun.end.margin)) + wrapDimension);
            } else {
                j2 = ((long) this.firstRun.start.margin) + this.firstRun.getWrapDimension();
                i2 = this.firstRun.end.margin;
            }
            return j;
        }
        long traverseStart = traverseStart(this.firstRun.start, 0);
        long traverseEnd = traverseEnd(this.firstRun.end, 0);
        long j4 = traverseStart - wrapDimension;
        if (j4 >= ((long) (-this.firstRun.end.margin))) {
            j4 += (long) this.firstRun.end.margin;
        }
        long j5 = ((-traverseEnd) - wrapDimension) - ((long) this.firstRun.start.margin);
        if (j5 >= ((long) this.firstRun.start.margin)) {
            j5 -= (long) this.firstRun.start.margin;
        }
        float biasPercent = this.firstRun.widget.getBiasPercent(i);
        if (biasPercent > 0.0f) {
            j3 = (long) ((((float) j5) / biasPercent) + (((float) j4) / (1.0f - biasPercent)));
        }
        float f = (float) j3;
        j2 = ((long) this.firstRun.start.margin) + ((long) ((f * biasPercent) + 0.5f)) + wrapDimension + ((long) ((f * (1.0f - biasPercent)) + 0.5f));
        i2 = this.firstRun.end.margin;
        j = j2 - ((long) i2);
        return j;
    }

    private boolean defineTerminalWidget(WidgetRun widgetRun, int i) {
        if (!widgetRun.widget.isTerminalWidget[i]) {
            return false;
        }
        for (Dependency dependency : widgetRun.start.dependencies) {
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode = (DependencyNode) dependency;
                if (dependencyNode.run != widgetRun && dependencyNode == dependencyNode.run.start) {
                    if (widgetRun instanceof ChainRun) {
                        Iterator it = ((ChainRun) widgetRun).widgets.iterator();
                        while (it.hasNext()) {
                            defineTerminalWidget((WidgetRun) it.next(), i);
                        }
                    } else if (!(widgetRun instanceof HelperReferences)) {
                        widgetRun.widget.isTerminalWidget[i] = false;
                    }
                    defineTerminalWidget(dependencyNode.run, i);
                }
            }
        }
        for (Dependency dependency2 : widgetRun.end.dependencies) {
            if (dependency2 instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency2;
                if (dependencyNode2.run != widgetRun && dependencyNode2 == dependencyNode2.run.start) {
                    if (widgetRun instanceof ChainRun) {
                        Iterator it2 = ((ChainRun) widgetRun).widgets.iterator();
                        while (it2.hasNext()) {
                            defineTerminalWidget((WidgetRun) it2.next(), i);
                        }
                    } else if (!(widgetRun instanceof HelperReferences)) {
                        widgetRun.widget.isTerminalWidget[i] = false;
                    }
                    defineTerminalWidget(dependencyNode2.run, i);
                }
            }
        }
        return false;
    }

    public void defineTerminalWidgets(boolean z, boolean z2) {
        if (z) {
            WidgetRun widgetRun = this.firstRun;
            if (widgetRun instanceof HorizontalWidgetRun) {
                defineTerminalWidget(widgetRun, 0);
            }
        }
        if (z2) {
            WidgetRun widgetRun2 = this.firstRun;
            if (widgetRun2 instanceof VerticalWidgetRun) {
                defineTerminalWidget(widgetRun2, 1);
            }
        }
    }
}
