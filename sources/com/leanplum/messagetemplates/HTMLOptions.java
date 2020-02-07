package com.leanplum.messagetemplates;

import android.app.Activity;
import android.text.TextUtils;
import com.leanplum.ActionArgs;
import com.leanplum.ActionContext;
import com.leanplum.Leanplum;
import com.leanplum.utils.SizeUtil;
import java.io.File;
import java.util.Map;

class HTMLOptions {
    private ActionContext actionContext;
    private String actionUrl;
    private String closeUrl;
    private String htmlAlign;
    private int htmlHeight;
    private boolean htmlTabOutsideToClose;
    private String htmlTemplate;
    private Size htmlWidth;
    private Size htmlYOffset;
    private String openUrl;
    private String trackActionUrl;
    private String trackUrl;

    static class Size {
        String type;
        int value;

        Size() {
        }
    }

    HTMLOptions(ActionContext actionContext2) {
        setActionContext(actionContext2);
        setHtmlTemplate(getTemplate(actionContext2));
        setCloseUrl(actionContext2.stringNamed("Close URL"));
        setOpenUrl(actionContext2.stringNamed("Open URL"));
        setTrackUrl(actionContext2.stringNamed("Track URL"));
        setActionUrl(actionContext2.stringNamed("Action URL"));
        setTrackActionUrl(actionContext2.stringNamed("Track Action URL"));
        setHtmlAlign(actionContext2.stringNamed("HTML Align"));
        setHtmlHeight(actionContext2.numberNamed("HTML Height").intValue());
        setHtmlWidth(actionContext2.stringNamed("HTML Width"));
        setHtmlYOffset(actionContext2.stringNamed("HTML Y Offset"));
        setHtmlTabOutsideToClose(actionContext2.booleanNamed("Tap Outside to Close"));
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0056 A[SYNTHETIC, Splitter:B:29:0x0056] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x005e A[Catch:{ Exception -> 0x005a }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007b A[SYNTHETIC, Splitter:B:40:0x007b] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0083 A[Catch:{ Exception -> 0x007f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String readFileAsString(com.leanplum.ActionContext r6, java.lang.String r7) {
        /*
            java.lang.String r0 = "Failed to close InputStream or BufferedReader: "
            java.lang.String r1 = "Leanplum"
            r2 = 0
            if (r6 == 0) goto L_0x009a
            boolean r3 = android.text.TextUtils.isEmpty(r7)
            if (r3 == 0) goto L_0x000f
            goto L_0x009a
        L_0x000f:
            java.io.InputStream r6 = r6.streamNamed(r7)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ IOException -> 0x004f }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x004f }
            java.lang.String r5 = "UTF-8"
            r4.<init>(r6, r5)     // Catch:{ IOException -> 0x004f }
            r3.<init>(r4)     // Catch:{ IOException -> 0x004f }
        L_0x0024:
            java.lang.String r2 = r3.readLine()     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
            if (r2 == 0) goto L_0x0033
            r7.append(r2)     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
            java.lang.String r2 = "\n"
            r7.append(r2)     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
            goto L_0x0024
        L_0x0033:
            r3.close()     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
            if (r6 == 0) goto L_0x003e
            r6.close()     // Catch:{ Exception -> 0x003c }
            goto L_0x003e
        L_0x003c:
            r6 = move-exception
            goto L_0x0042
        L_0x003e:
            r3.close()     // Catch:{ Exception -> 0x003c }
            goto L_0x0074
        L_0x0042:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            goto L_0x0067
        L_0x0048:
            r7 = move-exception
            r2 = r3
            goto L_0x0079
        L_0x004b:
            r2 = r3
            goto L_0x004f
        L_0x004d:
            r7 = move-exception
            goto L_0x0079
        L_0x004f:
            java.lang.String r3 = "Fail to get HTML template."
            android.util.Log.e(r1, r3)     // Catch:{ all -> 0x004d }
            if (r6 == 0) goto L_0x005c
            r6.close()     // Catch:{ Exception -> 0x005a }
            goto L_0x005c
        L_0x005a:
            r6 = move-exception
            goto L_0x0062
        L_0x005c:
            if (r2 == 0) goto L_0x0074
            r2.close()     // Catch:{ Exception -> 0x005a }
            goto L_0x0074
        L_0x0062:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
        L_0x0067:
            r2.append(r0)
            r2.append(r6)
            java.lang.String r6 = r2.toString()
            android.util.Log.w(r1, r6)
        L_0x0074:
            java.lang.String r6 = r7.toString()
            return r6
        L_0x0079:
            if (r6 == 0) goto L_0x0081
            r6.close()     // Catch:{ Exception -> 0x007f }
            goto L_0x0081
        L_0x007f:
            r6 = move-exception
            goto L_0x0087
        L_0x0081:
            if (r2 == 0) goto L_0x0099
            r2.close()     // Catch:{ Exception -> 0x007f }
            goto L_0x0099
        L_0x0087:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            r2.append(r6)
            java.lang.String r6 = r2.toString()
            android.util.Log.w(r1, r6)
        L_0x0099:
            throw r7
        L_0x009a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.messagetemplates.HTMLOptions.readFileAsString(com.leanplum.ActionContext, java.lang.String):java.lang.String");
    }

    private static Map<String, Object> replaceFileToLocalPath(Map<String, Object> map, String str) {
        String[] strArr;
        if (map == null) {
            return null;
        }
        for (String str2 : (String[]) map.keySet().toArray(new String[map.keySet().size()])) {
            if (map.get(str2) instanceof Map) {
                replaceFileToLocalPath((Map) map.get(str2), str);
            } else {
                String str3 = "__file__";
                if (str2.contains(str3) && !str2.equals(str)) {
                    String filePath = ActionContext.filePath((String) map.get(str2));
                    if (filePath != null) {
                        File file = new File(filePath);
                        StringBuilder sb = new StringBuilder();
                        sb.append("file://");
                        sb.append(file.getAbsolutePath());
                        String sb2 = sb.toString();
                        if (sb2.contains(Leanplum.getContext().getPackageName())) {
                            map.put(str2.replace(str3, ""), sb2.replace(" ", "%20"));
                        }
                        map.remove(str2);
                    }
                }
            }
        }
        return map;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0055, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0056, code lost:
        android.util.Log.e(r0, "Cannot get html template.", r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005c, code lost:
        android.util.Log.e(r0, "Cannot convert map of arguments to JSON object.");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[ExcHandler: JSONException (unused org.json.JSONException), PHI: r1 
      PHI: (r1v5 java.lang.String) = (r1v3 java.lang.String), (r1v6 java.lang.String) binds: [B:13:0x0043, B:15:0x004f] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC, Splitter:B:13:0x0043] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getTemplate(com.leanplum.ActionContext r5) {
        /*
            java.lang.String r0 = "Leanplum"
            r1 = 0
            if (r5 != 0) goto L_0x0006
            return r1
        L_0x0006:
            java.lang.String r2 = "__file__Template"
            java.lang.String r3 = readFileAsString(r5, r2)
            java.util.Map r4 = r5.getArgs()
            java.util.Map r2 = replaceFileToLocalPath(r4, r2)
            if (r2 == 0) goto L_0x006a
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 == 0) goto L_0x001d
            goto L_0x006a
        L_0x001d:
            java.lang.String r1 = r5.getMessageId()
            java.lang.String r4 = "messageId"
            r2.put(r4, r1)
            com.leanplum.ActionContext$ContextualValues r1 = r5.getContextualValues()
            if (r1 == 0) goto L_0x003f
            com.leanplum.ActionContext$ContextualValues r1 = r5.getContextualValues()
            java.util.Map<java.lang.String, java.lang.Object> r1 = r1.arguments
            if (r1 == 0) goto L_0x003f
            com.leanplum.ActionContext$ContextualValues r1 = r5.getContextualValues()
            java.util.Map<java.lang.String, java.lang.Object> r1 = r1.arguments
            java.lang.String r4 = "displayEvent"
            r2.put(r4, r1)
        L_0x003f:
            java.lang.String r1 = ""
            java.lang.String r4 = "##Vars##"
            org.json.JSONObject r2 = com.leanplum.ActionContext.mapToJsonObject(r2)     // Catch:{ JSONException -> 0x005c, Throwable -> 0x0055 }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x005c, Throwable -> 0x0055 }
            java.lang.String r1 = r3.replace(r4, r2)     // Catch:{ JSONException -> 0x005c, Throwable -> 0x0055 }
            java.lang.String r5 = r5.fillTemplate(r1)     // Catch:{ Throwable -> 0x0061, JSONException -> 0x005c }
            r1 = r5
            goto L_0x0061
        L_0x0055:
            r5 = move-exception
            java.lang.String r2 = "Cannot get html template."
            android.util.Log.e(r0, r2, r5)
            goto L_0x0061
        L_0x005c:
            java.lang.String r5 = "Cannot convert map of arguments to JSON object."
            android.util.Log.e(r0, r5)
        L_0x0061:
            java.lang.String r5 = "\\/"
            java.lang.String r0 = "/"
            java.lang.String r5 = r1.replace(r5, r0)
            return r5
        L_0x006a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.messagetemplates.HTMLOptions.getTemplate(com.leanplum.ActionContext):java.lang.String");
    }

    /* access modifiers changed from: 0000 */
    public boolean isFullScreen() {
        return this.htmlHeight == 0;
    }

    /* access modifiers changed from: 0000 */
    public int getHtmlHeight() {
        return this.htmlHeight;
    }

    /* access modifiers changed from: 0000 */
    public Size getHtmlWidth() {
        return this.htmlWidth;
    }

    private void setHtmlWidth(String str) {
        this.htmlWidth = getSizeValueAndType(str);
    }

    /* access modifiers changed from: 0000 */
    public int getHtmlYOffset(Activity activity) {
        int i = 0;
        if (activity == null) {
            return 0;
        }
        Size size = this.htmlYOffset;
        if (size != null && !TextUtils.isEmpty(size.type)) {
            int i2 = this.htmlYOffset.value;
            i = "%".equals(this.htmlYOffset.type) ? ((SizeUtil.getDisplaySize(activity).y - SizeUtil.getStatusBarHeight(activity)) * i2) / 100 : SizeUtil.dpToPx(activity, i2);
        }
        return i;
    }

    private void setHtmlYOffset(String str) {
        this.htmlYOffset = getSizeValueAndType(str);
    }

    private Size getSizeValueAndType(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Size size = new Size();
        String str2 = "px";
        if (str.contains(str2)) {
            String[] split = str.split(str2);
            if (split.length != 0) {
                size.value = Integer.parseInt(split[0]);
            }
            size.type = str2;
        } else {
            String str3 = "%";
            if (str.contains(str3)) {
                String[] split2 = str.split(str3);
                if (split2.length != 0) {
                    size.value = Integer.parseInt(split2[0]);
                }
                size.type = str3;
            }
        }
        return size;
    }

    /* access modifiers changed from: 0000 */
    public boolean isHtmlTabOutsideToClose() {
        return this.htmlTabOutsideToClose;
    }

    private void setHtmlTabOutsideToClose(boolean z) {
        this.htmlTabOutsideToClose = z;
    }

    private void setHtmlHeight(int i) {
        this.htmlHeight = i;
    }

    /* access modifiers changed from: 0000 */
    public String getHtmlAlign() {
        return this.htmlAlign;
    }

    private void setHtmlAlign(String str) {
        this.htmlAlign = str;
    }

    /* access modifiers changed from: 0000 */
    public ActionContext getActionContext() {
        return this.actionContext;
    }

    private void setActionContext(ActionContext actionContext2) {
        this.actionContext = actionContext2;
    }

    /* access modifiers changed from: 0000 */
    public String getHtmlTemplate() {
        return this.htmlTemplate;
    }

    private void setHtmlTemplate(String str) {
        this.htmlTemplate = str;
    }

    /* access modifiers changed from: 0000 */
    public String getTrackActionUrl() {
        return this.trackActionUrl;
    }

    private void setTrackActionUrl(String str) {
        this.trackActionUrl = str;
    }

    /* access modifiers changed from: 0000 */
    public String getTrackUrl() {
        return this.trackUrl;
    }

    private void setTrackUrl(String str) {
        this.trackUrl = str;
    }

    /* access modifiers changed from: 0000 */
    public String getOpenUrl() {
        return this.openUrl;
    }

    private void setOpenUrl(String str) {
        this.openUrl = str;
    }

    /* access modifiers changed from: 0000 */
    public String getActionUrl() {
        return this.actionUrl;
    }

    private void setActionUrl(String str) {
        this.actionUrl = str;
    }

    /* access modifiers changed from: 0000 */
    public String getCloseUrl() {
        return this.closeUrl;
    }

    private void setCloseUrl(String str) {
        this.closeUrl = str;
    }

    public static ActionArgs toArgs() {
        return new ActionArgs().with("Close URL", "http://leanplum/close").with("Open URL", "http://leanplum/loadFinished").with("Action URL", "http://leanplum/runAction").with("Track Action URL", "http://leanplum/runTrackedAction").with("Track URL", "http://leanplum/track").with("HTML Align", "Top").with("HTML Height", Integer.valueOf(0));
    }
}
