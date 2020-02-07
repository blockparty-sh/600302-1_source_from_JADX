package org.bitcoinj.script;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import javax.annotation.Nullable;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.TransactionSignature;

public class ScriptBuilder {
    private List<ScriptChunk> chunks;

    public ScriptBuilder() {
        this.chunks = Lists.newLinkedList();
    }

    public ScriptBuilder(Script script) {
        this.chunks = new ArrayList(script.getChunks());
    }

    public ScriptBuilder addChunk(ScriptChunk scriptChunk) {
        return addChunk(this.chunks.size(), scriptChunk);
    }

    public ScriptBuilder addChunk(int i, ScriptChunk scriptChunk) {
        this.chunks.add(i, scriptChunk);
        return this;
    }

    /* renamed from: op */
    public ScriptBuilder mo46573op(int i) {
        return mo46574op(this.chunks.size(), i);
    }

    /* renamed from: op */
    public ScriptBuilder mo46574op(int i, int i2) {
        Preconditions.checkArgument(i2 > 78);
        return addChunk(i, new ScriptChunk(i2, null));
    }

    public ScriptBuilder data(byte[] bArr) {
        if (bArr.length == 0) {
            return smallNum(0);
        }
        return data(this.chunks.size(), bArr);
    }

    public ScriptBuilder data(int i, byte[] bArr) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        int i2 = 76;
        if (bArr.length == 0) {
            i2 = 0;
        } else if (bArr.length == 1) {
            byte b = bArr[0];
            i2 = (b < 1 || b > 16) ? 1 : Script.encodeToOpN(b);
        } else if (bArr.length < 76) {
            i2 = bArr.length;
        } else if (bArr.length >= 256) {
            if (bArr.length < 65536) {
                i2 = 77;
            } else {
                throw new RuntimeException("Unimplemented");
            }
        }
        return addChunk(i, new ScriptChunk(i2, copyOf));
    }

    public ScriptBuilder number(long j) {
        if (j < 0 || j > 16) {
            return bigNum(j);
        }
        return smallNum((int) j);
    }

    public ScriptBuilder number(int i, long j) {
        if (j < 0 || j > 16) {
            return bigNum(i, j);
        }
        return addChunk(i, new ScriptChunk(Script.encodeToOpN((int) j), null));
    }

    public ScriptBuilder smallNum(int i) {
        return smallNum(this.chunks.size(), i);
    }

    /* access modifiers changed from: protected */
    public ScriptBuilder bigNum(long j) {
        return bigNum(this.chunks.size(), j);
    }

    public ScriptBuilder smallNum(int i, int i2) {
        boolean z = true;
        Preconditions.checkArgument(i2 >= 0, "Cannot encode negative numbers with smallNum");
        if (i2 > 16) {
            z = false;
        }
        Preconditions.checkArgument(z, "Cannot encode numbers larger than 16 with smallNum");
        return addChunk(i, new ScriptChunk(Script.encodeToOpN(i2), null));
    }

    /* access modifiers changed from: protected */
    public ScriptBuilder bigNum(int i, long j) {
        byte[] bArr;
        int i2 = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i2 == 0) {
            bArr = new byte[0];
        } else {
            Stack stack = new Stack();
            boolean z = i2 < 0;
            for (long abs = Math.abs(j); abs != 0; abs >>= 8) {
                stack.push(Byte.valueOf((byte) ((int) (255 & abs))));
            }
            int i3 = 128;
            if ((((Byte) stack.peek()).byteValue() & 128) != 0) {
                if (!z) {
                    i3 = 0;
                }
                stack.push(Byte.valueOf((byte) i3));
            } else if (z) {
                stack.push(Byte.valueOf((byte) (((Byte) stack.pop()).byteValue() | 128)));
            }
            bArr = new byte[stack.size()];
            for (int i4 = 0; i4 < bArr.length; i4++) {
                bArr[i4] = ((Byte) stack.get(i4)).byteValue();
            }
        }
        return addChunk(i, new ScriptChunk(bArr.length, bArr));
    }

    public Script build() {
        return new Script(this.chunks);
    }

    public static Script createOutputScript(Address address) {
        if (address.isP2SHAddress()) {
            return new ScriptBuilder().mo46573op(169).data(address.getHash160()).mo46573op(135).build();
        }
        return new ScriptBuilder().mo46573op(118).mo46573op(169).data(address.getHash160()).mo46573op(136).mo46573op(172).build();
    }

    public static Script createOutputScript(ECKey eCKey) {
        return new ScriptBuilder().data(eCKey.getPubKey()).mo46573op(172).build();
    }

    public static Script createInputScript(@Nullable TransactionSignature transactionSignature, ECKey eCKey) {
        return new ScriptBuilder().data(transactionSignature != null ? transactionSignature.encodeToBitcoin() : new byte[0]).data(eCKey.getPubKey()).build();
    }

    public static Script createInputScript(@Nullable TransactionSignature transactionSignature) {
        return new ScriptBuilder().data(transactionSignature != null ? transactionSignature.encodeToBitcoin() : new byte[0]).build();
    }

    public static Script createMultiSigOutputScript(int i, List<ECKey> list) {
        boolean z = true;
        Preconditions.checkArgument(i > 0);
        Preconditions.checkArgument(i <= list.size());
        if (list.size() > 16) {
            z = false;
        }
        Preconditions.checkArgument(z);
        ScriptBuilder scriptBuilder = new ScriptBuilder();
        scriptBuilder.smallNum(i);
        for (ECKey pubKey : list) {
            scriptBuilder.data(pubKey.getPubKey());
        }
        scriptBuilder.smallNum(list.size());
        scriptBuilder.mo46573op(174);
        return scriptBuilder.build();
    }

    public static Script createMultiSigInputScript(List<TransactionSignature> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (TransactionSignature encodeToBitcoin : list) {
            arrayList.add(encodeToBitcoin.encodeToBitcoin());
        }
        return createMultiSigInputScriptBytes(arrayList, null);
    }

    public static Script createMultiSigInputScript(TransactionSignature... transactionSignatureArr) {
        return createMultiSigInputScript(Arrays.asList(transactionSignatureArr));
    }

    public static Script createMultiSigInputScriptBytes(List<byte[]> list) {
        return createMultiSigInputScriptBytes(list, null);
    }

    public static Script createP2SHMultiSigInputScript(@Nullable List<TransactionSignature> list, Script script) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            int numberOfSignaturesRequiredToSpend = script.getNumberOfSignaturesRequiredToSpend();
            for (int i = 0; i < numberOfSignaturesRequiredToSpend; i++) {
                arrayList.add(new byte[0]);
            }
        } else {
            for (TransactionSignature encodeToBitcoin : list) {
                arrayList.add(encodeToBitcoin.encodeToBitcoin());
            }
        }
        return createMultiSigInputScriptBytes(arrayList, script.getProgram());
    }

    public static Script createMultiSigInputScriptBytes(List<byte[]> list, @Nullable byte[] bArr) {
        Preconditions.checkArgument(list.size() <= 16);
        ScriptBuilder scriptBuilder = new ScriptBuilder();
        scriptBuilder.smallNum(0);
        for (byte[] data : list) {
            scriptBuilder.data(data);
        }
        if (bArr != null) {
            scriptBuilder.data(bArr);
        }
        return scriptBuilder.build();
    }

    public static Script updateScriptWithSignature(Script script, byte[] bArr, int i, int i2, int i3) {
        ScriptBuilder scriptBuilder = new ScriptBuilder();
        List chunks2 = script.getChunks();
        int size = chunks2.size();
        int i4 = size - i3;
        Preconditions.checkArgument(((ScriptChunk) chunks2.get(i4 - 1)).equalsOpCode(0), "ScriptSig is already filled with signatures");
        for (ScriptChunk addChunk : chunks2.subList(0, i2)) {
            scriptBuilder.addChunk(addChunk);
        }
        int i5 = 0;
        boolean z = false;
        for (ScriptChunk scriptChunk : chunks2.subList(i2, i4)) {
            if (i5 == i) {
                scriptBuilder.data(bArr);
                i5++;
                z = true;
            }
            if (!scriptChunk.equalsOpCode(0)) {
                scriptBuilder.addChunk(scriptChunk);
                i5++;
            }
        }
        while (i5 < (size - i2) - i3) {
            if (i5 == i) {
                scriptBuilder.data(bArr);
                z = true;
            } else {
                scriptBuilder.addChunk(new ScriptChunk(0, null));
            }
            i5++;
        }
        for (ScriptChunk addChunk2 : chunks2.subList(i4, size)) {
            scriptBuilder.addChunk(addChunk2);
        }
        Preconditions.checkState(z);
        return scriptBuilder.build();
    }

    public static Script createP2SHOutputScript(byte[] bArr) {
        Preconditions.checkArgument(bArr.length == 20);
        return new ScriptBuilder().mo46573op(169).data(bArr).mo46573op(135).build();
    }

    public static Script createP2SHOutputScript(Script script) {
        return createP2SHOutputScript(C3447Utils.sha256hash160(script.getProgram()));
    }

    public static Script createP2SHOutputScript(int i, List<ECKey> list) {
        return createP2SHOutputScript(createRedeemScript(i, list));
    }

    public static Script createRedeemScript(int i, List<ECKey> list) {
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, ECKey.PUBKEY_COMPARATOR);
        return createMultiSigOutputScript(i, arrayList);
    }

    public static Script createOpReturnScript(byte[] bArr) {
        Preconditions.checkArgument(bArr.length <= 80);
        return new ScriptBuilder().mo46573op(106).data(bArr).build();
    }

    public static Script createCLTVPaymentChannelOutput(BigInteger bigInteger, ECKey eCKey, ECKey eCKey2) {
        byte[] reverseBytes = C3447Utils.reverseBytes(C3447Utils.encodeMPI(bigInteger, false));
        if (reverseBytes.length <= 5) {
            return new ScriptBuilder().mo46573op(99).data(eCKey2.getPubKey()).mo46573op(173).mo46573op(103).data(reverseBytes).mo46573op(177).mo46573op(117).mo46573op(104).data(eCKey.getPubKey()).mo46573op(172).build();
        }
        throw new RuntimeException("Time too large to encode as 5-byte int");
    }

    public static Script createCLTVPaymentChannelRefund(TransactionSignature transactionSignature) {
        ScriptBuilder scriptBuilder = new ScriptBuilder();
        scriptBuilder.data(transactionSignature.encodeToBitcoin());
        scriptBuilder.data(new byte[]{0});
        return scriptBuilder.build();
    }

    public static Script createCLTVPaymentChannelP2SHRefund(TransactionSignature transactionSignature, Script script) {
        ScriptBuilder scriptBuilder = new ScriptBuilder();
        scriptBuilder.data(transactionSignature.encodeToBitcoin());
        scriptBuilder.data(new byte[]{0});
        scriptBuilder.data(script.getProgram());
        return scriptBuilder.build();
    }

    public static Script createCLTVPaymentChannelP2SHInput(byte[] bArr, byte[] bArr2, Script script) {
        ScriptBuilder scriptBuilder = new ScriptBuilder();
        scriptBuilder.data(bArr);
        scriptBuilder.data(bArr2);
        scriptBuilder.smallNum(1);
        scriptBuilder.data(script.getProgram());
        return scriptBuilder.build();
    }

    public static Script createCLTVPaymentChannelInput(TransactionSignature transactionSignature, TransactionSignature transactionSignature2) {
        return createCLTVPaymentChannelInput(transactionSignature.encodeToBitcoin(), transactionSignature2.encodeToBitcoin());
    }

    public static Script createCLTVPaymentChannelInput(byte[] bArr, byte[] bArr2) {
        ScriptBuilder scriptBuilder = new ScriptBuilder();
        scriptBuilder.data(bArr);
        scriptBuilder.data(bArr2);
        scriptBuilder.smallNum(1);
        return scriptBuilder.build();
    }
}
