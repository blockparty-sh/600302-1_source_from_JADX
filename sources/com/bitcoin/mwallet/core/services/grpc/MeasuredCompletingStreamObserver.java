package com.bitcoin.mwallet.core.services.grpc;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p015io.grpc.stub.StreamObserver;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0015\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0010R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/grpc/MeasuredCompletingStreamObserver;", "T", "Lio/grpc/stub/StreamObserver;", "delegate", "(Lio/grpc/stub/StreamObserver;)V", "start", "", "getStart", "()J", "onCompleted", "", "onError", "t", "", "onNext", "value", "(Ljava/lang/Object;)V", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: MeasuredCompletingStreamObserver.kt */
public final class MeasuredCompletingStreamObserver<T> implements StreamObserver<T> {
    private final StreamObserver<T> delegate;
    private final long start = System.currentTimeMillis();

    public MeasuredCompletingStreamObserver(@NotNull StreamObserver<T> streamObserver) {
        Intrinsics.checkParameterIsNotNull(streamObserver, "delegate");
        this.delegate = streamObserver;
    }

    public final long getStart() {
        return this.start;
    }

    public void onNext(T t) {
        this.delegate.onNext(t);
    }

    public void onError(@Nullable Throwable th) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.delegate.getClass().getSimpleName());
        sb.append(" completed with error in ");
        sb.append(System.currentTimeMillis() - this.start);
        sb.append(" ms");
        Timber.m426d(sb.toString(), new Object[0]);
        this.delegate.onError(th);
    }

    public void onCompleted() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.delegate.getClass().getSimpleName());
        sb.append(" completed in ");
        sb.append(System.currentTimeMillis() - this.start);
        sb.append(" ms");
        Timber.m426d(sb.toString(), new Object[0]);
        this.delegate.onCompleted();
    }
}
