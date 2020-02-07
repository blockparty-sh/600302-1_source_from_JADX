package androidx.constraintlayout.motion.widget;

import android.graphics.RectF;
import android.view.View;
import java.util.HashSet;

abstract class KeyPositionBase extends C0188Key {
    protected static final float SELECTION_SLOPE = 20.0f;
    int mCurveFit = UNSET;

    /* access modifiers changed from: 0000 */
    public abstract void calcPosition(int i, int i2, float f, float f2, float f3, float f4);

    /* access modifiers changed from: 0000 */
    public void getAttributeNames(HashSet<String> hashSet) {
    }

    /* access modifiers changed from: 0000 */
    public abstract float getPositionX();

    /* access modifiers changed from: 0000 */
    public abstract float getPositionY();

    public abstract boolean intersects(int i, int i2, RectF rectF, RectF rectF2, float f, float f2);

    /* access modifiers changed from: 0000 */
    public abstract void positionAttributes(View view, RectF rectF, RectF rectF2, float f, float f2, String[] strArr, float[] fArr);

    KeyPositionBase() {
    }
}
