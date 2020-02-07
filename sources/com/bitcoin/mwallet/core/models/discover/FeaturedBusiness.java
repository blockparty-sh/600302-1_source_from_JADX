package com.bitcoin.mwallet.core.models.discover;

import com.google.android.gms.common.internal.ImagesContract;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongycastle.i18n.MessageBundle;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003JO\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0003HÖ\u0001J\t\u0010!\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006\""}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/discover/FeaturedBusiness;", "", "index", "", "id", "", "title", "shortDescription", "url", "imageURL", "logoImageURL", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getImageURL", "getIndex", "()I", "getLogoImageURL", "getShortDescription", "getTitle", "getUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: FeaturedBusiness.kt */
public final class FeaturedBusiness {
    @NotNull

    /* renamed from: id */
    private final String f368id;
    @NotNull
    private final String imageURL;
    private final int index;
    @NotNull
    private final String logoImageURL;
    @NotNull
    private final String shortDescription;
    @NotNull
    private final String title;
    @NotNull
    private final String url;

    @NotNull
    public static /* synthetic */ FeaturedBusiness copy$default(FeaturedBusiness featuredBusiness, int i, String str, String str2, String str3, String str4, String str5, String str6, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = featuredBusiness.index;
        }
        if ((i2 & 2) != 0) {
            str = featuredBusiness.f368id;
        }
        String str7 = str;
        if ((i2 & 4) != 0) {
            str2 = featuredBusiness.title;
        }
        String str8 = str2;
        if ((i2 & 8) != 0) {
            str3 = featuredBusiness.shortDescription;
        }
        String str9 = str3;
        if ((i2 & 16) != 0) {
            str4 = featuredBusiness.url;
        }
        String str10 = str4;
        if ((i2 & 32) != 0) {
            str5 = featuredBusiness.imageURL;
        }
        String str11 = str5;
        if ((i2 & 64) != 0) {
            str6 = featuredBusiness.logoImageURL;
        }
        return featuredBusiness.copy(i, str7, str8, str9, str10, str11, str6);
    }

    public final int component1() {
        return this.index;
    }

    @NotNull
    public final String component2() {
        return this.f368id;
    }

    @NotNull
    public final String component3() {
        return this.title;
    }

    @NotNull
    public final String component4() {
        return this.shortDescription;
    }

    @NotNull
    public final String component5() {
        return this.url;
    }

    @NotNull
    public final String component6() {
        return this.imageURL;
    }

    @NotNull
    public final String component7() {
        return this.logoImageURL;
    }

    @NotNull
    public final FeaturedBusiness copy(int i, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6) {
        String str7 = str;
        Intrinsics.checkParameterIsNotNull(str, CommonProperties.f657ID);
        String str8 = str2;
        Intrinsics.checkParameterIsNotNull(str2, MessageBundle.TITLE_ENTRY);
        String str9 = str3;
        Intrinsics.checkParameterIsNotNull(str3, "shortDescription");
        String str10 = str4;
        Intrinsics.checkParameterIsNotNull(str4, ImagesContract.URL);
        String str11 = str5;
        Intrinsics.checkParameterIsNotNull(str5, "imageURL");
        String str12 = str6;
        Intrinsics.checkParameterIsNotNull(str12, "logoImageURL");
        FeaturedBusiness featuredBusiness = new FeaturedBusiness(i, str7, str8, str9, str10, str11, str12);
        return featuredBusiness;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof FeaturedBusiness) {
                FeaturedBusiness featuredBusiness = (FeaturedBusiness) obj;
                if (!(this.index == featuredBusiness.index) || !Intrinsics.areEqual((Object) this.f368id, (Object) featuredBusiness.f368id) || !Intrinsics.areEqual((Object) this.title, (Object) featuredBusiness.title) || !Intrinsics.areEqual((Object) this.shortDescription, (Object) featuredBusiness.shortDescription) || !Intrinsics.areEqual((Object) this.url, (Object) featuredBusiness.url) || !Intrinsics.areEqual((Object) this.imageURL, (Object) featuredBusiness.imageURL) || !Intrinsics.areEqual((Object) this.logoImageURL, (Object) featuredBusiness.logoImageURL)) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.index * 31;
        String str = this.f368id;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.title;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.shortDescription;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.url;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.imageURL;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.logoImageURL;
        if (str6 != null) {
            i2 = str6.hashCode();
        }
        return hashCode5 + i2;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FeaturedBusiness(index=");
        sb.append(this.index);
        sb.append(", id=");
        sb.append(this.f368id);
        sb.append(", title=");
        sb.append(this.title);
        sb.append(", shortDescription=");
        sb.append(this.shortDescription);
        sb.append(", url=");
        sb.append(this.url);
        sb.append(", imageURL=");
        sb.append(this.imageURL);
        sb.append(", logoImageURL=");
        sb.append(this.logoImageURL);
        sb.append(")");
        return sb.toString();
    }

    public FeaturedBusiness(int i, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6) {
        Intrinsics.checkParameterIsNotNull(str, CommonProperties.f657ID);
        Intrinsics.checkParameterIsNotNull(str2, MessageBundle.TITLE_ENTRY);
        Intrinsics.checkParameterIsNotNull(str3, "shortDescription");
        Intrinsics.checkParameterIsNotNull(str4, ImagesContract.URL);
        Intrinsics.checkParameterIsNotNull(str5, "imageURL");
        Intrinsics.checkParameterIsNotNull(str6, "logoImageURL");
        this.index = i;
        this.f368id = str;
        this.title = str2;
        this.shortDescription = str3;
        this.url = str4;
        this.imageURL = str5;
        this.logoImageURL = str6;
    }

    public final int getIndex() {
        return this.index;
    }

    @NotNull
    public final String getId() {
        return this.f368id;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    @NotNull
    public final String getShortDescription() {
        return this.shortDescription;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }

    @NotNull
    public final String getImageURL() {
        return this.imageURL;
    }

    @NotNull
    public final String getLogoImageURL() {
        return this.logoImageURL;
    }
}
