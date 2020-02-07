package com.bitcoin.mwallet;

import com.bitcoin.mwallet.core.services.UserServiceGrpc;
import com.bitcoin.mwallet.core.services.address.AddressService;
import com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateService;
import com.bitcoin.mwallet.core.services.featuredbusiness.FeaturedBusinessService;
import com.bitcoin.mwallet.core.services.link.LinkService;
import com.bitcoin.mwallet.core.services.location.GeolocationService;
import com.bitcoin.mwallet.core.services.networkfee.NetworkFeeService;
import com.bitcoin.mwallet.core.services.notification.NotificationService;
import com.bitcoin.mwallet.core.services.p010tx.TxService;
import com.bitcoin.mwallet.core.services.verifiedtoken.VerifiedTokenService;
import com.bitcoin.mwallet.core.services.wallet.WalletService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.DefinitionFactory;
import org.koin.core.definition.Kind;
import org.koin.core.definition.Options;
import org.koin.core.module.Module;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.qualifier.QualifierKt;
import p015io.grpc.ManagedChannel;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, mo37405d2 = {"<anonymous>", "", "Lorg/koin/core/module/Module;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: KoinConfig.kt */
final class KoinConfig$grpcModule$1 extends Lambda implements Function1<Module, Unit> {
    public static final KoinConfig$grpcModule$1 INSTANCE = new KoinConfig$grpcModule$1();

    KoinConfig$grpcModule$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Module) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull Module module) {
        Intrinsics.checkParameterIsNotNull(module, "$receiver");
        Qualifier named = QualifierKt.named("GRPC_DISPATCHER");
        Function2 function2 = C09761.INSTANCE;
        DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
        Kind kind = Kind.Single;
        BeanDefinition beanDefinition = new BeanDefinition(named, Reflection.getOrCreateKotlinClass(CoroutineDispatcher.class));
        beanDefinition.setDefinition(function2);
        beanDefinition.setKind(kind);
        module.declareDefinition(beanDefinition, new Options(false, false));
        Boolean bool = BuildConfig.DEV_MODE;
        Intrinsics.checkExpressionValueIsNotNull(bool, "BuildConfig.DEV_MODE");
        if (bool.booleanValue()) {
            Function2 function22 = C09822.INSTANCE;
            Qualifier qualifier = null;
            DefinitionFactory definitionFactory2 = DefinitionFactory.INSTANCE;
            Kind kind2 = Kind.Single;
            BeanDefinition beanDefinition2 = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(ManagedChannel.class));
            beanDefinition2.setDefinition(function22);
            beanDefinition2.setKind(kind2);
            module.declareDefinition(beanDefinition2, new Options(false, false));
        } else {
            Function2 function23 = C09833.INSTANCE;
            Qualifier qualifier2 = null;
            DefinitionFactory definitionFactory3 = DefinitionFactory.INSTANCE;
            Kind kind3 = Kind.Single;
            BeanDefinition beanDefinition3 = new BeanDefinition(qualifier2, Reflection.getOrCreateKotlinClass(ManagedChannel.class));
            beanDefinition3.setDefinition(function23);
            beanDefinition3.setKind(kind3);
            module.declareDefinition(beanDefinition3, new Options(false, false));
        }
        Function2 function24 = C09844.INSTANCE;
        Qualifier qualifier3 = null;
        DefinitionFactory definitionFactory4 = DefinitionFactory.INSTANCE;
        Kind kind4 = Kind.Single;
        BeanDefinition beanDefinition4 = new BeanDefinition(qualifier3, Reflection.getOrCreateKotlinClass(GeolocationService.class));
        beanDefinition4.setDefinition(function24);
        beanDefinition4.setKind(kind4);
        module.declareDefinition(beanDefinition4, new Options(false, false));
        Function2 function25 = C09855.INSTANCE;
        DefinitionFactory definitionFactory5 = DefinitionFactory.INSTANCE;
        Kind kind5 = Kind.Single;
        BeanDefinition beanDefinition5 = new BeanDefinition(qualifier3, Reflection.getOrCreateKotlinClass(FeaturedBusinessService.class));
        beanDefinition5.setDefinition(function25);
        beanDefinition5.setKind(kind5);
        module.declareDefinition(beanDefinition5, new Options(false, false));
        Function2 function26 = C09866.INSTANCE;
        DefinitionFactory definitionFactory6 = DefinitionFactory.INSTANCE;
        Kind kind6 = Kind.Single;
        BeanDefinition beanDefinition6 = new BeanDefinition(qualifier3, Reflection.getOrCreateKotlinClass(LinkService.class));
        beanDefinition6.setDefinition(function26);
        beanDefinition6.setKind(kind6);
        module.declareDefinition(beanDefinition6, new Options(false, false));
        Function2 function27 = C09877.INSTANCE;
        DefinitionFactory definitionFactory7 = DefinitionFactory.INSTANCE;
        Kind kind7 = Kind.Single;
        BeanDefinition beanDefinition7 = new BeanDefinition(qualifier3, Reflection.getOrCreateKotlinClass(VerifiedTokenService.class));
        beanDefinition7.setDefinition(function27);
        beanDefinition7.setKind(kind7);
        module.declareDefinition(beanDefinition7, new Options(false, false));
        Function2 function28 = C09888.INSTANCE;
        DefinitionFactory definitionFactory8 = DefinitionFactory.INSTANCE;
        Kind kind8 = Kind.Single;
        BeanDefinition beanDefinition8 = new BeanDefinition(qualifier3, Reflection.getOrCreateKotlinClass(TxService.class));
        beanDefinition8.setDefinition(function28);
        beanDefinition8.setKind(kind8);
        module.declareDefinition(beanDefinition8, new Options(false, false));
        Function2 function29 = C09899.INSTANCE;
        DefinitionFactory definitionFactory9 = DefinitionFactory.INSTANCE;
        Kind kind9 = Kind.Single;
        BeanDefinition beanDefinition9 = new BeanDefinition(qualifier3, Reflection.getOrCreateKotlinClass(UserServiceGrpc.class));
        beanDefinition9.setDefinition(function29);
        beanDefinition9.setKind(kind9);
        module.declareDefinition(beanDefinition9, new Options(false, false));
        Function2 function210 = C097710.INSTANCE;
        DefinitionFactory definitionFactory10 = DefinitionFactory.INSTANCE;
        Kind kind10 = Kind.Single;
        BeanDefinition beanDefinition10 = new BeanDefinition(qualifier3, Reflection.getOrCreateKotlinClass(WalletService.class));
        beanDefinition10.setDefinition(function210);
        beanDefinition10.setKind(kind10);
        module.declareDefinition(beanDefinition10, new Options(false, false));
        Function2 function211 = C097811.INSTANCE;
        DefinitionFactory definitionFactory11 = DefinitionFactory.INSTANCE;
        Kind kind11 = Kind.Single;
        BeanDefinition beanDefinition11 = new BeanDefinition(qualifier3, Reflection.getOrCreateKotlinClass(ExchangeRateService.class));
        beanDefinition11.setDefinition(function211);
        beanDefinition11.setKind(kind11);
        module.declareDefinition(beanDefinition11, new Options(false, false));
        Function2 function212 = C097912.INSTANCE;
        DefinitionFactory definitionFactory12 = DefinitionFactory.INSTANCE;
        Kind kind12 = Kind.Single;
        BeanDefinition beanDefinition12 = new BeanDefinition(qualifier3, Reflection.getOrCreateKotlinClass(AddressService.class));
        beanDefinition12.setDefinition(function212);
        beanDefinition12.setKind(kind12);
        module.declareDefinition(beanDefinition12, new Options(false, false));
        Function2 function213 = C098013.INSTANCE;
        DefinitionFactory definitionFactory13 = DefinitionFactory.INSTANCE;
        Kind kind13 = Kind.Single;
        BeanDefinition beanDefinition13 = new BeanDefinition(qualifier3, Reflection.getOrCreateKotlinClass(NetworkFeeService.class));
        beanDefinition13.setDefinition(function213);
        beanDefinition13.setKind(kind13);
        module.declareDefinition(beanDefinition13, new Options(false, false));
        Function2 function214 = C098114.INSTANCE;
        DefinitionFactory definitionFactory14 = DefinitionFactory.INSTANCE;
        Kind kind14 = Kind.Single;
        BeanDefinition beanDefinition14 = new BeanDefinition(qualifier3, Reflection.getOrCreateKotlinClass(NotificationService.class));
        beanDefinition14.setDefinition(function214);
        beanDefinition14.setKind(kind14);
        module.declareDefinition(beanDefinition14, new Options(false, false));
    }
}
