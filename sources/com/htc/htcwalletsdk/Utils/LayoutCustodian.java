package com.htc.htcwalletsdk.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.htc.htcwalletsdk.CONSTANT.HW_SECURITY_UI;
import java.io.File;
import java.io.FileOutputStream;

public class LayoutCustodian {
    static final String TAG = "LayoutCustodian";

    public static boolean saveLayout(Context context, int i, String str) {
        try {
            Point point = new Point();
            ((Activity) context).getWindowManager().getDefaultDisplay().getRealSize(point);
            ViewGroup viewGroup = (ViewGroup) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(i, null);
            viewGroup.measure(MeasureSpec.makeMeasureSpec(point.x, 1073741824), MeasureSpec.makeMeasureSpec(point.y, 1073741824));
            viewGroup.layout(0, 0, viewGroup.getMeasuredWidth(), viewGroup.getMeasuredHeight());
            Bitmap viewToBitmap = viewToBitmap(viewGroup);
            try {
                File file = new File(context.getFilesDir(), "tmp");
                if (!file.exists()) {
                    file.mkdir();
                }
                StringBuilder sb = new StringBuilder();
                sb.append(file.getPath());
                sb.append(File.separator);
                sb.append(str);
                FileOutputStream fileOutputStream = new FileOutputStream(sb.toString());
                viewToBitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean saveLayout(Context context, HW_SECURITY_UI hw_security_ui, String str, int i) {
        String str2 = TAG;
        try {
            Point point = new Point();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealSize(point);
            int i2 = point.x;
            int i3 = point.y;
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(i2));
            sb.append(",");
            sb.append(String.valueOf(i3));
            ZKMALog.m272d(str2, sb.toString());
            HWLayoutHelper hWLayoutHelper = new HWLayoutHelper(context, hw_security_ui, i2, i3, i);
            Bitmap viewToBitmap = viewToBitmap(hWLayoutHelper.getView());
            try {
                File file = new File(context.getFilesDir(), "tmp");
                if (!file.exists()) {
                    file.mkdir();
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append(file.getPath());
                sb2.append(File.separator);
                sb2.append(str);
                FileOutputStream fileOutputStream = new FileOutputStream(sb2.toString());
                viewToBitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.close();
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Create image:");
                sb3.append(file.getPath());
                sb3.append(File.separator);
                sb3.append(str);
                ZKMALog.m272d(str2, sb3.toString());
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean saveLayout(Context context, HW_SECURITY_UI hw_security_ui, String str, String str2) {
        String str3 = TAG;
        try {
            Point point = new Point();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealSize(point);
            int i = point.x;
            int i2 = point.y;
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(i));
            sb.append(",");
            sb.append(String.valueOf(i2));
            ZKMALog.m272d(str3, sb.toString());
            HWLayoutHelper hWLayoutHelper = new HWLayoutHelper(context, hw_security_ui, i, i2, str2);
            Bitmap viewToBitmap = viewToBitmap(hWLayoutHelper.getView());
            try {
                File file = new File(context.getFilesDir(), "tmp");
                if (!file.exists()) {
                    file.mkdir();
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append(file.getPath());
                sb2.append(File.separator);
                sb2.append(str);
                FileOutputStream fileOutputStream = new FileOutputStream(sb2.toString());
                viewToBitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.close();
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Create image:");
                sb3.append(file.getPath());
                sb3.append(File.separator);
                sb3.append(str);
                ZKMALog.m272d(str3, sb3.toString());
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean saveLayout(Context context, HW_SECURITY_UI hw_security_ui, String str) {
        String str2 = TAG;
        try {
            Point point = new Point();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealSize(point);
            int i = point.x;
            int i2 = point.y;
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(i));
            sb.append(",");
            sb.append(String.valueOf(i2));
            ZKMALog.m272d(str2, sb.toString());
            Bitmap viewToBitmap = viewToBitmap(new HWLayoutHelper(context, hw_security_ui, i, i2).getView());
            try {
                File file = new File(context.getFilesDir(), "tmp");
                if (!file.exists()) {
                    file.mkdir();
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append(file.getPath());
                sb2.append(File.separator);
                sb2.append(str);
                FileOutputStream fileOutputStream = new FileOutputStream(sb2.toString());
                viewToBitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.close();
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Create image:");
                sb3.append(file.getPath());
                sb3.append(File.separator);
                sb3.append(str);
                ZKMALog.m272d(str2, sb3.toString());
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean removeLayout(Context context, HW_SECURITY_UI hw_security_ui, String str) {
        String str2 = TAG;
        boolean z = false;
        try {
            if (hw_security_ui != HW_SECURITY_UI.VERIFY_SOCIAL_CODE) {
                return false;
            }
            try {
                File file = new File(context.getFilesDir(), "tmp");
                StringBuilder sb = new StringBuilder();
                sb.append("file:");
                sb.append(file.getPath());
                ZKMALog.m272d(str2, sb.toString());
                if (!file.exists()) {
                    return false;
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append(file.getPath());
                sb2.append(File.separator);
                sb2.append(str);
                File file2 = new File(sb2.toString());
                StringBuilder sb3 = new StringBuilder();
                sb3.append("png_file:");
                sb3.append(file2.getPath());
                ZKMALog.m272d(str2, sb3.toString());
                if (file2.delete()) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(file2.getName());
                    sb4.append(" is deleted!");
                    ZKMALog.m272d(str2, sb4.toString());
                    z = true;
                } else {
                    ZKMALog.m272d(str2, "Delete operation is failed.");
                }
                return z;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static Bitmap viewToBitmap(View view) {
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }
}
