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

public final class Featuredbusiness {

    public static final class FeaturedBusiness extends GeneratedMessageLite<FeaturedBusiness, Builder> implements FeaturedBusinessOrBuilder {
        /* access modifiers changed from: private */
        public static final FeaturedBusiness DEFAULT_INSTANCE = new FeaturedBusiness();
        public static final int ID_FIELD_NUMBER = 2;
        public static final int IMAGE_URL_FIELD_NUMBER = 6;
        public static final int INDEX_FIELD_NUMBER = 1;
        public static final int LOGO_IMAGE_URL_FIELD_NUMBER = 7;
        private static volatile Parser<FeaturedBusiness> PARSER = null;
        public static final int SHORT_DESCRIPTION_FIELD_NUMBER = 4;
        public static final int TITLE_FIELD_NUMBER = 3;
        public static final int URL_FIELD_NUMBER = 5;
        private String id_;
        private String imageUrl_;
        private int index_;
        private String logoImageUrl_;
        private String shortDescription_;
        private String title_;
        private String url_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<FeaturedBusiness, Builder> implements FeaturedBusinessOrBuilder {
            private Builder() {
                super(FeaturedBusiness.DEFAULT_INSTANCE);
            }

            public int getIndex() {
                return ((FeaturedBusiness) this.instance).getIndex();
            }

            public Builder setIndex(int i) {
                copyOnWrite();
                ((FeaturedBusiness) this.instance).setIndex(i);
                return this;
            }

            public Builder clearIndex() {
                copyOnWrite();
                ((FeaturedBusiness) this.instance).clearIndex();
                return this;
            }

            public String getId() {
                return ((FeaturedBusiness) this.instance).getId();
            }

            public ByteString getIdBytes() {
                return ((FeaturedBusiness) this.instance).getIdBytes();
            }

            public Builder setId(String str) {
                copyOnWrite();
                ((FeaturedBusiness) this.instance).setId(str);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((FeaturedBusiness) this.instance).clearId();
                return this;
            }

            public Builder setIdBytes(ByteString byteString) {
                copyOnWrite();
                ((FeaturedBusiness) this.instance).setIdBytes(byteString);
                return this;
            }

            public String getTitle() {
                return ((FeaturedBusiness) this.instance).getTitle();
            }

            public ByteString getTitleBytes() {
                return ((FeaturedBusiness) this.instance).getTitleBytes();
            }

            public Builder setTitle(String str) {
                copyOnWrite();
                ((FeaturedBusiness) this.instance).setTitle(str);
                return this;
            }

            public Builder clearTitle() {
                copyOnWrite();
                ((FeaturedBusiness) this.instance).clearTitle();
                return this;
            }

            public Builder setTitleBytes(ByteString byteString) {
                copyOnWrite();
                ((FeaturedBusiness) this.instance).setTitleBytes(byteString);
                return this;
            }

            public String getShortDescription() {
                return ((FeaturedBusiness) this.instance).getShortDescription();
            }

            public ByteString getShortDescriptionBytes() {
                return ((FeaturedBusiness) this.instance).getShortDescriptionBytes();
            }

            public Builder setShortDescription(String str) {
                copyOnWrite();
                ((FeaturedBusiness) this.instance).setShortDescription(str);
                return this;
            }

            public Builder clearShortDescription() {
                copyOnWrite();
                ((FeaturedBusiness) this.instance).clearShortDescription();
                return this;
            }

            public Builder setShortDescriptionBytes(ByteString byteString) {
                copyOnWrite();
                ((FeaturedBusiness) this.instance).setShortDescriptionBytes(byteString);
                return this;
            }

            public String getUrl() {
                return ((FeaturedBusiness) this.instance).getUrl();
            }

            public ByteString getUrlBytes() {
                return ((FeaturedBusiness) this.instance).getUrlBytes();
            }

            public Builder setUrl(String str) {
                copyOnWrite();
                ((FeaturedBusiness) this.instance).setUrl(str);
                return this;
            }

            public Builder clearUrl() {
                copyOnWrite();
                ((FeaturedBusiness) this.instance).clearUrl();
                return this;
            }

            public Builder setUrlBytes(ByteString byteString) {
                copyOnWrite();
                ((FeaturedBusiness) this.instance).setUrlBytes(byteString);
                return this;
            }

            public String getImageUrl() {
                return ((FeaturedBusiness) this.instance).getImageUrl();
            }

            public ByteString getImageUrlBytes() {
                return ((FeaturedBusiness) this.instance).getImageUrlBytes();
            }

            public Builder setImageUrl(String str) {
                copyOnWrite();
                ((FeaturedBusiness) this.instance).setImageUrl(str);
                return this;
            }

            public Builder clearImageUrl() {
                copyOnWrite();
                ((FeaturedBusiness) this.instance).clearImageUrl();
                return this;
            }

            public Builder setImageUrlBytes(ByteString byteString) {
                copyOnWrite();
                ((FeaturedBusiness) this.instance).setImageUrlBytes(byteString);
                return this;
            }

            public String getLogoImageUrl() {
                return ((FeaturedBusiness) this.instance).getLogoImageUrl();
            }

            public ByteString getLogoImageUrlBytes() {
                return ((FeaturedBusiness) this.instance).getLogoImageUrlBytes();
            }

            public Builder setLogoImageUrl(String str) {
                copyOnWrite();
                ((FeaturedBusiness) this.instance).setLogoImageUrl(str);
                return this;
            }

            public Builder clearLogoImageUrl() {
                copyOnWrite();
                ((FeaturedBusiness) this.instance).clearLogoImageUrl();
                return this;
            }

            public Builder setLogoImageUrlBytes(ByteString byteString) {
                copyOnWrite();
                ((FeaturedBusiness) this.instance).setLogoImageUrlBytes(byteString);
                return this;
            }
        }

