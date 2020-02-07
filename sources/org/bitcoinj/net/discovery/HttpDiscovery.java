package org.bitcoinj.net.discovery;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.net.HttpHeaders;
import com.google.protobuf.InvalidProtocolBufferException;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.HttpUrl.Builder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.net.InetSocketAddress;
import java.net.URI;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import javax.annotation.Nullable;
import org.bitcoin.crawler.PeerSeedProtos.PeerSeedData;
import org.bitcoin.crawler.PeerSeedProtos.PeerSeeds;
import org.bitcoin.crawler.PeerSeedProtos.SignedPeerSeeds;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.VersionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpDiscovery implements PeerDiscovery {
    private static final Logger log = LoggerFactory.getLogger(HttpDiscovery.class);
    private final OkHttpClient client;
    private final Details details;
    private final NetworkParameters params;

    public static class Details {
        @Nullable
        public final ECKey pubkey;
        public final URI uri;

        public Details(@Nullable ECKey eCKey, URI uri2) {
            this.pubkey = eCKey;
            this.uri = uri2;
        }
    }

    public void shutdown() {
    }

    public HttpDiscovery(NetworkParameters networkParameters, URI uri, @Nullable ECKey eCKey) {
        this(networkParameters, new Details(eCKey, uri));
    }

    public HttpDiscovery(NetworkParameters networkParameters, Details details2) {
        this(networkParameters, details2, new OkHttpClient());
    }

    public HttpDiscovery(NetworkParameters networkParameters, Details details2, OkHttpClient okHttpClient) {
        Preconditions.checkArgument(details2.uri.getScheme().startsWith("http"));
        this.details = details2;
        this.params = networkParameters;
        this.client = okHttpClient;
    }

    public InetSocketAddress[] getPeers(long j, long j2, TimeUnit timeUnit) throws PeerDiscoveryException {
        GZIPInputStream gZIPInputStream;
        try {
            Builder newBuilder = HttpUrl.get(this.details.uri).newBuilder();
            if (j != 0) {
                newBuilder.addQueryParameter("srvmask", Long.toString(j));
            }
            Request.Builder builder = new Request.Builder();
            builder.url(newBuilder.build());
            builder.addHeader(HttpHeaders.USER_AGENT, VersionMessage.LIBRARY_SUBVER);
            log.info("Requesting seeds from {}", (Object) newBuilder);
            Response execute = this.client.newCall(builder.build()).execute();
            if (execute.isSuccessful()) {
                gZIPInputStream = new GZIPInputStream(execute.body().byteStream());
                SignedPeerSeeds parseDelimitedFrom = SignedPeerSeeds.parseDelimitedFrom(gZIPInputStream);
                gZIPInputStream.close();
                return protoToAddrs(parseDelimitedFrom);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("HTTP request failed: ");
            sb.append(execute.code());
            sb.append(" ");
            sb.append(execute.message());
            throw new PeerDiscoveryException(sb.toString());
        } catch (PeerDiscoveryException e) {
            throw e;
        } catch (Exception e2) {
            throw new PeerDiscoveryException((Throwable) e2);
        } catch (Throwable th) {
            gZIPInputStream.close();
            throw th;
        }
    }

    @VisibleForTesting
    public InetSocketAddress[] protoToAddrs(SignedPeerSeeds signedPeerSeeds) throws PeerDiscoveryException, InvalidProtocolBufferException, SignatureException {
        if (this.details.pubkey != null) {
            if (Arrays.equals(signedPeerSeeds.getPubkey().toByteArray(), this.details.pubkey.getPubKey())) {
                this.details.pubkey.verifyOrThrow(Sha256Hash.hash(signedPeerSeeds.getPeerSeeds().toByteArray()), signedPeerSeeds.getSignature().toByteArray());
            } else {
                throw new PeerDiscoveryException("Public key mismatch");
            }
        }
        PeerSeeds parseFrom = PeerSeeds.parseFrom(signedPeerSeeds.getPeerSeeds());
        if (parseFrom.getTimestamp() < C3447Utils.currentTimeSeconds() - 86400) {
            throw new PeerDiscoveryException("Seed data is more than one day old: replay attack?");
        } else if (parseFrom.getNet().equals(this.params.getPaymentProtocolId())) {
            InetSocketAddress[] inetSocketAddressArr = new InetSocketAddress[parseFrom.getSeedCount()];
            int i = 0;
            for (PeerSeedData peerSeedData : parseFrom.getSeedList()) {
                int i2 = i + 1;
                inetSocketAddressArr[i] = new InetSocketAddress(peerSeedData.getIpAddress(), peerSeedData.getPort());
                i = i2;
            }
            return inetSocketAddressArr;
        } else {
            throw new PeerDiscoveryException("Network mismatch");
        }
    }
}
