import java.util.concurrent.Callable;

public class Task<T> {
    MyCallable<? extends T> callable;
    private T result;
    public Task(Callable<? extends T> callable) {
        this.callable = (MyCallable<? extends T>) callable;
    }
    public T get() throws ResultException {
        if (result != null) {
            System.out.println(result+"1");
            return result;
        }
        else {
            synchronized (this) {
                if (result == null){
                    try {
                        result = callable.call();
                        System.out.println(result);
                    } catch (Exception e) {
                        throw new ResultException();
                    }
                }
            }
        }
        return result;
    }
}
