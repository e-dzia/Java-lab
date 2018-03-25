package lab5_2;

import notes.Notes;

import javax.swing.*;

public class Window {
    private JPanel panel1;
    private Notes notes;

    Window(){
        notes.setSizeOfText(10);
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("JavaBeans-2");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setContentPane(new Window().panel1);

        frame.pack();
        frame.setVisible(true);
    }

}
