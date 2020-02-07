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

public final class LinkOuterClass {

    public static final class Link extends GeneratedMessageLite<Link, Builder> implements LinkOrBuilder {
        /* access modifiers changed from: private */
        public static final Link DEFAULT_INSTANCE = new Link();
        public static final int ID_FIELD_NUMBER = 2;
        public static final int INDEX_FIELD_NUMBER = 1;
        public static final int LOGO_IMAGE_URL_FIELD_NUMBER = 6;
        private static volatile Parser<Link> PARSER = null;
        public static final int SHORT_DESCRIPTION_FIELD_NUMBER = 4;
        public static final int TITLE_FIELD_NUMBER = 3;
        public static final int URL_FIELD_NUMBER = 5;
        private String id_;
        private int index_;
        private String logoImageUrl_;
        private String shortDescription_;
        private String title_;
        private String url_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<Link, Builder> implements LinkOrBuilder {
            private Builder() {
                super(Link.DEFAULT_INSTANCE);
            }

            public int getIndex() {
                return ((Link) this.instance).getIndex();
            }

            public Builder setIndex(int i) {
                copyOnWrite();
                ((Link) this.instance).setIndex(i);
                return this;
            }

            public Builder clearIndex() {
                copyOnWrite();
                ((Link) this.instance).clearIndex();
                return this;
            }

            public String getId() {
                return ((Link) this.instance).getId();
            }

            public ByteString getIdBytes() {
                return ((Link) this.instance).getIdBytes();
            }

            public Builder setId(String str) {
                copyOnWrite();
                ((Link) this.instance).setId(str);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((Link) this.instance).clearId();
                return this;
            }

            public Builder setIdBytes(ByteString byteString) {
                copyOnWrite();
                ((Link) this.instance).setIdBytes(byteString);
                return this;
            }

            public String getTitle() {
                return ((Link) this.instance).getTitle();
            }

            public ByteString getTitleBytes() {
                return ((Link) this.instance).getTitleBytes();
            }

            public Builder setTitle(String str) {
                copyOnWrite();
                ((Link) this.instance).setTitle(str);
                return this;
            }

            public Builder clearTitle() {
                copyOnWrite();
                ((Link) this.instance).clearTitle();
                return this;
            }

            public Builder setTitleBytes(ByteString byteString) {
                copyOnWrite();
                ((Link) this.instance).setTitleBytes(byteString);
                return this;
            }

            public String getShortDescription() {
                return ((Link) this.instance).getShortDescription();
            }

            public ByteString getShortDescriptionBytes() {
                return ((Link) this.instance).getShortDescriptionBytes();
            }

            public Builder setShortDescription(String str) {
                copyOnWrite();
                ((Link) this.instance).setShortDescription(str);
                return this;
            }

            public Builder clearShortDescription() {
                copyOnWrite();
                ((Link) this.instance).clearShortDescription();
                return this;
            }

            public Builder setShortDescriptionBytes(ByteString byteString) {
                copyOnWrite();
                ((Link) this.instance).setShortDescriptionBytes(byteString);
                return this;
            }

            public String getUrl() {
                return ((Link) this.instance).getUrl();
            }

            public ByteString getUrlBytes() {
                return ((Link) this.instance).getUrlBytes();
            }

            public Builder setUrl(String str) {
                copyOnWrite();
                ((Link) this.instance).setUrl(str);
                return this;
            }

            public Builder clearUrl() {
                copyOnWrite();
                ((Link) this.instance).clearUrl();
                return this;
            }

            public Builder setUrlBytes(ByteString byteString) {
                copyOnWrite();
                ((Link) this.instance).setUrlBytes(byteString);
                return this;
            }

            public String getLogoImageUrl() {
                return ((Link) this.instance).getLogoImageUrl();
            }

            public ByteString getLogoImageUrlBytes() {
                return ((Link) this.instance).getLogoImageUrlBytes();
            }

            public Builder setLogoImageUrl(String str) {
                copyOnWrite();
                ((Link) this.instance).setLogoImageUrl(str);
                return this;
            }

            public Builder clearLogoImageUrl() {
                copyOnWrite();
                ((Link) this.instance).clearLogoImageUrl();
                return this;
            }

            public Builder setLogoImageUrlBytes(ByteString byteString) {
                copyOnWrite();
                ((Link) this.instance).setLogoImageUrlBytes(byteString);
                return this;
            }
        }

