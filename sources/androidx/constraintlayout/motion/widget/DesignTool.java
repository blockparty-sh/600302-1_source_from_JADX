package androidx.constraintlayout.motion.widget;

import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.C0215R;
import androidx.constraintlayout.widget.ConstraintSet;
import java.io.PrintStream;
import java.util.HashMap;

public class DesignTool implements ProxyInterface {
    private static final boolean DEBUG = false;
    private static final String TAG = "DesignTool";
    static final HashMap<Pair<Integer, Integer>, String> allAttributes = new HashMap<>();
    static final HashMap<String, String> allMargins = new HashMap<>();
    private String mLastEndState = null;
    private int mLastEndStateId = -1;
    private String mLastStartState = null;
    private int mLastStartStateId = -1;
    private final MotionLayout mMotionLayout;
    private MotionScene mSceneCache;

    public DesignTool(MotionLayout motionLayout) {
        this.mMotionLayout = motionLayout;
    }

    static {
        HashMap<Pair<Integer, Integer>, String> hashMap = allAttributes;
        Integer valueOf = Integer.valueOf(4);
        String str = "layout_constraintBottom_toBottomOf";
        hashMap.put(Pair.create(valueOf, valueOf), str);
        HashMap<Pair<Integer, Integer>, String> hashMap2 = allAttributes;
        Integer valueOf2 = Integer.valueOf(3);
        String str2 = "layout_constraintBottom_toTopOf";
        hashMap2.put(Pair.create(valueOf, valueOf2), str2);
        String str3 = "layout_constraintTop_toBottomOf";
        allAttributes.put(Pair.create(valueOf2, valueOf), str3);
        HashMap<Pair<Integer, Integer>, String> hashMap3 = allAttributes;
        Pair create = Pair.create(valueOf2, valueOf2);
        String str4 = "layout_constraintTop_toTopOf";
        hashMap3.put(create, str4);
        HashMap<Pair<Integer, Integer>, String> hashMap4 = allAttributes;
        Integer valueOf3 = Integer.valueOf(6);
        String str5 = "layout_constraintStart_toStartOf";
        hashMap4.put(Pair.create(valueOf3, valueOf3), str5);
        HashMap<Pair<Integer, Integer>, String> hashMap5 = allAttributes;
        Integer valueOf4 = Integer.valueOf(7);
        String str6 = "layout_constraintStart_toEndOf";
        hashMap5.put(Pair.create(valueOf3, valueOf4), str6);
        String str7 = "layout_constraintEnd_toStartOf";
        allAttributes.put(Pair.create(valueOf4, valueOf3), str7);
        HashMap<Pair<Integer, Integer>, String> hashMap6 = allAttributes;
        Pair create2 = Pair.create(valueOf4, valueOf4);
        String str8 = "layout_constraintEnd_toEndOf";
        hashMap6.put(create2, str8);
        HashMap<Pair<Integer, Integer>, String> hashMap7 = allAttributes;
        Integer valueOf5 = Integer.valueOf(1);
        String str9 = "layout_constraintLeft_toLeftOf";
        hashMap7.put(Pair.create(valueOf5, valueOf5), str9);
        HashMap<Pair<Integer, Integer>, String> hashMap8 = allAttributes;
        Integer valueOf6 = Integer.valueOf(2);
        hashMap8.put(Pair.create(valueOf5, valueOf6), "layout_constraintLeft_toRightOf");
        allAttributes.put(Pair.create(valueOf6, valueOf6), "layout_constraintRight_toRightOf");
        allAttributes.put(Pair.create(valueOf6, valueOf5), "layout_constraintRight_toLeftOf");
        allAttributes.put(Pair.create(Integer.valueOf(5), Integer.valueOf(5)), "layout_constraintBaseline_toBaselineOf");
        allMargins.put(str, "layout_marginBottom");
        allMargins.put(str2, "layout_marginBottom");
        allMargins.put(str3, "layout_marginTop");
        allMargins.put(str4, "layout_marginTop");
        allMargins.put(str5, "layout_marginStart");
        allMargins.put(str6, "layout_marginStart");
        allMargins.put(str7, "layout_marginEnd");
        allMargins.put(str8, "layout_marginEnd");
        allMargins.put(str9, "layout_marginLeft");
        allMargins.put("layout_constraintLeft_toRightOf", "layout_marginLeft");
        allMargins.put("layout_constraintRight_toRightOf", "layout_marginRight");
        allMargins.put("layout_constraintRight_toLeftOf", "layout_marginRight");
    }

