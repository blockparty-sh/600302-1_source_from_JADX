package com.leanplum.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import androidx.core.view.ViewCompat;
import com.leanplum.internal.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class BitmapUtil {
    private static final int BIG_PICTURE_MAX_HEIGHT_DP = 192;

    public static int getDarker(int i, int i2) {
        if (i2 < 0 || i2 > 100) {
            i2 = 0;
        }
        double d = ((double) (100 - i2)) / 100.0d;
        return (((int) (((double) (i & 255)) * d)) & 255) | (((i >> 24) & 255) << 24) | ((((int) (((double) ((i >> 16) & 255)) * d)) & 255) << 16) | ((((int) (((double) ((i >> 8) & 255)) * d)) & 255) << 8);
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        float f = (float) i;
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    public static void stateBackgroundDarkerByPercentage(View view, int i, int i2) {
        stateBackground(view, i, getDarker(i, i2));
    }

    public static void stateBackground(View view, int i, int i2) {
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(getBackground(i, i2));
        } else {
            view.setBackgroundColor(i);
        }
    }

    private static Drawable getBackground(int i, int i2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        float f = (float) SizeUtil.dp10;
        RoundRectShape roundRectShape = new RoundRectShape(new float[]{f, f, f, f, f, f, f, f}, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setShape(roundRectShape);
        shapeDrawable.getPaint().setColor(i2);
        stateListDrawable.addState(new int[]{16842919, 16842908}, shapeDrawable);
        stateListDrawable.addState(new int[]{-16842919, 16842908}, shapeDrawable);
        stateListDrawable.addState(new int[]{16842919, -16842908}, shapeDrawable);
        ShapeDrawable shapeDrawable2 = new ShapeDrawable();
        shapeDrawable2.setShape(roundRectShape);
        shapeDrawable2.getPaint().setColor(i);
        stateListDrawable.addState(new int[]{-16842919, -16842908}, shapeDrawable2);
        return stateListDrawable;
    }

    private static int calculateInSampleSize(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 >= i2 && i7 / i5 >= i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    private static Bitmap getBitmapFromUrl(String str, int i, int i2) {
        InputStream inputStream;
        try {
            inputStream = new URL(str).openStream();
            try {
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(inputStream, null, options);
                closeStream(inputStream);
                InputStream openStream = new URL(str).openStream();
                options.inSampleSize = calculateInSampleSize(options, i, i2);
                options.inJustDecodeBounds = false;
                Bitmap decodeStream = BitmapFactory.decodeStream(openStream, null, options);
                closeStream(openStream);
                return decodeStream;
            } catch (IOException e) {
                e = e;
                try {
                    Log.m280e(String.format("IOException in image download for URL: %s.", new Object[]{str}), e);
                    closeStream(inputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    closeStream(inputStream);
                    throw th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            inputStream = null;
            Log.m280e(String.format("IOException in image download for URL: %s.", new Object[]{str}), e);
            closeStream(inputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            closeStream(inputStream);
            throw th;
        }
    }

    public static Bitmap getScaledBitmap(Context context, String str) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int round = Math.round((displayMetrics.density * 192.0f) + 0.5f);
        int min = Math.min(round * 2, displayMetrics.widthPixels);
        Bitmap bitmapFromUrl = getBitmapFromUrl(str, min, round);
        try {
            return Bitmap.createScaledBitmap(bitmapFromUrl, min, round, true);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed on scale image ");
            sb.append(str);
            sb.append(" to (");
            sb.append(min);
            sb.append(", ");
            sb.append(round);
            sb.append(")");
            Log.m280e(sb.toString(), e);
            return bitmapFromUrl;
        }
    }

    private static void closeStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                Log.m280e("IOException during closing of image download stream.", e);
            }
        }
    }
}
