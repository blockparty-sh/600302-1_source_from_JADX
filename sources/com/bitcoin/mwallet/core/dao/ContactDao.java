package com.bitcoin.mwallet.core.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.bitcoin.mwallet.core.models.contact.Contact;
import com.bitcoin.mwallet.core.models.contact.ContactId;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Dao
@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H'J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH'J!\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\r\"\u00020\u0002H'¢\u0006\u0002\u0010\u000eJ!\u0010\u000f\u001a\u00020\u000b2\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\r\"\u00020\u0002H'¢\u0006\u0002\u0010\u000eJ\u0014\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00120\u0011H'¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/dao/ContactDao;", "Lcom/bitcoin/mwallet/core/dao/DaoBase;", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "contactByAddressAndWalletType", "address", "", "walletType", "contactById", "contactId", "Lcom/bitcoin/mwallet/core/models/contact/ContactId;", "deleteContact", "", "contacts", "", "([Lcom/bitcoin/mwallet/core/models/contact/Contact;)V", "insertContact", "selectAll", "Landroidx/lifecycle/LiveData;", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ContactDao.kt */
public interface ContactDao extends DaoBase<Contact> {
    @NotNull
    @Query("SELECT * FROM contact WHERE address = :address AND wallet_type = :walletType")
    Contact contactByAddressAndWalletType(@NotNull String str, @NotNull String str2);

    @NotNull
    @Query("SELECT * FROM contact WHERE id = :contactId")
    Contact contactById(@NotNull ContactId contactId);

    @Delete
    void deleteContact(@NotNull Contact... contactArr);

    @Insert(onConflict = 1)
    void insertContact(@NotNull Contact... contactArr);

    @NotNull
    @Query("SELECT * FROM contact")
    LiveData<List<Contact>> selectAll();
}
