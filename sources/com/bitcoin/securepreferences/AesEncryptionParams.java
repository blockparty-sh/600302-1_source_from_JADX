package com.bitcoin.securepreferences;

import android.os.Build.VERSION;
import com.yakivmospan.scytale.Options;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0005\b\b\u0018\u0000 $2\u00020\u0001:\u0001$B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\b¢\u0006\u0002\u0010\u000bJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\bHÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003JO\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\bHÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\u0014\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\rR\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011¨\u0006%"}, mo37405d2 = {"Lcom/bitcoin/securepreferences/AesEncryptionParams;", "", "cipher", "", "cipherMode", "padding", "keyGeneratorAlgorithm", "keySize", "", "keySpecAlgorithm", "version", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;I)V", "getCipher", "()Ljava/lang/String;", "getCipherMode", "getKeyGeneratorAlgorithm", "getKeySize", "()I", "getKeySpecAlgorithm", "getPadding", "transformation", "getTransformation", "getVersion", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "securepreferences_release"}, mo37406k = 1, mo37407mv = {1, 1, 13})
/* compiled from: aes.kt */
final class AesEncryptionParams {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String cipher;
    @NotNull
    private final String cipherMode;
    @NotNull
    private final String keyGeneratorAlgorithm;
    private final int keySize;
    @NotNull
    private final String keySpecAlgorithm;
    @NotNull
    private final String padding;
    @NotNull
    private final String transformation;
    private final int version;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\b\u0010\n\u001a\u00020\u0004H\u0002J\b\u0010\u000b\u001a\u00020\u0004H\u0002J\u0006\u0010\f\u001a\u00020\u0004R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/securepreferences/AesEncryptionParams$Companion;", "", "()V", "currentVersion", "Lcom/bitcoin/securepreferences/AesEncryptionParams;", "getCurrentVersion", "()Lcom/bitcoin/securepreferences/AesEncryptionParams;", "forVersion", "version", "", "forVersionKeyStore128", "forVersionKeyStore256", "forVersionWithoutKeyStore", "securepreferences_release"}, mo37406k = 1, mo37407mv = {1, 1, 13})
    /* compiled from: aes.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final AesEncryptionParams getCurrentVersion() {
            if (VERSION.SDK_INT >= 26) {
                return AesEncryptionParams.Companion.forVersion(3);
            }
            if (VERSION.SDK_INT >= 23) {
                return AesEncryptionParams.Companion.forVersion(2);
            }
            return AesEncryptionParams.Companion.forVersion(1);
        }

        @NotNull
        public final AesEncryptionParams forVersion(int i) {
            if (i == 1) {
                return forVersionWithoutKeyStore();
            }
            if (i == 2) {
                return forVersionKeyStore128();
            }
            if (i == 3) {
                return forVersionKeyStore256();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("AES encryption version ");
            sb.append(i);
            sb.append(" not recognised.");
            throw new Exception(sb.toString());
        }

        private final AesEncryptionParams forVersionKeyStore128() {
            if (VERSION.SDK_INT >= 23) {
                AesEncryptionParams aesEncryptionParams = new AesEncryptionParams(Options.ALGORITHM_AES, Options.BLOCK_MODE_CBC, Options.PADDING_PKCS_7, Options.ALGORITHM_AES, 128, Options.ALGORITHM_AES, 2);
                return aesEncryptionParams;
            }
            throw new Exception("Encryption version 2 unsupported on this device.");
        }

        private final AesEncryptionParams forVersionKeyStore256() {
            if (VERSION.SDK_INT >= 26) {
                AesEncryptionParams aesEncryptionParams = new AesEncryptionParams(Options.ALGORITHM_AES, Options.BLOCK_MODE_CBC, Options.PADDING_PKCS_7, Options.ALGORITHM_AES, 256, Options.ALGORITHM_AES, 3);
                return aesEncryptionParams;
            }
            throw new Exception("Encryption version 3 unsupported on this device.");
        }

        @NotNull
        public final AesEncryptionParams forVersionWithoutKeyStore() {
            AesEncryptionParams aesEncryptionParams = new AesEncryptionParams(Options.ALGORITHM_AES, Options.BLOCK_MODE_CBC, "PKCS5Padding", Options.ALGORITHM_AES, 128, Options.ALGORITHM_AES, 1);
            return aesEncryptionParams;
        }
    }

    @NotNull
    public static /* synthetic */ AesEncryptionParams copy$default(AesEncryptionParams aesEncryptionParams, String str, String str2, String str3, String str4, int i, String str5, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = aesEncryptionParams.cipher;
        }
        if ((i3 & 2) != 0) {
            str2 = aesEncryptionParams.cipherMode;
        }
        String str6 = str2;
        if ((i3 & 4) != 0) {
            str3 = aesEncryptionParams.padding;
        }
        String str7 = str3;
        if ((i3 & 8) != 0) {
            str4 = aesEncryptionParams.keyGeneratorAlgorithm;
        }
        String str8 = str4;
        if ((i3 & 16) != 0) {
            i = aesEncryptionParams.keySize;
        }
        int i4 = i;
        if ((i3 & 32) != 0) {
            str5 = aesEncryptionParams.keySpecAlgorithm;
        }
        String str9 = str5;
        if ((i3 & 64) != 0) {
            i2 = aesEncryptionParams.version;
        }
        return aesEncryptionParams.copy(str, str6, str7, str8, i4, str9, i2);
    }

    @NotNull
    public final String component1() {
        return this.cipher;
    }

    @NotNull
    public final String component2() {
        return this.cipherMode;
    }

    @NotNull
    public final String component3() {
        return this.padding;
    }

    @NotNull
    public final String component4() {
        return this.keyGeneratorAlgorithm;
    }

    public final int component5() {
        return this.keySize;
    }

    @NotNull
    public final String component6() {
        return this.keySpecAlgorithm;
    }

    public final int component7() {
        return this.version;
    }

    @NotNull
    public final AesEncryptionParams copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, int i, @NotNull String str5, int i2) {
        String str6 = str;
        Intrinsics.checkParameterIsNotNull(str, "cipher");
        String str7 = str2;
        Intrinsics.checkParameterIsNotNull(str2, "cipherMode");
        String str8 = str3;
        Intrinsics.checkParameterIsNotNull(str3, "padding");
        String str9 = str4;
        Intrinsics.checkParameterIsNotNull(str4, "keyGeneratorAlgorithm");
        String str10 = str5;
        Intrinsics.checkParameterIsNotNull(str5, "keySpecAlgorithm");
        AesEncryptionParams aesEncryptionParams = new AesEncryptionParams(str6, str7, str8, str9, i, str10, i2);
        return aesEncryptionParams;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof AesEncryptionParams) {
                AesEncryptionParams aesEncryptionParams = (AesEncryptionParams) obj;
                if (Intrinsics.areEqual((Object) this.cipher, (Object) aesEncryptionParams.cipher) && Intrinsics.areEqual((Object) this.cipherMode, (Object) aesEncryptionParams.cipherMode) && Intrinsics.areEqual((Object) this.padding, (Object) aesEncryptionParams.padding) && Intrinsics.areEqual((Object) this.keyGeneratorAlgorithm, (Object) aesEncryptionParams.keyGeneratorAlgorithm)) {
                    if ((this.keySize == aesEncryptionParams.keySize) && Intrinsics.areEqual((Object) this.keySpecAlgorithm, (Object) aesEncryptionParams.keySpecAlgorithm)) {
                        if (this.version == aesEncryptionParams.version) {
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
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.padding;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.keyGeneratorAlgorithm;
        int hashCode4 = (((hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31) + this.keySize) * 31;
        String str5 = this.keySpecAlgorithm;
        if (str5 != null) {
            i = str5.hashCode();
        }
        return ((hashCode4 + i) * 31) + this.version;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AesEncryptionParams(cipher=");
        sb.append(this.cipher);
        sb.append(", cipherMode=");
        sb.append(this.cipherMode);
        sb.append(", padding=");
        sb.append(this.padding);
        sb.append(", keyGeneratorAlgorithm=");
        sb.append(this.keyGeneratorAlgorithm);
        sb.append(", keySize=");
        sb.append(this.keySize);
        sb.append(", keySpecAlgorithm=");
        sb.append(this.keySpecAlgorithm);
        sb.append(", version=");
        sb.append(this.version);
        sb.append(")");
        return sb.toString();
    }

    public AesEncryptionParams(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, int i, @NotNull String str5, int i2) {
        Intrinsics.checkParameterIsNotNull(str, "cipher");
        Intrinsics.checkParameterIsNotNull(str2, "cipherMode");
        Intrinsics.checkParameterIsNotNull(str3, "padding");
        Intrinsics.checkParameterIsNotNull(str4, "keyGeneratorAlgorithm");
        Intrinsics.checkParameterIsNotNull(str5, "keySpecAlgorithm");
        this.cipher = str;
        this.cipherMode = str2;
        this.padding = str3;
        this.keyGeneratorAlgorithm = str4;
        this.keySize = i;
        this.keySpecAlgorithm = str5;
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

    @NotNull
    public final String getPadding() {
        return this.padding;
    }

    @NotNull
    public final String getKeyGeneratorAlgorithm() {
        return this.keyGeneratorAlgorithm;
    }

    public final int getKeySize() {
        return this.keySize;
    }

    @NotNull
    public final String getKeySpecAlgorithm() {
        return this.keySpecAlgorithm;
    }

    public final int getVersion() {
        return this.version;
    }

    @NotNull
    public final String getTransformation() {
        return this.transformation;
    }
}
