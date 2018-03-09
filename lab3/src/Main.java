import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static  ExecutorService exec = Executors.newFixedThreadPool(5);
    public static Map<Integer,Collection> collectionMap = Collections.synchronizedMap(new WeakHashMap<>());
    public static ArrayList<Boolean> allFinished = new ArrayList<Boolean>();

    private static class Task implements Runnable {
        static Random generator = new Random();
        PrintWriter output;

        public void run() {
            try {
                output = new PrintWriter("output_" + Thread.currentThread().getId()+".txt", "UTF-8");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            boolean madeNewCollection;

            for (int i = 0; i < 10; i++){
                int grain = generator.nextInt(4000);
                madeNewCollection = false;
                if (!collectionMap.containsKey(grain)){
                    collectionMap.put(grain, new Collection(grain));
                    madeNewCollection = true;
                }
                Double min = collectionMap.get(grain).countMinimum();
                String message = "Watek: " + Thread.currentThread().getId() +
                        ", Ziarno: " + grain + ", Min: " + min + ", Stworzenie kolekcji: " + madeNewCollection;
                synchronized (System.out) {
                    System.out.println(message);
                    System.out.println(Collection.deletedObjects);
                }
                output.write(message+"\n");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            output.close();
            synchronized (allFinished){
                allFinished.remove(0);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++){
            exec.execute(new Task());
            allFinished.add(false);
        }
        while(allFinished.size()>0) {
            Thread.sleep(1000);
        }
        exec.shutdown();
        System.out.println("Col: " + Collection.deletedObjects);
    }
}
