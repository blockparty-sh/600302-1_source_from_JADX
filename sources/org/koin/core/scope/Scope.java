package org.koin.core.scope;

import androidx.exifinterface.media.ExifInterface;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.koin.core.Koin;
import org.koin.core.KoinApplication;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.DefinitionFactory;
import org.koin.core.definition.Kind;
import org.koin.core.error.MissingPropertyException;
import org.koin.core.error.NoBeanDefFoundException;
import org.koin.core.instance.DefinitionInstance;
import org.koin.core.instance.InstanceContext;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.registry.BeanRegistry;
import org.koin.core.time.MeasureKt;
import org.koin.ext.KClassExtKt;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\b\u0018\u00002\u00020\u0001B#\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ?\u0010\u001d\u001a\u0002H\u001e\"\u0004\b\u0000\u0010\u001e2\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 2\n\u0010!\u001a\u0006\u0012\u0002\b\u00030 2\u0014\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0004\u0018\u0001`%¢\u0006\u0002\u0010&J6\u0010\u001d\u001a\u0002H\u001e\"\u0006\b\u0000\u0010\u001e\u0018\u0001\"\u0006\b\u0001\u0010'\u0018\u00012\u0016\b\n\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0004\u0018\u0001`%H\b¢\u0006\u0002\u0010(J\u0006\u0010)\u001a\u00020*J\r\u0010+\u001a\u00060\u0003j\u0002`\u0004HÆ\u0003J\t\u0010,\u001a\u00020\u0006HÆ\u0003J\u000e\u0010-\u001a\u00020\bHÀ\u0003¢\u0006\u0002\b.J+\u0010/\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\r\u00100\u001a\u00020*H\u0000¢\u0006\u0002\b1J@\u00102\u001a\u00020*\"\u0006\b\u0000\u00103\u0018\u00012\u0006\u00104\u001a\u0002H32\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\u0014\b\u0002\u00107\u001a\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030 \u0018\u000108H\b¢\u0006\u0002\u00109J\r\u0010:\u001a\u00020*H\u0000¢\u0006\u0002\b;J\u0013\u0010<\u001a\u00020\u00062\b\u0010=\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\"\u0010>\u001a\u0006\u0012\u0002\b\u00030?2\b\u00105\u001a\u0004\u0018\u0001062\n\u0010@\u001a\u0006\u0012\u0002\b\u00030 H\u0002J=\u0010A\u001a\u0002H3\"\u0004\b\u0000\u001032\n\u0010@\u001a\u0006\u0012\u0002\b\u00030 2\b\u00105\u001a\u0004\u0018\u0001062\u0014\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0004\u0018\u0001`%¢\u0006\u0002\u0010BJ:\u0010A\u001a\u0002H3\"\u0006\b\u0000\u00103\u0018\u00012\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\u0016\b\n\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0004\u0018\u0001`%H\b¢\u0006\u0002\u0010CJ\u0017\u0010D\u001a\b\u0012\u0004\u0012\u0002H308\"\u0006\b\u0000\u00103\u0018\u0001H\bJ\u001e\u0010D\u001a\b\u0012\u0004\u0012\u0002H308\"\u0004\b\u0000\u001032\n\u0010@\u001a\u0006\u0012\u0002\b\u00030 J\u0006\u0010E\u001a\u00020\bJ<\u0010F\u001a\u0004\u0018\u0001H3\"\u0006\b\u0000\u00103\u0018\u00012\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\u0016\b\n\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0004\u0018\u0001`%H\b¢\u0006\u0002\u0010CJ\u0019\u0010G\u001a\u0002H3\"\u0004\b\u0000\u001032\u0006\u0010H\u001a\u00020\u0003¢\u0006\u0002\u0010IJ!\u0010G\u001a\u0002H3\"\u0004\b\u0000\u001032\u0006\u0010H\u001a\u00020\u00032\u0006\u0010J\u001a\u0002H3¢\u0006\u0002\u0010KJ\u001b\u0010L\u001a\u0004\u0018\u0001H3\"\u0004\b\u0000\u001032\u0006\u0010H\u001a\u00020\u0003¢\u0006\u0002\u0010IJ\u0012\u0010M\u001a\u00020\u00002\n\u0010N\u001a\u00060\u0003j\u0002`\u0004J\t\u0010O\u001a\u00020PHÖ\u0001J;\u0010Q\u001a\b\u0012\u0004\u0012\u0002H30R\"\u0006\b\u0000\u00103\u0018\u00012\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\u0016\b\n\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0004\u0018\u0001`%H\bJ=\u0010S\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H30R\"\u0006\b\u0000\u00103\u0018\u00012\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\u0016\b\n\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0004\u0018\u0001`%H\bJ\u000e\u0010T\u001a\u00020*2\u0006\u0010U\u001a\u00020\u0012J?\u0010V\u001a\u0002H3\"\u0004\b\u0000\u001032\b\u00105\u001a\u0004\u0018\u0001062\n\u0010@\u001a\u0006\u0012\u0002\b\u00030 2\u0014\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0004\u0018\u0001`%H\u0002¢\u0006\u0002\u0010WJ\b\u0010X\u001a\u00020\u0003H\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0011j\b\u0012\u0004\u0012\u00020\u0012`\u0013X\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0016R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006Y"}, mo37405d2 = {"Lorg/koin/core/scope/Scope;", "", "id", "", "Lorg/koin/core/scope/ScopeID;", "isRoot", "", "_koin", "Lorg/koin/core/Koin;", "(Ljava/lang/String;ZLorg/koin/core/Koin;)V", "get_koin$koin_core", "()Lorg/koin/core/Koin;", "beanRegistry", "Lorg/koin/core/registry/BeanRegistry;", "getBeanRegistry", "()Lorg/koin/core/registry/BeanRegistry;", "callbacks", "Ljava/util/ArrayList;", "Lorg/koin/core/scope/ScopeCallback;", "Lkotlin/collections/ArrayList;", "getId", "()Ljava/lang/String;", "()Z", "set", "Lorg/koin/core/scope/ScopeDefinition;", "getSet", "()Lorg/koin/core/scope/ScopeDefinition;", "setSet", "(Lorg/koin/core/scope/ScopeDefinition;)V", "bind", "S", "primaryType", "Lkotlin/reflect/KClass;", "secondaryType", "parameters", "Lkotlin/Function0;", "Lorg/koin/core/parameter/DefinitionParameters;", "Lorg/koin/core/parameter/ParametersDefinition;", "(Lkotlin/reflect/KClass;Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "P", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "close", "", "component1", "component2", "component3", "component3$koin_core", "copy", "createEagerInstances", "createEagerInstances$koin_core", "declare", "T", "instance", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "secondaryTypes", "", "(Ljava/lang/Object;Lorg/koin/core/qualifier/Qualifier;Ljava/util/List;)V", "declareDefinitionsFromScopeSet", "declareDefinitionsFromScopeSet$koin_core", "equals", "other", "findDefinition", "Lorg/koin/core/definition/BeanDefinition;", "clazz", "get", "(Lkotlin/reflect/KClass;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "(Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getAll", "getKoin", "getOrNull", "getProperty", "key", "(Ljava/lang/String;)Ljava/lang/Object;", "defaultValue", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "getPropertyOrNull", "getScope", "scopeID", "hashCode", "", "inject", "Lkotlin/Lazy;", "injectOrNull", "registerCallback", "callback", "resolveInstance", "(Lorg/koin/core/qualifier/Qualifier;Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "toString", "koin-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Scope.kt */
public final class Scope {
    @NotNull
    private final Koin _koin;
    @NotNull
    private final BeanRegistry beanRegistry;
    private final ArrayList<ScopeCallback> callbacks;
    @NotNull

