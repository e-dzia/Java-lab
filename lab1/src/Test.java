import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Klasa przechowująca odpowiedzi na jeden test
 */
public class Test {
    int numberOfQuestions;
    int numberOfAnswers;
    Boolean[][] selectedAnswers;

    /**
     * Konstruktor sparametryzowany liczba pytan i liczba mozliwych odpowiedzi
     * @param numberOfQuestions liczba pytan na egzaminie
     * @param numberOfAnswers liczba mozliwych odpowiedzi na kazde pytanie
     */
    public Test(int numberOfQuestions, int numberOfAnswers){
        this.numberOfQuestions = numberOfQuestions;
        this.numberOfAnswers = numberOfAnswers;
        this.selectedAnswers = new Boolean[numberOfQuestions][numberOfAnswers];
    }

    /**
     * Funkcja wczytuje dany plik do tabeli
     * @param filename nazwa pliku (csv), z ktorego pobieramy dane nt. odpowiedzi
     * @return czy udalo sie wczytac dane
     */
    public boolean loadFromFile(String filename){
        Scanner in;
        try {
             in = new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e) {
            return false;
        }
        String header = in.nextLine();
        String[] headerArray = header.split(";");

        int questions = Integer.parseInt(headerArray[0]);
        int answers = Integer.parseInt(headerArray[1]);

        if (questions != numberOfQuestions) return false;
        if (answers != numberOfAnswers) return false;

        String studentAnswers;
        String[] studentAnswersArray;
        for (int i = 0; i < questions; i++){
            studentAnswers = in.nextLine();
            studentAnswersArray = studentAnswers.split(";");
            for (int j = 0; j < answers; j++){
                selectedAnswers[i][j] = studentAnswersArray[j].equals("1");
            }
        }
        return true;
    }

}
