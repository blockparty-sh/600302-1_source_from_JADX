package com.htc.wallettzservice;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class EncAddr implements Parcelable {
    public static final Creator<EncAddr> CREATOR = new Creator<EncAddr>() {
        public EncAddr createFromParcel(Parcel parcel) {
            return new EncAddr(parcel);
        }

        public EncAddr[] newArray(int i) {
            return new EncAddr[i];
        }
    };
    private byte[] mExtAddr;
    private byte[] mExtEncaddr;
    private byte[] mExtEncaddrSignature;

    public int describeContents() {
        return 0;
    }

    public byte[] getExtAddr() {
        return this.mExtAddr;
    }

    public void setExtAddr(byte[] bArr) {
        this.mExtAddr = bArr;
    }

    public byte[] getExtEncaddr() {
        return this.mExtEncaddr;
    }

    public void setExtEncaddr(byte[] bArr) {
        this.mExtEncaddr = bArr;
    }

    public byte[] getExtEncaddrSignature() {
        return this.mExtEncaddrSignature;
    }

    public void setExtEncaddrSignature(byte[] bArr) {
        this.mExtEncaddrSignature = bArr;
    }

    public EncAddr() {
    }

    private EncAddr(Parcel parcel) {
        this.mExtAddr = parcel.createByteArray();
        this.mExtEncaddr = parcel.createByteArray();
        this.mExtEncaddrSignature = parcel.createByteArray();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this.mExtAddr);
        parcel.writeByteArray(this.mExtEncaddr);
        parcel.writeByteArray(this.mExtEncaddrSignature);
    }
}
