package androidx.constraintlayout.solver.widgets.analyzer;

import com.microsoft.appcenter.Constants;
import java.util.ArrayList;
import java.util.List;

public class DependencyNode implements Dependency {
    public boolean delegateToWidgetRun = false;
    List<Dependency> dependencies = new ArrayList();
    int margin;
    DimensionDependency marginDependency = null;
    int marginFactor = 1;
    public boolean readyToSolve = false;
    public boolean resolved = false;
    WidgetRun run;
    List<DependencyNode> targets = new ArrayList();
    C0205Type type = C0205Type.UNKNOWN;
    public Dependency updateDelegate = null;
    public int value;

    /* renamed from: androidx.constraintlayout.solver.widgets.analyzer.DependencyNode$Type */
    enum C0205Type {
        UNKNOWN,
        HORIZONTAL_DIMENSION,
        VERTICAL_DIMENSION,
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        BASELINE
    }

    public DependencyNode(WidgetRun widgetRun) {
        this.run = widgetRun;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.run.widget.getDebugName());
        sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
        sb.append(this.type);
        sb.append("(");
        sb.append(this.resolved ? Integer.valueOf(this.value) : "unresolved");
        sb.append(") <t=");
        sb.append(this.targets.size());
        sb.append(":d=");
        sb.append(this.dependencies.size());
        sb.append(">");
        return sb.toString();
    }

    public void resolve(int i) {
        if (!this.resolved) {
            this.resolved = true;
            this.value = i;
            for (Dependency dependency : this.dependencies) {
                dependency.update(dependency);
            }
        }
    }

    public void update(Dependency dependency) {
        for (DependencyNode dependencyNode : this.targets) {
            if (!dependencyNode.resolved) {
                return;
            }
        }
        this.readyToSolve = true;
        Dependency dependency2 = this.updateDelegate;
        if (dependency2 != null) {
            dependency2.update(this);
        }
        if (this.delegateToWidgetRun) {
            this.run.update(this);
            return;
        }
        DependencyNode dependencyNode2 = null;
        int i = 0;
        for (DependencyNode dependencyNode3 : this.targets) {
            if (!(dependencyNode3 instanceof DimensionDependency)) {
                i++;
                dependencyNode2 = dependencyNode3;
            }
        }
        if (dependencyNode2 != null && i == 1 && dependencyNode2.resolved) {
            DimensionDependency dimensionDependency = this.marginDependency;
            if (dimensionDependency != null) {
                if (dimensionDependency.resolved) {
                    this.margin = this.marginFactor * this.marginDependency.value;
                } else {
                    return;
                }
            }
            resolve(dependencyNode2.value + this.margin);
        }
        Dependency dependency3 = this.updateDelegate;
        if (dependency3 != null) {
            dependency3.update(this);
        }
    }

    public void addDependency(Dependency dependency) {
        this.dependencies.add(dependency);
        if (this.resolved) {
            dependency.update(dependency);
        }
    }

    public String name() {
        String str;
        String debugName = this.run.widget.getDebugName();
        if (this.type == C0205Type.LEFT || this.type == C0205Type.RIGHT) {
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
        sb3.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
        sb3.append(this.type.name());
        return sb3.toString();
    }

    public void clear() {
        this.targets.clear();
        this.dependencies.clear();
        this.resolved = false;
        this.value = 0;
        this.readyToSolve = false;
        this.delegateToWidgetRun = false;
    }
}
