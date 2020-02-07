package org.bitcoinj.wallet;

import android.support.p000v4.media.session.PlaybackStateCompat;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.EnumDescriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessage.BuilderParent;
import com.google.protobuf.GeneratedMessage.FieldAccessorTable;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RepeatedFieldBuilder;
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.UnknownFieldSet;
import com.htc.htcwalletsdk.Export.RESULT;
import com.leanplum.internal.Constants.Keys;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bitcoinj.script.ScriptOpCodes;

public final class Protos {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_wallet_DeterministicKey_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(2));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_wallet_DeterministicKey_fieldAccessorTable = new FieldAccessorTable(internal_static_wallet_DeterministicKey_descriptor, new String[]{"ChainCode", "Path", "IssuedSubkeys", "LookaheadSize", "IsFollowing", "SigsRequiredToSpend"});
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_wallet_EncryptedData_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_wallet_EncryptedData_fieldAccessorTable = new FieldAccessorTable(internal_static_wallet_EncryptedData_descriptor, new String[]{"InitialisationVector", "EncryptedPrivateKey"});
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_wallet_ExchangeRate_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(14));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_wallet_ExchangeRate_fieldAccessorTable = new FieldAccessorTable(internal_static_wallet_ExchangeRate_descriptor, new String[]{"CoinValue", "FiatValue", "FiatCurrencyCode"});
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_wallet_Extension_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(10));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_wallet_Extension_fieldAccessorTable;
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_wallet_Key_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(3));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_wallet_Key_fieldAccessorTable = new FieldAccessorTable(internal_static_wallet_Key_descriptor, new String[]{"Type", "SecretBytes", "EncryptedData", "PublicKey", "Label", "CreationTimestamp", "DeterministicKey", "DeterministicSeed", "EncryptedDeterministicSeed"});
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_wallet_PeerAddress_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_wallet_PeerAddress_fieldAccessorTable = new FieldAccessorTable(internal_static_wallet_PeerAddress_descriptor, new String[]{"IpAddress", "Port", "Services"});
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_wallet_Script_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(4));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_wallet_Script_fieldAccessorTable = new FieldAccessorTable(internal_static_wallet_Script_descriptor, new String[]{"Program", "CreationTimestamp"});
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_wallet_ScryptParameters_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(9));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_wallet_ScryptParameters_fieldAccessorTable = new FieldAccessorTable(internal_static_wallet_ScryptParameters_descriptor, new String[]{"Salt", "N", "R", "P"});
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_wallet_Tag_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(11));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_wallet_Tag_fieldAccessorTable;
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_wallet_TransactionConfidence_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(7));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_wallet_TransactionConfidence_fieldAccessorTable = new FieldAccessorTable(internal_static_wallet_TransactionConfidence_descriptor, new String[]{"Type", "AppearedAtHeight", "OverridingTransaction", "Depth", "BroadcastBy", "LastBroadcastedAt", "Source"});
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_wallet_TransactionInput_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(5));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_wallet_TransactionInput_fieldAccessorTable;
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_wallet_TransactionOutput_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(6));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_wallet_TransactionOutput_fieldAccessorTable;
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_wallet_TransactionSigner_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(12));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_wallet_TransactionSigner_fieldAccessorTable;
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_wallet_Transaction_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(8));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_wallet_Transaction_fieldAccessorTable = new FieldAccessorTable(internal_static_wallet_Transaction_descriptor, new String[]{"Version", "Hash", "Pool", "LockTime", "UpdatedAt", "TransactionInput", "TransactionOutput", "BlockHash", "BlockRelativityOffsets", "Confidence", "Purpose", "ExchangeRate", "Memo"});
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_wallet_Wallet_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(13));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_wallet_Wallet_fieldAccessorTable = new FieldAccessorTable(internal_static_wallet_Wallet_descriptor, new String[]{"NetworkIdentifier", "LastSeenBlockHash", "LastSeenBlockHeight", "LastSeenBlockTimeSecs", "Key", "Transaction", "WatchedScript", "EncryptionType", "EncryptionParameters", "Version", "Extension", "Description", "KeyRotationTime", "Tags", "TransactionSigners"});

    public static final class DeterministicKey extends GeneratedMessage implements DeterministicKeyOrBuilder {
        public static final int CHAIN_CODE_FIELD_NUMBER = 1;
        public static final int ISFOLLOWING_FIELD_NUMBER = 5;
        public static final int ISSUED_SUBKEYS_FIELD_NUMBER = 3;
        public static final int LOOKAHEAD_SIZE_FIELD_NUMBER = 4;
        public static Parser<DeterministicKey> PARSER = new AbstractParser<DeterministicKey>() {
            public DeterministicKey parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new DeterministicKey(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PATH_FIELD_NUMBER = 2;
        public static final int SIGSREQUIREDTOSPEND_FIELD_NUMBER = 6;
        private static final DeterministicKey defaultInstance = new DeterministicKey(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public ByteString chainCode_;
        /* access modifiers changed from: private */
        public boolean isFollowing_;
        /* access modifiers changed from: private */
        public int issuedSubkeys_;
        /* access modifiers changed from: private */
        public int lookaheadSize_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public List<Integer> path_;
        /* access modifiers changed from: private */
        public int sigsRequiredToSpend_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements DeterministicKeyOrBuilder {
            private int bitField0_;
            private ByteString chainCode_;
            private boolean isFollowing_;
            private int issuedSubkeys_;
            private int lookaheadSize_;
            private List<Integer> path_;
            private int sigsRequiredToSpend_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_wallet_DeterministicKey_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_wallet_DeterministicKey_fieldAccessorTable.ensureFieldAccessorsInitialized(DeterministicKey.class, Builder.class);
            }

            private Builder() {
                this.chainCode_ = ByteString.EMPTY;
                this.path_ = Collections.emptyList();
                this.sigsRequiredToSpend_ = 1;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.chainCode_ = ByteString.EMPTY;
                this.path_ = Collections.emptyList();
                this.sigsRequiredToSpend_ = 1;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                DeterministicKey.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                DeterministicKey.super.clear();
                this.chainCode_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                this.path_ = Collections.emptyList();
                this.bitField0_ &= -3;
                this.issuedSubkeys_ = 0;
                this.bitField0_ &= -5;
                this.lookaheadSize_ = 0;
                this.bitField0_ &= -9;
                this.isFollowing_ = false;
                this.bitField0_ &= -17;
                this.sigsRequiredToSpend_ = 1;
                this.bitField0_ &= -33;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_wallet_DeterministicKey_descriptor;
            }

            public DeterministicKey getDefaultInstanceForType() {
                return DeterministicKey.getDefaultInstance();
            }

            public DeterministicKey build() {
                DeterministicKey buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public DeterministicKey buildPartial() {
                DeterministicKey deterministicKey = new DeterministicKey((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                deterministicKey.chainCode_ = this.chainCode_;
                if ((this.bitField0_ & 2) == 2) {
                    this.path_ = Collections.unmodifiableList(this.path_);
                    this.bitField0_ &= -3;
                }
                deterministicKey.path_ = this.path_;
                if ((i & 4) == 4) {
                    i2 |= 2;
                }
                deterministicKey.issuedSubkeys_ = this.issuedSubkeys_;
                if ((i & 8) == 8) {
                    i2 |= 4;
                }
                deterministicKey.lookaheadSize_ = this.lookaheadSize_;
                if ((i & 16) == 16) {
                    i2 |= 8;
                }
                deterministicKey.isFollowing_ = this.isFollowing_;
                if ((i & 32) == 32) {
                    i2 |= 16;
                }
                deterministicKey.sigsRequiredToSpend_ = this.sigsRequiredToSpend_;
                deterministicKey.bitField0_ = i2;
                onBuilt();
                return deterministicKey;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof DeterministicKey) {
                    return mergeFrom((DeterministicKey) message);
                }
                DeterministicKey.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(DeterministicKey deterministicKey) {
                if (deterministicKey == DeterministicKey.getDefaultInstance()) {
                    return this;
                }
                if (deterministicKey.hasChainCode()) {
                    setChainCode(deterministicKey.getChainCode());
                }
                if (!deterministicKey.path_.isEmpty()) {
                    if (this.path_.isEmpty()) {
                        this.path_ = deterministicKey.path_;
                        this.bitField0_ &= -3;
                    } else {
                        ensurePathIsMutable();
                        this.path_.addAll(deterministicKey.path_);
                    }
                    onChanged();
                }
                if (deterministicKey.hasIssuedSubkeys()) {
                    setIssuedSubkeys(deterministicKey.getIssuedSubkeys());
                }
                if (deterministicKey.hasLookaheadSize()) {
                    setLookaheadSize(deterministicKey.getLookaheadSize());
                }
                if (deterministicKey.hasIsFollowing()) {
                    setIsFollowing(deterministicKey.getIsFollowing());
                }
                if (deterministicKey.hasSigsRequiredToSpend()) {
                    setSigsRequiredToSpend(deterministicKey.getSigsRequiredToSpend());
                }
                mergeUnknownFields(deterministicKey.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                return hasChainCode();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                DeterministicKey deterministicKey;
                DeterministicKey deterministicKey2 = null;
                try {
                    DeterministicKey deterministicKey3 = (DeterministicKey) DeterministicKey.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (deterministicKey3 != null) {
                        mergeFrom(deterministicKey3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    deterministicKey = (DeterministicKey) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    deterministicKey2 = deterministicKey;
                }
                if (deterministicKey2 != null) {
                    mergeFrom(deterministicKey2);
                }
                throw th;
            }

            public boolean hasChainCode() {
                return (this.bitField0_ & 1) == 1;
            }

            public ByteString getChainCode() {
                return this.chainCode_;
            }

            public Builder setChainCode(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.chainCode_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearChainCode() {
                this.bitField0_ &= -2;
                this.chainCode_ = DeterministicKey.getDefaultInstance().getChainCode();
                onChanged();
                return this;
            }

            private void ensurePathIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.path_ = new ArrayList(this.path_);
                    this.bitField0_ |= 2;
                }
            }

            public List<Integer> getPathList() {
                return Collections.unmodifiableList(this.path_);
            }

            public int getPathCount() {
                return this.path_.size();
            }

            public int getPath(int i) {
                return ((Integer) this.path_.get(i)).intValue();
            }

            public Builder setPath(int i, int i2) {
                ensurePathIsMutable();
                this.path_.set(i, Integer.valueOf(i2));
                onChanged();
                return this;
            }

            public Builder addPath(int i) {
                ensurePathIsMutable();
                this.path_.add(Integer.valueOf(i));
                onChanged();
                return this;
            }

            public Builder addAllPath(Iterable<? extends Integer> iterable) {
                ensurePathIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.path_);
                onChanged();
                return this;
            }

            public Builder clearPath() {
                this.path_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
                return this;
            }

            public boolean hasIssuedSubkeys() {
                return (this.bitField0_ & 4) == 4;
            }

            public int getIssuedSubkeys() {
                return this.issuedSubkeys_;
            }

            public Builder setIssuedSubkeys(int i) {
                this.bitField0_ |= 4;
                this.issuedSubkeys_ = i;
                onChanged();
                return this;
            }

            public Builder clearIssuedSubkeys() {
                this.bitField0_ &= -5;
                this.issuedSubkeys_ = 0;
                onChanged();
                return this;
            }

            public boolean hasLookaheadSize() {
                return (this.bitField0_ & 8) == 8;
            }

            public int getLookaheadSize() {
                return this.lookaheadSize_;
            }

            public Builder setLookaheadSize(int i) {
                this.bitField0_ |= 8;
                this.lookaheadSize_ = i;
                onChanged();
                return this;
            }

            public Builder clearLookaheadSize() {
                this.bitField0_ &= -9;
                this.lookaheadSize_ = 0;
                onChanged();
                return this;
            }

            public boolean hasIsFollowing() {
                return (this.bitField0_ & 16) == 16;
            }

            public boolean getIsFollowing() {
                return this.isFollowing_;
            }

            public Builder setIsFollowing(boolean z) {
                this.bitField0_ |= 16;
                this.isFollowing_ = z;
                onChanged();
                return this;
            }

            public Builder clearIsFollowing() {
                this.bitField0_ &= -17;
                this.isFollowing_ = false;
                onChanged();
                return this;
            }

            public boolean hasSigsRequiredToSpend() {
                return (this.bitField0_ & 32) == 32;
            }

            public int getSigsRequiredToSpend() {
                return this.sigsRequiredToSpend_;
            }

            public Builder setSigsRequiredToSpend(int i) {
                this.bitField0_ |= 32;
                this.sigsRequiredToSpend_ = i;
                onChanged();
                return this;
            }

            public Builder clearSigsRequiredToSpend() {
                this.bitField0_ &= -33;
                this.sigsRequiredToSpend_ = 1;
                onChanged();
                return this;
            }
        }

        private DeterministicKey(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private DeterministicKey(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static DeterministicKey getDefaultInstance() {
            return defaultInstance;
        }

        public DeterministicKey getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r8v0, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$DeterministicKey] */
        private DeterministicKey(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.bitField0_ |= 1;
                            this.chainCode_ = codedInputStream.readBytes();
                        } else if (readTag == 16) {
                            if (!(z2 & true)) {
                                this.path_ = new ArrayList();
                                z2 |= true;
                            }
                            this.path_.add(Integer.valueOf(codedInputStream.readUInt32()));
                        } else if (readTag == 18) {
                            int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                            if (!(z2 & true) && codedInputStream.getBytesUntilLimit() > 0) {
                                this.path_ = new ArrayList();
                                z2 |= true;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.path_.add(Integer.valueOf(codedInputStream.readUInt32()));
                            }
                            codedInputStream.popLimit(pushLimit);
                        } else if (readTag == 24) {
                            this.bitField0_ |= 2;
                            this.issuedSubkeys_ = codedInputStream.readUInt32();
                        } else if (readTag == 32) {
                            this.bitField0_ |= 4;
                            this.lookaheadSize_ = codedInputStream.readUInt32();
                        } else if (readTag == 40) {
                            this.bitField0_ |= 8;
                            this.isFollowing_ = codedInputStream.readBool();
                        } else if (readTag == 48) {
                            this.bitField0_ |= 16;
                            this.sigsRequiredToSpend_ = codedInputStream.readUInt32();
                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.path_ = Collections.unmodifiableList(this.path_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.path_ = Collections.unmodifiableList(this.path_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        public static final Descriptor getDescriptor() {
            return Protos.internal_static_wallet_DeterministicKey_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_wallet_DeterministicKey_fieldAccessorTable.ensureFieldAccessorsInitialized(DeterministicKey.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<DeterministicKey> getParserForType() {
            return PARSER;
        }

        public boolean hasChainCode() {
            return (this.bitField0_ & 1) == 1;
        }

        public ByteString getChainCode() {
            return this.chainCode_;
        }

        public List<Integer> getPathList() {
            return this.path_;
        }

        public int getPathCount() {
            return this.path_.size();
        }

        public int getPath(int i) {
            return ((Integer) this.path_.get(i)).intValue();
        }

        public boolean hasIssuedSubkeys() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getIssuedSubkeys() {
            return this.issuedSubkeys_;
        }

        public boolean hasLookaheadSize() {
            return (this.bitField0_ & 4) == 4;
        }

        public int getLookaheadSize() {
            return this.lookaheadSize_;
        }

        public boolean hasIsFollowing() {
            return (this.bitField0_ & 8) == 8;
        }

        public boolean getIsFollowing() {
            return this.isFollowing_;
        }

        public boolean hasSigsRequiredToSpend() {
            return (this.bitField0_ & 16) == 16;
        }

        public int getSigsRequiredToSpend() {
            return this.sigsRequiredToSpend_;
        }

        private void initFields() {
            this.chainCode_ = ByteString.EMPTY;
            this.path_ = Collections.emptyList();
            this.issuedSubkeys_ = 0;
            this.lookaheadSize_ = 0;
            this.isFollowing_ = false;
            this.sigsRequiredToSpend_ = 1;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasChainCode()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, this.chainCode_);
            }
            for (int i = 0; i < this.path_.size(); i++) {
                codedOutputStream.writeUInt32(2, ((Integer) this.path_.get(i)).intValue());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeUInt32(3, this.issuedSubkeys_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeUInt32(4, this.lookaheadSize_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeBool(5, this.isFollowing_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeUInt32(6, this.sigsRequiredToSpend_);
            }
            getUnknownFields().writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeBytesSize(1, this.chainCode_) + 0 : 0;
            int i2 = 0;
            for (int i3 = 0; i3 < this.path_.size(); i3++) {
                i2 += CodedOutputStream.computeUInt32SizeNoTag(((Integer) this.path_.get(i3)).intValue());
            }
            int size = computeBytesSize + i2 + (getPathList().size() * 1);
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeUInt32Size(3, this.issuedSubkeys_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeUInt32Size(4, this.lookaheadSize_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size += CodedOutputStream.computeBoolSize(5, this.isFollowing_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size += CodedOutputStream.computeUInt32Size(6, this.sigsRequiredToSpend_);
            }
            int serializedSize = size + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static DeterministicKey parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (DeterministicKey) PARSER.parseFrom(byteString);
        }

        public static DeterministicKey parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeterministicKey) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static DeterministicKey parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (DeterministicKey) PARSER.parseFrom(bArr);
        }

        public static DeterministicKey parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeterministicKey) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static DeterministicKey parseFrom(InputStream inputStream) throws IOException {
            return (DeterministicKey) PARSER.parseFrom(inputStream);
        }

        public static DeterministicKey parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeterministicKey) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static DeterministicKey parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (DeterministicKey) PARSER.parseDelimitedFrom(inputStream);
        }

        public static DeterministicKey parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeterministicKey) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static DeterministicKey parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (DeterministicKey) PARSER.parseFrom(codedInputStream);
        }

        public static DeterministicKey parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeterministicKey) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(DeterministicKey deterministicKey) {
            return newBuilder().mergeFrom(deterministicKey);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface DeterministicKeyOrBuilder extends MessageOrBuilder {
        ByteString getChainCode();

        boolean getIsFollowing();

        int getIssuedSubkeys();

        int getLookaheadSize();

        int getPath(int i);

        int getPathCount();

        List<Integer> getPathList();

        int getSigsRequiredToSpend();

        boolean hasChainCode();

        boolean hasIsFollowing();

        boolean hasIssuedSubkeys();

        boolean hasLookaheadSize();

        boolean hasSigsRequiredToSpend();
    }

    public static final class EncryptedData extends GeneratedMessage implements EncryptedDataOrBuilder {
        public static final int ENCRYPTED_PRIVATE_KEY_FIELD_NUMBER = 2;
        public static final int INITIALISATION_VECTOR_FIELD_NUMBER = 1;
        public static Parser<EncryptedData> PARSER = new AbstractParser<EncryptedData>() {
            public EncryptedData parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new EncryptedData(codedInputStream, extensionRegistryLite);
            }
        };
        private static final EncryptedData defaultInstance = new EncryptedData(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public ByteString encryptedPrivateKey_;
        /* access modifiers changed from: private */
        public ByteString initialisationVector_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements EncryptedDataOrBuilder {
            private int bitField0_;
            private ByteString encryptedPrivateKey_;
            private ByteString initialisationVector_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_wallet_EncryptedData_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_wallet_EncryptedData_fieldAccessorTable.ensureFieldAccessorsInitialized(EncryptedData.class, Builder.class);
            }

            private Builder() {
                this.initialisationVector_ = ByteString.EMPTY;
                this.encryptedPrivateKey_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.initialisationVector_ = ByteString.EMPTY;
                this.encryptedPrivateKey_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                EncryptedData.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                EncryptedData.super.clear();
                this.initialisationVector_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                this.encryptedPrivateKey_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_wallet_EncryptedData_descriptor;
            }

            public EncryptedData getDefaultInstanceForType() {
                return EncryptedData.getDefaultInstance();
            }

            public EncryptedData build() {
                EncryptedData buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public EncryptedData buildPartial() {
                EncryptedData encryptedData = new EncryptedData((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                encryptedData.initialisationVector_ = this.initialisationVector_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                encryptedData.encryptedPrivateKey_ = this.encryptedPrivateKey_;
                encryptedData.bitField0_ = i2;
                onBuilt();
                return encryptedData;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof EncryptedData) {
                    return mergeFrom((EncryptedData) message);
                }
                EncryptedData.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(EncryptedData encryptedData) {
                if (encryptedData == EncryptedData.getDefaultInstance()) {
                    return this;
                }
                if (encryptedData.hasInitialisationVector()) {
                    setInitialisationVector(encryptedData.getInitialisationVector());
                }
                if (encryptedData.hasEncryptedPrivateKey()) {
                    setEncryptedPrivateKey(encryptedData.getEncryptedPrivateKey());
                }
                mergeUnknownFields(encryptedData.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (hasInitialisationVector() && hasEncryptedPrivateKey()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                EncryptedData encryptedData;
                EncryptedData encryptedData2 = null;
                try {
                    EncryptedData encryptedData3 = (EncryptedData) EncryptedData.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (encryptedData3 != null) {
                        mergeFrom(encryptedData3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    encryptedData = (EncryptedData) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    encryptedData2 = encryptedData;
                }
                if (encryptedData2 != null) {
                    mergeFrom(encryptedData2);
                }
                throw th;
            }

            public boolean hasInitialisationVector() {
                return (this.bitField0_ & 1) == 1;
            }

            public ByteString getInitialisationVector() {
                return this.initialisationVector_;
            }

            public Builder setInitialisationVector(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.initialisationVector_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearInitialisationVector() {
                this.bitField0_ &= -2;
                this.initialisationVector_ = EncryptedData.getDefaultInstance().getInitialisationVector();
                onChanged();
                return this;
            }

            public boolean hasEncryptedPrivateKey() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getEncryptedPrivateKey() {
                return this.encryptedPrivateKey_;
            }

            public Builder setEncryptedPrivateKey(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.encryptedPrivateKey_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearEncryptedPrivateKey() {
                this.bitField0_ &= -3;
                this.encryptedPrivateKey_ = EncryptedData.getDefaultInstance().getEncryptedPrivateKey();
                onChanged();
                return this;
            }
        }

        private EncryptedData(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private EncryptedData(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static EncryptedData getDefaultInstance() {
            return defaultInstance;
        }

        public EncryptedData getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$EncryptedData] */
        private EncryptedData(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.initialisationVector_ = codedInputStream.readBytes();
                        } else if (readTag == 18) {
                            this.bitField0_ |= 2;
                            this.encryptedPrivateKey_ = codedInputStream.readBytes();
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
            return Protos.internal_static_wallet_EncryptedData_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_wallet_EncryptedData_fieldAccessorTable.ensureFieldAccessorsInitialized(EncryptedData.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<EncryptedData> getParserForType() {
            return PARSER;
        }

        public boolean hasInitialisationVector() {
            return (this.bitField0_ & 1) == 1;
        }

        public ByteString getInitialisationVector() {
            return this.initialisationVector_;
        }

        public boolean hasEncryptedPrivateKey() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getEncryptedPrivateKey() {
            return this.encryptedPrivateKey_;
        }

        private void initFields() {
            this.initialisationVector_ = ByteString.EMPTY;
            this.encryptedPrivateKey_ = ByteString.EMPTY;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasInitialisationVector()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasEncryptedPrivateKey()) {
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
                codedOutputStream.writeBytes(1, this.initialisationVector_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.encryptedPrivateKey_);
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
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.initialisationVector_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(2, this.encryptedPrivateKey_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static EncryptedData parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (EncryptedData) PARSER.parseFrom(byteString);
        }

        public static EncryptedData parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (EncryptedData) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static EncryptedData parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (EncryptedData) PARSER.parseFrom(bArr);
        }

        public static EncryptedData parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (EncryptedData) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static EncryptedData parseFrom(InputStream inputStream) throws IOException {
            return (EncryptedData) PARSER.parseFrom(inputStream);
        }

        public static EncryptedData parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (EncryptedData) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static EncryptedData parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (EncryptedData) PARSER.parseDelimitedFrom(inputStream);
        }

        public static EncryptedData parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (EncryptedData) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static EncryptedData parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (EncryptedData) PARSER.parseFrom(codedInputStream);
        }

        public static EncryptedData parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (EncryptedData) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(EncryptedData encryptedData) {
            return newBuilder().mergeFrom(encryptedData);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface EncryptedDataOrBuilder extends MessageOrBuilder {
        ByteString getEncryptedPrivateKey();

        ByteString getInitialisationVector();

        boolean hasEncryptedPrivateKey();

        boolean hasInitialisationVector();
    }

    public static final class ExchangeRate extends GeneratedMessage implements ExchangeRateOrBuilder {
        public static final int COIN_VALUE_FIELD_NUMBER = 1;
        public static final int FIAT_CURRENCY_CODE_FIELD_NUMBER = 3;
        public static final int FIAT_VALUE_FIELD_NUMBER = 2;
        public static Parser<ExchangeRate> PARSER = new AbstractParser<ExchangeRate>() {
            public ExchangeRate parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ExchangeRate(codedInputStream, extensionRegistryLite);
            }
        };
        private static final ExchangeRate defaultInstance = new ExchangeRate(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public long coinValue_;
        /* access modifiers changed from: private */
        public Object fiatCurrencyCode_;
        /* access modifiers changed from: private */
        public long fiatValue_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements ExchangeRateOrBuilder {
            private int bitField0_;
            private long coinValue_;
            private Object fiatCurrencyCode_;
            private long fiatValue_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_wallet_ExchangeRate_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_wallet_ExchangeRate_fieldAccessorTable.ensureFieldAccessorsInitialized(ExchangeRate.class, Builder.class);
            }

            private Builder() {
                this.fiatCurrencyCode_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.fiatCurrencyCode_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                ExchangeRate.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                ExchangeRate.super.clear();
                this.coinValue_ = 0;
                this.bitField0_ &= -2;
                this.fiatValue_ = 0;
                this.bitField0_ &= -3;
                this.fiatCurrencyCode_ = "";
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_wallet_ExchangeRate_descriptor;
            }

            public ExchangeRate getDefaultInstanceForType() {
                return ExchangeRate.getDefaultInstance();
            }

            public ExchangeRate build() {
                ExchangeRate buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public ExchangeRate buildPartial() {
                ExchangeRate exchangeRate = new ExchangeRate((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                exchangeRate.coinValue_ = this.coinValue_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                exchangeRate.fiatValue_ = this.fiatValue_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                exchangeRate.fiatCurrencyCode_ = this.fiatCurrencyCode_;
                exchangeRate.bitField0_ = i2;
                onBuilt();
                return exchangeRate;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof ExchangeRate) {
                    return mergeFrom((ExchangeRate) message);
                }
                ExchangeRate.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ExchangeRate exchangeRate) {
                if (exchangeRate == ExchangeRate.getDefaultInstance()) {
                    return this;
                }
                if (exchangeRate.hasCoinValue()) {
                    setCoinValue(exchangeRate.getCoinValue());
                }
                if (exchangeRate.hasFiatValue()) {
                    setFiatValue(exchangeRate.getFiatValue());
                }
                if (exchangeRate.hasFiatCurrencyCode()) {
                    this.bitField0_ |= 4;
                    this.fiatCurrencyCode_ = exchangeRate.fiatCurrencyCode_;
                    onChanged();
                }
                mergeUnknownFields(exchangeRate.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (hasCoinValue() && hasFiatValue() && hasFiatCurrencyCode()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ExchangeRate exchangeRate;
                ExchangeRate exchangeRate2 = null;
                try {
                    ExchangeRate exchangeRate3 = (ExchangeRate) ExchangeRate.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (exchangeRate3 != null) {
                        mergeFrom(exchangeRate3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    exchangeRate = (ExchangeRate) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    exchangeRate2 = exchangeRate;
                }
                if (exchangeRate2 != null) {
                    mergeFrom(exchangeRate2);
                }
                throw th;
            }

            public boolean hasCoinValue() {
                return (this.bitField0_ & 1) == 1;
            }

            public long getCoinValue() {
                return this.coinValue_;
            }

            public Builder setCoinValue(long j) {
                this.bitField0_ |= 1;
                this.coinValue_ = j;
                onChanged();
                return this;
            }

            public Builder clearCoinValue() {
                this.bitField0_ &= -2;
                this.coinValue_ = 0;
                onChanged();
                return this;
            }

            public boolean hasFiatValue() {
                return (this.bitField0_ & 2) == 2;
            }

            public long getFiatValue() {
                return this.fiatValue_;
            }

            public Builder setFiatValue(long j) {
                this.bitField0_ |= 2;
                this.fiatValue_ = j;
                onChanged();
                return this;
            }

            public Builder clearFiatValue() {
                this.bitField0_ &= -3;
                this.fiatValue_ = 0;
                onChanged();
                return this;
            }

            public boolean hasFiatCurrencyCode() {
                return (this.bitField0_ & 4) == 4;
            }

            public String getFiatCurrencyCode() {
                Object obj = this.fiatCurrencyCode_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.fiatCurrencyCode_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getFiatCurrencyCodeBytes() {
                Object obj = this.fiatCurrencyCode_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.fiatCurrencyCode_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setFiatCurrencyCode(String str) {
                if (str != null) {
                    this.bitField0_ |= 4;
                    this.fiatCurrencyCode_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearFiatCurrencyCode() {
                this.bitField0_ &= -5;
                this.fiatCurrencyCode_ = ExchangeRate.getDefaultInstance().getFiatCurrencyCode();
                onChanged();
                return this;
            }

            public Builder setFiatCurrencyCodeBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 4;
                    this.fiatCurrencyCode_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }
        }

        private ExchangeRate(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private ExchangeRate(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ExchangeRate getDefaultInstance() {
            return defaultInstance;
        }

        public ExchangeRate getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$ExchangeRate] */
        private ExchangeRate(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 8) {
                            this.bitField0_ |= 1;
                            this.coinValue_ = codedInputStream.readInt64();
                        } else if (readTag == 16) {
                            this.bitField0_ |= 2;
                            this.fiatValue_ = codedInputStream.readInt64();
                        } else if (readTag == 26) {
                            ByteString readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 4;
                            this.fiatCurrencyCode_ = readBytes;
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
            return Protos.internal_static_wallet_ExchangeRate_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_wallet_ExchangeRate_fieldAccessorTable.ensureFieldAccessorsInitialized(ExchangeRate.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<ExchangeRate> getParserForType() {
            return PARSER;
        }

        public boolean hasCoinValue() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getCoinValue() {
            return this.coinValue_;
        }

        public boolean hasFiatValue() {
            return (this.bitField0_ & 2) == 2;
        }

        public long getFiatValue() {
            return this.fiatValue_;
        }

        public boolean hasFiatCurrencyCode() {
            return (this.bitField0_ & 4) == 4;
        }

        public String getFiatCurrencyCode() {
            Object obj = this.fiatCurrencyCode_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.fiatCurrencyCode_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getFiatCurrencyCodeBytes() {
            Object obj = this.fiatCurrencyCode_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.fiatCurrencyCode_ = copyFromUtf8;
            return copyFromUtf8;
        }

        private void initFields() {
            this.coinValue_ = 0;
            this.fiatValue_ = 0;
            this.fiatCurrencyCode_ = "";
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasCoinValue()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasFiatValue()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasFiatCurrencyCode()) {
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
                codedOutputStream.writeInt64(1, this.coinValue_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt64(2, this.fiatValue_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBytes(3, getFiatCurrencyCodeBytes());
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
                i2 = 0 + CodedOutputStream.computeInt64Size(1, this.coinValue_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeInt64Size(2, this.fiatValue_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeBytesSize(3, getFiatCurrencyCodeBytes());
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static ExchangeRate parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ExchangeRate) PARSER.parseFrom(byteString);
        }

        public static ExchangeRate parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ExchangeRate) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ExchangeRate parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ExchangeRate) PARSER.parseFrom(bArr);
        }

        public static ExchangeRate parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ExchangeRate) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ExchangeRate parseFrom(InputStream inputStream) throws IOException {
            return (ExchangeRate) PARSER.parseFrom(inputStream);
        }

        public static ExchangeRate parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExchangeRate) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static ExchangeRate parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ExchangeRate) PARSER.parseDelimitedFrom(inputStream);
        }

        public static ExchangeRate parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExchangeRate) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static ExchangeRate parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ExchangeRate) PARSER.parseFrom(codedInputStream);
        }

        public static ExchangeRate parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExchangeRate) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ExchangeRate exchangeRate) {
            return newBuilder().mergeFrom(exchangeRate);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface ExchangeRateOrBuilder extends MessageOrBuilder {
        long getCoinValue();

        String getFiatCurrencyCode();

        ByteString getFiatCurrencyCodeBytes();

        long getFiatValue();

        boolean hasCoinValue();

        boolean hasFiatCurrencyCode();

        boolean hasFiatValue();
    }

    public static final class Extension extends GeneratedMessage implements ExtensionOrBuilder {
        public static final int DATA_FIELD_NUMBER = 2;
        public static final int ID_FIELD_NUMBER = 1;
        public static final int MANDATORY_FIELD_NUMBER = 3;
        public static Parser<Extension> PARSER = new AbstractParser<Extension>() {
            public Extension parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Extension(codedInputStream, extensionRegistryLite);
            }
        };
        private static final Extension defaultInstance = new Extension(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public ByteString data_;
        /* access modifiers changed from: private */
        public Object id_;
        /* access modifiers changed from: private */
        public boolean mandatory_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements ExtensionOrBuilder {
            private int bitField0_;
            private ByteString data_;
            private Object id_;
            private boolean mandatory_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_wallet_Extension_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_wallet_Extension_fieldAccessorTable.ensureFieldAccessorsInitialized(Extension.class, Builder.class);
            }

            private Builder() {
                this.id_ = "";
                this.data_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.id_ = "";
                this.data_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                Extension.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                Extension.super.clear();
                this.id_ = "";
                this.bitField0_ &= -2;
                this.data_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                this.mandatory_ = false;
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_wallet_Extension_descriptor;
            }

            public Extension getDefaultInstanceForType() {
                return Extension.getDefaultInstance();
            }

            public Extension build() {
                Extension buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Extension buildPartial() {
                Extension extension = new Extension((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                extension.id_ = this.id_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                extension.data_ = this.data_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                extension.mandatory_ = this.mandatory_;
                extension.bitField0_ = i2;
                onBuilt();
                return extension;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof Extension) {
                    return mergeFrom((Extension) message);
                }
                Extension.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Extension extension) {
                if (extension == Extension.getDefaultInstance()) {
                    return this;
                }
                if (extension.hasId()) {
                    this.bitField0_ |= 1;
                    this.id_ = extension.id_;
                    onChanged();
                }
                if (extension.hasData()) {
                    setData(extension.getData());
                }
                if (extension.hasMandatory()) {
                    setMandatory(extension.getMandatory());
                }
                mergeUnknownFields(extension.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (hasId() && hasData() && hasMandatory()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Extension extension;
                Extension extension2 = null;
                try {
                    Extension extension3 = (Extension) Extension.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (extension3 != null) {
                        mergeFrom(extension3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    extension = (Extension) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    extension2 = extension;
                }
                if (extension2 != null) {
                    mergeFrom(extension2);
                }
                throw th;
            }

            public boolean hasId() {
                return (this.bitField0_ & 1) == 1;
            }

            public String getId() {
                Object obj = this.id_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.id_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getIdBytes() {
                Object obj = this.id_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.id_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setId(String str) {
                if (str != null) {
                    this.bitField0_ |= 1;
                    this.id_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearId() {
                this.bitField0_ &= -2;
                this.id_ = Extension.getDefaultInstance().getId();
                onChanged();
                return this;
            }

            public Builder setIdBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.id_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public boolean hasData() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getData() {
                return this.data_;
            }

            public Builder setData(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.data_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearData() {
                this.bitField0_ &= -3;
                this.data_ = Extension.getDefaultInstance().getData();
                onChanged();
                return this;
            }

            public boolean hasMandatory() {
                return (this.bitField0_ & 4) == 4;
            }

            public boolean getMandatory() {
                return this.mandatory_;
            }

            public Builder setMandatory(boolean z) {
                this.bitField0_ |= 4;
                this.mandatory_ = z;
                onChanged();
                return this;
            }

            public Builder clearMandatory() {
                this.bitField0_ &= -5;
                this.mandatory_ = false;
                onChanged();
                return this;
            }
        }

        private Extension(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Extension(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Extension getDefaultInstance() {
            return defaultInstance;
        }

        public Extension getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$Extension] */
        private Extension(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.id_ = readBytes;
                        } else if (readTag == 18) {
                            this.bitField0_ |= 2;
                            this.data_ = codedInputStream.readBytes();
                        } else if (readTag == 24) {
                            this.bitField0_ |= 4;
                            this.mandatory_ = codedInputStream.readBool();
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
            return Protos.internal_static_wallet_Extension_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_wallet_Extension_fieldAccessorTable.ensureFieldAccessorsInitialized(Extension.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<Extension> getParserForType() {
            return PARSER;
        }

        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        public String getId() {
            Object obj = this.id_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.id_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getIdBytes() {
            Object obj = this.id_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.id_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasData() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getData() {
            return this.data_;
        }

        public boolean hasMandatory() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean getMandatory() {
            return this.mandatory_;
        }

        private void initFields() {
            this.id_ = "";
            this.data_ = ByteString.EMPTY;
            this.mandatory_ = false;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasId()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasData()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasMandatory()) {
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
                codedOutputStream.writeBytes(1, getIdBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.data_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBool(3, this.mandatory_);
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
                i2 = 0 + CodedOutputStream.computeBytesSize(1, getIdBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(2, this.data_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeBoolSize(3, this.mandatory_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static Extension parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Extension) PARSER.parseFrom(byteString);
        }

        public static Extension parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Extension) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Extension parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Extension) PARSER.parseFrom(bArr);
        }

        public static Extension parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Extension) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Extension parseFrom(InputStream inputStream) throws IOException {
            return (Extension) PARSER.parseFrom(inputStream);
        }

        public static Extension parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Extension) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static Extension parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Extension) PARSER.parseDelimitedFrom(inputStream);
        }

        public static Extension parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Extension) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static Extension parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Extension) PARSER.parseFrom(codedInputStream);
        }

        public static Extension parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Extension) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Extension extension) {
            return newBuilder().mergeFrom(extension);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface ExtensionOrBuilder extends MessageOrBuilder {
        ByteString getData();

        String getId();

        ByteString getIdBytes();

        boolean getMandatory();

        boolean hasData();

        boolean hasId();

        boolean hasMandatory();
    }

    /* renamed from: org.bitcoinj.wallet.Protos$Key */
    public static final class C3509Key extends GeneratedMessage implements KeyOrBuilder {
        public static final int CREATION_TIMESTAMP_FIELD_NUMBER = 5;
        public static final int DETERMINISTIC_KEY_FIELD_NUMBER = 7;
        public static final int DETERMINISTIC_SEED_FIELD_NUMBER = 8;
        public static final int ENCRYPTED_DATA_FIELD_NUMBER = 6;
        public static final int ENCRYPTED_DETERMINISTIC_SEED_FIELD_NUMBER = 9;
        public static final int LABEL_FIELD_NUMBER = 4;
        public static Parser<C3509Key> PARSER = new AbstractParser<C3509Key>() {
            public C3509Key parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new C3509Key(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PUBLIC_KEY_FIELD_NUMBER = 3;
        public static final int SECRET_BYTES_FIELD_NUMBER = 2;
        public static final int TYPE_FIELD_NUMBER = 1;
        private static final C3509Key defaultInstance = new C3509Key(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public long creationTimestamp_;
        /* access modifiers changed from: private */
        public DeterministicKey deterministicKey_;
        /* access modifiers changed from: private */
        public ByteString deterministicSeed_;
        /* access modifiers changed from: private */
        public EncryptedData encryptedData_;
        /* access modifiers changed from: private */
        public EncryptedData encryptedDeterministicSeed_;
        /* access modifiers changed from: private */
        public Object label_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString publicKey_;
        /* access modifiers changed from: private */
        public ByteString secretBytes_;
        /* access modifiers changed from: private */
        public C3511Type type_;
        private final UnknownFieldSet unknownFields;

        /* renamed from: org.bitcoinj.wallet.Protos$Key$Builder */
        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements KeyOrBuilder {
            private int bitField0_;
            private long creationTimestamp_;
            private SingleFieldBuilder<DeterministicKey, Builder, DeterministicKeyOrBuilder> deterministicKeyBuilder_;
            private DeterministicKey deterministicKey_;
            private ByteString deterministicSeed_;
            private SingleFieldBuilder<EncryptedData, Builder, EncryptedDataOrBuilder> encryptedDataBuilder_;
            private EncryptedData encryptedData_;
            private SingleFieldBuilder<EncryptedData, Builder, EncryptedDataOrBuilder> encryptedDeterministicSeedBuilder_;
            private EncryptedData encryptedDeterministicSeed_;
            private Object label_;
            private ByteString publicKey_;
            private ByteString secretBytes_;
            private C3511Type type_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_wallet_Key_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_wallet_Key_fieldAccessorTable.ensureFieldAccessorsInitialized(C3509Key.class, Builder.class);
            }

            private Builder() {
                this.type_ = C3511Type.ORIGINAL;
                this.secretBytes_ = ByteString.EMPTY;
                this.encryptedData_ = EncryptedData.getDefaultInstance();
                this.publicKey_ = ByteString.EMPTY;
                this.label_ = "";
                this.deterministicKey_ = DeterministicKey.getDefaultInstance();
                this.deterministicSeed_ = ByteString.EMPTY;
                this.encryptedDeterministicSeed_ = EncryptedData.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.type_ = C3511Type.ORIGINAL;
                this.secretBytes_ = ByteString.EMPTY;
                this.encryptedData_ = EncryptedData.getDefaultInstance();
                this.publicKey_ = ByteString.EMPTY;
                this.label_ = "";
                this.deterministicKey_ = DeterministicKey.getDefaultInstance();
                this.deterministicSeed_ = ByteString.EMPTY;
                this.encryptedDeterministicSeed_ = EncryptedData.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (C3509Key.alwaysUseFieldBuilders) {
                    getEncryptedDataFieldBuilder();
                    getDeterministicKeyFieldBuilder();
                    getEncryptedDeterministicSeedFieldBuilder();
                }
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                C3509Key.super.clear();
                this.type_ = C3511Type.ORIGINAL;
                this.bitField0_ &= -2;
                this.secretBytes_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                SingleFieldBuilder<EncryptedData, Builder, EncryptedDataOrBuilder> singleFieldBuilder = this.encryptedDataBuilder_;
                if (singleFieldBuilder == null) {
                    this.encryptedData_ = EncryptedData.getDefaultInstance();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -5;
                this.publicKey_ = ByteString.EMPTY;
                this.bitField0_ &= -9;
                this.label_ = "";
                this.bitField0_ &= -17;
                this.creationTimestamp_ = 0;
                this.bitField0_ &= -33;
                SingleFieldBuilder<DeterministicKey, Builder, DeterministicKeyOrBuilder> singleFieldBuilder2 = this.deterministicKeyBuilder_;
                if (singleFieldBuilder2 == null) {
                    this.deterministicKey_ = DeterministicKey.getDefaultInstance();
                } else {
                    singleFieldBuilder2.clear();
                }
                this.bitField0_ &= -65;
                this.deterministicSeed_ = ByteString.EMPTY;
                this.bitField0_ &= -129;
                SingleFieldBuilder<EncryptedData, Builder, EncryptedDataOrBuilder> singleFieldBuilder3 = this.encryptedDeterministicSeedBuilder_;
                if (singleFieldBuilder3 == null) {
                    this.encryptedDeterministicSeed_ = EncryptedData.getDefaultInstance();
                } else {
                    singleFieldBuilder3.clear();
                }
                this.bitField0_ &= -257;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_wallet_Key_descriptor;
            }

            public C3509Key getDefaultInstanceForType() {
                return C3509Key.getDefaultInstance();
            }

            public C3509Key build() {
                C3509Key buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public C3509Key buildPartial() {
                C3509Key key = new C3509Key((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                key.type_ = this.type_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                key.secretBytes_ = this.secretBytes_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                SingleFieldBuilder<EncryptedData, Builder, EncryptedDataOrBuilder> singleFieldBuilder = this.encryptedDataBuilder_;
                if (singleFieldBuilder == null) {
                    key.encryptedData_ = this.encryptedData_;
                } else {
                    key.encryptedData_ = singleFieldBuilder.build();
                }
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                key.publicKey_ = this.publicKey_;
                if ((i & 16) == 16) {
                    i2 |= 16;
                }
                key.label_ = this.label_;
                if ((i & 32) == 32) {
                    i2 |= 32;
                }
                key.creationTimestamp_ = this.creationTimestamp_;
                if ((i & 64) == 64) {
                    i2 |= 64;
                }
                SingleFieldBuilder<DeterministicKey, Builder, DeterministicKeyOrBuilder> singleFieldBuilder2 = this.deterministicKeyBuilder_;
                if (singleFieldBuilder2 == null) {
                    key.deterministicKey_ = this.deterministicKey_;
                } else {
                    key.deterministicKey_ = singleFieldBuilder2.build();
                }
                if ((i & 128) == 128) {
                    i2 |= 128;
                }
                key.deterministicSeed_ = this.deterministicSeed_;
                if ((i & 256) == 256) {
                    i2 |= 256;
                }
                SingleFieldBuilder<EncryptedData, Builder, EncryptedDataOrBuilder> singleFieldBuilder3 = this.encryptedDeterministicSeedBuilder_;
                if (singleFieldBuilder3 == null) {
                    key.encryptedDeterministicSeed_ = this.encryptedDeterministicSeed_;
                } else {
                    key.encryptedDeterministicSeed_ = singleFieldBuilder3.build();
                }
                key.bitField0_ = i2;
                onBuilt();
                return key;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof C3509Key) {
                    return mergeFrom((C3509Key) message);
                }
                C3509Key.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(C3509Key key) {
                if (key == C3509Key.getDefaultInstance()) {
                    return this;
                }
                if (key.hasType()) {
                    setType(key.getType());
                }
                if (key.hasSecretBytes()) {
                    setSecretBytes(key.getSecretBytes());
                }
                if (key.hasEncryptedData()) {
                    mergeEncryptedData(key.getEncryptedData());
                }
                if (key.hasPublicKey()) {
                    setPublicKey(key.getPublicKey());
                }
                if (key.hasLabel()) {
                    this.bitField0_ |= 16;
                    this.label_ = key.label_;
                    onChanged();
                }
                if (key.hasCreationTimestamp()) {
                    setCreationTimestamp(key.getCreationTimestamp());
                }
                if (key.hasDeterministicKey()) {
                    mergeDeterministicKey(key.getDeterministicKey());
                }
                if (key.hasDeterministicSeed()) {
                    setDeterministicSeed(key.getDeterministicSeed());
                }
                if (key.hasEncryptedDeterministicSeed()) {
                    mergeEncryptedDeterministicSeed(key.getEncryptedDeterministicSeed());
                }
                mergeUnknownFields(key.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (!hasType()) {
                    return false;
                }
                if (hasEncryptedData() && !getEncryptedData().isInitialized()) {
                    return false;
                }
                if (hasDeterministicKey() && !getDeterministicKey().isInitialized()) {
                    return false;
                }
                if (!hasEncryptedDeterministicSeed() || getEncryptedDeterministicSeed().isInitialized()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                C3509Key key;
                C3509Key key2 = null;
                try {
                    C3509Key key3 = (C3509Key) C3509Key.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (key3 != null) {
                        mergeFrom(key3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    key = (C3509Key) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    key2 = key;
                }
                if (key2 != null) {
                    mergeFrom(key2);
                }
                throw th;
            }

            public boolean hasType() {
                return (this.bitField0_ & 1) == 1;
            }

            public C3511Type getType() {
                return this.type_;
            }

            public Builder setType(C3511Type type) {
                if (type != null) {
                    this.bitField0_ |= 1;
                    this.type_ = type;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearType() {
                this.bitField0_ &= -2;
                this.type_ = C3511Type.ORIGINAL;
                onChanged();
                return this;
            }

            public boolean hasSecretBytes() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getSecretBytes() {
                return this.secretBytes_;
            }

            public Builder setSecretBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.secretBytes_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearSecretBytes() {
                this.bitField0_ &= -3;
                this.secretBytes_ = C3509Key.getDefaultInstance().getSecretBytes();
                onChanged();
                return this;
            }

            public boolean hasEncryptedData() {
                return (this.bitField0_ & 4) == 4;
            }

            public EncryptedData getEncryptedData() {
                SingleFieldBuilder<EncryptedData, Builder, EncryptedDataOrBuilder> singleFieldBuilder = this.encryptedDataBuilder_;
                if (singleFieldBuilder == null) {
                    return this.encryptedData_;
                }
                return singleFieldBuilder.getMessage();
            }

            public Builder setEncryptedData(EncryptedData encryptedData) {
                SingleFieldBuilder<EncryptedData, Builder, EncryptedDataOrBuilder> singleFieldBuilder = this.encryptedDataBuilder_;
                if (singleFieldBuilder != null) {
                    singleFieldBuilder.setMessage(encryptedData);
                } else if (encryptedData != null) {
                    this.encryptedData_ = encryptedData;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setEncryptedData(Builder builder) {
                SingleFieldBuilder<EncryptedData, Builder, EncryptedDataOrBuilder> singleFieldBuilder = this.encryptedDataBuilder_;
                if (singleFieldBuilder == null) {
                    this.encryptedData_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builder.build());
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder mergeEncryptedData(EncryptedData encryptedData) {
                SingleFieldBuilder<EncryptedData, Builder, EncryptedDataOrBuilder> singleFieldBuilder = this.encryptedDataBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 4) != 4 || this.encryptedData_ == EncryptedData.getDefaultInstance()) {
                        this.encryptedData_ = encryptedData;
                    } else {
                        this.encryptedData_ = EncryptedData.newBuilder(this.encryptedData_).mergeFrom(encryptedData).buildPartial();
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(encryptedData);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder clearEncryptedData() {
                SingleFieldBuilder<EncryptedData, Builder, EncryptedDataOrBuilder> singleFieldBuilder = this.encryptedDataBuilder_;
                if (singleFieldBuilder == null) {
                    this.encryptedData_ = EncryptedData.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -5;
                return this;
            }

            public Builder getEncryptedDataBuilder() {
                this.bitField0_ |= 4;
                onChanged();
                return getEncryptedDataFieldBuilder().getBuilder();
            }

            public EncryptedDataOrBuilder getEncryptedDataOrBuilder() {
                SingleFieldBuilder<EncryptedData, Builder, EncryptedDataOrBuilder> singleFieldBuilder = this.encryptedDataBuilder_;
                if (singleFieldBuilder != null) {
                    return singleFieldBuilder.getMessageOrBuilder();
                }
                return this.encryptedData_;
            }

            private SingleFieldBuilder<EncryptedData, Builder, EncryptedDataOrBuilder> getEncryptedDataFieldBuilder() {
                if (this.encryptedDataBuilder_ == null) {
                    this.encryptedDataBuilder_ = new SingleFieldBuilder<>(getEncryptedData(), getParentForChildren(), isClean());
                    this.encryptedData_ = null;
                }
                return this.encryptedDataBuilder_;
            }

            public boolean hasPublicKey() {
                return (this.bitField0_ & 8) == 8;
            }

            public ByteString getPublicKey() {
                return this.publicKey_;
            }

            public Builder setPublicKey(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 8;
                    this.publicKey_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearPublicKey() {
                this.bitField0_ &= -9;
                this.publicKey_ = C3509Key.getDefaultInstance().getPublicKey();
                onChanged();
                return this;
            }

            public boolean hasLabel() {
                return (this.bitField0_ & 16) == 16;
            }

            public String getLabel() {
                Object obj = this.label_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.label_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getLabelBytes() {
                Object obj = this.label_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.label_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setLabel(String str) {
                if (str != null) {
                    this.bitField0_ |= 16;
                    this.label_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearLabel() {
                this.bitField0_ &= -17;
                this.label_ = C3509Key.getDefaultInstance().getLabel();
                onChanged();
                return this;
            }

            public Builder setLabelBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 16;
                    this.label_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public boolean hasCreationTimestamp() {
                return (this.bitField0_ & 32) == 32;
            }

            public long getCreationTimestamp() {
                return this.creationTimestamp_;
            }

            public Builder setCreationTimestamp(long j) {
                this.bitField0_ |= 32;
                this.creationTimestamp_ = j;
                onChanged();
                return this;
            }

            public Builder clearCreationTimestamp() {
                this.bitField0_ &= -33;
                this.creationTimestamp_ = 0;
                onChanged();
                return this;
            }

            public boolean hasDeterministicKey() {
                return (this.bitField0_ & 64) == 64;
            }

            public DeterministicKey getDeterministicKey() {
                SingleFieldBuilder<DeterministicKey, Builder, DeterministicKeyOrBuilder> singleFieldBuilder = this.deterministicKeyBuilder_;
                if (singleFieldBuilder == null) {
                    return this.deterministicKey_;
                }
                return singleFieldBuilder.getMessage();
            }

            public Builder setDeterministicKey(DeterministicKey deterministicKey) {
                SingleFieldBuilder<DeterministicKey, Builder, DeterministicKeyOrBuilder> singleFieldBuilder = this.deterministicKeyBuilder_;
                if (singleFieldBuilder != null) {
                    singleFieldBuilder.setMessage(deterministicKey);
                } else if (deterministicKey != null) {
                    this.deterministicKey_ = deterministicKey;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 64;
                return this;
            }

            public Builder setDeterministicKey(Builder builder) {
                SingleFieldBuilder<DeterministicKey, Builder, DeterministicKeyOrBuilder> singleFieldBuilder = this.deterministicKeyBuilder_;
                if (singleFieldBuilder == null) {
                    this.deterministicKey_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builder.build());
                }
                this.bitField0_ |= 64;
                return this;
            }

            public Builder mergeDeterministicKey(DeterministicKey deterministicKey) {
                SingleFieldBuilder<DeterministicKey, Builder, DeterministicKeyOrBuilder> singleFieldBuilder = this.deterministicKeyBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 64) != 64 || this.deterministicKey_ == DeterministicKey.getDefaultInstance()) {
                        this.deterministicKey_ = deterministicKey;
                    } else {
                        this.deterministicKey_ = DeterministicKey.newBuilder(this.deterministicKey_).mergeFrom(deterministicKey).buildPartial();
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(deterministicKey);
                }
                this.bitField0_ |= 64;
                return this;
            }

            public Builder clearDeterministicKey() {
                SingleFieldBuilder<DeterministicKey, Builder, DeterministicKeyOrBuilder> singleFieldBuilder = this.deterministicKeyBuilder_;
                if (singleFieldBuilder == null) {
                    this.deterministicKey_ = DeterministicKey.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -65;
                return this;
            }

            public Builder getDeterministicKeyBuilder() {
                this.bitField0_ |= 64;
                onChanged();
                return getDeterministicKeyFieldBuilder().getBuilder();
            }

            public DeterministicKeyOrBuilder getDeterministicKeyOrBuilder() {
                SingleFieldBuilder<DeterministicKey, Builder, DeterministicKeyOrBuilder> singleFieldBuilder = this.deterministicKeyBuilder_;
                if (singleFieldBuilder != null) {
                    return singleFieldBuilder.getMessageOrBuilder();
                }
                return this.deterministicKey_;
            }

            private SingleFieldBuilder<DeterministicKey, Builder, DeterministicKeyOrBuilder> getDeterministicKeyFieldBuilder() {
                if (this.deterministicKeyBuilder_ == null) {
                    this.deterministicKeyBuilder_ = new SingleFieldBuilder<>(getDeterministicKey(), getParentForChildren(), isClean());
                    this.deterministicKey_ = null;
                }
                return this.deterministicKeyBuilder_;
            }

            public boolean hasDeterministicSeed() {
                return (this.bitField0_ & 128) == 128;
            }

            public ByteString getDeterministicSeed() {
                return this.deterministicSeed_;
            }

            public Builder setDeterministicSeed(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 128;
                    this.deterministicSeed_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearDeterministicSeed() {
                this.bitField0_ &= -129;
                this.deterministicSeed_ = C3509Key.getDefaultInstance().getDeterministicSeed();
                onChanged();
                return this;
            }

            public boolean hasEncryptedDeterministicSeed() {
                return (this.bitField0_ & 256) == 256;
            }

            public EncryptedData getEncryptedDeterministicSeed() {
                SingleFieldBuilder<EncryptedData, Builder, EncryptedDataOrBuilder> singleFieldBuilder = this.encryptedDeterministicSeedBuilder_;
                if (singleFieldBuilder == null) {
                    return this.encryptedDeterministicSeed_;
                }
                return singleFieldBuilder.getMessage();
            }

            public Builder setEncryptedDeterministicSeed(EncryptedData encryptedData) {
                SingleFieldBuilder<EncryptedData, Builder, EncryptedDataOrBuilder> singleFieldBuilder = this.encryptedDeterministicSeedBuilder_;
                if (singleFieldBuilder != null) {
                    singleFieldBuilder.setMessage(encryptedData);
                } else if (encryptedData != null) {
                    this.encryptedDeterministicSeed_ = encryptedData;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 256;
                return this;
            }

            public Builder setEncryptedDeterministicSeed(Builder builder) {
                SingleFieldBuilder<EncryptedData, Builder, EncryptedDataOrBuilder> singleFieldBuilder = this.encryptedDeterministicSeedBuilder_;
                if (singleFieldBuilder == null) {
                    this.encryptedDeterministicSeed_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builder.build());
                }
                this.bitField0_ |= 256;
                return this;
            }

            public Builder mergeEncryptedDeterministicSeed(EncryptedData encryptedData) {
                SingleFieldBuilder<EncryptedData, Builder, EncryptedDataOrBuilder> singleFieldBuilder = this.encryptedDeterministicSeedBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 256) != 256 || this.encryptedDeterministicSeed_ == EncryptedData.getDefaultInstance()) {
                        this.encryptedDeterministicSeed_ = encryptedData;
                    } else {
                        this.encryptedDeterministicSeed_ = EncryptedData.newBuilder(this.encryptedDeterministicSeed_).mergeFrom(encryptedData).buildPartial();
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(encryptedData);
                }
                this.bitField0_ |= 256;
                return this;
            }

            public Builder clearEncryptedDeterministicSeed() {
                SingleFieldBuilder<EncryptedData, Builder, EncryptedDataOrBuilder> singleFieldBuilder = this.encryptedDeterministicSeedBuilder_;
                if (singleFieldBuilder == null) {
                    this.encryptedDeterministicSeed_ = EncryptedData.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -257;
                return this;
            }

            public Builder getEncryptedDeterministicSeedBuilder() {
                this.bitField0_ |= 256;
                onChanged();
                return getEncryptedDeterministicSeedFieldBuilder().getBuilder();
            }

            public EncryptedDataOrBuilder getEncryptedDeterministicSeedOrBuilder() {
                SingleFieldBuilder<EncryptedData, Builder, EncryptedDataOrBuilder> singleFieldBuilder = this.encryptedDeterministicSeedBuilder_;
                if (singleFieldBuilder != null) {
                    return singleFieldBuilder.getMessageOrBuilder();
                }
                return this.encryptedDeterministicSeed_;
            }

            private SingleFieldBuilder<EncryptedData, Builder, EncryptedDataOrBuilder> getEncryptedDeterministicSeedFieldBuilder() {
                if (this.encryptedDeterministicSeedBuilder_ == null) {
                    this.encryptedDeterministicSeedBuilder_ = new SingleFieldBuilder<>(getEncryptedDeterministicSeed(), getParentForChildren(), isClean());
                    this.encryptedDeterministicSeed_ = null;
                }
                return this.encryptedDeterministicSeedBuilder_;
            }
        }

        /* renamed from: org.bitcoinj.wallet.Protos$Key$Type */
        public enum C3511Type implements ProtocolMessageEnum {
            ORIGINAL(0, 1),
            ENCRYPTED_SCRYPT_AES(1, 2),
            DETERMINISTIC_MNEMONIC(2, 3),
            DETERMINISTIC_KEY(3, 4);
            
            public static final int DETERMINISTIC_KEY_VALUE = 4;
            public static final int DETERMINISTIC_MNEMONIC_VALUE = 3;
            public static final int ENCRYPTED_SCRYPT_AES_VALUE = 2;
            public static final int ORIGINAL_VALUE = 1;
            private static final C3511Type[] VALUES = null;
            private static EnumLiteMap<C3511Type> internalValueMap;
            private final int index;
            private final int value;

            static {
                internalValueMap = new EnumLiteMap<C3511Type>() {
                    public C3511Type findValueByNumber(int i) {
                        return C3511Type.valueOf(i);
                    }
                };
                VALUES = values();
            }

            public final int getNumber() {
                return this.value;
            }

            public static C3511Type valueOf(int i) {
                if (i == 1) {
                    return ORIGINAL;
                }
                if (i == 2) {
                    return ENCRYPTED_SCRYPT_AES;
                }
                if (i == 3) {
                    return DETERMINISTIC_MNEMONIC;
                }
                if (i != 4) {
                    return null;
                }
                return DETERMINISTIC_KEY;
            }

            public static EnumLiteMap<C3511Type> internalGetValueMap() {
                return internalValueMap;
            }

            public final EnumValueDescriptor getValueDescriptor() {
                return (EnumValueDescriptor) getDescriptor().getValues().get(this.index);
            }

            public final EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final EnumDescriptor getDescriptor() {
                return (EnumDescriptor) C3509Key.getDescriptor().getEnumTypes().get(0);
            }

            public static C3511Type valueOf(EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            private C3511Type(int i, int i2) {
                this.index = i;
                this.value = i2;
            }
        }

        private C3509Key(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private C3509Key(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static C3509Key getDefaultInstance() {
            return defaultInstance;
        }

        public C3509Key getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r6v0, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$Key] */
        /* JADX WARNING: type inference failed for: r5v2 */
        /* JADX WARNING: type inference failed for: r5v3, types: [org.bitcoinj.wallet.Protos$EncryptedData$Builder] */
        /* JADX WARNING: type inference failed for: r5v4, types: [org.bitcoinj.wallet.Protos$EncryptedData$Builder] */
        /* JADX WARNING: type inference failed for: r5v5, types: [org.bitcoinj.wallet.Protos$DeterministicKey$Builder] */
        /* JADX WARNING: type inference failed for: r5v6, types: [org.bitcoinj.wallet.Protos$DeterministicKey$Builder] */
        /* JADX WARNING: type inference failed for: r5v7, types: [org.bitcoinj.wallet.Protos$EncryptedData$Builder] */
        /* JADX WARNING: type inference failed for: r5v8, types: [org.bitcoinj.wallet.Protos$EncryptedData$Builder] */
        /* JADX WARNING: type inference failed for: r5v9 */
        /* JADX WARNING: type inference failed for: r5v10 */
        /* JADX WARNING: type inference failed for: r5v11 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r5v2
          assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], org.bitcoinj.wallet.Protos$DeterministicKey$Builder, org.bitcoinj.wallet.Protos$EncryptedData$Builder]
          uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], org.bitcoinj.wallet.Protos$EncryptedData$Builder, org.bitcoinj.wallet.Protos$DeterministicKey$Builder]
          mth insns count: 135
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 4 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private C3509Key(com.google.protobuf.CodedInputStream r7, com.google.protobuf.ExtensionRegistryLite r8) throws com.google.protobuf.InvalidProtocolBufferException {
            /*
                r6 = this;
                r6.<init>()
                r0 = -1
                r6.memoizedIsInitialized = r0
                r6.memoizedSerializedSize = r0
                r6.initFields()
                com.google.protobuf.UnknownFieldSet$Builder r0 = com.google.protobuf.UnknownFieldSet.newBuilder()
                r1 = 0
            L_0x0010:
                if (r1 != 0) goto L_0x0145
                int r2 = r7.readTag()     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r3 = 1
                if (r2 == 0) goto L_0x0121
                r4 = 8
                if (r2 == r4) goto L_0x0109
                r5 = 18
                if (r2 == r5) goto L_0x00fb
                r5 = 26
                if (r2 == r5) goto L_0x00ee
                r4 = 34
                if (r2 == r4) goto L_0x00e0
                r4 = 40
                if (r2 == r4) goto L_0x00d2
                r4 = 50
                r5 = 0
                if (r2 == r4) goto L_0x00a8
                r4 = 58
                if (r2 == r4) goto L_0x007d
                r4 = 66
                if (r2 == r4) goto L_0x0070
                r4 = 74
                if (r2 == r4) goto L_0x0046
                boolean r2 = r6.parseUnknownField(r7, r0, r8, r2)     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                if (r2 != 0) goto L_0x0010
                goto L_0x0121
            L_0x0046:
                int r2 = r6.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r3 = 256(0x100, float:3.59E-43)
                r2 = r2 & r3
                if (r2 != r3) goto L_0x0053
                org.bitcoinj.wallet.Protos$EncryptedData r2 = r6.encryptedDeterministicSeed_     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                org.bitcoinj.wallet.Protos$EncryptedData$Builder r5 = r2.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
            L_0x0053:
                com.google.protobuf.Parser<org.bitcoinj.wallet.Protos$EncryptedData> r2 = org.bitcoinj.wallet.Protos.EncryptedData.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                com.google.protobuf.MessageLite r2 = r7.readMessage(r2, r8)     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                org.bitcoinj.wallet.Protos$EncryptedData r2 = (org.bitcoinj.wallet.Protos.EncryptedData) r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r6.encryptedDeterministicSeed_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                if (r5 == 0) goto L_0x006a
                org.bitcoinj.wallet.Protos$EncryptedData r2 = r6.encryptedDeterministicSeed_     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r5.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                org.bitcoinj.wallet.Protos$EncryptedData r2 = r5.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r6.encryptedDeterministicSeed_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
            L_0x006a:
                int r2 = r6.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r2 = r2 | r3
                r6.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                goto L_0x0010
            L_0x0070:
                int r2 = r6.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r2 = r2 | 128(0x80, float:1.794E-43)
                r6.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                com.google.protobuf.ByteString r2 = r7.readBytes()     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r6.deterministicSeed_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                goto L_0x0010
            L_0x007d:
                int r2 = r6.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r3 = 64
                r2 = r2 & r3
                if (r2 != r3) goto L_0x008a
                org.bitcoinj.wallet.Protos$DeterministicKey r2 = r6.deterministicKey_     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                org.bitcoinj.wallet.Protos$DeterministicKey$Builder r5 = r2.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
            L_0x008a:
                com.google.protobuf.Parser<org.bitcoinj.wallet.Protos$DeterministicKey> r2 = org.bitcoinj.wallet.Protos.DeterministicKey.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                com.google.protobuf.MessageLite r2 = r7.readMessage(r2, r8)     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                org.bitcoinj.wallet.Protos$DeterministicKey r2 = (org.bitcoinj.wallet.Protos.DeterministicKey) r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r6.deterministicKey_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                if (r5 == 0) goto L_0x00a1
                org.bitcoinj.wallet.Protos$DeterministicKey r2 = r6.deterministicKey_     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r5.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                org.bitcoinj.wallet.Protos$DeterministicKey r2 = r5.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r6.deterministicKey_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
            L_0x00a1:
                int r2 = r6.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r2 = r2 | r3
                r6.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                goto L_0x0010
            L_0x00a8:
                int r2 = r6.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r3 = 4
                r2 = r2 & r3
                if (r2 != r3) goto L_0x00b4
                org.bitcoinj.wallet.Protos$EncryptedData r2 = r6.encryptedData_     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                org.bitcoinj.wallet.Protos$EncryptedData$Builder r5 = r2.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
            L_0x00b4:
                com.google.protobuf.Parser<org.bitcoinj.wallet.Protos$EncryptedData> r2 = org.bitcoinj.wallet.Protos.EncryptedData.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                com.google.protobuf.MessageLite r2 = r7.readMessage(r2, r8)     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                org.bitcoinj.wallet.Protos$EncryptedData r2 = (org.bitcoinj.wallet.Protos.EncryptedData) r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r6.encryptedData_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                if (r5 == 0) goto L_0x00cb
                org.bitcoinj.wallet.Protos$EncryptedData r2 = r6.encryptedData_     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r5.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                org.bitcoinj.wallet.Protos$EncryptedData r2 = r5.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r6.encryptedData_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
            L_0x00cb:
                int r2 = r6.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r2 = r2 | r3
                r6.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                goto L_0x0010
            L_0x00d2:
                int r2 = r6.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r2 = r2 | 32
                r6.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                long r2 = r7.readInt64()     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r6.creationTimestamp_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                goto L_0x0010
            L_0x00e0:
                com.google.protobuf.ByteString r2 = r7.readBytes()     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                int r3 = r6.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r3 = r3 | 16
                r6.bitField0_ = r3     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r6.label_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                goto L_0x0010
            L_0x00ee:
                int r2 = r6.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r2 = r2 | r4
                r6.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                com.google.protobuf.ByteString r2 = r7.readBytes()     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r6.publicKey_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                goto L_0x0010
            L_0x00fb:
                int r2 = r6.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r2 = r2 | 2
                r6.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                com.google.protobuf.ByteString r2 = r7.readBytes()     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r6.secretBytes_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                goto L_0x0010
            L_0x0109:
                int r2 = r7.readEnum()     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                org.bitcoinj.wallet.Protos$Key$Type r4 = org.bitcoinj.wallet.Protos.C3509Key.C3511Type.valueOf(r2)     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                if (r4 != 0) goto L_0x0118
                r0.mergeVarintField(r3, r2)     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                goto L_0x0010
            L_0x0118:
                int r2 = r6.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r2 = r2 | r3
                r6.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                r6.type_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x0135, IOException -> 0x0126 }
                goto L_0x0010
            L_0x0121:
                r1 = 1
                goto L_0x0010
            L_0x0124:
                r7 = move-exception
                goto L_0x013b
            L_0x0126:
                r7 = move-exception
                com.google.protobuf.InvalidProtocolBufferException r8 = new com.google.protobuf.InvalidProtocolBufferException     // Catch:{ all -> 0x0124 }
                java.lang.String r7 = r7.getMessage()     // Catch:{ all -> 0x0124 }
                r8.<init>(r7)     // Catch:{ all -> 0x0124 }
                com.google.protobuf.InvalidProtocolBufferException r7 = r8.setUnfinishedMessage(r6)     // Catch:{ all -> 0x0124 }
                throw r7     // Catch:{ all -> 0x0124 }
            L_0x0135:
                r7 = move-exception
                com.google.protobuf.InvalidProtocolBufferException r7 = r7.setUnfinishedMessage(r6)     // Catch:{ all -> 0x0124 }
                throw r7     // Catch:{ all -> 0x0124 }
            L_0x013b:
                com.google.protobuf.UnknownFieldSet r8 = r0.build()
                r6.unknownFields = r8
                r6.makeExtensionsImmutable()
                throw r7
            L_0x0145:
                com.google.protobuf.UnknownFieldSet r7 = r0.build()
                r6.unknownFields = r7
                r6.makeExtensionsImmutable()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.wallet.Protos.C3509Key.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
        }

        public static final Descriptor getDescriptor() {
            return Protos.internal_static_wallet_Key_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_wallet_Key_fieldAccessorTable.ensureFieldAccessorsInitialized(C3509Key.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<C3509Key> getParserForType() {
            return PARSER;
        }

        public boolean hasType() {
            return (this.bitField0_ & 1) == 1;
        }

        public C3511Type getType() {
            return this.type_;
        }

        public boolean hasSecretBytes() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getSecretBytes() {
            return this.secretBytes_;
        }

        public boolean hasEncryptedData() {
            return (this.bitField0_ & 4) == 4;
        }

        public EncryptedData getEncryptedData() {
            return this.encryptedData_;
        }

        public EncryptedDataOrBuilder getEncryptedDataOrBuilder() {
            return this.encryptedData_;
        }

        public boolean hasPublicKey() {
            return (this.bitField0_ & 8) == 8;
        }

        public ByteString getPublicKey() {
            return this.publicKey_;
        }

        public boolean hasLabel() {
            return (this.bitField0_ & 16) == 16;
        }

        public String getLabel() {
            Object obj = this.label_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.label_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getLabelBytes() {
            Object obj = this.label_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.label_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasCreationTimestamp() {
            return (this.bitField0_ & 32) == 32;
        }

        public long getCreationTimestamp() {
            return this.creationTimestamp_;
        }

        public boolean hasDeterministicKey() {
            return (this.bitField0_ & 64) == 64;
        }

        public DeterministicKey getDeterministicKey() {
            return this.deterministicKey_;
        }

        public DeterministicKeyOrBuilder getDeterministicKeyOrBuilder() {
            return this.deterministicKey_;
        }

        public boolean hasDeterministicSeed() {
            return (this.bitField0_ & 128) == 128;
        }

        public ByteString getDeterministicSeed() {
            return this.deterministicSeed_;
        }

        public boolean hasEncryptedDeterministicSeed() {
            return (this.bitField0_ & 256) == 256;
        }

        public EncryptedData getEncryptedDeterministicSeed() {
            return this.encryptedDeterministicSeed_;
        }

        public EncryptedDataOrBuilder getEncryptedDeterministicSeedOrBuilder() {
            return this.encryptedDeterministicSeed_;
        }

        private void initFields() {
            this.type_ = C3511Type.ORIGINAL;
            this.secretBytes_ = ByteString.EMPTY;
            this.encryptedData_ = EncryptedData.getDefaultInstance();
            this.publicKey_ = ByteString.EMPTY;
            this.label_ = "";
            this.creationTimestamp_ = 0;
            this.deterministicKey_ = DeterministicKey.getDefaultInstance();
            this.deterministicSeed_ = ByteString.EMPTY;
            this.encryptedDeterministicSeed_ = EncryptedData.getDefaultInstance();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasType()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (hasEncryptedData() && !getEncryptedData().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (hasDeterministicKey() && !getDeterministicKey().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasEncryptedDeterministicSeed() || getEncryptedDeterministicSeed().isInitialized()) {
                this.memoizedIsInitialized = 1;
                return true;
            } else {
                this.memoizedIsInitialized = 0;
                return false;
            }
        }

        /* JADX WARNING: type inference failed for: r1v4, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$EncryptedData] */
        /* JADX WARNING: type inference failed for: r2v4, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$DeterministicKey] */
        /* JADX WARNING: type inference failed for: r2v5, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$EncryptedData] */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v4, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$EncryptedData]
          assigns: [org.bitcoinj.wallet.Protos$EncryptedData]
          uses: [com.google.protobuf.MessageLite]
          mth insns count: 50
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void writeTo(com.google.protobuf.CodedOutputStream r7) throws java.io.IOException {
            /*
                r6 = this;
                r6.getSerializedSize()
                int r0 = r6.bitField0_
                r1 = 1
                r0 = r0 & r1
                if (r0 != r1) goto L_0x0012
                org.bitcoinj.wallet.Protos$Key$Type r0 = r6.type_
                int r0 = r0.getNumber()
                r7.writeEnum(r1, r0)
            L_0x0012:
                int r0 = r6.bitField0_
                r1 = 2
                r0 = r0 & r1
                if (r0 != r1) goto L_0x001d
                com.google.protobuf.ByteString r0 = r6.secretBytes_
                r7.writeBytes(r1, r0)
            L_0x001d:
                int r0 = r6.bitField0_
                r1 = 8
                r0 = r0 & r1
                if (r0 != r1) goto L_0x002a
                r0 = 3
                com.google.protobuf.ByteString r2 = r6.publicKey_
                r7.writeBytes(r0, r2)
            L_0x002a:
                int r0 = r6.bitField0_
                r2 = 16
                r0 = r0 & r2
                r3 = 4
                if (r0 != r2) goto L_0x0039
                com.google.protobuf.ByteString r0 = r6.getLabelBytes()
                r7.writeBytes(r3, r0)
            L_0x0039:
                int r0 = r6.bitField0_
                r2 = 32
                r0 = r0 & r2
                if (r0 != r2) goto L_0x0046
                r0 = 5
                long r4 = r6.creationTimestamp_
                r7.writeInt64(r0, r4)
            L_0x0046:
                int r0 = r6.bitField0_
                r0 = r0 & r3
                if (r0 != r3) goto L_0x0051
                r0 = 6
                org.bitcoinj.wallet.Protos$EncryptedData r2 = r6.encryptedData_
                r7.writeMessage(r0, r2)
            L_0x0051:
                int r0 = r6.bitField0_
                r2 = 64
                r0 = r0 & r2
                if (r0 != r2) goto L_0x005e
                r0 = 7
                org.bitcoinj.wallet.Protos$DeterministicKey r2 = r6.deterministicKey_
                r7.writeMessage(r0, r2)
            L_0x005e:
                int r0 = r6.bitField0_
                r2 = 128(0x80, float:1.794E-43)
                r0 = r0 & r2
                if (r0 != r2) goto L_0x006a
                com.google.protobuf.ByteString r0 = r6.deterministicSeed_
                r7.writeBytes(r1, r0)
            L_0x006a:
                int r0 = r6.bitField0_
                r1 = 256(0x100, float:3.59E-43)
                r0 = r0 & r1
                if (r0 != r1) goto L_0x0078
                r0 = 9
                org.bitcoinj.wallet.Protos$EncryptedData r1 = r6.encryptedDeterministicSeed_
                r7.writeMessage(r0, r1)
            L_0x0078:
                com.google.protobuf.UnknownFieldSet r0 = r6.getUnknownFields()
                r0.writeTo(r7)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.wallet.Protos.C3509Key.writeTo(com.google.protobuf.CodedOutputStream):void");
        }

        /* JADX WARNING: type inference failed for: r2v4, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$EncryptedData] */
        /* JADX WARNING: type inference failed for: r3v4, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$DeterministicKey] */
        /* JADX WARNING: type inference failed for: r3v5, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$EncryptedData] */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v4, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$EncryptedData]
          assigns: [org.bitcoinj.wallet.Protos$EncryptedData]
          uses: [com.google.protobuf.MessageLite]
          mth insns count: 64
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int getSerializedSize() {
            /*
                r7 = this;
                int r0 = r7.memoizedSerializedSize
                r1 = -1
                if (r0 == r1) goto L_0x0006
                return r0
            L_0x0006:
                r0 = 0
                int r1 = r7.bitField0_
                r2 = 1
                r1 = r1 & r2
                if (r1 != r2) goto L_0x0018
                org.bitcoinj.wallet.Protos$Key$Type r1 = r7.type_
                int r1 = r1.getNumber()
                int r1 = com.google.protobuf.CodedOutputStream.computeEnumSize(r2, r1)
                int r0 = r0 + r1
            L_0x0018:
                int r1 = r7.bitField0_
                r2 = 2
                r1 = r1 & r2
                if (r1 != r2) goto L_0x0025
                com.google.protobuf.ByteString r1 = r7.secretBytes_
                int r1 = com.google.protobuf.CodedOutputStream.computeBytesSize(r2, r1)
                int r0 = r0 + r1
            L_0x0025:
                int r1 = r7.bitField0_
                r2 = 8
                r1 = r1 & r2
                if (r1 != r2) goto L_0x0034
                r1 = 3
                com.google.protobuf.ByteString r3 = r7.publicKey_
                int r1 = com.google.protobuf.CodedOutputStream.computeBytesSize(r1, r3)
                int r0 = r0 + r1
            L_0x0034:
                int r1 = r7.bitField0_
                r3 = 16
                r1 = r1 & r3
                r4 = 4
                if (r1 != r3) goto L_0x0045
                com.google.protobuf.ByteString r1 = r7.getLabelBytes()
                int r1 = com.google.protobuf.CodedOutputStream.computeBytesSize(r4, r1)
                int r0 = r0 + r1
            L_0x0045:
                int r1 = r7.bitField0_
                r3 = 32
                r1 = r1 & r3
                if (r1 != r3) goto L_0x0054
                r1 = 5
                long r5 = r7.creationTimestamp_
                int r1 = com.google.protobuf.CodedOutputStream.computeInt64Size(r1, r5)
                int r0 = r0 + r1
            L_0x0054:
                int r1 = r7.bitField0_
                r1 = r1 & r4
                if (r1 != r4) goto L_0x0061
                r1 = 6
                org.bitcoinj.wallet.Protos$EncryptedData r3 = r7.encryptedData_
                int r1 = com.google.protobuf.CodedOutputStream.computeMessageSize(r1, r3)
                int r0 = r0 + r1
            L_0x0061:
                int r1 = r7.bitField0_
                r3 = 64
                r1 = r1 & r3
                if (r1 != r3) goto L_0x0070
                r1 = 7
                org.bitcoinj.wallet.Protos$DeterministicKey r3 = r7.deterministicKey_
                int r1 = com.google.protobuf.CodedOutputStream.computeMessageSize(r1, r3)
                int r0 = r0 + r1
            L_0x0070:
                int r1 = r7.bitField0_
                r3 = 128(0x80, float:1.794E-43)
                r1 = r1 & r3
                if (r1 != r3) goto L_0x007e
                com.google.protobuf.ByteString r1 = r7.deterministicSeed_
                int r1 = com.google.protobuf.CodedOutputStream.computeBytesSize(r2, r1)
                int r0 = r0 + r1
            L_0x007e:
                int r1 = r7.bitField0_
                r2 = 256(0x100, float:3.59E-43)
                r1 = r1 & r2
                if (r1 != r2) goto L_0x008e
                r1 = 9
                org.bitcoinj.wallet.Protos$EncryptedData r2 = r7.encryptedDeterministicSeed_
                int r1 = com.google.protobuf.CodedOutputStream.computeMessageSize(r1, r2)
                int r0 = r0 + r1
            L_0x008e:
                com.google.protobuf.UnknownFieldSet r1 = r7.getUnknownFields()
                int r1 = r1.getSerializedSize()
                int r0 = r0 + r1
                r7.memoizedSerializedSize = r0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.wallet.Protos.C3509Key.getSerializedSize():int");
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static C3509Key parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (C3509Key) PARSER.parseFrom(byteString);
        }

        public static C3509Key parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (C3509Key) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static C3509Key parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (C3509Key) PARSER.parseFrom(bArr);
        }

        public static C3509Key parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (C3509Key) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static C3509Key parseFrom(InputStream inputStream) throws IOException {
            return (C3509Key) PARSER.parseFrom(inputStream);
        }

        public static C3509Key parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (C3509Key) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static C3509Key parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (C3509Key) PARSER.parseDelimitedFrom(inputStream);
        }

        public static C3509Key parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (C3509Key) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static C3509Key parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (C3509Key) PARSER.parseFrom(codedInputStream);
        }

        public static C3509Key parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (C3509Key) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(C3509Key key) {
            return newBuilder().mergeFrom(key);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface KeyOrBuilder extends MessageOrBuilder {
        long getCreationTimestamp();

        DeterministicKey getDeterministicKey();

        DeterministicKeyOrBuilder getDeterministicKeyOrBuilder();

        ByteString getDeterministicSeed();

        EncryptedData getEncryptedData();

        EncryptedDataOrBuilder getEncryptedDataOrBuilder();

        EncryptedData getEncryptedDeterministicSeed();

        EncryptedDataOrBuilder getEncryptedDeterministicSeedOrBuilder();

        String getLabel();

        ByteString getLabelBytes();

        ByteString getPublicKey();

        ByteString getSecretBytes();

        C3511Type getType();

        boolean hasCreationTimestamp();

        boolean hasDeterministicKey();

        boolean hasDeterministicSeed();

        boolean hasEncryptedData();

        boolean hasEncryptedDeterministicSeed();

        boolean hasLabel();

        boolean hasPublicKey();

        boolean hasSecretBytes();

        boolean hasType();
    }

    public static final class PeerAddress extends GeneratedMessage implements PeerAddressOrBuilder {
        public static final int IP_ADDRESS_FIELD_NUMBER = 1;
        public static Parser<PeerAddress> PARSER = new AbstractParser<PeerAddress>() {
            public PeerAddress parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PeerAddress(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PORT_FIELD_NUMBER = 2;
        public static final int SERVICES_FIELD_NUMBER = 3;
        private static final PeerAddress defaultInstance = new PeerAddress(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public ByteString ipAddress_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public int port_;
        /* access modifiers changed from: private */
        public long services_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements PeerAddressOrBuilder {
            private int bitField0_;
            private ByteString ipAddress_;
            private int port_;
            private long services_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_wallet_PeerAddress_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_wallet_PeerAddress_fieldAccessorTable.ensureFieldAccessorsInitialized(PeerAddress.class, Builder.class);
            }

            private Builder() {
                this.ipAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.ipAddress_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                PeerAddress.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                PeerAddress.super.clear();
                this.ipAddress_ = ByteString.EMPTY;
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
                return Protos.internal_static_wallet_PeerAddress_descriptor;
            }

            public PeerAddress getDefaultInstanceForType() {
                return PeerAddress.getDefaultInstance();
            }

            public PeerAddress build() {
                PeerAddress buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public PeerAddress buildPartial() {
                PeerAddress peerAddress = new PeerAddress((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                peerAddress.ipAddress_ = this.ipAddress_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                peerAddress.port_ = this.port_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                peerAddress.services_ = this.services_;
                peerAddress.bitField0_ = i2;
                onBuilt();
                return peerAddress;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof PeerAddress) {
                    return mergeFrom((PeerAddress) message);
                }
                PeerAddress.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PeerAddress peerAddress) {
                if (peerAddress == PeerAddress.getDefaultInstance()) {
                    return this;
                }
                if (peerAddress.hasIpAddress()) {
                    setIpAddress(peerAddress.getIpAddress());
                }
                if (peerAddress.hasPort()) {
                    setPort(peerAddress.getPort());
                }
                if (peerAddress.hasServices()) {
                    setServices(peerAddress.getServices());
                }
                mergeUnknownFields(peerAddress.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (hasIpAddress() && hasPort() && hasServices()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                PeerAddress peerAddress;
                PeerAddress peerAddress2 = null;
                try {
                    PeerAddress peerAddress3 = (PeerAddress) PeerAddress.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (peerAddress3 != null) {
                        mergeFrom(peerAddress3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    peerAddress = (PeerAddress) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    peerAddress2 = peerAddress;
                }
                if (peerAddress2 != null) {
                    mergeFrom(peerAddress2);
                }
                throw th;
            }

            public boolean hasIpAddress() {
                return (this.bitField0_ & 1) == 1;
            }

            public ByteString getIpAddress() {
                return this.ipAddress_;
            }

            public Builder setIpAddress(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.ipAddress_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearIpAddress() {
                this.bitField0_ &= -2;
                this.ipAddress_ = PeerAddress.getDefaultInstance().getIpAddress();
                onChanged();
                return this;
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

            public long getServices() {
                return this.services_;
            }

            public Builder setServices(long j) {
                this.bitField0_ |= 4;
                this.services_ = j;
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

        private PeerAddress(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private PeerAddress(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static PeerAddress getDefaultInstance() {
            return defaultInstance;
        }

        public PeerAddress getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [org.bitcoinj.wallet.Protos$PeerAddress, com.google.protobuf.MessageLite] */
        private PeerAddress(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.ipAddress_ = codedInputStream.readBytes();
                        } else if (readTag == 16) {
                            this.bitField0_ |= 2;
                            this.port_ = codedInputStream.readUInt32();
                        } else if (readTag == 24) {
                            this.bitField0_ |= 4;
                            this.services_ = codedInputStream.readUInt64();
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
            return Protos.internal_static_wallet_PeerAddress_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_wallet_PeerAddress_fieldAccessorTable.ensureFieldAccessorsInitialized(PeerAddress.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<PeerAddress> getParserForType() {
            return PARSER;
        }

        public boolean hasIpAddress() {
            return (this.bitField0_ & 1) == 1;
        }

        public ByteString getIpAddress() {
            return this.ipAddress_;
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

        public long getServices() {
            return this.services_;
        }

        private void initFields() {
            this.ipAddress_ = ByteString.EMPTY;
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
                codedOutputStream.writeBytes(1, this.ipAddress_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeUInt32(2, this.port_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeUInt64(3, this.services_);
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
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.ipAddress_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeUInt32Size(2, this.port_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeUInt64Size(3, this.services_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static PeerAddress parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (PeerAddress) PARSER.parseFrom(byteString);
        }

        public static PeerAddress parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PeerAddress) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PeerAddress parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (PeerAddress) PARSER.parseFrom(bArr);
        }

        public static PeerAddress parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PeerAddress) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PeerAddress parseFrom(InputStream inputStream) throws IOException {
            return (PeerAddress) PARSER.parseFrom(inputStream);
        }

        public static PeerAddress parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PeerAddress) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static PeerAddress parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PeerAddress) PARSER.parseDelimitedFrom(inputStream);
        }

        public static PeerAddress parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PeerAddress) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static PeerAddress parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PeerAddress) PARSER.parseFrom(codedInputStream);
        }

        public static PeerAddress parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PeerAddress) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(PeerAddress peerAddress) {
            return newBuilder().mergeFrom(peerAddress);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface PeerAddressOrBuilder extends MessageOrBuilder {
        ByteString getIpAddress();

        int getPort();

        long getServices();

        boolean hasIpAddress();

        boolean hasPort();

        boolean hasServices();
    }

    public static final class Script extends GeneratedMessage implements ScriptOrBuilder {
        public static final int CREATION_TIMESTAMP_FIELD_NUMBER = 2;
        public static Parser<Script> PARSER = new AbstractParser<Script>() {
            public Script parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Script(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PROGRAM_FIELD_NUMBER = 1;
        private static final Script defaultInstance = new Script(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public long creationTimestamp_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString program_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements ScriptOrBuilder {
            private int bitField0_;
            private long creationTimestamp_;
            private ByteString program_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_wallet_Script_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_wallet_Script_fieldAccessorTable.ensureFieldAccessorsInitialized(Script.class, Builder.class);
            }

            private Builder() {
                this.program_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.program_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                Script.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                Script.super.clear();
                this.program_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                this.creationTimestamp_ = 0;
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_wallet_Script_descriptor;
            }

            public Script getDefaultInstanceForType() {
                return Script.getDefaultInstance();
            }

            public Script build() {
                Script buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Script buildPartial() {
                Script script = new Script((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                script.program_ = this.program_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                script.creationTimestamp_ = this.creationTimestamp_;
                script.bitField0_ = i2;
                onBuilt();
                return script;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof Script) {
                    return mergeFrom((Script) message);
                }
                Script.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Script script) {
                if (script == Script.getDefaultInstance()) {
                    return this;
                }
                if (script.hasProgram()) {
                    setProgram(script.getProgram());
                }
                if (script.hasCreationTimestamp()) {
                    setCreationTimestamp(script.getCreationTimestamp());
                }
                mergeUnknownFields(script.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (hasProgram() && hasCreationTimestamp()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Script script;
                Script script2 = null;
                try {
                    Script script3 = (Script) Script.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (script3 != null) {
                        mergeFrom(script3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    script = (Script) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    script2 = script;
                }
                if (script2 != null) {
                    mergeFrom(script2);
                }
                throw th;
            }

            public boolean hasProgram() {
                return (this.bitField0_ & 1) == 1;
            }

            public ByteString getProgram() {
                return this.program_;
            }

            public Builder setProgram(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.program_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearProgram() {
                this.bitField0_ &= -2;
                this.program_ = Script.getDefaultInstance().getProgram();
                onChanged();
                return this;
            }

            public boolean hasCreationTimestamp() {
                return (this.bitField0_ & 2) == 2;
            }

            public long getCreationTimestamp() {
                return this.creationTimestamp_;
            }

            public Builder setCreationTimestamp(long j) {
                this.bitField0_ |= 2;
                this.creationTimestamp_ = j;
                onChanged();
                return this;
            }

            public Builder clearCreationTimestamp() {
                this.bitField0_ &= -3;
                this.creationTimestamp_ = 0;
                onChanged();
                return this;
            }
        }

        private Script(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Script(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Script getDefaultInstance() {
            return defaultInstance;
        }

        public Script getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$Script] */
        private Script(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.program_ = codedInputStream.readBytes();
                        } else if (readTag == 16) {
                            this.bitField0_ |= 2;
                            this.creationTimestamp_ = codedInputStream.readInt64();
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
            return Protos.internal_static_wallet_Script_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_wallet_Script_fieldAccessorTable.ensureFieldAccessorsInitialized(Script.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<Script> getParserForType() {
            return PARSER;
        }

        public boolean hasProgram() {
            return (this.bitField0_ & 1) == 1;
        }

        public ByteString getProgram() {
            return this.program_;
        }

        public boolean hasCreationTimestamp() {
            return (this.bitField0_ & 2) == 2;
        }

        public long getCreationTimestamp() {
            return this.creationTimestamp_;
        }

        private void initFields() {
            this.program_ = ByteString.EMPTY;
            this.creationTimestamp_ = 0;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasProgram()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasCreationTimestamp()) {
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
                codedOutputStream.writeBytes(1, this.program_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt64(2, this.creationTimestamp_);
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
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.program_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeInt64Size(2, this.creationTimestamp_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static Script parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Script) PARSER.parseFrom(byteString);
        }

        public static Script parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Script) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Script parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Script) PARSER.parseFrom(bArr);
        }

        public static Script parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Script) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Script parseFrom(InputStream inputStream) throws IOException {
            return (Script) PARSER.parseFrom(inputStream);
        }

        public static Script parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Script) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static Script parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Script) PARSER.parseDelimitedFrom(inputStream);
        }

        public static Script parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Script) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static Script parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Script) PARSER.parseFrom(codedInputStream);
        }

        public static Script parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Script) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Script script) {
            return newBuilder().mergeFrom(script);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface ScriptOrBuilder extends MessageOrBuilder {
        long getCreationTimestamp();

        ByteString getProgram();

        boolean hasCreationTimestamp();

        boolean hasProgram();
    }

    public static final class ScryptParameters extends GeneratedMessage implements ScryptParametersOrBuilder {
        public static final int N_FIELD_NUMBER = 2;
        public static Parser<ScryptParameters> PARSER = new AbstractParser<ScryptParameters>() {
            public ScryptParameters parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ScryptParameters(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int P_FIELD_NUMBER = 4;
        public static final int R_FIELD_NUMBER = 3;
        public static final int SALT_FIELD_NUMBER = 1;
        private static final ScryptParameters defaultInstance = new ScryptParameters(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */

        /* renamed from: n_ */
        public long f822n_;
        /* access modifiers changed from: private */

        /* renamed from: p_ */
        public int f823p_;
        /* access modifiers changed from: private */

        /* renamed from: r_ */
        public int f824r_;
        /* access modifiers changed from: private */
        public ByteString salt_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements ScryptParametersOrBuilder {
            private int bitField0_;

            /* renamed from: n_ */
            private long f825n_;

            /* renamed from: p_ */
            private int f826p_;

            /* renamed from: r_ */
            private int f827r_;
            private ByteString salt_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_wallet_ScryptParameters_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_wallet_ScryptParameters_fieldAccessorTable.ensureFieldAccessorsInitialized(ScryptParameters.class, Builder.class);
            }

            private Builder() {
                this.salt_ = ByteString.EMPTY;
                this.f825n_ = PlaybackStateCompat.ACTION_PREPARE;
                this.f827r_ = 8;
                this.f826p_ = 1;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.salt_ = ByteString.EMPTY;
                this.f825n_ = PlaybackStateCompat.ACTION_PREPARE;
                this.f827r_ = 8;
                this.f826p_ = 1;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                ScryptParameters.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                ScryptParameters.super.clear();
                this.salt_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                this.f825n_ = PlaybackStateCompat.ACTION_PREPARE;
                this.bitField0_ &= -3;
                this.f827r_ = 8;
                this.bitField0_ &= -5;
                this.f826p_ = 1;
                this.bitField0_ &= -9;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_wallet_ScryptParameters_descriptor;
            }

            public ScryptParameters getDefaultInstanceForType() {
                return ScryptParameters.getDefaultInstance();
            }

            public ScryptParameters build() {
                ScryptParameters buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public ScryptParameters buildPartial() {
                ScryptParameters scryptParameters = new ScryptParameters((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                scryptParameters.salt_ = this.salt_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                scryptParameters.f822n_ = this.f825n_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                scryptParameters.f824r_ = this.f827r_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                scryptParameters.f823p_ = this.f826p_;
                scryptParameters.bitField0_ = i2;
                onBuilt();
                return scryptParameters;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof ScryptParameters) {
                    return mergeFrom((ScryptParameters) message);
                }
                ScryptParameters.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ScryptParameters scryptParameters) {
                if (scryptParameters == ScryptParameters.getDefaultInstance()) {
                    return this;
                }
                if (scryptParameters.hasSalt()) {
                    setSalt(scryptParameters.getSalt());
                }
                if (scryptParameters.hasN()) {
                    setN(scryptParameters.getN());
                }
                if (scryptParameters.hasR()) {
                    setR(scryptParameters.getR());
                }
                if (scryptParameters.hasP()) {
                    setP(scryptParameters.getP());
                }
                mergeUnknownFields(scryptParameters.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                return hasSalt();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ScryptParameters scryptParameters;
                ScryptParameters scryptParameters2 = null;
                try {
                    ScryptParameters scryptParameters3 = (ScryptParameters) ScryptParameters.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (scryptParameters3 != null) {
                        mergeFrom(scryptParameters3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    scryptParameters = (ScryptParameters) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    scryptParameters2 = scryptParameters;
                }
                if (scryptParameters2 != null) {
                    mergeFrom(scryptParameters2);
                }
                throw th;
            }

            public boolean hasSalt() {
                return (this.bitField0_ & 1) == 1;
            }

            public ByteString getSalt() {
                return this.salt_;
            }

            public Builder setSalt(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.salt_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearSalt() {
                this.bitField0_ &= -2;
                this.salt_ = ScryptParameters.getDefaultInstance().getSalt();
                onChanged();
                return this;
            }

            public boolean hasN() {
                return (this.bitField0_ & 2) == 2;
            }

            public long getN() {
                return this.f825n_;
            }

            public Builder setN(long j) {
                this.bitField0_ |= 2;
                this.f825n_ = j;
                onChanged();
                return this;
            }

            public Builder clearN() {
                this.bitField0_ &= -3;
                this.f825n_ = PlaybackStateCompat.ACTION_PREPARE;
                onChanged();
                return this;
            }

            public boolean hasR() {
                return (this.bitField0_ & 4) == 4;
            }

            public int getR() {
                return this.f827r_;
            }

            public Builder setR(int i) {
                this.bitField0_ |= 4;
                this.f827r_ = i;
                onChanged();
                return this;
            }

            public Builder clearR() {
                this.bitField0_ &= -5;
                this.f827r_ = 8;
                onChanged();
                return this;
            }

            public boolean hasP() {
                return (this.bitField0_ & 8) == 8;
            }

            public int getP() {
                return this.f826p_;
            }

            public Builder setP(int i) {
                this.bitField0_ |= 8;
                this.f826p_ = i;
                onChanged();
                return this;
            }

            public Builder clearP() {
                this.bitField0_ &= -9;
                this.f826p_ = 1;
                onChanged();
                return this;
            }
        }

        private ScryptParameters(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private ScryptParameters(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ScryptParameters getDefaultInstance() {
            return defaultInstance;
        }

        public ScryptParameters getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$ScryptParameters] */
        private ScryptParameters(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.salt_ = codedInputStream.readBytes();
                        } else if (readTag == 16) {
                            this.bitField0_ |= 2;
                            this.f822n_ = codedInputStream.readInt64();
                        } else if (readTag == 24) {
                            this.bitField0_ |= 4;
                            this.f824r_ = codedInputStream.readInt32();
                        } else if (readTag == 32) {
                            this.bitField0_ |= 8;
                            this.f823p_ = codedInputStream.readInt32();
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
            return Protos.internal_static_wallet_ScryptParameters_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_wallet_ScryptParameters_fieldAccessorTable.ensureFieldAccessorsInitialized(ScryptParameters.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<ScryptParameters> getParserForType() {
            return PARSER;
        }

        public boolean hasSalt() {
            return (this.bitField0_ & 1) == 1;
        }

        public ByteString getSalt() {
            return this.salt_;
        }

        public boolean hasN() {
            return (this.bitField0_ & 2) == 2;
        }

        public long getN() {
            return this.f822n_;
        }

        public boolean hasR() {
            return (this.bitField0_ & 4) == 4;
        }

        public int getR() {
            return this.f824r_;
        }

        public boolean hasP() {
            return (this.bitField0_ & 8) == 8;
        }

        public int getP() {
            return this.f823p_;
        }

        private void initFields() {
            this.salt_ = ByteString.EMPTY;
            this.f822n_ = PlaybackStateCompat.ACTION_PREPARE;
            this.f824r_ = 8;
            this.f823p_ = 1;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasSalt()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, this.salt_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt64(2, this.f822n_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeInt32(3, this.f824r_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeInt32(4, this.f823p_);
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
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.salt_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeInt64Size(2, this.f822n_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeInt32Size(3, this.f824r_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeInt32Size(4, this.f823p_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static ScryptParameters parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ScryptParameters) PARSER.parseFrom(byteString);
        }

        public static ScryptParameters parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ScryptParameters) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ScryptParameters parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ScryptParameters) PARSER.parseFrom(bArr);
        }

        public static ScryptParameters parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ScryptParameters) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ScryptParameters parseFrom(InputStream inputStream) throws IOException {
            return (ScryptParameters) PARSER.parseFrom(inputStream);
        }

        public static ScryptParameters parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ScryptParameters) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static ScryptParameters parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ScryptParameters) PARSER.parseDelimitedFrom(inputStream);
        }

        public static ScryptParameters parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ScryptParameters) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static ScryptParameters parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ScryptParameters) PARSER.parseFrom(codedInputStream);
        }

        public static ScryptParameters parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ScryptParameters) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ScryptParameters scryptParameters) {
            return newBuilder().mergeFrom(scryptParameters);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface ScryptParametersOrBuilder extends MessageOrBuilder {
        long getN();

        int getP();

        int getR();

        ByteString getSalt();

        boolean hasN();

        boolean hasP();

        boolean hasR();

        boolean hasSalt();
    }

    public static final class Tag extends GeneratedMessage implements TagOrBuilder {
        public static final int DATA_FIELD_NUMBER = 2;
        public static Parser<Tag> PARSER = new AbstractParser<Tag>() {
            public Tag parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Tag(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int TAG_FIELD_NUMBER = 1;
        private static final Tag defaultInstance = new Tag(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public ByteString data_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public Object tag_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements TagOrBuilder {
            private int bitField0_;
            private ByteString data_;
            private Object tag_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_wallet_Tag_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_wallet_Tag_fieldAccessorTable.ensureFieldAccessorsInitialized(Tag.class, Builder.class);
            }

            private Builder() {
                this.tag_ = "";
                this.data_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.tag_ = "";
                this.data_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                Tag.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                Tag.super.clear();
                this.tag_ = "";
                this.bitField0_ &= -2;
                this.data_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_wallet_Tag_descriptor;
            }

            public Tag getDefaultInstanceForType() {
                return Tag.getDefaultInstance();
            }

            public Tag build() {
                Tag buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Tag buildPartial() {
                Tag tag = new Tag((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                tag.tag_ = this.tag_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                tag.data_ = this.data_;
                tag.bitField0_ = i2;
                onBuilt();
                return tag;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof Tag) {
                    return mergeFrom((Tag) message);
                }
                Tag.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Tag tag) {
                if (tag == Tag.getDefaultInstance()) {
                    return this;
                }
                if (tag.hasTag()) {
                    this.bitField0_ |= 1;
                    this.tag_ = tag.tag_;
                    onChanged();
                }
                if (tag.hasData()) {
                    setData(tag.getData());
                }
                mergeUnknownFields(tag.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (hasTag() && hasData()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Tag tag;
                Tag tag2 = null;
                try {
                    Tag tag3 = (Tag) Tag.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (tag3 != null) {
                        mergeFrom(tag3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    tag = (Tag) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    tag2 = tag;
                }
                if (tag2 != null) {
                    mergeFrom(tag2);
                }
                throw th;
            }

            public boolean hasTag() {
                return (this.bitField0_ & 1) == 1;
            }

            public String getTag() {
                Object obj = this.tag_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.tag_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getTagBytes() {
                Object obj = this.tag_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.tag_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setTag(String str) {
                if (str != null) {
                    this.bitField0_ |= 1;
                    this.tag_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearTag() {
                this.bitField0_ &= -2;
                this.tag_ = Tag.getDefaultInstance().getTag();
                onChanged();
                return this;
            }

            public Builder setTagBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.tag_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public boolean hasData() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getData() {
                return this.data_;
            }

            public Builder setData(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.data_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearData() {
                this.bitField0_ &= -3;
                this.data_ = Tag.getDefaultInstance().getData();
                onChanged();
                return this;
            }
        }

        private Tag(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Tag(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Tag getDefaultInstance() {
            return defaultInstance;
        }

        public Tag getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [org.bitcoinj.wallet.Protos$Tag, com.google.protobuf.MessageLite] */
        private Tag(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.tag_ = readBytes;
                        } else if (readTag == 18) {
                            this.bitField0_ |= 2;
                            this.data_ = codedInputStream.readBytes();
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
            return Protos.internal_static_wallet_Tag_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_wallet_Tag_fieldAccessorTable.ensureFieldAccessorsInitialized(Tag.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<Tag> getParserForType() {
            return PARSER;
        }

        public boolean hasTag() {
            return (this.bitField0_ & 1) == 1;
        }

        public String getTag() {
            Object obj = this.tag_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.tag_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getTagBytes() {
            Object obj = this.tag_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.tag_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasData() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getData() {
            return this.data_;
        }

        private void initFields() {
            this.tag_ = "";
            this.data_ = ByteString.EMPTY;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasTag()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasData()) {
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
                codedOutputStream.writeBytes(1, getTagBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.data_);
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
                i2 = 0 + CodedOutputStream.computeBytesSize(1, getTagBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(2, this.data_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static Tag parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Tag) PARSER.parseFrom(byteString);
        }

        public static Tag parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Tag) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Tag parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Tag) PARSER.parseFrom(bArr);
        }

        public static Tag parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Tag) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Tag parseFrom(InputStream inputStream) throws IOException {
            return (Tag) PARSER.parseFrom(inputStream);
        }

        public static Tag parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Tag) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static Tag parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Tag) PARSER.parseDelimitedFrom(inputStream);
        }

        public static Tag parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Tag) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static Tag parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Tag) PARSER.parseFrom(codedInputStream);
        }

        public static Tag parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Tag) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Tag tag) {
            return newBuilder().mergeFrom(tag);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface TagOrBuilder extends MessageOrBuilder {
        ByteString getData();

        String getTag();

        ByteString getTagBytes();

        boolean hasData();

        boolean hasTag();
    }

    public static final class Transaction extends GeneratedMessage implements TransactionOrBuilder {
        public static final int BLOCK_HASH_FIELD_NUMBER = 8;
        public static final int BLOCK_RELATIVITY_OFFSETS_FIELD_NUMBER = 11;
        public static final int CONFIDENCE_FIELD_NUMBER = 9;
        public static final int EXCHANGE_RATE_FIELD_NUMBER = 12;
        public static final int HASH_FIELD_NUMBER = 2;
        public static final int LOCK_TIME_FIELD_NUMBER = 4;
        public static final int MEMO_FIELD_NUMBER = 13;
        public static Parser<Transaction> PARSER = new AbstractParser<Transaction>() {
            public Transaction parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Transaction(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int POOL_FIELD_NUMBER = 3;
        public static final int PURPOSE_FIELD_NUMBER = 10;
        public static final int TRANSACTION_INPUT_FIELD_NUMBER = 6;
        public static final int TRANSACTION_OUTPUT_FIELD_NUMBER = 7;
        public static final int UPDATED_AT_FIELD_NUMBER = 5;
        public static final int VERSION_FIELD_NUMBER = 1;
        private static final Transaction defaultInstance = new Transaction(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public List<ByteString> blockHash_;
        /* access modifiers changed from: private */
        public List<Integer> blockRelativityOffsets_;
        /* access modifiers changed from: private */
        public TransactionConfidence confidence_;
        /* access modifiers changed from: private */
        public ExchangeRate exchangeRate_;
        /* access modifiers changed from: private */
        public ByteString hash_;
        /* access modifiers changed from: private */
        public int lockTime_;
        /* access modifiers changed from: private */
        public Object memo_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public Pool pool_;
        /* access modifiers changed from: private */
        public Purpose purpose_;
        /* access modifiers changed from: private */
        public List<TransactionInput> transactionInput_;
        /* access modifiers changed from: private */
        public List<TransactionOutput> transactionOutput_;
        private final UnknownFieldSet unknownFields;
        /* access modifiers changed from: private */
        public long updatedAt_;
        /* access modifiers changed from: private */
        public int version_;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements TransactionOrBuilder {
            private int bitField0_;
            private List<ByteString> blockHash_;
            private List<Integer> blockRelativityOffsets_;
            private SingleFieldBuilder<TransactionConfidence, Builder, TransactionConfidenceOrBuilder> confidenceBuilder_;
            private TransactionConfidence confidence_;
            private SingleFieldBuilder<ExchangeRate, Builder, ExchangeRateOrBuilder> exchangeRateBuilder_;
            private ExchangeRate exchangeRate_;
            private ByteString hash_;
            private int lockTime_;
            private Object memo_;
            private Pool pool_;
            private Purpose purpose_;
            private RepeatedFieldBuilder<TransactionInput, Builder, TransactionInputOrBuilder> transactionInputBuilder_;
            private List<TransactionInput> transactionInput_;
            private RepeatedFieldBuilder<TransactionOutput, Builder, TransactionOutputOrBuilder> transactionOutputBuilder_;
            private List<TransactionOutput> transactionOutput_;
            private long updatedAt_;
            private int version_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_wallet_Transaction_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_wallet_Transaction_fieldAccessorTable.ensureFieldAccessorsInitialized(Transaction.class, Builder.class);
            }

            private Builder() {
                this.hash_ = ByteString.EMPTY;
                this.pool_ = Pool.UNSPENT;
                this.transactionInput_ = Collections.emptyList();
                this.transactionOutput_ = Collections.emptyList();
                this.blockHash_ = Collections.emptyList();
                this.blockRelativityOffsets_ = Collections.emptyList();
                this.confidence_ = TransactionConfidence.getDefaultInstance();
                this.purpose_ = Purpose.UNKNOWN;
                this.exchangeRate_ = ExchangeRate.getDefaultInstance();
                this.memo_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.hash_ = ByteString.EMPTY;
                this.pool_ = Pool.UNSPENT;
                this.transactionInput_ = Collections.emptyList();
                this.transactionOutput_ = Collections.emptyList();
                this.blockHash_ = Collections.emptyList();
                this.blockRelativityOffsets_ = Collections.emptyList();
                this.confidence_ = TransactionConfidence.getDefaultInstance();
                this.purpose_ = Purpose.UNKNOWN;
                this.exchangeRate_ = ExchangeRate.getDefaultInstance();
                this.memo_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (Transaction.alwaysUseFieldBuilders) {
                    getTransactionInputFieldBuilder();
                    getTransactionOutputFieldBuilder();
                    getConfidenceFieldBuilder();
                    getExchangeRateFieldBuilder();
                }
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                Transaction.super.clear();
                this.version_ = 0;
                this.bitField0_ &= -2;
                this.hash_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                this.pool_ = Pool.UNSPENT;
                this.bitField0_ &= -5;
                this.lockTime_ = 0;
                this.bitField0_ &= -9;
                this.updatedAt_ = 0;
                this.bitField0_ &= -17;
                RepeatedFieldBuilder<TransactionInput, Builder, TransactionInputOrBuilder> repeatedFieldBuilder = this.transactionInputBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.transactionInput_ = Collections.emptyList();
                    this.bitField0_ &= -33;
                } else {
                    repeatedFieldBuilder.clear();
                }
                RepeatedFieldBuilder<TransactionOutput, Builder, TransactionOutputOrBuilder> repeatedFieldBuilder2 = this.transactionOutputBuilder_;
                if (repeatedFieldBuilder2 == null) {
                    this.transactionOutput_ = Collections.emptyList();
                    this.bitField0_ &= -65;
                } else {
                    repeatedFieldBuilder2.clear();
                }
                this.blockHash_ = Collections.emptyList();
                this.bitField0_ &= -129;
                this.blockRelativityOffsets_ = Collections.emptyList();
                this.bitField0_ &= -257;
                SingleFieldBuilder<TransactionConfidence, Builder, TransactionConfidenceOrBuilder> singleFieldBuilder = this.confidenceBuilder_;
                if (singleFieldBuilder == null) {
                    this.confidence_ = TransactionConfidence.getDefaultInstance();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -513;
                this.purpose_ = Purpose.UNKNOWN;
                this.bitField0_ &= RESULT.E_TEEKM_JSON_OUT_ADDR_LEN;
                SingleFieldBuilder<ExchangeRate, Builder, ExchangeRateOrBuilder> singleFieldBuilder2 = this.exchangeRateBuilder_;
                if (singleFieldBuilder2 == null) {
                    this.exchangeRate_ = ExchangeRate.getDefaultInstance();
                } else {
                    singleFieldBuilder2.clear();
                }
                this.bitField0_ &= -2049;
                this.memo_ = "";
                this.bitField0_ &= -4097;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_wallet_Transaction_descriptor;
            }

            public Transaction getDefaultInstanceForType() {
                return Transaction.getDefaultInstance();
            }

            public Transaction build() {
                Transaction buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Transaction buildPartial() {
                Transaction transaction = new Transaction((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                transaction.version_ = this.version_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                transaction.hash_ = this.hash_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                transaction.pool_ = this.pool_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                transaction.lockTime_ = this.lockTime_;
                if ((i & 16) == 16) {
                    i2 |= 16;
                }
                transaction.updatedAt_ = this.updatedAt_;
                RepeatedFieldBuilder<TransactionInput, Builder, TransactionInputOrBuilder> repeatedFieldBuilder = this.transactionInputBuilder_;
                if (repeatedFieldBuilder == null) {
                    if ((this.bitField0_ & 32) == 32) {
                        this.transactionInput_ = Collections.unmodifiableList(this.transactionInput_);
                        this.bitField0_ &= -33;
                    }
                    transaction.transactionInput_ = this.transactionInput_;
                } else {
                    transaction.transactionInput_ = repeatedFieldBuilder.build();
                }
                RepeatedFieldBuilder<TransactionOutput, Builder, TransactionOutputOrBuilder> repeatedFieldBuilder2 = this.transactionOutputBuilder_;
                if (repeatedFieldBuilder2 == null) {
                    if ((this.bitField0_ & 64) == 64) {
                        this.transactionOutput_ = Collections.unmodifiableList(this.transactionOutput_);
                        this.bitField0_ &= -65;
                    }
                    transaction.transactionOutput_ = this.transactionOutput_;
                } else {
                    transaction.transactionOutput_ = repeatedFieldBuilder2.build();
                }
                if ((this.bitField0_ & 128) == 128) {
                    this.blockHash_ = Collections.unmodifiableList(this.blockHash_);
                    this.bitField0_ &= -129;
                }
                transaction.blockHash_ = this.blockHash_;
                if ((this.bitField0_ & 256) == 256) {
                    this.blockRelativityOffsets_ = Collections.unmodifiableList(this.blockRelativityOffsets_);
                    this.bitField0_ &= -257;
                }
                transaction.blockRelativityOffsets_ = this.blockRelativityOffsets_;
                if ((i & 512) == 512) {
                    i2 |= 32;
                }
                SingleFieldBuilder<TransactionConfidence, Builder, TransactionConfidenceOrBuilder> singleFieldBuilder = this.confidenceBuilder_;
                if (singleFieldBuilder == null) {
                    transaction.confidence_ = this.confidence_;
                } else {
                    transaction.confidence_ = singleFieldBuilder.build();
                }
                if ((i & 1024) == 1024) {
                    i2 |= 64;
                }
                transaction.purpose_ = this.purpose_;
                if ((i & 2048) == 2048) {
                    i2 |= 128;
                }
                SingleFieldBuilder<ExchangeRate, Builder, ExchangeRateOrBuilder> singleFieldBuilder2 = this.exchangeRateBuilder_;
                if (singleFieldBuilder2 == null) {
                    transaction.exchangeRate_ = this.exchangeRate_;
                } else {
                    transaction.exchangeRate_ = singleFieldBuilder2.build();
                }
                if ((i & 4096) == 4096) {
                    i2 |= 256;
                }
                transaction.memo_ = this.memo_;
                transaction.bitField0_ = i2;
                onBuilt();
                return transaction;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof Transaction) {
                    return mergeFrom((Transaction) message);
                }
                Transaction.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Transaction transaction) {
                if (transaction == Transaction.getDefaultInstance()) {
                    return this;
                }
                if (transaction.hasVersion()) {
                    setVersion(transaction.getVersion());
                }
                if (transaction.hasHash()) {
                    setHash(transaction.getHash());
                }
                if (transaction.hasPool()) {
                    setPool(transaction.getPool());
                }
                if (transaction.hasLockTime()) {
                    setLockTime(transaction.getLockTime());
                }
                if (transaction.hasUpdatedAt()) {
                    setUpdatedAt(transaction.getUpdatedAt());
                }
                RepeatedFieldBuilder<TransactionOutput, Builder, TransactionOutputOrBuilder> repeatedFieldBuilder = null;
                if (this.transactionInputBuilder_ == null) {
                    if (!transaction.transactionInput_.isEmpty()) {
                        if (this.transactionInput_.isEmpty()) {
                            this.transactionInput_ = transaction.transactionInput_;
                            this.bitField0_ &= -33;
                        } else {
                            ensureTransactionInputIsMutable();
                            this.transactionInput_.addAll(transaction.transactionInput_);
                        }
                        onChanged();
                    }
                } else if (!transaction.transactionInput_.isEmpty()) {
                    if (this.transactionInputBuilder_.isEmpty()) {
                        this.transactionInputBuilder_.dispose();
                        this.transactionInputBuilder_ = null;
                        this.transactionInput_ = transaction.transactionInput_;
                        this.bitField0_ &= -33;
                        this.transactionInputBuilder_ = Transaction.alwaysUseFieldBuilders ? getTransactionInputFieldBuilder() : null;
                    } else {
                        this.transactionInputBuilder_.addAllMessages(transaction.transactionInput_);
                    }
                }
                if (this.transactionOutputBuilder_ == null) {
                    if (!transaction.transactionOutput_.isEmpty()) {
                        if (this.transactionOutput_.isEmpty()) {
                            this.transactionOutput_ = transaction.transactionOutput_;
                            this.bitField0_ &= -65;
                        } else {
                            ensureTransactionOutputIsMutable();
                            this.transactionOutput_.addAll(transaction.transactionOutput_);
                        }
                        onChanged();
                    }
                } else if (!transaction.transactionOutput_.isEmpty()) {
                    if (this.transactionOutputBuilder_.isEmpty()) {
                        this.transactionOutputBuilder_.dispose();
                        this.transactionOutputBuilder_ = null;
                        this.transactionOutput_ = transaction.transactionOutput_;
                        this.bitField0_ &= -65;
                        if (Transaction.alwaysUseFieldBuilders) {
                            repeatedFieldBuilder = getTransactionOutputFieldBuilder();
                        }
                        this.transactionOutputBuilder_ = repeatedFieldBuilder;
                    } else {
                        this.transactionOutputBuilder_.addAllMessages(transaction.transactionOutput_);
                    }
                }
                if (!transaction.blockHash_.isEmpty()) {
                    if (this.blockHash_.isEmpty()) {
                        this.blockHash_ = transaction.blockHash_;
                        this.bitField0_ &= -129;
                    } else {
                        ensureBlockHashIsMutable();
                        this.blockHash_.addAll(transaction.blockHash_);
                    }
                    onChanged();
                }
                if (!transaction.blockRelativityOffsets_.isEmpty()) {
                    if (this.blockRelativityOffsets_.isEmpty()) {
                        this.blockRelativityOffsets_ = transaction.blockRelativityOffsets_;
                        this.bitField0_ &= -257;
                    } else {
                        ensureBlockRelativityOffsetsIsMutable();
                        this.blockRelativityOffsets_.addAll(transaction.blockRelativityOffsets_);
                    }
                    onChanged();
                }
                if (transaction.hasConfidence()) {
                    mergeConfidence(transaction.getConfidence());
                }
                if (transaction.hasPurpose()) {
                    setPurpose(transaction.getPurpose());
                }
                if (transaction.hasExchangeRate()) {
                    mergeExchangeRate(transaction.getExchangeRate());
                }
                if (transaction.hasMemo()) {
                    this.bitField0_ |= 4096;
                    this.memo_ = transaction.memo_;
                    onChanged();
                }
                mergeUnknownFields(transaction.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (!hasVersion() || !hasHash()) {
                    return false;
                }
                for (int i = 0; i < getTransactionInputCount(); i++) {
                    if (!getTransactionInput(i).isInitialized()) {
                        return false;
                    }
                }
                for (int i2 = 0; i2 < getTransactionOutputCount(); i2++) {
                    if (!getTransactionOutput(i2).isInitialized()) {
                        return false;
                    }
                }
                if (hasConfidence() && !getConfidence().isInitialized()) {
                    return false;
                }
                if (!hasExchangeRate() || getExchangeRate().isInitialized()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Transaction transaction;
                Transaction transaction2 = null;
                try {
                    Transaction transaction3 = (Transaction) Transaction.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (transaction3 != null) {
                        mergeFrom(transaction3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    transaction = (Transaction) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    transaction2 = transaction;
                }
                if (transaction2 != null) {
                    mergeFrom(transaction2);
                }
                throw th;
            }

            public boolean hasVersion() {
                return (this.bitField0_ & 1) == 1;
            }

            public int getVersion() {
                return this.version_;
            }

            public Builder setVersion(int i) {
                this.bitField0_ |= 1;
                this.version_ = i;
                onChanged();
                return this;
            }

            public Builder clearVersion() {
                this.bitField0_ &= -2;
                this.version_ = 0;
                onChanged();
                return this;
            }

            public boolean hasHash() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getHash() {
                return this.hash_;
            }

            public Builder setHash(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.hash_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearHash() {
                this.bitField0_ &= -3;
                this.hash_ = Transaction.getDefaultInstance().getHash();
                onChanged();
                return this;
            }

            public boolean hasPool() {
                return (this.bitField0_ & 4) == 4;
            }

            public Pool getPool() {
                return this.pool_;
            }

            public Builder setPool(Pool pool) {
                if (pool != null) {
                    this.bitField0_ |= 4;
                    this.pool_ = pool;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearPool() {
                this.bitField0_ &= -5;
                this.pool_ = Pool.UNSPENT;
                onChanged();
                return this;
            }

            public boolean hasLockTime() {
                return (this.bitField0_ & 8) == 8;
            }

            public int getLockTime() {
                return this.lockTime_;
            }

            public Builder setLockTime(int i) {
                this.bitField0_ |= 8;
                this.lockTime_ = i;
                onChanged();
                return this;
            }

            public Builder clearLockTime() {
                this.bitField0_ &= -9;
                this.lockTime_ = 0;
                onChanged();
                return this;
            }

            public boolean hasUpdatedAt() {
                return (this.bitField0_ & 16) == 16;
            }

            public long getUpdatedAt() {
                return this.updatedAt_;
            }

            public Builder setUpdatedAt(long j) {
                this.bitField0_ |= 16;
                this.updatedAt_ = j;
                onChanged();
                return this;
            }

            public Builder clearUpdatedAt() {
                this.bitField0_ &= -17;
                this.updatedAt_ = 0;
                onChanged();
                return this;
            }

            private void ensureTransactionInputIsMutable() {
                if ((this.bitField0_ & 32) != 32) {
                    this.transactionInput_ = new ArrayList(this.transactionInput_);
                    this.bitField0_ |= 32;
                }
            }

            public List<TransactionInput> getTransactionInputList() {
                RepeatedFieldBuilder<TransactionInput, Builder, TransactionInputOrBuilder> repeatedFieldBuilder = this.transactionInputBuilder_;
                if (repeatedFieldBuilder == null) {
                    return Collections.unmodifiableList(this.transactionInput_);
                }
                return repeatedFieldBuilder.getMessageList();
            }

            public int getTransactionInputCount() {
                RepeatedFieldBuilder<TransactionInput, Builder, TransactionInputOrBuilder> repeatedFieldBuilder = this.transactionInputBuilder_;
                if (repeatedFieldBuilder == null) {
                    return this.transactionInput_.size();
                }
                return repeatedFieldBuilder.getCount();
            }

            public TransactionInput getTransactionInput(int i) {
                RepeatedFieldBuilder<TransactionInput, Builder, TransactionInputOrBuilder> repeatedFieldBuilder = this.transactionInputBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (TransactionInput) this.transactionInput_.get(i);
                }
                return repeatedFieldBuilder.getMessage(i);
            }

            public Builder setTransactionInput(int i, TransactionInput transactionInput) {
                RepeatedFieldBuilder<TransactionInput, Builder, TransactionInputOrBuilder> repeatedFieldBuilder = this.transactionInputBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.setMessage(i, transactionInput);
                } else if (transactionInput != null) {
                    ensureTransactionInputIsMutable();
                    this.transactionInput_.set(i, transactionInput);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setTransactionInput(int i, Builder builder) {
                RepeatedFieldBuilder<TransactionInput, Builder, TransactionInputOrBuilder> repeatedFieldBuilder = this.transactionInputBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTransactionInputIsMutable();
                    this.transactionInput_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addTransactionInput(TransactionInput transactionInput) {
                RepeatedFieldBuilder<TransactionInput, Builder, TransactionInputOrBuilder> repeatedFieldBuilder = this.transactionInputBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(transactionInput);
                } else if (transactionInput != null) {
                    ensureTransactionInputIsMutable();
                    this.transactionInput_.add(transactionInput);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addTransactionInput(int i, TransactionInput transactionInput) {
                RepeatedFieldBuilder<TransactionInput, Builder, TransactionInputOrBuilder> repeatedFieldBuilder = this.transactionInputBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(i, transactionInput);
                } else if (transactionInput != null) {
                    ensureTransactionInputIsMutable();
                    this.transactionInput_.add(i, transactionInput);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addTransactionInput(Builder builder) {
                RepeatedFieldBuilder<TransactionInput, Builder, TransactionInputOrBuilder> repeatedFieldBuilder = this.transactionInputBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTransactionInputIsMutable();
                    this.transactionInput_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(builder.build());
                }
                return this;
            }

            public Builder addTransactionInput(int i, Builder builder) {
                RepeatedFieldBuilder<TransactionInput, Builder, TransactionInputOrBuilder> repeatedFieldBuilder = this.transactionInputBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTransactionInputIsMutable();
                    this.transactionInput_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllTransactionInput(Iterable<? extends TransactionInput> iterable) {
                RepeatedFieldBuilder<TransactionInput, Builder, TransactionInputOrBuilder> repeatedFieldBuilder = this.transactionInputBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTransactionInputIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.transactionInput_);
                    onChanged();
                } else {
                    repeatedFieldBuilder.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearTransactionInput() {
                RepeatedFieldBuilder<TransactionInput, Builder, TransactionInputOrBuilder> repeatedFieldBuilder = this.transactionInputBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.transactionInput_ = Collections.emptyList();
                    this.bitField0_ &= -33;
                    onChanged();
                } else {
                    repeatedFieldBuilder.clear();
                }
                return this;
            }

            public Builder removeTransactionInput(int i) {
                RepeatedFieldBuilder<TransactionInput, Builder, TransactionInputOrBuilder> repeatedFieldBuilder = this.transactionInputBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTransactionInputIsMutable();
                    this.transactionInput_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilder.remove(i);
                }
                return this;
            }

            public Builder getTransactionInputBuilder(int i) {
                return getTransactionInputFieldBuilder().getBuilder(i);
            }

            public TransactionInputOrBuilder getTransactionInputOrBuilder(int i) {
                RepeatedFieldBuilder<TransactionInput, Builder, TransactionInputOrBuilder> repeatedFieldBuilder = this.transactionInputBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (TransactionInputOrBuilder) this.transactionInput_.get(i);
                }
                return repeatedFieldBuilder.getMessageOrBuilder(i);
            }

            public List<? extends TransactionInputOrBuilder> getTransactionInputOrBuilderList() {
                RepeatedFieldBuilder<TransactionInput, Builder, TransactionInputOrBuilder> repeatedFieldBuilder = this.transactionInputBuilder_;
                if (repeatedFieldBuilder != null) {
                    return repeatedFieldBuilder.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.transactionInput_);
            }

            public Builder addTransactionInputBuilder() {
                return getTransactionInputFieldBuilder().addBuilder(TransactionInput.getDefaultInstance());
            }

            public Builder addTransactionInputBuilder(int i) {
                return getTransactionInputFieldBuilder().addBuilder(i, TransactionInput.getDefaultInstance());
            }

            public List<Builder> getTransactionInputBuilderList() {
                return getTransactionInputFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<TransactionInput, Builder, TransactionInputOrBuilder> getTransactionInputFieldBuilder() {
                if (this.transactionInputBuilder_ == null) {
                    this.transactionInputBuilder_ = new RepeatedFieldBuilder<>(this.transactionInput_, (this.bitField0_ & 32) == 32, getParentForChildren(), isClean());
                    this.transactionInput_ = null;
                }
                return this.transactionInputBuilder_;
            }

            private void ensureTransactionOutputIsMutable() {
                if ((this.bitField0_ & 64) != 64) {
                    this.transactionOutput_ = new ArrayList(this.transactionOutput_);
                    this.bitField0_ |= 64;
                }
            }

            public List<TransactionOutput> getTransactionOutputList() {
                RepeatedFieldBuilder<TransactionOutput, Builder, TransactionOutputOrBuilder> repeatedFieldBuilder = this.transactionOutputBuilder_;
                if (repeatedFieldBuilder == null) {
                    return Collections.unmodifiableList(this.transactionOutput_);
                }
                return repeatedFieldBuilder.getMessageList();
            }

            public int getTransactionOutputCount() {
                RepeatedFieldBuilder<TransactionOutput, Builder, TransactionOutputOrBuilder> repeatedFieldBuilder = this.transactionOutputBuilder_;
                if (repeatedFieldBuilder == null) {
                    return this.transactionOutput_.size();
                }
                return repeatedFieldBuilder.getCount();
            }

            public TransactionOutput getTransactionOutput(int i) {
                RepeatedFieldBuilder<TransactionOutput, Builder, TransactionOutputOrBuilder> repeatedFieldBuilder = this.transactionOutputBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (TransactionOutput) this.transactionOutput_.get(i);
                }
                return repeatedFieldBuilder.getMessage(i);
            }

            public Builder setTransactionOutput(int i, TransactionOutput transactionOutput) {
                RepeatedFieldBuilder<TransactionOutput, Builder, TransactionOutputOrBuilder> repeatedFieldBuilder = this.transactionOutputBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.setMessage(i, transactionOutput);
                } else if (transactionOutput != null) {
                    ensureTransactionOutputIsMutable();
                    this.transactionOutput_.set(i, transactionOutput);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setTransactionOutput(int i, Builder builder) {
                RepeatedFieldBuilder<TransactionOutput, Builder, TransactionOutputOrBuilder> repeatedFieldBuilder = this.transactionOutputBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTransactionOutputIsMutable();
                    this.transactionOutput_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addTransactionOutput(TransactionOutput transactionOutput) {
                RepeatedFieldBuilder<TransactionOutput, Builder, TransactionOutputOrBuilder> repeatedFieldBuilder = this.transactionOutputBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(transactionOutput);
                } else if (transactionOutput != null) {
                    ensureTransactionOutputIsMutable();
                    this.transactionOutput_.add(transactionOutput);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addTransactionOutput(int i, TransactionOutput transactionOutput) {
                RepeatedFieldBuilder<TransactionOutput, Builder, TransactionOutputOrBuilder> repeatedFieldBuilder = this.transactionOutputBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(i, transactionOutput);
                } else if (transactionOutput != null) {
                    ensureTransactionOutputIsMutable();
                    this.transactionOutput_.add(i, transactionOutput);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addTransactionOutput(Builder builder) {
                RepeatedFieldBuilder<TransactionOutput, Builder, TransactionOutputOrBuilder> repeatedFieldBuilder = this.transactionOutputBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTransactionOutputIsMutable();
                    this.transactionOutput_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(builder.build());
                }
                return this;
            }

            public Builder addTransactionOutput(int i, Builder builder) {
                RepeatedFieldBuilder<TransactionOutput, Builder, TransactionOutputOrBuilder> repeatedFieldBuilder = this.transactionOutputBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTransactionOutputIsMutable();
                    this.transactionOutput_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllTransactionOutput(Iterable<? extends TransactionOutput> iterable) {
                RepeatedFieldBuilder<TransactionOutput, Builder, TransactionOutputOrBuilder> repeatedFieldBuilder = this.transactionOutputBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTransactionOutputIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.transactionOutput_);
                    onChanged();
                } else {
                    repeatedFieldBuilder.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearTransactionOutput() {
                RepeatedFieldBuilder<TransactionOutput, Builder, TransactionOutputOrBuilder> repeatedFieldBuilder = this.transactionOutputBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.transactionOutput_ = Collections.emptyList();
                    this.bitField0_ &= -65;
                    onChanged();
                } else {
                    repeatedFieldBuilder.clear();
                }
                return this;
            }

            public Builder removeTransactionOutput(int i) {
                RepeatedFieldBuilder<TransactionOutput, Builder, TransactionOutputOrBuilder> repeatedFieldBuilder = this.transactionOutputBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTransactionOutputIsMutable();
                    this.transactionOutput_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilder.remove(i);
                }
                return this;
            }

            public Builder getTransactionOutputBuilder(int i) {
                return getTransactionOutputFieldBuilder().getBuilder(i);
            }

            public TransactionOutputOrBuilder getTransactionOutputOrBuilder(int i) {
                RepeatedFieldBuilder<TransactionOutput, Builder, TransactionOutputOrBuilder> repeatedFieldBuilder = this.transactionOutputBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (TransactionOutputOrBuilder) this.transactionOutput_.get(i);
                }
                return repeatedFieldBuilder.getMessageOrBuilder(i);
            }

            public List<? extends TransactionOutputOrBuilder> getTransactionOutputOrBuilderList() {
                RepeatedFieldBuilder<TransactionOutput, Builder, TransactionOutputOrBuilder> repeatedFieldBuilder = this.transactionOutputBuilder_;
                if (repeatedFieldBuilder != null) {
                    return repeatedFieldBuilder.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.transactionOutput_);
            }

            public Builder addTransactionOutputBuilder() {
                return getTransactionOutputFieldBuilder().addBuilder(TransactionOutput.getDefaultInstance());
            }

            public Builder addTransactionOutputBuilder(int i) {
                return getTransactionOutputFieldBuilder().addBuilder(i, TransactionOutput.getDefaultInstance());
            }

            public List<Builder> getTransactionOutputBuilderList() {
                return getTransactionOutputFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<TransactionOutput, Builder, TransactionOutputOrBuilder> getTransactionOutputFieldBuilder() {
                if (this.transactionOutputBuilder_ == null) {
                    this.transactionOutputBuilder_ = new RepeatedFieldBuilder<>(this.transactionOutput_, (this.bitField0_ & 64) == 64, getParentForChildren(), isClean());
                    this.transactionOutput_ = null;
                }
                return this.transactionOutputBuilder_;
            }

            private void ensureBlockHashIsMutable() {
                if ((this.bitField0_ & 128) != 128) {
                    this.blockHash_ = new ArrayList(this.blockHash_);
                    this.bitField0_ |= 128;
                }
            }

            public List<ByteString> getBlockHashList() {
                return Collections.unmodifiableList(this.blockHash_);
            }

            public int getBlockHashCount() {
                return this.blockHash_.size();
            }

            public ByteString getBlockHash(int i) {
                return (ByteString) this.blockHash_.get(i);
            }

            public Builder setBlockHash(int i, ByteString byteString) {
                if (byteString != null) {
                    ensureBlockHashIsMutable();
                    this.blockHash_.set(i, byteString);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addBlockHash(ByteString byteString) {
                if (byteString != null) {
                    ensureBlockHashIsMutable();
                    this.blockHash_.add(byteString);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addAllBlockHash(Iterable<? extends ByteString> iterable) {
                ensureBlockHashIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.blockHash_);
                onChanged();
                return this;
            }

            public Builder clearBlockHash() {
                this.blockHash_ = Collections.emptyList();
                this.bitField0_ &= -129;
                onChanged();
                return this;
            }

            private void ensureBlockRelativityOffsetsIsMutable() {
                if ((this.bitField0_ & 256) != 256) {
                    this.blockRelativityOffsets_ = new ArrayList(this.blockRelativityOffsets_);
                    this.bitField0_ |= 256;
                }
            }

            public List<Integer> getBlockRelativityOffsetsList() {
                return Collections.unmodifiableList(this.blockRelativityOffsets_);
            }

            public int getBlockRelativityOffsetsCount() {
                return this.blockRelativityOffsets_.size();
            }

            public int getBlockRelativityOffsets(int i) {
                return ((Integer) this.blockRelativityOffsets_.get(i)).intValue();
            }

            public Builder setBlockRelativityOffsets(int i, int i2) {
                ensureBlockRelativityOffsetsIsMutable();
                this.blockRelativityOffsets_.set(i, Integer.valueOf(i2));
                onChanged();
                return this;
            }

            public Builder addBlockRelativityOffsets(int i) {
                ensureBlockRelativityOffsetsIsMutable();
                this.blockRelativityOffsets_.add(Integer.valueOf(i));
                onChanged();
                return this;
            }

            public Builder addAllBlockRelativityOffsets(Iterable<? extends Integer> iterable) {
                ensureBlockRelativityOffsetsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.blockRelativityOffsets_);
                onChanged();
                return this;
            }

            public Builder clearBlockRelativityOffsets() {
                this.blockRelativityOffsets_ = Collections.emptyList();
                this.bitField0_ &= -257;
                onChanged();
                return this;
            }

            public boolean hasConfidence() {
                return (this.bitField0_ & 512) == 512;
            }

            public TransactionConfidence getConfidence() {
                SingleFieldBuilder<TransactionConfidence, Builder, TransactionConfidenceOrBuilder> singleFieldBuilder = this.confidenceBuilder_;
                if (singleFieldBuilder == null) {
                    return this.confidence_;
                }
                return singleFieldBuilder.getMessage();
            }

            public Builder setConfidence(TransactionConfidence transactionConfidence) {
                SingleFieldBuilder<TransactionConfidence, Builder, TransactionConfidenceOrBuilder> singleFieldBuilder = this.confidenceBuilder_;
                if (singleFieldBuilder != null) {
                    singleFieldBuilder.setMessage(transactionConfidence);
                } else if (transactionConfidence != null) {
                    this.confidence_ = transactionConfidence;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 512;
                return this;
            }

            public Builder setConfidence(Builder builder) {
                SingleFieldBuilder<TransactionConfidence, Builder, TransactionConfidenceOrBuilder> singleFieldBuilder = this.confidenceBuilder_;
                if (singleFieldBuilder == null) {
                    this.confidence_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builder.build());
                }
                this.bitField0_ |= 512;
                return this;
            }

            public Builder mergeConfidence(TransactionConfidence transactionConfidence) {
                SingleFieldBuilder<TransactionConfidence, Builder, TransactionConfidenceOrBuilder> singleFieldBuilder = this.confidenceBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 512) != 512 || this.confidence_ == TransactionConfidence.getDefaultInstance()) {
                        this.confidence_ = transactionConfidence;
                    } else {
                        this.confidence_ = TransactionConfidence.newBuilder(this.confidence_).mergeFrom(transactionConfidence).buildPartial();
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(transactionConfidence);
                }
                this.bitField0_ |= 512;
                return this;
            }

            public Builder clearConfidence() {
                SingleFieldBuilder<TransactionConfidence, Builder, TransactionConfidenceOrBuilder> singleFieldBuilder = this.confidenceBuilder_;
                if (singleFieldBuilder == null) {
                    this.confidence_ = TransactionConfidence.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -513;
                return this;
            }

            public Builder getConfidenceBuilder() {
                this.bitField0_ |= 512;
                onChanged();
                return getConfidenceFieldBuilder().getBuilder();
            }

            public TransactionConfidenceOrBuilder getConfidenceOrBuilder() {
                SingleFieldBuilder<TransactionConfidence, Builder, TransactionConfidenceOrBuilder> singleFieldBuilder = this.confidenceBuilder_;
                if (singleFieldBuilder != null) {
                    return singleFieldBuilder.getMessageOrBuilder();
                }
                return this.confidence_;
            }

            private SingleFieldBuilder<TransactionConfidence, Builder, TransactionConfidenceOrBuilder> getConfidenceFieldBuilder() {
                if (this.confidenceBuilder_ == null) {
                    this.confidenceBuilder_ = new SingleFieldBuilder<>(getConfidence(), getParentForChildren(), isClean());
                    this.confidence_ = null;
                }
                return this.confidenceBuilder_;
            }

            public boolean hasPurpose() {
                return (this.bitField0_ & 1024) == 1024;
            }

            public Purpose getPurpose() {
                return this.purpose_;
            }

            public Builder setPurpose(Purpose purpose) {
                if (purpose != null) {
                    this.bitField0_ |= 1024;
                    this.purpose_ = purpose;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearPurpose() {
                this.bitField0_ &= RESULT.E_TEEKM_JSON_OUT_ADDR_LEN;
                this.purpose_ = Purpose.UNKNOWN;
                onChanged();
                return this;
            }

            public boolean hasExchangeRate() {
                return (this.bitField0_ & 2048) == 2048;
            }

            public ExchangeRate getExchangeRate() {
                SingleFieldBuilder<ExchangeRate, Builder, ExchangeRateOrBuilder> singleFieldBuilder = this.exchangeRateBuilder_;
                if (singleFieldBuilder == null) {
                    return this.exchangeRate_;
                }
                return singleFieldBuilder.getMessage();
            }

            public Builder setExchangeRate(ExchangeRate exchangeRate) {
                SingleFieldBuilder<ExchangeRate, Builder, ExchangeRateOrBuilder> singleFieldBuilder = this.exchangeRateBuilder_;
                if (singleFieldBuilder != null) {
                    singleFieldBuilder.setMessage(exchangeRate);
                } else if (exchangeRate != null) {
                    this.exchangeRate_ = exchangeRate;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2048;
                return this;
            }

            public Builder setExchangeRate(Builder builder) {
                SingleFieldBuilder<ExchangeRate, Builder, ExchangeRateOrBuilder> singleFieldBuilder = this.exchangeRateBuilder_;
                if (singleFieldBuilder == null) {
                    this.exchangeRate_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builder.build());
                }
                this.bitField0_ |= 2048;
                return this;
            }

            public Builder mergeExchangeRate(ExchangeRate exchangeRate) {
                SingleFieldBuilder<ExchangeRate, Builder, ExchangeRateOrBuilder> singleFieldBuilder = this.exchangeRateBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 2048) != 2048 || this.exchangeRate_ == ExchangeRate.getDefaultInstance()) {
                        this.exchangeRate_ = exchangeRate;
                    } else {
                        this.exchangeRate_ = ExchangeRate.newBuilder(this.exchangeRate_).mergeFrom(exchangeRate).buildPartial();
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(exchangeRate);
                }
                this.bitField0_ |= 2048;
                return this;
            }

            public Builder clearExchangeRate() {
                SingleFieldBuilder<ExchangeRate, Builder, ExchangeRateOrBuilder> singleFieldBuilder = this.exchangeRateBuilder_;
                if (singleFieldBuilder == null) {
                    this.exchangeRate_ = ExchangeRate.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -2049;
                return this;
            }

            public Builder getExchangeRateBuilder() {
                this.bitField0_ |= 2048;
                onChanged();
                return getExchangeRateFieldBuilder().getBuilder();
            }

            public ExchangeRateOrBuilder getExchangeRateOrBuilder() {
                SingleFieldBuilder<ExchangeRate, Builder, ExchangeRateOrBuilder> singleFieldBuilder = this.exchangeRateBuilder_;
                if (singleFieldBuilder != null) {
                    return singleFieldBuilder.getMessageOrBuilder();
                }
                return this.exchangeRate_;
            }

            private SingleFieldBuilder<ExchangeRate, Builder, ExchangeRateOrBuilder> getExchangeRateFieldBuilder() {
                if (this.exchangeRateBuilder_ == null) {
                    this.exchangeRateBuilder_ = new SingleFieldBuilder<>(getExchangeRate(), getParentForChildren(), isClean());
                    this.exchangeRate_ = null;
                }
                return this.exchangeRateBuilder_;
            }

            public boolean hasMemo() {
                return (this.bitField0_ & 4096) == 4096;
            }

            public String getMemo() {
                Object obj = this.memo_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.memo_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getMemoBytes() {
                Object obj = this.memo_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.memo_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setMemo(String str) {
                if (str != null) {
                    this.bitField0_ |= 4096;
                    this.memo_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearMemo() {
                this.bitField0_ &= -4097;
                this.memo_ = Transaction.getDefaultInstance().getMemo();
                onChanged();
                return this;
            }

            public Builder setMemoBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 4096;
                    this.memo_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }
        }

        public enum Pool implements ProtocolMessageEnum {
            UNSPENT(0, 4),
            SPENT(1, 5),
            INACTIVE(2, 2),
            DEAD(3, 10),
            PENDING(4, 16),
            PENDING_INACTIVE(5, 18);
            
            public static final int DEAD_VALUE = 10;
            public static final int INACTIVE_VALUE = 2;
            public static final int PENDING_INACTIVE_VALUE = 18;
            public static final int PENDING_VALUE = 16;
            public static final int SPENT_VALUE = 5;
            public static final int UNSPENT_VALUE = 4;
            private static final Pool[] VALUES = null;
            private static EnumLiteMap<Pool> internalValueMap;
            private final int index;
            private final int value;

            static {
                internalValueMap = new EnumLiteMap<Pool>() {
                    public Pool findValueByNumber(int i) {
                        return Pool.valueOf(i);
                    }
                };
                VALUES = values();
            }

            public final int getNumber() {
                return this.value;
            }

            public static Pool valueOf(int i) {
                if (i == 2) {
                    return INACTIVE;
                }
                if (i == 10) {
                    return DEAD;
                }
                if (i == 16) {
                    return PENDING;
                }
                if (i == 18) {
                    return PENDING_INACTIVE;
                }
                if (i == 4) {
                    return UNSPENT;
                }
                if (i != 5) {
                    return null;
                }
                return SPENT;
            }

            public static EnumLiteMap<Pool> internalGetValueMap() {
                return internalValueMap;
            }

            public final EnumValueDescriptor getValueDescriptor() {
                return (EnumValueDescriptor) getDescriptor().getValues().get(this.index);
            }

            public final EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final EnumDescriptor getDescriptor() {
                return (EnumDescriptor) Transaction.getDescriptor().getEnumTypes().get(0);
            }

            public static Pool valueOf(EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            private Pool(int i, int i2) {
                this.index = i;
                this.value = i2;
            }
        }

        public enum Purpose implements ProtocolMessageEnum {
            UNKNOWN(0, 0),
            USER_PAYMENT(1, 1),
            KEY_ROTATION(2, 2),
            ASSURANCE_CONTRACT_CLAIM(3, 3),
            ASSURANCE_CONTRACT_PLEDGE(4, 4),
            ASSURANCE_CONTRACT_STUB(5, 5),
            RAISE_FEE(6, 6);
            
            public static final int ASSURANCE_CONTRACT_CLAIM_VALUE = 3;
            public static final int ASSURANCE_CONTRACT_PLEDGE_VALUE = 4;
            public static final int ASSURANCE_CONTRACT_STUB_VALUE = 5;
            public static final int KEY_ROTATION_VALUE = 2;
            public static final int RAISE_FEE_VALUE = 6;
            public static final int UNKNOWN_VALUE = 0;
            public static final int USER_PAYMENT_VALUE = 1;
            private static final Purpose[] VALUES = null;
            private static EnumLiteMap<Purpose> internalValueMap;
            private final int index;
            private final int value;

            static {
                internalValueMap = new EnumLiteMap<Purpose>() {
                    public Purpose findValueByNumber(int i) {
                        return Purpose.valueOf(i);
                    }
                };
                VALUES = values();
            }

            public final int getNumber() {
                return this.value;
            }

            public static Purpose valueOf(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN;
                    case 1:
                        return USER_PAYMENT;
                    case 2:
                        return KEY_ROTATION;
                    case 3:
                        return ASSURANCE_CONTRACT_CLAIM;
                    case 4:
                        return ASSURANCE_CONTRACT_PLEDGE;
                    case 5:
                        return ASSURANCE_CONTRACT_STUB;
                    case 6:
                        return RAISE_FEE;
                    default:
                        return null;
                }
            }

            public static EnumLiteMap<Purpose> internalGetValueMap() {
                return internalValueMap;
            }

            public final EnumValueDescriptor getValueDescriptor() {
                return (EnumValueDescriptor) getDescriptor().getValues().get(this.index);
            }

            public final EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final EnumDescriptor getDescriptor() {
                return (EnumDescriptor) Transaction.getDescriptor().getEnumTypes().get(1);
            }

            public static Purpose valueOf(EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            private Purpose(int i, int i2) {
                this.index = i;
                this.value = i2;
            }
        }

        private Transaction(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Transaction(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Transaction getDefaultInstance() {
            return defaultInstance;
        }

        public Transaction getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r10v0, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$Transaction] */
        /* JADX WARNING: type inference failed for: r8v0 */
        /* JADX WARNING: type inference failed for: r8v8, types: [org.bitcoinj.wallet.Protos$TransactionConfidence$Builder] */
        /* JADX WARNING: type inference failed for: r8v9, types: [org.bitcoinj.wallet.Protos$TransactionConfidence$Builder] */
        /* JADX WARNING: type inference failed for: r8v19, types: [org.bitcoinj.wallet.Protos$ExchangeRate$Builder] */
        /* JADX WARNING: type inference failed for: r8v20, types: [org.bitcoinj.wallet.Protos$ExchangeRate$Builder] */
        /* JADX WARNING: type inference failed for: r8v23 */
        /* JADX WARNING: type inference failed for: r8v24 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r8v0
          assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], org.bitcoinj.wallet.Protos$ExchangeRate$Builder, org.bitcoinj.wallet.Protos$TransactionConfidence$Builder]
          uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], org.bitcoinj.wallet.Protos$TransactionConfidence$Builder, org.bitcoinj.wallet.Protos$ExchangeRate$Builder]
          mth insns count: 215
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private Transaction(com.google.protobuf.CodedInputStream r11, com.google.protobuf.ExtensionRegistryLite r12) throws com.google.protobuf.InvalidProtocolBufferException {
            /*
                r10 = this;
                r10.<init>()
                r0 = -1
                r10.memoizedIsInitialized = r0
                r10.memoizedSerializedSize = r0
                r10.initFields()
                com.google.protobuf.UnknownFieldSet$Builder r0 = com.google.protobuf.UnknownFieldSet.newBuilder()
                r1 = 0
                r2 = 0
            L_0x0011:
                r3 = 64
                r4 = 128(0x80, float:1.794E-43)
                r5 = 32
                r6 = 256(0x100, float:3.59E-43)
                if (r1 != 0) goto L_0x01e4
                int r7 = r11.readTag()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r8 = 0
                r9 = 1
                switch(r7) {
                    case 0: goto L_0x018d;
                    case 8: goto L_0x0180;
                    case 18: goto L_0x0172;
                    case 24: goto L_0x0158;
                    case 32: goto L_0x014a;
                    case 40: goto L_0x013c;
                    case 50: goto L_0x0122;
                    case 58: goto L_0x0108;
                    case 66: goto L_0x00f0;
                    case 74: goto L_0x00c7;
                    case 80: goto L_0x00ad;
                    case 88: goto L_0x0091;
                    case 90: goto L_0x005e;
                    case 98: goto L_0x0036;
                    case 106: goto L_0x002a;
                    default: goto L_0x0024;
                }     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
            L_0x0024:
                boolean r3 = r10.parseUnknownField(r11, r0, r12, r7)     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                goto L_0x0190
            L_0x002a:
                com.google.protobuf.ByteString r7 = r11.readBytes()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                int r8 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r8 = r8 | r6
                r10.bitField0_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r10.memo_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                goto L_0x0011
            L_0x0036:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r7 = r7 & r4
                if (r7 != r4) goto L_0x0041
                org.bitcoinj.wallet.Protos$ExchangeRate r7 = r10.exchangeRate_     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                org.bitcoinj.wallet.Protos$ExchangeRate$Builder r8 = r7.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
            L_0x0041:
                com.google.protobuf.Parser<org.bitcoinj.wallet.Protos$ExchangeRate> r7 = org.bitcoinj.wallet.Protos.ExchangeRate.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                com.google.protobuf.MessageLite r7 = r11.readMessage(r7, r12)     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                org.bitcoinj.wallet.Protos$ExchangeRate r7 = (org.bitcoinj.wallet.Protos.ExchangeRate) r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r10.exchangeRate_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                if (r8 == 0) goto L_0x0058
                org.bitcoinj.wallet.Protos$ExchangeRate r7 = r10.exchangeRate_     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r8.mergeFrom(r7)     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                org.bitcoinj.wallet.Protos$ExchangeRate r7 = r8.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r10.exchangeRate_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
            L_0x0058:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r7 = r7 | r4
                r10.bitField0_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                goto L_0x0011
            L_0x005e:
                int r7 = r11.readRawVarint32()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                int r7 = r11.pushLimit(r7)     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r8 = r2 & 256(0x100, float:3.59E-43)
                if (r8 == r6) goto L_0x0079
                int r8 = r11.getBytesUntilLimit()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                if (r8 <= 0) goto L_0x0079
                java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r8.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r10.blockRelativityOffsets_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r2 = r2 | 256(0x100, float:3.59E-43)
            L_0x0079:
                int r8 = r11.getBytesUntilLimit()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                if (r8 <= 0) goto L_0x008d
                java.util.List<java.lang.Integer> r8 = r10.blockRelativityOffsets_     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                int r9 = r11.readInt32()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r8.add(r9)     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                goto L_0x0079
            L_0x008d:
                r11.popLimit(r7)     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                goto L_0x0011
            L_0x0091:
                r7 = r2 & 256(0x100, float:3.59E-43)
                if (r7 == r6) goto L_0x009e
                java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r7.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r10.blockRelativityOffsets_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r2 = r2 | 256(0x100, float:3.59E-43)
            L_0x009e:
                java.util.List<java.lang.Integer> r7 = r10.blockRelativityOffsets_     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                int r8 = r11.readInt32()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r7.add(r8)     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                goto L_0x0011
            L_0x00ad:
                int r7 = r11.readEnum()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                org.bitcoinj.wallet.Protos$Transaction$Purpose r8 = org.bitcoinj.wallet.Protos.Transaction.Purpose.valueOf(r7)     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                if (r8 != 0) goto L_0x00be
                r8 = 10
                r0.mergeVarintField(r8, r7)     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                goto L_0x0011
            L_0x00be:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r7 = r7 | r3
                r10.bitField0_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r10.purpose_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                goto L_0x0011
            L_0x00c7:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r7 = r7 & r5
                if (r7 != r5) goto L_0x00d2
                org.bitcoinj.wallet.Protos$TransactionConfidence r7 = r10.confidence_     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                org.bitcoinj.wallet.Protos$TransactionConfidence$Builder r8 = r7.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
            L_0x00d2:
                com.google.protobuf.Parser<org.bitcoinj.wallet.Protos$TransactionConfidence> r7 = org.bitcoinj.wallet.Protos.TransactionConfidence.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                com.google.protobuf.MessageLite r7 = r11.readMessage(r7, r12)     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                org.bitcoinj.wallet.Protos$TransactionConfidence r7 = (org.bitcoinj.wallet.Protos.TransactionConfidence) r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r10.confidence_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                if (r8 == 0) goto L_0x00e9
                org.bitcoinj.wallet.Protos$TransactionConfidence r7 = r10.confidence_     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r8.mergeFrom(r7)     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                org.bitcoinj.wallet.Protos$TransactionConfidence r7 = r8.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r10.confidence_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
            L_0x00e9:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r7 = r7 | r5
                r10.bitField0_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                goto L_0x0011
            L_0x00f0:
                r7 = r2 & 128(0x80, float:1.794E-43)
                if (r7 == r4) goto L_0x00fd
                java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r7.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r10.blockHash_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r2 = r2 | 128(0x80, float:1.794E-43)
            L_0x00fd:
                java.util.List<com.google.protobuf.ByteString> r7 = r10.blockHash_     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                com.google.protobuf.ByteString r8 = r11.readBytes()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r7.add(r8)     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                goto L_0x0011
            L_0x0108:
                r7 = r2 & 64
                if (r7 == r3) goto L_0x0115
                java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r7.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r10.transactionOutput_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r2 = r2 | 64
            L_0x0115:
                java.util.List<org.bitcoinj.wallet.Protos$TransactionOutput> r7 = r10.transactionOutput_     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                com.google.protobuf.Parser<org.bitcoinj.wallet.Protos$TransactionOutput> r8 = org.bitcoinj.wallet.Protos.TransactionOutput.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                com.google.protobuf.MessageLite r8 = r11.readMessage(r8, r12)     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r7.add(r8)     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                goto L_0x0011
            L_0x0122:
                r7 = r2 & 32
                if (r7 == r5) goto L_0x012f
                java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r7.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r10.transactionInput_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r2 = r2 | 32
            L_0x012f:
                java.util.List<org.bitcoinj.wallet.Protos$TransactionInput> r7 = r10.transactionInput_     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                com.google.protobuf.Parser<org.bitcoinj.wallet.Protos$TransactionInput> r8 = org.bitcoinj.wallet.Protos.TransactionInput.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                com.google.protobuf.MessageLite r8 = r11.readMessage(r8, r12)     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r7.add(r8)     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                goto L_0x0011
            L_0x013c:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r7 = r7 | 16
                r10.bitField0_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                long r7 = r11.readInt64()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r10.updatedAt_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                goto L_0x0011
            L_0x014a:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r7 = r7 | 8
                r10.bitField0_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                int r7 = r11.readUInt32()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r10.lockTime_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                goto L_0x0011
            L_0x0158:
                int r7 = r11.readEnum()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                org.bitcoinj.wallet.Protos$Transaction$Pool r8 = org.bitcoinj.wallet.Protos.Transaction.Pool.valueOf(r7)     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                if (r8 != 0) goto L_0x0168
                r8 = 3
                r0.mergeVarintField(r8, r7)     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                goto L_0x0011
            L_0x0168:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r7 = r7 | 4
                r10.bitField0_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r10.pool_ = r8     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                goto L_0x0011
            L_0x0172:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r7 = r7 | 2
                r10.bitField0_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                com.google.protobuf.ByteString r7 = r11.readBytes()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r10.hash_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                goto L_0x0011
            L_0x0180:
                int r7 = r10.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r7 = r7 | r9
                r10.bitField0_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                int r7 = r11.readInt32()     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                r10.version_ = r7     // Catch:{ InvalidProtocolBufferException -> 0x01a4, IOException -> 0x0195 }
                goto L_0x0011
            L_0x018d:
                r1 = 1
                goto L_0x0011
            L_0x0190:
                if (r3 != 0) goto L_0x0011
                goto L_0x018d
            L_0x0193:
                r11 = move-exception
                goto L_0x01aa
            L_0x0195:
                r11 = move-exception
                com.google.protobuf.InvalidProtocolBufferException r12 = new com.google.protobuf.InvalidProtocolBufferException     // Catch:{ all -> 0x0193 }
                java.lang.String r11 = r11.getMessage()     // Catch:{ all -> 0x0193 }
                r12.<init>(r11)     // Catch:{ all -> 0x0193 }
                com.google.protobuf.InvalidProtocolBufferException r11 = r12.setUnfinishedMessage(r10)     // Catch:{ all -> 0x0193 }
                throw r11     // Catch:{ all -> 0x0193 }
            L_0x01a4:
                r11 = move-exception
                com.google.protobuf.InvalidProtocolBufferException r11 = r11.setUnfinishedMessage(r10)     // Catch:{ all -> 0x0193 }
                throw r11     // Catch:{ all -> 0x0193 }
            L_0x01aa:
                r12 = r2 & 32
                if (r12 != r5) goto L_0x01b6
                java.util.List<org.bitcoinj.wallet.Protos$TransactionInput> r12 = r10.transactionInput_
                java.util.List r12 = java.util.Collections.unmodifiableList(r12)
                r10.transactionInput_ = r12
            L_0x01b6:
                r12 = r2 & 64
                if (r12 != r3) goto L_0x01c2
                java.util.List<org.bitcoinj.wallet.Protos$TransactionOutput> r12 = r10.transactionOutput_
                java.util.List r12 = java.util.Collections.unmodifiableList(r12)
                r10.transactionOutput_ = r12
            L_0x01c2:
                r12 = r2 & 128(0x80, float:1.794E-43)
                if (r12 != r4) goto L_0x01ce
                java.util.List<com.google.protobuf.ByteString> r12 = r10.blockHash_
                java.util.List r12 = java.util.Collections.unmodifiableList(r12)
                r10.blockHash_ = r12
            L_0x01ce:
                r12 = r2 & 256(0x100, float:3.59E-43)
                if (r12 != r6) goto L_0x01da
                java.util.List<java.lang.Integer> r12 = r10.blockRelativityOffsets_
                java.util.List r12 = java.util.Collections.unmodifiableList(r12)
                r10.blockRelativityOffsets_ = r12
            L_0x01da:
                com.google.protobuf.UnknownFieldSet r12 = r0.build()
                r10.unknownFields = r12
                r10.makeExtensionsImmutable()
                throw r11
            L_0x01e4:
                r11 = r2 & 32
                if (r11 != r5) goto L_0x01f0
                java.util.List<org.bitcoinj.wallet.Protos$TransactionInput> r11 = r10.transactionInput_
                java.util.List r11 = java.util.Collections.unmodifiableList(r11)
                r10.transactionInput_ = r11
            L_0x01f0:
                r11 = r2 & 64
                if (r11 != r3) goto L_0x01fc
                java.util.List<org.bitcoinj.wallet.Protos$TransactionOutput> r11 = r10.transactionOutput_
                java.util.List r11 = java.util.Collections.unmodifiableList(r11)
                r10.transactionOutput_ = r11
            L_0x01fc:
                r11 = r2 & 128(0x80, float:1.794E-43)
                if (r11 != r4) goto L_0x0208
                java.util.List<com.google.protobuf.ByteString> r11 = r10.blockHash_
                java.util.List r11 = java.util.Collections.unmodifiableList(r11)
                r10.blockHash_ = r11
            L_0x0208:
                r11 = r2 & 256(0x100, float:3.59E-43)
                if (r11 != r6) goto L_0x0214
                java.util.List<java.lang.Integer> r11 = r10.blockRelativityOffsets_
                java.util.List r11 = java.util.Collections.unmodifiableList(r11)
                r10.blockRelativityOffsets_ = r11
            L_0x0214:
                com.google.protobuf.UnknownFieldSet r11 = r0.build()
                r10.unknownFields = r11
                r10.makeExtensionsImmutable()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.wallet.Protos.Transaction.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
        }

        public static final Descriptor getDescriptor() {
            return Protos.internal_static_wallet_Transaction_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_wallet_Transaction_fieldAccessorTable.ensureFieldAccessorsInitialized(Transaction.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<Transaction> getParserForType() {
            return PARSER;
        }

        public boolean hasVersion() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getVersion() {
            return this.version_;
        }

        public boolean hasHash() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getHash() {
            return this.hash_;
        }

        public boolean hasPool() {
            return (this.bitField0_ & 4) == 4;
        }

        public Pool getPool() {
            return this.pool_;
        }

        public boolean hasLockTime() {
            return (this.bitField0_ & 8) == 8;
        }

        public int getLockTime() {
            return this.lockTime_;
        }

        public boolean hasUpdatedAt() {
            return (this.bitField0_ & 16) == 16;
        }

        public long getUpdatedAt() {
            return this.updatedAt_;
        }

        public List<TransactionInput> getTransactionInputList() {
            return this.transactionInput_;
        }

        public List<? extends TransactionInputOrBuilder> getTransactionInputOrBuilderList() {
            return this.transactionInput_;
        }

        public int getTransactionInputCount() {
            return this.transactionInput_.size();
        }

        public TransactionInput getTransactionInput(int i) {
            return (TransactionInput) this.transactionInput_.get(i);
        }

        public TransactionInputOrBuilder getTransactionInputOrBuilder(int i) {
            return (TransactionInputOrBuilder) this.transactionInput_.get(i);
        }

        public List<TransactionOutput> getTransactionOutputList() {
            return this.transactionOutput_;
        }

        public List<? extends TransactionOutputOrBuilder> getTransactionOutputOrBuilderList() {
            return this.transactionOutput_;
        }

        public int getTransactionOutputCount() {
            return this.transactionOutput_.size();
        }

        public TransactionOutput getTransactionOutput(int i) {
            return (TransactionOutput) this.transactionOutput_.get(i);
        }

        public TransactionOutputOrBuilder getTransactionOutputOrBuilder(int i) {
            return (TransactionOutputOrBuilder) this.transactionOutput_.get(i);
        }

        public List<ByteString> getBlockHashList() {
            return this.blockHash_;
        }

        public int getBlockHashCount() {
            return this.blockHash_.size();
        }

        public ByteString getBlockHash(int i) {
            return (ByteString) this.blockHash_.get(i);
        }

        public List<Integer> getBlockRelativityOffsetsList() {
            return this.blockRelativityOffsets_;
        }

        public int getBlockRelativityOffsetsCount() {
            return this.blockRelativityOffsets_.size();
        }

        public int getBlockRelativityOffsets(int i) {
            return ((Integer) this.blockRelativityOffsets_.get(i)).intValue();
        }

        public boolean hasConfidence() {
            return (this.bitField0_ & 32) == 32;
        }

        public TransactionConfidence getConfidence() {
            return this.confidence_;
        }

        public TransactionConfidenceOrBuilder getConfidenceOrBuilder() {
            return this.confidence_;
        }

        public boolean hasPurpose() {
            return (this.bitField0_ & 64) == 64;
        }

        public Purpose getPurpose() {
            return this.purpose_;
        }

        public boolean hasExchangeRate() {
            return (this.bitField0_ & 128) == 128;
        }

        public ExchangeRate getExchangeRate() {
            return this.exchangeRate_;
        }

        public ExchangeRateOrBuilder getExchangeRateOrBuilder() {
            return this.exchangeRate_;
        }

        public boolean hasMemo() {
            return (this.bitField0_ & 256) == 256;
        }

        public String getMemo() {
            Object obj = this.memo_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.memo_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getMemoBytes() {
            Object obj = this.memo_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.memo_ = copyFromUtf8;
            return copyFromUtf8;
        }

        private void initFields() {
            this.version_ = 0;
            this.hash_ = ByteString.EMPTY;
            this.pool_ = Pool.UNSPENT;
            this.lockTime_ = 0;
            this.updatedAt_ = 0;
            this.transactionInput_ = Collections.emptyList();
            this.transactionOutput_ = Collections.emptyList();
            this.blockHash_ = Collections.emptyList();
            this.blockRelativityOffsets_ = Collections.emptyList();
            this.confidence_ = TransactionConfidence.getDefaultInstance();
            this.purpose_ = Purpose.UNKNOWN;
            this.exchangeRate_ = ExchangeRate.getDefaultInstance();
            this.memo_ = "";
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasVersion()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasHash()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else {
                for (int i = 0; i < getTransactionInputCount(); i++) {
                    if (!getTransactionInput(i).isInitialized()) {
                        this.memoizedIsInitialized = 0;
                        return false;
                    }
                }
                for (int i2 = 0; i2 < getTransactionOutputCount(); i2++) {
                    if (!getTransactionOutput(i2).isInitialized()) {
                        this.memoizedIsInitialized = 0;
                        return false;
                    }
                }
                if (hasConfidence() && !getConfidence().isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                } else if (!hasExchangeRate() || getExchangeRate().isInitialized()) {
                    this.memoizedIsInitialized = 1;
                    return true;
                } else {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
        }

        /* JADX WARNING: type inference failed for: r1v19, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$ExchangeRate] */
        /* JADX WARNING: type inference failed for: r2v9, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$TransactionConfidence] */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v19, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$ExchangeRate]
          assigns: [org.bitcoinj.wallet.Protos$ExchangeRate]
          uses: [com.google.protobuf.MessageLite]
          mth insns count: 88
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 2 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void writeTo(com.google.protobuf.CodedOutputStream r6) throws java.io.IOException {
            /*
                r5 = this;
                r5.getSerializedSize()
                int r0 = r5.bitField0_
                r1 = 1
                r0 = r0 & r1
                if (r0 != r1) goto L_0x000e
                int r0 = r5.version_
                r6.writeInt32(r1, r0)
            L_0x000e:
                int r0 = r5.bitField0_
                r1 = 2
                r0 = r0 & r1
                if (r0 != r1) goto L_0x0019
                com.google.protobuf.ByteString r0 = r5.hash_
                r6.writeBytes(r1, r0)
            L_0x0019:
                int r0 = r5.bitField0_
                r1 = 4
                r0 = r0 & r1
                if (r0 != r1) goto L_0x0029
                r0 = 3
                org.bitcoinj.wallet.Protos$Transaction$Pool r2 = r5.pool_
                int r2 = r2.getNumber()
                r6.writeEnum(r0, r2)
            L_0x0029:
                int r0 = r5.bitField0_
                r2 = 8
                r0 = r0 & r2
                if (r0 != r2) goto L_0x0035
                int r0 = r5.lockTime_
                r6.writeUInt32(r1, r0)
            L_0x0035:
                int r0 = r5.bitField0_
                r1 = 16
                r0 = r0 & r1
                if (r0 != r1) goto L_0x0042
                r0 = 5
                long r3 = r5.updatedAt_
                r6.writeInt64(r0, r3)
            L_0x0042:
                r0 = 0
                r1 = 0
            L_0x0044:
                java.util.List<org.bitcoinj.wallet.Protos$TransactionInput> r3 = r5.transactionInput_
                int r3 = r3.size()
                if (r1 >= r3) goto L_0x005b
                r3 = 6
                java.util.List<org.bitcoinj.wallet.Protos$TransactionInput> r4 = r5.transactionInput_
                java.lang.Object r4 = r4.get(r1)
                com.google.protobuf.MessageLite r4 = (com.google.protobuf.MessageLite) r4
                r6.writeMessage(r3, r4)
                int r1 = r1 + 1
                goto L_0x0044
            L_0x005b:
                r1 = 0
            L_0x005c:
                java.util.List<org.bitcoinj.wallet.Protos$TransactionOutput> r3 = r5.transactionOutput_
                int r3 = r3.size()
                if (r1 >= r3) goto L_0x0073
                r3 = 7
                java.util.List<org.bitcoinj.wallet.Protos$TransactionOutput> r4 = r5.transactionOutput_
                java.lang.Object r4 = r4.get(r1)
                com.google.protobuf.MessageLite r4 = (com.google.protobuf.MessageLite) r4
                r6.writeMessage(r3, r4)
                int r1 = r1 + 1
                goto L_0x005c
            L_0x0073:
                r1 = 0
            L_0x0074:
                java.util.List<com.google.protobuf.ByteString> r3 = r5.blockHash_
                int r3 = r3.size()
                if (r1 >= r3) goto L_0x008a
                java.util.List<com.google.protobuf.ByteString> r3 = r5.blockHash_
                java.lang.Object r3 = r3.get(r1)
                com.google.protobuf.ByteString r3 = (com.google.protobuf.ByteString) r3
                r6.writeBytes(r2, r3)
                int r1 = r1 + 1
                goto L_0x0074
            L_0x008a:
                int r1 = r5.bitField0_
                r2 = 32
                r1 = r1 & r2
                if (r1 != r2) goto L_0x0098
                r1 = 9
                org.bitcoinj.wallet.Protos$TransactionConfidence r2 = r5.confidence_
                r6.writeMessage(r1, r2)
            L_0x0098:
                int r1 = r5.bitField0_
                r2 = 64
                r1 = r1 & r2
                if (r1 != r2) goto L_0x00aa
                r1 = 10
                org.bitcoinj.wallet.Protos$Transaction$Purpose r2 = r5.purpose_
                int r2 = r2.getNumber()
                r6.writeEnum(r1, r2)
            L_0x00aa:
                java.util.List<java.lang.Integer> r1 = r5.blockRelativityOffsets_
                int r1 = r1.size()
                if (r0 >= r1) goto L_0x00c6
                r1 = 11
                java.util.List<java.lang.Integer> r2 = r5.blockRelativityOffsets_
                java.lang.Object r2 = r2.get(r0)
                java.lang.Integer r2 = (java.lang.Integer) r2
                int r2 = r2.intValue()
                r6.writeInt32(r1, r2)
                int r0 = r0 + 1
                goto L_0x00aa
            L_0x00c6:
                int r0 = r5.bitField0_
                r1 = 128(0x80, float:1.794E-43)
                r0 = r0 & r1
                if (r0 != r1) goto L_0x00d4
                r0 = 12
                org.bitcoinj.wallet.Protos$ExchangeRate r1 = r5.exchangeRate_
                r6.writeMessage(r0, r1)
            L_0x00d4:
                int r0 = r5.bitField0_
                r1 = 256(0x100, float:3.59E-43)
                r0 = r0 & r1
                if (r0 != r1) goto L_0x00e4
                r0 = 13
                com.google.protobuf.ByteString r1 = r5.getMemoBytes()
                r6.writeBytes(r0, r1)
            L_0x00e4:
                com.google.protobuf.UnknownFieldSet r0 = r5.getUnknownFields()
                r0.writeTo(r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.wallet.Protos.Transaction.writeTo(com.google.protobuf.CodedOutputStream):void");
        }

        /* JADX WARNING: type inference failed for: r1v5, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$ExchangeRate] */
        /* JADX WARNING: type inference failed for: r4v20, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$TransactionConfidence] */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v5, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$ExchangeRate]
          assigns: [org.bitcoinj.wallet.Protos$ExchangeRate]
          uses: [com.google.protobuf.MessageLite]
          mth insns count: 119
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 2 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int getSerializedSize() {
            /*
                r6 = this;
                int r0 = r6.memoizedSerializedSize
                r1 = -1
                if (r0 == r1) goto L_0x0006
                return r0
            L_0x0006:
                int r0 = r6.bitField0_
                r1 = 1
                r0 = r0 & r1
                r2 = 0
                if (r0 != r1) goto L_0x0015
                int r0 = r6.version_
                int r0 = com.google.protobuf.CodedOutputStream.computeInt32Size(r1, r0)
                int r0 = r0 + r2
                goto L_0x0016
            L_0x0015:
                r0 = 0
            L_0x0016:
                int r3 = r6.bitField0_
                r4 = 2
                r3 = r3 & r4
                if (r3 != r4) goto L_0x0023
                com.google.protobuf.ByteString r3 = r6.hash_
                int r3 = com.google.protobuf.CodedOutputStream.computeBytesSize(r4, r3)
                int r0 = r0 + r3
            L_0x0023:
                int r3 = r6.bitField0_
                r4 = 4
                r3 = r3 & r4
                if (r3 != r4) goto L_0x0035
                r3 = 3
                org.bitcoinj.wallet.Protos$Transaction$Pool r5 = r6.pool_
                int r5 = r5.getNumber()
                int r3 = com.google.protobuf.CodedOutputStream.computeEnumSize(r3, r5)
                int r0 = r0 + r3
            L_0x0035:
                int r3 = r6.bitField0_
                r5 = 8
                r3 = r3 & r5
                if (r3 != r5) goto L_0x0043
                int r3 = r6.lockTime_
                int r3 = com.google.protobuf.CodedOutputStream.computeUInt32Size(r4, r3)
                int r0 = r0 + r3
            L_0x0043:
                int r3 = r6.bitField0_
                r4 = 16
                r3 = r3 & r4
                if (r3 != r4) goto L_0x0052
                r3 = 5
                long r4 = r6.updatedAt_
                int r3 = com.google.protobuf.CodedOutputStream.computeInt64Size(r3, r4)
                int r0 = r0 + r3
            L_0x0052:
                r3 = r0
                r0 = 0
            L_0x0054:
                java.util.List<org.bitcoinj.wallet.Protos$TransactionInput> r4 = r6.transactionInput_
                int r4 = r4.size()
                if (r0 >= r4) goto L_0x006d
                r4 = 6
                java.util.List<org.bitcoinj.wallet.Protos$TransactionInput> r5 = r6.transactionInput_
                java.lang.Object r5 = r5.get(r0)
                com.google.protobuf.MessageLite r5 = (com.google.protobuf.MessageLite) r5
                int r4 = com.google.protobuf.CodedOutputStream.computeMessageSize(r4, r5)
                int r3 = r3 + r4
                int r0 = r0 + 1
                goto L_0x0054
            L_0x006d:
                r0 = 0
            L_0x006e:
                java.util.List<org.bitcoinj.wallet.Protos$TransactionOutput> r4 = r6.transactionOutput_
                int r4 = r4.size()
                if (r0 >= r4) goto L_0x0087
                r4 = 7
                java.util.List<org.bitcoinj.wallet.Protos$TransactionOutput> r5 = r6.transactionOutput_
                java.lang.Object r5 = r5.get(r0)
                com.google.protobuf.MessageLite r5 = (com.google.protobuf.MessageLite) r5
                int r4 = com.google.protobuf.CodedOutputStream.computeMessageSize(r4, r5)
                int r3 = r3 + r4
                int r0 = r0 + 1
                goto L_0x006e
            L_0x0087:
                r0 = 0
                r4 = 0
            L_0x0089:
                java.util.List<com.google.protobuf.ByteString> r5 = r6.blockHash_
                int r5 = r5.size()
                if (r0 >= r5) goto L_0x00a1
                java.util.List<com.google.protobuf.ByteString> r5 = r6.blockHash_
                java.lang.Object r5 = r5.get(r0)
                com.google.protobuf.ByteString r5 = (com.google.protobuf.ByteString) r5
                int r5 = com.google.protobuf.CodedOutputStream.computeBytesSizeNoTag(r5)
                int r4 = r4 + r5
                int r0 = r0 + 1
                goto L_0x0089
            L_0x00a1:
                int r3 = r3 + r4
                java.util.List r0 = r6.getBlockHashList()
                int r0 = r0.size()
                int r0 = r0 * 1
                int r3 = r3 + r0
                int r0 = r6.bitField0_
                r4 = 32
                r0 = r0 & r4
                if (r0 != r4) goto L_0x00bd
                r0 = 9
                org.bitcoinj.wallet.Protos$TransactionConfidence r4 = r6.confidence_
                int r0 = com.google.protobuf.CodedOutputStream.computeMessageSize(r0, r4)
                int r3 = r3 + r0
            L_0x00bd:
                int r0 = r6.bitField0_
                r4 = 64
                r0 = r0 & r4
                if (r0 != r4) goto L_0x00d1
                r0 = 10
                org.bitcoinj.wallet.Protos$Transaction$Purpose r4 = r6.purpose_
                int r4 = r4.getNumber()
                int r0 = com.google.protobuf.CodedOutputStream.computeEnumSize(r0, r4)
                int r3 = r3 + r0
            L_0x00d1:
                r0 = 0
            L_0x00d2:
                java.util.List<java.lang.Integer> r4 = r6.blockRelativityOffsets_
                int r4 = r4.size()
                if (r2 >= r4) goto L_0x00ee
                java.util.List<java.lang.Integer> r4 = r6.blockRelativityOffsets_
                java.lang.Object r4 = r4.get(r2)
                java.lang.Integer r4 = (java.lang.Integer) r4
                int r4 = r4.intValue()
                int r4 = com.google.protobuf.CodedOutputStream.computeInt32SizeNoTag(r4)
                int r0 = r0 + r4
                int r2 = r2 + 1
                goto L_0x00d2
            L_0x00ee:
                int r3 = r3 + r0
                java.util.List r0 = r6.getBlockRelativityOffsetsList()
                int r0 = r0.size()
                int r0 = r0 * 1
                int r3 = r3 + r0
                int r0 = r6.bitField0_
                r1 = 128(0x80, float:1.794E-43)
                r0 = r0 & r1
                if (r0 != r1) goto L_0x010a
                r0 = 12
                org.bitcoinj.wallet.Protos$ExchangeRate r1 = r6.exchangeRate_
                int r0 = com.google.protobuf.CodedOutputStream.computeMessageSize(r0, r1)
                int r3 = r3 + r0
            L_0x010a:
                int r0 = r6.bitField0_
                r1 = 256(0x100, float:3.59E-43)
                r0 = r0 & r1
                if (r0 != r1) goto L_0x011c
                r0 = 13
                com.google.protobuf.ByteString r1 = r6.getMemoBytes()
                int r0 = com.google.protobuf.CodedOutputStream.computeBytesSize(r0, r1)
                int r3 = r3 + r0
            L_0x011c:
                com.google.protobuf.UnknownFieldSet r0 = r6.getUnknownFields()
                int r0 = r0.getSerializedSize()
                int r3 = r3 + r0
                r6.memoizedSerializedSize = r3
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.wallet.Protos.Transaction.getSerializedSize():int");
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static Transaction parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Transaction) PARSER.parseFrom(byteString);
        }

        public static Transaction parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Transaction) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Transaction parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Transaction) PARSER.parseFrom(bArr);
        }

        public static Transaction parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Transaction) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Transaction parseFrom(InputStream inputStream) throws IOException {
            return (Transaction) PARSER.parseFrom(inputStream);
        }

        public static Transaction parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Transaction) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static Transaction parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Transaction) PARSER.parseDelimitedFrom(inputStream);
        }

        public static Transaction parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Transaction) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static Transaction parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Transaction) PARSER.parseFrom(codedInputStream);
        }

        public static Transaction parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Transaction) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Transaction transaction) {
            return newBuilder().mergeFrom(transaction);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public static final class TransactionConfidence extends GeneratedMessage implements TransactionConfidenceOrBuilder {
        public static final int APPEARED_AT_HEIGHT_FIELD_NUMBER = 2;
        public static final int BROADCAST_BY_FIELD_NUMBER = 6;
        public static final int DEPTH_FIELD_NUMBER = 4;
        public static final int LAST_BROADCASTED_AT_FIELD_NUMBER = 8;
        public static final int OVERRIDING_TRANSACTION_FIELD_NUMBER = 3;
        public static Parser<TransactionConfidence> PARSER = new AbstractParser<TransactionConfidence>() {
            public TransactionConfidence parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new TransactionConfidence(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SOURCE_FIELD_NUMBER = 7;
        public static final int TYPE_FIELD_NUMBER = 1;
        private static final TransactionConfidence defaultInstance = new TransactionConfidence(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int appearedAtHeight_;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public List<PeerAddress> broadcastBy_;
        /* access modifiers changed from: private */
        public int depth_;
        /* access modifiers changed from: private */
        public long lastBroadcastedAt_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString overridingTransaction_;
        /* access modifiers changed from: private */
        public Source source_;
        /* access modifiers changed from: private */
        public C3522Type type_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements TransactionConfidenceOrBuilder {
            private int appearedAtHeight_;
            private int bitField0_;
            private RepeatedFieldBuilder<PeerAddress, Builder, PeerAddressOrBuilder> broadcastByBuilder_;
            private List<PeerAddress> broadcastBy_;
            private int depth_;
            private long lastBroadcastedAt_;
            private ByteString overridingTransaction_;
            private Source source_;
            private C3522Type type_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_wallet_TransactionConfidence_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_wallet_TransactionConfidence_fieldAccessorTable.ensureFieldAccessorsInitialized(TransactionConfidence.class, Builder.class);
            }

            private Builder() {
                this.type_ = C3522Type.UNKNOWN;
                this.overridingTransaction_ = ByteString.EMPTY;
                this.broadcastBy_ = Collections.emptyList();
                this.source_ = Source.SOURCE_UNKNOWN;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.type_ = C3522Type.UNKNOWN;
                this.overridingTransaction_ = ByteString.EMPTY;
                this.broadcastBy_ = Collections.emptyList();
                this.source_ = Source.SOURCE_UNKNOWN;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (TransactionConfidence.alwaysUseFieldBuilders) {
                    getBroadcastByFieldBuilder();
                }
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                TransactionConfidence.super.clear();
                this.type_ = C3522Type.UNKNOWN;
                this.bitField0_ &= -2;
                this.appearedAtHeight_ = 0;
                this.bitField0_ &= -3;
                this.overridingTransaction_ = ByteString.EMPTY;
                this.bitField0_ &= -5;
                this.depth_ = 0;
                this.bitField0_ &= -9;
                RepeatedFieldBuilder<PeerAddress, Builder, PeerAddressOrBuilder> repeatedFieldBuilder = this.broadcastByBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.broadcastBy_ = Collections.emptyList();
                    this.bitField0_ &= -17;
                } else {
                    repeatedFieldBuilder.clear();
                }
                this.lastBroadcastedAt_ = 0;
                this.bitField0_ &= -33;
                this.source_ = Source.SOURCE_UNKNOWN;
                this.bitField0_ &= -65;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_wallet_TransactionConfidence_descriptor;
            }

            public TransactionConfidence getDefaultInstanceForType() {
                return TransactionConfidence.getDefaultInstance();
            }

            public TransactionConfidence build() {
                TransactionConfidence buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public TransactionConfidence buildPartial() {
                TransactionConfidence transactionConfidence = new TransactionConfidence((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                transactionConfidence.type_ = this.type_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                transactionConfidence.appearedAtHeight_ = this.appearedAtHeight_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                transactionConfidence.overridingTransaction_ = this.overridingTransaction_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                transactionConfidence.depth_ = this.depth_;
                RepeatedFieldBuilder<PeerAddress, Builder, PeerAddressOrBuilder> repeatedFieldBuilder = this.broadcastByBuilder_;
                if (repeatedFieldBuilder == null) {
                    if ((this.bitField0_ & 16) == 16) {
                        this.broadcastBy_ = Collections.unmodifiableList(this.broadcastBy_);
                        this.bitField0_ &= -17;
                    }
                    transactionConfidence.broadcastBy_ = this.broadcastBy_;
                } else {
                    transactionConfidence.broadcastBy_ = repeatedFieldBuilder.build();
                }
                if ((i & 32) == 32) {
                    i2 |= 16;
                }
                transactionConfidence.lastBroadcastedAt_ = this.lastBroadcastedAt_;
                if ((i & 64) == 64) {
                    i2 |= 32;
                }
                transactionConfidence.source_ = this.source_;
                transactionConfidence.bitField0_ = i2;
                onBuilt();
                return transactionConfidence;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof TransactionConfidence) {
                    return mergeFrom((TransactionConfidence) message);
                }
                TransactionConfidence.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(TransactionConfidence transactionConfidence) {
                if (transactionConfidence == TransactionConfidence.getDefaultInstance()) {
                    return this;
                }
                if (transactionConfidence.hasType()) {
                    setType(transactionConfidence.getType());
                }
                if (transactionConfidence.hasAppearedAtHeight()) {
                    setAppearedAtHeight(transactionConfidence.getAppearedAtHeight());
                }
                if (transactionConfidence.hasOverridingTransaction()) {
                    setOverridingTransaction(transactionConfidence.getOverridingTransaction());
                }
                if (transactionConfidence.hasDepth()) {
                    setDepth(transactionConfidence.getDepth());
                }
                if (this.broadcastByBuilder_ == null) {
                    if (!transactionConfidence.broadcastBy_.isEmpty()) {
                        if (this.broadcastBy_.isEmpty()) {
                            this.broadcastBy_ = transactionConfidence.broadcastBy_;
                            this.bitField0_ &= -17;
                        } else {
                            ensureBroadcastByIsMutable();
                            this.broadcastBy_.addAll(transactionConfidence.broadcastBy_);
                        }
                        onChanged();
                    }
                } else if (!transactionConfidence.broadcastBy_.isEmpty()) {
                    if (this.broadcastByBuilder_.isEmpty()) {
                        this.broadcastByBuilder_.dispose();
                        RepeatedFieldBuilder<PeerAddress, Builder, PeerAddressOrBuilder> repeatedFieldBuilder = null;
                        this.broadcastByBuilder_ = null;
                        this.broadcastBy_ = transactionConfidence.broadcastBy_;
                        this.bitField0_ &= -17;
                        if (TransactionConfidence.alwaysUseFieldBuilders) {
                            repeatedFieldBuilder = getBroadcastByFieldBuilder();
                        }
                        this.broadcastByBuilder_ = repeatedFieldBuilder;
                    } else {
                        this.broadcastByBuilder_.addAllMessages(transactionConfidence.broadcastBy_);
                    }
                }
                if (transactionConfidence.hasLastBroadcastedAt()) {
                    setLastBroadcastedAt(transactionConfidence.getLastBroadcastedAt());
                }
                if (transactionConfidence.hasSource()) {
                    setSource(transactionConfidence.getSource());
                }
                mergeUnknownFields(transactionConfidence.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getBroadcastByCount(); i++) {
                    if (!getBroadcastBy(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                TransactionConfidence transactionConfidence;
                TransactionConfidence transactionConfidence2 = null;
                try {
                    TransactionConfidence transactionConfidence3 = (TransactionConfidence) TransactionConfidence.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (transactionConfidence3 != null) {
                        mergeFrom(transactionConfidence3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    transactionConfidence = (TransactionConfidence) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    transactionConfidence2 = transactionConfidence;
                }
                if (transactionConfidence2 != null) {
                    mergeFrom(transactionConfidence2);
                }
                throw th;
            }

            public boolean hasType() {
                return (this.bitField0_ & 1) == 1;
            }

            public C3522Type getType() {
                return this.type_;
            }

            public Builder setType(C3522Type type) {
                if (type != null) {
                    this.bitField0_ |= 1;
                    this.type_ = type;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearType() {
                this.bitField0_ &= -2;
                this.type_ = C3522Type.UNKNOWN;
                onChanged();
                return this;
            }

            public boolean hasAppearedAtHeight() {
                return (this.bitField0_ & 2) == 2;
            }

            public int getAppearedAtHeight() {
                return this.appearedAtHeight_;
            }

            public Builder setAppearedAtHeight(int i) {
                this.bitField0_ |= 2;
                this.appearedAtHeight_ = i;
                onChanged();
                return this;
            }

            public Builder clearAppearedAtHeight() {
                this.bitField0_ &= -3;
                this.appearedAtHeight_ = 0;
                onChanged();
                return this;
            }

            public boolean hasOverridingTransaction() {
                return (this.bitField0_ & 4) == 4;
            }

            public ByteString getOverridingTransaction() {
                return this.overridingTransaction_;
            }

            public Builder setOverridingTransaction(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 4;
                    this.overridingTransaction_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearOverridingTransaction() {
                this.bitField0_ &= -5;
                this.overridingTransaction_ = TransactionConfidence.getDefaultInstance().getOverridingTransaction();
                onChanged();
                return this;
            }

            public boolean hasDepth() {
                return (this.bitField0_ & 8) == 8;
            }

            public int getDepth() {
                return this.depth_;
            }

            public Builder setDepth(int i) {
                this.bitField0_ |= 8;
                this.depth_ = i;
                onChanged();
                return this;
            }

            public Builder clearDepth() {
                this.bitField0_ &= -9;
                this.depth_ = 0;
                onChanged();
                return this;
            }

            private void ensureBroadcastByIsMutable() {
                if ((this.bitField0_ & 16) != 16) {
                    this.broadcastBy_ = new ArrayList(this.broadcastBy_);
                    this.bitField0_ |= 16;
                }
            }

            public List<PeerAddress> getBroadcastByList() {
                RepeatedFieldBuilder<PeerAddress, Builder, PeerAddressOrBuilder> repeatedFieldBuilder = this.broadcastByBuilder_;
                if (repeatedFieldBuilder == null) {
                    return Collections.unmodifiableList(this.broadcastBy_);
                }
                return repeatedFieldBuilder.getMessageList();
            }

            public int getBroadcastByCount() {
                RepeatedFieldBuilder<PeerAddress, Builder, PeerAddressOrBuilder> repeatedFieldBuilder = this.broadcastByBuilder_;
                if (repeatedFieldBuilder == null) {
                    return this.broadcastBy_.size();
                }
                return repeatedFieldBuilder.getCount();
            }

            public PeerAddress getBroadcastBy(int i) {
                RepeatedFieldBuilder<PeerAddress, Builder, PeerAddressOrBuilder> repeatedFieldBuilder = this.broadcastByBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (PeerAddress) this.broadcastBy_.get(i);
                }
                return repeatedFieldBuilder.getMessage(i);
            }

            public Builder setBroadcastBy(int i, PeerAddress peerAddress) {
                RepeatedFieldBuilder<PeerAddress, Builder, PeerAddressOrBuilder> repeatedFieldBuilder = this.broadcastByBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.setMessage(i, peerAddress);
                } else if (peerAddress != null) {
                    ensureBroadcastByIsMutable();
                    this.broadcastBy_.set(i, peerAddress);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setBroadcastBy(int i, Builder builder) {
                RepeatedFieldBuilder<PeerAddress, Builder, PeerAddressOrBuilder> repeatedFieldBuilder = this.broadcastByBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureBroadcastByIsMutable();
                    this.broadcastBy_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addBroadcastBy(PeerAddress peerAddress) {
                RepeatedFieldBuilder<PeerAddress, Builder, PeerAddressOrBuilder> repeatedFieldBuilder = this.broadcastByBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(peerAddress);
                } else if (peerAddress != null) {
                    ensureBroadcastByIsMutable();
                    this.broadcastBy_.add(peerAddress);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addBroadcastBy(int i, PeerAddress peerAddress) {
                RepeatedFieldBuilder<PeerAddress, Builder, PeerAddressOrBuilder> repeatedFieldBuilder = this.broadcastByBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(i, peerAddress);
                } else if (peerAddress != null) {
                    ensureBroadcastByIsMutable();
                    this.broadcastBy_.add(i, peerAddress);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addBroadcastBy(Builder builder) {
                RepeatedFieldBuilder<PeerAddress, Builder, PeerAddressOrBuilder> repeatedFieldBuilder = this.broadcastByBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureBroadcastByIsMutable();
                    this.broadcastBy_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(builder.build());
                }
                return this;
            }

            public Builder addBroadcastBy(int i, Builder builder) {
                RepeatedFieldBuilder<PeerAddress, Builder, PeerAddressOrBuilder> repeatedFieldBuilder = this.broadcastByBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureBroadcastByIsMutable();
                    this.broadcastBy_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllBroadcastBy(Iterable<? extends PeerAddress> iterable) {
                RepeatedFieldBuilder<PeerAddress, Builder, PeerAddressOrBuilder> repeatedFieldBuilder = this.broadcastByBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureBroadcastByIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.broadcastBy_);
                    onChanged();
                } else {
                    repeatedFieldBuilder.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearBroadcastBy() {
                RepeatedFieldBuilder<PeerAddress, Builder, PeerAddressOrBuilder> repeatedFieldBuilder = this.broadcastByBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.broadcastBy_ = Collections.emptyList();
                    this.bitField0_ &= -17;
                    onChanged();
                } else {
                    repeatedFieldBuilder.clear();
                }
                return this;
            }

            public Builder removeBroadcastBy(int i) {
                RepeatedFieldBuilder<PeerAddress, Builder, PeerAddressOrBuilder> repeatedFieldBuilder = this.broadcastByBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureBroadcastByIsMutable();
                    this.broadcastBy_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilder.remove(i);
                }
                return this;
            }

            public Builder getBroadcastByBuilder(int i) {
                return getBroadcastByFieldBuilder().getBuilder(i);
            }

            public PeerAddressOrBuilder getBroadcastByOrBuilder(int i) {
                RepeatedFieldBuilder<PeerAddress, Builder, PeerAddressOrBuilder> repeatedFieldBuilder = this.broadcastByBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (PeerAddressOrBuilder) this.broadcastBy_.get(i);
                }
                return repeatedFieldBuilder.getMessageOrBuilder(i);
            }

            public List<? extends PeerAddressOrBuilder> getBroadcastByOrBuilderList() {
                RepeatedFieldBuilder<PeerAddress, Builder, PeerAddressOrBuilder> repeatedFieldBuilder = this.broadcastByBuilder_;
                if (repeatedFieldBuilder != null) {
                    return repeatedFieldBuilder.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.broadcastBy_);
            }

            public Builder addBroadcastByBuilder() {
                return getBroadcastByFieldBuilder().addBuilder(PeerAddress.getDefaultInstance());
            }

            public Builder addBroadcastByBuilder(int i) {
                return getBroadcastByFieldBuilder().addBuilder(i, PeerAddress.getDefaultInstance());
            }

            public List<Builder> getBroadcastByBuilderList() {
                return getBroadcastByFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<PeerAddress, Builder, PeerAddressOrBuilder> getBroadcastByFieldBuilder() {
                if (this.broadcastByBuilder_ == null) {
                    this.broadcastByBuilder_ = new RepeatedFieldBuilder<>(this.broadcastBy_, (this.bitField0_ & 16) == 16, getParentForChildren(), isClean());
                    this.broadcastBy_ = null;
                }
                return this.broadcastByBuilder_;
            }

            public boolean hasLastBroadcastedAt() {
                return (this.bitField0_ & 32) == 32;
            }

            public long getLastBroadcastedAt() {
                return this.lastBroadcastedAt_;
            }

            public Builder setLastBroadcastedAt(long j) {
                this.bitField0_ |= 32;
                this.lastBroadcastedAt_ = j;
                onChanged();
                return this;
            }

            public Builder clearLastBroadcastedAt() {
                this.bitField0_ &= -33;
                this.lastBroadcastedAt_ = 0;
                onChanged();
                return this;
            }

            public boolean hasSource() {
                return (this.bitField0_ & 64) == 64;
            }

            public Source getSource() {
                return this.source_;
            }

            public Builder setSource(Source source) {
                if (source != null) {
                    this.bitField0_ |= 64;
                    this.source_ = source;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearSource() {
                this.bitField0_ &= -65;
                this.source_ = Source.SOURCE_UNKNOWN;
                onChanged();
                return this;
            }
        }

        public enum Source implements ProtocolMessageEnum {
            SOURCE_UNKNOWN(0, 0),
            SOURCE_NETWORK(1, 1),
            SOURCE_SELF(2, 2);
            
            public static final int SOURCE_NETWORK_VALUE = 1;
            public static final int SOURCE_SELF_VALUE = 2;
            public static final int SOURCE_UNKNOWN_VALUE = 0;
            private static final Source[] VALUES = null;
            private static EnumLiteMap<Source> internalValueMap;
            private final int index;
            private final int value;

            static {
                internalValueMap = new EnumLiteMap<Source>() {
                    public Source findValueByNumber(int i) {
                        return Source.valueOf(i);
                    }
                };
                VALUES = values();
            }

            public final int getNumber() {
                return this.value;
            }

            public static Source valueOf(int i) {
                if (i == 0) {
                    return SOURCE_UNKNOWN;
                }
                if (i == 1) {
                    return SOURCE_NETWORK;
                }
                if (i != 2) {
                    return null;
                }
                return SOURCE_SELF;
            }

            public static EnumLiteMap<Source> internalGetValueMap() {
                return internalValueMap;
            }

            public final EnumValueDescriptor getValueDescriptor() {
                return (EnumValueDescriptor) getDescriptor().getValues().get(this.index);
            }

            public final EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final EnumDescriptor getDescriptor() {
                return (EnumDescriptor) TransactionConfidence.getDescriptor().getEnumTypes().get(1);
            }

            public static Source valueOf(EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            private Source(int i, int i2) {
                this.index = i;
                this.value = i2;
            }
        }

        /* renamed from: org.bitcoinj.wallet.Protos$TransactionConfidence$Type */
        public enum C3522Type implements ProtocolMessageEnum {
            UNKNOWN(0, 0),
            BUILDING(1, 1),
            PENDING(2, 2),
            NOT_IN_BEST_CHAIN(3, 3),
            DEAD(4, 4),
            IN_CONFLICT(5, 5);
            
            public static final int BUILDING_VALUE = 1;
            public static final int DEAD_VALUE = 4;
            public static final int IN_CONFLICT_VALUE = 5;
            public static final int NOT_IN_BEST_CHAIN_VALUE = 3;
            public static final int PENDING_VALUE = 2;
            public static final int UNKNOWN_VALUE = 0;
            private static final C3522Type[] VALUES = null;
            private static EnumLiteMap<C3522Type> internalValueMap;
            private final int index;
            private final int value;

            static {
                internalValueMap = new EnumLiteMap<C3522Type>() {
                    public C3522Type findValueByNumber(int i) {
                        return C3522Type.valueOf(i);
                    }
                };
                VALUES = values();
            }

            public final int getNumber() {
                return this.value;
            }

            public static C3522Type valueOf(int i) {
                if (i == 0) {
                    return UNKNOWN;
                }
                if (i == 1) {
                    return BUILDING;
                }
                if (i == 2) {
                    return PENDING;
                }
                if (i == 3) {
                    return NOT_IN_BEST_CHAIN;
                }
                if (i == 4) {
                    return DEAD;
                }
                if (i != 5) {
                    return null;
                }
                return IN_CONFLICT;
            }

            public static EnumLiteMap<C3522Type> internalGetValueMap() {
                return internalValueMap;
            }

            public final EnumValueDescriptor getValueDescriptor() {
                return (EnumValueDescriptor) getDescriptor().getValues().get(this.index);
            }

            public final EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final EnumDescriptor getDescriptor() {
                return (EnumDescriptor) TransactionConfidence.getDescriptor().getEnumTypes().get(0);
            }

            public static C3522Type valueOf(EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            private C3522Type(int i, int i2) {
                this.index = i;
                this.value = i2;
            }
        }

        private TransactionConfidence(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private TransactionConfidence(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static TransactionConfidence getDefaultInstance() {
            return defaultInstance;
        }

        public TransactionConfidence getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r8v0, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$TransactionConfidence] */
        private TransactionConfidence(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (readTag == 8) {
                            int readEnum = codedInputStream.readEnum();
                            C3522Type valueOf = C3522Type.valueOf(readEnum);
                            if (valueOf == null) {
                                newBuilder.mergeVarintField(1, readEnum);
                            } else {
                                this.bitField0_ |= 1;
                                this.type_ = valueOf;
                            }
                        } else if (readTag == 16) {
                            this.bitField0_ |= 2;
                            this.appearedAtHeight_ = codedInputStream.readInt32();
                        } else if (readTag == 26) {
                            this.bitField0_ |= 4;
                            this.overridingTransaction_ = codedInputStream.readBytes();
                        } else if (readTag == 32) {
                            this.bitField0_ |= 8;
                            this.depth_ = codedInputStream.readInt32();
                        } else if (readTag == 50) {
                            if (!(z2 & true)) {
                                this.broadcastBy_ = new ArrayList();
                                z2 |= true;
                            }
                            this.broadcastBy_.add(codedInputStream.readMessage(PeerAddress.PARSER, extensionRegistryLite));
                        } else if (readTag == 56) {
                            int readEnum2 = codedInputStream.readEnum();
                            Source valueOf2 = Source.valueOf(readEnum2);
                            if (valueOf2 == null) {
                                newBuilder.mergeVarintField(7, readEnum2);
                            } else {
                                this.bitField0_ |= 32;
                                this.source_ = valueOf2;
                            }
                        } else if (readTag == 64) {
                            this.bitField0_ |= 16;
                            this.lastBroadcastedAt_ = codedInputStream.readInt64();
                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.broadcastBy_ = Collections.unmodifiableList(this.broadcastBy_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.broadcastBy_ = Collections.unmodifiableList(this.broadcastBy_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        public static final Descriptor getDescriptor() {
            return Protos.internal_static_wallet_TransactionConfidence_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_wallet_TransactionConfidence_fieldAccessorTable.ensureFieldAccessorsInitialized(TransactionConfidence.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<TransactionConfidence> getParserForType() {
            return PARSER;
        }

        public boolean hasType() {
            return (this.bitField0_ & 1) == 1;
        }

        public C3522Type getType() {
            return this.type_;
        }

        public boolean hasAppearedAtHeight() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getAppearedAtHeight() {
            return this.appearedAtHeight_;
        }

        public boolean hasOverridingTransaction() {
            return (this.bitField0_ & 4) == 4;
        }

        public ByteString getOverridingTransaction() {
            return this.overridingTransaction_;
        }

        public boolean hasDepth() {
            return (this.bitField0_ & 8) == 8;
        }

        public int getDepth() {
            return this.depth_;
        }

        public List<PeerAddress> getBroadcastByList() {
            return this.broadcastBy_;
        }

        public List<? extends PeerAddressOrBuilder> getBroadcastByOrBuilderList() {
            return this.broadcastBy_;
        }

        public int getBroadcastByCount() {
            return this.broadcastBy_.size();
        }

        public PeerAddress getBroadcastBy(int i) {
            return (PeerAddress) this.broadcastBy_.get(i);
        }

        public PeerAddressOrBuilder getBroadcastByOrBuilder(int i) {
            return (PeerAddressOrBuilder) this.broadcastBy_.get(i);
        }

        public boolean hasLastBroadcastedAt() {
            return (this.bitField0_ & 16) == 16;
        }

        public long getLastBroadcastedAt() {
            return this.lastBroadcastedAt_;
        }

        public boolean hasSource() {
            return (this.bitField0_ & 32) == 32;
        }

        public Source getSource() {
            return this.source_;
        }

        private void initFields() {
            this.type_ = C3522Type.UNKNOWN;
            this.appearedAtHeight_ = 0;
            this.overridingTransaction_ = ByteString.EMPTY;
            this.depth_ = 0;
            this.broadcastBy_ = Collections.emptyList();
            this.lastBroadcastedAt_ = 0;
            this.source_ = Source.SOURCE_UNKNOWN;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getBroadcastByCount(); i++) {
                if (!getBroadcastBy(i).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeEnum(1, this.type_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(2, this.appearedAtHeight_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBytes(3, this.overridingTransaction_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeInt32(4, this.depth_);
            }
            for (int i = 0; i < this.broadcastBy_.size(); i++) {
                codedOutputStream.writeMessage(6, (MessageLite) this.broadcastBy_.get(i));
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeEnum(7, this.source_.getNumber());
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeInt64(8, this.lastBroadcastedAt_);
            }
            getUnknownFields().writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeEnumSize(1, this.type_.getNumber()) + 0 : 0;
            if ((this.bitField0_ & 2) == 2) {
                computeEnumSize += CodedOutputStream.computeInt32Size(2, this.appearedAtHeight_);
            }
            if ((this.bitField0_ & 4) == 4) {
                computeEnumSize += CodedOutputStream.computeBytesSize(3, this.overridingTransaction_);
            }
            if ((this.bitField0_ & 8) == 8) {
                computeEnumSize += CodedOutputStream.computeInt32Size(4, this.depth_);
            }
            for (int i2 = 0; i2 < this.broadcastBy_.size(); i2++) {
                computeEnumSize += CodedOutputStream.computeMessageSize(6, (MessageLite) this.broadcastBy_.get(i2));
            }
            if ((this.bitField0_ & 32) == 32) {
                computeEnumSize += CodedOutputStream.computeEnumSize(7, this.source_.getNumber());
            }
            if ((this.bitField0_ & 16) == 16) {
                computeEnumSize += CodedOutputStream.computeInt64Size(8, this.lastBroadcastedAt_);
            }
            int serializedSize = computeEnumSize + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static TransactionConfidence parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (TransactionConfidence) PARSER.parseFrom(byteString);
        }

        public static TransactionConfidence parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TransactionConfidence) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static TransactionConfidence parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (TransactionConfidence) PARSER.parseFrom(bArr);
        }

        public static TransactionConfidence parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TransactionConfidence) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static TransactionConfidence parseFrom(InputStream inputStream) throws IOException {
            return (TransactionConfidence) PARSER.parseFrom(inputStream);
        }

        public static TransactionConfidence parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransactionConfidence) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static TransactionConfidence parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (TransactionConfidence) PARSER.parseDelimitedFrom(inputStream);
        }

        public static TransactionConfidence parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransactionConfidence) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static TransactionConfidence parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (TransactionConfidence) PARSER.parseFrom(codedInputStream);
        }

        public static TransactionConfidence parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransactionConfidence) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(TransactionConfidence transactionConfidence) {
            return newBuilder().mergeFrom(transactionConfidence);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface TransactionConfidenceOrBuilder extends MessageOrBuilder {
        int getAppearedAtHeight();

        PeerAddress getBroadcastBy(int i);

        int getBroadcastByCount();

        List<PeerAddress> getBroadcastByList();

        PeerAddressOrBuilder getBroadcastByOrBuilder(int i);

        List<? extends PeerAddressOrBuilder> getBroadcastByOrBuilderList();

        int getDepth();

        long getLastBroadcastedAt();

        ByteString getOverridingTransaction();

        Source getSource();

        C3522Type getType();

        boolean hasAppearedAtHeight();

        boolean hasDepth();

        boolean hasLastBroadcastedAt();

        boolean hasOverridingTransaction();

        boolean hasSource();

        boolean hasType();
    }

    public static final class TransactionInput extends GeneratedMessage implements TransactionInputOrBuilder {
        public static Parser<TransactionInput> PARSER = new AbstractParser<TransactionInput>() {
            public TransactionInput parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new TransactionInput(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SCRIPT_BYTES_FIELD_NUMBER = 3;
        public static final int SEQUENCE_FIELD_NUMBER = 4;
        public static final int TRANSACTION_OUT_POINT_HASH_FIELD_NUMBER = 1;
        public static final int TRANSACTION_OUT_POINT_INDEX_FIELD_NUMBER = 2;
        public static final int VALUE_FIELD_NUMBER = 5;
        private static final TransactionInput defaultInstance = new TransactionInput(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString scriptBytes_;
        /* access modifiers changed from: private */
        public int sequence_;
        /* access modifiers changed from: private */
        public ByteString transactionOutPointHash_;
        /* access modifiers changed from: private */
        public int transactionOutPointIndex_;
        private final UnknownFieldSet unknownFields;
        /* access modifiers changed from: private */
        public long value_;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements TransactionInputOrBuilder {
            private int bitField0_;
            private ByteString scriptBytes_;
            private int sequence_;
            private ByteString transactionOutPointHash_;
            private int transactionOutPointIndex_;
            private long value_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_wallet_TransactionInput_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_wallet_TransactionInput_fieldAccessorTable.ensureFieldAccessorsInitialized(TransactionInput.class, Builder.class);
            }

            private Builder() {
                this.transactionOutPointHash_ = ByteString.EMPTY;
                this.scriptBytes_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.transactionOutPointHash_ = ByteString.EMPTY;
                this.scriptBytes_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                TransactionInput.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                TransactionInput.super.clear();
                this.transactionOutPointHash_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                this.transactionOutPointIndex_ = 0;
                this.bitField0_ &= -3;
                this.scriptBytes_ = ByteString.EMPTY;
                this.bitField0_ &= -5;
                this.sequence_ = 0;
                this.bitField0_ &= -9;
                this.value_ = 0;
                this.bitField0_ &= -17;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_wallet_TransactionInput_descriptor;
            }

            public TransactionInput getDefaultInstanceForType() {
                return TransactionInput.getDefaultInstance();
            }

            public TransactionInput build() {
                TransactionInput buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public TransactionInput buildPartial() {
                TransactionInput transactionInput = new TransactionInput((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                transactionInput.transactionOutPointHash_ = this.transactionOutPointHash_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                transactionInput.transactionOutPointIndex_ = this.transactionOutPointIndex_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                transactionInput.scriptBytes_ = this.scriptBytes_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                transactionInput.sequence_ = this.sequence_;
                if ((i & 16) == 16) {
                    i2 |= 16;
                }
                transactionInput.value_ = this.value_;
                transactionInput.bitField0_ = i2;
                onBuilt();
                return transactionInput;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof TransactionInput) {
                    return mergeFrom((TransactionInput) message);
                }
                TransactionInput.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(TransactionInput transactionInput) {
                if (transactionInput == TransactionInput.getDefaultInstance()) {
                    return this;
                }
                if (transactionInput.hasTransactionOutPointHash()) {
                    setTransactionOutPointHash(transactionInput.getTransactionOutPointHash());
                }
                if (transactionInput.hasTransactionOutPointIndex()) {
                    setTransactionOutPointIndex(transactionInput.getTransactionOutPointIndex());
                }
                if (transactionInput.hasScriptBytes()) {
                    setScriptBytes(transactionInput.getScriptBytes());
                }
                if (transactionInput.hasSequence()) {
                    setSequence(transactionInput.getSequence());
                }
                if (transactionInput.hasValue()) {
                    setValue(transactionInput.getValue());
                }
                mergeUnknownFields(transactionInput.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (hasTransactionOutPointHash() && hasTransactionOutPointIndex() && hasScriptBytes()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                TransactionInput transactionInput;
                TransactionInput transactionInput2 = null;
                try {
                    TransactionInput transactionInput3 = (TransactionInput) TransactionInput.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (transactionInput3 != null) {
                        mergeFrom(transactionInput3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    transactionInput = (TransactionInput) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    transactionInput2 = transactionInput;
                }
                if (transactionInput2 != null) {
                    mergeFrom(transactionInput2);
                }
                throw th;
            }

            public boolean hasTransactionOutPointHash() {
                return (this.bitField0_ & 1) == 1;
            }

            public ByteString getTransactionOutPointHash() {
                return this.transactionOutPointHash_;
            }

            public Builder setTransactionOutPointHash(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.transactionOutPointHash_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearTransactionOutPointHash() {
                this.bitField0_ &= -2;
                this.transactionOutPointHash_ = TransactionInput.getDefaultInstance().getTransactionOutPointHash();
                onChanged();
                return this;
            }

            public boolean hasTransactionOutPointIndex() {
                return (this.bitField0_ & 2) == 2;
            }

            public int getTransactionOutPointIndex() {
                return this.transactionOutPointIndex_;
            }

            public Builder setTransactionOutPointIndex(int i) {
                this.bitField0_ |= 2;
                this.transactionOutPointIndex_ = i;
                onChanged();
                return this;
            }

            public Builder clearTransactionOutPointIndex() {
                this.bitField0_ &= -3;
                this.transactionOutPointIndex_ = 0;
                onChanged();
                return this;
            }

            public boolean hasScriptBytes() {
                return (this.bitField0_ & 4) == 4;
            }

            public ByteString getScriptBytes() {
                return this.scriptBytes_;
            }

            public Builder setScriptBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 4;
                    this.scriptBytes_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearScriptBytes() {
                this.bitField0_ &= -5;
                this.scriptBytes_ = TransactionInput.getDefaultInstance().getScriptBytes();
                onChanged();
                return this;
            }

            public boolean hasSequence() {
                return (this.bitField0_ & 8) == 8;
            }

            public int getSequence() {
                return this.sequence_;
            }

            public Builder setSequence(int i) {
                this.bitField0_ |= 8;
                this.sequence_ = i;
                onChanged();
                return this;
            }

            public Builder clearSequence() {
                this.bitField0_ &= -9;
                this.sequence_ = 0;
                onChanged();
                return this;
            }

            public boolean hasValue() {
                return (this.bitField0_ & 16) == 16;
            }

            public long getValue() {
                return this.value_;
            }

            public Builder setValue(long j) {
                this.bitField0_ |= 16;
                this.value_ = j;
                onChanged();
                return this;
            }

            public Builder clearValue() {
                this.bitField0_ &= -17;
                this.value_ = 0;
                onChanged();
                return this;
            }
        }

        private TransactionInput(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private TransactionInput(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static TransactionInput getDefaultInstance() {
            return defaultInstance;
        }

        public TransactionInput getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r6v0, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$TransactionInput] */
        private TransactionInput(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.transactionOutPointHash_ = codedInputStream.readBytes();
                        } else if (readTag == 16) {
                            this.bitField0_ |= 2;
                            this.transactionOutPointIndex_ = codedInputStream.readUInt32();
                        } else if (readTag == 26) {
                            this.bitField0_ |= 4;
                            this.scriptBytes_ = codedInputStream.readBytes();
                        } else if (readTag == 32) {
                            this.bitField0_ |= 8;
                            this.sequence_ = codedInputStream.readUInt32();
                        } else if (readTag == 40) {
                            this.bitField0_ |= 16;
                            this.value_ = codedInputStream.readInt64();
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
            return Protos.internal_static_wallet_TransactionInput_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_wallet_TransactionInput_fieldAccessorTable.ensureFieldAccessorsInitialized(TransactionInput.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<TransactionInput> getParserForType() {
            return PARSER;
        }

        public boolean hasTransactionOutPointHash() {
            return (this.bitField0_ & 1) == 1;
        }

        public ByteString getTransactionOutPointHash() {
            return this.transactionOutPointHash_;
        }

        public boolean hasTransactionOutPointIndex() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getTransactionOutPointIndex() {
            return this.transactionOutPointIndex_;
        }

        public boolean hasScriptBytes() {
            return (this.bitField0_ & 4) == 4;
        }

        public ByteString getScriptBytes() {
            return this.scriptBytes_;
        }

        public boolean hasSequence() {
            return (this.bitField0_ & 8) == 8;
        }

        public int getSequence() {
            return this.sequence_;
        }

        public boolean hasValue() {
            return (this.bitField0_ & 16) == 16;
        }

        public long getValue() {
            return this.value_;
        }

        private void initFields() {
            this.transactionOutPointHash_ = ByteString.EMPTY;
            this.transactionOutPointIndex_ = 0;
            this.scriptBytes_ = ByteString.EMPTY;
            this.sequence_ = 0;
            this.value_ = 0;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasTransactionOutPointHash()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasTransactionOutPointIndex()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasScriptBytes()) {
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
                codedOutputStream.writeBytes(1, this.transactionOutPointHash_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeUInt32(2, this.transactionOutPointIndex_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBytes(3, this.scriptBytes_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeUInt32(4, this.sequence_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeInt64(5, this.value_);
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
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.transactionOutPointHash_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeUInt32Size(2, this.transactionOutPointIndex_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeBytesSize(3, this.scriptBytes_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeUInt32Size(4, this.sequence_);
            }
            if ((this.bitField0_ & 16) == 16) {
                i2 += CodedOutputStream.computeInt64Size(5, this.value_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static TransactionInput parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (TransactionInput) PARSER.parseFrom(byteString);
        }

        public static TransactionInput parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TransactionInput) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static TransactionInput parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (TransactionInput) PARSER.parseFrom(bArr);
        }

        public static TransactionInput parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TransactionInput) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static TransactionInput parseFrom(InputStream inputStream) throws IOException {
            return (TransactionInput) PARSER.parseFrom(inputStream);
        }

        public static TransactionInput parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransactionInput) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static TransactionInput parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (TransactionInput) PARSER.parseDelimitedFrom(inputStream);
        }

        public static TransactionInput parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransactionInput) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static TransactionInput parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (TransactionInput) PARSER.parseFrom(codedInputStream);
        }

        public static TransactionInput parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransactionInput) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(TransactionInput transactionInput) {
            return newBuilder().mergeFrom(transactionInput);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface TransactionInputOrBuilder extends MessageOrBuilder {
        ByteString getScriptBytes();

        int getSequence();

        ByteString getTransactionOutPointHash();

        int getTransactionOutPointIndex();

        long getValue();

        boolean hasScriptBytes();

        boolean hasSequence();

        boolean hasTransactionOutPointHash();

        boolean hasTransactionOutPointIndex();

        boolean hasValue();
    }

    public interface TransactionOrBuilder extends MessageOrBuilder {
        ByteString getBlockHash(int i);

        int getBlockHashCount();

        List<ByteString> getBlockHashList();

        int getBlockRelativityOffsets(int i);

        int getBlockRelativityOffsetsCount();

        List<Integer> getBlockRelativityOffsetsList();

        TransactionConfidence getConfidence();

        TransactionConfidenceOrBuilder getConfidenceOrBuilder();

        ExchangeRate getExchangeRate();

        ExchangeRateOrBuilder getExchangeRateOrBuilder();

        ByteString getHash();

        int getLockTime();

        String getMemo();

        ByteString getMemoBytes();

        Pool getPool();

        Purpose getPurpose();

        TransactionInput getTransactionInput(int i);

        int getTransactionInputCount();

        List<TransactionInput> getTransactionInputList();

        TransactionInputOrBuilder getTransactionInputOrBuilder(int i);

        List<? extends TransactionInputOrBuilder> getTransactionInputOrBuilderList();

        TransactionOutput getTransactionOutput(int i);

        int getTransactionOutputCount();

        List<TransactionOutput> getTransactionOutputList();

        TransactionOutputOrBuilder getTransactionOutputOrBuilder(int i);

        List<? extends TransactionOutputOrBuilder> getTransactionOutputOrBuilderList();

        long getUpdatedAt();

        int getVersion();

        boolean hasConfidence();

        boolean hasExchangeRate();

        boolean hasHash();

        boolean hasLockTime();

        boolean hasMemo();

        boolean hasPool();

        boolean hasPurpose();

        boolean hasUpdatedAt();

        boolean hasVersion();
    }

    public static final class TransactionOutput extends GeneratedMessage implements TransactionOutputOrBuilder {
        public static Parser<TransactionOutput> PARSER = new AbstractParser<TransactionOutput>() {
            public TransactionOutput parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new TransactionOutput(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SCRIPT_BYTES_FIELD_NUMBER = 2;
        public static final int SPENT_BY_TRANSACTION_HASH_FIELD_NUMBER = 3;
        public static final int SPENT_BY_TRANSACTION_INDEX_FIELD_NUMBER = 4;
        public static final int VALUE_FIELD_NUMBER = 1;
        private static final TransactionOutput defaultInstance = new TransactionOutput(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString scriptBytes_;
        /* access modifiers changed from: private */
        public ByteString spentByTransactionHash_;
        /* access modifiers changed from: private */
        public int spentByTransactionIndex_;
        private final UnknownFieldSet unknownFields;
        /* access modifiers changed from: private */
        public long value_;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements TransactionOutputOrBuilder {
            private int bitField0_;
            private ByteString scriptBytes_;
            private ByteString spentByTransactionHash_;
            private int spentByTransactionIndex_;
            private long value_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_wallet_TransactionOutput_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_wallet_TransactionOutput_fieldAccessorTable.ensureFieldAccessorsInitialized(TransactionOutput.class, Builder.class);
            }

            private Builder() {
                this.scriptBytes_ = ByteString.EMPTY;
                this.spentByTransactionHash_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.scriptBytes_ = ByteString.EMPTY;
                this.spentByTransactionHash_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                TransactionOutput.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                TransactionOutput.super.clear();
                this.value_ = 0;
                this.bitField0_ &= -2;
                this.scriptBytes_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                this.spentByTransactionHash_ = ByteString.EMPTY;
                this.bitField0_ &= -5;
                this.spentByTransactionIndex_ = 0;
                this.bitField0_ &= -9;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_wallet_TransactionOutput_descriptor;
            }

            public TransactionOutput getDefaultInstanceForType() {
                return TransactionOutput.getDefaultInstance();
            }

            public TransactionOutput build() {
                TransactionOutput buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public TransactionOutput buildPartial() {
                TransactionOutput transactionOutput = new TransactionOutput((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                transactionOutput.value_ = this.value_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                transactionOutput.scriptBytes_ = this.scriptBytes_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                transactionOutput.spentByTransactionHash_ = this.spentByTransactionHash_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                transactionOutput.spentByTransactionIndex_ = this.spentByTransactionIndex_;
                transactionOutput.bitField0_ = i2;
                onBuilt();
                return transactionOutput;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof TransactionOutput) {
                    return mergeFrom((TransactionOutput) message);
                }
                TransactionOutput.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(TransactionOutput transactionOutput) {
                if (transactionOutput == TransactionOutput.getDefaultInstance()) {
                    return this;
                }
                if (transactionOutput.hasValue()) {
                    setValue(transactionOutput.getValue());
                }
                if (transactionOutput.hasScriptBytes()) {
                    setScriptBytes(transactionOutput.getScriptBytes());
                }
                if (transactionOutput.hasSpentByTransactionHash()) {
                    setSpentByTransactionHash(transactionOutput.getSpentByTransactionHash());
                }
                if (transactionOutput.hasSpentByTransactionIndex()) {
                    setSpentByTransactionIndex(transactionOutput.getSpentByTransactionIndex());
                }
                mergeUnknownFields(transactionOutput.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (hasValue() && hasScriptBytes()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                TransactionOutput transactionOutput;
                TransactionOutput transactionOutput2 = null;
                try {
                    TransactionOutput transactionOutput3 = (TransactionOutput) TransactionOutput.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (transactionOutput3 != null) {
                        mergeFrom(transactionOutput3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    transactionOutput = (TransactionOutput) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    transactionOutput2 = transactionOutput;
                }
                if (transactionOutput2 != null) {
                    mergeFrom(transactionOutput2);
                }
                throw th;
            }

            public boolean hasValue() {
                return (this.bitField0_ & 1) == 1;
            }

            public long getValue() {
                return this.value_;
            }

            public Builder setValue(long j) {
                this.bitField0_ |= 1;
                this.value_ = j;
                onChanged();
                return this;
            }

            public Builder clearValue() {
                this.bitField0_ &= -2;
                this.value_ = 0;
                onChanged();
                return this;
            }

            public boolean hasScriptBytes() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getScriptBytes() {
                return this.scriptBytes_;
            }

            public Builder setScriptBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.scriptBytes_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearScriptBytes() {
                this.bitField0_ &= -3;
                this.scriptBytes_ = TransactionOutput.getDefaultInstance().getScriptBytes();
                onChanged();
                return this;
            }

            public boolean hasSpentByTransactionHash() {
                return (this.bitField0_ & 4) == 4;
            }

            public ByteString getSpentByTransactionHash() {
                return this.spentByTransactionHash_;
            }

            public Builder setSpentByTransactionHash(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 4;
                    this.spentByTransactionHash_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearSpentByTransactionHash() {
                this.bitField0_ &= -5;
                this.spentByTransactionHash_ = TransactionOutput.getDefaultInstance().getSpentByTransactionHash();
                onChanged();
                return this;
            }

            public boolean hasSpentByTransactionIndex() {
                return (this.bitField0_ & 8) == 8;
            }

            public int getSpentByTransactionIndex() {
                return this.spentByTransactionIndex_;
            }

            public Builder setSpentByTransactionIndex(int i) {
                this.bitField0_ |= 8;
                this.spentByTransactionIndex_ = i;
                onChanged();
                return this;
            }

            public Builder clearSpentByTransactionIndex() {
                this.bitField0_ &= -9;
                this.spentByTransactionIndex_ = 0;
                onChanged();
                return this;
            }
        }

        private TransactionOutput(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private TransactionOutput(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static TransactionOutput getDefaultInstance() {
            return defaultInstance;
        }

        public TransactionOutput getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r6v0, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$TransactionOutput] */
        private TransactionOutput(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 8) {
                            this.bitField0_ |= 1;
                            this.value_ = codedInputStream.readInt64();
                        } else if (readTag == 18) {
                            this.bitField0_ |= 2;
                            this.scriptBytes_ = codedInputStream.readBytes();
                        } else if (readTag == 26) {
                            this.bitField0_ |= 4;
                            this.spentByTransactionHash_ = codedInputStream.readBytes();
                        } else if (readTag == 32) {
                            this.bitField0_ |= 8;
                            this.spentByTransactionIndex_ = codedInputStream.readInt32();
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
            return Protos.internal_static_wallet_TransactionOutput_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_wallet_TransactionOutput_fieldAccessorTable.ensureFieldAccessorsInitialized(TransactionOutput.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<TransactionOutput> getParserForType() {
            return PARSER;
        }

        public boolean hasValue() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getValue() {
            return this.value_;
        }

        public boolean hasScriptBytes() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getScriptBytes() {
            return this.scriptBytes_;
        }

        public boolean hasSpentByTransactionHash() {
            return (this.bitField0_ & 4) == 4;
        }

        public ByteString getSpentByTransactionHash() {
            return this.spentByTransactionHash_;
        }

        public boolean hasSpentByTransactionIndex() {
            return (this.bitField0_ & 8) == 8;
        }

        public int getSpentByTransactionIndex() {
            return this.spentByTransactionIndex_;
        }

        private void initFields() {
            this.value_ = 0;
            this.scriptBytes_ = ByteString.EMPTY;
            this.spentByTransactionHash_ = ByteString.EMPTY;
            this.spentByTransactionIndex_ = 0;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasValue()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasScriptBytes()) {
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
                codedOutputStream.writeInt64(1, this.value_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.scriptBytes_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBytes(3, this.spentByTransactionHash_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeInt32(4, this.spentByTransactionIndex_);
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
                i2 = 0 + CodedOutputStream.computeInt64Size(1, this.value_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(2, this.scriptBytes_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeBytesSize(3, this.spentByTransactionHash_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeInt32Size(4, this.spentByTransactionIndex_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static TransactionOutput parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (TransactionOutput) PARSER.parseFrom(byteString);
        }

        public static TransactionOutput parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TransactionOutput) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static TransactionOutput parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (TransactionOutput) PARSER.parseFrom(bArr);
        }

        public static TransactionOutput parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TransactionOutput) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static TransactionOutput parseFrom(InputStream inputStream) throws IOException {
            return (TransactionOutput) PARSER.parseFrom(inputStream);
        }

        public static TransactionOutput parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransactionOutput) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static TransactionOutput parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (TransactionOutput) PARSER.parseDelimitedFrom(inputStream);
        }

        public static TransactionOutput parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransactionOutput) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static TransactionOutput parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (TransactionOutput) PARSER.parseFrom(codedInputStream);
        }

        public static TransactionOutput parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransactionOutput) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(TransactionOutput transactionOutput) {
            return newBuilder().mergeFrom(transactionOutput);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface TransactionOutputOrBuilder extends MessageOrBuilder {
        ByteString getScriptBytes();

        ByteString getSpentByTransactionHash();

        int getSpentByTransactionIndex();

        long getValue();

        boolean hasScriptBytes();

        boolean hasSpentByTransactionHash();

        boolean hasSpentByTransactionIndex();

        boolean hasValue();
    }

    public static final class TransactionSigner extends GeneratedMessage implements TransactionSignerOrBuilder {
        public static final int CLASS_NAME_FIELD_NUMBER = 1;
        public static final int DATA_FIELD_NUMBER = 2;
        public static Parser<TransactionSigner> PARSER = new AbstractParser<TransactionSigner>() {
            public TransactionSigner parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new TransactionSigner(codedInputStream, extensionRegistryLite);
            }
        };
        private static final TransactionSigner defaultInstance = new TransactionSigner(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public Object className_;
        /* access modifiers changed from: private */
        public ByteString data_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements TransactionSignerOrBuilder {
            private int bitField0_;
            private Object className_;
            private ByteString data_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_wallet_TransactionSigner_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_wallet_TransactionSigner_fieldAccessorTable.ensureFieldAccessorsInitialized(TransactionSigner.class, Builder.class);
            }

            private Builder() {
                this.className_ = "";
                this.data_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.className_ = "";
                this.data_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                TransactionSigner.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                TransactionSigner.super.clear();
                this.className_ = "";
                this.bitField0_ &= -2;
                this.data_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_wallet_TransactionSigner_descriptor;
            }

            public TransactionSigner getDefaultInstanceForType() {
                return TransactionSigner.getDefaultInstance();
            }

            public TransactionSigner build() {
                TransactionSigner buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public TransactionSigner buildPartial() {
                TransactionSigner transactionSigner = new TransactionSigner((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                transactionSigner.className_ = this.className_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                transactionSigner.data_ = this.data_;
                transactionSigner.bitField0_ = i2;
                onBuilt();
                return transactionSigner;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof TransactionSigner) {
                    return mergeFrom((TransactionSigner) message);
                }
                TransactionSigner.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(TransactionSigner transactionSigner) {
                if (transactionSigner == TransactionSigner.getDefaultInstance()) {
                    return this;
                }
                if (transactionSigner.hasClassName()) {
                    this.bitField0_ |= 1;
                    this.className_ = transactionSigner.className_;
                    onChanged();
                }
                if (transactionSigner.hasData()) {
                    setData(transactionSigner.getData());
                }
                mergeUnknownFields(transactionSigner.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                return hasClassName();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                TransactionSigner transactionSigner;
                TransactionSigner transactionSigner2 = null;
                try {
                    TransactionSigner transactionSigner3 = (TransactionSigner) TransactionSigner.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (transactionSigner3 != null) {
                        mergeFrom(transactionSigner3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    transactionSigner = (TransactionSigner) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    transactionSigner2 = transactionSigner;
                }
                if (transactionSigner2 != null) {
                    mergeFrom(transactionSigner2);
                }
                throw th;
            }

            public boolean hasClassName() {
                return (this.bitField0_ & 1) == 1;
            }

            public String getClassName() {
                Object obj = this.className_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.className_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getClassNameBytes() {
                Object obj = this.className_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.className_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setClassName(String str) {
                if (str != null) {
                    this.bitField0_ |= 1;
                    this.className_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearClassName() {
                this.bitField0_ &= -2;
                this.className_ = TransactionSigner.getDefaultInstance().getClassName();
                onChanged();
                return this;
            }

            public Builder setClassNameBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.className_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public boolean hasData() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getData() {
                return this.data_;
            }

            public Builder setData(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.data_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearData() {
                this.bitField0_ &= -3;
                this.data_ = TransactionSigner.getDefaultInstance().getData();
                onChanged();
                return this;
            }
        }

        private TransactionSigner(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private TransactionSigner(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static TransactionSigner getDefaultInstance() {
            return defaultInstance;
        }

        public TransactionSigner getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [org.bitcoinj.wallet.Protos$TransactionSigner, com.google.protobuf.MessageLite] */
        private TransactionSigner(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.className_ = readBytes;
                        } else if (readTag == 18) {
                            this.bitField0_ |= 2;
                            this.data_ = codedInputStream.readBytes();
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
            return Protos.internal_static_wallet_TransactionSigner_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_wallet_TransactionSigner_fieldAccessorTable.ensureFieldAccessorsInitialized(TransactionSigner.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<TransactionSigner> getParserForType() {
            return PARSER;
        }

        public boolean hasClassName() {
            return (this.bitField0_ & 1) == 1;
        }

        public String getClassName() {
            Object obj = this.className_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.className_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getClassNameBytes() {
            Object obj = this.className_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.className_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasData() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getData() {
            return this.data_;
        }

        private void initFields() {
            this.className_ = "";
            this.data_ = ByteString.EMPTY;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasClassName()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, getClassNameBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.data_);
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
                i2 = 0 + CodedOutputStream.computeBytesSize(1, getClassNameBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(2, this.data_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static TransactionSigner parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (TransactionSigner) PARSER.parseFrom(byteString);
        }

        public static TransactionSigner parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TransactionSigner) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static TransactionSigner parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (TransactionSigner) PARSER.parseFrom(bArr);
        }

        public static TransactionSigner parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TransactionSigner) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static TransactionSigner parseFrom(InputStream inputStream) throws IOException {
            return (TransactionSigner) PARSER.parseFrom(inputStream);
        }

        public static TransactionSigner parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransactionSigner) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static TransactionSigner parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (TransactionSigner) PARSER.parseDelimitedFrom(inputStream);
        }

        public static TransactionSigner parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransactionSigner) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static TransactionSigner parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (TransactionSigner) PARSER.parseFrom(codedInputStream);
        }

        public static TransactionSigner parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TransactionSigner) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(TransactionSigner transactionSigner) {
            return newBuilder().mergeFrom(transactionSigner);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface TransactionSignerOrBuilder extends MessageOrBuilder {
        String getClassName();

        ByteString getClassNameBytes();

        ByteString getData();

        boolean hasClassName();

        boolean hasData();
    }

    /* renamed from: org.bitcoinj.wallet.Protos$Wallet */
    public static final class C3527Wallet extends GeneratedMessage implements WalletOrBuilder {
        public static final int DESCRIPTION_FIELD_NUMBER = 11;
        public static final int ENCRYPTION_PARAMETERS_FIELD_NUMBER = 6;
        public static final int ENCRYPTION_TYPE_FIELD_NUMBER = 5;
        public static final int EXTENSION_FIELD_NUMBER = 10;
        public static final int KEY_FIELD_NUMBER = 3;
        public static final int KEY_ROTATION_TIME_FIELD_NUMBER = 13;
        public static final int LAST_SEEN_BLOCK_HASH_FIELD_NUMBER = 2;
        public static final int LAST_SEEN_BLOCK_HEIGHT_FIELD_NUMBER = 12;
        public static final int LAST_SEEN_BLOCK_TIME_SECS_FIELD_NUMBER = 14;
        public static final int NETWORK_IDENTIFIER_FIELD_NUMBER = 1;
        public static Parser<C3527Wallet> PARSER = new AbstractParser<C3527Wallet>() {
            public C3527Wallet parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new C3527Wallet(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int TAGS_FIELD_NUMBER = 16;
        public static final int TRANSACTION_FIELD_NUMBER = 4;
        public static final int TRANSACTION_SIGNERS_FIELD_NUMBER = 17;
        public static final int VERSION_FIELD_NUMBER = 7;
        public static final int WATCHED_SCRIPT_FIELD_NUMBER = 15;
        private static final C3527Wallet defaultInstance = new C3527Wallet(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public Object description_;
        /* access modifiers changed from: private */
        public ScryptParameters encryptionParameters_;
        /* access modifiers changed from: private */
        public EncryptionType encryptionType_;
        /* access modifiers changed from: private */
        public List<Extension> extension_;
        /* access modifiers changed from: private */
        public long keyRotationTime_;
        /* access modifiers changed from: private */
        public List<C3509Key> key_;
        /* access modifiers changed from: private */
        public ByteString lastSeenBlockHash_;
        /* access modifiers changed from: private */
        public int lastSeenBlockHeight_;
        /* access modifiers changed from: private */
        public long lastSeenBlockTimeSecs_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public Object networkIdentifier_;
        /* access modifiers changed from: private */
        public List<Tag> tags_;
        /* access modifiers changed from: private */
        public List<TransactionSigner> transactionSigners_;
        /* access modifiers changed from: private */
        public List<Transaction> transaction_;
        private final UnknownFieldSet unknownFields;
        /* access modifiers changed from: private */
        public int version_;
        /* access modifiers changed from: private */
        public List<Script> watchedScript_;

        /* renamed from: org.bitcoinj.wallet.Protos$Wallet$Builder */
        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements WalletOrBuilder {
            private int bitField0_;
            private Object description_;
            private SingleFieldBuilder<ScryptParameters, Builder, ScryptParametersOrBuilder> encryptionParametersBuilder_;
            private ScryptParameters encryptionParameters_;
            private EncryptionType encryptionType_;
            private RepeatedFieldBuilder<Extension, Builder, ExtensionOrBuilder> extensionBuilder_;
            private List<Extension> extension_;
            private RepeatedFieldBuilder<C3509Key, Builder, KeyOrBuilder> keyBuilder_;
            private long keyRotationTime_;
            private List<C3509Key> key_;
            private ByteString lastSeenBlockHash_;
            private int lastSeenBlockHeight_;
            private long lastSeenBlockTimeSecs_;
            private Object networkIdentifier_;
            private RepeatedFieldBuilder<Tag, Builder, TagOrBuilder> tagsBuilder_;
            private List<Tag> tags_;
            private RepeatedFieldBuilder<Transaction, Builder, TransactionOrBuilder> transactionBuilder_;
            private RepeatedFieldBuilder<TransactionSigner, Builder, TransactionSignerOrBuilder> transactionSignersBuilder_;
            private List<TransactionSigner> transactionSigners_;
            private List<Transaction> transaction_;
            private int version_;
            private RepeatedFieldBuilder<Script, Builder, ScriptOrBuilder> watchedScriptBuilder_;
            private List<Script> watchedScript_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_wallet_Wallet_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_wallet_Wallet_fieldAccessorTable.ensureFieldAccessorsInitialized(C3527Wallet.class, Builder.class);
            }

            private Builder() {
                String str = "";
                this.networkIdentifier_ = str;
                this.lastSeenBlockHash_ = ByteString.EMPTY;
                this.key_ = Collections.emptyList();
                this.transaction_ = Collections.emptyList();
                this.watchedScript_ = Collections.emptyList();
                this.encryptionType_ = EncryptionType.UNENCRYPTED;
                this.encryptionParameters_ = ScryptParameters.getDefaultInstance();
                this.version_ = 1;
                this.extension_ = Collections.emptyList();
                this.description_ = str;
                this.tags_ = Collections.emptyList();
                this.transactionSigners_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                String str = "";
                this.networkIdentifier_ = str;
                this.lastSeenBlockHash_ = ByteString.EMPTY;
                this.key_ = Collections.emptyList();
                this.transaction_ = Collections.emptyList();
                this.watchedScript_ = Collections.emptyList();
                this.encryptionType_ = EncryptionType.UNENCRYPTED;
                this.encryptionParameters_ = ScryptParameters.getDefaultInstance();
                this.version_ = 1;
                this.extension_ = Collections.emptyList();
                this.description_ = str;
                this.tags_ = Collections.emptyList();
                this.transactionSigners_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (C3527Wallet.alwaysUseFieldBuilders) {
                    getKeyFieldBuilder();
                    getTransactionFieldBuilder();
                    getWatchedScriptFieldBuilder();
                    getEncryptionParametersFieldBuilder();
                    getExtensionFieldBuilder();
                    getTagsFieldBuilder();
                    getTransactionSignersFieldBuilder();
                }
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                C3527Wallet.super.clear();
                String str = "";
                this.networkIdentifier_ = str;
                this.bitField0_ &= -2;
                this.lastSeenBlockHash_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                this.lastSeenBlockHeight_ = 0;
                this.bitField0_ &= -5;
                this.lastSeenBlockTimeSecs_ = 0;
                this.bitField0_ &= -9;
                RepeatedFieldBuilder<C3509Key, Builder, KeyOrBuilder> repeatedFieldBuilder = this.keyBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.key_ = Collections.emptyList();
                    this.bitField0_ &= -17;
                } else {
                    repeatedFieldBuilder.clear();
                }
                RepeatedFieldBuilder<Transaction, Builder, TransactionOrBuilder> repeatedFieldBuilder2 = this.transactionBuilder_;
                if (repeatedFieldBuilder2 == null) {
                    this.transaction_ = Collections.emptyList();
                    this.bitField0_ &= -33;
                } else {
                    repeatedFieldBuilder2.clear();
                }
                RepeatedFieldBuilder<Script, Builder, ScriptOrBuilder> repeatedFieldBuilder3 = this.watchedScriptBuilder_;
                if (repeatedFieldBuilder3 == null) {
                    this.watchedScript_ = Collections.emptyList();
                    this.bitField0_ &= -65;
                } else {
                    repeatedFieldBuilder3.clear();
                }
                this.encryptionType_ = EncryptionType.UNENCRYPTED;
                this.bitField0_ &= -129;
                SingleFieldBuilder<ScryptParameters, Builder, ScryptParametersOrBuilder> singleFieldBuilder = this.encryptionParametersBuilder_;
                if (singleFieldBuilder == null) {
                    this.encryptionParameters_ = ScryptParameters.getDefaultInstance();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -257;
                this.version_ = 1;
                this.bitField0_ &= -513;
                RepeatedFieldBuilder<Extension, Builder, ExtensionOrBuilder> repeatedFieldBuilder4 = this.extensionBuilder_;
                if (repeatedFieldBuilder4 == null) {
                    this.extension_ = Collections.emptyList();
                    this.bitField0_ &= RESULT.E_TEEKM_JSON_OUT_ADDR_LEN;
                } else {
                    repeatedFieldBuilder4.clear();
                }
                this.description_ = str;
                this.bitField0_ &= -2049;
                this.keyRotationTime_ = 0;
                this.bitField0_ &= -4097;
                RepeatedFieldBuilder<Tag, Builder, TagOrBuilder> repeatedFieldBuilder5 = this.tagsBuilder_;
                if (repeatedFieldBuilder5 == null) {
                    this.tags_ = Collections.emptyList();
                    this.bitField0_ &= -8193;
                } else {
                    repeatedFieldBuilder5.clear();
                }
                RepeatedFieldBuilder<TransactionSigner, Builder, TransactionSignerOrBuilder> repeatedFieldBuilder6 = this.transactionSignersBuilder_;
                if (repeatedFieldBuilder6 == null) {
                    this.transactionSigners_ = Collections.emptyList();
                    this.bitField0_ &= -16385;
                } else {
                    repeatedFieldBuilder6.clear();
                }
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_wallet_Wallet_descriptor;
            }

            public C3527Wallet getDefaultInstanceForType() {
                return C3527Wallet.getDefaultInstance();
            }

            public C3527Wallet build() {
                C3527Wallet buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public C3527Wallet buildPartial() {
                C3527Wallet wallet = new C3527Wallet((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                wallet.networkIdentifier_ = this.networkIdentifier_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                wallet.lastSeenBlockHash_ = this.lastSeenBlockHash_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                wallet.lastSeenBlockHeight_ = this.lastSeenBlockHeight_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                wallet.lastSeenBlockTimeSecs_ = this.lastSeenBlockTimeSecs_;
                RepeatedFieldBuilder<C3509Key, Builder, KeyOrBuilder> repeatedFieldBuilder = this.keyBuilder_;
                if (repeatedFieldBuilder == null) {
                    if ((this.bitField0_ & 16) == 16) {
                        this.key_ = Collections.unmodifiableList(this.key_);
                        this.bitField0_ &= -17;
                    }
                    wallet.key_ = this.key_;
                } else {
                    wallet.key_ = repeatedFieldBuilder.build();
                }
                RepeatedFieldBuilder<Transaction, Builder, TransactionOrBuilder> repeatedFieldBuilder2 = this.transactionBuilder_;
                if (repeatedFieldBuilder2 == null) {
                    if ((this.bitField0_ & 32) == 32) {
                        this.transaction_ = Collections.unmodifiableList(this.transaction_);
                        this.bitField0_ &= -33;
                    }
                    wallet.transaction_ = this.transaction_;
                } else {
                    wallet.transaction_ = repeatedFieldBuilder2.build();
                }
                RepeatedFieldBuilder<Script, Builder, ScriptOrBuilder> repeatedFieldBuilder3 = this.watchedScriptBuilder_;
                if (repeatedFieldBuilder3 == null) {
                    if ((this.bitField0_ & 64) == 64) {
                        this.watchedScript_ = Collections.unmodifiableList(this.watchedScript_);
                        this.bitField0_ &= -65;
                    }
                    wallet.watchedScript_ = this.watchedScript_;
                } else {
                    wallet.watchedScript_ = repeatedFieldBuilder3.build();
                }
                if ((i & 128) == 128) {
                    i2 |= 16;
                }
                wallet.encryptionType_ = this.encryptionType_;
                if ((i & 256) == 256) {
                    i2 |= 32;
                }
                SingleFieldBuilder<ScryptParameters, Builder, ScryptParametersOrBuilder> singleFieldBuilder = this.encryptionParametersBuilder_;
                if (singleFieldBuilder == null) {
                    wallet.encryptionParameters_ = this.encryptionParameters_;
                } else {
                    wallet.encryptionParameters_ = singleFieldBuilder.build();
                }
                if ((i & 512) == 512) {
                    i2 |= 64;
                }
                wallet.version_ = this.version_;
                RepeatedFieldBuilder<Extension, Builder, ExtensionOrBuilder> repeatedFieldBuilder4 = this.extensionBuilder_;
                if (repeatedFieldBuilder4 == null) {
                    if ((this.bitField0_ & 1024) == 1024) {
                        this.extension_ = Collections.unmodifiableList(this.extension_);
                        this.bitField0_ &= RESULT.E_TEEKM_JSON_OUT_ADDR_LEN;
                    }
                    wallet.extension_ = this.extension_;
                } else {
                    wallet.extension_ = repeatedFieldBuilder4.build();
                }
                if ((i & 2048) == 2048) {
                    i2 |= 128;
                }
                wallet.description_ = this.description_;
                if ((i & 4096) == 4096) {
                    i2 |= 256;
                }
                wallet.keyRotationTime_ = this.keyRotationTime_;
                RepeatedFieldBuilder<Tag, Builder, TagOrBuilder> repeatedFieldBuilder5 = this.tagsBuilder_;
                if (repeatedFieldBuilder5 == null) {
                    if ((this.bitField0_ & 8192) == 8192) {
                        this.tags_ = Collections.unmodifiableList(this.tags_);
                        this.bitField0_ &= -8193;
                    }
                    wallet.tags_ = this.tags_;
                } else {
                    wallet.tags_ = repeatedFieldBuilder5.build();
                }
                RepeatedFieldBuilder<TransactionSigner, Builder, TransactionSignerOrBuilder> repeatedFieldBuilder6 = this.transactionSignersBuilder_;
                if (repeatedFieldBuilder6 == null) {
                    if ((this.bitField0_ & 16384) == 16384) {
                        this.transactionSigners_ = Collections.unmodifiableList(this.transactionSigners_);
                        this.bitField0_ &= -16385;
                    }
                    wallet.transactionSigners_ = this.transactionSigners_;
                } else {
                    wallet.transactionSigners_ = repeatedFieldBuilder6.build();
                }
                wallet.bitField0_ = i2;
                onBuilt();
                return wallet;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof C3527Wallet) {
                    return mergeFrom((C3527Wallet) message);
                }
                C3527Wallet.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(C3527Wallet wallet) {
                if (wallet == C3527Wallet.getDefaultInstance()) {
                    return this;
                }
                if (wallet.hasNetworkIdentifier()) {
                    this.bitField0_ |= 1;
                    this.networkIdentifier_ = wallet.networkIdentifier_;
                    onChanged();
                }
                if (wallet.hasLastSeenBlockHash()) {
                    setLastSeenBlockHash(wallet.getLastSeenBlockHash());
                }
                if (wallet.hasLastSeenBlockHeight()) {
                    setLastSeenBlockHeight(wallet.getLastSeenBlockHeight());
                }
                if (wallet.hasLastSeenBlockTimeSecs()) {
                    setLastSeenBlockTimeSecs(wallet.getLastSeenBlockTimeSecs());
                }
                RepeatedFieldBuilder<TransactionSigner, Builder, TransactionSignerOrBuilder> repeatedFieldBuilder = null;
                if (this.keyBuilder_ == null) {
                    if (!wallet.key_.isEmpty()) {
                        if (this.key_.isEmpty()) {
                            this.key_ = wallet.key_;
                            this.bitField0_ &= -17;
                        } else {
                            ensureKeyIsMutable();
                            this.key_.addAll(wallet.key_);
                        }
                        onChanged();
                    }
                } else if (!wallet.key_.isEmpty()) {
                    if (this.keyBuilder_.isEmpty()) {
                        this.keyBuilder_.dispose();
                        this.keyBuilder_ = null;
                        this.key_ = wallet.key_;
                        this.bitField0_ &= -17;
                        this.keyBuilder_ = C3527Wallet.alwaysUseFieldBuilders ? getKeyFieldBuilder() : null;
                    } else {
                        this.keyBuilder_.addAllMessages(wallet.key_);
                    }
                }
                if (this.transactionBuilder_ == null) {
                    if (!wallet.transaction_.isEmpty()) {
                        if (this.transaction_.isEmpty()) {
                            this.transaction_ = wallet.transaction_;
                            this.bitField0_ &= -33;
                        } else {
                            ensureTransactionIsMutable();
                            this.transaction_.addAll(wallet.transaction_);
                        }
                        onChanged();
                    }
                } else if (!wallet.transaction_.isEmpty()) {
                    if (this.transactionBuilder_.isEmpty()) {
                        this.transactionBuilder_.dispose();
                        this.transactionBuilder_ = null;
                        this.transaction_ = wallet.transaction_;
                        this.bitField0_ &= -33;
                        this.transactionBuilder_ = C3527Wallet.alwaysUseFieldBuilders ? getTransactionFieldBuilder() : null;
                    } else {
                        this.transactionBuilder_.addAllMessages(wallet.transaction_);
                    }
                }
                if (this.watchedScriptBuilder_ == null) {
                    if (!wallet.watchedScript_.isEmpty()) {
                        if (this.watchedScript_.isEmpty()) {
                            this.watchedScript_ = wallet.watchedScript_;
                            this.bitField0_ &= -65;
                        } else {
                            ensureWatchedScriptIsMutable();
                            this.watchedScript_.addAll(wallet.watchedScript_);
                        }
                        onChanged();
                    }
                } else if (!wallet.watchedScript_.isEmpty()) {
                    if (this.watchedScriptBuilder_.isEmpty()) {
                        this.watchedScriptBuilder_.dispose();
                        this.watchedScriptBuilder_ = null;
                        this.watchedScript_ = wallet.watchedScript_;
                        this.bitField0_ &= -65;
                        this.watchedScriptBuilder_ = C3527Wallet.alwaysUseFieldBuilders ? getWatchedScriptFieldBuilder() : null;
                    } else {
                        this.watchedScriptBuilder_.addAllMessages(wallet.watchedScript_);
                    }
                }
                if (wallet.hasEncryptionType()) {
                    setEncryptionType(wallet.getEncryptionType());
                }
                if (wallet.hasEncryptionParameters()) {
                    mergeEncryptionParameters(wallet.getEncryptionParameters());
                }
                if (wallet.hasVersion()) {
                    setVersion(wallet.getVersion());
                }
                if (this.extensionBuilder_ == null) {
                    if (!wallet.extension_.isEmpty()) {
                        if (this.extension_.isEmpty()) {
                            this.extension_ = wallet.extension_;
                            this.bitField0_ &= RESULT.E_TEEKM_JSON_OUT_ADDR_LEN;
                        } else {
                            ensureExtensionIsMutable();
                            this.extension_.addAll(wallet.extension_);
                        }
                        onChanged();
                    }
                } else if (!wallet.extension_.isEmpty()) {
                    if (this.extensionBuilder_.isEmpty()) {
                        this.extensionBuilder_.dispose();
                        this.extensionBuilder_ = null;
                        this.extension_ = wallet.extension_;
                        this.bitField0_ &= RESULT.E_TEEKM_JSON_OUT_ADDR_LEN;
                        this.extensionBuilder_ = C3527Wallet.alwaysUseFieldBuilders ? getExtensionFieldBuilder() : null;
                    } else {
                        this.extensionBuilder_.addAllMessages(wallet.extension_);
                    }
                }
                if (wallet.hasDescription()) {
                    this.bitField0_ |= 2048;
                    this.description_ = wallet.description_;
                    onChanged();
                }
                if (wallet.hasKeyRotationTime()) {
                    setKeyRotationTime(wallet.getKeyRotationTime());
                }
                if (this.tagsBuilder_ == null) {
                    if (!wallet.tags_.isEmpty()) {
                        if (this.tags_.isEmpty()) {
                            this.tags_ = wallet.tags_;
                            this.bitField0_ &= -8193;
                        } else {
                            ensureTagsIsMutable();
                            this.tags_.addAll(wallet.tags_);
                        }
                        onChanged();
                    }
                } else if (!wallet.tags_.isEmpty()) {
                    if (this.tagsBuilder_.isEmpty()) {
                        this.tagsBuilder_.dispose();
                        this.tagsBuilder_ = null;
                        this.tags_ = wallet.tags_;
                        this.bitField0_ &= -8193;
                        this.tagsBuilder_ = C3527Wallet.alwaysUseFieldBuilders ? getTagsFieldBuilder() : null;
                    } else {
                        this.tagsBuilder_.addAllMessages(wallet.tags_);
                    }
                }
                if (this.transactionSignersBuilder_ == null) {
                    if (!wallet.transactionSigners_.isEmpty()) {
                        if (this.transactionSigners_.isEmpty()) {
                            this.transactionSigners_ = wallet.transactionSigners_;
                            this.bitField0_ &= -16385;
                        } else {
                            ensureTransactionSignersIsMutable();
                            this.transactionSigners_.addAll(wallet.transactionSigners_);
                        }
                        onChanged();
                    }
                } else if (!wallet.transactionSigners_.isEmpty()) {
                    if (this.transactionSignersBuilder_.isEmpty()) {
                        this.transactionSignersBuilder_.dispose();
                        this.transactionSignersBuilder_ = null;
                        this.transactionSigners_ = wallet.transactionSigners_;
                        this.bitField0_ &= -16385;
                        if (C3527Wallet.alwaysUseFieldBuilders) {
                            repeatedFieldBuilder = getTransactionSignersFieldBuilder();
                        }
                        this.transactionSignersBuilder_ = repeatedFieldBuilder;
                    } else {
                        this.transactionSignersBuilder_.addAllMessages(wallet.transactionSigners_);
                    }
                }
                mergeUnknownFields(wallet.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (!hasNetworkIdentifier()) {
                    return false;
                }
                for (int i = 0; i < getKeyCount(); i++) {
                    if (!getKey(i).isInitialized()) {
                        return false;
                    }
                }
                for (int i2 = 0; i2 < getTransactionCount(); i2++) {
                    if (!getTransaction(i2).isInitialized()) {
                        return false;
                    }
                }
                for (int i3 = 0; i3 < getWatchedScriptCount(); i3++) {
                    if (!getWatchedScript(i3).isInitialized()) {
                        return false;
                    }
                }
                if (hasEncryptionParameters() && !getEncryptionParameters().isInitialized()) {
                    return false;
                }
                for (int i4 = 0; i4 < getExtensionCount(); i4++) {
                    if (!getExtension(i4).isInitialized()) {
                        return false;
                    }
                }
                for (int i5 = 0; i5 < getTagsCount(); i5++) {
                    if (!getTags(i5).isInitialized()) {
                        return false;
                    }
                }
                for (int i6 = 0; i6 < getTransactionSignersCount(); i6++) {
                    if (!getTransactionSigners(i6).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                C3527Wallet wallet;
                C3527Wallet wallet2 = null;
                try {
                    C3527Wallet wallet3 = (C3527Wallet) C3527Wallet.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (wallet3 != null) {
                        mergeFrom(wallet3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    wallet = (C3527Wallet) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    wallet2 = wallet;
                }
                if (wallet2 != null) {
                    mergeFrom(wallet2);
                }
                throw th;
            }

            public boolean hasNetworkIdentifier() {
                return (this.bitField0_ & 1) == 1;
            }

            public String getNetworkIdentifier() {
                Object obj = this.networkIdentifier_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.networkIdentifier_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getNetworkIdentifierBytes() {
                Object obj = this.networkIdentifier_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.networkIdentifier_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setNetworkIdentifier(String str) {
                if (str != null) {
                    this.bitField0_ |= 1;
                    this.networkIdentifier_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearNetworkIdentifier() {
                this.bitField0_ &= -2;
                this.networkIdentifier_ = C3527Wallet.getDefaultInstance().getNetworkIdentifier();
                onChanged();
                return this;
            }

            public Builder setNetworkIdentifierBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.networkIdentifier_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public boolean hasLastSeenBlockHash() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getLastSeenBlockHash() {
                return this.lastSeenBlockHash_;
            }

            public Builder setLastSeenBlockHash(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.lastSeenBlockHash_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearLastSeenBlockHash() {
                this.bitField0_ &= -3;
                this.lastSeenBlockHash_ = C3527Wallet.getDefaultInstance().getLastSeenBlockHash();
                onChanged();
                return this;
            }

            public boolean hasLastSeenBlockHeight() {
                return (this.bitField0_ & 4) == 4;
            }

            public int getLastSeenBlockHeight() {
                return this.lastSeenBlockHeight_;
            }

            public Builder setLastSeenBlockHeight(int i) {
                this.bitField0_ |= 4;
                this.lastSeenBlockHeight_ = i;
                onChanged();
                return this;
            }

            public Builder clearLastSeenBlockHeight() {
                this.bitField0_ &= -5;
                this.lastSeenBlockHeight_ = 0;
                onChanged();
                return this;
            }

            public boolean hasLastSeenBlockTimeSecs() {
                return (this.bitField0_ & 8) == 8;
            }

            public long getLastSeenBlockTimeSecs() {
                return this.lastSeenBlockTimeSecs_;
            }

            public Builder setLastSeenBlockTimeSecs(long j) {
                this.bitField0_ |= 8;
                this.lastSeenBlockTimeSecs_ = j;
                onChanged();
                return this;
            }

            public Builder clearLastSeenBlockTimeSecs() {
                this.bitField0_ &= -9;
                this.lastSeenBlockTimeSecs_ = 0;
                onChanged();
                return this;
            }

            private void ensureKeyIsMutable() {
                if ((this.bitField0_ & 16) != 16) {
                    this.key_ = new ArrayList(this.key_);
                    this.bitField0_ |= 16;
                }
            }

            public List<C3509Key> getKeyList() {
                RepeatedFieldBuilder<C3509Key, Builder, KeyOrBuilder> repeatedFieldBuilder = this.keyBuilder_;
                if (repeatedFieldBuilder == null) {
                    return Collections.unmodifiableList(this.key_);
                }
                return repeatedFieldBuilder.getMessageList();
            }

            public int getKeyCount() {
                RepeatedFieldBuilder<C3509Key, Builder, KeyOrBuilder> repeatedFieldBuilder = this.keyBuilder_;
                if (repeatedFieldBuilder == null) {
                    return this.key_.size();
                }
                return repeatedFieldBuilder.getCount();
            }

            public C3509Key getKey(int i) {
                RepeatedFieldBuilder<C3509Key, Builder, KeyOrBuilder> repeatedFieldBuilder = this.keyBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (C3509Key) this.key_.get(i);
                }
                return repeatedFieldBuilder.getMessage(i);
            }

            public Builder setKey(int i, C3509Key key) {
                RepeatedFieldBuilder<C3509Key, Builder, KeyOrBuilder> repeatedFieldBuilder = this.keyBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.setMessage(i, key);
                } else if (key != null) {
                    ensureKeyIsMutable();
                    this.key_.set(i, key);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setKey(int i, Builder builder) {
                RepeatedFieldBuilder<C3509Key, Builder, KeyOrBuilder> repeatedFieldBuilder = this.keyBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureKeyIsMutable();
                    this.key_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addKey(C3509Key key) {
                RepeatedFieldBuilder<C3509Key, Builder, KeyOrBuilder> repeatedFieldBuilder = this.keyBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(key);
                } else if (key != null) {
                    ensureKeyIsMutable();
                    this.key_.add(key);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addKey(int i, C3509Key key) {
                RepeatedFieldBuilder<C3509Key, Builder, KeyOrBuilder> repeatedFieldBuilder = this.keyBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(i, key);
                } else if (key != null) {
                    ensureKeyIsMutable();
                    this.key_.add(i, key);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addKey(Builder builder) {
                RepeatedFieldBuilder<C3509Key, Builder, KeyOrBuilder> repeatedFieldBuilder = this.keyBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureKeyIsMutable();
                    this.key_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(builder.build());
                }
                return this;
            }

            public Builder addKey(int i, Builder builder) {
                RepeatedFieldBuilder<C3509Key, Builder, KeyOrBuilder> repeatedFieldBuilder = this.keyBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureKeyIsMutable();
                    this.key_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllKey(Iterable<? extends C3509Key> iterable) {
                RepeatedFieldBuilder<C3509Key, Builder, KeyOrBuilder> repeatedFieldBuilder = this.keyBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureKeyIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.key_);
                    onChanged();
                } else {
                    repeatedFieldBuilder.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearKey() {
                RepeatedFieldBuilder<C3509Key, Builder, KeyOrBuilder> repeatedFieldBuilder = this.keyBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.key_ = Collections.emptyList();
                    this.bitField0_ &= -17;
                    onChanged();
                } else {
                    repeatedFieldBuilder.clear();
                }
                return this;
            }

            public Builder removeKey(int i) {
                RepeatedFieldBuilder<C3509Key, Builder, KeyOrBuilder> repeatedFieldBuilder = this.keyBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureKeyIsMutable();
                    this.key_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilder.remove(i);
                }
                return this;
            }

            public Builder getKeyBuilder(int i) {
                return getKeyFieldBuilder().getBuilder(i);
            }

            public KeyOrBuilder getKeyOrBuilder(int i) {
                RepeatedFieldBuilder<C3509Key, Builder, KeyOrBuilder> repeatedFieldBuilder = this.keyBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (KeyOrBuilder) this.key_.get(i);
                }
                return repeatedFieldBuilder.getMessageOrBuilder(i);
            }

            public List<? extends KeyOrBuilder> getKeyOrBuilderList() {
                RepeatedFieldBuilder<C3509Key, Builder, KeyOrBuilder> repeatedFieldBuilder = this.keyBuilder_;
                if (repeatedFieldBuilder != null) {
                    return repeatedFieldBuilder.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.key_);
            }

            public Builder addKeyBuilder() {
                return getKeyFieldBuilder().addBuilder(C3509Key.getDefaultInstance());
            }

            public Builder addKeyBuilder(int i) {
                return getKeyFieldBuilder().addBuilder(i, C3509Key.getDefaultInstance());
            }

            public List<Builder> getKeyBuilderList() {
                return getKeyFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<C3509Key, Builder, KeyOrBuilder> getKeyFieldBuilder() {
                if (this.keyBuilder_ == null) {
                    this.keyBuilder_ = new RepeatedFieldBuilder<>(this.key_, (this.bitField0_ & 16) == 16, getParentForChildren(), isClean());
                    this.key_ = null;
                }
                return this.keyBuilder_;
            }

            private void ensureTransactionIsMutable() {
                if ((this.bitField0_ & 32) != 32) {
                    this.transaction_ = new ArrayList(this.transaction_);
                    this.bitField0_ |= 32;
                }
            }

            public List<Transaction> getTransactionList() {
                RepeatedFieldBuilder<Transaction, Builder, TransactionOrBuilder> repeatedFieldBuilder = this.transactionBuilder_;
                if (repeatedFieldBuilder == null) {
                    return Collections.unmodifiableList(this.transaction_);
                }
                return repeatedFieldBuilder.getMessageList();
            }

            public int getTransactionCount() {
                RepeatedFieldBuilder<Transaction, Builder, TransactionOrBuilder> repeatedFieldBuilder = this.transactionBuilder_;
                if (repeatedFieldBuilder == null) {
                    return this.transaction_.size();
                }
                return repeatedFieldBuilder.getCount();
            }

            public Transaction getTransaction(int i) {
                RepeatedFieldBuilder<Transaction, Builder, TransactionOrBuilder> repeatedFieldBuilder = this.transactionBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (Transaction) this.transaction_.get(i);
                }
                return repeatedFieldBuilder.getMessage(i);
            }

            public Builder setTransaction(int i, Transaction transaction) {
                RepeatedFieldBuilder<Transaction, Builder, TransactionOrBuilder> repeatedFieldBuilder = this.transactionBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.setMessage(i, transaction);
                } else if (transaction != null) {
                    ensureTransactionIsMutable();
                    this.transaction_.set(i, transaction);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setTransaction(int i, Builder builder) {
                RepeatedFieldBuilder<Transaction, Builder, TransactionOrBuilder> repeatedFieldBuilder = this.transactionBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTransactionIsMutable();
                    this.transaction_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addTransaction(Transaction transaction) {
                RepeatedFieldBuilder<Transaction, Builder, TransactionOrBuilder> repeatedFieldBuilder = this.transactionBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(transaction);
                } else if (transaction != null) {
                    ensureTransactionIsMutable();
                    this.transaction_.add(transaction);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addTransaction(int i, Transaction transaction) {
                RepeatedFieldBuilder<Transaction, Builder, TransactionOrBuilder> repeatedFieldBuilder = this.transactionBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(i, transaction);
                } else if (transaction != null) {
                    ensureTransactionIsMutable();
                    this.transaction_.add(i, transaction);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addTransaction(Builder builder) {
                RepeatedFieldBuilder<Transaction, Builder, TransactionOrBuilder> repeatedFieldBuilder = this.transactionBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTransactionIsMutable();
                    this.transaction_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(builder.build());
                }
                return this;
            }

            public Builder addTransaction(int i, Builder builder) {
                RepeatedFieldBuilder<Transaction, Builder, TransactionOrBuilder> repeatedFieldBuilder = this.transactionBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTransactionIsMutable();
                    this.transaction_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllTransaction(Iterable<? extends Transaction> iterable) {
                RepeatedFieldBuilder<Transaction, Builder, TransactionOrBuilder> repeatedFieldBuilder = this.transactionBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTransactionIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.transaction_);
                    onChanged();
                } else {
                    repeatedFieldBuilder.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearTransaction() {
                RepeatedFieldBuilder<Transaction, Builder, TransactionOrBuilder> repeatedFieldBuilder = this.transactionBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.transaction_ = Collections.emptyList();
                    this.bitField0_ &= -33;
                    onChanged();
                } else {
                    repeatedFieldBuilder.clear();
                }
                return this;
            }

            public Builder removeTransaction(int i) {
                RepeatedFieldBuilder<Transaction, Builder, TransactionOrBuilder> repeatedFieldBuilder = this.transactionBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTransactionIsMutable();
                    this.transaction_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilder.remove(i);
                }
                return this;
            }

            public Builder getTransactionBuilder(int i) {
                return getTransactionFieldBuilder().getBuilder(i);
            }

            public TransactionOrBuilder getTransactionOrBuilder(int i) {
                RepeatedFieldBuilder<Transaction, Builder, TransactionOrBuilder> repeatedFieldBuilder = this.transactionBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (TransactionOrBuilder) this.transaction_.get(i);
                }
                return repeatedFieldBuilder.getMessageOrBuilder(i);
            }

            public List<? extends TransactionOrBuilder> getTransactionOrBuilderList() {
                RepeatedFieldBuilder<Transaction, Builder, TransactionOrBuilder> repeatedFieldBuilder = this.transactionBuilder_;
                if (repeatedFieldBuilder != null) {
                    return repeatedFieldBuilder.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.transaction_);
            }

            public Builder addTransactionBuilder() {
                return getTransactionFieldBuilder().addBuilder(Transaction.getDefaultInstance());
            }

            public Builder addTransactionBuilder(int i) {
                return getTransactionFieldBuilder().addBuilder(i, Transaction.getDefaultInstance());
            }

            public List<Builder> getTransactionBuilderList() {
                return getTransactionFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<Transaction, Builder, TransactionOrBuilder> getTransactionFieldBuilder() {
                if (this.transactionBuilder_ == null) {
                    this.transactionBuilder_ = new RepeatedFieldBuilder<>(this.transaction_, (this.bitField0_ & 32) == 32, getParentForChildren(), isClean());
                    this.transaction_ = null;
                }
                return this.transactionBuilder_;
            }

            private void ensureWatchedScriptIsMutable() {
                if ((this.bitField0_ & 64) != 64) {
                    this.watchedScript_ = new ArrayList(this.watchedScript_);
                    this.bitField0_ |= 64;
                }
            }

            public List<Script> getWatchedScriptList() {
                RepeatedFieldBuilder<Script, Builder, ScriptOrBuilder> repeatedFieldBuilder = this.watchedScriptBuilder_;
                if (repeatedFieldBuilder == null) {
                    return Collections.unmodifiableList(this.watchedScript_);
                }
                return repeatedFieldBuilder.getMessageList();
            }

            public int getWatchedScriptCount() {
                RepeatedFieldBuilder<Script, Builder, ScriptOrBuilder> repeatedFieldBuilder = this.watchedScriptBuilder_;
                if (repeatedFieldBuilder == null) {
                    return this.watchedScript_.size();
                }
                return repeatedFieldBuilder.getCount();
            }

            public Script getWatchedScript(int i) {
                RepeatedFieldBuilder<Script, Builder, ScriptOrBuilder> repeatedFieldBuilder = this.watchedScriptBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (Script) this.watchedScript_.get(i);
                }
                return repeatedFieldBuilder.getMessage(i);
            }

            public Builder setWatchedScript(int i, Script script) {
                RepeatedFieldBuilder<Script, Builder, ScriptOrBuilder> repeatedFieldBuilder = this.watchedScriptBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.setMessage(i, script);
                } else if (script != null) {
                    ensureWatchedScriptIsMutable();
                    this.watchedScript_.set(i, script);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setWatchedScript(int i, Builder builder) {
                RepeatedFieldBuilder<Script, Builder, ScriptOrBuilder> repeatedFieldBuilder = this.watchedScriptBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureWatchedScriptIsMutable();
                    this.watchedScript_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addWatchedScript(Script script) {
                RepeatedFieldBuilder<Script, Builder, ScriptOrBuilder> repeatedFieldBuilder = this.watchedScriptBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(script);
                } else if (script != null) {
                    ensureWatchedScriptIsMutable();
                    this.watchedScript_.add(script);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addWatchedScript(int i, Script script) {
                RepeatedFieldBuilder<Script, Builder, ScriptOrBuilder> repeatedFieldBuilder = this.watchedScriptBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(i, script);
                } else if (script != null) {
                    ensureWatchedScriptIsMutable();
                    this.watchedScript_.add(i, script);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addWatchedScript(Builder builder) {
                RepeatedFieldBuilder<Script, Builder, ScriptOrBuilder> repeatedFieldBuilder = this.watchedScriptBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureWatchedScriptIsMutable();
                    this.watchedScript_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(builder.build());
                }
                return this;
            }

            public Builder addWatchedScript(int i, Builder builder) {
                RepeatedFieldBuilder<Script, Builder, ScriptOrBuilder> repeatedFieldBuilder = this.watchedScriptBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureWatchedScriptIsMutable();
                    this.watchedScript_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllWatchedScript(Iterable<? extends Script> iterable) {
                RepeatedFieldBuilder<Script, Builder, ScriptOrBuilder> repeatedFieldBuilder = this.watchedScriptBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureWatchedScriptIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.watchedScript_);
                    onChanged();
                } else {
                    repeatedFieldBuilder.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearWatchedScript() {
                RepeatedFieldBuilder<Script, Builder, ScriptOrBuilder> repeatedFieldBuilder = this.watchedScriptBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.watchedScript_ = Collections.emptyList();
                    this.bitField0_ &= -65;
                    onChanged();
                } else {
                    repeatedFieldBuilder.clear();
                }
                return this;
            }

            public Builder removeWatchedScript(int i) {
                RepeatedFieldBuilder<Script, Builder, ScriptOrBuilder> repeatedFieldBuilder = this.watchedScriptBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureWatchedScriptIsMutable();
                    this.watchedScript_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilder.remove(i);
                }
                return this;
            }

            public Builder getWatchedScriptBuilder(int i) {
                return getWatchedScriptFieldBuilder().getBuilder(i);
            }

            public ScriptOrBuilder getWatchedScriptOrBuilder(int i) {
                RepeatedFieldBuilder<Script, Builder, ScriptOrBuilder> repeatedFieldBuilder = this.watchedScriptBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (ScriptOrBuilder) this.watchedScript_.get(i);
                }
                return repeatedFieldBuilder.getMessageOrBuilder(i);
            }

            public List<? extends ScriptOrBuilder> getWatchedScriptOrBuilderList() {
                RepeatedFieldBuilder<Script, Builder, ScriptOrBuilder> repeatedFieldBuilder = this.watchedScriptBuilder_;
                if (repeatedFieldBuilder != null) {
                    return repeatedFieldBuilder.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.watchedScript_);
            }

            public Builder addWatchedScriptBuilder() {
                return getWatchedScriptFieldBuilder().addBuilder(Script.getDefaultInstance());
            }

            public Builder addWatchedScriptBuilder(int i) {
                return getWatchedScriptFieldBuilder().addBuilder(i, Script.getDefaultInstance());
            }

            public List<Builder> getWatchedScriptBuilderList() {
                return getWatchedScriptFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<Script, Builder, ScriptOrBuilder> getWatchedScriptFieldBuilder() {
                if (this.watchedScriptBuilder_ == null) {
                    this.watchedScriptBuilder_ = new RepeatedFieldBuilder<>(this.watchedScript_, (this.bitField0_ & 64) == 64, getParentForChildren(), isClean());
                    this.watchedScript_ = null;
                }
                return this.watchedScriptBuilder_;
            }

            public boolean hasEncryptionType() {
                return (this.bitField0_ & 128) == 128;
            }

            public EncryptionType getEncryptionType() {
                return this.encryptionType_;
            }

            public Builder setEncryptionType(EncryptionType encryptionType) {
                if (encryptionType != null) {
                    this.bitField0_ |= 128;
                    this.encryptionType_ = encryptionType;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearEncryptionType() {
                this.bitField0_ &= -129;
                this.encryptionType_ = EncryptionType.UNENCRYPTED;
                onChanged();
                return this;
            }

            public boolean hasEncryptionParameters() {
                return (this.bitField0_ & 256) == 256;
            }

            public ScryptParameters getEncryptionParameters() {
                SingleFieldBuilder<ScryptParameters, Builder, ScryptParametersOrBuilder> singleFieldBuilder = this.encryptionParametersBuilder_;
                if (singleFieldBuilder == null) {
                    return this.encryptionParameters_;
                }
                return singleFieldBuilder.getMessage();
            }

            public Builder setEncryptionParameters(ScryptParameters scryptParameters) {
                SingleFieldBuilder<ScryptParameters, Builder, ScryptParametersOrBuilder> singleFieldBuilder = this.encryptionParametersBuilder_;
                if (singleFieldBuilder != null) {
                    singleFieldBuilder.setMessage(scryptParameters);
                } else if (scryptParameters != null) {
                    this.encryptionParameters_ = scryptParameters;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 256;
                return this;
            }

            public Builder setEncryptionParameters(Builder builder) {
                SingleFieldBuilder<ScryptParameters, Builder, ScryptParametersOrBuilder> singleFieldBuilder = this.encryptionParametersBuilder_;
                if (singleFieldBuilder == null) {
                    this.encryptionParameters_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builder.build());
                }
                this.bitField0_ |= 256;
                return this;
            }

            public Builder mergeEncryptionParameters(ScryptParameters scryptParameters) {
                SingleFieldBuilder<ScryptParameters, Builder, ScryptParametersOrBuilder> singleFieldBuilder = this.encryptionParametersBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 256) != 256 || this.encryptionParameters_ == ScryptParameters.getDefaultInstance()) {
                        this.encryptionParameters_ = scryptParameters;
                    } else {
                        this.encryptionParameters_ = ScryptParameters.newBuilder(this.encryptionParameters_).mergeFrom(scryptParameters).buildPartial();
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(scryptParameters);
                }
                this.bitField0_ |= 256;
                return this;
            }

            public Builder clearEncryptionParameters() {
                SingleFieldBuilder<ScryptParameters, Builder, ScryptParametersOrBuilder> singleFieldBuilder = this.encryptionParametersBuilder_;
                if (singleFieldBuilder == null) {
                    this.encryptionParameters_ = ScryptParameters.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -257;
                return this;
            }

            public Builder getEncryptionParametersBuilder() {
                this.bitField0_ |= 256;
                onChanged();
                return getEncryptionParametersFieldBuilder().getBuilder();
            }

            public ScryptParametersOrBuilder getEncryptionParametersOrBuilder() {
                SingleFieldBuilder<ScryptParameters, Builder, ScryptParametersOrBuilder> singleFieldBuilder = this.encryptionParametersBuilder_;
                if (singleFieldBuilder != null) {
                    return singleFieldBuilder.getMessageOrBuilder();
                }
                return this.encryptionParameters_;
            }

            private SingleFieldBuilder<ScryptParameters, Builder, ScryptParametersOrBuilder> getEncryptionParametersFieldBuilder() {
                if (this.encryptionParametersBuilder_ == null) {
                    this.encryptionParametersBuilder_ = new SingleFieldBuilder<>(getEncryptionParameters(), getParentForChildren(), isClean());
                    this.encryptionParameters_ = null;
                }
                return this.encryptionParametersBuilder_;
            }

            public boolean hasVersion() {
                return (this.bitField0_ & 512) == 512;
            }

            public int getVersion() {
                return this.version_;
            }

            public Builder setVersion(int i) {
                this.bitField0_ |= 512;
                this.version_ = i;
                onChanged();
                return this;
            }

            public Builder clearVersion() {
                this.bitField0_ &= -513;
                this.version_ = 1;
                onChanged();
                return this;
            }

            private void ensureExtensionIsMutable() {
                if ((this.bitField0_ & 1024) != 1024) {
                    this.extension_ = new ArrayList(this.extension_);
                    this.bitField0_ |= 1024;
                }
            }

            public List<Extension> getExtensionList() {
                RepeatedFieldBuilder<Extension, Builder, ExtensionOrBuilder> repeatedFieldBuilder = this.extensionBuilder_;
                if (repeatedFieldBuilder == null) {
                    return Collections.unmodifiableList(this.extension_);
                }
                return repeatedFieldBuilder.getMessageList();
            }

            public int getExtensionCount() {
                RepeatedFieldBuilder<Extension, Builder, ExtensionOrBuilder> repeatedFieldBuilder = this.extensionBuilder_;
                if (repeatedFieldBuilder == null) {
                    return this.extension_.size();
                }
                return repeatedFieldBuilder.getCount();
            }

            public Extension getExtension(int i) {
                RepeatedFieldBuilder<Extension, Builder, ExtensionOrBuilder> repeatedFieldBuilder = this.extensionBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (Extension) this.extension_.get(i);
                }
                return repeatedFieldBuilder.getMessage(i);
            }

            public Builder setExtension(int i, Extension extension) {
                RepeatedFieldBuilder<Extension, Builder, ExtensionOrBuilder> repeatedFieldBuilder = this.extensionBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.setMessage(i, extension);
                } else if (extension != null) {
                    ensureExtensionIsMutable();
                    this.extension_.set(i, extension);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setExtension(int i, Builder builder) {
                RepeatedFieldBuilder<Extension, Builder, ExtensionOrBuilder> repeatedFieldBuilder = this.extensionBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureExtensionIsMutable();
                    this.extension_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addExtension(Extension extension) {
                RepeatedFieldBuilder<Extension, Builder, ExtensionOrBuilder> repeatedFieldBuilder = this.extensionBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(extension);
                } else if (extension != null) {
                    ensureExtensionIsMutable();
                    this.extension_.add(extension);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addExtension(int i, Extension extension) {
                RepeatedFieldBuilder<Extension, Builder, ExtensionOrBuilder> repeatedFieldBuilder = this.extensionBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(i, extension);
                } else if (extension != null) {
                    ensureExtensionIsMutable();
                    this.extension_.add(i, extension);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addExtension(Builder builder) {
                RepeatedFieldBuilder<Extension, Builder, ExtensionOrBuilder> repeatedFieldBuilder = this.extensionBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureExtensionIsMutable();
                    this.extension_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(builder.build());
                }
                return this;
            }

            public Builder addExtension(int i, Builder builder) {
                RepeatedFieldBuilder<Extension, Builder, ExtensionOrBuilder> repeatedFieldBuilder = this.extensionBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureExtensionIsMutable();
                    this.extension_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllExtension(Iterable<? extends Extension> iterable) {
                RepeatedFieldBuilder<Extension, Builder, ExtensionOrBuilder> repeatedFieldBuilder = this.extensionBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureExtensionIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.extension_);
                    onChanged();
                } else {
                    repeatedFieldBuilder.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearExtension() {
                RepeatedFieldBuilder<Extension, Builder, ExtensionOrBuilder> repeatedFieldBuilder = this.extensionBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.extension_ = Collections.emptyList();
                    this.bitField0_ &= RESULT.E_TEEKM_JSON_OUT_ADDR_LEN;
                    onChanged();
                } else {
                    repeatedFieldBuilder.clear();
                }
                return this;
            }

            public Builder removeExtension(int i) {
                RepeatedFieldBuilder<Extension, Builder, ExtensionOrBuilder> repeatedFieldBuilder = this.extensionBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureExtensionIsMutable();
                    this.extension_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilder.remove(i);
                }
                return this;
            }

            public Builder getExtensionBuilder(int i) {
                return getExtensionFieldBuilder().getBuilder(i);
            }

            public ExtensionOrBuilder getExtensionOrBuilder(int i) {
                RepeatedFieldBuilder<Extension, Builder, ExtensionOrBuilder> repeatedFieldBuilder = this.extensionBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (ExtensionOrBuilder) this.extension_.get(i);
                }
                return repeatedFieldBuilder.getMessageOrBuilder(i);
            }

            public List<? extends ExtensionOrBuilder> getExtensionOrBuilderList() {
                RepeatedFieldBuilder<Extension, Builder, ExtensionOrBuilder> repeatedFieldBuilder = this.extensionBuilder_;
                if (repeatedFieldBuilder != null) {
                    return repeatedFieldBuilder.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.extension_);
            }

            public Builder addExtensionBuilder() {
                return getExtensionFieldBuilder().addBuilder(Extension.getDefaultInstance());
            }

            public Builder addExtensionBuilder(int i) {
                return getExtensionFieldBuilder().addBuilder(i, Extension.getDefaultInstance());
            }

            public List<Builder> getExtensionBuilderList() {
                return getExtensionFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<Extension, Builder, ExtensionOrBuilder> getExtensionFieldBuilder() {
                if (this.extensionBuilder_ == null) {
                    this.extensionBuilder_ = new RepeatedFieldBuilder<>(this.extension_, (this.bitField0_ & 1024) == 1024, getParentForChildren(), isClean());
                    this.extension_ = null;
                }
                return this.extensionBuilder_;
            }

            public boolean hasDescription() {
                return (this.bitField0_ & 2048) == 2048;
            }

            public String getDescription() {
                Object obj = this.description_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.description_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getDescriptionBytes() {
                Object obj = this.description_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.description_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setDescription(String str) {
                if (str != null) {
                    this.bitField0_ |= 2048;
                    this.description_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearDescription() {
                this.bitField0_ &= -2049;
                this.description_ = C3527Wallet.getDefaultInstance().getDescription();
                onChanged();
                return this;
            }

            public Builder setDescriptionBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2048;
                    this.description_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public boolean hasKeyRotationTime() {
                return (this.bitField0_ & 4096) == 4096;
            }

            public long getKeyRotationTime() {
                return this.keyRotationTime_;
            }

            public Builder setKeyRotationTime(long j) {
                this.bitField0_ |= 4096;
                this.keyRotationTime_ = j;
                onChanged();
                return this;
            }

            public Builder clearKeyRotationTime() {
                this.bitField0_ &= -4097;
                this.keyRotationTime_ = 0;
                onChanged();
                return this;
            }

            private void ensureTagsIsMutable() {
                if ((this.bitField0_ & 8192) != 8192) {
                    this.tags_ = new ArrayList(this.tags_);
                    this.bitField0_ |= 8192;
                }
            }

            public List<Tag> getTagsList() {
                RepeatedFieldBuilder<Tag, Builder, TagOrBuilder> repeatedFieldBuilder = this.tagsBuilder_;
                if (repeatedFieldBuilder == null) {
                    return Collections.unmodifiableList(this.tags_);
                }
                return repeatedFieldBuilder.getMessageList();
            }

            public int getTagsCount() {
                RepeatedFieldBuilder<Tag, Builder, TagOrBuilder> repeatedFieldBuilder = this.tagsBuilder_;
                if (repeatedFieldBuilder == null) {
                    return this.tags_.size();
                }
                return repeatedFieldBuilder.getCount();
            }

            public Tag getTags(int i) {
                RepeatedFieldBuilder<Tag, Builder, TagOrBuilder> repeatedFieldBuilder = this.tagsBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (Tag) this.tags_.get(i);
                }
                return repeatedFieldBuilder.getMessage(i);
            }

            public Builder setTags(int i, Tag tag) {
                RepeatedFieldBuilder<Tag, Builder, TagOrBuilder> repeatedFieldBuilder = this.tagsBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.setMessage(i, tag);
                } else if (tag != null) {
                    ensureTagsIsMutable();
                    this.tags_.set(i, tag);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setTags(int i, Builder builder) {
                RepeatedFieldBuilder<Tag, Builder, TagOrBuilder> repeatedFieldBuilder = this.tagsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTagsIsMutable();
                    this.tags_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addTags(Tag tag) {
                RepeatedFieldBuilder<Tag, Builder, TagOrBuilder> repeatedFieldBuilder = this.tagsBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(tag);
                } else if (tag != null) {
                    ensureTagsIsMutable();
                    this.tags_.add(tag);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addTags(int i, Tag tag) {
                RepeatedFieldBuilder<Tag, Builder, TagOrBuilder> repeatedFieldBuilder = this.tagsBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(i, tag);
                } else if (tag != null) {
                    ensureTagsIsMutable();
                    this.tags_.add(i, tag);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addTags(Builder builder) {
                RepeatedFieldBuilder<Tag, Builder, TagOrBuilder> repeatedFieldBuilder = this.tagsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTagsIsMutable();
                    this.tags_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(builder.build());
                }
                return this;
            }

            public Builder addTags(int i, Builder builder) {
                RepeatedFieldBuilder<Tag, Builder, TagOrBuilder> repeatedFieldBuilder = this.tagsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTagsIsMutable();
                    this.tags_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllTags(Iterable<? extends Tag> iterable) {
                RepeatedFieldBuilder<Tag, Builder, TagOrBuilder> repeatedFieldBuilder = this.tagsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTagsIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.tags_);
                    onChanged();
                } else {
                    repeatedFieldBuilder.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearTags() {
                RepeatedFieldBuilder<Tag, Builder, TagOrBuilder> repeatedFieldBuilder = this.tagsBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.tags_ = Collections.emptyList();
                    this.bitField0_ &= -8193;
                    onChanged();
                } else {
                    repeatedFieldBuilder.clear();
                }
                return this;
            }

            public Builder removeTags(int i) {
                RepeatedFieldBuilder<Tag, Builder, TagOrBuilder> repeatedFieldBuilder = this.tagsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTagsIsMutable();
                    this.tags_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilder.remove(i);
                }
                return this;
            }

            public Builder getTagsBuilder(int i) {
                return getTagsFieldBuilder().getBuilder(i);
            }

            public TagOrBuilder getTagsOrBuilder(int i) {
                RepeatedFieldBuilder<Tag, Builder, TagOrBuilder> repeatedFieldBuilder = this.tagsBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (TagOrBuilder) this.tags_.get(i);
                }
                return repeatedFieldBuilder.getMessageOrBuilder(i);
            }

            public List<? extends TagOrBuilder> getTagsOrBuilderList() {
                RepeatedFieldBuilder<Tag, Builder, TagOrBuilder> repeatedFieldBuilder = this.tagsBuilder_;
                if (repeatedFieldBuilder != null) {
                    return repeatedFieldBuilder.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.tags_);
            }

            public Builder addTagsBuilder() {
                return getTagsFieldBuilder().addBuilder(Tag.getDefaultInstance());
            }

            public Builder addTagsBuilder(int i) {
                return getTagsFieldBuilder().addBuilder(i, Tag.getDefaultInstance());
            }

            public List<Builder> getTagsBuilderList() {
                return getTagsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<Tag, Builder, TagOrBuilder> getTagsFieldBuilder() {
                if (this.tagsBuilder_ == null) {
                    this.tagsBuilder_ = new RepeatedFieldBuilder<>(this.tags_, (this.bitField0_ & 8192) == 8192, getParentForChildren(), isClean());
                    this.tags_ = null;
                }
                return this.tagsBuilder_;
            }

            private void ensureTransactionSignersIsMutable() {
                if ((this.bitField0_ & 16384) != 16384) {
                    this.transactionSigners_ = new ArrayList(this.transactionSigners_);
                    this.bitField0_ |= 16384;
                }
            }

            public List<TransactionSigner> getTransactionSignersList() {
                RepeatedFieldBuilder<TransactionSigner, Builder, TransactionSignerOrBuilder> repeatedFieldBuilder = this.transactionSignersBuilder_;
                if (repeatedFieldBuilder == null) {
                    return Collections.unmodifiableList(this.transactionSigners_);
                }
                return repeatedFieldBuilder.getMessageList();
            }

            public int getTransactionSignersCount() {
                RepeatedFieldBuilder<TransactionSigner, Builder, TransactionSignerOrBuilder> repeatedFieldBuilder = this.transactionSignersBuilder_;
                if (repeatedFieldBuilder == null) {
                    return this.transactionSigners_.size();
                }
                return repeatedFieldBuilder.getCount();
            }

            public TransactionSigner getTransactionSigners(int i) {
                RepeatedFieldBuilder<TransactionSigner, Builder, TransactionSignerOrBuilder> repeatedFieldBuilder = this.transactionSignersBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (TransactionSigner) this.transactionSigners_.get(i);
                }
                return repeatedFieldBuilder.getMessage(i);
            }

            public Builder setTransactionSigners(int i, TransactionSigner transactionSigner) {
                RepeatedFieldBuilder<TransactionSigner, Builder, TransactionSignerOrBuilder> repeatedFieldBuilder = this.transactionSignersBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.setMessage(i, transactionSigner);
                } else if (transactionSigner != null) {
                    ensureTransactionSignersIsMutable();
                    this.transactionSigners_.set(i, transactionSigner);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setTransactionSigners(int i, Builder builder) {
                RepeatedFieldBuilder<TransactionSigner, Builder, TransactionSignerOrBuilder> repeatedFieldBuilder = this.transactionSignersBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTransactionSignersIsMutable();
                    this.transactionSigners_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addTransactionSigners(TransactionSigner transactionSigner) {
                RepeatedFieldBuilder<TransactionSigner, Builder, TransactionSignerOrBuilder> repeatedFieldBuilder = this.transactionSignersBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(transactionSigner);
                } else if (transactionSigner != null) {
                    ensureTransactionSignersIsMutable();
                    this.transactionSigners_.add(transactionSigner);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addTransactionSigners(int i, TransactionSigner transactionSigner) {
                RepeatedFieldBuilder<TransactionSigner, Builder, TransactionSignerOrBuilder> repeatedFieldBuilder = this.transactionSignersBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(i, transactionSigner);
                } else if (transactionSigner != null) {
                    ensureTransactionSignersIsMutable();
                    this.transactionSigners_.add(i, transactionSigner);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addTransactionSigners(Builder builder) {
                RepeatedFieldBuilder<TransactionSigner, Builder, TransactionSignerOrBuilder> repeatedFieldBuilder = this.transactionSignersBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTransactionSignersIsMutable();
                    this.transactionSigners_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(builder.build());
                }
                return this;
            }

            public Builder addTransactionSigners(int i, Builder builder) {
                RepeatedFieldBuilder<TransactionSigner, Builder, TransactionSignerOrBuilder> repeatedFieldBuilder = this.transactionSignersBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTransactionSignersIsMutable();
                    this.transactionSigners_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllTransactionSigners(Iterable<? extends TransactionSigner> iterable) {
                RepeatedFieldBuilder<TransactionSigner, Builder, TransactionSignerOrBuilder> repeatedFieldBuilder = this.transactionSignersBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTransactionSignersIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.transactionSigners_);
                    onChanged();
                } else {
                    repeatedFieldBuilder.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearTransactionSigners() {
                RepeatedFieldBuilder<TransactionSigner, Builder, TransactionSignerOrBuilder> repeatedFieldBuilder = this.transactionSignersBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.transactionSigners_ = Collections.emptyList();
                    this.bitField0_ &= -16385;
                    onChanged();
                } else {
                    repeatedFieldBuilder.clear();
                }
                return this;
            }

            public Builder removeTransactionSigners(int i) {
                RepeatedFieldBuilder<TransactionSigner, Builder, TransactionSignerOrBuilder> repeatedFieldBuilder = this.transactionSignersBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureTransactionSignersIsMutable();
                    this.transactionSigners_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilder.remove(i);
                }
                return this;
            }

            public Builder getTransactionSignersBuilder(int i) {
                return getTransactionSignersFieldBuilder().getBuilder(i);
            }

            public TransactionSignerOrBuilder getTransactionSignersOrBuilder(int i) {
                RepeatedFieldBuilder<TransactionSigner, Builder, TransactionSignerOrBuilder> repeatedFieldBuilder = this.transactionSignersBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (TransactionSignerOrBuilder) this.transactionSigners_.get(i);
                }
                return repeatedFieldBuilder.getMessageOrBuilder(i);
            }

            public List<? extends TransactionSignerOrBuilder> getTransactionSignersOrBuilderList() {
                RepeatedFieldBuilder<TransactionSigner, Builder, TransactionSignerOrBuilder> repeatedFieldBuilder = this.transactionSignersBuilder_;
                if (repeatedFieldBuilder != null) {
                    return repeatedFieldBuilder.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.transactionSigners_);
            }

            public Builder addTransactionSignersBuilder() {
                return getTransactionSignersFieldBuilder().addBuilder(TransactionSigner.getDefaultInstance());
            }

            public Builder addTransactionSignersBuilder(int i) {
                return getTransactionSignersFieldBuilder().addBuilder(i, TransactionSigner.getDefaultInstance());
            }

            public List<Builder> getTransactionSignersBuilderList() {
                return getTransactionSignersFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<TransactionSigner, Builder, TransactionSignerOrBuilder> getTransactionSignersFieldBuilder() {
                if (this.transactionSignersBuilder_ == null) {
                    this.transactionSignersBuilder_ = new RepeatedFieldBuilder<>(this.transactionSigners_, (this.bitField0_ & 16384) == 16384, getParentForChildren(), isClean());
                    this.transactionSigners_ = null;
                }
                return this.transactionSignersBuilder_;
            }
        }

        /* renamed from: org.bitcoinj.wallet.Protos$Wallet$EncryptionType */
        public enum EncryptionType implements ProtocolMessageEnum {
            UNENCRYPTED(0, 1),
            ENCRYPTED_SCRYPT_AES(1, 2);
            
            public static final int ENCRYPTED_SCRYPT_AES_VALUE = 2;
            public static final int UNENCRYPTED_VALUE = 1;
            private static final EncryptionType[] VALUES = null;
            private static EnumLiteMap<EncryptionType> internalValueMap;
            private final int index;
            private final int value;

            static {
                internalValueMap = new EnumLiteMap<EncryptionType>() {
                    public EncryptionType findValueByNumber(int i) {
                        return EncryptionType.valueOf(i);
                    }
                };
                VALUES = values();
            }

            public final int getNumber() {
                return this.value;
            }

            public static EncryptionType valueOf(int i) {
                if (i == 1) {
                    return UNENCRYPTED;
                }
                if (i != 2) {
                    return null;
                }
                return ENCRYPTED_SCRYPT_AES;
            }

            public static EnumLiteMap<EncryptionType> internalGetValueMap() {
                return internalValueMap;
            }

            public final EnumValueDescriptor getValueDescriptor() {
                return (EnumValueDescriptor) getDescriptor().getValues().get(this.index);
            }

            public final EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final EnumDescriptor getDescriptor() {
                return (EnumDescriptor) C3527Wallet.getDescriptor().getEnumTypes().get(0);
            }

            public static EncryptionType valueOf(EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            private EncryptionType(int i, int i2) {
                this.index = i;
                this.value = i2;
            }
        }

        private C3527Wallet(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private C3527Wallet(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static C3527Wallet getDefaultInstance() {
            return defaultInstance;
        }

        public C3527Wallet getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r12v0, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$Wallet] */
        private C3527Wallet(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            z = true;
                            break;
                        case 10:
                            ByteString readBytes = codedInputStream.readBytes();
                            this.bitField0_ = 1 | this.bitField0_;
                            this.networkIdentifier_ = readBytes;
                            break;
                        case 18:
                            this.bitField0_ |= 2;
                            this.lastSeenBlockHash_ = codedInputStream.readBytes();
                            break;
                        case 26:
                            if (!(z2 & true)) {
                                this.key_ = new ArrayList();
                                z2 |= true;
                            }
                            this.key_.add(codedInputStream.readMessage(C3509Key.PARSER, extensionRegistryLite));
                            break;
                        case 34:
                            if (!(z2 & true)) {
                                this.transaction_ = new ArrayList();
                                z2 |= true;
                            }
                            this.transaction_.add(codedInputStream.readMessage(Transaction.PARSER, extensionRegistryLite));
                            break;
                        case 40:
                            int readEnum = codedInputStream.readEnum();
                            EncryptionType valueOf = EncryptionType.valueOf(readEnum);
                            if (valueOf != null) {
                                this.bitField0_ |= 16;
                                this.encryptionType_ = valueOf;
                                break;
                            } else {
                                newBuilder.mergeVarintField(5, readEnum);
                                break;
                            }
                        case 50:
                            Builder builder = null;
                            if ((this.bitField0_ & 32) == 32) {
                                builder = this.encryptionParameters_.toBuilder();
                            }
                            this.encryptionParameters_ = (ScryptParameters) codedInputStream.readMessage(ScryptParameters.PARSER, extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.encryptionParameters_);
                                this.encryptionParameters_ = builder.buildPartial();
                            }
                            this.bitField0_ |= 32;
                            break;
                        case 56:
                            this.bitField0_ |= 64;
                            this.version_ = codedInputStream.readInt32();
                            break;
                        case 82:
                            if (!(z2 & true)) {
                                this.extension_ = new ArrayList();
                                z2 |= true;
                            }
                            this.extension_.add(codedInputStream.readMessage(Extension.PARSER, extensionRegistryLite));
                            break;
                        case 90:
                            ByteString readBytes2 = codedInputStream.readBytes();
                            this.bitField0_ |= 128;
                            this.description_ = readBytes2;
                            break;
                        case 96:
                            this.bitField0_ |= 4;
                            this.lastSeenBlockHeight_ = codedInputStream.readUInt32();
                            break;
                        case 104:
                            this.bitField0_ |= 256;
                            this.keyRotationTime_ = codedInputStream.readUInt64();
                            break;
                        case 112:
                            this.bitField0_ |= 8;
                            this.lastSeenBlockTimeSecs_ = codedInputStream.readInt64();
                            break;
                        case 122:
                            if (!(z2 & true)) {
                                this.watchedScript_ = new ArrayList();
                                z2 |= true;
                            }
                            this.watchedScript_.add(codedInputStream.readMessage(Script.PARSER, extensionRegistryLite));
                            break;
                        case ScriptOpCodes.OP_SIZE /*130*/:
                            if (!(z2 & true)) {
                                this.tags_ = new ArrayList();
                                z2 |= true;
                            }
                            this.tags_.add(codedInputStream.readMessage(Tag.PARSER, extensionRegistryLite));
                            break;
                        case 138:
                            if (!(z2 & true)) {
                                this.transactionSigners_ = new ArrayList();
                                z2 |= true;
                            }
                            this.transactionSigners_.add(codedInputStream.readMessage(TransactionSigner.PARSER, extensionRegistryLite));
                            break;
                        default:
                            if (parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                break;
                            }
                            z = true;
                            break;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.key_ = Collections.unmodifiableList(this.key_);
                    }
                    if (z2 & true) {
                        this.transaction_ = Collections.unmodifiableList(this.transaction_);
                    }
                    if (z2 & true) {
                        this.extension_ = Collections.unmodifiableList(this.extension_);
                    }
                    if (z2 & true) {
                        this.watchedScript_ = Collections.unmodifiableList(this.watchedScript_);
                    }
                    if (z2 & true) {
                        this.tags_ = Collections.unmodifiableList(this.tags_);
                    }
                    if (z2 & true) {
                        this.transactionSigners_ = Collections.unmodifiableList(this.transactionSigners_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.key_ = Collections.unmodifiableList(this.key_);
            }
            if (z2 & true) {
                this.transaction_ = Collections.unmodifiableList(this.transaction_);
            }
            if (z2 & true) {
                this.extension_ = Collections.unmodifiableList(this.extension_);
            }
            if (z2 & true) {
                this.watchedScript_ = Collections.unmodifiableList(this.watchedScript_);
            }
            if (z2 & true) {
                this.tags_ = Collections.unmodifiableList(this.tags_);
            }
            if (z2 & true) {
                this.transactionSigners_ = Collections.unmodifiableList(this.transactionSigners_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        public static final Descriptor getDescriptor() {
            return Protos.internal_static_wallet_Wallet_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_wallet_Wallet_fieldAccessorTable.ensureFieldAccessorsInitialized(C3527Wallet.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<C3527Wallet> getParserForType() {
            return PARSER;
        }

        public boolean hasNetworkIdentifier() {
            return (this.bitField0_ & 1) == 1;
        }

        public String getNetworkIdentifier() {
            Object obj = this.networkIdentifier_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.networkIdentifier_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getNetworkIdentifierBytes() {
            Object obj = this.networkIdentifier_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.networkIdentifier_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasLastSeenBlockHash() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getLastSeenBlockHash() {
            return this.lastSeenBlockHash_;
        }

        public boolean hasLastSeenBlockHeight() {
            return (this.bitField0_ & 4) == 4;
        }

        public int getLastSeenBlockHeight() {
            return this.lastSeenBlockHeight_;
        }

        public boolean hasLastSeenBlockTimeSecs() {
            return (this.bitField0_ & 8) == 8;
        }

        public long getLastSeenBlockTimeSecs() {
            return this.lastSeenBlockTimeSecs_;
        }

        public List<C3509Key> getKeyList() {
            return this.key_;
        }

        public List<? extends KeyOrBuilder> getKeyOrBuilderList() {
            return this.key_;
        }

        public int getKeyCount() {
            return this.key_.size();
        }

        public C3509Key getKey(int i) {
            return (C3509Key) this.key_.get(i);
        }

        public KeyOrBuilder getKeyOrBuilder(int i) {
            return (KeyOrBuilder) this.key_.get(i);
        }

        public List<Transaction> getTransactionList() {
            return this.transaction_;
        }

        public List<? extends TransactionOrBuilder> getTransactionOrBuilderList() {
            return this.transaction_;
        }

        public int getTransactionCount() {
            return this.transaction_.size();
        }

        public Transaction getTransaction(int i) {
            return (Transaction) this.transaction_.get(i);
        }

        public TransactionOrBuilder getTransactionOrBuilder(int i) {
            return (TransactionOrBuilder) this.transaction_.get(i);
        }

        public List<Script> getWatchedScriptList() {
            return this.watchedScript_;
        }

        public List<? extends ScriptOrBuilder> getWatchedScriptOrBuilderList() {
            return this.watchedScript_;
        }

        public int getWatchedScriptCount() {
            return this.watchedScript_.size();
        }

        public Script getWatchedScript(int i) {
            return (Script) this.watchedScript_.get(i);
        }

        public ScriptOrBuilder getWatchedScriptOrBuilder(int i) {
            return (ScriptOrBuilder) this.watchedScript_.get(i);
        }

        public boolean hasEncryptionType() {
            return (this.bitField0_ & 16) == 16;
        }

        public EncryptionType getEncryptionType() {
            return this.encryptionType_;
        }

        public boolean hasEncryptionParameters() {
            return (this.bitField0_ & 32) == 32;
        }

        public ScryptParameters getEncryptionParameters() {
            return this.encryptionParameters_;
        }

        public ScryptParametersOrBuilder getEncryptionParametersOrBuilder() {
            return this.encryptionParameters_;
        }

        public boolean hasVersion() {
            return (this.bitField0_ & 64) == 64;
        }

        public int getVersion() {
            return this.version_;
        }

        public List<Extension> getExtensionList() {
            return this.extension_;
        }

        public List<? extends ExtensionOrBuilder> getExtensionOrBuilderList() {
            return this.extension_;
        }

        public int getExtensionCount() {
            return this.extension_.size();
        }

        public Extension getExtension(int i) {
            return (Extension) this.extension_.get(i);
        }

        public ExtensionOrBuilder getExtensionOrBuilder(int i) {
            return (ExtensionOrBuilder) this.extension_.get(i);
        }

        public boolean hasDescription() {
            return (this.bitField0_ & 128) == 128;
        }

        public String getDescription() {
            Object obj = this.description_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.description_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getDescriptionBytes() {
            Object obj = this.description_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.description_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasKeyRotationTime() {
            return (this.bitField0_ & 256) == 256;
        }

        public long getKeyRotationTime() {
            return this.keyRotationTime_;
        }

        public List<Tag> getTagsList() {
            return this.tags_;
        }

        public List<? extends TagOrBuilder> getTagsOrBuilderList() {
            return this.tags_;
        }

        public int getTagsCount() {
            return this.tags_.size();
        }

        public Tag getTags(int i) {
            return (Tag) this.tags_.get(i);
        }

        public TagOrBuilder getTagsOrBuilder(int i) {
            return (TagOrBuilder) this.tags_.get(i);
        }

        public List<TransactionSigner> getTransactionSignersList() {
            return this.transactionSigners_;
        }

        public List<? extends TransactionSignerOrBuilder> getTransactionSignersOrBuilderList() {
            return this.transactionSigners_;
        }

        public int getTransactionSignersCount() {
            return this.transactionSigners_.size();
        }

        public TransactionSigner getTransactionSigners(int i) {
            return (TransactionSigner) this.transactionSigners_.get(i);
        }

        public TransactionSignerOrBuilder getTransactionSignersOrBuilder(int i) {
            return (TransactionSignerOrBuilder) this.transactionSigners_.get(i);
        }

        private void initFields() {
            String str = "";
            this.networkIdentifier_ = str;
            this.lastSeenBlockHash_ = ByteString.EMPTY;
            this.lastSeenBlockHeight_ = 0;
            this.lastSeenBlockTimeSecs_ = 0;
            this.key_ = Collections.emptyList();
            this.transaction_ = Collections.emptyList();
            this.watchedScript_ = Collections.emptyList();
            this.encryptionType_ = EncryptionType.UNENCRYPTED;
            this.encryptionParameters_ = ScryptParameters.getDefaultInstance();
            this.version_ = 1;
            this.extension_ = Collections.emptyList();
            this.description_ = str;
            this.keyRotationTime_ = 0;
            this.tags_ = Collections.emptyList();
            this.transactionSigners_ = Collections.emptyList();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasNetworkIdentifier()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (int i = 0; i < getKeyCount(); i++) {
                if (!getKey(i).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for (int i2 = 0; i2 < getTransactionCount(); i2++) {
                if (!getTransaction(i2).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for (int i3 = 0; i3 < getWatchedScriptCount(); i3++) {
                if (!getWatchedScript(i3).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if (!hasEncryptionParameters() || getEncryptionParameters().isInitialized()) {
                for (int i4 = 0; i4 < getExtensionCount(); i4++) {
                    if (!getExtension(i4).isInitialized()) {
                        this.memoizedIsInitialized = 0;
                        return false;
                    }
                }
                for (int i5 = 0; i5 < getTagsCount(); i5++) {
                    if (!getTags(i5).isInitialized()) {
                        this.memoizedIsInitialized = 0;
                        return false;
                    }
                }
                for (int i6 = 0; i6 < getTransactionSignersCount(); i6++) {
                    if (!getTransactionSigners(i6).isInitialized()) {
                        this.memoizedIsInitialized = 0;
                        return false;
                    }
                }
                this.memoizedIsInitialized = 1;
                return true;
            }
            this.memoizedIsInitialized = 0;
            return false;
        }

        /* JADX WARNING: type inference failed for: r4v11, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$ScryptParameters] */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r4v11, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$ScryptParameters]
          assigns: [org.bitcoinj.wallet.Protos$ScryptParameters]
          uses: [com.google.protobuf.MessageLite]
          mth insns count: 104
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void writeTo(com.google.protobuf.CodedOutputStream r7) throws java.io.IOException {
            /*
                r6 = this;
                r6.getSerializedSize()
                int r0 = r6.bitField0_
                r1 = 1
                r0 = r0 & r1
                if (r0 != r1) goto L_0x0010
                com.google.protobuf.ByteString r0 = r6.getNetworkIdentifierBytes()
                r7.writeBytes(r1, r0)
            L_0x0010:
                int r0 = r6.bitField0_
                r1 = 2
                r0 = r0 & r1
                if (r0 != r1) goto L_0x001b
                com.google.protobuf.ByteString r0 = r6.lastSeenBlockHash_
                r7.writeBytes(r1, r0)
            L_0x001b:
                r0 = 0
                r1 = 0
            L_0x001d:
                java.util.List<org.bitcoinj.wallet.Protos$Key> r2 = r6.key_
                int r2 = r2.size()
                if (r1 >= r2) goto L_0x0034
                r2 = 3
                java.util.List<org.bitcoinj.wallet.Protos$Key> r3 = r6.key_
                java.lang.Object r3 = r3.get(r1)
                com.google.protobuf.MessageLite r3 = (com.google.protobuf.MessageLite) r3
                r7.writeMessage(r2, r3)
                int r1 = r1 + 1
                goto L_0x001d
            L_0x0034:
                r1 = 0
            L_0x0035:
                java.util.List<org.bitcoinj.wallet.Protos$Transaction> r2 = r6.transaction_
                int r2 = r2.size()
                r3 = 4
                if (r1 >= r2) goto L_0x004c
                java.util.List<org.bitcoinj.wallet.Protos$Transaction> r2 = r6.transaction_
                java.lang.Object r2 = r2.get(r1)
                com.google.protobuf.MessageLite r2 = (com.google.protobuf.MessageLite) r2
                r7.writeMessage(r3, r2)
                int r1 = r1 + 1
                goto L_0x0035
            L_0x004c:
                int r1 = r6.bitField0_
                r2 = 16
                r1 = r1 & r2
                if (r1 != r2) goto L_0x005d
                r1 = 5
                org.bitcoinj.wallet.Protos$Wallet$EncryptionType r4 = r6.encryptionType_
                int r4 = r4.getNumber()
                r7.writeEnum(r1, r4)
            L_0x005d:
                int r1 = r6.bitField0_
                r4 = 32
                r1 = r1 & r4
                if (r1 != r4) goto L_0x006a
                r1 = 6
                org.bitcoinj.wallet.Protos$ScryptParameters r4 = r6.encryptionParameters_
                r7.writeMessage(r1, r4)
            L_0x006a:
                int r1 = r6.bitField0_
                r4 = 64
                r1 = r1 & r4
                if (r1 != r4) goto L_0x0077
                r1 = 7
                int r4 = r6.version_
                r7.writeInt32(r1, r4)
            L_0x0077:
                r1 = 0
            L_0x0078:
                java.util.List<org.bitcoinj.wallet.Protos$Extension> r4 = r6.extension_
                int r4 = r4.size()
                if (r1 >= r4) goto L_0x0090
                r4 = 10
                java.util.List<org.bitcoinj.wallet.Protos$Extension> r5 = r6.extension_
                java.lang.Object r5 = r5.get(r1)
                com.google.protobuf.MessageLite r5 = (com.google.protobuf.MessageLite) r5
                r7.writeMessage(r4, r5)
                int r1 = r1 + 1
                goto L_0x0078
            L_0x0090:
                int r1 = r6.bitField0_
                r4 = 128(0x80, float:1.794E-43)
                r1 = r1 & r4
                if (r1 != r4) goto L_0x00a0
                r1 = 11
                com.google.protobuf.ByteString r4 = r6.getDescriptionBytes()
                r7.writeBytes(r1, r4)
            L_0x00a0:
                int r1 = r6.bitField0_
                r1 = r1 & r3
                if (r1 != r3) goto L_0x00ac
                r1 = 12
                int r3 = r6.lastSeenBlockHeight_
                r7.writeUInt32(r1, r3)
            L_0x00ac:
                int r1 = r6.bitField0_
                r3 = 256(0x100, float:3.59E-43)
                r1 = r1 & r3
                if (r1 != r3) goto L_0x00ba
                r1 = 13
                long r3 = r6.keyRotationTime_
                r7.writeUInt64(r1, r3)
            L_0x00ba:
                int r1 = r6.bitField0_
                r3 = 8
                r1 = r1 & r3
                if (r1 != r3) goto L_0x00c8
                r1 = 14
                long r3 = r6.lastSeenBlockTimeSecs_
                r7.writeInt64(r1, r3)
            L_0x00c8:
                r1 = 0
            L_0x00c9:
                java.util.List<org.bitcoinj.wallet.Protos$Script> r3 = r6.watchedScript_
                int r3 = r3.size()
                if (r1 >= r3) goto L_0x00e1
                r3 = 15
                java.util.List<org.bitcoinj.wallet.Protos$Script> r4 = r6.watchedScript_
                java.lang.Object r4 = r4.get(r1)
                com.google.protobuf.MessageLite r4 = (com.google.protobuf.MessageLite) r4
                r7.writeMessage(r3, r4)
                int r1 = r1 + 1
                goto L_0x00c9
            L_0x00e1:
                r1 = 0
            L_0x00e2:
                java.util.List<org.bitcoinj.wallet.Protos$Tag> r3 = r6.tags_
                int r3 = r3.size()
                if (r1 >= r3) goto L_0x00f8
                java.util.List<org.bitcoinj.wallet.Protos$Tag> r3 = r6.tags_
                java.lang.Object r3 = r3.get(r1)
                com.google.protobuf.MessageLite r3 = (com.google.protobuf.MessageLite) r3
                r7.writeMessage(r2, r3)
                int r1 = r1 + 1
                goto L_0x00e2
            L_0x00f8:
                java.util.List<org.bitcoinj.wallet.Protos$TransactionSigner> r1 = r6.transactionSigners_
                int r1 = r1.size()
                if (r0 >= r1) goto L_0x0110
                r1 = 17
                java.util.List<org.bitcoinj.wallet.Protos$TransactionSigner> r2 = r6.transactionSigners_
                java.lang.Object r2 = r2.get(r0)
                com.google.protobuf.MessageLite r2 = (com.google.protobuf.MessageLite) r2
                r7.writeMessage(r1, r2)
                int r0 = r0 + 1
                goto L_0x00f8
            L_0x0110:
                com.google.protobuf.UnknownFieldSet r0 = r6.getUnknownFields()
                r0.writeTo(r7)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.wallet.Protos.C3527Wallet.writeTo(com.google.protobuf.CodedOutputStream):void");
        }

        /* JADX WARNING: type inference failed for: r5v12, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$ScryptParameters] */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r5v12, types: [com.google.protobuf.MessageLite, org.bitcoinj.wallet.Protos$ScryptParameters]
          assigns: [org.bitcoinj.wallet.Protos$ScryptParameters]
          uses: [com.google.protobuf.MessageLite]
          mth insns count: 125
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int getSerializedSize() {
            /*
                r7 = this;
                int r0 = r7.memoizedSerializedSize
                r1 = -1
                if (r0 == r1) goto L_0x0006
                return r0
            L_0x0006:
                int r0 = r7.bitField0_
                r1 = 1
                r0 = r0 & r1
                r2 = 0
                if (r0 != r1) goto L_0x0017
                com.google.protobuf.ByteString r0 = r7.getNetworkIdentifierBytes()
                int r0 = com.google.protobuf.CodedOutputStream.computeBytesSize(r1, r0)
                int r0 = r0 + r2
                goto L_0x0018
            L_0x0017:
                r0 = 0
            L_0x0018:
                int r1 = r7.bitField0_
                r3 = 2
                r1 = r1 & r3
                if (r1 != r3) goto L_0x0025
                com.google.protobuf.ByteString r1 = r7.lastSeenBlockHash_
                int r1 = com.google.protobuf.CodedOutputStream.computeBytesSize(r3, r1)
                int r0 = r0 + r1
            L_0x0025:
                r1 = r0
                r0 = 0
            L_0x0027:
                java.util.List<org.bitcoinj.wallet.Protos$Key> r3 = r7.key_
                int r3 = r3.size()
                if (r0 >= r3) goto L_0x0040
                r3 = 3
                java.util.List<org.bitcoinj.wallet.Protos$Key> r4 = r7.key_
                java.lang.Object r4 = r4.get(r0)
                com.google.protobuf.MessageLite r4 = (com.google.protobuf.MessageLite) r4
                int r3 = com.google.protobuf.CodedOutputStream.computeMessageSize(r3, r4)
                int r1 = r1 + r3
                int r0 = r0 + 1
                goto L_0x0027
            L_0x0040:
                r0 = 0
            L_0x0041:
                java.util.List<org.bitcoinj.wallet.Protos$Transaction> r3 = r7.transaction_
                int r3 = r3.size()
                r4 = 4
                if (r0 >= r3) goto L_0x005a
                java.util.List<org.bitcoinj.wallet.Protos$Transaction> r3 = r7.transaction_
                java.lang.Object r3 = r3.get(r0)
                com.google.protobuf.MessageLite r3 = (com.google.protobuf.MessageLite) r3
                int r3 = com.google.protobuf.CodedOutputStream.computeMessageSize(r4, r3)
                int r1 = r1 + r3
                int r0 = r0 + 1
                goto L_0x0041
            L_0x005a:
                int r0 = r7.bitField0_
                r3 = 16
                r0 = r0 & r3
                if (r0 != r3) goto L_0x006d
                r0 = 5
                org.bitcoinj.wallet.Protos$Wallet$EncryptionType r5 = r7.encryptionType_
                int r5 = r5.getNumber()
                int r0 = com.google.protobuf.CodedOutputStream.computeEnumSize(r0, r5)
                int r1 = r1 + r0
            L_0x006d:
                int r0 = r7.bitField0_
                r5 = 32
                r0 = r0 & r5
                if (r0 != r5) goto L_0x007c
                r0 = 6
                org.bitcoinj.wallet.Protos$ScryptParameters r5 = r7.encryptionParameters_
                int r0 = com.google.protobuf.CodedOutputStream.computeMessageSize(r0, r5)
                int r1 = r1 + r0
            L_0x007c:
                int r0 = r7.bitField0_
                r5 = 64
                r0 = r0 & r5
                if (r0 != r5) goto L_0x008b
                r0 = 7
                int r5 = r7.version_
                int r0 = com.google.protobuf.CodedOutputStream.computeInt32Size(r0, r5)
                int r1 = r1 + r0
            L_0x008b:
                r0 = 0
            L_0x008c:
                java.util.List<org.bitcoinj.wallet.Protos$Extension> r5 = r7.extension_
                int r5 = r5.size()
                if (r0 >= r5) goto L_0x00a6
                r5 = 10
                java.util.List<org.bitcoinj.wallet.Protos$Extension> r6 = r7.extension_
                java.lang.Object r6 = r6.get(r0)
                com.google.protobuf.MessageLite r6 = (com.google.protobuf.MessageLite) r6
                int r5 = com.google.protobuf.CodedOutputStream.computeMessageSize(r5, r6)
                int r1 = r1 + r5
                int r0 = r0 + 1
                goto L_0x008c
            L_0x00a6:
                int r0 = r7.bitField0_
                r5 = 128(0x80, float:1.794E-43)
                r0 = r0 & r5
                if (r0 != r5) goto L_0x00b8
                r0 = 11
                com.google.protobuf.ByteString r5 = r7.getDescriptionBytes()
                int r0 = com.google.protobuf.CodedOutputStream.computeBytesSize(r0, r5)
                int r1 = r1 + r0
            L_0x00b8:
                int r0 = r7.bitField0_
                r0 = r0 & r4
                if (r0 != r4) goto L_0x00c6
                r0 = 12
                int r4 = r7.lastSeenBlockHeight_
                int r0 = com.google.protobuf.CodedOutputStream.computeUInt32Size(r0, r4)
                int r1 = r1 + r0
            L_0x00c6:
                int r0 = r7.bitField0_
                r4 = 256(0x100, float:3.59E-43)
                r0 = r0 & r4
                if (r0 != r4) goto L_0x00d6
                r0 = 13
                long r4 = r7.keyRotationTime_
                int r0 = com.google.protobuf.CodedOutputStream.computeUInt64Size(r0, r4)
                int r1 = r1 + r0
            L_0x00d6:
                int r0 = r7.bitField0_
                r4 = 8
                r0 = r0 & r4
                if (r0 != r4) goto L_0x00e6
                r0 = 14
                long r4 = r7.lastSeenBlockTimeSecs_
                int r0 = com.google.protobuf.CodedOutputStream.computeInt64Size(r0, r4)
                int r1 = r1 + r0
            L_0x00e6:
                r0 = 0
            L_0x00e7:
                java.util.List<org.bitcoinj.wallet.Protos$Script> r4 = r7.watchedScript_
                int r4 = r4.size()
                if (r0 >= r4) goto L_0x0101
                r4 = 15
                java.util.List<org.bitcoinj.wallet.Protos$Script> r5 = r7.watchedScript_
                java.lang.Object r5 = r5.get(r0)
                com.google.protobuf.MessageLite r5 = (com.google.protobuf.MessageLite) r5
                int r4 = com.google.protobuf.CodedOutputStream.computeMessageSize(r4, r5)
                int r1 = r1 + r4
                int r0 = r0 + 1
                goto L_0x00e7
            L_0x0101:
                r0 = 0
            L_0x0102:
                java.util.List<org.bitcoinj.wallet.Protos$Tag> r4 = r7.tags_
                int r4 = r4.size()
                if (r0 >= r4) goto L_0x011a
                java.util.List<org.bitcoinj.wallet.Protos$Tag> r4 = r7.tags_
                java.lang.Object r4 = r4.get(r0)
                com.google.protobuf.MessageLite r4 = (com.google.protobuf.MessageLite) r4
                int r4 = com.google.protobuf.CodedOutputStream.computeMessageSize(r3, r4)
                int r1 = r1 + r4
                int r0 = r0 + 1
                goto L_0x0102
            L_0x011a:
                java.util.List<org.bitcoinj.wallet.Protos$TransactionSigner> r0 = r7.transactionSigners_
                int r0 = r0.size()
                if (r2 >= r0) goto L_0x0134
                r0 = 17
                java.util.List<org.bitcoinj.wallet.Protos$TransactionSigner> r3 = r7.transactionSigners_
                java.lang.Object r3 = r3.get(r2)
                com.google.protobuf.MessageLite r3 = (com.google.protobuf.MessageLite) r3
                int r0 = com.google.protobuf.CodedOutputStream.computeMessageSize(r0, r3)
                int r1 = r1 + r0
                int r2 = r2 + 1
                goto L_0x011a
            L_0x0134:
                com.google.protobuf.UnknownFieldSet r0 = r7.getUnknownFields()
                int r0 = r0.getSerializedSize()
                int r1 = r1 + r0
                r7.memoizedSerializedSize = r1
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.wallet.Protos.C3527Wallet.getSerializedSize():int");
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static C3527Wallet parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (C3527Wallet) PARSER.parseFrom(byteString);
        }

        public static C3527Wallet parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (C3527Wallet) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static C3527Wallet parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (C3527Wallet) PARSER.parseFrom(bArr);
        }

        public static C3527Wallet parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (C3527Wallet) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static C3527Wallet parseFrom(InputStream inputStream) throws IOException {
            return (C3527Wallet) PARSER.parseFrom(inputStream);
        }

        public static C3527Wallet parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (C3527Wallet) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static C3527Wallet parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (C3527Wallet) PARSER.parseDelimitedFrom(inputStream);
        }

        public static C3527Wallet parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (C3527Wallet) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static C3527Wallet parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (C3527Wallet) PARSER.parseFrom(codedInputStream);
        }

        public static C3527Wallet parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (C3527Wallet) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(C3527Wallet wallet) {
            return newBuilder().mergeFrom(wallet);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface WalletOrBuilder extends MessageOrBuilder {
        String getDescription();

        ByteString getDescriptionBytes();

        ScryptParameters getEncryptionParameters();

        ScryptParametersOrBuilder getEncryptionParametersOrBuilder();

        EncryptionType getEncryptionType();

        Extension getExtension(int i);

        int getExtensionCount();

        List<Extension> getExtensionList();

        ExtensionOrBuilder getExtensionOrBuilder(int i);

        List<? extends ExtensionOrBuilder> getExtensionOrBuilderList();

        C3509Key getKey(int i);

        int getKeyCount();

        List<C3509Key> getKeyList();

        KeyOrBuilder getKeyOrBuilder(int i);

        List<? extends KeyOrBuilder> getKeyOrBuilderList();

        long getKeyRotationTime();

        ByteString getLastSeenBlockHash();

        int getLastSeenBlockHeight();

        long getLastSeenBlockTimeSecs();

        String getNetworkIdentifier();

        ByteString getNetworkIdentifierBytes();

        Tag getTags(int i);

        int getTagsCount();

        List<Tag> getTagsList();

        TagOrBuilder getTagsOrBuilder(int i);

        List<? extends TagOrBuilder> getTagsOrBuilderList();

        Transaction getTransaction(int i);

        int getTransactionCount();

        List<Transaction> getTransactionList();

        TransactionOrBuilder getTransactionOrBuilder(int i);

        List<? extends TransactionOrBuilder> getTransactionOrBuilderList();

        TransactionSigner getTransactionSigners(int i);

        int getTransactionSignersCount();

        List<TransactionSigner> getTransactionSignersList();

        TransactionSignerOrBuilder getTransactionSignersOrBuilder(int i);

        List<? extends TransactionSignerOrBuilder> getTransactionSignersOrBuilderList();

        int getVersion();

        Script getWatchedScript(int i);

        int getWatchedScriptCount();

        List<Script> getWatchedScriptList();

        ScriptOrBuilder getWatchedScriptOrBuilder(int i);

        List<? extends ScriptOrBuilder> getWatchedScriptOrBuilderList();

        boolean hasDescription();

        boolean hasEncryptionParameters();

        boolean hasEncryptionType();

        boolean hasKeyRotationTime();

        boolean hasLastSeenBlockHash();

        boolean hasLastSeenBlockHeight();

        boolean hasLastSeenBlockTimeSecs();

        boolean hasNetworkIdentifier();

        boolean hasVersion();
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
    }

    private Protos() {
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\fwallet.proto\u0012\u0006wallet\"A\n\u000bPeerAddress\u0012\u0012\n\nip_address\u0018\u0001 \u0002(\f\u0012\f\n\u0004port\u0018\u0002 \u0002(\r\u0012\u0010\n\bservices\u0018\u0003 \u0002(\u0004\"M\n\rEncryptedData\u0012\u001d\n\u0015initialisation_vector\u0018\u0001 \u0002(\f\u0012\u001d\n\u0015encrypted_private_key\u0018\u0002 \u0002(\f\"\u0001\n\u0010DeterministicKey\u0012\u0012\n\nchain_code\u0018\u0001 \u0002(\f\u0012\f\n\u0004path\u0018\u0002 \u0003(\r\u0012\u0016\n\u000eissued_subkeys\u0018\u0003 \u0001(\r\u0012\u0016\n\u000elookahead_size\u0018\u0004 \u0001(\r\u0012\u0013\n\u000bisFollowing\u0018\u0005 \u0001(\b\u0012\u001e\n\u0013sigsRequiredToSpend\u0018\u0006 \u0001(\r:\u00011\"\u0003\n\u0003Key\u0012\u001e\n\u0004type\u0018\u0001 \u0002(\u000e2\u0010.wallet.Key.Type\u0012\u0014\n\fsecret_bytes\u0018\u0002 \u0001(\f\u0012-\n\u000eencrypted_", "data\u0018\u0006 \u0001(\u000b2\u0015.wallet.EncryptedData\u0012\u0012\n\npublic_key\u0018\u0003 \u0001(\f\u0012\r\n\u0005label\u0018\u0004 \u0001(\t\u0012\u001a\n\u0012creation_timestamp\u0018\u0005 \u0001(\u0003\u00123\n\u0011deterministic_key\u0018\u0007 \u0001(\u000b2\u0018.wallet.DeterministicKey\u0012\u001a\n\u0012deterministic_seed\u0018\b \u0001(\f\u0012;\n\u001cencrypted_deterministic_seed\u0018\t \u0001(\u000b2\u0015.wallet.EncryptedData\"a\n\u0004Type\u0012\f\n\bORIGINAL\u0010\u0001\u0012\u0018\n\u0014ENCRYPTED_SCRYPT_AES\u0010\u0002\u0012\u001a\n\u0016DETERMINISTIC_MNEMONIC\u0010\u0003\u0012\u0015\n\u0011DETERMINISTIC_KEY\u0010\u0004\"5\n\u0006Script\u0012\u000f\n\u0007program\u0018\u0001 \u0002(\f\u0012\u001a\n\u0012creation_timestamp\u0018\u0002 \u0002(\u0003\"\u0001\n\u0010Tra", "nsactionInput\u0012\"\n\u001atransaction_out_point_hash\u0018\u0001 \u0002(\f\u0012#\n\u001btransaction_out_point_index\u0018\u0002 \u0002(\r\u0012\u0014\n\fscript_bytes\u0018\u0003 \u0002(\f\u0012\u0010\n\bsequence\u0018\u0004 \u0001(\r\u0012\r\n\u0005value\u0018\u0005 \u0001(\u0003\"\n\u0011TransactionOutput\u0012\r\n\u0005value\u0018\u0001 \u0002(\u0003\u0012\u0014\n\fscript_bytes\u0018\u0002 \u0002(\f\u0012!\n\u0019spent_by_transaction_hash\u0018\u0003 \u0001(\f\u0012\"\n\u001aspent_by_transaction_index\u0018\u0004 \u0001(\u0005\"·\u0003\n\u0015TransactionConfidence\u00120\n\u0004type\u0018\u0001 \u0001(\u000e2\".wallet.TransactionConfidence.Type\u0012\u001a\n\u0012appeared_at_height\u0018\u0002 \u0001(\u0005\u0012\u001e\n\u0016overriding_transaction", "\u0018\u0003 \u0001(\f\u0012\r\n\u0005depth\u0018\u0004 \u0001(\u0005\u0012)\n\fbroadcast_by\u0018\u0006 \u0003(\u000b2\u0013.wallet.PeerAddress\u0012\u001b\n\u0013last_broadcasted_at\u0018\b \u0001(\u0003\u00124\n\u0006source\u0018\u0007 \u0001(\u000e2$.wallet.TransactionConfidence.Source\"`\n\u0004Type\u0012\u000b\n\u0007UNKNOWN\u0010\u0000\u0012\f\n\bBUILDING\u0010\u0001\u0012\u000b\n\u0007PENDING\u0010\u0002\u0012\u0015\n\u0011NOT_IN_BEST_CHAIN\u0010\u0003\u0012\b\n\u0004DEAD\u0010\u0004\u0012\u000f\n\u000bIN_CONFLICT\u0010\u0005\"A\n\u0006Source\u0012\u0012\n\u000eSOURCE_UNKNOWN\u0010\u0000\u0012\u0012\n\u000eSOURCE_NETWORK\u0010\u0001\u0012\u000f\n\u000bSOURCE_SELF\u0010\u0002\"Ã\u0005\n\u000bTransaction\u0012\u000f\n\u0007version\u0018\u0001 \u0002(\u0005\u0012\f\n\u0004hash\u0018\u0002 \u0002(\f\u0012&\n\u0004pool\u0018\u0003 \u0001(\u000e2\u0018.wallet.Transaction.Pool\u0012", "\u0011\n\tlock_time\u0018\u0004 \u0001(\r\u0012\u0012\n\nupdated_at\u0018\u0005 \u0001(\u0003\u00123\n\u0011transaction_input\u0018\u0006 \u0003(\u000b2\u0018.wallet.TransactionInput\u00125\n\u0012transaction_output\u0018\u0007 \u0003(\u000b2\u0019.wallet.TransactionOutput\u0012\u0012\n\nblock_hash\u0018\b \u0003(\f\u0012 \n\u0018block_relativity_offsets\u0018\u000b \u0003(\u0005\u00121\n\nconfidence\u0018\t \u0001(\u000b2\u001d.wallet.TransactionConfidence\u00125\n\u0007purpose\u0018\n \u0001(\u000e2\u001b.wallet.Transaction.Purpose:\u0007UNKNOWN\u0012+\n\rexchange_rate\u0018\f \u0001(\u000b2\u0014.wallet.ExchangeRate\u0012\f\n\u0004memo\u0018\r \u0001(\t\"Y\n\u0004Pool\u0012\u000b\n\u0007UNSPENT\u0010\u0004\u0012\t\n\u0005SPENT\u0010\u0005\u0012\f\n", "\bINACTIVE\u0010\u0002\u0012\b\n\u0004DEAD\u0010\n\u0012\u000b\n\u0007PENDING\u0010\u0010\u0012\u0014\n\u0010PENDING_INACTIVE\u0010\u0012\"£\u0001\n\u0007Purpose\u0012\u000b\n\u0007UNKNOWN\u0010\u0000\u0012\u0010\n\fUSER_PAYMENT\u0010\u0001\u0012\u0010\n\fKEY_ROTATION\u0010\u0002\u0012\u001c\n\u0018ASSURANCE_CONTRACT_CLAIM\u0010\u0003\u0012\u001d\n\u0019ASSURANCE_CONTRACT_PLEDGE\u0010\u0004\u0012\u001b\n\u0017ASSURANCE_CONTRACT_STUB\u0010\u0005\u0012\r\n\tRAISE_FEE\u0010\u0006\"N\n\u0010ScryptParameters\u0012\f\n\u0004salt\u0018\u0001 \u0002(\f\u0012\u0010\n\u0001n\u0018\u0002 \u0001(\u0003:\u000516384\u0012\f\n\u0001r\u0018\u0003 \u0001(\u0005:\u00018\u0012\f\n\u0001p\u0018\u0004 \u0001(\u0005:\u00011\"8\n\tExtension\u0012\n\n\u0002id\u0018\u0001 \u0002(\t\u0012\f\n\u0004data\u0018\u0002 \u0002(\f\u0012\u0011\n\tmandatory\u0018\u0003 \u0002(\b\" \n\u0003Tag\u0012\u000b\n\u0003tag\u0018\u0001 \u0002(\t\u0012\f\n\u0004data\u0018\u0002 \u0002(\f\"5\n\u0011Tr", "ansactionSigner\u0012\u0012\n\nclass_name\u0018\u0001 \u0002(\t\u0012\f\n\u0004data\u0018\u0002 \u0001(\f\"é\u0004\n\u0006Wallet\u0012\u001a\n\u0012network_identifier\u0018\u0001 \u0002(\t\u0012\u001c\n\u0014last_seen_block_hash\u0018\u0002 \u0001(\f\u0012\u001e\n\u0016last_seen_block_height\u0018\f \u0001(\r\u0012!\n\u0019last_seen_block_time_secs\u0018\u000e \u0001(\u0003\u0012\u0018\n\u0003key\u0018\u0003 \u0003(\u000b2\u000b.wallet.Key\u0012(\n\u000btransaction\u0018\u0004 \u0003(\u000b2\u0013.wallet.Transaction\u0012&\n\u000ewatched_script\u0018\u000f \u0003(\u000b2\u000e.wallet.Script\u0012C\n\u000fencryption_type\u0018\u0005 \u0001(\u000e2\u001d.wallet.Wallet.EncryptionType:\u000bUNENCRYPTED\u00127\n\u0015encryption_parameters\u0018\u0006 \u0001(\u000b2\u0018.wall", "et.ScryptParameters\u0012\u0012\n\u0007version\u0018\u0007 \u0001(\u0005:\u00011\u0012$\n\textension\u0018\n \u0003(\u000b2\u0011.wallet.Extension\u0012\u0013\n\u000bdescription\u0018\u000b \u0001(\t\u0012\u0019\n\u0011key_rotation_time\u0018\r \u0001(\u0004\u0012\u0019\n\u0004tags\u0018\u0010 \u0003(\u000b2\u000b.wallet.Tag\u00126\n\u0013transaction_signers\u0018\u0011 \u0003(\u000b2\u0019.wallet.TransactionSigner\";\n\u000eEncryptionType\u0012\u000f\n\u000bUNENCRYPTED\u0010\u0001\u0012\u0018\n\u0014ENCRYPTED_SCRYPT_AES\u0010\u0002\"R\n\fExchangeRate\u0012\u0012\n\ncoin_value\u0018\u0001 \u0002(\u0003\u0012\u0012\n\nfiat_value\u0018\u0002 \u0002(\u0003\u0012\u001a\n\u0012fiat_currency_code\u0018\u0003 \u0002(\tB\u001d\n\u0013org.bitcoinj.walletB\u0006Protos"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                Protos.descriptor = fileDescriptor;
                return null;
            }
        });
        String str = "Value";
        String str2 = "ScriptBytes";
        internal_static_wallet_TransactionInput_fieldAccessorTable = new FieldAccessorTable(internal_static_wallet_TransactionInput_descriptor, new String[]{"TransactionOutPointHash", "TransactionOutPointIndex", str2, "Sequence", str});
        internal_static_wallet_TransactionOutput_fieldAccessorTable = new FieldAccessorTable(internal_static_wallet_TransactionOutput_descriptor, new String[]{str, str2, "SpentByTransactionHash", "SpentByTransactionIndex"});
        Descriptor descriptor2 = internal_static_wallet_Extension_descriptor;
        String str3 = Keys.DATA;
        internal_static_wallet_Extension_fieldAccessorTable = new FieldAccessorTable(descriptor2, new String[]{"Id", str3, "Mandatory"});
        internal_static_wallet_Tag_fieldAccessorTable = new FieldAccessorTable(internal_static_wallet_Tag_descriptor, new String[]{"Tag", str3});
        internal_static_wallet_TransactionSigner_fieldAccessorTable = new FieldAccessorTable(internal_static_wallet_TransactionSigner_descriptor, new String[]{"ClassName", str3});
    }
}
