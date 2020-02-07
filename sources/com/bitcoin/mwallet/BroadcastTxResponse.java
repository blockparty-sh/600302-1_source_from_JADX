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

public final class BroadcastTxResponse extends GeneratedMessageLite<BroadcastTxResponse, Builder> implements BroadcastTxResponseOrBuilder {
    public static final int BROADCAST_ERROR_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final BroadcastTxResponse DEFAULT_INSTANCE = new BroadcastTxResponse();
    public static final int ERROR_FIELD_NUMBER = 3;
    private static volatile Parser<BroadcastTxResponse> PARSER = null;
    public static final int TX_ID_FIELD_NUMBER = 1;
    private int responseCase_ = 0;
    private Object response_;

    /* renamed from: com.bitcoin.mwallet.BroadcastTxResponse$1 */
    static /* synthetic */ class C08931 {
        static final /* synthetic */ int[] $SwitchMap$com$bitcoin$mwallet$BroadcastTxResponse$ResponseCase = new int[ResponseCase.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|(2:21|22)|23|25|26|27|28|29|30|(3:31|32|34)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Can't wrap try/catch for region: R(30:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0035 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0075 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x007f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0089 */
        static {
            /*
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke[] r0 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f111xa1df5c61 = r0
                r0 = 1
                int[] r1 = f111xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r2 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = f111xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = f111xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                r3 = 4
                int[] r4 = f111xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r5 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r4 = f111xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r5 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r6 = 5
                r4[r5] = r6     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r4 = f111xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r5 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r6 = 6
                r4[r5] = r6     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r4 = f111xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r5 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r6 = 7
                r4[r5] = r6     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r4 = f111xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r5 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r6 = 8
                r4[r5] = r6     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                com.bitcoin.mwallet.BroadcastTxResponse$ResponseCase[] r4 = com.bitcoin.mwallet.BroadcastTxResponse.ResponseCase.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$com$bitcoin$mwallet$BroadcastTxResponse$ResponseCase = r4
                int[] r4 = $SwitchMap$com$bitcoin$mwallet$BroadcastTxResponse$ResponseCase     // Catch:{ NoSuchFieldError -> 0x0075 }
                com.bitcoin.mwallet.BroadcastTxResponse$ResponseCase r5 = com.bitcoin.mwallet.BroadcastTxResponse.ResponseCase.TX_ID     // Catch:{ NoSuchFieldError -> 0x0075 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0075 }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x0075 }
            L_0x0075:
                int[] r0 = $SwitchMap$com$bitcoin$mwallet$BroadcastTxResponse$ResponseCase     // Catch:{ NoSuchFieldError -> 0x007f }
                com.bitcoin.mwallet.BroadcastTxResponse$ResponseCase r4 = com.bitcoin.mwallet.BroadcastTxResponse.ResponseCase.BROADCAST_ERROR     // Catch:{ NoSuchFieldError -> 0x007f }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x007f }
                r0[r4] = r1     // Catch:{ NoSuchFieldError -> 0x007f }
            L_0x007f:
                int[] r0 = $SwitchMap$com$bitcoin$mwallet$BroadcastTxResponse$ResponseCase     // Catch:{ NoSuchFieldError -> 0x0089 }
                com.bitcoin.mwallet.BroadcastTxResponse$ResponseCase r1 = com.bitcoin.mwallet.BroadcastTxResponse.ResponseCase.ERROR     // Catch:{ NoSuchFieldError -> 0x0089 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0089 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0089 }
            L_0x0089:
                int[] r0 = $SwitchMap$com$bitcoin$mwallet$BroadcastTxResponse$ResponseCase     // Catch:{ NoSuchFieldError -> 0x0093 }
                com.bitcoin.mwallet.BroadcastTxResponse$ResponseCase r1 = com.bitcoin.mwallet.BroadcastTxResponse.ResponseCase.RESPONSE_NOT_SET     // Catch:{ NoSuchFieldError -> 0x0093 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0093 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0093 }
            L_0x0093:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.BroadcastTxResponse.C08931.<clinit>():void");
        }
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<BroadcastTxResponse, Builder> implements BroadcastTxResponseOrBuilder {
        /* synthetic */ Builder(C08931 r1) {
            this();
        }

        private Builder() {
            super(BroadcastTxResponse.DEFAULT_INSTANCE);
        }

        public ResponseCase getResponseCase() {
            return ((BroadcastTxResponse) this.instance).getResponseCase();
        }

        public Builder clearResponse() {
            copyOnWrite();
            ((BroadcastTxResponse) this.instance).clearResponse();
            return this;
        }

        public String getTxId() {
            return ((BroadcastTxResponse) this.instance).getTxId();
        }

        public ByteString getTxIdBytes() {
            return ((BroadcastTxResponse) this.instance).getTxIdBytes();
        }

        public Builder setTxId(String str) {
            copyOnWrite();
            ((BroadcastTxResponse) this.instance).setTxId(str);
            return this;
        }

        public Builder clearTxId() {
            copyOnWrite();
            ((BroadcastTxResponse) this.instance).clearTxId();
            return this;
        }

        public Builder setTxIdBytes(ByteString byteString) {
            copyOnWrite();
            ((BroadcastTxResponse) this.instance).setTxIdBytes(byteString);
            return this;
        }

        public int getBroadcastErrorValue() {
            return ((BroadcastTxResponse) this.instance).getBroadcastErrorValue();
        }

        public Builder setBroadcastErrorValue(int i) {
            copyOnWrite();
            ((BroadcastTxResponse) this.instance).setBroadcastErrorValue(i);
            return this;
        }

        public BroadcastTxError getBroadcastError() {
            return ((BroadcastTxResponse) this.instance).getBroadcastError();
        }

        public Builder setBroadcastError(BroadcastTxError broadcastTxError) {
            copyOnWrite();
            ((BroadcastTxResponse) this.instance).setBroadcastError(broadcastTxError);
            return this;
        }

        public Builder clearBroadcastError() {
            copyOnWrite();
            ((BroadcastTxResponse) this.instance).clearBroadcastError();
            return this;
        }

        public ResponseError getError() {
            return ((BroadcastTxResponse) this.instance).getError();
        }

        public Builder setError(ResponseError responseError) {
            copyOnWrite();
            ((BroadcastTxResponse) this.instance).setError(responseError);
            return this;
        }

        public Builder setError(com.bitcoin.mwallet.ResponseError.Builder builder) {
            copyOnWrite();
            ((BroadcastTxResponse) this.instance).setError(builder);
            return this;
        }

        public Builder mergeError(ResponseError responseError) {
            copyOnWrite();
            ((BroadcastTxResponse) this.instance).mergeError(responseError);
            return this;
        }

        public Builder clearError() {
            copyOnWrite();
            ((BroadcastTxResponse) this.instance).clearError();
            return this;
        }
    }

    public enum ResponseCase implements EnumLite {
        TX_ID(1),
        BROADCAST_ERROR(2),
        ERROR(3),
        RESPONSE_NOT_SET(0);
        
        private final int value;

        private ResponseCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static ResponseCase valueOf(int i) {
            return forNumber(i);
        }

        public static ResponseCase forNumber(int i) {
            if (i == 0) {
                return RESPONSE_NOT_SET;
            }
            if (i == 1) {
                return TX_ID;
            }
            if (i == 2) {
                return BROADCAST_ERROR;
            }
            if (i != 3) {
                return null;
            }
            return ERROR;
        }

        public int getNumber() {
            return this.value;
        }
    }

    private BroadcastTxResponse() {
    }

    public ResponseCase getResponseCase() {
        return ResponseCase.forNumber(this.responseCase_);
    }

    /* access modifiers changed from: private */
    public void clearResponse() {
        this.responseCase_ = 0;
        this.response_ = null;
    }

    public String getTxId() {
        return this.responseCase_ == 1 ? (String) this.response_ : "";
    }

    public ByteString getTxIdBytes() {
        return ByteString.copyFromUtf8(this.responseCase_ == 1 ? (String) this.response_ : "");
    }

    /* access modifiers changed from: private */
    public void setTxId(String str) {
        if (str != null) {
            this.responseCase_ = 1;
            this.response_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearTxId() {
        if (this.responseCase_ == 1) {
            this.responseCase_ = 0;
            this.response_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setTxIdBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.responseCase_ = 1;
            this.response_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public int getBroadcastErrorValue() {
        if (this.responseCase_ == 2) {
            return ((Integer) this.response_).intValue();
        }
        return 0;
    }

    public BroadcastTxError getBroadcastError() {
        if (this.responseCase_ != 2) {
            return BroadcastTxError.BROADCAST_UNKNOWN_ERROR;
        }
        BroadcastTxError forNumber = BroadcastTxError.forNumber(((Integer) this.response_).intValue());
        if (forNumber == null) {
            forNumber = BroadcastTxError.UNRECOGNIZED;
        }
        return forNumber;
    }

    /* access modifiers changed from: private */
    public void setBroadcastErrorValue(int i) {
        this.responseCase_ = 2;
        this.response_ = Integer.valueOf(i);
    }

    /* access modifiers changed from: private */
    public void setBroadcastError(BroadcastTxError broadcastTxError) {
        if (broadcastTxError != null) {
            this.responseCase_ = 2;
            this.response_ = Integer.valueOf(broadcastTxError.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearBroadcastError() {
        if (this.responseCase_ == 2) {
            this.responseCase_ = 0;
            this.response_ = null;
        }
    }

    public ResponseError getError() {
        if (this.responseCase_ == 3) {
            return (ResponseError) this.response_;
        }
        return ResponseError.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setError(ResponseError responseError) {
        if (responseError != null) {
            this.response_ = responseError;
            this.responseCase_ = 3;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setError(com.bitcoin.mwallet.ResponseError.Builder builder) {
        this.response_ = builder.build();
        this.responseCase_ = 3;
    }

    /* access modifiers changed from: private */
    public void mergeError(ResponseError responseError) {
        if (this.responseCase_ != 3 || this.response_ == ResponseError.getDefaultInstance()) {
            this.response_ = responseError;
        } else {
            this.response_ = ((com.bitcoin.mwallet.ResponseError.Builder) ResponseError.newBuilder((ResponseError) this.response_).mergeFrom(responseError)).buildPartial();
        }
        this.responseCase_ = 3;
    }

    /* access modifiers changed from: private */
    public void clearError() {
        if (this.responseCase_ == 3) {
            this.responseCase_ = 0;
            this.response_ = null;
        }
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.responseCase_ == 1) {
            codedOutputStream.writeString(1, getTxId());
        }
        if (this.responseCase_ == 2) {
            codedOutputStream.writeEnum(2, ((Integer) this.response_).intValue());
        }
        if (this.responseCase_ == 3) {
            codedOutputStream.writeMessage(3, (ResponseError) this.response_);
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.responseCase_ == 1) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getTxId());
        }
        if (this.responseCase_ == 2) {
            i2 += CodedOutputStream.computeEnumSize(2, ((Integer) this.response_).intValue());
        }
        if (this.responseCase_ == 3) {
            i2 += CodedOutputStream.computeMessageSize(3, (ResponseError) this.response_);
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static BroadcastTxResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (BroadcastTxResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static BroadcastTxResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BroadcastTxResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BroadcastTxResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (BroadcastTxResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BroadcastTxResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BroadcastTxResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BroadcastTxResponse parseFrom(InputStream inputStream) throws IOException {
        return (BroadcastTxResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BroadcastTxResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BroadcastTxResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BroadcastTxResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BroadcastTxResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BroadcastTxResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BroadcastTxResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BroadcastTxResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BroadcastTxResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BroadcastTxResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BroadcastTxResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BroadcastTxResponse broadcastTxResponse) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(broadcastTxResponse);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new BroadcastTxResponse();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder(null);
            case VISIT:
                Visitor visitor = (Visitor) obj;
                BroadcastTxResponse broadcastTxResponse = (BroadcastTxResponse) obj2;
                int i = C08931.$SwitchMap$com$bitcoin$mwallet$BroadcastTxResponse$ResponseCase[broadcastTxResponse.getResponseCase().ordinal()];
                if (i == 1) {
                    if (this.responseCase_ == 1) {
                        z = true;
                    }
                    this.response_ = visitor.visitOneofString(z, this.response_, broadcastTxResponse.response_);
                } else if (i == 2) {
                    if (this.responseCase_ == 2) {
                        z = true;
                    }
                    this.response_ = visitor.visitOneofInt(z, this.response_, broadcastTxResponse.response_);
                } else if (i == 3) {
                    if (this.responseCase_ == 3) {
                        z = true;
                    }
                    this.response_ = visitor.visitOneofMessage(z, this.response_, broadcastTxResponse.response_);
                } else if (i == 4) {
                    if (this.responseCase_ != 0) {
                        z = true;
                    }
                    visitor.visitOneofNotSet(z);
                }
                if (visitor == MergeFromVisitor.INSTANCE) {
                    int i2 = broadcastTxResponse.responseCase_;
                    if (i2 != 0) {
                        this.responseCase_ = i2;
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
                                String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                this.responseCase_ = 1;
                                this.response_ = readStringRequireUtf8;
                            } else if (readTag == 16) {
                                int readEnum = codedInputStream.readEnum();
                                this.responseCase_ = 2;
                                this.response_ = Integer.valueOf(readEnum);
                            } else if (readTag == 26) {
                                com.bitcoin.mwallet.ResponseError.Builder builder = this.responseCase_ == 3 ? (com.bitcoin.mwallet.ResponseError.Builder) ((ResponseError) this.response_).toBuilder() : null;
                                this.response_ = codedInputStream.readMessage(ResponseError.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom((ResponseError) this.response_);
                                    this.response_ = builder.buildPartial();
                                }
                                this.responseCase_ = 3;
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
                    synchronized (BroadcastTxResponse.class) {
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

    public static BroadcastTxResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BroadcastTxResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
