package org.bitcoinj.core;

import java.util.List;

public interface UTXOProvider {
    int getChainHeadHeight() throws UTXOProviderException;

    List<UTXO> getOpenTransactionOutputs(List<Address> list) throws UTXOProviderException;

    NetworkParameters getParams();
}
