package com.bitcoin.mwallet;

import com.bitcoin.mwallet.Token.ParsingStatus;
import com.bitcoin.mwallet.Token.TokenValid;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface TokenOrBuilder extends MessageLiteOrBuilder {
    ParsingStatus getParsingStatus();

    int getParsingStatusValue();

    int getTokenDecimals();

    String getTokenId();

    ByteString getTokenIdBytes();

    String getTokenName();

    ByteString getTokenNameBytes();

    String getTokenTicker();

    ByteString getTokenTickerBytes();

    String getTokenType();

    ByteString getTokenTypeBytes();

    TokenValid getTokenValid();

    int getTokenValidValue();

    long getTokenValue();

    String getTransactionType();

    ByteString getTransactionTypeBytes();
}
