package org.koin.android.ext.koin;

import android.app.Application;
import android.content.Context;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.koin.android.logger.AndroidLogger;
import org.koin.core.KoinApplication;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.DefinitionFactory;
import org.koin.core.definition.Kind;
import org.koin.core.logger.Level;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.registry.BeanRegistry;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0000\u001a\u00020\u0002\u001a\u0014\u0010\u0003\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u001a\u0014\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b¨\u0006\t"}, mo37405d2 = {"androidContext", "Lorg/koin/core/KoinApplication;", "Landroid/content/Context;", "androidFileProperties", "koinPropertyFile", "", "androidLogger", "level", "Lorg/koin/core/logger/Level;", "koin-android_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: KoinExt.kt */
public final class KoinExtKt {
    @NotNull
    public static /* synthetic */ KoinApplication androidLogger$default(KoinApplication koinApplication, Level level, int i, Object obj) {
        if ((i & 1) != 0) {
            level = Level.INFO;
        }
        return androidLogger(koinApplication, level);
    }

    @NotNull
    public static final KoinApplication androidLogger(@NotNull KoinApplication koinApplication, @NotNull Level level) {
        Intrinsics.checkParameterIsNotNull(koinApplication, "$this$androidLogger");
        Intrinsics.checkParameterIsNotNull(level, Param.LEVEL);
        KoinApplication.Companion.setLogger(new AndroidLogger(level));
        return koinApplication;
    }

