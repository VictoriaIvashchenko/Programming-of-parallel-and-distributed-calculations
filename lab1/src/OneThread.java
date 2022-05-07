import java.io.*;
import java.util.*;

import static java.lang.System.currentTimeMillis;

public class OneThread {

    public static void countWords(String path) throws IOException {
        File[] list = Objects.requireNonNull(new File(path).listFiles());
        TreeMap<String, Integer> wordMap = new TreeMap<>();
        long time =  currentTimeMillis();
        for (File file : list) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while (line != null) {

                if(wordMap.get(line) == null){
                    wordMap.put(line, 1);
                }else {
                    Integer num = wordMap.get(line);
                    wordMap.replace(line, ++num);
                }

                line = reader.readLine();
            }

        }
        System.out.println(wordMap);
        System.out.println("Time elapsed: "+ (currentTimeMillis() - time) +" ms\n");
    }


}
