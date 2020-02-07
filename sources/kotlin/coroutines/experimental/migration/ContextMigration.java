package kotlin.coroutines.experimental.migration;

import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext.C2916Key;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, mo37405d2 = {"Lkotlin/coroutines/experimental/migration/ContextMigration;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "(Lkotlin/coroutines/experimental/CoroutineContext;)V", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "Key", "kotlin-stdlib-coroutines"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CoroutinesMigration.kt */
final class ContextMigration extends AbstractCoroutineContextElement {
    public static final C2923Key Key = new C2923Key(null);
    @NotNull
    private final CoroutineContext context;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, mo37405d2 = {"Lkotlin/coroutines/experimental/migration/ContextMigration$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlin/coroutines/experimental/migration/ContextMigration;", "()V", "kotlin-stdlib-coroutines"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* renamed from: kotlin.coroutines.experimental.migration.ContextMigration$Key */
    /* compiled from: CoroutinesMigration.kt */
    public static final class C2923Key implements C2916Key<ContextMigration> {
        private C2923Key() {
        }

        public /* synthetic */ C2923Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ContextMigration(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        super(Key);
        this.context = coroutineContext;
    }

    @NotNull
    public final CoroutineContext getContext() {
        return this.context;
    }
}
