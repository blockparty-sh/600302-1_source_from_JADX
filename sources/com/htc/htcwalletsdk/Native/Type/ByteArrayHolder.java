package com.htc.htcwalletsdk.Native.Type;

import com.htc.htcwalletsdk.Utils.ZKMALog;

public class ByteArrayHolder {
    private static final int DEFAULT_ARRAY_SIZE = 2048;
    private static final int MAX_ARRAY_SIZE = 16384;
    public byte[] byteArray = new byte[2048];
    public int receivedLength = 0;

    public void extendSize(int i) {
        String str = "newSize=";
        String str2 = "ByteArrayHolder";
        if (i > 0) {
            if (i > 16384) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(i);
                sb.append(" > ");
                sb.append(16384);
                sb.append(" , SDK can only support max 16KB now.");
                ZKMALog.m277w(str2, sb.toString());
                i = 16384;
            }
            this.byteArray = new byte[i];
            this.receivedLength = i;
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(i);
        sb2.append(" less than 0, not support!");
        ZKMALog.m273e(str2, sb2.toString());
        throw new IllegalArgumentException();
    }
}
