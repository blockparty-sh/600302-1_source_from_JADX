package androidx.constraintlayout.solver.state.helpers;

import androidx.constraintlayout.solver.state.HelperReference;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.solver.state.State.Chain;
import androidx.constraintlayout.solver.state.State.Helper;

public class ChainReference extends HelperReference {
    protected float mBias = 0.5f;
    protected Chain mStyle = Chain.SPREAD;

    public ChainReference(State state, Helper helper) {
        super(state, helper);
    }

    public Chain getStyle() {
        return Chain.SPREAD;
    }

    public void style(Chain chain) {
        this.mStyle = chain;
    }

    public float getBias() {
        return this.mBias;
    }

    public void bias(float f) {
        this.mBias = f;
    }
}
