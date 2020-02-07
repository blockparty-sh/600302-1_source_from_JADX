package org.bitcoinj.net;

import com.google.common.base.Throwables;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NioClient implements MessageWriteTarget {
    /* access modifiers changed from: private */
    public static final Logger log = LoggerFactory.getLogger(NioClient.class);
    private final Handler handler;
    /* access modifiers changed from: private */
    public final NioClientManager manager = new NioClientManager();

    class Handler extends AbstractTimeoutHandler implements StreamConnection {
        private boolean closeCalled = false;
        private boolean closeOnOpen = false;
        private final StreamConnection upstreamConnection;
        /* access modifiers changed from: private */
        public MessageWriteTarget writeTarget;

        Handler(StreamConnection streamConnection, int i) {
            this.upstreamConnection = streamConnection;
            setSocketTimeout(i);
            setTimeoutEnabled(true);
        }

        /* access modifiers changed from: protected */
        public synchronized void timeoutOccurred() {
            this.closeOnOpen = true;
            connectionClosed();
        }

        public synchronized void connectionClosed() {
            NioClient.this.manager.stopAsync();
            if (!this.closeCalled) {
                this.closeCalled = true;
                this.upstreamConnection.connectionClosed();
            }
        }

        public synchronized void connectionOpened() {
            if (!this.closeOnOpen) {
                this.upstreamConnection.connectionOpened();
            }
        }

        public int receiveBytes(ByteBuffer byteBuffer) throws Exception {
            return this.upstreamConnection.receiveBytes(byteBuffer);
        }

        public synchronized void setWriteTarget(MessageWriteTarget messageWriteTarget) {
            if (this.closeOnOpen) {
                messageWriteTarget.closeConnection();
            } else {
                setTimeoutEnabled(false);
                this.writeTarget = messageWriteTarget;
                this.upstreamConnection.setWriteTarget(messageWriteTarget);
            }
        }

        public int getMaxMessageSize() {
            return this.upstreamConnection.getMaxMessageSize();
        }
    }

    public NioClient(final SocketAddress socketAddress, StreamConnection streamConnection, int i) throws IOException {
        this.manager.startAsync();
        this.manager.awaitRunning();
        this.handler = new Handler(streamConnection, i);
        Futures.addCallback(this.manager.openConnection(socketAddress, this.handler), new FutureCallback<SocketAddress>() {
            public void onSuccess(SocketAddress socketAddress) {
            }

            public void onFailure(Throwable th) {
                NioClient.log.error("Connect to {} failed: {}", (Object) socketAddress, (Object) Throwables.getRootCause(th));
            }
        });
    }

    public void closeConnection() {
        this.handler.writeTarget.closeConnection();
    }

    public synchronized void writeBytes(byte[] bArr) throws IOException {
        this.handler.writeTarget.writeBytes(bArr);
    }
}
