package com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.Uri;
import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet.OldWalletsJsonSource;
import java.io.File;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p016io.FilesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\u0005\u001a\u00020\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\n\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u0013\u0010\n\u001a\u0004\u0018\u00010\u000bH@ø\u0001\u0000¢\u0006\u0002\u0010\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWalletsFileSystemSource;", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWallet$OldWalletsJsonSource;", "applicationContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "fileExists", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFile", "Ljava/io/File;", "read", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: OldWalletsFileSystemSource.kt */
public final class OldWalletsFileSystemSource implements OldWalletsJsonSource {
    private final Context applicationContext;

    public OldWalletsFileSystemSource(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "applicationContext");
        this.applicationContext = context;
    }

    @Nullable
    public Object read(@NotNull Continuation<? super String> continuation) {
        File file = getFile();
        if (file == null || !file.exists()) {
            return null;
        }
        String readText$default = FilesKt.readText$default(file, null, 1, null);
        StringBuilder sb = new StringBuilder();
        sb.append("profile: ");
        sb.append(readText$default);
        Timber.m426d(sb.toString(), new Object[0]);
        return readText$default;
    }

    @Nullable
    public Object fileExists(@NotNull Continuation<? super Boolean> continuation) {
        boolean z;
        File file = getFile();
        if (file != null) {
            Boolean boxBoolean = Boxing.boxBoolean(file.exists());
            if (boxBoolean != null) {
                z = boxBoolean.booleanValue();
                return Boxing.boxBoolean(z);
            }
        }
        z = false;
        return Boxing.boxBoolean(z);
    }

    private final File getFile() {
        String packageName = this.applicationContext.getPackageName();
        Intrinsics.checkExpressionValueIsNotNull(packageName, "applicationContext.packageName");
        PackageInfo packageInfo = this.applicationContext.getPackageManager().getPackageInfo(packageName, 0);
        Intrinsics.checkExpressionValueIsNotNull(packageInfo, "applicationContext.packa…ckageInfo(packageName, 0)");
        String str = packageInfo.applicationInfo.dataDir;
        Intrinsics.checkExpressionValueIsNotNull(str, "packageInfo.applicationInfo.dataDir");
        StringBuilder sb = new StringBuilder();
        sb.append("dataDirectory: ");
        sb.append(str);
        Timber.m426d(sb.toString(), new Object[0]);
        Uri parse = Uri.parse(str);
        if (parse == null) {
            return null;
        }
        Uri withAppendedPath = Uri.withAppendedPath(parse, "files/profile");
        if (withAppendedPath != null) {
            return new File(withAppendedPath.getPath());
        }
        return null;
    }
}
