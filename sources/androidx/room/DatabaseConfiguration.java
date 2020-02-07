package androidx.room;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.room.RoomDatabase.Callback;
import androidx.room.RoomDatabase.JournalMode;
import androidx.room.RoomDatabase.MigrationContainer;
import androidx.sqlite.p006db.SupportSQLiteOpenHelper.Factory;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

public class DatabaseConfiguration {
    public final boolean allowDestructiveMigrationOnDowngrade;
    public final boolean allowMainThreadQueries;
    @Nullable
    public final List<Callback> callbacks;
    @NonNull
    public final Context context;
    public final JournalMode journalMode;
    private final Set<Integer> mMigrationNotRequiredFrom;
    @NonNull
    public final MigrationContainer migrationContainer;
    public final boolean multiInstanceInvalidation;
    @Nullable
    public final String name;
    @NonNull
    public final Executor queryExecutor;
    public final boolean requireMigration;
    @NonNull
    public final Factory sqliteOpenHelperFactory;
    @NonNull
    public final Executor transactionExecutor;

    @Deprecated
    @RestrictTo({Scope.LIBRARY_GROUP_PREFIX})
    public DatabaseConfiguration(@NonNull Context context2, @Nullable String str, @NonNull Factory factory, @NonNull MigrationContainer migrationContainer2, @Nullable List<Callback> list, boolean z, JournalMode journalMode2, @NonNull Executor executor, boolean z2, @Nullable Set<Integer> set) {
        this(context2, str, factory, migrationContainer2, list, z, journalMode2, executor, executor, false, z2, false, set);
    }

    @RestrictTo({Scope.LIBRARY_GROUP_PREFIX})
    public DatabaseConfiguration(@NonNull Context context2, @Nullable String str, @NonNull Factory factory, @NonNull MigrationContainer migrationContainer2, @Nullable List<Callback> list, boolean z, JournalMode journalMode2, @NonNull Executor executor, @NonNull Executor executor2, boolean z2, boolean z3, boolean z4, @Nullable Set<Integer> set) {
        this.sqliteOpenHelperFactory = factory;
        this.context = context2;
        this.name = str;
        this.migrationContainer = migrationContainer2;
        this.callbacks = list;
        this.allowMainThreadQueries = z;
        this.journalMode = journalMode2;
        this.queryExecutor = executor;
        this.transactionExecutor = executor2;
        this.multiInstanceInvalidation = z2;
        this.requireMigration = z3;
        this.allowDestructiveMigrationOnDowngrade = z4;
        this.mMigrationNotRequiredFrom = set;
    }

    public boolean isMigrationRequiredFrom(int i) {
        return isMigrationRequired(i, i + 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        if (r4.contains(java.lang.Integer.valueOf(r3)) != false) goto L_0x0021;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isMigrationRequired(int r3, int r4) {
        /*
            r2 = this;
            r0 = 1
            r1 = 0
            if (r3 <= r4) goto L_0x0006
            r4 = 1
            goto L_0x0007
        L_0x0006:
            r4 = 0
        L_0x0007:
            if (r4 == 0) goto L_0x000e
            boolean r4 = r2.allowDestructiveMigrationOnDowngrade
            if (r4 == 0) goto L_0x000e
            return r1
        L_0x000e:
            boolean r4 = r2.requireMigration
            if (r4 == 0) goto L_0x0021
            java.util.Set<java.lang.Integer> r4 = r2.mMigrationNotRequiredFrom
            if (r4 == 0) goto L_0x0022
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            boolean r3 = r4.contains(r3)
            if (r3 != 0) goto L_0x0021
            goto L_0x0022
        L_0x0021:
            r0 = 0
        L_0x0022:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.DatabaseConfiguration.isMigrationRequired(int, int):boolean");
    }
}
