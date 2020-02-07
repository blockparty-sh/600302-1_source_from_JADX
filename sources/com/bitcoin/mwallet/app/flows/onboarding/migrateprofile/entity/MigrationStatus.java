package com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u0014B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/MigrationStatus;", "", "status", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/MigrationStatus$Migration;", "statusText", "", "(Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/MigrationStatus$Migration;Ljava/lang/String;)V", "getStatus", "()Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/MigrationStatus$Migration;", "getStatusText", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "Migration", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: MigrationStatus.kt */
public final class MigrationStatus {
    @NotNull
    private final Migration status;
    @NotNull
    private final String statusText;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/MigrationStatus$Migration;", "", "(Ljava/lang/String;I)V", "NOT_STARTED", "MIGRATING", "DONE", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: MigrationStatus.kt */
    public enum Migration {
        NOT_STARTED,
        MIGRATING,
        DONE
    }

    @NotNull
    public static /* synthetic */ MigrationStatus copy$default(MigrationStatus migrationStatus, Migration migration, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            migration = migrationStatus.status;
        }
        if ((i & 2) != 0) {
            str = migrationStatus.statusText;
        }
        return migrationStatus.copy(migration, str);
    }

    @NotNull
    public final Migration component1() {
        return this.status;
    }

    @NotNull
    public final String component2() {
        return this.statusText;
    }

    @NotNull
    public final MigrationStatus copy(@NotNull Migration migration, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(migration, NotificationCompat.CATEGORY_STATUS);
        Intrinsics.checkParameterIsNotNull(str, "statusText");
        return new MigrationStatus(migration, str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.statusText, (java.lang.Object) r3.statusText) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus
            if (r0 == 0) goto L_0x001d
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus r3 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus) r3
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus$Migration r0 = r2.status
            com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus$Migration r1 = r3.status
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            java.lang.String r0 = r2.statusText
            java.lang.String r3 = r3.statusText
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r3 = 0
            return r3
        L_0x001f:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        Migration migration = this.status;
        int i = 0;
        int hashCode = (migration != null ? migration.hashCode() : 0) * 31;
        String str = this.statusText;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MigrationStatus(status=");
        sb.append(this.status);
        sb.append(", statusText=");
        sb.append(this.statusText);
        sb.append(")");
        return sb.toString();
    }

    public MigrationStatus(@NotNull Migration migration, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(migration, NotificationCompat.CATEGORY_STATUS);
        Intrinsics.checkParameterIsNotNull(str, "statusText");
        this.status = migration;
        this.statusText = str;
    }

    @NotNull
    public final Migration getStatus() {
        return this.status;
    }

    @NotNull
    public final String getStatusText() {
        return this.statusText;
    }
}
