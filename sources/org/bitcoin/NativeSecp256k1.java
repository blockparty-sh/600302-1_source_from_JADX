package org.bitcoin;

import com.google.common.base.Preconditions;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.bitcoin.NativeSecp256k1Util.AssertFailException;

public class NativeSecp256k1 {
    private static ThreadLocal<ByteBuffer> nativeECDSABuffer = new ThreadLocal<>();

    /* renamed from: r */
    private static final Lock f789r = rwl.readLock();
    private static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    /* renamed from: w */
    private static final Lock f790w = rwl.writeLock();

    private static native int secp256k1_context_randomize(ByteBuffer byteBuffer, long j);

    private static native long secp256k1_ctx_clone(long j);

    private static native void secp256k1_destroy_context(long j);

    private static native byte[][] secp256k1_ec_pubkey_create(ByteBuffer byteBuffer, long j);

    private static native byte[][] secp256k1_ec_pubkey_parse(ByteBuffer byteBuffer, long j, int i);

    private static native int secp256k1_ec_seckey_verify(ByteBuffer byteBuffer, long j);

    private static native byte[][] secp256k1_ecdh(ByteBuffer byteBuffer, long j, int i);

    private static native byte[][] secp256k1_ecdsa_sign(ByteBuffer byteBuffer, long j);

    private static native int secp256k1_ecdsa_verify(ByteBuffer byteBuffer, long j, int i, int i2);

    private static native byte[][] secp256k1_privkey_tweak_add(ByteBuffer byteBuffer, long j);

    private static native byte[][] secp256k1_privkey_tweak_mul(ByteBuffer byteBuffer, long j);

    private static native byte[][] secp256k1_pubkey_tweak_add(ByteBuffer byteBuffer, long j, int i);

    private static native byte[][] secp256k1_pubkey_tweak_mul(ByteBuffer byteBuffer, long j, int i);

    private static native byte[][] secp256k1_schnorr_sign(ByteBuffer byteBuffer, long j);