        private FeaturedBusiness() {
            String str = "";
            this.id_ = str;
            this.title_ = str;
            this.shortDescription_ = str;
            this.url_ = str;
            this.imageUrl_ = str;
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

        public String getImageUrl() {
            return this.imageUrl_;
        }

        public ByteString getImageUrlBytes() {
            return ByteString.copyFromUtf8(this.imageUrl_);
        }

        /* access modifiers changed from: private */
        public void setImageUrl(String str) {
            if (str != null) {
                this.imageUrl_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void clearImageUrl() {
            this.imageUrl_ = getDefaultInstance().getImageUrl();
        }

        /* access modifiers changed from: private */
        public void setImageUrlBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.imageUrl_ = byteString.toStringUtf8();
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
            if (!this.imageUrl_.isEmpty()) {
                codedOutputStream.writeString(6, getImageUrl());
            }
            if (!this.logoImageUrl_.isEmpty()) {
                codedOutputStream.writeString(7, getLogoImageUrl());
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
            if (!this.imageUrl_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(6, getImageUrl());
            }
            if (!this.logoImageUrl_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(7, getLogoImageUrl());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static FeaturedBusiness parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FeaturedBusiness) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static FeaturedBusiness parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FeaturedBusiness) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static FeaturedBusiness parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FeaturedBusiness) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FeaturedBusiness parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FeaturedBusiness) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static FeaturedBusiness parseFrom(InputStream inputStream) throws IOException {
            return (FeaturedBusiness) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FeaturedBusiness parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FeaturedBusiness) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FeaturedBusiness parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FeaturedBusiness) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FeaturedBusiness parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FeaturedBusiness) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FeaturedBusiness parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FeaturedBusiness) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FeaturedBusiness parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FeaturedBusiness) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FeaturedBusiness featuredBusiness) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(featuredBusiness);
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new FeaturedBusiness();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    Visitor visitor = (Visitor) obj;
                    FeaturedBusiness featuredBusiness = (FeaturedBusiness) obj2;
                    boolean z2 = this.index_ != 0;
                    int i = this.index_;
                    if (featuredBusiness.index_ != 0) {
                        z = true;
                    }
                    this.index_ = visitor.visitInt(z2, i, z, featuredBusiness.index_);
                    this.id_ = visitor.visitString(!this.id_.isEmpty(), this.id_, !featuredBusiness.id_.isEmpty(), featuredBusiness.id_);
                    this.title_ = visitor.visitString(!this.title_.isEmpty(), this.title_, !featuredBusiness.title_.isEmpty(), featuredBusiness.title_);
                    this.shortDescription_ = visitor.visitString(!this.shortDescription_.isEmpty(), this.shortDescription_, !featuredBusiness.shortDescription_.isEmpty(), featuredBusiness.shortDescription_);
                    this.url_ = visitor.visitString(!this.url_.isEmpty(), this.url_, !featuredBusiness.url_.isEmpty(), featuredBusiness.url_);
                    this.imageUrl_ = visitor.visitString(!this.imageUrl_.isEmpty(), this.imageUrl_, !featuredBusiness.imageUrl_.isEmpty(), featuredBusiness.imageUrl_);
                    this.logoImageUrl_ = visitor.visitString(!this.logoImageUrl_.isEmpty(), this.logoImageUrl_, !featuredBusiness.logoImageUrl_.isEmpty(), featuredBusiness.logoImageUrl_);
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
                                    this.imageUrl_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 58) {
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
                        synchronized (FeaturedBusiness.class) {
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

        public static FeaturedBusiness getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FeaturedBusiness> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface FeaturedBusinessOrBuilder extends MessageLiteOrBuilder {
        String getId();

        ByteString getIdBytes();

        String getImageUrl();

        ByteString getImageUrlBytes();

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

    public static final class FeaturedBusinessRequest extends GeneratedMessageLite<FeaturedBusinessRequest, Builder> implements FeaturedBusinessRequestOrBuilder {
        /* access modifiers changed from: private */
        public static final FeaturedBusinessRequest DEFAULT_INSTANCE = new FeaturedBusinessRequest();
        private static volatile Parser<FeaturedBusinessRequest> PARSER;

        public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<FeaturedBusinessRequest, Builder> implements FeaturedBusinessRequestOrBuilder {
            private Builder() {
                super(FeaturedBusinessRequest.DEFAULT_INSTANCE);
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        }

        private FeaturedBusinessRequest() {
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            this.memoizedSerializedSize = 0;
            return 0;
        }

        public static FeaturedBusinessRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FeaturedBusinessRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static FeaturedBusinessRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FeaturedBusinessRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static FeaturedBusinessRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FeaturedBusinessRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FeaturedBusinessRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FeaturedBusinessRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static FeaturedBusinessRequest parseFrom(InputStream inputStream) throws IOException {
            return (FeaturedBusinessRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FeaturedBusinessRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FeaturedBusinessRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FeaturedBusinessRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FeaturedBusinessRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FeaturedBusinessRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FeaturedBusinessRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FeaturedBusinessRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FeaturedBusinessRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FeaturedBusinessRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FeaturedBusinessRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FeaturedBusinessRequest featuredBusinessRequest) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(featuredBusinessRequest);
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new FeaturedBusinessRequest();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    Visitor visitor = (Visitor) obj;
                    FeaturedBusinessRequest featuredBusinessRequest = (FeaturedBusinessRequest) obj2;
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
                        synchronized (FeaturedBusinessRequest.class) {
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

        public static FeaturedBusinessRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FeaturedBusinessRequest> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface FeaturedBusinessRequestOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class FeaturedBusinessResponse extends GeneratedMessageLite<FeaturedBusinessResponse, Builder> implements FeaturedBusinessResponseOrBuilder {
        /* access modifiers changed from: private */
        public static final FeaturedBusinessResponse DEFAULT_INSTANCE = new FeaturedBusinessResponse();
        public static final int LIST_FIELD_NUMBER = 1;
        private static volatile Parser<FeaturedBusinessResponse> PARSER;
        private ProtobufList<FeaturedBusiness> list_ = emptyProtobufList();

        public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<FeaturedBusinessResponse, Builder> implements FeaturedBusinessResponseOrBuilder {
            private Builder() {
                super(FeaturedBusinessResponse.DEFAULT_INSTANCE);
            }

            public List<FeaturedBusiness> getListList() {
                return Collections.unmodifiableList(((FeaturedBusinessResponse) this.instance).getListList());
            }

            public int getListCount() {
                return ((FeaturedBusinessResponse) this.instance).getListCount();
            }

            public FeaturedBusiness getList(int i) {
                return ((FeaturedBusinessResponse) this.instance).getList(i);
            }

            public Builder setList(int i, FeaturedBusiness featuredBusiness) {
                copyOnWrite();
                ((FeaturedBusinessResponse) this.instance).setList(i, featuredBusiness);
                return this;
            }

            public Builder setList(int i, Builder builder) {
                copyOnWrite();
                ((FeaturedBusinessResponse) this.instance).setList(i, builder);
                return this;
            }

            public Builder addList(FeaturedBusiness featuredBusiness) {
                copyOnWrite();
                ((FeaturedBusinessResponse) this.instance).addList(featuredBusiness);
                return this;
            }

            public Builder addList(int i, FeaturedBusiness featuredBusiness) {
                copyOnWrite();
                ((FeaturedBusinessResponse) this.instance).addList(i, featuredBusiness);
                return this;
            }

            public Builder addList(Builder builder) {
                copyOnWrite();
                ((FeaturedBusinessResponse) this.instance).addList(builder);
                return this;
            }

            public Builder addList(int i, Builder builder) {
                copyOnWrite();
                ((FeaturedBusinessResponse) this.instance).addList(i, builder);
                return this;
            }

            public Builder addAllList(Iterable<? extends FeaturedBusiness> iterable) {
                copyOnWrite();
                ((FeaturedBusinessResponse) this.instance).addAllList(iterable);
                return this;
            }

            public Builder clearList() {
                copyOnWrite();
                ((FeaturedBusinessResponse) this.instance).clearList();
                return this;
            }

            public Builder removeList(int i) {
                copyOnWrite();
                ((FeaturedBusinessResponse) this.instance).removeList(i);
                return this;
            }
        }

        private FeaturedBusinessResponse() {
        }

        public List<FeaturedBusiness> getListList() {
            return this.list_;
        }

        public List<? extends FeaturedBusinessOrBuilder> getListOrBuilderList() {
            return this.list_;
        }

        public int getListCount() {
            return this.list_.size();
        }

        public FeaturedBusiness getList(int i) {
            return (FeaturedBusiness) this.list_.get(i);
        }

        public FeaturedBusinessOrBuilder getListOrBuilder(int i) {
            return (FeaturedBusinessOrBuilder) this.list_.get(i);
        }

        private void ensureListIsMutable() {
            if (!this.list_.isModifiable()) {
                this.list_ = GeneratedMessageLite.mutableCopy(this.list_);
            }
        }

        /* access modifiers changed from: private */
        public void setList(int i, FeaturedBusiness featuredBusiness) {
            if (featuredBusiness != null) {
                ensureListIsMutable();
                this.list_.set(i, featuredBusiness);
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
        public void addList(FeaturedBusiness featuredBusiness) {
            if (featuredBusiness != null) {
                ensureListIsMutable();
                this.list_.add(featuredBusiness);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void addList(int i, FeaturedBusiness featuredBusiness) {
            if (featuredBusiness != null) {
                ensureListIsMutable();
                this.list_.add(i, featuredBusiness);
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
        public void addAllList(Iterable<? extends FeaturedBusiness> iterable) {
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

        public static FeaturedBusinessResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FeaturedBusinessResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static FeaturedBusinessResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FeaturedBusinessResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static FeaturedBusinessResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FeaturedBusinessResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FeaturedBusinessResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FeaturedBusinessResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static FeaturedBusinessResponse parseFrom(InputStream inputStream) throws IOException {
            return (FeaturedBusinessResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FeaturedBusinessResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FeaturedBusinessResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FeaturedBusinessResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FeaturedBusinessResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FeaturedBusinessResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FeaturedBusinessResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FeaturedBusinessResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FeaturedBusinessResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FeaturedBusinessResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FeaturedBusinessResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FeaturedBusinessResponse featuredBusinessResponse) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(featuredBusinessResponse);
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new FeaturedBusinessResponse();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.list_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    this.list_ = ((Visitor) obj).visitList(this.list_, ((FeaturedBusinessResponse) obj2).list_);
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
                                    this.list_.add(codedInputStream.readMessage(FeaturedBusiness.parser(), extensionRegistryLite));
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
                        synchronized (FeaturedBusinessResponse.class) {
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

        public static FeaturedBusinessResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FeaturedBusinessResponse> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface FeaturedBusinessResponseOrBuilder extends MessageLiteOrBuilder {
        FeaturedBusiness getList(int i);

        int getListCount();

        List<FeaturedBusiness> getListList();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private Featuredbusiness() {
    }
}
