package androidx.constraintlayout.solver.state.helpers;

import androidx.constraintlayout.solver.state.ConstraintReference;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.solver.state.State.Chain;
import androidx.constraintlayout.solver.state.State.Helper;
import java.util.Iterator;

public class VerticalChainReference extends ChainReference {
    private Object mBottomToBottom;
    private Object mBottomToTop;
    private Object mTopToBottom;
    private Object mTopToTop;

    /* renamed from: androidx.constraintlayout.solver.state.helpers.VerticalChainReference$1 */
    static /* synthetic */ class C02001 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$solver$state$State$Chain = new int[Chain.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                androidx.constraintlayout.solver.state.State$Chain[] r0 = androidx.constraintlayout.solver.state.State.Chain.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$constraintlayout$solver$state$State$Chain = r0
                int[] r0 = $SwitchMap$androidx$constraintlayout$solver$state$State$Chain     // Catch:{ NoSuchFieldError -> 0x0014 }
                androidx.constraintlayout.solver.state.State$Chain r1 = androidx.constraintlayout.solver.state.State.Chain.SPREAD     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$androidx$constraintlayout$solver$state$State$Chain     // Catch:{ NoSuchFieldError -> 0x001f }
                androidx.constraintlayout.solver.state.State$Chain r1 = androidx.constraintlayout.solver.state.State.Chain.SPREAD_INSIDE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$androidx$constraintlayout$solver$state$State$Chain     // Catch:{ NoSuchFieldError -> 0x002a }
                androidx.constraintlayout.solver.state.State$Chain r1 = androidx.constraintlayout.solver.state.State.Chain.PACKED     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.state.helpers.VerticalChainReference.C02001.<clinit>():void");
        }
    }

    public VerticalChainReference(State state) {
        super(state, Helper.VERTICAL_CHAIN);
    }

    public void apply() {
        Iterator it = this.mReferences.iterator();
        while (it.hasNext()) {
            this.mState.constraints(it.next()).clearVertical();
        }
        Iterator it2 = this.mReferences.iterator();
        ConstraintReference constraintReference = null;
        ConstraintReference constraintReference2 = null;
        while (it2.hasNext()) {
            ConstraintReference constraints = this.mState.constraints(it2.next());
            if (constraintReference2 == null) {
                Object obj = this.mTopToTop;
                if (obj != null) {
                    constraints.topToTop(obj);
                } else {
                    Object obj2 = this.mTopToBottom;
                    if (obj2 != null) {
                        constraints.topToBottom(obj2);
                    } else {
                        constraints.topToTop(State.PARENT);
                    }
                }
                constraintReference2 = constraints;
            }
            if (constraintReference != null) {
                constraintReference.bottomToTop(constraints.getKey());
                constraints.topToBottom(constraintReference.getKey());
            }
            constraintReference = constraints;
        }
        if (constraintReference != null) {
            Object obj3 = this.mBottomToTop;
            if (obj3 != null) {
                constraintReference.bottomToTop(obj3);
            } else {
                Object obj4 = this.mBottomToBottom;
                if (obj4 != null) {
                    constraintReference.bottomToBottom(obj4);
                } else {
                    constraintReference.bottomToBottom(State.PARENT);
                }
            }
        }
        if (!(constraintReference2 == null || this.mBias == 0.5f)) {
            constraintReference2.verticalBias(this.mBias);
        }
        int i = C02001.$SwitchMap$androidx$constraintlayout$solver$state$State$Chain[this.mStyle.ordinal()];
        if (i == 1) {
            constraintReference2.setVerticalChainStyle(0);
        } else if (i == 2) {
            constraintReference2.setVerticalChainStyle(1);
        } else if (i == 3) {
            constraintReference2.setVerticalChainStyle(2);
        }
    }

    public void topToTop(Object obj) {
        this.mTopToTop = obj;
    }

    public void topToBottom(Object obj) {
        this.mTopToBottom = obj;
    }

    public void bottomToTop(Object obj) {
        this.mBottomToTop = obj;
    }

    public void bottomToBottom(Object obj) {
        this.mBottomToBottom = obj;
    }
}