        private Link() {
            String str = "";
            this.id_ = str;
            this.title_ = str;
            this.shortDescription_ = str;
            this.url_ = str;
            this.logoImageUrl_ = str;
        }

        public int getIndex() {
            return this.index_;
        }

        /* access modifiers changed from: private */
        public void setIndex(int i) {
            this.index_ = i;
        }

        /* access modifiers changed from: private */
        public void clearIndex() {
            this.index_ = 0;
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

        public String getTitle() {
            return this.title_;
        }

        public ByteString getTitleBytes() {
            return ByteString.copyFromUtf8(this.title_);
        }

        /* access modifiers changed from: private */
        public void setTitle(String str) {
            if (str != null) {
                this.title_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void clearTitle() {
            this.title_ = getDefaultInstance().getTitle();
        }

        /* access modifiers changed from: private */
        public void setTitleBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.title_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public String getShortDescription() {
            return this.shortDescription_;
        }

        public ByteString getShortDescriptionBytes() {
            return ByteString.copyFromUtf8(this.shortDescription_);
        }

        /* access modifiers changed from: private */
        public void setShortDescription(String str) {
            if (str != null) {
                this.shortDescription_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void clearShortDescription() {
            this.shortDescription_ = getDefaultInstance().getShortDescription();
        }

        /* access modifiers changed from: private */
        public void setShortDescriptionBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.shortDescription_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public String getUrl() {
            return this.url_;
        }

        public ByteString getUrlBytes() {
            return ByteString.copyFromUtf8(this.url_);
        }

        /* access modifiers changed from: private */
        public void setUrl(String str) {
            if (str != null) {
                this.url_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void clearUrl() {
            this.url_ = getDefaultInstance().getUrl();
        }

        /* access modifiers changed from: private */
        public void setUrlBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.url_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public String getLogoImageUrl() {
            return this.logoImageUrl_;
        }

        public ByteString getLogoImageUrlBytes() {
            return ByteString.copyFromUtf8(this.logoImageUrl_);
        }

        /* access modifiers changed from: private */
        public void setLogoImageUrl(String str) {
            if (str != null) {
                this.logoImageUrl_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void clearLogoImageUrl() {
            this.logoImageUrl_ = getDefaultInstance().getLogoImageUrl();
        }

        /* access modifiers changed from: private */
        public void setLogoImageUrlBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.logoImageUrl_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.index_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (!this.id_.isEmpty()) {
                codedOutputStream.writeString(2, getId());
            }
            if (!this.title_.isEmpty()) {
                codedOutputStream.writeString(3, getTitle());
            }
            if (!this.shortDescription_.isEmpty()) {
                codedOutputStream.writeString(4, getShortDescription());
            }
            if (!this.url_.isEmpty()) {
                codedOutputStream.writeString(5, getUrl());
            }
            if (!this.logoImageUrl_.isEmpty()) {
                codedOutputStream.writeString(6, getLogoImageUrl());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.index_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            if (!this.id_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getId());
            }
            if (!this.title_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(3, getTitle());
            }
            if (!this.shortDescription_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(4, getShortDescription());
            }
            if (!this.url_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(5, getUrl());
            }
            if (!this.logoImageUrl_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(6, getLogoImageUrl());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static Link parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Link parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Link parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Link parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Link parseFrom(InputStream inputStream) throws IOException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Link parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Link parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Link) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Link parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Link) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Link parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Link parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Link link) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(link);
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new Link();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    Visitor visitor = (Visitor) obj;
                    Link link = (Link) obj2;
                    boolean z2 = this.index_ != 0;
                    int i = this.index_;
                    if (link.index_ != 0) {
                        z = true;
                    }
                    this.index_ = visitor.visitInt(z2, i, z, link.index_);
                    this.id_ = visitor.visitString(!this.id_.isEmpty(), this.id_, !link.id_.isEmpty(), link.id_);
                    this.title_ = visitor.visitString(!this.title_.isEmpty(), this.title_, !link.title_.isEmpty(), link.title_);
                    this.shortDescription_ = visitor.visitString(!this.shortDescription_.isEmpty(), this.shortDescription_, !link.shortDescription_.isEmpty(), link.shortDescription_);
                    this.url_ = visitor.visitString(!this.url_.isEmpty(), this.url_, !link.url_.isEmpty(), link.url_);
                    this.logoImageUrl_ = visitor.visitString(!this.logoImageUrl_.isEmpty(), this.logoImageUrl_, !link.logoImageUrl_.isEmpty(), link.logoImageUrl_);
                    MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.index_ = codedInputStream.readUInt32();
                                } else if (readTag == 18) {
                                    this.id_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 26) {
                                    this.title_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 34) {
                                    this.shortDescription_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 42) {
                                    this.url_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 50) {
                                    this.logoImageUrl_ = codedInputStream.readStringRequireUtf8();
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
                        synchronized (Link.class) {
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

        public static Link getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Link> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface LinkOrBuilder extends MessageLiteOrBuilder {
        String getId();

        ByteString getIdBytes();

        int getIndex();

        String getLogoImageUrl();

        ByteString getLogoImageUrlBytes();

        String getShortDescription();

        ByteString getShortDescriptionBytes();

        String getTitle();

        ByteString getTitleBytes();

        String getUrl();

        ByteString getUrlBytes();
    }

    public static final class LinkRequest extends GeneratedMessageLite<LinkRequest, Builder> implements LinkRequestOrBuilder {
        /* access modifiers changed from: private */
        public static final LinkRequest DEFAULT_INSTANCE = new LinkRequest();
        private static volatile Parser<LinkRequest> PARSER;

        public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<LinkRequest, Builder> implements LinkRequestOrBuilder {
            private Builder() {
                super(LinkRequest.DEFAULT_INSTANCE);
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        }

        private LinkRequest() {
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            this.memoizedSerializedSize = 0;
            return 0;
        }

        public static LinkRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (LinkRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static LinkRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LinkRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static LinkRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (LinkRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static LinkRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LinkRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static LinkRequest parseFrom(InputStream inputStream) throws IOException {
            return (LinkRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static LinkRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LinkRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static LinkRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (LinkRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static LinkRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LinkRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static LinkRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (LinkRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static LinkRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LinkRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(LinkRequest linkRequest) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(linkRequest);
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new LinkRequest();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    Visitor visitor = (Visitor) obj;
                    LinkRequest linkRequest = (LinkRequest) obj2;
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
                        synchronized (LinkRequest.class) {
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

        public static LinkRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<LinkRequest> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface LinkRequestOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class LinkResponse extends GeneratedMessageLite<LinkResponse, Builder> implements LinkResponseOrBuilder {
        /* access modifiers changed from: private */
        public static final LinkResponse DEFAULT_INSTANCE = new LinkResponse();
        public static final int ITEMS_FIELD_NUMBER = 1;
        private static volatile Parser<LinkResponse> PARSER;
        private ProtobufList<Link> items_ = emptyProtobufList();

        public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<LinkResponse, Builder> implements LinkResponseOrBuilder {
            private Builder() {
                super(LinkResponse.DEFAULT_INSTANCE);
            }

            public List<Link> getItemsList() {
                return Collections.unmodifiableList(((LinkResponse) this.instance).getItemsList());
            }

            public int getItemsCount() {
                return ((LinkResponse) this.instance).getItemsCount();
            }

            public Link getItems(int i) {
                return ((LinkResponse) this.instance).getItems(i);
            }

            public Builder setItems(int i, Link link) {
                copyOnWrite();
                ((LinkResponse) this.instance).setItems(i, link);
                return this;
            }

            public Builder setItems(int i, Builder builder) {
                copyOnWrite();
                ((LinkResponse) this.instance).setItems(i, builder);
                return this;
            }

            public Builder addItems(Link link) {
                copyOnWrite();
                ((LinkResponse) this.instance).addItems(link);
                return this;
            }

            public Builder addItems(int i, Link link) {
                copyOnWrite();
                ((LinkResponse) this.instance).addItems(i, link);
                return this;
            }

            public Builder addItems(Builder builder) {
                copyOnWrite();
                ((LinkResponse) this.instance).addItems(builder);
                return this;
            }

            public Builder addItems(int i, Builder builder) {
                copyOnWrite();
                ((LinkResponse) this.instance).addItems(i, builder);
                return this;
            }

            public Builder addAllItems(Iterable<? extends Link> iterable) {
                copyOnWrite();
                ((LinkResponse) this.instance).addAllItems(iterable);
                return this;
            }

            public Builder clearItems() {
                copyOnWrite();
                ((LinkResponse) this.instance).clearItems();
                return this;
            }

            public Builder removeItems(int i) {
                copyOnWrite();
                ((LinkResponse) this.instance).removeItems(i);
                return this;
            }
        }

        private LinkResponse() {
        }

        public List<Link> getItemsList() {
            return this.items_;
        }

        public List<? extends LinkOrBuilder> getItemsOrBuilderList() {
            return this.items_;
        }

        public int getItemsCount() {
            return this.items_.size();
        }

        public Link getItems(int i) {
            return (Link) this.items_.get(i);
        }

        public LinkOrBuilder getItemsOrBuilder(int i) {
            return (LinkOrBuilder) this.items_.get(i);
        }

        private void ensureItemsIsMutable() {
            if (!this.items_.isModifiable()) {
                this.items_ = GeneratedMessageLite.mutableCopy(this.items_);
            }
        }

        /* access modifiers changed from: private */
        public void setItems(int i, Link link) {
            if (link != null) {
                ensureItemsIsMutable();
                this.items_.set(i, link);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void setItems(int i, Builder builder) {
            ensureItemsIsMutable();
            this.items_.set(i, builder.build());
        }

        /* access modifiers changed from: private */
        public void addItems(Link link) {
            if (link != null) {
                ensureItemsIsMutable();
                this.items_.add(link);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void addItems(int i, Link link) {
            if (link != null) {
                ensureItemsIsMutable();
                this.items_.add(i, link);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void addItems(Builder builder) {
            ensureItemsIsMutable();
            this.items_.add(builder.build());
        }

        /* access modifiers changed from: private */
        public void addItems(int i, Builder builder) {
            ensureItemsIsMutable();
            this.items_.add(i, builder.build());
        }

        /* access modifiers changed from: private */
        public void addAllItems(Iterable<? extends Link> iterable) {
            ensureItemsIsMutable();
            AbstractMessageLite.addAll(iterable, this.items_);
        }

        /* access modifiers changed from: private */
        public void clearItems() {
            this.items_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeItems(int i) {
            ensureItemsIsMutable();
            this.items_.remove(i);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.items_.size(); i++) {
                codedOutputStream.writeMessage(1, (MessageLite) this.items_.get(i));
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.items_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.items_.get(i3));
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static LinkResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (LinkResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static LinkResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LinkResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static LinkResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (LinkResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static LinkResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LinkResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static LinkResponse parseFrom(InputStream inputStream) throws IOException {
            return (LinkResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static LinkResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LinkResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static LinkResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (LinkResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static LinkResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LinkResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static LinkResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (LinkResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static LinkResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LinkResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(LinkResponse linkResponse) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(linkResponse);
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new LinkResponse();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.items_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    this.items_ = ((Visitor) obj).visitList(this.items_, ((LinkResponse) obj2).items_);
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
                                    if (!this.items_.isModifiable()) {
                                        this.items_ = GeneratedMessageLite.mutableCopy(this.items_);
                                    }
                                    this.items_.add(codedInputStream.readMessage(Link.parser(), extensionRegistryLite));
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
                        synchronized (LinkResponse.class) {
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

        public static LinkResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<LinkResponse> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface LinkResponseOrBuilder extends MessageLiteOrBuilder {
        Link getItems(int i);

        int getItemsCount();

        List<Link> getItemsList();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private LinkOuterClass() {
    }
}
