package app;

import beans.AppConfig;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static int numberOfThreads = 3;
    static int cacheSize = 5;
    
    static CacheMemory cacheMemory = new CacheMemory(cacheSize);
    static ArrayList<AnalizeThread> threads = new ArrayList <>();
    
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        AppConfig mBean = new AppConfig(numberOfThreads, cacheSize);
        ObjectName name = new ObjectName("lab14:type=AppConfig");
        mbs.registerMBean(mBean, name);
        
        AnalizeThread.setCacheMemory(cacheMemory);
        for (int i = 0; i < numberOfThreads; i++){
            AnalizeThread analizeThread = new AnalizeThread();
            analizeThread.start();
            threads.add(analizeThread);
        }
    }
    
    public static void changeNumberOfThreads(int newNumberOfThreads){
        if (newNumberOfThreads < Main.numberOfThreads){
            //usuwanie watkow
            synchronized (threads) {
                for (int i = 0; i < Main.numberOfThreads - newNumberOfThreads; i++){
                    threads.get(i).stopThread();
                }
               for (AnalizeThread analizeThread: threads){
                    //System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$" + analizeThread.isStillRunning());
                    if (!analizeThread.isStillRunning()){
                       // System.out.println("Usuwam " + analizeThread.getId());
                        try {
                            if (analizeThread.isAlive())
                            analizeThread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                
                for (int i = 0; i < Main.numberOfThreads; i++){
                    //System.out.println("%%%%%%%%%%%%%%%%%%%%%%%" + threads.get(i).isStillRunning());
                    if (!threads.get(i).isStillRunning()){
                        //System.out.println("Usuwam " + threads.get(i).getId());
                        threads.remove(i);
                    }
                }
            }
        }
        else if (newNumberOfThreads > Main.numberOfThreads){
            //dodawanie watkow
            synchronized (threads) {
                for (int i = 0; i < newNumberOfThreads - Main.numberOfThreads; i++) {
//                    threads.add(new AnalizeThread(cacheMemory));
//                    threads.get(numberOfThreads + i).start();
                    AnalizeThread analizeThread = new AnalizeThread();
                    analizeThread.start();
                    threads.add(analizeThread);
                }
            }
        }
        Main.numberOfThreads = newNumberOfThreads;
    }
    
    public static void changeCacheSize(int newCacheSize){
        synchronized (cacheMemory) {
            cacheMemory.setMaxCapacity(newCacheSize);
        }
        cacheSize = newCacheSize;
    }
    
    public static String getStats(){
        return "Threads: " + numberOfThreads +
                "; Cache size: " + cacheSize +
                "; Cache occupied: " + cacheMemory.getOccupiedEntries() +
                "; Cache free: " + cacheMemory.getFreeEntries() +
                //"; Cache space: " + cacheMemory.getOccupiedSpace() +
                "; Errors: " + AnalizeThread.getNumberOfErrors();
    }
    
}
