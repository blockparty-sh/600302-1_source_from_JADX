package com.bitcoin.mwallet.zion;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0001\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/zion/ZionError;", "", "(Ljava/lang/String;I)V", "NOT_SUCCESS", "NO_MANAGER", "BAD_ARGUMENTS", "USER_CANCEL", "USER_TIMEOUT", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ZionError.kt */
public enum ZionError {
    NOT_SUCCESS,
    NO_MANAGER,
    BAD_ARGUMENTS,
    USER_CANCEL,
    USER_TIMEOUT;
    
    public static final Companion Companion = null;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/zion/ZionError$Companion;", "", "()V", "fromResult", "Lcom/bitcoin/mwallet/zion/ZionError;", "result", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ZionError.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final ZionError fromResult(int i) {
            if (i == -805) {
                return ZionError.USER_TIMEOUT;
            }
            if (i == -101) {
                return ZionError.USER_CANCEL;
            }
            if (i != 0) {
                return ZionError.NOT_SUCCESS;
            }
            return null;
        }
    }

    static {
        Companion = new Companion(null);
    }
}
