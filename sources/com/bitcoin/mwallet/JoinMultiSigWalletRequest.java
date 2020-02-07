package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.MethodToInvoke;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class JoinMultiSigWalletRequest extends GeneratedMessageLite<JoinMultiSigWalletRequest, Builder> implements JoinMultiSigWalletRequestOrBuilder {
    /* access modifiers changed from: private */
    public static final JoinMultiSigWalletRequest DEFAULT_INSTANCE = new JoinMultiSigWalletRequest();
    private static volatile Parser<JoinMultiSigWalletRequest> PARSER = null;
    public static final int WALLET_ID_FIELD_NUMBER = 1;
    public static final int X_PUB_KEY_FIELD_NUMBER = 3;
    public static final int YOUR_NAME_FIELD_NUMBER = 2;
    private String walletId_;
    private String xPubKey_;
    private String yourName_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<JoinMultiSigWalletRequest, Builder> implements JoinMultiSigWalletRequestOrBuilder {
        private Builder() {
            super(JoinMultiSigWalletRequest.DEFAULT_INSTANCE);
        }

        public String getWalletId() {
            return ((JoinMultiSigWalletRequest) this.instance).getWalletId();
        }

        public ByteString getWalletIdBytes() {
            return ((JoinMultiSigWalletRequest) this.instance).getWalletIdBytes();
        }

        public Builder setWalletId(String str) {
            copyOnWrite();
            ((JoinMultiSigWalletRequest) this.instance).setWalletId(str);
            return this;
        }

        public Builder clearWalletId() {
            copyOnWrite();
            ((JoinMultiSigWalletRequest) this.instance).clearWalletId();
            return this;
        }

        public Builder setWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((JoinMultiSigWalletRequest) this.instance).setWalletIdBytes(byteString);
            return this;
        }

        public String getYourName() {
            return ((JoinMultiSigWalletRequest) this.instance).getYourName();
        }

        public ByteString getYourNameBytes() {
            return ((JoinMultiSigWalletRequest) this.instance).getYourNameBytes();
        }

        public Builder setYourName(String str) {
            copyOnWrite();
            ((JoinMultiSigWalletRequest) this.instance).setYourName(str);
            return this;
        }

        public Builder clearYourName() {
            copyOnWrite();
            ((JoinMultiSigWalletRequest) this.instance).clearYourName();
            return this;
        }

        public Builder setYourNameBytes(ByteString byteString) {
            copyOnWrite();
            ((JoinMultiSigWalletRequest) this.instance).setYourNameBytes(byteString);
            return this;
        }

        public String getXPubKey() {
            return ((JoinMultiSigWalletRequest) this.instance).getXPubKey();
        }

        public ByteString getXPubKeyBytes() {
            return ((JoinMultiSigWalletRequest) this.instance).getXPubKeyBytes();
        }

        public Builder setXPubKey(String str) {
            copyOnWrite();
            ((JoinMultiSigWalletRequest) this.instance).setXPubKey(str);
            return this;
        }

        public Builder clearXPubKey() {
            copyOnWrite();
            ((JoinMultiSigWalletRequest) this.instance).clearXPubKey();
            return this;
        }

        public Builder setXPubKeyBytes(ByteString byteString) {
            copyOnWrite();
            ((JoinMultiSigWalletRequest) this.instance).setXPubKeyBytes(byteString);
            return this;
        }
    }

    private JoinMultiSigWalletRequest() {
        String str = "";
        this.walletId_ = str;
        this.yourName_ = str;
        this.xPubKey_ = str;
    }

    public String getWalletId() {
        return this.walletId_;
    }

    public ByteString getWalletIdBytes() {
        return ByteString.copyFromUtf8(this.walletId_);
    }

    /* access modifiers changed from: private */
    public void setWalletId(String str) {
        if (str != null) {
            this.walletId_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearWalletId() {
        this.walletId_ = getDefaultInstance().getWalletId();
    }

    /* access modifiers changed from: private */
    public void setWalletIdBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.walletId_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getYourName() {
        return this.yourName_;
    }

    public ByteString getYourNameBytes() {
        return ByteString.copyFromUtf8(this.yourName_);
    }

    /* access modifiers changed from: private */
    public void setYourName(String str) {
        if (str != null) {
            this.yourName_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearYourName() {
        this.yourName_ = getDefaultInstance().getYourName();
    }

    /* access modifiers changed from: private */
    public void setYourNameBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.yourName_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getXPubKey() {
        return this.xPubKey_;
    }

    public ByteString getXPubKeyBytes() {
        return ByteString.copyFromUtf8(this.xPubKey_);
    }

    /* access modifiers changed from: private */
    public void setXPubKey(String str) {
        if (str != null) {
            this.xPubKey_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearXPubKey() {
        this.xPubKey_ = getDefaultInstance().getXPubKey();
    }

    /* access modifiers changed from: private */
    public void setXPubKeyBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.xPubKey_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.walletId_.isEmpty()) {
            codedOutputStream.writeString(1, getWalletId());
        }
        if (!this.yourName_.isEmpty()) {
            codedOutputStream.writeString(2, getYourName());
        }
        if (!this.xPubKey_.isEmpty()) {
            codedOutputStream.writeString(3, getXPubKey());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.walletId_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getWalletId());
        }
        if (!this.yourName_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(2, getYourName());
        }
        if (!this.xPubKey_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(3, getXPubKey());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static JoinMultiSigWalletRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (JoinMultiSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static JoinMultiSigWalletRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (JoinMultiSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static JoinMultiSigWalletRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (JoinMultiSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static JoinMultiSigWalletRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (JoinMultiSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static JoinMultiSigWalletRequest parseFrom(InputStream inputStream) throws IOException {
        return (JoinMultiSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static JoinMultiSigWalletRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (JoinMultiSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static JoinMultiSigWalletRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (JoinMultiSigWalletRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static JoinMultiSigWalletRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (JoinMultiSigWalletRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static JoinMultiSigWalletRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (JoinMultiSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static JoinMultiSigWalletRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (JoinMultiSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(JoinMultiSigWalletRequest joinMultiSigWalletRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(joinMultiSigWalletRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new JoinMultiSigWalletRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                JoinMultiSigWalletRequest joinMultiSigWalletRequest = (JoinMultiSigWalletRequest) obj2;
                this.walletId_ = visitor.visitString(!this.walletId_.isEmpty(), this.walletId_, !joinMultiSigWalletRequest.walletId_.isEmpty(), joinMultiSigWalletRequest.walletId_);
                this.yourName_ = visitor.visitString(!this.yourName_.isEmpty(), this.yourName_, !joinMultiSigWalletRequest.yourName_.isEmpty(), joinMultiSigWalletRequest.yourName_);
                this.xPubKey_ = visitor.visitString(!this.xPubKey_.isEmpty(), this.xPubKey_, true ^ joinMultiSigWalletRequest.xPubKey_.isEmpty(), joinMultiSigWalletRequest.xPubKey_);
                MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                this.walletId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.yourName_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                this.xPubKey_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (JoinMultiSigWalletRequest.class) {
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

    public static JoinMultiSigWalletRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<JoinMultiSigWalletRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
