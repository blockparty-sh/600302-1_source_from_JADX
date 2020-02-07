package com.htc.htcwalletsdk.Security.Trusted;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.text.TextUtils;
import com.google.common.base.Ascii;
import com.htc.htcwalletsdk.Utils.ZKMALog;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

public final class TrustedPartners {
    private static final String HASH_ALGORITHM = "SHA256";
    private static final String TAG = "TrustedPartners";
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();
    private final PackageManager packageManager;
    private final Set<String> trustedPartnerCertificateHashes;

    public TrustedPartners(Context context, Set<String> set) {
        this.packageManager = context.getPackageManager();
        this.trustedPartnerCertificateHashes = set;
    }

    public boolean isTrustedApplication(String str) {
        String str2 = "); do not trust";
        boolean isEmpty = TextUtils.isEmpty(str);
        String str3 = TAG;
        if (isEmpty) {
            if (ZKMALog.isLoggable(str3, 5)) {
                ZKMALog.m277w(str3, "null or empty package name; do not trust");
            }
            return false;
        }
        try {
            PackageInfo packageInfo = this.packageManager.getPackageInfo(str, 64);
            if (packageInfo.signatures == null || packageInfo.signatures.length != 1) {
                if (ZKMALog.isLoggable(str3, 5)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(packageInfo.signatures.length);
                    sb.append(" signatures found for package (");
                    sb.append(str);
                    sb.append(str2);
                    ZKMALog.m277w(str3, sb.toString());
                }
                return false;
            }
            try {
                MessageDigest instance = MessageDigest.getInstance(HASH_ALGORITHM);
                instance.update(packageInfo.signatures[0].toByteArray());
                return this.trustedPartnerCertificateHashes.contains(bytesToHex(instance.digest()));
            } catch (NoSuchAlgorithmException unused) {
                if (ZKMALog.isLoggable(str3, 6)) {
                    ZKMALog.m273e(str3, "unable to compute hash using SHA256; do not trust");
                }
                return false;
            }
        } catch (NameNotFoundException unused2) {
            if (ZKMALog.isLoggable(str3, 5)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("package not found (");
                sb2.append(str);
                sb2.append(str2);
                ZKMALog.m277w(str3, sb2.toString());
            }
            return false;
        }
    }

    public boolean isTrustedAuthority(String str) {
        ProviderInfo resolveContentProvider = this.packageManager.resolveContentProvider(str, 0);
        if (resolveContentProvider != null) {
            return isTrustedApplication(resolveContentProvider.packageName);
        }
        String str2 = TAG;
        if (ZKMALog.isLoggable(str2, 5)) {
            StringBuilder sb = new StringBuilder();
            sb.append("no provider found for ");
            sb.append(str);
            sb.append("; do not trust");
            ZKMALog.m277w(str2, sb.toString());
        }
        return false;
    }

    public static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i] & 255;
            int i2 = i * 2;
            char[] cArr2 = hexArray;
            cArr[i2] = cArr2[b >>> 4];
            cArr[i2 + 1] = cArr2[b & Ascii.f528SI];
        }
        return new String(cArr);
    }
}
