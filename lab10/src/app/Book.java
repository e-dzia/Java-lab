package app;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Klasa przechowujaca dane o jednej ksiazce
 */
public class Book {
    String author;
    String title;
    Date publicationDate;
    double price;
    //Icon cover;

    Book(String data) throws ParseException, IOException {
        String[] separateData = data.split(";");
        author = separateData[0];
        title = separateData[1];
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        publicationDate = dateFormat.parse(separateData[2]);
        price = Double.parseDouble(separateData[3]);
       // cover = new ImageIcon(folderName + "\\" + separateData[4]);
    }

    public Object[] toArray(){
        Object[] array = new Object[5];
        array[0] = 0;
        array[1] = author;
        array[2] = title;
        array[3] = publicationDate;
        array[4] = price;
      //  array[5] = cover;
        return array;
    }
}
