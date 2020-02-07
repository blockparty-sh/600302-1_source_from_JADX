package p015io.grpc.perfmark;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: io.grpc.perfmark.PerfMarker */
public @interface PerfMarker {
    String computedTag() default "";

    boolean immutable() default false;

    String taskName();
}