    private static int GetPxFromDp(int i, String str) {
        if (str == null) {
            return 0;
        }
        int indexOf = str.indexOf(100);
        if (indexOf == -1) {
            return 0;
        }
        return (int) (((float) (Integer.valueOf(str.substring(0, indexOf)).intValue() * i)) / 160.0f);
    }

    private static void Connect(int i, ConstraintSet constraintSet, View view, HashMap<String, String> hashMap, int i2, int i3) {
        String str = (String) allAttributes.get(Pair.create(Integer.valueOf(i2), Integer.valueOf(i3)));
        String str2 = (String) hashMap.get(str);
        if (str2 != null) {
            String str3 = (String) allMargins.get(str);
            constraintSet.connect(view.getId(), i2, Integer.parseInt(str2), i3, str3 != null ? GetPxFromDp(i, (String) hashMap.get(str3)) : 0);
        }
    }

    private static void SetBias(ConstraintSet constraintSet, View view, HashMap<String, String> hashMap, int i) {
        String str = (String) hashMap.get(i == 1 ? "layout_constraintVertical_bias" : "layout_constraintHorizontal_bias");
        if (str == null) {
            return;
        }
        if (i == 0) {
            constraintSet.setHorizontalBias(view.getId(), Float.parseFloat(str));
        } else if (i == 1) {
            constraintSet.setVerticalBias(view.getId(), Float.parseFloat(str));
        }
    }

    private static void SetDimensions(int i, ConstraintSet constraintSet, View view, HashMap<String, String> hashMap, int i2) {
        String str = (String) hashMap.get(i2 == 1 ? "layout_height" : "layout_width");
        if (str != null) {
            int i3 = -2;
            if (!str.equalsIgnoreCase("wrap_content")) {
                i3 = GetPxFromDp(i, str);
            }
            if (i2 == 0) {
                constraintSet.constrainWidth(view.getId(), i3);
            } else {
                constraintSet.constrainHeight(view.getId(), i3);
            }
        }
    }

    private static void SetAbsolutePositions(int i, ConstraintSet constraintSet, View view, HashMap<String, String> hashMap) {
        String str = (String) hashMap.get("layout_editor_absoluteX");
        if (str != null) {
            constraintSet.setEditorAbsoluteX(view.getId(), GetPxFromDp(i, str));
        }
        String str2 = (String) hashMap.get("layout_editor_absoluteY");
        if (str2 != null) {
            constraintSet.setEditorAbsoluteY(view.getId(), GetPxFromDp(i, str2));
        }
    }

    public int getAnimationPath(Object obj, float[] fArr, int i) {
        if (this.mMotionLayout.mScene == null) {
            return -1;
        }
        MotionController motionController = (MotionController) this.mMotionLayout.mFrameArrayList.get(obj);
        if (motionController == null) {
            return 0;
        }
        motionController.buildPath(fArr, i);
        return i;
    }

    public void getAnimationRectangles(Object obj, float[] fArr) {
        if (this.mMotionLayout.mScene != null) {
            int duration = this.mMotionLayout.mScene.getDuration() / 16;
            MotionController motionController = (MotionController) this.mMotionLayout.mFrameArrayList.get(obj);
            if (motionController != null) {
                motionController.buildRectangles(fArr, duration);
            }
        }
    }

    public int getAnimationKeyFrames(Object obj, float[] fArr) {
        if (this.mMotionLayout.mScene == null) {
            return -1;
        }
        int duration = this.mMotionLayout.mScene.getDuration() / 16;
        MotionController motionController = (MotionController) this.mMotionLayout.mFrameArrayList.get(obj);
        if (motionController == null) {
            return 0;
        }
        motionController.buildKeyFrames(fArr, null);
        return duration;
    }

    public void setToolPosition(float f) {
        if (this.mMotionLayout.mScene == null) {
            this.mMotionLayout.mScene = this.mSceneCache;
        }
        this.mMotionLayout.setProgress(f);
        this.mMotionLayout.evaluate(true);
        this.mMotionLayout.requestLayout();
        this.mMotionLayout.invalidate();
    }

