package com.bitcoin.mwallet;

import com.bitcoin.mwallet.MigrateSingleSigWalletResponse.ResponseCase;
import com.google.protobuf.MessageLiteOrBuilder;

public interface MigrateSingleSigWalletResponseOrBuilder extends MessageLiteOrBuilder {
    ResponseError getError();

    MigrateResponse getMigrateResponse();

    MigrationError getMigrationError();

    int getMigrationErrorValue();

    ResponseCase getResponseCase();
}
