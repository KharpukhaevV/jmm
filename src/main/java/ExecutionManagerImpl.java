import java.util.concurrent.*;

public class ExecutionManagerImpl implements ExecutionManager {
    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        Semaphore semaphore = new Semaphore(3);
        for (Runnable task : tasks) {
            try {
//                semaphore.acquire();
                executor.execute(task);
                Thread.sleep(100);
                System.out.println("The stream fell asleep for 2 seconds");
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
//        semaphore.release();
        executor.execute(callback);
        System.out.println("Callback");
        return new ContextImpl(executor, tasks.length);
    }
}

