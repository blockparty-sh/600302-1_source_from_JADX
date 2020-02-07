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
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class UnsubscribeRequest extends GeneratedMessageLite<UnsubscribeRequest, Builder> implements UnsubscribeRequestOrBuilder {
    /* access modifiers changed from: private */
    public static final UnsubscribeRequest DEFAULT_INSTANCE = new UnsubscribeRequest();
    private static volatile Parser<UnsubscribeRequest> PARSER = null;
    public static final int REQUEST_FIELD_NUMBER = 1;
    private ProtobufList<SubscriptionRequest> request_ = emptyProtobufList();

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<UnsubscribeRequest, Builder> implements UnsubscribeRequestOrBuilder {
        private Builder() {
            super(UnsubscribeRequest.DEFAULT_INSTANCE);
        }

        public List<SubscriptionRequest> getRequestList() {
            return Collections.unmodifiableList(((UnsubscribeRequest) this.instance).getRequestList());
        }

        public int getRequestCount() {
            return ((UnsubscribeRequest) this.instance).getRequestCount();
        }

        public SubscriptionRequest getRequest(int i) {
            return ((UnsubscribeRequest) this.instance).getRequest(i);
        }

        public Builder setRequest(int i, SubscriptionRequest subscriptionRequest) {
            copyOnWrite();
            ((UnsubscribeRequest) this.instance).setRequest(i, subscriptionRequest);
            return this;
        }

        public Builder setRequest(int i, com.bitcoin.mwallet.SubscriptionRequest.Builder builder) {
            copyOnWrite();
            ((UnsubscribeRequest) this.instance).setRequest(i, builder);
            return this;
        }

        public Builder addRequest(SubscriptionRequest subscriptionRequest) {
            copyOnWrite();
            ((UnsubscribeRequest) this.instance).addRequest(subscriptionRequest);
            return this;
        }

        public Builder addRequest(int i, SubscriptionRequest subscriptionRequest) {
            copyOnWrite();
            ((UnsubscribeRequest) this.instance).addRequest(i, subscriptionRequest);
            return this;
        }

        public Builder addRequest(com.bitcoin.mwallet.SubscriptionRequest.Builder builder) {
            copyOnWrite();
            ((UnsubscribeRequest) this.instance).addRequest(builder);
            return this;
        }

        public Builder addRequest(int i, com.bitcoin.mwallet.SubscriptionRequest.Builder builder) {
            copyOnWrite();
            ((UnsubscribeRequest) this.instance).addRequest(i, builder);
            return this;
        }

        public Builder addAllRequest(Iterable<? extends SubscriptionRequest> iterable) {
            copyOnWrite();
            ((UnsubscribeRequest) this.instance).addAllRequest(iterable);
            return this;
        }

        public Builder clearRequest() {
            copyOnWrite();
            ((UnsubscribeRequest) this.instance).clearRequest();
            return this;
        }

        public Builder removeRequest(int i) {
            copyOnWrite();
            ((UnsubscribeRequest) this.instance).removeRequest(i);
            return this;
        }
    }

    private UnsubscribeRequest() {
    }

    public List<SubscriptionRequest> getRequestList() {
        return this.request_;
    }

    public List<? extends SubscriptionRequestOrBuilder> getRequestOrBuilderList() {
        return this.request_;
    }

    public int getRequestCount() {
        return this.request_.size();
    }

    public SubscriptionRequest getRequest(int i) {
        return (SubscriptionRequest) this.request_.get(i);
    }

    public SubscriptionRequestOrBuilder getRequestOrBuilder(int i) {
        return (SubscriptionRequestOrBuilder) this.request_.get(i);
    }

    private void ensureRequestIsMutable() {
        if (!this.request_.isModifiable()) {
            this.request_ = GeneratedMessageLite.mutableCopy(this.request_);
        }
    }

    /* access modifiers changed from: private */
    public void setRequest(int i, SubscriptionRequest subscriptionRequest) {
        if (subscriptionRequest != null) {
            ensureRequestIsMutable();
            this.request_.set(i, subscriptionRequest);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setRequest(int i, com.bitcoin.mwallet.SubscriptionRequest.Builder builder) {
        ensureRequestIsMutable();
        this.request_.set(i, builder.build());
    }

    /* access modifiers changed from: private */
    public void addRequest(SubscriptionRequest subscriptionRequest) {
        if (subscriptionRequest != null) {
            ensureRequestIsMutable();
            this.request_.add(subscriptionRequest);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addRequest(int i, SubscriptionRequest subscriptionRequest) {
        if (subscriptionRequest != null) {
            ensureRequestIsMutable();
            this.request_.add(i, subscriptionRequest);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addRequest(com.bitcoin.mwallet.SubscriptionRequest.Builder builder) {
        ensureRequestIsMutable();
        this.request_.add(builder.build());
    }

    /* access modifiers changed from: private */
    public void addRequest(int i, com.bitcoin.mwallet.SubscriptionRequest.Builder builder) {
        ensureRequestIsMutable();
        this.request_.add(i, builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllRequest(Iterable<? extends SubscriptionRequest> iterable) {
        ensureRequestIsMutable();
        AbstractMessageLite.addAll(iterable, this.request_);
    }

    /* access modifiers changed from: private */
    public void clearRequest() {
        this.request_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeRequest(int i) {
        ensureRequestIsMutable();
        this.request_.remove(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.request_.size(); i++) {
            codedOutputStream.writeMessage(1, (MessageLite) this.request_.get(i));
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.request_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.request_.get(i3));
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static UnsubscribeRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (UnsubscribeRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static UnsubscribeRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UnsubscribeRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static UnsubscribeRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (UnsubscribeRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static UnsubscribeRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UnsubscribeRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static UnsubscribeRequest parseFrom(InputStream inputStream) throws IOException {
        return (UnsubscribeRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static UnsubscribeRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UnsubscribeRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static UnsubscribeRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (UnsubscribeRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static UnsubscribeRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UnsubscribeRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static UnsubscribeRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (UnsubscribeRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static UnsubscribeRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UnsubscribeRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UnsubscribeRequest unsubscribeRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(unsubscribeRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new UnsubscribeRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.request_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.request_ = ((Visitor) obj).visitList(this.request_, ((UnsubscribeRequest) obj2).request_);
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
                                if (!this.request_.isModifiable()) {
                                    this.request_ = GeneratedMessageLite.mutableCopy(this.request_);
                                }
                                this.request_.add(codedInputStream.readMessage(SubscriptionRequest.parser(), extensionRegistryLite));
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
                    synchronized (UnsubscribeRequest.class) {
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

    public static UnsubscribeRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UnsubscribeRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