    /* renamed from: id */
    private final String f833id;
    private final boolean isRoot;
    @Nullable
    private ScopeDefinition set;

    @NotNull
    public static /* synthetic */ Scope copy$default(Scope scope, String str, boolean z, Koin koin, int i, Object obj) {
        if ((i & 1) != 0) {
            str = scope.f833id;
        }
        if ((i & 2) != 0) {
            z = scope.isRoot;
        }
        if ((i & 4) != 0) {
            koin = scope._koin;
        }
        return scope.copy(str, z, koin);
    }

    @JvmOverloads
    private final <T> T get() {
        return get$default(this, null, null, 3, null);
    }

    @JvmOverloads
    private final <T> T get(Qualifier qualifier) {
        return get$default(this, qualifier, null, 2, null);
    }

    @JvmOverloads
    private final <T> T getOrNull() {
        return getOrNull$default(this, null, null, 3, null);
    }

    @JvmOverloads
    private final <T> T getOrNull(Qualifier qualifier) {
        return getOrNull$default(this, qualifier, null, 2, null);
    }

    @JvmOverloads
    private final <T> Lazy<T> inject() {
        return inject$default(this, null, null, 3, null);
    }

    @JvmOverloads
    private final <T> Lazy<T> inject(Qualifier qualifier) {
        return inject$default(this, qualifier, null, 2, null);
    }

