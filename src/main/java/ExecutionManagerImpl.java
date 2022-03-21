import java.util.concurrent.*;

public class ExecutionManagerImpl implements ExecutionManager {
    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        Semaphore semaphore = new Semaphore(1);
        try {
            for (Runnable task : tasks) {
                Thread.sleep(100);
                executor.execute(task);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.run();
        return new ContextImpl(executor, tasks.length);
    }
}

