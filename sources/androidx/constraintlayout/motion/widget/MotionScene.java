package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.constraintlayout.motion.utils.Easing;
import androidx.constraintlayout.widget.C0215R;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.StateSet;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.xmlpull.v1.XmlPullParser;

public class MotionScene {
    static final int ANTICIPATE = 4;
    static final int BOUNCE = 5;
    private static final boolean DEBUG = false;
    static final int EASE_IN = 1;
    static final int EASE_IN_OUT = 0;
    static final int EASE_OUT = 2;
    private static final int INTERPOLATOR_REFRENCE_ID = -2;
    static final int LINEAR = 3;
    private static final int SPLINE_STRING = -1;
    public static final String TAG = "MotionScene";
    static final int TRANSITION_BACKWARD = 0;
    static final int TRANSITION_FORWARD = 1;
    public static final int UNSET = -1;
    private boolean DEBUG_DESKTOP = false;
    private HashMap<String, Integer> mConstraintSetIdMap = new HashMap<>();
    /* access modifiers changed from: private */
    public SparseArray<ConstraintSet> mConstraintSetMap = new SparseArray<>();
    Transition mCurrentTransition = null;
    /* access modifiers changed from: private */
    public int mDefaultDuration = 100;
    private SparseIntArray mDeriveMap = new SparseIntArray();
    private boolean mDisableAutoTransition = false;
    private MotionEvent mLastTouchDown;
    float mLastTouchX;
    float mLastTouchY;
    /* access modifiers changed from: private */
    public final MotionLayout mMotionLayout;
    private boolean mMotionOutsideRegion = false;
    StateSet mStateSet = null;
    private ArrayList<Transition> mTransitionList = new ArrayList<>();
    private MotionTracker mVelocityTracker;

    public static class Transition {
        public static final int AUTO_ANIMATE_TO_END = 4;
        public static final int AUTO_ANIMATE_TO_START = 3;
        public static final int AUTO_JUMP_TO_END = 2;
        public static final int AUTO_JUMP_TO_START = 1;
        public static final int AUTO_NONE = 0;
        /* access modifiers changed from: private */
        public int mAutoTransition = 0;
        /* access modifiers changed from: private */
        public int mConstraintSetEnd = 0;
        /* access modifiers changed from: private */
        public int mConstraintSetStart = 0;
        /* access modifiers changed from: private */
        public int mDefaultInterpolator = 0;
        /* access modifiers changed from: private */
        public int mDefaultInterpolatorID = -1;
        /* access modifiers changed from: private */
        public String mDefaultInterpolatorString = null;
        /* access modifiers changed from: private */
        public boolean mDisable = false;
        /* access modifiers changed from: private */
        public int mDuration = 400;
        /* access modifiers changed from: private */
        public int mId = -1;
        /* access modifiers changed from: private */
        public ArrayList<KeyFrames> mKeyFramesList = new ArrayList<>();
        /* access modifiers changed from: private */
        public final MotionScene mMotionScene;
        /* access modifiers changed from: private */
        public ArrayList<TransitionOnClick> mOnClicks = new ArrayList<>();
        /* access modifiers changed from: private */
        public float mStagger = 0.0f;
        /* access modifiers changed from: private */
        public TouchResponse mTouchResponse = null;

        static class TransitionOnClick implements OnClickListener {
            public static final int ANIM_TOGGLE = 17;
            public static final int ANIM_TO_END = 1;
            public static final int ANIM_TO_START = 16;
            public static final int JUMP_TO_END = 256;
            public static final int JUMP_TO_START = 4096;
            int mMode = 17;
            int mTargetId = -1;
            private final Transition mTransition;

            public TransitionOnClick(Context context, Transition transition, XmlPullParser xmlPullParser) {
                this.mTransition = transition;
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), C0215R.styleable.OnClick);
                int indexCount = obtainStyledAttributes.getIndexCount();
                for (int i = 0; i < indexCount; i++) {
                    int index = obtainStyledAttributes.getIndex(i);
                    if (index == C0215R.styleable.OnClick_targetId) {
                        this.mTargetId = obtainStyledAttributes.getResourceId(index, this.mTargetId);
                    } else if (index == C0215R.styleable.OnClick_clickAction) {
                        this.mMode = obtainStyledAttributes.getInt(index, this.mMode);
                    }
                }
                obtainStyledAttributes.recycle();
            }

