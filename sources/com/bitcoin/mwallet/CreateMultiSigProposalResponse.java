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

public final class CreateMultiSigProposalResponse extends GeneratedMessageLite<CreateMultiSigProposalResponse, Builder> implements CreateMultiSigProposalResponseOrBuilder {
    public static final int CREATION_ERROR_FIELD_NUMBER = 2;
    public static final int CREATION_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final CreateMultiSigProposalResponse DEFAULT_INSTANCE = new CreateMultiSigProposalResponse();
    public static final int ERROR_FIELD_NUMBER = 3;
    private static volatile Parser<CreateMultiSigProposalResponse> PARSER;
    private int responseCase_ = 0;
    private Object response_;

    /* renamed from: com.bitcoin.mwallet.CreateMultiSigProposalResponse$1 */
    static /* synthetic */ class C08981 {

        /* renamed from: $SwitchMap$com$bitcoin$mwallet$CreateMultiSigProposalResponse$ResponseCase */
        static final /* synthetic */ int[] f115xb9ceb827 = new int[ResponseCase.values().length];

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
                f116xa1df5c61 = r0
                r0 = 1
                int[] r1 = f116xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r2 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = f116xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = f116xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                r3 = 4
                int[] r4 = f116xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r5 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r4 = f116xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r5 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r6 = 5
                r4[r5] = r6     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r4 = f116xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r5 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r6 = 6
                r4[r5] = r6     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r4 = f116xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r5 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r6 = 7
                r4[r5] = r6     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r4 = f116xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r5 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r6 = 8
                r4[r5] = r6     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                com.bitcoin.mwallet.CreateMultiSigProposalResponse$ResponseCase[] r4 = com.bitcoin.mwallet.CreateMultiSigProposalResponse.ResponseCase.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                f115xb9ceb827 = r4
                int[] r4 = f115xb9ceb827     // Catch:{ NoSuchFieldError -> 0x0075 }
                com.bitcoin.mwallet.CreateMultiSigProposalResponse$ResponseCase r5 = com.bitcoin.mwallet.CreateMultiSigProposalResponse.ResponseCase.CREATION     // Catch:{ NoSuchFieldError -> 0x0075 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0075 }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x0075 }
            L_0x0075:
                int[] r0 = f115xb9ceb827     // Catch:{ NoSuchFieldError -> 0x007f }
                com.bitcoin.mwallet.CreateMultiSigProposalResponse$ResponseCase r4 = com.bitcoin.mwallet.CreateMultiSigProposalResponse.ResponseCase.CREATION_ERROR     // Catch:{ NoSuchFieldError -> 0x007f }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x007f }
                r0[r4] = r1     // Catch:{ NoSuchFieldError -> 0x007f }
            L_0x007f:
                int[] r0 = f115xb9ceb827     // Catch:{ NoSuchFieldError -> 0x0089 }
                com.bitcoin.mwallet.CreateMultiSigProposalResponse$ResponseCase r1 = com.bitcoin.mwallet.CreateMultiSigProposalResponse.ResponseCase.ERROR     // Catch:{ NoSuchFieldError -> 0x0089 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0089 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0089 }
            L_0x0089:
                int[] r0 = f115xb9ceb827     // Catch:{ NoSuchFieldError -> 0x0093 }
                com.bitcoin.mwallet.CreateMultiSigProposalResponse$ResponseCase r1 = com.bitcoin.mwallet.CreateMultiSigProposalResponse.ResponseCase.RESPONSE_NOT_SET     // Catch:{ NoSuchFieldError -> 0x0093 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0093 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0093 }
            L_0x0093:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.CreateMultiSigProposalResponse.C08981.<clinit>():void");
        }
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<CreateMultiSigProposalResponse, Builder> implements CreateMultiSigProposalResponseOrBuilder {
        /* synthetic */ Builder(C08981 r1) {
            this();
        }

        private Builder() {
            super(CreateMultiSigProposalResponse.DEFAULT_INSTANCE);
        }

        public ResponseCase getResponseCase() {
            return ((CreateMultiSigProposalResponse) this.instance).getResponseCase();
        }

        public Builder clearResponse() {
            copyOnWrite();
            ((CreateMultiSigProposalResponse) this.instance).clearResponse();
            return this;
        }

        public CreateProposalResponse getCreation() {
            return ((CreateMultiSigProposalResponse) this.instance).getCreation();
        }

        public Builder setCreation(CreateProposalResponse createProposalResponse) {
            copyOnWrite();
            ((CreateMultiSigProposalResponse) this.instance).setCreation(createProposalResponse);
            return this;
        }

        public Builder setCreation(com.bitcoin.mwallet.CreateProposalResponse.Builder builder) {
            copyOnWrite();
            ((CreateMultiSigProposalResponse) this.instance).setCreation(builder);
            return this;
        }

        public Builder mergeCreation(CreateProposalResponse createProposalResponse) {
            copyOnWrite();
            ((CreateMultiSigProposalResponse) this.instance).mergeCreation(createProposalResponse);
            return this;
        }

        public Builder clearCreation() {
            copyOnWrite();
            ((CreateMultiSigProposalResponse) this.instance).clearCreation();
            return this;
        }

        public int getCreationErrorValue() {
            return ((CreateMultiSigProposalResponse) this.instance).getCreationErrorValue();
        }

        public Builder setCreationErrorValue(int i) {
            copyOnWrite();
            ((CreateMultiSigProposalResponse) this.instance).setCreationErrorValue(i);
            return this;
        }

        public ProposalCreationError getCreationError() {
            return ((CreateMultiSigProposalResponse) this.instance).getCreationError();
        }

        public Builder setCreationError(ProposalCreationError proposalCreationError) {
            copyOnWrite();
            ((CreateMultiSigProposalResponse) this.instance).setCreationError(proposalCreationError);
            return this;
        }

        public Builder clearCreationError() {
            copyOnWrite();
            ((CreateMultiSigProposalResponse) this.instance).clearCreationError();
            return this;
        }

        public ResponseError getError() {
            return ((CreateMultiSigProposalResponse) this.instance).getError();
        }

        public Builder setError(ResponseError responseError) {
            copyOnWrite();
            ((CreateMultiSigProposalResponse) this.instance).setError(responseError);
            return this;
        }

        public Builder setError(com.bitcoin.mwallet.ResponseError.Builder builder) {
            copyOnWrite();
            ((CreateMultiSigProposalResponse) this.instance).setError(builder);
            return this;
        }

        public Builder mergeError(ResponseError responseError) {
            copyOnWrite();
            ((CreateMultiSigProposalResponse) this.instance).mergeError(responseError);
            return this;
        }

        public Builder clearError() {
            copyOnWrite();
            ((CreateMultiSigProposalResponse) this.instance).clearError();
            return this;
        }
    }