    public void setState(String str) {
        if (str == null) {
            str = "motion_base";
        }
        if (this.mLastStartState != str) {
            this.mLastStartState = str;
            this.mLastEndState = null;
            if (this.mMotionLayout.mScene == null) {
                this.mMotionLayout.mScene = this.mSceneCache;
            }
            int lookUpConstraintId = str != null ? this.mMotionLayout.lookUpConstraintId(str) : C0215R.C0218id.motion_base;
            this.mLastStartStateId = lookUpConstraintId;
            if (lookUpConstraintId != 0) {
                if (lookUpConstraintId == this.mMotionLayout.getStartState()) {
                    this.mMotionLayout.setProgress(0.0f);
                } else if (lookUpConstraintId == this.mMotionLayout.getEndState()) {
                    this.mMotionLayout.setProgress(1.0f);
                } else {
                    this.mMotionLayout.transitionToState(lookUpConstraintId);
                    this.mMotionLayout.setProgress(1.0f);
                }
            }
            this.mMotionLayout.requestLayout();
        }
    }

    public String getStartState() {
        int startState = this.mMotionLayout.getStartState();
        if (this.mLastStartStateId == startState) {
            return this.mLastStartState;
        }
        String constraintSetNames = this.mMotionLayout.getConstraintSetNames(startState);
        if (constraintSetNames != null) {
            this.mLastStartState = constraintSetNames;
            this.mLastStartStateId = startState;
        }
        return this.mMotionLayout.getConstraintSetNames(startState);
    }

    public String getEndState() {
        int endState = this.mMotionLayout.getEndState();
        if (this.mLastEndStateId == endState) {
            return this.mLastEndState;
        }
        String constraintSetNames = this.mMotionLayout.getConstraintSetNames(endState);
        if (constraintSetNames != null) {
            this.mLastEndState = constraintSetNames;
            this.mLastEndStateId = endState;
        }
        return constraintSetNames;
    }

    public float getProgress() {
        return this.mMotionLayout.getProgress();
    }

    public String getState() {
        if (!(this.mLastStartState == null || this.mLastEndState == null)) {
            float progress = getProgress();
            if (progress <= 0.01f) {
                return this.mLastStartState;
            }
            if (progress >= 0.99f) {
                return this.mLastEndState;
            }
        }
        return this.mLastStartState;
    }

    public boolean isInTransition() {
        return (this.mLastStartState == null || this.mLastEndState == null) ? false : true;
    }

    public void setTransition(String str, String str2) {
        if (this.mMotionLayout.mScene == null) {
            this.mMotionLayout.mScene = this.mSceneCache;
        }
        int lookUpConstraintId = this.mMotionLayout.lookUpConstraintId(str);
        int lookUpConstraintId2 = this.mMotionLayout.lookUpConstraintId(str2);
        this.mMotionLayout.setTransition(lookUpConstraintId, lookUpConstraintId2);
        this.mLastStartStateId = lookUpConstraintId;
        this.mLastEndStateId = lookUpConstraintId2;
        this.mLastStartState = str;
        this.mLastEndState = str2;
    }

    public void disableAutoTransition(boolean z) {
        this.mMotionLayout.disableAutoTransition(z);
    }

    public long getTransitionTimeMs() {
        return this.mMotionLayout.getTransitionTimeMs();
    }

    public int getKeyFramePositions(Object obj, int[] iArr, float[] fArr) {
        return ((MotionController) this.mMotionLayout.mFrameArrayList.get((View) obj)).getkeyFramePositions(iArr, fArr);
    }

    public float getKeyFramePosition(Object obj, int i, float f, float f2) {
        return ((MotionController) this.mMotionLayout.mFrameArrayList.get((View) obj)).getKeyFrameParameter(i, f, f2);
    }

    public void setKeyFrame(Object obj, int i, String str, Object obj2) {
        if (this.mMotionLayout.mScene != null) {
            this.mMotionLayout.mScene.setKeyframe((View) obj, i, str, obj2);
            MotionLayout motionLayout = this.mMotionLayout;
            motionLayout.mTransitionGoalPosition = ((float) i) / 100.0f;
            motionLayout.mTransitionLastPosition = 0.0f;
            motionLayout.rebuildScene();
            this.mMotionLayout.evaluate(true);
        }
    }

