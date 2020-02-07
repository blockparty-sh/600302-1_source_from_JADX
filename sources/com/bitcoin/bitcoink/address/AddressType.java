package com.bitcoin.bitcoink.address;

import com.bitcoin.bitcoink.util.ByteUtils;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0007\b\u0001\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\n"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/address/AddressType;", "", "byte", "", "(Ljava/lang/String;IB)V", "getByte", "()B", "PUBKEY", "SCRIPT", "Companion", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AddressType.kt */
public enum AddressType {
    PUBKEY((byte) 0),
    SCRIPT((byte) 1);
    
    public static final Companion Companion = null;

    /* renamed from: byte reason: not valid java name */
    private final byte f1564byte;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/address/AddressType$Companion;", "", "()V", "fromVersion", "Lcom/bitcoin/bitcoink/address/AddressType;", "version", "", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: AddressType.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final AddressType fromVersion(byte b) {
            int shr = ByteUtils.INSTANCE.shr(b, 3) & 31;
            if (shr == 0) {
                return AddressType.PUBKEY;
            }
            if (shr != 1) {
                return null;
            }
            return AddressType.SCRIPT;
        }
    }

    private AddressType(byte b) {
        this.f1564byte = b;
    }

    public final byte getByte() {
        return this.f1564byte;
    }

    static {
        Companion = new Companion(null);
    }
}
