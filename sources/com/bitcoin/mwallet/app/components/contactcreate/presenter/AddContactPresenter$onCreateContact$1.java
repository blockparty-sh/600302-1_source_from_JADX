package com.bitcoin.mwallet.app.components.contactcreate.presenter;

import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.app.components.contacts.interactor.ContactsInteractor;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.contact.Contact;
import com.bitcoin.mwallet.core.models.contact.Contact.Companion;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.components.contactcreate.presenter.AddContactPresenter$onCreateContact$1", mo38000f = "AddContactPresenter.kt", mo38001i = {}, mo38002l = {39}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: AddContactPresenter.kt */
final class AddContactPresenter$onCreateContact$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f225p$;
    final /* synthetic */ AddContactPresenter this$0;

    AddContactPresenter$onCreateContact$1(AddContactPresenter addContactPresenter, Continuation continuation) {
        this.this$0 = addContactPresenter;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        AddContactPresenter$onCreateContact$1 addContactPresenter$onCreateContact$1 = new AddContactPresenter$onCreateContact$1(this.this$0, continuation);
        addContactPresenter$onCreateContact$1.f225p$ = (CoroutineScope) obj;
        return addContactPresenter$onCreateContact$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((AddContactPresenter$onCreateContact$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        MutableLiveData mutableLiveData;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f225p$;
            AnalyticsService access$getAnalyticsService$p = this.this$0.analyticsService;
            Object value = this.this$0.getSelectedWalletType().getValue();
            if (value == null) {
                Intrinsics.throwNpe();
            }
            String str = "contact_created";
            access$getAnalyticsService$p.track(str, MapsKt.mapOf(TuplesKt.m309to("coin", ((WalletType) value).name())));
            MutableLiveData daoResult = this.this$0.getDaoResult();
            ContactsInteractor access$getInteractor$p = this.this$0.interactor;
            Companion companion = Contact.Companion;
            Object value2 = this.this$0.getSelectedWalletType().getValue();
            if (value2 == null) {
                Intrinsics.throwNpe();
            }
            String name = ((WalletType) value2).name();
            Object value3 = this.this$0.getName().getValue();
            if (value3 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(value3, "name.value!!");
            String str2 = (String) value3;
            Object value4 = this.this$0.getAddress().getValue();
            if (value4 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(value4, "address.value!!");
            Contact createNew = companion.createNew(name, str2, (String) value4);
            this.L$0 = daoResult;
            this.label = 1;
            Object createContact = access$getInteractor$p.createContact(createNew, this);
            if (createContact == coroutine_suspended) {
                return coroutine_suspended;
            }
            mutableLiveData = daoResult;
            obj = createContact;
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
