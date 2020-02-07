package androidx.core.provider;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import androidx.annotation.GuardedBy;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.VisibleForTesting;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.content.res.ResourcesCompat.FontCallback;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.graphics.TypefaceCompatUtil;
import androidx.core.provider.SelfDestructiveThread.ReplyCallback;
import androidx.core.util.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

public class FontsContractCompat {
    private static final int BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS = 10000;
    @RestrictTo({Scope.LIBRARY_GROUP_PREFIX})
    public static final String PARCEL_FONT_RESULTS = "font_results";
    @RestrictTo({Scope.LIBRARY_GROUP_PREFIX})
    static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;
    @RestrictTo({Scope.LIBRARY_GROUP_PREFIX})
    static final int RESULT_CODE_WRONG_CERTIFICATES = -2;
    private static final SelfDestructiveThread sBackgroundThread = new SelfDestructiveThread("fonts", 10, 10000);
    private static final Comparator<byte[]> sByteArrayComparator = new Comparator<byte[]>() {
        public int compare(byte[] bArr, byte[] bArr2) {
            int i;
            int i2;
            if (bArr.length != bArr2.length) {
                i2 = bArr.length;
                i = bArr2.length;
            } else {
                int i3 = 0;
                while (i3 < bArr.length) {
                    if (bArr[i3] != bArr2[i3]) {
                        i2 = bArr[i3];
                        i = bArr2[i3];
                    } else {
                        i3++;
                    }
                }
                return 0;
            }
            return i2 - i;
        }
    };
    @GuardedBy("sLock")
    private static Executor sExecutor;
    static final Object sLock = new Object();
    @GuardedBy("sLock")
    static final SimpleArrayMap<String, ArrayList<ReplyCallback<TypefaceResult>>> sPendingReplies = new SimpleArrayMap<>();
    static final LruCache<String, Typeface> sTypefaceCache = new LruCache<>(16);

    public static final class Columns implements BaseColumns {
        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";
    }

    public static class FontFamilyResult {
        public static final int STATUS_OK = 0;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        private final FontInfo[] mFonts;
        private final int mStatusCode;

        @RestrictTo({Scope.LIBRARY_GROUP_PREFIX})
        public FontFamilyResult(int i, @Nullable FontInfo[] fontInfoArr) {
            this.mStatusCode = i;
            this.mFonts = fontInfoArr;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }

        public FontInfo[] getFonts() {
            return this.mFonts;
        }
    }

    public static class FontInfo {
        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final int mWeight;

        @RestrictTo({Scope.LIBRARY_GROUP_PREFIX})
        public FontInfo(@NonNull Uri uri, @IntRange(from = 0) int i, @IntRange(from = 1, mo644to = 1000) int i2, boolean z, int i3) {
            this.mUri = (Uri) Preconditions.checkNotNull(uri);
            this.mTtcIndex = i;
            this.mWeight = i2;
            this.mItalic = z;
            this.mResultCode = i3;
        }

        @NonNull
        public Uri getUri() {
            return this.mUri;
        }

        @IntRange(from = 0)
        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        @IntRange(from = 1, mo644to = 1000)
        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }

