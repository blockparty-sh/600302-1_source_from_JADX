package com.bitcoin.mwallet.core.models.contact;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.Coin;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b \n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 12\u00020\u0001:\u00011Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u000e¢\u0006\u0002\u0010\u0012J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u000eHÆ\u0003J\t\u0010!\u001a\u00020\u000eHÆ\u0003J\t\u0010\"\u001a\u00020\u000eHÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\tHÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u000eHÆ\u0003J\u0001\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u000eHÆ\u0001J\u0013\u0010,\u001a\u00020\u000e2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010.\u001a\u00020/HÖ\u0001J\t\u00100\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u000b\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u0011\u001a\u00020\u000e8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u001bR\u0016\u0010\u0010\u001a\u00020\u000e8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u001bR\u0016\u0010\r\u001a\u00020\u000e8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u001bR\u0016\u0010\u000f\u001a\u00020\u000e8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u001bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0016\u0010\n\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014R\u0016\u0010\f\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014¨\u00062"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/contact/Contact;", "", "id", "Lcom/bitcoin/mwallet/core/models/contact/ContactId;", "name", "", "email", "contactDescription", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "walletType", "address", "website", "isEditable", "", "isKnownAddress", "isAlwaysShown", "isAlwaysHidden", "(Lcom/bitcoin/mwallet/core/models/contact/ContactId;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/bitcoin/mwallet/core/models/Coin;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZZ)V", "getAddress", "()Ljava/lang/String;", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "getContactDescription", "getEmail", "getId", "()Lcom/bitcoin/mwallet/core/models/contact/ContactId;", "()Z", "getName", "getWalletType", "getWebsite", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@Entity(tableName = "contact")
/* compiled from: Contact.kt */
public final class Contact {
    public static final Companion Companion = new Companion(null);
    @ColumnInfo(name = "address")
    @NotNull
    private final String address;
    @ColumnInfo(name = "coin")
    @NotNull
    private final Coin coin;
    @ColumnInfo(name = "description")
    @NotNull
    private final String contactDescription;
    @ColumnInfo(name = "email")
    @NotNull
    private final String email;
    @ColumnInfo(name = "id")
    @NotNull
    @PrimaryKey

