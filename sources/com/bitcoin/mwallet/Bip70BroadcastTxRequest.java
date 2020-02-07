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

public final class Bip70BroadcastTxRequest extends GeneratedMessageLite<Bip70BroadcastTxRequest, Builder> implements Bip70BroadcastTxRequestOrBuilder {
    public static final int BIP_70_URL_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final Bip70BroadcastTxRequest DEFAULT_INSTANCE = new Bip70BroadcastTxRequest();
    public static final int DEPOSIT_ADDRESS_FIELD_NUMBER = 3;
    private static volatile Parser<Bip70BroadcastTxRequest> PARSER = null;
    public static final int TX_RAW_FIELD_NUMBER = 4;
    public static final int WALLET_ID_FIELD_NUMBER = 1;
    private String bip70Url_;
    private String depositAddress_;
    private ByteString txRaw_ = ByteString.EMPTY;
    private String walletId_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<Bip70BroadcastTxRequest, Builder> implements Bip70BroadcastTxRequestOrBuilder {
        private Builder() {
            super(Bip70BroadcastTxRequest.DEFAULT_INSTANCE);
        }

        public String getWalletId() {
            return ((Bip70BroadcastTxRequest) this.instance).getWalletId();
        }

        public ByteString getWalletIdBytes() {
            return ((Bip70BroadcastTxRequest) this.instance).getWalletIdBytes();
        }

        public Builder setWalletId(String str) {
            copyOnWrite();
            ((Bip70BroadcastTxRequest) this.instance).setWalletId(str);
            return this;
        }

        public Builder clearWalletId() {
            copyOnWrite();
            ((Bip70BroadcastTxRequest) this.instance).clearWalletId();
            return this;
        }

        public Builder setWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((Bip70BroadcastTxRequest) this.instance).setWalletIdBytes(byteString);
            return this;
        }

        public String getBip70Url() {
            return ((Bip70BroadcastTxRequest) this.instance).getBip70Url();
        }

        public ByteString getBip70UrlBytes() {
            return ((Bip70BroadcastTxRequest) this.instance).getBip70UrlBytes();
        }

        public Builder setBip70Url(String str) {
            copyOnWrite();
            ((Bip70BroadcastTxRequest) this.instance).setBip70Url(str);
            return this;
        }

        public Builder clearBip70Url() {
            copyOnWrite();
            ((Bip70BroadcastTxRequest) this.instance).clearBip70Url();
            return this;
        }

        public Builder setBip70UrlBytes(ByteString byteString) {
            copyOnWrite();
            ((Bip70BroadcastTxRequest) this.instance).setBip70UrlBytes(byteString);
            return this;
        }

        public String getDepositAddress() {
            return ((Bip70BroadcastTxRequest) this.instance).getDepositAddress();
        }

        public ByteString getDepositAddressBytes() {
            return ((Bip70BroadcastTxRequest) this.instance).getDepositAddressBytes();
        }

        public Builder setDepositAddress(String str) {
            copyOnWrite();
            ((Bip70BroadcastTxRequest) this.instance).setDepositAddress(str);
            return this;
        }

        public Builder clearDepositAddress() {
            copyOnWrite();
            ((Bip70BroadcastTxRequest) this.instance).clearDepositAddress();
            return this;
        }

        public Builder setDepositAddressBytes(ByteString byteString) {
            copyOnWrite();
            ((Bip70BroadcastTxRequest) this.instance).setDepositAddressBytes(byteString);
            return this;
        }

        public ByteString getTxRaw() {
            return ((Bip70BroadcastTxRequest) this.instance).getTxRaw();
        }

        public Builder setTxRaw(ByteString byteString) {
            copyOnWrite();
            ((Bip70BroadcastTxRequest) this.instance).setTxRaw(byteString);
            return this;
        }

