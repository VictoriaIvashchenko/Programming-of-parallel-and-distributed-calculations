import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class WorkerThread extends Thread {
    private final Queue<WorkItem<Integer, Integer>> queue;

    public WorkerThread(Queue<WorkItem<Integer, Integer>> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            WorkItem<Integer, Integer> task = queue.poll();
            if(task == null){
                return;
            }
            FutureResult<Integer> future = task.getFuture();
            Integer result = task.getFunction().apply(task.getArg());
            future.setResult(result);

        }
    }
}