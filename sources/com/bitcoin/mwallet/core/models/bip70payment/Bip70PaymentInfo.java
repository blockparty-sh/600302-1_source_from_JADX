package com.bitcoin.mwallet.core.models.bip70payment;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b'\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B}\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0002\u0010\u0014J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0012HÆ\u0003J\t\u0010/\u001a\u00020\u0012HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0006HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\u0001\u00108\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0012HÆ\u0001J\u0013\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010<\u001a\u00020\u0006HÖ\u0001J\t\u0010=\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u001a\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0011\u0010\u0013\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0016R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0016R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0016R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(¨\u0006>"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/bip70payment/Bip70PaymentInfo;", "", "merchantProcessor", "", "merchantName", "verification", "", "invoiceCurrency", "invoiceAmount", "paymentCurrency", "paymentAmount", "conversionRate", "conversionAssets", "itemDescription", "email", "merchantWebSite", "phone", "createdTime", "", "expiryTime", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJ)V", "getConversionAssets", "()Ljava/lang/String;", "getConversionRate", "getCreatedTime", "()J", "setCreatedTime", "(J)V", "getEmail", "getExpiryTime", "getInvoiceAmount", "getInvoiceCurrency", "getItemDescription", "getMerchantName", "getMerchantProcessor", "getMerchantWebSite", "getPaymentAmount", "getPaymentCurrency", "getPhone", "getVerification", "()I", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Bip70PaymentInfo.kt */
public final class Bip70PaymentInfo {
    @NotNull
    private final String conversionAssets;
    @NotNull
    private final String conversionRate;
    private long createdTime;
    @NotNull
    private final String email;
    private final long expiryTime;
    @NotNull
    private final String invoiceAmount;
    @NotNull
    private final String invoiceCurrency;
    @NotNull
    private final String itemDescription;
    @NotNull
    private final String merchantName;
    @NotNull
    private final String merchantProcessor;
    @NotNull
    private final String merchantWebSite;
    @NotNull
    private final String paymentAmount;
    @NotNull
    private final String paymentCurrency;
    @NotNull
    private final String phone;
    private final int verification;

    @NotNull
    public static /* synthetic */ Bip70PaymentInfo copy$default(Bip70PaymentInfo bip70PaymentInfo, String str, String str2, int i, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, long j, long j2, int i2, Object obj) {
        String str13;
        long j3;
        long j4;
        long j5;
        Bip70PaymentInfo bip70PaymentInfo2 = bip70PaymentInfo;
        int i3 = i2;
        String str14 = (i3 & 1) != 0 ? bip70PaymentInfo2.merchantProcessor : str;
        String str15 = (i3 & 2) != 0 ? bip70PaymentInfo2.merchantName : str2;
        int i4 = (i3 & 4) != 0 ? bip70PaymentInfo2.verification : i;
        String str16 = (i3 & 8) != 0 ? bip70PaymentInfo2.invoiceCurrency : str3;
        String str17 = (i3 & 16) != 0 ? bip70PaymentInfo2.invoiceAmount : str4;
        String str18 = (i3 & 32) != 0 ? bip70PaymentInfo2.paymentCurrency : str5;
        String str19 = (i3 & 64) != 0 ? bip70PaymentInfo2.paymentAmount : str6;
        String str20 = (i3 & 128) != 0 ? bip70PaymentInfo2.conversionRate : str7;
        String str21 = (i3 & 256) != 0 ? bip70PaymentInfo2.conversionAssets : str8;
        String str22 = (i3 & 512) != 0 ? bip70PaymentInfo2.itemDescription : str9;
        String str23 = (i3 & 1024) != 0 ? bip70PaymentInfo2.email : str10;
        String str24 = (i3 & 2048) != 0 ? bip70PaymentInfo2.merchantWebSite : str11;
        String str25 = (i3 & 4096) != 0 ? bip70PaymentInfo2.phone : str12;
        if ((i3 & 8192) != 0) {
            str13 = str25;
            j3 = bip70PaymentInfo2.createdTime;
        } else {
            str13 = str25;
            j3 = j;
        }
        if ((i3 & 16384) != 0) {
            j4 = j3;
            j5 = bip70PaymentInfo2.expiryTime;
        } else {
            j4 = j3;
            j5 = j2;
        }
        return bip70PaymentInfo.copy(str14, str15, i4, str16, str17, str18, str19, str20, str21, str22, str23, str24, str13, j4, j5);
    }

    @NotNull
    public final String component1() {
        return this.merchantProcessor;
    }

    @NotNull
    public final String component10() {
        return this.itemDescription;
    }

    @NotNull
    public final String component11() {
        return this.email;
    }

    @NotNull
    public final String component12() {
        return this.merchantWebSite;
    }

    @NotNull
    public final String component13() {
        return this.phone;
    }

    public final long component14() {
        return this.createdTime;
    }

