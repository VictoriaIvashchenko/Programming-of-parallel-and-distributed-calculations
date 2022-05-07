import java.io.File;
import java.util.*;
import java.util.concurrent.*;

import static java.lang.System.currentTimeMillis;

public class MultiThread {
    public static void countWords(String path) throws ExecutionException, InterruptedException {
        File[] listFiles = Objects.requireNonNull(new File(path).listFiles());
        long time = currentTimeMillis();

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        List<Future<Map<String, Integer>>> list = new ArrayList<>();
        for (File f : listFiles) {
            list.add(
                    CompletableFuture.supplyAsync(()->OneThread2.countWords(f), threadPool)
            );
        }
        List<Map<String, Integer>> oneFileResult = new ArrayList<>();
        for (Future<Map<String, Integer>> threadResult : list) {
            oneFileResult.add(threadResult.get());
        }
        Map<String, Integer> result = oneFileResult.stream().reduce(OneThread2::mergeMap).orElse(new TreeMap<>());
        threadPool.shutdown();
        System.out.println(result);
        System.out.println("Time elapsed: "+ (currentTimeMillis() - time) +" ms\n");
    }
}
