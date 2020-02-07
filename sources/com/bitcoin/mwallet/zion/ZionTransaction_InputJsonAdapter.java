package com.bitcoin.mwallet.zion;

import com.bitcoin.mwallet.zion.ZionTransaction.Input;
import com.htc.htcwalletsdk.Utils.JsonParser.JsonDataKey_signMessage;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonReader.Options;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.bitcoinj.uri.BitcoinURI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0012\u001a\u00020\tH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/mwallet/zion/ZionTransaction_InputJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/bitcoin/mwallet/zion/ZionTransaction$Input;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ZionTransaction_InputJsonAdapter.kt */
public final class ZionTransaction_InputJsonAdapter extends JsonAdapter<Input> {
    private final Options options;
    private final JsonAdapter<String> stringAdapter;

    @NotNull
    public String toString() {
        return "GeneratedJsonAdapter(ZionTransaction.Input)";
    }

    public ZionTransaction_InputJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkParameterIsNotNull(moshi, "moshi");
        Options of = Options.m288of(JsonDataKey_signMessage.path, "tx_id", "tx_index", "scriptSig", "sequence", BitcoinURI.FIELD_AMOUNT);
        Intrinsics.checkExpressionValueIsNotNull(of, "JsonReader.Options.of(\"p…g\", \"sequence\", \"amount\")");
        this.options = of;
        JsonAdapter<String> adapter = moshi.adapter(String.class, SetsKt.emptySet(), JsonDataKey_signMessage.path);
        Intrinsics.checkExpressionValueIsNotNull(adapter, "moshi.adapter<String>(St…tions.emptySet(), \"path\")");
        this.stringAdapter = adapter;
    }

    @NotNull
    public Input fromJson(@NotNull JsonReader jsonReader) {
        String str;
        JsonReader jsonReader2 = jsonReader;
        Intrinsics.checkParameterIsNotNull(jsonReader2, "reader");
        String str2 = null;
        jsonReader.beginObject();
        String str3 = str2;
        String str4 = str3;
        String str5 = str4;
        String str6 = str5;
        String str7 = str6;
        while (jsonReader.hasNext()) {
            switch (jsonReader2.selectName(this.options)) {
                case -1:
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    break;
                case 0:
                    str2 = (String) this.stringAdapter.fromJson(jsonReader2);
                    if (str2 != null) {
                        break;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Non-null value 'path' was null at ");
                        sb.append(jsonReader.getPath());
                        throw new JsonDataException(sb.toString());
                    }
                case 1:
                    String str8 = (String) this.stringAdapter.fromJson(jsonReader2);
                    if (str8 != null) {
                        str3 = str8;
                        break;
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Non-null value 'txId' was null at ");
                        sb2.append(jsonReader.getPath());
                        throw new JsonDataException(sb2.toString());
                    }
                case 2:
                    String str9 = (String) this.stringAdapter.fromJson(jsonReader2);
                    if (str9 != null) {
                        str4 = str9;
                        break;
                    } else {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Non-null value 'txIndex' was null at ");
                        sb3.append(jsonReader.getPath());
                        throw new JsonDataException(sb3.toString());
                    }
                case 3:
                    String str10 = (String) this.stringAdapter.fromJson(jsonReader2);
                    if (str10 != null) {
                        str5 = str10;
                        break;
                    } else {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("Non-null value 'scriptSig' was null at ");
                        sb4.append(jsonReader.getPath());
                        throw new JsonDataException(sb4.toString());
                    }
                case 4:
                    String str11 = (String) this.stringAdapter.fromJson(jsonReader2);
                    if (str11 != null) {
                        str7 = str11;
                        break;
                    } else {
                        StringBuilder sb5 = new StringBuilder();
                        sb5.append("Non-null value 'sequence' was null at ");
                        sb5.append(jsonReader.getPath());
                        throw new JsonDataException(sb5.toString());
                    }
                case 5:
                    String str12 = (String) this.stringAdapter.fromJson(jsonReader2);
                    if (str12 != null) {
                        str6 = str12;
                        break;
                    } else {
                        StringBuilder sb6 = new StringBuilder();
                        sb6.append("Non-null value 'amount' was null at ");
                        sb6.append(jsonReader.getPath());
                        throw new JsonDataException(sb6.toString());
                    }
            }
        }
        jsonReader.endObject();
        if (str2 == null) {
            StringBuilder sb7 = new StringBuilder();
            sb7.append("Required property 'path' missing at ");
            sb7.append(jsonReader.getPath());
            throw new JsonDataException(sb7.toString());
        } else if (str3 == null) {
            StringBuilder sb8 = new StringBuilder();
            sb8.append("Required property 'txId' missing at ");
            sb8.append(jsonReader.getPath());
            throw new JsonDataException(sb8.toString());
        } else if (str4 == null) {
            StringBuilder sb9 = new StringBuilder();
            sb9.append("Required property 'txIndex' missing at ");
            sb9.append(jsonReader.getPath());
            throw new JsonDataException(sb9.toString());
        } else if (str5 == null) {
            StringBuilder sb10 = new StringBuilder();
            sb10.append("Required property 'scriptSig' missing at ");
            sb10.append(jsonReader.getPath());
            throw new JsonDataException(sb10.toString());
        } else if (str6 != null) {
            Input input = new Input(str2, str3, str4, str5, null, str6, 16, null);
            if (str7 != null) {
                str = str7;
            } else {
                str = input.getSequence();
            }
            Input input2 = new Input(str2, str3, str4, str5, str, str6);
            return input2;
        } else {
            StringBuilder sb11 = new StringBuilder();
            sb11.append("Required property 'amount' missing at ");
            sb11.append(jsonReader.getPath());
            throw new JsonDataException(sb11.toString());
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable Input input) {
        Intrinsics.checkParameterIsNotNull(jsonWriter, "writer");
        if (input != null) {
            jsonWriter.beginObject();
            jsonWriter.name(JsonDataKey_signMessage.path);
            this.stringAdapter.toJson(jsonWriter, input.getPath());
            jsonWriter.name("tx_id");
            this.stringAdapter.toJson(jsonWriter, input.getTxId());
            jsonWriter.name("tx_index");
            this.stringAdapter.toJson(jsonWriter, input.getTxIndex());
            jsonWriter.name("scriptSig");
            this.stringAdapter.toJson(jsonWriter, input.getScriptSig());
            jsonWriter.name("sequence");
            this.stringAdapter.toJson(jsonWriter, input.getSequence());
            jsonWriter.name(BitcoinURI.FIELD_AMOUNT);
            this.stringAdapter.toJson(jsonWriter, input.getAmount());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.");
    }
}
