import java.io.*;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static int numberOfThreads = 5;
    static int numberOfIterations = 5;

    static  ExecutorService exec = Executors.newFixedThreadPool(numberOfThreads);
    static ReferenceQueue<Collection> referenceQueue = new ReferenceQueue<>();
    static Map<Integer,SoftReference<Collection>> collectionMap = Collections.synchronizedMap(new HashMap<>());
    static ArrayList<Boolean> allFinished = new ArrayList<>();

    private static class Task implements Runnable {
        static Random generator = new Random();
        PrintWriter output;

        public void run() {
            try {
                output = new PrintWriter("output_" + Thread.currentThread().getId()+".txt", "UTF-8");
            } catch (FileNotFoundException | UnsupportedEncodingException e){
                e.printStackTrace();
            }

            boolean madeNewCollection;

            for (int i = 0; i < numberOfIterations; i++){
                Integer grain = generator.nextInt(50);
                madeNewCollection = false;
                Double min = -1.0;
                synchronized (collectionMap){
                    if (!collectionMap.containsKey(grain) || collectionMap.get(grain).isEnqueued()){
                        System.out.println("\tTworze kolekcje:\t" + grain);
                        output.write("\tTworze kolekcje:\t" + grain + "\n");
                        collectionMap.put(grain, new SoftReference<>(new Collection(grain),referenceQueue));
                        madeNewCollection = true;
                    }
                    try {
                        min = collectionMap.get(grain).get().countMinimum();
                    }
                    catch (NullPointerException e1){
                        System.out.println("\t\tNull pointer: " + grain);
                    }
                }
                synchronized (System.out){
                    System.out.println("Watek: " + Thread.currentThread().getId() + ", Iteracja: " + i +
                            ", Ziarno: " + grain + ", Min: " + min + ", Stworzenie kolekcji: " + madeNewCollection);
                    //System.out.println(collectionMap.size());
                }
                output.write("Ziarno: " + grain + ", Min: " + min +"\n");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){
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
        for (int i = 0; i < Main.numberOfThreads; i++){
            exec.execute(new Task());
            allFinished.add(false);
        }
        while(allFinished.size()>0) {
            Thread.sleep(1000);
        }
        exec.shutdown();
        System.out.println("Usuniete kolekcje: " + Collection.deletedObjects);
    }
}