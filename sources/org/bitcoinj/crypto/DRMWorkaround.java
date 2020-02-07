package org.bitcoinj.crypto;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.Permission;
import org.bitcoinj.core.C3447Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DRMWorkaround {
    private static boolean done = false;
    private static Logger log = LoggerFactory.getLogger(DRMWorkaround.class);

    public static void maybeDisableExportControls() {
        String str = "javax.crypto.CryptoPermissions";
        String str2 = "javax.crypto.JceSecurity";
        if (!done) {
            done = true;
            if (!C3447Utils.isAndroidRuntime()) {
                try {
                    Field declaredField = Class.forName(str2).getDeclaredField("isRestricted");
                    declaredField.setAccessible(true);
                    declaredField.setBoolean(null, false);
                    Field declaredField2 = Class.forName("javax.crypto.CryptoAllPermission").getDeclaredField("INSTANCE");
                    declaredField2.setAccessible(true);
                    Object obj = declaredField2.get(null);
                    Constructor declaredConstructor = Class.forName(str).getDeclaredConstructor(new Class[0]);
                    declaredConstructor.setAccessible(true);
                    Object newInstance = declaredConstructor.newInstance(new Object[0]);
                    Method declaredMethod = Class.forName(str).getDeclaredMethod("add", new Class[]{Permission.class});
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(newInstance, new Object[]{obj});
                    Field declaredField3 = Class.forName(str2).getDeclaredField("defaultPolicy");
                    declaredField3.setAccessible(true);
                    declaredField3.set(null, newInstance);
                } catch (Exception e) {
                    Logger logger = log;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to deactivate AES-256 barrier logic, Tor mode/BIP38 decryption may crash if this JVM requires it: ");
                    sb.append(e.getMessage());
                    logger.warn(sb.toString());
                }
            }
        }
    }
}
