package org.koin.core.instance;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.koin.core.KoinApplication;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.error.BadScopeInstanceException;
import org.koin.core.error.ScopeNotCreatedException;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;
import org.koin.core.scope.ScopeDefinition;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u001c\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\nH\u0016J\u001b\u0010\u000f\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0002\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, mo37405d2 = {"Lorg/koin/core/instance/ScopeDefinitionInstance;", "T", "Lorg/koin/core/instance/DefinitionInstance;", "beanDefinition", "Lorg/koin/core/definition/BeanDefinition;", "(Lorg/koin/core/definition/BeanDefinition;)V", "values", "", "", "checkScopeResolution", "", "definition", "scope", "Lorg/koin/core/scope/Scope;", "close", "get", "context", "Lorg/koin/core/instance/InstanceContext;", "(Lorg/koin/core/instance/InstanceContext;)Ljava/lang/Object;", "isCreated", "", "release", "koin-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ScopeDefinitionInstance.kt */
public final class ScopeDefinitionInstance<T> extends DefinitionInstance<T> {
    private final Map<String, T> values = new ConcurrentHashMap();

    public ScopeDefinitionInstance(@NotNull BeanDefinition<T> beanDefinition) {
        Intrinsics.checkParameterIsNotNull(beanDefinition, "beanDefinition");
        super(beanDefinition);
    }

    public boolean isCreated(@NotNull InstanceContext instanceContext) {
        Intrinsics.checkParameterIsNotNull(instanceContext, "context");
        return (instanceContext.getScope() == null || this.values.get(instanceContext.getScope().getId()) == null) ? false : true;
    }

    public void release(@NotNull InstanceContext instanceContext) {
        Intrinsics.checkParameterIsNotNull(instanceContext, "context");
        Scope scope = instanceContext.getScope();
        if (scope != null) {
            if (KoinApplication.Companion.getLogger().isAt(Level.DEBUG)) {
                Logger logger = KoinApplication.Companion.getLogger();
                StringBuilder sb = new StringBuilder();
                sb.append("releasing '");
                sb.append(scope);
                sb.append("' ~ ");
                sb.append(getBeanDefinition());
                sb.append(' ');
                logger.debug(sb.toString());
            }
            Function1 onRelease = getBeanDefinition().getOnRelease();
            if (onRelease != null) {
                Unit unit = (Unit) onRelease.invoke(this.values.get(scope.getId()));
            }
            this.values.remove(scope.getId());
            return;
        }
        throw new IllegalStateException("ScopeDefinitionInstance has no scope in context".toString());
    }

    public <T> T get(@NotNull InstanceContext instanceContext) {
        Intrinsics.checkParameterIsNotNull(instanceContext, "context");
        if (instanceContext.getKoin() == null) {
            throw new IllegalStateException("ScopeDefinitionInstance has no registered Koin instance".toString());
        } else if (!Intrinsics.areEqual((Object) instanceContext.getScope(), (Object) instanceContext.getKoin().getRootScope())) {
            Scope scope = instanceContext.getScope();
            if (scope != null) {
                checkScopeResolution(getBeanDefinition(), scope);
                String id = scope.getId();
                T t = this.values.get(id);
                if (t == null) {
                    t = create(instanceContext);
                    Map<String, T> map = this.values;
                    if (t != null) {
                        map.put(id, t);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Instance creation from ");
                        sb.append(getBeanDefinition());
                        sb.append(" should not be null");
                        throw new IllegalStateException(sb.toString().toString());
                    }
                }
                return t;
            }
            throw new IllegalStateException("ScopeDefinitionInstance has no scope in context".toString());
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("No scope instance created to resolve ");
            sb2.append(getBeanDefinition());
            throw new ScopeNotCreatedException(sb2.toString());
        }
    }

    public void close() {
        Function1 onClose = getBeanDefinition().getOnClose();
        if (onClose != null) {
            Unit unit = (Unit) onClose.invoke(null);
        }
        this.values.clear();
    }

    private final void checkScopeResolution(BeanDefinition<?> beanDefinition, Scope scope) {
        ScopeDefinition set = scope.getSet();
        Qualifier qualifier = set != null ? set.getQualifier() : null;
        Qualifier scopeName = beanDefinition.getScopeName();
        if (!Intrinsics.areEqual((Object) scopeName, (Object) qualifier)) {
            String str = ". Use a scope instance with scope '";
            String str2 = " defined for scope '";
            String str3 = "Can't use definition ";
            if (qualifier == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str3);
                sb.append(beanDefinition);
                sb.append(str2);
                sb.append(scopeName);
                sb.append("', with an open scope instance ");
                sb.append(scope);
                sb.append(str);
                sb.append(scopeName);
                sb.append('\'');
                throw new BadScopeInstanceException(sb.toString());
            } else if (scopeName != null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str3);
                sb2.append(beanDefinition);
                sb2.append(str2);
                sb2.append(scopeName);
                sb2.append("' with scope instance ");
                sb2.append(scope);
                sb2.append(str);
                sb2.append(scopeName);
                sb2.append("'.");
                throw new BadScopeInstanceException(sb2.toString());
            }
        }
    }
}