    public boolean setKeyFramePosition(Object obj, int i, int i2, float f, float f2) {
        if (this.mMotionLayout.mScene != null) {
            MotionController motionController = (MotionController) this.mMotionLayout.mFrameArrayList.get(obj);
            int i3 = (int) (this.mMotionLayout.mTransitionPosition * 100.0f);
            if (motionController != null) {
                View view = (View) obj;
                if (this.mMotionLayout.mScene.hasKeyFramePosition(view, i3)) {
                    float keyFrameParameter = motionController.getKeyFrameParameter(2, f, f2);
                    float keyFrameParameter2 = motionController.getKeyFrameParameter(5, f, f2);
                    this.mMotionLayout.mScene.setKeyframe(view, i3, "motion:percentX", Float.valueOf(keyFrameParameter));
                    this.mMotionLayout.mScene.setKeyframe(view, i3, "motion:percentY", Float.valueOf(keyFrameParameter2));
                    this.mMotionLayout.rebuildScene();
                    this.mMotionLayout.evaluate(true);
                    this.mMotionLayout.invalidate();
                    return true;
                }
            }
        }
        return false;
    }

    public void setViewDebug(Object obj, int i) {
        MotionController motionController = (MotionController) this.mMotionLayout.mFrameArrayList.get(obj);
        if (motionController != null) {
            motionController.setDrawPath(i);
            this.mMotionLayout.invalidate();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0019, code lost:
        if (r4 == null) goto L_0x001b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int designAccess(int r2, java.lang.String r3, java.lang.Object r4, float[] r5, int r6, float[] r7, int r8) {
        /*
            r1 = this;
            android.view.View r4 = (android.view.View) r4
            r5 = 0
            r6 = -1
            if (r2 == 0) goto L_0x001c
            androidx.constraintlayout.motion.widget.MotionLayout r0 = r1.mMotionLayout
            androidx.constraintlayout.motion.widget.MotionScene r0 = r0.mScene
            if (r0 != 0) goto L_0x000d
            return r6
        L_0x000d:
            if (r4 == 0) goto L_0x001b
            androidx.constraintlayout.motion.widget.MotionLayout r0 = r1.mMotionLayout
            java.util.HashMap<android.view.View, androidx.constraintlayout.motion.widget.MotionController> r0 = r0.mFrameArrayList
            java.lang.Object r4 = r0.get(r4)
            androidx.constraintlayout.motion.widget.MotionController r4 = (androidx.constraintlayout.motion.widget.MotionController) r4
            if (r4 != 0) goto L_0x001d
        L_0x001b:
            return r6
        L_0x001c:
            r4 = r5
        L_0x001d:
            r0 = 1
            if (r2 == 0) goto L_0x0054
            if (r2 == r0) goto L_0x0046
            r0 = 2
            if (r2 == r0) goto L_0x0038
            r5 = 3
            if (r2 == r5) goto L_0x0029
            return r6
        L_0x0029:
            androidx.constraintlayout.motion.widget.MotionLayout r2 = r1.mMotionLayout
            androidx.constraintlayout.motion.widget.MotionScene r2 = r2.mScene
            int r2 = r2.getDuration()
            int r2 = r2 / 16
            int r2 = r4.getAttributeValues(r3, r7, r8)
            return r2
        L_0x0038:
            androidx.constraintlayout.motion.widget.MotionLayout r2 = r1.mMotionLayout
            androidx.constraintlayout.motion.widget.MotionScene r2 = r2.mScene
            int r2 = r2.getDuration()
            int r2 = r2 / 16
            r4.buildKeyFrames(r7, r5)
            return r2
        L_0x0046:
            androidx.constraintlayout.motion.widget.MotionLayout r2 = r1.mMotionLayout
            androidx.constraintlayout.motion.widget.MotionScene r2 = r2.mScene
            int r2 = r2.getDuration()
            int r2 = r2 / 16
            r4.buildPath(r7, r2)
            return r2
        L_0x0054:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.DesignTool.designAccess(int, java.lang.String, java.lang.Object, float[], int, float[], int):int");
    }

    public Object getKeyframe(int i, int i2, int i3) {
        if (this.mMotionLayout.mScene == null) {
            return null;
        }
        return this.mMotionLayout.mScene.getKeyFrame(this.mMotionLayout.getContext(), i, i2, i3);
    }

    public Object getKeyframeAtLocation(Object obj, float f, float f2) {
        View view = (View) obj;
        if (this.mMotionLayout.mScene == null) {
            return Integer.valueOf(-1);
        }
        if (view == null) {
            return null;
        }
        MotionController motionController = (MotionController) this.mMotionLayout.mFrameArrayList.get(view);
        if (motionController == null) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        return motionController.getPositionKeyframe(viewGroup.getWidth(), viewGroup.getHeight(), f, f2);
    }

    public Boolean getPositionKeyframe(Object obj, Object obj2, float f, float f2, String[] strArr, float[] fArr) {
        if (!(obj instanceof KeyPositionBase)) {
            return Boolean.valueOf(false);
        }
        View view = (View) obj2;
        ((MotionController) this.mMotionLayout.mFrameArrayList.get(view)).positionKeyframe(view, (KeyPositionBase) obj, f, f2, strArr, fArr);
        this.mMotionLayout.rebuildScene();
        this.mMotionLayout.mInTransition = true;
        return Boolean.valueOf(true);
    }

    public Object getKeyframe(Object obj, int i, int i2) {
        if (this.mMotionLayout.mScene == null) {
            return null;
        }
        return this.mMotionLayout.mScene.getKeyFrame(this.mMotionLayout.getContext(), i, ((View) obj).getId(), i2);
    }

    public void setKeyframe(Object obj, String str, Object obj2) {
        if (obj instanceof C0188Key) {
            ((C0188Key) obj).setValue(str, obj2);
            this.mMotionLayout.rebuildScene();
            this.mMotionLayout.mInTransition = true;
        }
    }

    public void setAttributes(int i, String str, Object obj, Object obj2) {
        View view = (View) obj;
        HashMap hashMap = (HashMap) obj2;
        int lookUpConstraintId = this.mMotionLayout.lookUpConstraintId(str);
        ConstraintSet constraintSet = this.mMotionLayout.mScene.getConstraintSet(lookUpConstraintId);
        if (constraintSet != null) {
            constraintSet.clear(view.getId());
            SetDimensions(i, constraintSet, view, hashMap, 0);
            SetDimensions(i, constraintSet, view, hashMap, 1);
            int i2 = i;
            ConstraintSet constraintSet2 = constraintSet;
            View view2 = view;
            HashMap hashMap2 = hashMap;
            Connect(i2, constraintSet2, view2, hashMap2, 6, 6);
            Connect(i2, constraintSet2, view2, hashMap2, 6, 7);
            Connect(i2, constraintSet2, view2, hashMap2, 7, 7);
            Connect(i2, constraintSet2, view2, hashMap2, 7, 6);
            Connect(i2, constraintSet2, view2, hashMap2, 1, 1);
            Connect(i2, constraintSet2, view2, hashMap2, 1, 2);
            Connect(i2, constraintSet2, view2, hashMap2, 2, 2);
            Connect(i2, constraintSet2, view2, hashMap2, 2, 1);
            Connect(i2, constraintSet2, view2, hashMap2, 3, 3);
            Connect(i2, constraintSet2, view2, hashMap2, 3, 4);
            Connect(i2, constraintSet2, view2, hashMap2, 4, 3);
            Connect(i2, constraintSet2, view2, hashMap2, 4, 4);
            Connect(i2, constraintSet2, view2, hashMap2, 5, 5);
            SetBias(constraintSet, view, hashMap, 0);
            SetBias(constraintSet, view, hashMap, 1);
            SetAbsolutePositions(i, constraintSet, view, hashMap);
            this.mMotionLayout.updateState(lookUpConstraintId, constraintSet);
            this.mMotionLayout.requestLayout();
        }
    }

    public void dumpConstraintSet(String str) {
        if (this.mMotionLayout.mScene == null) {
            this.mMotionLayout.mScene = this.mSceneCache;
        }
        int lookUpConstraintId = this.mMotionLayout.lookUpConstraintId(str);
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append(" dumping  ");
        sb.append(str);
        sb.append(" (");
        sb.append(lookUpConstraintId);
        sb.append(")");
        printStream.println(sb.toString());
        try {
            this.mMotionLayout.mScene.getConstraintSet(lookUpConstraintId).dump(this.mMotionLayout.mScene, new int[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
