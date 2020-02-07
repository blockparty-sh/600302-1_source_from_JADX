package com.bitcoin.mwallet.app.components.contacts.interactor;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H@ø\u0001\u0000"}, mo37405d2 = {"createContact", "", "contact", "Lcom/bitcoin/mwallet/core/models/contact/Contact;", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/bitcoin/mwallet/app/components/contacts/interactor/ContactsInteractor$ContactResult;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor", mo38000f = "ContactsInteractor.kt", mo38001i = {0, 0, 1, 1}, mo38002l = {33, 36}, mo38003m = "createContact", mo38004n = {"this", "contact", "this", "contact"}, mo38005s = {"L$0", "L$1", "L$0", "L$1"})
/* compiled from: ContactsInteractor.kt */
final class ContactsInteractor$createContact$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ContactsInteractor this$0;

    ContactsInteractor$createContact$1(ContactsInteractor contactsInteractor, Continuation continuation) {
        this.this$0 = contactsInteractor;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.createContact(null, this);
    }
}
