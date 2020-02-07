package org.bitcoinj.store;

import java.nio.MappedByteBuffer;
import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;

public class WindowsMMapHack {
    public static void forceRelease(MappedByteBuffer mappedByteBuffer) {
        Cleaner cleaner = ((DirectBuffer) mappedByteBuffer).cleaner();
        if (cleaner != null) {
            cleaner.clean();
        }
    }
}
