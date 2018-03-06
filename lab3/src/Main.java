import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static  ExecutorService exec = Executors.newFixedThreadPool(5);
    public static Map<Integer,Collection> collectionMap = Collections.synchronizedMap(new WeakHashMap<>());

    private static class Task implements Runnable {
        static Random generator = new Random();

        public void run() {
            for (int i = 0; i < 10; i++){
                int grain = generator.nextInt(4000);
                if (!collectionMap.containsKey(grain)){
                    collectionMap.put(grain, new Collection(grain));
                }
                Double min = collectionMap.get(grain).countMinimum();
                synchronized (System.out) {
                    System.out.println(grain + " " + Thread.currentThread().getId());
                    System.out.println("Min: " + min + " " + Thread.currentThread().getId());
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++){
            exec.execute(new Task());
        }
        do{
            System.out.println("Col: " + Collection.finalizes);
        }while(exec.isShutdown());
    }
}
