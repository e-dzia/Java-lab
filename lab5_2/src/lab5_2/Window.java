package lab5_2;

import javax.swing.*;

public class Window {
    private JPanel panel1;

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("JavaBeans");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setContentPane(new Window().panel1);

        frame.pack();
        frame.setVisible(true);
    }

}