    @NotNull
    public static final KoinApplication androidContext(@NotNull KoinApplication koinApplication, @NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(koinApplication, "$this$androidContext");
        Intrinsics.checkParameterIsNotNull(context, "androidContext");
        if (KoinApplication.Companion.getLogger().isAt(Level.INFO)) {
            KoinApplication.Companion.getLogger().info("[init] declare Android Context");
        }
        BeanRegistry beanRegistry = koinApplication.getKoin().getRootScope().getBeanRegistry();
        DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
        Function2 koinExtKt$androidContext$1 = new KoinExtKt$androidContext$1(context);
        Qualifier qualifier = null;
        Kind kind = Kind.Single;
        BeanDefinition beanDefinition = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(Context.class));
        beanDefinition.setDefinition(koinExtKt$androidContext$1);
        beanDefinition.setKind(kind);
        beanRegistry.saveDefinition(beanDefinition);
        if (context instanceof Application) {
            BeanRegistry beanRegistry2 = koinApplication.getKoin().getRootScope().getBeanRegistry();
            DefinitionFactory definitionFactory2 = DefinitionFactory.INSTANCE;
            Function2 koinExtKt$androidContext$2 = new KoinExtKt$androidContext$2(context);
            Kind kind2 = Kind.Single;
            BeanDefinition beanDefinition2 = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(Application.class));
            beanDefinition2.setDefinition(koinExtKt$androidContext$2);
            beanDefinition2.setKind(kind2);
            beanRegistry2.saveDefinition(beanDefinition2);
        }
        return koinApplication;
    }

    @NotNull
    public static /* synthetic */ KoinApplication androidFileProperties$default(KoinApplication koinApplication, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "koin.properties";
        }
        return androidFileProperties(koinApplication, str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00b7 A[Catch:{ Exception -> 0x00d1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0040 A[SYNTHETIC, Splitter:B:9:0x0040] */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final org.koin.core.KoinApplication androidFileProperties(@org.jetbrains.annotations.NotNull org.koin.core.KoinApplication r6, @org.jetbrains.annotations.NotNull java.lang.String r7) {
        /*
            java.lang.String r0 = "$this$androidFileProperties"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            java.lang.String r0 = "koinPropertyFile"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            java.util.Properties r0 = new java.util.Properties
            r0.<init>()
            org.koin.core.Koin r1 = r6.getKoin()
            r2 = 0
            r3 = r2
            org.koin.core.qualifier.Qualifier r3 = (org.koin.core.qualifier.Qualifier) r3
            r4 = r2
            kotlin.jvm.functions.Function0 r4 = (kotlin.jvm.functions.Function0) r4
            org.koin.core.scope.Scope r1 = r1.getRootScope()
            java.lang.Class<android.content.Context> r5 = android.content.Context.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            java.lang.Object r1 = r1.get(r5, r3, r4)
            android.content.Context r1 = (android.content.Context) r1
            android.content.res.AssetManager r3 = r1.getAssets()     // Catch:{ Exception -> 0x00d1 }
            if (r3 == 0) goto L_0x003d
            java.lang.String r4 = ""
            java.lang.String[] r3 = r3.list(r4)     // Catch:{ Exception -> 0x00d1 }
            if (r3 == 0) goto L_0x003d
            boolean r3 = kotlin.collections.ArraysKt.contains((T[]) r3, r7)     // Catch:{ Exception -> 0x00d1 }
            goto L_0x003e
        L_0x003d:
            r3 = 0
        L_0x003e:
            if (r3 == 0) goto L_0x00b7
            android.content.res.AssetManager r1 = r1.getAssets()     // Catch:{ Exception -> 0x009b }
            java.io.InputStream r7 = r1.open(r7)     // Catch:{ Exception -> 0x009b }
            java.io.Closeable r7 = (java.io.Closeable) r7     // Catch:{ Exception -> 0x009b }
            java.lang.Throwable r2 = (java.lang.Throwable) r2     // Catch:{ Exception -> 0x009b }
            r1 = r7
            java.io.InputStream r1 = (java.io.InputStream) r1     // Catch:{ Throwable -> 0x0094 }
            r0.load(r1)     // Catch:{ Throwable -> 0x0094 }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0094 }
            kotlin.p016io.CloseableKt.closeFinally(r7, r2)     // Catch:{ Exception -> 0x009b }
            org.koin.core.Koin r7 = r6.getKoin()     // Catch:{ Exception -> 0x009b }
            org.koin.core.registry.PropertyRegistry r7 = r7.getPropertyRegistry()     // Catch:{ Exception -> 0x009b }
            r7.saveProperties(r0)     // Catch:{ Exception -> 0x009b }
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x009b }
            org.koin.core.KoinApplication$Companion r0 = org.koin.core.KoinApplication.Companion     // Catch:{ Exception -> 0x009b }
            org.koin.core.logger.Logger r0 = r0.getLogger()     // Catch:{ Exception -> 0x009b }
            org.koin.core.logger.Level r1 = org.koin.core.logger.Level.INFO     // Catch:{ Exception -> 0x009b }
            boolean r0 = r0.isAt(r1)     // Catch:{ Exception -> 0x009b }
            if (r0 == 0) goto L_0x00ec
            org.koin.core.KoinApplication$Companion r0 = org.koin.core.KoinApplication.Companion     // Catch:{ Exception -> 0x009b }
            org.koin.core.logger.Logger r0 = r0.getLogger()     // Catch:{ Exception -> 0x009b }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009b }
            r1.<init>()     // Catch:{ Exception -> 0x009b }
            java.lang.String r2 = "[Android-Properties] loaded "
            r1.append(r2)     // Catch:{ Exception -> 0x009b }
            r1.append(r7)     // Catch:{ Exception -> 0x009b }
            java.lang.String r7 = " properties from assets/koin.properties"
            r1.append(r7)     // Catch:{ Exception -> 0x009b }
            java.lang.String r7 = r1.toString()     // Catch:{ Exception -> 0x009b }
            r0.info(r7)     // Catch:{ Exception -> 0x009b }
            goto L_0x00ec
        L_0x0092:
            r0 = move-exception
            goto L_0x0097
        L_0x0094:
            r0 = move-exception
            r2 = r0
            throw r2     // Catch:{ all -> 0x0092 }
        L_0x0097:
            kotlin.p016io.CloseableKt.closeFinally(r7, r2)     // Catch:{ Exception -> 0x009b }
            throw r0     // Catch:{ Exception -> 0x009b }
        L_0x009b:
            r7 = move-exception
            org.koin.core.KoinApplication$Companion r0 = org.koin.core.KoinApplication.Companion     // Catch:{ Exception -> 0x00d1 }
            org.koin.core.logger.Logger r0 = r0.getLogger()     // Catch:{ Exception -> 0x00d1 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00d1 }
            r1.<init>()     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r2 = "[Android-Properties] error for binding properties : "
            r1.append(r2)     // Catch:{ Exception -> 0x00d1 }
            r1.append(r7)     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r7 = r1.toString()     // Catch:{ Exception -> 0x00d1 }
            r0.error(r7)     // Catch:{ Exception -> 0x00d1 }
            goto L_0x00ec
        L_0x00b7:
            org.koin.core.KoinApplication$Companion r7 = org.koin.core.KoinApplication.Companion     // Catch:{ Exception -> 0x00d1 }
            org.koin.core.logger.Logger r7 = r7.getLogger()     // Catch:{ Exception -> 0x00d1 }
            org.koin.core.logger.Level r0 = org.koin.core.logger.Level.INFO     // Catch:{ Exception -> 0x00d1 }
            boolean r7 = r7.isAt(r0)     // Catch:{ Exception -> 0x00d1 }
            if (r7 == 0) goto L_0x00ec
            org.koin.core.KoinApplication$Companion r7 = org.koin.core.KoinApplication.Companion     // Catch:{ Exception -> 0x00d1 }
            org.koin.core.logger.Logger r7 = r7.getLogger()     // Catch:{ Exception -> 0x00d1 }
            java.lang.String r0 = "[Android-Properties] no assets/koin.properties file to load"
            r7.info(r0)     // Catch:{ Exception -> 0x00d1 }
            goto L_0x00ec
        L_0x00d1:
            r7 = move-exception
            org.koin.core.KoinApplication$Companion r0 = org.koin.core.KoinApplication.Companion
            org.koin.core.logger.Logger r0 = r0.getLogger()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "[Android-Properties] error while loading properties from assets/koin.properties : "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            r0.error(r7)
        L_0x00ec:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.koin.android.ext.koin.KoinExtKt.androidFileProperties(org.koin.core.KoinApplication, java.lang.String):org.koin.core.KoinApplication");
    }
}
