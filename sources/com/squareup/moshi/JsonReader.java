package com.squareup.moshi;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

public abstract class JsonReader implements Closeable {
    boolean failOnUnknown;
    boolean lenient;
    int[] pathIndices;
    String[] pathNames;
    int[] scopes;
    int stackSize;

    public static final class Options {
        final okio.Options doubleQuoteSuffix;
        final String[] strings;

        private Options(String[] strArr, okio.Options options) {
            this.strings = strArr;
            this.doubleQuoteSuffix = options;
        }

        @CheckReturnValue
        /* renamed from: of */
        public static Options m288of(String... strArr) {
            try {
                ByteString[] byteStringArr = new ByteString[strArr.length];
                Buffer buffer = new Buffer();
                for (int i = 0; i < strArr.length; i++) {
                    JsonUtf8Writer.string(buffer, strArr[i]);
                    buffer.readByte();
                    byteStringArr[i] = buffer.readByteString();
                }
                return new Options((String[]) strArr.clone(), okio.Options.m331of(byteStringArr));
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }
    }

    public enum Token {
        BEGIN_ARRAY,
        END_ARRAY,
        BEGIN_OBJECT,
        END_OBJECT,
        NAME,
        STRING,
        NUMBER,
        BOOLEAN,
        NULL,
        END_DOCUMENT
    }

    public abstract void beginArray() throws IOException;

    public abstract void beginObject() throws IOException;

    public abstract void endArray() throws IOException;

    public abstract void endObject() throws IOException;

    @CheckReturnValue
    public abstract boolean hasNext() throws IOException;

    public abstract boolean nextBoolean() throws IOException;

    public abstract double nextDouble() throws IOException;

    public abstract int nextInt() throws IOException;

    public abstract long nextLong() throws IOException;

    @CheckReturnValue
    public abstract String nextName() throws IOException;

    @Nullable
    public abstract <T> T nextNull() throws IOException;

    public abstract String nextString() throws IOException;

    @CheckReturnValue
    public abstract Token peek() throws IOException;

    @CheckReturnValue
    public abstract JsonReader peekJson();

    /* access modifiers changed from: 0000 */
    public abstract void promoteNameToValue() throws IOException;

    @CheckReturnValue
    public abstract int selectName(Options options) throws IOException;

    @CheckReturnValue
    public abstract int selectString(Options options) throws IOException;

    public abstract void skipName() throws IOException;

    public abstract void skipValue() throws IOException;

    @CheckReturnValue
    /* renamed from: of */
    public static JsonReader m287of(BufferedSource bufferedSource) {
        return new JsonUtf8Reader(bufferedSource);
    }

    JsonReader() {
        this.scopes = new int[32];
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
    }

    JsonReader(JsonReader jsonReader) {
        this.stackSize = jsonReader.stackSize;
        this.scopes = (int[]) jsonReader.scopes.clone();
        this.pathNames = (String[]) jsonReader.pathNames.clone();
        this.pathIndices = (int[]) jsonReader.pathIndices.clone();
        this.lenient = jsonReader.lenient;
        this.failOnUnknown = jsonReader.failOnUnknown;
    }

    /* access modifiers changed from: 0000 */
    public final void pushScope(int i) {
        int i2 = this.stackSize;
        int[] iArr = this.scopes;
        if (i2 == iArr.length) {
            if (i2 != 256) {
                this.scopes = Arrays.copyOf(iArr, iArr.length * 2);
                String[] strArr = this.pathNames;
                this.pathNames = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
                int[] iArr2 = this.pathIndices;
                this.pathIndices = Arrays.copyOf(iArr2, iArr2.length * 2);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Nesting too deep at ");
                sb.append(getPath());
                throw new JsonDataException(sb.toString());
            }
        }
        int[] iArr3 = this.scopes;
        int i3 = this.stackSize;
        this.stackSize = i3 + 1;
        iArr3[i3] = i;
    }

    /* access modifiers changed from: 0000 */
    public final JsonEncodingException syntaxError(String str) throws JsonEncodingException {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" at path ");
        sb.append(getPath());
        throw new JsonEncodingException(sb.toString());
    }

    /* access modifiers changed from: 0000 */
    public final JsonDataException typeMismatch(@Nullable Object obj, Object obj2) {
        String str = "Expected ";
        if (obj == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(obj2);
            sb.append(" but was null at path ");
            sb.append(getPath());
            return new JsonDataException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(obj2);
        sb2.append(" but was ");
        sb2.append(obj);
        sb2.append(", a ");
        sb2.append(obj.getClass().getName());
        sb2.append(", at path ");
        sb2.append(getPath());
        return new JsonDataException(sb2.toString());
    }

    public final void setLenient(boolean z) {
        this.lenient = z;
    }

    @CheckReturnValue
    public final boolean isLenient() {
        return this.lenient;
    }

    public final void setFailOnUnknown(boolean z) {
        this.failOnUnknown = z;
    }

    @CheckReturnValue
    public final boolean failOnUnknown() {
        return this.failOnUnknown;
    }

    @Nullable
    public final Object readJsonValue() throws IOException {
        switch (peek()) {
            case BEGIN_ARRAY:
                ArrayList arrayList = new ArrayList();
                beginArray();
                while (hasNext()) {
                    arrayList.add(readJsonValue());
                }
                endArray();
                return arrayList;
            case BEGIN_OBJECT:
                LinkedHashTreeMap linkedHashTreeMap = new LinkedHashTreeMap();
                beginObject();
                while (hasNext()) {
                    String nextName = nextName();
                    Object readJsonValue = readJsonValue();
                    Object put = linkedHashTreeMap.put(nextName, readJsonValue);
                    if (put != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Map key '");
                        sb.append(nextName);
                        sb.append("' has multiple values at path ");
                        sb.append(getPath());
                        sb.append(": ");
                        sb.append(put);
                        sb.append(" and ");
                        sb.append(readJsonValue);
                        throw new JsonDataException(sb.toString());
                    }
                }
                endObject();
                return linkedHashTreeMap;
            case STRING:
                return nextString();
            case NUMBER:
                return Double.valueOf(nextDouble());
            case BOOLEAN:
                return Boolean.valueOf(nextBoolean());
            case NULL:
                return nextNull();
            default:
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Expected a value but was ");
                sb2.append(peek());
                sb2.append(" at path ");
                sb2.append(getPath());
                throw new IllegalStateException(sb2.toString());
        }
    }

    @CheckReturnValue
    public final String getPath() {
        return JsonScope.getPath(this.stackSize, this.scopes, this.pathNames, this.pathIndices);
    }
}
