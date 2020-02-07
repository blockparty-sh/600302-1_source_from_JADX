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

public final class SigningResponse extends GeneratedMessageLite<SigningResponse, Builder> implements SigningResponseOrBuilder {
    public static final int BROADCASTED_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final SigningResponse DEFAULT_INSTANCE = new SigningResponse();
    public static final int ERROR_FIELD_NUMBER = 1;
    public static final int NUM_MORE_SIGNATURES_REQUIRED_FIELD_NUMBER = 3;
    private static volatile Parser<SigningResponse> PARSER;
    private int responseCase_ = 0;
    private Object response_;

    /* renamed from: com.bitcoin.mwallet.SigningResponse$1 */
    static /* synthetic */ class C10451 {
        static final /* synthetic */ int[] $SwitchMap$com$bitcoin$mwallet$SigningResponse$ResponseCase = new int[ResponseCase.values().length];

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
                f198xa1df5c61 = r0
                r0 = 1
                int[] r1 = f198xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r2 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = f198xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = f198xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                r3 = 4
                int[] r4 = f198xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r5 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r4 = f198xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r5 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r6 = 5
                r4[r5] = r6     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r4 = f198xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r5 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r6 = 6
                r4[r5] = r6     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r4 = f198xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r5 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r6 = 7
                r4[r5] = r6     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r4 = f198xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r5 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r6 = 8
                r4[r5] = r6     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                com.bitcoin.mwallet.SigningResponse$ResponseCase[] r4 = com.bitcoin.mwallet.SigningResponse.ResponseCase.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$com$bitcoin$mwallet$SigningResponse$ResponseCase = r4
                int[] r4 = $SwitchMap$com$bitcoin$mwallet$SigningResponse$ResponseCase     // Catch:{ NoSuchFieldError -> 0x0075 }
                com.bitcoin.mwallet.SigningResponse$ResponseCase r5 = com.bitcoin.mwallet.SigningResponse.ResponseCase.ERROR     // Catch:{ NoSuchFieldError -> 0x0075 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0075 }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x0075 }
            L_0x0075:
                int[] r0 = $SwitchMap$com$bitcoin$mwallet$SigningResponse$ResponseCase     // Catch:{ NoSuchFieldError -> 0x007f }
                com.bitcoin.mwallet.SigningResponse$ResponseCase r4 = com.bitcoin.mwallet.SigningResponse.ResponseCase.BROADCASTED     // Catch:{ NoSuchFieldError -> 0x007f }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x007f }
                r0[r4] = r1     // Catch:{ NoSuchFieldError -> 0x007f }
            L_0x007f:
                int[] r0 = $SwitchMap$com$bitcoin$mwallet$SigningResponse$ResponseCase     // Catch:{ NoSuchFieldError -> 0x0089 }
                com.bitcoin.mwallet.SigningResponse$ResponseCase r1 = com.bitcoin.mwallet.SigningResponse.ResponseCase.NUM_MORE_SIGNATURES_REQUIRED     // Catch:{ NoSuchFieldError -> 0x0089 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0089 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0089 }
            L_0x0089:
                int[] r0 = $SwitchMap$com$bitcoin$mwallet$SigningResponse$ResponseCase     // Catch:{ NoSuchFieldError -> 0x0093 }
                com.bitcoin.mwallet.SigningResponse$ResponseCase r1 = com.bitcoin.mwallet.SigningResponse.ResponseCase.RESPONSE_NOT_SET     // Catch:{ NoSuchFieldError -> 0x0093 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0093 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0093 }
            L_0x0093:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.SigningResponse.C10451.<clinit>():void");
        }
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<SigningResponse, Builder> implements SigningResponseOrBuilder {
        /* synthetic */ Builder(C10451 r1) {
            this();
        }

        private Builder() {
            super(SigningResponse.DEFAULT_INSTANCE);
        }

        public ResponseCase getResponseCase() {
            return ((SigningResponse) this.instance).getResponseCase();
        }

        public Builder clearResponse() {
            copyOnWrite();
            ((SigningResponse) this.instance).clearResponse();
            return this;
        }

        public int getErrorValue() {
            return ((SigningResponse) this.instance).getErrorValue();
        }

        public Builder setErrorValue(int i) {
            copyOnWrite();
            ((SigningResponse) this.instance).setErrorValue(i);
            return this;
        }

        public BroadcastProposalError getError() {
            return ((SigningResponse) this.instance).getError();
        }

        public Builder setError(BroadcastProposalError broadcastProposalError) {
            copyOnWrite();
            ((SigningResponse) this.instance).setError(broadcastProposalError);
            return this;
        }

        public Builder clearError() {
            copyOnWrite();
            ((SigningResponse) this.instance).clearError();
            return this;
        }

        public boolean getBroadcasted() {
            return ((SigningResponse) this.instance).getBroadcasted();
        }

        public Builder setBroadcasted(boolean z) {
            copyOnWrite();
            ((SigningResponse) this.instance).setBroadcasted(z);
            return this;
        }

        public Builder clearBroadcasted() {
            copyOnWrite();
            ((SigningResponse) this.instance).clearBroadcasted();
            return this;
        }

        public int getNumMoreSignaturesRequired() {
            return ((SigningResponse) this.instance).getNumMoreSignaturesRequired();
        }

        public Builder setNumMoreSignaturesRequired(int i) {
            copyOnWrite();
            ((SigningResponse) this.instance).setNumMoreSignaturesRequired(i);
            return this;
        }

        public Builder clearNumMoreSignaturesRequired() {
            copyOnWrite();
            ((SigningResponse) this.instance).clearNumMoreSignaturesRequired();
            return this;
        }
    }

