package org.koin.core.registry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.koin.core.KoinApplication;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.Kind;
import org.koin.core.error.DefinitionOverrideException;
import org.koin.core.error.NoBeanDefFoundException;
import org.koin.core.instance.DefinitionInstance;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.core.module.Module;
import org.koin.core.qualifier.Qualifier;
import org.koin.ext.KClassExtKt;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0010\u001a\u00020\u0011J,\u0010\u0012\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\rj\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005`\u000e2\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002J\u0017\u0010\u0014\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0015H\u0000¢\u0006\u0002\b\u0016J$\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u000bJ\u0016\u0010\u001b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\u0006\u0010\u001c\u001a\u00020\tH\u0002J\u001a\u0010\u001d\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002J\u001a\u0010\u001f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002J\u0010\u0010 \u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0015J\u0018\u0010!\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u000bJ\u001c\u0010\"\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050#2\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u000bJ\u0014\u0010$\u001a\u00020\u00112\f\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&J\u0014\u0010(\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002J\u0014\u0010*\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002J \u0010+\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u00052\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002J\u0014\u0010,\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002J\u0014\u0010-\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002J\u0010\u0010.\u001a\u00020\u00112\u0006\u0010/\u001a\u00020'H\u0002J\u0012\u00100\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005J\u0014\u00101\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002J \u00102\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u00052\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002J\u0014\u00103\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002J\u0014\u00104\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002J \u00105\u001a\u00020\u00112\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u000b2\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002J\u0014\u00106\u001a\u00020\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002J\u0010\u00107\u001a\u00020\u00112\u0006\u0010/\u001a\u00020'H\u0002J\u0006\u00108\u001a\u000209J\u001b\u0010:\u001a\u00020\u00112\f\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&H\u0000¢\u0006\u0002\b;J0\u0010<\u001a\u00020\u0011*\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005`\u00062\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002R&\u0010\u0003\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005`\u0006X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\bX\u0004¢\u0006\u0002\n\u0000R\"\u0010\n\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\bX\u0004¢\u0006\u0002\n\u0000R6\u0010\f\u001a*\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u001c\u0012\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\rj\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005`\u000e0\bX\u0004¢\u0006\u0002\n\u0000R&\u0010\u000f\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005`\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006="}, mo37405d2 = {"Lorg/koin/core/registry/BeanRegistry;", "", "()V", "definitions", "Ljava/util/HashSet;", "Lorg/koin/core/definition/BeanDefinition;", "Lkotlin/collections/HashSet;", "definitionsNames", "", "", "definitionsPrimaryTypes", "Lkotlin/reflect/KClass;", "definitionsSecondaryTypes", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "definitionsToCreate", "close", "", "createSecondaryType", "type", "findAllCreatedAtStartDefinition", "", "findAllCreatedAtStartDefinition$koin_core", "findDefinition", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "clazz", "findDefinitionByName", "name", "findDefinitionBySecondaryType", "kClass", "findDefinitionByType", "getAllDefinitions", "getDefinition", "getDefinitionsForClass", "", "loadModules", "modules", "", "Lorg/koin/core/module/Module;", "removeDefinition", "definition", "removeDefinitionForName", "removeDefinitionForSecondaryType", "removeDefinitionForSecondaryTypes", "removeDefinitionForTypes", "removeDefinitions", "module", "saveDefinition", "saveDefinitionForName", "saveDefinitionForSecondaryType", "saveDefinitionForSecondaryTypes", "saveDefinitionForStart", "saveDefinitionForType", "saveDefinitionForTypes", "saveDefinitions", "size", "", "unloadModules", "unloadModules$koin_core", "addDefinition", "koin-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: BeanRegistry.kt */
public final class BeanRegistry {
    private final HashSet<BeanDefinition<?>> definitions = new HashSet<>();
    private final Map<String, BeanDefinition<?>> definitionsNames = new ConcurrentHashMap();
    private final Map<KClass<?>, BeanDefinition<?>> definitionsPrimaryTypes = new ConcurrentHashMap();
    private final Map<KClass<?>, ArrayList<BeanDefinition<?>>> definitionsSecondaryTypes = new ConcurrentHashMap();
    private final HashSet<BeanDefinition<?>> definitionsToCreate = new HashSet<>();

    private final void removeDefinitions(Module module) {
        for (BeanDefinition removeDefinition : module.getDefinitions$koin_core()) {
            removeDefinition(removeDefinition);
        }
    }

    private final void saveDefinitions(Module module) {
        for (BeanDefinition saveDefinition : module.getDefinitions$koin_core()) {
            saveDefinition(saveDefinition);
        }
    }

    @NotNull
    public final Set<BeanDefinition<?>> getAllDefinitions() {
        return this.definitions;
    }

    private final void removeDefinition(BeanDefinition<?> beanDefinition) {
        DefinitionInstance instance = beanDefinition.getInstance();
        if (instance != null) {
            instance.close();
        }
        this.definitions.remove(beanDefinition);
        if (beanDefinition.getQualifier() != null) {
            removeDefinitionForName(beanDefinition);
        } else {
            removeDefinitionForTypes(beanDefinition);
        }
        removeDefinitionForSecondaryTypes(beanDefinition);
    }

    public final void saveDefinition(@NotNull BeanDefinition<?> beanDefinition) {
        Intrinsics.checkParameterIsNotNull(beanDefinition, "definition");
        addDefinition(this.definitions, beanDefinition);
        beanDefinition.createInstanceHolder();
        if (beanDefinition.getQualifier() != null) {
            saveDefinitionForName(beanDefinition);
        } else {
            saveDefinitionForTypes(beanDefinition);
        }
        if (!beanDefinition.getSecondaryTypes().isEmpty()) {
            saveDefinitionForSecondaryTypes(beanDefinition);
        }
        if (beanDefinition.getOptions().isCreatedAtStart()) {
            saveDefinitionForStart(beanDefinition);
        }
    }

    private final void saveDefinitionForSecondaryTypes(BeanDefinition<?> beanDefinition) {
        for (KClass saveDefinitionForSecondaryType : beanDefinition.getSecondaryTypes()) {
            saveDefinitionForSecondaryType(beanDefinition, saveDefinitionForSecondaryType);
        }
    }

    private final void saveDefinitionForSecondaryType(BeanDefinition<?> beanDefinition, KClass<?> kClass) {
        ArrayList arrayList = (ArrayList) this.definitionsSecondaryTypes.get(kClass);
        if (arrayList == null) {
            arrayList = createSecondaryType(kClass);
        }
        arrayList.add(beanDefinition);
        if (KoinApplication.Companion.getLogger().isAt(Level.INFO)) {
            Logger logger = KoinApplication.Companion.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("bind secondary type:'");
            sb.append(KClassExtKt.getFullName(kClass));
            sb.append("' ~ ");
            sb.append(beanDefinition);
            logger.info(sb.toString());
        }
    }

    private final ArrayList<BeanDefinition<?>> createSecondaryType(KClass<?> kClass) {
        this.definitionsSecondaryTypes.put(kClass, new ArrayList());
        Object obj = this.definitionsSecondaryTypes.get(kClass);
        if (obj == null) {
            Intrinsics.throwNpe();
        }
        return (ArrayList) obj;
    }

    private final void saveDefinitionForStart(BeanDefinition<?> beanDefinition) {
        this.definitionsToCreate.add(beanDefinition);
    }

    private final void addDefinition(@NotNull HashSet<BeanDefinition<?>> hashSet, BeanDefinition<?> beanDefinition) {
        if (!hashSet.add(beanDefinition) && !beanDefinition.getOptions().getOverride()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Already existing definition or try to override an existing one: ");
            sb.append(beanDefinition);
            throw new DefinitionOverrideException(sb.toString());
        }
    }

    private final void saveDefinitionForTypes(BeanDefinition<?> beanDefinition) {
        saveDefinitionForType(beanDefinition.getPrimaryType(), beanDefinition);
    }

    private final void removeDefinitionForSecondaryTypes(BeanDefinition<?> beanDefinition) {
        for (KClass removeDefinitionForSecondaryType : beanDefinition.getSecondaryTypes()) {
            removeDefinitionForSecondaryType(beanDefinition, removeDefinitionForSecondaryType);
        }
    }

    private final void removeDefinitionForSecondaryType(BeanDefinition<?> beanDefinition, KClass<?> kClass) {
        ArrayList arrayList = (ArrayList) this.definitionsSecondaryTypes.get(kClass);
        boolean remove = arrayList != null ? arrayList.remove(beanDefinition) : false;
        if (KoinApplication.Companion.getLogger().isAt(Level.DEBUG) && remove) {
            Logger logger = KoinApplication.Companion.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("unbind secondary type:'");
            sb.append(KClassExtKt.getFullName(kClass));
            sb.append("' ~ ");
            sb.append(beanDefinition);
            logger.info(sb.toString());
        }
    }

    private final void removeDefinitionForTypes(BeanDefinition<?> beanDefinition) {
        KClass primaryType = beanDefinition.getPrimaryType();
        if (Intrinsics.areEqual((Object) (BeanDefinition) this.definitionsPrimaryTypes.get(primaryType), (Object) beanDefinition)) {
            this.definitionsPrimaryTypes.remove(primaryType);
            if (KoinApplication.Companion.getLogger().isAt(Level.DEBUG)) {
                Logger logger = KoinApplication.Companion.getLogger();
                StringBuilder sb = new StringBuilder();
                sb.append("unbind type:'");
                sb.append(KClassExtKt.getFullName(primaryType));
                sb.append("' ~ ");
                sb.append(beanDefinition);
                logger.info(sb.toString());
            }
        }
    }

    private final void saveDefinitionForType(KClass<?> kClass, BeanDefinition<?> beanDefinition) {
        if (this.definitionsPrimaryTypes.get(kClass) == null || beanDefinition.getOptions().getOverride()) {
            this.definitionsPrimaryTypes.put(kClass, beanDefinition);
            if (KoinApplication.Companion.getLogger().isAt(Level.INFO)) {
                Logger logger = KoinApplication.Companion.getLogger();
                StringBuilder sb = new StringBuilder();
                sb.append("bind type:'");
                sb.append(KClassExtKt.getFullName(kClass));
                sb.append("' ~ ");
                sb.append(beanDefinition);
                logger.info(sb.toString());
                return;
            }
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Already existing definition or try to override an existing one with type '");
        sb2.append(kClass);
        sb2.append("' and ");
        sb2.append(beanDefinition);
        sb2.append(" but has already registered ");
        sb2.append((BeanDefinition) this.definitionsPrimaryTypes.get(kClass));
        throw new DefinitionOverrideException(sb2.toString());
    }

    private final void removeDefinitionForName(BeanDefinition<?> beanDefinition) {
        Qualifier qualifier = beanDefinition.getQualifier();
        if (qualifier != null) {
            String obj = qualifier.toString();
            if (Intrinsics.areEqual((Object) (BeanDefinition) this.definitionsNames.get(obj), (Object) beanDefinition)) {
                this.definitionsNames.remove(obj);
                if (KoinApplication.Companion.getLogger().isAt(Level.DEBUG)) {
                    Logger logger = KoinApplication.Companion.getLogger();
                    StringBuilder sb = new StringBuilder();
                    sb.append("unbind qualifier:'");
                    sb.append(obj);
                    sb.append("' ~ ");
                    sb.append(beanDefinition);
                    logger.info(sb.toString());
                }
            }
        }
    }

    private final void saveDefinitionForName(BeanDefinition<?> beanDefinition) {
        Qualifier qualifier = beanDefinition.getQualifier();
        if (qualifier == null) {
            return;
        }
        if (this.definitionsNames.get(qualifier.toString()) == null || beanDefinition.getOptions().getOverride()) {
            this.definitionsNames.put(qualifier.toString(), beanDefinition);
            if (KoinApplication.Companion.getLogger().isAt(Level.INFO)) {
                Logger logger = KoinApplication.Companion.getLogger();
                StringBuilder sb = new StringBuilder();
                sb.append("bind qualifier:'");
                sb.append(beanDefinition.getQualifier());
                sb.append("' ~ ");
                sb.append(beanDefinition);
                logger.info(sb.toString());
                return;
            }
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Already existing definition or try to override an existing one with qualifier '");
        sb2.append(qualifier);
        sb2.append("' with ");
        sb2.append(beanDefinition);
        sb2.append(" but has already registered ");
        sb2.append((BeanDefinition) this.definitionsNames.get(qualifier.toString()));
        throw new DefinitionOverrideException(sb2.toString());
    }

    @Nullable
    public static /* synthetic */ BeanDefinition findDefinition$default(BeanRegistry beanRegistry, Qualifier qualifier, KClass kClass, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        return beanRegistry.findDefinition(qualifier, kClass);
    }

    @Nullable
    public final BeanDefinition<?> findDefinition(@Nullable Qualifier qualifier, @NotNull KClass<?> kClass) {
        Intrinsics.checkParameterIsNotNull(kClass, "clazz");
        if (qualifier != null) {
            return findDefinitionByName(qualifier.toString());
        }
        BeanDefinition<?> findDefinitionByType = findDefinitionByType(kClass);
        return findDefinitionByType != null ? findDefinitionByType : findDefinitionBySecondaryType(kClass);
    }

    private final BeanDefinition<?> findDefinitionByType(KClass<?> kClass) {
        return (BeanDefinition) this.definitionsPrimaryTypes.get(kClass);
    }

    private final BeanDefinition<?> findDefinitionBySecondaryType(KClass<?> kClass) {
        ArrayList arrayList = (ArrayList) this.definitionsSecondaryTypes.get(kClass);
        if (arrayList != null && arrayList.size() == 1) {
            return (BeanDefinition) arrayList.get(0);
        }
        if (arrayList == null || arrayList.size() <= 1) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Found multiple definitions for type '");
        sb.append(KClassExtKt.getFullName(kClass));
        sb.append("': ");
        sb.append(arrayList);
        sb.append(". Please use the 'bind<P,S>()' function to bind your instance from primary and secondary types.");
        throw new NoBeanDefFoundException(sb.toString());
    }

    private final BeanDefinition<?> findDefinitionByName(String str) {
        return (BeanDefinition) this.definitionsNames.get(str);
    }

    @NotNull
    public final Set<BeanDefinition<?>> findAllCreatedAtStartDefinition$koin_core() {
        return this.definitionsToCreate;
    }

    public final int size() {
        return this.definitions.size();
    }

    @Nullable
    public final BeanDefinition<?> getDefinition(@NotNull KClass<?> kClass) {
        Object obj;
        boolean z;
        Intrinsics.checkParameterIsNotNull(kClass, "clazz");
        Iterator it = this.definitions.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            BeanDefinition beanDefinition = (BeanDefinition) obj;
            if (Intrinsics.areEqual((Object) beanDefinition.getPrimaryType(), (Object) kClass) || beanDefinition.getSecondaryTypes().contains(kClass)) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (z) {
                break;
            }
        }
        return (BeanDefinition) obj;
    }

    public final void close() {
        for (BeanDefinition close : this.definitions) {
            close.close();
        }
        this.definitions.clear();
        this.definitionsNames.clear();
        this.definitionsPrimaryTypes.clear();
        this.definitionsToCreate.clear();
    }

    @NotNull
    public final List<BeanDefinition<?>> getDefinitionsForClass(@NotNull KClass<?> kClass) {
        Intrinsics.checkParameterIsNotNull(kClass, "clazz");
        Iterable allDefinitions = getAllDefinitions();
        Collection arrayList = new ArrayList();
        for (Object next : allDefinitions) {
            BeanDefinition beanDefinition = (BeanDefinition) next;
            if (Intrinsics.areEqual((Object) beanDefinition.getPrimaryType(), (Object) kClass) || (beanDefinition.getSecondaryTypes().contains(kClass) && !beanDefinition.isKind(Kind.Scope))) {
                arrayList.add(next);
            }
        }
        return (List) arrayList;
    }

    public final void loadModules(@NotNull Iterable<Module> iterable) {
        Intrinsics.checkParameterIsNotNull(iterable, "modules");
        for (Module saveDefinitions : iterable) {
            saveDefinitions(saveDefinitions);
        }
    }

    public final void unloadModules$koin_core(@NotNull Iterable<Module> iterable) {
        Intrinsics.checkParameterIsNotNull(iterable, "modules");
        for (Module removeDefinitions : iterable) {
            removeDefinitions(removeDefinitions);
        }
    }
}
