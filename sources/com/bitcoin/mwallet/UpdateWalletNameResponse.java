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

public final class UpdateWalletNameResponse extends GeneratedMessageLite<UpdateWalletNameResponse, Builder> implements UpdateWalletNameResponseOrBuilder {
    /* access modifiers changed from: private */
    public static final UpdateWalletNameResponse DEFAULT_INSTANCE = new UpdateWalletNameResponse();
    public static final int ERROR_FIELD_NUMBER = 1;
    private static volatile Parser<UpdateWalletNameResponse> PARSER;
    private int responseCase_ = 0;
    private Object response_;

    /* renamed from: com.bitcoin.mwallet.UpdateWalletNameResponse$1 */
    static /* synthetic */ class C10651 {

        /* renamed from: $SwitchMap$com$bitcoin$mwallet$UpdateWalletNameResponse$ResponseCase */
        static final /* synthetic */ int[] f214xb6bdf280 = new int[ResponseCase.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|(2:1|2)|3|5|6|7|8|9|10|11|12|13|14|15|16|(2:17|18)|19|21|22|23|24|26) */
        /* JADX WARNING: Can't wrap try/catch for region: R(23:0|1|2|3|5|6|7|8|9|10|11|12|13|14|15|16|(2:17|18)|19|21|22|23|24|26) */
        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|1|2|3|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|21|22|23|24|26) */
        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0035 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0075 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002a */
        static {
            /*
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke[] r0 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f215xa1df5c61 = r0
                r0 = 1
                int[] r1 = f215xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r2 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = f215xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r2 = f215xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r4 = 3
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r2 = f215xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r4 = 4
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r2 = f215xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r4 = 5
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r2 = f215xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r4 = 6
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r2 = f215xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r4 = 7
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r2 = f215xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r4 = 8
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                com.bitcoin.mwallet.UpdateWalletNameResponse$ResponseCase[] r2 = com.bitcoin.mwallet.UpdateWalletNameResponse.ResponseCase.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                f214xb6bdf280 = r2
                int[] r2 = f214xb6bdf280     // Catch:{ NoSuchFieldError -> 0x0075 }
                com.bitcoin.mwallet.UpdateWalletNameResponse$ResponseCase r3 = com.bitcoin.mwallet.UpdateWalletNameResponse.ResponseCase.ERROR     // Catch:{ NoSuchFieldError -> 0x0075 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0075 }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0075 }
            L_0x0075:
                int[] r0 = f214xb6bdf280     // Catch:{ NoSuchFieldError -> 0x007f }
                com.bitcoin.mwallet.UpdateWalletNameResponse$ResponseCase r2 = com.bitcoin.mwallet.UpdateWalletNameResponse.ResponseCase.RESPONSE_NOT_SET     // Catch:{ NoSuchFieldError -> 0x007f }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x007f }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x007f }
            L_0x007f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.UpdateWalletNameResponse.C10651.<clinit>():void");
        }
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<UpdateWalletNameResponse, Builder> implements UpdateWalletNameResponseOrBuilder {
        /* synthetic */ Builder(C10651 r1) {
            this();
        }

        private Builder() {
            super(UpdateWalletNameResponse.DEFAULT_INSTANCE);
        }

        public ResponseCase getResponseCase() {
            return ((UpdateWalletNameResponse) this.instance).getResponseCase();
        }

        public Builder clearResponse() {
            copyOnWrite();
            ((UpdateWalletNameResponse) this.instance).clearResponse();
            return this;
        }

        public ResponseError getError() {
            return ((UpdateWalletNameResponse) this.instance).getError();
        }

        public Builder setError(ResponseError responseError) {
            copyOnWrite();
            ((UpdateWalletNameResponse) this.instance).setError(responseError);
            return this;
        }

        public Builder setError(com.bitcoin.mwallet.ResponseError.Builder builder) {
            copyOnWrite();
            ((UpdateWalletNameResponse) this.instance).setError(builder);
            return this;
        }

        public Builder mergeError(ResponseError responseError) {
            copyOnWrite();
            ((UpdateWalletNameResponse) this.instance).mergeError(responseError);
            return this;
        }

        public Builder clearError() {
            copyOnWrite();
            ((UpdateWalletNameResponse) this.instance).clearError();
            return this;
        }
    }

