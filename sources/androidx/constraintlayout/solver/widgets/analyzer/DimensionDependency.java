package androidx.constraintlayout.solver.widgets.analyzer;

class DimensionDependency extends DependencyNode {
    public int wrapValue;

    public DimensionDependency(WidgetRun widgetRun) {
        super(widgetRun);
        if (widgetRun instanceof HorizontalWidgetRun) {
            this.type = C0205Type.HORIZONTAL_DIMENSION;
        } else {
            this.type = C0205Type.VERTICAL_DIMENSION;
        }
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
}
