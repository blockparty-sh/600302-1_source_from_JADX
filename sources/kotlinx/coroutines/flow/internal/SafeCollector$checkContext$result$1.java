package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext.C2916Key;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, mo37405d2 = {"<anonymous>", "", "T", "count", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SafeCollector.kt */
final class SafeCollector$checkContext$result$1 extends Lambda implements Function2<Integer, Element, Integer> {
    final /* synthetic */ SafeCollector this$0;

    SafeCollector$checkContext$result$1(SafeCollector safeCollector) {
        this.this$0 = safeCollector;
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return Integer.valueOf(invoke(((Number) obj).intValue(), (Element) obj2));
    }

    public final int invoke(int i, @NotNull Element element) {
        Intrinsics.checkParameterIsNotNull(element, "element");
        C2916Key key = element.getKey();
        Element element2 = this.this$0.collectContext.get(key);
        if (key != Job.Key) {
            return element != element2 ? Integer.MIN_VALUE : i + 1;
        }
        Job job = (Job) element2;
        Job access$transitiveCoroutineParent = this.this$0.transitiveCoroutineParent((Job) element, job);
        if (access$transitiveCoroutineParent == job) {
            return i + 1;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Flow invariant is violated: emission from another coroutine is detected (child of ");
        sb.append(access$transitiveCoroutineParent);
        sb.append(", expected child of ");
        sb.append(job);
        sb.append("). ");
        sb.append("FlowCollector is not thread-safe and concurrent emissions are prohibited. To mitigate this restriction please use 'flowChannel' builder instead of 'flow'");
        throw new IllegalStateException(sb.toString().toString());
    }
}
