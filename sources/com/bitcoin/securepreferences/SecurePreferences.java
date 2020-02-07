package com.bitcoin.securepreferences;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000f\u001a\u00020\u0010J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\u0005R\u000e\u0010\u0007\u001a\u00020\u0005XD¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/securepreferences/SecurePreferences;", "", "context", "Landroid/content/Context;", "namespace", "", "(Landroid/content/Context;Ljava/lang/String;)V", "TAG", "mApplicationContext", "mDeviceIsSecure", "", "mSharedPreferences", "Landroid/content/SharedPreferences;", "mStringEncrypter", "Lcom/bitcoin/securepreferences/SecureStringEncrypter;", "edit", "Lcom/bitcoin/securepreferences/SecurePreferences$Editor;", "getString", "key", "Editor", "securepreferences_release"}, mo37406k = 1, mo37407mv = {1, 1, 13})
/* compiled from: SecurePreferences.kt */
public final class SecurePreferences {
    private final String TAG = "SecurePreferences";
    private final Context mApplicationContext;
    private final boolean mDeviceIsSecure;
    private final SharedPreferences mSharedPreferences;
    private final SecureStringEncrypter mStringEncrypter;
    private final String namespace;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\tJ\u0016\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007J\u000e\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0007R\u000e\u0010\r\u001a\u00020\u0007XD¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, mo37405d2 = {"Lcom/bitcoin/securepreferences/SecurePreferences$Editor;", "", "editor", "Landroid/content/SharedPreferences$Editor;", "mApplicationContext", "Landroid/content/Context;", "namespace", "", "deviceIsSecure", "", "stringEncrypter", "Lcom/bitcoin/securepreferences/SecureStringEncrypter;", "(Landroid/content/SharedPreferences$Editor;Landroid/content/Context;Ljava/lang/String;ZLcom/bitcoin/securepreferences/SecureStringEncrypter;)V", "TAG", "clear", "", "commit", "putString", "key", "value", "remove", "securepreferences_release"}, mo37406k = 1, mo37407mv = {1, 1, 13})
    /* compiled from: SecurePreferences.kt */
    public static final class Editor {
        private final String TAG = "SecurePreferencesEditor";
        private final boolean deviceIsSecure;
        private final android.content.SharedPreferences.Editor editor;
        private final Context mApplicationContext;
        private final String namespace;
        private final SecureStringEncrypter stringEncrypter;

        public Editor(@NotNull android.content.SharedPreferences.Editor editor2, @NotNull Context context, @NotNull String str, boolean z, @NotNull SecureStringEncrypter secureStringEncrypter) {
            Intrinsics.checkParameterIsNotNull(editor2, "editor");
            Intrinsics.checkParameterIsNotNull(context, "mApplicationContext");
            Intrinsics.checkParameterIsNotNull(str, "namespace");
            Intrinsics.checkParameterIsNotNull(secureStringEncrypter, "stringEncrypter");
            this.editor = editor2;
            this.mApplicationContext = context;
            this.namespace = str;
            this.deviceIsSecure = z;
            this.stringEncrypter = secureStringEncrypter;
        }

        public final void clear() {
            this.editor.clear();
            AesKt.deleteAesEncryptionKeyFromKeyStoreIfExists(this.namespace);
            RsaKt.deleteRsaEncryptionKeyFromKeyStoreIfExists(this.namespace);
        }

        @NotNull
        public final Editor putString(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkParameterIsNotNull(str, "key");
            Intrinsics.checkParameterIsNotNull(str2, "value");
            this.editor.putString(str, this.stringEncrypter.encryptString(str2));
            return this;
        }

        public final void remove(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "key");
            this.editor.remove(str);
        }

        public final boolean commit() {
            return this.editor.commit();
        }
    }

    public SecurePreferences(@NotNull Context context, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(str, "namespace");
        this.namespace = str;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
        this.mApplicationContext = applicationContext;
        boolean z = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.namespace, 0);
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "context.getSharedPrefere…ce, Context.MODE_PRIVATE)");
        this.mSharedPreferences = sharedPreferences;
        this.mStringEncrypter = new SecureStringEncrypter(context, this.namespace);
        Object systemService = context.getSystemService("keyguard");
        if (!(systemService instanceof KeyguardManager)) {
            systemService = null;
        }
        KeyguardManager keyguardManager = (KeyguardManager) systemService;
        if (keyguardManager != null) {
            if (VERSION.SDK_INT >= 23) {
                z = keyguardManager.isDeviceSecure();
            } else {
                z = keyguardManager.isKeyguardSecure();
            }
        }
        this.mDeviceIsSecure = z;
        String str2 = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Device is secure: ");
        sb.append(this.mDeviceIsSecure);
        Log.d(str2, sb.toString());
    }

    @NotNull
    public final Editor edit() {
        android.content.SharedPreferences.Editor edit = this.mSharedPreferences.edit();
        Intrinsics.checkExpressionValueIsNotNull(edit, "mSharedPreferences.edit()");
        Editor editor = new Editor(edit, this.mApplicationContext, this.namespace, this.mDeviceIsSecure, this.mStringEncrypter);
        return editor;
    }

    @Nullable
    public final String getString(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        String string = this.mSharedPreferences.getString(str, null);
        if (string != null) {
            return this.mStringEncrypter.decryptString(string);
        }
        return null;
    }
}