    public final long component15() {
        return this.expiryTime;
    }

    @NotNull
    public final String component2() {
        return this.merchantName;
    }

    public final int component3() {
        return this.verification;
    }

    @NotNull
    public final String component4() {
        return this.invoiceCurrency;
    }

    @NotNull
    public final String component5() {
        return this.invoiceAmount;
    }

    @NotNull
    public final String component6() {
        return this.paymentCurrency;
    }

    @NotNull
    public final String component7() {
        return this.paymentAmount;
    }

    @NotNull
    public final String component8() {
        return this.conversionRate;
    }

    @NotNull
    public final String component9() {
        return this.conversionAssets;
    }

    @NotNull
    public final Bip70PaymentInfo copy(@NotNull String str, @NotNull String str2, int i, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, @NotNull String str8, @NotNull String str9, @NotNull String str10, @NotNull String str11, @NotNull String str12, long j, long j2) {
        String str13 = str;
        String str14 = str2;
        int i2 = i;
        String str15 = str3;
        String str16 = str4;
        String str17 = str5;
        String str18 = str6;
        String str19 = str7;
        String str20 = str8;
        String str21 = str9;
        String str22 = str10;
        String str23 = str11;
        String str24 = str12;
        long j3 = j;
        long j4 = j2;
        String str25 = str13;
        Intrinsics.checkParameterIsNotNull(str13, "merchantProcessor");
        Intrinsics.checkParameterIsNotNull(str2, "merchantName");
        Intrinsics.checkParameterIsNotNull(str3, "invoiceCurrency");
        Intrinsics.checkParameterIsNotNull(str4, "invoiceAmount");
        Intrinsics.checkParameterIsNotNull(str5, "paymentCurrency");
        Intrinsics.checkParameterIsNotNull(str6, "paymentAmount");
        Intrinsics.checkParameterIsNotNull(str7, "conversionRate");
        Intrinsics.checkParameterIsNotNull(str8, "conversionAssets");
        Intrinsics.checkParameterIsNotNull(str9, "itemDescription");
        Intrinsics.checkParameterIsNotNull(str10, "email");
        Intrinsics.checkParameterIsNotNull(str11, "merchantWebSite");
        Intrinsics.checkParameterIsNotNull(str12, "phone");
        Bip70PaymentInfo bip70PaymentInfo = new Bip70PaymentInfo(str25, str14, i2, str15, str16, str17, str18, str19, str20, str21, str22, str23, str24, j3, j4);
        return bip70PaymentInfo;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof Bip70PaymentInfo) {
                Bip70PaymentInfo bip70PaymentInfo = (Bip70PaymentInfo) obj;
                if (Intrinsics.areEqual((Object) this.merchantProcessor, (Object) bip70PaymentInfo.merchantProcessor) && Intrinsics.areEqual((Object) this.merchantName, (Object) bip70PaymentInfo.merchantName)) {
                    if ((this.verification == bip70PaymentInfo.verification) && Intrinsics.areEqual((Object) this.invoiceCurrency, (Object) bip70PaymentInfo.invoiceCurrency) && Intrinsics.areEqual((Object) this.invoiceAmount, (Object) bip70PaymentInfo.invoiceAmount) && Intrinsics.areEqual((Object) this.paymentCurrency, (Object) bip70PaymentInfo.paymentCurrency) && Intrinsics.areEqual((Object) this.paymentAmount, (Object) bip70PaymentInfo.paymentAmount) && Intrinsics.areEqual((Object) this.conversionRate, (Object) bip70PaymentInfo.conversionRate) && Intrinsics.areEqual((Object) this.conversionAssets, (Object) bip70PaymentInfo.conversionAssets) && Intrinsics.areEqual((Object) this.itemDescription, (Object) bip70PaymentInfo.itemDescription) && Intrinsics.areEqual((Object) this.email, (Object) bip70PaymentInfo.email) && Intrinsics.areEqual((Object) this.merchantWebSite, (Object) bip70PaymentInfo.merchantWebSite) && Intrinsics.areEqual((Object) this.phone, (Object) bip70PaymentInfo.phone)) {
                        if (this.createdTime == bip70PaymentInfo.createdTime) {
                            if (this.expiryTime == bip70PaymentInfo.expiryTime) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.merchantProcessor;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.merchantName;
        int hashCode2 = (((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.verification) * 31;
        String str3 = this.invoiceCurrency;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.invoiceAmount;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.paymentCurrency;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.paymentAmount;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.conversionRate;
        int hashCode7 = (hashCode6 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.conversionAssets;
        int hashCode8 = (hashCode7 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.itemDescription;
        int hashCode9 = (hashCode8 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.email;
        int hashCode10 = (hashCode9 + (str10 != null ? str10.hashCode() : 0)) * 31;
        String str11 = this.merchantWebSite;
        int hashCode11 = (hashCode10 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.phone;
        if (str12 != null) {
            i = str12.hashCode();
        }
        int i2 = (hashCode11 + i) * 31;
        long j = this.createdTime;
        int i3 = (i2 + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.expiryTime;
        return i3 + ((int) (j2 ^ (j2 >>> 32)));
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bip70PaymentInfo(merchantProcessor=");
        sb.append(this.merchantProcessor);
        sb.append(", merchantName=");
        sb.append(this.merchantName);
        sb.append(", verification=");
        sb.append(this.verification);
        sb.append(", invoiceCurrency=");
        sb.append(this.invoiceCurrency);
        sb.append(", invoiceAmount=");
        sb.append(this.invoiceAmount);
        sb.append(", paymentCurrency=");
        sb.append(this.paymentCurrency);
        sb.append(", paymentAmount=");
        sb.append(this.paymentAmount);
        sb.append(", conversionRate=");
        sb.append(this.conversionRate);
        sb.append(", conversionAssets=");
        sb.append(this.conversionAssets);
        sb.append(", itemDescription=");
        sb.append(this.itemDescription);
        sb.append(", email=");
        sb.append(this.email);
        sb.append(", merchantWebSite=");
        sb.append(this.merchantWebSite);
        sb.append(", phone=");
        sb.append(this.phone);
        sb.append(", createdTime=");
        sb.append(this.createdTime);
        sb.append(", expiryTime=");
        sb.append(this.expiryTime);
        sb.append(")");
        return sb.toString();
    }

    public Bip70PaymentInfo(@NotNull String str, @NotNull String str2, int i, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, @NotNull String str8, @NotNull String str9, @NotNull String str10, @NotNull String str11, @NotNull String str12, long j, long j2) {
        String str13 = str;
        String str14 = str2;
        String str15 = str3;
        String str16 = str4;
        String str17 = str5;
        String str18 = str6;
        String str19 = str7;
        String str20 = str8;
        String str21 = str9;
        String str22 = str10;
        String str23 = str11;
        String str24 = str12;
        Intrinsics.checkParameterIsNotNull(str, "merchantProcessor");
        Intrinsics.checkParameterIsNotNull(str14, "merchantName");
        Intrinsics.checkParameterIsNotNull(str15, "invoiceCurrency");
        Intrinsics.checkParameterIsNotNull(str16, "invoiceAmount");
        Intrinsics.checkParameterIsNotNull(str17, "paymentCurrency");
        Intrinsics.checkParameterIsNotNull(str18, "paymentAmount");
        Intrinsics.checkParameterIsNotNull(str19, "conversionRate");
        Intrinsics.checkParameterIsNotNull(str20, "conversionAssets");
        Intrinsics.checkParameterIsNotNull(str21, "itemDescription");
        Intrinsics.checkParameterIsNotNull(str22, "email");
        Intrinsics.checkParameterIsNotNull(str23, "merchantWebSite");
        Intrinsics.checkParameterIsNotNull(str24, "phone");
        this.merchantProcessor = str13;
        this.merchantName = str14;
        this.verification = i;
        this.invoiceCurrency = str15;
        this.invoiceAmount = str16;
        this.paymentCurrency = str17;
        this.paymentAmount = str18;
        this.conversionRate = str19;
        this.conversionAssets = str20;
        this.itemDescription = str21;
        this.email = str22;
        this.merchantWebSite = str23;
        this.phone = str24;
        this.createdTime = j;
        this.expiryTime = j2;
    }

    @NotNull
    public final String getMerchantProcessor() {
        return this.merchantProcessor;
    }

    @NotNull
    public final String getMerchantName() {
        return this.merchantName;
    }

    public final int getVerification() {
        return this.verification;
    }

    @NotNull
    public final String getInvoiceCurrency() {
        return this.invoiceCurrency;
    }

    @NotNull
    public final String getInvoiceAmount() {
        return this.invoiceAmount;
    }

    @NotNull
    public final String getPaymentCurrency() {
        return this.paymentCurrency;
    }

    @NotNull
    public final String getPaymentAmount() {
        return this.paymentAmount;
    }

    @NotNull
    public final String getConversionRate() {
        return this.conversionRate;
    }

    @NotNull
    public final String getConversionAssets() {
        return this.conversionAssets;
    }

    @NotNull
    public final String getItemDescription() {
        return this.itemDescription;
    }

    @NotNull
    public final String getEmail() {
        return this.email;
    }

    @NotNull
    public final String getMerchantWebSite() {
        return this.merchantWebSite;
    }

    @NotNull
    public final String getPhone() {
        return this.phone;
    }

    public final long getCreatedTime() {
        return this.createdTime;
    }

    public final void setCreatedTime(long j) {
        this.createdTime = j;
    }

    public final long getExpiryTime() {
        return this.expiryTime;
    }
}
