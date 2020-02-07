package org.bitcoinj.net;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.AbstractExecutionThreadService;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NioServer extends AbstractExecutionThreadService {
    private static final Logger log = LoggerFactory.getLogger(NioServer.class);
    private final StreamConnectionFactory connectionFactory;

    /* renamed from: sc */
    private final ServerSocketChannel f805sc = ServerSocketChannel.open();
    @VisibleForTesting
    final Selector selector;

    private void handleKey(Selector selector2, SelectionKey selectionKey) throws IOException {
        if (!selectionKey.isValid() || !selectionKey.isAcceptable()) {
            ConnectionHandler.handleKey(selectionKey);
            return;
        }
        SocketChannel accept = this.f805sc.accept();
        accept.configureBlocking(false);
        SelectionKey register = accept.register(selector2, 1);
        try {
            ConnectionHandler connectionHandler = new ConnectionHandler(this.connectionFactory, register);
            register.attach(connectionHandler);
            connectionHandler.connection.connectionOpened();
        } catch (IOException e) {
            log.error("Error handling new connection", (Object) Throwables.getRootCause(e).getMessage());
            register.channel().close();
        }
    }

    public NioServer(StreamConnectionFactory streamConnectionFactory, InetSocketAddress inetSocketAddress) throws IOException {
        this.connectionFactory = streamConnectionFactory;
        this.f805sc.configureBlocking(false);
        this.f805sc.socket().bind(inetSocketAddress);
        this.selector = SelectorProvider.provider().openSelector();
        this.f805sc.register(this.selector, 16);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x000e A[Catch:{ Exception -> 0x0081, all -> 0x007f }] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:24:0x0072=Splitter:B:24:0x0072, B:48:0x00c9=Splitter:B:48:0x00c9} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() throws java.lang.Exception {
        /*
            r9 = this;
            java.lang.String r0 = "Error closing selection key"
            java.lang.String r1 = "Error closing server channel"
            java.lang.String r2 = "Error closing channel"
            java.lang.String r3 = "Error closing server selector"
        L_0x0008:
            boolean r4 = r9.isRunning()     // Catch:{ Exception -> 0x0081 }
            if (r4 == 0) goto L_0x0032
            java.nio.channels.Selector r4 = r9.selector     // Catch:{ Exception -> 0x0081 }
            r4.select()     // Catch:{ Exception -> 0x0081 }
            java.nio.channels.Selector r4 = r9.selector     // Catch:{ Exception -> 0x0081 }
            java.util.Set r4 = r4.selectedKeys()     // Catch:{ Exception -> 0x0081 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ Exception -> 0x0081 }
        L_0x001d:
            boolean r5 = r4.hasNext()     // Catch:{ Exception -> 0x0081 }
            if (r5 == 0) goto L_0x0008
            java.lang.Object r5 = r4.next()     // Catch:{ Exception -> 0x0081 }
            java.nio.channels.SelectionKey r5 = (java.nio.channels.SelectionKey) r5     // Catch:{ Exception -> 0x0081 }
            r4.remove()     // Catch:{ Exception -> 0x0081 }
            java.nio.channels.Selector r6 = r9.selector     // Catch:{ Exception -> 0x0081 }
            r9.handleKey(r6, r5)     // Catch:{ Exception -> 0x0081 }
            goto L_0x001d
        L_0x0032:
            java.nio.channels.Selector r4 = r9.selector
            java.util.Set r4 = r4.keys()
            java.util.Iterator r4 = r4.iterator()
        L_0x003c:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0066
            java.lang.Object r5 = r4.next()
            java.nio.channels.SelectionKey r5 = (java.nio.channels.SelectionKey) r5
            java.nio.channels.SelectableChannel r6 = r5.channel()     // Catch:{ IOException -> 0x0050 }
            r6.close()     // Catch:{ IOException -> 0x0050 }
            goto L_0x0056
        L_0x0050:
            r6 = move-exception
            org.slf4j.Logger r7 = log
            r7.error(r2, r6)
        L_0x0056:
            r5.cancel()     // Catch:{ IOException -> 0x005f }
            java.nio.channels.Selector r6 = r9.selector     // Catch:{ IOException -> 0x005f }
            r9.handleKey(r6, r5)     // Catch:{ IOException -> 0x005f }
            goto L_0x003c
        L_0x005f:
            r5 = move-exception
            org.slf4j.Logger r6 = log
            r6.error(r0, r5)
            goto L_0x003c
        L_0x0066:
            java.nio.channels.Selector r0 = r9.selector     // Catch:{ IOException -> 0x006c }
            r0.close()     // Catch:{ IOException -> 0x006c }
            goto L_0x0072
        L_0x006c:
            r0 = move-exception
            org.slf4j.Logger r2 = log
            r2.error(r3, r0)
        L_0x0072:
            java.nio.channels.ServerSocketChannel r0 = r9.f805sc     // Catch:{ IOException -> 0x0078 }
            r0.close()     // Catch:{ IOException -> 0x0078 }
            goto L_0x00ce
        L_0x0078:
            r0 = move-exception
            org.slf4j.Logger r2 = log
            r2.error(r1, r0)
            goto L_0x00ce
        L_0x007f:
            r4 = move-exception
            goto L_0x00cf
        L_0x0081:
            r4 = move-exception
            org.slf4j.Logger r5 = log     // Catch:{ all -> 0x007f }
            java.lang.String r6 = "Error trying to open/read from connection: {}"
            r5.error(r6, r4)     // Catch:{ all -> 0x007f }
            java.nio.channels.Selector r4 = r9.selector
            java.util.Set r4 = r4.keys()
            java.util.Iterator r4 = r4.iterator()
        L_0x0093:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x00bd
            java.lang.Object r5 = r4.next()
            java.nio.channels.SelectionKey r5 = (java.nio.channels.SelectionKey) r5
            java.nio.channels.SelectableChannel r6 = r5.channel()     // Catch:{ IOException -> 0x00a7 }
            r6.close()     // Catch:{ IOException -> 0x00a7 }
            goto L_0x00ad
        L_0x00a7:
            r6 = move-exception
            org.slf4j.Logger r7 = log
            r7.error(r2, r6)
        L_0x00ad:
            r5.cancel()     // Catch:{ IOException -> 0x00b6 }
            java.nio.channels.Selector r6 = r9.selector     // Catch:{ IOException -> 0x00b6 }
            r9.handleKey(r6, r5)     // Catch:{ IOException -> 0x00b6 }
            goto L_0x0093
        L_0x00b6:
            r5 = move-exception
            org.slf4j.Logger r6 = log
            r6.error(r0, r5)
            goto L_0x0093
        L_0x00bd:
            java.nio.channels.Selector r0 = r9.selector     // Catch:{ IOException -> 0x00c3 }
            r0.close()     // Catch:{ IOException -> 0x00c3 }
            goto L_0x00c9
        L_0x00c3:
            r0 = move-exception
            org.slf4j.Logger r2 = log
            r2.error(r3, r0)
        L_0x00c9:
            java.nio.channels.ServerSocketChannel r0 = r9.f805sc     // Catch:{ IOException -> 0x0078 }
            r0.close()     // Catch:{ IOException -> 0x0078 }
        L_0x00ce:
            return
        L_0x00cf:
            java.nio.channels.Selector r5 = r9.selector
            java.util.Set r5 = r5.keys()
            java.util.Iterator r5 = r5.iterator()
        L_0x00d9:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0103
            java.lang.Object r6 = r5.next()
            java.nio.channels.SelectionKey r6 = (java.nio.channels.SelectionKey) r6
            java.nio.channels.SelectableChannel r7 = r6.channel()     // Catch:{ IOException -> 0x00ed }
            r7.close()     // Catch:{ IOException -> 0x00ed }
            goto L_0x00f3
        L_0x00ed:
            r7 = move-exception
            org.slf4j.Logger r8 = log
            r8.error(r2, r7)
        L_0x00f3:
            r6.cancel()     // Catch:{ IOException -> 0x00fc }
            java.nio.channels.Selector r7 = r9.selector     // Catch:{ IOException -> 0x00fc }
            r9.handleKey(r7, r6)     // Catch:{ IOException -> 0x00fc }
            goto L_0x00d9
        L_0x00fc:
            r6 = move-exception
            org.slf4j.Logger r7 = log
            r7.error(r0, r6)
            goto L_0x00d9
        L_0x0103:
            java.nio.channels.Selector r0 = r9.selector     // Catch:{ IOException -> 0x0109 }
            r0.close()     // Catch:{ IOException -> 0x0109 }
            goto L_0x010f
        L_0x0109:
            r0 = move-exception
            org.slf4j.Logger r2 = log
            r2.error(r3, r0)
        L_0x010f:
            java.nio.channels.ServerSocketChannel r0 = r9.f805sc     // Catch:{ IOException -> 0x0115 }
            r0.close()     // Catch:{ IOException -> 0x0115 }
            goto L_0x011b
        L_0x0115:
            r0 = move-exception
            org.slf4j.Logger r2 = log
            r2.error(r1, r0)
        L_0x011b:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.net.NioServer.run():void");
    }

    public void triggerShutdown() {
        this.selector.wakeup();
    }
}
