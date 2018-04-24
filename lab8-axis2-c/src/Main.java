import client.NodeWindow;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                NodeWindow nodeWindow1 = new NodeWindow();
                NodeWindow nodeWindow2 = new NodeWindow();
                NodeWindow nodeWindow3 = new NodeWindow();
                NodeWindow nodeWindow4 = new NodeWindow();
                NodeWindow nodeWindow5 = new NodeWindow();
                NodeWindow nodeWindow6 = new NodeWindow();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
