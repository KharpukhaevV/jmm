import java.util.concurrent.*;

public class ContextImpl implements Context {
    private final ThreadPoolExecutor executor;
    private final int taskCount;
    private int taskInterruptedCount;

    public ContextImpl(ThreadPoolExecutor executor, int taskCount) {
        this.executor = executor;
        this.taskCount = taskCount + 1;
        taskInterruptedCount = 0;
    }

    @Override
    public int getCompletedTaskCount() {
        return (int) executor.getCompletedTaskCount() - 1;
    }

    @Override
    public int getFailedTaskCount() {
        return taskCount - (int) executor.getCompletedTaskCount();
    }

    @Override
    public int getInterruptedTaskCount() {
        return taskInterruptedCount;
    }

    @Override
    public void interrupt() {
        executor.shutdownNow();
        taskInterruptedCount = executor.shutdownNow().size();
    }

    @Override
    public boolean isFinished() {
        if (executor.getCompletedTaskCount() == taskCount) {
            return true;
        } else return false;
    }
}