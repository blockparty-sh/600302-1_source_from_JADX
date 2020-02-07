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

public final class RejectProposalResponse extends GeneratedMessageLite<RejectProposalResponse, Builder> implements RejectProposalResponseOrBuilder {
    /* access modifiers changed from: private */
    public static final RejectProposalResponse DEFAULT_INSTANCE = new RejectProposalResponse();
    public static final int ERROR_FIELD_NUMBER = 2;
    private static volatile Parser<RejectProposalResponse> PARSER = null;
    public static final int REJECTED_FIELD_NUMBER = 1;
    private int responseCase_ = 0;
    private Object response_;

    /* renamed from: com.bitcoin.mwallet.RejectProposalResponse$1 */
    static /* synthetic */ class C10341 {

        /* renamed from: $SwitchMap$com$bitcoin$mwallet$RejectProposalResponse$ResponseCase */
        static final /* synthetic */ int[] f186x8547f35c = new int[ResponseCase.values().length];

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
                f187xa1df5c61 = r0
                r0 = 1
                int[] r1 = f187xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r2 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = f187xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = f187xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r3 = f187xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r3 = f187xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r5 = 5
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r3 = f187xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r5 = 6
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r3 = f187xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r5 = 7
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r3 = f187xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r5 = 8
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                com.bitcoin.mwallet.RejectProposalResponse$ResponseCase[] r3 = com.bitcoin.mwallet.RejectProposalResponse.ResponseCase.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                f186x8547f35c = r3
                int[] r3 = f186x8547f35c     // Catch:{ NoSuchFieldError -> 0x0075 }
                com.bitcoin.mwallet.RejectProposalResponse$ResponseCase r4 = com.bitcoin.mwallet.RejectProposalResponse.ResponseCase.REJECTED     // Catch:{ NoSuchFieldError -> 0x0075 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0075 }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0075 }
            L_0x0075:
                int[] r0 = f186x8547f35c     // Catch:{ NoSuchFieldError -> 0x007f }
                com.bitcoin.mwallet.RejectProposalResponse$ResponseCase r3 = com.bitcoin.mwallet.RejectProposalResponse.ResponseCase.ERROR     // Catch:{ NoSuchFieldError -> 0x007f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x007f }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x007f }
            L_0x007f:
                int[] r0 = f186x8547f35c     // Catch:{ NoSuchFieldError -> 0x0089 }
                com.bitcoin.mwallet.RejectProposalResponse$ResponseCase r1 = com.bitcoin.mwallet.RejectProposalResponse.ResponseCase.RESPONSE_NOT_SET     // Catch:{ NoSuchFieldError -> 0x0089 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0089 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0089 }
            L_0x0089:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.RejectProposalResponse.C10341.<clinit>():void");
        }
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<RejectProposalResponse, Builder> implements RejectProposalResponseOrBuilder {
        /* synthetic */ Builder(C10341 r1) {
            this();
        }

        private Builder() {
            super(RejectProposalResponse.DEFAULT_INSTANCE);
        }

        public ResponseCase getResponseCase() {
            return ((RejectProposalResponse) this.instance).getResponseCase();
        }

        public Builder clearResponse() {
            copyOnWrite();
            ((RejectProposalResponse) this.instance).clearResponse();
            return this;
        }

        public boolean getRejected() {
            return ((RejectProposalResponse) this.instance).getRejected();
        }

        public Builder setRejected(boolean z) {
            copyOnWrite();
            ((RejectProposalResponse) this.instance).setRejected(z);
            return this;
        }

        public Builder clearRejected() {
            copyOnWrite();
            ((RejectProposalResponse) this.instance).clearRejected();
            return this;
        }

        public ResponseError getError() {
            return ((RejectProposalResponse) this.instance).getError();
        }

        public Builder setError(ResponseError responseError) {
            copyOnWrite();
            ((RejectProposalResponse) this.instance).setError(responseError);
            return this;
        }

        public Builder setError(com.bitcoin.mwallet.ResponseError.Builder builder) {
            copyOnWrite();
            ((RejectProposalResponse) this.instance).setError(builder);
            return this;
        }

        public Builder mergeError(ResponseError responseError) {
            copyOnWrite();
            ((RejectProposalResponse) this.instance).mergeError(responseError);
            return this;
        }

        public Builder clearError() {
            copyOnWrite();
            ((RejectProposalResponse) this.instance).clearError();
            return this;
        }
    }