    public enum ResponseCase implements EnumLite {
        CREATION(1),
        CREATION_ERROR(2),
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
                return CREATION;
            }
            if (i == 2) {
                return CREATION_ERROR;
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

    private CreateMultiSigProposalResponse() {
    }

    public ResponseCase getResponseCase() {
        return ResponseCase.forNumber(this.responseCase_);
    }

    /* access modifiers changed from: private */
    public void clearResponse() {
        this.responseCase_ = 0;
        this.response_ = null;
    }

    public CreateProposalResponse getCreation() {
        if (this.responseCase_ == 1) {
            return (CreateProposalResponse) this.response_;
        }
        return CreateProposalResponse.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setCreation(CreateProposalResponse createProposalResponse) {
        if (createProposalResponse != null) {
            this.response_ = createProposalResponse;
            this.responseCase_ = 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setCreation(com.bitcoin.mwallet.CreateProposalResponse.Builder builder) {
        this.response_ = builder.build();
        this.responseCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void mergeCreation(CreateProposalResponse createProposalResponse) {
        if (this.responseCase_ != 1 || this.response_ == CreateProposalResponse.getDefaultInstance()) {
            this.response_ = createProposalResponse;
        } else {
            this.response_ = ((com.bitcoin.mwallet.CreateProposalResponse.Builder) CreateProposalResponse.newBuilder((CreateProposalResponse) this.response_).mergeFrom(createProposalResponse)).buildPartial();
        }
        this.responseCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void clearCreation() {
        if (this.responseCase_ == 1) {
            this.responseCase_ = 0;
            this.response_ = null;
        }
    }

    public int getCreationErrorValue() {
        if (this.responseCase_ == 2) {
            return ((Integer) this.response_).intValue();
        }
        return 0;
    }

    public ProposalCreationError getCreationError() {
        if (this.responseCase_ != 2) {
            return ProposalCreationError.UNKNOWN_CREATION_ERROR;
        }
        ProposalCreationError forNumber = ProposalCreationError.forNumber(((Integer) this.response_).intValue());
        if (forNumber == null) {
            forNumber = ProposalCreationError.UNRECOGNIZED;
        }
        return forNumber;
    }

    /* access modifiers changed from: private */
    public void setCreationErrorValue(int i) {
        this.responseCase_ = 2;
        this.response_ = Integer.valueOf(i);
    }

    /* access modifiers changed from: private */
    public void setCreationError(ProposalCreationError proposalCreationError) {
        if (proposalCreationError != null) {
            this.responseCase_ = 2;
            this.response_ = Integer.valueOf(proposalCreationError.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearCreationError() {
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
            codedOutputStream.writeMessage(1, (CreateProposalResponse) this.response_);
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
            i2 = 0 + CodedOutputStream.computeMessageSize(1, (CreateProposalResponse) this.response_);
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

    public static CreateMultiSigProposalResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (CreateMultiSigProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static CreateMultiSigProposalResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CreateMultiSigProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static CreateMultiSigProposalResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (CreateMultiSigProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static CreateMultiSigProposalResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CreateMultiSigProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static CreateMultiSigProposalResponse parseFrom(InputStream inputStream) throws IOException {
        return (CreateMultiSigProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CreateMultiSigProposalResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateMultiSigProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CreateMultiSigProposalResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (CreateMultiSigProposalResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CreateMultiSigProposalResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateMultiSigProposalResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CreateMultiSigProposalResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (CreateMultiSigProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static CreateMultiSigProposalResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateMultiSigProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CreateMultiSigProposalResponse createMultiSigProposalResponse) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(createMultiSigProposalResponse);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new CreateMultiSigProposalResponse();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder(null);
            case VISIT:
                Visitor visitor = (Visitor) obj;
                CreateMultiSigProposalResponse createMultiSigProposalResponse = (CreateMultiSigProposalResponse) obj2;
                int i = C08981.f115xb9ceb827[createMultiSigProposalResponse.getResponseCase().ordinal()];
                if (i == 1) {
                    if (this.responseCase_ == 1) {
                        z = true;
                    }
                    this.response_ = visitor.visitOneofMessage(z, this.response_, createMultiSigProposalResponse.response_);
                } else if (i == 2) {
                    if (this.responseCase_ == 2) {
                        z = true;
                    }
                    this.response_ = visitor.visitOneofInt(z, this.response_, createMultiSigProposalResponse.response_);
                } else if (i == 3) {
                    if (this.responseCase_ == 3) {
                        z = true;
                    }
                    this.response_ = visitor.visitOneofMessage(z, this.response_, createMultiSigProposalResponse.response_);
                } else if (i == 4) {
                    if (this.responseCase_ != 0) {
                        z = true;
                    }
                    visitor.visitOneofNotSet(z);
                }
                if (visitor == MergeFromVisitor.INSTANCE) {
                    int i2 = createMultiSigProposalResponse.responseCase_;
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
                                com.bitcoin.mwallet.CreateProposalResponse.Builder builder = this.responseCase_ == 1 ? (com.bitcoin.mwallet.CreateProposalResponse.Builder) ((CreateProposalResponse) this.response_).toBuilder() : null;
                                this.response_ = codedInputStream.readMessage(CreateProposalResponse.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom((CreateProposalResponse) this.response_);
                                    this.response_ = builder.buildPartial();
                                }
                                this.responseCase_ = 1;
                            } else if (readTag == 16) {
                                int readEnum = codedInputStream.readEnum();
                                this.responseCase_ = 2;
                                this.response_ = Integer.valueOf(readEnum);
                            } else if (readTag == 26) {
                                com.bitcoin.mwallet.ResponseError.Builder builder2 = this.responseCase_ == 3 ? (com.bitcoin.mwallet.ResponseError.Builder) ((ResponseError) this.response_).toBuilder() : null;
                                this.response_ = codedInputStream.readMessage(ResponseError.parser(), extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom((ResponseError) this.response_);
                                    this.response_ = builder2.buildPartial();
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
                    synchronized (CreateMultiSigProposalResponse.class) {
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

    public static CreateMultiSigProposalResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CreateMultiSigProposalResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
