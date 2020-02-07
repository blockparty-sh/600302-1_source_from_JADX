package com.bitcoin.bitcoink;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p016io.CloseableKt;
import kotlin.p016io.TextStreamsKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/WordList;", "", "()V", "Companion", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WordList.kt */
public final class WordList {
    public static final Companion Companion = new Companion(null);

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0003\u001a\u00020\u00042\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\nH\u0002J&\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\n0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\nJ\u0016\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\nH\u0002¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/WordList$Companion;", "", "()V", "checkContains", "", "list", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "words", "", "correctList", "Lkotlin/Pair;", "Ljava/io/InputStream;", "mnemonic", "getStream", "Ljava/io/ByteArrayInputStream;", "result", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: WordList.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Pair<InputStream, List<String>> correctList(@NotNull List<String> list) {
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Throwable th5;
            Throwable th6;
            Throwable th7;
            Throwable th8;
            Throwable th9;
            Intrinsics.checkParameterIsNotNull(list, "mnemonic");
            InputStream resourceAsStream = Mnemonic.Companion.getClass().getClassLoader().getResourceAsStream("english.txt");
            Intrinsics.checkExpressionValueIsNotNull(resourceAsStream, "Mnemonic.javaClass.class…ceAsStream(\"english.txt\")");
            Reader inputStreamReader = new InputStreamReader(resourceAsStream, Charsets.UTF_8);
            Closeable bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            Throwable th10 = null;
            try {
                List readLines = TextStreamsKt.readLines((BufferedReader) bufferedReader);
                CloseableKt.closeFinally(bufferedReader, th10);
                InputStream resourceAsStream2 = Mnemonic.Companion.getClass().getClassLoader().getResourceAsStream("spanish.txt");
                Intrinsics.checkExpressionValueIsNotNull(resourceAsStream2, "Mnemonic.javaClass.class…ceAsStream(\"spanish.txt\")");
                Reader inputStreamReader2 = new InputStreamReader(resourceAsStream2, Charsets.UTF_8);
                Closeable bufferedReader2 = inputStreamReader2 instanceof BufferedReader ? (BufferedReader) inputStreamReader2 : new BufferedReader(inputStreamReader2, 8192);
                try {
                    List readLines2 = TextStreamsKt.readLines((BufferedReader) bufferedReader2);
                    CloseableKt.closeFinally(bufferedReader2, th10);
                    InputStream resourceAsStream3 = Mnemonic.Companion.getClass().getClassLoader().getResourceAsStream("korean.txt");
                    Intrinsics.checkExpressionValueIsNotNull(resourceAsStream3, "Mnemonic.javaClass.class…rceAsStream(\"korean.txt\")");
                    Reader inputStreamReader3 = new InputStreamReader(resourceAsStream3, Charsets.UTF_8);
                    Closeable bufferedReader3 = inputStreamReader3 instanceof BufferedReader ? (BufferedReader) inputStreamReader3 : new BufferedReader(inputStreamReader3, 8192);
                    try {
                        List readLines3 = TextStreamsKt.readLines((BufferedReader) bufferedReader3);
                        CloseableKt.closeFinally(bufferedReader3, th10);
                        InputStream resourceAsStream4 = Mnemonic.Companion.getClass().getClassLoader().getResourceAsStream("japanese.txt");
                        Intrinsics.checkExpressionValueIsNotNull(resourceAsStream4, "Mnemonic.javaClass.class…eAsStream(\"japanese.txt\")");
                        Reader inputStreamReader4 = new InputStreamReader(resourceAsStream4, Charsets.UTF_8);
                        Closeable bufferedReader4 = inputStreamReader4 instanceof BufferedReader ? (BufferedReader) inputStreamReader4 : new BufferedReader(inputStreamReader4, 8192);
                        try {
                            List readLines4 = TextStreamsKt.readLines((BufferedReader) bufferedReader4);
                            CloseableKt.closeFinally(bufferedReader4, th10);
                            InputStream resourceAsStream5 = Mnemonic.Companion.getClass().getClassLoader().getResourceAsStream("japanese_combined.txt");
                            Intrinsics.checkExpressionValueIsNotNull(resourceAsStream5, "Mnemonic.javaClass.class…(\"japanese_combined.txt\")");
                            Reader inputStreamReader5 = new InputStreamReader(resourceAsStream5, Charsets.UTF_8);
                            Closeable bufferedReader5 = inputStreamReader5 instanceof BufferedReader ? (BufferedReader) inputStreamReader5 : new BufferedReader(inputStreamReader5, 8192);
                            try {
                                List readLines5 = TextStreamsKt.readLines((BufferedReader) bufferedReader5);
                                CloseableKt.closeFinally(bufferedReader5, th10);
                                InputStream resourceAsStream6 = Mnemonic.Companion.getClass().getClassLoader().getResourceAsStream("italian.txt");
                                Intrinsics.checkExpressionValueIsNotNull(resourceAsStream6, "Mnemonic.javaClass.class…ceAsStream(\"italian.txt\")");
                                Reader inputStreamReader6 = new InputStreamReader(resourceAsStream6, Charsets.UTF_8);
                                Closeable bufferedReader6 = inputStreamReader6 instanceof BufferedReader ? (BufferedReader) inputStreamReader6 : new BufferedReader(inputStreamReader6, 8192);
                                try {
                                    List readLines6 = TextStreamsKt.readLines((BufferedReader) bufferedReader6);
                                    CloseableKt.closeFinally(bufferedReader6, th10);
                                    InputStream resourceAsStream7 = Mnemonic.Companion.getClass().getClassLoader().getResourceAsStream("french.txt");
                                    Intrinsics.checkExpressionValueIsNotNull(resourceAsStream7, "Mnemonic.javaClass.class…rceAsStream(\"french.txt\")");
                                    Reader inputStreamReader7 = new InputStreamReader(resourceAsStream7, Charsets.UTF_8);
                                    Closeable bufferedReader7 = inputStreamReader7 instanceof BufferedReader ? (BufferedReader) inputStreamReader7 : new BufferedReader(inputStreamReader7, 8192);
                                    try {
                                        List readLines7 = TextStreamsKt.readLines((BufferedReader) bufferedReader7);
                                        CloseableKt.closeFinally(bufferedReader7, th10);
                                        InputStream resourceAsStream8 = Mnemonic.Companion.getClass().getClassLoader().getResourceAsStream("chinese_simplified.txt");
                                        Intrinsics.checkExpressionValueIsNotNull(resourceAsStream8, "Mnemonic.javaClass.class…\"chinese_simplified.txt\")");
                                        Reader inputStreamReader8 = new InputStreamReader(resourceAsStream8, Charsets.UTF_8);
                                        Closeable bufferedReader8 = inputStreamReader8 instanceof BufferedReader ? (BufferedReader) inputStreamReader8 : new BufferedReader(inputStreamReader8, 8192);
                                        try {
                                            List readLines8 = TextStreamsKt.readLines((BufferedReader) bufferedReader8);
                                            CloseableKt.closeFinally(bufferedReader8, th10);
                                            InputStream resourceAsStream9 = Mnemonic.Companion.getClass().getClassLoader().getResourceAsStream("chinese_traditional.txt");
                                            Intrinsics.checkExpressionValueIsNotNull(resourceAsStream9, "Mnemonic.javaClass.class…chinese_traditional.txt\")");
                                            Reader inputStreamReader9 = new InputStreamReader(resourceAsStream9, Charsets.UTF_8);
                                            Closeable bufferedReader9 = inputStreamReader9 instanceof BufferedReader ? (BufferedReader) inputStreamReader9 : new BufferedReader(inputStreamReader9, 8192);
                                            try {
                                                List readLines9 = TextStreamsKt.readLines((BufferedReader) bufferedReader9);
                                                CloseableKt.closeFinally(bufferedReader9, th10);
                                                Companion companion = this;
                                                if (companion.checkContains(new HashSet(readLines), list)) {
                                                    return new Pair<>(companion.getStream(readLines), readLines);
                                                }
                                                if (companion.checkContains(new HashSet(readLines2), list)) {
                                                    return new Pair<>(companion.getStream(readLines2), readLines2);
                                                }
                                                if (companion.checkContains(new HashSet(readLines6), list)) {
                                                    return new Pair<>(companion.getStream(readLines6), readLines6);
                                                }
                                                if (companion.checkContains(new HashSet(readLines3), list)) {
                                                    return new Pair<>(companion.getStream(readLines3), readLines3);
                                                }
                                                if (companion.checkContains(new HashSet(readLines7), list)) {
                                                    return new Pair<>(companion.getStream(readLines7), readLines7);
                                                }
                                                if (companion.checkContains(new HashSet(readLines4), list)) {
                                                    return new Pair<>(companion.getStream(readLines4), readLines4);
                                                }
                                                if (companion.checkContains(new HashSet(readLines5), list)) {
                                                    return new Pair<>(companion.getStream(readLines5), readLines5);
                                                }
                                                if (companion.checkContains(new HashSet(readLines8), list)) {
                                                    return new Pair<>(companion.getStream(readLines8), readLines8);
                                                }
                                                if (companion.checkContains(new HashSet(readLines9), list)) {
                                                    return new Pair<>(companion.getStream(readLines9), readLines9);
                                                }
                                                return new Pair<>(companion.getStream(readLines), readLines);
                                            } catch (Throwable th11) {
                                                CloseableKt.closeFinally(bufferedReader9, th9);
                                                throw th11;
                                            }
                                        } catch (Throwable th12) {
                                            CloseableKt.closeFinally(bufferedReader8, th8);
                                            throw th12;
                                        }
                                    } catch (Throwable th13) {
                                        CloseableKt.closeFinally(bufferedReader7, th7);
                                        throw th13;
                                    }
                                } catch (Throwable th14) {
                                    CloseableKt.closeFinally(bufferedReader6, th6);
                                    throw th14;
                                }
                            } catch (Throwable th15) {
                                CloseableKt.closeFinally(bufferedReader5, th5);
                                throw th15;
                            }
                        } catch (Throwable th16) {
                            CloseableKt.closeFinally(bufferedReader4, th4);
                            throw th16;
                        }
                    } catch (Throwable th17) {
                        CloseableKt.closeFinally(bufferedReader3, th3);
                        throw th17;
                    }
                } catch (Throwable th18) {
                    CloseableKt.closeFinally(bufferedReader2, th2);
                    throw th18;
                }
            } catch (Throwable th19) {
                CloseableKt.closeFinally(bufferedReader, th);
                throw th19;
            }
        }

        private final boolean checkContains(HashSet<String> hashSet, List<String> list) {
            return hashSet.containsAll(list);
        }

        private final ByteArrayInputStream getStream(List<String> list) {
            StringBuilder sb = new StringBuilder();
            for (String append : list) {
                sb.append(append);
                Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
                StringsKt.appendln(sb);
            }
            String sb2 = sb.toString();
            Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
            Charset charset = Charsets.UTF_8;
            if (sb2 != null) {
                byte[] bytes = sb2.getBytes(charset);
                Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                return new ByteArrayInputStream(bytes);
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
    }
}
