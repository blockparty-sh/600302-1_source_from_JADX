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

public final class SubscriptionRequest extends GeneratedMessageLite<SubscriptionRequest, Builder> implements SubscriptionRequestOrBuilder {
    public static final int COPAYER_ID_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final SubscriptionRequest DEFAULT_INSTANCE = new SubscriptionRequest();
    private static volatile Parser<SubscriptionRequest> PARSER = null;
    public static final int SINGLE_SIG_WALLET_ID_FIELD_NUMBER = 1;
    public static final int TOKEN_FIELD_NUMBER = 3;
    private int idCase_ = 0;
    private Object id_;
    private String token_ = "";

    /* renamed from: com.bitcoin.mwallet.SubscriptionRequest$1 */
    static /* synthetic */ class C10501 {
        static final /* synthetic */ int[] $SwitchMap$com$bitcoin$mwallet$SubscriptionRequest$IdCase = new int[IdCase.values().length];

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
                f203xa1df5c61 = r0
                r0 = 1
                int[] r1 = f203xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r2 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = f203xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = f203xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r3 = f203xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r3 = f203xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r5 = 5
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r3 = f203xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r5 = 6
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r3 = f203xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r5 = 7
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r3 = f203xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r5 = 8
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                com.bitcoin.mwallet.SubscriptionRequest$IdCase[] r3 = com.bitcoin.mwallet.SubscriptionRequest.IdCase.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$bitcoin$mwallet$SubscriptionRequest$IdCase = r3
                int[] r3 = $SwitchMap$com$bitcoin$mwallet$SubscriptionRequest$IdCase     // Catch:{ NoSuchFieldError -> 0x0075 }
                com.bitcoin.mwallet.SubscriptionRequest$IdCase r4 = com.bitcoin.mwallet.SubscriptionRequest.IdCase.SINGLE_SIG_WALLET_ID     // Catch:{ NoSuchFieldError -> 0x0075 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0075 }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0075 }
            L_0x0075:
                int[] r0 = $SwitchMap$com$bitcoin$mwallet$SubscriptionRequest$IdCase     // Catch:{ NoSuchFieldError -> 0x007f }
                com.bitcoin.mwallet.SubscriptionRequest$IdCase r3 = com.bitcoin.mwallet.SubscriptionRequest.IdCase.COPAYER_ID     // Catch:{ NoSuchFieldError -> 0x007f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x007f }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x007f }
            L_0x007f:
                int[] r0 = $SwitchMap$com$bitcoin$mwallet$SubscriptionRequest$IdCase     // Catch:{ NoSuchFieldError -> 0x0089 }
                com.bitcoin.mwallet.SubscriptionRequest$IdCase r1 = com.bitcoin.mwallet.SubscriptionRequest.IdCase.ID_NOT_SET     // Catch:{ NoSuchFieldError -> 0x0089 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0089 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0089 }
            L_0x0089:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.SubscriptionRequest.C10501.<clinit>():void");
        }
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<SubscriptionRequest, Builder> implements SubscriptionRequestOrBuilder {
        /* synthetic */ Builder(C10501 r1) {
            this();
        }

        private Builder() {
            super(SubscriptionRequest.DEFAULT_INSTANCE);
        }

        public IdCase getIdCase() {
            return ((SubscriptionRequest) this.instance).getIdCase();
        }

        public Builder clearId() {
            copyOnWrite();
            ((SubscriptionRequest) this.instance).clearId();
            return this;
        }

        public String getSingleSigWalletId() {
            return ((SubscriptionRequest) this.instance).getSingleSigWalletId();
        }

        public ByteString getSingleSigWalletIdBytes() {
            return ((SubscriptionRequest) this.instance).getSingleSigWalletIdBytes();
        }

        public Builder setSingleSigWalletId(String str) {
            copyOnWrite();
            ((SubscriptionRequest) this.instance).setSingleSigWalletId(str);
            return this;
        }

        public Builder clearSingleSigWalletId() {
            copyOnWrite();
            ((SubscriptionRequest) this.instance).clearSingleSigWalletId();
            return this;
        }

        public Builder setSingleSigWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((SubscriptionRequest) this.instance).setSingleSigWalletIdBytes(byteString);
            return this;
        }

        public String getCopayerId() {
            return ((SubscriptionRequest) this.instance).getCopayerId();
        }

        public ByteString getCopayerIdBytes() {
            return ((SubscriptionRequest) this.instance).getCopayerIdBytes();
        }

        public Builder setCopayerId(String str) {
            copyOnWrite();
            ((SubscriptionRequest) this.instance).setCopayerId(str);
            return this;
        }

        public Builder clearCopayerId() {
            copyOnWrite();
            ((SubscriptionRequest) this.instance).clearCopayerId();
            return this;
        }

        public Builder setCopayerIdBytes(ByteString byteString) {
            copyOnWrite();
            ((SubscriptionRequest) this.instance).setCopayerIdBytes(byteString);
            return this;
        }

        public String getToken() {
            return ((SubscriptionRequest) this.instance).getToken();
        }

        public ByteString getTokenBytes() {
            return ((SubscriptionRequest) this.instance).getTokenBytes();
        }

        public Builder setToken(String str) {
            copyOnWrite();
            ((SubscriptionRequest) this.instance).setToken(str);
            return this;
        }

        public Builder clearToken() {
            copyOnWrite();
            ((SubscriptionRequest) this.instance).clearToken();
            return this;
        }

