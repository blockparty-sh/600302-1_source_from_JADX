package org.bitcoinj.script;

import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.ECKey.ECDSASignature;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.ProtocolException;
import org.bitcoinj.core.ScriptException;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.UnsafeByteArrayOutputStream;
import org.bitcoinj.crypto.TransactionSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Script {
    public static final EnumSet<VerifyFlag> ALL_VERIFY_FLAGS = EnumSet.allOf(VerifyFlag.class);
    public static final int MAX_P2SH_SIGOPS = 15;
    public static final long MAX_SCRIPT_ELEMENT_SIZE = 520;
    public static final int SIG_SIZE = 75;
    private static final ScriptChunk[] STANDARD_TRANSACTION_SCRIPT_CHUNKS = {new ScriptChunk(118, null, 0), new ScriptChunk(169, null, 1), new ScriptChunk(136, null, 23), new ScriptChunk(172, null, 24)};
    private static final Logger log = LoggerFactory.getLogger(Script.class);
    protected List<ScriptChunk> chunks;
    private long creationTimeSeconds;
    protected byte[] program;

    public enum ScriptType {
        NO_TYPE,
        P2PKH,
        PUB_KEY,
        P2SH
    }

    public enum VerifyFlag {
        P2SH,
        STRICTENC,
        DERSIG,
        LOW_S,
        NULLDUMMY,
        SIGPUSHONLY,
        MINIMALDATA,
        DISCOURAGE_UPGRADABLE_NOPS,
        CLEANSTACK,
        CHECKLOCKTIMEVERIFY,
        ENABLESIGHASHFORKID
    }

    private Script() {
        this.chunks = Lists.newArrayList();
    }

    Script(List<ScriptChunk> list) {
        this.chunks = Collections.unmodifiableList(new ArrayList(list));
        this.creationTimeSeconds = C3447Utils.currentTimeSeconds();
    }

    public Script(byte[] bArr) throws ScriptException {
        this.program = bArr;
        parse(bArr);
        this.creationTimeSeconds = 0;
    }

    public Script(byte[] bArr, long j) throws ScriptException {
        this.program = bArr;
        parse(bArr);
        this.creationTimeSeconds = j;
    }

    public long getCreationTimeSeconds() {
        return this.creationTimeSeconds;
    }

    public void setCreationTimeSeconds(long j) {
        this.creationTimeSeconds = j;
    }

    public String toString() {
        return C3447Utils.join(this.chunks);
    }

    public byte[] getProgram() {
        try {
            if (this.program != null) {
                return Arrays.copyOf(this.program, this.program.length);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (ScriptChunk write : this.chunks) {
                write.write(byteArrayOutputStream);
            }
            this.program = byteArrayOutputStream.toByteArray();
            return this.program;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ScriptChunk> getChunks() {
        return Collections.unmodifiableList(this.chunks);
    }

    private void parse(byte[] bArr) throws ScriptException {
        long j;
        ScriptChunk scriptChunk;
        ScriptChunk[] scriptChunkArr;
        int read;
        this.chunks = new ArrayList(5);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        int available = byteArrayInputStream.available();
        while (byteArrayInputStream.available() > 0) {
            int available2 = available - byteArrayInputStream.available();
            int read2 = byteArrayInputStream.read();
            boolean z = true;
            if (read2 < 0 || read2 >= 76) {
                String str = "Unexpected end of script";
                if (read2 == 76) {
                    if (byteArrayInputStream.available() >= 1) {
                        read = byteArrayInputStream.read();
                    } else {
                        throw new ScriptException(str);
                    }
                } else if (read2 == 77) {
                    if (byteArrayInputStream.available() >= 2) {
                        read = byteArrayInputStream.read() | (byteArrayInputStream.read() << 8);
                    } else {
                        throw new ScriptException(str);
                    }
                } else if (read2 != 78) {
                    j = -1;
                } else if (byteArrayInputStream.available() >= 4) {
                    j = (((long) byteArrayInputStream.read()) << 8) | ((long) byteArrayInputStream.read()) | (((long) byteArrayInputStream.read()) << 16) | (((long) byteArrayInputStream.read()) << 24);
                } else {
                    throw new ScriptException(str);
                }
                j = (long) read;
            } else {
                j = (long) read2;
            }
            if (j == -1) {
                scriptChunk = new ScriptChunk(read2, null, available2);
            } else if (j <= ((long) byteArrayInputStream.available())) {
                int i = (int) j;
                byte[] bArr2 = new byte[i];
                if (!(j == 0 || ((long) byteArrayInputStream.read(bArr2, 0, i)) == j)) {
                    z = false;
                }
                Preconditions.checkState(z);
                scriptChunk = new ScriptChunk(read2, bArr2, available2);
            } else {
                throw new ScriptException("Push of data element that is larger than remaining data");
            }
            for (ScriptChunk scriptChunk2 : STANDARD_TRANSACTION_SCRIPT_CHUNKS) {
                if (scriptChunk2.equals(scriptChunk)) {
                    scriptChunk = scriptChunk2;
                }
            }
            this.chunks.add(scriptChunk);
        }
    }

    public boolean isSentToRawPubKey() {
        if (this.chunks.size() != 2 || !((ScriptChunk) this.chunks.get(1)).equalsOpCode(172) || ((ScriptChunk) this.chunks.get(0)).isOpCode() || ((ScriptChunk) this.chunks.get(0)).data.length <= 1) {
            return false;
        }
        return true;
    }

    public boolean isSentToAddress() {
        if (this.chunks.size() != 5 || !((ScriptChunk) this.chunks.get(0)).equalsOpCode(118) || !((ScriptChunk) this.chunks.get(1)).equalsOpCode(169) || ((ScriptChunk) this.chunks.get(2)).data.length != 20 || !((ScriptChunk) this.chunks.get(3)).equalsOpCode(136) || !((ScriptChunk) this.chunks.get(4)).equalsOpCode(172)) {
            return false;
        }
        return true;
    }

    @Deprecated
    public boolean isSentToP2SH() {
        return isPayToScriptHash();
    }

    public byte[] getPubKeyHash() throws ScriptException {
        if (isSentToAddress()) {
            return ((ScriptChunk) this.chunks.get(2)).data;
        }
        if (isPayToScriptHash()) {
            return ((ScriptChunk) this.chunks.get(1)).data;
        }
        throw new ScriptException("Script not in the standard scriptPubKey form");
    }

    public byte[] getPubKey() throws ScriptException {
        if (this.chunks.size() == 2) {
            byte[] bArr = ((ScriptChunk) this.chunks.get(0)).data;
            ScriptChunk scriptChunk = (ScriptChunk) this.chunks.get(1);
            byte[] bArr2 = scriptChunk.data;
            if (bArr != null && bArr.length > 2 && bArr2 != null && bArr2.length > 2) {
                return bArr2;
            }
            if (scriptChunk.equalsOpCode(172) && bArr != null && bArr.length > 2) {
                return bArr;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Script did not match expected form: ");
            sb.append(this);
            throw new ScriptException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Script not of right size, expecting 2 but got ");
        sb2.append(this.chunks.size());
        throw new ScriptException(sb2.toString());
    }

    public byte[] getCLTVPaymentChannelSenderPubKey() throws ScriptException {
        if (isSentToCLTVPaymentChannel()) {
            return ((ScriptChunk) this.chunks.get(8)).data;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Script not a standard CHECKLOCKTIMVERIFY transaction: ");
        sb.append(this);
        throw new ScriptException(sb.toString());
    }

    public byte[] getCLTVPaymentChannelRecipientPubKey() throws ScriptException {
        if (isSentToCLTVPaymentChannel()) {
            return ((ScriptChunk) this.chunks.get(1)).data;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Script not a standard CHECKLOCKTIMVERIFY transaction: ");
        sb.append(this);
        throw new ScriptException(sb.toString());
    }

    public BigInteger getCLTVPaymentChannelExpiry() {
        if (isSentToCLTVPaymentChannel()) {
            return castToBigInteger(((ScriptChunk) this.chunks.get(4)).data, 5);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Script not a standard CHECKLOCKTIMEVERIFY transaction: ");
        sb.append(this);
        throw new ScriptException(sb.toString());
    }

    @Deprecated
    public Address getFromAddress(NetworkParameters networkParameters) throws ScriptException {
        return new Address(networkParameters, C3447Utils.sha256hash160(getPubKey()));
    }

    public Address getToAddress(NetworkParameters networkParameters) throws ScriptException {
        return getToAddress(networkParameters, false);
    }

    public Address getToAddress(NetworkParameters networkParameters, boolean z) throws ScriptException {
        if (isSentToAddress()) {
            return new Address(networkParameters, getPubKeyHash());
        }
        if (isPayToScriptHash()) {
            return Address.fromP2SHScript(networkParameters, this);
        }
        if (z && isSentToRawPubKey()) {
            return ECKey.fromPublicOnly(getPubKey()).toAddress(networkParameters);
        }
        throw new ScriptException("Cannot cast this script to a pay-to-address type");
    }

    public static void writeBytes(OutputStream outputStream, byte[] bArr) throws IOException {
        if (bArr.length < 76) {
            outputStream.write(bArr.length);
            outputStream.write(bArr);
        } else if (bArr.length < 256) {
            outputStream.write(76);
            outputStream.write(bArr.length);
            outputStream.write(bArr);
        } else if (bArr.length < 65536) {
            outputStream.write(77);
            outputStream.write(bArr.length & 255);
            outputStream.write((bArr.length >> 8) & 255);
            outputStream.write(bArr);
        } else {
            throw new RuntimeException("Unimplemented");
        }
    }

    public static byte[] createMultiSigOutputScript(int i, List<ECKey> list) {
        boolean z = true;
        Preconditions.checkArgument(i > 0);
        Preconditions.checkArgument(i <= list.size());
        if (list.size() > 16) {
            z = false;
        }
        Preconditions.checkArgument(z);
        if (list.size() > 3) {
            log.warn("Creating a multi-signature output that is non-standard: {} pubkeys, should be <= 3", (Object) Integer.valueOf(list.size()));
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(encodeToOpN(i));
            for (ECKey pubKey : list) {
                writeBytes(byteArrayOutputStream, pubKey.getPubKey());
            }
            byteArrayOutputStream.write(encodeToOpN(list.size()));
            byteArrayOutputStream.write(174);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] createInputScript(byte[] bArr, byte[] bArr2) {
        try {
            UnsafeByteArrayOutputStream unsafeByteArrayOutputStream = new UnsafeByteArrayOutputStream(bArr.length + bArr2.length + 2);
            writeBytes(unsafeByteArrayOutputStream, bArr);
            writeBytes(unsafeByteArrayOutputStream, bArr2);
            return unsafeByteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] createInputScript(byte[] bArr) {
        try {
            UnsafeByteArrayOutputStream unsafeByteArrayOutputStream = new UnsafeByteArrayOutputStream(bArr.length + 2);
            writeBytes(unsafeByteArrayOutputStream, bArr);
            return unsafeByteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Script createEmptyInputScript(@Nullable ECKey eCKey, @Nullable Script script) {
        boolean z = true;
        if (isSentToAddress()) {
            if (eCKey == null) {
                z = false;
            }
            Preconditions.checkArgument(z, "Key required to create pay-to-address input script");
            return ScriptBuilder.createInputScript(null, eCKey);
        } else if (isSentToRawPubKey()) {
            return ScriptBuilder.createInputScript(null);
        } else {
            if (isPayToScriptHash()) {
                if (script == null) {
                    z = false;
                }
                Preconditions.checkArgument(z, "Redeem script required to create P2SH input script");
                return ScriptBuilder.createP2SHMultiSigInputScript(null, script);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Do not understand script type: ");
            sb.append(this);
            throw new ScriptException(sb.toString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0016, code lost:
        if (isSentToAddress() != false) goto L_0x001a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bitcoinj.script.Script getScriptSigWithSignature(org.bitcoinj.script.Script r4, byte[] r5, int r6) {
        /*
            r3 = this;
            boolean r0 = r3.isPayToScriptHash()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x000a
            r1 = 1
            goto L_0x001a
        L_0x000a:
            boolean r0 = r3.isSentToMultiSig()
            if (r0 == 0) goto L_0x0012
            r1 = 1
            goto L_0x0019
        L_0x0012:
            boolean r0 = r3.isSentToAddress()
            if (r0 == 0) goto L_0x0019
            goto L_0x001a
        L_0x0019:
            r2 = 0
        L_0x001a:
            org.bitcoinj.script.Script r4 = org.bitcoinj.script.ScriptBuilder.updateScriptWithSignature(r4, r5, r6, r1, r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.script.Script.getScriptSigWithSignature(org.bitcoinj.script.Script, byte[], int):org.bitcoinj.script.Script");
    }

    public int getSigInsertionIndex(Sha256Hash sha256Hash, ECKey eCKey) {
        List<ScriptChunk> list = this.chunks;
        List<ScriptChunk> subList = list.subList(1, list.size() - 1);
        List<ScriptChunk> list2 = this.chunks;
        ScriptChunk scriptChunk = (ScriptChunk) list2.get(list2.size() - 1);
        Preconditions.checkNotNull(scriptChunk.data);
        Script script = new Script(scriptChunk.data);
        int findKeyInRedeem = script.findKeyInRedeem(eCKey);
        int i = 0;
        for (ScriptChunk scriptChunk2 : subList) {
            if (scriptChunk2.opcode != 0) {
                Preconditions.checkNotNull(scriptChunk2.data);
                if (findKeyInRedeem < script.findSigInRedeem(scriptChunk2.data, sha256Hash)) {
                    return i;
                }
                i++;
            }
        }
        return i;
    }

    private int findKeyInRedeem(ECKey eCKey) {
        int i = 0;
        Preconditions.checkArgument(((ScriptChunk) this.chunks.get(0)).isOpCode());
        List<ScriptChunk> list = this.chunks;
        int decodeFromOpN = decodeFromOpN(((ScriptChunk) list.get(list.size() - 2)).opcode);
        while (i < decodeFromOpN) {
            int i2 = i + 1;
            if (Arrays.equals(((ScriptChunk) this.chunks.get(i2)).data, eCKey.getPubKey())) {
                return i;
            }
            i = i2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Could not find matching key ");
        sb.append(eCKey.toString());
        sb.append(" in script ");
        sb.append(this);
        throw new IllegalStateException(sb.toString());
    }

    public List<ECKey> getPubKeys() {
        if (isSentToMultiSig()) {
            ArrayList newArrayList = Lists.newArrayList();
            List<ScriptChunk> list = this.chunks;
            int decodeFromOpN = decodeFromOpN(((ScriptChunk) list.get(list.size() - 2)).opcode);
            int i = 0;
            while (i < decodeFromOpN) {
                i++;
                newArrayList.add(ECKey.fromPublicOnly(((ScriptChunk) this.chunks.get(i)).data));
            }
            return newArrayList;
        }
        throw new ScriptException("Only usable for multisig scripts.");
    }

    private int findSigInRedeem(byte[] bArr, Sha256Hash sha256Hash) {
        int i = 0;
        Preconditions.checkArgument(((ScriptChunk) this.chunks.get(0)).isOpCode());
        List<ScriptChunk> list = this.chunks;
        int decodeFromOpN = decodeFromOpN(((ScriptChunk) list.get(list.size() - 2)).opcode);
        TransactionSignature decodeFromBitcoin = TransactionSignature.decodeFromBitcoin(bArr, true);
        while (i < decodeFromOpN) {
            int i2 = i + 1;
            if (ECKey.fromPublicOnly(((ScriptChunk) this.chunks.get(i2)).data).verify(sha256Hash, (ECDSASignature) decodeFromBitcoin)) {
                return i;
            }
            i = i2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Could not find matching key for signature on ");
        sb.append(sha256Hash.toString());
        sb.append(" sig ");
        sb.append(C3447Utils.HEX.encode(bArr));
        throw new IllegalStateException(sb.toString());
    }

    private static int getSigOpCount(List<ScriptChunk> list, boolean z) throws ScriptException {
        int i = 0;
        int i2 = 255;
        for (ScriptChunk scriptChunk : list) {
            if (scriptChunk.isOpCode()) {
                switch (scriptChunk.opcode) {
                    case 172:
                    case 173:
                        i++;
                        break;
                    case 174:
                    case 175:
                        if (z && i2 >= 81 && i2 <= 96) {
                            i += decodeFromOpN(i2);
                            break;
                        } else {
                            i += 20;
                            break;
                        }
                        break;
                }
                i2 = scriptChunk.opcode;
            }
        }
        return i;
    }

    static int decodeFromOpN(int i) {
        Preconditions.checkArgument(i == 0 || i == 79 || (i >= 81 && i <= 96), "decodeFromOpN called on non OP_N opcode");
        if (i == 0) {
            return 0;
        }
        if (i == 79) {
            return -1;
        }
        return (i + 1) - 81;
    }

    static int encodeToOpN(int i) {
        boolean z = i >= -1 && i <= 16;
        StringBuilder sb = new StringBuilder();
        sb.append("encodeToOpN called for ");
        sb.append(i);
        sb.append(" which we cannot encode in an opcode.");
        Preconditions.checkArgument(z, sb.toString());
        if (i == 0) {
            return 0;
        }
        if (i == -1) {
            return 79;
        }
        return (i - 1) + 81;
    }

    public static int getSigOpCount(byte[] bArr) throws ScriptException {
        Script script = new Script();
        try {
            script.parse(bArr);
        } catch (ScriptException unused) {
        }
        return getSigOpCount(script.chunks, false);
    }

    public static long getP2SHSigOpCount(byte[] bArr) throws ScriptException {
        Script script = new Script();
        try {
            script.parse(bArr);
        } catch (ScriptException unused) {
        }
        for (int size = script.chunks.size() - 1; size >= 0; size--) {
            if (!((ScriptChunk) script.chunks.get(size)).isOpCode()) {
                Script script2 = new Script();
                script2.parse(((ScriptChunk) script.chunks.get(size)).data);
                return (long) getSigOpCount(script2.chunks, true);
            }
        }
        return 0;
    }

    public int getNumberOfSignaturesRequiredToSpend() {
        if (isSentToMultiSig()) {
            return decodeFromOpN(((ScriptChunk) this.chunks.get(0)).opcode);
        }
        if (isSentToAddress() || isSentToRawPubKey()) {
            return 1;
        }
        if (isPayToScriptHash()) {
            throw new IllegalStateException("For P2SH number of signatures depends on redeem script");
        }
        throw new IllegalStateException("Unsupported script type");
    }

    public int getNumberOfBytesRequiredToSpend(@Nullable ECKey eCKey, @Nullable Script script) {
        boolean z = true;
        if (isPayToScriptHash()) {
            if (script == null) {
                z = false;
            }
            Preconditions.checkArgument(z, "P2SH script requires redeemScript to be spent");
            return (script.getNumberOfSignaturesRequiredToSpend() * 75) + script.getProgram().length;
        } else if (isSentToMultiSig()) {
            return (getNumberOfSignaturesRequiredToSpend() * 75) + 1;
        } else {
            if (isSentToRawPubKey()) {
                return 75;
            }
            if (isSentToAddress()) {
                int i = 65;
                if (eCKey != null) {
                    i = eCKey.getPubKey().length;
                }
                return i + 75;
            }
            throw new IllegalStateException("Unsupported script type");
        }
    }

    public boolean isPayToScriptHash() {
        byte[] program2 = getProgram();
        return program2.length == 23 && (program2[0] & 255) == 169 && (program2[1] & 255) == 20 && (program2[22] & 255) == 135;
    }

    public boolean isSentToMultiSig() {
        if (this.chunks.size() < 4) {
            return false;
        }
        List<ScriptChunk> list = this.chunks;
        ScriptChunk scriptChunk = (ScriptChunk) list.get(list.size() - 1);
        if (!scriptChunk.isOpCode()) {
            return false;
        }
        if (!scriptChunk.equalsOpCode(174) && !scriptChunk.equalsOpCode(175)) {
            return false;
        }
        try {
            ScriptChunk scriptChunk2 = (ScriptChunk) this.chunks.get(this.chunks.size() - 2);
            if (!scriptChunk2.isOpCode()) {
                return false;
            }
            int decodeFromOpN = decodeFromOpN(scriptChunk2.opcode);
            if (decodeFromOpN >= 1) {
                if (this.chunks.size() == decodeFromOpN + 3) {
                    for (int i = 1; i < this.chunks.size() - 2; i++) {
                        if (((ScriptChunk) this.chunks.get(i)).isOpCode()) {
                            return false;
                        }
                    }
                    if (decodeFromOpN(((ScriptChunk) this.chunks.get(0)).opcode) < 1) {
                        return false;
                    }
                    return true;
                }
            }
            return false;
        } catch (IllegalArgumentException unused) {
        }
    }

    public boolean isSentToCLTVPaymentChannel() {
        if (this.chunks.size() == 10 && ((ScriptChunk) this.chunks.get(0)).equalsOpCode(99) && ((ScriptChunk) this.chunks.get(2)).equalsOpCode(173) && ((ScriptChunk) this.chunks.get(3)).equalsOpCode(103) && ((ScriptChunk) this.chunks.get(5)).equalsOpCode(177) && ((ScriptChunk) this.chunks.get(6)).equalsOpCode(117) && ((ScriptChunk) this.chunks.get(7)).equalsOpCode(104) && ((ScriptChunk) this.chunks.get(9)).equalsOpCode(172)) {
            return true;
        }
        return false;
    }

    private static boolean equalsRange(byte[] bArr, int i, byte[] bArr2) {
        if (bArr2.length + i > bArr.length) {
            return false;
        }
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            if (bArr[i2 + i] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static byte[] removeAllInstancesOf(byte[] bArr, byte[] bArr2) {
        int i;
        UnsafeByteArrayOutputStream unsafeByteArrayOutputStream = new UnsafeByteArrayOutputStream(bArr.length);
        int i2 = 0;
        while (i2 < bArr.length) {
            boolean equalsRange = equalsRange(bArr, i2, bArr2);
            int i3 = i2 + 1;
            byte b = bArr[i2] & 255;
            if (b >= 0 && b < 76) {
                i = b;
            } else if (b == 76) {
                i = (bArr[i3] & 255) + 1;
            } else if (b == 77) {
                i = ((bArr[i3] & 255) | ((bArr[i3 + 1] & 255) << 8)) + 2;
            } else if (b == 78) {
                int i4 = i3 + 1;
                i = ((bArr[i3] & 255) | ((bArr[i4] & 255) << 8) | ((bArr[i4] & 255) << 16) | ((bArr[i4] & 255) << Ascii.CAN)) + 4;
            } else {
                i = 0;
            }
            if (!equalsRange) {
                try {
                    unsafeByteArrayOutputStream.write(b);
                    unsafeByteArrayOutputStream.write(Arrays.copyOfRange(bArr, i3, i3 + i));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            i2 = i3 + i;
        }
        return unsafeByteArrayOutputStream.toByteArray();
    }

    public static byte[] removeAllInstancesOfOp(byte[] bArr, int i) {
        return removeAllInstancesOf(bArr, new byte[]{(byte) i});
    }

    private static boolean castToBool(byte[] bArr) {
        boolean z = false;
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != 0) {
                if (!(i == bArr.length - 1 && (bArr[i] & 255) == 128)) {
                    z = true;
                }
                return z;
            }
        }
        return false;
    }

    private static BigInteger castToBigInteger(byte[] bArr) throws ScriptException {
        if (bArr.length <= 4) {
            return C3447Utils.decodeMPI(C3447Utils.reverseBytes(bArr), false);
        }
        throw new ScriptException("Script attempted to use an integer larger than 4 bytes");
    }

    private static BigInteger castToBigInteger(byte[] bArr, int i) throws ScriptException {
        if (bArr.length <= i) {
            return C3447Utils.decodeMPI(C3447Utils.reverseBytes(bArr), false);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Script attempted to use an integer larger than ");
        sb.append(i);
        sb.append(" bytes");
        throw new ScriptException(sb.toString());
    }

    public boolean isOpReturn() {
        return this.chunks.size() > 0 && ((ScriptChunk) this.chunks.get(0)).equalsOpCode(106);
    }

    @Deprecated
    public static void executeScript(@Nullable Transaction transaction, long j, Script script, LinkedList<byte[]> linkedList, boolean z) throws ScriptException {
        EnumSet enumSet;
        if (z) {
            enumSet = EnumSet.of(VerifyFlag.NULLDUMMY);
        } else {
            enumSet = EnumSet.noneOf(VerifyFlag.class);
        }
        Transaction transaction2 = transaction;
        long j2 = j;
        Script script2 = script;
        LinkedList<byte[]> linkedList2 = linkedList;
        executeScript(transaction2, j2, script2, linkedList2, Coin.ZERO, enumSet);
    }

    @Deprecated
    public static void executeScript(@Nullable Transaction transaction, long j, Script script, LinkedList<byte[]> linkedList, Set<VerifyFlag> set) throws ScriptException {
        executeScript(transaction, j, script, linkedList, Coin.ZERO, set);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0319, code lost:
        if (r2.compareTo(r1) > 0) goto L_0x0322;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0320, code lost:
        if (r2.compareTo(r1) < 0) goto L_0x0322;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0322, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:357:0x07a7, code lost:
        r19 = r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void executeScript(@javax.annotation.Nullable org.bitcoinj.core.Transaction r20, long r21, org.bitcoinj.script.Script r23, java.util.LinkedList<byte[]> r24, org.bitcoinj.core.Coin r25, java.util.Set<org.bitcoinj.script.Script.VerifyFlag> r26) throws org.bitcoinj.core.ScriptException {
        /*
            r10 = r21
            r0 = r24
            r12 = r26
            java.util.LinkedList r13 = new java.util.LinkedList
            r13.<init>()
            java.util.LinkedList r14 = new java.util.LinkedList
            r14.<init>()
            r15 = r23
            java.util.List<org.bitcoinj.script.ScriptChunk> r1 = r15.chunks
            java.util.Iterator r16 = r1.iterator()
            r9 = 0
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r9)
            r1 = 0
            r17 = 0
        L_0x0020:
            boolean r2 = r16.hasNext()
            if (r2 == 0) goto L_0x08a8
            java.lang.Object r2 = r16.next()
            org.bitcoinj.script.ScriptChunk r2 = (org.bitcoinj.script.ScriptChunk) r2
            boolean r3 = r14.contains(r8)
            r4 = 1
            r3 = r3 ^ r4
            int r5 = r2.opcode
            if (r5 != 0) goto L_0x003f
            if (r3 != 0) goto L_0x0039
            goto L_0x0020
        L_0x0039:
            byte[] r2 = new byte[r9]
            r0.add(r2)
            goto L_0x0057
        L_0x003f:
            boolean r5 = r2.isOpCode()
            if (r5 != 0) goto L_0x0064
            byte[] r4 = r2.data
            int r4 = r4.length
            long r4 = (long) r4
            r6 = 520(0x208, double:2.57E-321)
            int r18 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r18 > 0) goto L_0x005c
            if (r3 != 0) goto L_0x0052
            goto L_0x0020
        L_0x0052:
            byte[] r2 = r2.data
            r0.add(r2)
        L_0x0057:
            r19 = r8
            r12 = 0
            goto L_0x07d9
        L_0x005c:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted to push a data string larger than 520 bytes"
            r0.<init>(r1)
            throw r0
        L_0x0064:
            int r7 = r2.opcode
            r5 = 96
            if (r7 <= r5) goto L_0x0079
            int r1 = r1 + 1
            r5 = 201(0xc9, float:2.82E-43)
            if (r1 > r5) goto L_0x0071
            goto L_0x0079
        L_0x0071:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "More script operations than is allowed"
            r0.<init>(r1)
            throw r0
        L_0x0079:
            r18 = r1
            r1 = 101(0x65, float:1.42E-43)
            if (r7 == r1) goto L_0x08a0
            r1 = 102(0x66, float:1.43E-43)
            if (r7 == r1) goto L_0x08a0
            r1 = 126(0x7e, float:1.77E-43)
            if (r7 == r1) goto L_0x0898
            r1 = 127(0x7f, float:1.78E-43)
            if (r7 == r1) goto L_0x0898
            r1 = 128(0x80, float:1.794E-43)
            if (r7 == r1) goto L_0x0898
            r1 = 129(0x81, float:1.81E-43)
            if (r7 == r1) goto L_0x0898
            r1 = 131(0x83, float:1.84E-43)
            if (r7 == r1) goto L_0x0898
            r1 = 132(0x84, float:1.85E-43)
            if (r7 == r1) goto L_0x0898
            r1 = 133(0x85, float:1.86E-43)
            if (r7 == r1) goto L_0x0898
            r1 = 134(0x86, float:1.88E-43)
            if (r7 == r1) goto L_0x0898
            r1 = 141(0x8d, float:1.98E-43)
            if (r7 == r1) goto L_0x0898
            r1 = 142(0x8e, float:1.99E-43)
            if (r7 == r1) goto L_0x0898
            r1 = 149(0x95, float:2.09E-43)
            if (r7 == r1) goto L_0x0898
            r1 = 150(0x96, float:2.1E-43)
            if (r7 == r1) goto L_0x0898
            r1 = 151(0x97, float:2.12E-43)
            if (r7 == r1) goto L_0x0898
            r1 = 152(0x98, float:2.13E-43)
            if (r7 == r1) goto L_0x0898
            r1 = 153(0x99, float:2.14E-43)
            if (r7 == r1) goto L_0x0898
            r1 = 99
            if (r7 == r1) goto L_0x086a
            r1 = 100
            if (r7 == r1) goto L_0x083c
            r1 = 103(0x67, float:1.44E-43)
            if (r7 == r1) goto L_0x0818
            r1 = 104(0x68, float:1.46E-43)
            if (r7 == r1) goto L_0x0800
            if (r3 != 0) goto L_0x00d5
            r1 = r8
            r12 = 0
            goto L_0x0889
        L_0x00d5:
            r1 = 79
            if (r7 == r1) goto L_0x07c3
            switch(r7) {
                case 81: goto L_0x07ab;
                case 82: goto L_0x07ab;
                case 83: goto L_0x07ab;
                case 84: goto L_0x07ab;
                case 85: goto L_0x07ab;
                case 86: goto L_0x07ab;
                case 87: goto L_0x07ab;
                case 88: goto L_0x07ab;
                case 89: goto L_0x07ab;
                case 90: goto L_0x07ab;
                case 91: goto L_0x07ab;
                case 92: goto L_0x07ab;
                case 93: goto L_0x07ab;
                case 94: goto L_0x07ab;
                case 95: goto L_0x07ab;
                case 96: goto L_0x07ab;
                case 97: goto L_0x07a7;
                default: goto L_0x00dc;
            }
        L_0x00dc:
            r1 = 4
            r3 = 3
            java.lang.String r5 = "Attempted to use disabled Script Op."
            switch(r7) {
                case 105: goto L_0x0781;
                case 106: goto L_0x0779;
                case 107: goto L_0x0760;
                case 108: goto L_0x0746;
                case 109: goto L_0x072c;
                case 110: goto L_0x0704;
                case 111: goto L_0x06d4;
                case 112: goto L_0x06a7;
                case 113: goto L_0x065d;
                case 114: goto L_0x0626;
                case 115: goto L_0x0600;
                case 116: goto L_0x05e7;
                case 117: goto L_0x05d1;
                case 118: goto L_0x05b7;
                case 119: goto L_0x0597;
                case 120: goto L_0x0575;
                case 121: goto L_0x051e;
                case 122: goto L_0x051e;
                case 123: goto L_0x04f0;
                case 124: goto L_0x04c3;
                case 125: goto L_0x04c3;
                case 126: goto L_0x04bd;
                case 127: goto L_0x04bd;
                case 128: goto L_0x04bd;
                case 129: goto L_0x04bd;
                case 130: goto L_0x0493;
                case 131: goto L_0x048d;
                case 132: goto L_0x048d;
                case 133: goto L_0x048d;
                case 134: goto L_0x048d;
                case 135: goto L_0x045d;
                case 136: goto L_0x042f;
                default: goto L_0x00e3;
            }
        L_0x00e3:
            java.lang.String r1 = "Script attempted signature check but no tx was provided"
            java.lang.String r9 = "Attempted OP_SHA256 on an empty stack"
            java.lang.String r6 = "Script used a reserved opcode "
            switch(r7) {
                case 139: goto L_0x03c3;
                case 140: goto L_0x03c3;
                case 141: goto L_0x03bd;
                case 142: goto L_0x03bd;
                case 143: goto L_0x03c3;
                case 144: goto L_0x03c3;
                case 145: goto L_0x03c3;
                case 146: goto L_0x03c3;
                case 147: goto L_0x02ec;
                case 148: goto L_0x02ec;
                case 149: goto L_0x02e6;
                case 150: goto L_0x02e6;
                case 151: goto L_0x02e6;
                case 152: goto L_0x02e6;
                case 153: goto L_0x02e6;
                case 154: goto L_0x02ec;
                case 155: goto L_0x02ec;
                case 156: goto L_0x02ec;
                case 157: goto L_0x02b0;
                case 158: goto L_0x02ec;
                case 159: goto L_0x02ec;
                case 160: goto L_0x02ec;
                case 161: goto L_0x02ec;
                case 162: goto L_0x02ec;
                case 163: goto L_0x02ec;
                case 164: goto L_0x02ec;
                case 165: goto L_0x0257;
                case 166: goto L_0x022b;
                case 167: goto L_0x01fe;
                case 168: goto L_0x01e0;
                case 169: goto L_0x01c0;
                case 170: goto L_0x01a2;
                case 171: goto L_0x0197;
                case 172: goto L_0x0179;
                case 173: goto L_0x0179;
                case 174: goto L_0x0158;
                case 175: goto L_0x0158;
                case 176: goto L_0x0139;
                case 177: goto L_0x0101;
                case 178: goto L_0x0139;
                case 179: goto L_0x0139;
                case 180: goto L_0x0139;
                case 181: goto L_0x0139;
                case 182: goto L_0x0139;
                case 183: goto L_0x0139;
                case 184: goto L_0x0139;
                case 185: goto L_0x0139;
                default: goto L_0x00ec;
            }
        L_0x00ec:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r6)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0101:
            org.bitcoinj.script.Script$VerifyFlag r1 = org.bitcoinj.script.Script.VerifyFlag.CHECKLOCKTIMEVERIFY
            boolean r1 = r12.contains(r1)
            if (r1 != 0) goto L_0x0128
            org.bitcoinj.script.Script$VerifyFlag r1 = org.bitcoinj.script.Script.VerifyFlag.DISCOURAGE_UPGRADABLE_NOPS
            boolean r1 = r12.contains(r1)
            if (r1 != 0) goto L_0x0113
            goto L_0x07a7
        L_0x0113:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r6)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0128:
            int r2 = (int) r10
            r1 = r20
            r3 = r23
            r4 = r24
            r5 = r17
            r6 = r7
            r7 = r26
            executeCheckLockTimeVerify(r1, r2, r3, r4, r5, r6, r7)
            goto L_0x07a7
        L_0x0139:
            org.bitcoinj.script.Script$VerifyFlag r1 = org.bitcoinj.script.Script.VerifyFlag.DISCOURAGE_UPGRADABLE_NOPS
            boolean r1 = r12.contains(r1)
            if (r1 != 0) goto L_0x0143
            goto L_0x07a7
        L_0x0143:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r6)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0158:
            if (r20 == 0) goto L_0x0173
            int r2 = (int) r10
            r1 = r20
            r3 = r23
            r4 = r24
            r5 = r18
            r6 = r17
            r9 = r8
            r8 = r25
            r19 = r9
            r12 = 0
            r9 = r26
            int r1 = executeMultiSig(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x07d9
        L_0x0173:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x0179:
            r19 = r8
            r12 = 0
            if (r20 == 0) goto L_0x0191
            int r2 = (int) r10
            r1 = r20
            r3 = r23
            r4 = r24
            r5 = r17
            r6 = r7
            r7 = r25
            r8 = r26
            executeCheckSig(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x07d7
        L_0x0191:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x0197:
            r19 = r8
            r12 = 0
            int r1 = r2.getStartLocationInProgram()
            int r17 = r1 + 1
            goto L_0x07d7
        L_0x01a2:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            if (r1 < r4) goto L_0x01ba
            java.lang.Object r1 = r24.pollLast()
            byte[] r1 = (byte[]) r1
            byte[] r1 = org.bitcoinj.core.Sha256Hash.hashTwice(r1)
            r0.add(r1)
            goto L_0x07d7
        L_0x01ba:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            r0.<init>(r9)
            throw r0
        L_0x01c0:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            if (r1 < r4) goto L_0x01d8
            java.lang.Object r1 = r24.pollLast()
            byte[] r1 = (byte[]) r1
            byte[] r1 = org.bitcoinj.core.C3447Utils.sha256hash160(r1)
            r0.add(r1)
            goto L_0x07d7
        L_0x01d8:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_HASH160 on an empty stack"
            r0.<init>(r1)
            throw r0
        L_0x01e0:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            if (r1 < r4) goto L_0x01f8
            java.lang.Object r1 = r24.pollLast()
            byte[] r1 = (byte[]) r1
            byte[] r1 = org.bitcoinj.core.Sha256Hash.hash(r1)
            r0.add(r1)
            goto L_0x07d7
        L_0x01f8:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            r0.<init>(r9)
            throw r0
        L_0x01fe:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            if (r1 < r4) goto L_0x0223
            java.lang.String r1 = "SHA-1"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)     // Catch:{ NoSuchAlgorithmException -> 0x021c }
            java.lang.Object r2 = r24.pollLast()     // Catch:{ NoSuchAlgorithmException -> 0x021c }
            byte[] r2 = (byte[]) r2     // Catch:{ NoSuchAlgorithmException -> 0x021c }
            byte[] r1 = r1.digest(r2)     // Catch:{ NoSuchAlgorithmException -> 0x021c }
            r0.add(r1)     // Catch:{ NoSuchAlgorithmException -> 0x021c }
            goto L_0x07d7
        L_0x021c:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        L_0x0223:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_SHA1 on an empty stack"
            r0.<init>(r1)
            throw r0
        L_0x022b:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            if (r1 < r4) goto L_0x024f
            org.spongycastle.crypto.digests.RIPEMD160Digest r1 = new org.spongycastle.crypto.digests.RIPEMD160Digest
            r1.<init>()
            java.lang.Object r2 = r24.pollLast()
            byte[] r2 = (byte[]) r2
            int r3 = r2.length
            r1.update(r2, r12, r3)
            r2 = 20
            byte[] r2 = new byte[r2]
            r1.doFinal(r2, r12)
            r0.add(r2)
            goto L_0x07d7
        L_0x024f:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_RIPEMD160 on an empty stack"
            r0.<init>(r1)
            throw r0
        L_0x0257:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            if (r1 < r3) goto L_0x02a8
            java.lang.Object r1 = r24.pollLast()
            byte[] r1 = (byte[]) r1
            java.math.BigInteger r1 = castToBigInteger(r1)
            java.lang.Object r2 = r24.pollLast()
            byte[] r2 = (byte[]) r2
            java.math.BigInteger r2 = castToBigInteger(r2)
            java.lang.Object r3 = r24.pollLast()
            byte[] r3 = (byte[]) r3
            java.math.BigInteger r3 = castToBigInteger(r3)
            int r2 = r2.compareTo(r3)
            if (r2 > 0) goto L_0x0299
            int r1 = r3.compareTo(r1)
            if (r1 >= 0) goto L_0x0299
            java.math.BigInteger r1 = java.math.BigInteger.ONE
            byte[] r1 = org.bitcoinj.core.C3447Utils.encodeMPI(r1, r12)
            byte[] r1 = org.bitcoinj.core.C3447Utils.reverseBytes(r1)
            r0.add(r1)
            goto L_0x07d7
        L_0x0299:
            java.math.BigInteger r1 = java.math.BigInteger.ZERO
            byte[] r1 = org.bitcoinj.core.C3447Utils.encodeMPI(r1, r12)
            byte[] r1 = org.bitcoinj.core.C3447Utils.reverseBytes(r1)
            r0.add(r1)
            goto L_0x07d7
        L_0x02a8:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_WITHIN on a stack with size < 3"
            r0.<init>(r1)
            throw r0
        L_0x02b0:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            r2 = 2
            if (r1 < r2) goto L_0x02de
            java.lang.Object r1 = r24.pollLast()
            byte[] r1 = (byte[]) r1
            java.math.BigInteger r1 = castToBigInteger(r1)
            java.lang.Object r2 = r24.pollLast()
            byte[] r2 = (byte[]) r2
            java.math.BigInteger r2 = castToBigInteger(r2)
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x02d6
            goto L_0x07d7
        L_0x02d6:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "OP_NUMEQUALVERIFY failed"
            r0.<init>(r1)
            throw r0
        L_0x02de:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_NUMEQUALVERIFY on a stack with size < 2"
            r0.<init>(r1)
            throw r0
        L_0x02e6:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            r0.<init>(r5)
            throw r0
        L_0x02ec:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            r2 = 2
            if (r1 < r2) goto L_0x03b5
            java.lang.Object r1 = r24.pollLast()
            byte[] r1 = (byte[]) r1
            java.math.BigInteger r1 = castToBigInteger(r1)
            java.lang.Object r2 = r24.pollLast()
            byte[] r2 = (byte[]) r2
            java.math.BigInteger r2 = castToBigInteger(r2)
            switch(r7) {
                case 147: goto L_0x03a4;
                case 148: goto L_0x039f;
                case 149: goto L_0x030d;
                case 150: goto L_0x030d;
                case 151: goto L_0x030d;
                case 152: goto L_0x030d;
                case 153: goto L_0x030d;
                case 154: goto L_0x0389;
                case 155: goto L_0x0372;
                case 156: goto L_0x0366;
                case 157: goto L_0x030d;
                case 158: goto L_0x035a;
                case 159: goto L_0x034e;
                case 160: goto L_0x0341;
                case 161: goto L_0x0333;
                case 162: goto L_0x0325;
                case 163: goto L_0x031c;
                case 164: goto L_0x0315;
                default: goto L_0x030d;
            }
        L_0x030d:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "Opcode switched at runtime?"
            r0.<init>(r1)
            throw r0
        L_0x0315:
            int r3 = r2.compareTo(r1)
            if (r3 <= 0) goto L_0x03a8
            goto L_0x0322
        L_0x031c:
            int r3 = r2.compareTo(r1)
            if (r3 >= 0) goto L_0x03a8
        L_0x0322:
            r1 = r2
            goto L_0x03a8
        L_0x0325:
            int r1 = r2.compareTo(r1)
            if (r1 < 0) goto L_0x032f
            java.math.BigInteger r1 = java.math.BigInteger.ONE
            goto L_0x03a8
        L_0x032f:
            java.math.BigInteger r1 = java.math.BigInteger.ZERO
            goto L_0x03a8
        L_0x0333:
            int r1 = r2.compareTo(r1)
            if (r1 > 0) goto L_0x033d
            java.math.BigInteger r1 = java.math.BigInteger.ONE
            goto L_0x03a8
        L_0x033d:
            java.math.BigInteger r1 = java.math.BigInteger.ZERO
            goto L_0x03a8
        L_0x0341:
            int r1 = r2.compareTo(r1)
            if (r1 <= 0) goto L_0x034b
            java.math.BigInteger r1 = java.math.BigInteger.ONE
            goto L_0x03a8
        L_0x034b:
            java.math.BigInteger r1 = java.math.BigInteger.ZERO
            goto L_0x03a8
        L_0x034e:
            int r1 = r2.compareTo(r1)
            if (r1 >= 0) goto L_0x0357
            java.math.BigInteger r1 = java.math.BigInteger.ONE
            goto L_0x03a8
        L_0x0357:
            java.math.BigInteger r1 = java.math.BigInteger.ZERO
            goto L_0x03a8
        L_0x035a:
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0363
            java.math.BigInteger r1 = java.math.BigInteger.ONE
            goto L_0x03a8
        L_0x0363:
            java.math.BigInteger r1 = java.math.BigInteger.ZERO
            goto L_0x03a8
        L_0x0366:
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x036f
            java.math.BigInteger r1 = java.math.BigInteger.ONE
            goto L_0x03a8
        L_0x036f:
            java.math.BigInteger r1 = java.math.BigInteger.ZERO
            goto L_0x03a8
        L_0x0372:
            java.math.BigInteger r3 = java.math.BigInteger.ZERO
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0386
            java.math.BigInteger r2 = java.math.BigInteger.ZERO
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0383
            goto L_0x0386
        L_0x0383:
            java.math.BigInteger r1 = java.math.BigInteger.ZERO
            goto L_0x03a8
        L_0x0386:
            java.math.BigInteger r1 = java.math.BigInteger.ONE
            goto L_0x03a8
        L_0x0389:
            java.math.BigInteger r3 = java.math.BigInteger.ZERO
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x039c
            java.math.BigInteger r2 = java.math.BigInteger.ZERO
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x039c
            java.math.BigInteger r1 = java.math.BigInteger.ONE
            goto L_0x03a8
        L_0x039c:
            java.math.BigInteger r1 = java.math.BigInteger.ZERO
            goto L_0x03a8
        L_0x039f:
            java.math.BigInteger r1 = r2.subtract(r1)
            goto L_0x03a8
        L_0x03a4:
            java.math.BigInteger r1 = r2.add(r1)
        L_0x03a8:
            byte[] r1 = org.bitcoinj.core.C3447Utils.encodeMPI(r1, r12)
            byte[] r1 = org.bitcoinj.core.C3447Utils.reverseBytes(r1)
            r0.add(r1)
            goto L_0x07d7
        L_0x03b5:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted a numeric op on a stack with size < 2"
            r0.<init>(r1)
            throw r0
        L_0x03bd:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            r0.<init>(r5)
            throw r0
        L_0x03c3:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            if (r1 < r4) goto L_0x0427
            java.lang.Object r1 = r24.pollLast()
            byte[] r1 = (byte[]) r1
            java.math.BigInteger r1 = castToBigInteger(r1)
            switch(r7) {
                case 139: goto L_0x0414;
                case 140: goto L_0x040d;
                case 141: goto L_0x03d9;
                case 142: goto L_0x03d9;
                case 143: goto L_0x0408;
                case 144: goto L_0x03fd;
                case 145: goto L_0x03ef;
                case 146: goto L_0x03e1;
                default: goto L_0x03d9;
            }
        L_0x03d9:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            java.lang.String r1 = "Unreachable"
            r0.<init>(r1)
            throw r0
        L_0x03e1:
            java.math.BigInteger r2 = java.math.BigInteger.ZERO
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x03ec
            java.math.BigInteger r1 = java.math.BigInteger.ZERO
            goto L_0x041a
        L_0x03ec:
            java.math.BigInteger r1 = java.math.BigInteger.ONE
            goto L_0x041a
        L_0x03ef:
            java.math.BigInteger r2 = java.math.BigInteger.ZERO
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x03fa
            java.math.BigInteger r1 = java.math.BigInteger.ONE
            goto L_0x041a
        L_0x03fa:
            java.math.BigInteger r1 = java.math.BigInteger.ZERO
            goto L_0x041a
        L_0x03fd:
            int r2 = r1.signum()
            if (r2 >= 0) goto L_0x041a
            java.math.BigInteger r1 = r1.negate()
            goto L_0x041a
        L_0x0408:
            java.math.BigInteger r1 = r1.negate()
            goto L_0x041a
        L_0x040d:
            java.math.BigInteger r2 = java.math.BigInteger.ONE
            java.math.BigInteger r1 = r1.subtract(r2)
            goto L_0x041a
        L_0x0414:
            java.math.BigInteger r2 = java.math.BigInteger.ONE
            java.math.BigInteger r1 = r1.add(r2)
        L_0x041a:
            byte[] r1 = org.bitcoinj.core.C3447Utils.encodeMPI(r1, r12)
            byte[] r1 = org.bitcoinj.core.C3447Utils.reverseBytes(r1)
            r0.add(r1)
            goto L_0x07d7
        L_0x0427:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted a numeric op on an empty stack"
            r0.<init>(r1)
            throw r0
        L_0x042f:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            r2 = 2
            if (r1 < r2) goto L_0x0455
            java.lang.Object r1 = r24.pollLast()
            byte[] r1 = (byte[]) r1
            java.lang.Object r2 = r24.pollLast()
            byte[] r2 = (byte[]) r2
            boolean r1 = java.util.Arrays.equals(r1, r2)
            if (r1 == 0) goto L_0x044d
            goto L_0x07d7
        L_0x044d:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "OP_EQUALVERIFY: non-equal data"
            r0.<init>(r1)
            throw r0
        L_0x0455:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_EQUALVERIFY on a stack with size < 2"
            r0.<init>(r1)
            throw r0
        L_0x045d:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            r2 = 2
            if (r1 < r2) goto L_0x0485
            java.lang.Object r1 = r24.pollLast()
            byte[] r1 = (byte[]) r1
            java.lang.Object r2 = r24.pollLast()
            byte[] r2 = (byte[]) r2
            boolean r1 = java.util.Arrays.equals(r1, r2)
            if (r1 == 0) goto L_0x047e
            byte[] r1 = new byte[r4]
            r1[r12] = r4
            goto L_0x0480
        L_0x047e:
            byte[] r1 = new byte[r12]
        L_0x0480:
            r0.add(r1)
            goto L_0x07d7
        L_0x0485:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_EQUAL on a stack with size < 2"
            r0.<init>(r1)
            throw r0
        L_0x048d:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            r0.<init>(r5)
            throw r0
        L_0x0493:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            if (r1 < r4) goto L_0x04b5
            java.lang.Object r1 = r24.getLast()
            byte[] r1 = (byte[]) r1
            int r1 = r1.length
            long r1 = (long) r1
            java.math.BigInteger r1 = java.math.BigInteger.valueOf(r1)
            byte[] r1 = org.bitcoinj.core.C3447Utils.encodeMPI(r1, r12)
            byte[] r1 = org.bitcoinj.core.C3447Utils.reverseBytes(r1)
            r0.add(r1)
            goto L_0x07d7
        L_0x04b5:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_SIZE on an empty stack"
            r0.<init>(r1)
            throw r0
        L_0x04bd:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            r0.<init>(r5)
            throw r0
        L_0x04c3:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            r2 = 2
            if (r1 < r2) goto L_0x04e8
            java.lang.Object r1 = r24.pollLast()
            byte[] r1 = (byte[]) r1
            java.lang.Object r2 = r24.pollLast()
            byte[] r2 = (byte[]) r2
            r0.add(r1)
            r0.add(r2)
            r2 = 125(0x7d, float:1.75E-43)
            if (r7 != r2) goto L_0x07d7
            r0.add(r1)
            goto L_0x07d7
        L_0x04e8:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_SWAP on a stack with size < 2"
            r0.<init>(r1)
            throw r0
        L_0x04f0:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            if (r1 < r3) goto L_0x0516
            java.lang.Object r1 = r24.pollLast()
            byte[] r1 = (byte[]) r1
            java.lang.Object r2 = r24.pollLast()
            byte[] r2 = (byte[]) r2
            java.lang.Object r3 = r24.pollLast()
            byte[] r3 = (byte[]) r3
            r0.add(r2)
            r0.add(r1)
            r0.add(r3)
            goto L_0x07d7
        L_0x0516:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_ROT on a stack with size < 3"
            r0.<init>(r1)
            throw r0
        L_0x051e:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            if (r1 < r4) goto L_0x056d
            java.lang.Object r1 = r24.pollLast()
            byte[] r1 = (byte[]) r1
            java.math.BigInteger r1 = castToBigInteger(r1)
            long r1 = r1.longValue()
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 < 0) goto L_0x0565
            int r5 = r24.size()
            long r5 = (long) r5
            int r8 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r8 >= 0) goto L_0x0565
            java.util.Iterator r5 = r24.descendingIterator()
        L_0x0548:
            int r6 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r6 >= 0) goto L_0x0553
            r5.next()
            r8 = 1
            long r3 = r3 + r8
            goto L_0x0548
        L_0x0553:
            java.lang.Object r1 = r5.next()
            byte[] r1 = (byte[]) r1
            r2 = 122(0x7a, float:1.71E-43)
            if (r7 != r2) goto L_0x0560
            r5.remove()
        L_0x0560:
            r0.add(r1)
            goto L_0x07d7
        L_0x0565:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "OP_PICK/OP_ROLL attempted to get data deeper than stack size"
            r0.<init>(r1)
            throw r0
        L_0x056d:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_PICK/OP_ROLL on an empty stack"
            r0.<init>(r1)
            throw r0
        L_0x0575:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            r2 = 2
            if (r1 < r2) goto L_0x058f
            java.util.Iterator r1 = r24.descendingIterator()
            r1.next()
            java.lang.Object r1 = r1.next()
            r0.add(r1)
            goto L_0x07d7
        L_0x058f:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_OVER on a stack with size < 2"
            r0.<init>(r1)
            throw r0
        L_0x0597:
            r19 = r8
            r2 = 2
            r12 = 0
            int r1 = r24.size()
            if (r1 < r2) goto L_0x05af
            java.lang.Object r1 = r24.pollLast()
            byte[] r1 = (byte[]) r1
            r24.pollLast()
            r0.add(r1)
            goto L_0x07d7
        L_0x05af:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_NIP on a stack with size < 2"
            r0.<init>(r1)
            throw r0
        L_0x05b7:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            if (r1 < r4) goto L_0x05c9
            java.lang.Object r1 = r24.getLast()
            r0.add(r1)
            goto L_0x07d7
        L_0x05c9:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_DUP on an empty stack"
            r0.<init>(r1)
            throw r0
        L_0x05d1:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            if (r1 < r4) goto L_0x05df
            r24.pollLast()
            goto L_0x07d7
        L_0x05df:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_DROP on an empty stack"
            r0.<init>(r1)
            throw r0
        L_0x05e7:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            long r1 = (long) r1
            java.math.BigInteger r1 = java.math.BigInteger.valueOf(r1)
            byte[] r1 = org.bitcoinj.core.C3447Utils.encodeMPI(r1, r12)
            byte[] r1 = org.bitcoinj.core.C3447Utils.reverseBytes(r1)
            r0.add(r1)
            goto L_0x07d7
        L_0x0600:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            if (r1 < r4) goto L_0x061e
            java.lang.Object r1 = r24.getLast()
            byte[] r1 = (byte[]) r1
            boolean r1 = castToBool(r1)
            if (r1 == 0) goto L_0x07d7
            java.lang.Object r1 = r24.getLast()
            r0.add(r1)
            goto L_0x07d7
        L_0x061e:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_IFDUP on an empty stack"
            r0.<init>(r1)
            throw r0
        L_0x0626:
            r19 = r8
            r12 = 0
            int r2 = r24.size()
            if (r2 < r1) goto L_0x0655
            java.lang.Object r1 = r24.pollLast()
            byte[] r1 = (byte[]) r1
            java.lang.Object r2 = r24.pollLast()
            byte[] r2 = (byte[]) r2
            java.lang.Object r3 = r24.pollLast()
            byte[] r3 = (byte[]) r3
            java.lang.Object r4 = r24.pollLast()
            byte[] r4 = (byte[]) r4
            r0.add(r2)
            r0.add(r1)
            r0.add(r4)
            r0.add(r3)
            goto L_0x07d7
        L_0x0655:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_2SWAP on a stack with size < 4"
            r0.<init>(r1)
            throw r0
        L_0x065d:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            r2 = 6
            if (r1 < r2) goto L_0x069f
            java.lang.Object r1 = r24.pollLast()
            byte[] r1 = (byte[]) r1
            java.lang.Object r2 = r24.pollLast()
            byte[] r2 = (byte[]) r2
            java.lang.Object r3 = r24.pollLast()
            byte[] r3 = (byte[]) r3
            java.lang.Object r4 = r24.pollLast()
            byte[] r4 = (byte[]) r4
            java.lang.Object r5 = r24.pollLast()
            byte[] r5 = (byte[]) r5
            java.lang.Object r6 = r24.pollLast()
            byte[] r6 = (byte[]) r6
            r0.add(r4)
            r0.add(r3)
            r0.add(r2)
            r0.add(r1)
            r0.add(r6)
            r0.add(r5)
            goto L_0x07d7
        L_0x069f:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_2ROT on a stack with size < 6"
            r0.<init>(r1)
            throw r0
        L_0x06a7:
            r19 = r8
            r12 = 0
            int r2 = r24.size()
            if (r2 < r1) goto L_0x06cc
            java.util.Iterator r1 = r24.descendingIterator()
            r1.next()
            r1.next()
            java.lang.Object r2 = r1.next()
            byte[] r2 = (byte[]) r2
            java.lang.Object r1 = r1.next()
            r0.add(r1)
            r0.add(r2)
            goto L_0x07d7
        L_0x06cc:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_2OVER on a stack with size < 4"
            r0.<init>(r1)
            throw r0
        L_0x06d4:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            if (r1 < r3) goto L_0x06fc
            java.util.Iterator r1 = r24.descendingIterator()
            java.lang.Object r2 = r1.next()
            byte[] r2 = (byte[]) r2
            java.lang.Object r3 = r1.next()
            byte[] r3 = (byte[]) r3
            java.lang.Object r1 = r1.next()
            r0.add(r1)
            r0.add(r3)
            r0.add(r2)
            goto L_0x07d7
        L_0x06fc:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_3DUP on a stack with size < 3"
            r0.<init>(r1)
            throw r0
        L_0x0704:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            r2 = 2
            if (r1 < r2) goto L_0x0724
            java.util.Iterator r1 = r24.descendingIterator()
            java.lang.Object r2 = r1.next()
            byte[] r2 = (byte[]) r2
            java.lang.Object r1 = r1.next()
            r0.add(r1)
            r0.add(r2)
            goto L_0x07d7
        L_0x0724:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_2DUP on a stack with size < 2"
            r0.<init>(r1)
            throw r0
        L_0x072c:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            r2 = 2
            if (r1 < r2) goto L_0x073e
            r24.pollLast()
            r24.pollLast()
            goto L_0x07d7
        L_0x073e:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_2DROP on a stack with size < 2"
            r0.<init>(r1)
            throw r0
        L_0x0746:
            r19 = r8
            r12 = 0
            int r1 = r13.size()
            if (r1 < r4) goto L_0x0758
            java.lang.Object r1 = r13.pollLast()
            r0.add(r1)
            goto L_0x07d7
        L_0x0758:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_FROMALTSTACK on an empty altstack"
            r0.<init>(r1)
            throw r0
        L_0x0760:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            if (r1 < r4) goto L_0x0771
            java.lang.Object r1 = r24.pollLast()
            r13.add(r1)
            goto L_0x07d7
        L_0x0771:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_TOALTSTACK on an empty stack"
            r0.<init>(r1)
            throw r0
        L_0x0779:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Script called OP_RETURN"
            r0.<init>(r1)
            throw r0
        L_0x0781:
            r19 = r8
            r12 = 0
            int r1 = r24.size()
            if (r1 < r4) goto L_0x079f
            java.lang.Object r1 = r24.pollLast()
            byte[] r1 = (byte[]) r1
            boolean r1 = castToBool(r1)
            if (r1 == 0) goto L_0x0797
            goto L_0x07d7
        L_0x0797:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "OP_VERIFY failed"
            r0.<init>(r1)
            throw r0
        L_0x079f:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_VERIFY on an empty stack"
            r0.<init>(r1)
            throw r0
        L_0x07a7:
            r19 = r8
            r12 = 0
            goto L_0x07d7
        L_0x07ab:
            r19 = r8
            r12 = 0
            int r1 = decodeFromOpN(r7)
            long r1 = (long) r1
            java.math.BigInteger r1 = java.math.BigInteger.valueOf(r1)
            byte[] r1 = org.bitcoinj.core.C3447Utils.encodeMPI(r1, r12)
            byte[] r1 = org.bitcoinj.core.C3447Utils.reverseBytes(r1)
            r0.add(r1)
            goto L_0x07d7
        L_0x07c3:
            r19 = r8
            r12 = 0
            java.math.BigInteger r1 = java.math.BigInteger.ONE
            java.math.BigInteger r1 = r1.negate()
            byte[] r1 = org.bitcoinj.core.C3447Utils.encodeMPI(r1, r12)
            byte[] r1 = org.bitcoinj.core.C3447Utils.reverseBytes(r1)
            r0.add(r1)
        L_0x07d7:
            r1 = r18
        L_0x07d9:
            int r2 = r24.size()
            int r3 = r13.size()
            int r2 = r2 + r3
            r3 = 1000(0x3e8, float:1.401E-42)
            if (r2 > r3) goto L_0x07f8
            int r2 = r24.size()
            int r3 = r13.size()
            int r2 = r2 + r3
            if (r2 < 0) goto L_0x07f8
            r12 = r26
            r8 = r19
        L_0x07f5:
            r9 = 0
            goto L_0x0020
        L_0x07f8:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Stack size exceeded range"
            r0.<init>(r1)
            throw r0
        L_0x0800:
            r19 = r8
            r12 = 0
            boolean r1 = r14.isEmpty()
            if (r1 != 0) goto L_0x0810
            r14.pollLast()
        L_0x080c:
            r1 = r19
            goto L_0x0889
        L_0x0810:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_ENDIF without OP_IF/NOTIF"
            r0.<init>(r1)
            throw r0
        L_0x0818:
            r19 = r8
            r12 = 0
            boolean r1 = r14.isEmpty()
            if (r1 != 0) goto L_0x0834
            java.lang.Object r1 = r14.pollLast()
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            r1 = r1 ^ r4
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r14.add(r1)
            goto L_0x080c
        L_0x0834:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_ELSE without OP_IF/NOTIF"
            r0.<init>(r1)
            throw r0
        L_0x083c:
            r19 = r8
            r12 = 0
            if (r3 != 0) goto L_0x0847
            r1 = r19
            r14.add(r1)
            goto L_0x0889
        L_0x0847:
            r1 = r19
            int r2 = r24.size()
            if (r2 < r4) goto L_0x0862
            java.lang.Object r2 = r24.pollLast()
            byte[] r2 = (byte[]) r2
            boolean r2 = castToBool(r2)
            r2 = r2 ^ r4
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            r14.add(r2)
            goto L_0x0889
        L_0x0862:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_NOTIF on an empty stack"
            r0.<init>(r1)
            throw r0
        L_0x086a:
            r1 = r8
            r12 = 0
            if (r3 != 0) goto L_0x0872
            r14.add(r1)
            goto L_0x0889
        L_0x0872:
            int r2 = r24.size()
            if (r2 < r4) goto L_0x0890
            java.lang.Object r2 = r24.pollLast()
            byte[] r2 = (byte[]) r2
            boolean r2 = castToBool(r2)
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            r14.add(r2)
        L_0x0889:
            r12 = r26
            r8 = r1
            r1 = r18
            goto L_0x07f5
        L_0x0890:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Attempted OP_IF on an empty stack"
            r0.<init>(r1)
            throw r0
        L_0x0898:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Script included a disabled Script Op."
            r0.<init>(r1)
            throw r0
        L_0x08a0:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "Script included OP_VERIF or OP_VERNOTIF"
            r0.<init>(r1)
            throw r0
        L_0x08a8:
            boolean r0 = r14.isEmpty()
            if (r0 == 0) goto L_0x08af
            return
        L_0x08af:
            org.bitcoinj.core.ScriptException r0 = new org.bitcoinj.core.ScriptException
            java.lang.String r1 = "OP_IF/OP_NOTIF without OP_ENDIF"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.script.Script.executeScript(org.bitcoinj.core.Transaction, long, org.bitcoinj.script.Script, java.util.LinkedList, org.bitcoinj.core.Coin, java.util.Set):void");
    }

    private static void executeCheckLockTimeVerify(Transaction transaction, int i, Script script, LinkedList<byte[]> linkedList, int i2, int i3, Set<VerifyFlag> set) throws ScriptException {
        if (linkedList.size() >= 1) {
            BigInteger castToBigInteger = castToBigInteger((byte[]) linkedList.getLast(), 5);
            if (castToBigInteger.compareTo(BigInteger.ZERO) < 0) {
                throw new ScriptException("Negative locktime");
            } else if ((transaction.getLockTime() >= 500000000 || castToBigInteger.compareTo(Transaction.LOCKTIME_THRESHOLD_BIG) >= 0) && (transaction.getLockTime() < 500000000 || castToBigInteger.compareTo(Transaction.LOCKTIME_THRESHOLD_BIG) < 0)) {
                throw new ScriptException("Locktime requirement type mismatch");
            } else if (castToBigInteger.compareTo(BigInteger.valueOf(transaction.getLockTime())) > 0) {
                throw new ScriptException("Locktime requirement not satisfied");
            } else if (!transaction.getInput((long) i).hasSequence()) {
                throw new ScriptException("Transaction contains a final transaction input for a CHECKLOCKTIMEVERIFY script.");
            }
        } else {
            throw new ScriptException("Attempted OP_CHECKLOCKTIMEVERIFY on a stack with size < 1");
        }
    }

    private static void executeCheckSig(Transaction transaction, int i, Script script, LinkedList<byte[]> linkedList, int i2, int i3, Coin coin, Set<VerifyFlag> set) throws ScriptException {
        boolean z;
        Sha256Hash sha256Hash;
        int i4 = i3;
        Set<VerifyFlag> set2 = set;
        boolean z2 = set2.contains(VerifyFlag.STRICTENC) || set2.contains(VerifyFlag.DERSIG) || set2.contains(VerifyFlag.LOW_S);
        if (linkedList.size() >= 2) {
            byte[] bArr = (byte[]) linkedList.pollLast();
            byte[] bArr2 = (byte[]) linkedList.pollLast();
            byte[] program2 = script.getProgram();
            byte[] copyOfRange = Arrays.copyOfRange(program2, i2, program2.length);
            UnsafeByteArrayOutputStream unsafeByteArrayOutputStream = new UnsafeByteArrayOutputStream(bArr2.length + 1);
            try {
                writeBytes(unsafeByteArrayOutputStream, bArr2);
                byte[] removeAllInstancesOf = removeAllInstancesOf(copyOfRange, unsafeByteArrayOutputStream.toByteArray());
                try {
                    TransactionSignature decodeFromBitcoin = TransactionSignature.decodeFromBitcoin(bArr2, z2, set2.contains(VerifyFlag.LOW_S));
                    if (decodeFromBitcoin.useForkId()) {
                        sha256Hash = transaction.hashForSignatureWitness(i, removeAllInstancesOf, coin, decodeFromBitcoin.sigHashMode(), decodeFromBitcoin.anyoneCanPay());
                    } else {
                        Transaction transaction2 = transaction;
                        sha256Hash = transaction.hashForSignature(i, removeAllInstancesOf, (byte) decodeFromBitcoin.sighashFlags);
                    }
                    z = ECKey.verify(sha256Hash.getBytes(), (ECDSASignature) decodeFromBitcoin, bArr);
                } catch (Exception e) {
                    if (!e.getMessage().contains("Reached past end of ASN.1 stream")) {
                        log.warn("Signature checking failed!", (Throwable) e);
                    }
                    z = false;
                }
                if (i4 == 172) {
                    linkedList.add(z ? new byte[]{1} : new byte[0]);
                } else if (i4 == 173 && !z) {
                    throw new ScriptException("Script failed OP_CHECKSIGVERIFY");
                }
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        } else {
            throw new ScriptException("Attempted OP_CHECKSIG(VERIFY) on a stack with size < 2");
        }
    }

    private static int executeMultiSig(Transaction transaction, int i, Script script, LinkedList<byte[]> linkedList, int i2, int i3, int i4, Coin coin, Set<VerifyFlag> set) throws ScriptException {
        boolean z;
        Sha256Hash sha256Hash;
        TransactionSignature transactionSignature;
        int i5 = i4;
        Set<VerifyFlag> set2 = set;
        boolean z2 = set2.contains(VerifyFlag.STRICTENC) || set2.contains(VerifyFlag.DERSIG) || set2.contains(VerifyFlag.LOW_S);
        if (linkedList.size() >= 2) {
            int intValue = castToBigInteger((byte[]) linkedList.pollLast()).intValue();
            if (intValue < 0 || intValue > 20) {
                throw new ScriptException("OP_CHECKMULTISIG(VERIFY) with pubkey count out of range");
            }
            int i6 = i2 + intValue;
            if (i6 > 201) {
                throw new ScriptException("Total op count > 201 during OP_CHECKMULTISIG(VERIFY)");
            } else if (linkedList.size() >= intValue + 1) {
                LinkedList linkedList2 = new LinkedList();
                for (int i7 = 0; i7 < intValue; i7++) {
                    linkedList2.add((byte[]) linkedList.pollLast());
                }
                int intValue2 = castToBigInteger((byte[]) linkedList.pollLast()).intValue();
                if (intValue2 < 0 || intValue2 > intValue) {
                    throw new ScriptException("OP_CHECKMULTISIG(VERIFY) with sig count out of range");
                } else if (linkedList.size() >= intValue2 + 1) {
                    LinkedList linkedList3 = new LinkedList();
                    for (int i8 = 0; i8 < intValue2; i8++) {
                        linkedList3.add((byte[]) linkedList.pollLast());
                    }
                    byte[] program2 = script.getProgram();
                    byte[] copyOfRange = Arrays.copyOfRange(program2, i3, program2.length);
                    Iterator it = linkedList3.iterator();
                    while (it.hasNext()) {
                        byte[] bArr = (byte[]) it.next();
                        UnsafeByteArrayOutputStream unsafeByteArrayOutputStream = new UnsafeByteArrayOutputStream(bArr.length + 1);
                        try {
                            writeBytes(unsafeByteArrayOutputStream, bArr);
                            copyOfRange = removeAllInstancesOf(copyOfRange, unsafeByteArrayOutputStream.toByteArray());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    while (true) {
                        if (linkedList3.size() <= 0) {
                            z = true;
                            break;
                        }
                        byte[] bArr2 = (byte[]) linkedList2.pollFirst();
                        try {
                            TransactionSignature decodeFromBitcoin = TransactionSignature.decodeFromBitcoin((byte[]) linkedList3.getFirst(), z2);
                            if (decodeFromBitcoin.useForkId()) {
                                transactionSignature = decodeFromBitcoin;
                                sha256Hash = transaction.hashForSignatureWitness(i, copyOfRange, coin, decodeFromBitcoin.sigHashMode(), decodeFromBitcoin.anyoneCanPay());
                                Transaction transaction2 = transaction;
                                int i9 = i;
                            } else {
                                transactionSignature = decodeFromBitcoin;
                                try {
                                    sha256Hash = transaction.hashForSignature(i, copyOfRange, (byte) transactionSignature.sighashFlags);
                                } catch (Exception unused) {
                                }
                            }
                            if (ECKey.verify(sha256Hash.getBytes(), (ECDSASignature) transactionSignature, bArr2)) {
                                linkedList3.pollFirst();
                            }
                        } catch (Exception unused2) {
                            Transaction transaction3 = transaction;
                            int i10 = i;
                        }
                        if (linkedList3.size() > linkedList2.size()) {
                            z = false;
                            break;
                        }
                    }
                    byte[] bArr3 = (byte[]) linkedList.pollLast();
                    if (!set2.contains(VerifyFlag.NULLDUMMY) || bArr3.length <= 0) {
                        if (i5 == 174) {
                            linkedList.add(z ? new byte[]{1} : new byte[0]);
                        } else if (i5 == 175 && !z) {
                            throw new ScriptException("Script failed OP_CHECKMULTISIGVERIFY");
                        }
                        return i6;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("OP_CHECKMULTISIG(VERIFY) with non-null nulldummy: ");
                    sb.append(Arrays.toString(bArr3));
                    throw new ScriptException(sb.toString());
                } else {
                    throw new ScriptException("Attempted OP_CHECKMULTISIG(VERIFY) on a stack with size < num_of_pubkeys + num_of_signatures + 3");
                }
            } else {
                throw new ScriptException("Attempted OP_CHECKMULTISIG(VERIFY) on a stack with size < num_of_pubkeys + 2");
            }
        } else {
            throw new ScriptException("Attempted OP_CHECKMULTISIG(VERIFY) on a stack with size < 2");
        }
    }

    @Deprecated
    public void correctlySpends(Transaction transaction, long j, Script script) throws ScriptException {
        correctlySpends(transaction, j, script, Coin.ZERO, ALL_VERIFY_FLAGS);
    }

    @Deprecated
    public void correctlySpends(Transaction transaction, long j, Script script, Set<VerifyFlag> set) throws ScriptException {
        correctlySpends(transaction, j, script, Coin.ZERO, set);
    }

    public void correctlySpends(Transaction transaction, long j, Script script, Coin coin, Set<VerifyFlag> set) throws ScriptException {
        try {
            Transaction makeTransaction = transaction.getParams().getDefaultSerializer().makeTransaction(transaction.bitcoinSerialize());
            if (getProgram().length > 10000 || script.getProgram().length > 10000) {
                throw new ScriptException("Script larger than 10,000 bytes");
            }
            LinkedList linkedList = new LinkedList();
            LinkedList linkedList2 = null;
            executeScript(makeTransaction, j, this, linkedList, coin, set);
            if (set.contains(VerifyFlag.P2SH)) {
                linkedList2 = new LinkedList(linkedList);
            }
            executeScript(makeTransaction, j, script, linkedList, coin, set);
            if (linkedList.size() == 0) {
                throw new ScriptException("Stack empty at end of script execution.");
            } else if (!castToBool((byte[]) linkedList.pollLast())) {
                StringBuilder sb = new StringBuilder();
                sb.append("Script resulted in a non-true stack: ");
                sb.append(linkedList);
                throw new ScriptException(sb.toString());
            } else if (set.contains(VerifyFlag.P2SH) && script.isPayToScriptHash()) {
                for (ScriptChunk scriptChunk : this.chunks) {
                    if (scriptChunk.isOpCode() && scriptChunk.opcode > 96) {
                        throw new ScriptException("Attempted to spend a P2SH scriptPubKey with a script that contained script ops");
                    }
                }
                executeScript(makeTransaction, j, new Script((byte[]) linkedList2.pollLast()), linkedList2, coin, set);
                if (linkedList2.size() == 0) {
                    throw new ScriptException("P2SH stack empty at end of script execution.");
                } else if (!castToBool((byte[]) linkedList2.pollLast())) {
                    throw new ScriptException("P2SH script execution resulted in a non-true stack");
                }
            }
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] getQuickProgram() {
        byte[] bArr = this.program;
        if (bArr != null) {
            return bArr;
        }
        return getProgram();
    }

    public ScriptType getScriptType() {
        ScriptType scriptType = ScriptType.NO_TYPE;
        if (isSentToAddress()) {
            return ScriptType.P2PKH;
        }
        if (isSentToRawPubKey()) {
            return ScriptType.PUB_KEY;
        }
        return isPayToScriptHash() ? ScriptType.P2SH : scriptType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(getQuickProgram(), ((Script) obj).getQuickProgram());
    }

    public int hashCode() {
        return Arrays.hashCode(getQuickProgram());
    }
}
