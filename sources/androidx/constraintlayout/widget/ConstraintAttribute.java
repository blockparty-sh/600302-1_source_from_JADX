package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.View;
import androidx.constraintlayout.motion.widget.Debug;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;

public class ConstraintAttribute {
    private static final String TAG = "TransitionLayout";
    boolean mBooleanValue;
    private int mColorValue;
    private float mFloatValue;
    private int mIntegerValue;
    String mName;
    private String mStringValue;
    private AttributeType mType;

    public enum AttributeType {
        INT_TYPE,
        FLOAT_TYPE,
        COLOR_TYPE,
        COLOR_DRAWABLE_TYPE,
        STRING_TYPE,
        BOOLEAN_TYPE,
        DIMENSION_TYPE
    }

    private static int clamp(int i) {
        int i2 = (i & (~(i >> 31))) - 255;
        return (i2 & (i2 >> 31)) + 255;
    }

    public AttributeType getType() {
        return this.mType;
    }

    public void setFloatValue(float f) {
        this.mFloatValue = f;
    }

    public void setColorValue(int i) {
        this.mColorValue = i;
    }

    public void setIntValue(int i) {
        this.mIntegerValue = i;
    }

    public void setStringValue(String str) {
        this.mStringValue = str;
    }

    public int noOfInterpValues() {
        int i = C02131.f47x66adad53[this.mType.ordinal()];
        return (i == 1 || i == 2) ? 4 : 1;
    }

    public float getValueToInterpolate() {
        switch (this.mType) {
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case INT_TYPE:
                return (float) this.mIntegerValue;
            case FLOAT_TYPE:
                return this.mFloatValue;
            case STRING_TYPE:
                throw new RuntimeException("Cannot interpolate String");
            case BOOLEAN_TYPE:
                return this.mBooleanValue ? 0.0f : 1.0f;
            case DIMENSION_TYPE:
                return this.mFloatValue;
            default:
                return Float.NaN;
        }
    }

    public void getValuesToInterpolate(float[] fArr) {
        switch (this.mType) {
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                int i = this.mColorValue;
                int i2 = (i >> 24) & 255;
                float pow = (float) Math.pow((double) (((float) ((i >> 16) & 255)) / 255.0f), 2.2d);
                float pow2 = (float) Math.pow((double) (((float) ((i >> 8) & 255)) / 255.0f), 2.2d);
                float pow3 = (float) Math.pow((double) (((float) (i & 255)) / 255.0f), 2.2d);
                fArr[0] = pow;
                fArr[1] = pow2;
                fArr[2] = pow3;
                fArr[3] = ((float) i2) / 255.0f;
                return;
            case INT_TYPE:
                fArr[0] = (float) this.mIntegerValue;
                return;
            case FLOAT_TYPE:
                fArr[0] = this.mFloatValue;
                return;
            case STRING_TYPE:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case BOOLEAN_TYPE:
                fArr[0] = this.mBooleanValue ? 0.0f : 1.0f;
                return;
            case DIMENSION_TYPE:
                fArr[0] = this.mFloatValue;
                return;
            default:
                return;
        }
    }

    public void setValue(float[] fArr) {
        boolean z = false;
        switch (this.mType) {
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                this.mColorValue = Color.HSVToColor(fArr);
                this.mColorValue = (clamp((int) (fArr[3] * 255.0f)) << 24) | (this.mColorValue & 16777215);
                return;
            case INT_TYPE:
                this.mIntegerValue = (int) fArr[0];
                return;
            case FLOAT_TYPE:
                this.mFloatValue = fArr[0];
                return;
            case STRING_TYPE:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case BOOLEAN_TYPE:
                if (((double) fArr[0]) > 0.5d) {
                    z = true;
                }
                this.mBooleanValue = z;
                return;
            case DIMENSION_TYPE:
                this.mFloatValue = fArr[0];
                return;
            default:
                return;
        }
    }

