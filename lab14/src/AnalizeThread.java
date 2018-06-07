import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.TreeMap;

public class AnalizeThread extends Thread {
    CacheMemory cacheMemory;
    private boolean stillRunning = true;
    
    AnalizeThread(int max_cache_capacity){
        this.cacheMemory = new CacheMemory(max_cache_capacity);
    }
    
    @Override
    public void run() {
        while (this.stillRunning){
            int analizeType = new Random().nextInt(3);
            String filename = randomFileToAnalize();
            synchronized (cacheMemory) {
                try {
                    if (!cacheMemory.filesContents.containsKey(filename)){
                        byte[] encoded = new byte[0];
                        try {
                            encoded = Files.readAllBytes(Paths.get(filename));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        cacheMemory.put(filename, new String(encoded));
                        /*try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
        
                    }
                    analizeFile(filename, analizeType);
                }
                catch(OutOfMemoryError e){
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    public void stopThread() {
        this.stillRunning = false;
    }
    
    private void analizeFile(String filename, int analizeType) {
        switch(analizeType){
            case 0:
                try {
                    countNumberOfRs(filename);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                try {
                    histogramOfVowels(filename);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    compareSmallAndBigLetters(filename);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
        
    }
    
    private void compareSmallAndBigLetters(String filename) throws IOException {
        String input = cacheMemory.filesContents.get(filename);
        int smallLetters = 0;
        int bigLetters = 0;
        
        for (int i = 0; i < input.length(); i++){
            if (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z'){
                bigLetters++;
            }
            else if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z'){
                smallLetters++;
            }
        }
        
        System.out.println(currentThread().getId() + " " + filename + ": Small letters: " + smallLetters + "\tBig letters: " + bigLetters);
    }
    
    private void histogramOfVowels(String filename) throws IOException {
        TreeMap<Character, Integer> histogram = new TreeMap <>();
    
        String input = cacheMemory.filesContents.get(filename);
        
        input = input.toLowerCase();
        for (int i = 0; i < input.length(); i++){
            switch (input.charAt(i)){
                case 'a':
                    putLetterInHistogram(histogram, 'a');
                    break;
                case 'e':
                    putLetterInHistogram(histogram, 'e');
                    break;
                case 'i':
                    putLetterInHistogram(histogram, 'i');
                    break;
                case 'o':
                    putLetterInHistogram(histogram, 'o');
                    break;
                case 'u':
                    putLetterInHistogram(histogram, 'u');
                    break;
                case 'y':
                    putLetterInHistogram(histogram, 'y');
                    break;
            }
        }
        System.out.println(currentThread().getId() + " " + filename + ": Histogram of vowels:");
        printHistogram(histogram);
    }
    
    private void putLetterInHistogram(TreeMap <Character, Integer> histogram, Character letter) {
        if (histogram.containsKey(letter)){
            histogram.put(letter, histogram.get(letter) + 1);
        }
        else {
            histogram.put(letter, 1);
        }
    }
    
    private static void printHistogram(TreeMap<Character, Integer> histogram) {
        Character key = histogram.firstKey();
        for (char i = key; i <= histogram.lastKey(); i++){
            if (histogram.containsKey(i)){
                System.out.print(i + " " + histogram.get(i));
                /*for (int j = 0; j < histogram.get(i); j++){
                    System.out.print("*");
                }*/
                System.out.println();
            }
        }
    }
    
    private void countNumberOfRs(String filename) throws IOException {
        String input = cacheMemory.filesContents.get(filename);
        int numberOfRs = 0;
        
        for (int i = 0; i < input.length(); i++){
            if (input.charAt(i) == 'r' || input.charAt(i) == 'R'){
                numberOfRs++;
            }
        }
        System.out.println(currentThread().getId() + " " + filename + ": Number of R's: " + numberOfRs);
    }
    
    private String randomFileToAnalize() {
        File folder = new File("files/");
        File[] listOfFiles = folder.listFiles();
        int random = new Random().nextInt(listOfFiles.length);
        
        return listOfFiles[random].getAbsolutePath();
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            new AnalizeThread(5).start();
        }
        
    }
}
