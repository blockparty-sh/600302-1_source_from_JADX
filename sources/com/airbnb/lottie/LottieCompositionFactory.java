package com.airbnb.lottie;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.JsonReader;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.annotation.WorkerThread;
import com.airbnb.lottie.model.LottieCompositionCache;
import com.airbnb.lottie.network.NetworkFetcher;
import com.airbnb.lottie.parser.LottieCompositionParser;
import com.airbnb.lottie.utils.C0878Utils;
import com.microsoft.appcenter.crashes.utils.ErrorLogHelper;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.json.JSONObject;

public class LottieCompositionFactory {
    /* access modifiers changed from: private */
    public static final Map<String, LottieTask<LottieComposition>> taskCache = new HashMap();

    private LottieCompositionFactory() {
    }

    public static void setMaxCacheSize(int i) {
        LottieCompositionCache.getInstance().resize(i);
    }

    public static LottieTask<LottieComposition> fromUrl(final Context context, final String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("url_");
        sb.append(str);
        return cache(sb.toString(), new Callable<LottieResult<LottieComposition>>() {
            public LottieResult<LottieComposition> call() {
                return NetworkFetcher.fetchSync(context, str);
            }
        });
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromUrlSync(Context context, String str) {
        return NetworkFetcher.fetchSync(context, str);
    }

    public static LottieTask<LottieComposition> fromAsset(Context context, final String str) {
        final Context applicationContext = context.getApplicationContext();
        return cache(str, new Callable<LottieResult<LottieComposition>>() {
            public LottieResult<LottieComposition> call() {
                return LottieCompositionFactory.fromAssetSync(applicationContext, str);
            }
        });
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromAssetSync(Context context, String str) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("asset_");
            sb.append(str);
            String sb2 = sb.toString();
            if (str.endsWith(".zip")) {
                return fromZipStreamSync(new ZipInputStream(context.getAssets().open(str)), sb2);
            }
            return fromJsonInputStreamSync(context.getAssets().open(str), sb2);
        } catch (IOException e) {
            return new LottieResult<>((Throwable) e);
        }
    }

