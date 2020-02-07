package com.google.android.gms.internal.vision;

import com.facebook.stetho.common.Utf8Charset;
import java.nio.charset.Charset;
import org.spongycastle.i18n.LocalizedMessage;

public final class zzjr {
    private static final Charset ISO_8859_1 = Charset.forName(LocalizedMessage.DEFAULT_ENCODING);
    protected static final Charset UTF_8 = Charset.forName(Utf8Charset.NAME);
    public static final Object zzado = new Object();

    public static void zza(zzjn zzjn, zzjn zzjn2) {
        if (zzjn.zzadg != null) {
            zzjn2.zzadg = (zzjp) zzjn.zzadg.clone();
        }
    }
}
