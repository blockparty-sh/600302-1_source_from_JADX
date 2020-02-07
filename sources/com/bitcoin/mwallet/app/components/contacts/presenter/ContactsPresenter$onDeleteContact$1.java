package com.bitcoin.mwallet.app.components.contacts.presenter;

import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor;
import com.bitcoin.mwallet.core.models.contact.Contact;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.components.contacts.presenter.ContactsPresenter$onDeleteContact$1", mo38000f = "ContactsPresenter.kt", mo38001i = {}, mo38002l = {28}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: ContactsPresenter.kt */
final class ContactsPresenter$onDeleteContact$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Contact $contact;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f226p$;
    final /* synthetic */ ContactsPresenter this$0;

    ContactsPresenter$onDeleteContact$1(ContactsPresenter contactsPresenter, Contact contact, Continuation continuation) {
        this.this$0 = contactsPresenter;
        this.$contact = contact;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ContactsPresenter$onDeleteContact$1 contactsPresenter$onDeleteContact$1 = new ContactsPresenter$onDeleteContact$1(this.this$0, this.$contact, continuation);
        contactsPresenter$onDeleteContact$1.f226p$ = (CoroutineScope) obj;
        return contactsPresenter$onDeleteContact$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ContactsPresenter$onDeleteContact$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        MutableLiveData mutableLiveData;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f226p$;
            MutableLiveData daoResult = this.this$0.getDaoResult();
            ContactsInteractor access$getInteractor$p = this.this$0.interactor;
            Contact contact = this.$contact;
            this.L$0 = daoResult;
            this.label = 1;
            Object deleteContact = access$getInteractor$p.deleteContact(contact, this);
            if (deleteContact == coroutine_suspended) {
                return coroutine_suspended;
            }
            mutableLiveData = daoResult;
            obj = deleteContact;
        } else if (i == 1) {
            mutableLiveData = (MutableLiveData) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        mutableLiveData.postValue(obj);
        return Unit.INSTANCE;
    }
}
