import client.NodeWindow;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                NodeWindow nodeWindow1 = new NodeWindow("A1");
                NodeWindow nodeWindow2 = new NodeWindow("A2");
                NodeWindow nodeWindow3 = new NodeWindow("A3");
                NodeWindow nodeWindow4 = new NodeWindow("B1");
                NodeWindow nodeWindow5 = new NodeWindow("B2");
                NodeWindow nodeWindow6 = new NodeWindow("B3");
                NodeWindow nodeWindow7 = new NodeWindow("C1");
                NodeWindow nodeWindow8 = new NodeWindow("C2");
                NodeWindow nodeWindow9 = new NodeWindow("C3");
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