    public enum ResponseCase implements EnumLite {
        REJECTED(1),
        ERROR(2),
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
                return REJECTED;
            }
            if (i != 2) {
                return null;
            }
            return ERROR;
        }

        public int getNumber() {
            return this.value;
        }
    }

    private RejectProposalResponse() {
    }

    public ResponseCase getResponseCase() {
        return ResponseCase.forNumber(this.responseCase_);
    }

    /* access modifiers changed from: private */
    public void clearResponse() {
        this.responseCase_ = 0;
        this.response_ = null;
    }

    public boolean getRejected() {
        if (this.responseCase_ == 1) {
            return ((Boolean) this.response_).booleanValue();
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void setRejected(boolean z) {
        this.responseCase_ = 1;
        this.response_ = Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public void clearRejected() {
        if (this.responseCase_ == 1) {
            this.responseCase_ = 0;
            this.response_ = null;
        }
    }

    public ResponseError getError() {
        if (this.responseCase_ == 2) {
            return (ResponseError) this.response_;
        }
        return ResponseError.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setError(ResponseError responseError) {
        if (responseError != null) {
            this.response_ = responseError;
            this.responseCase_ = 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setError(com.bitcoin.mwallet.ResponseError.Builder builder) {
        this.response_ = builder.build();
        this.responseCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void mergeError(ResponseError responseError) {
        if (this.responseCase_ != 2 || this.response_ == ResponseError.getDefaultInstance()) {
            this.response_ = responseError;
        } else {
            this.response_ = ((com.bitcoin.mwallet.ResponseError.Builder) ResponseError.newBuilder((ResponseError) this.response_).mergeFrom(responseError)).buildPartial();
        }
        this.responseCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void clearError() {
        if (this.responseCase_ == 2) {
            this.responseCase_ = 0;
            this.response_ = null;
        }
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.responseCase_ == 1) {
            codedOutputStream.writeBool(1, ((Boolean) this.response_).booleanValue());
        }
        if (this.responseCase_ == 2) {
            codedOutputStream.writeMessage(2, (ResponseError) this.response_);
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.responseCase_ == 1) {
            i2 = 0 + CodedOutputStream.computeBoolSize(1, ((Boolean) this.response_).booleanValue());
        }
        if (this.responseCase_ == 2) {
            i2 += CodedOutputStream.computeMessageSize(2, (ResponseError) this.response_);
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static RejectProposalResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RejectProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static RejectProposalResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RejectProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RejectProposalResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RejectProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static RejectProposalResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RejectProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static RejectProposalResponse parseFrom(InputStream inputStream) throws IOException {
        return (RejectProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RejectProposalResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RejectProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RejectProposalResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (RejectProposalResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RejectProposalResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RejectProposalResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RejectProposalResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (RejectProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RejectProposalResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RejectProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RejectProposalResponse rejectProposalResponse) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(rejectProposalResponse);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new RejectProposalResponse();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder(null);
            case VISIT:
                Visitor visitor = (Visitor) obj;
                RejectProposalResponse rejectProposalResponse = (RejectProposalResponse) obj2;
                int i = C10341.f186x8547f35c[rejectProposalResponse.getResponseCase().ordinal()];
                if (i == 1) {
                    if (this.responseCase_ == 1) {
                        z = true;
                    }
                    this.response_ = visitor.visitOneofBoolean(z, this.response_, rejectProposalResponse.response_);
                } else if (i == 2) {
                    if (this.responseCase_ == 2) {
                        z = true;
                    }
                    this.response_ = visitor.visitOneofMessage(z, this.response_, rejectProposalResponse.response_);
                } else if (i == 3) {
                    if (this.responseCase_ != 0) {
                        z = true;
                    }
                    visitor.visitOneofNotSet(z);
                }
                if (visitor == MergeFromVisitor.INSTANCE) {
                    int i2 = rejectProposalResponse.responseCase_;
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
                            if (readTag == 8) {
                                this.responseCase_ = 1;
                                this.response_ = Boolean.valueOf(codedInputStream.readBool());
                            } else if (readTag == 18) {
                                com.bitcoin.mwallet.ResponseError.Builder builder = this.responseCase_ == 2 ? (com.bitcoin.mwallet.ResponseError.Builder) ((ResponseError) this.response_).toBuilder() : null;
                                this.response_ = codedInputStream.readMessage(ResponseError.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom((ResponseError) this.response_);
                                    this.response_ = builder.buildPartial();
                                }
                                this.responseCase_ = 2;
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
                    synchronized (RejectProposalResponse.class) {
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

    public static RejectProposalResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RejectProposalResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
