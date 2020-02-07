package androidx.constraintlayout.solver.state.helpers;

import androidx.constraintlayout.solver.state.HelperReference;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.solver.state.State.Direction;
import androidx.constraintlayout.solver.state.State.Helper;
import androidx.constraintlayout.solver.widgets.Barrier;
import androidx.constraintlayout.solver.widgets.HelperWidget;

public class BarrierReference extends HelperReference {
    private Barrier mBarrierWidget;
    private Direction mDirection;
    private int mMargin;

    public BarrierReference(State state) {
        super(state, Helper.BARRIER);
    }

    public void setBarrierDirection(Direction direction) {
        this.mDirection = direction;
    }

    public void margin(Object obj) {
        margin(this.mState.convertDimension(obj));
    }

    public void margin(int i) {
        this.mMargin = i;
    }

    public HelperWidget getHelperWidget() {
        if (this.mBarrierWidget == null) {
            this.mBarrierWidget = new Barrier();
        }
        return this.mBarrierWidget;
    }

    public void apply() {
        getHelperWidget();
        int i = 0;
        switch (this.mDirection) {
            case RIGHT:
            case END:
                i = 1;
                break;
            case TOP:
                i = 2;
                break;
            case BOTTOM:
                i = 3;
                break;
        }
        this.mBarrierWidget.setBarrierType(i);
        this.mBarrierWidget.setMargin(this.mMargin);
    }
}