    public enum ResponseCase implements EnumLite {
        ERROR(1),
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
            if (i != 1) {
                return null;
            }
            return ERROR;
        }

        public int getNumber() {
            return this.value;
        }
    }

    private UpdateWalletNameResponse() {
    }

    public ResponseCase getResponseCase() {
        return ResponseCase.forNumber(this.responseCase_);
    }

    /* access modifiers changed from: private */
    public void clearResponse() {
        this.responseCase_ = 0;
        this.response_ = null;
    }

    public ResponseError getError() {
        if (this.responseCase_ == 1) {
            return (ResponseError) this.response_;
        }
        return ResponseError.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setError(ResponseError responseError) {
        if (responseError != null) {
            this.response_ = responseError;
            this.responseCase_ = 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setError(com.bitcoin.mwallet.ResponseError.Builder builder) {
        this.response_ = builder.build();
        this.responseCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void mergeError(ResponseError responseError) {
        if (this.responseCase_ != 1 || this.response_ == ResponseError.getDefaultInstance()) {
            this.response_ = responseError;
        } else {
            this.response_ = ((com.bitcoin.mwallet.ResponseError.Builder) ResponseError.newBuilder((ResponseError) this.response_).mergeFrom(responseError)).buildPartial();
        }
        this.responseCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void clearError() {
        if (this.responseCase_ == 1) {
            this.responseCase_ = 0;
            this.response_ = null;
        }
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.responseCase_ == 1) {
            codedOutputStream.writeMessage(1, (ResponseError) this.response_);
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.responseCase_ == 1) {
            i2 = 0 + CodedOutputStream.computeMessageSize(1, (ResponseError) this.response_);
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static UpdateWalletNameResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (UpdateWalletNameResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static UpdateWalletNameResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UpdateWalletNameResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static UpdateWalletNameResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (UpdateWalletNameResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static UpdateWalletNameResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UpdateWalletNameResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static UpdateWalletNameResponse parseFrom(InputStream inputStream) throws IOException {
        return (UpdateWalletNameResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static UpdateWalletNameResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateWalletNameResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static UpdateWalletNameResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (UpdateWalletNameResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static UpdateWalletNameResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateWalletNameResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static UpdateWalletNameResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (UpdateWalletNameResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static UpdateWalletNameResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateWalletNameResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UpdateWalletNameResponse updateWalletNameResponse) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(updateWalletNameResponse);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new UpdateWalletNameResponse();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder(null);
            case VISIT:
                Visitor visitor = (Visitor) obj;
                UpdateWalletNameResponse updateWalletNameResponse = (UpdateWalletNameResponse) obj2;
                int i = C10651.f214xb6bdf280[updateWalletNameResponse.getResponseCase().ordinal()];
                if (i == 1) {
                    if (this.responseCase_ == 1) {
                        z = true;
                    }
                    this.response_ = visitor.visitOneofMessage(z, this.response_, updateWalletNameResponse.response_);
                } else if (i == 2) {
                    if (this.responseCase_ != 0) {
                        z = true;
                    }
                    visitor.visitOneofNotSet(z);
                }
                if (visitor == MergeFromVisitor.INSTANCE) {
                    int i2 = updateWalletNameResponse.responseCase_;
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
                                com.bitcoin.mwallet.ResponseError.Builder builder = this.responseCase_ == 1 ? (com.bitcoin.mwallet.ResponseError.Builder) ((ResponseError) this.response_).toBuilder() : null;
                                this.response_ = codedInputStream.readMessage(ResponseError.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom((ResponseError) this.response_);
                                    this.response_ = builder.buildPartial();
                                }
                                this.responseCase_ = 1;
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
                    synchronized (UpdateWalletNameResponse.class) {
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

    public static UpdateWalletNameResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UpdateWalletNameResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
