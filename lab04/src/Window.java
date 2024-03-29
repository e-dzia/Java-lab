import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Window {
    private JPanel mainPanel;
    private JButton zaladujButton;
    private JButton wybierzButton;
    private JComboBox comboBox1;
    private JTextPane textPane1;
    private JButton pokazInformacjeButton;
    private JButton wyladujButton;
    private JButton odswiezButton;
    private JComboBox comboBox2;

    private ArrayList<Class> listOfClasses = new ArrayList<>();
    private ArrayList<String> foundClasses  = new ArrayList<>();


    public Window() {
        textPane1.setEditable(false);
        zaladujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClassLoader classLoader = new CustomClassLoader(ClassLoader.getSystemClassLoader());
                Class algorithmClass = null;
                String filename = (String) comboBox2.getSelectedItem();
                try {
                    algorithmClass = classLoader.loadClass(filename);
                } catch (ClassNotFoundException e1) {
                    textPane1.setText("Nie znaleziono klasy.");
                }
                listOfClasses.add(algorithmClass);
                updateGUI();
            }
        });
        wybierzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Class algorithmClass = (Class) comboBox1.getSelectedItem();
                Object algorithmObject = null;

                Class[] parametersClass = new Class[] {Main.graphArray.getArray().getClass()};
                Object[] parameters = {Main.graphArray.getArray()};
                Constructor algorithmConstructor;
                try {
                    algorithmConstructor = algorithmClass.getConstructor(parametersClass);
                    algorithmObject = algorithmConstructor.newInstance(parameters);
                } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException | NullPointerException e1) {
                    textPane1.setText("Nie wybrano klasy.");
                    return;
                }

                Class[] arguments = new Class[0];
                Method algorithmMethod = null;
                try {
                    algorithmMethod = algorithmClass.getMethod("algorithm",arguments);
                } catch (NoSuchMethodException e1) {
                    e1.printStackTrace();
                }
                try {
                    algorithmMethod.invoke(algorithmObject,arguments);
                } catch (IllegalAccessException | InvocationTargetException e1) {
                    e1.printStackTrace();
                }

                Method printMethod = null;
                try {
                    printMethod = algorithmClass.getMethod("print",arguments);
                } catch (NoSuchMethodException e1) {
                    e1.printStackTrace();
                }
                Object output = null;
                try {
                    output = printMethod.invoke(algorithmObject,arguments);
                } catch (IllegalAccessException | InvocationTargetException e1) {
                    e1.printStackTrace();
                }

                textPane1.setText((String) output);
            }
        });
        pokazInformacjeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Class algorithmClass = (Class) comboBox1.getSelectedItem();
                Object algorithmObject = null;

                Class[] parametersClass = new Class[] {Main.graphArray.getArray().getClass()};
                Object[] parameters = {Main.graphArray.getArray()};
                Constructor algorithmConstructor;
                try {
                    algorithmConstructor = algorithmClass.getConstructor(parametersClass);
                    algorithmObject = algorithmConstructor.newInstance(parameters);
                } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException | NullPointerException e1) {
                    textPane1.setText("Nie wybrano klasy.");
                    return;
                }

                Class[] arguments = new Class[0];
                Method infoMethod = null;
                try {
                    infoMethod = algorithmClass.getMethod("getInfo",arguments);
                } catch (NoSuchMethodException e1) {
                    e1.printStackTrace();
                }
                Object info = null;
                try {
                    info = infoMethod.invoke(algorithmObject,arguments);
                } catch (IllegalAccessException | InvocationTargetException e1) {
                    e1.printStackTrace();
                }
                textPane1.setText((String) info);
            }
        });
        wyladujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Class algorithmClass = (Class) comboBox1.getSelectedItem();
                listOfClasses.remove(algorithmClass);
                updateGUI();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                System.gc();
            }
        });
        odswiezButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                foundClasses.clear();
                File folder = new File("lab4-dodatkowe pliki");
                File[] listOfFiles = folder.listFiles();

                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".class")) {
                        foundClasses.add(listOfFiles[i].getName().split("\\.")[0]);
                    }
                }
                updateGUI();
                System.gc();
            }
        });
    }


    public static void createAndShowGUI() {
        JFrame frame = new JFrame("ClassLoader");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Window window = new Window();
        frame.setContentPane(window.mainPanel);

        frame.pack();
        frame.setVisible(true);
    }

    private void updateGUI(){
        comboBox1.removeAllItems();
        for (Object loadedClass : listOfClasses) {
            comboBox1.addItem(loadedClass);
        }

        comboBox2.removeAllItems();
        for (Object foundClass : foundClasses) {
            comboBox2.addItem(foundClass);
        }
    }

}
