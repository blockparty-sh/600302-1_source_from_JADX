package com.leanplum;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;

public class LeanplumInflater {
    private Context context;
    private LeanplumResources res;

    public static LeanplumInflater from(Context context2) {
        return new LeanplumInflater(context2);
    }

    private LeanplumInflater(Context context2) {
        this.context = context2;
    }

    public LeanplumResources getLeanplumResources() {
        return getLeanplumResources(null);
    }

    public LeanplumResources getLeanplumResources(Resources resources) {
        LeanplumResources leanplumResources = this.res;
        if (leanplumResources != null) {
            return leanplumResources;
        }
        if (resources == null) {
            resources = this.context.getResources();
        }
        if (resources instanceof LeanplumResources) {
            return (LeanplumResources) resources;
        }
        this.res = new LeanplumResources(resources);
        return this.res;
    }

    public View inflate(int i) {
        return inflate(i, null, false);
    }

    public View inflate(int i, ViewGroup viewGroup) {
        return inflate(i, viewGroup, viewGroup != null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ae A[SYNTHETIC, Splitter:B:44:0x00ae] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e3 A[SYNTHETIC, Splitter:B:56:0x00e3] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00fc A[SYNTHETIC, Splitter:B:64:0x00fc] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View inflate(int r12, android.view.ViewGroup r13, boolean r14) {
        /*
            r11 = this;
            java.lang.String r0 = "Failed to close input stream."
            android.content.Context r1 = r11.context     // Catch:{ Throwable -> 0x0113 }
            android.content.res.Resources r1 = r1.getResources()     // Catch:{ Throwable -> 0x0113 }
            com.leanplum.LeanplumResources r1 = r11.getLeanplumResources(r1)     // Catch:{ Throwable -> 0x0113 }
            com.leanplum.Var r1 = r1.getOverrideResource(r12)     // Catch:{ Throwable -> 0x0113 }
            if (r1 == 0) goto L_0x0108
            java.lang.String r2 = r1.stringValue     // Catch:{ Throwable -> 0x0113 }
            java.lang.Object r3 = r1.defaultValue()     // Catch:{ Throwable -> 0x0113 }
            boolean r2 = r2.equals(r3)     // Catch:{ Throwable -> 0x0113 }
            if (r2 == 0) goto L_0x0020
            goto L_0x0108
        L_0x0020:
            int r2 = r1.overrideResId()     // Catch:{ Throwable -> 0x0113 }
            if (r2 == 0) goto L_0x0031
            android.content.Context r0 = r11.context     // Catch:{ Throwable -> 0x0113 }
            android.view.LayoutInflater r0 = android.view.LayoutInflater.from(r0)     // Catch:{ Throwable -> 0x0113 }
            android.view.View r12 = r0.inflate(r2, r13, r14)     // Catch:{ Throwable -> 0x0113 }
            return r12
        L_0x0031:
            r2 = 0
            r3 = 1
            r4 = 0
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x00b7, all -> 0x00b4 }
            r5.<init>()     // Catch:{ Throwable -> 0x00b7, all -> 0x00b4 }
            java.io.InputStream r6 = r1.stream()     // Catch:{ Throwable -> 0x00b7, all -> 0x00b4 }
            r7 = 8192(0x2000, float:1.14794E-41)
            byte[] r7 = new byte[r7]     // Catch:{ Throwable -> 0x00b2 }
        L_0x0041:
            int r8 = r6.read(r7)     // Catch:{ Throwable -> 0x00b2 }
            r9 = -1
            if (r8 <= r9) goto L_0x004c
            r5.write(r7, r4, r8)     // Catch:{ Throwable -> 0x00b2 }
            goto L_0x0041
        L_0x004c:
            java.lang.String r7 = "android.content.res.XmlBlock"
            java.lang.Class r7 = java.lang.Class.forName(r7)     // Catch:{ Throwable -> 0x00b2 }
            java.lang.Class[] r8 = new java.lang.Class[r3]     // Catch:{ Throwable -> 0x00b2 }
            java.lang.Class<byte[]> r9 = byte[].class
            r8[r4] = r9     // Catch:{ Throwable -> 0x00b2 }
            java.lang.reflect.Constructor r7 = r7.getConstructor(r8)     // Catch:{ Throwable -> 0x00b2 }
            java.lang.Object[] r8 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x00b2 }
            byte[] r5 = r5.toByteArray()     // Catch:{ Throwable -> 0x00b2 }
            r8[r4] = r5     // Catch:{ Throwable -> 0x00b2 }
            java.lang.Object r5 = r7.newInstance(r8)     // Catch:{ Throwable -> 0x00b2 }
            java.lang.Class r7 = r5.getClass()     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r8 = "newParser"
            java.lang.Class[] r9 = new java.lang.Class[r4]     // Catch:{ Throwable -> 0x00a5 }
            java.lang.reflect.Method r7 = r7.getMethod(r8, r9)     // Catch:{ Throwable -> 0x00a5 }
            java.lang.Object[] r8 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x00a5 }
            java.lang.Object r5 = r7.invoke(r5, r8)     // Catch:{ Throwable -> 0x00a5 }
            android.content.res.XmlResourceParser r5 = (android.content.res.XmlResourceParser) r5     // Catch:{ Throwable -> 0x00a5 }
            android.content.Context r2 = r11.context     // Catch:{ Throwable -> 0x009e, all -> 0x0099 }
            android.view.LayoutInflater r2 = android.view.LayoutInflater.from(r2)     // Catch:{ Throwable -> 0x009e, all -> 0x0099 }
            android.view.View r2 = r2.inflate(r5, r13, r14)     // Catch:{ Throwable -> 0x009e, all -> 0x0099 }
            if (r5 == 0) goto L_0x008b
            r5.close()     // Catch:{ Throwable -> 0x00b2 }
        L_0x008b:
            if (r6 == 0) goto L_0x0098
            r6.close()     // Catch:{ IOException -> 0x0091 }
            goto L_0x0098
        L_0x0091:
            java.lang.Object[] r12 = new java.lang.Object[r3]
            r12[r4] = r0
            com.leanplum.internal.Log.m280e(r12)
        L_0x0098:
            return r2
        L_0x0099:
            r2 = move-exception
            r10 = r5
            r5 = r2
            r2 = r10
            goto L_0x00ac
        L_0x009e:
            r2 = move-exception
            r10 = r5
            r5 = r2
            r2 = r10
            goto L_0x00a6
        L_0x00a3:
            r5 = move-exception
            goto L_0x00ac
        L_0x00a5:
            r5 = move-exception
        L_0x00a6:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException     // Catch:{ all -> 0x00a3 }
            r7.<init>(r5)     // Catch:{ all -> 0x00a3 }
            throw r7     // Catch:{ all -> 0x00a3 }
        L_0x00ac:
            if (r2 == 0) goto L_0x00b1
            r2.close()     // Catch:{ Throwable -> 0x00b2 }
        L_0x00b1:
            throw r5     // Catch:{ Throwable -> 0x00b2 }
        L_0x00b2:
            r2 = move-exception
            goto L_0x00ba
        L_0x00b4:
            r12 = move-exception
            r6 = r2
            goto L_0x00fa
        L_0x00b7:
            r5 = move-exception
            r6 = r2
            r2 = r5
        L_0x00ba:
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x00f9 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f9 }
            r7.<init>()     // Catch:{ all -> 0x00f9 }
            java.lang.String r8 = "Could not inflate resource "
            r7.append(r8)     // Catch:{ all -> 0x00f9 }
            r7.append(r12)     // Catch:{ all -> 0x00f9 }
            java.lang.String r8 = ":"
            r7.append(r8)     // Catch:{ all -> 0x00f9 }
            java.lang.String r1 = r1.stringValue()     // Catch:{ all -> 0x00f9 }
            r7.append(r1)     // Catch:{ all -> 0x00f9 }
            java.lang.String r1 = r7.toString()     // Catch:{ all -> 0x00f9 }
            r5[r4] = r1     // Catch:{ all -> 0x00f9 }
            r5[r3] = r2     // Catch:{ all -> 0x00f9 }
            com.leanplum.internal.Log.m280e(r5)     // Catch:{ all -> 0x00f9 }
            if (r6 == 0) goto L_0x00ee
            r6.close()     // Catch:{ IOException -> 0x00e7 }
            goto L_0x00ee
        L_0x00e7:
            java.lang.Object[] r1 = new java.lang.Object[r3]
            r1[r4] = r0
            com.leanplum.internal.Log.m280e(r1)
        L_0x00ee:
            android.content.Context r0 = r11.context
            android.view.LayoutInflater r0 = android.view.LayoutInflater.from(r0)
            android.view.View r12 = r0.inflate(r12, r13, r14)
            return r12
        L_0x00f9:
            r12 = move-exception
        L_0x00fa:
            if (r6 == 0) goto L_0x0107
            r6.close()     // Catch:{ IOException -> 0x0100 }
            goto L_0x0107
        L_0x0100:
            java.lang.Object[] r13 = new java.lang.Object[r3]
            r13[r4] = r0
            com.leanplum.internal.Log.m280e(r13)
        L_0x0107:
            throw r12
        L_0x0108:
            android.content.Context r0 = r11.context     // Catch:{ Throwable -> 0x0113 }
            android.view.LayoutInflater r0 = android.view.LayoutInflater.from(r0)     // Catch:{ Throwable -> 0x0113 }
            android.view.View r12 = r0.inflate(r12, r13, r14)     // Catch:{ Throwable -> 0x0113 }
            return r12
        L_0x0113:
            r0 = move-exception
            boolean r1 = r0 instanceof android.view.InflateException
            if (r1 != 0) goto L_0x011b
            com.leanplum.internal.Util.handleException(r0)
        L_0x011b:
            android.content.Context r0 = r11.context
            android.view.LayoutInflater r0 = android.view.LayoutInflater.from(r0)
            android.view.View r12 = r0.inflate(r12, r13, r14)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.LeanplumInflater.inflate(int, android.view.ViewGroup, boolean):android.view.View");
    }
}
