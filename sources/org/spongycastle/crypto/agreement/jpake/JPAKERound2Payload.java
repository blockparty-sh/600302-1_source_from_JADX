package org.spongycastle.crypto.agreement.jpake;

import java.math.BigInteger;
import org.spongycastle.util.Arrays;

public class JPAKERound2Payload {

    /* renamed from: a */
    private final BigInteger f936a;
    private final BigInteger[] knowledgeProofForX2s;
    private final String participantId;

    public JPAKERound2Payload(String str, BigInteger bigInteger, BigInteger[] bigIntegerArr) {
        JPAKEUtil.validateNotNull(str, "participantId");
        JPAKEUtil.validateNotNull(bigInteger, "a");
        JPAKEUtil.validateNotNull(bigIntegerArr, "knowledgeProofForX2s");
        this.participantId = str;
        this.f936a = bigInteger;
        this.knowledgeProofForX2s = Arrays.copyOf(bigIntegerArr, bigIntegerArr.length);
    }

    public String getParticipantId() {
        return this.participantId;
    }

    public BigInteger getA() {
        return this.f936a;
    }

    public BigInteger[] getKnowledgeProofForX2s() {
        BigInteger[] bigIntegerArr = this.knowledgeProofForX2s;
        return Arrays.copyOf(bigIntegerArr, bigIntegerArr.length);
    }
}
