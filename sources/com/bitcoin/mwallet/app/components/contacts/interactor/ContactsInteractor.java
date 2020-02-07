package com.bitcoin.mwallet.app.components.contacts.interactor;

import androidx.lifecycle.LiveData;
import com.bitcoin.mwallet.core.models.contact.Contact;
import com.bitcoin.mwallet.core.repositories.ContactRepository;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0019\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contacts/interactor/ContactsInteractor;", "", "contactRepository", "Lcom/bitcoin/mwallet/core/repositories/ContactRepository;", "(Lcom/bitcoin/mwallet/core/repositories/ContactRepository;)V", "getContactRepository", "()Lcom/bitcoin/mwallet/core/repositories/ContactRepository;", "contacts", "Landroidx/lifecycle/LiveData;", "", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "getContacts", "()Landroidx/lifecycle/LiveData;", "createContact", "Lcom/bitcoin/mwallet/app/components/contacts/interactor/ContactsInteractor$ContactResult;", "contact", "(Lcom/bitcoin/mwallet/core/models/contact/Contact;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteContact", "ContactResult", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ContactsInteractor.kt */
public final class ContactsInteractor {
    @NotNull
    private final ContactRepository contactRepository;
    @NotNull
    private final LiveData<List<Contact>> contacts = this.contactRepository.getContacts();

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contacts/interactor/ContactsInteractor$ContactResult;", "", "(Ljava/lang/String;I)V", "FAILED", "CANCELED", "SUCCESS", "ALREADY_EXIST", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ContactsInteractor.kt */
    public enum ContactResult {
        FAILED,
        CANCELED,
        SUCCESS,
        ALREADY_EXIST
    }

    public ContactsInteractor(@NotNull ContactRepository contactRepository2) {
        Intrinsics.checkParameterIsNotNull(contactRepository2, "contactRepository");
        this.contactRepository = contactRepository2;
    }

    @NotNull
    public final ContactRepository getContactRepository() {
        return this.contactRepository;
    }

    @NotNull
    public final LiveData<List<Contact>> getContacts() {
        return this.contacts;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0066 A[Catch:{ Exception -> 0x007b }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0069 A[Catch:{ Exception -> 0x007b }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object createContact(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.contact.Contact r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor.ContactResult> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor$createContact$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor$createContact$1 r0 = (com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor$createContact$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor$createContact$1 r0 = new com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor$createContact$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0049
            if (r2 == r4) goto L_0x003d
            if (r2 != r3) goto L_0x0035
            java.lang.Object r7 = r0.L$1
            com.bitcoin.mwallet.core.models.contact.Contact r7 = (com.bitcoin.mwallet.core.models.contact.Contact) r7
            java.lang.Object r7 = r0.L$0
            com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor r7 = (com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor) r7
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ Exception -> 0x007b }
            goto L_0x0078
        L_0x0035:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003d:
            java.lang.Object r7 = r0.L$1
            com.bitcoin.mwallet.core.models.contact.Contact r7 = (com.bitcoin.mwallet.core.models.contact.Contact) r7
            java.lang.Object r2 = r0.L$0
            com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor r2 = (com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ Exception -> 0x007b }
            goto L_0x0064
        L_0x0049:
            kotlin.ResultKt.throwOnFailure(r8)
            com.bitcoin.mwallet.core.repositories.ContactRepository r8 = r6.contactRepository     // Catch:{ Exception -> 0x007b }
            java.lang.String r2 = r7.getAddress()     // Catch:{ Exception -> 0x007b }
            java.lang.String r5 = r7.getWalletType()     // Catch:{ Exception -> 0x007b }
            r0.L$0 = r6     // Catch:{ Exception -> 0x007b }
            r0.L$1 = r7     // Catch:{ Exception -> 0x007b }
            r0.label = r4     // Catch:{ Exception -> 0x007b }
            java.lang.Object r8 = r8.getContactByAddressAndWalletType(r2, r5, r0)     // Catch:{ Exception -> 0x007b }
            if (r8 != r1) goto L_0x0063
            return r1
        L_0x0063:
            r2 = r6
        L_0x0064:
            if (r8 == 0) goto L_0x0069
            com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor$ContactResult r7 = com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor.ContactResult.ALREADY_EXIST     // Catch:{ Exception -> 0x007b }
            return r7
        L_0x0069:
            com.bitcoin.mwallet.core.repositories.ContactRepository r8 = r2.contactRepository     // Catch:{ Exception -> 0x007b }
            r0.L$0 = r2     // Catch:{ Exception -> 0x007b }
            r0.L$1 = r7     // Catch:{ Exception -> 0x007b }
            r0.label = r3     // Catch:{ Exception -> 0x007b }
            java.lang.Object r7 = r8.insert(r7, r0)     // Catch:{ Exception -> 0x007b }
            if (r7 != r1) goto L_0x0078
            return r1
        L_0x0078:
            com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor$ContactResult r7 = com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor.ContactResult.SUCCESS
            return r7
        L_0x007b:
            com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor$ContactResult r7 = com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor.ContactResult.FAILED
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor.createContact(com.bitcoin.mwallet.core.models.contact.Contact, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object deleteContact(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.contact.Contact r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor.ContactResult> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor$deleteContact$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor$deleteContact$1 r0 = (com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor$deleteContact$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor$deleteContact$1 r0 = new com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor$deleteContact$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r5 = r0.L$1
            com.bitcoin.mwallet.core.models.contact.Contact r5 = (com.bitcoin.mwallet.core.models.contact.Contact) r5
            java.lang.Object r5 = r0.L$0
            com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor r5 = (com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor) r5
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ Exception -> 0x004f }
            goto L_0x004c
        L_0x0032:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r6)
            com.bitcoin.mwallet.core.repositories.ContactRepository r6 = r4.contactRepository     // Catch:{ Exception -> 0x004f }
            r0.L$0 = r4     // Catch:{ Exception -> 0x004f }
            r0.L$1 = r5     // Catch:{ Exception -> 0x004f }
            r0.label = r3     // Catch:{ Exception -> 0x004f }
            java.lang.Object r5 = r6.delete(r5, r0)     // Catch:{ Exception -> 0x004f }
            if (r5 != r1) goto L_0x004c
            return r1
        L_0x004c:
            com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor$ContactResult r5 = com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor.ContactResult.SUCCESS
            return r5
        L_0x004f:
            com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor$ContactResult r5 = com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor.ContactResult.FAILED
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor.deleteContact(com.bitcoin.mwallet.core.models.contact.Contact, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
