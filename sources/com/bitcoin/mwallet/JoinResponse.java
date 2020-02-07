package com.bitcoin.mwallet;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.MethodToInvoke;
import com.google.protobuf.Internal.ProtobufList;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class JoinResponse extends GeneratedMessageLite<JoinResponse, Builder> implements JoinResponseOrBuilder {
    public static final int COMPLETED_FIELD_NUMBER = 1;
    public static final int COPAYER_ID_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final JoinResponse DEFAULT_INSTANCE = new JoinResponse();
    public static final int OTHER_NAMES_FIELD_NUMBER = 4;
    private static volatile Parser<JoinResponse> PARSER = null;
    public static final int WALLET_NAME_FIELD_NUMBER = 3;
    private int bitField0_;
    private boolean completed_;
    private String copayerId_;
    private ProtobufList<String> otherNames_ = GeneratedMessageLite.emptyProtobufList();
    private String walletName_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<JoinResponse, Builder> implements JoinResponseOrBuilder {
        private Builder() {
            super(JoinResponse.DEFAULT_INSTANCE);
        }

        public boolean getCompleted() {
            return ((JoinResponse) this.instance).getCompleted();
        }

        public Builder setCompleted(boolean z) {
            copyOnWrite();
            ((JoinResponse) this.instance).setCompleted(z);
            return this;
        }

        public Builder clearCompleted() {
            copyOnWrite();
            ((JoinResponse) this.instance).clearCompleted();
            return this;
        }

        public String getCopayerId() {
            return ((JoinResponse) this.instance).getCopayerId();
        }

        public ByteString getCopayerIdBytes() {
            return ((JoinResponse) this.instance).getCopayerIdBytes();
        }

        public Builder setCopayerId(String str) {
            copyOnWrite();
            ((JoinResponse) this.instance).setCopayerId(str);
            return this;
        }

        public Builder clearCopayerId() {
            copyOnWrite();
            ((JoinResponse) this.instance).clearCopayerId();
            return this;
        }

        public Builder setCopayerIdBytes(ByteString byteString) {
            copyOnWrite();
            ((JoinResponse) this.instance).setCopayerIdBytes(byteString);
            return this;
        }

        public String getWalletName() {
            return ((JoinResponse) this.instance).getWalletName();
        }

        public ByteString getWalletNameBytes() {
            return ((JoinResponse) this.instance).getWalletNameBytes();
        }

        public Builder setWalletName(String str) {
            copyOnWrite();
            ((JoinResponse) this.instance).setWalletName(str);
            return this;
        }

        public Builder clearWalletName() {
            copyOnWrite();
            ((JoinResponse) this.instance).clearWalletName();
            return this;
        }

        public Builder setWalletNameBytes(ByteString byteString) {
            copyOnWrite();
            ((JoinResponse) this.instance).setWalletNameBytes(byteString);
            return this;
        }

        public List<String> getOtherNamesList() {
            return Collections.unmodifiableList(((JoinResponse) this.instance).getOtherNamesList());
        }

        public int getOtherNamesCount() {
            return ((JoinResponse) this.instance).getOtherNamesCount();
        }

        public String getOtherNames(int i) {
            return ((JoinResponse) this.instance).getOtherNames(i);
        }

        public ByteString getOtherNamesBytes(int i) {
            return ((JoinResponse) this.instance).getOtherNamesBytes(i);
        }

        public Builder setOtherNames(int i, String str) {
            copyOnWrite();
            ((JoinResponse) this.instance).setOtherNames(i, str);
            return this;
        }

        public Builder addOtherNames(String str) {
            copyOnWrite();
            ((JoinResponse) this.instance).addOtherNames(str);
            return this;
        }

        public Builder addAllOtherNames(Iterable<String> iterable) {
            copyOnWrite();
            ((JoinResponse) this.instance).addAllOtherNames(iterable);
            return this;
        }

        public Builder clearOtherNames() {
            copyOnWrite();
            ((JoinResponse) this.instance).clearOtherNames();
            return this;
        }

        public Builder addOtherNamesBytes(ByteString byteString) {
            copyOnWrite();
            ((JoinResponse) this.instance).addOtherNamesBytes(byteString);
            return this;
        }
    }

    private JoinResponse() {
        String str = "";
        this.copayerId_ = str;
        this.walletName_ = str;
    }

    public boolean getCompleted() {
        return this.completed_;
    }

    /* access modifiers changed from: private */
    public void setCompleted(boolean z) {
        this.completed_ = z;
    }

    /* access modifiers changed from: private */
    public void clearCompleted() {
        this.completed_ = false;
    }

    public String getCopayerId() {
        return this.copayerId_;
    }

    public ByteString getCopayerIdBytes() {
        return ByteString.copyFromUtf8(this.copayerId_);
    }

    /* access modifiers changed from: private */
    public void setCopayerId(String str) {
        if (str != null) {
            this.copayerId_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearCopayerId() {
        this.copayerId_ = getDefaultInstance().getCopayerId();
    }

    /* access modifiers changed from: private */
    public void setCopayerIdBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.copayerId_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getWalletName() {
        return this.walletName_;
    }

    public ByteString getWalletNameBytes() {
        return ByteString.copyFromUtf8(this.walletName_);
    }

    /* access modifiers changed from: private */
    public void setWalletName(String str) {
        if (str != null) {
            this.walletName_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearWalletName() {
        this.walletName_ = getDefaultInstance().getWalletName();
    }

    /* access modifiers changed from: private */
    public void setWalletNameBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.walletName_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public List<String> getOtherNamesList() {
        return this.otherNames_;
    }

    public int getOtherNamesCount() {
        return this.otherNames_.size();
    }

    public String getOtherNames(int i) {
        return (String) this.otherNames_.get(i);
    }

    public ByteString getOtherNamesBytes(int i) {
        return ByteString.copyFromUtf8((String) this.otherNames_.get(i));
    }

    private void ensureOtherNamesIsMutable() {
        if (!this.otherNames_.isModifiable()) {
            this.otherNames_ = GeneratedMessageLite.mutableCopy(this.otherNames_);
        }
    }

    /* access modifiers changed from: private */
    public void setOtherNames(int i, String str) {
        if (str != null) {
            ensureOtherNamesIsMutable();
            this.otherNames_.set(i, str);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addOtherNames(String str) {
        if (str != null) {
            ensureOtherNamesIsMutable();
            this.otherNames_.add(str);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addAllOtherNames(Iterable<String> iterable) {
        ensureOtherNamesIsMutable();
        AbstractMessageLite.addAll(iterable, this.otherNames_);
    }

    /* access modifiers changed from: private */
    public void clearOtherNames() {
        this.otherNames_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void addOtherNamesBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            ensureOtherNamesIsMutable();
            this.otherNames_.add(byteString.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        boolean z = this.completed_;
        if (z) {
            codedOutputStream.writeBool(1, z);
        }
        if (!this.copayerId_.isEmpty()) {
            codedOutputStream.writeString(2, getCopayerId());
        }
        if (!this.walletName_.isEmpty()) {
            codedOutputStream.writeString(3, getWalletName());
        }
        for (int i = 0; i < this.otherNames_.size(); i++) {
            codedOutputStream.writeString(4, (String) this.otherNames_.get(i));
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        boolean z = this.completed_;
        int computeBoolSize = z ? CodedOutputStream.computeBoolSize(1, z) + 0 : 0;
        if (!this.copayerId_.isEmpty()) {
            computeBoolSize += CodedOutputStream.computeStringSize(2, getCopayerId());
        }
        if (!this.walletName_.isEmpty()) {
            computeBoolSize += CodedOutputStream.computeStringSize(3, getWalletName());
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.otherNames_.size(); i3++) {
            i2 += CodedOutputStream.computeStringSizeNoTag((String) this.otherNames_.get(i3));
        }
        int size = computeBoolSize + i2 + (getOtherNamesList().size() * 1);
        this.memoizedSerializedSize = size;
        return size;
    }

    public static JoinResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (JoinResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static JoinResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (JoinResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static JoinResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (JoinResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static JoinResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (JoinResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static JoinResponse parseFrom(InputStream inputStream) throws IOException {
        return (JoinResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static JoinResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (JoinResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static JoinResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (JoinResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static JoinResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (JoinResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static JoinResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (JoinResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static JoinResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (JoinResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(JoinResponse joinResponse) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(joinResponse);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new JoinResponse();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.otherNames_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                JoinResponse joinResponse = (JoinResponse) obj2;
                boolean z = this.completed_;
                boolean z2 = joinResponse.completed_;
                this.completed_ = visitor.visitBoolean(z, z, z2, z2);
                this.copayerId_ = visitor.visitString(!this.copayerId_.isEmpty(), this.copayerId_, !joinResponse.copayerId_.isEmpty(), joinResponse.copayerId_);
                this.walletName_ = visitor.visitString(!this.walletName_.isEmpty(), this.walletName_, true ^ joinResponse.walletName_.isEmpty(), joinResponse.walletName_);
                this.otherNames_ = visitor.visitList(this.otherNames_, joinResponse.otherNames_);
                if (visitor == MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= joinResponse.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                boolean z3 = false;
                while (!z3) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.completed_ = codedInputStream.readBool();
                            } else if (readTag == 18) {
                                this.copayerId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                this.walletName_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 34) {
                                String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                if (!this.otherNames_.isModifiable()) {
                                    this.otherNames_ = GeneratedMessageLite.mutableCopy(this.otherNames_);
                                }
                                this.otherNames_.add(readStringRequireUtf8);
                            } else if (!codedInputStream.skipField(readTag)) {
                            }
                        }
                        z3 = true;
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
                    synchronized (JoinResponse.class) {
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

    public static JoinResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<JoinResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
