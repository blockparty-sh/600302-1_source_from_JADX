package com.bitcoin.mwallet;

import com.bitcoin.mwallet.core.interactors.UtxoSelector;
import com.bitcoin.mwallet.core.preferences.ApplicationPreferences;
import com.bitcoin.mwallet.core.services.WalletRefresherTemp;
import com.bitcoin.mwallet.core.services.eventstream.EventStreamHandler;
import com.bitcoin.mwallet.core.services.eventstream.EventStreamServiceGrpc;
import com.bitcoin.mwallet.core.services.securestorage.SecureStorage;
import com.bitcoin.mwallet.zion.ZionRepository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.DefinitionFactory;
import org.koin.core.definition.Kind;
import org.koin.core.definition.Options;
import org.koin.core.module.Module;
import org.koin.core.qualifier.Qualifier;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, mo37405d2 = {"<anonymous>", "", "Lorg/koin/core/module/Module;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: KoinConfig.kt */
final class KoinConfig$globalModule$1 extends Lambda implements Function1<Module, Unit> {
    public static final KoinConfig$globalModule$1 INSTANCE = new KoinConfig$globalModule$1();

    KoinConfig$globalModule$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Module) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull Module module) {
        Intrinsics.checkParameterIsNotNull(module, "$receiver");
        Function2 function2 = C09691.INSTANCE;
        Qualifier qualifier = null;
        DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
        Kind kind = Kind.Single;
        BeanDefinition beanDefinition = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(UtxoSelector.class));
        beanDefinition.setDefinition(function2);
        beanDefinition.setKind(kind);
        module.declareDefinition(beanDefinition, new Options(false, false));
        Function2 function22 = C09702.INSTANCE;
        DefinitionFactory definitionFactory2 = DefinitionFactory.INSTANCE;
        Kind kind2 = Kind.Single;
        BeanDefinition beanDefinition2 = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(WalletRefresherTemp.class));
        beanDefinition2.setDefinition(function22);
        beanDefinition2.setKind(kind2);
        module.declareDefinition(beanDefinition2, new Options(false, false));
        Function2 function23 = C09713.INSTANCE;
        DefinitionFactory definitionFactory3 = DefinitionFactory.INSTANCE;
        Kind kind3 = Kind.Single;
        BeanDefinition beanDefinition3 = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(EventStreamServiceGrpc.class));
        beanDefinition3.setDefinition(function23);
        beanDefinition3.setKind(kind3);
        module.declareDefinition(beanDefinition3, new Options(false, false));
        Function2 function24 = C09724.INSTANCE;
        DefinitionFactory definitionFactory4 = DefinitionFactory.INSTANCE;
        Kind kind4 = Kind.Single;
        BeanDefinition beanDefinition4 = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(EventStreamHandler.class));
        beanDefinition4.setDefinition(function24);
        beanDefinition4.setKind(kind4);
        module.declareDefinition(beanDefinition4, new Options(false, false));
        Function2 function25 = C09735.INSTANCE;
        DefinitionFactory definitionFactory5 = DefinitionFactory.INSTANCE;
        Kind kind5 = Kind.Single;
        BeanDefinition beanDefinition5 = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(ApplicationPreferences.class));
        beanDefinition5.setDefinition(function25);
        beanDefinition5.setKind(kind5);
        module.declareDefinition(beanDefinition5, new Options(false, false));
        Function2 function26 = C09746.INSTANCE;
        DefinitionFactory definitionFactory6 = DefinitionFactory.INSTANCE;
        Kind kind6 = Kind.Single;
        BeanDefinition beanDefinition6 = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(ZionRepository.class));
        beanDefinition6.setDefinition(function26);
        beanDefinition6.setKind(kind6);
        module.declareDefinition(beanDefinition6, new Options(false, false));
        Function2 function27 = C09757.INSTANCE;
        DefinitionFactory definitionFactory7 = DefinitionFactory.INSTANCE;
        Kind kind7 = Kind.Single;
        BeanDefinition beanDefinition7 = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(SecureStorage.class));
        beanDefinition7.setDefinition(function27);
        beanDefinition7.setKind(kind7);
        module.declareDefinition(beanDefinition7, new Options(false, false));
    }
}
