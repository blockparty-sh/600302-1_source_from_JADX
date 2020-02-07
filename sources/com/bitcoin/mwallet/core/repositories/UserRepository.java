package com.bitcoin.mwallet.core.repositories;

import androidx.lifecycle.LiveData;
import com.bitcoin.mwallet.core.dao.CredentialDao;
import com.bitcoin.mwallet.core.dao.UserDao;
import com.bitcoin.mwallet.core.models.user.User;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\u0011\u0010\n\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0019\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0011\u0010\u0011\u001a\u00020\u0012H@ø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/repositories/UserRepository;", "", "credentialDao", "Lcom/bitcoin/mwallet/core/dao/CredentialDao;", "dao", "Lcom/bitcoin/mwallet/core/dao/UserDao;", "(Lcom/bitcoin/mwallet/core/dao/CredentialDao;Lcom/bitcoin/mwallet/core/dao/UserDao;)V", "getAllUsers", "Landroidx/lifecycle/LiveData;", "Lcom/bitcoin/mwallet/core/models/user/User;", "getOrCreateUser", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setUserId", "", "userId", "Lcom/bitcoin/mwallet/core/models/user/UserId;", "(Lcom/bitcoin/mwallet/core/models/user/UserId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "userExists", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: UserRepository.kt */
public final class UserRepository {
    /* access modifiers changed from: private */
    public final CredentialDao credentialDao;
    /* access modifiers changed from: private */
    public final UserDao dao;

    public UserRepository(@NotNull CredentialDao credentialDao2, @NotNull UserDao userDao) {
        Intrinsics.checkParameterIsNotNull(credentialDao2, "credentialDao");
        Intrinsics.checkParameterIsNotNull(userDao, "dao");
        this.credentialDao = credentialDao2;
        this.dao = userDao;
    }

    @NotNull
    public final LiveData<User> getAllUsers() {
        return this.dao.getAllUsers();
    }

