package com.bitcoin.bitcoink;

import com.facebook.stetho.common.Utf8Charset;
import com.google.common.base.Stopwatch;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.crypto.MnemonicCode;
import org.bitcoinj.crypto.MnemonicException;
import org.bitcoinj.crypto.MnemonicException.MnemonicChecksumException;
import org.bitcoinj.crypto.MnemonicException.MnemonicLengthException;
import org.bitcoinj.crypto.MnemonicException.MnemonicWordException;
import org.bitcoinj.crypto.PBKDF2SHA512;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MnemonicCodeCustom {
    private static final String BIP39_ENGLISH_RESOURCE_NAME = "mnemonic/wordlist/english.txt";
    private static final String BIP39_ENGLISH_SHA256 = "ad90bf3beb7b0eb7e5acd74727dc0da96e0a280a258354e7293fb7e211ac03db";
    public static long BIP39_STANDARDISATION_TIME_SECS = 1381276800;
    public static MnemonicCodeCustom INSTANCE = null;
    private static final int PBKDF2_ROUNDS = 2048;
    private static final Logger log = LoggerFactory.getLogger(MnemonicCode.class);
    private ArrayList<String> wordList;
    private Map<String, Integer> wordListToIndex;

    static {
        try {
            INSTANCE = new MnemonicCodeCustom();
        } catch (FileNotFoundException e) {
            if (!C3447Utils.isAndroidRuntime()) {
                log.error("Could not find word list", (Throwable) e);
            }
        } catch (IOException e2) {
            log.error("Failed to load word list", (Throwable) e2);
        }
    }

    public MnemonicCodeCustom() throws IOException {
        this(openDefaultWords(), BIP39_ENGLISH_SHA256, Utf8Charset.NAME);
    }

    private static InputStream openDefaultWords() throws IOException {
        String str = BIP39_ENGLISH_RESOURCE_NAME;
        InputStream resourceAsStream = MnemonicCode.class.getResourceAsStream(str);
        if (resourceAsStream != null) {
            return resourceAsStream;
        }
        throw new FileNotFoundException(str);
    }

    public MnemonicCodeCustom(InputStream inputStream, String str, String str2) throws IOException, IllegalArgumentException {
        this.wordListToIndex = new HashMap();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str2));
        this.wordList = new ArrayList<>(2048);
        MessageDigest newDigest = Sha256Hash.newDigest();
        int i = 0;
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                break;
            }
            newDigest.update(readLine.getBytes());
            this.wordList.add(readLine);
            int i2 = i + 1;
            this.wordListToIndex.put(readLine, Integer.valueOf(i));
            i = i2;
        }
        bufferedReader.close();
        if (this.wordList.size() != 2048) {
            throw new IllegalArgumentException("input stream did not contain 2048 words");
        } else if (str != null) {
            if (!C3447Utils.HEX.encode(newDigest.digest()).equals(str)) {
                throw new IllegalArgumentException("wordlist digest mismatch");
            }
        }
    }

    public List<String> getWordList() {
        return this.wordList;
    }

    public static byte[] toSeed(List<String> list, String str) {
        String join = C3447Utils.join(list);
        StringBuilder sb = new StringBuilder();
        sb.append("mnemonic");
        sb.append(str);
        String sb2 = sb.toString();
        Stopwatch createStarted = Stopwatch.createStarted();
        byte[] derive = PBKDF2SHA512.derive(join, sb2, 2048, 64);
        createStarted.stop();
        log.info("PBKDF2 took {}", (Object) createStarted);
        return derive;
    }

    public byte[] toEntropy(List<String> list) throws MnemonicLengthException, MnemonicWordException, MnemonicChecksumException {
        if (list.size() % 3 > 0) {
            throw new MnemonicLengthException("Word list size must be multiple of three words.");
        } else if (list.size() != 0) {
            int size = list.size() * 11;
            boolean[] zArr = new boolean[size];
            int i = 0;
            int i2 = 0;
            for (String str : list) {
                int intValue = ((Integer) this.wordListToIndex.get(str)).intValue();
                if (intValue >= 0) {
                    for (int i3 = 0; i3 < 11; i3++) {
                        zArr[(i2 * 11) + i3] = ((1 << (10 - i3)) & intValue) != 0;
                    }
                    i2++;
                } else {
                    throw new MnemonicWordException(str);
                }
            }
            int i4 = size / 33;
            int i5 = size - i4;
            byte[] bArr = new byte[(i5 / 8)];
            for (int i6 = 0; i6 < bArr.length; i6++) {
                for (int i7 = 0; i7 < 8; i7++) {
                    if (zArr[(i6 * 8) + i7]) {
                        bArr[i6] = (byte) (bArr[i6] | (1 << (7 - i7)));
                    }
                }
            }
            boolean[] bytesToBits = bytesToBits(Sha256Hash.hash(bArr));
            while (i < i4) {
                if (zArr[i5 + i] == bytesToBits[i]) {
                    i++;
                } else {
                    throw new MnemonicChecksumException();
                }
            }
            return bArr;
        } else {
            throw new MnemonicLengthException("Word list is empty.");
        }
    }

    public List<String> toMnemonic(byte[] bArr) throws MnemonicLengthException {
        if (bArr.length % 4 > 0) {
            throw new MnemonicLengthException("Entropy length not multiple of 32 bits.");
        } else if (bArr.length != 0) {
            boolean[] bytesToBits = bytesToBits(Sha256Hash.hash(bArr));
            boolean[] bytesToBits2 = bytesToBits(bArr);
            int length = bytesToBits2.length / 32;
            boolean[] zArr = new boolean[(bytesToBits2.length + length)];
            System.arraycopy(bytesToBits2, 0, zArr, 0, bytesToBits2.length);
            System.arraycopy(bytesToBits, 0, zArr, bytesToBits2.length, length);
            ArrayList arrayList = new ArrayList();
            int length2 = zArr.length / 11;
            for (int i = 0; i < length2; i++) {
                int i2 = 0;
                for (int i3 = 0; i3 < 11; i3++) {
                    i2 <<= 1;
                    if (zArr[(i * 11) + i3]) {
                        i2 |= 1;
                    }
                }
                arrayList.add(this.wordList.get(i2));
            }
            return arrayList;
        } else {
            throw new MnemonicLengthException("Entropy is empty.");
        }
    }

    public void check(List<String> list) throws MnemonicException {
        toEntropy(list);
    }

    private static boolean[] bytesToBits(byte[] bArr) {
        boolean[] zArr = new boolean[(bArr.length * 8)];
        for (int i = 0; i < bArr.length; i++) {
            for (int i2 = 0; i2 < 8; i2++) {
                int i3 = (i * 8) + i2;
                boolean z = true;
                if ((bArr[i] & (1 << (7 - i2))) == 0) {
                    z = false;
                }
                zArr[i3] = z;
            }
        }
        return zArr;
    }
}
