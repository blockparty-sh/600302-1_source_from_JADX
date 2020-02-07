package androidx.core.graphics;

import android.graphics.ImageDecoder;
import android.graphics.ImageDecoder.ImageInfo;
import android.graphics.ImageDecoder.OnHeaderDecodedListener;
import android.graphics.ImageDecoder.Source;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.leanplum.internal.Constants.Params;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, mo37405d2 = {"<anonymous>", "", "decoder", "Landroid/graphics/ImageDecoder;", "info", "Landroid/graphics/ImageDecoder$ImageInfo;", "source", "Landroid/graphics/ImageDecoder$Source;", "onHeaderDecoded"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ImageDecoder.kt */
public final class ImageDecoderKt$decodeBitmap$1 implements OnHeaderDecodedListener {
    final /* synthetic */ Function3 $action;

    public ImageDecoderKt$decodeBitmap$1(Function3 function3) {
        this.$action = function3;
    }

    public final void onHeaderDecoded(@NotNull ImageDecoder imageDecoder, @NotNull ImageInfo imageInfo, @NotNull Source source) {
        Intrinsics.checkParameterIsNotNull(imageDecoder, "decoder");
        Intrinsics.checkParameterIsNotNull(imageInfo, Params.INFO);
        Intrinsics.checkParameterIsNotNull(source, Param.SOURCE);
        this.$action.invoke(imageDecoder, imageInfo, source);
    }
}
