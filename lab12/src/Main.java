import java.awt.*;

public class Main {
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GameWindow gameWindow = new GameWindow();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    
    }
}
