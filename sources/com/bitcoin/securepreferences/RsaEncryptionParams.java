package com.bitcoin.securepreferences;

import android.os.Build.VERSION;
import androidx.annotation.RequiresApi;
import com.yakivmospan.scytale.Options;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0005\b\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0002\u0010\tJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000e¨\u0006\u001f"}, mo37405d2 = {"Lcom/bitcoin/securepreferences/RsaEncryptionParams;", "", "cipher", "", "cipherMode", "keySize", "", "padding", "version", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;I)V", "getCipher", "()Ljava/lang/String;", "getCipherMode", "getKeySize", "()I", "getPadding", "transformation", "getTransformation", "getVersion", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "securepreferences_release"}, mo37406k = 1, mo37407mv = {1, 1, 13})
/* compiled from: rsa.kt */
final class RsaEncryptionParams {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String cipher;
    @NotNull
    private final String cipherMode;
    private final int keySize;
    @NotNull
    private final String padding;
    @NotNull
    private final String transformation;
    private final int version;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\b\u0010\n\u001a\u00020\u0004H\u0002J\b\u0010\u000b\u001a\u00020\u0004H\u0003R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, mo37405d2 = {"Lcom/bitcoin/securepreferences/RsaEncryptionParams$Companion;", "", "()V", "currentVersion", "Lcom/bitcoin/securepreferences/RsaEncryptionParams;", "getCurrentVersion", "()Lcom/bitcoin/securepreferences/RsaEncryptionParams;", "forVersion", "version", "", "version1Params", "version1ParamsForKeyGenParameterSpec", "securepreferences_release"}, mo37406k = 1, mo37407mv = {1, 1, 13})
    /* compiled from: rsa.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final RsaEncryptionParams getCurrentVersion() {
            return RsaEncryptionParams.Companion.forVersion(1);
        }

        @NotNull
        public final RsaEncryptionParams forVersion(int i) {
            if (i == 1) {
                return version1Params();
            }
            throw new Exception("RSA encryption version not recognised.");
        }

        private final RsaEncryptionParams version1Params() {
            if (VERSION.SDK_INT >= 23) {
                return version1ParamsForKeyGenParameterSpec();
            }
            RsaEncryptionParams rsaEncryptionParams = new RsaEncryptionParams(Options.ALGORITHM_RSA, "NONE", 2048, Options.PADDING_PKCS_1, 1);
            return rsaEncryptionParams;
        }

        @RequiresApi(23)
        private final RsaEncryptionParams version1ParamsForKeyGenParameterSpec() {
            RsaEncryptionParams rsaEncryptionParams = new RsaEncryptionParams(Options.ALGORITHM_RSA, "NONE", 2048, Options.PADDING_PKCS_1, 1);
            return rsaEncryptionParams;
        }
    }

    @NotNull
    public static /* synthetic */ RsaEncryptionParams copy$default(RsaEncryptionParams rsaEncryptionParams, String str, String str2, int i, String str3, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = rsaEncryptionParams.cipher;
        }
        if ((i3 & 2) != 0) {
            str2 = rsaEncryptionParams.cipherMode;
        }
        String str4 = str2;
        if ((i3 & 4) != 0) {
            i = rsaEncryptionParams.keySize;
        }
        int i4 = i;
        if ((i3 & 8) != 0) {
            str3 = rsaEncryptionParams.padding;
        }
        String str5 = str3;
        if ((i3 & 16) != 0) {
            i2 = rsaEncryptionParams.version;
        }
        return rsaEncryptionParams.copy(str, str4, i4, str5, i2);
    }

    @NotNull
    public final String component1() {
        return this.cipher;
    }

    @NotNull
    public final String component2() {
        return this.cipherMode;
    }

    public final int component3() {
        return this.keySize;
    }

    @NotNull
    public final String component4() {
        return this.padding;
    }

    public final int component5() {
        return this.version;
    }

    @NotNull
    public final RsaEncryptionParams copy(@NotNull String str, @NotNull String str2, int i, @NotNull String str3, int i2) {
        Intrinsics.checkParameterIsNotNull(str, "cipher");
        Intrinsics.checkParameterIsNotNull(str2, "cipherMode");
        Intrinsics.checkParameterIsNotNull(str3, "padding");
        RsaEncryptionParams rsaEncryptionParams = new RsaEncryptionParams(str, str2, i, str3, i2);
        return rsaEncryptionParams;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof RsaEncryptionParams) {
                RsaEncryptionParams rsaEncryptionParams = (RsaEncryptionParams) obj;
                if (Intrinsics.areEqual((Object) this.cipher, (Object) rsaEncryptionParams.cipher) && Intrinsics.areEqual((Object) this.cipherMode, (Object) rsaEncryptionParams.cipherMode)) {
                    if ((this.keySize == rsaEncryptionParams.keySize) && Intrinsics.areEqual((Object) this.padding, (Object) rsaEncryptionParams.padding)) {
                        if (this.version == rsaEncryptionParams.version) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.cipher;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.cipherMode;
        int hashCode2 = (((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.keySize) * 31;
        String str3 = this.padding;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return ((hashCode2 + i) * 31) + this.version;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RsaEncryptionParams(cipher=");
        sb.append(this.cipher);
        sb.append(", cipherMode=");
        sb.append(this.cipherMode);
        sb.append(", keySize=");
        sb.append(this.keySize);
        sb.append(", padding=");
        sb.append(this.padding);
        sb.append(", version=");
        sb.append(this.version);
        sb.append(")");
        return sb.toString();
    }

    public RsaEncryptionParams(@NotNull String str, @NotNull String str2, int i, @NotNull String str3, int i2) {
        Intrinsics.checkParameterIsNotNull(str, "cipher");
        Intrinsics.checkParameterIsNotNull(str2, "cipherMode");
        Intrinsics.checkParameterIsNotNull(str3, "padding");
        this.cipher = str;
        this.cipherMode = str2;
        this.keySize = i;
        this.padding = str3;
        this.version = i2;
        StringBuilder sb = new StringBuilder();
        sb.append(this.cipher);
        sb.append('/');
        sb.append(this.cipherMode);
        sb.append('/');
        sb.append(this.padding);
        this.transformation = sb.toString();
    }

    @NotNull
    public final String getCipher() {
        return this.cipher;
    }

    @NotNull
    public final String getCipherMode() {
        return this.cipherMode;
    }

    public final int getKeySize() {
        return this.keySize;
    }

    @NotNull
    public final String getPadding() {
        return this.padding;
    }

    public final int getVersion() {
        return this.version;
    }

    @NotNull
    public final String getTransformation() {
        return this.transformation;
    }
}
