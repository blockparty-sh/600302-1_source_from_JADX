package org.spongycastle.asn1.util;

import java.io.IOException;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Boolean;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1Enumerated;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.ASN1UTCTime;
import org.spongycastle.asn1.BERApplicationSpecific;
import org.spongycastle.asn1.BEROctetString;
import org.spongycastle.asn1.BERSequence;
import org.spongycastle.asn1.BERSet;
import org.spongycastle.asn1.BERTaggedObject;
import org.spongycastle.asn1.DERApplicationSpecific;
import org.spongycastle.asn1.DERBMPString;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERExternal;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DERPrintableString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERT61String;
import org.spongycastle.asn1.DERUTF8String;
import org.spongycastle.asn1.DERVisibleString;
import org.spongycastle.util.encoders.Hex;

public class ASN1Dump {
    private static final int SAMPLE_SIZE = 32;
    private static final String TAB = "    ";

    static void _dumpAsString(String str, boolean z, ASN1Primitive aSN1Primitive, StringBuffer stringBuffer) {
        String property = System.getProperty("line.separator");
        boolean z2 = aSN1Primitive instanceof ASN1Sequence;
        String str2 = "NULL";
        String str3 = TAB;
        if (z2) {
            Enumeration objects = ((ASN1Sequence) aSN1Primitive).getObjects();
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(str3);
            String sb2 = sb.toString();
            stringBuffer.append(str);
            if (aSN1Primitive instanceof BERSequence) {
                stringBuffer.append("BER Sequence");
            } else if (aSN1Primitive instanceof DERSequence) {
                stringBuffer.append("DER Sequence");
            } else {
                stringBuffer.append("Sequence");
            }
            stringBuffer.append(property);
            while (objects.hasMoreElements()) {
                Object nextElement = objects.nextElement();
                if (nextElement == null || nextElement.equals(DERNull.INSTANCE)) {
                    stringBuffer.append(sb2);
                    stringBuffer.append(str2);
                    stringBuffer.append(property);
                } else if (nextElement instanceof ASN1Primitive) {
                    _dumpAsString(sb2, z, (ASN1Primitive) nextElement, stringBuffer);
                } else {
                    _dumpAsString(sb2, z, ((ASN1Encodable) nextElement).toASN1Primitive(), stringBuffer);
                }
            }
        } else if (aSN1Primitive instanceof ASN1TaggedObject) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append(str3);
            String sb4 = sb3.toString();
            stringBuffer.append(str);
            if (aSN1Primitive instanceof BERTaggedObject) {
                stringBuffer.append("BER Tagged [");
            } else {
                stringBuffer.append("Tagged [");
            }
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive;
            stringBuffer.append(Integer.toString(aSN1TaggedObject.getTagNo()));
            stringBuffer.append(']');
            if (!aSN1TaggedObject.isExplicit()) {
                stringBuffer.append(" IMPLICIT ");
            }
            stringBuffer.append(property);
            if (aSN1TaggedObject.isEmpty()) {
                stringBuffer.append(sb4);
                stringBuffer.append("EMPTY");
                stringBuffer.append(property);
                return;
            }
            _dumpAsString(sb4, z, aSN1TaggedObject.getObject(), stringBuffer);
        } else if (aSN1Primitive instanceof ASN1Set) {
            Enumeration objects2 = ((ASN1Set) aSN1Primitive).getObjects();
            StringBuilder sb5 = new StringBuilder();
            sb5.append(str);
            sb5.append(str3);
            String sb6 = sb5.toString();
            stringBuffer.append(str);
            if (aSN1Primitive instanceof BERSet) {
                stringBuffer.append("BER Set");
            } else {
                stringBuffer.append("DER Set");
            }
            stringBuffer.append(property);
            while (objects2.hasMoreElements()) {
                Object nextElement2 = objects2.nextElement();
                if (nextElement2 == null) {
                    stringBuffer.append(sb6);
                    stringBuffer.append(str2);
                    stringBuffer.append(property);
                } else if (nextElement2 instanceof ASN1Primitive) {
                    _dumpAsString(sb6, z, (ASN1Primitive) nextElement2, stringBuffer);
                } else {
                    _dumpAsString(sb6, z, ((ASN1Encodable) nextElement2).toASN1Primitive(), stringBuffer);
                }
            }
        } else {
            String str4 = "] ";
            String str5 = "[";
            if (aSN1Primitive instanceof ASN1OctetString) {
                ASN1OctetString aSN1OctetString = (ASN1OctetString) aSN1Primitive;
                if (aSN1Primitive instanceof BEROctetString) {
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append(str);
                    sb7.append("BER Constructed Octet String");
                    sb7.append(str5);
                    sb7.append(aSN1OctetString.getOctets().length);
                    sb7.append(str4);
                    stringBuffer.append(sb7.toString());
                } else {
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append(str);
                    sb8.append("DER Octet String");
                    sb8.append(str5);
                    sb8.append(aSN1OctetString.getOctets().length);
                    sb8.append(str4);
                    stringBuffer.append(sb8.toString());
                }
                if (z) {
                    stringBuffer.append(dumpBinaryDataAsString(str, aSN1OctetString.getOctets()));
                } else {
                    stringBuffer.append(property);
                }
            } else {
                String str6 = ")";
                if (aSN1Primitive instanceof ASN1ObjectIdentifier) {
                    StringBuilder sb9 = new StringBuilder();
                    sb9.append(str);
                    sb9.append("ObjectIdentifier(");
                    sb9.append(((ASN1ObjectIdentifier) aSN1Primitive).getId());
                    sb9.append(str6);
                    sb9.append(property);
                    stringBuffer.append(sb9.toString());
                } else if (aSN1Primitive instanceof ASN1Boolean) {
                    StringBuilder sb10 = new StringBuilder();
                    sb10.append(str);
                    sb10.append("Boolean(");
                    sb10.append(((ASN1Boolean) aSN1Primitive).isTrue());
                    sb10.append(str6);
                    sb10.append(property);
                    stringBuffer.append(sb10.toString());
                } else if (aSN1Primitive instanceof ASN1Integer) {
                    StringBuilder sb11 = new StringBuilder();
                    sb11.append(str);
                    sb11.append("Integer(");
                    sb11.append(((ASN1Integer) aSN1Primitive).getValue());
                    sb11.append(str6);
                    sb11.append(property);
                    stringBuffer.append(sb11.toString());
                } else if (aSN1Primitive instanceof DERBitString) {
                    DERBitString dERBitString = (DERBitString) aSN1Primitive;
                    StringBuilder sb12 = new StringBuilder();
                    sb12.append(str);
                    sb12.append("DER Bit String");
                    sb12.append(str5);
                    sb12.append(dERBitString.getBytes().length);
                    sb12.append(", ");
                    sb12.append(dERBitString.getPadBits());
                    sb12.append(str4);
                    stringBuffer.append(sb12.toString());
                    if (z) {
                        stringBuffer.append(dumpBinaryDataAsString(str, dERBitString.getBytes()));
                    } else {
                        stringBuffer.append(property);
                    }
                } else {
                    String str7 = ") ";
                    if (aSN1Primitive instanceof DERIA5String) {
                        StringBuilder sb13 = new StringBuilder();
                        sb13.append(str);
                        sb13.append("IA5String(");
                        sb13.append(((DERIA5String) aSN1Primitive).getString());
                        sb13.append(str7);
                        sb13.append(property);
                        stringBuffer.append(sb13.toString());
                    } else if (aSN1Primitive instanceof DERUTF8String) {
                        StringBuilder sb14 = new StringBuilder();
                        sb14.append(str);
                        sb14.append("UTF8String(");
                        sb14.append(((DERUTF8String) aSN1Primitive).getString());
                        sb14.append(str7);
                        sb14.append(property);
                        stringBuffer.append(sb14.toString());
                    } else if (aSN1Primitive instanceof DERPrintableString) {
                        StringBuilder sb15 = new StringBuilder();
                        sb15.append(str);
                        sb15.append("PrintableString(");
                        sb15.append(((DERPrintableString) aSN1Primitive).getString());
                        sb15.append(str7);
                        sb15.append(property);
                        stringBuffer.append(sb15.toString());
                    } else if (aSN1Primitive instanceof DERVisibleString) {
                        StringBuilder sb16 = new StringBuilder();
                        sb16.append(str);
                        sb16.append("VisibleString(");
                        sb16.append(((DERVisibleString) aSN1Primitive).getString());
                        sb16.append(str7);
                        sb16.append(property);
                        stringBuffer.append(sb16.toString());
                    } else if (aSN1Primitive instanceof DERBMPString) {
                        StringBuilder sb17 = new StringBuilder();
                        sb17.append(str);
                        sb17.append("BMPString(");
                        sb17.append(((DERBMPString) aSN1Primitive).getString());
                        sb17.append(str7);
                        sb17.append(property);
                        stringBuffer.append(sb17.toString());
                    } else if (aSN1Primitive instanceof DERT61String) {
                        StringBuilder sb18 = new StringBuilder();
                        sb18.append(str);
                        sb18.append("T61String(");
                        sb18.append(((DERT61String) aSN1Primitive).getString());
                        sb18.append(str7);
                        sb18.append(property);
                        stringBuffer.append(sb18.toString());
                    } else if (aSN1Primitive instanceof ASN1UTCTime) {
                        StringBuilder sb19 = new StringBuilder();
                        sb19.append(str);
                        sb19.append("UTCTime(");
                        sb19.append(((ASN1UTCTime) aSN1Primitive).getTime());
                        sb19.append(str7);
                        sb19.append(property);
                        stringBuffer.append(sb19.toString());
                    } else if (aSN1Primitive instanceof ASN1GeneralizedTime) {
                        StringBuilder sb20 = new StringBuilder();
                        sb20.append(str);
                        sb20.append("GeneralizedTime(");
                        sb20.append(((ASN1GeneralizedTime) aSN1Primitive).getTime());
                        sb20.append(str7);
                        sb20.append(property);
                        stringBuffer.append(sb20.toString());
                    } else if (aSN1Primitive instanceof BERApplicationSpecific) {
                        stringBuffer.append(outputApplicationSpecific(ASN1Encoding.BER, str, z, aSN1Primitive, property));
                    } else if (aSN1Primitive instanceof DERApplicationSpecific) {
                        stringBuffer.append(outputApplicationSpecific(ASN1Encoding.DER, str, z, aSN1Primitive, property));
                    } else if (aSN1Primitive instanceof ASN1Enumerated) {
                        ASN1Enumerated aSN1Enumerated = (ASN1Enumerated) aSN1Primitive;
                        StringBuilder sb21 = new StringBuilder();
                        sb21.append(str);
                        sb21.append("DER Enumerated(");
                        sb21.append(aSN1Enumerated.getValue());
                        sb21.append(str6);
                        sb21.append(property);
                        stringBuffer.append(sb21.toString());
                    } else if (aSN1Primitive instanceof DERExternal) {
                        DERExternal dERExternal = (DERExternal) aSN1Primitive;
                        StringBuilder sb22 = new StringBuilder();
                        sb22.append(str);
                        sb22.append("External ");
                        sb22.append(property);
                        stringBuffer.append(sb22.toString());
                        StringBuilder sb23 = new StringBuilder();
                        sb23.append(str);
                        sb23.append(str3);
                        String sb24 = sb23.toString();
                        if (dERExternal.getDirectReference() != null) {
                            StringBuilder sb25 = new StringBuilder();
                            sb25.append(sb24);
                            sb25.append("Direct Reference: ");
                            sb25.append(dERExternal.getDirectReference().getId());
                            sb25.append(property);
                            stringBuffer.append(sb25.toString());
                        }
                        if (dERExternal.getIndirectReference() != null) {
                            StringBuilder sb26 = new StringBuilder();
                            sb26.append(sb24);
                            sb26.append("Indirect Reference: ");
                            sb26.append(dERExternal.getIndirectReference().toString());
                            sb26.append(property);
                            stringBuffer.append(sb26.toString());
                        }
                        if (dERExternal.getDataValueDescriptor() != null) {
                            _dumpAsString(sb24, z, dERExternal.getDataValueDescriptor(), stringBuffer);
                        }
                        StringBuilder sb27 = new StringBuilder();
                        sb27.append(sb24);
                        sb27.append("Encoding: ");
                        sb27.append(dERExternal.getEncoding());
                        sb27.append(property);
                        stringBuffer.append(sb27.toString());
                        _dumpAsString(sb24, z, dERExternal.getExternalContent(), stringBuffer);
                    } else {
                        StringBuilder sb28 = new StringBuilder();
                        sb28.append(str);
                        sb28.append(aSN1Primitive.toString());
                        sb28.append(property);
                        stringBuffer.append(sb28.toString());
                    }
                }
            }
        }
    }

    private static String outputApplicationSpecific(String str, String str2, boolean z, ASN1Primitive aSN1Primitive, String str3) {
        DERApplicationSpecific dERApplicationSpecific = (DERApplicationSpecific) aSN1Primitive;
        StringBuffer stringBuffer = new StringBuffer();
        String str4 = " ApplicationSpecific[";
        if (dERApplicationSpecific.isConstructed()) {
            try {
                ASN1Sequence instance = ASN1Sequence.getInstance(dERApplicationSpecific.getObject(16));
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                sb.append(str);
                sb.append(str4);
                sb.append(dERApplicationSpecific.getApplicationTag());
                sb.append("]");
                sb.append(str3);
                stringBuffer.append(sb.toString());
                Enumeration objects = instance.getObjects();
                while (objects.hasMoreElements()) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str2);
                    sb2.append(TAB);
                    _dumpAsString(sb2.toString(), z, (ASN1Primitive) objects.nextElement(), stringBuffer);
                }
            } catch (IOException e) {
                stringBuffer.append(e);
            }
            return stringBuffer.toString();
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str2);
        sb3.append(str);
        sb3.append(str4);
        sb3.append(dERApplicationSpecific.getApplicationTag());
        sb3.append("] (");
        sb3.append(new String(Hex.encode(dERApplicationSpecific.getContents())));
        sb3.append(")");
        sb3.append(str3);
        return sb3.toString();
    }

    public static String dumpAsString(Object obj) {
        return dumpAsString(obj, false);
    }

    public static String dumpAsString(Object obj, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        String str = "";
        if (obj instanceof ASN1Primitive) {
            _dumpAsString(str, z, (ASN1Primitive) obj, stringBuffer);
        } else if (obj instanceof ASN1Encodable) {
            _dumpAsString(str, z, ((ASN1Encodable) obj).toASN1Primitive(), stringBuffer);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("unknown object type ");
            sb.append(obj.toString());
            return sb.toString();
        }
        return stringBuffer.toString();
    }

    private static String dumpBinaryDataAsString(String str, byte[] bArr) {
        String property = System.getProperty("line.separator");
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        String str2 = TAB;
        sb.append(str2);
        String sb2 = sb.toString();
        stringBuffer.append(property);
        for (int i = 0; i < bArr.length; i += 32) {
            if (bArr.length - i > 32) {
                stringBuffer.append(sb2);
                stringBuffer.append(new String(Hex.encode(bArr, i, 32)));
                stringBuffer.append(str2);
                stringBuffer.append(calculateAscString(bArr, i, 32));
                stringBuffer.append(property);
            } else {
                stringBuffer.append(sb2);
                stringBuffer.append(new String(Hex.encode(bArr, i, bArr.length - i)));
                for (int length = bArr.length - i; length != 32; length++) {
                    stringBuffer.append("  ");
                }
                stringBuffer.append(str2);
                stringBuffer.append(calculateAscString(bArr, i, bArr.length - i));
                stringBuffer.append(property);
            }
        }
        return stringBuffer.toString();
    }

    private static String calculateAscString(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = i; i3 != i + i2; i3++) {
            if (bArr[i3] >= 32 && bArr[i3] <= 126) {
                stringBuffer.append((char) bArr[i3]);
            }
        }
        return stringBuffer.toString();
    }
}
