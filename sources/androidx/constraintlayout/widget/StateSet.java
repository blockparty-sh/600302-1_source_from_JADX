package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class StateSet {
    private static final boolean DEBUG = false;
    public static final String TAG = "ConstraintLayoutStates";
    private SparseArray<ConstraintSet> mConstraintSetMap = new SparseArray<>();
    private ConstraintsChangedListener mConstraintsChangedListener = null;
    int mCurrentConstraintNumber = -1;
    int mCurrentStateId = -1;
    ConstraintSet mDefaultConstraintSet;
    int mDefaultState = -1;
    private SparseArray<State> mStateList = new SparseArray<>();

    static class State {
        int mConstraintID = -1;
        int mId;
        boolean mIsLayout;
        ArrayList<Variant> mVariants = new ArrayList<>();

        public State(Context context, XmlPullParser xmlPullParser) {
            this.mIsLayout = false;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), C0215R.styleable.State);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == C0215R.styleable.State_android_id) {
                    this.mId = obtainStyledAttributes.getResourceId(index, this.mId);
                } else if (index == C0215R.styleable.State_constraints) {
                    this.mConstraintID = obtainStyledAttributes.getResourceId(index, this.mConstraintID);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.mConstraintID);
                    context.getResources().getResourceName(this.mConstraintID);
                    if ("layout".equals(resourceTypeName)) {
                        this.mIsLayout = true;
                    }
                }
            }
            obtainStyledAttributes.recycle();
        }

        /* access modifiers changed from: 0000 */
        public void add(Variant variant) {
            this.mVariants.add(variant);
        }

        public int findMatch(float f, float f2) {
            for (int i = 0; i < this.mVariants.size(); i++) {
                if (((Variant) this.mVariants.get(i)).match(f, f2)) {
                    return i;
                }
            }
            return -1;
        }
    }

    static class Variant {
        int mConstraintID = -1;
        int mId;
        boolean mIsLayout;
        float mMaxHeight = Float.NaN;
        float mMaxWidth = Float.NaN;
        float mMinHeight = Float.NaN;
        float mMinWidth = Float.NaN;

        public Variant(Context context, XmlPullParser xmlPullParser) {
            this.mIsLayout = false;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), C0215R.styleable.Variant);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == C0215R.styleable.Variant_constraints) {
                    this.mConstraintID = obtainStyledAttributes.getResourceId(index, this.mConstraintID);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.mConstraintID);
                    context.getResources().getResourceName(this.mConstraintID);
                    if ("layout".equals(resourceTypeName)) {
                        this.mIsLayout = true;
                    }
                } else if (index == C0215R.styleable.Variant_region_heightLessThan) {
                    this.mMaxHeight = obtainStyledAttributes.getDimension(index, this.mMaxHeight);
                } else if (index == C0215R.styleable.Variant_region_heightMoreThan) {
                    this.mMinHeight = obtainStyledAttributes.getDimension(index, this.mMinHeight);
                } else if (index == C0215R.styleable.Variant_region_widthLessThan) {
                    this.mMaxWidth = obtainStyledAttributes.getDimension(index, this.mMaxWidth);
                } else if (index == C0215R.styleable.Variant_region_widthMoreThan) {
                    this.mMinWidth = obtainStyledAttributes.getDimension(index, this.mMinWidth);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            obtainStyledAttributes.recycle();
        }

        /* access modifiers changed from: 0000 */
        public boolean match(float f, float f2) {
            if (!Float.isNaN(this.mMinWidth) && f < this.mMinWidth) {
                return false;
            }
            if (!Float.isNaN(this.mMinHeight) && f2 < this.mMinHeight) {
                return false;
            }
            if (!Float.isNaN(this.mMaxWidth) && f > this.mMaxWidth) {
                return false;
            }
            if (Float.isNaN(this.mMaxHeight) || f2 <= this.mMaxHeight) {
                return true;
            }
            return false;
        }
    }

    public StateSet(Context context, XmlPullParser xmlPullParser) {
        load(context, xmlPullParser);
    }

    private void load(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), C0215R.styleable.StateSet);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == C0215R.styleable.StateSet_defaultState) {
                this.mDefaultState = obtainStyledAttributes.getResourceId(index, this.mDefaultState);
            }
        }
        State state = null;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType != 0) {
                    String str = "StateSet";
                    if (eventType == 2) {
                        String name = xmlPullParser.getName();
                        char c = 65535;
                        switch (name.hashCode()) {
                            case 80204913:
                                if (name.equals("State")) {
                                    c = 2;
                                    break;
                                }
                                break;
                            case 1301459538:
                                if (name.equals("LayoutDescription")) {
                                    c = 0;
                                    break;
                                }
                                break;
                            case 1382829617:
                                if (name.equals(str)) {
                                    c = 1;
                                    break;
                                }
                                break;
                            case 1901439077:
                                if (name.equals("Variant")) {
                                    c = 3;
                                    break;
                                }
                                break;
                        }
                        if (!(c == 0 || c == 1)) {
                            if (c == 2) {
                                state = new State(context, xmlPullParser);
                                this.mStateList.put(state.mId, state);
                            } else if (c != 3) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("unknown tag ");
                                sb.append(name);
                                Log.v("ConstraintLayoutStates", sb.toString());
                            } else {
                                Variant variant = new Variant(context, xmlPullParser);
                                if (state != null) {
                                    state.add(variant);
                                }
                            }
                        }
                    } else if (eventType != 3) {
                        continue;
                    } else if (str.equals(xmlPullParser.getName())) {
                        return;
                    }
                } else {
                    xmlPullParser.getName();
                }
                eventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public boolean needsToChange(int i, float f, float f2) {
        int i2 = this.mCurrentStateId;
        if (i2 != i) {
            return true;
        }
        State state = (State) (i == -1 ? this.mStateList.valueAt(0) : this.mStateList.get(i2));
        return (this.mCurrentConstraintNumber == -1 || !((Variant) state.mVariants.get(this.mCurrentConstraintNumber)).match(f, f2)) && this.mCurrentConstraintNumber != state.findMatch(f, f2);
    }

    public void setOnConstraintsChanged(ConstraintsChangedListener constraintsChangedListener) {
        this.mConstraintsChangedListener = constraintsChangedListener;
    }

    public int stateGetConstraintID(int i, int i2, int i3) {
        return updateConstraints(-1, i, (float) i2, (float) i3);
    }

    public int convertToConstraintSet(int i, int i2, float f, float f2) {
        State state = (State) this.mStateList.get(i2);
        if (state == null) {
            return i2;
        }
        if (f != -1.0f && f2 != -1.0f) {
            Variant variant = null;
            Iterator it = state.mVariants.iterator();
            while (it.hasNext()) {
                Variant variant2 = (Variant) it.next();
                if (variant2.match(f, f2)) {
                    if (i == variant2.mConstraintID) {
                        return i;
                    }
                    variant = variant2;
                }
            }
            if (variant != null) {
                return variant.mConstraintID;
            }
            return state.mConstraintID;
        } else if (state.mConstraintID == i) {
            return i;
        } else {
            Iterator it2 = state.mVariants.iterator();
            while (it2.hasNext()) {
                if (i == ((Variant) it2.next()).mConstraintID) {
                    return i;
                }
            }
            return state.mConstraintID;
        }
    }

    public int updateConstraints(int i, int i2, float f, float f2) {
        State state;
        if (i == i2) {
            if (i2 == -1) {
                state = (State) this.mStateList.valueAt(0);
            } else {
                state = (State) this.mStateList.get(this.mCurrentStateId);
            }
            if (state == null) {
                return -1;
            }
            if (this.mCurrentConstraintNumber != -1 && ((Variant) state.mVariants.get(i)).match(f, f2)) {
                return i;
            }
            int findMatch = state.findMatch(f, f2);
            if (i == findMatch) {
                return i;
            }
            return findMatch == -1 ? state.mConstraintID : ((Variant) state.mVariants.get(findMatch)).mConstraintID;
        }
        State state2 = (State) this.mStateList.get(i2);
        if (state2 == null) {
            return -1;
        }
        int findMatch2 = state2.findMatch(f, f2);
        return findMatch2 == -1 ? state2.mConstraintID : ((Variant) state2.mVariants.get(findMatch2)).mConstraintID;
    }
}
