package org.bitcoin;

import java.security.AccessControlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Secp256k1Context {
    private static final long context;
    private static final boolean enabled;
    private static final Logger log = LoggerFactory.getLogger(Secp256k1Context.class);

    private static native long secp256k1_init_context();

    static {
        boolean z = false;
        long j = -1;
        try {
            System.loadLibrary("secp256k1");
            j = secp256k1_init_context();
            z = true;
        } catch (UnsatisfiedLinkError e) {
            log.info(e.toString());
        } catch (AccessControlException e2) {
            log.debug(e2.toString());
        }
        enabled = z;
        context = j;
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static long getContext() {
        if (!enabled) {
            return -1;
        }
        return context;
    }
}