            /* JADX WARNING: type inference failed for: r3v1, types: [android.view.View] */
            /* JADX WARNING: type inference failed for: r3v4, types: [android.view.View] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* JADX WARNING: Unknown variable types count: 1 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void addOnClickListeners(androidx.constraintlayout.motion.widget.MotionLayout r3) {
                /*
                    r2 = this;
                    int r0 = r2.mTargetId
                    r1 = -1
                    if (r0 != r1) goto L_0x0006
                    goto L_0x000a
                L_0x0006:
                    android.view.View r3 = r3.findViewById(r0)
                L_0x000a:
                    if (r3 != 0) goto L_0x0025
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                    r3.<init>()
                    java.lang.String r0 = " (*)  could not find id "
                    r3.append(r0)
                    int r0 = r2.mTargetId
                    r3.append(r0)
                    java.lang.String r3 = r3.toString()
                    java.lang.String r0 = "MotionScene"
                    android.util.Log.e(r0, r3)
                    return
                L_0x0025:
                    r3.setOnClickListener(r2)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionScene.Transition.TransitionOnClick.addOnClickListeners(androidx.constraintlayout.motion.widget.MotionLayout):void");
            }

            public void removeOnClickListeners(MotionLayout motionLayout) {
                View findViewById = motionLayout.findViewById(this.mTargetId);
                if (findViewById == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(" (*)  could not find id ");
                    sb.append(this.mTargetId);
                    Log.e(MotionScene.TAG, sb.toString());
                    return;
                }
                findViewById.setOnClickListener(null);
            }

            /* access modifiers changed from: 0000 */
            public boolean isTransitionViable(Transition transition, boolean z, MotionLayout motionLayout) {
                Transition transition2 = this.mTransition;
                boolean z2 = true;
                if (transition2 == transition) {
                    return true;
                }
                int access$000 = z ? transition2.mConstraintSetEnd : transition2.mConstraintSetStart;
                int access$100 = z ? this.mTransition.mConstraintSetStart : this.mTransition.mConstraintSetEnd;
                if (motionLayout.getProgress() == 0.0f) {
                    if (motionLayout.mCurrentState != access$100) {
                        z2 = false;
                    }
                    return z2;
                } else if (motionLayout.getProgress() != 1.0f) {
                    return false;
                } else {
                    if (motionLayout.mCurrentState != access$000) {
                        z2 = false;
                    }
                    return z2;
                }
            }