        public Builder setTokenBytes(ByteString byteString) {
            copyOnWrite();
            ((SubscriptionRequest) this.instance).setTokenBytes(byteString);
            return this;
        }
    }

    public enum IdCase implements EnumLite {
        SINGLE_SIG_WALLET_ID(1),
        COPAYER_ID(2),
        ID_NOT_SET(0);
        
        private final int value;

        private IdCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static IdCase valueOf(int i) {
            return forNumber(i);
        }

        public static IdCase forNumber(int i) {
            if (i == 0) {
                return ID_NOT_SET;
            }
            if (i == 1) {
                return SINGLE_SIG_WALLET_ID;
            }
            if (i != 2) {
                return null;
            }
            return COPAYER_ID;
        }

        public int getNumber() {
            return this.value;
        }
    }

    private SubscriptionRequest() {
    }

    public IdCase getIdCase() {
        return IdCase.forNumber(this.idCase_);
    }

    /* access modifiers changed from: private */
    public void clearId() {
        this.idCase_ = 0;
        this.id_ = null;
    }

    public String getSingleSigWalletId() {
        return this.idCase_ == 1 ? (String) this.id_ : "";
    }

    public ByteString getSingleSigWalletIdBytes() {
        return ByteString.copyFromUtf8(this.idCase_ == 1 ? (String) this.id_ : "");
    }

    /* access modifiers changed from: private */
    public void setSingleSigWalletId(String str) {
        if (str != null) {
            this.idCase_ = 1;
            this.id_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearSingleSigWalletId() {
        if (this.idCase_ == 1) {
            this.idCase_ = 0;
            this.id_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setSingleSigWalletIdBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.idCase_ = 1;
            this.id_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getCopayerId() {
        return this.idCase_ == 2 ? (String) this.id_ : "";
    }

    public ByteString getCopayerIdBytes() {
        return ByteString.copyFromUtf8(this.idCase_ == 2 ? (String) this.id_ : "");
    }

    /* access modifiers changed from: private */
    public void setCopayerId(String str) {
        if (str != null) {
            this.idCase_ = 2;
            this.id_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearCopayerId() {
        if (this.idCase_ == 2) {
            this.idCase_ = 0;
            this.id_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setCopayerIdBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.idCase_ = 2;
            this.id_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getToken() {
        return this.token_;
    }

    public ByteString getTokenBytes() {
        return ByteString.copyFromUtf8(this.token_);
    }

    /* access modifiers changed from: private */
    public void setToken(String str) {
        if (str != null) {
            this.token_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearToken() {
        this.token_ = getDefaultInstance().getToken();
    }

    /* access modifiers changed from: private */
    public void setTokenBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.token_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.idCase_ == 1) {
            codedOutputStream.writeString(1, getSingleSigWalletId());
        }
        if (this.idCase_ == 2) {
            codedOutputStream.writeString(2, getCopayerId());
        }
        if (!this.token_.isEmpty()) {
            codedOutputStream.writeString(3, getToken());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.idCase_ == 1) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getSingleSigWalletId());
        }
        if (this.idCase_ == 2) {
            i2 += CodedOutputStream.computeStringSize(2, getCopayerId());
        }
        if (!this.token_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(3, getToken());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static SubscriptionRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (SubscriptionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static SubscriptionRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SubscriptionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static SubscriptionRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (SubscriptionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static SubscriptionRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SubscriptionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static SubscriptionRequest parseFrom(InputStream inputStream) throws IOException {
        return (SubscriptionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static SubscriptionRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SubscriptionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static SubscriptionRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SubscriptionRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static SubscriptionRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SubscriptionRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static SubscriptionRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SubscriptionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static SubscriptionRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SubscriptionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SubscriptionRequest subscriptionRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(subscriptionRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new SubscriptionRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder(null);
            case VISIT:
                Visitor visitor = (Visitor) obj;
                SubscriptionRequest subscriptionRequest = (SubscriptionRequest) obj2;
                this.token_ = visitor.visitString(!this.token_.isEmpty(), this.token_, !subscriptionRequest.token_.isEmpty(), subscriptionRequest.token_);
                int i = C10501.$SwitchMap$com$bitcoin$mwallet$SubscriptionRequest$IdCase[subscriptionRequest.getIdCase().ordinal()];
                if (i == 1) {
                    if (this.idCase_ == 1) {
                        z = true;
                    }
                    this.id_ = visitor.visitOneofString(z, this.id_, subscriptionRequest.id_);
                } else if (i == 2) {
                    if (this.idCase_ == 2) {
                        z = true;
                    }
                    this.id_ = visitor.visitOneofString(z, this.id_, subscriptionRequest.id_);
                } else if (i == 3) {
                    if (this.idCase_ != 0) {
                        z = true;
                    }
                    visitor.visitOneofNotSet(z);
                }
                if (visitor == MergeFromVisitor.INSTANCE) {
                    int i2 = subscriptionRequest.idCase_;
                    if (i2 != 0) {
                        this.idCase_ = i2;
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
                                this.idCase_ = 1;
                                this.id_ = readStringRequireUtf8;
                            } else if (readTag == 18) {
                                String readStringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                                this.idCase_ = 2;
                                this.id_ = readStringRequireUtf82;
                            } else if (readTag == 26) {
                                this.token_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (SubscriptionRequest.class) {
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

    public static SubscriptionRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SubscriptionRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