    public boolean diff(ConstraintAttribute constraintAttribute) {
        boolean z = false;
        if (constraintAttribute != null && this.mType == constraintAttribute.mType) {
            switch (this.mType) {
                case COLOR_TYPE:
                case COLOR_DRAWABLE_TYPE:
                    if (this.mColorValue == constraintAttribute.mColorValue) {
                        z = true;
                        break;
                    }
                    break;
                case INT_TYPE:
                    if (this.mIntegerValue == constraintAttribute.mIntegerValue) {
                        z = true;
                    }
                    return z;
                case FLOAT_TYPE:
                    if (this.mFloatValue == constraintAttribute.mFloatValue) {
                        z = true;
                    }
                    return z;
                case STRING_TYPE:
                    if (this.mIntegerValue == constraintAttribute.mIntegerValue) {
                        z = true;
                    }
                    return z;
                case BOOLEAN_TYPE:
                    if (this.mBooleanValue == constraintAttribute.mBooleanValue) {
                        z = true;
                    }
                    return z;
                case DIMENSION_TYPE:
                    if (this.mFloatValue == constraintAttribute.mFloatValue) {
                        z = true;
                    }
                    return z;
                default:
                    return false;
            }
        }
        return z;
    }

    public ConstraintAttribute(String str, AttributeType attributeType) {
        this.mName = str;
        this.mType = attributeType;
    }

    public ConstraintAttribute(String str, AttributeType attributeType, Object obj) {
        this.mName = str;
        this.mType = attributeType;
        setValue(obj);
    }

    public ConstraintAttribute(ConstraintAttribute constraintAttribute, Object obj) {
        this.mName = constraintAttribute.mName;
        this.mType = constraintAttribute.mType;
        setValue(obj);
    }

    public void setValue(Object obj) {
        switch (this.mType) {
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                this.mColorValue = ((Integer) obj).intValue();
                return;
            case INT_TYPE:
                this.mIntegerValue = ((Integer) obj).intValue();
                return;
            case FLOAT_TYPE:
                this.mFloatValue = ((Float) obj).floatValue();
                return;
            case STRING_TYPE:
                this.mStringValue = (String) obj;
                return;
            case BOOLEAN_TYPE:
                this.mBooleanValue = ((Boolean) obj).booleanValue();
                return;
            case DIMENSION_TYPE:
                this.mFloatValue = ((Float) obj).floatValue();
                return;
            default:
                return;
        }
    }

