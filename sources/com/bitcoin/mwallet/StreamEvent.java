package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.MethodToInvoke;
import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class StreamEvent extends GeneratedMessageLite<StreamEvent, Builder> implements StreamEventOrBuilder {
    /* access modifiers changed from: private */
    public static final StreamEvent DEFAULT_INSTANCE = new StreamEvent();
    private static volatile Parser<StreamEvent> PARSER = null;
    public static final int TX_HISTORY_FIELD_NUMBER = 1;
    public static final int UTXOS_FIELD_NUMBER = 2;
    private int eventCase_ = 0;
    private Object event_;

    /* renamed from: com.bitcoin.mwallet.StreamEvent$1 */
    static /* synthetic */ class C10461 {
        static final /* synthetic */ int[] $SwitchMap$com$bitcoin$mwallet$StreamEvent$EventCase = new int[EventCase.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|(2:19|20)|21|23|24|25|26|27|28|30) */
        /* JADX WARNING: Can't wrap try/catch for region: R(25:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|(2:19|20)|21|23|24|25|26|27|28|30) */
        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|21|23|24|25|26|27|28|30) */
        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|1|2|3|5|6|7|9|10|11|12|13|14|15|16|17|18|19|20|21|23|24|25|26|27|28|30) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0035 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0075 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x007f */
        static {
            /*
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke[] r0 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f199xa1df5c61 = r0
                r0 = 1
                int[] r1 = f199xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r2 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = f199xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = f199xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r3 = f199xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r3 = f199xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r5 = 5
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r3 = f199xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r5 = 6
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r3 = f199xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r5 = 7
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r3 = f199xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r5 = 8
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                com.bitcoin.mwallet.StreamEvent$EventCase[] r3 = com.bitcoin.mwallet.StreamEvent.EventCase.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$bitcoin$mwallet$StreamEvent$EventCase = r3
                int[] r3 = $SwitchMap$com$bitcoin$mwallet$StreamEvent$EventCase     // Catch:{ NoSuchFieldError -> 0x0075 }
                com.bitcoin.mwallet.StreamEvent$EventCase r4 = com.bitcoin.mwallet.StreamEvent.EventCase.TX_HISTORY     // Catch:{ NoSuchFieldError -> 0x0075 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0075 }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0075 }
            L_0x0075:
                int[] r0 = $SwitchMap$com$bitcoin$mwallet$StreamEvent$EventCase     // Catch:{ NoSuchFieldError -> 0x007f }
                com.bitcoin.mwallet.StreamEvent$EventCase r3 = com.bitcoin.mwallet.StreamEvent.EventCase.UTXOS     // Catch:{ NoSuchFieldError -> 0x007f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x007f }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x007f }
            L_0x007f:
                int[] r0 = $SwitchMap$com$bitcoin$mwallet$StreamEvent$EventCase     // Catch:{ NoSuchFieldError -> 0x0089 }
                com.bitcoin.mwallet.StreamEvent$EventCase r1 = com.bitcoin.mwallet.StreamEvent.EventCase.EVENT_NOT_SET     // Catch:{ NoSuchFieldError -> 0x0089 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0089 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0089 }
            L_0x0089:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.StreamEvent.C10461.<clinit>():void");
        }
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<StreamEvent, Builder> implements StreamEventOrBuilder {
        /* synthetic */ Builder(C10461 r1) {
            this();
        }

        private Builder() {
            super(StreamEvent.DEFAULT_INSTANCE);
        }

        public EventCase getEventCase() {
            return ((StreamEvent) this.instance).getEventCase();
        }

        public Builder clearEvent() {
            copyOnWrite();
            ((StreamEvent) this.instance).clearEvent();
            return this;
        }

        public TxHistory getTxHistory() {
            return ((StreamEvent) this.instance).getTxHistory();
        }

        public Builder setTxHistory(TxHistory txHistory) {
            copyOnWrite();
            ((StreamEvent) this.instance).setTxHistory(txHistory);
            return this;
        }

        public Builder setTxHistory(com.bitcoin.mwallet.TxHistory.Builder builder) {
            copyOnWrite();
            ((StreamEvent) this.instance).setTxHistory(builder);
            return this;
        }

        public Builder mergeTxHistory(TxHistory txHistory) {
            copyOnWrite();
            ((StreamEvent) this.instance).mergeTxHistory(txHistory);
            return this;
        }

        public Builder clearTxHistory() {
            copyOnWrite();
            ((StreamEvent) this.instance).clearTxHistory();
            return this;
        }

        public WalletUtxos getUtxos() {
            return ((StreamEvent) this.instance).getUtxos();
        }

        public Builder setUtxos(WalletUtxos walletUtxos) {
            copyOnWrite();
            ((StreamEvent) this.instance).setUtxos(walletUtxos);
            return this;
        }

        public Builder setUtxos(com.bitcoin.mwallet.WalletUtxos.Builder builder) {
            copyOnWrite();
            ((StreamEvent) this.instance).setUtxos(builder);
            return this;
        }

        public Builder mergeUtxos(WalletUtxos walletUtxos) {
            copyOnWrite();
            ((StreamEvent) this.instance).mergeUtxos(walletUtxos);
            return this;
        }

        public Builder clearUtxos() {
            copyOnWrite();
            ((StreamEvent) this.instance).clearUtxos();
            return this;
        }
    }

    public enum EventCase implements EnumLite {
        TX_HISTORY(1),
        UTXOS(2),
        EVENT_NOT_SET(0);
        
        private final int value;

        private EventCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static EventCase valueOf(int i) {
            return forNumber(i);
        }

        public static EventCase forNumber(int i) {
            if (i == 0) {
                return EVENT_NOT_SET;
            }
            if (i == 1) {
                return TX_HISTORY;
            }
            if (i != 2) {
                return null;
            }
            return UTXOS;
        }

        public int getNumber() {
            return this.value;
        }
    }

    private StreamEvent() {
    }

    public EventCase getEventCase() {
        return EventCase.forNumber(this.eventCase_);
    }

    /* access modifiers changed from: private */
    public void clearEvent() {
        this.eventCase_ = 0;
        this.event_ = null;
    }

    public TxHistory getTxHistory() {
        if (this.eventCase_ == 1) {
            return (TxHistory) this.event_;
        }
        return TxHistory.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setTxHistory(TxHistory txHistory) {
        if (txHistory != null) {
            this.event_ = txHistory;
            this.eventCase_ = 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setTxHistory(com.bitcoin.mwallet.TxHistory.Builder builder) {
        this.event_ = builder.build();
        this.eventCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void mergeTxHistory(TxHistory txHistory) {
        if (this.eventCase_ != 1 || this.event_ == TxHistory.getDefaultInstance()) {
            this.event_ = txHistory;
        } else {
            this.event_ = ((com.bitcoin.mwallet.TxHistory.Builder) TxHistory.newBuilder((TxHistory) this.event_).mergeFrom(txHistory)).buildPartial();
        }
        this.eventCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void clearTxHistory() {
        if (this.eventCase_ == 1) {
            this.eventCase_ = 0;
            this.event_ = null;
        }
    }

    public WalletUtxos getUtxos() {
        if (this.eventCase_ == 2) {
            return (WalletUtxos) this.event_;
        }
        return WalletUtxos.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setUtxos(WalletUtxos walletUtxos) {
        if (walletUtxos != null) {
            this.event_ = walletUtxos;
            this.eventCase_ = 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setUtxos(com.bitcoin.mwallet.WalletUtxos.Builder builder) {
        this.event_ = builder.build();
        this.eventCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void mergeUtxos(WalletUtxos walletUtxos) {
        if (this.eventCase_ != 2 || this.event_ == WalletUtxos.getDefaultInstance()) {
            this.event_ = walletUtxos;
        } else {
            this.event_ = ((com.bitcoin.mwallet.WalletUtxos.Builder) WalletUtxos.newBuilder((WalletUtxos) this.event_).mergeFrom(walletUtxos)).buildPartial();
        }
        this.eventCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void clearUtxos() {
        if (this.eventCase_ == 2) {
            this.eventCase_ = 0;
            this.event_ = null;
        }
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.eventCase_ == 1) {
            codedOutputStream.writeMessage(1, (TxHistory) this.event_);
        }
        if (this.eventCase_ == 2) {
            codedOutputStream.writeMessage(2, (WalletUtxos) this.event_);
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.eventCase_ == 1) {
            i2 = 0 + CodedOutputStream.computeMessageSize(1, (TxHistory) this.event_);
        }
        if (this.eventCase_ == 2) {
            i2 += CodedOutputStream.computeMessageSize(2, (WalletUtxos) this.event_);
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static StreamEvent parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (StreamEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static StreamEvent parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StreamEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static StreamEvent parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (StreamEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static StreamEvent parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StreamEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static StreamEvent parseFrom(InputStream inputStream) throws IOException {
        return (StreamEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static StreamEvent parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StreamEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static StreamEvent parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (StreamEvent) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static StreamEvent parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StreamEvent) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static StreamEvent parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (StreamEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static StreamEvent parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StreamEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(StreamEvent streamEvent) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(streamEvent);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new StreamEvent();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder(null);
            case VISIT:
                Visitor visitor = (Visitor) obj;
                StreamEvent streamEvent = (StreamEvent) obj2;
                int i = C10461.$SwitchMap$com$bitcoin$mwallet$StreamEvent$EventCase[streamEvent.getEventCase().ordinal()];
                if (i == 1) {
                    if (this.eventCase_ == 1) {
                        z = true;
                    }
                    this.event_ = visitor.visitOneofMessage(z, this.event_, streamEvent.event_);
                } else if (i == 2) {
                    if (this.eventCase_ == 2) {
                        z = true;
                    }
                    this.event_ = visitor.visitOneofMessage(z, this.event_, streamEvent.event_);
                } else if (i == 3) {
                    if (this.eventCase_ != 0) {
                        z = true;
                    }
                    visitor.visitOneofNotSet(z);
                }
                if (visitor == MergeFromVisitor.INSTANCE) {
                    int i2 = streamEvent.eventCase_;
                    if (i2 != 0) {
                        this.eventCase_ = i2;
                    }
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                com.bitcoin.mwallet.TxHistory.Builder builder = this.eventCase_ == 1 ? (com.bitcoin.mwallet.TxHistory.Builder) ((TxHistory) this.event_).toBuilder() : null;
                                this.event_ = codedInputStream.readMessage(TxHistory.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom((TxHistory) this.event_);
                                    this.event_ = builder.buildPartial();
                                }
                                this.eventCase_ = 1;
                            } else if (readTag == 18) {
                                com.bitcoin.mwallet.WalletUtxos.Builder builder2 = this.eventCase_ == 2 ? (com.bitcoin.mwallet.WalletUtxos.Builder) ((WalletUtxos) this.event_).toBuilder() : null;
                                this.event_ = codedInputStream.readMessage(WalletUtxos.parser(), extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom((WalletUtxos) this.event_);
                                    this.event_ = builder2.buildPartial();
                                }
                                this.eventCase_ = 2;
                            } else if (!codedInputStream.skipField(readTag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case GET_DEFAULT_INSTANCE:
                break;
            case GET_PARSER:
                if (PARSER == null) {
                    synchronized (StreamEvent.class) {
                        if (PARSER == null) {
                            PARSER = new DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                        }
                    }
                }
                return PARSER;
            default:
                throw new UnsupportedOperationException();
        }
        return DEFAULT_INSTANCE;
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    public static StreamEvent getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<StreamEvent> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
