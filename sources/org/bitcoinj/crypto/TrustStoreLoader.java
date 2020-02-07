package org.bitcoinj.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import javax.annotation.Nonnull;

public interface TrustStoreLoader {
    public static final String DEFAULT_KEYSTORE_PASSWORD = "changeit";
    public static final String DEFAULT_KEYSTORE_TYPE = KeyStore.getDefaultType();

    public static class DefaultTrustStoreLoader implements TrustStoreLoader {
        public KeyStore getKeyStore() throws FileNotFoundException, KeyStoreException {
            String str;
            String str2 = DEFAULT_KEYSTORE_TYPE;
            try {
                Class cls = Class.forName("android.os.Build$VERSION");
                if (cls.getDeclaredField("SDK_INT").getInt(cls) >= 14) {
                    return loadIcsKeyStore();
                }
                str2 = "BKS";
                StringBuilder sb = new StringBuilder();
                sb.append(System.getProperty("java.home"));
                sb.append("/etc/security/cacerts.bks".replace('/', File.separatorChar));
                str = sb.toString();
                if (str == null) {
                    str = System.getProperty("javax.net.ssl.trustStore");
                }
                if (str == null) {
                    return loadFallbackStore();
                }
                try {
                    return X509Utils.loadKeyStore(str2, TrustStoreLoader.DEFAULT_KEYSTORE_PASSWORD, new FileInputStream(str));
                } catch (FileNotFoundException unused) {
                    return loadFallbackStore();
                }
            } catch (ClassNotFoundException unused2) {
                str = null;
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            }
        }

        private KeyStore loadIcsKeyStore() throws KeyStoreException {
            try {
                KeyStore instance = KeyStore.getInstance("AndroidCAStore");
                instance.load(null, null);
                return instance;
            } catch (IOException e) {
                throw new KeyStoreException(e);
            } catch (GeneralSecurityException e2) {
                throw new KeyStoreException(e2);
            }
        }

        private KeyStore loadFallbackStore() throws FileNotFoundException, KeyStoreException {
            return X509Utils.loadKeyStore("JKS", TrustStoreLoader.DEFAULT_KEYSTORE_PASSWORD, getClass().getResourceAsStream("cacerts"));
        }
    }

    public static class FileTrustStoreLoader implements TrustStoreLoader {
        private final File path;

        public FileTrustStoreLoader(@Nonnull File file) throws FileNotFoundException {
            if (file.exists()) {
                this.path = file;
                return;
            }
            throw new FileNotFoundException(file.toString());
        }

        public KeyStore getKeyStore() throws FileNotFoundException, KeyStoreException {
            return X509Utils.loadKeyStore(DEFAULT_KEYSTORE_TYPE, TrustStoreLoader.DEFAULT_KEYSTORE_PASSWORD, new FileInputStream(this.path));
        }
    }

    KeyStore getKeyStore() throws FileNotFoundException, KeyStoreException;
}
