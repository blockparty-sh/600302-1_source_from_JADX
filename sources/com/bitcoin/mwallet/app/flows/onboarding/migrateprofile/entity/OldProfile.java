package com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldProfile;", "", "credentials", "", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWallet;", "(Ljava/util/List;)V", "getCredentials", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: OldProfile.kt */
public final class OldProfile {
    @NotNull
    private final List<OldWallet> credentials;

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet>, for r1v0, types: [java.util.List] */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldProfile copy$default(com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldProfile r0, java.util.List<com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet> r1, int r2, java.lang.Object r3) {
        /*
            r2 = r2 & 1
            if (r2 == 0) goto L_0x0006
            java.util.List<com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet> r1 = r0.credentials
        L_0x0006:
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldProfile r0 = r0.copy(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldProfile.copy$default(com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldProfile, java.util.List, int, java.lang.Object):com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldProfile");
    }

    @NotNull
    public final List<OldWallet> component1() {
        return this.credentials;
    }

    @NotNull
    public final OldProfile copy(@NotNull List<OldWallet> list) {
        Intrinsics.checkParameterIsNotNull(list, "credentials");
        return new OldProfile(list);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1.credentials, (java.lang.Object) ((com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldProfile) r2).credentials) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r2) {
        /*
            r1 = this;
            if (r1 == r2) goto L_0x0015
            boolean r0 = r2 instanceof com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldProfile
            if (r0 == 0) goto L_0x0013
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldProfile r2 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldProfile) r2
            java.util.List<com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet> r0 = r1.credentials
            java.util.List<com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet> r2 = r2.credentials
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
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldProfile.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        List<OldWallet> list = this.credentials;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OldProfile(credentials=");
        sb.append(this.credentials);
        sb.append(")");
        return sb.toString();
    }

    public OldProfile(@NotNull List<OldWallet> list) {
        Intrinsics.checkParameterIsNotNull(list, "credentials");
        this.credentials = list;
    }

    @NotNull
    public final List<OldWallet> getCredentials() {
        return this.credentials;
    }
}
