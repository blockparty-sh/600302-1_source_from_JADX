package com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldContact;

import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.contact.Contact;
import com.bitcoin.mwallet.core.models.contact.ContactId;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\b\u0018\u0000 \u001a2\u00020\u0001:\u0002\u001a\u001bB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\u0006\u0010\u0017\u001a\u00020\u0018J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldContact/OldContact;", "", "name", "", "email", "address", "coin", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAddress", "()Ljava/lang/String;", "getCoin", "getEmail", "getName", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toNewContact", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "toString", "Companion", "OldContactJsonSource", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldContact.OldContact */
/* compiled from: OldContact.kt */
public final class C1098OldContact {
    private static final String COIN_BCH = "bch";
    private static final String COIN_BTC = "btc";
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String address;
    @NotNull
    private final String coin;
    @NotNull
    private final String email;
    @NotNull
    private final String name;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldContact/OldContact$Companion;", "", "()V", "COIN_BCH", "", "COIN_BTC", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* renamed from: com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldContact.OldContact$Companion */
    /* compiled from: OldContact.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldContact/OldContact$OldContactJsonSource;", "", "fileExists", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "read", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* renamed from: com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldContact.OldContact$OldContactJsonSource */
    /* compiled from: OldContact.kt */
    public interface OldContactJsonSource {
        @Nullable
        Object fileExists(@NotNull Continuation<? super Boolean> continuation);

        @Nullable
        Object read(@NotNull Continuation<? super String> continuation);
    }

    @NotNull
    public static /* synthetic */ C1098OldContact copy$default(C1098OldContact oldContact, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = oldContact.name;
        }
        if ((i & 2) != 0) {
            str2 = oldContact.email;
        }
        if ((i & 4) != 0) {
            str3 = oldContact.address;
        }
        if ((i & 8) != 0) {
            str4 = oldContact.coin;
        }
        return oldContact.copy(str, str2, str3, str4);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final String component2() {
        return this.email;
    }

    @NotNull
    public final String component3() {
        return this.address;
    }

    @NotNull
    public final String component4() {
        return this.coin;
    }

    @NotNull
    public final C1098OldContact copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(str2, "email");
        Intrinsics.checkParameterIsNotNull(str3, "address");
        Intrinsics.checkParameterIsNotNull(str4, "coin");
        return new C1098OldContact(str, str2, str3, str4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002e, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.coin, (java.lang.Object) r3.coin) != false) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x0033
            boolean r0 = r3 instanceof com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldContact.C1098OldContact
            if (r0 == 0) goto L_0x0031
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldContact.OldContact r3 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldContact.C1098OldContact) r3
            java.lang.String r0 = r2.name
            java.lang.String r1 = r3.name
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0031
            java.lang.String r0 = r2.email
            java.lang.String r1 = r3.email
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0031
            java.lang.String r0 = r2.address
            java.lang.String r1 = r3.address
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0031
            java.lang.String r0 = r2.coin
            java.lang.String r3 = r3.coin
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x0031
            goto L_0x0033
        L_0x0031:
            r3 = 0
            return r3
        L_0x0033:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldContact.C1098OldContact.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.name;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.email;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.address;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.coin;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OldContact(name=");
        sb.append(this.name);
        sb.append(", email=");
        sb.append(this.email);
        sb.append(", address=");
        sb.append(this.address);
        sb.append(", coin=");
        sb.append(this.coin);
        sb.append(")");
        return sb.toString();
    }

    public C1098OldContact(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(str2, "email");
        Intrinsics.checkParameterIsNotNull(str3, "address");
        Intrinsics.checkParameterIsNotNull(str4, "coin");
        this.name = str;
        this.email = str2;
        this.address = str3;
        this.coin = str4;
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
    public final String getAddress() {
        return this.address;
    }

    @NotNull
    public final String getCoin() {
        return this.coin;
    }

    @NotNull
    public final Contact toNewContact() {
        Coin coin2;
        String str = this.coin;
        int hashCode = str.hashCode();
        if (hashCode != 97351) {
            if (hashCode == 97873 && str.equals(COIN_BTC)) {
                coin2 = Coin.BTC;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("coin=");
            sb.append(this.coin);
            throw new IllegalArgumentException(sb.toString());
        }
        if (str.equals(COIN_BCH)) {
            coin2 = Coin.BCH;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("coin=");
        sb2.append(this.coin);
        throw new IllegalArgumentException(sb2.toString());
        String str2 = "";
        Contact contact = new Contact(new ContactId(this.address), this.name, this.email, str2, coin2, "", this.address, "", true, true, true, true);
        return contact;
    }
}
