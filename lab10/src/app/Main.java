package app;

import java.io.IOException;
import java.text.ParseException;

/**
 * Klasa glowna - tworzy kolekcję i okienko GUI
 */
public class Main {
    static Collection collection;

    public static void main(String[] args) throws IOException, ParseException {
        collection = new Collection("Data\\books.csv");

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Window.createAndShowGUI();
            }
        });
    }
}
