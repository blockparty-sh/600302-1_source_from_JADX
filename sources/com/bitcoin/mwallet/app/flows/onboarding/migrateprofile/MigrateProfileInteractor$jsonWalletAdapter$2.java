package com.bitcoin.mwallet.app.flows.onboarding.migrateprofile;

import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldProfile;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Moshi.Builder;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldProfile;", "kotlin.jvm.PlatformType", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: MigrateProfileInteractor.kt */
final class MigrateProfileInteractor$jsonWalletAdapter$2 extends Lambda implements Function0<JsonAdapter<OldProfile>> {
    public static final MigrateProfileInteractor$jsonWalletAdapter$2 INSTANCE = new MigrateProfileInteractor$jsonWalletAdapter$2();

    MigrateProfileInteractor$jsonWalletAdapter$2() {
        super(0);
    }

    public final JsonAdapter<OldProfile> invoke() {
        Moshi build = new Builder().build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Moshi.Builder().build()");
        return build.adapter(OldProfile.class);
    }
}
