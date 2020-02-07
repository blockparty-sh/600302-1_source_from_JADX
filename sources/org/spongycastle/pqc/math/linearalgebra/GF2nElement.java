package org.spongycastle.pqc.math.linearalgebra;

public abstract class GF2nElement implements GFElement {
    protected int mDegree;
    protected GF2nField mField;

    /* access modifiers changed from: 0000 */
    public abstract void assignOne();

    /* access modifiers changed from: 0000 */
    public abstract void assignZero();

    public abstract Object clone();

    public abstract GF2nElement increase();

    public abstract void increaseThis();

    public abstract GF2nElement solveQuadraticEquation() throws RuntimeException;

    public abstract GF2nElement square();

    public abstract GF2nElement squareRoot();

    public abstract void squareRootThis();

    public abstract void squareThis();

    /* access modifiers changed from: 0000 */
    public abstract boolean testBit(int i);

    public abstract boolean testRightmostBit();

    public abstract int trace();

    public final GF2nField getField() {
        return this.mField;
    }

    public final GFElement subtract(GFElement gFElement) throws RuntimeException {
        return add(gFElement);
    }

    public final void subtractFromThis(GFElement gFElement) {
        addToThis(gFElement);
    }

    public final GF2nElement convert(GF2nField gF2nField) throws RuntimeException {
        return this.mField.convert(this, gF2nField);
    }
}
