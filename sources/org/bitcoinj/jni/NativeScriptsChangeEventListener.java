package org.bitcoinj.jni;

import java.util.List;
import org.bitcoinj.script.Script;
import org.bitcoinj.wallet.C3530Wallet;
import org.bitcoinj.wallet.listeners.ScriptsChangeEventListener;

public class NativeScriptsChangeEventListener implements ScriptsChangeEventListener {
    public long ptr;

    public native void onScriptsChanged(C3530Wallet wallet, List<Script> list, boolean z);
}