    /* renamed from: id */
    private final ContactId f364id;
    @ColumnInfo(name = "always_hidden")
    private final boolean isAlwaysHidden;
    @ColumnInfo(name = "always_shown")
    private final boolean isAlwaysShown;
    @ColumnInfo(name = "editable")
    private final boolean isEditable;
    @ColumnInfo(name = "known_address")
    private final boolean isKnownAddress;
    @ColumnInfo(name = "name")
    @NotNull
    private final String name;
    @ColumnInfo(name = "wallet_type")
    @NotNull
    private final String walletType;
    @ColumnInfo(name = "website")
    @NotNull
    private final String website;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/contact/Contact$Companion;", "", "()V", "createNew", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "walletType", "", "name", "address", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: Contact.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Contact createNew(@NotNull String str, @NotNull String str2, @NotNull String str3) {
            String str4 = str;
            Intrinsics.checkParameterIsNotNull(str, "walletType");
            Intrinsics.checkParameterIsNotNull(str2, "name");
            Intrinsics.checkParameterIsNotNull(str3, "address");
            String uuid = UUID.randomUUID().toString();
            Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
            Contact contact = new Contact(new ContactId(uuid), str2, "", "", Intrinsics.areEqual((Object) str, (Object) WalletType.BTC.toString()) ? Coin.BTC : Coin.BCH, str, str3, "", true, true, true, false);
            return contact;
        }
    }

    @NotNull
    public static /* synthetic */ Contact copy$default(Contact contact, ContactId contactId, String str, String str2, String str3, Coin coin2, String str4, String str5, String str6, boolean z, boolean z2, boolean z3, boolean z4, int i, Object obj) {
        Contact contact2 = contact;
        int i2 = i;
        return contact.copy((i2 & 1) != 0 ? contact2.f364id : contactId, (i2 & 2) != 0 ? contact2.name : str, (i2 & 4) != 0 ? contact2.email : str2, (i2 & 8) != 0 ? contact2.contactDescription : str3, (i2 & 16) != 0 ? contact2.coin : coin2, (i2 & 32) != 0 ? contact2.walletType : str4, (i2 & 64) != 0 ? contact2.address : str5, (i2 & 128) != 0 ? contact2.website : str6, (i2 & 256) != 0 ? contact2.isEditable : z, (i2 & 512) != 0 ? contact2.isKnownAddress : z2, (i2 & 1024) != 0 ? contact2.isAlwaysShown : z3, (i2 & 2048) != 0 ? contact2.isAlwaysHidden : z4);
    }

    @NotNull
    public final ContactId component1() {
        return this.f364id;
    }

    public final boolean component10() {
        return this.isKnownAddress;
    }

    public final boolean component11() {
        return this.isAlwaysShown;
    }

    public final boolean component12() {
        return this.isAlwaysHidden;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    @NotNull
    public final String component3() {
        return this.email;
    }

    @NotNull
    public final String component4() {
        return this.contactDescription;
    }

    @NotNull
    public final Coin component5() {
        return this.coin;
    }

    @NotNull
    public final String component6() {
        return this.walletType;
    }

    @NotNull
    public final String component7() {
        return this.address;
    }

    @NotNull
    public final String component8() {
        return this.website;
    }

    public final boolean component9() {
        return this.isEditable;
    }

    @NotNull
    public final Contact copy(@NotNull ContactId contactId, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull Coin coin2, @NotNull String str4, @NotNull String str5, @NotNull String str6, boolean z, boolean z2, boolean z3, boolean z4) {
        ContactId contactId2 = contactId;
        Intrinsics.checkParameterIsNotNull(contactId, CommonProperties.f657ID);
        String str7 = str;
        Intrinsics.checkParameterIsNotNull(str7, "name");
        String str8 = str2;
        Intrinsics.checkParameterIsNotNull(str8, "email");
        String str9 = str3;
        Intrinsics.checkParameterIsNotNull(str9, "contactDescription");
        Coin coin3 = coin2;
        Intrinsics.checkParameterIsNotNull(coin3, "coin");
        String str10 = str4;
        Intrinsics.checkParameterIsNotNull(str10, "walletType");
        String str11 = str5;
        Intrinsics.checkParameterIsNotNull(str11, "address");
        String str12 = str6;
        Intrinsics.checkParameterIsNotNull(str12, "website");
        Contact contact = new Contact(contactId2, str7, str8, str9, coin3, str10, str11, str12, z, z2, z3, z4);
        return contact;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof Contact) {
                Contact contact = (Contact) obj;
                if (Intrinsics.areEqual((Object) this.f364id, (Object) contact.f364id) && Intrinsics.areEqual((Object) this.name, (Object) contact.name) && Intrinsics.areEqual((Object) this.email, (Object) contact.email) && Intrinsics.areEqual((Object) this.contactDescription, (Object) contact.contactDescription) && Intrinsics.areEqual((Object) this.coin, (Object) contact.coin) && Intrinsics.areEqual((Object) this.walletType, (Object) contact.walletType) && Intrinsics.areEqual((Object) this.address, (Object) contact.address) && Intrinsics.areEqual((Object) this.website, (Object) contact.website)) {
                    if (this.isEditable == contact.isEditable) {
                        if (this.isKnownAddress == contact.isKnownAddress) {
                            if (this.isAlwaysShown == contact.isAlwaysShown) {
                                if (this.isAlwaysHidden == contact.isAlwaysHidden) {
                                    return true;
                                }
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
        ContactId contactId = this.f364id;
        int i = 0;
        int hashCode = (contactId != null ? contactId.hashCode() : 0) * 31;
        String str = this.name;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.email;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.contactDescription;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        Coin coin2 = this.coin;
        int hashCode5 = (hashCode4 + (coin2 != null ? coin2.hashCode() : 0)) * 31;
        String str4 = this.walletType;
        int hashCode6 = (hashCode5 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.address;
        int hashCode7 = (hashCode6 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.website;
        if (str6 != null) {
            i = str6.hashCode();
        }
        int i2 = (hashCode7 + i) * 31;
        boolean z = this.isEditable;
        if (z) {
            z = true;
        }
        int i3 = (i2 + (z ? 1 : 0)) * 31;
        boolean z2 = this.isKnownAddress;
        if (z2) {
            z2 = true;
        }
        int i4 = (i3 + (z2 ? 1 : 0)) * 31;
        boolean z3 = this.isAlwaysShown;
        if (z3) {
            z3 = true;
        }
        int i5 = (i4 + (z3 ? 1 : 0)) * 31;
        boolean z4 = this.isAlwaysHidden;
        if (z4) {
            z4 = true;
        }
        return i5 + (z4 ? 1 : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Contact(id=");
        sb.append(this.f364id);
        sb.append(", name=");
        sb.append(this.name);
        sb.append(", email=");
        sb.append(this.email);
        sb.append(", contactDescription=");
        sb.append(this.contactDescription);
        sb.append(", coin=");
        sb.append(this.coin);
        sb.append(", walletType=");
        sb.append(this.walletType);
        sb.append(", address=");
        sb.append(this.address);
        sb.append(", website=");
        sb.append(this.website);
        sb.append(", isEditable=");
        sb.append(this.isEditable);
        sb.append(", isKnownAddress=");
        sb.append(this.isKnownAddress);
        sb.append(", isAlwaysShown=");
        sb.append(this.isAlwaysShown);
        sb.append(", isAlwaysHidden=");
        sb.append(this.isAlwaysHidden);
        sb.append(")");
        return sb.toString();
    }

    public Contact(@NotNull ContactId contactId, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull Coin coin2, @NotNull String str4, @NotNull String str5, @NotNull String str6, boolean z, boolean z2, boolean z3, boolean z4) {
        Intrinsics.checkParameterIsNotNull(contactId, CommonProperties.f657ID);
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(str2, "email");
        Intrinsics.checkParameterIsNotNull(str3, "contactDescription");
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        Intrinsics.checkParameterIsNotNull(str4, "walletType");
        Intrinsics.checkParameterIsNotNull(str5, "address");
        Intrinsics.checkParameterIsNotNull(str6, "website");
        this.f364id = contactId;
        this.name = str;
        this.email = str2;
        this.contactDescription = str3;
        this.coin = coin2;
        this.walletType = str4;
        this.address = str5;
        this.website = str6;
        this.isEditable = z;
        this.isKnownAddress = z2;
        this.isAlwaysShown = z3;
        this.isAlwaysHidden = z4;
    }

    @NotNull
    public final ContactId getId() {
        return this.f364id;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getEmail() {
        return this.email;
    }

    @NotNull
    public final String getContactDescription() {
        return this.contactDescription;
    }

    @NotNull
    public final Coin getCoin() {
        return this.coin;
    }

    @NotNull
    public final String getWalletType() {
        return this.walletType;
    }

    @NotNull
    public final String getAddress() {
        return this.address;
    }

    @NotNull
    public final String getWebsite() {
        return this.website;
    }

    public final boolean isEditable() {
        return this.isEditable;
    }

    public final boolean isKnownAddress() {
        return this.isKnownAddress;
    }

    public final boolean isAlwaysShown() {
        return this.isAlwaysShown;
    }

    public final boolean isAlwaysHidden() {
        return this.isAlwaysHidden;
    }
}
