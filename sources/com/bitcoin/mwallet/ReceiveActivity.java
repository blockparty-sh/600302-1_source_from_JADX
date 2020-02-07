package com.bitcoin.mwallet;

import android.os.Bundle;
import android.view.View;
import com.bitcoin.mwallet.app.flows.receive.ReceiveGlobalViewModel;
import com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler;
import com.guardanis.applock.activities.LockableAppCompatActivity;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.koin.core.Koin;
import org.koin.core.KoinComponent;
import org.koin.core.KoinComponent.DefaultImpls;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0011H\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001b\u0010\n\u001a\u00020\u000b8FX\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u0006\u0015"}, mo37405d2 = {"Lcom/bitcoin/mwallet/ReceiveActivity;", "Lcom/guardanis/applock/activities/LockableAppCompatActivity;", "Lorg/koin/core/KoinComponent;", "()V", "globalReceiveModel", "Lcom/bitcoin/mwallet/app/flows/receive/ReceiveGlobalViewModel;", "getGlobalReceiveModel", "()Lcom/bitcoin/mwallet/app/flows/receive/ReceiveGlobalViewModel;", "setGlobalReceiveModel", "(Lcom/bitcoin/mwallet/app/flows/receive/ReceiveGlobalViewModel;)V", "streamHandler", "Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamHandler;", "getStreamHandler", "()Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamHandler;", "streamHandler$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onPostResume", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveActivity.kt */
public final class ReceiveActivity extends LockableAppCompatActivity implements KoinComponent {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReceiveActivity.class), "streamHandler", "getStreamHandler()Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamHandler;"))};
    private HashMap _$_findViewCache;
    @NotNull
    private ReceiveGlobalViewModel globalReceiveModel = new ReceiveGlobalViewModel();
    @NotNull
    private final Lazy streamHandler$delegate = LazyKt.lazy(new ReceiveActivity$$special$$inlined$inject$1(getKoin().getRootScope(), null, null));

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @NotNull
    public final EventStreamHandler getStreamHandler() {
        Lazy lazy = this.streamHandler$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (EventStreamHandler) lazy.getValue();
    }

    @NotNull
    public Koin getKoin() {
        return DefaultImpls.getKoin(this);
    }

    @NotNull
    public final ReceiveGlobalViewModel getGlobalReceiveModel() {
        return this.globalReceiveModel;
    }

    public final void setGlobalReceiveModel(@NotNull ReceiveGlobalViewModel receiveGlobalViewModel) {
        Intrinsics.checkParameterIsNotNull(receiveGlobalViewModel, "<set-?>");
        this.globalReceiveModel = receiveGlobalViewModel;
    }

    /* access modifiers changed from: protected */
    public void onPostResume() {
        super.onPostResume();
        getStreamHandler().restartStream();
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C1018R.layout.activity_receive);
    }
}
