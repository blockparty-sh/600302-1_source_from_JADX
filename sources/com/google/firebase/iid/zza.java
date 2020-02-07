package com.google.firebase.iid;

import com.yakivmospan.scytale.Options;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/* compiled from: com.google.firebase:firebase-iid@@19.0.1 */
public final class zza {
    public static KeyPair zza() {
        try {
            KeyPairGenerator instance = KeyPairGenerator.getInstance(Options.ALGORITHM_RSA);
            instance.initialize(2048);
            return instance.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }
}
