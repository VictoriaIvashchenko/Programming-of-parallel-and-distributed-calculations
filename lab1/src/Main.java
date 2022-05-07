import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        String path = "docs-1000-10000";
        OneThread.countWords(path);
        OneThread2.countWords(path);
        MultiThread.countWords(path);
    }
}
