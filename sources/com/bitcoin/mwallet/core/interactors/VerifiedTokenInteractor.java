package com.bitcoin.mwallet.core.interactors;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.widget.ImageView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken;
import com.bitcoin.mwallet.core.repositories.VerifiedTokenRepository;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.BooleanRef;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002J\u001e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u001e\u0010\u0016\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0019"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "", "verifiedTokenRepository", "Lcom/bitcoin/mwallet/core/repositories/VerifiedTokenRepository;", "(Lcom/bitcoin/mwallet/core/repositories/VerifiedTokenRepository;)V", "getVerifiedTokenRepository", "()Lcom/bitcoin/mwallet/core/repositories/VerifiedTokenRepository;", "getImageDrawable", "Landroidx/core/graphics/drawable/RoundedBitmapDrawable;", "resources", "Landroid/content/res/Resources;", "imageBitmap", "Landroid/graphics/Bitmap;", "getVerifiedTokenList", "", "Lcom/bitcoin/mwallet/core/models/verifiedtoken/VerifiedToken;", "trySetImage", "", "imageView", "Landroid/widget/ImageView;", "imagePath", "", "trySetVerifiedTokenImage", "tokenId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: VerifiedTokenInteractor.kt */
public final class VerifiedTokenInteractor {
    @NotNull
    private final VerifiedTokenRepository verifiedTokenRepository;

    public VerifiedTokenInteractor(@NotNull VerifiedTokenRepository verifiedTokenRepository2) {
        Intrinsics.checkParameterIsNotNull(verifiedTokenRepository2, "verifiedTokenRepository");
        this.verifiedTokenRepository = verifiedTokenRepository2;
    }

    @NotNull
    public final VerifiedTokenRepository getVerifiedTokenRepository() {
        return this.verifiedTokenRepository;
    }

    private final List<VerifiedToken> getVerifiedTokenList() {
        return (List) BuildersKt__BuildersKt.runBlocking$default(null, new VerifiedTokenInteractor$getVerifiedTokenList$1(this, null), 1, null);
    }

    public final boolean trySetImage(@NotNull Resources resources, @NotNull ImageView imageView, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(resources, "resources");
        Intrinsics.checkParameterIsNotNull(imageView, "imageView");
        Intrinsics.checkParameterIsNotNull(str, "imagePath");
        BooleanRef booleanRef = new BooleanRef();
        booleanRef.element = false;
        Picasso.get().load(str).into(imageView, new VerifiedTokenInteractor$trySetImage$1(this, imageView, resources, booleanRef));
        return booleanRef.element;
    }

    public final boolean trySetVerifiedTokenImage(@NotNull Resources resources, @NotNull ImageView imageView, @NotNull SlpId slpId) {
        String str;
        Object obj;
        Intrinsics.checkParameterIsNotNull(resources, "resources");
        Intrinsics.checkParameterIsNotNull(imageView, "imageView");
        Intrinsics.checkParameterIsNotNull(slpId, "tokenId");
        List verifiedTokenList = getVerifiedTokenList();
        BooleanRef booleanRef = new BooleanRef();
        booleanRef.element = false;
        Iterator it = verifiedTokenList.iterator();
        while (true) {
            str = null;
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual((Object) ((VerifiedToken) obj).getId(), (Object) slpId.getId())) {
                break;
            }
        }
        VerifiedToken verifiedToken = (VerifiedToken) obj;
        Picasso picasso = Picasso.get();
        if (verifiedToken != null) {
            str = verifiedToken.getIconImageUrl();
        }
        RequestCreator load = picasso.load(str);
        C1260x77dad321 verifiedTokenInteractor$trySetVerifiedTokenImage$$inlined$let$lambda$1 = new C1260x77dad321(this, verifiedToken, imageView, resources, booleanRef);
        load.into(imageView, verifiedTokenInteractor$trySetVerifiedTokenImage$$inlined$let$lambda$1);
        return booleanRef.element;
    }

    /* access modifiers changed from: private */
    public final RoundedBitmapDrawable getImageDrawable(Resources resources, Bitmap bitmap) {
        RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(resources, bitmap);
        Intrinsics.checkExpressionValueIsNotNull(create, "RoundedBitmapDrawableFac…e(resources, imageBitmap)");
        create.setCircular(true);
        create.setCornerRadius(((float) bitmap.getHeight()) / 2.0f);
        return create;
    }
}
