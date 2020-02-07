package org.bitcoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NativeSecp256k1Util {
    private static final Logger log = LoggerFactory.getLogger(NativeSecp256k1Util.class);

    public static class AssertFailException extends Exception {
        public AssertFailException(String str) {
            super(str);
        }
    }

    public static void assertEquals(int i, int i2, String str) throws AssertFailException {
        if (i != i2) {
            StringBuilder sb = new StringBuilder();
            sb.append("FAIL: ");
            sb.append(str);
            throw new AssertFailException(sb.toString());
        }
    }

    public static void assertEquals(boolean z, boolean z2, String str) throws AssertFailException {
        if (z == z2) {
            Logger logger = log;
            StringBuilder sb = new StringBuilder();
            sb.append("PASS: ");
            sb.append(str);
            logger.debug(sb.toString());
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("FAIL: ");
        sb2.append(str);
        throw new AssertFailException(sb2.toString());
    }

    public static void assertEquals(String str, String str2, String str3) throws AssertFailException {
        if (str.equals(str2)) {
            Logger logger = log;
            StringBuilder sb = new StringBuilder();
            sb.append("PASS: ");
            sb.append(str3);
            logger.debug(sb.toString());
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("FAIL: ");
        sb2.append(str3);
        throw new AssertFailException(sb2.toString());
    }
}
