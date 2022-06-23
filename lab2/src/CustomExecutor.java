import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;

public class CustomExecutor {
    private int maxThreads;
    private final Queue<WorkItem<Integer, Integer>> queue;
    private WorkerThread[] threads;

    public CustomExecutor(int maxThreads) {
        this.maxThreads = maxThreads;
        queue = new LinkedBlockingQueue<>();
        threads = new WorkerThread[maxThreads];
        for (int i = 0; i < maxThreads; i++) {
            threads[i] = new WorkerThread(queue);
            threads[i].start();
        }
    }

    public FutureResult<Integer> execute(Function<Integer, Integer> function, int arg) {
        WorkItem<Integer, Integer> task = new WorkItem<>(function, arg);
        queue.add(task);
        return task.getFuture();
    }

    public List<FutureResult<Integer>> map(Function<Integer, Integer> function, int... args) {
        List<FutureResult<Integer>> futures = new LinkedList<>();
        for (int arg : args) {
            futures.add(execute(function, arg));
        }
        return futures;
    }

    public void shutdown() {
        for (WorkerThread thread : threads) {
            thread.stop();
        }
    }
}