    @Nullable
    public final Object userExists(@NotNull Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new UserRepository$userExists$2(this, null), continuation);
    }

    @Nullable
    public final synchronized Object getOrCreateUser(@NotNull Continuation<? super User> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new UserRepository$getOrCreateUser$2(this, null), continuation);
    }

    /* JADX INFO: used method not loaded: com.bitcoin.mwallet.core.models.credential.CredentialId.Companion.from(com.bitcoin.mwallet.core.models.user.UserId):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00f7, code lost:
        r14 = (com.bitcoin.mwallet.core.models.user.User) r14;
        r5 = r14.getCredentialId();
        r6 = r2.credentialDao;
        r7 = r5.getId();
        r0.L$0 = r2;
        r0.L$1 = r13;
        r0.L$2 = r14;
        r0.L$3 = r5;
        r0.label = 2;
        r6 = r6.selectMnemonic(r7, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0112, code lost:
        if (r6 != r1) goto L_0x0115;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0114, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0115, code lost:
        r9 = r13;
        r8 = r14;
        r10 = r2;
        r7 = r5;
        r14 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x011a, code lost:
        r6 = (com.bitcoin.mwallet.core.models.credential.CredentialMnemonic) r14;
        r5 = com.bitcoin.mwallet.core.models.credential.CredentialId.Companion.from(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0123, code lost:
        if (r6 == null) goto L_0x012a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0125, code lost:
        r13 = r6.copyWithId(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x012a, code lost:
        r13 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x012b, code lost:
        r2 = r13;
        r13 = r8.copy(r9, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0130, code lost:
        if (r2 == null) goto L_0x014e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0132, code lost:
        r14 = r10.credentialDao;
        r0.L$0 = r10;
        r0.L$1 = r9;
        r0.L$2 = r8;
        r0.L$3 = r7;
        r0.L$4 = r6;
        r0.L$5 = r5;
        r0.L$6 = r2;
        r0.L$7 = r13;
        r0.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x014b, code lost:
        if (r14.upsert(r2, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0) != r1) goto L_0x0164;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x014d, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x014e, code lost:
        r14 = new java.lang.StringBuilder();
        r14.append("Old credentials not present on userId=");
        r14.append(r9);
        timber.log.Timber.m429e(r14.toString(), new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0164, code lost:
        r14 = r10.dao;
        r4 = new com.bitcoin.mwallet.core.models.user.User[]{r13};
        r0.L$0 = r10;
        r0.L$1 = r9;
        r0.L$2 = r8;
        r0.L$3 = r7;
        r0.L$4 = r6;
        r0.L$5 = r5;
        r0.L$6 = r2;
        r0.L$7 = r13;
        r0.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0181, code lost:
        if (r14.upsert(r4, r0) != r1) goto L_0x0184;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0183, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0184, code lost:
        r3 = r5;
        r4 = r6;
        r5 = r7;
        r6 = r8;
        r7 = r9;
        r8 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x018a, code lost:
        r14 = r8.credentialDao;
        r9 = r5.getId();
        r0.L$0 = r8;
        r0.L$1 = r7;
        r0.L$2 = r6;
        r0.L$3 = r5;
        r0.L$4 = r4;
        r0.L$5 = r3;
        r0.L$6 = r2;
        r0.L$7 = r13;
        r0.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x01a7, code lost:
        if (r14.deleteMnemonic(r9, r0) != r1) goto L_0x01aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x01a9, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x01aa, code lost:
        r14 = r8.dao;
        r9 = r6.getId();
        r0.L$0 = r8;
        r0.L$1 = r7;
        r0.L$2 = r6;
        r0.L$3 = r5;
        r0.L$4 = r4;
        r0.L$5 = r3;
        r0.L$6 = r2;
        r0.L$7 = r13;
        r0.label = 6;
        r14 = r14.removeUser(r9, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x01c7, code lost:
        if (r14 != r1) goto L_0x01ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x01c9, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x01ca, code lost:
        return r14;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object setUserId(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.user.UserId r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r12 = this;
            boolean r0 = r14 instanceof com.bitcoin.mwallet.core.repositories.UserRepository$setUserId$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            com.bitcoin.mwallet.core.repositories.UserRepository$setUserId$1 r0 = (com.bitcoin.mwallet.core.repositories.UserRepository$setUserId$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.repositories.UserRepository$setUserId$1 r0 = new com.bitcoin.mwallet.core.repositories.UserRepository$setUserId$1
            r0.<init>(r12, r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            switch(r2) {
                case 0: goto L_0x00e6;
                case 1: goto L_0x00da;
                case 2: goto L_0x00c2;
                case 3: goto L_0x009d;
                case 4: goto L_0x0078;
                case 5: goto L_0x0053;
                case 6: goto L_0x002e;
                default: goto L_0x0026;
            }
        L_0x0026:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x002e:
            java.lang.Object r13 = r0.L$7
            com.bitcoin.mwallet.core.models.user.User r13 = (com.bitcoin.mwallet.core.models.user.User) r13
            java.lang.Object r13 = r0.L$6
            com.bitcoin.mwallet.core.models.credential.Credential r13 = (com.bitcoin.mwallet.core.models.credential.Credential) r13
            java.lang.Object r13 = r0.L$5
            com.bitcoin.mwallet.core.models.credential.CredentialId r13 = (com.bitcoin.mwallet.core.models.credential.CredentialId) r13
            java.lang.Object r13 = r0.L$4
            com.bitcoin.mwallet.core.models.credential.CredentialMnemonic r13 = (com.bitcoin.mwallet.core.models.credential.CredentialMnemonic) r13
            java.lang.Object r13 = r0.L$3
            com.bitcoin.mwallet.core.models.credential.CredentialId r13 = (com.bitcoin.mwallet.core.models.credential.CredentialId) r13
            java.lang.Object r13 = r0.L$2
            com.bitcoin.mwallet.core.models.user.User r13 = (com.bitcoin.mwallet.core.models.user.User) r13
            java.lang.Object r13 = r0.L$1
            com.bitcoin.mwallet.core.models.user.UserId r13 = (com.bitcoin.mwallet.core.models.user.UserId) r13
            java.lang.Object r13 = r0.L$0
            com.bitcoin.mwallet.core.repositories.UserRepository r13 = (com.bitcoin.mwallet.core.repositories.UserRepository) r13
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x01ca
        L_0x0053:
            java.lang.Object r13 = r0.L$7
            com.bitcoin.mwallet.core.models.user.User r13 = (com.bitcoin.mwallet.core.models.user.User) r13
            java.lang.Object r2 = r0.L$6
            com.bitcoin.mwallet.core.models.credential.Credential r2 = (com.bitcoin.mwallet.core.models.credential.Credential) r2
            java.lang.Object r3 = r0.L$5
            com.bitcoin.mwallet.core.models.credential.CredentialId r3 = (com.bitcoin.mwallet.core.models.credential.CredentialId) r3
            java.lang.Object r4 = r0.L$4
            com.bitcoin.mwallet.core.models.credential.CredentialMnemonic r4 = (com.bitcoin.mwallet.core.models.credential.CredentialMnemonic) r4
            java.lang.Object r5 = r0.L$3
            com.bitcoin.mwallet.core.models.credential.CredentialId r5 = (com.bitcoin.mwallet.core.models.credential.CredentialId) r5
            java.lang.Object r6 = r0.L$2
            com.bitcoin.mwallet.core.models.user.User r6 = (com.bitcoin.mwallet.core.models.user.User) r6
            java.lang.Object r7 = r0.L$1
            com.bitcoin.mwallet.core.models.user.UserId r7 = (com.bitcoin.mwallet.core.models.user.UserId) r7
            java.lang.Object r8 = r0.L$0
            com.bitcoin.mwallet.core.repositories.UserRepository r8 = (com.bitcoin.mwallet.core.repositories.UserRepository) r8
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x01aa
        L_0x0078:
            java.lang.Object r13 = r0.L$7
            com.bitcoin.mwallet.core.models.user.User r13 = (com.bitcoin.mwallet.core.models.user.User) r13
            java.lang.Object r2 = r0.L$6
            com.bitcoin.mwallet.core.models.credential.Credential r2 = (com.bitcoin.mwallet.core.models.credential.Credential) r2
            java.lang.Object r3 = r0.L$5
            com.bitcoin.mwallet.core.models.credential.CredentialId r3 = (com.bitcoin.mwallet.core.models.credential.CredentialId) r3
            java.lang.Object r4 = r0.L$4
            com.bitcoin.mwallet.core.models.credential.CredentialMnemonic r4 = (com.bitcoin.mwallet.core.models.credential.CredentialMnemonic) r4
            java.lang.Object r5 = r0.L$3
            com.bitcoin.mwallet.core.models.credential.CredentialId r5 = (com.bitcoin.mwallet.core.models.credential.CredentialId) r5
            java.lang.Object r6 = r0.L$2
            com.bitcoin.mwallet.core.models.user.User r6 = (com.bitcoin.mwallet.core.models.user.User) r6
            java.lang.Object r7 = r0.L$1
            com.bitcoin.mwallet.core.models.user.UserId r7 = (com.bitcoin.mwallet.core.models.user.UserId) r7
            java.lang.Object r8 = r0.L$0
            com.bitcoin.mwallet.core.repositories.UserRepository r8 = (com.bitcoin.mwallet.core.repositories.UserRepository) r8
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x018a
        L_0x009d:
            java.lang.Object r13 = r0.L$7
            com.bitcoin.mwallet.core.models.user.User r13 = (com.bitcoin.mwallet.core.models.user.User) r13
            java.lang.Object r2 = r0.L$6
            com.bitcoin.mwallet.core.models.credential.Credential r2 = (com.bitcoin.mwallet.core.models.credential.Credential) r2
            java.lang.Object r5 = r0.L$5
            com.bitcoin.mwallet.core.models.credential.CredentialId r5 = (com.bitcoin.mwallet.core.models.credential.CredentialId) r5
            java.lang.Object r6 = r0.L$4
            com.bitcoin.mwallet.core.models.credential.CredentialMnemonic r6 = (com.bitcoin.mwallet.core.models.credential.CredentialMnemonic) r6
            java.lang.Object r7 = r0.L$3
            com.bitcoin.mwallet.core.models.credential.CredentialId r7 = (com.bitcoin.mwallet.core.models.credential.CredentialId) r7
            java.lang.Object r8 = r0.L$2
            com.bitcoin.mwallet.core.models.user.User r8 = (com.bitcoin.mwallet.core.models.user.User) r8
            java.lang.Object r9 = r0.L$1
            com.bitcoin.mwallet.core.models.user.UserId r9 = (com.bitcoin.mwallet.core.models.user.UserId) r9
            java.lang.Object r10 = r0.L$0
            com.bitcoin.mwallet.core.repositories.UserRepository r10 = (com.bitcoin.mwallet.core.repositories.UserRepository) r10
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0164
        L_0x00c2:
            java.lang.Object r13 = r0.L$3
            com.bitcoin.mwallet.core.models.credential.CredentialId r13 = (com.bitcoin.mwallet.core.models.credential.CredentialId) r13
            java.lang.Object r2 = r0.L$2
            com.bitcoin.mwallet.core.models.user.User r2 = (com.bitcoin.mwallet.core.models.user.User) r2
            java.lang.Object r5 = r0.L$1
            com.bitcoin.mwallet.core.models.user.UserId r5 = (com.bitcoin.mwallet.core.models.user.UserId) r5
            java.lang.Object r6 = r0.L$0
            com.bitcoin.mwallet.core.repositories.UserRepository r6 = (com.bitcoin.mwallet.core.repositories.UserRepository) r6
            kotlin.ResultKt.throwOnFailure(r14)
            r7 = r13
            r8 = r2
            r9 = r5
            r10 = r6
            goto L_0x011a
        L_0x00da:
            java.lang.Object r13 = r0.L$1
            com.bitcoin.mwallet.core.models.user.UserId r13 = (com.bitcoin.mwallet.core.models.user.UserId) r13
            java.lang.Object r2 = r0.L$0
            com.bitcoin.mwallet.core.repositories.UserRepository r2 = (com.bitcoin.mwallet.core.repositories.UserRepository) r2
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00f7
        L_0x00e6:
            kotlin.ResultKt.throwOnFailure(r14)
            r0.L$0 = r12
            r0.L$1 = r13
            r0.label = r4
            java.lang.Object r14 = r12.getOrCreateUser(r0)
            if (r14 != r1) goto L_0x00f6
            return r1
        L_0x00f6:
            r2 = r12
        L_0x00f7:
            com.bitcoin.mwallet.core.models.user.User r14 = (com.bitcoin.mwallet.core.models.user.User) r14
            com.bitcoin.mwallet.core.models.credential.CredentialId r5 = r14.getCredentialId()
            com.bitcoin.mwallet.core.dao.CredentialDao r6 = r2.credentialDao
            java.lang.String r7 = r5.getId()
            r0.L$0 = r2
            r0.L$1 = r13
            r0.L$2 = r14
            r0.L$3 = r5
            r8 = 2
            r0.label = r8
            java.lang.Object r6 = r6.selectMnemonic(r7, r0)
            if (r6 != r1) goto L_0x0115
            return r1
        L_0x0115:
            r9 = r13
            r8 = r14
            r10 = r2
            r7 = r5
            r14 = r6
        L_0x011a:
            r6 = r14
            com.bitcoin.mwallet.core.models.credential.CredentialMnemonic r6 = (com.bitcoin.mwallet.core.models.credential.CredentialMnemonic) r6
            com.bitcoin.mwallet.core.models.credential.CredentialId$Companion r13 = com.bitcoin.mwallet.core.models.credential.CredentialId.Companion
            com.bitcoin.mwallet.core.models.credential.CredentialId r5 = r13.from(r9)
            if (r6 == 0) goto L_0x012a
            com.bitcoin.mwallet.core.models.credential.Credential r13 = r6.copyWithId(r5)
            goto L_0x012b
        L_0x012a:
            r13 = 0
        L_0x012b:
            r2 = r13
            com.bitcoin.mwallet.core.models.user.User r13 = r8.copy(r9, r5)
            if (r2 == 0) goto L_0x014e
            com.bitcoin.mwallet.core.dao.CredentialDao r14 = r10.credentialDao
            r0.L$0 = r10
            r0.L$1 = r9
            r0.L$2 = r8
            r0.L$3 = r7
            r0.L$4 = r6
            r0.L$5 = r5
            r0.L$6 = r2
            r0.L$7 = r13
            r11 = 3
            r0.label = r11
            java.lang.Object r14 = r14.upsert(r2, r0)
            if (r14 != r1) goto L_0x0164
            return r1
        L_0x014e:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r11 = "Old credentials not present on userId="
            r14.append(r11)
            r14.append(r9)
            java.lang.String r14 = r14.toString()
            java.lang.Object[] r11 = new java.lang.Object[r3]
            timber.log.Timber.m429e(r14, r11)
        L_0x0164:
            com.bitcoin.mwallet.core.dao.UserDao r14 = r10.dao
            com.bitcoin.mwallet.core.models.user.User[] r4 = new com.bitcoin.mwallet.core.models.user.User[r4]
            r4[r3] = r13
            r0.L$0 = r10
            r0.L$1 = r9
            r0.L$2 = r8
            r0.L$3 = r7
            r0.L$4 = r6
            r0.L$5 = r5
            r0.L$6 = r2
            r0.L$7 = r13
            r3 = 4
            r0.label = r3
            java.lang.Object r14 = r14.upsert(r4, r0)
            if (r14 != r1) goto L_0x0184
            return r1
        L_0x0184:
            r3 = r5
            r4 = r6
            r5 = r7
            r6 = r8
            r7 = r9
            r8 = r10
        L_0x018a:
            com.bitcoin.mwallet.core.dao.CredentialDao r14 = r8.credentialDao
            java.lang.String r9 = r5.getId()
            r0.L$0 = r8
            r0.L$1 = r7
            r0.L$2 = r6
            r0.L$3 = r5
            r0.L$4 = r4
            r0.L$5 = r3
            r0.L$6 = r2
            r0.L$7 = r13
            r10 = 5
            r0.label = r10
            java.lang.Object r14 = r14.deleteMnemonic(r9, r0)
            if (r14 != r1) goto L_0x01aa
            return r1
        L_0x01aa:
            com.bitcoin.mwallet.core.dao.UserDao r14 = r8.dao
            com.bitcoin.mwallet.core.models.user.UserId r9 = r6.getId()
            r0.L$0 = r8
            r0.L$1 = r7
            r0.L$2 = r6
            r0.L$3 = r5
            r0.L$4 = r4
            r0.L$5 = r3
            r0.L$6 = r2
            r0.L$7 = r13
            r13 = 6
            r0.label = r13
            java.lang.Object r14 = r14.removeUser(r9, r0)
            if (r14 != r1) goto L_0x01ca
            return r1
        L_0x01ca:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.UserRepository.setUserId(com.bitcoin.mwallet.core.models.user.UserId, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