    @JvmOverloads
    private final <T> Lazy<T> injectOrNull() {
        return injectOrNull$default(this, null, null, 3, null);
    }

    @JvmOverloads
    private final <T> Lazy<T> injectOrNull(Qualifier qualifier) {
        return injectOrNull$default(this, qualifier, null, 2, null);
    }

    @NotNull
    public final String component1() {
        return this.f833id;
    }

    public final boolean component2() {
        return this.isRoot;
    }

    @NotNull
    public final Koin component3$koin_core() {
        return this._koin;
    }

    @NotNull
    public final Scope copy(@NotNull String str, boolean z, @NotNull Koin koin) {
        Intrinsics.checkParameterIsNotNull(str, CommonProperties.f657ID);
        Intrinsics.checkParameterIsNotNull(koin, "_koin");
        return new Scope(str, z, koin);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof Scope) {
                Scope scope = (Scope) obj;
                if (Intrinsics.areEqual((Object) this.f833id, (Object) scope.f833id)) {
                    if (!(this.isRoot == scope.isRoot) || !Intrinsics.areEqual((Object) this._koin, (Object) scope._koin)) {
                        return false;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.f833id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.isRoot;
        if (z) {
            z = true;
        }
        int i2 = (hashCode + (z ? 1 : 0)) * 31;
        Koin koin = this._koin;
        if (koin != null) {
            i = koin.hashCode();
        }
        return i2 + i;
    }

    public Scope(@NotNull String str, boolean z, @NotNull Koin koin) {
        Intrinsics.checkParameterIsNotNull(str, CommonProperties.f657ID);
        Intrinsics.checkParameterIsNotNull(koin, "_koin");
        this.f833id = str;
        this.isRoot = z;
        this._koin = koin;
        this.beanRegistry = new BeanRegistry();
        this.callbacks = new ArrayList<>();
    }

    @NotNull
    public final String getId() {
        return this.f833id;
    }

    public /* synthetic */ Scope(String str, boolean z, Koin koin, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            z = false;
        }
        this(str, z, koin);
    }

    public final boolean isRoot() {
        return this.isRoot;
    }

    @NotNull
    public final Koin get_koin$koin_core() {
        return this._koin;
    }

    @NotNull
    public final BeanRegistry getBeanRegistry() {
        return this.beanRegistry;
    }

    @Nullable
    public final ScopeDefinition getSet() {
        return this.set;
    }

    public final void setSet(@Nullable ScopeDefinition scopeDefinition) {
        this.set = scopeDefinition;
    }

    @JvmOverloads
    static /* synthetic */ Lazy inject$default(Scope scope, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Scope$inject$1(scope, qualifier, function0));
    }

