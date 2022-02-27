package common.java.NotNull;

import java.util.function.Consumer;

public class FinalClassHelper<T> {
    private final T v;

    private FinalClassHelper(T v) {
        this.v = v;
    }

    public static <T> FinalClassHelper<T> build(T v) {
        return new FinalClassHelper<>(v);
    }

    public T get() {
        return v;
    }

    public FinalClassHelper<T> unwrap(Consumer<T> fn) {
        fn.accept(v);
        return this;
    }

    public FinalClassHelper<T> isNull(Runnable fn) {
        fn.run();
        return this;
    }

}
