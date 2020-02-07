package com.bitcoin.mwallet.core.qrscan;

import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/qrscan/PermissionsReceiver;", "", "onPermissionResult", "", "grantResult", "", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: PermissionsReceiver.kt */
public interface PermissionsReceiver {
    public static final Companion Companion = Companion.$$INSTANCE;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/qrscan/PermissionsReceiver$Companion;", "", "()V", "REQ_CODE_CAMERA", "", "getREQ_CODE_CAMERA", "()I", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: PermissionsReceiver.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final int REQ_CODE_CAMERA = REQ_CODE_CAMERA;

        private Companion() {
        }

        public final int getREQ_CODE_CAMERA() {
            return REQ_CODE_CAMERA;
        }
    }

    void onPermissionResult(int i);
}
