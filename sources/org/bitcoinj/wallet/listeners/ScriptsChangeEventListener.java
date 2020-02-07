package org.bitcoinj.wallet.listeners;

import java.util.List;
import org.bitcoinj.script.Script;
import org.bitcoinj.wallet.C3530Wallet;

public interface ScriptsChangeEventListener {
    void onScriptsChanged(C3530Wallet wallet, List<Script> list, boolean z);
}
