package app;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
    
    public void setMaxCapacity(int maxCapacity){
        if (maxCapacity > MAX_CAPACITY){
            MAX_CAPACITY = maxCapacity;
        }
        else if (maxCapacity < MAX_CAPACITY){
            System.out.println("####################" + maxCapacity + " " + MAX_CAPACITY);
            for (int i = 0; i < MAX_CAPACITY - maxCapacity; i++){
                filesContents.remove(filesContents.firstKey());
            }
            MAX_CAPACITY = maxCapacity;
        }
    }
    
    public void put(String filename, String content) {
        if (filesContents.size() >= MAX_CAPACITY && !filesContents.containsKey(filename)) {
            int index = new Random().nextInt(MAX_CAPACITY);
            filesContents.remove(filesContents.keySet().toArray()[index]);
            filesContents.put(filename, content);
        } else {
            filesContents.put(filename, content);
        }
    }
    
    public int getFreeEntries(){
        return MAX_CAPACITY-filesContents.size();
    }
    
    public int getOccupiedEntries(){
        return filesContents.size();
    }
    
    public int getOccupiedSpace(){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(filesContents);
            oos.close();
            return baos.size();
        }
        catch (IOException e){
            e.printStackTrace();
            return -1;
        }
    }
}
