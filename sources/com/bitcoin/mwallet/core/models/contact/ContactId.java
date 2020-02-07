package com.bitcoin.mwallet.core.models.contact;

import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/contact/ContactId;", "Ljava/io/Serializable;", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ContactId.kt */
public final class ContactId implements Serializable {
    @NotNull

    /* renamed from: id */
    private final String f365id;

    @NotNull
    public static /* synthetic */ ContactId copy$default(ContactId contactId, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = contactId.f365id;
        }
        return contactId.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.f365id;
    }

    @NotNull
    public final ContactId copy(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, CommonProperties.f657ID);
        return new ContactId(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1.f365id, (java.lang.Object) ((com.bitcoin.mwallet.core.models.contact.ContactId) r2).f365id) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r2) {
        /*
            r1 = this;
            if (r1 == r2) goto L_0x0015
            boolean r0 = r2 instanceof com.bitcoin.mwallet.core.models.contact.ContactId
            if (r0 == 0) goto L_0x0013
            com.bitcoin.mwallet.core.models.contact.ContactId r2 = (com.bitcoin.mwallet.core.models.contact.ContactId) r2
            java.lang.String r0 = r1.f365id
            java.lang.String r2 = r2.f365id
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r2 == 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r2 = 0
            return r2
        L_0x0015:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.models.contact.ContactId.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.f365id;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public ContactId(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, CommonProperties.f657ID);
        this.f365id = str;
    }

    @NotNull
    public final String getId() {
        return this.f365id;
    }

    @NotNull
    public String toString() {
        return this.f365id;
    }
}
