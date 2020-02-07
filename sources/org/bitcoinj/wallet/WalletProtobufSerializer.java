package org.bitcoinj.wallet;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.TextFormat;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.PeerAddress;
import org.bitcoinj.core.ScriptException;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionConfidence.ConfidenceType;
import org.bitcoinj.core.TransactionConfidence.Source;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.core.TransactionOutPoint;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.crypto.KeyCrypter;
import org.bitcoinj.crypto.KeyCrypterScrypt;
import org.bitcoinj.script.Script;
import org.bitcoinj.signers.LocalTransactionSigner;
import org.bitcoinj.signers.TransactionSigner;
import org.bitcoinj.utils.ExchangeRate;
import org.bitcoinj.utils.Fiat;
import org.bitcoinj.wallet.Protos.C3527Wallet;
import org.bitcoinj.wallet.Protos.C3527Wallet.Builder;
import org.bitcoinj.wallet.Protos.C3527Wallet.EncryptionType;
import org.bitcoinj.wallet.Protos.Extension;
import org.bitcoinj.wallet.Protos.Tag;
import org.bitcoinj.wallet.Protos.Transaction.Purpose;
import org.bitcoinj.wallet.Protos.TransactionConfidence;
import org.bitcoinj.wallet.Protos.TransactionConfidence.C3522Type;
import org.bitcoinj.wallet.UnreadableWalletException.FutureVersion;
import org.bitcoinj.wallet.UnreadableWalletException.WrongNetwork;
import org.bitcoinj.wallet.WalletTransaction.Pool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WalletProtobufSerializer {
    public static final int CURRENT_WALLET_VERSION = C3527Wallet.getDefaultInstance().getVersion();
    private static final int WALLET_SIZE_LIMIT = 536870912;
    private static final Logger log = LoggerFactory.getLogger(WalletProtobufSerializer.class);
    private final WalletFactory factory;
    private KeyChainFactory keyChainFactory;
    private boolean requireMandatoryExtensions;
    protected Map<ByteString, Transaction> txMap;

    /* renamed from: org.bitcoinj.wallet.WalletProtobufSerializer$2 */
    static /* synthetic */ class C35442 {
        static final /* synthetic */ int[] $SwitchMap$org$bitcoinj$core$TransactionConfidence$Source = new int[Source.values().length];

        /* renamed from: $SwitchMap$org$bitcoinj$wallet$Protos$TransactionConfidence$Source */
        static final /* synthetic */ int[] f832xbf0af79d = new int[TransactionConfidence.Source.values().length];
        static final /* synthetic */ int[] $SwitchMap$org$bitcoinj$wallet$Protos$TransactionConfidence$Type = new int[C3522Type.values().length];
        static final /* synthetic */ int[] $SwitchMap$org$bitcoinj$wallet$WalletTransaction$Pool = new int[Pool.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(76:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|35|36|37|38|39|40|41|42|(2:43|44)|45|47|48|49|50|51|52|53|54|55|56|(2:57|58)|59|61|62|63|65|66|67|68|69|70|71|73|74|75|76|77|78|(2:79|80)|81|83|84|85|86|87|88|89|90|91|92|93|94|95|96|98) */
        /* JADX WARNING: Can't wrap try/catch for region: R(78:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|35|36|37|38|39|40|41|42|43|44|45|47|48|49|50|51|52|53|54|55|56|57|58|59|61|62|63|65|66|67|68|69|70|71|73|74|75|76|77|78|(2:79|80)|81|83|84|85|86|87|88|89|90|91|92|93|94|95|96|98) */
        /* JADX WARNING: Can't wrap try/catch for region: R(79:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|29|30|31|33|34|35|36|37|38|39|40|41|42|43|44|45|47|48|49|50|51|52|53|54|55|56|57|58|59|61|62|63|65|66|67|68|69|70|71|73|74|75|76|77|78|(2:79|80)|81|83|84|85|86|87|88|89|90|91|92|93|94|95|96|98) */
        /* JADX WARNING: Can't wrap try/catch for region: R(80:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|29|30|31|33|34|35|36|37|38|39|40|41|42|43|44|45|47|48|49|50|51|52|53|54|55|56|57|58|59|61|62|63|65|66|67|68|69|70|71|73|74|75|76|77|78|(2:79|80)|81|83|84|85|86|87|88|89|90|91|92|93|94|95|96|98) */
        /* JADX WARNING: Can't wrap try/catch for region: R(81:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|(2:17|18)|19|(2:21|22)|23|25|26|27|29|30|31|33|34|35|36|37|38|39|40|41|42|43|44|45|47|48|49|50|51|52|53|54|55|56|57|58|59|61|62|63|65|66|67|68|69|70|71|73|74|75|76|77|78|(2:79|80)|81|83|84|85|86|87|88|89|90|91|92|93|94|95|96|98) */
        /* JADX WARNING: Can't wrap try/catch for region: R(83:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|16|(2:17|18)|19|(2:21|22)|23|25|26|27|29|30|31|33|34|35|36|37|38|39|40|41|42|43|44|45|47|48|49|50|51|52|53|54|55|56|57|58|59|61|62|63|65|66|67|68|69|70|71|73|74|75|76|77|78|79|80|81|83|84|85|86|87|88|89|90|91|92|93|94|95|96|98) */
        /* JADX WARNING: Can't wrap try/catch for region: R(84:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|16|(2:17|18)|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|38|39|40|41|42|43|44|45|47|48|49|50|51|52|53|54|55|56|57|58|59|61|62|63|65|66|67|68|69|70|71|73|74|75|76|77|78|79|80|81|83|84|85|86|87|88|89|90|91|92|93|94|95|96|98) */
        /* JADX WARNING: Can't wrap try/catch for region: R(85:0|1|2|3|5|6|7|9|10|11|13|14|15|16|(2:17|18)|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|38|39|40|41|42|43|44|45|47|48|49|50|51|52|53|54|55|56|57|58|59|61|62|63|65|66|67|68|69|70|71|73|74|75|76|77|78|79|80|81|83|84|85|86|87|88|89|90|91|92|93|94|95|96|98) */
        /* JADX WARNING: Can't wrap try/catch for region: R(86:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|38|39|40|41|42|43|44|45|47|48|49|50|51|52|53|54|55|56|57|58|59|61|62|63|65|66|67|68|69|70|71|73|74|75|76|77|78|79|80|81|83|84|85|86|87|88|89|90|91|92|93|94|95|96|98) */
        /* JADX WARNING: Code restructure failed: missing block: B:99:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0047 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0085 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x008f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x0099 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00a3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00ad */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00ca */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x00d4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x00de */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x00e8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x00f2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:67:0x011a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:69:0x0124 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:75:0x0141 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:77:0x014b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:79:0x0155 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:85:0x0172 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:87:0x017c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:89:0x0186 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:91:0x0190 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:93:0x019a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:95:0x01a4 */
        static {
            /*
                org.bitcoinj.wallet.Protos$TransactionConfidence$Source[] r0 = org.bitcoinj.wallet.Protos.TransactionConfidence.Source.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f832xbf0af79d = r0
                r0 = 1
                int[] r1 = f832xbf0af79d     // Catch:{ NoSuchFieldError -> 0x0014 }
                org.bitcoinj.wallet.Protos$TransactionConfidence$Source r2 = org.bitcoinj.wallet.Protos.TransactionConfidence.Source.SOURCE_SELF     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = f832xbf0af79d     // Catch:{ NoSuchFieldError -> 0x001f }
                org.bitcoinj.wallet.Protos$TransactionConfidence$Source r3 = org.bitcoinj.wallet.Protos.TransactionConfidence.Source.SOURCE_NETWORK     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = f832xbf0af79d     // Catch:{ NoSuchFieldError -> 0x002a }
                org.bitcoinj.wallet.Protos$TransactionConfidence$Source r4 = org.bitcoinj.wallet.Protos.TransactionConfidence.Source.SOURCE_UNKNOWN     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                org.bitcoinj.wallet.Protos$TransactionConfidence$Type[] r3 = org.bitcoinj.wallet.Protos.TransactionConfidence.C3522Type.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$org$bitcoinj$wallet$Protos$TransactionConfidence$Type = r3
                int[] r3 = $SwitchMap$org$bitcoinj$wallet$Protos$TransactionConfidence$Type     // Catch:{ NoSuchFieldError -> 0x003d }
                org.bitcoinj.wallet.Protos$TransactionConfidence$Type r4 = org.bitcoinj.wallet.Protos.TransactionConfidence.C3522Type.BUILDING     // Catch:{ NoSuchFieldError -> 0x003d }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                int[] r3 = $SwitchMap$org$bitcoinj$wallet$Protos$TransactionConfidence$Type     // Catch:{ NoSuchFieldError -> 0x0047 }
                org.bitcoinj.wallet.Protos$TransactionConfidence$Type r4 = org.bitcoinj.wallet.Protos.TransactionConfidence.C3522Type.DEAD     // Catch:{ NoSuchFieldError -> 0x0047 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0047 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0047 }
            L_0x0047:
                int[] r3 = $SwitchMap$org$bitcoinj$wallet$Protos$TransactionConfidence$Type     // Catch:{ NoSuchFieldError -> 0x0051 }
                org.bitcoinj.wallet.Protos$TransactionConfidence$Type r4 = org.bitcoinj.wallet.Protos.TransactionConfidence.C3522Type.NOT_IN_BEST_CHAIN     // Catch:{ NoSuchFieldError -> 0x0051 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0051 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0051 }
            L_0x0051:
                r3 = 4
                int[] r4 = $SwitchMap$org$bitcoinj$wallet$Protos$TransactionConfidence$Type     // Catch:{ NoSuchFieldError -> 0x005c }
                org.bitcoinj.wallet.Protos$TransactionConfidence$Type r5 = org.bitcoinj.wallet.Protos.TransactionConfidence.C3522Type.PENDING     // Catch:{ NoSuchFieldError -> 0x005c }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x005c }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x005c }
            L_0x005c:
                r4 = 5
                int[] r5 = $SwitchMap$org$bitcoinj$wallet$Protos$TransactionConfidence$Type     // Catch:{ NoSuchFieldError -> 0x0067 }
                org.bitcoinj.wallet.Protos$TransactionConfidence$Type r6 = org.bitcoinj.wallet.Protos.TransactionConfidence.C3522Type.IN_CONFLICT     // Catch:{ NoSuchFieldError -> 0x0067 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0067 }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x0067 }
            L_0x0067:
                r5 = 6
                int[] r6 = $SwitchMap$org$bitcoinj$wallet$Protos$TransactionConfidence$Type     // Catch:{ NoSuchFieldError -> 0x0072 }
                org.bitcoinj.wallet.Protos$TransactionConfidence$Type r7 = org.bitcoinj.wallet.Protos.TransactionConfidence.C3522Type.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0072 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0072 }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x0072 }
            L_0x0072:
                org.bitcoinj.wallet.Protos$Transaction$Pool[] r6 = org.bitcoinj.wallet.Protos.Transaction.Pool.values()
                int r6 = r6.length
                int[] r6 = new int[r6]
                $SwitchMap$org$bitcoinj$wallet$Protos$Transaction$Pool = r6
                int[] r6 = $SwitchMap$org$bitcoinj$wallet$Protos$Transaction$Pool     // Catch:{ NoSuchFieldError -> 0x0085 }
                org.bitcoinj.wallet.Protos$Transaction$Pool r7 = org.bitcoinj.wallet.Protos.Transaction.Pool.DEAD     // Catch:{ NoSuchFieldError -> 0x0085 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0085 }
                r6[r7] = r0     // Catch:{ NoSuchFieldError -> 0x0085 }
            L_0x0085:
                int[] r6 = $SwitchMap$org$bitcoinj$wallet$Protos$Transaction$Pool     // Catch:{ NoSuchFieldError -> 0x008f }
                org.bitcoinj.wallet.Protos$Transaction$Pool r7 = org.bitcoinj.wallet.Protos.Transaction.Pool.PENDING     // Catch:{ NoSuchFieldError -> 0x008f }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x008f }
                r6[r7] = r1     // Catch:{ NoSuchFieldError -> 0x008f }
            L_0x008f:
                int[] r6 = $SwitchMap$org$bitcoinj$wallet$Protos$Transaction$Pool     // Catch:{ NoSuchFieldError -> 0x0099 }
                org.bitcoinj.wallet.Protos$Transaction$Pool r7 = org.bitcoinj.wallet.Protos.Transaction.Pool.SPENT     // Catch:{ NoSuchFieldError -> 0x0099 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0099 }
                r6[r7] = r2     // Catch:{ NoSuchFieldError -> 0x0099 }
            L_0x0099:
                int[] r6 = $SwitchMap$org$bitcoinj$wallet$Protos$Transaction$Pool     // Catch:{ NoSuchFieldError -> 0x00a3 }
                org.bitcoinj.wallet.Protos$Transaction$Pool r7 = org.bitcoinj.wallet.Protos.Transaction.Pool.UNSPENT     // Catch:{ NoSuchFieldError -> 0x00a3 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a3 }
                r6[r7] = r3     // Catch:{ NoSuchFieldError -> 0x00a3 }
            L_0x00a3:
                int[] r6 = $SwitchMap$org$bitcoinj$wallet$Protos$Transaction$Pool     // Catch:{ NoSuchFieldError -> 0x00ad }
                org.bitcoinj.wallet.Protos$Transaction$Pool r7 = org.bitcoinj.wallet.Protos.Transaction.Pool.INACTIVE     // Catch:{ NoSuchFieldError -> 0x00ad }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ad }
                r6[r7] = r4     // Catch:{ NoSuchFieldError -> 0x00ad }
            L_0x00ad:
                int[] r6 = $SwitchMap$org$bitcoinj$wallet$Protos$Transaction$Pool     // Catch:{ NoSuchFieldError -> 0x00b7 }
                org.bitcoinj.wallet.Protos$Transaction$Pool r7 = org.bitcoinj.wallet.Protos.Transaction.Pool.PENDING_INACTIVE     // Catch:{ NoSuchFieldError -> 0x00b7 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b7 }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x00b7 }
            L_0x00b7:
                org.bitcoinj.wallet.Protos$Transaction$Purpose[] r6 = org.bitcoinj.wallet.Protos.Transaction.Purpose.values()
                int r6 = r6.length
                int[] r6 = new int[r6]
                $SwitchMap$org$bitcoinj$wallet$Protos$Transaction$Purpose = r6
                int[] r6 = $SwitchMap$org$bitcoinj$wallet$Protos$Transaction$Purpose     // Catch:{ NoSuchFieldError -> 0x00ca }
                org.bitcoinj.wallet.Protos$Transaction$Purpose r7 = org.bitcoinj.wallet.Protos.Transaction.Purpose.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x00ca }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ca }
                r6[r7] = r0     // Catch:{ NoSuchFieldError -> 0x00ca }
            L_0x00ca:
                int[] r6 = $SwitchMap$org$bitcoinj$wallet$Protos$Transaction$Purpose     // Catch:{ NoSuchFieldError -> 0x00d4 }
                org.bitcoinj.wallet.Protos$Transaction$Purpose r7 = org.bitcoinj.wallet.Protos.Transaction.Purpose.USER_PAYMENT     // Catch:{ NoSuchFieldError -> 0x00d4 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d4 }
                r6[r7] = r1     // Catch:{ NoSuchFieldError -> 0x00d4 }
            L_0x00d4:
                int[] r6 = $SwitchMap$org$bitcoinj$wallet$Protos$Transaction$Purpose     // Catch:{ NoSuchFieldError -> 0x00de }
                org.bitcoinj.wallet.Protos$Transaction$Purpose r7 = org.bitcoinj.wallet.Protos.Transaction.Purpose.KEY_ROTATION     // Catch:{ NoSuchFieldError -> 0x00de }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00de }
                r6[r7] = r2     // Catch:{ NoSuchFieldError -> 0x00de }
            L_0x00de:
                int[] r6 = $SwitchMap$org$bitcoinj$wallet$Protos$Transaction$Purpose     // Catch:{ NoSuchFieldError -> 0x00e8 }
                org.bitcoinj.wallet.Protos$Transaction$Purpose r7 = org.bitcoinj.wallet.Protos.Transaction.Purpose.ASSURANCE_CONTRACT_CLAIM     // Catch:{ NoSuchFieldError -> 0x00e8 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e8 }
                r6[r7] = r3     // Catch:{ NoSuchFieldError -> 0x00e8 }
            L_0x00e8:
                int[] r6 = $SwitchMap$org$bitcoinj$wallet$Protos$Transaction$Purpose     // Catch:{ NoSuchFieldError -> 0x00f2 }
                org.bitcoinj.wallet.Protos$Transaction$Purpose r7 = org.bitcoinj.wallet.Protos.Transaction.Purpose.ASSURANCE_CONTRACT_PLEDGE     // Catch:{ NoSuchFieldError -> 0x00f2 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00f2 }
                r6[r7] = r4     // Catch:{ NoSuchFieldError -> 0x00f2 }
            L_0x00f2:
                int[] r6 = $SwitchMap$org$bitcoinj$wallet$Protos$Transaction$Purpose     // Catch:{ NoSuchFieldError -> 0x00fc }
                org.bitcoinj.wallet.Protos$Transaction$Purpose r7 = org.bitcoinj.wallet.Protos.Transaction.Purpose.ASSURANCE_CONTRACT_STUB     // Catch:{ NoSuchFieldError -> 0x00fc }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00fc }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x00fc }
            L_0x00fc:
                r6 = 7
                int[] r7 = $SwitchMap$org$bitcoinj$wallet$Protos$Transaction$Purpose     // Catch:{ NoSuchFieldError -> 0x0107 }
                org.bitcoinj.wallet.Protos$Transaction$Purpose r8 = org.bitcoinj.wallet.Protos.Transaction.Purpose.RAISE_FEE     // Catch:{ NoSuchFieldError -> 0x0107 }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x0107 }
                r7[r8] = r6     // Catch:{ NoSuchFieldError -> 0x0107 }
            L_0x0107:
                org.bitcoinj.core.TransactionConfidence$Source[] r7 = org.bitcoinj.core.TransactionConfidence.Source.values()
                int r7 = r7.length
                int[] r7 = new int[r7]
                $SwitchMap$org$bitcoinj$core$TransactionConfidence$Source = r7
                int[] r7 = $SwitchMap$org$bitcoinj$core$TransactionConfidence$Source     // Catch:{ NoSuchFieldError -> 0x011a }
                org.bitcoinj.core.TransactionConfidence$Source r8 = org.bitcoinj.core.TransactionConfidence.Source.SELF     // Catch:{ NoSuchFieldError -> 0x011a }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x011a }
                r7[r8] = r0     // Catch:{ NoSuchFieldError -> 0x011a }
            L_0x011a:
                int[] r7 = $SwitchMap$org$bitcoinj$core$TransactionConfidence$Source     // Catch:{ NoSuchFieldError -> 0x0124 }
                org.bitcoinj.core.TransactionConfidence$Source r8 = org.bitcoinj.core.TransactionConfidence.Source.NETWORK     // Catch:{ NoSuchFieldError -> 0x0124 }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x0124 }
                r7[r8] = r1     // Catch:{ NoSuchFieldError -> 0x0124 }
            L_0x0124:
                int[] r7 = $SwitchMap$org$bitcoinj$core$TransactionConfidence$Source     // Catch:{ NoSuchFieldError -> 0x012e }
                org.bitcoinj.core.TransactionConfidence$Source r8 = org.bitcoinj.core.TransactionConfidence.Source.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x012e }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x012e }
                r7[r8] = r2     // Catch:{ NoSuchFieldError -> 0x012e }
            L_0x012e:
                org.bitcoinj.wallet.WalletTransaction$Pool[] r7 = org.bitcoinj.wallet.WalletTransaction.Pool.values()
                int r7 = r7.length
                int[] r7 = new int[r7]
                $SwitchMap$org$bitcoinj$wallet$WalletTransaction$Pool = r7
                int[] r7 = $SwitchMap$org$bitcoinj$wallet$WalletTransaction$Pool     // Catch:{ NoSuchFieldError -> 0x0141 }
                org.bitcoinj.wallet.WalletTransaction$Pool r8 = org.bitcoinj.wallet.WalletTransaction.Pool.UNSPENT     // Catch:{ NoSuchFieldError -> 0x0141 }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x0141 }
                r7[r8] = r0     // Catch:{ NoSuchFieldError -> 0x0141 }
            L_0x0141:
                int[] r7 = $SwitchMap$org$bitcoinj$wallet$WalletTransaction$Pool     // Catch:{ NoSuchFieldError -> 0x014b }
                org.bitcoinj.wallet.WalletTransaction$Pool r8 = org.bitcoinj.wallet.WalletTransaction.Pool.SPENT     // Catch:{ NoSuchFieldError -> 0x014b }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x014b }
                r7[r8] = r1     // Catch:{ NoSuchFieldError -> 0x014b }
            L_0x014b:
                int[] r7 = $SwitchMap$org$bitcoinj$wallet$WalletTransaction$Pool     // Catch:{ NoSuchFieldError -> 0x0155 }
                org.bitcoinj.wallet.WalletTransaction$Pool r8 = org.bitcoinj.wallet.WalletTransaction.Pool.DEAD     // Catch:{ NoSuchFieldError -> 0x0155 }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x0155 }
                r7[r8] = r2     // Catch:{ NoSuchFieldError -> 0x0155 }
            L_0x0155:
                int[] r7 = $SwitchMap$org$bitcoinj$wallet$WalletTransaction$Pool     // Catch:{ NoSuchFieldError -> 0x015f }
                org.bitcoinj.wallet.WalletTransaction$Pool r8 = org.bitcoinj.wallet.WalletTransaction.Pool.PENDING     // Catch:{ NoSuchFieldError -> 0x015f }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x015f }
                r7[r8] = r3     // Catch:{ NoSuchFieldError -> 0x015f }
            L_0x015f:
                org.bitcoinj.core.Transaction$Purpose[] r7 = org.bitcoinj.core.Transaction.Purpose.values()
                int r7 = r7.length
                int[] r7 = new int[r7]
                $SwitchMap$org$bitcoinj$core$Transaction$Purpose = r7
                int[] r7 = $SwitchMap$org$bitcoinj$core$Transaction$Purpose     // Catch:{ NoSuchFieldError -> 0x0172 }
                org.bitcoinj.core.Transaction$Purpose r8 = org.bitcoinj.core.Transaction.Purpose.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0172 }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x0172 }
                r7[r8] = r0     // Catch:{ NoSuchFieldError -> 0x0172 }
            L_0x0172:
                int[] r0 = $SwitchMap$org$bitcoinj$core$Transaction$Purpose     // Catch:{ NoSuchFieldError -> 0x017c }
                org.bitcoinj.core.Transaction$Purpose r7 = org.bitcoinj.core.Transaction.Purpose.USER_PAYMENT     // Catch:{ NoSuchFieldError -> 0x017c }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x017c }
                r0[r7] = r1     // Catch:{ NoSuchFieldError -> 0x017c }
            L_0x017c:
                int[] r0 = $SwitchMap$org$bitcoinj$core$Transaction$Purpose     // Catch:{ NoSuchFieldError -> 0x0186 }
                org.bitcoinj.core.Transaction$Purpose r1 = org.bitcoinj.core.Transaction.Purpose.KEY_ROTATION     // Catch:{ NoSuchFieldError -> 0x0186 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0186 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0186 }
            L_0x0186:
                int[] r0 = $SwitchMap$org$bitcoinj$core$Transaction$Purpose     // Catch:{ NoSuchFieldError -> 0x0190 }
                org.bitcoinj.core.Transaction$Purpose r1 = org.bitcoinj.core.Transaction.Purpose.ASSURANCE_CONTRACT_CLAIM     // Catch:{ NoSuchFieldError -> 0x0190 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0190 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0190 }
            L_0x0190:
                int[] r0 = $SwitchMap$org$bitcoinj$core$Transaction$Purpose     // Catch:{ NoSuchFieldError -> 0x019a }
                org.bitcoinj.core.Transaction$Purpose r1 = org.bitcoinj.core.Transaction.Purpose.ASSURANCE_CONTRACT_PLEDGE     // Catch:{ NoSuchFieldError -> 0x019a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x019a }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x019a }
            L_0x019a:
                int[] r0 = $SwitchMap$org$bitcoinj$core$Transaction$Purpose     // Catch:{ NoSuchFieldError -> 0x01a4 }
                org.bitcoinj.core.Transaction$Purpose r1 = org.bitcoinj.core.Transaction.Purpose.ASSURANCE_CONTRACT_STUB     // Catch:{ NoSuchFieldError -> 0x01a4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01a4 }
                r0[r1] = r5     // Catch:{ NoSuchFieldError -> 0x01a4 }
            L_0x01a4:
                int[] r0 = $SwitchMap$org$bitcoinj$core$Transaction$Purpose     // Catch:{ NoSuchFieldError -> 0x01ae }
                org.bitcoinj.core.Transaction$Purpose r1 = org.bitcoinj.core.Transaction.Purpose.RAISE_FEE     // Catch:{ NoSuchFieldError -> 0x01ae }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01ae }
                r0[r1] = r6     // Catch:{ NoSuchFieldError -> 0x01ae }
            L_0x01ae:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.wallet.WalletProtobufSerializer.C35442.<clinit>():void");
        }
    }

    public interface WalletFactory {
        C3530Wallet create(NetworkParameters networkParameters, KeyChainGroup keyChainGroup);
    }

    public WalletProtobufSerializer() {
        this(new WalletFactory() {
            public C3530Wallet create(NetworkParameters networkParameters, KeyChainGroup keyChainGroup) {
                return new C3530Wallet(networkParameters, keyChainGroup);
            }
        });
    }

    public WalletProtobufSerializer(WalletFactory walletFactory) {
        this.requireMandatoryExtensions = true;
        this.txMap = new HashMap();
        this.factory = walletFactory;
        this.keyChainFactory = new DefaultKeyChainFactory();
    }

    public void setKeyChainFactory(KeyChainFactory keyChainFactory2) {
        this.keyChainFactory = keyChainFactory2;
    }

    public void setRequireMandatoryExtensions(boolean z) {
        this.requireMandatoryExtensions = z;
    }

    public void writeWallet(C3530Wallet wallet, OutputStream outputStream) throws IOException {
        walletToProto(wallet).writeTo(outputStream);
    }

    public String walletToText(C3530Wallet wallet) {
        return TextFormat.printToString(walletToProto(wallet));
    }

    public C3527Wallet walletToProto(C3530Wallet wallet) {
        Builder newBuilder = C3527Wallet.newBuilder();
        newBuilder.setNetworkIdentifier(wallet.getNetworkParameters().getId());
        if (wallet.getDescription() != null) {
            newBuilder.setDescription(wallet.getDescription());
        }
        for (WalletTransaction makeTxProto : wallet.getWalletTransactions()) {
            newBuilder.addTransaction(makeTxProto(makeTxProto));
        }
        newBuilder.addAllKey(wallet.serializeKeyChainGroupToProtobuf());
        for (Script script : wallet.getWatchedScripts()) {
            newBuilder.addWatchedScript(Protos.Script.newBuilder().setProgram(ByteString.copyFrom(script.getProgram())).setCreationTimestamp(script.getCreationTimeSeconds() * 1000).build());
        }
        Sha256Hash lastBlockSeenHash = wallet.getLastBlockSeenHash();
        if (lastBlockSeenHash != null) {
            newBuilder.setLastSeenBlockHash(hashToByteString(lastBlockSeenHash));
            newBuilder.setLastSeenBlockHeight(wallet.getLastBlockSeenHeight());
        }
        if (wallet.getLastBlockSeenTimeSecs() > 0) {
            newBuilder.setLastSeenBlockTimeSecs(wallet.getLastBlockSeenTimeSecs());
        }
        KeyCrypter keyCrypter = wallet.getKeyCrypter();
        if (keyCrypter == null) {
            newBuilder.setEncryptionType(EncryptionType.UNENCRYPTED);
        } else {
            newBuilder.setEncryptionType(keyCrypter.getUnderstoodEncryptionType());
            if (keyCrypter instanceof KeyCrypterScrypt) {
                newBuilder.setEncryptionParameters(((KeyCrypterScrypt) keyCrypter).getScryptParameters());
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("The wallet has encryption of type '");
                sb.append(keyCrypter.getUnderstoodEncryptionType());
                sb.append("' but this WalletProtobufSerializer does not know how to persist this.");
                throw new RuntimeException(sb.toString());
            }
        }
        if (wallet.getKeyRotationTime() != null) {
            newBuilder.setKeyRotationTime(wallet.getKeyRotationTime().getTime() / 1000);
        }
        populateExtensions(wallet, newBuilder);
        for (Entry entry : wallet.getTags().entrySet()) {
            newBuilder.addTags(Tag.newBuilder().setTag((String) entry.getKey()).setData((ByteString) entry.getValue()));
        }
        for (TransactionSigner transactionSigner : wallet.getTransactionSigners()) {
            if (!(transactionSigner instanceof LocalTransactionSigner)) {
                Protos.TransactionSigner.Builder newBuilder2 = Protos.TransactionSigner.newBuilder();
                newBuilder2.setClassName(transactionSigner.getClass().getName());
                newBuilder2.setData(ByteString.copyFrom(transactionSigner.serialize()));
                newBuilder.addTransactionSigners(newBuilder2);
            }
        }
        newBuilder.setVersion(wallet.getVersion());
        return newBuilder.build();
    }

    private static void populateExtensions(C3530Wallet wallet, Builder builder) {
        for (WalletExtension walletExtension : wallet.getExtensions().values()) {
            Extension.Builder newBuilder = Extension.newBuilder();
            newBuilder.setId(walletExtension.getWalletExtensionID());
            newBuilder.setMandatory(walletExtension.isWalletExtensionMandatory());
            newBuilder.setData(ByteString.copyFrom(walletExtension.serializeWalletExtension()));
            builder.addExtension(newBuilder);
        }
    }

    private static Protos.Transaction makeTxProto(WalletTransaction walletTransaction) {
        Purpose purpose;
        Transaction transaction = walletTransaction.getTransaction();
        Protos.Transaction.Builder newBuilder = Protos.Transaction.newBuilder();
        newBuilder.setPool(getProtoPool(walletTransaction)).setHash(hashToByteString(transaction.getHash())).setVersion((int) transaction.getVersion());
        if (transaction.getUpdateTime() != null) {
            newBuilder.setUpdatedAt(transaction.getUpdateTime().getTime());
        }
        if (transaction.getLockTime() > 0) {
            newBuilder.setLockTime((int) transaction.getLockTime());
        }
        for (TransactionInput transactionInput : transaction.getInputs()) {
            Protos.TransactionInput.Builder transactionOutPointIndex = Protos.TransactionInput.newBuilder().setScriptBytes(ByteString.copyFrom(transactionInput.getScriptBytes())).setTransactionOutPointHash(hashToByteString(transactionInput.getOutpoint().getHash())).setTransactionOutPointIndex((int) transactionInput.getOutpoint().getIndex());
            if (transactionInput.hasSequence()) {
                transactionOutPointIndex.setSequence((int) transactionInput.getSequenceNumber());
            }
            if (transactionInput.getValue() != null) {
                transactionOutPointIndex.setValue(transactionInput.getValue().value);
            }
            newBuilder.addTransactionInput(transactionOutPointIndex);
        }
        for (TransactionOutput transactionOutput : transaction.getOutputs()) {
            Protos.TransactionOutput.Builder value = Protos.TransactionOutput.newBuilder().setScriptBytes(ByteString.copyFrom(transactionOutput.getScriptBytes())).setValue(transactionOutput.getValue().value);
            TransactionInput spentBy = transactionOutput.getSpentBy();
            if (spentBy != null) {
                Sha256Hash hash = spentBy.getParentTransaction().getHash();
                value.setSpentByTransactionHash(hashToByteString(hash)).setSpentByTransactionIndex(spentBy.getParentTransaction().getInputs().indexOf(spentBy));
            }
            newBuilder.addTransactionOutput(value);
        }
        Map appearsInHashes = transaction.getAppearsInHashes();
        if (appearsInHashes != null) {
            for (Entry entry : appearsInHashes.entrySet()) {
                newBuilder.addBlockHash(hashToByteString((Sha256Hash) entry.getKey()));
                newBuilder.addBlockRelativityOffsets(((Integer) entry.getValue()).intValue());
            }
        }
        if (transaction.hasConfidence()) {
            writeConfidence(newBuilder, transaction.getConfidence(), TransactionConfidence.newBuilder());
        }
        switch (transaction.getPurpose()) {
            case UNKNOWN:
                purpose = Purpose.UNKNOWN;
                break;
            case USER_PAYMENT:
                purpose = Purpose.USER_PAYMENT;
                break;
            case KEY_ROTATION:
                purpose = Purpose.KEY_ROTATION;
                break;
            case ASSURANCE_CONTRACT_CLAIM:
                purpose = Purpose.ASSURANCE_CONTRACT_CLAIM;
                break;
            case ASSURANCE_CONTRACT_PLEDGE:
                purpose = Purpose.ASSURANCE_CONTRACT_PLEDGE;
                break;
            case ASSURANCE_CONTRACT_STUB:
                purpose = Purpose.ASSURANCE_CONTRACT_STUB;
                break;
            case RAISE_FEE:
                purpose = Purpose.RAISE_FEE;
                break;
            default:
                throw new RuntimeException("New tx purpose serialization not implemented.");
        }
        newBuilder.setPurpose(purpose);
        ExchangeRate exchangeRate = transaction.getExchangeRate();
        if (exchangeRate != null) {
            newBuilder.setExchangeRate(Protos.ExchangeRate.newBuilder().setCoinValue(exchangeRate.coin.value).setFiatValue(exchangeRate.fiat.value).setFiatCurrencyCode(exchangeRate.fiat.currencyCode));
        }
        if (transaction.getMemo() != null) {
            newBuilder.setMemo(transaction.getMemo());
        }
        return newBuilder.build();
    }

    private static Protos.Transaction.Pool getProtoPool(WalletTransaction walletTransaction) {
        int i = C35442.$SwitchMap$org$bitcoinj$wallet$WalletTransaction$Pool[walletTransaction.getPool().ordinal()];
        if (i == 1) {
            return Protos.Transaction.Pool.UNSPENT;
        }
        if (i == 2) {
            return Protos.Transaction.Pool.SPENT;
        }
        if (i == 3) {
            return Protos.Transaction.Pool.DEAD;
        }
        if (i == 4) {
            return Protos.Transaction.Pool.PENDING;
        }
        throw new RuntimeException("Unreachable");
    }

    private static void writeConfidence(Protos.Transaction.Builder builder, org.bitcoinj.core.TransactionConfidence transactionConfidence, TransactionConfidence.Builder builder2) {
        synchronized (transactionConfidence) {
            builder2.setType(C3522Type.valueOf(transactionConfidence.getConfidenceType().getValue()));
            if (transactionConfidence.getConfidenceType() == ConfidenceType.BUILDING) {
                builder2.setAppearedAtHeight(transactionConfidence.getAppearedAtChainHeight());
                builder2.setDepth(transactionConfidence.getDepthInBlocks());
            }
            if (transactionConfidence.getConfidenceType() == ConfidenceType.DEAD && transactionConfidence.getOverridingTransaction() != null) {
                builder2.setOverridingTransaction(hashToByteString(transactionConfidence.getOverridingTransaction().getHash()));
            }
            int i = C35442.$SwitchMap$org$bitcoinj$core$TransactionConfidence$Source[transactionConfidence.getSource().ordinal()];
            if (i == 1) {
                builder2.setSource(TransactionConfidence.Source.SOURCE_SELF);
            } else if (i != 2) {
                builder2.setSource(TransactionConfidence.Source.SOURCE_UNKNOWN);
            } else {
                builder2.setSource(TransactionConfidence.Source.SOURCE_NETWORK);
            }
        }
        for (PeerAddress peerAddress : transactionConfidence.getBroadcastBy()) {
            builder2.addBroadcastBy(Protos.PeerAddress.newBuilder().setIpAddress(ByteString.copyFrom(peerAddress.getAddr().getAddress())).setPort(peerAddress.getPort()).setServices(peerAddress.getServices().longValue()).build());
        }
        Date lastBroadcastedAt = transactionConfidence.getLastBroadcastedAt();
        if (lastBroadcastedAt != null) {
            builder2.setLastBroadcastedAt(lastBroadcastedAt.getTime());
        }
        builder.setConfidence(builder2);
    }

    public static ByteString hashToByteString(Sha256Hash sha256Hash) {
        return ByteString.copyFrom(sha256Hash.getBytes());
    }

    public static Sha256Hash byteStringToHash(ByteString byteString) {
        return Sha256Hash.wrap(byteString.toByteArray());
    }

    public C3530Wallet readWallet(InputStream inputStream, @Nullable WalletExtension... walletExtensionArr) throws UnreadableWalletException {
        return readWallet(inputStream, false, walletExtensionArr);
    }

    public C3530Wallet readWallet(InputStream inputStream, boolean z, @Nullable WalletExtension[] walletExtensionArr) throws UnreadableWalletException {
        String str = "Could not parse input stream to protobuf";
        try {
            C3527Wallet parseToProto = parseToProto(inputStream);
            String networkIdentifier = parseToProto.getNetworkIdentifier();
            NetworkParameters fromID = NetworkParameters.fromID(networkIdentifier);
            if (fromID != null) {
                return readWallet(fromID, walletExtensionArr, parseToProto, z);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown network parameters ID ");
            sb.append(networkIdentifier);
            throw new UnreadableWalletException(sb.toString());
        } catch (IOException e) {
            throw new UnreadableWalletException(str, e);
        } catch (IllegalStateException e2) {
            throw new UnreadableWalletException(str, e2);
        } catch (IllegalArgumentException e3) {
            throw new UnreadableWalletException(str, e3);
        }
    }

    public C3530Wallet readWallet(NetworkParameters networkParameters, @Nullable WalletExtension[] walletExtensionArr, C3527Wallet wallet) throws UnreadableWalletException {
        return readWallet(networkParameters, walletExtensionArr, wallet, false);
    }

    public C3530Wallet readWallet(NetworkParameters networkParameters, @Nullable WalletExtension[] walletExtensionArr, C3527Wallet wallet, boolean z) throws UnreadableWalletException {
        KeyChainGroup keyChainGroup;
        if (wallet.getVersion() > CURRENT_WALLET_VERSION) {
            throw new FutureVersion();
        } else if (wallet.getNetworkIdentifier().equals(networkParameters.getId())) {
            if (wallet.hasEncryptionParameters()) {
                keyChainGroup = KeyChainGroup.fromProtobufEncrypted(networkParameters, wallet.getKeyList(), new KeyCrypterScrypt(wallet.getEncryptionParameters()), this.keyChainFactory);
            } else {
                keyChainGroup = KeyChainGroup.fromProtobufUnencrypted(networkParameters, wallet.getKeyList(), this.keyChainFactory);
            }
            C3530Wallet create = this.factory.create(networkParameters, keyChainGroup);
            ArrayList newArrayList = Lists.newArrayList();
            for (Protos.Script script : wallet.getWatchedScriptList()) {
                try {
                    newArrayList.add(new Script(script.getProgram().toByteArray(), script.getCreationTimestamp() / 1000));
                } catch (ScriptException unused) {
                    throw new UnreadableWalletException("Unparseable script in wallet");
                }
            }
            create.addWatchedScripts(newArrayList);
            if (wallet.hasDescription()) {
                create.setDescription(wallet.getDescription());
            }
            if (z) {
                create.setLastBlockSeenHash(null);
                create.setLastBlockSeenHeight(-1);
                create.setLastBlockSeenTimeSecs(0);
            } else {
                for (Protos.Transaction readTransaction : wallet.getTransactionList()) {
                    readTransaction(readTransaction, create.getParams());
                }
                for (Protos.Transaction connectTransactionOutputs : wallet.getTransactionList()) {
                    create.addWalletTransaction(connectTransactionOutputs(networkParameters, connectTransactionOutputs));
                }
                if (!wallet.hasLastSeenBlockHash()) {
                    create.setLastBlockSeenHash(null);
                } else {
                    create.setLastBlockSeenHash(byteStringToHash(wallet.getLastSeenBlockHash()));
                }
                if (!wallet.hasLastSeenBlockHeight()) {
                    create.setLastBlockSeenHeight(-1);
                } else {
                    create.setLastBlockSeenHeight(wallet.getLastSeenBlockHeight());
                }
                create.setLastBlockSeenTimeSecs(wallet.getLastSeenBlockTimeSecs());
                if (wallet.hasKeyRotationTime()) {
                    create.setKeyRotationTime(new Date(wallet.getKeyRotationTime() * 1000));
                }
            }
            if (walletExtensionArr == null) {
                walletExtensionArr = new WalletExtension[0];
            }
            loadExtensions(create, walletExtensionArr, wallet);
            for (Tag tag : wallet.getTagsList()) {
                create.setTag(tag.getTag(), tag.getData());
            }
            for (Protos.TransactionSigner transactionSigner : wallet.getTransactionSignersList()) {
                try {
                    TransactionSigner transactionSigner2 = (TransactionSigner) Class.forName(transactionSigner.getClassName()).newInstance();
                    transactionSigner2.deserialize(transactionSigner.getData().toByteArray());
                    create.addTransactionSigner(transactionSigner2);
                } catch (Exception e) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unable to deserialize TransactionSigner instance: ");
                    sb.append(transactionSigner.getClassName());
                    throw new UnreadableWalletException(sb.toString(), e);
                }
            }
            if (wallet.hasVersion()) {
                create.setVersion(wallet.getVersion());
            }
            this.txMap.clear();
            return create;
        } else {
            throw new WrongNetwork();
        }
    }

    private void loadExtensions(C3530Wallet wallet, WalletExtension[] walletExtensionArr, C3527Wallet wallet2) throws UnreadableWalletException {
        HashMap hashMap = new HashMap();
        for (WalletExtension walletExtension : walletExtensionArr) {
            hashMap.put(walletExtension.getWalletExtensionID(), walletExtension);
        }
        hashMap.putAll(wallet.getExtensions());
        for (Extension extension : wallet2.getExtensionList()) {
            String id = extension.getId();
            WalletExtension walletExtension2 = (WalletExtension) hashMap.get(id);
            if (walletExtension2 != null) {
                log.info("Loading wallet extension {}", (Object) id);
                try {
                    wallet.deserializeExtension(walletExtension2, extension.getData().toByteArray());
                } catch (Exception unused) {
                    if (extension.getMandatory() && this.requireMandatoryExtensions) {
                        log.error("Error whilst reading mandatory extension {}, failing to read wallet", (Object) id);
                        StringBuilder sb = new StringBuilder();
                        sb.append("Could not parse mandatory extension in wallet: ");
                        sb.append(id);
                        throw new UnreadableWalletException(sb.toString());
                    }
                }
            } else if (!extension.getMandatory()) {
                continue;
            } else if (!this.requireMandatoryExtensions) {
                log.error("Unknown extension in wallet {}, ignoring", (Object) id);
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Unknown mandatory extension in wallet: ");
                sb2.append(id);
                throw new UnreadableWalletException(sb2.toString());
            }
        }
    }

    public static C3527Wallet parseToProto(InputStream inputStream) throws IOException {
        CodedInputStream newInstance = CodedInputStream.newInstance(inputStream);
        newInstance.setSizeLimit(536870912);
        return C3527Wallet.parseFrom(newInstance);
    }

    private void readTransaction(Protos.Transaction transaction, NetworkParameters networkParameters) throws UnreadableWalletException {
        Transaction transaction2 = new Transaction(networkParameters);
        transaction2.setVersion(transaction.getVersion());
        if (transaction.hasUpdatedAt()) {
            transaction2.setUpdateTime(new Date(transaction.getUpdatedAt()));
        }
        for (Protos.TransactionOutput transactionOutput : transaction.getTransactionOutputList()) {
            transaction2.addOutput(new TransactionOutput(networkParameters, transaction2, Coin.valueOf(transactionOutput.getValue()), transactionOutput.getScriptBytes().toByteArray()));
        }
        for (Protos.TransactionInput transactionInput : transaction.getTransactionInputList()) {
            TransactionInput transactionInput2 = new TransactionInput(networkParameters, transaction2, transactionInput.getScriptBytes().toByteArray(), new TransactionOutPoint(networkParameters, ((long) transactionInput.getTransactionOutPointIndex()) & TransactionInput.NO_SEQUENCE, byteStringToHash(transactionInput.getTransactionOutPointHash())), transactionInput.hasValue() ? Coin.valueOf(transactionInput.getValue()) : null);
            if (transactionInput.hasSequence()) {
                transactionInput2.setSequenceNumber(((long) transactionInput.getSequence()) & TransactionInput.NO_SEQUENCE);
            }
            transaction2.addInput(transactionInput2);
        }
        int i = 0;
        while (i < transaction.getBlockHashCount()) {
            transaction2.addBlockAppearance(byteStringToHash(transaction.getBlockHash(i)), transaction.getBlockRelativityOffsetsCount() > 0 ? transaction.getBlockRelativityOffsets(i) : 0);
            i++;
        }
        if (transaction.hasLockTime()) {
            transaction2.setLockTime(((long) transaction.getLockTime()) & TransactionInput.NO_SEQUENCE);
        }
        if (transaction.hasPurpose()) {
            switch (transaction.getPurpose()) {
                case UNKNOWN:
                    transaction2.setPurpose(Transaction.Purpose.UNKNOWN);
                    break;
                case USER_PAYMENT:
                    transaction2.setPurpose(Transaction.Purpose.USER_PAYMENT);
                    break;
                case KEY_ROTATION:
                    transaction2.setPurpose(Transaction.Purpose.KEY_ROTATION);
                    break;
                case ASSURANCE_CONTRACT_CLAIM:
                    transaction2.setPurpose(Transaction.Purpose.ASSURANCE_CONTRACT_CLAIM);
                    break;
                case ASSURANCE_CONTRACT_PLEDGE:
                    transaction2.setPurpose(Transaction.Purpose.ASSURANCE_CONTRACT_PLEDGE);
                    break;
                case ASSURANCE_CONTRACT_STUB:
                    transaction2.setPurpose(Transaction.Purpose.ASSURANCE_CONTRACT_STUB);
                    break;
                case RAISE_FEE:
                    transaction2.setPurpose(Transaction.Purpose.RAISE_FEE);
                    break;
                default:
                    throw new RuntimeException("New purpose serialization not implemented");
            }
        } else {
            transaction2.setPurpose(Transaction.Purpose.USER_PAYMENT);
        }
        if (transaction.hasExchangeRate()) {
            Protos.ExchangeRate exchangeRate = transaction.getExchangeRate();
            transaction2.setExchangeRate(new ExchangeRate(Coin.valueOf(exchangeRate.getCoinValue()), Fiat.valueOf(exchangeRate.getFiatCurrencyCode(), exchangeRate.getFiatValue())));
        }
        if (transaction.hasMemo()) {
            transaction2.setMemo(transaction.getMemo());
        }
        Sha256Hash byteStringToHash = byteStringToHash(transaction.getHash());
        if (!transaction2.getHash().equals(byteStringToHash)) {
            throw new UnreadableWalletException(String.format(Locale.US, "Transaction did not deserialize completely: %s vs %s", new Object[]{transaction2.getHash(), byteStringToHash}));
        } else if (!this.txMap.containsKey(transaction.getHash())) {
            this.txMap.put(transaction.getHash(), transaction2);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Wallet contained duplicate transaction ");
            sb.append(byteStringToHash(transaction.getHash()));
            throw new UnreadableWalletException(sb.toString());
        }
    }

    private WalletTransaction connectTransactionOutputs(NetworkParameters networkParameters, Protos.Transaction transaction) throws UnreadableWalletException {
        Pool pool;
        Transaction transaction2 = (Transaction) this.txMap.get(transaction.getHash());
        switch (transaction.getPool()) {
            case DEAD:
                pool = Pool.DEAD;
                break;
            case PENDING:
                pool = Pool.PENDING;
                break;
            case SPENT:
                pool = Pool.SPENT;
                break;
            case UNSPENT:
                pool = Pool.UNSPENT;
                break;
            case INACTIVE:
            case PENDING_INACTIVE:
                pool = Pool.PENDING;
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown transaction pool: ");
                sb.append(transaction.getPool());
                throw new UnreadableWalletException(sb.toString());
        }
        for (int i = 0; i < transaction2.getOutputs().size(); i++) {
            TransactionOutput transactionOutput = (TransactionOutput) transaction2.getOutputs().get(i);
            Protos.TransactionOutput transactionOutput2 = transaction.getTransactionOutput(i);
            if (transactionOutput2.hasSpentByTransactionHash()) {
                ByteString spentByTransactionHash = transactionOutput2.getSpentByTransactionHash();
                Transaction transaction3 = (Transaction) this.txMap.get(spentByTransactionHash);
                if (transaction3 != null) {
                    ((TransactionInput) Preconditions.checkNotNull(transaction3.getInput((long) transactionOutput2.getSpentByTransactionIndex()))).connect(transactionOutput);
                } else {
                    throw new UnreadableWalletException(String.format(Locale.US, "Could not connect %s to %s", new Object[]{transaction2.getHashAsString(), byteStringToHash(spentByTransactionHash)}));
                }
            }
        }
        if (transaction.hasConfidence()) {
            readConfidence(networkParameters, transaction2, transaction.getConfidence(), transaction2.getConfidence());
        }
        return new WalletTransaction(pool, transaction2);
    }

    private void readConfidence(NetworkParameters networkParameters, Transaction transaction, TransactionConfidence transactionConfidence, org.bitcoinj.core.TransactionConfidence transactionConfidence2) throws UnreadableWalletException {
        ConfidenceType confidenceType;
        if (!transactionConfidence.hasType()) {
            log.warn("Unknown confidence type for tx {}", (Object) transaction.getHashAsString());
            return;
        }
        int i = C35442.$SwitchMap$org$bitcoinj$wallet$Protos$TransactionConfidence$Type[transactionConfidence.getType().ordinal()];
        if (i == 1) {
            confidenceType = ConfidenceType.BUILDING;
        } else if (i == 2) {
            confidenceType = ConfidenceType.DEAD;
        } else if (i == 3) {
            confidenceType = ConfidenceType.PENDING;
        } else if (i == 4) {
            confidenceType = ConfidenceType.PENDING;
        } else if (i != 5) {
            confidenceType = ConfidenceType.UNKNOWN;
        } else {
            confidenceType = ConfidenceType.IN_CONFLICT;
        }
        transactionConfidence2.setConfidenceType(confidenceType);
        if (transactionConfidence.hasAppearedAtHeight()) {
            if (transactionConfidence2.getConfidenceType() != ConfidenceType.BUILDING) {
                log.warn("Have appearedAtHeight but not BUILDING for tx {}", (Object) transaction.getHashAsString());
                return;
            }
            transactionConfidence2.setAppearedAtChainHeight(transactionConfidence.getAppearedAtHeight());
        }
        if (transactionConfidence.hasDepth()) {
            if (transactionConfidence2.getConfidenceType() != ConfidenceType.BUILDING) {
                log.warn("Have depth but not BUILDING for tx {}", (Object) transaction.getHashAsString());
                return;
            }
            transactionConfidence2.setDepthInBlocks(transactionConfidence.getDepth());
        }
        if (transactionConfidence.hasOverridingTransaction()) {
            if (transactionConfidence2.getConfidenceType() != ConfidenceType.DEAD) {
                log.warn("Have overridingTransaction but not OVERRIDDEN for tx {}", (Object) transaction.getHashAsString());
                return;
            }
            Transaction transaction2 = (Transaction) this.txMap.get(transactionConfidence.getOverridingTransaction());
            if (transaction2 == null) {
                log.warn("Have overridingTransaction that is not in wallet for tx {}", (Object) transaction.getHashAsString());
                return;
            }
            transactionConfidence2.setOverridingTransaction(transaction2);
        }
        for (Protos.PeerAddress peerAddress : transactionConfidence.getBroadcastByList()) {
            try {
                PeerAddress peerAddress2 = new PeerAddress(networkParameters, InetAddress.getByAddress(peerAddress.getIpAddress().toByteArray()), peerAddress.getPort());
                peerAddress2.setServices(BigInteger.valueOf(peerAddress.getServices()));
                transactionConfidence2.markBroadcastBy(peerAddress2);
            } catch (UnknownHostException e) {
                throw new UnreadableWalletException("Peer IP address does not have the right length", e);
            }
        }
        if (transactionConfidence.hasLastBroadcastedAt()) {
            transactionConfidence2.setLastBroadcastedAt(new Date(transactionConfidence.getLastBroadcastedAt()));
        }
        int i2 = C35442.f832xbf0af79d[transactionConfidence.getSource().ordinal()];
        if (i2 == 1) {
            transactionConfidence2.setSource(Source.SELF);
        } else if (i2 != 2) {
            transactionConfidence2.setSource(Source.UNKNOWN);
        } else {
            transactionConfidence2.setSource(Source.NETWORK);
        }
    }

    public static boolean isWallet(InputStream inputStream) {
        boolean z = false;
        try {
            CodedInputStream newInstance = CodedInputStream.newInstance(inputStream);
            if (WireFormat.getTagFieldNumber(newInstance.readTag()) != 1) {
                return false;
            }
            if (NetworkParameters.fromID(newInstance.readString()) != null) {
                z = true;
            }
            return z;
        } catch (IOException unused) {
        }
    }
}
