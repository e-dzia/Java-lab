import com.sun.org.apache.xpath.internal.SourceTree;

import java.lang.ref.ReferenceQueue;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static  ExecutorService exec = Executors.newFixedThreadPool(5);
    public static ReferenceQueue<Collection> referenceQueue = new ReferenceQueue<>();
    public static Map<Integer,Collection> collectionMap
            = Collections.synchronizedMap(new WeakHashMap<Integer,Collection>());

    private static class Task implements Runnable {
        static Random generator = new Random();

        public void run() {
            for (int i = 0; i < 5; i++){
                int grain = generator.nextInt(4000);
                if (!collectionMap.containsKey(grain)){
                    collectionMap.put(grain, new Collection(grain));
                }
                Double min = collectionMap.get(grain).countMinimum();
                synchronized (System.out) {
                    System.out.println(grain + " " + Thread.currentThread().getId());
                    System.out.println("Min: " + min + " " + Thread.currentThread().getId());
                    System.out.println(collectionMap.keySet().size());
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.gc();
            System.out.println("Col: " + Collection.finalizes);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++){
            exec.execute(new Task());
        }
        if (!exec.isShutdown()) {
            exec.shutdown();
            System.out.println("Col: " + Collection.finalizes);
        }
    }
}
