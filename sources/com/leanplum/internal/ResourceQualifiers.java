package com.leanplum.internal;

import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.microsoft.appcenter.ingestion.models.properties.LongTypedProperty;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.cli.HelpFormatter;
import org.bitcoinj.net.discovery.TorDiscovery;
import org.bitcoinj.uri.BitcoinURI;

public class ResourceQualifiers {
    public Map<Qualifier, Object> qualifiers = new HashMap();

    public enum Qualifier {
        MCC(new QualifierFilter() {
            public Object getMatch(String str) {
                if (str.startsWith("mcc")) {
                    return Integer.getInteger(str.substring(3));
                }
                return null;
            }

            public boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics) {
                return configuration.mcc == ((Integer) obj).intValue();
            }
        }),
        MNC(new QualifierFilter() {
            public Object getMatch(String str) {
                if (str.startsWith("mnc")) {
                    return Integer.getInteger(str.substring(3));
                }
                return null;
            }

            public boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics) {
                return configuration.mnc == ((Integer) obj).intValue();
            }
        }),
        LANGUAGE(new QualifierFilter() {
            public Object getMatch(String str) {
                if (str.length() == 2) {
                    return str;
                }
                return null;
            }

            public boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics) {
                return configuration.locale.getLanguage().equals(obj);
            }
        }),
        REGION(new QualifierFilter() {
            public Object getMatch(String str) {
                if (!str.startsWith(BitcoinURI.FIELD_PAYMENT_REQUEST_URL) || str.length() != 3) {
                    return null;
                }
                return str.substring(1);
            }

            public boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics) {
                return configuration.locale.getCountry().toLowerCase().equals(obj);
            }
        }),
        LAYOUT_DIRECTION(new QualifierFilter() {
            public static final int SCREENLAYOUT_LAYOUTDIR_LTR = 64;
            public static final int SCREENLAYOUT_LAYOUTDIR_MASK = 192;
            public static final int SCREENLAYOUT_LAYOUTDIR_RTL = 128;

            public Object getMatch(String str) {
                if ("ldrtl".equals(str)) {
                    return Integer.valueOf(128);
                }
                if ("ldltr".equals(str)) {
                    return Integer.valueOf(64);
                }
                return null;
            }

            public boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics) {
                return (configuration.screenLayout & 192) == ((Integer) obj).intValue();
            }
        }),
        SMALLEST_WIDTH(new QualifierFilter() {
            public Object getMatch(String str) {
                if (!str.startsWith("sw") || !str.endsWith("dp")) {
                    return null;
                }
                return Integer.getInteger(str.substring(2, str.length() - 2));
            }

            public boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics) {
                boolean z = false;
                try {
                    if (((Integer) configuration.getClass().getField("smallestScreenWidthDp").get(configuration)).intValue() >= ((Integer) obj).intValue()) {
                        z = true;
                    }
                    return z;
                } catch (Throwable th) {
                    Util.handleException(th);
                    return false;
                }
            }

            public Map<String, Object> bestMatch(Map<String, Object> map, Configuration configuration, DisplayMetrics displayMetrics) {
                HashMap hashMap = new HashMap();
                int i = Integer.MIN_VALUE;
                for (Entry entry : map.entrySet()) {
                    Integer num = (Integer) entry.getValue();
                    if (num.intValue() > i) {
                        i = num.intValue();
                        hashMap.clear();
                    }
                    if (num.intValue() == i) {
                        hashMap.put(entry.getKey(), num);
                    }
                }
                return hashMap;
            }
        }),
        AVAILABLE_WIDTH(new QualifierFilter() {
            public Object getMatch(String str) {
                if (!str.startsWith("w") || !str.endsWith("dp")) {
                    return null;
                }
                return Integer.getInteger(str.substring(1, str.length() - 2));
            }

            public boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics) {
                boolean z = false;
                try {
                    if (((Integer) configuration.getClass().getField("screenWidthDp").get(configuration)).intValue() >= ((Integer) obj).intValue()) {
                        z = true;
                    }
                    return z;
                } catch (Throwable th) {
                    Util.handleException(th);
                    return false;
                }
            }

            public Map<String, Object> bestMatch(Map<String, Object> map, Configuration configuration, DisplayMetrics displayMetrics) {
                HashMap hashMap = new HashMap();
                int i = Integer.MIN_VALUE;
                for (Entry entry : map.entrySet()) {
                    Integer num = (Integer) entry.getValue();
                    if (num.intValue() > i) {
                        i = num.intValue();
                        hashMap.clear();
                    }
                    if (num.intValue() == i) {
                        hashMap.put(entry.getKey(), num);
                    }
                }
                return hashMap;
            }
        }),
        AVAILABLE_HEIGHT(new QualifierFilter() {
            public Object getMatch(String str) {
                if (!str.startsWith("h") || !str.endsWith("dp")) {
                    return null;
                }
                return Integer.getInteger(str.substring(1, str.length() - 2));
            }

            public boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics) {
                boolean z = false;
                try {
                    if (((Integer) configuration.getClass().getField("screenHeightDp").get(configuration)).intValue() >= ((Integer) obj).intValue()) {
                        z = true;
                    }
                    return z;
                } catch (Throwable th) {
                    Util.handleException(th);
                    return false;
                }
            }

            public Map<String, Object> bestMatch(Map<String, Object> map, Configuration configuration, DisplayMetrics displayMetrics) {
                HashMap hashMap = new HashMap();
                int i = Integer.MIN_VALUE;
                for (Entry entry : map.entrySet()) {
                    Integer num = (Integer) entry.getValue();
                    if (num.intValue() > i) {
                        i = num.intValue();
                        hashMap.clear();
                    }
                    if (num.intValue() == i) {
                        hashMap.put(entry.getKey(), num);
                    }
                }
                return hashMap;
            }
        }),
        SCREEN_SIZE(new QualifierFilter() {
            public Object getMatch(String str) {
                if ("small".equals(str)) {
                    return Integer.valueOf(1);
                }
                if ("normal".equals(str)) {
                    return Integer.valueOf(2);
                }
                if ("large".equals(str)) {
                    return Integer.valueOf(3);
                }
                if ("xlarge".equals(str)) {
                    return Integer.valueOf(4);
                }
                return null;
            }

            public boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics) {
                return (configuration.screenLayout & 15) <= ((Integer) obj).intValue();
            }

            public Map<String, Object> bestMatch(Map<String, Object> map, Configuration configuration, DisplayMetrics displayMetrics) {
                HashMap hashMap = new HashMap();
                int i = Integer.MIN_VALUE;
                for (Entry entry : map.entrySet()) {
                    Integer num = (Integer) entry.getValue();
                    if (num.intValue() > i) {
                        i = num.intValue();
                        hashMap.clear();
                    }
                    if (num.intValue() == i) {
                        hashMap.put(entry.getKey(), num);
                    }
                }
                return hashMap;
            }
        }),
        SCREEN_ASPECT(new QualifierFilter() {
            public Object getMatch(String str) {
                if (LongTypedProperty.TYPE.equals(str)) {
                    return Integer.valueOf(32);
                }
                if ("notlong".equals(str)) {
                    return Integer.valueOf(16);
                }
                return null;
            }

            public boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics) {
                return (configuration.screenLayout & 48) == ((Integer) obj).intValue();
            }
        }),
        SCREEN_ORIENTATION(new QualifierFilter() {
            public Object getMatch(String str) {
                if ("port".equals(str)) {
                    return Integer.valueOf(1);
                }
                if ("land".equals(str)) {
                    return Integer.valueOf(2);
                }
                return null;
            }

            public boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics) {
                return configuration.orientation == ((Integer) obj).intValue();
            }
        }),
        UI_MODE(new QualifierFilter() {
            public static final int UI_MODE_TYPE_APPLIANCE = 5;
            public static final int UI_MODE_TYPE_TELEVISION = 4;

            public Object getMatch(String str) {
                if ("car".equals(str)) {
                    return Integer.valueOf(3);
                }
                if ("desk".equals(str)) {
                    return Integer.valueOf(2);
                }
                if ("television".equals(str)) {
                    return Integer.valueOf(4);
                }
                if ("appliance".equals(str)) {
                    return Integer.valueOf(5);
                }
                return null;
            }

            public boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics) {
                return (configuration.uiMode & 15) == ((Integer) obj).intValue();
            }
        }),
        NIGHT_MODE(new QualifierFilter() {
            public Object getMatch(String str) {
                if ("night".equals(str)) {
                    return Integer.valueOf(32);
                }
                if ("notnight".equals(str)) {
                    return Integer.valueOf(16);
                }
                return null;
            }

            public boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics) {
                return (configuration.uiMode & 48) == ((Integer) obj).intValue();
            }
        }),
        SCREEN_PIXEL_DENSITY(new QualifierFilter() {
            public static final int DENSITY_NONE = 0;
            public static final int DENSITY_TV = 213;
            public static final int DENSITY_XXHIGH = 480;

            public boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics) {
                return true;
            }

            public Object getMatch(String str) {
                if ("ldpi".equals(str)) {
                    return Integer.valueOf(120);
                }
                if ("mdpi".equals(str)) {
                    return Integer.valueOf(160);
                }
                if ("hdpi".equals(str)) {
                    return Integer.valueOf(TorDiscovery.RESOLVE_ERROR);
                }
                if ("xhdpi".equals(str)) {
                    return Integer.valueOf(320);
                }
                if ("nodpi".equals(str)) {
                    return Integer.valueOf(0);
                }
                if ("tvdpi".equals(str)) {
                    return Integer.valueOf(DENSITY_TV);
                }
                if ("xxhigh".equals(str)) {
                    return Integer.valueOf(DENSITY_XXHIGH);
                }
                return null;
            }

            public Map<String, Object> bestMatch(Map<String, Object> map, Configuration configuration, DisplayMetrics displayMetrics) {
                HashMap hashMap = new HashMap();
                int i = Integer.MAX_VALUE;
                for (Entry entry : map.entrySet()) {
                    Integer num = (Integer) entry.getValue();
                    if (num.intValue() < i && num.intValue() >= displayMetrics.densityDpi) {
                        i = num.intValue();
                        hashMap.clear();
                    }
                    if (num.intValue() == i) {
                        hashMap.put(entry.getKey(), num);
                    }
                }
                if (hashMap.size() == 0) {
                    int i2 = Integer.MIN_VALUE;
                    for (String str : map.keySet()) {
                        Integer num2 = (Integer) map.get(str);
                        if (num2.intValue() > i2) {
                            i2 = num2.intValue();
                            hashMap.clear();
                        }
                        if (num2.intValue() == i2) {
                            hashMap.put(str, num2);
                        }
                    }
                }
                return hashMap;
            }
        }),
        TOUCHSCREEN_TYPE(new QualifierFilter() {
            public Object getMatch(String str) {
                if ("notouch".equals(str)) {
                    return Integer.valueOf(1);
                }
                if ("finger".equals(str)) {
                    return Integer.valueOf(3);
                }
                return null;
            }

            public boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics) {
                return configuration.touchscreen == ((Integer) obj).intValue();
            }
        }),
        KEYBOARD_AVAILABILITY(new QualifierFilter() {
            public Object getMatch(String str) {
                if ("keysexposed".equals(str)) {
                    return Integer.valueOf(1);
                }
                if ("keyshidden".equals(str)) {
                    return Integer.valueOf(2);
                }
                if ("keyssoft".equals(str)) {
                    return Integer.valueOf(0);
                }
                return null;
            }

            public boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics) {
                Integer num = (Integer) obj;
                return num.intValue() == 0 || configuration.keyboardHidden == num.intValue();
            }
        }),
        PRIMARY_TEXT_INPUTMETHOD(new QualifierFilter() {
            public Object getMatch(String str) {
                if ("nokeys".equals(str)) {
                    return Integer.valueOf(1);
                }
                if ("qwerty".equals(str)) {
                    return Integer.valueOf(2);
                }
                if ("12key".equals(str)) {
                    return Integer.valueOf(3);
                }
                return null;
            }

            public boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics) {
                return configuration.keyboard == ((Integer) obj).intValue();
            }
        }),
        NAVIGATION_KEY_AVAILABILITY(new QualifierFilter() {
            public Object getMatch(String str) {
                if ("navexposed".equals(str)) {
                    return Integer.valueOf(1);
                }
                if ("navhidden".equals(str)) {
                    return Integer.valueOf(2);
                }
                return null;
            }

            public boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics) {
                return configuration.navigationHidden == ((Integer) obj).intValue();
            }
        }),
        PRIMARY_NON_TOUCH_NAVIGATION_METHOD(new QualifierFilter() {
            public Object getMatch(String str) {
                if ("nonav".equals(str)) {
                    return Integer.valueOf(1);
                }
                if ("dpad".equals(str)) {
                    return Integer.valueOf(2);
                }
                if ("trackball".equals(str)) {
                    return Integer.valueOf(3);
                }
                if ("wheel".equals(str)) {
                    return Integer.valueOf(4);
                }
                return null;
            }

            public boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics) {
                return configuration.navigation == ((Integer) obj).intValue();
            }
        }),
        PLATFORM_VERSION(new QualifierFilter() {
            public Object getMatch(String str) {
                if (str.startsWith("v")) {
                    return Integer.getInteger(str.substring(1));
                }
                return null;
            }

            public boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics) {
                return VERSION.SDK_INT >= ((Integer) obj).intValue();
            }
        });
        
        private QualifierFilter filter;

        private Qualifier(QualifierFilter qualifierFilter) {
            this.filter = qualifierFilter;
        }

        public QualifierFilter getFilter() {
            return this.filter;
        }
    }

    public static abstract class QualifierFilter {
        public Map<String, Object> bestMatch(Map<String, Object> map, Configuration configuration, DisplayMetrics displayMetrics) {
            return map;
        }

        /* access modifiers changed from: 0000 */
        public abstract Object getMatch(String str);

        public abstract boolean isMatch(Object obj, Configuration configuration, DisplayMetrics displayMetrics);
    }

    public static ResourceQualifiers fromFolder(String str) {
        ResourceQualifiers resourceQualifiers = new ResourceQualifiers();
        String[] split = str.toLowerCase().split(HelpFormatter.DEFAULT_OPT_PREFIX);
        int length = split.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            String str2 = split[i];
            int i3 = i2;
            boolean z = false;
            while (!z && i3 < Qualifier.values().length) {
                Qualifier qualifier = Qualifier.values()[i3];
                Object match = qualifier.getFilter().getMatch(str2);
                if (match != null) {
                    resourceQualifiers.qualifiers.put(qualifier, match);
                    z = true;
                }
                i3++;
            }
            i++;
            i2 = i3;
        }
        return resourceQualifiers;
    }
}
