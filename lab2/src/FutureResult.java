import java.util.concurrent.CountDownLatch;

public class FutureResult<T> {
    private final CountDownLatch latch;

    public FutureResult() {
        this.latch = new CountDownLatch(1);
    }

    public T getResult() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void setResult(T result) {
        this.result = result;
        latch.countDown();
    }

    private T result;

}
