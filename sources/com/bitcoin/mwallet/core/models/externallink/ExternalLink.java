package com.bitcoin.mwallet.core.models.externallink;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003JO\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\bHÖ\u0001J\t\u0010#\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u000b\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0016\u0010\n\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0016\u0010\t\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010¨\u0006$"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/externallink/ExternalLink;", "", "id", "Lcom/bitcoin/mwallet/core/models/externallink/ExternalLinkId;", "name", "", "linkDescription", "backgroundImageId", "", "website", "titleColor", "descriptionColor", "(Lcom/bitcoin/mwallet/core/models/externallink/ExternalLinkId;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBackgroundImageId", "()I", "getDescriptionColor", "()Ljava/lang/String;", "getId", "()Lcom/bitcoin/mwallet/core/models/externallink/ExternalLinkId;", "getLinkDescription", "getName", "getTitleColor", "getWebsite", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@Entity(tableName = "external_links")
/* compiled from: ExternalLink.kt */
public final class ExternalLink {
    @ColumnInfo(name = "background_image_id")
    private final int backgroundImageId;
    @ColumnInfo(name = "description_color")
    @NotNull
    private final String descriptionColor;
    @ColumnInfo(name = "id")
    @NotNull
    @PrimaryKey

    /* renamed from: id */
    private final ExternalLinkId f370id;
    @ColumnInfo(name = "description")
    @NotNull
    private final String linkDescription;
    @ColumnInfo(name = "name")
    @NotNull
    private final String name;
    @ColumnInfo(name = "title_color")
    @NotNull
    private final String titleColor;
    @ColumnInfo(name = "website")
    @NotNull
    private final String website;

    @NotNull
    public static /* synthetic */ ExternalLink copy$default(ExternalLink externalLink, ExternalLinkId externalLinkId, String str, String str2, int i, String str3, String str4, String str5, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            externalLinkId = externalLink.f370id;
        }
        if ((i2 & 2) != 0) {
            str = externalLink.name;
        }
        String str6 = str;
        if ((i2 & 4) != 0) {
            str2 = externalLink.linkDescription;
        }
        String str7 = str2;
        if ((i2 & 8) != 0) {
            i = externalLink.backgroundImageId;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            str3 = externalLink.website;
        }
        String str8 = str3;
        if ((i2 & 32) != 0) {
            str4 = externalLink.titleColor;
        }
        String str9 = str4;
        if ((i2 & 64) != 0) {
            str5 = externalLink.descriptionColor;
        }
        return externalLink.copy(externalLinkId, str6, str7, i3, str8, str9, str5);
    }

    @NotNull
    public final ExternalLinkId component1() {
        return this.f370id;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    @NotNull
    public final String component3() {
        return this.linkDescription;
    }

    public final int component4() {
        return this.backgroundImageId;
    }

    @NotNull
    public final String component5() {
        return this.website;
    }

    @NotNull
    public final String component6() {
        return this.titleColor;
    }

    @NotNull
    public final String component7() {
        return this.descriptionColor;
    }

    @NotNull
    public final ExternalLink copy(@NotNull ExternalLinkId externalLinkId, @NotNull String str, @NotNull String str2, int i, @NotNull String str3, @NotNull String str4, @NotNull String str5) {
        ExternalLinkId externalLinkId2 = externalLinkId;
        Intrinsics.checkParameterIsNotNull(externalLinkId, CommonProperties.f657ID);
        String str6 = str;
        Intrinsics.checkParameterIsNotNull(str, "name");
        String str7 = str2;
        Intrinsics.checkParameterIsNotNull(str2, "linkDescription");
        String str8 = str3;
        Intrinsics.checkParameterIsNotNull(str3, "website");
        String str9 = str4;
        Intrinsics.checkParameterIsNotNull(str4, "titleColor");
        String str10 = str5;
        Intrinsics.checkParameterIsNotNull(str10, "descriptionColor");
        ExternalLink externalLink = new ExternalLink(externalLinkId2, str6, str7, i, str8, str9, str10);
        return externalLink;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof ExternalLink) {
                ExternalLink externalLink = (ExternalLink) obj;
                if (Intrinsics.areEqual((Object) this.f370id, (Object) externalLink.f370id) && Intrinsics.areEqual((Object) this.name, (Object) externalLink.name) && Intrinsics.areEqual((Object) this.linkDescription, (Object) externalLink.linkDescription)) {
                    if (!(this.backgroundImageId == externalLink.backgroundImageId) || !Intrinsics.areEqual((Object) this.website, (Object) externalLink.website) || !Intrinsics.areEqual((Object) this.titleColor, (Object) externalLink.titleColor) || !Intrinsics.areEqual((Object) this.descriptionColor, (Object) externalLink.descriptionColor)) {
                        return false;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        ExternalLinkId externalLinkId = this.f370id;
        int i = 0;
        int hashCode = (externalLinkId != null ? externalLinkId.hashCode() : 0) * 31;
        String str = this.name;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.linkDescription;
        int hashCode3 = (((hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.backgroundImageId) * 31;
        String str3 = this.website;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.titleColor;
        int hashCode5 = (hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.descriptionColor;
        if (str5 != null) {
            i = str5.hashCode();
        }
        return hashCode5 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ExternalLink(id=");
        sb.append(this.f370id);
        sb.append(", name=");
        sb.append(this.name);
        sb.append(", linkDescription=");
        sb.append(this.linkDescription);
        sb.append(", backgroundImageId=");
        sb.append(this.backgroundImageId);
        sb.append(", website=");
        sb.append(this.website);
        sb.append(", titleColor=");
        sb.append(this.titleColor);
        sb.append(", descriptionColor=");
        sb.append(this.descriptionColor);
        sb.append(")");
        return sb.toString();
    }

    public ExternalLink(@NotNull ExternalLinkId externalLinkId, @NotNull String str, @NotNull String str2, int i, @NotNull String str3, @NotNull String str4, @NotNull String str5) {
        Intrinsics.checkParameterIsNotNull(externalLinkId, CommonProperties.f657ID);
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(str2, "linkDescription");
        Intrinsics.checkParameterIsNotNull(str3, "website");
        Intrinsics.checkParameterIsNotNull(str4, "titleColor");
        Intrinsics.checkParameterIsNotNull(str5, "descriptionColor");
        this.f370id = externalLinkId;
        this.name = str;
        this.linkDescription = str2;
        this.backgroundImageId = i;
        this.website = str3;
        this.titleColor = str4;
        this.descriptionColor = str5;
    }

    @NotNull
    public final ExternalLinkId getId() {
        return this.f370id;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getLinkDescription() {
        return this.linkDescription;
    }

    public final int getBackgroundImageId() {
        return this.backgroundImageId;
    }

    @NotNull
    public final String getWebsite() {
        return this.website;
    }

    @NotNull
    public final String getTitleColor() {
        return this.titleColor;
    }

    @NotNull
    public final String getDescriptionColor() {
        return this.descriptionColor;
    }
}
