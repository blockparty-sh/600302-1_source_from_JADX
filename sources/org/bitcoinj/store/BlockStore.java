package org.bitcoinj.store;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.StoredBlock;

public interface BlockStore {
    void close() throws BlockStoreException;

    StoredBlock get(Sha256Hash sha256Hash) throws BlockStoreException;

    StoredBlock getChainHead() throws BlockStoreException;

    NetworkParameters getParams();

    void put(StoredBlock storedBlock) throws BlockStoreException;

    void setChainHead(StoredBlock storedBlock) throws BlockStoreException;
}