    public static HashMap<String, ConstraintAttribute> extractAttributes(HashMap<String, ConstraintAttribute> hashMap, View view) {
        HashMap<String, ConstraintAttribute> hashMap2 = new HashMap<>();
        Class cls = view.getClass();
        for (String str : hashMap.keySet()) {
            ConstraintAttribute constraintAttribute = (ConstraintAttribute) hashMap.get(str);
            try {
                if (str.equals("BackgroundColor")) {
                    hashMap2.put(str, new ConstraintAttribute(constraintAttribute, (Object) Integer.valueOf(((ColorDrawable) view.getBackground()).getColor())));
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("getMap");
                    sb.append(str);
                    hashMap2.put(str, new ConstraintAttribute(constraintAttribute, cls.getMethod(sb.toString(), new Class[0]).invoke(view, new Object[0])));
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
        return hashMap2;
    }

    public static void setAttributes(View view, HashMap<String, ConstraintAttribute> hashMap) {
        Class cls = view.getClass();
        for (String str : hashMap.keySet()) {
            ConstraintAttribute constraintAttribute = (ConstraintAttribute) hashMap.get(str);
            StringBuilder sb = new StringBuilder();
            sb.append("set");
            sb.append(str);
            String sb2 = sb.toString();
            try {
                switch (constraintAttribute.mType) {
                    case COLOR_TYPE:
                        cls.getMethod(sb2, new Class[]{Integer.TYPE}).invoke(view, new Object[]{Integer.valueOf(constraintAttribute.mColorValue)});
                        break;
                    case COLOR_DRAWABLE_TYPE:
                        Method method = cls.getMethod(sb2, new Class[]{Drawable.class});
                        ColorDrawable colorDrawable = new ColorDrawable();
                        colorDrawable.setColor(constraintAttribute.mColorValue);
                        method.invoke(view, new Object[]{colorDrawable});
                        break;
                    case INT_TYPE:
                        cls.getMethod(sb2, new Class[]{Integer.TYPE}).invoke(view, new Object[]{Integer.valueOf(constraintAttribute.mIntegerValue)});
                        break;
                    case FLOAT_TYPE:
                        cls.getMethod(sb2, new Class[]{Float.TYPE}).invoke(view, new Object[]{Float.valueOf(constraintAttribute.mFloatValue)});
                        break;
                    case STRING_TYPE:
                        cls.getMethod(sb2, new Class[]{CharSequence.class}).invoke(view, new Object[]{constraintAttribute.mStringValue});
                        break;
                    case BOOLEAN_TYPE:
                        cls.getMethod(sb2, new Class[]{Boolean.TYPE}).invoke(view, new Object[]{Boolean.valueOf(constraintAttribute.mBooleanValue)});
                        break;
                    case DIMENSION_TYPE:
                        cls.getMethod(sb2, new Class[]{Float.TYPE}).invoke(view, new Object[]{Float.valueOf(constraintAttribute.mFloatValue)});
                        break;
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
    }

    public void setInterpolatedValue(View view, float[] fArr) {
        View view2 = view;
        String str = "\"";
        String str2 = "on View \"";
        String str3 = TAG;
        Class cls = view.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append("set");
        sb.append(this.mName);
        String sb2 = sb.toString();
        try {
            boolean z = true;
            switch (this.mType) {
                case COLOR_TYPE:
                    Method method = cls.getMethod(sb2, new Class[]{Integer.TYPE});
                    int clamp = clamp((int) (((float) Math.pow((double) fArr[0], 0.45454545454545453d)) * 255.0f));
                    int clamp2 = clamp((int) (((float) Math.pow((double) fArr[1], 0.45454545454545453d)) * 255.0f));
                    method.invoke(view2, new Object[]{Integer.valueOf((clamp << 16) | (clamp((int) (fArr[3] * 255.0f)) << 24) | (clamp2 << 8) | clamp((int) (((float) Math.pow((double) fArr[2], 0.45454545454545453d)) * 255.0f)))});
                    return;
                case COLOR_DRAWABLE_TYPE:
                    Method method2 = cls.getMethod(sb2, new Class[]{Drawable.class});
                    int clamp3 = clamp((int) (((float) Math.pow((double) fArr[0], 0.45454545454545453d)) * 255.0f));
                    int clamp4 = clamp((int) (((float) Math.pow((double) fArr[1], 0.45454545454545453d)) * 255.0f));
                    int clamp5 = (clamp3 << 16) | (clamp((int) (fArr[3] * 255.0f)) << 24) | (clamp4 << 8) | clamp((int) (((float) Math.pow((double) fArr[2], 0.45454545454545453d)) * 255.0f));
                    ColorDrawable colorDrawable = new ColorDrawable();
                    colorDrawable.setColor(clamp5);
                    method2.invoke(view2, new Object[]{colorDrawable});
                    return;
                case INT_TYPE:
                    cls.getMethod(sb2, new Class[]{Integer.TYPE}).invoke(view2, new Object[]{Integer.valueOf((int) fArr[0])});
                    return;
                case FLOAT_TYPE:
                    cls.getMethod(sb2, new Class[]{Float.TYPE}).invoke(view2, new Object[]{Float.valueOf(fArr[0])});
                    return;
                case STRING_TYPE:
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("unable to interpolate strings ");
                    sb3.append(this.mName);
                    throw new RuntimeException(sb3.toString());
                case BOOLEAN_TYPE:
                    Method method3 = cls.getMethod(sb2, new Class[]{Boolean.TYPE});
                    Object[] objArr = new Object[1];
                    if (fArr[0] <= 0.5f) {
                        z = false;
                    }
                    objArr[0] = Boolean.valueOf(z);
                    method3.invoke(view2, objArr);
                    return;
                case DIMENSION_TYPE:
                    cls.getMethod(sb2, new Class[]{Float.TYPE}).invoke(view2, new Object[]{Float.valueOf(fArr[0])});
                    return;
                default:
                    return;
            }
        } catch (NoSuchMethodException e) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("no method ");
            sb4.append(sb2);
            sb4.append(str2);
            sb4.append(Debug.getName(view));
            sb4.append(str);
            Log.e(str3, sb4.toString());
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("cannot access method ");
            sb5.append(sb2);
            sb5.append(str2);
            sb5.append(Debug.getName(view));
            sb5.append(str);
            Log.e(str3, sb5.toString());
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }

    public static void parse(Context context, XmlPullParser xmlPullParser, HashMap<String, ConstraintAttribute> hashMap) {
        AttributeType attributeType;
        Object string;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), C0215R.styleable.CustomAttribute);
        int indexCount = obtainStyledAttributes.getIndexCount();
        String str = null;
        Object obj = null;
        AttributeType attributeType2 = null;
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == C0215R.styleable.CustomAttribute_attributeName) {
                str = obtainStyledAttributes.getString(index);
                if (str != null && str.length() > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(Character.toUpperCase(str.charAt(0)));
                    sb.append(str.substring(1));
                    str = sb.toString();
                }
            } else if (index == C0215R.styleable.CustomAttribute_customBoolean) {
                obj = Boolean.valueOf(obtainStyledAttributes.getBoolean(index, false));
                attributeType2 = AttributeType.BOOLEAN_TYPE;
            } else {
                if (index == C0215R.styleable.CustomAttribute_customColorValue) {
                    attributeType = AttributeType.COLOR_TYPE;
                    string = Integer.valueOf(obtainStyledAttributes.getColor(index, 0));
                } else if (index == C0215R.styleable.CustomAttribute_customColorDrawableValue) {
                    attributeType = AttributeType.COLOR_DRAWABLE_TYPE;
                    string = Integer.valueOf(obtainStyledAttributes.getColor(index, 0));
                } else if (index == C0215R.styleable.CustomAttribute_customPixelDimension) {
                    attributeType = AttributeType.DIMENSION_TYPE;
                    string = Float.valueOf(TypedValue.applyDimension(1, obtainStyledAttributes.getDimension(index, 0.0f), context.getResources().getDisplayMetrics()));
                } else if (index == C0215R.styleable.CustomAttribute_customDimension) {
                    attributeType = AttributeType.DIMENSION_TYPE;
                    string = Float.valueOf(obtainStyledAttributes.getDimension(index, 0.0f));
                } else if (index == C0215R.styleable.CustomAttribute_customFloatValue) {
                    attributeType = AttributeType.FLOAT_TYPE;
                    string = Float.valueOf(obtainStyledAttributes.getFloat(index, Float.NaN));
                } else if (index == C0215R.styleable.CustomAttribute_customIntegerValue) {
                    attributeType = AttributeType.INT_TYPE;
                    string = Integer.valueOf(obtainStyledAttributes.getInteger(index, -1));
                } else if (index == C0215R.styleable.CustomAttribute_customStringValue) {
                    attributeType = AttributeType.STRING_TYPE;
                    string = obtainStyledAttributes.getString(index);
                }
                Object obj2 = string;
                attributeType2 = attributeType;
                obj = obj2;
            }
        }
        if (!(str == null || obj == null)) {
            hashMap.put(str, new ConstraintAttribute(str, attributeType2, obj));
        }
        obtainStyledAttributes.recycle();
    }
}
