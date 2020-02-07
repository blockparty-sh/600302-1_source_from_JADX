package com.bitcoin.mwallet.core.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.webkit.URLUtil;
import androidx.core.app.FrameMetricsAggregator;
import com.bitcoin.bitcoink.Network;
import com.bitcoin.bitcoink.address.Address;
import com.bitcoin.bitcoink.address.Address.Companion;
import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.mwallet.core.models.address.AddressAndOriginalText;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.facebook.stetho.common.Utf8Charset;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchGroup;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 52\u00020\u0001:\u00015B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004Bk\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0002\u0010\u0014J\b\u00100\u001a\u000201H\u0016J\u0018\u00102\u001a\u0002032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00104\u001a\u000201H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010\u0010\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010%\"\u0004\b&\u0010'R\u001a\u0010\r\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010%\"\u0004\b(\u0010'R\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010%\"\u0004\b)\u0010'R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\"\"\u0004\b/\u0010$¨\u00066"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "address", "Lcom/bitcoin/mwallet/core/models/address/AddressAndOriginalText;", "amount", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "isValid", "", "isSlp", "tokenId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "isBip70", "url", "", "domain", "(Lcom/bitcoin/mwallet/core/models/address/AddressAndOriginalText;Lcom/bitcoin/bitcoink/tx/Satoshis;Lcom/bitcoin/mwallet/core/models/Coin;ZZLcom/bitcoin/mwallet/core/models/slp/SlpId;ZLjava/lang/String;Ljava/lang/String;)V", "getAddress", "()Lcom/bitcoin/mwallet/core/models/address/AddressAndOriginalText;", "setAddress", "(Lcom/bitcoin/mwallet/core/models/address/AddressAndOriginalText;)V", "getAmount", "()Lcom/bitcoin/bitcoink/tx/Satoshis;", "setAmount", "(Lcom/bitcoin/bitcoink/tx/Satoshis;)V", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "setCoin", "(Lcom/bitcoin/mwallet/core/models/Coin;)V", "getDomain", "()Ljava/lang/String;", "setDomain", "(Ljava/lang/String;)V", "()Z", "setBip70", "(Z)V", "setSlp", "setValid", "getTokenId", "()Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "setTokenId", "(Lcom/bitcoin/mwallet/core/models/slp/SlpId;)V", "getUrl", "setUrl", "describeContents", "", "writeToParcel", "", "flags", "CREATOR", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: BitcoinUriContent.kt */
public final class BitcoinUriContent implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    @NotNull
    public static final String PREFIX_BITCOIN = "bitcoin:";
    @NotNull
    public static final String PREFIX_BITCOIN_CASH = "bitcoincash:";
    private static final String PREFIX_SLP = "simpleledger:";
    private static final String QUERY_KEY_AMOUNT = "amount";
    /* access modifiers changed from: private */
    public static final Regex bitcoinCashPrefixPattern = new Regex("^bitcoin-?cash ?$");
    /* access modifiers changed from: private */
    public static final Regex bitcoinCorePrefixPattern = new Regex("^bitcoin ?$");
    /* access modifiers changed from: private */
    public static final Regex colonSplitPattern = new Regex("^([\\w-]*):?(.*)$");
    /* access modifiers changed from: private */
    public static final Regex goCryptoIdPattern = new Regex("gocrypto_id=?(\\d+)");
    /* access modifiers changed from: private */
    public static final List<Regex> goCryptoPatternList = CollectionsKt.listOf(new Regex("^https://(www\\.)?gocrypto\\.com/"), new Regex("^https://(www\\.)?elipay\\.com/"));
    /* access modifiers changed from: private */
    public static final Regex questionMarkSplitPattern = new Regex("^([^\\?]*)\\??([^\\?]*)$");
    /* access modifiers changed from: private */
    public static final Regex slpPrefixPattern = new Regex("^simpleledger ?$");
    @Nullable
    private AddressAndOriginalText address;
    @Nullable
    private Satoshis amount;
    @Nullable
    private Coin coin;
    @Nullable
    private String domain;
    private boolean isBip70;
    private boolean isSlp;
    private boolean isValid;
    @Nullable
    private SlpId tokenId;
    @Nullable
    private String url;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J\u0010\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u0005H\u0002J\u0010\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u0005H\u0002J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J\u001d\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016¢\u0006\u0002\u0010!J\u000e\u0010\"\u001a\u00020\u00022\u0006\u0010#\u001a\u00020\u0005J\u001c\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020\u00052\b\u0010'\u001a\u0004\u0018\u00010(H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/BitcoinUriContent$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "()V", "PREFIX_BITCOIN", "", "PREFIX_BITCOIN_CASH", "PREFIX_SLP", "QUERY_KEY_AMOUNT", "bitcoinCashPrefixPattern", "Lkotlin/text/Regex;", "bitcoinCorePrefixPattern", "colonSplitPattern", "goCryptoIdPattern", "goCryptoPatternList", "", "questionMarkSplitPattern", "slpPrefixPattern", "createFromParcel", "parcel", "Landroid/os/Parcel;", "isBitcoinCashPrefix", "", "prefix", "isBitcoinPrefix", "isGoCrypto", "input", "isGoCryptoId", "isSlpPrefix", "newArray", "", "size", "", "(I)[Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "parse", "uri", "parseAddress", "Lcom/bitcoin/bitcoink/address/Address;", "addressText", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: BitcoinUriContent.kt */
    public static final class CREATOR implements Creator<BitcoinUriContent> {

        @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Coin.values().length];

            static {
                $EnumSwitchMapping$0[Coin.BCH.ordinal()] = 1;
                $EnumSwitchMapping$0[Coin.BTC.ordinal()] = 2;
            }
        }

        private CREATOR() {
        }

        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final boolean isGoCrypto(String str) {
            boolean z;
            Iterable<Regex> access$getGoCryptoPatternList$cp = BitcoinUriContent.goCryptoPatternList;
            if ((access$getGoCryptoPatternList$cp instanceof Collection) && ((Collection) access$getGoCryptoPatternList$cp).isEmpty()) {
                return false;
            }
            for (Regex find$default : access$getGoCryptoPatternList$cp) {
                if (Regex.find$default(find$default, str, 0, 2, null) != null) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    return true;
                }
            }
            return false;
        }

        private final boolean isGoCryptoId(String str) {
            return Regex.find$default(BitcoinUriContent.goCryptoIdPattern, str, 0, 2, null) != null;
        }

        private final boolean isBitcoinCashPrefix(String str) {
            return BitcoinUriContent.bitcoinCashPrefixPattern.matches(str);
        }

        private final boolean isSlpPrefix(String str) {
            return BitcoinUriContent.slpPrefixPattern.matches(str);
        }

        private final boolean isBitcoinPrefix(String str) {
            return BitcoinUriContent.bitcoinCorePrefixPattern.matches(str);
        }

        @NotNull
        public final BitcoinUriContent parse(@NotNull String str) {
            String obj;
            String str2 = str;
            Intrinsics.checkParameterIsNotNull(str2, "uri");
            BitcoinUriContent bitcoinUriContent = new BitcoinUriContent(null, null, null, false, false, null, false, null, null, 503, null);
            CharSequence charSequence = str2;
            String obj2 = StringsKt.trim(charSequence).toString();
            CREATOR creator = this;
            if (creator.isGoCrypto(str2)) {
                bitcoinUriContent.setDomain(new URL(str2).getHost());
                bitcoinUriContent.setBip70(true);
                bitcoinUriContent.setValid(true);
                bitcoinUriContent.setCoin(Coin.BCH);
                bitcoinUriContent.setUrl(str2);
                return bitcoinUriContent;
            }
            String str3 = null;
            if (creator.isGoCryptoId(str2)) {
                MatchResult find$default = Regex.find$default(BitcoinUriContent.goCryptoIdPattern, charSequence, 0, 2, null);
                if (find$default != null) {
                    MatchGroup matchGroup = find$default.getGroups().get(1);
                    if (matchGroup != null) {
                        str3 = matchGroup.getValue();
                    }
                    bitcoinUriContent.setBip70(true);
                    bitcoinUriContent.setValid(true);
                    bitcoinUriContent.setCoin(Coin.BCH);
                    StringBuilder sb = new StringBuilder();
                    sb.append("https://www.gocrypto.com/sticker?gocrypto_id=");
                    sb.append(str3);
                    bitcoinUriContent.setUrl(sb.toString());
                    bitcoinUriContent.setDomain(new URL(bitcoinUriContent.getUrl()).getHost());
                    return bitcoinUriContent;
                }
            }
            Sequence findAll$default = Regex.findAll$default(BitcoinUriContent.colonSplitPattern, obj2, 0, 2, null);
            if (SequencesKt.count(findAll$default) == 0) {
                return bitcoinUriContent;
            }
            List groupValues = ((MatchResult) SequencesKt.first(findAll$default)).getGroupValues();
            if (groupValues.size() != 3) {
                return bitcoinUriContent;
            }
            String str4 = (String) groupValues.get(1);
            if (str4 != null) {
                String lowerCase = str4.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
                String str5 = "null cannot be cast to non-null type kotlin.CharSequence";
                if (creator.isBitcoinPrefix(lowerCase)) {
                    bitcoinUriContent.setCoin(Coin.BTC);
                    String str6 = (String) groupValues.get(2);
                    if (str6 != null) {
                        obj = StringsKt.trim((CharSequence) str6).toString();
                    } else {
                        throw new TypeCastException(str5);
                    }
                } else if (creator.isSlpPrefix(lowerCase)) {
                    bitcoinUriContent.setCoin(Coin.BCH);
                    String str7 = (String) groupValues.get(2);
                    if (str7 != null) {
                        obj = StringsKt.trim((CharSequence) str7).toString();
                        bitcoinUriContent.setSlp(true);
                    } else {
                        throw new TypeCastException(str5);
                    }
                } else if (creator.isBitcoinCashPrefix(lowerCase)) {
                    bitcoinUriContent.setCoin(Coin.BCH);
                    String str8 = (String) groupValues.get(2);
                    if (str8 != null) {
                        obj = StringsKt.trim((CharSequence) str8).toString();
                    } else {
                        throw new TypeCastException(str5);
                    }
                } else {
                    String str9 = (String) groupValues.get(2);
                    if (str9 != null) {
                        if (!(StringsKt.trim((CharSequence) str9).toString().length() == 0)) {
                            return bitcoinUriContent;
                        }
                        String str10 = (String) groupValues.get(1);
                        if (str10 != null) {
                            obj = StringsKt.trim((CharSequence) str10).toString();
                        } else {
                            throw new TypeCastException(str5);
                        }
                    } else {
                        throw new TypeCastException(str5);
                    }
                }
                String str11 = obj;
                MatchResult matchResult = (MatchResult) SequencesKt.firstOrNull(Regex.findAll$default(BitcoinUriContent.questionMarkSplitPattern, str11, 0, 2, null));
                List groupValues2 = matchResult != null ? matchResult.getGroupValues() : null;
                if (groupValues2 == null || groupValues2.size() != 3) {
                    return bitcoinUriContent;
                }
                String str12 = (String) CollectionsKt.getOrNull(groupValues2, 1);
                if (str12 == null) {
                    return bitcoinUriContent;
                }
                for (CharSequence split : StringsKt.split$default((CharSequence) (String) groupValues2.get(2), new String[]{"&"}, false, 0, 6, (Object) null)) {
                    List split2 = new Regex("=").split(split, 2);
                    if (split2.size() == 2) {
                        String str13 = (String) split2.get(0);
                        String decode = URLDecoder.decode((String) split2.get(1), Utf8Charset.NAME);
                        Intrinsics.checkExpressionValueIsNotNull(decode, "URLDecoder.decode(value, \"UTF-8\")");
                        if (str13.hashCode() == -1413853096 && str13.equals("amount")) {
                            try {
                                bitcoinUriContent.setAmount(Satoshis.Companion.fromCoins(new BigDecimal(decode)));
                            } catch (NumberFormatException unused) {
                                return bitcoinUriContent;
                            }
                        }
                    }
                }
                String replace$default = StringsKt.replace$default(str11, "?r=", "", false, 4, (Object) null);
                if (URLUtil.isValidUrl(replace$default)) {
                    bitcoinUriContent.setDomain(new URL(replace$default).getHost());
                    bitcoinUriContent.setValid(true);
                    bitcoinUriContent.setBip70(true);
                    bitcoinUriContent.setUrl(str2);
                    return bitcoinUriContent;
                }
                Address parseAddress = creator.parseAddress(str12, bitcoinUriContent.getCoin());
                if (parseAddress == null && bitcoinUriContent.getCoin() == null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(BitcoinUriContent.PREFIX_BITCOIN_CASH);
                    sb2.append(str12);
                    parseAddress = creator.parseAddress(sb2.toString(), null);
                    if (parseAddress != null) {
                        bitcoinUriContent.setCoin(Coin.BCH);
                    }
                }
                if (parseAddress != null) {
                    bitcoinUriContent.setValid(true);
                    bitcoinUriContent.setAddress(new AddressAndOriginalText(parseAddress, str12));
                }
                StringBuilder sb3 = new StringBuilder();
                sb3.append("BitcoinUriContent.parse() address: ");
                sb3.append(bitcoinUriContent.getAddress());
                Timber.m426d(sb3.toString(), new Object[0]);
                StringBuilder sb4 = new StringBuilder();
                sb4.append("BitcoinUriContent.parse() coin:    ");
                sb4.append(bitcoinUriContent.getCoin());
                Timber.m426d(sb4.toString(), new Object[0]);
                StringBuilder sb5 = new StringBuilder();
                sb5.append("BitcoinUriContent.parse() amount:  ");
                sb5.append(bitcoinUriContent.getAmount());
                Timber.m426d(sb5.toString(), new Object[0]);
                return bitcoinUriContent;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }

        private final Address parseAddress(String str, Coin coin) {
            if (coin != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[coin.ordinal()];
                if (i == 1) {
                    Companion companion = Address.Companion;
                    Network network = Network.MAIN;
                    StringBuilder sb = new StringBuilder();
                    sb.append(BitcoinUriContent.PREFIX_BITCOIN_CASH);
                    sb.append(str);
                    Address tryParse = companion.tryParse(network, sb.toString());
                    if (tryParse == null) {
                        tryParse = Address.Companion.tryParse(Network.MAIN, str);
                    }
                    if (tryParse == null) {
                        Companion companion2 = Address.Companion;
                        Network network2 = Network.MAIN;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(BitcoinUriContent.PREFIX_SLP);
                        sb2.append(str);
                        tryParse = companion2.tryParse(network2, sb2.toString());
                    }
                    return tryParse != null ? tryParse : Address.Companion.tryParse(Network.MAIN, str);
                } else if (i == 2) {
                    Companion companion3 = Address.Companion;
                    Network network3 = Network.MAIN;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(BitcoinUriContent.PREFIX_BITCOIN);
                    sb3.append(str);
                    return companion3.tryParse(network3, sb3.toString());
                }
            }
            return Address.Companion.tryParse(Network.MAIN, str);
        }

        @NotNull
        public BitcoinUriContent createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            return new BitcoinUriContent(parcel);
        }

        @NotNull
        public BitcoinUriContent[] newArray(int i) {
            return new BitcoinUriContent[i];
        }
    }

    public BitcoinUriContent() {
        this(null, null, null, false, false, null, false, null, null, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    public int describeContents() {
        return 0;
    }

    public BitcoinUriContent(@Nullable AddressAndOriginalText addressAndOriginalText, @Nullable Satoshis satoshis, @Nullable Coin coin2, boolean z, boolean z2, @Nullable SlpId slpId, boolean z3, @Nullable String str, @Nullable String str2) {
        this.address = addressAndOriginalText;
        this.amount = satoshis;
        this.coin = coin2;
        this.isValid = z;
        this.isSlp = z2;
        this.tokenId = slpId;
        this.isBip70 = z3;
        this.url = str;
        this.domain = str2;
    }

    public /* synthetic */ BitcoinUriContent(AddressAndOriginalText addressAndOriginalText, Satoshis satoshis, Coin coin2, boolean z, boolean z2, SlpId slpId, boolean z3, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        int i2 = i;
        AddressAndOriginalText addressAndOriginalText2 = (i2 & 1) != 0 ? null : addressAndOriginalText;
        Satoshis satoshis2 = (i2 & 2) != 0 ? null : satoshis;
        Coin coin3 = (i2 & 4) != 0 ? null : coin2;
        boolean z4 = false;
        boolean z5 = (i2 & 8) != 0 ? false : z;
        boolean z6 = (i2 & 16) != 0 ? false : z2;
        SlpId slpId2 = (i2 & 32) != 0 ? null : slpId;
        if ((i2 & 64) == 0) {
            z4 = z3;
        }
        this(addressAndOriginalText2, satoshis2, coin3, z5, z6, slpId2, z4, (i2 & 128) != 0 ? null : str, (i2 & 256) != 0 ? null : str2);
    }

    @Nullable
    public final AddressAndOriginalText getAddress() {
        return this.address;
    }

    public final void setAddress(@Nullable AddressAndOriginalText addressAndOriginalText) {
        this.address = addressAndOriginalText;
    }

    @Nullable
    public final Satoshis getAmount() {
        return this.amount;
    }

    public final void setAmount(@Nullable Satoshis satoshis) {
        this.amount = satoshis;
    }

    @Nullable
    public final Coin getCoin() {
        return this.coin;
    }

    public final void setCoin(@Nullable Coin coin2) {
        this.coin = coin2;
    }

    public final boolean isValid() {
        return this.isValid;
    }

    public final void setValid(boolean z) {
        this.isValid = z;
    }

    public final boolean isSlp() {
        return this.isSlp;
    }

    public final void setSlp(boolean z) {
        this.isSlp = z;
    }

    @Nullable
    public final SlpId getTokenId() {
        return this.tokenId;
    }

    public final void setTokenId(@Nullable SlpId slpId) {
        this.tokenId = slpId;
    }

    public final boolean isBip70() {
        return this.isBip70;
    }

    public final void setBip70(boolean z) {
        this.isBip70 = z;
    }

    @Nullable
    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(@Nullable String str) {
        this.url = str;
    }

    @Nullable
    public final String getDomain() {
        return this.domain;
    }

    public final void setDomain(@Nullable String str) {
        this.domain = str;
    }

    public BitcoinUriContent(@NotNull Parcel parcel) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        this((AddressAndOriginalText) parcel.readParcelable(AddressAndOriginalText.class.getClassLoader()), new Satoshis(parcel.readLong()), (Coin) parcel.readParcelable(Coin.class.getClassLoader()), parcel.readByte() != ((byte) 0), false, null, false, null, null, 496, null);
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeParcelable(this.address, i);
        Satoshis satoshis = this.amount;
        parcel.writeLong(satoshis != null ? satoshis.getSatoshis() : 0);
        parcel.writeParcelable(this.coin, i);
        parcel.writeByte(this.isValid ? (byte) 1 : 0);
        parcel.writeByte(this.isSlp ? (byte) 1 : 0);
    }
}
