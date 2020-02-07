package androidx.lifecycle;

import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "", "run"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: DispatchQueue.kt */
final class DispatchQueue$consumer$1 implements Runnable {
    final /* synthetic */ DispatchQueue this$0;

    DispatchQueue$consumer$1(DispatchQueue dispatchQueue) {
        this.this$0 = dispatchQueue;
    }

    public final void run() {
        if (this.this$0.canRun()) {
            Runnable runnable = (Runnable) this.this$0.queue.poll();
            if (runnable != null) {
                try {
                    runnable.run();
                } finally {
                    this.this$0.maybeEnqueueConsumer();
                }
            }
        }
    }
}
