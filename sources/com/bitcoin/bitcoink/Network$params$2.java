package com.bitcoin.bitcoink;

import com.bitcoin.bitcoink.address.MainNetParamsFork;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.bitcoinj.params.AbstractBitcoinNetParams;
import org.bitcoinj.params.TestNet3Params;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, mo37405d2 = {"<anonymous>", "Lorg/bitcoinj/params/AbstractBitcoinNetParams;", "kotlin.jvm.PlatformType", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Network.kt */
final class Network$params$2 extends Lambda implements Function0<AbstractBitcoinNetParams> {
    final /* synthetic */ Network this$0;

    Network$params$2(Network network) {
        this.this$0 = network;
        super(0);
    }

    public final AbstractBitcoinNetParams invoke() {
        return this.this$0 == Network.MAIN ? MainNetParamsFork.get() : TestNet3Params.get();
    }
}
