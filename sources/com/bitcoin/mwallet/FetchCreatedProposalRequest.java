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

public final class FetchCreatedProposalRequest extends GeneratedMessageLite<FetchCreatedProposalRequest, Builder> implements FetchCreatedProposalRequestOrBuilder {
    /* access modifiers changed from: private */
    public static final FetchCreatedProposalRequest DEFAULT_INSTANCE = new FetchCreatedProposalRequest();
    private static volatile Parser<FetchCreatedProposalRequest> PARSER = null;
    public static final int PROPOSAL_ID_FIELD_NUMBER = 2;
    public static final int WALLET_ID_FIELD_NUMBER = 1;
    private int requestCase_ = 0;
    private Object request_;

    /* renamed from: com.bitcoin.mwallet.FetchCreatedProposalRequest$1 */
    static /* synthetic */ class C09181 {

        /* renamed from: $SwitchMap$com$bitcoin$mwallet$FetchCreatedProposalRequest$RequestCase */
        static final /* synthetic */ int[] f134x11507303 = new int[RequestCase.values().length];

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
                f135xa1df5c61 = r0
                r0 = 1
                int[] r1 = f135xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r2 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = f135xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = f135xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r3 = f135xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r3 = f135xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r5 = 5
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r3 = f135xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r5 = 6
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r3 = f135xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r5 = 7
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r3 = f135xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r5 = 8
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                com.bitcoin.mwallet.FetchCreatedProposalRequest$RequestCase[] r3 = com.bitcoin.mwallet.FetchCreatedProposalRequest.RequestCase.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                f134x11507303 = r3
                int[] r3 = f134x11507303     // Catch:{ NoSuchFieldError -> 0x0075 }
                com.bitcoin.mwallet.FetchCreatedProposalRequest$RequestCase r4 = com.bitcoin.mwallet.FetchCreatedProposalRequest.RequestCase.WALLET_ID     // Catch:{ NoSuchFieldError -> 0x0075 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0075 }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0075 }
            L_0x0075:
                int[] r0 = f134x11507303     // Catch:{ NoSuchFieldError -> 0x007f }
                com.bitcoin.mwallet.FetchCreatedProposalRequest$RequestCase r3 = com.bitcoin.mwallet.FetchCreatedProposalRequest.RequestCase.PROPOSAL_ID     // Catch:{ NoSuchFieldError -> 0x007f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x007f }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x007f }
            L_0x007f:
                int[] r0 = f134x11507303     // Catch:{ NoSuchFieldError -> 0x0089 }
                com.bitcoin.mwallet.FetchCreatedProposalRequest$RequestCase r1 = com.bitcoin.mwallet.FetchCreatedProposalRequest.RequestCase.REQUEST_NOT_SET     // Catch:{ NoSuchFieldError -> 0x0089 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0089 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0089 }
            L_0x0089:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.FetchCreatedProposalRequest.C09181.<clinit>():void");
        }
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<FetchCreatedProposalRequest, Builder> implements FetchCreatedProposalRequestOrBuilder {
        /* synthetic */ Builder(C09181 r1) {
            this();
        }

        private Builder() {
            super(FetchCreatedProposalRequest.DEFAULT_INSTANCE);
        }

        public RequestCase getRequestCase() {
            return ((FetchCreatedProposalRequest) this.instance).getRequestCase();
        }

        public Builder clearRequest() {
            copyOnWrite();
            ((FetchCreatedProposalRequest) this.instance).clearRequest();
            return this;
        }

        public String getWalletId() {
            return ((FetchCreatedProposalRequest) this.instance).getWalletId();
        }

        public ByteString getWalletIdBytes() {
            return ((FetchCreatedProposalRequest) this.instance).getWalletIdBytes();
        }

        public Builder setWalletId(String str) {
            copyOnWrite();
            ((FetchCreatedProposalRequest) this.instance).setWalletId(str);
            return this;
        }

        public Builder clearWalletId() {
            copyOnWrite();
            ((FetchCreatedProposalRequest) this.instance).clearWalletId();
            return this;
        }

        public Builder setWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((FetchCreatedProposalRequest) this.instance).setWalletIdBytes(byteString);
            return this;
        }

        public String getProposalId() {
            return ((FetchCreatedProposalRequest) this.instance).getProposalId();
        }

        public ByteString getProposalIdBytes() {
            return ((FetchCreatedProposalRequest) this.instance).getProposalIdBytes();
        }

        public Builder setProposalId(String str) {
            copyOnWrite();
            ((FetchCreatedProposalRequest) this.instance).setProposalId(str);
            return this;
        }

        public Builder clearProposalId() {
            copyOnWrite();
            ((FetchCreatedProposalRequest) this.instance).clearProposalId();
            return this;
        }

