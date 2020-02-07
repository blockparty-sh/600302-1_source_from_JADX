package com.bitcoin.mwallet.core.models.discover;

import com.google.android.gms.common.internal.ImagesContract;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongycastle.i18n.MessageBundle;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006\u001f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/discover/Link;", "", "index", "", "id", "", "title", "short_description", "url", "logo_image_url", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getIndex", "()I", "getLogo_image_url", "getShort_description", "getTitle", "getUrl", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Link.kt */
public final class Link {
    @NotNull

    /* renamed from: id */
    private final String f369id;
    private final int index;
    @NotNull
    private final String logo_image_url;
    @NotNull
    private final String short_description;
    @NotNull
    private final String title;
    @NotNull
    private final String url;

    @NotNull
    public static /* synthetic */ Link copy$default(Link link, int i, String str, String str2, String str3, String str4, String str5, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = link.index;
        }
        if ((i2 & 2) != 0) {
            str = link.f369id;
        }
        String str6 = str;
        if ((i2 & 4) != 0) {
            str2 = link.title;
        }
        String str7 = str2;
        if ((i2 & 8) != 0) {
            str3 = link.short_description;
        }
        String str8 = str3;
        if ((i2 & 16) != 0) {
            str4 = link.url;
        }
        String str9 = str4;
        if ((i2 & 32) != 0) {
            str5 = link.logo_image_url;
        }
        return link.copy(i, str6, str7, str8, str9, str5);
    }

    public final int component1() {
        return this.index;
    }

    @NotNull
    public final String component2() {
        return this.f369id;
    }

    @NotNull
    public final String component3() {
        return this.title;
    }

    @NotNull
    public final String component4() {
        return this.short_description;
    }

    @NotNull
    public final String component5() {
        return this.url;
    }

    @NotNull
    public final String component6() {
        return this.logo_image_url;
    }

    @NotNull
    public final Link copy(int i, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5) {
        Intrinsics.checkParameterIsNotNull(str, CommonProperties.f657ID);
        Intrinsics.checkParameterIsNotNull(str2, MessageBundle.TITLE_ENTRY);
        Intrinsics.checkParameterIsNotNull(str3, "short_description");
        Intrinsics.checkParameterIsNotNull(str4, ImagesContract.URL);
        Intrinsics.checkParameterIsNotNull(str5, "logo_image_url");
        Link link = new Link(i, str, str2, str3, str4, str5);
        return link;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof Link) {
                Link link = (Link) obj;
                if (!(this.index == link.index) || !Intrinsics.areEqual((Object) this.f369id, (Object) link.f369id) || !Intrinsics.areEqual((Object) this.title, (Object) link.title) || !Intrinsics.areEqual((Object) this.short_description, (Object) link.short_description) || !Intrinsics.areEqual((Object) this.url, (Object) link.url) || !Intrinsics.areEqual((Object) this.logo_image_url, (Object) link.logo_image_url)) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.index * 31;
        String str = this.f369id;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.title;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.short_description;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.url;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.logo_image_url;
        if (str5 != null) {
            i2 = str5.hashCode();
        }
        return hashCode4 + i2;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Link(index=");
        sb.append(this.index);
        sb.append(", id=");
        sb.append(this.f369id);
        sb.append(", title=");
        sb.append(this.title);
        sb.append(", short_description=");
        sb.append(this.short_description);
        sb.append(", url=");
        sb.append(this.url);
        sb.append(", logo_image_url=");
        sb.append(this.logo_image_url);
        sb.append(")");
        return sb.toString();
    }

    public Link(int i, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5) {
        Intrinsics.checkParameterIsNotNull(str, CommonProperties.f657ID);
        Intrinsics.checkParameterIsNotNull(str2, MessageBundle.TITLE_ENTRY);
        Intrinsics.checkParameterIsNotNull(str3, "short_description");
        Intrinsics.checkParameterIsNotNull(str4, ImagesContract.URL);
        Intrinsics.checkParameterIsNotNull(str5, "logo_image_url");
        this.index = i;
        this.f369id = str;
        this.title = str2;
        this.short_description = str3;
        this.url = str4;
        this.logo_image_url = str5;
    }

    public final int getIndex() {
        return this.index;
    }

    @NotNull
    public final String getId() {
        return this.f369id;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    @NotNull
    public final String getShort_description() {
        return this.short_description;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }

    @NotNull
    public final String getLogo_image_url() {
        return this.logo_image_url;
    }
}
