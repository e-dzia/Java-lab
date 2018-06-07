import java.util.ArrayList;

public class Main {
    static int numberOfThreads = 3;
    static int max_cache_capacity = 5;
    
    public static void main(String[] args) {
        ArrayList<AnalizeThread> threads = new ArrayList <>();
        for (int i = 0; i < numberOfThreads; i++){
            threads.add(new AnalizeThread(max_cache_capacity));
            threads.get(i).start();
        }
    }
}
