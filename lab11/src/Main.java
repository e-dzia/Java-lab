import java.util.Scanner;

public class Main {
    static {
        System.loadLibrary("lab11"); // Load native library at runtime
        // lab11.dll (Windows) or liblab11.so (Unixes)
    }
    
    private native void helloWorld();
    private native boolean isPrime(int num);
    private native float [] forEachElement(float [] array, float val, String op);
    
    public static void main(String[] args) {
        new Main().helloWorld();
        
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        System.out.println(new Main().isPrime(number));
       /* for (int i = 0; i < 20; i++){
            System.out.println(i + " " + new Main().isPrime(i));
        }*/
        
        float[] numbers = {22.1f, 33.2f, 55.3f};
        System.out.println("ADD 5");
        float[] results = new Main().forEachElement(numbers, 5, "add");
        for (int i = 0; i < results.length; i++) {
            System.out.println(i + " " + results[i]);
        }
        System.out.println("SUBTRACT 2");
        results = new Main().forEachElement(numbers, 2, "subtract");
        for (int i = 0; i < results.length; i++) {
            System.out.println(i + " " + results[i]);
        }
        System.out.println("MULTIPLY 6");
        results = new Main().forEachElement(numbers, 6, "multiply");
        for (int i = 0; i < results.length; i++) {
            System.out.println(i + " " + results[i]);
        }
        System.out.println("DIVIDE 5");
        results = new Main().forEachElement(numbers, 0, "divide");
        for (int i = 0; i < results.length; i++) {
            System.out.println(i + " " + results[i]);
        }
    }

}
