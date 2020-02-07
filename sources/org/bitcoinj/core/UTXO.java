package org.bitcoinj.core;

import com.google.common.base.Objects;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Locale;
import org.bitcoinj.script.Script;

public class UTXO {
    private String address;
    private boolean coinbase;
    private Sha256Hash hash;
    private int height;
    private long index;
    private Script script;
    private Coin value;

    public UTXO(Sha256Hash sha256Hash, long j, Coin coin, int i, boolean z, Script script2) {
        this.hash = sha256Hash;
        this.index = j;
        this.value = coin;
        this.height = i;
        this.script = script2;
        this.coinbase = z;
        this.address = "";
    }

    public UTXO(Sha256Hash sha256Hash, long j, Coin coin, int i, boolean z, Script script2, String str) {
        this(sha256Hash, j, coin, i, z, script2);
        this.address = str;
    }

    public UTXO(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[8];
        if (inputStream.read(bArr, 0, 8) == 8) {
            this.value = Coin.valueOf(C3447Utils.readInt64(bArr, 0));
            int read = (inputStream.read() & 255) | ((inputStream.read() & 255) << 8) | ((inputStream.read() & 255) << 16) | ((inputStream.read() & 255) << 24);
            byte[] bArr2 = new byte[read];
            if (inputStream.read(bArr2) == read) {
                this.script = new Script(bArr2);
                byte[] bArr3 = new byte[32];
                if (inputStream.read(bArr3) == 32) {
                    this.hash = Sha256Hash.wrap(bArr3);
                    byte[] bArr4 = new byte[4];
                    if (inputStream.read(bArr4) == 4) {
                        this.index = C3447Utils.readUint32(bArr4, 0);
                        this.height = ((inputStream.read() & 255) << 8) | (inputStream.read() & 255) | ((inputStream.read() & 255) << 16) | ((inputStream.read() & 255) << 24);
                        boolean z = true;
                        byte[] bArr5 = new byte[1];
                        inputStream.read(bArr5);
                        if (bArr5[0] != 1) {
                            z = false;
                        }
                        this.coinbase = z;
                        return;
                    }
                    throw new EOFException();
                }
                throw new EOFException();
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    public Coin getValue() {
        return this.value;
    }

    public Script getScript() {
        return this.script;
    }

    public Sha256Hash getHash() {
        return this.hash;
    }

    public long getIndex() {
        return this.index;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isCoinbase() {
        return this.coinbase;
    }

    public String getAddress() {
        return this.address;
    }

    public String toString() {
        return String.format(Locale.US, "Stored TxOut of %s (%s:%d)", new Object[]{this.value.toFriendlyString(), this.hash, Long.valueOf(this.index)});
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(getIndex()), getHash());
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UTXO utxo = (UTXO) obj;
        if (getIndex() != utxo.getIndex() || !getHash().equals(utxo.getHash())) {
            z = false;
        }
        return z;
    }

    public void serializeToStream(OutputStream outputStream) throws IOException {
        C3447Utils.uint64ToByteStreamLE(BigInteger.valueOf(this.value.value), outputStream);
        byte[] program = this.script.getProgram();
        outputStream.write(program.length & 255);
        outputStream.write((program.length >> 8) & 255);
        outputStream.write((program.length >> 16) & 255);
        outputStream.write((program.length >> 24) & 255);
        outputStream.write(program);
        outputStream.write(this.hash.getBytes());
        C3447Utils.uint32ToByteStreamLE(this.index, outputStream);
        outputStream.write(this.height & 255);
        outputStream.write((this.height >> 8) & 255);
        outputStream.write((this.height >> 16) & 255);
        outputStream.write((this.height >> 24) & 255);
        outputStream.write(new byte[]{this.coinbase ? (byte) 1 : 0});
    }
}
