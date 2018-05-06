package lab5_2;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Window.createAndShowGUI();
                } catch (Exception var2) {
                    var2.printStackTrace();
                }

            }
        });
    }
}
