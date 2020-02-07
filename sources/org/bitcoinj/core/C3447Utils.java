package org.bitcoinj.core;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.p011io.BaseEncoding;
import com.google.common.p011io.Resources;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedLongs;
import com.google.common.util.concurrent.Uninterruptibles;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import org.spongycastle.crypto.digests.RIPEMD160Digest;

/* renamed from: org.bitcoinj.core.Utils */
public class C3447Utils {
    public static final String BITCOIN_SIGNED_MESSAGE_HEADER = "Bitcoin Signed Message:\n";
    public static final byte[] BITCOIN_SIGNED_MESSAGE_HEADER_BYTES = BITCOIN_SIGNED_MESSAGE_HEADER.getBytes(Charsets.UTF_8);
    static long ForkBlockTime = 1501593374;
    public static final BaseEncoding HEX = BaseEncoding.base16().lowerCase();
    private static final Joiner SPACE_JOINER = Joiner.m96on(" ");
    private static final TimeZone UTC = TimeZone.getTimeZone("UTC");
    private static final int[] bitMask = {1, 2, 4, 8, 16, 32, 64, 128};
    private static int isAndroid = -1;
    private static BlockingQueue<Boolean> mockSleepQueue;
    public static volatile Date mockTime;

    /* renamed from: org.bitcoinj.core.Utils$Pair */
    private static class Pair implements Comparable<Pair> {
        int count;
        int item;

        public Pair(int i, int i2) {
            this.count = i2;
            this.item = i;
        }

        public int compareTo(Pair pair) {
            return -Ints.compare(this.count, pair.count);
        }
    }

    public static byte[] bigIntegerToBytes(BigInteger bigInteger, int i) {
        if (bigInteger == null) {
            return null;
        }
        byte[] bArr = new byte[i];
        byte[] byteArray = bigInteger.toByteArray();
        int i2 = byteArray.length == i + 1 ? 1 : 0;
        int min = Math.min(byteArray.length, i);
        System.arraycopy(byteArray, i2, bArr, i - min, min);
        return bArr;
    }

    public static void uint32ToByteArrayBE(long j, byte[] bArr, int i) {
        bArr[i] = (byte) ((int) ((j >> 24) & 255));
        bArr[i + 1] = (byte) ((int) ((j >> 16) & 255));
        bArr[i + 2] = (byte) ((int) ((j >> 8) & 255));
        bArr[i + 3] = (byte) ((int) (j & 255));
    }

    public static void uint32ToByteArrayLE(long j, byte[] bArr, int i) {
        bArr[i] = (byte) ((int) (j & 255));
        bArr[i + 1] = (byte) ((int) ((j >> 8) & 255));
        bArr[i + 2] = (byte) ((int) ((j >> 16) & 255));
        bArr[i + 3] = (byte) ((int) ((j >> 24) & 255));
    }

    public static void uint64ToByteArrayLE(long j, byte[] bArr, int i) {
        bArr[i] = (byte) ((int) (j & 255));
        bArr[i + 1] = (byte) ((int) ((j >> 8) & 255));
        bArr[i + 2] = (byte) ((int) ((j >> 16) & 255));
        bArr[i + 3] = (byte) ((int) ((j >> 24) & 255));
        bArr[i + 4] = (byte) ((int) ((j >> 32) & 255));
        bArr[i + 5] = (byte) ((int) ((j >> 40) & 255));
        bArr[i + 6] = (byte) ((int) ((j >> 48) & 255));
        bArr[i + 7] = (byte) ((int) ((j >> 56) & 255));
    }

    public static void uint32ToByteStreamLE(long j, OutputStream outputStream) throws IOException {
        outputStream.write((int) (j & 255));
        outputStream.write((int) ((j >> 8) & 255));
        outputStream.write((int) ((j >> 16) & 255));
        outputStream.write((int) ((j >> 24) & 255));
    }

