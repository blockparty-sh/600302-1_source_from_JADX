package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class NewSessionTicket {
    protected byte[] ticket;
    protected long ticketLifetimeHint;

    public NewSessionTicket(long j, byte[] bArr) {
        this.ticketLifetimeHint = j;
        this.ticket = bArr;
    }

    public long getTicketLifetimeHint() {
        return this.ticketLifetimeHint;
    }

    public byte[] getTicket() {
        return this.ticket;
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsUtils.writeUint32(this.ticketLifetimeHint, outputStream);
        TlsUtils.writeOpaque16(this.ticket, outputStream);
    }

    public static NewSessionTicket parse(InputStream inputStream) throws IOException {
        return new NewSessionTicket(TlsUtils.readUint32(inputStream), TlsUtils.readOpaque16(inputStream));
    }
}
