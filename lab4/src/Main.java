import java.awt.*;
import java.io.IOException;
import java.lang.reflect.*;
import javax.swing.*;

public class Main {
    static GraphArray graphArray;


    public static void main(String[] args) throws ClassNotFoundException, IOException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, InterruptedException {
        graphArray = new GraphArray();
        graphArray.loadFromFile("tsp10.txt");

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Window.createAndShowGUI();
            }
        });
/*
       // Thread.sleep(10000);
        loadClass("BruteForceAlgorithm");
        System.out.println();
        loadClass("GreedyAlgorithm");
        System.out.println();
        loadClass("BestOfAlgorithm");
        System.out.println();
        System.gc();
        //Thread.sleep(10000);
*/
    }

    private static void loadClass(String name) throws ClassNotFoundException, IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        ClassLoader classLoader = new CustomClassLoader(ClassLoader.getSystemClassLoader());
        Class algorithmClass = classLoader.loadClass(name);

        System.out.println("Klasa " + algorithmClass.getName() + " zaladowana ladowaczem " + algorithmClass.getClassLoader());

        Class[] theInterfaces = algorithmClass.getInterfaces();
        boolean implementsAlgorithm = false;
        for (int i = 0; i < theInterfaces.length; i++) {
            String interfaceName = theInterfaces[i].getName();
            if (interfaceName.equals("Algorithm")) implementsAlgorithm = true;
        }
        if (!implementsAlgorithm) return;

        Object algorithmObject = null;

        Class[] parametersClass = new Class[] {graphArray.getArray().getClass()};
        Object[] parameters = {graphArray.getArray()};
        Constructor algorithmConstructor;
        try {
            algorithmConstructor = algorithmClass.getConstructor(parametersClass);
            algorithmObject = algorithmConstructor.newInstance(parameters);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        Class[] arguments = new Class[0];
        Method infoMethod = algorithmClass.getMethod("getInfo",arguments);
        Object info = infoMethod.invoke(algorithmObject,arguments);
        System.out.println(info);

        Method algorithmMethod = algorithmClass.getMethod("algorithm",arguments);
        algorithmMethod.invoke(algorithmObject,arguments);

        Method printMethod = algorithmClass.getMethod("print",arguments);
        Object output = printMethod.invoke(algorithmObject,arguments);
        System.out.println(output);

        System.gc();
    }
}
