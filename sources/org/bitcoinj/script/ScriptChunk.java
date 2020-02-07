package org.bitcoinj.script;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import javax.annotation.Nullable;
import org.bitcoinj.core.C3447Utils;

public class ScriptChunk {
    @Nullable
    public final byte[] data;
    public final int opcode;
    private int startLocationInProgram;

    public ScriptChunk(int i, byte[] bArr) {
        this(i, bArr, -1);
    }

    public ScriptChunk(int i, byte[] bArr, int i2) {
        this.opcode = i;
        this.data = bArr;
        this.startLocationInProgram = i2;
    }

    public boolean equalsOpCode(int i) {
        return i == this.opcode;
    }

    public boolean isOpCode() {
        return this.opcode > 78;
    }

    public boolean isPushData() {
        return this.opcode <= 96;
    }

    public int getStartLocationInProgram() {
        Preconditions.checkState(this.startLocationInProgram >= 0);
        return this.startLocationInProgram;
    }

    public int decodeOpN() {
        Preconditions.checkState(isOpCode());
        return Script.decodeFromOpN(this.opcode);
    }

    public boolean isShortestPossiblePushData() {
        Preconditions.checkState(isPushData());
        byte[] bArr = this.data;
        boolean z = true;
        if (bArr == null) {
            return true;
        }
        if (bArr.length == 0) {
            if (this.opcode != 0) {
                z = false;
            }
            return z;
        }
        if (bArr.length == 1) {
            byte b = bArr[0];
            if (b >= 1 && b <= 16) {
                if (this.opcode != (b + 81) - 1) {
                    z = false;
                }
                return z;
            } else if ((b & 255) == 129) {
                if (this.opcode != 79) {
                    z = false;
                }
                return z;
            }
        }
        byte[] bArr2 = this.data;
        if (bArr2.length < 76) {
            if (this.opcode != bArr2.length) {
                z = false;
            }
            return z;
        } else if (bArr2.length < 256) {
            if (this.opcode != 76) {
                z = false;
            }
            return z;
        } else if (bArr2.length < 65536) {
            if (this.opcode != 77) {
                z = false;
            }
            return z;
        } else {
            if (this.opcode != 78) {
                z = false;
            }
            return z;
        }
    }

    public void write(OutputStream outputStream) throws IOException {
        boolean z = true;
        if (isOpCode()) {
            if (this.data != null) {
                z = false;
            }
            Preconditions.checkState(z);
            outputStream.write(this.opcode);
            return;
        }
        byte[] bArr = this.data;
        if (bArr != null) {
            int i = this.opcode;
            if (i < 76) {
                if (bArr.length != i) {
                    z = false;
                }
                Preconditions.checkState(z);
                outputStream.write(this.opcode);
            } else if (i == 76) {
                if (bArr.length > 255) {
                    z = false;
                }
                Preconditions.checkState(z);
                outputStream.write(76);
                outputStream.write(this.data.length);
            } else if (i == 77) {
                if (bArr.length > 65535) {
                    z = false;
                }
                Preconditions.checkState(z);
                outputStream.write(77);
                outputStream.write(this.data.length & 255);
                outputStream.write((this.data.length >> 8) & 255);
            } else if (i == 78) {
                if (((long) bArr.length) > 520) {
                    z = false;
                }
                Preconditions.checkState(z);
                outputStream.write(78);
                C3447Utils.uint32ToByteStreamLE((long) this.data.length, outputStream);
            } else {
                throw new RuntimeException("Unimplemented");
            }
            outputStream.write(this.data);
            return;
        }
        outputStream.write(this.opcode);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isOpCode()) {
            sb.append(ScriptOpCodes.getOpCodeName(this.opcode));
        } else if (this.data != null) {
            sb.append(ScriptOpCodes.getPushDataName(this.opcode));
            sb.append("[");
            sb.append(C3447Utils.HEX.encode(this.data));
            sb.append("]");
        } else {
            sb.append(Script.decodeFromOpN(this.opcode));
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ScriptChunk scriptChunk = (ScriptChunk) obj;
        if (!(this.opcode == scriptChunk.opcode && this.startLocationInProgram == scriptChunk.startLocationInProgram && Arrays.equals(this.data, scriptChunk.data))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.opcode), Integer.valueOf(this.startLocationInProgram), Integer.valueOf(Arrays.hashCode(this.data)));
    }
}
