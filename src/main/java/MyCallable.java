import java.util.concurrent.Callable;

public class MyCallable<T> implements Callable {
    private T t;

    public MyCallable(T t) {
        this.t = t;
    }

    @Override
    public T call() throws Exception {
        if (t == null || t.equals("")) {
            throw new Exception();
        }
        return t;
    }
}
