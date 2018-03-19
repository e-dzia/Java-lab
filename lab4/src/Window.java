import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Window {
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton zaladujButton;
    private JButton wybierzButton;
    private JComboBox comboBox1;
    private JTextPane textPane1;
    private JButton pokazInformacjeButton;
    private JButton wyladujButton;

    private ArrayList<Class> listOfClasses = new ArrayList<Class>();
    private Object[] loadedClasses;


    public Window() {
        textPane1.setEditable(false);
        zaladujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClassLoader classLoader = new CustomClassLoader(ClassLoader.getSystemClassLoader());
                Class algorithmClass = null;
                try {
                    algorithmClass = classLoader.loadClass(textField1.getText());
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
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
                } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException e1) {
                    e1.printStackTrace();
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
                } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e1) {
                    e1.printStackTrace();
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
        loadedClasses = listOfClasses.toArray();
        comboBox1.removeAllItems();
        for (Object loadedClass : loadedClasses) {
            comboBox1.addItem(loadedClass);
        }
    }

}
