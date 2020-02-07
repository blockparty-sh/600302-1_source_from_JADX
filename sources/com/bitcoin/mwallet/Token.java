package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.MethodToInvoke;
import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class Token extends GeneratedMessageLite<Token, Builder> implements TokenOrBuilder {
    /* access modifiers changed from: private */
    public static final Token DEFAULT_INSTANCE = new Token();
    private static volatile Parser<Token> PARSER = null;
    public static final int PARSING_STATUS_FIELD_NUMBER = 9;
    public static final int TOKEN_DECIMALS_FIELD_NUMBER = 6;
    public static final int TOKEN_ID_FIELD_NUMBER = 1;
    public static final int TOKEN_NAME_FIELD_NUMBER = 5;
    public static final int TOKEN_TICKER_FIELD_NUMBER = 3;
    public static final int TOKEN_TYPE_FIELD_NUMBER = 4;
    public static final int TOKEN_VALID_FIELD_NUMBER = 8;
    public static final int TOKEN_VALUE_FIELD_NUMBER = 2;
    public static final int TRANSACTION_TYPE_FIELD_NUMBER = 7;
    private int parsingStatus_;
    private int tokenDecimals_;
    private String tokenId_;
    private String tokenName_;
    private String tokenTicker_;
    private String tokenType_;
    private int tokenValid_;
    private long tokenValue_;
    private String transactionType_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<Token, Builder> implements TokenOrBuilder {
        private Builder() {
            super(Token.DEFAULT_INSTANCE);
        }

        public String getTokenId() {
            return ((Token) this.instance).getTokenId();
        }

        public ByteString getTokenIdBytes() {
            return ((Token) this.instance).getTokenIdBytes();
        }

        public Builder setTokenId(String str) {
            copyOnWrite();
            ((Token) this.instance).setTokenId(str);
            return this;
        }

        public Builder clearTokenId() {
            copyOnWrite();
            ((Token) this.instance).clearTokenId();
            return this;
        }

        public Builder setTokenIdBytes(ByteString byteString) {
            copyOnWrite();
            ((Token) this.instance).setTokenIdBytes(byteString);
            return this;
        }

        public long getTokenValue() {
            return ((Token) this.instance).getTokenValue();
        }

        public Builder setTokenValue(long j) {
            copyOnWrite();
            ((Token) this.instance).setTokenValue(j);
            return this;
        }

        public Builder clearTokenValue() {
            copyOnWrite();
            ((Token) this.instance).clearTokenValue();
            return this;
        }

        public String getTokenTicker() {
            return ((Token) this.instance).getTokenTicker();
        }

        public ByteString getTokenTickerBytes() {
            return ((Token) this.instance).getTokenTickerBytes();
        }

        public Builder setTokenTicker(String str) {
            copyOnWrite();
            ((Token) this.instance).setTokenTicker(str);
            return this;
        }

        public Builder clearTokenTicker() {
            copyOnWrite();
            ((Token) this.instance).clearTokenTicker();
            return this;
        }

        public Builder setTokenTickerBytes(ByteString byteString) {
            copyOnWrite();
            ((Token) this.instance).setTokenTickerBytes(byteString);
            return this;
        }

        public String getTokenType() {
            return ((Token) this.instance).getTokenType();
        }

        public ByteString getTokenTypeBytes() {
            return ((Token) this.instance).getTokenTypeBytes();
        }

        public Builder setTokenType(String str) {
            copyOnWrite();
            ((Token) this.instance).setTokenType(str);
            return this;
        }

        public Builder clearTokenType() {
            copyOnWrite();
            ((Token) this.instance).clearTokenType();
            return this;
        }

        public Builder setTokenTypeBytes(ByteString byteString) {
            copyOnWrite();
            ((Token) this.instance).setTokenTypeBytes(byteString);
            return this;
        }

        public String getTokenName() {
            return ((Token) this.instance).getTokenName();
        }

        public ByteString getTokenNameBytes() {
            return ((Token) this.instance).getTokenNameBytes();
        }

        public Builder setTokenName(String str) {
            copyOnWrite();
            ((Token) this.instance).setTokenName(str);
            return this;
        }

        public Builder clearTokenName() {
            copyOnWrite();
            ((Token) this.instance).clearTokenName();
            return this;
        }

        public Builder setTokenNameBytes(ByteString byteString) {
            copyOnWrite();
            ((Token) this.instance).setTokenNameBytes(byteString);
            return this;
        }

        public int getTokenDecimals() {
            return ((Token) this.instance).getTokenDecimals();
        }

        public Builder setTokenDecimals(int i) {
            copyOnWrite();
            ((Token) this.instance).setTokenDecimals(i);
            return this;
        }

        public Builder clearTokenDecimals() {
            copyOnWrite();
            ((Token) this.instance).clearTokenDecimals();
            return this;
        }

        public String getTransactionType() {
            return ((Token) this.instance).getTransactionType();
        }

        public ByteString getTransactionTypeBytes() {
            return ((Token) this.instance).getTransactionTypeBytes();
        }

        public Builder setTransactionType(String str) {
            copyOnWrite();
            ((Token) this.instance).setTransactionType(str);
            return this;
        }

        public Builder clearTransactionType() {
            copyOnWrite();
            ((Token) this.instance).clearTransactionType();
            return this;
        }

        public Builder setTransactionTypeBytes(ByteString byteString) {
            copyOnWrite();
            ((Token) this.instance).setTransactionTypeBytes(byteString);
            return this;
        }

        public int getTokenValidValue() {
            return ((Token) this.instance).getTokenValidValue();
        }

        public Builder setTokenValidValue(int i) {
            copyOnWrite();
            ((Token) this.instance).setTokenValidValue(i);
            return this;
        }

        public TokenValid getTokenValid() {
            return ((Token) this.instance).getTokenValid();
        }

        public Builder setTokenValid(TokenValid tokenValid) {
            copyOnWrite();
            ((Token) this.instance).setTokenValid(tokenValid);
            return this;
        }

        public Builder clearTokenValid() {
            copyOnWrite();
            ((Token) this.instance).clearTokenValid();
            return this;
        }

        public int getParsingStatusValue() {
            return ((Token) this.instance).getParsingStatusValue();
        }

        public Builder setParsingStatusValue(int i) {
            copyOnWrite();
            ((Token) this.instance).setParsingStatusValue(i);
            return this;
        }

        public ParsingStatus getParsingStatus() {
            return ((Token) this.instance).getParsingStatus();
        }

        public Builder setParsingStatus(ParsingStatus parsingStatus) {
            copyOnWrite();
            ((Token) this.instance).setParsingStatus(parsingStatus);
            return this;
        }

        public Builder clearParsingStatus() {
            copyOnWrite();
            ((Token) this.instance).clearParsingStatus();
            return this;
        }
    }

    public enum ParsingStatus implements EnumLite {
        UNKNOWN_PARSING_STATUS(0),
        WITHOUT_TOKEN_DETAILS(1),
        SUCCESSFUL(2),
        UNRECOGNIZED(-1);
        
        public static final int SUCCESSFUL_VALUE = 2;
        public static final int UNKNOWN_PARSING_STATUS_VALUE = 0;
        public static final int WITHOUT_TOKEN_DETAILS_VALUE = 1;
        private static final EnumLiteMap<ParsingStatus> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new EnumLiteMap<ParsingStatus>() {
                public ParsingStatus findValueByNumber(int i) {
                    return ParsingStatus.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ParsingStatus valueOf(int i) {
            return forNumber(i);
        }

        public static ParsingStatus forNumber(int i) {
            if (i == 0) {
                return UNKNOWN_PARSING_STATUS;
            }
            if (i == 1) {
                return WITHOUT_TOKEN_DETAILS;
            }
            if (i != 2) {
                return null;
            }
            return SUCCESSFUL;
        }

        public static EnumLiteMap<ParsingStatus> internalGetValueMap() {
            return internalValueMap;
        }

        private ParsingStatus(int i) {
            this.value = i;
        }
    }

    public enum TokenValid implements EnumLite {
        UNKNOWN_TOKEN_VALID(0),
        INVALID(1),
        VALID(2),
        UNRECOGNIZED(-1);
        
        public static final int INVALID_VALUE = 1;
        public static final int UNKNOWN_TOKEN_VALID_VALUE = 0;
        public static final int VALID_VALUE = 2;
        private static final EnumLiteMap<TokenValid> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new EnumLiteMap<TokenValid>() {
                public TokenValid findValueByNumber(int i) {
                    return TokenValid.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static TokenValid valueOf(int i) {
            return forNumber(i);
        }

        public static TokenValid forNumber(int i) {
            if (i == 0) {
                return UNKNOWN_TOKEN_VALID;
            }
            if (i == 1) {
                return INVALID;
            }
            if (i != 2) {
                return null;
            }
            return VALID;
        }

        public static EnumLiteMap<TokenValid> internalGetValueMap() {
            return internalValueMap;
        }

        private TokenValid(int i) {
            this.value = i;
        }
    }

    private Token() {
        String str = "";
        this.tokenId_ = str;
        this.tokenTicker_ = str;
        this.tokenType_ = str;
        this.tokenName_ = str;
        this.transactionType_ = str;
    }

    public String getTokenId() {
        return this.tokenId_;
    }

    public ByteString getTokenIdBytes() {
        return ByteString.copyFromUtf8(this.tokenId_);
    }

    /* access modifiers changed from: private */
    public void setTokenId(String str) {
        if (str != null) {
            this.tokenId_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearTokenId() {
        this.tokenId_ = getDefaultInstance().getTokenId();
    }

    /* access modifiers changed from: private */
    public void setTokenIdBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.tokenId_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public long getTokenValue() {
        return this.tokenValue_;
    }

    /* access modifiers changed from: private */
    public void setTokenValue(long j) {
        this.tokenValue_ = j;
    }

    /* access modifiers changed from: private */
    public void clearTokenValue() {
        this.tokenValue_ = 0;
    }

    public String getTokenTicker() {
        return this.tokenTicker_;
    }

    public ByteString getTokenTickerBytes() {
        return ByteString.copyFromUtf8(this.tokenTicker_);
    }

    /* access modifiers changed from: private */
    public void setTokenTicker(String str) {
        if (str != null) {
            this.tokenTicker_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearTokenTicker() {
        this.tokenTicker_ = getDefaultInstance().getTokenTicker();
    }

    /* access modifiers changed from: private */
    public void setTokenTickerBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.tokenTicker_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getTokenType() {
        return this.tokenType_;
    }

    public ByteString getTokenTypeBytes() {
        return ByteString.copyFromUtf8(this.tokenType_);
    }

    /* access modifiers changed from: private */
    public void setTokenType(String str) {
        if (str != null) {
            this.tokenType_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearTokenType() {
        this.tokenType_ = getDefaultInstance().getTokenType();
    }

    /* access modifiers changed from: private */
    public void setTokenTypeBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.tokenType_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getTokenName() {
        return this.tokenName_;
    }

    public ByteString getTokenNameBytes() {
        return ByteString.copyFromUtf8(this.tokenName_);
    }

    /* access modifiers changed from: private */
    public void setTokenName(String str) {
        if (str != null) {
            this.tokenName_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearTokenName() {
        this.tokenName_ = getDefaultInstance().getTokenName();
    }

    /* access modifiers changed from: private */
    public void setTokenNameBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.tokenName_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public int getTokenDecimals() {
        return this.tokenDecimals_;
    }

    /* access modifiers changed from: private */
    public void setTokenDecimals(int i) {
        this.tokenDecimals_ = i;
    }

    /* access modifiers changed from: private */
    public void clearTokenDecimals() {
        this.tokenDecimals_ = 0;
    }

    public String getTransactionType() {
        return this.transactionType_;
    }

    public ByteString getTransactionTypeBytes() {
        return ByteString.copyFromUtf8(this.transactionType_);
    }

    /* access modifiers changed from: private */
    public void setTransactionType(String str) {
        if (str != null) {
            this.transactionType_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearTransactionType() {
        this.transactionType_ = getDefaultInstance().getTransactionType();
    }

    /* access modifiers changed from: private */
    public void setTransactionTypeBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.transactionType_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public int getTokenValidValue() {
        return this.tokenValid_;
    }

    public TokenValid getTokenValid() {
        TokenValid forNumber = TokenValid.forNumber(this.tokenValid_);
        return forNumber == null ? TokenValid.UNRECOGNIZED : forNumber;
    }

    /* access modifiers changed from: private */
    public void setTokenValidValue(int i) {
        this.tokenValid_ = i;
    }

    /* access modifiers changed from: private */
    public void setTokenValid(TokenValid tokenValid) {
        if (tokenValid != null) {
            this.tokenValid_ = tokenValid.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearTokenValid() {
        this.tokenValid_ = 0;
    }

    public int getParsingStatusValue() {
        return this.parsingStatus_;
    }

    public ParsingStatus getParsingStatus() {
        ParsingStatus forNumber = ParsingStatus.forNumber(this.parsingStatus_);
        return forNumber == null ? ParsingStatus.UNRECOGNIZED : forNumber;
    }

    /* access modifiers changed from: private */
    public void setParsingStatusValue(int i) {
        this.parsingStatus_ = i;
    }

    /* access modifiers changed from: private */
    public void setParsingStatus(ParsingStatus parsingStatus) {
        if (parsingStatus != null) {
            this.parsingStatus_ = parsingStatus.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearParsingStatus() {
        this.parsingStatus_ = 0;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.tokenId_.isEmpty()) {
            codedOutputStream.writeString(1, getTokenId());
        }
        long j = this.tokenValue_;
        if (j != 0) {
            codedOutputStream.writeUInt64(2, j);
        }
        if (!this.tokenTicker_.isEmpty()) {
            codedOutputStream.writeString(3, getTokenTicker());
        }
        if (!this.tokenType_.isEmpty()) {
            codedOutputStream.writeString(4, getTokenType());
        }
        if (!this.tokenName_.isEmpty()) {
            codedOutputStream.writeString(5, getTokenName());
        }
        int i = this.tokenDecimals_;
        if (i != 0) {
            codedOutputStream.writeUInt32(6, i);
        }
        if (!this.transactionType_.isEmpty()) {
            codedOutputStream.writeString(7, getTransactionType());
        }
        if (this.tokenValid_ != TokenValid.UNKNOWN_TOKEN_VALID.getNumber()) {
            codedOutputStream.writeEnum(8, this.tokenValid_);
        }
        if (this.parsingStatus_ != ParsingStatus.UNKNOWN_PARSING_STATUS.getNumber()) {
            codedOutputStream.writeEnum(9, this.parsingStatus_);
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.tokenId_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getTokenId());
        }
        long j = this.tokenValue_;
        if (j != 0) {
            i2 += CodedOutputStream.computeUInt64Size(2, j);
        }
        if (!this.tokenTicker_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(3, getTokenTicker());
        }
        if (!this.tokenType_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(4, getTokenType());
        }
        if (!this.tokenName_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(5, getTokenName());
        }
        int i3 = this.tokenDecimals_;
        if (i3 != 0) {
            i2 += CodedOutputStream.computeUInt32Size(6, i3);
        }
        if (!this.transactionType_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(7, getTransactionType());
        }
        if (this.tokenValid_ != TokenValid.UNKNOWN_TOKEN_VALID.getNumber()) {
            i2 += CodedOutputStream.computeEnumSize(8, this.tokenValid_);
        }
        if (this.parsingStatus_ != ParsingStatus.UNKNOWN_PARSING_STATUS.getNumber()) {
            i2 += CodedOutputStream.computeEnumSize(9, this.parsingStatus_);
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static Token parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Token) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Token parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Token) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Token parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Token) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Token parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Token) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Token parseFrom(InputStream inputStream) throws IOException {
        return (Token) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Token parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Token) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Token parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Token) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Token parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Token) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Token parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Token) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Token parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Token) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Token token) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(token);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new Token();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                Token token = (Token) obj2;
                this.tokenId_ = visitor.visitString(!this.tokenId_.isEmpty(), this.tokenId_, !token.tokenId_.isEmpty(), token.tokenId_);
                this.tokenValue_ = visitor.visitLong(this.tokenValue_ != 0, this.tokenValue_, token.tokenValue_ != 0, token.tokenValue_);
                this.tokenTicker_ = visitor.visitString(!this.tokenTicker_.isEmpty(), this.tokenTicker_, !token.tokenTicker_.isEmpty(), token.tokenTicker_);
                this.tokenType_ = visitor.visitString(!this.tokenType_.isEmpty(), this.tokenType_, !token.tokenType_.isEmpty(), token.tokenType_);
                this.tokenName_ = visitor.visitString(!this.tokenName_.isEmpty(), this.tokenName_, !token.tokenName_.isEmpty(), token.tokenName_);
                this.tokenDecimals_ = visitor.visitInt(this.tokenDecimals_ != 0, this.tokenDecimals_, token.tokenDecimals_ != 0, token.tokenDecimals_);
                this.transactionType_ = visitor.visitString(!this.transactionType_.isEmpty(), this.transactionType_, !token.transactionType_.isEmpty(), token.transactionType_);
                this.tokenValid_ = visitor.visitInt(this.tokenValid_ != 0, this.tokenValid_, token.tokenValid_ != 0, token.tokenValid_);
                boolean z2 = this.parsingStatus_ != 0;
                int i = this.parsingStatus_;
                if (token.parsingStatus_ != 0) {
                    z = true;
                }
                this.parsingStatus_ = visitor.visitInt(z2, i, z, token.parsingStatus_);
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
                                this.tokenId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 16) {
                                this.tokenValue_ = codedInputStream.readUInt64();
                            } else if (readTag == 26) {
                                this.tokenTicker_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 34) {
                                this.tokenType_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 42) {
                                this.tokenName_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 48) {
                                this.tokenDecimals_ = codedInputStream.readUInt32();
                            } else if (readTag == 58) {
                                this.transactionType_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 64) {
                                this.tokenValid_ = codedInputStream.readEnum();
                            } else if (readTag == 72) {
                                this.parsingStatus_ = codedInputStream.readEnum();
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
                    synchronized (Token.class) {
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

    public static Token getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Token> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