    @JvmOverloads
    private final <T> Lazy<T> inject(Qualifier qualifier, Function0<DefinitionParameters> function0) {
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Scope$inject$1(this, qualifier, function0));
    }

    @JvmOverloads
    static /* synthetic */ Lazy injectOrNull$default(Scope scope, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Scope$injectOrNull$1(scope, qualifier, function0));
    }

    @JvmOverloads
    private final <T> Lazy<T> injectOrNull(Qualifier qualifier, Function0<DefinitionParameters> function0) {
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Scope$injectOrNull$1(this, qualifier, function0));
    }

    @JvmOverloads
    static /* synthetic */ Object get$default(Scope scope, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return scope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, function0);
    }

    @JvmOverloads
    private final <T> T get(Qualifier qualifier, Function0<DefinitionParameters> function0) {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, function0);
    }

    @JvmOverloads
    static /* synthetic */ Object getOrNull$default(Scope scope, Qualifier qualifier, Function0 function0, int i, Object obj) {
        String str = ExifInterface.GPS_DIRECTION_TRUE;
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        try {
            Intrinsics.reifiedOperationMarker(4, str);
            return scope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, function0);
        } catch (Exception unused) {
            Logger logger = KoinApplication.Companion.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Can't get instance for ");
            Intrinsics.reifiedOperationMarker(4, str);
            sb.append(KClassExtKt.getFullName(Reflection.getOrCreateKotlinClass(Object.class)));
            logger.error(sb.toString());
            return null;
        }
    }

    @JvmOverloads
    private final <T> T getOrNull(Qualifier qualifier, Function0<DefinitionParameters> function0) {
        String str = ExifInterface.GPS_DIRECTION_TRUE;
        try {
            Intrinsics.reifiedOperationMarker(4, str);
            return get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, function0);
        } catch (Exception unused) {
            Logger logger = KoinApplication.Companion.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Can't get instance for ");
            Intrinsics.reifiedOperationMarker(4, str);
            sb.append(KClassExtKt.getFullName(Reflection.getOrCreateKotlinClass(Object.class)));
            logger.error(sb.toString());
            return null;
        }
    }

    public final <T> T get(@NotNull KClass<?> kClass, @Nullable Qualifier qualifier, @Nullable Function0<DefinitionParameters> function0) {
        Intrinsics.checkParameterIsNotNull(kClass, "clazz");
        synchronized (this) {
            if (KoinApplication.Companion.getLogger().isAt(Level.DEBUG)) {
                Logger logger = KoinApplication.Companion.getLogger();
                StringBuilder sb = new StringBuilder();
                sb.append("+- get '");
                sb.append(KClassExtKt.getFullName(kClass));
                sb.append('\'');
                logger.debug(sb.toString());
                Pair measureDuration = MeasureKt.measureDuration(new Scope$get$$inlined$synchronized$lambda$1(this, kClass, qualifier, function0));
                T component1 = measureDuration.component1();
                double doubleValue = ((Number) measureDuration.component2()).doubleValue();
                Logger logger2 = KoinApplication.Companion.getLogger();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("+- got '");
                sb2.append(KClassExtKt.getFullName(kClass));
                sb2.append("' in ");
                sb2.append(doubleValue);
                sb2.append(" ms");
                logger2.debug(sb2.toString());
                return component1;
            }
            T resolveInstance = resolveInstance(qualifier, kClass, function0);
            return resolveInstance;
        }
    }

    /* access modifiers changed from: private */
    public final <T> T resolveInstance(Qualifier qualifier, KClass<?> kClass, Function0<DefinitionParameters> function0) {
        return findDefinition(qualifier, kClass).resolveInstance(new InstanceContext(this._koin, this, function0));
    }

    private final BeanDefinition<?> findDefinition(Qualifier qualifier, KClass<?> kClass) {
        BeanDefinition<?> findDefinition = this.beanRegistry.findDefinition(qualifier, kClass);
        if (findDefinition != null) {
            return findDefinition;
        }
        if (!this.isRoot) {
            return this._koin.getRootScope().findDefinition(qualifier, kClass);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("No definition found for '");
        sb.append(KClassExtKt.getFullName(kClass));
        sb.append("' has been found. Check your module definitions.");
        throw new NoBeanDefFoundException(sb.toString());
    }

    public final void createEagerInstances$koin_core() {
        if (this.isRoot) {
            Set<BeanDefinition> findAllCreatedAtStartDefinition$koin_core = this.beanRegistry.findAllCreatedAtStartDefinition$koin_core();
            if (!findAllCreatedAtStartDefinition$koin_core.isEmpty()) {
                for (BeanDefinition beanDefinition : findAllCreatedAtStartDefinition$koin_core) {
                    InstanceContext instanceContext = new InstanceContext(this._koin, this, null, 4, null);
                    beanDefinition.resolveInstance(instanceContext);
                }
            }
        }
    }

    static /* synthetic */ void declare$default(Scope scope, Object obj, Qualifier qualifier, List list, int i, Object obj2) {
        BeanDefinition beanDefinition;
        Qualifier qualifier2 = null;
        if ((i & 2) != 0) {
            qualifier = null;
        }
        if ((i & 4) != 0) {
            list = null;
        }
        boolean isRoot2 = scope.isRoot();
        String str = ExifInterface.GPS_DIRECTION_TRUE;
        if (isRoot2) {
            DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
            Function2 scope$declare$definition$1 = new Scope$declare$definition$1(obj);
            Kind kind = Kind.Single;
            Intrinsics.reifiedOperationMarker(4, str);
            beanDefinition = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(Object.class));
            beanDefinition.setDefinition(scope$declare$definition$1);
            beanDefinition.setKind(kind);
        } else {
            DefinitionFactory definitionFactory2 = DefinitionFactory.INSTANCE;
            ScopeDefinition set2 = scope.getSet();
            if (set2 != null) {
                qualifier2 = set2.getQualifier();
            }
            Function2 scope$declare$definition$2 = new Scope$declare$definition$2(obj);
            Kind kind2 = Kind.Scope;
            Intrinsics.reifiedOperationMarker(4, str);
            BeanDefinition beanDefinition2 = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(Object.class));
            beanDefinition2.setDefinition(scope$declare$definition$2);
            beanDefinition2.setKind(kind2);
            beanDefinition2.setScopeName(qualifier2);
            beanDefinition = beanDefinition2;
        }
        if (list != null) {
            beanDefinition.getSecondaryTypes().addAll(list);
        }
        scope.getBeanRegistry().saveDefinition(beanDefinition);
    }

    private final <T> void declare(T t, Qualifier qualifier, List<? extends KClass<?>> list) {
        BeanDefinition beanDefinition;
        boolean isRoot2 = isRoot();
        String str = ExifInterface.GPS_DIRECTION_TRUE;
        if (isRoot2) {
            DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
            Function2 scope$declare$definition$1 = new Scope$declare$definition$1(t);
            Kind kind = Kind.Single;
            Intrinsics.reifiedOperationMarker(4, str);
            beanDefinition = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(Object.class));
            beanDefinition.setDefinition(scope$declare$definition$1);
            beanDefinition.setKind(kind);
        } else {
            DefinitionFactory definitionFactory2 = DefinitionFactory.INSTANCE;
            ScopeDefinition set2 = getSet();
            Qualifier qualifier2 = set2 != null ? set2.getQualifier() : null;
            Function2 scope$declare$definition$2 = new Scope$declare$definition$2(t);
            Kind kind2 = Kind.Scope;
            Intrinsics.reifiedOperationMarker(4, str);
            BeanDefinition beanDefinition2 = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(Object.class));
            beanDefinition2.setDefinition(scope$declare$definition$2);
            beanDefinition2.setKind(kind2);
            beanDefinition2.setScopeName(qualifier2);
            beanDefinition = beanDefinition2;
        }
        if (list != null) {
            beanDefinition.getSecondaryTypes().addAll(list);
        }
        getBeanRegistry().saveDefinition(beanDefinition);
    }

    @NotNull
    public final Koin getKoin() {
        return this._koin;
    }

    @NotNull
    public final Scope getScope(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "scopeID");
        return getKoin().getScope(str);
    }

    public final void registerCallback(@NotNull ScopeCallback scopeCallback) {
        Intrinsics.checkParameterIsNotNull(scopeCallback, "callback");
        this.callbacks.add(scopeCallback);
    }

    private final <T> List<T> getAll() {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return getAll(Reflection.getOrCreateKotlinClass(Object.class));
    }

    @NotNull
    public final <T> List<T> getAll(@NotNull KClass<?> kClass) {
        Intrinsics.checkParameterIsNotNull(kClass, "clazz");
        Iterable<BeanDefinition> definitionsForClass = this.beanRegistry.getDefinitionsForClass(kClass);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(definitionsForClass, 10));
        for (BeanDefinition instance : definitionsForClass) {
            DefinitionInstance instance2 = instance.getInstance();
            if (instance2 == null) {
                Intrinsics.throwNpe();
            }
            InstanceContext instanceContext = new InstanceContext(this._koin, this, null, 4, null);
            arrayList.add(instance2.get(instanceContext));
        }
        return (List) arrayList;
    }

    static /* synthetic */ Object bind$default(Scope scope, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LATITUDE_SOUTH);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(4, "P");
        return scope.bind(Reflection.getOrCreateKotlinClass(Object.class), orCreateKotlinClass, function0);
    }

    private final <S, P> S bind(Function0<DefinitionParameters> function0) {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LATITUDE_SOUTH);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(4, "P");
        return bind(Reflection.getOrCreateKotlinClass(Object.class), orCreateKotlinClass, function0);
    }

    public final <S> S bind(@NotNull KClass<?> kClass, @NotNull KClass<?> kClass2, @Nullable Function0<DefinitionParameters> function0) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(kClass, "primaryType");
        Intrinsics.checkParameterIsNotNull(kClass2, "secondaryType");
        for (BeanDefinition beanDefinition : this.beanRegistry.getAllDefinitions()) {
            if (!Intrinsics.areEqual((Object) beanDefinition.getPrimaryType(), (Object) kClass) || !beanDefinition.getSecondaryTypes().contains(kClass2) || beanDefinition.isKind(Kind.Scope)) {
                z = false;
                continue;
            } else {
                z = true;
                continue;
            }
            if (z) {
                DefinitionInstance instance = beanDefinition.getInstance();
                if (instance == null) {
                    Intrinsics.throwNpe();
                }
                return instance.get(new InstanceContext(getKoin(), this, function0));
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public final <T> T getProperty(@NotNull String str, T t) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        return this._koin.getProperty(str, t);
    }

    @Nullable
    public final <T> T getPropertyOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        return this._koin.getProperty(str);
    }

    public final <T> T getProperty(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        T property = this._koin.getProperty(str);
        if (property != null) {
            return property;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Property '");
        sb.append(str);
        sb.append("' not found");
        throw new MissingPropertyException(sb.toString());
    }

    public final void declareDefinitionsFromScopeSet$koin_core() {
        ScopeDefinition scopeDefinition = this.set;
        if (scopeDefinition != null) {
            for (BeanDefinition beanDefinition : scopeDefinition.getDefinitions()) {
                this.beanRegistry.saveDefinition(beanDefinition);
                beanDefinition.createInstanceHolder();
            }
        }
    }

    public final void close() {
        synchronized (this) {
            if (KoinApplication.Companion.getLogger().isAt(Level.DEBUG)) {
                Logger logger = KoinApplication.Companion.getLogger();
                StringBuilder sb = new StringBuilder();
                sb.append("closing scope:'");
                sb.append(this.f833id);
                sb.append('\'');
                logger.info(sb.toString());
            }
            for (ScopeCallback onScopeClose : this.callbacks) {
                onScopeClose.onScopeClose(this);
            }
            this.callbacks.clear();
            ScopeDefinition scopeDefinition = this.set;
            if (scopeDefinition != null) {
                scopeDefinition.release$koin_core(this);
            }
            this.beanRegistry.close();
            this._koin.deleteScope(this.f833id);
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x001e, code lost:
        if (r0 != null) goto L_0x0023;
     */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
            r4 = this;
            org.koin.core.scope.ScopeDefinition r0 = r4.set
            r1 = 39
            if (r0 == 0) goto L_0x0021
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ",set:'"
            r2.append(r3)
            org.koin.core.qualifier.Qualifier r0 = r0.getQualifier()
            r2.append(r0)
            r2.append(r1)
            java.lang.String r0 = r2.toString()
            if (r0 == 0) goto L_0x0021
            goto L_0x0023
        L_0x0021:
            java.lang.String r0 = ""
        L_0x0023:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Scope[id:'"
            r2.append(r3)
            java.lang.String r3 = r4.f833id
            r2.append(r3)
            r2.append(r1)
            r2.append(r0)
            r0 = 93
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.koin.core.scope.Scope.toString():java.lang.String");
    }
}
