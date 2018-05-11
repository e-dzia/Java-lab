package app;

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
        Scanner in = new Scanner("author;title;publication_date;price\n" +
                "Stephen King;The Long Walk;1979-07-01;12320.5\n" +
                "Rafał Kosik;Felix, Net i Nika oraz Gang Niewidzialnych ludzi;2004-11-15;15.7\n" +
                "Javier Sierra;El fuego invisible;2017-01-01;1234525.7\n" +
                "Stephen King;The Long Walk;1979-07-01;12320.5\n" +
                "Rafał Kosik;Felix, Net i Nika oraz Gang Niewidzialnych ludzi;2004-11-15;15.7\n" +
                "Javier Sierra;El fuego invisible;2017-01-01;1234525.7\n" +
                "Stephen King;The Long Walk;1979-07-01;12320.5\n" +
                "Rafał Kosik;Felix, Net i Nika oraz Gang Niewidzialnych ludzi;2004-11-15;15.7\n" +
                "Javier Sierra;El fuego invisible;2017-01-01;1234525.7\n" +
                "Stephen King;The Long Walk;1979-07-01;12320.5\n" +
                "Rafał Kosik;Felix, Net i Nika oraz Gang Niewidzialnych ludzi;2004-11-15;15.7\n" +
                "Javier Sierra;El fuego invisible;2017-01-01;1234525.7\n" +
                "Stephen King;The Long Walk;1979-07-01;12320.5\n" +
                "Rafał Kosik;Felix, Net i Nika oraz Gang Niewidzialnych ludzi;2004-11-15;15.7\n" +
                "Javier Sierra;El fuego invisible;2017-01-01;1234525.7\n" +
                "Stephen King;The Long Walk;1979-07-01;12320.5\n" +
                "Rafał Kosik;Felix, Net i Nika oraz Gang Niewidzialnych ludzi;2004-11-15;15.7\n" +
                "Javier Sierra;El fuego invisible;2017-01-01;1234525.7\n");
       /*Scanner in;
        try {
            in = new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e){
            return false;
        }*/
        String firstLine = in.nextLine();
        this.header = firstLine.split(";");
        
        while(in.hasNext()){
            String data = in.nextLine();
            //String folderName = filename.split("\\\\").length > 1 ? filename.split("\\\\")[0] : "";
            Book book = new Book(data);
            books.add(book);
        }
        
        return true;
    }
    
    
}