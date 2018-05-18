import java.util.Scanner;

public class Main {
    static {
        System.loadLibrary("lab11"); // Load native library at runtime
        // lab11.dll (Windows) or liblab11.so (Unixes)
    }
    
    // Declare a native method helloWorld() that receives nothing and returns void
    private native void helloWorld();
    private native boolean isPrime(int num);
    private native float [] forEachElement(float [] array, float val, String op);
    
    // Test Driver
    public static void main(String[] args) {
        new Main().helloWorld();  // invoke the native method
        for (int i = 0; i < 20; i++){
            System.out.println(i + " " + new Main().isPrime(i));
        }
        
        float[] numbers = {22.1f, 33.2f, 55.3f};
        System.out.println("ADD");
        float[] results = new Main().forEachElement(numbers, 5, "add");
        for (int i = 0; i < results.length; i++) {
            System.out.println(i + " " + results[i]);
        }
        System.out.println("SUBTRACT");
        results = new Main().forEachElement(numbers, 2, "subtract");
        for (int i = 0; i < results.length; i++) {
            System.out.println(i + " " + results[i]);
        }
        System.out.println("MULTIPLY");
        results = new Main().forEachElement(numbers, 6, "multiply");
        for (int i = 0; i < results.length; i++) {
            System.out.println(i + " " + results[i]);
        }
        System.out.println("DIVIDE");
        results = new Main().forEachElement(numbers, 5, "divide");
        for (int i = 0; i < results.length; i++) {
            System.out.println(i + " " + results[i]);
        }
    }

}
