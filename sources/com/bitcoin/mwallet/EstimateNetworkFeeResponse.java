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

public final class EstimateNetworkFeeResponse extends GeneratedMessageLite<EstimateNetworkFeeResponse, Builder> implements EstimateNetworkFeeResponseOrBuilder {
    /* access modifiers changed from: private */
    public static final EstimateNetworkFeeResponse DEFAULT_INSTANCE = new EstimateNetworkFeeResponse();
    public static final int ERROR_FIELD_NUMBER = 2;
    public static final int ESTIMATE_FIELD_NUMBER = 1;
    private static volatile Parser<EstimateNetworkFeeResponse> PARSER;
    private int responseCase_ = 0;
    private Object response_;

    /* renamed from: com.bitcoin.mwallet.EstimateNetworkFeeResponse$1 */
    static /* synthetic */ class C09091 {

        /* renamed from: $SwitchMap$com$bitcoin$mwallet$EstimateNetworkFeeResponse$ResponseCase */
        static final /* synthetic */ int[] f128x3665302d = new int[ResponseCase.values().length];

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
                f129xa1df5c61 = r0
                r0 = 1
                int[] r1 = f129xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r2 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = f129xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = f129xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r3 = f129xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r3 = f129xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r5 = 5
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r3 = f129xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r5 = 6
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r3 = f129xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r5 = 7
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r3 = f129xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r5 = 8
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                com.bitcoin.mwallet.EstimateNetworkFeeResponse$ResponseCase[] r3 = com.bitcoin.mwallet.EstimateNetworkFeeResponse.ResponseCase.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                f128x3665302d = r3
                int[] r3 = f128x3665302d     // Catch:{ NoSuchFieldError -> 0x0075 }
                com.bitcoin.mwallet.EstimateNetworkFeeResponse$ResponseCase r4 = com.bitcoin.mwallet.EstimateNetworkFeeResponse.ResponseCase.ESTIMATE     // Catch:{ NoSuchFieldError -> 0x0075 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0075 }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0075 }
            L_0x0075:
                int[] r0 = f128x3665302d     // Catch:{ NoSuchFieldError -> 0x007f }
                com.bitcoin.mwallet.EstimateNetworkFeeResponse$ResponseCase r3 = com.bitcoin.mwallet.EstimateNetworkFeeResponse.ResponseCase.ERROR     // Catch:{ NoSuchFieldError -> 0x007f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x007f }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x007f }
            L_0x007f:
                int[] r0 = f128x3665302d     // Catch:{ NoSuchFieldError -> 0x0089 }
                com.bitcoin.mwallet.EstimateNetworkFeeResponse$ResponseCase r1 = com.bitcoin.mwallet.EstimateNetworkFeeResponse.ResponseCase.RESPONSE_NOT_SET     // Catch:{ NoSuchFieldError -> 0x0089 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0089 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0089 }
            L_0x0089:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.EstimateNetworkFeeResponse.C09091.<clinit>():void");
        }
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<EstimateNetworkFeeResponse, Builder> implements EstimateNetworkFeeResponseOrBuilder {
        /* synthetic */ Builder(C09091 r1) {
            this();
        }

        private Builder() {
            super(EstimateNetworkFeeResponse.DEFAULT_INSTANCE);
        }

        public ResponseCase getResponseCase() {
            return ((EstimateNetworkFeeResponse) this.instance).getResponseCase();
        }

        public Builder clearResponse() {
            copyOnWrite();
            ((EstimateNetworkFeeResponse) this.instance).clearResponse();
            return this;
        }

        public EstimateFeeResponse getEstimate() {
            return ((EstimateNetworkFeeResponse) this.instance).getEstimate();
        }

        public Builder setEstimate(EstimateFeeResponse estimateFeeResponse) {
            copyOnWrite();
            ((EstimateNetworkFeeResponse) this.instance).setEstimate(estimateFeeResponse);
            return this;
        }

        public Builder setEstimate(com.bitcoin.mwallet.EstimateFeeResponse.Builder builder) {
            copyOnWrite();
            ((EstimateNetworkFeeResponse) this.instance).setEstimate(builder);
            return this;
        }

        public Builder mergeEstimate(EstimateFeeResponse estimateFeeResponse) {
            copyOnWrite();
            ((EstimateNetworkFeeResponse) this.instance).mergeEstimate(estimateFeeResponse);
            return this;
        }

        public Builder clearEstimate() {
            copyOnWrite();
            ((EstimateNetworkFeeResponse) this.instance).clearEstimate();
            return this;
        }

        public ResponseError getError() {
            return ((EstimateNetworkFeeResponse) this.instance).getError();
        }

        public Builder setError(ResponseError responseError) {
            copyOnWrite();
            ((EstimateNetworkFeeResponse) this.instance).setError(responseError);
            return this;
        }

        public Builder setError(com.bitcoin.mwallet.ResponseError.Builder builder) {
            copyOnWrite();
            ((EstimateNetworkFeeResponse) this.instance).setError(builder);
            return this;
        }

        public Builder mergeError(ResponseError responseError) {
            copyOnWrite();
            ((EstimateNetworkFeeResponse) this.instance).mergeError(responseError);
            return this;
        }

        public Builder clearError() {
            copyOnWrite();
            ((EstimateNetworkFeeResponse) this.instance).clearError();
            return this;
        }
    }

