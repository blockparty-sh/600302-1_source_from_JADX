package org.bitcoinj.wallet;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.script.Script;

public class RedeemData {
    public final List<ECKey> keys;
    public final Script redeemScript;

    private RedeemData(List<ECKey> list, Script script) {
        this.redeemScript = script;
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, ECKey.PUBKEY_COMPARATOR);
        this.keys = arrayList;
    }

    /* renamed from: of */
    public static RedeemData m343of(List<ECKey> list, Script script) {
        return new RedeemData(list, script);
    }

    /* renamed from: of */
    public static RedeemData m344of(ECKey eCKey, Script script) {
        Preconditions.checkArgument(script.isSentToAddress() || script.isSentToRawPubKey());
        if (eCKey != null) {
            return new RedeemData(Collections.singletonList(eCKey), script);
        }
        return null;
    }

    public ECKey getFullKey() {
        for (ECKey eCKey : this.keys) {
            if (eCKey.hasPrivKey()) {
                return eCKey;
            }
        }
        return null;
    }
}
