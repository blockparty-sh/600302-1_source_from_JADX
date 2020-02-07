package com.bitcoin.mwallet.zion;

import com.bitcoin.mwallet.zion.ZionTransaction.Change;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0012\u001a\u00020\tH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/mwallet/zion/ZionTransaction_ChangeJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/bitcoin/mwallet/zion/ZionTransaction$Change;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ZionTransaction_ChangeJsonAdapter.kt */
public final class ZionTransaction_ChangeJsonAdapter extends JsonAdapter<Change> {
    private final Options options;
    private final JsonAdapter<String> stringAdapter;

    @NotNull
    public String toString() {
        return "GeneratedJsonAdapter(ZionTransaction.Change)";
    }

    public ZionTransaction_ChangeJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkParameterIsNotNull(moshi, "moshi");
        String str = JsonDataKey_signMessage.path;
        Options of = Options.m288of(str, BitcoinURI.FIELD_AMOUNT);
        Intrinsics.checkExpressionValueIsNotNull(of, "JsonReader.Options.of(\"path\", \"amount\")");
        this.options = of;
        JsonAdapter<String> adapter = moshi.adapter(String.class, SetsKt.emptySet(), str);
        Intrinsics.checkExpressionValueIsNotNull(adapter, "moshi.adapter<String>(St…tions.emptySet(), \"path\")");
        this.stringAdapter = adapter;
    }

    @NotNull
    public Change fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkParameterIsNotNull(jsonReader, "reader");
        String str = null;
        jsonReader.beginObject();
        String str2 = str;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                str = (String) this.stringAdapter.fromJson(jsonReader);
                if (str == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Non-null value 'path' was null at ");
                    sb.append(jsonReader.getPath());
                    throw new JsonDataException(sb.toString());
                }
            } else if (selectName != 1) {
                continue;
            } else {
                str2 = (String) this.stringAdapter.fromJson(jsonReader);
                if (str2 == null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Non-null value 'amount' was null at ");
                    sb2.append(jsonReader.getPath());
                    throw new JsonDataException(sb2.toString());
                }
            }
        }
        jsonReader.endObject();
        if (str == null) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Required property 'path' missing at ");
            sb3.append(jsonReader.getPath());
            throw new JsonDataException(sb3.toString());
        } else if (str2 != null) {
            return new Change(str, str2);
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Required property 'amount' missing at ");
            sb4.append(jsonReader.getPath());
            throw new JsonDataException(sb4.toString());
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable Change change) {
        Intrinsics.checkParameterIsNotNull(jsonWriter, "writer");
        if (change != null) {
            jsonWriter.beginObject();
            jsonWriter.name(JsonDataKey_signMessage.path);
            this.stringAdapter.toJson(jsonWriter, change.getPath());
            jsonWriter.name(BitcoinURI.FIELD_AMOUNT);
            this.stringAdapter.toJson(jsonWriter, change.getAmount());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.");
    }
}