    public enum ResponseCase implements EnumLite {
        ESTIMATE(1),
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
                return ESTIMATE;
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

    private EstimateNetworkFeeResponse() {
    }

    public ResponseCase getResponseCase() {
        return ResponseCase.forNumber(this.responseCase_);
    }

    /* access modifiers changed from: private */
    public void clearResponse() {
        this.responseCase_ = 0;
        this.response_ = null;
    }

    public EstimateFeeResponse getEstimate() {
        if (this.responseCase_ == 1) {
            return (EstimateFeeResponse) this.response_;
        }
        return EstimateFeeResponse.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setEstimate(EstimateFeeResponse estimateFeeResponse) {
        if (estimateFeeResponse != null) {
            this.response_ = estimateFeeResponse;
            this.responseCase_ = 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setEstimate(com.bitcoin.mwallet.EstimateFeeResponse.Builder builder) {
        this.response_ = builder.build();
        this.responseCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void mergeEstimate(EstimateFeeResponse estimateFeeResponse) {
        if (this.responseCase_ != 1 || this.response_ == EstimateFeeResponse.getDefaultInstance()) {
            this.response_ = estimateFeeResponse;
        } else {
            this.response_ = ((com.bitcoin.mwallet.EstimateFeeResponse.Builder) EstimateFeeResponse.newBuilder((EstimateFeeResponse) this.response_).mergeFrom(estimateFeeResponse)).buildPartial();
        }
        this.responseCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void clearEstimate() {
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
            codedOutputStream.writeMessage(1, (EstimateFeeResponse) this.response_);
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
            i2 = 0 + CodedOutputStream.computeMessageSize(1, (EstimateFeeResponse) this.response_);
        }
        if (this.responseCase_ == 2) {
            i2 += CodedOutputStream.computeMessageSize(2, (ResponseError) this.response_);
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static EstimateNetworkFeeResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (EstimateNetworkFeeResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static EstimateNetworkFeeResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EstimateNetworkFeeResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static EstimateNetworkFeeResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (EstimateNetworkFeeResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static EstimateNetworkFeeResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EstimateNetworkFeeResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static EstimateNetworkFeeResponse parseFrom(InputStream inputStream) throws IOException {
        return (EstimateNetworkFeeResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EstimateNetworkFeeResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EstimateNetworkFeeResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EstimateNetworkFeeResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (EstimateNetworkFeeResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EstimateNetworkFeeResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EstimateNetworkFeeResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EstimateNetworkFeeResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (EstimateNetworkFeeResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static EstimateNetworkFeeResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EstimateNetworkFeeResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(EstimateNetworkFeeResponse estimateNetworkFeeResponse) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(estimateNetworkFeeResponse);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new EstimateNetworkFeeResponse();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder(null);
            case VISIT:
                Visitor visitor = (Visitor) obj;
                EstimateNetworkFeeResponse estimateNetworkFeeResponse = (EstimateNetworkFeeResponse) obj2;
                int i = C09091.f128x3665302d[estimateNetworkFeeResponse.getResponseCase().ordinal()];
                if (i == 1) {
                    if (this.responseCase_ == 1) {
                        z = true;
                    }
                    this.response_ = visitor.visitOneofMessage(z, this.response_, estimateNetworkFeeResponse.response_);
                } else if (i == 2) {
                    if (this.responseCase_ == 2) {
                        z = true;
                    }
                    this.response_ = visitor.visitOneofMessage(z, this.response_, estimateNetworkFeeResponse.response_);
                } else if (i == 3) {
                    if (this.responseCase_ != 0) {
                        z = true;
                    }
                    visitor.visitOneofNotSet(z);
                }
                if (visitor == MergeFromVisitor.INSTANCE) {
                    int i2 = estimateNetworkFeeResponse.responseCase_;
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
                                com.bitcoin.mwallet.EstimateFeeResponse.Builder builder = this.responseCase_ == 1 ? (com.bitcoin.mwallet.EstimateFeeResponse.Builder) ((EstimateFeeResponse) this.response_).toBuilder() : null;
                                this.response_ = codedInputStream.readMessage(EstimateFeeResponse.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom((EstimateFeeResponse) this.response_);
                                    this.response_ = builder.buildPartial();
                                }
                                this.responseCase_ = 1;
                            } else if (readTag == 18) {
                                com.bitcoin.mwallet.ResponseError.Builder builder2 = this.responseCase_ == 2 ? (com.bitcoin.mwallet.ResponseError.Builder) ((ResponseError) this.response_).toBuilder() : null;
                                this.response_ = codedInputStream.readMessage(ResponseError.parser(), extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom((ResponseError) this.response_);
                                    this.response_ = builder2.buildPartial();
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
                    synchronized (EstimateNetworkFeeResponse.class) {
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

    public static EstimateNetworkFeeResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<EstimateNetworkFeeResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
