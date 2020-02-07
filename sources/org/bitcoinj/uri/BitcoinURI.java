package org.bitcoinj.uri;

import com.facebook.stetho.common.Utf8Charset;
import com.google.common.base.Preconditions;
import com.microsoft.appcenter.Constants;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.AddressFormatException;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.NetworkParameters;
import org.slf4j.Marker;

public class BitcoinURI {
    private static final String AMPERSAND_SEPARATOR = "&";
    @Deprecated
    public static final String BITCOIN_SCHEME = "bitcoincash";
    private static final String ENCODED_SPACE_CHARACTER = "%20";
    public static final String FIELD_ADDRESS = "address";
    public static final String FIELD_AMOUNT = "amount";
    public static final String FIELD_LABEL = "label";
    public static final String FIELD_MESSAGE = "message";
    public static final String FIELD_PAYMENT_REQUEST_URL = "r";
    private static final String QUESTION_MARK_SEPARATOR = "?";
    private final Map<String, Object> parameterMap;

    public BitcoinURI(String str) throws BitcoinURIParseException {
        this(null, str);
    }

    public BitcoinURI(@Nullable NetworkParameters networkParameters, String str) throws BitcoinURIParseException {
        String str2;
        String str3;
        String[] strArr;
        this.parameterMap = new LinkedHashMap();
        Preconditions.checkNotNull(str);
        if (networkParameters == null) {
            str2 = "bitcoincash";
        } else {
            str2 = networkParameters.getUriScheme();
        }
        try {
            URI uri = new URI(str);
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append("://");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str2);
            sb3.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            String sb4 = sb3.toString();
            if (str.startsWith(sb2)) {
                str3 = str.substring(sb2.length());
            } else if (str.startsWith(sb4)) {
                str3 = str.substring(sb4.length());
            } else {
                StringBuilder sb5 = new StringBuilder();
                sb5.append("Unsupported URI scheme: ");
                sb5.append(uri.getScheme());
                throw new BitcoinURIParseException(sb5.toString());
            }
            String[] split = str3.split("\\?", 2);
            if (split.length != 0) {
                String str4 = split[0];
                if (split.length == 1) {
                    strArr = new String[0];
                } else {
                    strArr = split[1].split(AMPERSAND_SEPARATOR);
                }
                parseParameters(networkParameters, str4, strArr);
                if (!str4.isEmpty()) {
                    try {
                        putWithValidation("address", Address.fromBase58(networkParameters, str4));
                    } catch (AddressFormatException e) {
                        throw new BitcoinURIParseException("Bad address", e);
                    }
                }
                if (str4.isEmpty() && getPaymentRequestUrl() == null) {
                    throw new BitcoinURIParseException("No address and no r= parameter found");
                }
                return;
            }
            throw new BitcoinURIParseException("No data found after the bitcoin: prefix");
        } catch (URISyntaxException e2) {
            throw new BitcoinURIParseException("Bad URI syntax", e2);
        }
    }

    private void parseParameters(@Nullable NetworkParameters networkParameters, String str, String[] strArr) throws BitcoinURIParseException {
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            String str2 = strArr[i];
            int indexOf = str2.indexOf(61);
            String str3 = "'";
            if (indexOf == -1) {
                StringBuilder sb = new StringBuilder();
                sb.append("Malformed Bitcoin URI - no separator in '");
                sb.append(str2);
                sb.append(str3);
                throw new BitcoinURIParseException(sb.toString());
            } else if (indexOf != 0) {
                String lowerCase = str2.substring(0, indexOf).toLowerCase(Locale.ENGLISH);
                String substring = str2.substring(indexOf + 1);
                String str4 = FIELD_AMOUNT;
                if (str4.equals(lowerCase)) {
                    try {
                        Coin parseCoin = Coin.parseCoin(substring);
                        if (networkParameters != null) {
                            if (parseCoin.isGreaterThan(networkParameters.getMaxMoney())) {
                                throw new BitcoinURIParseException("Max number of coins exceeded");
                            }
                        }
                        if (parseCoin.signum() >= 0) {
                            putWithValidation(str4, parseCoin);
                        } else {
                            throw new ArithmeticException("Negative coins specified");
                        }
                    } catch (IllegalArgumentException e) {
                        throw new OptionalFieldValidationException(String.format(Locale.US, "'%s' is not a valid amount", new Object[]{substring}), e);
                    } catch (ArithmeticException e2) {
                        throw new OptionalFieldValidationException(String.format(Locale.US, "'%s' has too many decimal places", new Object[]{substring}), e2);
                    }
                } else if (!lowerCase.startsWith("req-")) {
                    try {
                        if (substring.length() > 0) {
                            putWithValidation(lowerCase, URLDecoder.decode(substring, Utf8Charset.NAME));
                        }
                    } catch (UnsupportedEncodingException e3) {
                        throw new RuntimeException(e3);
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str3);
                    sb2.append(lowerCase);
                    sb2.append("' is required but not known, this URI is not valid");
                    throw new RequiredFieldValidationException(sb2.toString());
                }
                i++;
            } else {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Malformed Bitcoin URI - empty name '");
                sb3.append(str2);
                sb3.append(str3);
                throw new BitcoinURIParseException(sb3.toString());
            }
        }
    }

    private void putWithValidation(String str, Object obj) throws BitcoinURIParseException {
        if (!this.parameterMap.containsKey(str)) {
            this.parameterMap.put(str, obj);
            return;
        }
        throw new BitcoinURIParseException(String.format(Locale.US, "'%s' is duplicated, URI is invalid", new Object[]{str}));
    }

    @Nullable
    public Address getAddress() {
        return (Address) this.parameterMap.get("address");
    }

    public Coin getAmount() {
        return (Coin) this.parameterMap.get(FIELD_AMOUNT);
    }

    public String getLabel() {
        return (String) this.parameterMap.get(FIELD_LABEL);
    }

    public String getMessage() {
        return (String) this.parameterMap.get("message");
    }

    public final String getPaymentRequestUrl() {
        return (String) this.parameterMap.get(FIELD_PAYMENT_REQUEST_URL);
    }

    public List<String> getPaymentRequestUrls() {
        ArrayList arrayList = new ArrayList();
        while (true) {
            int size = arrayList.size();
            StringBuilder sb = new StringBuilder();
            sb.append(FIELD_PAYMENT_REQUEST_URL);
            sb.append(size > 0 ? Integer.toString(size) : "");
            String str = (String) this.parameterMap.get(sb.toString());
            if (str == null) {
                Collections.reverse(arrayList);
                return arrayList;
            }
            arrayList.add(str);
        }
    }

    public Object getParameterByName(String str) {
        return this.parameterMap.get(str);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("BitcoinURI[");
        boolean z = true;
        for (Entry entry : this.parameterMap.entrySet()) {
            if (z) {
                z = false;
            } else {
                sb.append(",");
            }
            String str = "'";
            sb.append(str);
            sb.append((String) entry.getKey());
            sb.append("'=");
            sb.append(str);
            sb.append(entry.getValue());
            sb.append(str);
        }
        sb.append("]");
        return sb.toString();
    }

    public static String convertToBitcoinURI(Address address, Coin coin, String str, String str2) {
        return convertToBitcoinURI(address.getParameters(), address.toString(), coin, str, str2);
    }

    public static String convertToBitcoinURI(NetworkParameters networkParameters, String str, @Nullable Coin coin, @Nullable String str2, @Nullable String str3) {
        Preconditions.checkNotNull(networkParameters);
        Preconditions.checkNotNull(str);
        if (coin == null || coin.signum() >= 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(networkParameters.getUriScheme());
            sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            sb.append(str);
            boolean z = false;
            String str4 = "=";
            String str5 = QUESTION_MARK_SEPARATOR;
            if (coin != null) {
                sb.append(str5);
                sb.append(FIELD_AMOUNT);
                sb.append(str4);
                sb.append(coin.toPlainString());
                z = true;
            }
            String str6 = AMPERSAND_SEPARATOR;
            String str7 = "";
            if (str2 != null && !str7.equals(str2)) {
                if (z) {
                    sb.append(str6);
                } else {
                    sb.append(str5);
                    z = true;
                }
                sb.append(FIELD_LABEL);
                sb.append(str4);
                sb.append(encodeURLString(str2));
            }
            if (str3 != null && !str7.equals(str3)) {
                if (z) {
                    sb.append(str6);
                } else {
                    sb.append(str5);
                }
                sb.append("message");
                sb.append(str4);
                sb.append(encodeURLString(str3));
            }
            return sb.toString();
        }
        throw new IllegalArgumentException("Coin must be positive");
    }

    static String encodeURLString(String str) {
        try {
            return URLEncoder.encode(str, Utf8Charset.NAME).replace(Marker.ANY_NON_NULL_MARKER, ENCODED_SPACE_CHARACTER);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
