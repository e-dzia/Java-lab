import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Klasa przechowujaca dane o kolekcji ksiazek
 */
public class Collection {
    ArrayList<Book> books = new ArrayList<>();
    String[] header;

    Collection(String filename) throws IOException, ParseException {
        loadFromFile(filename);
    }

    public boolean loadFromFile(String filename) throws IOException, ParseException {
        Scanner in;
        try {
            in = new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e){
            return false;
        }
        String firstLine = in.nextLine();
        this.header = firstLine.split(";");

        while(in.hasNext()){
            String data = in.nextLine();
            String folderName = filename.split("\\\\").length > 1 ? filename.split("\\\\")[0] : "";
            Book book = new Book(data,folderName);
            books.add(book);
        }

        return true;
    }


}
