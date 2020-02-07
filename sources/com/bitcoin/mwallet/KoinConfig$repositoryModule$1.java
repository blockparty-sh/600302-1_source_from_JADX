package com.bitcoin.mwallet;

import com.bitcoin.mwallet.core.repositories.ContactRepository;
import com.bitcoin.mwallet.core.repositories.ExchangeRateRepository;
import com.bitcoin.mwallet.core.repositories.MerchantRepository;
import com.bitcoin.mwallet.core.repositories.NetworkFeeRepository;
import com.bitcoin.mwallet.core.repositories.NotesRepository;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import com.bitcoin.mwallet.core.repositories.TransactionHistoryRepository;
import com.bitcoin.mwallet.core.repositories.UserRepository;
import com.bitcoin.mwallet.core.repositories.UtxoRepository;
import com.bitcoin.mwallet.core.repositories.VerifiedTokenRepository;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
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
final class KoinConfig$repositoryModule$1 extends Lambda implements Function1<Module, Unit> {
    public static final KoinConfig$repositoryModule$1 INSTANCE = new KoinConfig$repositoryModule$1();

    KoinConfig$repositoryModule$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Module) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull Module module) {
        Intrinsics.checkParameterIsNotNull(module, "$receiver");
        Function2 function2 = C09901.INSTANCE;
        Qualifier qualifier = null;
        DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
        Kind kind = Kind.Single;
        BeanDefinition beanDefinition = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(WalletRepository.class));
        beanDefinition.setDefinition(function2);
        beanDefinition.setKind(kind);
        module.declareDefinition(beanDefinition, new Options(false, false));
        Function2 function22 = C09932.INSTANCE;
        DefinitionFactory definitionFactory2 = DefinitionFactory.INSTANCE;
        Kind kind2 = Kind.Single;
        BeanDefinition beanDefinition2 = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(VerifiedTokenRepository.class));
        beanDefinition2.setDefinition(function22);
        beanDefinition2.setKind(kind2);
        module.declareDefinition(beanDefinition2, new Options(false, false));
        Function2 function23 = C09943.INSTANCE;
        DefinitionFactory definitionFactory3 = DefinitionFactory.INSTANCE;
        Kind kind3 = Kind.Single;
        BeanDefinition beanDefinition3 = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(UtxoRepository.class));
        beanDefinition3.setDefinition(function23);
        beanDefinition3.setKind(kind3);
        module.declareDefinition(beanDefinition3, new Options(false, false));
        Function2 function24 = C09954.INSTANCE;
        DefinitionFactory definitionFactory4 = DefinitionFactory.INSTANCE;
        Kind kind4 = Kind.Single;
        BeanDefinition beanDefinition4 = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(NotesRepository.class));
        beanDefinition4.setDefinition(function24);
        beanDefinition4.setKind(kind4);
        module.declareDefinition(beanDefinition4, new Options(false, false));
        Function2 function25 = C09965.INSTANCE;
        DefinitionFactory definitionFactory5 = DefinitionFactory.INSTANCE;
        Kind kind5 = Kind.Single;
        BeanDefinition beanDefinition5 = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(TransactionHistoryRepository.class));
        beanDefinition5.setDefinition(function25);
        beanDefinition5.setKind(kind5);
        module.declareDefinition(beanDefinition5, new Options(false, false));
        Function2 function26 = C09976.INSTANCE;
        DefinitionFactory definitionFactory6 = DefinitionFactory.INSTANCE;
        Kind kind6 = Kind.Single;
        BeanDefinition beanDefinition6 = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(ExchangeRateRepository.class));
        beanDefinition6.setDefinition(function26);
        beanDefinition6.setKind(kind6);
        module.declareDefinition(beanDefinition6, new Options(false, false));
        Function2 function27 = C09987.INSTANCE;
        DefinitionFactory definitionFactory7 = DefinitionFactory.INSTANCE;
        Kind kind7 = Kind.Single;
        BeanDefinition beanDefinition7 = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(UserRepository.class));
        beanDefinition7.setDefinition(function27);
        beanDefinition7.setKind(kind7);
        module.declareDefinition(beanDefinition7, new Options(false, false));
        Function2 function28 = C09998.INSTANCE;
        DefinitionFactory definitionFactory8 = DefinitionFactory.INSTANCE;
        Kind kind8 = Kind.Single;
        BeanDefinition beanDefinition8 = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(ContactRepository.class));
        beanDefinition8.setDefinition(function28);
        beanDefinition8.setKind(kind8);
        module.declareDefinition(beanDefinition8, new Options(false, false));
        Function2 function29 = C10009.INSTANCE;
        DefinitionFactory definitionFactory9 = DefinitionFactory.INSTANCE;
        Kind kind9 = Kind.Single;
        BeanDefinition beanDefinition9 = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(MerchantRepository.class));
        beanDefinition9.setDefinition(function29);
        beanDefinition9.setKind(kind9);
        module.declareDefinition(beanDefinition9, new Options(false, false));
        Function2 function210 = C099110.INSTANCE;
        DefinitionFactory definitionFactory10 = DefinitionFactory.INSTANCE;
        Kind kind10 = Kind.Single;
        BeanDefinition beanDefinition10 = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(NetworkFeeRepository.class));
        beanDefinition10.setDefinition(function210);
        beanDefinition10.setKind(kind10);
        module.declareDefinition(beanDefinition10, new Options(false, false));
        Function2 function211 = C099211.INSTANCE;
        DefinitionFactory definitionFactory11 = DefinitionFactory.INSTANCE;
        Kind kind11 = Kind.Single;
        BeanDefinition beanDefinition11 = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(SlpRepository.class));
        beanDefinition11.setDefinition(function211);
        beanDefinition11.setKind(kind11);
        module.declareDefinition(beanDefinition11, new Options(false, false));
    }
}