    public static LottieTask<LottieComposition> fromRawRes(Context context, @RawRes final int i) {
        final Context applicationContext = context.getApplicationContext();
        return cache(rawResCacheKey(i), new Callable<LottieResult<LottieComposition>>() {
            public LottieResult<LottieComposition> call() {
                return LottieCompositionFactory.fromRawResSync(applicationContext, i);
            }
        });
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromRawResSync(Context context, @RawRes int i) {
        try {
            return fromJsonInputStreamSync(context.getResources().openRawResource(i), rawResCacheKey(i));
        } catch (NotFoundException e) {
            return new LottieResult<>((Throwable) e);
        }
    }

    private static String rawResCacheKey(@RawRes int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("rawRes_");
        sb.append(i);
        return sb.toString();
    }

    public static LottieTask<LottieComposition> fromJsonInputStream(final InputStream inputStream, @Nullable final String str) {
        return cache(str, new Callable<LottieResult<LottieComposition>>() {
            public LottieResult<LottieComposition> call() {
                return LottieCompositionFactory.fromJsonInputStreamSync(inputStream, str);
            }
        });
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromJsonInputStreamSync(InputStream inputStream, @Nullable String str) {
        return fromJsonInputStreamSync(inputStream, str, true);
    }

    @WorkerThread
    private static LottieResult<LottieComposition> fromJsonInputStreamSync(InputStream inputStream, @Nullable String str, boolean z) {
        try {
            return fromJsonReaderSync(new JsonReader(new InputStreamReader(inputStream)), str);
        } finally {
            if (z) {
                C0878Utils.closeQuietly(inputStream);
            }
        }
    }

    @Deprecated
    public static LottieTask<LottieComposition> fromJson(final JSONObject jSONObject, @Nullable final String str) {
        return cache(str, new Callable<LottieResult<LottieComposition>>() {
            public LottieResult<LottieComposition> call() {
                return LottieCompositionFactory.fromJsonSync(jSONObject, str);
            }
        });
    }

    @WorkerThread
    @Deprecated
    public static LottieResult<LottieComposition> fromJsonSync(JSONObject jSONObject, @Nullable String str) {
        return fromJsonStringSync(jSONObject.toString(), str);
    }

    public static LottieTask<LottieComposition> fromJsonString(final String str, @Nullable final String str2) {
        return cache(str2, new Callable<LottieResult<LottieComposition>>() {
            public LottieResult<LottieComposition> call() {
                return LottieCompositionFactory.fromJsonStringSync(str, str2);
            }
        });
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromJsonStringSync(String str, @Nullable String str2) {
        return fromJsonReaderSync(new JsonReader(new StringReader(str)), str2);
    }

    public static LottieTask<LottieComposition> fromJsonReader(final JsonReader jsonReader, @Nullable final String str) {
        return cache(str, new Callable<LottieResult<LottieComposition>>() {
            public LottieResult<LottieComposition> call() {
                return LottieCompositionFactory.fromJsonReaderSync(jsonReader, str);
            }
        });
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromJsonReaderSync(JsonReader jsonReader, @Nullable String str) {
        return fromJsonReaderSyncInternal(jsonReader, str, true);
    }

    private static LottieResult<LottieComposition> fromJsonReaderSyncInternal(JsonReader jsonReader, @Nullable String str, boolean z) {
        try {
            LottieComposition parse = LottieCompositionParser.parse(jsonReader);
            LottieCompositionCache.getInstance().put(str, parse);
            LottieResult<LottieComposition> lottieResult = new LottieResult<>(parse);
            if (z) {
                C0878Utils.closeQuietly(jsonReader);
            }
            return lottieResult;
        } catch (Exception e) {
            LottieResult<LottieComposition> lottieResult2 = new LottieResult<>((Throwable) e);
            if (z) {
                C0878Utils.closeQuietly(jsonReader);
            }
            return lottieResult2;
        } catch (Throwable th) {
            if (z) {
                C0878Utils.closeQuietly(jsonReader);
            }
            throw th;
        }
    }

    public static LottieTask<LottieComposition> fromZipStream(final ZipInputStream zipInputStream, @Nullable final String str) {
        return cache(str, new Callable<LottieResult<LottieComposition>>() {
            public LottieResult<LottieComposition> call() {
                return LottieCompositionFactory.fromZipStreamSync(zipInputStream, str);
            }
        });
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromZipStreamSync(ZipInputStream zipInputStream, @Nullable String str) {
        try {
            return fromZipStreamSyncInternal(zipInputStream, str);
        } finally {
            C0878Utils.closeQuietly(zipInputStream);
        }
    }

    @WorkerThread
    private static LottieResult<LottieComposition> fromZipStreamSyncInternal(ZipInputStream zipInputStream, @Nullable String str) {
        HashMap hashMap = new HashMap();
        try {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            LottieComposition lottieComposition = null;
            while (nextEntry != null) {
                if (nextEntry.getName().contains("__MACOSX")) {
                    zipInputStream.closeEntry();
                } else if (nextEntry.getName().contains(ErrorLogHelper.ERROR_LOG_FILE_EXTENSION)) {
                    lottieComposition = (LottieComposition) fromJsonReaderSyncInternal(new JsonReader(new InputStreamReader(zipInputStream)), null, false).getValue();
                } else if (nextEntry.getName().contains(".png")) {
                    String[] split = nextEntry.getName().split("/");
                    hashMap.put(split[split.length - 1], BitmapFactory.decodeStream(zipInputStream));
                } else {
                    zipInputStream.closeEntry();
                }
                nextEntry = zipInputStream.getNextEntry();
            }
            if (lottieComposition == null) {
                return new LottieResult<>((Throwable) new IllegalArgumentException("Unable to parse composition"));
            }
            for (Entry entry : hashMap.entrySet()) {
                LottieImageAsset findImageAssetForFileName = findImageAssetForFileName(lottieComposition, (String) entry.getKey());
                if (findImageAssetForFileName != null) {
                    findImageAssetForFileName.setBitmap((Bitmap) entry.getValue());
                }
            }
            for (Entry entry2 : lottieComposition.getImages().entrySet()) {
                if (((LottieImageAsset) entry2.getValue()).getBitmap() == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("There is no image for ");
                    sb.append(((LottieImageAsset) entry2.getValue()).getFileName());
                    return new LottieResult<>((Throwable) new IllegalStateException(sb.toString()));
                }
            }
            LottieCompositionCache.getInstance().put(str, lottieComposition);
            return new LottieResult<>(lottieComposition);
        } catch (IOException e) {
            return new LottieResult<>((Throwable) e);
        }
    }

    @Nullable
    private static LottieImageAsset findImageAssetForFileName(LottieComposition lottieComposition, String str) {
        for (LottieImageAsset lottieImageAsset : lottieComposition.getImages().values()) {
            if (lottieImageAsset.getFileName().equals(str)) {
                return lottieImageAsset;
            }
        }
        return null;
    }

    private static LottieTask<LottieComposition> cache(@Nullable final String str, Callable<LottieResult<LottieComposition>> callable) {
        final LottieComposition lottieComposition = str == null ? null : LottieCompositionCache.getInstance().get(str);
        if (lottieComposition != null) {
            return new LottieTask<>(new Callable<LottieResult<LottieComposition>>() {
                public LottieResult<LottieComposition> call() {
                    return new LottieResult<>(lottieComposition);
                }
            });
        }
        if (str != null && taskCache.containsKey(str)) {
            return (LottieTask) taskCache.get(str);
        }
        LottieTask<LottieComposition> lottieTask = new LottieTask<>(callable);
        lottieTask.addListener(new LottieListener<LottieComposition>() {
            public void onResult(LottieComposition lottieComposition) {
                if (str != null) {
                    LottieCompositionCache.getInstance().put(str, lottieComposition);
                }
                LottieCompositionFactory.taskCache.remove(str);
            }
        });
        lottieTask.addFailureListener(new LottieListener<Throwable>() {
            public void onResult(Throwable th) {
                LottieCompositionFactory.taskCache.remove(str);
            }
        });
        taskCache.put(str, lottieTask);
        return lottieTask;
    }
}
