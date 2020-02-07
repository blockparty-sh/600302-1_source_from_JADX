package org.bitcoinj.core;

import javax.annotation.Nullable;

public abstract class ChildMessage extends Message {
    @Nullable
    protected Message parent;

    @Deprecated
    protected ChildMessage() {
    }

    public ChildMessage(NetworkParameters networkParameters) {
        super(networkParameters);
    }

    public ChildMessage(NetworkParameters networkParameters, byte[] bArr, int i, int i2) throws ProtocolException {
        super(networkParameters, bArr, i, i2);
    }

    public ChildMessage(NetworkParameters networkParameters, byte[] bArr, int i, int i2, Message message, MessageSerializer messageSerializer, int i3) throws ProtocolException {
        super(networkParameters, bArr, i, i2, messageSerializer, i3);
        this.parent = message;
    }

    public ChildMessage(NetworkParameters networkParameters, byte[] bArr, int i) throws ProtocolException {
        super(networkParameters, bArr, i);
    }

    public ChildMessage(NetworkParameters networkParameters, byte[] bArr, int i, @Nullable Message message, MessageSerializer messageSerializer, int i2) throws ProtocolException {
        super(networkParameters, bArr, i, messageSerializer, i2);
        this.parent = message;
    }

    public final void setParent(@Nullable Message message) {
        Message message2 = this.parent;
        if (!(message2 == null || message2 == message || message == null)) {
            message2.unCache();
        }
        this.parent = message;
    }

    /* access modifiers changed from: protected */
    public void unCache() {
        super.unCache();
        Message message = this.parent;
        if (message != null) {
            message.unCache();
        }
    }

    /* access modifiers changed from: protected */
    public void adjustLength(int i) {
        adjustLength(0, i);
    }

    /* access modifiers changed from: protected */
    public void adjustLength(int i, int i2) {
        super.adjustLength(i, i2);
        Message message = this.parent;
        if (message != null) {
            message.adjustLength(i, i2);
        }
    }
}
