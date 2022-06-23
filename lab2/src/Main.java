import java.util.List;

public class Main {
    public static void main(String[] args){
        CustomExecutor customExecutor = new CustomExecutor(2);

        List<FutureResult<Integer>> futures = customExecutor.map(x -> x * x, 1, 2, 3, 4, 5);
        for (FutureResult<Integer> future : futures) {
            System.out.printf("Future result: %d \n", future.getResult());
            System.out.println(java.time.LocalTime.now());
        }

        customExecutor.shutdown();
        }
    }





