import javax.swing.*;

public class Window {

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("JavaBeans");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setContentPane(new Notes());

        frame.pack();
        frame.setVisible(true);
    }

}
