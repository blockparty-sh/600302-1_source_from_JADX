package org.bitcoin.crawler;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessage.BuilderParent;
import com.google.protobuf.GeneratedMessage.FieldAccessorTable;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilder;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PeerSeedProtos {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_org_bitcoin_crawler_PeerSeedData_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    /* access modifiers changed from: private */

    /* renamed from: internal_static_org_bitcoin_crawler_PeerSeedData_fieldAccessorTable */
    public static FieldAccessorTable f791x782bf9d9 = new FieldAccessorTable(internal_static_org_bitcoin_crawler_PeerSeedData_descriptor, new String[]{"IpAddress", "Port", "Services"});
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_org_bitcoin_crawler_PeerSeeds_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_org_bitcoin_crawler_PeerSeeds_fieldAccessorTable = new FieldAccessorTable(internal_static_org_bitcoin_crawler_PeerSeeds_descriptor, new String[]{"Seed", "Timestamp", "Net"});
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_org_bitcoin_crawler_SignedPeerSeeds_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(2));
    /* access modifiers changed from: private */

    /* renamed from: internal_static_org_bitcoin_crawler_SignedPeerSeeds_fieldAccessorTable */
    public static FieldAccessorTable f792x487ba9fe = new FieldAccessorTable(internal_static_org_bitcoin_crawler_SignedPeerSeeds_descriptor, new String[]{"PeerSeeds", "Signature", "Pubkey"});

    public static final class PeerSeedData extends GeneratedMessage implements PeerSeedDataOrBuilder {
        public static final int IP_ADDRESS_FIELD_NUMBER = 1;
        public static Parser<PeerSeedData> PARSER = new AbstractParser<PeerSeedData>() {
            public PeerSeedData parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PeerSeedData(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PORT_FIELD_NUMBER = 2;
        public static final int SERVICES_FIELD_NUMBER = 3;
        private static final PeerSeedData defaultInstance = new PeerSeedData(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public Object ipAddress_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public int port_;
        /* access modifiers changed from: private */
        public int services_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements PeerSeedDataOrBuilder {
            private int bitField0_;
            private Object ipAddress_;
            private int port_;
            private int services_;

            public static final Descriptor getDescriptor() {
                return PeerSeedProtos.internal_static_org_bitcoin_crawler_PeerSeedData_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return PeerSeedProtos.f791x782bf9d9.ensureFieldAccessorsInitialized(PeerSeedData.class, Builder.class);
            }

            private Builder() {
                this.ipAddress_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.ipAddress_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                PeerSeedData.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                PeerSeedData.super.clear();
                this.ipAddress_ = "";
                this.bitField0_ &= -2;
                this.port_ = 0;
                this.bitField0_ &= -3;
                this.services_ = 0;
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return PeerSeedProtos.internal_static_org_bitcoin_crawler_PeerSeedData_descriptor;
            }

            public PeerSeedData getDefaultInstanceForType() {
                return PeerSeedData.getDefaultInstance();
            }

            public PeerSeedData build() {
                PeerSeedData buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public PeerSeedData buildPartial() {
                PeerSeedData peerSeedData = new PeerSeedData((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                peerSeedData.ipAddress_ = this.ipAddress_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                peerSeedData.port_ = this.port_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                peerSeedData.services_ = this.services_;
                peerSeedData.bitField0_ = i2;
                onBuilt();
                return peerSeedData;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof PeerSeedData) {
                    return mergeFrom((PeerSeedData) message);
                }
                PeerSeedData.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PeerSeedData peerSeedData) {
                if (peerSeedData == PeerSeedData.getDefaultInstance()) {
                    return this;
                }
                if (peerSeedData.hasIpAddress()) {
                    this.bitField0_ |= 1;
                    this.ipAddress_ = peerSeedData.ipAddress_;
                    onChanged();
                }
                if (peerSeedData.hasPort()) {
                    setPort(peerSeedData.getPort());
                }
                if (peerSeedData.hasServices()) {
                    setServices(peerSeedData.getServices());
                }
                mergeUnknownFields(peerSeedData.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (hasIpAddress() && hasPort() && hasServices()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                PeerSeedData peerSeedData;
                PeerSeedData peerSeedData2 = null;
                try {
                    PeerSeedData peerSeedData3 = (PeerSeedData) PeerSeedData.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (peerSeedData3 != null) {
                        mergeFrom(peerSeedData3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    peerSeedData = (PeerSeedData) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    peerSeedData2 = peerSeedData;
                }
                if (peerSeedData2 != null) {
                    mergeFrom(peerSeedData2);
                }
                throw th;
            }

            public boolean hasIpAddress() {
                return (this.bitField0_ & 1) == 1;
            }

            public String getIpAddress() {
                Object obj = this.ipAddress_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.ipAddress_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getIpAddressBytes() {
                Object obj = this.ipAddress_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.ipAddress_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setIpAddress(String str) {
                if (str != null) {
                    this.bitField0_ |= 1;
                    this.ipAddress_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearIpAddress() {
                this.bitField0_ &= -2;
                this.ipAddress_ = PeerSeedData.getDefaultInstance().getIpAddress();
                onChanged();
                return this;
            }

            public Builder setIpAddressBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.ipAddress_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public boolean hasPort() {
                return (this.bitField0_ & 2) == 2;
            }

            public int getPort() {
                return this.port_;
            }

            public Builder setPort(int i) {
                this.bitField0_ |= 2;
                this.port_ = i;
                onChanged();
                return this;
            }

            public Builder clearPort() {
                this.bitField0_ &= -3;
                this.port_ = 0;
                onChanged();
                return this;
            }

            public boolean hasServices() {
                return (this.bitField0_ & 4) == 4;
            }

            public int getServices() {
                return this.services_;
            }

            public Builder setServices(int i) {
                this.bitField0_ |= 4;
                this.services_ = i;
                onChanged();
                return this;
            }

            public Builder clearServices() {
                this.bitField0_ &= -5;
                this.services_ = 0;
                onChanged();
                return this;
            }
        }

        private PeerSeedData(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private PeerSeedData(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static PeerSeedData getDefaultInstance() {
            return defaultInstance;
        }

        public PeerSeedData getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.protobuf.MessageLite, org.bitcoin.crawler.PeerSeedProtos$PeerSeedData] */
        private PeerSeedData(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            ByteString readBytes = codedInputStream.readBytes();
                            this.bitField0_ = 1 | this.bitField0_;
                            this.ipAddress_ = readBytes;
                        } else if (readTag == 16) {
                            this.bitField0_ |= 2;
                            this.port_ = codedInputStream.readUInt32();
                        } else if (readTag == 24) {
                            this.bitField0_ |= 4;
                            this.services_ = codedInputStream.readUInt32();
                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        public static final Descriptor getDescriptor() {
            return PeerSeedProtos.internal_static_org_bitcoin_crawler_PeerSeedData_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return PeerSeedProtos.f791x782bf9d9.ensureFieldAccessorsInitialized(PeerSeedData.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<PeerSeedData> getParserForType() {
            return PARSER;
        }

        public boolean hasIpAddress() {
            return (this.bitField0_ & 1) == 1;
        }

        public String getIpAddress() {
            Object obj = this.ipAddress_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.ipAddress_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getIpAddressBytes() {
            Object obj = this.ipAddress_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.ipAddress_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasPort() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getPort() {
            return this.port_;
        }

        public boolean hasServices() {
            return (this.bitField0_ & 4) == 4;
        }

        public int getServices() {
            return this.services_;
        }

        private void initFields() {
            this.ipAddress_ = "";
            this.port_ = 0;
            this.services_ = 0;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasIpAddress()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasPort()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasServices()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else {
                this.memoizedIsInitialized = 1;
                return true;
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, getIpAddressBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeUInt32(2, this.port_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeUInt32(3, this.services_);
            }
            getUnknownFields().writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, getIpAddressBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeUInt32Size(2, this.port_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeUInt32Size(3, this.services_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return PeerSeedProtos.super.writeReplace();
        }

        public static PeerSeedData parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (PeerSeedData) PARSER.parseFrom(byteString);
        }

        public static PeerSeedData parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PeerSeedData) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PeerSeedData parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (PeerSeedData) PARSER.parseFrom(bArr);
        }

        public static PeerSeedData parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PeerSeedData) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PeerSeedData parseFrom(InputStream inputStream) throws IOException {
            return (PeerSeedData) PARSER.parseFrom(inputStream);
        }

        public static PeerSeedData parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PeerSeedData) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static PeerSeedData parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PeerSeedData) PARSER.parseDelimitedFrom(inputStream);
        }

        public static PeerSeedData parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PeerSeedData) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static PeerSeedData parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PeerSeedData) PARSER.parseFrom(codedInputStream);
        }

        public static PeerSeedData parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PeerSeedData) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(PeerSeedData peerSeedData) {
            return newBuilder().mergeFrom(peerSeedData);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface PeerSeedDataOrBuilder extends MessageOrBuilder {
        String getIpAddress();

        ByteString getIpAddressBytes();

        int getPort();

        int getServices();

        boolean hasIpAddress();

        boolean hasPort();

        boolean hasServices();
    }

    public static final class PeerSeeds extends GeneratedMessage implements PeerSeedsOrBuilder {
        public static final int NET_FIELD_NUMBER = 3;
        public static Parser<PeerSeeds> PARSER = new AbstractParser<PeerSeeds>() {
            public PeerSeeds parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PeerSeeds(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SEED_FIELD_NUMBER = 1;
        public static final int TIMESTAMP_FIELD_NUMBER = 2;
        private static final PeerSeeds defaultInstance = new PeerSeeds(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public Object net_;
        /* access modifiers changed from: private */
        public List<PeerSeedData> seed_;
        /* access modifiers changed from: private */
        public long timestamp_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements PeerSeedsOrBuilder {
            private int bitField0_;
            private Object net_;
            private RepeatedFieldBuilder<PeerSeedData, Builder, PeerSeedDataOrBuilder> seedBuilder_;
            private List<PeerSeedData> seed_;
            private long timestamp_;

            public static final Descriptor getDescriptor() {
                return PeerSeedProtos.internal_static_org_bitcoin_crawler_PeerSeeds_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return PeerSeedProtos.internal_static_org_bitcoin_crawler_PeerSeeds_fieldAccessorTable.ensureFieldAccessorsInitialized(PeerSeeds.class, Builder.class);
            }

            private Builder() {
                this.seed_ = Collections.emptyList();
                this.net_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.seed_ = Collections.emptyList();
                this.net_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (PeerSeeds.alwaysUseFieldBuilders) {
                    getSeedFieldBuilder();
                }
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                PeerSeeds.super.clear();
                RepeatedFieldBuilder<PeerSeedData, Builder, PeerSeedDataOrBuilder> repeatedFieldBuilder = this.seedBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.seed_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilder.clear();
                }
                this.timestamp_ = 0;
                this.bitField0_ &= -3;
                this.net_ = "";
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return PeerSeedProtos.internal_static_org_bitcoin_crawler_PeerSeeds_descriptor;
            }

            public PeerSeeds getDefaultInstanceForType() {
                return PeerSeeds.getDefaultInstance();
            }

            public PeerSeeds build() {
                PeerSeeds buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public PeerSeeds buildPartial() {
                PeerSeeds peerSeeds = new PeerSeeds((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                RepeatedFieldBuilder<PeerSeedData, Builder, PeerSeedDataOrBuilder> repeatedFieldBuilder = this.seedBuilder_;
                int i2 = 1;
                if (repeatedFieldBuilder == null) {
                    if ((i & 1) == 1) {
                        this.seed_ = Collections.unmodifiableList(this.seed_);
                        this.bitField0_ &= -2;
                    }
                    peerSeeds.seed_ = this.seed_;
                } else {
                    peerSeeds.seed_ = repeatedFieldBuilder.build();
                }
                if ((i & 2) != 2) {
                    i2 = 0;
                }
                peerSeeds.timestamp_ = this.timestamp_;
                if ((i & 4) == 4) {
                    i2 |= 2;
                }
                peerSeeds.net_ = this.net_;
                peerSeeds.bitField0_ = i2;
                onBuilt();
                return peerSeeds;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof PeerSeeds) {
                    return mergeFrom((PeerSeeds) message);
                }
                PeerSeeds.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PeerSeeds peerSeeds) {
                if (peerSeeds == PeerSeeds.getDefaultInstance()) {
                    return this;
                }
                if (this.seedBuilder_ == null) {
                    if (!peerSeeds.seed_.isEmpty()) {
                        if (this.seed_.isEmpty()) {
                            this.seed_ = peerSeeds.seed_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureSeedIsMutable();
                            this.seed_.addAll(peerSeeds.seed_);
                        }
                        onChanged();
                    }
                } else if (!peerSeeds.seed_.isEmpty()) {
                    if (this.seedBuilder_.isEmpty()) {
                        this.seedBuilder_.dispose();
                        RepeatedFieldBuilder<PeerSeedData, Builder, PeerSeedDataOrBuilder> repeatedFieldBuilder = null;
                        this.seedBuilder_ = null;
                        this.seed_ = peerSeeds.seed_;
                        this.bitField0_ &= -2;
                        if (PeerSeeds.alwaysUseFieldBuilders) {
                            repeatedFieldBuilder = getSeedFieldBuilder();
                        }
                        this.seedBuilder_ = repeatedFieldBuilder;
                    } else {
                        this.seedBuilder_.addAllMessages(peerSeeds.seed_);
                    }
                }
                if (peerSeeds.hasTimestamp()) {
                    setTimestamp(peerSeeds.getTimestamp());
                }
                if (peerSeeds.hasNet()) {
                    this.bitField0_ |= 4;
                    this.net_ = peerSeeds.net_;
                    onChanged();
                }
                mergeUnknownFields(peerSeeds.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (!hasTimestamp() || !hasNet()) {
                    return false;
                }
                for (int i = 0; i < getSeedCount(); i++) {
                    if (!getSeed(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                PeerSeeds peerSeeds;
                PeerSeeds peerSeeds2 = null;
                try {
                    PeerSeeds peerSeeds3 = (PeerSeeds) PeerSeeds.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (peerSeeds3 != null) {
                        mergeFrom(peerSeeds3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    peerSeeds = (PeerSeeds) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    peerSeeds2 = peerSeeds;
                }
                if (peerSeeds2 != null) {
                    mergeFrom(peerSeeds2);
                }
                throw th;
            }

            private void ensureSeedIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.seed_ = new ArrayList(this.seed_);
                    this.bitField0_ |= 1;
                }
            }

            public List<PeerSeedData> getSeedList() {
                RepeatedFieldBuilder<PeerSeedData, Builder, PeerSeedDataOrBuilder> repeatedFieldBuilder = this.seedBuilder_;
                if (repeatedFieldBuilder == null) {
                    return Collections.unmodifiableList(this.seed_);
                }
                return repeatedFieldBuilder.getMessageList();
            }

            public int getSeedCount() {
                RepeatedFieldBuilder<PeerSeedData, Builder, PeerSeedDataOrBuilder> repeatedFieldBuilder = this.seedBuilder_;
                if (repeatedFieldBuilder == null) {
                    return this.seed_.size();
                }
                return repeatedFieldBuilder.getCount();
            }

            public PeerSeedData getSeed(int i) {
                RepeatedFieldBuilder<PeerSeedData, Builder, PeerSeedDataOrBuilder> repeatedFieldBuilder = this.seedBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (PeerSeedData) this.seed_.get(i);
                }
                return repeatedFieldBuilder.getMessage(i);
            }

            public Builder setSeed(int i, PeerSeedData peerSeedData) {
                RepeatedFieldBuilder<PeerSeedData, Builder, PeerSeedDataOrBuilder> repeatedFieldBuilder = this.seedBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.setMessage(i, peerSeedData);
                } else if (peerSeedData != null) {
                    ensureSeedIsMutable();
                    this.seed_.set(i, peerSeedData);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setSeed(int i, Builder builder) {
                RepeatedFieldBuilder<PeerSeedData, Builder, PeerSeedDataOrBuilder> repeatedFieldBuilder = this.seedBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureSeedIsMutable();
                    this.seed_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addSeed(PeerSeedData peerSeedData) {
                RepeatedFieldBuilder<PeerSeedData, Builder, PeerSeedDataOrBuilder> repeatedFieldBuilder = this.seedBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(peerSeedData);
                } else if (peerSeedData != null) {
                    ensureSeedIsMutable();
                    this.seed_.add(peerSeedData);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addSeed(int i, PeerSeedData peerSeedData) {
                RepeatedFieldBuilder<PeerSeedData, Builder, PeerSeedDataOrBuilder> repeatedFieldBuilder = this.seedBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(i, peerSeedData);
                } else if (peerSeedData != null) {
                    ensureSeedIsMutable();
                    this.seed_.add(i, peerSeedData);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addSeed(Builder builder) {
                RepeatedFieldBuilder<PeerSeedData, Builder, PeerSeedDataOrBuilder> repeatedFieldBuilder = this.seedBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureSeedIsMutable();
                    this.seed_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(builder.build());
                }
                return this;
            }

            public Builder addSeed(int i, Builder builder) {
                RepeatedFieldBuilder<PeerSeedData, Builder, PeerSeedDataOrBuilder> repeatedFieldBuilder = this.seedBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureSeedIsMutable();
                    this.seed_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllSeed(Iterable<? extends PeerSeedData> iterable) {
                RepeatedFieldBuilder<PeerSeedData, Builder, PeerSeedDataOrBuilder> repeatedFieldBuilder = this.seedBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureSeedIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.seed_);
                    onChanged();
                } else {
                    repeatedFieldBuilder.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearSeed() {
                RepeatedFieldBuilder<PeerSeedData, Builder, PeerSeedDataOrBuilder> repeatedFieldBuilder = this.seedBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.seed_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilder.clear();
                }
                return this;
            }

            public Builder removeSeed(int i) {
                RepeatedFieldBuilder<PeerSeedData, Builder, PeerSeedDataOrBuilder> repeatedFieldBuilder = this.seedBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureSeedIsMutable();
                    this.seed_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilder.remove(i);
                }
                return this;
            }

            public Builder getSeedBuilder(int i) {
                return getSeedFieldBuilder().getBuilder(i);
            }

            public PeerSeedDataOrBuilder getSeedOrBuilder(int i) {
                RepeatedFieldBuilder<PeerSeedData, Builder, PeerSeedDataOrBuilder> repeatedFieldBuilder = this.seedBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (PeerSeedDataOrBuilder) this.seed_.get(i);
                }
                return repeatedFieldBuilder.getMessageOrBuilder(i);
            }

            public List<? extends PeerSeedDataOrBuilder> getSeedOrBuilderList() {
                RepeatedFieldBuilder<PeerSeedData, Builder, PeerSeedDataOrBuilder> repeatedFieldBuilder = this.seedBuilder_;
                if (repeatedFieldBuilder != null) {
                    return repeatedFieldBuilder.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.seed_);
            }

            public Builder addSeedBuilder() {
                return getSeedFieldBuilder().addBuilder(PeerSeedData.getDefaultInstance());
            }

            public Builder addSeedBuilder(int i) {
                return getSeedFieldBuilder().addBuilder(i, PeerSeedData.getDefaultInstance());
            }

            public List<Builder> getSeedBuilderList() {
                return getSeedFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<PeerSeedData, Builder, PeerSeedDataOrBuilder> getSeedFieldBuilder() {
                if (this.seedBuilder_ == null) {
                    List<PeerSeedData> list = this.seed_;
                    boolean z = true;
                    if ((this.bitField0_ & 1) != 1) {
                        z = false;
                    }
                    this.seedBuilder_ = new RepeatedFieldBuilder<>(list, z, getParentForChildren(), isClean());
                    this.seed_ = null;
                }
                return this.seedBuilder_;
            }

            public boolean hasTimestamp() {
                return (this.bitField0_ & 2) == 2;
            }

            public long getTimestamp() {
                return this.timestamp_;
            }

            public Builder setTimestamp(long j) {
                this.bitField0_ |= 2;
                this.timestamp_ = j;
                onChanged();
                return this;
            }

            public Builder clearTimestamp() {
                this.bitField0_ &= -3;
                this.timestamp_ = 0;
                onChanged();
                return this;
            }

            public boolean hasNet() {
                return (this.bitField0_ & 4) == 4;
            }

            public String getNet() {
                Object obj = this.net_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.net_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getNetBytes() {
                Object obj = this.net_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.net_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setNet(String str) {
                if (str != null) {
                    this.bitField0_ |= 4;
                    this.net_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearNet() {
                this.bitField0_ &= -5;
                this.net_ = PeerSeeds.getDefaultInstance().getNet();
                onChanged();
                return this;
            }

            public Builder setNetBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 4;
                    this.net_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }
        }

        private PeerSeeds(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private PeerSeeds(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static PeerSeeds getDefaultInstance() {
            return defaultInstance;
        }

        public PeerSeeds getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r6v0, types: [com.google.protobuf.MessageLite, org.bitcoin.crawler.PeerSeedProtos$PeerSeeds] */
        private PeerSeeds(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            if (!z2 || !true) {
                                this.seed_ = new ArrayList();
                                z2 |= true;
                            }
                            this.seed_.add(codedInputStream.readMessage(PeerSeedData.PARSER, extensionRegistryLite));
                        } else if (readTag == 16) {
                            this.bitField0_ |= 1;
                            this.timestamp_ = codedInputStream.readUInt64();
                        } else if (readTag == 26) {
                            ByteString readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 2;
                            this.net_ = readBytes;
                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.seed_ = Collections.unmodifiableList(this.seed_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 && true) {
                this.seed_ = Collections.unmodifiableList(this.seed_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        public static final Descriptor getDescriptor() {
            return PeerSeedProtos.internal_static_org_bitcoin_crawler_PeerSeeds_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return PeerSeedProtos.internal_static_org_bitcoin_crawler_PeerSeeds_fieldAccessorTable.ensureFieldAccessorsInitialized(PeerSeeds.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<PeerSeeds> getParserForType() {
            return PARSER;
        }

        public List<PeerSeedData> getSeedList() {
            return this.seed_;
        }

        public List<? extends PeerSeedDataOrBuilder> getSeedOrBuilderList() {
            return this.seed_;
        }

        public int getSeedCount() {
            return this.seed_.size();
        }

        public PeerSeedData getSeed(int i) {
            return (PeerSeedData) this.seed_.get(i);
        }

        public PeerSeedDataOrBuilder getSeedOrBuilder(int i) {
            return (PeerSeedDataOrBuilder) this.seed_.get(i);
        }

        public boolean hasTimestamp() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getTimestamp() {
            return this.timestamp_;
        }

        public boolean hasNet() {
            return (this.bitField0_ & 2) == 2;
        }

        public String getNet() {
            Object obj = this.net_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.net_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getNetBytes() {
            Object obj = this.net_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.net_ = copyFromUtf8;
            return copyFromUtf8;
        }

        private void initFields() {
            this.seed_ = Collections.emptyList();
            this.timestamp_ = 0;
            this.net_ = "";
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasTimestamp()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasNet()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else {
                for (int i = 0; i < getSeedCount(); i++) {
                    if (!getSeed(i).isInitialized()) {
                        this.memoizedIsInitialized = 0;
                        return false;
                    }
                }
                this.memoizedIsInitialized = 1;
                return true;
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            for (int i = 0; i < this.seed_.size(); i++) {
                codedOutputStream.writeMessage(1, (MessageLite) this.seed_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeUInt64(2, this.timestamp_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(3, getNetBytes());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.seed_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.seed_.get(i3));
            }
            if ((this.bitField0_ & 1) == 1) {
                i2 += CodedOutputStream.computeUInt64Size(2, this.timestamp_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(3, getNetBytes());
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return PeerSeedProtos.super.writeReplace();
        }

        public static PeerSeeds parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (PeerSeeds) PARSER.parseFrom(byteString);
        }

        public static PeerSeeds parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PeerSeeds) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PeerSeeds parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (PeerSeeds) PARSER.parseFrom(bArr);
        }

        public static PeerSeeds parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PeerSeeds) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PeerSeeds parseFrom(InputStream inputStream) throws IOException {
            return (PeerSeeds) PARSER.parseFrom(inputStream);
        }

        public static PeerSeeds parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PeerSeeds) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static PeerSeeds parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PeerSeeds) PARSER.parseDelimitedFrom(inputStream);
        }

        public static PeerSeeds parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PeerSeeds) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static PeerSeeds parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PeerSeeds) PARSER.parseFrom(codedInputStream);
        }

        public static PeerSeeds parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PeerSeeds) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(PeerSeeds peerSeeds) {
            return newBuilder().mergeFrom(peerSeeds);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface PeerSeedsOrBuilder extends MessageOrBuilder {
        String getNet();

        ByteString getNetBytes();

        PeerSeedData getSeed(int i);

        int getSeedCount();

        List<PeerSeedData> getSeedList();

        PeerSeedDataOrBuilder getSeedOrBuilder(int i);

        List<? extends PeerSeedDataOrBuilder> getSeedOrBuilderList();

        long getTimestamp();

        boolean hasNet();

        boolean hasTimestamp();
    }

    public static final class SignedPeerSeeds extends GeneratedMessage implements SignedPeerSeedsOrBuilder {
        public static Parser<SignedPeerSeeds> PARSER = new AbstractParser<SignedPeerSeeds>() {
            public SignedPeerSeeds parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new SignedPeerSeeds(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PEER_SEEDS_FIELD_NUMBER = 1;
        public static final int PUBKEY_FIELD_NUMBER = 3;
        public static final int SIGNATURE_FIELD_NUMBER = 2;
        private static final SignedPeerSeeds defaultInstance = new SignedPeerSeeds(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString peerSeeds_;
        /* access modifiers changed from: private */
        public ByteString pubkey_;
        /* access modifiers changed from: private */
        public ByteString signature_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements SignedPeerSeedsOrBuilder {
            private int bitField0_;
            private ByteString peerSeeds_;
            private ByteString pubkey_;
            private ByteString signature_;

            public static final Descriptor getDescriptor() {
                return PeerSeedProtos.internal_static_org_bitcoin_crawler_SignedPeerSeeds_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return PeerSeedProtos.f792x487ba9fe.ensureFieldAccessorsInitialized(SignedPeerSeeds.class, Builder.class);
            }

            private Builder() {
                this.peerSeeds_ = ByteString.EMPTY;
                this.signature_ = ByteString.EMPTY;
                this.pubkey_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.peerSeeds_ = ByteString.EMPTY;
                this.signature_ = ByteString.EMPTY;
                this.pubkey_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                SignedPeerSeeds.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                SignedPeerSeeds.super.clear();
                this.peerSeeds_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                this.signature_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                this.pubkey_ = ByteString.EMPTY;
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return PeerSeedProtos.internal_static_org_bitcoin_crawler_SignedPeerSeeds_descriptor;
            }

            public SignedPeerSeeds getDefaultInstanceForType() {
                return SignedPeerSeeds.getDefaultInstance();
            }

            public SignedPeerSeeds build() {
                SignedPeerSeeds buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public SignedPeerSeeds buildPartial() {
                SignedPeerSeeds signedPeerSeeds = new SignedPeerSeeds((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                signedPeerSeeds.peerSeeds_ = this.peerSeeds_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                signedPeerSeeds.signature_ = this.signature_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                signedPeerSeeds.pubkey_ = this.pubkey_;
                signedPeerSeeds.bitField0_ = i2;
                onBuilt();
                return signedPeerSeeds;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof SignedPeerSeeds) {
                    return mergeFrom((SignedPeerSeeds) message);
                }
                SignedPeerSeeds.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(SignedPeerSeeds signedPeerSeeds) {
                if (signedPeerSeeds == SignedPeerSeeds.getDefaultInstance()) {
                    return this;
                }
                if (signedPeerSeeds.hasPeerSeeds()) {
                    setPeerSeeds(signedPeerSeeds.getPeerSeeds());
                }
                if (signedPeerSeeds.hasSignature()) {
                    setSignature(signedPeerSeeds.getSignature());
                }
                if (signedPeerSeeds.hasPubkey()) {
                    setPubkey(signedPeerSeeds.getPubkey());
                }
                mergeUnknownFields(signedPeerSeeds.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (hasPeerSeeds() && hasSignature() && hasPubkey()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                SignedPeerSeeds signedPeerSeeds;
                SignedPeerSeeds signedPeerSeeds2 = null;
                try {
                    SignedPeerSeeds signedPeerSeeds3 = (SignedPeerSeeds) SignedPeerSeeds.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (signedPeerSeeds3 != null) {
                        mergeFrom(signedPeerSeeds3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    signedPeerSeeds = (SignedPeerSeeds) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    signedPeerSeeds2 = signedPeerSeeds;
                }
                if (signedPeerSeeds2 != null) {
                    mergeFrom(signedPeerSeeds2);
                }
                throw th;
            }

            public boolean hasPeerSeeds() {
                return (this.bitField0_ & 1) == 1;
            }

            public ByteString getPeerSeeds() {
                return this.peerSeeds_;
            }

            public Builder setPeerSeeds(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.peerSeeds_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearPeerSeeds() {
                this.bitField0_ &= -2;
                this.peerSeeds_ = SignedPeerSeeds.getDefaultInstance().getPeerSeeds();
                onChanged();
                return this;
            }

            public boolean hasSignature() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getSignature() {
                return this.signature_;
            }

            public Builder setSignature(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.signature_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearSignature() {
                this.bitField0_ &= -3;
                this.signature_ = SignedPeerSeeds.getDefaultInstance().getSignature();
                onChanged();
                return this;
            }

            public boolean hasPubkey() {
                return (this.bitField0_ & 4) == 4;
            }

            public ByteString getPubkey() {
                return this.pubkey_;
            }

            public Builder setPubkey(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 4;
                    this.pubkey_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearPubkey() {
                this.bitField0_ &= -5;
                this.pubkey_ = SignedPeerSeeds.getDefaultInstance().getPubkey();
                onChanged();
                return this;
            }
        }

        private SignedPeerSeeds(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private SignedPeerSeeds(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static SignedPeerSeeds getDefaultInstance() {
            return defaultInstance;
        }

        public SignedPeerSeeds getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.protobuf.MessageLite, org.bitcoin.crawler.PeerSeedProtos$SignedPeerSeeds] */
        private SignedPeerSeeds(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            this.bitField0_ |= 1;
                            this.peerSeeds_ = codedInputStream.readBytes();
                        } else if (readTag == 18) {
                            this.bitField0_ |= 2;
                            this.signature_ = codedInputStream.readBytes();
                        } else if (readTag == 26) {
                            this.bitField0_ |= 4;
                            this.pubkey_ = codedInputStream.readBytes();
                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        public static final Descriptor getDescriptor() {
            return PeerSeedProtos.internal_static_org_bitcoin_crawler_SignedPeerSeeds_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return PeerSeedProtos.f792x487ba9fe.ensureFieldAccessorsInitialized(SignedPeerSeeds.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<SignedPeerSeeds> getParserForType() {
            return PARSER;
        }

        public boolean hasPeerSeeds() {
            return (this.bitField0_ & 1) == 1;
        }

        public ByteString getPeerSeeds() {
            return this.peerSeeds_;
        }

        public boolean hasSignature() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getSignature() {
            return this.signature_;
        }

        public boolean hasPubkey() {
            return (this.bitField0_ & 4) == 4;
        }

        public ByteString getPubkey() {
            return this.pubkey_;
        }

        private void initFields() {
            this.peerSeeds_ = ByteString.EMPTY;
            this.signature_ = ByteString.EMPTY;
            this.pubkey_ = ByteString.EMPTY;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasPeerSeeds()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasSignature()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasPubkey()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else {
                this.memoizedIsInitialized = 1;
                return true;
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, this.peerSeeds_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.signature_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBytes(3, this.pubkey_);
            }
            getUnknownFields().writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.peerSeeds_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(2, this.signature_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeBytesSize(3, this.pubkey_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return PeerSeedProtos.super.writeReplace();
        }

        public static SignedPeerSeeds parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (SignedPeerSeeds) PARSER.parseFrom(byteString);
        }

        public static SignedPeerSeeds parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SignedPeerSeeds) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static SignedPeerSeeds parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (SignedPeerSeeds) PARSER.parseFrom(bArr);
        }

        public static SignedPeerSeeds parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SignedPeerSeeds) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static SignedPeerSeeds parseFrom(InputStream inputStream) throws IOException {
            return (SignedPeerSeeds) PARSER.parseFrom(inputStream);
        }

        public static SignedPeerSeeds parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SignedPeerSeeds) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static SignedPeerSeeds parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SignedPeerSeeds) PARSER.parseDelimitedFrom(inputStream);
        }

        public static SignedPeerSeeds parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SignedPeerSeeds) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static SignedPeerSeeds parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SignedPeerSeeds) PARSER.parseFrom(codedInputStream);
        }

        public static SignedPeerSeeds parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SignedPeerSeeds) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(SignedPeerSeeds signedPeerSeeds) {
            return newBuilder().mergeFrom(signedPeerSeeds);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface SignedPeerSeedsOrBuilder extends MessageOrBuilder {
        ByteString getPeerSeeds();

        ByteString getPubkey();

        ByteString getSignature();

        boolean hasPeerSeeds();

        boolean hasPubkey();

        boolean hasSignature();
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
    }

    private PeerSeedProtos() {
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u000fpeerseeds.proto\u0012\u0013org.bitcoin.crawler\"B\n\fPeerSeedData\u0012\u0012\n\nip_address\u0018\u0001 \u0002(\t\u0012\f\n\u0004port\u0018\u0002 \u0002(\r\u0012\u0010\n\bservices\u0018\u0003 \u0002(\r\"\\\n\tPeerSeeds\u0012/\n\u0004seed\u0018\u0001 \u0003(\u000b2!.org.bitcoin.crawler.PeerSeedData\u0012\u0011\n\ttimestamp\u0018\u0002 \u0002(\u0004\u0012\u000b\n\u0003net\u0018\u0003 \u0002(\t\"H\n\u000fSignedPeerSeeds\u0012\u0012\n\npeer_seeds\u0018\u0001 \u0002(\f\u0012\u0011\n\tsignature\u0018\u0002 \u0002(\f\u0012\u000e\n\u0006pubkey\u0018\u0003 \u0002(\fB%\n\u0013org.bitcoin.crawlerB\u000ePeerSeedProtos"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                PeerSeedProtos.descriptor = fileDescriptor;
                return null;
            }
        });
    }
}
