import java.util.function.Function;

public class WorkItem<Targ, TResult>{
    private Function<Integer, Integer> function;
    private Targ arg;

    FutureResult<TResult> future;

    public WorkItem(Function<Integer, Integer> function, Targ arg) {
        this.function = function;
        this.arg = arg;
        this.future = new FutureResult<>();
    }

    public Function<Integer, Integer> getFunction() {
        return function;
    }

    public Targ getArg() {
        return arg;
    }

    public FutureResult<TResult> getFuture() {
        return future;
    }

    public void setFuture(FutureResult<TResult> future) {
        this.future = future;
    }
}