    public static void int64ToByteStreamLE(long j, OutputStream outputStream) throws IOException {
        outputStream.write((int) (j & 255));
        outputStream.write((int) ((j >> 8) & 255));
        outputStream.write((int) ((j >> 16) & 255));
        outputStream.write((int) ((j >> 24) & 255));
        outputStream.write((int) ((j >> 32) & 255));
        outputStream.write((int) ((j >> 40) & 255));
        outputStream.write((int) ((j >> 48) & 255));
        outputStream.write((int) ((j >> 56) & 255));
    }

    public static void uint64ToByteStreamLE(BigInteger bigInteger, OutputStream outputStream) throws IOException {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length <= 8) {
            byte[] reverseBytes = reverseBytes(byteArray);
            outputStream.write(reverseBytes);
            if (reverseBytes.length < 8) {
                for (int i = 0; i < 8 - reverseBytes.length; i++) {
                    outputStream.write(0);
                }
                return;
            }
            return;
        }
        throw new RuntimeException("Input too large to encode into a uint64");
    }

    public static boolean isLessThanUnsigned(long j, long j2) {
        return UnsignedLongs.compare(j, j2) < 0;
    }

    public static boolean isLessThanOrEqualToUnsigned(long j, long j2) {
        return UnsignedLongs.compare(j, j2) <= 0;
    }

    public static byte[] reverseBytes(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = bArr[(bArr.length - 1) - i];
        }
        return bArr2;
    }

    public static byte[] reverseDwordBytes(byte[] bArr, int i) {
        boolean z = true;
        Preconditions.checkArgument(bArr.length % 4 == 0);
        if (i >= 0 && i % 4 != 0) {
            z = false;
        }
        Preconditions.checkArgument(z);
        if (i < 0 || bArr.length <= i) {
            i = bArr.length;
        }
        byte[] bArr2 = new byte[i];
        for (int i2 = 0; i2 < bArr2.length; i2 += 4) {
            System.arraycopy(bArr, i2, bArr2, i2, 4);
            for (int i3 = 0; i3 < 4; i3++) {
                bArr2[i2 + i3] = bArr[(i2 + 3) - i3];
            }
        }
        return bArr2;
    }

    public static long readUint32(byte[] bArr, int i) {
        return ((((long) bArr[i + 3]) & 255) << 24) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16);
    }

    public static long readInt64(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    public static long readUint32BE(byte[] bArr, int i) {
        return (((long) bArr[i + 3]) & 255) | ((((long) bArr[i]) & 255) << 24) | ((((long) bArr[i + 1]) & 255) << 16) | ((((long) bArr[i + 2]) & 255) << 8);
    }

    public static int readUint16BE(byte[] bArr, int i) {
        return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
    }

    public static byte[] sha256hash160(byte[] bArr) {
        byte[] hash = Sha256Hash.hash(bArr);
        RIPEMD160Digest rIPEMD160Digest = new RIPEMD160Digest();
        rIPEMD160Digest.update(hash, 0, hash.length);
        byte[] bArr2 = new byte[20];
        rIPEMD160Digest.doFinal(bArr2, 0);
        return bArr2;
    }

    public static BigInteger decodeMPI(byte[] bArr, boolean z) {
        if (z) {
            int readUint32BE = (int) readUint32BE(bArr, 0);
            byte[] bArr2 = new byte[readUint32BE];
            System.arraycopy(bArr, 4, bArr2, 0, readUint32BE);
            bArr = bArr2;
        }
        if (bArr.length == 0) {
            return BigInteger.ZERO;
        }
        boolean z2 = (bArr[0] & 128) == 128;
        if (z2) {
            bArr[0] = (byte) (bArr[0] & Byte.MAX_VALUE);
        }
        BigInteger bigInteger = new BigInteger(bArr);
        if (z2) {
            bigInteger = bigInteger.negate();
        }
        return bigInteger;
    }

    public static byte[] encodeMPI(BigInteger bigInteger, boolean z) {
        if (!bigInteger.equals(BigInteger.ZERO)) {
            boolean z2 = bigInteger.signum() < 0;
            if (z2) {
                bigInteger = bigInteger.negate();
            }
            byte[] byteArray = bigInteger.toByteArray();
            int length = byteArray.length;
            if ((byteArray[0] & 128) == 128) {
                length++;
            }
            if (z) {
                byte[] bArr = new byte[(length + 4)];
                System.arraycopy(byteArray, 0, bArr, (length - byteArray.length) + 3, byteArray.length);
                uint32ToByteArrayBE((long) length, bArr, 0);
                if (z2) {
                    bArr[4] = (byte) (bArr[4] | 128);
                }
                return bArr;
            }
            if (length != byteArray.length) {
                byte[] bArr2 = new byte[length];
                System.arraycopy(byteArray, 0, bArr2, 1, byteArray.length);
                byteArray = bArr2;
            }
            if (z2) {
                byteArray[0] = (byte) (byteArray[0] | 128);
            }
            return byteArray;
        } else if (!z) {
            return new byte[0];
        } else {
            return new byte[]{0, 0, 0, 0};
        }
    }

    public static BigInteger decodeCompactBits(long j) {
        int i = ((int) (j >> 24)) & 255;
        byte[] bArr = new byte[(i + 4)];
        bArr[3] = (byte) i;
        if (i >= 1) {
            bArr[4] = (byte) ((int) ((j >> 16) & 255));
        }
        if (i >= 2) {
            bArr[5] = (byte) ((int) ((j >> 8) & 255));
        }
        if (i >= 3) {
            bArr[6] = (byte) ((int) (j & 255));
        }
        return decodeMPI(bArr, true);
    }

    public static long encodeCompactBits(BigInteger bigInteger) {
        long j;
        int length = bigInteger.toByteArray().length;
        if (length <= 3) {
            j = bigInteger.longValue() << ((3 - length) * 8);
        } else {
            j = bigInteger.shiftRight((length - 3) * 8).longValue();
        }
        long j2 = 8388608;
        if ((j & 8388608) != 0) {
            j >>= 8;
            length++;
        }
        long j3 = ((long) (length << 24)) | j;
        if (bigInteger.signum() != -1) {
            j2 = 0;
        }
        return j3 | j2;
    }

    public static Date rollMockClock(int i) {
        return rollMockClockMillis((long) (i * 1000));
    }

    public static Date rollMockClockMillis(long j) {
        if (mockTime != null) {
            mockTime = new Date(mockTime.getTime() + j);
            return mockTime;
        }
        throw new IllegalStateException("You need to use setMockClock() first.");
    }

    public static void setMockClock() {
        mockTime = new Date();
    }

    public static void setMockClock(long j) {
        mockTime = new Date(j * 1000);
    }

    public static Date now() {
        return mockTime != null ? mockTime : new Date();
    }

    public static long currentTimeMillis() {
        return mockTime != null ? mockTime.getTime() : System.currentTimeMillis();
    }

    public static long currentTimeSeconds() {
        return currentTimeMillis() / 1000;
    }

    public static String dateTimeFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpleDateFormat.setTimeZone(UTC);
        return simpleDateFormat.format(date);
    }

    public static String dateTimeFormat(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpleDateFormat.setTimeZone(UTC);
        return simpleDateFormat.format(Long.valueOf(j));
    }

    public static <T> String join(Iterable<T> iterable) {
        return SPACE_JOINER.join(iterable);
    }

    public static byte[] copyOf(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, Math.min(i, bArr.length));
        return bArr2;
    }

    public static byte[] appendByte(byte[] bArr, byte b) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length + 1);
        copyOf[copyOf.length - 1] = b;
        return copyOf;
    }

    public static String toString(byte[] bArr, String str) {
        try {
            return new String(bArr, str);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] toBytes(CharSequence charSequence, String str) {
        try {
            return charSequence.toString().getBytes(str);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:3|4|5) */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000b, code lost:
        return org.bitcoinj.core.Base58.decodeChecked(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000d, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0007 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] parseAsHexOrBase58(java.lang.String r1) {
        /*
            com.google.common.io.BaseEncoding r0 = HEX     // Catch:{ Exception -> 0x0007 }
            byte[] r1 = r0.decode(r1)     // Catch:{ Exception -> 0x0007 }
            return r1
        L_0x0007:
            byte[] r1 = org.bitcoinj.core.Base58.decodeChecked(r1)     // Catch:{ AddressFormatException -> 0x000c }
            return r1
        L_0x000c:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.core.C3447Utils.parseAsHexOrBase58(java.lang.String):byte[]");
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    public static byte[] formatMessageForSigning(String str) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(BITCOIN_SIGNED_MESSAGE_HEADER_BYTES.length);
            byteArrayOutputStream.write(BITCOIN_SIGNED_MESSAGE_HEADER_BYTES);
            byte[] bytes = str.getBytes(Charsets.UTF_8);
            byteArrayOutputStream.write(new VarInt((long) bytes.length).encode());
            byteArrayOutputStream.write(bytes);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkBitLE(byte[] bArr, int i) {
        return (bArr[i >>> 3] & bitMask[i & 7]) != 0;
    }

    public static void setBitLE(byte[] bArr, int i) {
        int i2 = i >>> 3;
        bArr[i2] = (byte) (bitMask[i & 7] | bArr[i2]);
    }

    public static void sleep(long j) {
        BlockingQueue<Boolean> blockingQueue = mockSleepQueue;
        if (blockingQueue == null) {
            Uninterruptibles.sleepUninterruptibly(j, TimeUnit.MILLISECONDS);
            return;
        }
        try {
            boolean booleanValue = ((Boolean) blockingQueue.take()).booleanValue();
            rollMockClockMillis(j);
            if (booleanValue) {
                mockSleepQueue.offer(Boolean.valueOf(true));
            }
        } catch (InterruptedException unused) {
        }
    }

    public static void setMockSleep(boolean z) {
        if (z) {
            mockSleepQueue = new ArrayBlockingQueue(1);
            mockTime = new Date(System.currentTimeMillis());
            return;
        }
        mockSleepQueue = null;
    }

    public static void passMockSleep() {
        mockSleepQueue.offer(Boolean.valueOf(false));
    }

    public static void finishMockSleep() {
        BlockingQueue<Boolean> blockingQueue = mockSleepQueue;
        if (blockingQueue != null) {
            blockingQueue.offer(Boolean.valueOf(true));
        }
    }

    public static boolean isAndroidRuntime() {
        if (isAndroid == -1) {
            String property = System.getProperty("java.runtime.name");
            isAndroid = (property == null || !property.equals("Android Runtime")) ? 0 : 1;
        }
        if (isAndroid == 1) {
            return true;
        }
        return false;
    }

    public static int maxOfMostFreq(int... iArr) {
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int valueOf : iArr) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        return maxOfMostFreq((List<Integer>) arrayList);
    }

    public static int maxOfMostFreq(List<Integer> list) {
        if (list.isEmpty()) {
            return 0;
        }
        List<Integer> sortedCopy = Ordering.natural().reverse().sortedCopy(list);
        LinkedList newLinkedList = Lists.newLinkedList();
        newLinkedList.add(new Pair(((Integer) sortedCopy.get(0)).intValue(), 0));
        for (Integer intValue : sortedCopy) {
            int intValue2 = intValue.intValue();
            Pair pair = (Pair) newLinkedList.getLast();
            if (pair.item != intValue2) {
                pair = new Pair(intValue2, 0);
                newLinkedList.add(pair);
            }
            pair.count++;
        }
        Collections.sort(newLinkedList);
        int i = ((Pair) newLinkedList.getFirst()).count;
        int i2 = ((Pair) newLinkedList.getFirst()).item;
        Iterator it = newLinkedList.iterator();
        while (it.hasNext()) {
            Pair pair2 = (Pair) it.next();
            if (pair2.count != i) {
                break;
            }
            i2 = Math.max(i2, pair2.item);
        }
        return i2;
    }

    public static String getResourceAsString(URL url) throws IOException {
        return Joiner.m95on(10).join((Iterable<?>) Resources.readLines(url, Charsets.UTF_8));
    }

    public static InputStream closeUnchecked(InputStream inputStream) {
        try {
            inputStream.close();
            return inputStream;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static OutputStream closeUnchecked(OutputStream outputStream) {
        try {
            outputStream.close();
            return outputStream;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isAfterFork(long j) {
        return j >= ForkBlockTime;
    }
}