        public Builder setProposalIdBytes(ByteString byteString) {
            copyOnWrite();
            ((FetchCreatedProposalRequest) this.instance).setProposalIdBytes(byteString);
            return this;
        }
    }

    public enum RequestCase implements EnumLite {
        WALLET_ID(1),
        PROPOSAL_ID(2),
        REQUEST_NOT_SET(0);
        
        private final int value;

        private RequestCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static RequestCase valueOf(int i) {
            return forNumber(i);
        }

        public static RequestCase forNumber(int i) {
            if (i == 0) {
                return REQUEST_NOT_SET;
            }
            if (i == 1) {
                return WALLET_ID;
            }
            if (i != 2) {
                return null;
            }
            return PROPOSAL_ID;
        }

        public int getNumber() {
            return this.value;
        }
    }

    private FetchCreatedProposalRequest() {
    }

    public RequestCase getRequestCase() {
        return RequestCase.forNumber(this.requestCase_);
    }

    /* access modifiers changed from: private */
    public void clearRequest() {
        this.requestCase_ = 0;
        this.request_ = null;
    }

    public String getWalletId() {
        return this.requestCase_ == 1 ? (String) this.request_ : "";
    }

    public ByteString getWalletIdBytes() {
        return ByteString.copyFromUtf8(this.requestCase_ == 1 ? (String) this.request_ : "");
    }

    /* access modifiers changed from: private */
    public void setWalletId(String str) {
        if (str != null) {
            this.requestCase_ = 1;
            this.request_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearWalletId() {
        if (this.requestCase_ == 1) {
            this.requestCase_ = 0;
            this.request_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setWalletIdBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.requestCase_ = 1;
            this.request_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getProposalId() {
        return this.requestCase_ == 2 ? (String) this.request_ : "";
    }

    public ByteString getProposalIdBytes() {
        return ByteString.copyFromUtf8(this.requestCase_ == 2 ? (String) this.request_ : "");
    }

    /* access modifiers changed from: private */
    public void setProposalId(String str) {
        if (str != null) {
            this.requestCase_ = 2;
            this.request_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearProposalId() {
        if (this.requestCase_ == 2) {
            this.requestCase_ = 0;
            this.request_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setProposalIdBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.requestCase_ = 2;
            this.request_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.requestCase_ == 1) {
            codedOutputStream.writeString(1, getWalletId());
        }
        if (this.requestCase_ == 2) {
            codedOutputStream.writeString(2, getProposalId());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.requestCase_ == 1) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getWalletId());
        }
        if (this.requestCase_ == 2) {
            i2 += CodedOutputStream.computeStringSize(2, getProposalId());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static FetchCreatedProposalRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (FetchCreatedProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static FetchCreatedProposalRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FetchCreatedProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static FetchCreatedProposalRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (FetchCreatedProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static FetchCreatedProposalRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FetchCreatedProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static FetchCreatedProposalRequest parseFrom(InputStream inputStream) throws IOException {
        return (FetchCreatedProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static FetchCreatedProposalRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FetchCreatedProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static FetchCreatedProposalRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (FetchCreatedProposalRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static FetchCreatedProposalRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FetchCreatedProposalRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static FetchCreatedProposalRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (FetchCreatedProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static FetchCreatedProposalRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FetchCreatedProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(FetchCreatedProposalRequest fetchCreatedProposalRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(fetchCreatedProposalRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new FetchCreatedProposalRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder(null);
            case VISIT:
                Visitor visitor = (Visitor) obj;
                FetchCreatedProposalRequest fetchCreatedProposalRequest = (FetchCreatedProposalRequest) obj2;
                int i = C09181.f134x11507303[fetchCreatedProposalRequest.getRequestCase().ordinal()];
                if (i == 1) {
                    if (this.requestCase_ == 1) {
                        z = true;
                    }
                    this.request_ = visitor.visitOneofString(z, this.request_, fetchCreatedProposalRequest.request_);
                } else if (i == 2) {
                    if (this.requestCase_ == 2) {
                        z = true;
                    }
                    this.request_ = visitor.visitOneofString(z, this.request_, fetchCreatedProposalRequest.request_);
                } else if (i == 3) {
                    if (this.requestCase_ != 0) {
                        z = true;
                    }
                    visitor.visitOneofNotSet(z);
                }
                if (visitor == MergeFromVisitor.INSTANCE) {
                    int i2 = fetchCreatedProposalRequest.requestCase_;
                    if (i2 != 0) {
                        this.requestCase_ = i2;
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
                                this.requestCase_ = 1;
                                this.request_ = readStringRequireUtf8;
                            } else if (readTag == 18) {
                                String readStringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                                this.requestCase_ = 2;
                                this.request_ = readStringRequireUtf82;
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
                    synchronized (FetchCreatedProposalRequest.class) {
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

    public static FetchCreatedProposalRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<FetchCreatedProposalRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
