package com.bitcoin.mwallet.core.models.verifiedtoken;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001c"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/verifiedtoken/VerifiedToken;", "", "id", "", "name", "symbol", "decimals", "", "iconImageUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getDecimals", "()I", "getIconImageUrl", "()Ljava/lang/String;", "getId", "getName", "getSymbol", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@Entity(primaryKeys = {"id"}, tableName = "verified_token")
/* compiled from: VerifiedToken.kt */
public final class VerifiedToken {
    @ColumnInfo(name = "decimals")
    private final int decimals;
    @ColumnInfo(name = "iconImageUrl")
    @NotNull
    private final String iconImageUrl;
    @ColumnInfo(index = true, name = "id")
    @NotNull

    /* renamed from: id */
    private final String f375id;
    @ColumnInfo(index = true, name = "name")
    @NotNull
    private final String name;
    @ColumnInfo(index = true, name = "symbol")
    @NotNull
    private final String symbol;

    @NotNull
    public static /* synthetic */ VerifiedToken copy$default(VerifiedToken verifiedToken, String str, String str2, String str3, int i, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = verifiedToken.f375id;
        }
        if ((i2 & 2) != 0) {
            str2 = verifiedToken.name;
        }
        String str5 = str2;
        if ((i2 & 4) != 0) {
            str3 = verifiedToken.symbol;
        }
        String str6 = str3;
        if ((i2 & 8) != 0) {
            i = verifiedToken.decimals;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            str4 = verifiedToken.iconImageUrl;
        }
        return verifiedToken.copy(str, str5, str6, i3, str4);
    }

    @NotNull
    public final String component1() {
        return this.f375id;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    @NotNull
    public final String component3() {
        return this.symbol;
    }

    public final int component4() {
        return this.decimals;
    }

    @NotNull
    public final String component5() {
        return this.iconImageUrl;
    }

    @NotNull
    public final VerifiedToken copy(@NotNull String str, @NotNull String str2, @NotNull String str3, int i, @NotNull String str4) {
        Intrinsics.checkParameterIsNotNull(str, CommonProperties.f657ID);
        Intrinsics.checkParameterIsNotNull(str2, "name");
        Intrinsics.checkParameterIsNotNull(str3, "symbol");
        Intrinsics.checkParameterIsNotNull(str4, "iconImageUrl");
        VerifiedToken verifiedToken = new VerifiedToken(str, str2, str3, i, str4);
        return verifiedToken;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof VerifiedToken) {
                VerifiedToken verifiedToken = (VerifiedToken) obj;
                if (Intrinsics.areEqual((Object) this.f375id, (Object) verifiedToken.f375id) && Intrinsics.areEqual((Object) this.name, (Object) verifiedToken.name) && Intrinsics.areEqual((Object) this.symbol, (Object) verifiedToken.symbol)) {
                    if (!(this.decimals == verifiedToken.decimals) || !Intrinsics.areEqual((Object) this.iconImageUrl, (Object) verifiedToken.iconImageUrl)) {
                        return false;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.f375id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.name;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.symbol;
        int hashCode3 = (((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.decimals) * 31;
        String str4 = this.iconImageUrl;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("VerifiedToken(id=");
        sb.append(this.f375id);
        sb.append(", name=");
        sb.append(this.name);
        sb.append(", symbol=");
        sb.append(this.symbol);
        sb.append(", decimals=");
        sb.append(this.decimals);
        sb.append(", iconImageUrl=");
        sb.append(this.iconImageUrl);
        sb.append(")");
        return sb.toString();
    }

    public VerifiedToken(@NotNull String str, @NotNull String str2, @NotNull String str3, int i, @NotNull String str4) {
        Intrinsics.checkParameterIsNotNull(str, CommonProperties.f657ID);
        Intrinsics.checkParameterIsNotNull(str2, "name");
        Intrinsics.checkParameterIsNotNull(str3, "symbol");
        Intrinsics.checkParameterIsNotNull(str4, "iconImageUrl");
        this.f375id = str;
        this.name = str2;
        this.symbol = str3;
        this.decimals = i;
        this.iconImageUrl = str4;
    }

    @NotNull
    public final String getId() {
        return this.f375id;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getSymbol() {
        return this.symbol;
    }

    public final int getDecimals() {
        return this.decimals;
    }

    @NotNull
    public final String getIconImageUrl() {
        return this.iconImageUrl;
    }
}
