import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class CacheMemory {
    Integer MAX_CAPACITY = 5;
    TreeMap<String, String> filesContents = new TreeMap<>();
    
    CacheMemory(int max_capacity){
        this.MAX_CAPACITY = max_capacity;
    }
    
    public void put(String filename, String content) {
        if (filesContents.size() >= MAX_CAPACITY && !filesContents.containsKey(filename)) {
            int index = new Random().nextInt(5);
            filesContents.remove(filesContents.keySet().toArray()[index]);
            filesContents.put(filename, content);
        } else {
            filesContents.put(filename, content);
        }
    }
}
