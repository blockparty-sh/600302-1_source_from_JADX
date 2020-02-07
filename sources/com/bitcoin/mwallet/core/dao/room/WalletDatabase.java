package com.bitcoin.mwallet.core.dao.room;

import android.content.Context;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.bitcoin.mwallet.core.dao.WalletDaos;
import com.bitcoin.mwallet.core.entity.ZionNameEntity;
import com.bitcoin.mwallet.core.models.address.WalletAddressInfo;
import com.bitcoin.mwallet.core.models.contact.Contact;
import com.bitcoin.mwallet.core.models.credential.CredentialEncrypted;
import com.bitcoin.mwallet.core.models.credential.CredentialMnemonic;
import com.bitcoin.mwallet.core.models.credential.CredentialZion;
import com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate;
import com.bitcoin.mwallet.core.models.merchant.Merchant;
import com.bitcoin.mwallet.core.models.networkfee.NetworkFee;
import com.bitcoin.mwallet.core.models.p009tx.SpentUtxo;
import com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction;
import com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxo;
import com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.user.User;
import com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.utils.SingletonHolder;
import kotlin.Metadata;

@Database(entities = {CredentialMnemonic.class, CredentialEncrypted.class, CredentialZion.class, User.class, ExchangeRate.class, Utxo.class, NetworkFee.class, C1261Wallet.class, Merchant.class, Slp.class, WalletAddressInfo.class, HistoricTransaction.class, Contact.class, ZionNameEntity.class, SlpUtxo.class, VerifiedToken.class, SpentUtxo.class}, exportSchema = false, version = 32)
@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u0000 \u00042\u00020\u00012\u00020\u0002:\u0001\u0004B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0005"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/dao/room/WalletDatabase;", "Landroidx/room/RoomDatabase;", "Lcom/bitcoin/mwallet/core/dao/WalletDaos;", "()V", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@TypeConverters({RoomConverters.class})
/* compiled from: WalletDatabase.kt */
public abstract class WalletDatabase extends RoomDatabase implements WalletDaos {
    public static final Companion Companion = new Companion(null);

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/dao/room/WalletDatabase$Companion;", "Lcom/bitcoin/mwallet/core/utils/SingletonHolder;", "Lcom/bitcoin/mwallet/core/dao/room/WalletDatabase;", "Landroid/content/Context;", "()V", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: WalletDatabase.kt */
    public static final class Companion extends SingletonHolder<WalletDatabase, Context> {
        private Companion() {
            super(C12561.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
