package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.Cache;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.SolverVariable.C0194Type;
import com.microsoft.appcenter.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ConstraintAnchor {
    private static final boolean ALLOW_BINARY = false;
    private static final int UNSET_GONE_MARGIN = -1;
    int mGoneMargin = -1;
    public int mMargin = 0;
    public final ConstraintWidget mOwner;
    SolverVariable mSolverVariable;
    public ConstraintAnchor mTarget;
    public final C0202Type mType;

    /* renamed from: androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type */
    public enum C0202Type {
        NONE,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        BASELINE,
        CENTER,
        CENTER_X,
        CENTER_Y
    }

    public void copyFrom(ConstraintAnchor constraintAnchor, HashMap<ConstraintWidget, ConstraintWidget> hashMap) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 != null) {
            this.mTarget = ((ConstraintWidget) hashMap.get(constraintAnchor.mTarget.mOwner)).getAnchor(constraintAnchor2.getType());
        } else {
            this.mTarget = null;
        }
        this.mMargin = constraintAnchor.mMargin;
        this.mGoneMargin = constraintAnchor.mGoneMargin;
    }

    public ConstraintAnchor(ConstraintWidget constraintWidget, C0202Type type) {
        this.mOwner = constraintWidget;
        this.mType = type;
    }

    public SolverVariable getSolverVariable() {
        return this.mSolverVariable;
    }

    public void resetSolverVariable(Cache cache) {
        SolverVariable solverVariable = this.mSolverVariable;
        if (solverVariable == null) {
            this.mSolverVariable = new SolverVariable(C0194Type.UNRESTRICTED, (String) null);
        } else {
            solverVariable.reset();
        }
    }

    public ConstraintWidget getOwner() {
        return this.mOwner;
    }

    public C0202Type getType() {
        return this.mType;
    }

    public int getMargin() {
        if (this.mOwner.getVisibility() == 8) {
            return 0;
        }
        if (this.mGoneMargin > -1) {
            ConstraintAnchor constraintAnchor = this.mTarget;
            if (constraintAnchor != null && constraintAnchor.mOwner.getVisibility() == 8) {
                return this.mGoneMargin;
            }
        }
        return this.mMargin;
    }

    public ConstraintAnchor getTarget() {
        return this.mTarget;
    }

    public void reset() {
        this.mTarget = null;
        this.mMargin = 0;
        this.mGoneMargin = -1;
    }

    public boolean connect(ConstraintAnchor constraintAnchor, int i, int i2, boolean z) {
        if (constraintAnchor == null) {
            this.mTarget = null;
            this.mMargin = 0;
            this.mGoneMargin = -1;
            return true;
        } else if (!z && !isValidConnection(constraintAnchor)) {
            return false;
        } else {
            this.mTarget = constraintAnchor;
            if (i > 0) {
                this.mMargin = i;
            } else {
                this.mMargin = 0;
            }
            this.mGoneMargin = i2;
            return true;
        }
    }

    public boolean connect(ConstraintAnchor constraintAnchor, int i) {
        return connect(constraintAnchor, i, -1, false);
    }

    public boolean isConnected() {
        return this.mTarget != null;
    }

    public boolean isValidConnection(ConstraintAnchor constraintAnchor) {
        boolean z = false;
        if (constraintAnchor == null) {
            return false;
        }
        C0202Type type = constraintAnchor.getType();
        C0202Type type2 = this.mType;
        if (type != type2) {
            switch (this.mType) {
                case CENTER:
                    if (!(type == C0202Type.BASELINE || type == C0202Type.CENTER_X || type == C0202Type.CENTER_Y)) {
                        z = true;
                    }
                    return z;
                case LEFT:
                case RIGHT:
                    boolean z2 = type == C0202Type.LEFT || type == C0202Type.RIGHT;
                    if (constraintAnchor.getOwner() instanceof Guideline) {
                        z2 = z2 || type == C0202Type.CENTER_X;
                    }
                    return z2;
                case TOP:
                case BOTTOM:
                    boolean z3 = type == C0202Type.TOP || type == C0202Type.BOTTOM;
                    if (constraintAnchor.getOwner() instanceof Guideline) {
                        z3 = z3 || type == C0202Type.CENTER_Y;
                    }
                    return z3;
                case BASELINE:
                case CENTER_X:
                case CENTER_Y:
                case NONE:
                    return false;
                default:
                    throw new AssertionError(this.mType.name());
            }
        } else if (type2 != C0202Type.BASELINE || (constraintAnchor.getOwner().hasBaseline() && getOwner().hasBaseline())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSideAnchor() {
        switch (this.mType) {
            case CENTER:
            case BASELINE:
            case CENTER_X:
            case CENTER_Y:
            case NONE:
                return false;
            case LEFT:
            case RIGHT:
            case TOP:
            case BOTTOM:
                return true;
            default:
                throw new AssertionError(this.mType.name());
        }
    }

    public boolean isSimilarDimensionConnection(ConstraintAnchor constraintAnchor) {
        C0202Type type = constraintAnchor.getType();
        boolean z = true;
        if (type == this.mType) {
            return true;
        }
        switch (this.mType) {
            case CENTER:
                if (type == C0202Type.BASELINE) {
                    z = false;
                }
                return z;
            case LEFT:
            case RIGHT:
            case CENTER_X:
                if (!(type == C0202Type.LEFT || type == C0202Type.RIGHT || type == C0202Type.CENTER_X)) {
                    z = false;
                }
                return z;
            case TOP:
            case BOTTOM:
            case BASELINE:
            case CENTER_Y:
                if (!(type == C0202Type.TOP || type == C0202Type.BOTTOM || type == C0202Type.CENTER_Y || type == C0202Type.BASELINE)) {
                    z = false;
                }
                return z;
            case NONE:
                return false;
            default:
                throw new AssertionError(this.mType.name());
        }
    }

    public void setMargin(int i) {
        if (isConnected()) {
            this.mMargin = i;
        }
    }

    public void setGoneMargin(int i) {
        if (isConnected()) {
            this.mGoneMargin = i;
        }
    }

    public boolean isVerticalAnchor() {
        switch (this.mType) {
            case CENTER:
            case LEFT:
            case RIGHT:
            case CENTER_X:
                return false;
            case TOP:
            case BOTTOM:
            case BASELINE:
            case CENTER_Y:
            case NONE:
                return true;
            default:
                throw new AssertionError(this.mType.name());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mOwner.getDebugName());
        sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
        sb.append(this.mType.toString());
        return sb.toString();
    }

    public boolean isConnectionAllowed(ConstraintWidget constraintWidget, ConstraintAnchor constraintAnchor) {
        return isConnectionAllowed(constraintWidget);
    }

    public boolean isConnectionAllowed(ConstraintWidget constraintWidget) {
        if (isConnectionToMe(constraintWidget, new HashSet())) {
            return false;
        }
        ConstraintWidget parent = getOwner().getParent();
        if (parent == constraintWidget || constraintWidget.getParent() == parent) {
            return true;
        }
        return false;
    }

    private boolean isConnectionToMe(ConstraintWidget constraintWidget, HashSet<ConstraintWidget> hashSet) {
        if (hashSet.contains(constraintWidget)) {
            return false;
        }
        hashSet.add(constraintWidget);
        if (constraintWidget == getOwner()) {
            return true;
        }
        ArrayList anchors = constraintWidget.getAnchors();
        int size = anchors.size();
        for (int i = 0; i < size; i++) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) anchors.get(i);
            if (constraintAnchor.isSimilarDimensionConnection(this) && constraintAnchor.isConnected() && isConnectionToMe(constraintAnchor.getTarget().getOwner(), hashSet)) {
                return true;
            }
        }
        return false;
    }

    public final ConstraintAnchor getOpposite() {
        switch (this.mType) {
            case CENTER:
            case BASELINE:
            case CENTER_X:
            case CENTER_Y:
            case NONE:
                return null;
            case LEFT:
                return this.mOwner.mRight;
            case RIGHT:
                return this.mOwner.mLeft;
            case TOP:
                return this.mOwner.mBottom;
            case BOTTOM:
                return this.mOwner.mTop;
            default:
                throw new AssertionError(this.mType.name());
        }
    }
}
