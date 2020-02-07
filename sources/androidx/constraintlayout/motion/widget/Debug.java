package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.leanplum.core.BuildConfig;
import com.microsoft.appcenter.Constants;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Debug {
    public static void logStack(String str, String str2, int i) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int min = Math.min(i, stackTrace.length - 1);
        String str3 = " ";
        String str4 = str3;
        for (int i2 = 1; i2 <= min; i2++) {
            StackTraceElement stackTraceElement = stackTrace[i2];
            StringBuilder sb = new StringBuilder();
            sb.append(".(");
            sb.append(stackTrace[i2].getFileName());
            sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            sb.append(stackTrace[i2].getLineNumber());
            sb.append(") ");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str4);
            sb3.append(str3);
            str4 = sb3.toString();
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str2);
            sb4.append(str4);
            sb4.append(sb2);
            sb4.append(str4);
            Log.v(str, sb4.toString());
        }
    }

    public static void printStack(String str, int i) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int min = Math.min(i, stackTrace.length - 1);
        String str2 = " ";
        String str3 = str2;
        for (int i2 = 1; i2 <= min; i2++) {
            StackTraceElement stackTraceElement = stackTrace[i2];
            StringBuilder sb = new StringBuilder();
            sb.append(".(");
            sb.append(stackTrace[i2].getFileName());
            sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            sb.append(stackTrace[i2].getLineNumber());
            sb.append(") ");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str3);
            sb3.append(str2);
            str3 = sb3.toString();
            PrintStream printStream = System.out;
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str);
            sb4.append(str3);
            sb4.append(sb2);
            sb4.append(str3);
            printStream.println(sb4.toString());
        }
    }

    public static String getName(View view) {
        try {
            return view.getContext().getResources().getResourceEntryName(view.getId());
        } catch (Exception unused) {
            return "UNKNOWN";
        }
    }

    public static void dumpPoc(Object obj) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        StringBuilder sb = new StringBuilder();
        sb.append(".(");
        sb.append(stackTraceElement.getFileName());
        sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
        sb.append(stackTraceElement.getLineNumber());
        sb.append(")");
        String sb2 = sb.toString();
        Class cls = obj.getClass();
        PrintStream printStream = System.out;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sb2);
        String str = "------------- ";
        sb3.append(str);
        sb3.append(cls.getName());
        String str2 = " --------------------";
        sb3.append(str2);
        printStream.println(sb3.toString());
        Field[] fields = cls.getFields();
        for (Field field : fields) {
            try {
                Object obj2 = field.get(obj);
                if (field.getName().startsWith("layout_constraint")) {
                    if (!(obj2 instanceof Integer) || !obj2.toString().equals("-1")) {
                        if (!(obj2 instanceof Integer) || !obj2.toString().equals(BuildConfig.BUILD_NUMBER)) {
                            if (!(obj2 instanceof Float) || !obj2.toString().equals("1.0")) {
                                if (!(obj2 instanceof Float) || !obj2.toString().equals("0.5")) {
                                    PrintStream printStream2 = System.out;
                                    StringBuilder sb4 = new StringBuilder();
                                    sb4.append(sb2);
                                    sb4.append("    ");
                                    sb4.append(field.getName());
                                    sb4.append(" ");
                                    sb4.append(obj2);
                                    printStream2.println(sb4.toString());
                                }
                            }
                        }
                    }
                }
            } catch (IllegalAccessException unused) {
            }
        }
        PrintStream printStream3 = System.out;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(sb2);
        sb5.append(str);
        sb5.append(cls.getSimpleName());
        sb5.append(str2);
        printStream3.println(sb5.toString());
    }

    public static String getName(Context context, int i) {
        String str = "UNKNOWN";
        if (i != -1) {
            try {
                return context.getResources().getResourceEntryName(i);
            } catch (Exception unused) {
            }
        }
        return str;
    }

    public static String getName(Context context, int[] iArr) {
        int i = 0;
        String str = "";
        String str2 = str;
        while (i < iArr.length) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                sb.append(i == 0 ? str : " ");
                String sb2 = sb.toString();
                StringBuilder sb3 = new StringBuilder();
                sb3.append(sb2);
                sb3.append(context.getResources().getResourceEntryName(iArr[i]));
                str2 = sb3.toString();
                i++;
            } catch (Exception unused) {
                return "UNKNOWN";
            }
        }
        return str2;
    }

    public static String getState(MotionLayout motionLayout, int i) {
        if (i == -1) {
            return "UNDEFINED";
        }
        return motionLayout.getContext().getResources().getResourceEntryName(i);
    }

    public static String getActionType(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        Field[] fields = MotionEvent.class.getFields();
        for (Field field : fields) {
            try {
                if (Modifier.isStatic(field.getModifiers()) && field.getType().equals(Integer.TYPE) && field.getInt(null) == action) {
                    return field.getName();
                }
            } catch (IllegalAccessException unused) {
            }
        }
        return "---";
    }

    public static String getLocation() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        StringBuilder sb = new StringBuilder();
        sb.append(".(");
        sb.append(stackTraceElement.getFileName());
        sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
        sb.append(stackTraceElement.getLineNumber());
        sb.append(")");
        return sb.toString();
    }

    public static String getLocation2() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        StringBuilder sb = new StringBuilder();
        sb.append(".(");
        sb.append(stackTraceElement.getFileName());
        sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
        sb.append(stackTraceElement.getLineNumber());
        sb.append(")");
        return sb.toString();
    }

    public static String getCallFrom(int i) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[i + 2];
        StringBuilder sb = new StringBuilder();
        sb.append(".(");
        sb.append(stackTraceElement.getFileName());
        sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
        sb.append(stackTraceElement.getLineNumber());
        sb.append(")");
        return sb.toString();
    }

    public static void dumpLayoutParams(ViewGroup viewGroup, String str) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        StringBuilder sb = new StringBuilder();
        sb.append(".(");
        sb.append(stackTraceElement.getFileName());
        sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
        sb.append(stackTraceElement.getLineNumber());
        sb.append(") ");
        sb.append(str);
        sb.append("  ");
        String sb2 = sb.toString();
        int childCount = viewGroup.getChildCount();
        PrintStream printStream = System.out;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str);
        sb3.append(" children ");
        sb3.append(childCount);
        printStream.println(sb3.toString());
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            PrintStream printStream2 = System.out;
            StringBuilder sb4 = new StringBuilder();
            sb4.append(sb2);
            sb4.append("     ");
            sb4.append(getName(childAt));
            printStream2.println(sb4.toString());
            LayoutParams layoutParams = childAt.getLayoutParams();
            Field[] fields = layoutParams.getClass().getFields();
            for (Field field : fields) {
                try {
                    Object obj = field.get(layoutParams);
                    if (field.getName().contains("To")) {
                        if (!obj.toString().equals("-1")) {
                            PrintStream printStream3 = System.out;
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append(sb2);
                            sb5.append("       ");
                            sb5.append(field.getName());
                            sb5.append(" ");
                            sb5.append(obj);
                            printStream3.println(sb5.toString());
                        }
                    }
                } catch (IllegalAccessException unused) {
                }
            }
        }
    }

    public static void dumpLayoutParams(LayoutParams layoutParams, String str) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        StringBuilder sb = new StringBuilder();
        sb.append(".(");
        sb.append(stackTraceElement.getFileName());
        sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
        sb.append(stackTraceElement.getLineNumber());
        sb.append(") ");
        sb.append(str);
        String str2 = "  ";
        sb.append(str2);
        String sb2 = sb.toString();
        PrintStream printStream = System.out;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(" >>>>>>>>>>>>>>>>>>. dump ");
        sb3.append(sb2);
        sb3.append(str2);
        sb3.append(layoutParams.getClass().getName());
        printStream.println(sb3.toString());
        Field[] fields = layoutParams.getClass().getFields();
        for (Field field : fields) {
            try {
                Object obj = field.get(layoutParams);
                String name = field.getName();
                if (name.contains("To")) {
                    if (!obj.toString().equals("-1")) {
                        PrintStream printStream2 = System.out;
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(sb2);
                        sb4.append("       ");
                        sb4.append(name);
                        sb4.append(" ");
                        sb4.append(obj);
                        printStream2.println(sb4.toString());
                    }
                }
            } catch (IllegalAccessException unused) {
            }
        }
        PrintStream printStream3 = System.out;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(" <<<<<<<<<<<<<<<<< dump ");
        sb5.append(sb2);
        printStream3.println(sb5.toString());
    }
}
