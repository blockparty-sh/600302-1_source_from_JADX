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
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class Verifiedtoken {

    public static final class VerifiedToken extends GeneratedMessageLite<VerifiedToken, Builder> implements VerifiedTokenOrBuilder {
        public static final int DECIMALS_FIELD_NUMBER = 4;
        /* access modifiers changed from: private */
        public static final VerifiedToken DEFAULT_INSTANCE = new VerifiedToken();
        public static final int ICON_IMAGE_URL_FIELD_NUMBER = 5;
        public static final int ID_FIELD_NUMBER = 1;
        public static final int NAME_FIELD_NUMBER = 2;
        private static volatile Parser<VerifiedToken> PARSER = null;
        public static final int SYMBOL_FIELD_NUMBER = 3;
        private int decimals_;
        private String iconImageUrl_;
        private String id_;
        private String name_;
        private String symbol_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<VerifiedToken, Builder> implements VerifiedTokenOrBuilder {
            private Builder() {
                super(VerifiedToken.DEFAULT_INSTANCE);
            }

            public String getId() {
                return ((VerifiedToken) this.instance).getId();
            }

            public ByteString getIdBytes() {
                return ((VerifiedToken) this.instance).getIdBytes();
            }

            public Builder setId(String str) {
                copyOnWrite();
                ((VerifiedToken) this.instance).setId(str);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((VerifiedToken) this.instance).clearId();
                return this;
            }

            public Builder setIdBytes(ByteString byteString) {
                copyOnWrite();
                ((VerifiedToken) this.instance).setIdBytes(byteString);
                return this;
            }

            public String getName() {
                return ((VerifiedToken) this.instance).getName();
            }

            public ByteString getNameBytes() {
                return ((VerifiedToken) this.instance).getNameBytes();
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((VerifiedToken) this.instance).setName(str);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((VerifiedToken) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((VerifiedToken) this.instance).setNameBytes(byteString);
                return this;
            }

            public String getSymbol() {
                return ((VerifiedToken) this.instance).getSymbol();
            }

            public ByteString getSymbolBytes() {
                return ((VerifiedToken) this.instance).getSymbolBytes();
            }

            public Builder setSymbol(String str) {
                copyOnWrite();
                ((VerifiedToken) this.instance).setSymbol(str);
                return this;
            }

            public Builder clearSymbol() {
                copyOnWrite();
                ((VerifiedToken) this.instance).clearSymbol();
                return this;
            }

            public Builder setSymbolBytes(ByteString byteString) {
                copyOnWrite();
                ((VerifiedToken) this.instance).setSymbolBytes(byteString);
                return this;
            }

            public int getDecimals() {
                return ((VerifiedToken) this.instance).getDecimals();
            }

            public Builder setDecimals(int i) {
                copyOnWrite();
                ((VerifiedToken) this.instance).setDecimals(i);
                return this;
            }

            public Builder clearDecimals() {
                copyOnWrite();
                ((VerifiedToken) this.instance).clearDecimals();
                return this;
            }

            public String getIconImageUrl() {
                return ((VerifiedToken) this.instance).getIconImageUrl();
            }

            public ByteString getIconImageUrlBytes() {
                return ((VerifiedToken) this.instance).getIconImageUrlBytes();
            }

            public Builder setIconImageUrl(String str) {
                copyOnWrite();
                ((VerifiedToken) this.instance).setIconImageUrl(str);
                return this;
            }

            public Builder clearIconImageUrl() {
                copyOnWrite();
                ((VerifiedToken) this.instance).clearIconImageUrl();
                return this;
            }

            public Builder setIconImageUrlBytes(ByteString byteString) {
                copyOnWrite();
                ((VerifiedToken) this.instance).setIconImageUrlBytes(byteString);
                return this;
            }
        }

        private VerifiedToken() {
            String str = "";
            this.id_ = str;
            this.name_ = str;
            this.symbol_ = str;
            this.iconImageUrl_ = str;
        }

        public String getId() {
            return this.id_;
        }

        public ByteString getIdBytes() {
            return ByteString.copyFromUtf8(this.id_);
        }

        /* access modifiers changed from: private */
        public void setId(String str) {
            if (str != null) {
                this.id_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void clearId() {
            this.id_ = getDefaultInstance().getId();
        }

        /* access modifiers changed from: private */
        public void setIdBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.id_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public String getName() {
            return this.name_;
        }

        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        /* access modifiers changed from: private */
        public void setName(String str) {
            if (str != null) {
                this.name_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        /* access modifiers changed from: private */
        public void setNameBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.name_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public String getSymbol() {
            return this.symbol_;
        }

        public ByteString getSymbolBytes() {
            return ByteString.copyFromUtf8(this.symbol_);
        }

        /* access modifiers changed from: private */
        public void setSymbol(String str) {
            if (str != null) {
                this.symbol_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void clearSymbol() {
            this.symbol_ = getDefaultInstance().getSymbol();
        }

        /* access modifiers changed from: private */
        public void setSymbolBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.symbol_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public int getDecimals() {
            return this.decimals_;
        }

        /* access modifiers changed from: private */
        public void setDecimals(int i) {
            this.decimals_ = i;
        }

        /* access modifiers changed from: private */
        public void clearDecimals() {
            this.decimals_ = 0;
        }

        public String getIconImageUrl() {
            return this.iconImageUrl_;
        }

        public ByteString getIconImageUrlBytes() {
            return ByteString.copyFromUtf8(this.iconImageUrl_);
        }

        /* access modifiers changed from: private */
        public void setIconImageUrl(String str) {
            if (str != null) {
                this.iconImageUrl_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void clearIconImageUrl() {
            this.iconImageUrl_ = getDefaultInstance().getIconImageUrl();
        }

        /* access modifiers changed from: private */
        public void setIconImageUrlBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.iconImageUrl_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.id_.isEmpty()) {
                codedOutputStream.writeString(1, getId());
            }
            if (!this.name_.isEmpty()) {
                codedOutputStream.writeString(2, getName());
            }
            if (!this.symbol_.isEmpty()) {
                codedOutputStream.writeString(3, getSymbol());
            }
            int i = this.decimals_;
            if (i != 0) {
                codedOutputStream.writeUInt32(4, i);
            }
            if (!this.iconImageUrl_.isEmpty()) {
                codedOutputStream.writeString(5, getIconImageUrl());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.id_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getId());
            }
            if (!this.name_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getName());
            }
            if (!this.symbol_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(3, getSymbol());
            }
            int i3 = this.decimals_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(4, i3);
            }
            if (!this.iconImageUrl_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(5, getIconImageUrl());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static VerifiedToken parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (VerifiedToken) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static VerifiedToken parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (VerifiedToken) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static VerifiedToken parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (VerifiedToken) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static VerifiedToken parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (VerifiedToken) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static VerifiedToken parseFrom(InputStream inputStream) throws IOException {
            return (VerifiedToken) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static VerifiedToken parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VerifiedToken) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static VerifiedToken parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (VerifiedToken) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static VerifiedToken parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VerifiedToken) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static VerifiedToken parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (VerifiedToken) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static VerifiedToken parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VerifiedToken) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(VerifiedToken verifiedToken) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(verifiedToken);
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new VerifiedToken();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    Visitor visitor = (Visitor) obj;
                    VerifiedToken verifiedToken = (VerifiedToken) obj2;
                    this.id_ = visitor.visitString(!this.id_.isEmpty(), this.id_, !verifiedToken.id_.isEmpty(), verifiedToken.id_);
                    this.name_ = visitor.visitString(!this.name_.isEmpty(), this.name_, !verifiedToken.name_.isEmpty(), verifiedToken.name_);
                    this.symbol_ = visitor.visitString(!this.symbol_.isEmpty(), this.symbol_, !verifiedToken.symbol_.isEmpty(), verifiedToken.symbol_);
                    boolean z2 = this.decimals_ != 0;
                    int i = this.decimals_;
                    if (verifiedToken.decimals_ != 0) {
                        z = true;
                    }
                    this.decimals_ = visitor.visitInt(z2, i, z, verifiedToken.decimals_);
                    this.iconImageUrl_ = visitor.visitString(!this.iconImageUrl_.isEmpty(), this.iconImageUrl_, !verifiedToken.iconImageUrl_.isEmpty(), verifiedToken.iconImageUrl_);
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
                                    this.id_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 18) {
                                    this.name_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 26) {
                                    this.symbol_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 32) {
                                    this.decimals_ = codedInputStream.readUInt32();
                                } else if (readTag == 42) {
                                    this.iconImageUrl_ = codedInputStream.readStringRequireUtf8();
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
                        synchronized (VerifiedToken.class) {
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

        public static VerifiedToken getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<VerifiedToken> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface VerifiedTokenOrBuilder extends MessageLiteOrBuilder {
        int getDecimals();

        String getIconImageUrl();

        ByteString getIconImageUrlBytes();

        String getId();

        ByteString getIdBytes();

        String getName();

        ByteString getNameBytes();

        String getSymbol();

        ByteString getSymbolBytes();
    }

    public static final class VerifiedTokenRequest extends GeneratedMessageLite<VerifiedTokenRequest, Builder> implements VerifiedTokenRequestOrBuilder {
        /* access modifiers changed from: private */
        public static final VerifiedTokenRequest DEFAULT_INSTANCE = new VerifiedTokenRequest();
        private static volatile Parser<VerifiedTokenRequest> PARSER;

        public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<VerifiedTokenRequest, Builder> implements VerifiedTokenRequestOrBuilder {
            private Builder() {
                super(VerifiedTokenRequest.DEFAULT_INSTANCE);
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        }

        private VerifiedTokenRequest() {
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            this.memoizedSerializedSize = 0;
            return 0;
        }

        public static VerifiedTokenRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (VerifiedTokenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static VerifiedTokenRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (VerifiedTokenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static VerifiedTokenRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (VerifiedTokenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static VerifiedTokenRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (VerifiedTokenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static VerifiedTokenRequest parseFrom(InputStream inputStream) throws IOException {
            return (VerifiedTokenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static VerifiedTokenRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VerifiedTokenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static VerifiedTokenRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (VerifiedTokenRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static VerifiedTokenRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VerifiedTokenRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static VerifiedTokenRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (VerifiedTokenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static VerifiedTokenRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VerifiedTokenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(VerifiedTokenRequest verifiedTokenRequest) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(verifiedTokenRequest);
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new VerifiedTokenRequest();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    Visitor visitor = (Visitor) obj;
                    VerifiedTokenRequest verifiedTokenRequest = (VerifiedTokenRequest) obj2;
                    MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag == 0 || !codedInputStream.skipField(readTag)) {
                                z = true;
                            }
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
                        synchronized (VerifiedTokenRequest.class) {
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

        public static VerifiedTokenRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<VerifiedTokenRequest> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface VerifiedTokenRequestOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class VerifiedTokenResponse extends GeneratedMessageLite<VerifiedTokenResponse, Builder> implements VerifiedTokenResponseOrBuilder {
        /* access modifiers changed from: private */
        public static final VerifiedTokenResponse DEFAULT_INSTANCE = new VerifiedTokenResponse();
        public static final int LIST_FIELD_NUMBER = 1;
        private static volatile Parser<VerifiedTokenResponse> PARSER;
        private ProtobufList<VerifiedToken> list_ = emptyProtobufList();

        public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<VerifiedTokenResponse, Builder> implements VerifiedTokenResponseOrBuilder {
            private Builder() {
                super(VerifiedTokenResponse.DEFAULT_INSTANCE);
            }

            public List<VerifiedToken> getListList() {
                return Collections.unmodifiableList(((VerifiedTokenResponse) this.instance).getListList());
            }

            public int getListCount() {
                return ((VerifiedTokenResponse) this.instance).getListCount();
            }

            public VerifiedToken getList(int i) {
                return ((VerifiedTokenResponse) this.instance).getList(i);
            }

            public Builder setList(int i, VerifiedToken verifiedToken) {
                copyOnWrite();
                ((VerifiedTokenResponse) this.instance).setList(i, verifiedToken);
                return this;
            }

            public Builder setList(int i, Builder builder) {
                copyOnWrite();
                ((VerifiedTokenResponse) this.instance).setList(i, builder);
                return this;
            }

            public Builder addList(VerifiedToken verifiedToken) {
                copyOnWrite();
                ((VerifiedTokenResponse) this.instance).addList(verifiedToken);
                return this;
            }

            public Builder addList(int i, VerifiedToken verifiedToken) {
                copyOnWrite();
                ((VerifiedTokenResponse) this.instance).addList(i, verifiedToken);
                return this;
            }

            public Builder addList(Builder builder) {
                copyOnWrite();
                ((VerifiedTokenResponse) this.instance).addList(builder);
                return this;
            }

            public Builder addList(int i, Builder builder) {
                copyOnWrite();
                ((VerifiedTokenResponse) this.instance).addList(i, builder);
                return this;
            }

            public Builder addAllList(Iterable<? extends VerifiedToken> iterable) {
                copyOnWrite();
                ((VerifiedTokenResponse) this.instance).addAllList(iterable);
                return this;
            }

            public Builder clearList() {
                copyOnWrite();
                ((VerifiedTokenResponse) this.instance).clearList();
                return this;
            }

            public Builder removeList(int i) {
                copyOnWrite();
                ((VerifiedTokenResponse) this.instance).removeList(i);
                return this;
            }
        }

        private VerifiedTokenResponse() {
        }

        public List<VerifiedToken> getListList() {
            return this.list_;
        }

        public List<? extends VerifiedTokenOrBuilder> getListOrBuilderList() {
            return this.list_;
        }

        public int getListCount() {
            return this.list_.size();
        }

        public VerifiedToken getList(int i) {
            return (VerifiedToken) this.list_.get(i);
        }

        public VerifiedTokenOrBuilder getListOrBuilder(int i) {
            return (VerifiedTokenOrBuilder) this.list_.get(i);
        }

        private void ensureListIsMutable() {
            if (!this.list_.isModifiable()) {
                this.list_ = GeneratedMessageLite.mutableCopy(this.list_);
            }
        }

        /* access modifiers changed from: private */
        public void setList(int i, VerifiedToken verifiedToken) {
            if (verifiedToken != null) {
                ensureListIsMutable();
                this.list_.set(i, verifiedToken);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void setList(int i, Builder builder) {
            ensureListIsMutable();
            this.list_.set(i, builder.build());
        }

        /* access modifiers changed from: private */
        public void addList(VerifiedToken verifiedToken) {
            if (verifiedToken != null) {
                ensureListIsMutable();
                this.list_.add(verifiedToken);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void addList(int i, VerifiedToken verifiedToken) {
            if (verifiedToken != null) {
                ensureListIsMutable();
                this.list_.add(i, verifiedToken);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void addList(Builder builder) {
            ensureListIsMutable();
            this.list_.add(builder.build());
        }

        /* access modifiers changed from: private */
        public void addList(int i, Builder builder) {
            ensureListIsMutable();
            this.list_.add(i, builder.build());
        }

        /* access modifiers changed from: private */
        public void addAllList(Iterable<? extends VerifiedToken> iterable) {
            ensureListIsMutable();
            AbstractMessageLite.addAll(iterable, this.list_);
        }

        /* access modifiers changed from: private */
        public void clearList() {
            this.list_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeList(int i) {
            ensureListIsMutable();
            this.list_.remove(i);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.list_.size(); i++) {
                codedOutputStream.writeMessage(1, (MessageLite) this.list_.get(i));
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.list_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.list_.get(i3));
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static VerifiedTokenResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (VerifiedTokenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static VerifiedTokenResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (VerifiedTokenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static VerifiedTokenResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (VerifiedTokenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static VerifiedTokenResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (VerifiedTokenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static VerifiedTokenResponse parseFrom(InputStream inputStream) throws IOException {
            return (VerifiedTokenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static VerifiedTokenResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VerifiedTokenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static VerifiedTokenResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (VerifiedTokenResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static VerifiedTokenResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VerifiedTokenResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static VerifiedTokenResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (VerifiedTokenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static VerifiedTokenResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VerifiedTokenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(VerifiedTokenResponse verifiedTokenResponse) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(verifiedTokenResponse);
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new VerifiedTokenResponse();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.list_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    this.list_ = ((Visitor) obj).visitList(this.list_, ((VerifiedTokenResponse) obj2).list_);
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
                                    if (!this.list_.isModifiable()) {
                                        this.list_ = GeneratedMessageLite.mutableCopy(this.list_);
                                    }
                                    this.list_.add(codedInputStream.readMessage(VerifiedToken.parser(), extensionRegistryLite));
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
                        synchronized (VerifiedTokenResponse.class) {
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

        public static VerifiedTokenResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<VerifiedTokenResponse> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface VerifiedTokenResponseOrBuilder extends MessageLiteOrBuilder {
        VerifiedToken getList(int i);

        int getListCount();

        List<VerifiedToken> getListList();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private Verifiedtoken() {
    }
}
