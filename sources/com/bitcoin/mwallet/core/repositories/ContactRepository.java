package com.bitcoin.mwallet.core.repositories;

import androidx.lifecycle.LiveData;
import com.bitcoin.mwallet.core.dao.ContactDao;
import com.bitcoin.mwallet.core.models.contact.Contact;
import com.bitcoin.mwallet.core.models.contact.ContactId;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ!\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J\u0019\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u0019\u0010\u0018\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH@ø\u0001\u0000¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/repositories/ContactRepository;", "", "contactDao", "Lcom/bitcoin/mwallet/core/dao/ContactDao;", "(Lcom/bitcoin/mwallet/core/dao/ContactDao;)V", "contacts", "Landroidx/lifecycle/LiveData;", "", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "getContacts", "()Landroidx/lifecycle/LiveData;", "delete", "", "contact", "(Lcom/bitcoin/mwallet/core/models/contact/Contact;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getContactByAddressAndWalletType", "address", "", "walletType", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getContactById", "contactId", "Lcom/bitcoin/mwallet/core/models/contact/ContactId;", "(Lcom/bitcoin/mwallet/core/models/contact/ContactId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ContactRepository.kt */
public final class ContactRepository {
    /* access modifiers changed from: private */
    public final ContactDao contactDao;
    @NotNull
    private final LiveData<List<Contact>> contacts = this.contactDao.selectAll();

    public ContactRepository(@NotNull ContactDao contactDao2) {
        Intrinsics.checkParameterIsNotNull(contactDao2, "contactDao");
        this.contactDao = contactDao2;
    }

    @NotNull
    public final LiveData<List<Contact>> getContacts() {
        return this.contacts;
    }

    @Nullable
    public final Object insert(@NotNull Contact contact, @NotNull Continuation<? super Unit> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new ContactRepository$insert$2(this, contact, null), continuation);
    }

    @Nullable
    public final Object getContactById(@NotNull ContactId contactId, @NotNull Continuation<? super Contact> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new ContactRepository$getContactById$2(this, contactId, null), continuation);
    }

    @Nullable
    public final Object getContactByAddressAndWalletType(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super Contact> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new ContactRepository$getContactByAddressAndWalletType$2(this, str, str2, null), continuation);
    }

    @Nullable
    public final Object delete(@NotNull Contact contact, @NotNull Continuation<? super Unit> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new ContactRepository$delete$2(this, contact, null), continuation);
    }
}