            public void onClick(View view) {
                MotionLayout access$900 = this.mTransition.mMotionScene.mMotionLayout;
                Transition transition = this.mTransition.mMotionScene.mCurrentTransition;
                int i = this.mMode;
                boolean z = ((i & 1) == 0 && (i & 256) == 0) ? false : true;
                int i2 = this.mMode;
                boolean z2 = ((i2 & 16) == 0 && (i2 & 4096) == 0) ? false : true;
                if (z && z2) {
                    Transition transition2 = this.mTransition.mMotionScene.mCurrentTransition;
                    Transition transition3 = this.mTransition;
                    if (transition2 != transition3) {
                        access$900.setTransition(transition3);
                    }
                    if (access$900.getCurrentState() == access$900.getEndState() || access$900.getProgress() > 0.5f) {
                        z = false;
                    } else {
                        z2 = false;
                    }
                }
                if (z) {
                    if (!isTransitionViable(transition, true, access$900) || (this.mMode & 1) == 0) {
                        access$900.setProgress(1.0f);
                    } else {
                        access$900.transitionToEnd();
                    }
                } else if (!z2) {
                } else {
                    if (!isTransitionViable(transition, false, access$900) || (this.mMode & 16) == 0) {
                        access$900.setProgress(0.0f);
                    } else {
                        access$900.transitionToStart();
                    }
                }
            }
        }

        public void addOnClick(Context context, XmlPullParser xmlPullParser) {
            this.mOnClicks.add(new TransitionOnClick(context, this, xmlPullParser));
        }

        public int getEndConstraintSetId() {
            return this.mConstraintSetEnd;
        }

        public int getStartConstraintSetId() {
            return this.mConstraintSetStart;
        }

        public void setDuration(int i) {
            this.mDuration = i;
        }

        public int getDuration() {
            return this.mDuration;
        }

        public float getStagger() {
            return this.mStagger;
        }

        public List<KeyFrames> getKeyFrameList() {
            return this.mKeyFramesList;
        }

        public List<TransitionOnClick> getOnClickList() {
            return this.mOnClicks;
        }

        public TouchResponse getTouchResponse() {
            return this.mTouchResponse;
        }

        public void setStagger(float f) {
            this.mStagger = f;
        }

        public boolean isEnabled() {
            return !this.mDisable;
        }

        public void setEnable(boolean z) {
            this.mDisable = !z;
        }

        public String debugString(Context context) {
            String str;
            if (this.mConstraintSetEnd == -1) {
                str = "null";
            } else {
                str = context.getResources().getResourceEntryName(this.mConstraintSetStart);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" -> ");
            sb.append(context.getResources().getResourceEntryName(this.mConstraintSetEnd));
            return sb.toString();
        }

        Transition(MotionScene motionScene) {
            this.mMotionScene = motionScene;
        }

        public Transition(int i, MotionScene motionScene, int i2, int i3) {
            this.mId = i;
            this.mMotionScene = motionScene;
            this.mConstraintSetStart = i2;
            this.mConstraintSetEnd = i3;
        }

        Transition(MotionScene motionScene, Context context, XmlPullParser xmlPullParser) {
            this.mDuration = motionScene.mDefaultDuration;
            this.mMotionScene = motionScene;
            fillFromAttributeList(motionScene, context, Xml.asAttributeSet(xmlPullParser));
        }

        private void fillFromAttributeList(MotionScene motionScene, Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0215R.styleable.Transition);
            fill(motionScene, context, obtainStyledAttributes);
            obtainStyledAttributes.recycle();
        }

        private void fill(MotionScene motionScene, Context context, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                String str = "layout";
                if (index == C0215R.styleable.Transition_constraintSetEnd) {
                    this.mConstraintSetEnd = typedArray.getResourceId(index, this.mConstraintSetEnd);
                    if (str.equals(context.getResources().getResourceTypeName(this.mConstraintSetEnd))) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        constraintSet.load(context, this.mConstraintSetEnd);
                        motionScene.mConstraintSetMap.append(this.mConstraintSetEnd, constraintSet);
                    }
                } else if (index == C0215R.styleable.Transition_constraintSetStart) {
                    this.mConstraintSetStart = typedArray.getResourceId(index, this.mConstraintSetStart);
                    if (str.equals(context.getResources().getResourceTypeName(this.mConstraintSetStart))) {
                        ConstraintSet constraintSet2 = new ConstraintSet();
                        constraintSet2.load(context, this.mConstraintSetStart);
                        motionScene.mConstraintSetMap.append(this.mConstraintSetStart, constraintSet2);
                    }
                } else if (index == C0215R.styleable.Transition_motionInterpolator) {
                    TypedValue peekValue = typedArray.peekValue(index);
                    if (peekValue.type == 1) {
                        this.mDefaultInterpolatorID = typedArray.getResourceId(index, -1);
                        if (this.mDefaultInterpolatorID != -1) {
                            this.mDefaultInterpolator = -2;
                        }
                    } else if (peekValue.type == 3) {
                        this.mDefaultInterpolatorString = typedArray.getString(index);
                        if (this.mDefaultInterpolatorString.indexOf("/") > 0) {
                            this.mDefaultInterpolatorID = typedArray.getResourceId(index, -1);
                            this.mDefaultInterpolator = -2;
                        } else {
                            this.mDefaultInterpolator = -1;
                        }
                    } else {
                        this.mDefaultInterpolator = typedArray.getInteger(index, this.mDefaultInterpolator);
                    }
                } else if (index == C0215R.styleable.Transition_duration) {
                    this.mDuration = typedArray.getInt(index, this.mDuration);
                } else if (index == C0215R.styleable.Transition_staggered) {
                    this.mStagger = typedArray.getFloat(index, this.mStagger);
                } else if (index == C0215R.styleable.Transition_autoTransition) {
                    this.mAutoTransition = typedArray.getInteger(index, this.mAutoTransition);
                } else if (index == C0215R.styleable.Transition_android_id) {
                    this.mId = typedArray.getResourceId(index, this.mId);
                } else if (index == C0215R.styleable.Transition_transitionDisable) {
                    this.mDisable = typedArray.getBoolean(index, this.mDisable);
                }
            }
        }
    }

    public float getPathPercent(View view, int i) {
        return 0.0f;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0013, code lost:
        if (r2 != -1) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setTransition(int r6, int r7) {
        /*
            r5 = this;
            androidx.constraintlayout.widget.StateSet r0 = r5.mStateSet
            if (r0 == 0) goto L_0x0016
            r1 = -1
            int r0 = r0.stateGetConstraintID(r6, r1, r1)
            if (r0 == r1) goto L_0x000c
            goto L_0x000d
        L_0x000c:
            r0 = r6
        L_0x000d:
            androidx.constraintlayout.widget.StateSet r2 = r5.mStateSet
            int r2 = r2.stateGetConstraintID(r7, r1, r1)
            if (r2 == r1) goto L_0x0017
            goto L_0x0018
        L_0x0016:
            r0 = r6
        L_0x0017:
            r2 = r7
        L_0x0018:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionScene$Transition> r1 = r5.mTransitionList
            java.util.Iterator r1 = r1.iterator()
        L_0x001e:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0045
            java.lang.Object r3 = r1.next()
            androidx.constraintlayout.motion.widget.MotionScene$Transition r3 = (androidx.constraintlayout.motion.widget.MotionScene.Transition) r3
            int r4 = r3.mConstraintSetEnd
            if (r4 != r2) goto L_0x0036
            int r4 = r3.mConstraintSetStart
            if (r4 == r0) goto L_0x0042
        L_0x0036:
            int r4 = r3.mConstraintSetEnd
            if (r4 != r7) goto L_0x001e
            int r4 = r3.mConstraintSetStart
            if (r4 != r6) goto L_0x001e
        L_0x0042:
            r5.mCurrentTransition = r3
            return
        L_0x0045:
            androidx.constraintlayout.motion.widget.MotionScene$Transition r6 = new androidx.constraintlayout.motion.widget.MotionScene$Transition
            r6.<init>(r5)
            r6.mConstraintSetStart = r0
            r6.mConstraintSetEnd = r2
            int r7 = r5.mDefaultDuration
            r6.mDuration = r7
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionScene$Transition> r7 = r5.mTransitionList
            r7.add(r6)
            r5.mCurrentTransition = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionScene.setTransition(int, int):void");
    }

    public void addTransition(Transition transition) {
        int index = getIndex(transition);
        if (index == -1) {
            this.mTransitionList.add(transition);
        } else {
            this.mTransitionList.set(index, transition);
        }
    }

    public void removeTransition(Transition transition) {
        int index = getIndex(transition);
        if (index != -1) {
            this.mTransitionList.remove(index);
        }
    }

    private int getIndex(Transition transition) {
        int access$300 = transition.mId;
        if (access$300 != -1) {
            for (int i = 0; i < this.mTransitionList.size(); i++) {
                if (((Transition) this.mTransitionList.get(i)).mId == access$300) {
                    return i;
                }
            }
            return -1;
        }
        throw new IllegalArgumentException("The transition must have an id");
    }

    public boolean validateLayout(MotionLayout motionLayout) {
        return motionLayout == this.mMotionLayout && motionLayout.mScene == this;
    }

    public void setTransition(Transition transition) {
        this.mCurrentTransition = transition;
    }

    private int getRealID(int i) {
        StateSet stateSet = this.mStateSet;
        if (stateSet != null) {
            int stateGetConstraintID = stateSet.stateGetConstraintID(i, -1, -1);
            if (stateGetConstraintID != -1) {
                return stateGetConstraintID;
            }
        }
        return i;
    }

    public List<Transition> getTransitionsWithState(int i) {
        int realID = getRealID(i);
        ArrayList arrayList = new ArrayList();
        Iterator it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            Transition transition = (Transition) it.next();
            if (transition.mConstraintSetStart == realID || transition.mConstraintSetEnd == realID) {
                arrayList.add(transition);
            }
        }
        return arrayList;
    }

    public void addOnClickListeners(MotionLayout motionLayout, int i) {
        Iterator it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            Transition transition = (Transition) it.next();
            if (transition.mOnClicks.size() > 0) {
                Iterator it2 = transition.mOnClicks.iterator();
                while (it2.hasNext()) {
                    TransitionOnClick transitionOnClick = (TransitionOnClick) it2.next();
                    if (i == transition.mConstraintSetStart || i == transition.mConstraintSetEnd) {
                        transitionOnClick.addOnClickListeners(motionLayout);
                    } else {
                        transitionOnClick.removeOnClickListeners(motionLayout);
                    }
                }
            }
        }
    }

    public Transition bestTransitionFor(int i, float f, float f2, MotionEvent motionEvent) {
        if (i == -1) {
            return this.mCurrentTransition;
        }
        List<Transition> transitionsWithState = getTransitionsWithState(i);
        float f3 = 0.0f;
        Transition transition = null;
        RectF rectF = new RectF();
        for (Transition transition2 : transitionsWithState) {
            if (!transition2.mDisable && transition2.mTouchResponse != null) {
                RectF touchRegion = transition2.mTouchResponse.getTouchRegion(this.mMotionLayout, rectF);
                if (touchRegion == null || touchRegion.contains(motionEvent.getX(), motionEvent.getY())) {
                    float dot = transition2.mTouchResponse.dot(f, f2);
                    if (transition2.mConstraintSetEnd == i) {
                        dot *= -1.0f;
                    }
                    if (dot > f3) {
                        transition = transition2;
                        f3 = dot;
                    }
                }
            }
        }
        return transition;
    }

    public ArrayList<Transition> getDefinedTransitions() {
        return this.mTransitionList;
    }

    public Transition getTransitionById(int i) {
        Iterator it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            Transition transition = (Transition) it.next();
            if (transition.mId == i) {
                return transition;
            }
        }
        return null;
    }

    public int[] getConstraintSetIds() {
        int[] iArr = new int[this.mConstraintSetMap.size()];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = this.mConstraintSetMap.keyAt(i);
        }
        return iArr;
    }

    /* access modifiers changed from: 0000 */
    public boolean autoTransition(MotionLayout motionLayout, int i) {
        if (isProcessingTouch() || this.mDisableAutoTransition) {
            return false;
        }
        Iterator it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            Transition transition = (Transition) it.next();
            if (transition.mConstraintSetStart != 0) {
                if (i == transition.mConstraintSetStart && (transition.mAutoTransition == 4 || transition.mAutoTransition == 2)) {
                    motionLayout.setTransition(transition);
                    if (transition.mAutoTransition == 4) {
                        motionLayout.transitionToEnd();
                    } else {
                        motionLayout.setProgress(1.0f);
                    }
                    return true;
                } else if (i == transition.mConstraintSetEnd && (transition.mAutoTransition == 3 || transition.mAutoTransition == 1)) {
                    motionLayout.setTransition(transition);
                    if (transition.mAutoTransition == 3) {
                        motionLayout.transitionToStart();
                    } else {
                        motionLayout.setProgress(0.0f);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isProcessingTouch() {
        return this.mVelocityTracker != null;
    }

    public MotionScene(MotionLayout motionLayout) {
        this.mMotionLayout = motionLayout;
    }

    MotionScene(Context context, MotionLayout motionLayout, int i) {
        this.mMotionLayout = motionLayout;
        load(context, i);
        this.mConstraintSetMap.put(C0215R.C0218id.motion_base, new ConstraintSet());
        this.mConstraintSetIdMap.put("motion_base", Integer.valueOf(C0215R.C0218id.motion_base));
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void load(android.content.Context r10, int r11) {
        /*
            r9 = this;
            android.content.res.Resources r0 = r10.getResources()
            android.content.res.XmlResourceParser r0 = r0.getXml(r11)
            r1 = 0
            int r2 = r0.getEventType()     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
        L_0x000d:
            r3 = 1
            if (r2 == r3) goto L_0x0120
            if (r2 == 0) goto L_0x010e
            r4 = 3
            r5 = 2
            if (r2 == r5) goto L_0x0018
            goto L_0x0111
        L_0x0018:
            java.lang.String r2 = r0.getName()     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            boolean r6 = r9.DEBUG_DESKTOP     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            if (r6 == 0) goto L_0x0036
            java.io.PrintStream r6 = java.lang.System.out     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            r7.<init>()     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            java.lang.String r8 = "parsing = "
            r7.append(r8)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            r7.append(r2)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            java.lang.String r7 = r7.toString()     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            r6.println(r7)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
        L_0x0036:
            r6 = -1
            int r7 = r2.hashCode()     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            java.lang.String r8 = "MotionScene"
            switch(r7) {
                case -1349929691: goto L_0x007a;
                case -1239391468: goto L_0x0070;
                case 269306229: goto L_0x0067;
                case 312750793: goto L_0x005d;
                case 327855227: goto L_0x0053;
                case 793277014: goto L_0x004b;
                case 1382829617: goto L_0x0041;
                default: goto L_0x0040;
            }
        L_0x0040:
            goto L_0x0084
        L_0x0041:
            java.lang.String r3 = "StateSet"
            boolean r3 = r2.equals(r3)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            if (r3 == 0) goto L_0x0084
            r3 = 4
            goto L_0x0085
        L_0x004b:
            boolean r3 = r2.equals(r8)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            if (r3 == 0) goto L_0x0084
            r3 = 0
            goto L_0x0085
        L_0x0053:
            java.lang.String r3 = "OnSwipe"
            boolean r3 = r2.equals(r3)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            if (r3 == 0) goto L_0x0084
            r3 = 2
            goto L_0x0085
        L_0x005d:
            java.lang.String r3 = "OnClick"
            boolean r3 = r2.equals(r3)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            if (r3 == 0) goto L_0x0084
            r3 = 3
            goto L_0x0085
        L_0x0067:
            java.lang.String r4 = "Transition"
            boolean r4 = r2.equals(r4)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            if (r4 == 0) goto L_0x0084
            goto L_0x0085
        L_0x0070:
            java.lang.String r3 = "KeyFrameSet"
            boolean r3 = r2.equals(r3)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            if (r3 == 0) goto L_0x0084
            r3 = 6
            goto L_0x0085
        L_0x007a:
            java.lang.String r3 = "ConstraintSet"
            boolean r3 = r2.equals(r3)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            if (r3 == 0) goto L_0x0084
            r3 = 5
            goto L_0x0085
        L_0x0084:
            r3 = -1
        L_0x0085:
            switch(r3) {
                case 0: goto L_0x00f5;
                case 1: goto L_0x00e3;
                case 2: goto L_0x00a9;
                case 3: goto L_0x00a5;
                case 4: goto L_0x009d;
                case 5: goto L_0x0098;
                case 6: goto L_0x008a;
                default: goto L_0x0088;
            }     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
        L_0x0088:
            goto L_0x00f9
        L_0x008a:
            androidx.constraintlayout.motion.widget.KeyFrames r2 = new androidx.constraintlayout.motion.widget.KeyFrames     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            r2.<init>(r10, r0)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            java.util.ArrayList r3 = r1.mKeyFramesList     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            r3.add(r2)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            goto L_0x0111
        L_0x0098:
            r9.parseConstraintSet(r10, r0)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            goto L_0x0111
        L_0x009d:
            androidx.constraintlayout.widget.StateSet r2 = new androidx.constraintlayout.widget.StateSet     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            r2.<init>(r10, r0)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            r9.mStateSet = r2     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            goto L_0x0111
        L_0x00a5:
            r1.addOnClick(r10, r0)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            goto L_0x0111
        L_0x00a9:
            if (r1 != 0) goto L_0x00d8
            android.content.res.Resources r2 = r10.getResources()     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            java.lang.String r2 = r2.getResourceEntryName(r11)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            int r3 = r0.getLineNumber()     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            r4.<init>()     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            java.lang.String r5 = " OnSwipe ("
            r4.append(r5)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            r4.append(r2)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            java.lang.String r2 = ".xml:"
            r4.append(r2)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            r4.append(r3)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            java.lang.String r2 = ")"
            r4.append(r2)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            java.lang.String r2 = r4.toString()     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            android.util.Log.v(r8, r2)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
        L_0x00d8:
            androidx.constraintlayout.motion.widget.TouchResponse r2 = new androidx.constraintlayout.motion.widget.TouchResponse     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            androidx.constraintlayout.motion.widget.MotionLayout r3 = r9.mMotionLayout     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            r2.<init>(r10, r3, r0)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            r1.mTouchResponse = r2     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            goto L_0x0111
        L_0x00e3:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionScene$Transition> r1 = r9.mTransitionList     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            androidx.constraintlayout.motion.widget.MotionScene$Transition r2 = new androidx.constraintlayout.motion.widget.MotionScene$Transition     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            r2.<init>(r9, r10, r0)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            r1.add(r2)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            androidx.constraintlayout.motion.widget.MotionScene$Transition r1 = r9.mCurrentTransition     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            if (r1 != 0) goto L_0x00f3
            r9.mCurrentTransition = r2     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
        L_0x00f3:
            r1 = r2
            goto L_0x0111
        L_0x00f5:
            r9.parseMotionSceneTags(r10, r0)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            goto L_0x0111
        L_0x00f9:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            r3.<init>()     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            java.lang.String r4 = "WARNING UNKNOWN ATTRIBUTE "
            r3.append(r4)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            r3.append(r2)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            java.lang.String r2 = r3.toString()     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            android.util.Log.v(r8, r2)     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            goto L_0x0111
        L_0x010e:
            r0.getName()     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
        L_0x0111:
            int r2 = r0.next()     // Catch:{ XmlPullParserException -> 0x011c, IOException -> 0x0117 }
            goto L_0x000d
        L_0x0117:
            r10 = move-exception
            r10.printStackTrace()
            goto L_0x0120
        L_0x011c:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0120:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionScene.load(android.content.Context, int):void");
    }

    private void parseMotionSceneTags(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), C0215R.styleable.MotionScene);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == C0215R.styleable.MotionScene_defaultDuration) {
                this.mDefaultDuration = obtainStyledAttributes.getInt(index, this.mDefaultDuration);
            }
        }
        obtainStyledAttributes.recycle();
    }

    private int getId(Context context, String str) {
        int i;
        if (str.contains("/")) {
            String substring = str.substring(str.indexOf(47) + 1);
            i = context.getResources().getIdentifier(substring, CommonProperties.f657ID, context.getPackageName());
            if (this.DEBUG_DESKTOP) {
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append("id getMap res = ");
                sb.append(i);
                printStream.println(sb.toString());
            }
        } else {
            i = -1;
        }
        if (i != -1) {
            return i;
        }
        if (str != null && str.length() > 1) {
            return Integer.parseInt(str.substring(1));
        }
        Log.e(TAG, "error in parsing id");
        return i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0063  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parseConstraintSet(android.content.Context r14, org.xmlpull.v1.XmlPullParser r15) {
        /*
            r13 = this;
            androidx.constraintlayout.widget.ConstraintSet r0 = new androidx.constraintlayout.widget.ConstraintSet
            r0.<init>()
            r1 = 0
            r0.setForceId(r1)
            int r2 = r15.getAttributeCount()
            r3 = -1
            r4 = 0
            r5 = -1
            r6 = -1
        L_0x0011:
            r7 = 1
            if (r4 >= r2) goto L_0x0077
            java.lang.String r8 = r15.getAttributeName(r4)
            java.lang.String r9 = r15.getAttributeValue(r4)
            boolean r10 = r13.DEBUG_DESKTOP
            if (r10 == 0) goto L_0x0036
            java.io.PrintStream r10 = java.lang.System.out
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "id string = "
            r11.append(r12)
            r11.append(r9)
            java.lang.String r11 = r11.toString()
            r10.println(r11)
        L_0x0036:
            int r10 = r8.hashCode()
            r11 = -1496482599(0xffffffffa6cd7cd9, float:-1.4258573E-15)
            if (r10 == r11) goto L_0x004e
            r11 = 3355(0xd1b, float:4.701E-42)
            if (r10 == r11) goto L_0x0044
            goto L_0x0058
        L_0x0044:
            java.lang.String r10 = "id"
            boolean r8 = r8.equals(r10)
            if (r8 == 0) goto L_0x0058
            r8 = 0
            goto L_0x0059
        L_0x004e:
            java.lang.String r10 = "deriveConstraintsFrom"
            boolean r8 = r8.equals(r10)
            if (r8 == 0) goto L_0x0058
            r8 = 1
            goto L_0x0059
        L_0x0058:
            r8 = -1
        L_0x0059:
            if (r8 == 0) goto L_0x0063
            if (r8 == r7) goto L_0x005e
            goto L_0x0074
        L_0x005e:
            int r6 = r13.getId(r14, r9)
            goto L_0x0074
        L_0x0063:
            int r5 = r13.getId(r14, r9)
            java.util.HashMap<java.lang.String, java.lang.Integer> r7 = r13.mConstraintSetIdMap
            java.lang.String r8 = stripID(r9)
            java.lang.Integer r9 = java.lang.Integer.valueOf(r5)
            r7.put(r8, r9)
        L_0x0074:
            int r4 = r4 + 1
            goto L_0x0011
        L_0x0077:
            if (r5 == r3) goto L_0x0091
            androidx.constraintlayout.motion.widget.MotionLayout r1 = r13.mMotionLayout
            int r1 = r1.mDebugPath
            if (r1 == 0) goto L_0x0082
            r0.setValidateOnParse(r7)
        L_0x0082:
            r0.load(r14, r15)
            if (r6 == r3) goto L_0x008c
            android.util.SparseIntArray r14 = r13.mDeriveMap
            r14.put(r5, r6)
        L_0x008c:
            android.util.SparseArray<androidx.constraintlayout.widget.ConstraintSet> r14 = r13.mConstraintSetMap
            r14.put(r5, r0)
        L_0x0091:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionScene.parseConstraintSet(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public ConstraintSet getConstraintSet(Context context, String str) {
        if (this.DEBUG_DESKTOP) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("id ");
            sb.append(str);
            printStream.println(sb.toString());
            PrintStream printStream2 = System.out;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("size ");
            sb2.append(this.mConstraintSetMap.size());
            printStream2.println(sb2.toString());
        }
        for (int i = 0; i < this.mConstraintSetMap.size(); i++) {
            int keyAt = this.mConstraintSetMap.keyAt(i);
            String resourceName = context.getResources().getResourceName(keyAt);
            if (this.DEBUG_DESKTOP) {
                PrintStream printStream3 = System.out;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Id for <");
                sb3.append(i);
                sb3.append("> is <");
                sb3.append(resourceName);
                sb3.append("> looking for <");
                sb3.append(str);
                sb3.append(">");
                printStream3.println(sb3.toString());
            }
            if (str.equals(resourceName)) {
                return (ConstraintSet) this.mConstraintSetMap.get(keyAt);
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public ConstraintSet getConstraintSet(int i) {
        return getConstraintSet(i, -1, -1);
    }

    /* access modifiers changed from: 0000 */
    public ConstraintSet getConstraintSet(int i, int i2, int i3) {
        if (this.DEBUG_DESKTOP) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("id ");
            sb.append(i);
            printStream.println(sb.toString());
            PrintStream printStream2 = System.out;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("size ");
            sb2.append(this.mConstraintSetMap.size());
            printStream2.println(sb2.toString());
        }
        StateSet stateSet = this.mStateSet;
        if (stateSet != null) {
            int stateGetConstraintID = stateSet.stateGetConstraintID(i, i2, i3);
            if (stateGetConstraintID != -1) {
                i = stateGetConstraintID;
            }
        }
        if (this.mConstraintSetMap.get(i) != null) {
            return (ConstraintSet) this.mConstraintSetMap.get(i);
        }
        SparseArray<ConstraintSet> sparseArray = this.mConstraintSetMap;
        return (ConstraintSet) sparseArray.get(sparseArray.keyAt(0));
    }

    public void setConstraintSet(int i, ConstraintSet constraintSet) {
        this.mConstraintSetMap.put(i, constraintSet);
    }

    public void getKeyFrames(MotionController motionController) {
        Transition transition = this.mCurrentTransition;
        if (transition != null) {
            Iterator it = transition.mKeyFramesList.iterator();
            while (it.hasNext()) {
                ((KeyFrames) it.next()).addFrames(motionController);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public C0188Key getKeyFrame(Context context, int i, int i2, int i3) {
        Transition transition = this.mCurrentTransition;
        if (transition == null) {
            return null;
        }
        Iterator it = transition.mKeyFramesList.iterator();
        while (it.hasNext()) {
            KeyFrames keyFrames = (KeyFrames) it.next();
            Iterator it2 = keyFrames.getKeys().iterator();
            while (true) {
                if (it2.hasNext()) {
                    Integer num = (Integer) it2.next();
                    if (i2 == num.intValue()) {
                        Iterator it3 = keyFrames.getKeyFramesForView(num.intValue()).iterator();
                        while (it3.hasNext()) {
                            C0188Key key = (C0188Key) it3.next();
                            if (key.mFramePosition == i3 && key.mType == i) {
                                return key;
                            }
                        }
                        continue;
                    }
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public int getTransitionDirection(int i) {
        Iterator it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            if (((Transition) it.next()).mConstraintSetStart == i) {
                return 0;
            }
        }
        return 1;
    }

    /* access modifiers changed from: 0000 */
    public boolean hasKeyFramePosition(View view, int i) {
        Transition transition = this.mCurrentTransition;
        if (transition == null) {
            return false;
        }
        Iterator it = transition.mKeyFramesList.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((KeyFrames) it.next()).getKeyFramesForView(view.getId()).iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (((C0188Key) it2.next()).mFramePosition == i) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void setKeyframe(View view, int i, String str, Object obj) {
        Transition transition = this.mCurrentTransition;
        if (transition != null) {
            Iterator it = transition.mKeyFramesList.iterator();
            while (it.hasNext()) {
                Iterator it2 = ((KeyFrames) it.next()).getKeyFramesForView(view.getId()).iterator();
                while (it2.hasNext()) {
                    if (((C0188Key) it2.next()).mFramePosition == i) {
                        int i2 = ((obj != null ? ((Float) obj).floatValue() : 0.0f) > 0.0f ? 1 : ((obj != null ? ((Float) obj).floatValue() : 0.0f) == 0.0f ? 0 : -1));
                        str.equalsIgnoreCase("app:PerpendicularPath_percent");
                    }
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean supportTouch() {
        Iterator it = this.mTransitionList.iterator();
        do {
            boolean z = true;
            if (!it.hasNext()) {
                Transition transition = this.mCurrentTransition;
                if (transition == null || transition.mTouchResponse == null) {
                    z = false;
                }
                return z;
            }
        } while (((Transition) it.next()).mTouchResponse == null);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void processTouchEvent(MotionEvent motionEvent, int i, MotionLayout motionLayout) {
        RectF rectF = new RectF();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = this.mMotionLayout.obtainVelocityTracker();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        if (i != -1) {
            int action = motionEvent.getAction();
            boolean z = false;
            if (action == 0) {
                this.mLastTouchX = motionEvent.getRawX();
                this.mLastTouchY = motionEvent.getRawY();
                this.mLastTouchDown = motionEvent;
                if (this.mCurrentTransition.mTouchResponse != null) {
                    RectF touchRegion = this.mCurrentTransition.mTouchResponse.getTouchRegion(this.mMotionLayout, rectF);
                    if (touchRegion == null || touchRegion.contains(this.mLastTouchDown.getX(), this.mLastTouchDown.getY())) {
                        this.mMotionOutsideRegion = false;
                    } else {
                        this.mMotionOutsideRegion = true;
                    }
                    this.mCurrentTransition.mTouchResponse.setDown(this.mLastTouchX, this.mLastTouchY);
                }
                return;
            } else if (action == 2) {
                float rawY = motionEvent.getRawY() - this.mLastTouchY;
                float rawX = motionEvent.getRawX() - this.mLastTouchX;
                if (((double) rawX) != 0.0d || ((double) rawY) != 0.0d) {
                    Transition bestTransitionFor = bestTransitionFor(i, rawX, rawY, this.mLastTouchDown);
                    if (bestTransitionFor != null) {
                        motionLayout.setTransition(bestTransitionFor);
                        RectF touchRegion2 = this.mCurrentTransition.mTouchResponse.getTouchRegion(this.mMotionLayout, rectF);
                        if (touchRegion2 != null && !touchRegion2.contains(this.mLastTouchDown.getX(), this.mLastTouchDown.getY())) {
                            z = true;
                        }
                        this.mMotionOutsideRegion = z;
                        this.mCurrentTransition.mTouchResponse.setUpTouchEvent(this.mLastTouchX, this.mLastTouchY);
                    }
                } else {
                    return;
                }
            }
        }
        Transition transition = this.mCurrentTransition;
        if (!(transition == null || transition.mTouchResponse == null || this.mMotionOutsideRegion)) {
            this.mCurrentTransition.mTouchResponse.processTouchEvent(motionEvent, this.mVelocityTracker, i, this);
        }
        this.mLastTouchX = motionEvent.getRawX();
        this.mLastTouchY = motionEvent.getRawY();
        if (motionEvent.getAction() == 1) {
            MotionTracker motionTracker = this.mVelocityTracker;
            if (motionTracker != null) {
                motionTracker.recycle();
                this.mVelocityTracker = null;
                if (motionLayout.mCurrentState != -1) {
                    autoTransition(motionLayout, motionLayout.mCurrentState);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void processScrollMove(float f, float f2) {
        Transition transition = this.mCurrentTransition;
        if (transition != null && transition.mTouchResponse != null) {
            this.mCurrentTransition.mTouchResponse.scrollMove(f, f2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void processScrollUp(float f, float f2) {
        Transition transition = this.mCurrentTransition;
        if (transition != null && transition.mTouchResponse != null) {
            this.mCurrentTransition.mTouchResponse.scrollUp(f, f2);
        }
    }

    /* access modifiers changed from: 0000 */
    public float getProgressDirection(float f, float f2) {
        Transition transition = this.mCurrentTransition;
        if (transition == null || transition.mTouchResponse == null) {
            return 0.0f;
        }
        return this.mCurrentTransition.mTouchResponse.getProgressDirection(f, f2);
    }

    /* access modifiers changed from: 0000 */
    public int getStartId() {
        Transition transition = this.mCurrentTransition;
        if (transition == null) {
            return -1;
        }
        return transition.mConstraintSetStart;
    }

    /* access modifiers changed from: 0000 */
    public int getEndId() {
        Transition transition = this.mCurrentTransition;
        if (transition == null) {
            return -1;
        }
        return transition.mConstraintSetEnd;
    }

    public Interpolator getInterpolator() {
        switch (this.mCurrentTransition.mDefaultInterpolator) {
            case -2:
                return AnimationUtils.loadInterpolator(this.mMotionLayout.getContext(), this.mCurrentTransition.mDefaultInterpolatorID);
            case -1:
                final Easing interpolator = Easing.getInterpolator(this.mCurrentTransition.mDefaultInterpolatorString);
                return new Interpolator() {
                    public float getInterpolation(float f) {
                        return (float) interpolator.get((double) f);
                    }
                };
            case 0:
                return new AccelerateDecelerateInterpolator();
            case 1:
                return new AccelerateInterpolator();
            case 2:
                return new DecelerateInterpolator();
            case 3:
                return null;
            case 4:
                return new AnticipateInterpolator();
            case 5:
                return new BounceInterpolator();
            default:
                return null;
        }
    }

    public int getDuration() {
        Transition transition = this.mCurrentTransition;
        if (transition != null) {
            return transition.mDuration;
        }
        return this.mDefaultDuration;
    }

    public void setDuration(int i) {
        Transition transition = this.mCurrentTransition;
        if (transition != null) {
            transition.setDuration(i);
        } else {
            this.mDefaultDuration = i;
        }
    }

    public float getStaggered() {
        Transition transition = this.mCurrentTransition;
        if (transition != null) {
            return transition.mStagger;
        }
        return 0.0f;
    }

    /* access modifiers changed from: 0000 */
    public float getMaxAcceleration() {
        Transition transition = this.mCurrentTransition;
        if (transition == null || transition.mTouchResponse == null) {
            return 0.0f;
        }
        return this.mCurrentTransition.mTouchResponse.getMaxAcceleration();
    }

    /* access modifiers changed from: 0000 */
    public float getMaxVelocity() {
        Transition transition = this.mCurrentTransition;
        if (transition == null || transition.mTouchResponse == null) {
            return 0.0f;
        }
        return this.mCurrentTransition.mTouchResponse.getMaxVelocity();
    }

    /* access modifiers changed from: 0000 */
    public void setupTouch() {
        Transition transition = this.mCurrentTransition;
        if (transition != null && transition.mTouchResponse != null) {
            this.mCurrentTransition.mTouchResponse.setupTouch();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean getMoveWhenScrollAtTop() {
        Transition transition = this.mCurrentTransition;
        if (transition == null || transition.mTouchResponse == null) {
            return false;
        }
        return this.mCurrentTransition.mTouchResponse.getMoveWhenScrollAtTop();
    }

    /* access modifiers changed from: 0000 */
    public void readFallback(MotionLayout motionLayout) {
        for (int i = 0; i < this.mConstraintSetMap.size(); i++) {
            readConstraintChain(this.mConstraintSetMap.keyAt(i));
        }
        for (int i2 = 0; i2 < this.mConstraintSetMap.size(); i2++) {
            ((ConstraintSet) this.mConstraintSetMap.valueAt(i2)).readFallback((ConstraintLayout) motionLayout);
        }
    }

    private void readConstraintChain(int i) {
        int i2 = this.mDeriveMap.get(i);
        if (i2 > 0) {
            readConstraintChain(this.mDeriveMap.get(i));
            ((ConstraintSet) this.mConstraintSetMap.get(i)).readFallback((ConstraintSet) this.mConstraintSetMap.get(i2));
            this.mDeriveMap.put(i, -1);
        }
    }

    public static String stripID(String str) {
        if (str == null) {
            return "";
        }
        int indexOf = str.indexOf(47);
        if (indexOf < 0) {
            return str;
        }
        return str.substring(indexOf + 1);
    }

    public int lookUpConstraintId(String str) {
        return ((Integer) this.mConstraintSetIdMap.get(str)).intValue();
    }

    public String lookUpConstraintName(int i) {
        for (Entry entry : this.mConstraintSetIdMap.entrySet()) {
            if (((Integer) entry.getValue()).intValue() == i) {
                return (String) entry.getKey();
            }
        }
        return null;
    }

    public void disableAutoTransition(boolean z) {
        this.mDisableAutoTransition = z;
    }
}
