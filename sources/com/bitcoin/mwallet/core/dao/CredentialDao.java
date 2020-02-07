package com.bitcoin.mwallet.core.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.bitcoin.mwallet.core.models.credential.Credential;
import com.bitcoin.mwallet.core.models.credential.CredentialEncrypted;
import com.bitcoin.mwallet.core.models.credential.CredentialId;
import com.bitcoin.mwallet.core.models.credential.CredentialMnemonic;
import com.bitcoin.mwallet.core.models.credential.CredentialType;
import com.bitcoin.mwallet.core.models.credential.CredentialZion;
import com.bitcoin.mwallet.zion.ZionId;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dao
@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u0019\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0019\u0010\f\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0019\u0010\r\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u001f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u001b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\t\u001a\u00020\nH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u001b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\t\u001a\u00020\nH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u001b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\t\u001a\u00020\nH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0019\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0014H@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ%\u0010\u001c\u001a\u00020\u00042\u0012\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u001f\"\u00020\u0017H§@ø\u0001\u0000¢\u0006\u0002\u0010 J%\u0010\u001c\u001a\u00020\u00042\u0012\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00190\u001f\"\u00020\u0019H§@ø\u0001\u0000¢\u0006\u0002\u0010!J%\u0010\u001c\u001a\u00020\u00042\u0012\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001b0\u001f\"\u00020\u001bH§@ø\u0001\u0000¢\u0006\u0002\u0010\"\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/dao/CredentialDao;", "", "()V", "delete", "", "credentialId", "Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "(Lcom/bitcoin/mwallet/core/models/credential/CredentialId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteEncrypted", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteMnemonic", "deleteZion", "findIds", "", "zionId", "Lcom/bitcoin/mwallet/zion/ZionId;", "(Lcom/bitcoin/mwallet/zion/ZionId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "select", "Lcom/bitcoin/mwallet/core/models/credential/Credential;", "credential", "selectEncrypted", "Lcom/bitcoin/mwallet/core/models/credential/CredentialEncrypted;", "selectMnemonic", "Lcom/bitcoin/mwallet/core/models/credential/CredentialMnemonic;", "selectZion", "Lcom/bitcoin/mwallet/core/models/credential/CredentialZion;", "upsert", "(Lcom/bitcoin/mwallet/core/models/credential/Credential;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "values", "", "([Lcom/bitcoin/mwallet/core/models/credential/CredentialEncrypted;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "([Lcom/bitcoin/mwallet/core/models/credential/CredentialMnemonic;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "([Lcom/bitcoin/mwallet/core/models/credential/CredentialZion;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CredentialDao.kt */
public abstract class CredentialDao {

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[CredentialType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[CredentialType.values().length];

        static {
            $EnumSwitchMapping$0[CredentialType.MNEMONIC_AND_PROTECTED.ordinal()] = 1;
            $EnumSwitchMapping$0[CredentialType.MNEMONIC_AND_PRIVATE_KEY.ordinal()] = 2;
            $EnumSwitchMapping$0[CredentialType.ZION_VAULT.ordinal()] = 3;
            $EnumSwitchMapping$0[CredentialType.ENCRYPTED_MNEMONIC.ordinal()] = 4;
            $EnumSwitchMapping$1[CredentialType.MNEMONIC_AND_PROTECTED.ordinal()] = 1;
            $EnumSwitchMapping$1[CredentialType.MNEMONIC_AND_PRIVATE_KEY.ordinal()] = 2;
            $EnumSwitchMapping$1[CredentialType.ZION_VAULT.ordinal()] = 3;
            $EnumSwitchMapping$1[CredentialType.ENCRYPTED_MNEMONIC.ordinal()] = 4;
        }
    }

    @Nullable
    public Object delete(@NotNull CredentialId credentialId, @NotNull Continuation<? super Unit> continuation) {
        return delete$suspendImpl(this, credentialId, continuation);
    }

    @Nullable
    @Query("DELETE FROM credential_encrypted WHERE id_id = :id")
    public abstract Object deleteEncrypted(@NotNull String str, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    @Query("DELETE FROM credential_mnemonic WHERE id_id = :id")
    public abstract Object deleteMnemonic(@NotNull String str, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    @Query("DELETE FROM credential_zion WHERE id_id = :id")
    public abstract Object deleteZion(@NotNull String str, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    @Query("SELECT id_id FROM credential_zion WHERE zion_id = :zionId")
    public abstract Object findIds(@NotNull ZionId zionId, @NotNull Continuation<? super List<String>> continuation);

    @Nullable
    public Object select(@NotNull CredentialId credentialId, @NotNull Continuation<? super Credential> continuation) {
        return select$suspendImpl(this, credentialId, continuation);
    }

    @Nullable
    @Query("SELECT * From credential_encrypted WHERE id_id = :id ")
    public abstract Object selectEncrypted(@NotNull String str, @NotNull Continuation<? super CredentialEncrypted> continuation);

    @Nullable
    @Query("SELECT * FROM credential_mnemonic WHERE id_id = :id")
    public abstract Object selectMnemonic(@NotNull String str, @NotNull Continuation<? super CredentialMnemonic> continuation);

    @Nullable
    @Query("SELECT * FROM credential_zion WHERE id_id = :id")
    public abstract Object selectZion(@NotNull String str, @NotNull Continuation<? super CredentialZion> continuation);

    @Nullable
    public Object upsert(@NotNull Credential credential, @NotNull Continuation<? super Unit> continuation) {
        return upsert$suspendImpl(this, credential, continuation);
    }

    @Nullable
    @Insert(onConflict = 1)
    public abstract Object upsert(@NotNull CredentialEncrypted[] credentialEncryptedArr, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    @Insert(onConflict = 1)
    public abstract Object upsert(@NotNull CredentialMnemonic[] credentialMnemonicArr, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    @Insert(onConflict = 1)
    public abstract Object upsert(@NotNull CredentialZion[] credentialZionArr, @NotNull Continuation<? super Unit> continuation);

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object select$suspendImpl(com.bitcoin.mwallet.core.dao.CredentialDao r7, com.bitcoin.mwallet.core.models.credential.CredentialId r8, kotlin.coroutines.Continuation r9) {
        /*
            boolean r0 = r9 instanceof com.bitcoin.mwallet.core.dao.CredentialDao$select$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            com.bitcoin.mwallet.core.dao.CredentialDao$select$1 r0 = (com.bitcoin.mwallet.core.dao.CredentialDao$select$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.dao.CredentialDao$select$1 r0 = new com.bitcoin.mwallet.core.dao.CredentialDao$select$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L_0x0044
            if (r2 == r6) goto L_0x0038
            if (r2 == r5) goto L_0x0038
            if (r2 == r4) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            goto L_0x0038
        L_0x0030:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0038:
            java.lang.Object r7 = r0.L$1
            com.bitcoin.mwallet.core.models.credential.CredentialId r7 = (com.bitcoin.mwallet.core.models.credential.CredentialId) r7
            java.lang.Object r7 = r0.L$0
            com.bitcoin.mwallet.core.dao.CredentialDao r7 = (com.bitcoin.mwallet.core.dao.CredentialDao) r7
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00a5
        L_0x0044:
            kotlin.ResultKt.throwOnFailure(r9)
            com.bitcoin.mwallet.core.models.credential.CredentialType r9 = r8.getType()
            int[] r2 = com.bitcoin.mwallet.core.dao.CredentialDao.WhenMappings.$EnumSwitchMapping$0
            int r9 = r9.ordinal()
            r9 = r2[r9]
            if (r9 == r6) goto L_0x0094
            if (r9 == r5) goto L_0x0083
            if (r9 == r4) goto L_0x0072
            if (r9 != r3) goto L_0x006c
            java.lang.String r9 = r8.getId()
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r3
            java.lang.Object r9 = r7.selectEncrypted(r9, r0)
            if (r9 != r1) goto L_0x00a5
            return r1
        L_0x006c:
            kotlin.NoWhenBranchMatchedException r7 = new kotlin.NoWhenBranchMatchedException
            r7.<init>()
            throw r7
        L_0x0072:
            java.lang.String r9 = r8.getId()
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r4
            java.lang.Object r9 = r7.selectZion(r9, r0)
            if (r9 != r1) goto L_0x00a5
            return r1
        L_0x0083:
            java.lang.String r9 = r8.getId()
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r5
            java.lang.Object r9 = r7.selectMnemonic(r9, r0)
            if (r9 != r1) goto L_0x00a5
            return r1
        L_0x0094:
            java.lang.String r9 = r8.getId()
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r6
            java.lang.Object r9 = r7.selectMnemonic(r9, r0)
            if (r9 != r1) goto L_0x00a5
            return r1
        L_0x00a5:
            com.bitcoin.mwallet.core.models.credential.Credential r9 = (com.bitcoin.mwallet.core.models.credential.Credential) r9
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.dao.CredentialDao.select$suspendImpl(com.bitcoin.mwallet.core.dao.CredentialDao, com.bitcoin.mwallet.core.models.credential.CredentialId, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object delete$suspendImpl(CredentialDao credentialDao, CredentialId credentialId, Continuation continuation) {
        int i = WhenMappings.$EnumSwitchMapping$1[credentialId.getType().ordinal()];
        if (i == 1) {
            return credentialDao.deleteMnemonic(credentialId.getId(), continuation);
        }
        if (i == 2) {
            return credentialDao.deleteMnemonic(credentialId.getId(), continuation);
        }
        if (i == 3) {
            return credentialDao.deleteZion(credentialId.getId(), continuation);
        }
        if (i != 4) {
            return Unit.INSTANCE;
        }
        return credentialDao.deleteEncrypted(credentialId.getId(), continuation);
    }

    static /* synthetic */ Object upsert$suspendImpl(CredentialDao credentialDao, Credential credential, Continuation continuation) {
        if (credential instanceof CredentialMnemonic) {
            return credentialDao.upsert(new CredentialMnemonic[]{(CredentialMnemonic) credential}, continuation);
        } else if (credential instanceof CredentialEncrypted) {
            return credentialDao.upsert(new CredentialEncrypted[]{(CredentialEncrypted) credential}, continuation);
        } else if (!(credential instanceof CredentialZion)) {
            return Unit.INSTANCE;
        } else {
            return credentialDao.upsert(new CredentialZion[]{(CredentialZion) credential}, continuation);
        }
    }
}