        public Builder clearTxRaw() {
            copyOnWrite();
            ((Bip70BroadcastTxRequest) this.instance).clearTxRaw();
            return this;
        }
    }

    private Bip70BroadcastTxRequest() {
        String str = "";
        this.walletId_ = str;
        this.bip70Url_ = str;
        this.depositAddress_ = str;
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

    public String getBip70Url() {
        return this.bip70Url_;
    }

    public ByteString getBip70UrlBytes() {
        return ByteString.copyFromUtf8(this.bip70Url_);
    }

    /* access modifiers changed from: private */
    public void setBip70Url(String str) {
        if (str != null) {
            this.bip70Url_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearBip70Url() {
        this.bip70Url_ = getDefaultInstance().getBip70Url();
    }

    /* access modifiers changed from: private */
    public void setBip70UrlBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.bip70Url_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getDepositAddress() {
        return this.depositAddress_;
    }

    public ByteString getDepositAddressBytes() {
        return ByteString.copyFromUtf8(this.depositAddress_);
    }

    /* access modifiers changed from: private */
    public void setDepositAddress(String str) {
        if (str != null) {
            this.depositAddress_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearDepositAddress() {
        this.depositAddress_ = getDefaultInstance().getDepositAddress();
    }

    /* access modifiers changed from: private */
    public void setDepositAddressBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.depositAddress_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public ByteString getTxRaw() {
        return this.txRaw_;
    }

    /* access modifiers changed from: private */
    public void setTxRaw(ByteString byteString) {
        if (byteString != null) {
            this.txRaw_ = byteString;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearTxRaw() {
        this.txRaw_ = getDefaultInstance().getTxRaw();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.walletId_.isEmpty()) {
            codedOutputStream.writeString(1, getWalletId());
        }
        if (!this.bip70Url_.isEmpty()) {
            codedOutputStream.writeString(2, getBip70Url());
        }
        if (!this.depositAddress_.isEmpty()) {
            codedOutputStream.writeString(3, getDepositAddress());
        }
        if (!this.txRaw_.isEmpty()) {
            codedOutputStream.writeBytes(4, this.txRaw_);
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
        if (!this.bip70Url_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(2, getBip70Url());
        }
        if (!this.depositAddress_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(3, getDepositAddress());
        }
        if (!this.txRaw_.isEmpty()) {
            i2 += CodedOutputStream.computeBytesSize(4, this.txRaw_);
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static Bip70BroadcastTxRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Bip70BroadcastTxRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Bip70BroadcastTxRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Bip70BroadcastTxRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Bip70BroadcastTxRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Bip70BroadcastTxRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Bip70BroadcastTxRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Bip70BroadcastTxRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Bip70BroadcastTxRequest parseFrom(InputStream inputStream) throws IOException {
        return (Bip70BroadcastTxRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Bip70BroadcastTxRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Bip70BroadcastTxRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Bip70BroadcastTxRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Bip70BroadcastTxRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Bip70BroadcastTxRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Bip70BroadcastTxRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Bip70BroadcastTxRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Bip70BroadcastTxRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Bip70BroadcastTxRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Bip70BroadcastTxRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Bip70BroadcastTxRequest bip70BroadcastTxRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(bip70BroadcastTxRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new Bip70BroadcastTxRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                Bip70BroadcastTxRequest bip70BroadcastTxRequest = (Bip70BroadcastTxRequest) obj2;
                this.walletId_ = visitor.visitString(!this.walletId_.isEmpty(), this.walletId_, !bip70BroadcastTxRequest.walletId_.isEmpty(), bip70BroadcastTxRequest.walletId_);
                this.bip70Url_ = visitor.visitString(!this.bip70Url_.isEmpty(), this.bip70Url_, !bip70BroadcastTxRequest.bip70Url_.isEmpty(), bip70BroadcastTxRequest.bip70Url_);
                this.depositAddress_ = visitor.visitString(!this.depositAddress_.isEmpty(), this.depositAddress_, !bip70BroadcastTxRequest.depositAddress_.isEmpty(), bip70BroadcastTxRequest.depositAddress_);
                boolean z2 = this.txRaw_ != ByteString.EMPTY;
                ByteString byteString = this.txRaw_;
                if (bip70BroadcastTxRequest.txRaw_ != ByteString.EMPTY) {
                    z = true;
                }
                this.txRaw_ = visitor.visitByteString(z2, byteString, z, bip70BroadcastTxRequest.txRaw_);
                MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                this.walletId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.bip70Url_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                this.depositAddress_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 34) {
                                this.txRaw_ = codedInputStream.readBytes();
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
                    synchronized (Bip70BroadcastTxRequest.class) {
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

    public static Bip70BroadcastTxRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Bip70BroadcastTxRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
