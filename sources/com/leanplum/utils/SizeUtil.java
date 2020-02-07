package com.leanplum.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import androidx.recyclerview.widget.ItemTouchHelper.Callback;

public class SizeUtil {
    public static int dp10 = 0;
    public static int dp100 = 0;
    public static int dp14 = 0;
    public static int dp16 = 0;
    public static int dp18 = 0;
    public static int dp2 = 0;
    public static int dp20 = 0;
    public static int dp200 = 0;
    public static int dp250 = 0;
    public static int dp30 = 0;
    public static int dp5 = 0;
    public static int dp50 = 0;
    public static int dp7 = 0;
    private static boolean hasInited = false;
    public static final int textSize0 = 20;
    public static final int textSize0_1 = 18;
    public static final int textSize0_2 = 16;
    public static final int textSize1 = 22;
    public static final int textSize2 = 24;

    public static void init(Context context) {
        if (!hasInited) {
            hasInited = true;
            dp30 = dpToPx(context, 30);
            dp5 = dpToPx(context, 5);
            dp20 = dpToPx(context, 20);
            dp10 = dpToPx(context, 10);
            dp7 = dpToPx(context, 7);
            dp18 = dpToPx(context, 18);
            dp16 = dpToPx(context, 16);
            dp14 = dpToPx(context, 14);
            dp100 = dpToPx(context, 100);
            dp200 = dpToPx(context, 200);
            dp250 = dpToPx(context, Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            dp2 = dpToPx(context, 2);
            dp50 = dpToPx(context, 50);
        }
    }

    public static int dpToPx(Context context, int i) {
        init(context);
        return Math.round(((float) i) * (context.getResources().getDisplayMetrics().xdpi / 160.0f));
    }

    public static int pxToDp(Context context, int i) {
        init(context);
        return Math.round(((float) i) / (context.getResources().getDisplayMetrics().xdpi / 160.0f));
    }

    public static int spToPx(Context context, int i) {
        init(context);
        return (int) (((float) i) * context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static int pxToSp(Context context, int i) {
        init(context);
        return (int) (((float) i) / context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static int getStatusBarHeight(Activity activity) {
        init(activity);
        int i = 0;
        if ((activity.getWindow().getAttributes().flags & 1024) == 1024) {
            return 0;
        }
        int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            i = activity.getResources().getDimensionPixelSize(identifier);
        }
        return i;
    }

    public static int getStatusBarHeight(Context context) {
        init(context);
        try {
            int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (identifier > 0) {
                return context.getResources().getDimensionPixelSize(identifier);
            }
            return 0;
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static Point getDisplaySize(Activity activity) {
        Point point = new Point();
        if (activity == null) {
            return point;
        }
        try {
            activity.getWindowManager().getDefaultDisplay().getSize(point);
        } catch (Throwable unused) {
        }
        return point;
    }
}
