public class Main {
    public static void main(String[] args) throws Exception {
//        Task<String> task = new Task<>(new MyCallable<>("Hello world!"));
//        for (int i = 0; i <10 ; i++) {
//            Thread thread = null;
//            try {
//                thread = new Thread(task.get());
//            } catch (ResultException e) {
//                e.printStackTrace();
//            }
//            thread.start();
//        }

        Runnable runnable1 = () -> System.out.println("Print from runnable - 1");
        Runnable runnable2 = () -> System.out.println("Print from runnable - 2");
        Runnable runnable3 = () -> System.out.println("Print from runnable - 3");
        Runnable callback = () -> System.out.println("Print from callback");

        ExecutionManager executionManager = new ExecutionManagerImpl();
        Context context = executionManager.execute(callback, runnable1, runnable2, runnable3);
        System.out.println("Успешно выполнено: " + context.getCompletedTaskCount());
        System.out.println("Вызвано Exception: " + context.getFailedTaskCount());
//        context.interrupt();
        System.out.println("Было прервано: " + context.getInterruptedTaskCount());
        System.out.println("Все было выполнено: " + context.isFinished());
    }
}
