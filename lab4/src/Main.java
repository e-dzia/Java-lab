import java.io.IOException;
import java.lang.reflect.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        GraphArray graphArray = new GraphArray();
        graphArray.loadFromFile("tsp10.txt");
        graphArray.print();

        //TODO:zaladowac greedy, bruteforce i bestof
        Object o = new GraphArray();
        Class c = o.getClass();


        //System.in.read();

        ClassLoader classLoader = new CCLoader(ClassLoader.getSystemClassLoader());
        Class bruteForce = classLoader.loadClass("BruteForceAlgorithm");
        System.out.println(bruteForce.getClassLoader());

        Class[] theInterfaces = bruteForce.getInterfaces();
        for (int i = 0; i < theInterfaces.length; i++) {
            String interfaceName = theInterfaces[i].getName();
            System.out.println(interfaceName);
        }
    }
}
