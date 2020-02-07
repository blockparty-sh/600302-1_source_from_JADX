package com.bitcoin.mwallet.zion;

import com.bitcoin.mwallet.zion.ZionTransaction.Change;
import com.bitcoin.mwallet.zion.ZionTransaction.Input;
import com.bitcoin.mwallet.zion.ZionTransaction.Output;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonReader.Options;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.bitcoinj.script.ScriptOpCodes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0019\u001a\u00020\u0010H\u0016R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0001X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0001X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\u0001X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, mo37405d2 = {"Lcom/bitcoin/mwallet/zion/ZionTransactionJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/bitcoin/mwallet/zion/ZionTransaction;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/squareup/moshi/Moshi;)V", "listOfChangeAdapter", "", "Lcom/bitcoin/mwallet/zion/ZionTransaction$Change;", "listOfInputAdapter", "Lcom/bitcoin/mwallet/zion/ZionTransaction$Input;", "listOfOutputAdapter", "Lcom/bitcoin/mwallet/zion/ZionTransaction$Output;", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ZionTransactionJsonAdapter.kt */
public final class ZionTransactionJsonAdapter extends JsonAdapter<ZionTransaction> {
    private final JsonAdapter<List<Change>> listOfChangeAdapter;
    private final JsonAdapter<List<Input>> listOfInputAdapter;
    private final JsonAdapter<List<Output>> listOfOutputAdapter;
    private final Options options;
    private final JsonAdapter<String> stringAdapter;

    @NotNull
    public String toString() {
        return "GeneratedJsonAdapter(ZionTransaction)";
    }

    public ZionTransactionJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkParameterIsNotNull(moshi, "moshi");
        Options of = Options.m288of("tx_version", "tx_inputs_count", "tx_inputs", "tx_outputs_count", "tx_outputs", "tx_changes_count", "tx_changes", "lock_time");
        Intrinsics.checkExpressionValueIsNotNull(of, "JsonReader.Options.of(\"t…tx_changes\", \"lock_time\")");
        this.options = of;
        JsonAdapter<String> adapter = moshi.adapter(String.class, SetsKt.emptySet(), "version");
        Intrinsics.checkExpressionValueIsNotNull(adapter, "moshi.adapter<String>(St…ns.emptySet(), \"version\")");
        this.stringAdapter = adapter;
        JsonAdapter<List<Input>> adapter2 = moshi.adapter(Types.newParameterizedType(List.class, Input.class), SetsKt.emptySet(), "inputs");
        Intrinsics.checkExpressionValueIsNotNull(adapter2, "moshi.adapter<List<ZionT…ons.emptySet(), \"inputs\")");
        this.listOfInputAdapter = adapter2;
        JsonAdapter<List<Output>> adapter3 = moshi.adapter(Types.newParameterizedType(List.class, Output.class), SetsKt.emptySet(), "outputs");
        Intrinsics.checkExpressionValueIsNotNull(adapter3, "moshi.adapter<List<ZionT…ns.emptySet(), \"outputs\")");
        this.listOfOutputAdapter = adapter3;
        JsonAdapter<List<Change>> adapter4 = moshi.adapter(Types.newParameterizedType(List.class, Change.class), SetsKt.emptySet(), "changes");
        Intrinsics.checkExpressionValueIsNotNull(adapter4, "moshi.adapter<List<ZionT…ns.emptySet(), \"changes\")");
        this.listOfChangeAdapter = adapter4;
    }

    @NotNull
    public ZionTransaction fromJson(@NotNull JsonReader jsonReader) {
        String str;
        String str2;
        JsonReader jsonReader2 = jsonReader;
        Intrinsics.checkParameterIsNotNull(jsonReader2, "reader");
        String str3 = null;
        List list = null;
        jsonReader.beginObject();
        List list2 = list;
        List list3 = list2;
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
                    String str8 = (String) this.stringAdapter.fromJson(jsonReader2);
                    if (str8 != null) {
                        str6 = str8;
                        break;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Non-null value 'version' was null at ");
                        sb.append(jsonReader.getPath());
                        throw new JsonDataException(sb.toString());
                    }
                case 1:
                    str3 = (String) this.stringAdapter.fromJson(jsonReader2);
                    if (str3 != null) {
                        break;
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Non-null value 'inputCount' was null at ");
                        sb2.append(jsonReader.getPath());
                        throw new JsonDataException(sb2.toString());
                    }
                case 2:
                    list = (List) this.listOfInputAdapter.fromJson(jsonReader2);
                    if (list != null) {
                        break;
                    } else {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Non-null value 'inputs' was null at ");
                        sb3.append(jsonReader.getPath());
                        throw new JsonDataException(sb3.toString());
                    }
                case 3:
                    String str9 = (String) this.stringAdapter.fromJson(jsonReader2);
                    if (str9 != null) {
                        str4 = str9;
                        break;
                    } else {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("Non-null value 'outputCount' was null at ");
                        sb4.append(jsonReader.getPath());
                        throw new JsonDataException(sb4.toString());
                    }
                case 4:
                    List list4 = (List) this.listOfOutputAdapter.fromJson(jsonReader2);
                    if (list4 != null) {
                        list2 = list4;
                        break;
                    } else {
                        StringBuilder sb5 = new StringBuilder();
                        sb5.append("Non-null value 'outputs' was null at ");
                        sb5.append(jsonReader.getPath());
                        throw new JsonDataException(sb5.toString());
                    }
                case 5:
                    String str10 = (String) this.stringAdapter.fromJson(jsonReader2);
                    if (str10 != null) {
                        str5 = str10;
                        break;
                    } else {
                        StringBuilder sb6 = new StringBuilder();
                        sb6.append("Non-null value 'changeCount' was null at ");
                        sb6.append(jsonReader.getPath());
                        throw new JsonDataException(sb6.toString());
                    }
                case 6:
                    List list5 = (List) this.listOfChangeAdapter.fromJson(jsonReader2);
                    if (list5 != null) {
                        list3 = list5;
                        break;
                    } else {
                        StringBuilder sb7 = new StringBuilder();
                        sb7.append("Non-null value 'changes' was null at ");
                        sb7.append(jsonReader.getPath());
                        throw new JsonDataException(sb7.toString());
                    }
                case 7:
                    String str11 = (String) this.stringAdapter.fromJson(jsonReader2);
                    if (str11 != null) {
                        str7 = str11;
                        break;
                    } else {
                        StringBuilder sb8 = new StringBuilder();
                        sb8.append("Non-null value 'lockTime' was null at ");
                        sb8.append(jsonReader.getPath());
                        throw new JsonDataException(sb8.toString());
                    }
            }
        }
        jsonReader.endObject();
        if (str3 == null) {
            StringBuilder sb9 = new StringBuilder();
            sb9.append("Required property 'inputCount' missing at ");
            sb9.append(jsonReader.getPath());
            throw new JsonDataException(sb9.toString());
        } else if (list == null) {
            StringBuilder sb10 = new StringBuilder();
            sb10.append("Required property 'inputs' missing at ");
            sb10.append(jsonReader.getPath());
            throw new JsonDataException(sb10.toString());
        } else if (str4 == null) {
            StringBuilder sb11 = new StringBuilder();
            sb11.append("Required property 'outputCount' missing at ");
            sb11.append(jsonReader.getPath());
            throw new JsonDataException(sb11.toString());
        } else if (list2 == null) {
            StringBuilder sb12 = new StringBuilder();
            sb12.append("Required property 'outputs' missing at ");
            sb12.append(jsonReader.getPath());
            throw new JsonDataException(sb12.toString());
        } else if (str5 == null) {
            StringBuilder sb13 = new StringBuilder();
            sb13.append("Required property 'changeCount' missing at ");
            sb13.append(jsonReader.getPath());
            throw new JsonDataException(sb13.toString());
        } else if (list3 != null) {
            ZionTransaction zionTransaction = new ZionTransaction(null, str3, list, str4, list2, str5, list3, null, ScriptOpCodes.OP_RIGHT, null);
            if (str6 != null) {
                str = str6;
            } else {
                str = zionTransaction.getVersion();
            }
            if (str7 != null) {
                str2 = str7;
            } else {
                str2 = zionTransaction.getLockTime();
            }
            ZionTransaction zionTransaction2 = new ZionTransaction(str, str3, list, str4, list2, str5, list3, str2);
            return zionTransaction2;
        } else {
            StringBuilder sb14 = new StringBuilder();
            sb14.append("Required property 'changes' missing at ");
            sb14.append(jsonReader.getPath());
            throw new JsonDataException(sb14.toString());
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable ZionTransaction zionTransaction) {
        Intrinsics.checkParameterIsNotNull(jsonWriter, "writer");
        if (zionTransaction != null) {
            jsonWriter.beginObject();
            jsonWriter.name("tx_version");
            this.stringAdapter.toJson(jsonWriter, zionTransaction.getVersion());
            jsonWriter.name("tx_inputs_count");
            this.stringAdapter.toJson(jsonWriter, zionTransaction.getInputCount());
            jsonWriter.name("tx_inputs");
            this.listOfInputAdapter.toJson(jsonWriter, zionTransaction.getInputs());
            jsonWriter.name("tx_outputs_count");
            this.stringAdapter.toJson(jsonWriter, zionTransaction.getOutputCount());
            jsonWriter.name("tx_outputs");
            this.listOfOutputAdapter.toJson(jsonWriter, zionTransaction.getOutputs());
            jsonWriter.name("tx_changes_count");
            this.stringAdapter.toJson(jsonWriter, zionTransaction.getChangeCount());
            jsonWriter.name("tx_changes");
            this.listOfChangeAdapter.toJson(jsonWriter, zionTransaction.getChanges());
            jsonWriter.name("lock_time");
            this.stringAdapter.toJson(jsonWriter, zionTransaction.getLockTime());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.");
    }
}