    public enum ResponseCase implements EnumLite {
        ERROR(1),
        BROADCASTED(2),
        NUM_MORE_SIGNATURES_REQUIRED(3),
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
                return ERROR;
            }
            if (i == 2) {
                return BROADCASTED;
            }
            if (i != 3) {
                return null;
            }
            return NUM_MORE_SIGNATURES_REQUIRED;
        }

        public int getNumber() {
            return this.value;
        }
    }

    private SigningResponse() {
    }

    public ResponseCase getResponseCase() {
        return ResponseCase.forNumber(this.responseCase_);
    }

    /* access modifiers changed from: private */
    public void clearResponse() {
        this.responseCase_ = 0;
        this.response_ = null;
    }

    public int getErrorValue() {
        if (this.responseCase_ == 1) {
            return ((Integer) this.response_).intValue();
        }
        return 0;
    }

    public BroadcastProposalError getError() {
        if (this.responseCase_ != 1) {
            return BroadcastProposalError.BROADCAST_PROPOSAL_UNKNOWN_ERROR;
        }
        BroadcastProposalError forNumber = BroadcastProposalError.forNumber(((Integer) this.response_).intValue());
        if (forNumber == null) {
            forNumber = BroadcastProposalError.UNRECOGNIZED;
        }
        return forNumber;
    }

    /* access modifiers changed from: private */
    public void setErrorValue(int i) {
        this.responseCase_ = 1;
        this.response_ = Integer.valueOf(i);
    }

    /* access modifiers changed from: private */
    public void setError(BroadcastProposalError broadcastProposalError) {
        if (broadcastProposalError != null) {
            this.responseCase_ = 1;
            this.response_ = Integer.valueOf(broadcastProposalError.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearError() {
        if (this.responseCase_ == 1) {
            this.responseCase_ = 0;
            this.response_ = null;
        }
    }

    public boolean getBroadcasted() {
        if (this.responseCase_ == 2) {
            return ((Boolean) this.response_).booleanValue();
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void setBroadcasted(boolean z) {
        this.responseCase_ = 2;
        this.response_ = Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public void clearBroadcasted() {
        if (this.responseCase_ == 2) {
            this.responseCase_ = 0;
            this.response_ = null;
        }
    }

    public int getNumMoreSignaturesRequired() {
        if (this.responseCase_ == 3) {
            return ((Integer) this.response_).intValue();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public void setNumMoreSignaturesRequired(int i) {
        this.responseCase_ = 3;
        this.response_ = Integer.valueOf(i);
    }

    /* access modifiers changed from: private */
    public void clearNumMoreSignaturesRequired() {
        if (this.responseCase_ == 3) {
            this.responseCase_ = 0;
            this.response_ = null;
        }
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.responseCase_ == 1) {
            codedOutputStream.writeEnum(1, ((Integer) this.response_).intValue());
        }
        if (this.responseCase_ == 2) {
            codedOutputStream.writeBool(2, ((Boolean) this.response_).booleanValue());
        }
        if (this.responseCase_ == 3) {
            codedOutputStream.writeInt32(3, ((Integer) this.response_).intValue());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.responseCase_ == 1) {
            i2 = 0 + CodedOutputStream.computeEnumSize(1, ((Integer) this.response_).intValue());
        }
        if (this.responseCase_ == 2) {
            i2 += CodedOutputStream.computeBoolSize(2, ((Boolean) this.response_).booleanValue());
        }
        if (this.responseCase_ == 3) {
            i2 += CodedOutputStream.computeInt32Size(3, ((Integer) this.response_).intValue());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static SigningResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (SigningResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static SigningResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SigningResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static SigningResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (SigningResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static SigningResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SigningResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static SigningResponse parseFrom(InputStream inputStream) throws IOException {
        return (SigningResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static SigningResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SigningResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static SigningResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SigningResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static SigningResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SigningResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static SigningResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SigningResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static SigningResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SigningResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SigningResponse signingResponse) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(signingResponse);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new SigningResponse();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder(null);
            case VISIT:
                Visitor visitor = (Visitor) obj;
                SigningResponse signingResponse = (SigningResponse) obj2;
                int i = C10451.$SwitchMap$com$bitcoin$mwallet$SigningResponse$ResponseCase[signingResponse.getResponseCase().ordinal()];
                if (i == 1) {
                    if (this.responseCase_ == 1) {
                        z = true;
                    }
                    this.response_ = visitor.visitOneofInt(z, this.response_, signingResponse.response_);
                } else if (i == 2) {
                    if (this.responseCase_ == 2) {
                        z = true;
                    }
                    this.response_ = visitor.visitOneofBoolean(z, this.response_, signingResponse.response_);
                } else if (i == 3) {
                    if (this.responseCase_ == 3) {
                        z = true;
                    }
                    this.response_ = visitor.visitOneofInt(z, this.response_, signingResponse.response_);
                } else if (i == 4) {
                    if (this.responseCase_ != 0) {
                        z = true;
                    }
                    visitor.visitOneofNotSet(z);
                }
                if (visitor == MergeFromVisitor.INSTANCE) {
                    int i2 = signingResponse.responseCase_;
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
                                int readEnum = codedInputStream.readEnum();
                                this.responseCase_ = 1;
                                this.response_ = Integer.valueOf(readEnum);
                            } else if (readTag == 16) {
                                this.responseCase_ = 2;
                                this.response_ = Boolean.valueOf(codedInputStream.readBool());
                            } else if (readTag == 24) {
                                this.responseCase_ = 3;
                                this.response_ = Integer.valueOf(codedInputStream.readInt32());
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
                    synchronized (SigningResponse.class) {
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

    public static SigningResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SigningResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
