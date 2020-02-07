package org.spongycastle.crypto.tls;

import java.io.IOException;

public class DTLSTransport implements DatagramTransport {
    private final DTLSRecordLayer recordLayer;

    DTLSTransport(DTLSRecordLayer dTLSRecordLayer) {
        this.recordLayer = dTLSRecordLayer;
    }

    public int getReceiveLimit() throws IOException {
        return this.recordLayer.getReceiveLimit();
    }

    public int getSendLimit() throws IOException {
        return this.recordLayer.getSendLimit();
    }

    public int receive(byte[] bArr, int i, int i2, int i3) throws IOException {
        try {
            return this.recordLayer.receive(bArr, i, i2, i3);
        } catch (TlsFatalAlert e) {
            this.recordLayer.fail(e.getAlertDescription());
            throw e;
        } catch (IOException e2) {
            this.recordLayer.fail(80);
            throw e2;
        } catch (RuntimeException unused) {
            this.recordLayer.fail(80);
            throw new TlsFatalAlert(80);
        }
    }

    public void send(byte[] bArr, int i, int i2) throws IOException {
        try {
            this.recordLayer.send(bArr, i, i2);
        } catch (TlsFatalAlert e) {
            this.recordLayer.fail(e.getAlertDescription());
            throw e;
        } catch (IOException e2) {
            this.recordLayer.fail(80);
            throw e2;
        } catch (RuntimeException unused) {
            this.recordLayer.fail(80);
            throw new TlsFatalAlert(80);
        }
    }

    public void close() throws IOException {
        this.recordLayer.close();
    }
}
