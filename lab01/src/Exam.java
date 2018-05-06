import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Klasa przechowująca wszystkie odpowiedzi i klucz do testu
 */
public class Exam {
    ArrayList<Test> studentTests = new ArrayList<>();
    Test keyAnswers;
    int numberOfQuestions;
    int numberOfAnswers;

    private String directoryName;

    /**
     * Konstruktor sparametryzowany nazwa folderu, w ktorym przechowywane sa odpowiedzi
     * @param directoryName nazwa (lub sciezka do) folderu, w ktorym przechowywane sa odpowiedzi
     */
    public Exam(String directoryName){
        this.directoryName = directoryName;
        this.loadFromFiles();
    }

    /**
     * Funkcja wczytuje dane z katalogu
     * @return czy udalo sie wczytac dane
     */
    public boolean loadFromFiles(){
        List<String> results = getFilesFromDirectory();

        if(!results.contains("key.csv")){
            return false;
        }
        results.remove("key.csv");
        if(!loadKeyAnswers()) return false;

        for (String filename : results){
            if (filename.split("\\.")[1].equals("csv")) {
                Test test = new Test(numberOfQuestions,numberOfAnswers);
                test.loadFromFile(directoryName + "\\" + filename);
                studentTests.add(test);
            }
        }
        if (studentTests.size() == 0) return false;
        return true;
    }

    /**
     * Funkcja wczytuje klucz odpowiedzi do odpowiedniej zmiennej
     * @return czy udalo sie wczytac odpowiedzi z klucza
     */
    private boolean loadKeyAnswers(){
        Scanner in;
        try {
            in = new Scanner(new FileReader(directoryName + "\\key.csv"));
        } catch (FileNotFoundException e) {
            return false;
        }
        String header = in.nextLine();
        in.close();

        String[] headerArray = header.split(";");

        numberOfQuestions = Integer.parseInt(headerArray[0]);
        numberOfAnswers = Integer.parseInt(headerArray[1]);

        keyAnswers = new Test(numberOfQuestions,numberOfAnswers);
        if (!keyAnswers.loadFromFile(directoryName + "\\key.csv")) return false;
        return true;
    }

    /**
     * Funkcja pobiera nazwy wszystkich plikow w folderze
     * @return lista nazw plikow w danym folderze
     */
    private ArrayList<String> getFilesFromDirectory(){
        ArrayList<String> results = new ArrayList<String>();

        File[] files = new File(directoryName).listFiles();

        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        return results;
    }
}
