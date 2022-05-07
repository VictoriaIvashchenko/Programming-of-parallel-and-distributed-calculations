import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.lang.System.currentTimeMillis;
import static java.util.stream.Collectors.*;

public class OneThread2 {
    public static void countWords(String path) {
        List<File> listFiles = Arrays.asList(Objects.requireNonNull(new File(path).listFiles()));
        long time = currentTimeMillis();
        Map<String, Integer> result = listFiles.stream()
                .map(OneThread2::countWords)
                .reduce(OneThread2::mergeMap)
                .orElse(new TreeMap<>());
        System.out.println(result);
        System.out.println("Time elapsed: "+ (currentTimeMillis() - time) +" ms\n");
    }

    public static Map<String, Integer> countWords(File file){
        Map<String, Integer> countMap = null;

        try (Stream<String> lineStream = Files.lines(file.toPath())) {
            countMap = lineStream.collect(toMap(
                    s -> s,
                    s -> 1,
                    Integer::sum));
        } catch (IOException ignored) {
        }
        return countMap;
    }

    public static Map<String, Integer> mergeMap(Map<String, Integer> map1, Map<String, Integer> map2){
        BiFunction<Integer, Integer, Integer> bFunc = Integer::sum;

        for (Map.Entry<String, Integer> entry : map2.entrySet()) {
            map1.merge(entry.getKey(), entry.getValue(), bFunc);
        }
        return map1;
    }


}