        public int getResultCode() {
            return this.mResultCode;
        }
    }

    public static class FontRequestCallback {
        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_SECURITY_VIOLATION = -4;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;
        @RestrictTo({Scope.LIBRARY_GROUP_PREFIX})
        public static final int RESULT_OK = 0;

        @RestrictTo({Scope.LIBRARY_GROUP_PREFIX})
        @Retention(RetentionPolicy.SOURCE)
        public @interface FontRequestFailReason {
        }

        public void onTypefaceRequestFailed(int i) {
        }

        public void onTypefaceRetrieved(Typeface typeface) {
        }
    }

    private interface OnCompletedCallback<T> {
        void onCompleted(T t);
    }

    private static final class OnFetchCompletedAndFirePendingReplyCallback implements OnCompletedCallback<TypefaceResult> {
        @NonNull
        private final String mCacheId;

        OnFetchCompletedAndFirePendingReplyCallback(@NonNull String str) {
            this.mCacheId = str;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
            if (r0 >= r1.size()) goto L_0x002c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
            ((androidx.core.provider.SelfDestructiveThread.ReplyCallback) r1.get(r0)).onReply(r5);
            r0 = r0 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0019, code lost:
            r0 = 0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onCompleted(androidx.core.provider.FontsContractCompat.TypefaceResult r5) {
            /*
                r4 = this;
                java.lang.Object r0 = androidx.core.provider.FontsContractCompat.sLock
                monitor-enter(r0)
                androidx.collection.SimpleArrayMap<java.lang.String, java.util.ArrayList<androidx.core.provider.SelfDestructiveThread$ReplyCallback<androidx.core.provider.FontsContractCompat$TypefaceResult>>> r1 = androidx.core.provider.FontsContractCompat.sPendingReplies     // Catch:{ all -> 0x002d }
                java.lang.String r2 = r4.mCacheId     // Catch:{ all -> 0x002d }
                java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x002d }
                java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ all -> 0x002d }
                if (r1 != 0) goto L_0x0011
                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                return
            L_0x0011:
                androidx.collection.SimpleArrayMap<java.lang.String, java.util.ArrayList<androidx.core.provider.SelfDestructiveThread$ReplyCallback<androidx.core.provider.FontsContractCompat$TypefaceResult>>> r2 = androidx.core.provider.FontsContractCompat.sPendingReplies     // Catch:{ all -> 0x002d }
                java.lang.String r3 = r4.mCacheId     // Catch:{ all -> 0x002d }
                r2.remove(r3)     // Catch:{ all -> 0x002d }
                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                r0 = 0
            L_0x001a:
                int r2 = r1.size()
                if (r0 >= r2) goto L_0x002c
                java.lang.Object r2 = r1.get(r0)
                androidx.core.provider.SelfDestructiveThread$ReplyCallback r2 = (androidx.core.provider.SelfDestructiveThread.ReplyCallback) r2
                r2.onReply(r5)
                int r0 = r0 + 1
                goto L_0x001a
            L_0x002c:
                return
            L_0x002d:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontsContractCompat.OnFetchCompletedAndFirePendingReplyCallback.onCompleted(androidx.core.provider.FontsContractCompat$TypefaceResult):void");
        }
    }

    private static final class SyncFontFetchTask extends FutureTask<TypefaceResult> {

        private static final class CallableWrapper implements Callable<TypefaceResult> {
            private final Callable<TypefaceResult> mOriginalCallback;
            private final OnCompletedCallback<TypefaceResult> mTypefaceResultOnCompletedCallback;

            CallableWrapper(@NonNull Callable<TypefaceResult> callable, @NonNull OnCompletedCallback<TypefaceResult> onCompletedCallback) {
                this.mOriginalCallback = callable;
                this.mTypefaceResultOnCompletedCallback = onCompletedCallback;
            }

            public TypefaceResult call() throws Exception {
                TypefaceResult typefaceResult = (TypefaceResult) this.mOriginalCallback.call();
                this.mTypefaceResultOnCompletedCallback.onCompleted(typefaceResult);
                return typefaceResult;
            }
        }

        SyncFontFetchTask(@NonNull SyncFontFetchTaskCallable syncFontFetchTaskCallable) {
            super(syncFontFetchTaskCallable);
        }

        SyncFontFetchTask(@NonNull SyncFontFetchTaskCallable syncFontFetchTaskCallable, @NonNull OnCompletedCallback<TypefaceResult> onCompletedCallback) {
            super(new CallableWrapper(syncFontFetchTaskCallable, onCompletedCallback));
        }
    }

    private static final class SyncFontFetchTaskCallable implements Callable<TypefaceResult> {
        @NonNull
        private final Context mAppContext;
        @NonNull
        private final String mCacheId;
        @NonNull
        private final FontRequest mRequest;
        private final int mStyle;

        SyncFontFetchTaskCallable(@NonNull Context context, @NonNull FontRequest fontRequest, int i, @NonNull String str) {
            this.mCacheId = str;
            this.mAppContext = context.getApplicationContext();
            this.mRequest = fontRequest;
            this.mStyle = i;
        }

        public TypefaceResult call() throws Exception {
            TypefaceResult fontInternal = FontsContractCompat.getFontInternal(this.mAppContext, this.mRequest, this.mStyle);
            if (fontInternal.mTypeface != null) {
                FontsContractCompat.sTypefaceCache.put(this.mCacheId, fontInternal.mTypeface);
            }
            return fontInternal;
        }
    }

    private static final class TypefaceResult {
        final int mResult;
        final Typeface mTypeface;

        TypefaceResult(@Nullable Typeface typeface, int i) {
            this.mTypeface = typeface;
            this.mResult = i;
        }
    }

    private FontsContractCompat() {
    }

    @NonNull
    static TypefaceResult getFontInternal(Context context, FontRequest fontRequest, int i) {
        try {
            FontFamilyResult fetchFonts = fetchFonts(context, null, fontRequest);
            int i2 = -3;
            if (fetchFonts.getStatusCode() == 0) {
                Typeface createFromFontInfo = TypefaceCompat.createFromFontInfo(context, null, fetchFonts.getFonts(), i);
                if (createFromFontInfo != null) {
                    i2 = 0;
                }
                return new TypefaceResult(createFromFontInfo, i2);
            }
            if (fetchFonts.getStatusCode() == 1) {
                i2 = -2;
            }
            return new TypefaceResult(null, i2);
        } catch (NameNotFoundException unused) {
            return new TypefaceResult(null, -1);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP_PREFIX})
    public static void resetCache() {
        sTypefaceCache.evictAll();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00b2, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00c3, code lost:
        if (r10 != null) goto L_0x00d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00c5, code lost:
        sBackgroundThread.postAndReply(r2, new androidx.core.provider.FontsContractCompat.C02543());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00d0, code lost:
        r10.execute(new androidx.core.provider.FontsContractCompat.SyncFontFetchTask(r2, new androidx.core.provider.FontsContractCompat.OnFetchCompletedAndFirePendingReplyCallback(r0)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00dd, code lost:
        return null;
     */
    @androidx.annotation.RestrictTo({androidx.annotation.RestrictTo.Scope.LIBRARY})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Typeface getFontSync(android.content.Context r3, androidx.core.provider.FontRequest r4, @androidx.annotation.Nullable final androidx.core.content.res.ResourcesCompat.FontCallback r5, @androidx.annotation.Nullable final android.os.Handler r6, boolean r7, int r8, int r9, boolean r10) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r4.getIdentifier()
            r0.append(r1)
            java.lang.String r1 = "-"
            r0.append(r1)
            r0.append(r9)
            java.lang.String r0 = r0.toString()
            androidx.collection.LruCache<java.lang.String, android.graphics.Typeface> r1 = sTypefaceCache
            java.lang.Object r1 = r1.get(r0)
            android.graphics.Typeface r1 = (android.graphics.Typeface) r1
            if (r1 == 0) goto L_0x0028
            if (r5 == 0) goto L_0x0027
            r5.onFontRetrieved(r1)
        L_0x0027:
            return r1
        L_0x0028:
            if (r7 == 0) goto L_0x0045
            r1 = -1
            if (r8 != r1) goto L_0x0045
            androidx.core.provider.FontsContractCompat$TypefaceResult r3 = getFontInternal(r3, r4, r9)
            if (r5 == 0) goto L_0x0042
            int r4 = r3.mResult
            if (r4 != 0) goto L_0x003d
            android.graphics.Typeface r4 = r3.mTypeface
            r5.callbackSuccessAsync(r4, r6)
            goto L_0x0042
        L_0x003d:
            int r4 = r3.mResult
            r5.callbackFailAsync(r4, r6)
        L_0x0042:
            android.graphics.Typeface r3 = r3.mTypeface
            return r3
        L_0x0045:
            r1 = 0
            if (r10 == 0) goto L_0x0064
            if (r6 != 0) goto L_0x0064
            java.util.concurrent.Executor r10 = sExecutor
            if (r10 != 0) goto L_0x0061
            java.lang.Object r10 = sLock
            monitor-enter(r10)
            java.util.concurrent.Executor r2 = sExecutor     // Catch:{ all -> 0x005e }
            if (r2 != 0) goto L_0x005c
            r2 = 1
            java.util.concurrent.ExecutorService r2 = java.util.concurrent.Executors.newFixedThreadPool(r2)     // Catch:{ all -> 0x005e }
            sExecutor = r2     // Catch:{ all -> 0x005e }
        L_0x005c:
            monitor-exit(r10)     // Catch:{ all -> 0x005e }
            goto L_0x0061
        L_0x005e:
            r3 = move-exception
            monitor-exit(r10)     // Catch:{ all -> 0x005e }
            throw r3
        L_0x0061:
            java.util.concurrent.Executor r10 = sExecutor
            goto L_0x0065
        L_0x0064:
            r10 = r1
        L_0x0065:
            androidx.core.provider.FontsContractCompat$SyncFontFetchTaskCallable r2 = new androidx.core.provider.FontsContractCompat$SyncFontFetchTaskCallable
            r2.<init>(r3, r4, r9, r0)
            if (r7 == 0) goto L_0x008e
            if (r10 != 0) goto L_0x0079
            androidx.core.provider.SelfDestructiveThread r3 = sBackgroundThread     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008d }
            java.lang.Object r3 = r3.postAndWait(r2, r8)     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008d }
            androidx.core.provider.FontsContractCompat$TypefaceResult r3 = (androidx.core.provider.FontsContractCompat.TypefaceResult) r3     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008d }
            android.graphics.Typeface r3 = r3.mTypeface     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008d }
            return r3
        L_0x0079:
            androidx.core.provider.FontsContractCompat$SyncFontFetchTask r3 = new androidx.core.provider.FontsContractCompat$SyncFontFetchTask     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008d }
            r3.<init>(r2)     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008d }
            r10.execute(r3)     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008d }
            long r4 = (long) r8     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008d }
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008d }
            java.lang.Object r3 = r3.get(r4, r6)     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008d }
            androidx.core.provider.FontsContractCompat$TypefaceResult r3 = (androidx.core.provider.FontsContractCompat.TypefaceResult) r3     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008d }
            android.graphics.Typeface r3 = r3.mTypeface     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008d }
            return r3
        L_0x008d:
            return r1
        L_0x008e:
            if (r5 == 0) goto L_0x009e
            if (r10 != 0) goto L_0x0098
            androidx.core.provider.FontsContractCompat$1 r3 = new androidx.core.provider.FontsContractCompat$1
            r3.<init>(r5, r6)
            goto L_0x009f
        L_0x0098:
            androidx.core.provider.FontsContractCompat$2 r3 = new androidx.core.provider.FontsContractCompat$2
            r3.<init>(r5)
            goto L_0x009f
        L_0x009e:
            r3 = r1
        L_0x009f:
            java.lang.Object r4 = sLock
            monitor-enter(r4)
            androidx.collection.SimpleArrayMap<java.lang.String, java.util.ArrayList<androidx.core.provider.SelfDestructiveThread$ReplyCallback<androidx.core.provider.FontsContractCompat$TypefaceResult>>> r5 = sPendingReplies     // Catch:{ all -> 0x00de }
            java.lang.Object r5 = r5.get(r0)     // Catch:{ all -> 0x00de }
            java.util.ArrayList r5 = (java.util.ArrayList) r5     // Catch:{ all -> 0x00de }
            if (r5 == 0) goto L_0x00b3
            if (r3 == 0) goto L_0x00b1
            r5.add(r3)     // Catch:{ all -> 0x00de }
        L_0x00b1:
            monitor-exit(r4)     // Catch:{ all -> 0x00de }
            return r1
        L_0x00b3:
            if (r3 == 0) goto L_0x00c2
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x00de }
            r5.<init>()     // Catch:{ all -> 0x00de }
            r5.add(r3)     // Catch:{ all -> 0x00de }
            androidx.collection.SimpleArrayMap<java.lang.String, java.util.ArrayList<androidx.core.provider.SelfDestructiveThread$ReplyCallback<androidx.core.provider.FontsContractCompat$TypefaceResult>>> r3 = sPendingReplies     // Catch:{ all -> 0x00de }
            r3.put(r0, r5)     // Catch:{ all -> 0x00de }
        L_0x00c2:
            monitor-exit(r4)     // Catch:{ all -> 0x00de }
            if (r10 != 0) goto L_0x00d0
            androidx.core.provider.SelfDestructiveThread r3 = sBackgroundThread
            androidx.core.provider.FontsContractCompat$3 r4 = new androidx.core.provider.FontsContractCompat$3
            r4.<init>(r0)
            r3.postAndReply(r2, r4)
            goto L_0x00dd
        L_0x00d0:
            androidx.core.provider.FontsContractCompat$SyncFontFetchTask r3 = new androidx.core.provider.FontsContractCompat$SyncFontFetchTask
            androidx.core.provider.FontsContractCompat$OnFetchCompletedAndFirePendingReplyCallback r4 = new androidx.core.provider.FontsContractCompat$OnFetchCompletedAndFirePendingReplyCallback
            r4.<init>(r0)
            r3.<init>(r2, r4)
            r10.execute(r3)
        L_0x00dd:
            return r1
        L_0x00de:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x00de }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontsContractCompat.getFontSync(android.content.Context, androidx.core.provider.FontRequest, androidx.core.content.res.ResourcesCompat$FontCallback, android.os.Handler, boolean, int, int, boolean):android.graphics.Typeface");
    }

    @RestrictTo({Scope.LIBRARY_GROUP_PREFIX})
    public static Typeface getFontSync(Context context, FontRequest fontRequest, @Nullable FontCallback fontCallback, @Nullable Handler handler, boolean z, int i, int i2) {
        return getFontSync(context, fontRequest, fontCallback, handler, z, i, i2, false);
    }

    public static void requestFont(@NonNull Context context, @NonNull FontRequest fontRequest, @NonNull FontRequestCallback fontRequestCallback, @NonNull Handler handler) {
        requestFontInternal(context.getApplicationContext(), fontRequest, fontRequestCallback, handler);
    }

    private static void requestFontInternal(@NonNull final Context context, @NonNull final FontRequest fontRequest, @NonNull final FontRequestCallback fontRequestCallback, @NonNull Handler handler) {
        final Handler handler2 = new Handler();
        handler.post(new Runnable() {
            public void run() {
                try {
                    FontFamilyResult fetchFonts = FontsContractCompat.fetchFonts(context, null, fontRequest);
                    if (fetchFonts.getStatusCode() != 0) {
                        int statusCode = fetchFonts.getStatusCode();
                        if (statusCode == 1) {
                            handler2.post(new Runnable() {
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(-2);
                                }
                            });
                        } else if (statusCode != 2) {
                            handler2.post(new Runnable() {
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(-3);
                                }
                            });
                        } else {
                            handler2.post(new Runnable() {
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(-3);
                                }
                            });
                        }
                    } else {
                        FontInfo[] fonts = fetchFonts.getFonts();
                        if (fonts == null || fonts.length == 0) {
                            handler2.post(new Runnable() {
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(1);
                                }
                            });
                            return;
                        }
                        for (FontInfo fontInfo : fonts) {
                            if (fontInfo.getResultCode() != 0) {
                                final int resultCode = fontInfo.getResultCode();
                                if (resultCode < 0) {
                                    handler2.post(new Runnable() {
                                        public void run() {
                                            fontRequestCallback.onTypefaceRequestFailed(-3);
                                        }
                                    });
                                } else {
                                    handler2.post(new Runnable() {
                                        public void run() {
                                            fontRequestCallback.onTypefaceRequestFailed(resultCode);
                                        }
                                    });
                                }
                                return;
                            }
                        }
                        final Typeface buildTypeface = FontsContractCompat.buildTypeface(context, null, fonts);
                        if (buildTypeface == null) {
                            handler2.post(new Runnable() {
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(-3);
                                }
                            });
                        } else {
                            handler2.post(new Runnable() {
                                public void run() {
                                    fontRequestCallback.onTypefaceRetrieved(buildTypeface);
                                }
                            });
                        }
                    }
                } catch (NameNotFoundException unused) {
                    handler2.post(new Runnable() {
                        public void run() {
                            fontRequestCallback.onTypefaceRequestFailed(-1);
                        }
                    });
                }
            }
        });
    }

    @Nullable
    public static Typeface buildTypeface(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontInfo[] fontInfoArr) {
        return TypefaceCompat.createFromFontInfo(context, cancellationSignal, fontInfoArr, 0);
    }

    @RequiresApi(19)
    @RestrictTo({Scope.LIBRARY_GROUP_PREFIX})
    public static Map<Uri, ByteBuffer> prepareFontData(Context context, FontInfo[] fontInfoArr, CancellationSignal cancellationSignal) {
        HashMap hashMap = new HashMap();
        for (FontInfo fontInfo : fontInfoArr) {
            if (fontInfo.getResultCode() == 0) {
                Uri uri = fontInfo.getUri();
                if (!hashMap.containsKey(uri)) {
                    hashMap.put(uri, TypefaceCompatUtil.mmap(context, cancellationSignal, uri));
                }
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    @NonNull
    public static FontFamilyResult fetchFonts(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontRequest fontRequest) throws NameNotFoundException {
        ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
        if (provider == null) {
            return new FontFamilyResult(1, null);
        }
        return new FontFamilyResult(0, getFontFromProvider(context, fontRequest, provider.authority, cancellationSignal));
    }

    @Nullable
    @RestrictTo({Scope.LIBRARY_GROUP_PREFIX})
    @VisibleForTesting
    public static ProviderInfo getProvider(@NonNull PackageManager packageManager, @NonNull FontRequest fontRequest, @Nullable Resources resources) throws NameNotFoundException {
        String providerAuthority = fontRequest.getProviderAuthority();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
        if (resolveContentProvider == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("No package found for authority: ");
            sb.append(providerAuthority);
            throw new NameNotFoundException(sb.toString());
        } else if (resolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
            List convertToByteArrayList = convertToByteArrayList(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
            Collections.sort(convertToByteArrayList, sByteArrayComparator);
            List certificates = getCertificates(fontRequest, resources);
            for (int i = 0; i < certificates.size(); i++) {
                ArrayList arrayList = new ArrayList((Collection) certificates.get(i));
                Collections.sort(arrayList, sByteArrayComparator);
                if (equalsByteArrayList(convertToByteArrayList, arrayList)) {
                    return resolveContentProvider;
                }
            }
            return null;
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Found content provider ");
            sb2.append(providerAuthority);
            sb2.append(", but package was not ");
            sb2.append(fontRequest.getProviderPackage());
            throw new NameNotFoundException(sb2.toString());
        }
    }

    private static List<List<byte[]>> getCertificates(FontRequest fontRequest, Resources resources) {
        if (fontRequest.getCertificates() != null) {
            return fontRequest.getCertificates();
        }
        return FontResourcesParserCompat.readCerts(resources, fontRequest.getCertificatesArrayResId());
    }

    private static boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals((byte[]) list.get(i), (byte[]) list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature byteArray : signatureArr) {
            arrayList.add(byteArray.toByteArray());
        }
        return arrayList;
    }

    /* JADX INFO: finally extract failed */
    @VisibleForTesting
    @NonNull
    static FontInfo[] getFontFromProvider(Context context, FontRequest fontRequest, String str, CancellationSignal cancellationSignal) {
        Cursor cursor;
        Uri uri;
        String str2 = str;
        ArrayList arrayList = new ArrayList();
        Builder builder = new Builder();
        String str3 = Param.CONTENT;
        Uri build = builder.scheme(str3).authority(str2).build();
        Uri build2 = new Builder().scheme(str3).authority(str2).appendPath("file").build();
        Cursor cursor2 = null;
        try {
            if (VERSION.SDK_INT > 16) {
                cursor = context.getContentResolver().query(build, new String[]{"_id", Columns.FILE_ID, Columns.TTC_INDEX, Columns.VARIATION_SETTINGS, Columns.WEIGHT, Columns.ITALIC, Columns.RESULT_CODE}, "query = ?", new String[]{fontRequest.getQuery()}, null, cancellationSignal);
            } else {
                cursor = context.getContentResolver().query(build, new String[]{"_id", Columns.FILE_ID, Columns.TTC_INDEX, Columns.VARIATION_SETTINGS, Columns.WEIGHT, Columns.ITALIC, Columns.RESULT_CODE}, "query = ?", new String[]{fontRequest.getQuery()}, null);
            }
            if (cursor != null && cursor.getCount() > 0) {
                int columnIndex = cursor.getColumnIndex(Columns.RESULT_CODE);
                ArrayList arrayList2 = new ArrayList();
                int columnIndex2 = cursor.getColumnIndex("_id");
                int columnIndex3 = cursor.getColumnIndex(Columns.FILE_ID);
                int columnIndex4 = cursor.getColumnIndex(Columns.TTC_INDEX);
                int columnIndex5 = cursor.getColumnIndex(Columns.WEIGHT);
                int columnIndex6 = cursor.getColumnIndex(Columns.ITALIC);
                while (cursor.moveToNext()) {
                    int i = columnIndex != -1 ? cursor.getInt(columnIndex) : 0;
                    int i2 = columnIndex4 != -1 ? cursor.getInt(columnIndex4) : 0;
                    if (columnIndex3 == -1) {
                        uri = ContentUris.withAppendedId(build, cursor.getLong(columnIndex2));
                    } else {
                        uri = ContentUris.withAppendedId(build2, cursor.getLong(columnIndex3));
                    }
                    FontInfo fontInfo = new FontInfo(uri, i2, columnIndex5 != -1 ? cursor.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursor.getInt(columnIndex6) == 1, i);
                    arrayList2.add(fontInfo);
                }
                arrayList = arrayList2;
            }
            if (cursor != null) {
                cursor.close();
            }
            return (FontInfo[]) arrayList.toArray(new FontInfo[0]);
        } catch (Throwable th) {
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }
}
