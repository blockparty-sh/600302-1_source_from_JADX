package com.bitcoin.mwallet.core.repositories;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.bitcoin.bitcoink.ExtendedPublicKey;
import com.bitcoin.bitcoink.Network;
import com.bitcoin.mwallet.core.dao.CredentialDao;
import com.bitcoin.mwallet.core.dao.WalletAddressInfoDao;
import com.bitcoin.mwallet.core.dao.WalletDao;
import com.bitcoin.mwallet.core.dao.room.WalletDatabase;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.CopayerId;
import com.bitcoin.mwallet.core.models.address.AddressAndPath;
import com.bitcoin.mwallet.core.models.address.AddressPurpose;
import com.bitcoin.mwallet.core.models.address.WalletAddressInfo.PathY;
import com.bitcoin.mwallet.core.models.credential.Credential;
import com.bitcoin.mwallet.core.models.credential.CredentialId;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoSatoshis;
import com.bitcoin.mwallet.core.preferences.ApplicationPreferences;
import com.bitcoin.mwallet.core.services.address.AddressService;
import com.bitcoin.mwallet.core.services.notification.NotificationService;
import com.bitcoin.mwallet.core.services.wallet.WalletService;
import com.bitcoin.mwallet.core.utils.signature.SigningKey;
import com.bitcoin.mwallet.zion.ZionId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000Ì\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0002^_BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J!\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H@ø\u0001\u0000¢\u0006\u0002\u0010&J\u0019\u0010'\u001a\u00020!2\u0006\u0010(\u001a\u00020)H@ø\u0001\u0000¢\u0006\u0002\u0010*J!\u0010'\u001a\u00020!2\u0006\u0010(\u001a\u00020)2\u0006\u0010+\u001a\u00020,H@ø\u0001\u0000¢\u0006\u0002\u0010-J\u001b\u0010.\u001a\u0004\u0018\u00010/2\u0006\u0010+\u001a\u00020,H@ø\u0001\u0000¢\u0006\u0002\u00100J\u001b\u0010.\u001a\u0004\u0018\u00010/2\u0006\u0010(\u001a\u00020)H@ø\u0001\u0000¢\u0006\u0002\u0010*J\u001b\u00101\u001a\u0004\u0018\u00010,2\u0006\u0010(\u001a\u00020)H@ø\u0001\u0000¢\u0006\u0002\u0010*J)\u00102\u001a\u0002032\u0006\u0010(\u001a\u00020)2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H@ø\u0001\u0000¢\u0006\u0002\u00108J+\u00109\u001a\u0004\u0018\u0001032\u0006\u0010(\u001a\u00020)2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H@ø\u0001\u0000¢\u0006\u0002\u00108J\u001b\u0010:\u001a\u0004\u0018\u00010\u001e2\u0006\u0010(\u001a\u00020)H@ø\u0001\u0000¢\u0006\u0002\u0010*J\u001b\u0010;\u001a\u0004\u0018\u00010\u001e2\u0006\u0010<\u001a\u00020)H@ø\u0001\u0000¢\u0006\u0002\u0010*J\u001b\u0010=\u001a\u0004\u0018\u00010#2\u0006\u0010(\u001a\u00020)H@ø\u0001\u0000¢\u0006\u0002\u0010*J\u0017\u0010>\u001a\b\u0012\u0004\u0012\u00020)0\u0019H@ø\u0001\u0000¢\u0006\u0002\u0010?J\u001f\u0010>\u001a\b\u0012\u0004\u0012\u00020)0\u00192\u0006\u0010\"\u001a\u00020#H@ø\u0001\u0000¢\u0006\u0002\u0010@J\u0017\u0010A\u001a\b\u0012\u0004\u0012\u00020)0\u0019H@ø\u0001\u0000¢\u0006\u0002\u0010?J\u001f\u0010A\u001a\b\u0012\u0004\u0012\u00020)0\u00192\u0006\u0010\"\u001a\u00020#H@ø\u0001\u0000¢\u0006\u0002\u0010@J\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0019H@ø\u0001\u0000¢\u0006\u0002\u0010?J!\u0010B\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u00192\u0006\u0010(\u001a\u00020)H@ø\u0001\u0000¢\u0006\u0002\u0010*J\u001f\u0010C\u001a\b\u0012\u0004\u0012\u00020)0D2\u0006\u0010E\u001a\u00020FH@ø\u0001\u0000¢\u0006\u0002\u0010GJ!\u0010H\u001a\u00020!2\u0006\u0010(\u001a\u00020)2\u0006\u0010I\u001a\u00020%H@ø\u0001\u0000¢\u0006\u0002\u0010JJ+\u0010K\u001a\u00020!2\u0006\u0010L\u001a\u00020\u001e2\u0006\u0010M\u001a\u00020/2\b\b\u0002\u0010N\u001a\u00020OH@ø\u0001\u0000¢\u0006\u0002\u0010PJ)\u0010Q\u001a\u00020R2\u0006\u0010L\u001a\u00020\u001e2\u0006\u0010M\u001a\u00020/2\u0006\u0010N\u001a\u00020OH@ø\u0001\u0000¢\u0006\u0002\u0010PJ!\u0010Q\u001a\u00020R2\u0006\u0010(\u001a\u00020)2\u0006\u0010N\u001a\u00020OH@ø\u0001\u0000¢\u0006\u0002\u0010SJ \u0010T\u001a\u00020!2\u0006\u0010(\u001a\u00020)2\u0006\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020XH\u0002J\u0014\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00182\u0006\u0010(\u001a\u00020)J\u0014\u0010Z\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00182\u0006\u0010[\u001a\u00020)J\u001f\u0010\\\u001a\b\u0012\u0004\u0012\u00020)0\u00192\u0006\u0010\"\u001a\u00020#H@ø\u0001\u0000¢\u0006\u0002\u0010@J\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u00182\u0006\u0010\"\u001a\u00020#J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00182\u0006\u0010(\u001a\u00020)J\u001f\u0010]\u001a\b\u0012\u0004\u0012\u00020)0\u00192\u0006\u0010\"\u001a\u00020#H@ø\u0001\u0000¢\u0006\u0002\u0010@R\u0012\u0010\u0015\u001a\u00060\u0016R\u00020\u0000X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00190\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001c\u0002\u0004\n\u0002\b\u0019¨\u0006`"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "", "application", "Landroid/app/Application;", "database", "Lcom/bitcoin/mwallet/core/dao/room/WalletDatabase;", "credentialDao", "Lcom/bitcoin/mwallet/core/dao/CredentialDao;", "walletAddressInfoDao", "Lcom/bitcoin/mwallet/core/dao/WalletAddressInfoDao;", "walletDao", "Lcom/bitcoin/mwallet/core/dao/WalletDao;", "walletService", "Lcom/bitcoin/mwallet/core/services/wallet/WalletService;", "addressService", "Lcom/bitcoin/mwallet/core/services/address/AddressService;", "notificationService", "Lcom/bitcoin/mwallet/core/services/notification/NotificationService;", "applicationPreferences", "Lcom/bitcoin/mwallet/core/preferences/ApplicationPreferences;", "(Landroid/app/Application;Lcom/bitcoin/mwallet/core/dao/room/WalletDatabase;Lcom/bitcoin/mwallet/core/dao/CredentialDao;Lcom/bitcoin/mwallet/core/dao/WalletAddressInfoDao;Lcom/bitcoin/mwallet/core/dao/WalletDao;Lcom/bitcoin/mwallet/core/services/wallet/WalletService;Lcom/bitcoin/mwallet/core/services/address/AddressService;Lcom/bitcoin/mwallet/core/services/notification/NotificationService;Lcom/bitcoin/mwallet/core/preferences/ApplicationPreferences;)V", "addressHandler", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository$AddressHandler;", "walletInfoSatoshis", "Landroidx/lifecycle/LiveData;", "", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoSatoshis;", "getWalletInfoSatoshis", "()Landroidx/lifecycle/LiveData;", "wallets", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "getWallets", "createAndSaveNewWallet", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "walletName", "", "(Lcom/bitcoin/mwallet/core/models/Coin;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteWallet", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "credentialId", "Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/credential/CredentialId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCredential", "Lcom/bitcoin/mwallet/core/models/credential/Credential;", "(Lcom/bitcoin/mwallet/core/models/credential/CredentialId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCredentialId", "getCurrentAddress", "Lcom/bitcoin/mwallet/core/models/address/AddressAndPath;", "network", "Lcom/bitcoin/bitcoink/Network;", "purpose", "Lcom/bitcoin/mwallet/core/models/address/AddressPurpose;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/bitcoink/Network;Lcom/bitcoin/mwallet/core/models/address/AddressPurpose;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNextAddress", "getWalletById", "getWalletByPk", "walletPK", "getWalletCoin", "getWalletIds", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lcom/bitcoin/mwallet/core/models/Coin;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWalletPks", "getWalletsById", "getWalletsIds", "", "zionId", "Lcom/bitcoin/mwallet/zion/ZionId;", "(Lcom/bitcoin/mwallet/zion/ZionId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "renameWallet", "newWalletName", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveNewWallet", "wallet", "credential", "saveType", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository$WalletSaveType;", "(Lcom/bitcoin/mwallet/core/models/wallet/Wallet;Lcom/bitcoin/mwallet/core/models/credential/Credential;Lcom/bitcoin/mwallet/core/repositories/WalletRepository$WalletSaveType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveNewWalletRemote", "", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/repositories/WalletRepository$WalletSaveType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "subscribeToNotifications", "copayerId", "Lcom/bitcoin/mwallet/core/models/CopayerId;", "signingKey", "Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "walletById", "walletByPk", "walletPk", "walletIdsByCoin", "walletPksByCoin", "AddressHandler", "WalletSaveType", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletRepository.kt */
public final class WalletRepository {
    private final AddressHandler addressHandler = new AddressHandler();
    /* access modifiers changed from: private */
    public final AddressService addressService;
    private final Application application;
    private final ApplicationPreferences applicationPreferences;
    /* access modifiers changed from: private */
    public final CredentialDao credentialDao;
    /* access modifiers changed from: private */
    public final WalletDatabase database;
    /* access modifiers changed from: private */
    public final NotificationService notificationService;
    /* access modifiers changed from: private */
    public final WalletAddressInfoDao walletAddressInfoDao;
    /* access modifiers changed from: private */
    public final WalletDao walletDao;
    @NotNull
    private final LiveData<List<WalletInfoSatoshis>> walletInfoSatoshis = this.walletDao.selectWalletInfoSatoshis();
    private final WalletService walletService;
    @NotNull
    private final LiveData<List<C1261Wallet>> wallets = this.walletDao.allWallets();

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J)\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J+\u0010\u0011\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0019\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J0\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/repositories/WalletRepository$AddressHandler;", "", "(Lcom/bitcoin/mwallet/core/repositories/WalletRepository;)V", "publicKeyCache", "", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "Lcom/bitcoin/bitcoink/ExtendedPublicKey;", "requestMoreAddressesJob", "Lkotlinx/coroutines/Job;", "getCurrentAddress", "Lcom/bitcoin/mwallet/core/models/address/AddressAndPath;", "walletId", "network", "Lcom/bitcoin/bitcoink/Network;", "purpose", "Lcom/bitcoin/mwallet/core/models/address/AddressPurpose;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/bitcoink/Network;Lcom/bitcoin/mwallet/core/models/address/AddressPurpose;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNextAddress", "getPublicKey", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestMoreAddressesAsync", "", "copayerId", "Lcom/bitcoin/mwallet/core/models/CopayerId;", "signingKey", "Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "pathYCurrent", "Lcom/bitcoin/mwallet/core/models/address/WalletAddressInfo$PathY;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: WalletRepository.kt */
    public final class AddressHandler {
        private final Map<WalletId, ExtendedPublicKey> publicKeyCache = new HashMap();
        private Job requestMoreAddressesJob;

        public AddressHandler() {
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x0040  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
        @org.jetbrains.annotations.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final /* synthetic */ java.lang.Object getPublicKey(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.bitcoink.ExtendedPublicKey> r7) {
            /*
                r5 = this;
                boolean r0 = r7 instanceof com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler$getPublicKey$1
                if (r0 == 0) goto L_0x0014
                r0 = r7
                com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler$getPublicKey$1 r0 = (com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler$getPublicKey$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r7 = r0.label
                int r7 = r7 - r2
                r0.label = r7
                goto L_0x0019
            L_0x0014:
                com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler$getPublicKey$1 r0 = new com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler$getPublicKey$1
                r0.<init>(r5, r7)
            L_0x0019:
                java.lang.Object r7 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L_0x0040
                if (r2 != r3) goto L_0x0038
                java.lang.Object r6 = r0.L$3
                java.lang.Object r6 = r0.L$2
                java.util.Map r6 = (java.util.Map) r6
                java.lang.Object r1 = r0.L$1
                com.bitcoin.mwallet.core.models.wallet.WalletId r1 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r1
                java.lang.Object r0 = r0.L$0
                com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler r0 = (com.bitcoin.mwallet.core.repositories.WalletRepository.AddressHandler) r0
                kotlin.ResultKt.throwOnFailure(r7)
                goto L_0x0065
            L_0x0038:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L_0x0040:
                kotlin.ResultKt.throwOnFailure(r7)
                java.util.Map<com.bitcoin.mwallet.core.models.wallet.WalletId, com.bitcoin.bitcoink.ExtendedPublicKey> r7 = r5.publicKeyCache
                java.lang.Object r2 = r7.get(r6)
                if (r2 != 0) goto L_0x006b
                com.bitcoin.mwallet.core.repositories.WalletRepository r4 = com.bitcoin.mwallet.core.repositories.WalletRepository.this
                com.bitcoin.mwallet.core.dao.WalletDao r4 = r4.walletDao
                r0.L$0 = r5
                r0.L$1 = r6
                r0.L$2 = r7
                r0.L$3 = r2
                r0.label = r3
                java.lang.Object r0 = r4.getPublicKey(r6, r0)
                if (r0 != r1) goto L_0x0062
                return r1
            L_0x0062:
                r1 = r6
                r6 = r7
                r7 = r0
            L_0x0065:
                r2 = r7
                com.bitcoin.bitcoink.ExtendedPublicKey r2 = (com.bitcoin.bitcoink.ExtendedPublicKey) r2
                r6.put(r1, r2)
            L_0x006b:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.WalletRepository.AddressHandler.getPublicKey(com.bitcoin.mwallet.core.models.wallet.WalletId, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x0062  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x0094 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
        @org.jetbrains.annotations.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object getCurrentAddress(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r7, @org.jetbrains.annotations.NotNull com.bitcoin.bitcoink.Network r8, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.address.AddressPurpose r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.models.address.AddressAndPath> r10) {
            /*
                r6 = this;
                boolean r0 = r10 instanceof com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler$getCurrentAddress$1
                if (r0 == 0) goto L_0x0014
                r0 = r10
                com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler$getCurrentAddress$1 r0 = (com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler$getCurrentAddress$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r10 = r0.label
                int r10 = r10 - r2
                r0.label = r10
                goto L_0x0019
            L_0x0014:
                com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler$getCurrentAddress$1 r0 = new com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler$getCurrentAddress$1
                r0.<init>(r6, r10)
            L_0x0019:
                java.lang.Object r10 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L_0x0062
                if (r2 == r4) goto L_0x004c
                if (r2 != r3) goto L_0x0044
                java.lang.Object r7 = r0.L$4
                com.bitcoin.mwallet.core.models.address.WalletAddressInfo$PathY r7 = (com.bitcoin.mwallet.core.models.address.WalletAddressInfo.PathY) r7
                java.lang.Object r8 = r0.L$3
                com.bitcoin.mwallet.core.models.address.AddressPurpose r8 = (com.bitcoin.mwallet.core.models.address.AddressPurpose) r8
                java.lang.Object r9 = r0.L$2
                com.bitcoin.bitcoink.Network r9 = (com.bitcoin.bitcoink.Network) r9
                java.lang.Object r1 = r0.L$1
                com.bitcoin.mwallet.core.models.wallet.WalletId r1 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r1
                java.lang.Object r0 = r0.L$0
                com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler r0 = (com.bitcoin.mwallet.core.repositories.WalletRepository.AddressHandler) r0
                kotlin.ResultKt.throwOnFailure(r10)
                r5 = r10
                r10 = r7
                r7 = r5
                goto L_0x0095
            L_0x0044:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r8)
                throw r7
            L_0x004c:
                java.lang.Object r7 = r0.L$3
                r9 = r7
                com.bitcoin.mwallet.core.models.address.AddressPurpose r9 = (com.bitcoin.mwallet.core.models.address.AddressPurpose) r9
                java.lang.Object r7 = r0.L$2
                r8 = r7
                com.bitcoin.bitcoink.Network r8 = (com.bitcoin.bitcoink.Network) r8
                java.lang.Object r7 = r0.L$1
                com.bitcoin.mwallet.core.models.wallet.WalletId r7 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r7
                java.lang.Object r2 = r0.L$0
                com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler r2 = (com.bitcoin.mwallet.core.repositories.WalletRepository.AddressHandler) r2
                kotlin.ResultKt.throwOnFailure(r10)
                goto L_0x007d
            L_0x0062:
                kotlin.ResultKt.throwOnFailure(r10)
                com.bitcoin.mwallet.core.repositories.WalletRepository r10 = com.bitcoin.mwallet.core.repositories.WalletRepository.this
                com.bitcoin.mwallet.core.dao.WalletAddressInfoDao r10 = r10.walletAddressInfoDao
                r0.L$0 = r6
                r0.L$1 = r7
                r0.L$2 = r8
                r0.L$3 = r9
                r0.label = r4
                java.lang.Object r10 = r10.getOrCreatePathY(r7, r9, r0)
                if (r10 != r1) goto L_0x007c
                return r1
            L_0x007c:
                r2 = r6
            L_0x007d:
                r5 = r9
                r9 = r8
                r8 = r5
                com.bitcoin.mwallet.core.models.address.WalletAddressInfo$PathY r10 = (com.bitcoin.mwallet.core.models.address.WalletAddressInfo.PathY) r10
                r0.L$0 = r2
                r0.L$1 = r7
                r0.L$2 = r9
                r0.L$3 = r8
                r0.L$4 = r10
                r0.label = r3
                java.lang.Object r7 = r2.getPublicKey(r7, r0)
                if (r7 != r1) goto L_0x0095
                return r1
            L_0x0095:
                com.bitcoin.bitcoink.ExtendedPublicKey r7 = (com.bitcoin.bitcoink.ExtendedPublicKey) r7
                com.bitcoin.bitcoink.DerivationPath$Companion r0 = com.bitcoin.bitcoink.DerivationPath.Companion
                int r8 = r8.getPathX()
                int r10 = r10.getNextOrMax()
                com.bitcoin.bitcoink.DerivationPath r8 = r0.addressPath(r8, r10)
                com.bitcoin.bitcoink.address.Address r7 = r7.deriveChild(r9, r8)
                com.bitcoin.mwallet.core.models.address.AddressAndPath r9 = new com.bitcoin.mwallet.core.models.address.AddressAndPath
                r9.<init>(r7, r8)
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.WalletRepository.AddressHandler.getCurrentAddress(com.bitcoin.mwallet.core.models.wallet.WalletId, com.bitcoin.bitcoink.Network, com.bitcoin.mwallet.core.models.address.AddressPurpose, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARNING: Removed duplicated region for block: B:10:0x0026  */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x0063  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0087  */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x00ad  */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x00e2  */
        @org.jetbrains.annotations.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final synchronized java.lang.Object getNextAddress(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r12, @org.jetbrains.annotations.NotNull com.bitcoin.bitcoink.Network r13, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.address.AddressPurpose r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.models.address.AddressAndPath> r15) {
            /*
                r11 = this;
                monitor-enter(r11)
                boolean r0 = r15 instanceof com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler$getNextAddress$1     // Catch:{ all -> 0x00e5 }
                if (r0 == 0) goto L_0x0015
                r0 = r15
                com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler$getNextAddress$1 r0 = (com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler$getNextAddress$1) r0     // Catch:{ all -> 0x00e5 }
                int r1 = r0.label     // Catch:{ all -> 0x00e5 }
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0015
                int r15 = r0.label     // Catch:{ all -> 0x00e5 }
                int r15 = r15 - r2
                r0.label = r15     // Catch:{ all -> 0x00e5 }
                goto L_0x001a
            L_0x0015:
                com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler$getNextAddress$1 r0 = new com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler$getNextAddress$1     // Catch:{ all -> 0x00e5 }
                r0.<init>(r11, r15)     // Catch:{ all -> 0x00e5 }
            L_0x001a:
                java.lang.Object r15 = r0.result     // Catch:{ all -> 0x00e5 }
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()     // Catch:{ all -> 0x00e5 }
                int r2 = r0.label     // Catch:{ all -> 0x00e5 }
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L_0x0063
                if (r2 == r4) goto L_0x004d
                if (r2 != r3) goto L_0x0045
                int r12 = r0.I$0     // Catch:{ all -> 0x00e5 }
                java.lang.Object r13 = r0.L$4     // Catch:{ all -> 0x00e5 }
                com.bitcoin.mwallet.core.models.address.WalletAddressInfo$PathY r13 = (com.bitcoin.mwallet.core.models.address.WalletAddressInfo.PathY) r13     // Catch:{ all -> 0x00e5 }
                java.lang.Object r13 = r0.L$3     // Catch:{ all -> 0x00e5 }
                com.bitcoin.mwallet.core.models.address.AddressPurpose r13 = (com.bitcoin.mwallet.core.models.address.AddressPurpose) r13     // Catch:{ all -> 0x00e5 }
                java.lang.Object r14 = r0.L$2     // Catch:{ all -> 0x00e5 }
                com.bitcoin.bitcoink.Network r14 = (com.bitcoin.bitcoink.Network) r14     // Catch:{ all -> 0x00e5 }
                java.lang.Object r1 = r0.L$1     // Catch:{ all -> 0x00e5 }
                com.bitcoin.mwallet.core.models.wallet.WalletId r1 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r1     // Catch:{ all -> 0x00e5 }
                java.lang.Object r0 = r0.L$0     // Catch:{ all -> 0x00e5 }
                com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler r0 = (com.bitcoin.mwallet.core.repositories.WalletRepository.AddressHandler) r0     // Catch:{ all -> 0x00e5 }
                kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ all -> 0x00e5 }
                goto L_0x00cb
            L_0x0045:
                java.lang.IllegalStateException r12 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00e5 }
                java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
                r12.<init>(r13)     // Catch:{ all -> 0x00e5 }
                throw r12     // Catch:{ all -> 0x00e5 }
            L_0x004d:
                java.lang.Object r12 = r0.L$3     // Catch:{ all -> 0x00e5 }
                r14 = r12
                com.bitcoin.mwallet.core.models.address.AddressPurpose r14 = (com.bitcoin.mwallet.core.models.address.AddressPurpose) r14     // Catch:{ all -> 0x00e5 }
                java.lang.Object r12 = r0.L$2     // Catch:{ all -> 0x00e5 }
                r13 = r12
                com.bitcoin.bitcoink.Network r13 = (com.bitcoin.bitcoink.Network) r13     // Catch:{ all -> 0x00e5 }
                java.lang.Object r12 = r0.L$1     // Catch:{ all -> 0x00e5 }
                com.bitcoin.mwallet.core.models.wallet.WalletId r12 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r12     // Catch:{ all -> 0x00e5 }
                java.lang.Object r2 = r0.L$0     // Catch:{ all -> 0x00e5 }
                com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler r2 = (com.bitcoin.mwallet.core.repositories.WalletRepository.AddressHandler) r2     // Catch:{ all -> 0x00e5 }
                kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ all -> 0x00e5 }
                goto L_0x007f
            L_0x0063:
                kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ all -> 0x00e5 }
                com.bitcoin.mwallet.core.repositories.WalletRepository r15 = com.bitcoin.mwallet.core.repositories.WalletRepository.this     // Catch:{ all -> 0x00e5 }
                com.bitcoin.mwallet.core.dao.WalletAddressInfoDao r15 = r15.walletAddressInfoDao     // Catch:{ all -> 0x00e5 }
                r0.L$0 = r11     // Catch:{ all -> 0x00e5 }
                r0.L$1 = r12     // Catch:{ all -> 0x00e5 }
                r0.L$2 = r13     // Catch:{ all -> 0x00e5 }
                r0.L$3 = r14     // Catch:{ all -> 0x00e5 }
                r0.label = r4     // Catch:{ all -> 0x00e5 }
                java.lang.Object r15 = r15.incrementAndGetPathY(r12, r14, r0)     // Catch:{ all -> 0x00e5 }
                if (r15 != r1) goto L_0x007e
                monitor-exit(r11)
                return r1
            L_0x007e:
                r2 = r11
            L_0x007f:
                com.bitcoin.mwallet.core.models.address.WalletAddressInfo$PathY r15 = (com.bitcoin.mwallet.core.models.address.WalletAddressInfo.PathY) r15     // Catch:{ all -> 0x00e5 }
                boolean r4 = r15.needMore()     // Catch:{ all -> 0x00e5 }
                if (r4 == 0) goto L_0x00a7
                com.bitcoin.mwallet.core.repositories.WalletRepository r4 = com.bitcoin.mwallet.core.repositories.WalletRepository.this     // Catch:{ all -> 0x00e5 }
                com.bitcoin.mwallet.core.dao.WalletDao r4 = r4.walletDao     // Catch:{ all -> 0x00e5 }
                com.bitcoin.mwallet.core.models.wallet.Wallet r4 = r4.getWalletById(r12)     // Catch:{ all -> 0x00e5 }
                com.bitcoin.mwallet.core.models.wallet.WalletId r5 = r4.getId()     // Catch:{ all -> 0x00e5 }
                com.bitcoin.mwallet.core.models.Copayers r6 = r4.getCopayers()     // Catch:{ all -> 0x00e5 }
                com.bitcoin.mwallet.core.models.CopayerId r6 = r6.getWalletCopayerId()     // Catch:{ all -> 0x00e5 }
                com.bitcoin.mwallet.core.utils.signature.SigningKey r7 = r4.getSigningKey()     // Catch:{ all -> 0x00e5 }
                r4 = r2
                r8 = r14
                r9 = r15
                r4.requestMoreAddressesAsync(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00e5 }
            L_0x00a7:
                java.lang.Integer r4 = r15.getNext()     // Catch:{ all -> 0x00e5 }
                if (r4 == 0) goto L_0x00e2
                int r4 = r4.intValue()     // Catch:{ all -> 0x00e5 }
                r0.L$0 = r2     // Catch:{ all -> 0x00e5 }
                r0.L$1 = r12     // Catch:{ all -> 0x00e5 }
                r0.L$2 = r13     // Catch:{ all -> 0x00e5 }
                r0.L$3 = r14     // Catch:{ all -> 0x00e5 }
                r0.L$4 = r15     // Catch:{ all -> 0x00e5 }
                r0.I$0 = r4     // Catch:{ all -> 0x00e5 }
                r0.label = r3     // Catch:{ all -> 0x00e5 }
                java.lang.Object r15 = r2.getPublicKey(r12, r0)     // Catch:{ all -> 0x00e5 }
                if (r15 != r1) goto L_0x00c7
                monitor-exit(r11)
                return r1
            L_0x00c7:
                r12 = r4
                r10 = r14
                r14 = r13
                r13 = r10
            L_0x00cb:
                com.bitcoin.bitcoink.ExtendedPublicKey r15 = (com.bitcoin.bitcoink.ExtendedPublicKey) r15     // Catch:{ all -> 0x00e5 }
                com.bitcoin.bitcoink.DerivationPath$Companion r0 = com.bitcoin.bitcoink.DerivationPath.Companion     // Catch:{ all -> 0x00e5 }
                int r13 = r13.getPathX()     // Catch:{ all -> 0x00e5 }
                com.bitcoin.bitcoink.DerivationPath r12 = r0.addressPath(r13, r12)     // Catch:{ all -> 0x00e5 }
                com.bitcoin.bitcoink.address.Address r13 = r15.deriveChild(r14, r12)     // Catch:{ all -> 0x00e5 }
                com.bitcoin.mwallet.core.models.address.AddressAndPath r14 = new com.bitcoin.mwallet.core.models.address.AddressAndPath     // Catch:{ all -> 0x00e5 }
                r14.<init>(r13, r12)     // Catch:{ all -> 0x00e5 }
                monitor-exit(r11)
                return r14
            L_0x00e2:
                r12 = 0
                monitor-exit(r11)
                return r12
            L_0x00e5:
                r12 = move-exception
                monitor-exit(r11)
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.WalletRepository.AddressHandler.getNextAddress(com.bitcoin.mwallet.core.models.wallet.WalletId, com.bitcoin.bitcoink.Network, com.bitcoin.mwallet.core.models.address.AddressPurpose, kotlin.coroutines.Continuation):java.lang.Object");
        }

        private final void requestMoreAddressesAsync(WalletId walletId, CopayerId copayerId, SigningKey signingKey, AddressPurpose addressPurpose, PathY pathY) {
            Job job = this.requestMoreAddressesJob;
            if (!(job != null ? job.isActive() : false)) {
                CoroutineScope coroutineScope = GlobalScope.INSTANCE;
                WalletRepository$AddressHandler$requestMoreAddressesAsync$1 walletRepository$AddressHandler$requestMoreAddressesAsync$1 = new WalletRepository$AddressHandler$requestMoreAddressesAsync$1(this, walletId, copayerId, signingKey, addressPurpose, pathY, null);
                this.requestMoreAddressesJob = BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, walletRepository$AddressHandler$requestMoreAddressesAsync$1, 3, null);
            }
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/repositories/WalletRepository$WalletSaveType;", "", "(Ljava/lang/String;I)V", "NEW", "FROM_MNEMONIC", "MIGRATED", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: WalletRepository.kt */
    public enum WalletSaveType {
        NEW,
        FROM_MNEMONIC,
        MIGRATED
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[WalletSaveType.values().length];

        static {
            $EnumSwitchMapping$0[WalletSaveType.NEW.ordinal()] = 1;
            $EnumSwitchMapping$0[WalletSaveType.FROM_MNEMONIC.ordinal()] = 2;
            $EnumSwitchMapping$0[WalletSaveType.MIGRATED.ordinal()] = 3;
        }
    }

    public WalletRepository(@NotNull Application application2, @NotNull WalletDatabase walletDatabase, @NotNull CredentialDao credentialDao2, @NotNull WalletAddressInfoDao walletAddressInfoDao2, @NotNull WalletDao walletDao2, @NotNull WalletService walletService2, @NotNull AddressService addressService2, @NotNull NotificationService notificationService2, @NotNull ApplicationPreferences applicationPreferences2) {
        Intrinsics.checkParameterIsNotNull(application2, "application");
        Intrinsics.checkParameterIsNotNull(walletDatabase, "database");
        Intrinsics.checkParameterIsNotNull(credentialDao2, "credentialDao");
        Intrinsics.checkParameterIsNotNull(walletAddressInfoDao2, "walletAddressInfoDao");
        Intrinsics.checkParameterIsNotNull(walletDao2, "walletDao");
        Intrinsics.checkParameterIsNotNull(walletService2, "walletService");
        Intrinsics.checkParameterIsNotNull(addressService2, "addressService");
        Intrinsics.checkParameterIsNotNull(notificationService2, "notificationService");
        Intrinsics.checkParameterIsNotNull(applicationPreferences2, "applicationPreferences");
        this.application = application2;
        this.database = walletDatabase;
        this.credentialDao = credentialDao2;
        this.walletAddressInfoDao = walletAddressInfoDao2;
        this.walletDao = walletDao2;
        this.walletService = walletService2;
        this.addressService = addressService2;
        this.notificationService = notificationService2;
        this.applicationPreferences = applicationPreferences2;
    }

    @NotNull
    public final LiveData<List<C1261Wallet>> getWallets() {
        return this.wallets;
    }

    @NotNull
    public final LiveData<List<WalletInfoSatoshis>> getWalletInfoSatoshis() {
        return this.walletInfoSatoshis;
    }

    @NotNull
    public final LiveData<WalletInfoSatoshis> walletInfoSatoshis(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        return this.walletDao.selectWalletInfoSatoshis(walletId);
    }

    @NotNull
    public final LiveData<List<WalletInfoSatoshis>> walletInfoSatoshis(@NotNull Coin coin) {
        Intrinsics.checkParameterIsNotNull(coin, "coin");
        return this.walletDao.selectWalletInfoSatoshis(coin);
    }

    @Nullable
    public final Object getNextAddress(@NotNull WalletId walletId, @NotNull Network network, @NotNull AddressPurpose addressPurpose, @NotNull Continuation<? super AddressAndPath> continuation) {
        return this.addressHandler.getNextAddress(walletId, network, addressPurpose, continuation);
    }

    @Nullable
    public final Object getCurrentAddress(@NotNull WalletId walletId, @NotNull Network network, @NotNull AddressPurpose addressPurpose, @NotNull Continuation<? super AddressAndPath> continuation) {
        return this.addressHandler.getCurrentAddress(walletId, network, addressPurpose, continuation);
    }

    @NotNull
    public final LiveData<C1261Wallet> walletById(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        return this.walletDao.walletById(walletId);
    }

    @NotNull
    public final LiveData<C1261Wallet> walletByPk(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletPk");
        return this.walletDao.walletByPk(walletId);
    }

    @Nullable
    public final Object getWalletById(@NotNull WalletId walletId, @NotNull Continuation<? super C1261Wallet> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new WalletRepository$getWalletById$2(this, walletId, null), continuation);
    }

    @Nullable
    public final Object getWalletByPk(@NotNull WalletId walletId, @NotNull Continuation<? super C1261Wallet> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new WalletRepository$getWalletByPk$2(this, walletId, null), continuation);
    }

    @Nullable
    public final Object getWalletsById(@NotNull WalletId walletId, @NotNull Continuation<? super List<C1261Wallet>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new WalletRepository$getWalletsById$2(this, walletId, null), continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object saveNewWallet(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.C1261Wallet r9, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.credential.Credential r10, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r8 = this;
            boolean r0 = r12 instanceof com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWallet$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWallet$1 r0 = (com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWallet$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWallet$1 r0 = new com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWallet$1
            r0.<init>(r8, r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 0
            r5 = 1
            if (r2 == 0) goto L_0x0062
            if (r2 == r5) goto L_0x004a
            if (r2 != r3) goto L_0x0042
            java.lang.Object r9 = r0.L$3
            com.bitcoin.mwallet.core.repositories.WalletRepository$WalletSaveType r9 = (com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType) r9
            java.lang.Object r10 = r0.L$2
            com.bitcoin.mwallet.core.models.credential.Credential r10 = (com.bitcoin.mwallet.core.models.credential.Credential) r10
            java.lang.Object r10 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.Wallet r10 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r10
            java.lang.Object r11 = r0.L$0
            com.bitcoin.mwallet.core.repositories.WalletRepository r11 = (com.bitcoin.mwallet.core.repositories.WalletRepository) r11
            kotlin.ResultKt.throwOnFailure(r12)
            r7 = r12
            r12 = r9
            r9 = r7
            goto L_0x0115
        L_0x0042:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x004a:
            java.lang.Object r9 = r0.L$3
            r11 = r9
            com.bitcoin.mwallet.core.repositories.WalletRepository$WalletSaveType r11 = (com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType) r11
            java.lang.Object r9 = r0.L$2
            r10 = r9
            com.bitcoin.mwallet.core.models.credential.Credential r10 = (com.bitcoin.mwallet.core.models.credential.Credential) r10
            java.lang.Object r9 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.Wallet r9 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r9
            java.lang.Object r2 = r0.L$0
            com.bitcoin.mwallet.core.repositories.WalletRepository r2 = (com.bitcoin.mwallet.core.repositories.WalletRepository) r2
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = r11
            r11 = r2
            goto L_0x00a0
        L_0x0062:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r2 = "saveNewWallet id: "
            r12.append(r2)
            com.bitcoin.mwallet.core.models.wallet.WalletId r2 = r9.getId()
            r12.append(r2)
            java.lang.String r12 = r12.toString()
            java.lang.Object[] r2 = new java.lang.Object[r4]
            timber.log.Timber.m426d(r12, r2)
            kotlinx.coroutines.CoroutineDispatcher r12 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r12 = (kotlin.coroutines.CoroutineContext) r12
            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWallet$2 r2 = new com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWallet$2
            r6 = 0
            r2.<init>(r8, r9, r10, r6)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r8
            r0.L$1 = r9
            r0.L$2 = r10
            r0.L$3 = r11
            r0.label = r5
            java.lang.Object r12 = kotlinx.coroutines.BuildersKt.withContext(r12, r2, r0)
            if (r12 != r1) goto L_0x009e
            return r1
        L_0x009e:
            r12 = r11
            r11 = r8
        L_0x00a0:
            r7 = r10
            r10 = r9
            r9 = r7
            boolean r2 = r10.isMultiSig()
            if (r2 == 0) goto L_0x00d2
            com.bitcoin.mwallet.core.preferences.WalletPreference r9 = r10.preference()
            r9.setHasBackedUp(r5)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r11 = "Not saving multisig in backend walletid="
            r9.append(r11)
            com.bitcoin.mwallet.core.models.wallet.WalletId r10 = r10.getId()
            r9.append(r10)
            r10 = 125(0x7d, float:1.75E-43)
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            java.lang.Object[] r10 = new java.lang.Object[r4]
            timber.log.Timber.m426d(r9, r10)
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x00d2:
            boolean r2 = r9 instanceof com.bitcoin.mwallet.core.models.credential.CredentialEncrypted
            if (r2 == 0) goto L_0x00e7
            com.bitcoin.mwallet.core.preferences.WalletPreference r9 = r10.preference()
            r9.setHasBackedUp(r5)
            java.lang.Object[] r9 = new java.lang.Object[r4]
            java.lang.String r10 = "Don't save encrypted Wallets"
            timber.log.Timber.m426d(r10, r9)
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x00e7:
            com.bitcoin.mwallet.core.models.credential.CredentialId r2 = r9.getId()
            com.bitcoin.mwallet.core.models.credential.CredentialType r2 = r2.getType()
            com.bitcoin.mwallet.core.models.credential.CredentialType r6 = com.bitcoin.mwallet.core.models.credential.CredentialType.MNEMONIC_AND_PROTECTED
            if (r2 != r6) goto L_0x0104
            com.bitcoin.mwallet.core.preferences.WalletPreference r9 = r10.preference()
            r9.setHasBackedUp(r5)
            java.lang.Object[] r9 = new java.lang.Object[r4]
            java.lang.String r10 = "Don't save password protected Wallets"
            timber.log.Timber.m426d(r10, r9)
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x0104:
            r0.L$0 = r11
            r0.L$1 = r10
            r0.L$2 = r9
            r0.L$3 = r12
            r0.label = r3
            java.lang.Object r9 = r11.saveNewWalletRemote(r10, r9, r12, r0)
            if (r9 != r1) goto L_0x0115
            return r1
        L_0x0115:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 != 0) goto L_0x015a
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = "Enqueuing SaveWalletWorker walletId="
            r9.append(r0)
            com.bitcoin.mwallet.core.models.wallet.WalletId r0 = r10.getId()
            r9.append(r0)
            java.lang.String r9 = r9.toString()
            java.lang.Object[] r0 = new java.lang.Object[r4]
            timber.log.Timber.m426d(r9, r0)
            android.app.Application r9 = r11.application
            android.content.Context r9 = r9.getApplicationContext()
            androidx.work.WorkManager r9 = androidx.work.WorkManager.getInstance(r9)
            com.bitcoin.mwallet.core.workers.SaveWalletWorker$Companion r11 = com.bitcoin.mwallet.core.workers.SaveWalletWorker.Companion
            com.bitcoin.mwallet.core.models.wallet.WalletId r0 = r10.getId()
            java.lang.String r11 = r11.uniqueWorkName(r0)
            androidx.work.ExistingWorkPolicy r0 = androidx.work.ExistingWorkPolicy.KEEP
            com.bitcoin.mwallet.core.workers.SaveWalletWorker$Companion r1 = com.bitcoin.mwallet.core.workers.SaveWalletWorker.Companion
            com.bitcoin.mwallet.core.models.wallet.WalletId r10 = r10.getId()
            androidx.work.OneTimeWorkRequest r10 = r1.request(r10, r12)
            r9.enqueueUniqueWork(r11, r0, r10)
        L_0x015a:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.WalletRepository.saveNewWallet(com.bitcoin.mwallet.core.models.wallet.Wallet, com.bitcoin.mwallet.core.models.credential.Credential, com.bitcoin.mwallet.core.repositories.WalletRepository$WalletSaveType, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static /* synthetic */ Object saveNewWallet$default(WalletRepository walletRepository, C1261Wallet wallet, Credential credential, WalletSaveType walletSaveType, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            walletSaveType = WalletSaveType.NEW;
        }
        return walletRepository.saveNewWallet(wallet, credential, walletSaveType, continuation);
    }

    private final void subscribeToNotifications(WalletId walletId, CopayerId copayerId, SigningKey signingKey) {
        CoroutineScope coroutineScope = GlobalScope.INSTANCE;
        WalletRepository$subscribeToNotifications$1 walletRepository$subscribeToNotifications$1 = new WalletRepository$subscribeToNotifications$1(this, walletId, copayerId, signingKey, null);
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, walletRepository$subscribeToNotifications$1, 3, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object saveNewWalletRemote(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r8, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$1 r0 = (com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$1 r0 = new com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$1
            r0.<init>(r7, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x005e
            if (r2 == r4) goto L_0x004a
            if (r2 != r3) goto L_0x0042
            java.lang.Object r8 = r0.L$4
            com.bitcoin.mwallet.core.models.credential.Credential r8 = (com.bitcoin.mwallet.core.models.credential.Credential) r8
            java.lang.Object r8 = r0.L$3
            com.bitcoin.mwallet.core.models.wallet.Wallet r8 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r8
            java.lang.Object r8 = r0.L$2
            com.bitcoin.mwallet.core.repositories.WalletRepository$WalletSaveType r8 = (com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType) r8
            java.lang.Object r8 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r8 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r8
            java.lang.Object r8 = r0.L$0
            com.bitcoin.mwallet.core.repositories.WalletRepository r8 = (com.bitcoin.mwallet.core.repositories.WalletRepository) r8
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00b6
        L_0x0042:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x004a:
            java.lang.Object r8 = r0.L$3
            com.bitcoin.mwallet.core.models.wallet.Wallet r8 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r8
            java.lang.Object r9 = r0.L$2
            com.bitcoin.mwallet.core.repositories.WalletRepository$WalletSaveType r9 = (com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType) r9
            java.lang.Object r2 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r2 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r2
            java.lang.Object r5 = r0.L$0
            com.bitcoin.mwallet.core.repositories.WalletRepository r5 = (com.bitcoin.mwallet.core.repositories.WalletRepository) r5
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0083
        L_0x005e:
            kotlin.ResultKt.throwOnFailure(r10)
            com.bitcoin.mwallet.core.dao.WalletDao r10 = r7.walletDao
            com.bitcoin.mwallet.core.models.wallet.Wallet r10 = r10.getWalletById(r8)
            com.bitcoin.mwallet.core.dao.CredentialDao r2 = r7.credentialDao
            com.bitcoin.mwallet.core.models.credential.CredentialId r5 = r10.getCredentialId()
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r10
            r0.label = r4
            java.lang.Object r2 = r2.select(r5, r0)
            if (r2 != r1) goto L_0x007e
            return r1
        L_0x007e:
            r5 = r7
            r6 = r2
            r2 = r8
            r8 = r10
            r10 = r6
        L_0x0083:
            com.bitcoin.mwallet.core.models.credential.Credential r10 = (com.bitcoin.mwallet.core.models.credential.Credential) r10
            if (r10 != 0) goto L_0x00a3
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Expected credential to exist for walletId="
            r8.append(r9)
            r8.append(r2)
            java.lang.String r8 = r8.toString()
            r9 = 0
            java.lang.Object[] r9 = new java.lang.Object[r9]
            timber.log.Timber.m429e(r8, r9)
            java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r8
        L_0x00a3:
            r0.L$0 = r5
            r0.L$1 = r2
            r0.L$2 = r9
            r0.L$3 = r8
            r0.L$4 = r10
            r0.label = r3
            java.lang.Object r10 = r5.saveNewWalletRemote(r8, r10, r9, r0)
            if (r10 != r1) goto L_0x00b6
            return r1
        L_0x00b6:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.WalletRepository.saveNewWalletRemote(com.bitcoin.mwallet.core.models.wallet.WalletId, com.bitcoin.mwallet.core.repositories.WalletRepository$WalletSaveType, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0225, code lost:
        if (r3 != null) goto L_0x0229;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x01c9  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x02a4  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x02a7  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x02b4  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x02b6  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x02f0 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0033 A[ADDED_TO_REGION] */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object saveNewWalletRemote(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.C1261Wallet r30, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.credential.Credential r31, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType r32, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r33) {
        /*
            r29 = this;
            r0 = r29
            r1 = r30
            r2 = r31
            r3 = r32
            r4 = r33
            boolean r5 = r4 instanceof com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$2
            if (r5 == 0) goto L_0x001e
            r5 = r4
            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$2 r5 = (com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$2) r5
            int r6 = r5.label
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = r6 & r7
            if (r6 == 0) goto L_0x001e
            int r4 = r5.label
            int r4 = r4 - r7
            r5.label = r4
            goto L_0x0023
        L_0x001e:
            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$2 r5 = new com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$2
            r5.<init>(r0, r4)
        L_0x0023:
            java.lang.Object r4 = r5.result
            java.lang.Object r13 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r6 = r5.label
            r14 = 5
            r15 = 4
            r7 = 3
            r8 = 2
            r12 = 0
            r11 = 1
            if (r6 == 0) goto L_0x00b8
            if (r6 == r11) goto L_0x009c
            if (r6 == r8) goto L_0x009c
            if (r6 == r7) goto L_0x0096
            if (r6 == r15) goto L_0x006d
            if (r6 != r14) goto L_0x0065
            boolean r1 = r5.Z$0
            java.lang.Object r1 = r5.L$7
            com.bitcoin.mwallet.core.models.credential.Credential r1 = (com.bitcoin.mwallet.core.models.credential.Credential) r1
            java.lang.Object r1 = r5.L$6
            com.bitcoin.mwallet.core.models.wallet.Wallet r1 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r1
            java.lang.Object r1 = r5.L$5
            com.bitcoin.mwallet.core.models.credential.CredentialId r1 = (com.bitcoin.mwallet.core.models.credential.CredentialId) r1
            java.lang.Object r1 = r5.L$4
            com.bitcoin.mwallet.core.services.wallet.CreateWalletResponse r1 = (com.bitcoin.mwallet.core.services.wallet.CreateWalletResponse) r1
            java.lang.Object r1 = r5.L$3
            com.bitcoin.mwallet.core.repositories.WalletRepository$WalletSaveType r1 = (com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType) r1
            java.lang.Object r1 = r5.L$2
            com.bitcoin.mwallet.core.models.credential.Credential r1 = (com.bitcoin.mwallet.core.models.credential.Credential) r1
            java.lang.Object r1 = r5.L$1
            com.bitcoin.mwallet.core.models.wallet.Wallet r1 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r1
            java.lang.Object r1 = r5.L$0
            com.bitcoin.mwallet.core.repositories.WalletRepository r1 = (com.bitcoin.mwallet.core.repositories.WalletRepository) r1
            kotlin.ResultKt.throwOnFailure(r4)
        L_0x0062:
            r0 = 1
            goto L_0x02f1
        L_0x0065:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x006d:
            boolean r1 = r5.Z$0
            java.lang.Object r2 = r5.L$7
            com.bitcoin.mwallet.core.models.credential.Credential r2 = (com.bitcoin.mwallet.core.models.credential.Credential) r2
            java.lang.Object r3 = r5.L$6
            com.bitcoin.mwallet.core.models.wallet.Wallet r3 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r3
            java.lang.Object r6 = r5.L$5
            com.bitcoin.mwallet.core.models.credential.CredentialId r6 = (com.bitcoin.mwallet.core.models.credential.CredentialId) r6
            java.lang.Object r7 = r5.L$4
            com.bitcoin.mwallet.core.services.wallet.CreateWalletResponse r7 = (com.bitcoin.mwallet.core.services.wallet.CreateWalletResponse) r7
            java.lang.Object r8 = r5.L$3
            com.bitcoin.mwallet.core.repositories.WalletRepository$WalletSaveType r8 = (com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType) r8
            java.lang.Object r9 = r5.L$2
            com.bitcoin.mwallet.core.models.credential.Credential r9 = (com.bitcoin.mwallet.core.models.credential.Credential) r9
            java.lang.Object r10 = r5.L$1
            com.bitcoin.mwallet.core.models.wallet.Wallet r10 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r10
            java.lang.Object r15 = r5.L$0
            com.bitcoin.mwallet.core.repositories.WalletRepository r15 = (com.bitcoin.mwallet.core.repositories.WalletRepository) r15
            kotlin.ResultKt.throwOnFailure(r4)
            r11 = r1
            r1 = r10
            goto L_0x0298
        L_0x0096:
            java.lang.Object r1 = r5.L$4
            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r1
            int r1 = r5.I$0
        L_0x009c:
            java.lang.Object r1 = r5.L$3
            com.bitcoin.mwallet.core.repositories.WalletRepository$WalletSaveType r1 = (com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType) r1
            java.lang.Object r2 = r5.L$2
            com.bitcoin.mwallet.core.models.credential.Credential r2 = (com.bitcoin.mwallet.core.models.credential.Credential) r2
            java.lang.Object r3 = r5.L$1
            com.bitcoin.mwallet.core.models.wallet.Wallet r3 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r3
            java.lang.Object r6 = r5.L$0
            com.bitcoin.mwallet.core.repositories.WalletRepository r6 = (com.bitcoin.mwallet.core.repositories.WalletRepository) r6
            kotlin.ResultKt.throwOnFailure(r4)
            r7 = r1
            r1 = r3
            r14 = 0
            r3 = r2
            r2 = r6
            r6 = r4
            r4 = 1
            goto L_0x01be
        L_0x00b8:
            kotlin.ResultKt.throwOnFailure(r4)
            com.bitcoin.mwallet.core.models.wallet.WalletId r4 = r30.getId()
            boolean r4 = r4.isLocal()
            if (r4 != 0) goto L_0x00e4
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Trying to save wallet with non local walletId="
            r2.append(r3)
            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = r30.getId()
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            java.lang.Object[] r2 = new java.lang.Object[r12]
            timber.log.Timber.m438w(r1, r2)
            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r11)
            return r1
        L_0x00e4:
            int[] r4 = com.bitcoin.mwallet.core.repositories.WalletRepository.WhenMappings.$EnumSwitchMapping$0
            int r6 = r32.ordinal()
            r4 = r4[r6]
            if (r4 == r11) goto L_0x018d
            if (r4 == r8) goto L_0x015b
            if (r4 != r7) goto L_0x0155
            com.bitcoin.mwallet.core.models.wallet.WalletId$Companion r4 = com.bitcoin.mwallet.core.models.wallet.WalletId.Companion
            com.bitcoin.mwallet.core.models.Coin r6 = r30.getCoin()
            java.lang.String r4 = r4.localPrefix(r6)
            int r4 = r4.length()
            com.bitcoin.mwallet.core.models.wallet.WalletId r6 = r30.getId()
            java.lang.String r6 = r6.getId()
            if (r6 == 0) goto L_0x014d
            java.lang.String r6 = r6.substring(r4)
            java.lang.String r8 = "(this as java.lang.String).substring(startIndex)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r8)
            com.bitcoin.mwallet.core.models.wallet.WalletId r8 = new com.bitcoin.mwallet.core.models.wallet.WalletId
            r8.<init>(r6)
            com.bitcoin.mwallet.core.services.wallet.WalletService r6 = r0.walletService
            com.bitcoin.mwallet.core.models.Coin r9 = r30.getCoin()
            com.bitcoin.bitcoink.Network r10 = r30.getNetwork()
            com.bitcoin.bitcoink.ExtendedPublicKey r16 = r30.getPublicKey()
            com.bitcoin.mwallet.core.utils.signature.SigningKey r17 = r30.getSigningKey()
            java.lang.String r17 = r17.getPublicKey()
            r5.L$0 = r0
            r5.L$1 = r1
            r5.L$2 = r2
            r5.L$3 = r3
            r5.I$0 = r4
            r5.L$4 = r8
            r5.label = r7
            r7 = r8
            r8 = r9
            r9 = r10
            r10 = r16
            r4 = 1
            r11 = r17
            r14 = 0
            r12 = r5
            java.lang.Object r6 = r6.migrateExisitingWallet(r7, r8, r9, r10, r11, r12)
            if (r6 != r13) goto L_0x01bb
            return r13
        L_0x014d:
            kotlin.TypeCastException r1 = new kotlin.TypeCastException
            java.lang.String r2 = "null cannot be cast to non-null type java.lang.String"
            r1.<init>(r2)
            throw r1
        L_0x0155:
            kotlin.NoWhenBranchMatchedException r1 = new kotlin.NoWhenBranchMatchedException
            r1.<init>()
            throw r1
        L_0x015b:
            r4 = 1
            r14 = 0
            com.bitcoin.mwallet.core.services.wallet.WalletService r6 = r0.walletService
            com.bitcoin.mwallet.core.models.Coin r7 = r30.getCoin()
            com.bitcoin.bitcoink.Network r9 = r30.getNetwork()
            com.bitcoin.bitcoink.ExtendedPublicKey r10 = r30.getPublicKey()
            java.lang.String r11 = r30.getName()
            com.bitcoin.mwallet.core.utils.signature.SigningKey r12 = r30.getSigningKey()
            java.lang.String r12 = r12.getPublicKey()
            r5.L$0 = r0
            r5.L$1 = r1
            r5.L$2 = r2
            r5.L$3 = r3
            r5.label = r8
            r8 = r9
            r9 = r10
            r10 = r11
            r11 = r12
            r12 = r5
            java.lang.Object r6 = r6.recoverSingleSigWallet(r7, r8, r9, r10, r11, r12)
            if (r6 != r13) goto L_0x01bb
            return r13
        L_0x018d:
            r4 = 1
            r14 = 0
            com.bitcoin.mwallet.core.services.wallet.WalletService r6 = r0.walletService
            com.bitcoin.mwallet.core.models.Coin r7 = r30.getCoin()
            com.bitcoin.bitcoink.Network r8 = r30.getNetwork()
            com.bitcoin.bitcoink.ExtendedPublicKey r9 = r30.getPublicKey()
            java.lang.String r10 = r30.getName()
            com.bitcoin.mwallet.core.utils.signature.SigningKey r11 = r30.getSigningKey()
            java.lang.String r11 = r11.getPublicKey()
            r5.L$0 = r0
            r5.L$1 = r1
            r5.L$2 = r2
            r5.L$3 = r3
            r5.label = r4
            r12 = r5
            java.lang.Object r6 = r6.createSingleSigWallet(r7, r8, r9, r10, r11, r12)
            if (r6 != r13) goto L_0x01bb
            return r13
        L_0x01bb:
            r7 = r3
            r3 = r2
            r2 = r0
        L_0x01be:
            com.bitcoin.mwallet.core.services.wallet.CreateWalletResponse r6 = (com.bitcoin.mwallet.core.services.wallet.CreateWalletResponse) r6
            r9 = r3
            r8 = r7
            r7 = r6
            com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse r3 = r7.getGrpcError()
            if (r3 == 0) goto L_0x01db
            com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse r1 = r7.getGrpcError()
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r2 = new java.lang.Object[r14]
            timber.log.Timber.m429e(r1, r2)
            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r14)
            return r1
        L_0x01db:
            java.lang.RuntimeException r3 = r7.getError()
            if (r3 == 0) goto L_0x01f3
            java.lang.RuntimeException r1 = r7.getError()
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r2 = new java.lang.Object[r14]
            timber.log.Timber.m429e(r1, r2)
            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r14)
            return r1
        L_0x01f3:
            com.bitcoin.mwallet.core.models.credential.CredentialId r6 = r1.getCredentialId()
            com.bitcoin.mwallet.core.models.wallet.WalletId r18 = r7.getWalletId()
            if (r18 == 0) goto L_0x0228
            com.bitcoin.mwallet.core.models.credential.CredentialId r3 = r1.getCredentialId()
            com.bitcoin.mwallet.core.models.credential.CredentialId$Companion r10 = com.bitcoin.mwallet.core.models.credential.CredentialId.Companion
            com.bitcoin.mwallet.core.models.credential.CredentialType r3 = r3.getType()
            com.bitcoin.mwallet.core.models.credential.CredentialId r22 = r10.from(r3)
            r17 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 989(0x3dd, float:1.386E-42)
            r28 = 0
            r16 = r1
            com.bitcoin.mwallet.core.models.wallet.Wallet r3 = com.bitcoin.mwallet.core.models.wallet.C1261Wallet.copy$default(r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)
            if (r3 == 0) goto L_0x0228
            goto L_0x0229
        L_0x0228:
            r3 = r1
        L_0x0229:
            com.bitcoin.mwallet.core.models.credential.CredentialId r10 = r3.getCredentialId()
            com.bitcoin.mwallet.core.models.credential.Credential r10 = r9.copyWithId(r10)
            com.bitcoin.mwallet.core.preferences.ApplicationPreferences r11 = r2.applicationPreferences
            com.bitcoin.mwallet.core.preferences.SharedPreference r12 = com.bitcoin.mwallet.core.preferences.SharedPreference.NOTIFICATION
            java.lang.String r12 = r12.getKey()
            boolean r11 = r11.getBoolean(r12, r4)
            if (r11 == 0) goto L_0x0252
            com.bitcoin.mwallet.core.models.wallet.WalletId r12 = r3.getId()
            com.bitcoin.mwallet.core.models.Copayers r16 = r3.getCopayers()
            com.bitcoin.mwallet.core.models.CopayerId r14 = r16.getWalletCopayerId()
            com.bitcoin.mwallet.core.utils.signature.SigningKey r4 = r3.getSigningKey()
            r2.subscribeToNotifications(r12, r14, r4)
        L_0x0252:
            com.bitcoin.mwallet.core.services.notification.NotificationService r4 = r2.notificationService
            com.bitcoin.mwallet.core.utils.SignedStub$SignedRequest r12 = new com.bitcoin.mwallet.core.utils.SignedStub$SignedRequest
            com.bitcoin.mwallet.core.models.wallet.WalletId r14 = r3.getId()
            com.bitcoin.mwallet.core.models.Copayers r18 = r3.getCopayers()
            com.bitcoin.mwallet.core.models.CopayerId r15 = r18.getWalletCopayerId()
            com.bitcoin.mwallet.core.utils.signature.SigningKey r0 = r3.getSigningKey()
            r12.<init>(r14, r15, r0)
            java.util.Set r0 = kotlin.collections.SetsKt.setOf(r12)
            java.util.Locale r12 = java.util.Locale.getDefault()
            java.lang.String r12 = r12.toString()
            java.lang.String r14 = "Locale.getDefault().toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r12, r14)
            r5.L$0 = r2
            r5.L$1 = r1
            r5.L$2 = r9
            r5.L$3 = r8
            r5.L$4 = r7
            r5.L$5 = r6
            r5.L$6 = r3
            r5.L$7 = r10
            r5.Z$0 = r11
            r14 = 4
            r5.label = r14
            java.lang.Object r0 = r4.registerRegion(r0, r12, r5)
            if (r0 != r13) goto L_0x0296
            return r13
        L_0x0296:
            r15 = r2
            r2 = r10
        L_0x0298:
            com.bitcoin.mwallet.core.preferences.WalletPreference r0 = r3.preference()
            com.bitcoin.mwallet.core.models.Coin r4 = r3.getCoin()
            com.bitcoin.mwallet.core.models.Coin r10 = com.bitcoin.mwallet.core.models.Coin.BCH
            if (r4 != r10) goto L_0x02a7
            java.lang.String r4 = "#0AC18E"
            goto L_0x02a9
        L_0x02a7:
            java.lang.String r4 = "#F09E0E"
        L_0x02a9:
            r0.setColor(r4)
            com.bitcoin.mwallet.core.preferences.WalletPreference r0 = r3.preference()
            com.bitcoin.mwallet.core.repositories.WalletRepository$WalletSaveType r4 = com.bitcoin.mwallet.core.repositories.WalletRepository.WalletSaveType.NEW
            if (r8 == r4) goto L_0x02b6
            r4 = 1
            goto L_0x02b7
        L_0x02b6:
            r4 = 0
        L_0x02b7:
            r0.setHasBackedUp(r4)
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0
            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3 r4 = new com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3
            r24 = 0
            r18 = r4
            r19 = r15
            r20 = r1
            r21 = r3
            r22 = r6
            r23 = r2
            r18.<init>(r19, r20, r21, r22, r23, r24)
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r5.L$0 = r15
            r5.L$1 = r1
            r5.L$2 = r9
            r5.L$3 = r8
            r5.L$4 = r7
            r5.L$5 = r6
            r5.L$6 = r3
            r5.L$7 = r2
            r5.Z$0 = r11
            r1 = 5
            r5.label = r1
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r0, r4, r5)
            if (r0 != r13) goto L_0x0062
            return r13
        L_0x02f1:
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.WalletRepository.saveNewWalletRemote(com.bitcoin.mwallet.core.models.wallet.Wallet, com.bitcoin.mwallet.core.models.credential.Credential, com.bitcoin.mwallet.core.repositories.WalletRepository$WalletSaveType, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00a7 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00a8 A[PHI: r12 
      PHI: (r12v2 java.lang.Object) = (r12v7 java.lang.Object), (r12v1 java.lang.Object) binds: [B:19:0x00a5, B:10:0x002a] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object createAndSaveNewWallet(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.Coin r10, @org.jetbrains.annotations.NotNull java.lang.String r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r9 = this;
            boolean r0 = r12 instanceof com.bitcoin.mwallet.core.repositories.WalletRepository$createAndSaveNewWallet$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            com.bitcoin.mwallet.core.repositories.WalletRepository$createAndSaveNewWallet$1 r0 = (com.bitcoin.mwallet.core.repositories.WalletRepository$createAndSaveNewWallet$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.repositories.WalletRepository$createAndSaveNewWallet$1 r0 = new com.bitcoin.mwallet.core.repositories.WalletRepository$createAndSaveNewWallet$1
            r0.<init>(r9, r12)
        L_0x0019:
            r5 = r0
            java.lang.Object r12 = r5.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x005b
            if (r1 == r3) goto L_0x004a
            if (r1 != r2) goto L_0x0042
            java.lang.Object r10 = r5.L$4
            com.bitcoin.mwallet.core.models.credential.CredentialMnemonic r10 = (com.bitcoin.mwallet.core.models.credential.CredentialMnemonic) r10
            java.lang.Object r10 = r5.L$3
            com.bitcoin.mwallet.core.models.wallet.Wallet r10 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r10
            java.lang.Object r10 = r5.L$2
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r10 = r5.L$1
            com.bitcoin.mwallet.core.models.Coin r10 = (com.bitcoin.mwallet.core.models.Coin) r10
            java.lang.Object r10 = r5.L$0
            com.bitcoin.mwallet.core.repositories.WalletRepository r10 = (com.bitcoin.mwallet.core.repositories.WalletRepository) r10
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x00a8
        L_0x0042:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x004a:
            java.lang.Object r10 = r5.L$2
            r11 = r10
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r10 = r5.L$1
            com.bitcoin.mwallet.core.models.Coin r10 = (com.bitcoin.mwallet.core.models.Coin) r10
            java.lang.Object r1 = r5.L$0
            com.bitcoin.mwallet.core.repositories.WalletRepository r1 = (com.bitcoin.mwallet.core.repositories.WalletRepository) r1
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x007c
        L_0x005b:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlinx.coroutines.CoroutineDispatcher r12 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r12 = (kotlin.coroutines.CoroutineContext) r12
            com.bitcoin.mwallet.core.repositories.WalletRepository$createAndSaveNewWallet$2 r1 = new com.bitcoin.mwallet.core.repositories.WalletRepository$createAndSaveNewWallet$2
            r4 = 0
            r1.<init>(r10, r11, r4)
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            r5.L$0 = r9
            r5.L$1 = r10
            r5.L$2 = r11
            r5.label = r3
            java.lang.Object r12 = kotlinx.coroutines.BuildersKt.withContext(r12, r1, r5)
            if (r12 != r0) goto L_0x007b
            return r0
        L_0x007b:
            r1 = r9
        L_0x007c:
            kotlin.Pair r12 = (kotlin.Pair) r12
            java.lang.Object r3 = r12.component1()
            com.bitcoin.mwallet.core.models.wallet.Wallet r3 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r3
            java.lang.Object r12 = r12.component2()
            com.bitcoin.mwallet.core.models.credential.CredentialMnemonic r12 = (com.bitcoin.mwallet.core.models.credential.CredentialMnemonic) r12
            r4 = r12
            com.bitcoin.mwallet.core.models.credential.Credential r4 = (com.bitcoin.mwallet.core.models.credential.Credential) r4
            r6 = 0
            r7 = 4
            r8 = 0
            r5.L$0 = r1
            r5.L$1 = r10
            r5.L$2 = r11
            r5.L$3 = r3
            r5.L$4 = r12
            r5.label = r2
            r2 = r3
            r3 = r4
            r4 = r6
            r6 = r7
            r7 = r8
            java.lang.Object r12 = saveNewWallet$default(r1, r2, r3, r4, r5, r6, r7)
            if (r12 != r0) goto L_0x00a8
            return r0
        L_0x00a8:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.WalletRepository.createAndSaveNewWallet(com.bitcoin.mwallet.core.models.Coin, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object getWallets(@NotNull Continuation<? super List<C1261Wallet>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new WalletRepository$getWallets$2(this, null), continuation);
    }

    @Nullable
    public final Object getWalletIds(@NotNull Continuation<? super List<WalletId>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new WalletRepository$getWalletIds$2(this, null), continuation);
    }

    @Nullable
    public final Object getWalletIds(@NotNull Coin coin, @NotNull Continuation<? super List<WalletId>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new WalletRepository$getWalletIds$4(this, coin, null), continuation);
    }

    @Nullable
    public final Object getWalletPks(@NotNull Coin coin, @NotNull Continuation<? super List<WalletId>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new WalletRepository$getWalletPks$2(this, coin, null), continuation);
    }

    @Nullable
    public final Object getWalletPks(@NotNull Continuation<? super List<WalletId>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new WalletRepository$getWalletPks$4(this, null), continuation);
    }

    @Nullable
    public final Object walletIdsByCoin(@NotNull Coin coin, @NotNull Continuation<? super List<WalletId>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new WalletRepository$walletIdsByCoin$2(this, coin, null), continuation);
    }

    @Nullable
    public final Object walletPksByCoin(@NotNull Coin coin, @NotNull Continuation<? super List<WalletId>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new WalletRepository$walletPksByCoin$2(this, coin, null), continuation);
    }

    @Nullable
    public final Object getWalletsIds(@NotNull ZionId zionId, @NotNull Continuation<? super Set<WalletId>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new WalletRepository$getWalletsIds$2(this, zionId, null), continuation);
    }

    @Nullable
    public final Object getWalletCoin(@NotNull WalletId walletId, @NotNull Continuation<? super Coin> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new WalletRepository$getWalletCoin$2(this, walletId, null), continuation);
    }

    @Nullable
    public final Object getCredential(@NotNull WalletId walletId, @NotNull Continuation<? super Credential> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new WalletRepository$getCredential$2(this, walletId, null), continuation);
    }

    @Nullable
    public final Object getCredential(@NotNull CredentialId credentialId, @NotNull Continuation<? super Credential> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new WalletRepository$getCredential$4(this, credentialId, null), continuation);
    }

    @Nullable
    public final Object getCredentialId(@NotNull WalletId walletId, @NotNull Continuation<? super CredentialId> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new WalletRepository$getCredentialId$2(this, walletId, null), continuation);
    }

    @Nullable
    public final Object deleteWallet(@NotNull WalletId walletId, @NotNull Continuation<? super Unit> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new WalletRepository$deleteWallet$2(this, walletId, null), continuation);
    }

    @Nullable
    public final Object deleteWallet(@NotNull WalletId walletId, @NotNull CredentialId credentialId, @NotNull Continuation<? super Unit> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new WalletRepository$deleteWallet$4(this, credentialId, walletId, null), continuation);
    }

    @Nullable
    public final Object renameWallet(@NotNull WalletId walletId, @NotNull String str, @NotNull Continuation<? super Unit> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new WalletRepository$renameWallet$2(this, walletId, str, null), continuation);
    }
}
