package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.util.Log;
import android.util.Xml;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class KeyFrames {
    private static final String TAG = "KeyFrames";
    public static final int UNSET = -1;
    static HashMap<String, Constructor<? extends C0188Key>> sKeyMakers = new HashMap<>();
    private HashMap<Integer, ArrayList<C0188Key>> mFramesMap = new HashMap<>();

    static {
        try {
            sKeyMakers.put("KeyAttribute", KeyAttributes.class.getConstructor(new Class[0]));
            sKeyMakers.put("KeyPosition", KeyPosition.class.getConstructor(new Class[0]));
            sKeyMakers.put("KeyCycle", KeyCycle.class.getConstructor(new Class[0]));
            sKeyMakers.put("KeyTimeCycle", KeyTimeCycle.class.getConstructor(new Class[0]));
            sKeyMakers.put("KeyTrigger", KeyTrigger.class.getConstructor(new Class[0]));
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "unable to load", e);
        }
    }

    private void addKey(C0188Key key) {
        if (!this.mFramesMap.containsKey(Integer.valueOf(key.mTargetId))) {
            this.mFramesMap.put(Integer.valueOf(key.mTargetId), new ArrayList());
        }
        ((ArrayList) this.mFramesMap.get(Integer.valueOf(key.mTargetId))).add(key);
    }

    public KeyFrames(Context context, XmlPullParser xmlPullParser) {
        C0188Key key = null;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType != 0) {
                    if (eventType == 2) {
                        String name = xmlPullParser.getName();
                        if (sKeyMakers.containsKey(name)) {
                            try {
                                C0188Key key2 = (C0188Key) ((Constructor) sKeyMakers.get(name)).newInstance(new Object[0]);
                                try {
                                    key2.load(context, Xml.asAttributeSet(xmlPullParser));
                                    addKey(key2);
                                    key = key2;
                                } catch (Exception e) {
                                    C0188Key key3 = key2;
                                    e = e;
                                    key = key3;
                                    Log.e(TAG, "unable to create ", e);
                                    eventType = xmlPullParser.next();
                                }
                            } catch (Exception e2) {
                                e = e2;
                                Log.e(TAG, "unable to create ", e);
                                eventType = xmlPullParser.next();
                            }
                        } else if (!(!name.equalsIgnoreCase("CustomAttribute") || key == null || key.mCustomConstraints == null)) {
                            ConstraintAttribute.parse(context, xmlPullParser, key.mCustomConstraints);
                        }
                    } else if (eventType == 3) {
                        if ("KeyFrameSet".equals(xmlPullParser.getName())) {
                            return;
                        }
                    }
                }
                eventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    public void addFrames(MotionController motionController) {
        ArrayList arrayList = (ArrayList) this.mFramesMap.get(Integer.valueOf(motionController.mId));
        if (arrayList != null) {
            motionController.addKeys(arrayList);
        }
        ArrayList arrayList2 = (ArrayList) this.mFramesMap.get(Integer.valueOf(-1));
        if (arrayList2 != null) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                C0188Key key = (C0188Key) it.next();
                if (key.matches(((LayoutParams) motionController.mView.getLayoutParams()).constraintTag)) {
                    motionController.addKey(key);
                }
            }
        }
    }

    static String name(int i, Context context) {
        return context.getResources().getResourceEntryName(i);
    }

    public Set<Integer> getKeys() {
        return this.mFramesMap.keySet();
    }

    public ArrayList<C0188Key> getKeyFramesForView(int i) {
        return (ArrayList) this.mFramesMap.get(Integer.valueOf(i));
    }
}