    public static boolean verify(byte[] bArr, byte[] bArr2, byte[] bArr3) throws AssertFailException {
        boolean z = false;
        Preconditions.checkArgument(bArr.length == 32 && bArr2.length <= 520 && bArr3.length <= 520);
        ByteBuffer byteBuffer = (ByteBuffer) nativeECDSABuffer.get();
        if (byteBuffer == null || byteBuffer.capacity() < 520) {
            byteBuffer = ByteBuffer.allocateDirect(520);
            byteBuffer.order(ByteOrder.nativeOrder());
            nativeECDSABuffer.set(byteBuffer);
        }
        byteBuffer.rewind();
        byteBuffer.put(bArr);
        byteBuffer.put(bArr2);
        byteBuffer.put(bArr3);
        f789r.lock();
        try {
            if (secp256k1_ecdsa_verify(byteBuffer, Secp256k1Context.getContext(), bArr2.length, bArr3.length) == 1) {
                z = true;
            }
            return z;
        } finally {
            f789r.unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    public static byte[] sign(byte[] bArr, byte[] bArr2) throws AssertFailException {
        Preconditions.checkArgument(bArr.length == 32 && bArr2.length <= 32);
        ByteBuffer byteBuffer = (ByteBuffer) nativeECDSABuffer.get();
        if (byteBuffer == null || byteBuffer.capacity() < 64) {
            byteBuffer = ByteBuffer.allocateDirect(64);
            byteBuffer.order(ByteOrder.nativeOrder());
            nativeECDSABuffer.set(byteBuffer);
        }
        byteBuffer.rewind();
        byteBuffer.put(bArr);
        byteBuffer.put(bArr2);
        f789r.lock();
        try {
            byte[][] secp256k1_ecdsa_sign = secp256k1_ecdsa_sign(byteBuffer, Secp256k1Context.getContext());
            f789r.unlock();
            byte[] bArr3 = secp256k1_ecdsa_sign[0];
            int intValue = new BigInteger(new byte[]{secp256k1_ecdsa_sign[1][0]}).intValue();
            int intValue2 = new BigInteger(new byte[]{secp256k1_ecdsa_sign[1][1]}).intValue();
            NativeSecp256k1Util.assertEquals(bArr3.length, intValue, "Got bad signature length.");
            if (intValue2 == 0) {
                return new byte[0];
            }
            return bArr3;
        } catch (Throwable th) {
            f789r.unlock();
            throw th;
        }
    }

    public static boolean secKeyVerify(byte[] bArr) {
        boolean z = false;
        Preconditions.checkArgument(bArr.length == 32);
        ByteBuffer byteBuffer = (ByteBuffer) nativeECDSABuffer.get();
        if (byteBuffer == null || byteBuffer.capacity() < bArr.length) {
            byteBuffer = ByteBuffer.allocateDirect(bArr.length);
            byteBuffer.order(ByteOrder.nativeOrder());
            nativeECDSABuffer.set(byteBuffer);
        }
        byteBuffer.rewind();
        byteBuffer.put(bArr);
        f789r.lock();
        try {
            if (secp256k1_ec_seckey_verify(byteBuffer, Secp256k1Context.getContext()) == 1) {
                z = true;
            }
            return z;
        } finally {
            f789r.unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    public static byte[] computePubkey(byte[] bArr) throws AssertFailException {
        Preconditions.checkArgument(bArr.length == 32);
        ByteBuffer byteBuffer = (ByteBuffer) nativeECDSABuffer.get();
        if (byteBuffer == null || byteBuffer.capacity() < bArr.length) {
            byteBuffer = ByteBuffer.allocateDirect(bArr.length);
            byteBuffer.order(ByteOrder.nativeOrder());
            nativeECDSABuffer.set(byteBuffer);
        }
        byteBuffer.rewind();
        byteBuffer.put(bArr);
        f789r.lock();
        try {
            byte[][] secp256k1_ec_pubkey_create = secp256k1_ec_pubkey_create(byteBuffer, Secp256k1Context.getContext());
            f789r.unlock();
            byte[] bArr2 = secp256k1_ec_pubkey_create[0];
            int intValue = new BigInteger(new byte[]{secp256k1_ec_pubkey_create[1][0]}).intValue();
            int intValue2 = new BigInteger(new byte[]{secp256k1_ec_pubkey_create[1][1]}).intValue();
            NativeSecp256k1Util.assertEquals(bArr2.length, intValue, "Got bad pubkey length.");
            if (intValue2 == 0) {
                return new byte[0];
            }
            return bArr2;
        } catch (Throwable th) {
            f789r.unlock();
            throw th;
        }
    }

    public static synchronized void cleanup() {
        synchronized (NativeSecp256k1.class) {
            f790w.lock();
            try {
                secp256k1_destroy_context(Secp256k1Context.getContext());
            } finally {
                f790w.unlock();
            }
        }
    }

    public static long cloneContext() {
        f789r.lock();
        try {
            return secp256k1_ctx_clone(Secp256k1Context.getContext());
        } finally {
            f789r.unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    public static byte[] privKeyTweakMul(byte[] bArr, byte[] bArr2) throws AssertFailException {
        Preconditions.checkArgument(bArr.length == 32);
        ByteBuffer byteBuffer = (ByteBuffer) nativeECDSABuffer.get();
        if (byteBuffer == null || byteBuffer.capacity() < bArr.length + bArr2.length) {
            byteBuffer = ByteBuffer.allocateDirect(bArr.length + bArr2.length);
            byteBuffer.order(ByteOrder.nativeOrder());
            nativeECDSABuffer.set(byteBuffer);
        }
        byteBuffer.rewind();
        byteBuffer.put(bArr);
        byteBuffer.put(bArr2);
        f789r.lock();
        try {
            byte[][] secp256k1_privkey_tweak_mul = secp256k1_privkey_tweak_mul(byteBuffer, Secp256k1Context.getContext());
            f789r.unlock();
            byte[] bArr3 = secp256k1_privkey_tweak_mul[0];
            byte intValue = ((byte) new BigInteger(new byte[]{secp256k1_privkey_tweak_mul[1][0]}).intValue()) & 255;
            int intValue2 = new BigInteger(new byte[]{secp256k1_privkey_tweak_mul[1][1]}).intValue();
            NativeSecp256k1Util.assertEquals(bArr3.length, (int) intValue, "Got bad pubkey length.");
            NativeSecp256k1Util.assertEquals(intValue2, 1, "Failed return value check.");
            return bArr3;
        } catch (Throwable th) {
            f789r.unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public static byte[] privKeyTweakAdd(byte[] bArr, byte[] bArr2) throws AssertFailException {
        Preconditions.checkArgument(bArr.length == 32);
        ByteBuffer byteBuffer = (ByteBuffer) nativeECDSABuffer.get();
        if (byteBuffer == null || byteBuffer.capacity() < bArr.length + bArr2.length) {
            byteBuffer = ByteBuffer.allocateDirect(bArr.length + bArr2.length);
            byteBuffer.order(ByteOrder.nativeOrder());
            nativeECDSABuffer.set(byteBuffer);
        }
        byteBuffer.rewind();
        byteBuffer.put(bArr);
        byteBuffer.put(bArr2);
        f789r.lock();
        try {
            byte[][] secp256k1_privkey_tweak_add = secp256k1_privkey_tweak_add(byteBuffer, Secp256k1Context.getContext());
            f789r.unlock();
            byte[] bArr3 = secp256k1_privkey_tweak_add[0];
            byte intValue = ((byte) new BigInteger(new byte[]{secp256k1_privkey_tweak_add[1][0]}).intValue()) & 255;
            int intValue2 = new BigInteger(new byte[]{secp256k1_privkey_tweak_add[1][1]}).intValue();
            NativeSecp256k1Util.assertEquals(bArr3.length, (int) intValue, "Got bad pubkey length.");
            NativeSecp256k1Util.assertEquals(intValue2, 1, "Failed return value check.");
            return bArr3;
        } catch (Throwable th) {
            f789r.unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public static byte[] pubKeyTweakAdd(byte[] bArr, byte[] bArr2) throws AssertFailException {
        Preconditions.checkArgument(bArr.length == 33 || bArr.length == 65);
        ByteBuffer byteBuffer = (ByteBuffer) nativeECDSABuffer.get();
        if (byteBuffer == null || byteBuffer.capacity() < bArr.length + bArr2.length) {
            byteBuffer = ByteBuffer.allocateDirect(bArr.length + bArr2.length);
            byteBuffer.order(ByteOrder.nativeOrder());
            nativeECDSABuffer.set(byteBuffer);
        }
        byteBuffer.rewind();
        byteBuffer.put(bArr);
        byteBuffer.put(bArr2);
        f789r.lock();
        try {
            byte[][] secp256k1_pubkey_tweak_add = secp256k1_pubkey_tweak_add(byteBuffer, Secp256k1Context.getContext(), bArr.length);
            f789r.unlock();
            byte[] bArr3 = secp256k1_pubkey_tweak_add[0];
            byte intValue = ((byte) new BigInteger(new byte[]{secp256k1_pubkey_tweak_add[1][0]}).intValue()) & 255;
            int intValue2 = new BigInteger(new byte[]{secp256k1_pubkey_tweak_add[1][1]}).intValue();
            NativeSecp256k1Util.assertEquals(bArr3.length, (int) intValue, "Got bad pubkey length.");
            NativeSecp256k1Util.assertEquals(intValue2, 1, "Failed return value check.");
            return bArr3;
        } catch (Throwable th) {
            f789r.unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public static byte[] pubKeyTweakMul(byte[] bArr, byte[] bArr2) throws AssertFailException {
        Preconditions.checkArgument(bArr.length == 33 || bArr.length == 65);
        ByteBuffer byteBuffer = (ByteBuffer) nativeECDSABuffer.get();
        if (byteBuffer == null || byteBuffer.capacity() < bArr.length + bArr2.length) {
            byteBuffer = ByteBuffer.allocateDirect(bArr.length + bArr2.length);
            byteBuffer.order(ByteOrder.nativeOrder());
            nativeECDSABuffer.set(byteBuffer);
        }
        byteBuffer.rewind();
        byteBuffer.put(bArr);
        byteBuffer.put(bArr2);
        f789r.lock();
        try {
            byte[][] secp256k1_pubkey_tweak_mul = secp256k1_pubkey_tweak_mul(byteBuffer, Secp256k1Context.getContext(), bArr.length);
            f789r.unlock();
            byte[] bArr3 = secp256k1_pubkey_tweak_mul[0];
            byte intValue = ((byte) new BigInteger(new byte[]{secp256k1_pubkey_tweak_mul[1][0]}).intValue()) & 255;
            int intValue2 = new BigInteger(new byte[]{secp256k1_pubkey_tweak_mul[1][1]}).intValue();
            NativeSecp256k1Util.assertEquals(bArr3.length, (int) intValue, "Got bad pubkey length.");
            NativeSecp256k1Util.assertEquals(intValue2, 1, "Failed return value check.");
            return bArr3;
        } catch (Throwable th) {
            f789r.unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public static byte[] createECDHSecret(byte[] bArr, byte[] bArr2) throws AssertFailException {
        Preconditions.checkArgument(bArr.length <= 32 && bArr2.length <= 65);
        ByteBuffer byteBuffer = (ByteBuffer) nativeECDSABuffer.get();
        if (byteBuffer == null || byteBuffer.capacity() < bArr2.length + 32) {
            byteBuffer = ByteBuffer.allocateDirect(bArr2.length + 32);
            byteBuffer.order(ByteOrder.nativeOrder());
            nativeECDSABuffer.set(byteBuffer);
        }
        byteBuffer.rewind();
        byteBuffer.put(bArr);
        byteBuffer.put(bArr2);
        f789r.lock();
        try {
            byte[][] secp256k1_ecdh = secp256k1_ecdh(byteBuffer, Secp256k1Context.getContext(), bArr2.length);
            f789r.unlock();
            byte[] bArr3 = secp256k1_ecdh[0];
            int intValue = new BigInteger(new byte[]{secp256k1_ecdh[1][0]}).intValue();
            NativeSecp256k1Util.assertEquals(bArr3.length, 32, "Got bad result length.");
            NativeSecp256k1Util.assertEquals(intValue, 1, "Failed return value check.");
            return bArr3;
        } catch (Throwable th) {
            f789r.unlock();
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x004a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized boolean randomize(byte[] r7) throws org.bitcoin.NativeSecp256k1Util.AssertFailException {
        /*
            java.lang.Class<org.bitcoin.NativeSecp256k1> r0 = org.bitcoin.NativeSecp256k1.class
            monitor-enter(r0)
            int r1 = r7.length     // Catch:{ all -> 0x0059 }
            r2 = 32
            r3 = 0
            r4 = 1
            if (r1 == r2) goto L_0x000f
            if (r7 != 0) goto L_0x000d
            goto L_0x000f
        L_0x000d:
            r1 = 0
            goto L_0x0010
        L_0x000f:
            r1 = 1
        L_0x0010:
            com.google.common.base.Preconditions.checkArgument(r1)     // Catch:{ all -> 0x0059 }
            java.lang.ThreadLocal<java.nio.ByteBuffer> r1 = nativeECDSABuffer     // Catch:{ all -> 0x0059 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0059 }
            java.nio.ByteBuffer r1 = (java.nio.ByteBuffer) r1     // Catch:{ all -> 0x0059 }
            if (r1 == 0) goto L_0x0024
            int r2 = r1.capacity()     // Catch:{ all -> 0x0059 }
            int r5 = r7.length     // Catch:{ all -> 0x0059 }
            if (r2 >= r5) goto L_0x0035
        L_0x0024:
            int r1 = r7.length     // Catch:{ all -> 0x0059 }
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocateDirect(r1)     // Catch:{ all -> 0x0059 }
            java.nio.ByteOrder r2 = java.nio.ByteOrder.nativeOrder()     // Catch:{ all -> 0x0059 }
            r1.order(r2)     // Catch:{ all -> 0x0059 }
            java.lang.ThreadLocal<java.nio.ByteBuffer> r2 = nativeECDSABuffer     // Catch:{ all -> 0x0059 }
            r2.set(r1)     // Catch:{ all -> 0x0059 }
        L_0x0035:
            r1.rewind()     // Catch:{ all -> 0x0059 }
            r1.put(r7)     // Catch:{ all -> 0x0059 }
            java.util.concurrent.locks.Lock r7 = f790w     // Catch:{ all -> 0x0059 }
            r7.lock()     // Catch:{ all -> 0x0059 }
            long r5 = org.bitcoin.Secp256k1Context.getContext()     // Catch:{ all -> 0x0052 }
            int r7 = secp256k1_context_randomize(r1, r5)     // Catch:{ all -> 0x0052 }
            if (r7 != r4) goto L_0x004b
            r3 = 1
        L_0x004b:
            java.util.concurrent.locks.Lock r7 = f790w     // Catch:{ all -> 0x0059 }
            r7.unlock()     // Catch:{ all -> 0x0059 }
            monitor-exit(r0)
            return r3
        L_0x0052:
            r7 = move-exception
            java.util.concurrent.locks.Lock r1 = f790w     // Catch:{ all -> 0x0059 }
            r1.unlock()     // Catch:{ all -> 0x0059 }
            throw r7     // Catch:{ all -> 0x0059 }
        L_0x0059:
            r7 = move-exception
            monitor-exit(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoin.NativeSecp256k1.randomize(byte[]):boolean");
    }

    /* JADX INFO: finally extract failed */
    public static byte[] schnorrSign(byte[] bArr, byte[] bArr2) throws AssertFailException {
        Preconditions.checkArgument(bArr.length == 32 && bArr2.length <= 32);
        ByteBuffer byteBuffer = (ByteBuffer) nativeECDSABuffer.get();
        if (byteBuffer == null) {
            byteBuffer = ByteBuffer.allocateDirect(64);
            byteBuffer.order(ByteOrder.nativeOrder());
            nativeECDSABuffer.set(byteBuffer);
        }
        byteBuffer.rewind();
        byteBuffer.put(bArr);
        byteBuffer.put(bArr2);
        f789r.lock();
        try {
            byte[][] secp256k1_schnorr_sign = secp256k1_schnorr_sign(byteBuffer, Secp256k1Context.getContext());
            f789r.unlock();
            byte[] bArr3 = secp256k1_schnorr_sign[0];
            int intValue = new BigInteger(new byte[]{secp256k1_schnorr_sign[1][0]}).intValue();
            NativeSecp256k1Util.assertEquals(bArr3.length, 64, "Got bad signature length.");
            if (intValue == 0) {
                return new byte[0];
            }
            return bArr3;
        } catch (Throwable th) {
            f789r.unlock();
            throw th;
        }
    }
}
