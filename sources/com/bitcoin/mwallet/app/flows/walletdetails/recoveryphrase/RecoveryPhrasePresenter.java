package com.bitcoin.mwallet.app.flows.walletdetails.recoveryphrase;

import android.content.Context;
import android.graphics.Bitmap;
import com.bitcoin.bitcoink.DerivationPath;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.interactors.GetCredentialInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.models.credential.Credential;
import com.bitcoin.mwallet.core.models.credential.CredentialMnemonic;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0002\u0010\fJ\u0012\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020\u000eH\u0002J\b\u0010#\u001a\u00020\u000eH\u0002J\b\u0010$\u001a\u00020\u000eH\u0002J\b\u0010%\u001a\u0004\u0018\u00010!J\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000e0'R\u000e\u0010\r\u001a\u00020\u000eXD¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0016X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006("}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/recoveryphrase/RecoveryPhrasePresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/recoveryphrase/RecoveryPhraseRouter;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "walletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "credentialInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCredentialInteractor;", "router", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;Lcom/bitcoin/mwallet/core/interactors/GetCredentialInteractor;Lcom/bitcoin/mwallet/app/flows/walletdetails/recoveryphrase/RecoveryPhraseRouter;)V", "NETWORK_MAINNET", "", "barcodeEncoder", "Lcom/journeyapps/barcodescanner/BarcodeEncoder;", "getBarcodeEncoder", "()Lcom/journeyapps/barcodescanner/BarcodeEncoder;", "qrCodeSize", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "setWalletId", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "walletTicker", "getWalletTicker", "()Ljava/lang/String;", "setWalletTicker", "(Ljava/lang/String;)V", "createQrCode", "Landroid/graphics/Bitmap;", "rawString", "createRawString", "getMnemonic", "getMnemonicQrCode", "getMnemonicWords", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: RecoveryPhrasePresenter.kt */
public final class RecoveryPhrasePresenter extends ScreenPresenter<RecoveryPhraseRouter> {
    private final String NETWORK_MAINNET;
    @NotNull
    private final BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
    /* access modifiers changed from: private */
    public final GetCredentialInteractor credentialInteractor;
    private final int qrCodeSize;
    @NotNull
    public WalletId walletId;
    /* access modifiers changed from: private */
    public final GetWalletInteractor walletInteractor;
    @Nullable
    private String walletTicker;

    public RecoveryPhrasePresenter(@NotNull Context context, @NotNull CoroutineScope coroutineScope, @NotNull GetWalletInteractor getWalletInteractor, @NotNull GetCredentialInteractor getCredentialInteractor, @NotNull RecoveryPhraseRouter recoveryPhraseRouter) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(getWalletInteractor, "walletInteractor");
        Intrinsics.checkParameterIsNotNull(getCredentialInteractor, "credentialInteractor");
        Intrinsics.checkParameterIsNotNull(recoveryPhraseRouter, "router");
        super(context, recoveryPhraseRouter);
        this.walletInteractor = getWalletInteractor;
        this.credentialInteractor = getCredentialInteractor;
        this.qrCodeSize = (int) context.getResources().getDimension(C1018R.dimen.qr_code_size);
        this.NETWORK_MAINNET = "livenet";
    }

    @NotNull
    public final WalletId getWalletId() {
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        return walletId2;
    }

    public final void setWalletId(@NotNull WalletId walletId2) {
        Intrinsics.checkParameterIsNotNull(walletId2, "<set-?>");
        this.walletId = walletId2;
    }

    @NotNull
    public final BarcodeEncoder getBarcodeEncoder() {
        return this.barcodeEncoder;
    }

    @Nullable
    public final String getWalletTicker() {
        return this.walletTicker;
    }

    public final void setWalletTicker(@Nullable String str) {
        this.walletTicker = str;
    }

    private final String getMnemonic() {
        Credential credential = (Credential) BuildersKt__BuildersKt.runBlocking$default(null, new RecoveryPhrasePresenter$getMnemonic$credential$1(this, null), 1, null);
        if (credential != null) {
            return CollectionsKt.joinToString$default(((CredentialMnemonic) credential).getMnemonic(), " ", null, null, 0, null, null, 62, null);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.core.models.credential.CredentialMnemonic");
    }

    private final String createRawString() {
        String mnemonic = getMnemonic();
        DerivationPath derivationPath = null;
        C1261Wallet wallet = (C1261Wallet) BuildersKt__BuildersKt.runBlocking$default(null, new RecoveryPhrasePresenter$createRawString$wallet$1(this, null), 1, null);
        String[] strArr = new String[5];
        strArr[0] = "1";
        strArr[1] = mnemonic;
        strArr[2] = this.NETWORK_MAINNET;
        if (wallet != null) {
            derivationPath = wallet.getPath();
        }
        if (derivationPath == null) {
            Intrinsics.throwNpe();
        }
        strArr[3] = derivationPath.asString(true);
        strArr[4] = "false";
        return CollectionsKt.joinToString$default(CollectionsKt.listOf(strArr), "|", null, null, 0, null, null, 62, null);
    }

    private final Bitmap createQrCode(String str) {
        try {
            return this.barcodeEncoder.encodeBitmap(str, BarcodeFormat.QR_CODE, this.qrCodeSize, this.qrCodeSize);
        } catch (Throwable th) {
            Timber.m431e(th, "Failed to encode bitmap", new Object[0]);
            return null;
        }
    }

    @Nullable
    public final Bitmap getMnemonicQrCode() {
        return createQrCode(createRawString());
    }

    @NotNull
    public final List<String> getMnemonicWords() {
        return StringsKt.split$default((CharSequence) getMnemonic(), new String[]{" "}, false, 0, 6, (Object) null);
    }
}
