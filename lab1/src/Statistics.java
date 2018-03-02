import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Klasa wylicza statystyki dotyczące wszystkich odpowiedzi na test
 */
public class Statistics {
    Exam toCount;
    String directoryName;
    ArrayList<Integer> studentPoints = new ArrayList<>();
    ArrayList<Integer> questionPoints = new ArrayList<>();

    /**
     * Konstruktor sparametryzowany nazwa folderu, w ktorym przechowujemy egzaminy
     * @param directoryName nazwa (lub sciezka do) folderu, w ktorym przechowujemy egzaminy
     */
    public Statistics(String directoryName){
        this.directoryName = directoryName;
        this.toCount = new Exam(this.directoryName);
        countAllStudentPoints();
        countAllQuestionPoints();
    }

    /**
     * Funkcja liczy srednia liczbe punktow zdobyta przez studentow
     * @return średnia liczba punktow zdobyta przez studentow
     */
    public int averageNumberOfPointsPerStudent(){
        int average = 0;
        if (studentPoints.size() == 0) return -1;
        for (int i = 0; i < studentPoints.size(); i++){
            average += studentPoints.get(i);
        }
        average /= studentPoints.size();
        return average;
    }

    /**
     * Funkcja wyznacza histogram zdobytych punktow w kazdym pytaniu
     * @return mapa z numerami pytan (klucz) i liczba studentow, ktora na nie odpowiedziala poprawnie (wartosc)
     */
    public TreeMap<Integer, Integer> histogramOfQuestions(){
        TreeMap<Integer, Integer> histogram = new TreeMap<>();
        if (studentPoints.size() == 0) return null;

        for (int i = 0; i < questionPoints.size(); i++){
            histogram.put(i,questionPoints.get(i));
        }

        return histogram;
    }

    /**
     * Funkcja wyznacza histogram zdobytych punków przez studentow
     * @return mapa z liczba punktow (klucz) i liczba studentow, ktora je zdobyla (wartosc)
     */
    public TreeMap<Integer, Integer> histogramOfNumberOfPoints(){
        if (studentPoints.size() == 0) return null;

        TreeMap<Integer, Integer> histogram = new TreeMap<>();

        for (int i = 0; i < studentPoints.size(); i++){
            if (!histogram.containsKey(studentPoints.get(i))){
                histogram.put(studentPoints.get(i),1);
            }
            else {
                histogram.put(studentPoints.get(i), histogram.get(studentPoints.get(i)) + 1);
            }
        }

        return histogram;
    }

    /**
     * Funkcja liczy punkty wybranego studenta
     * @param student id w tabeli z wynikami
     * @return liczba zdobytych przez studenta punktow
     */
    private int countStudentPoints(int student){
        int points = 0;
        boolean allGood;
        for (int i = 0; i < toCount.keyAnswers.numberOfQuestions; i++){
            allGood = true;
            for (int j = 0; j < toCount.keyAnswers.numberOfAnswers; j++){
                if (toCount.studentTests.get(student).selectedAnswers[i][j] != toCount.keyAnswers.selectedAnswers[i][j]){
                    allGood = false;
                }
            }
            if (allGood) points++;
        }
        return points;
    }

    /**
     * Funkcja liczy punkty wszystkich studentow
     */
    private void countAllStudentPoints(){
        for (int i = 0; i < toCount.studentTests.size(); i++){
            studentPoints.add(i, countStudentPoints(i));
        }
    }

    /**
     * Funkcja liczy poprawne odpowiedzi na wskazane pytanie
     * @param question id w tabeli z wynikami
     * @return liczba poprawnych odpowiedzi na dane pytanie
     */
    private int countQuestionPoints(int question){
        int points = 0;
        boolean allGood;
        for (int i = 0; i < toCount.studentTests.size(); i++){
            allGood = true;
            for (int j = 0; j < toCount.keyAnswers.numberOfAnswers; j++){
                if (toCount.studentTests.get(i).selectedAnswers[question][j] != toCount.keyAnswers.selectedAnswers[question][j]){
                    allGood = false;
                }
            }
            if (allGood) points++;
        }
        return points;
    }

    /**
     * Funkcja liczy poprawne odpowiedzi na wszystkie pytania
     */
    private void countAllQuestionPoints(){
        for (int i = 0; i < toCount.keyAnswers.numberOfQuestions; i++){
            questionPoints.add(i, countQuestionPoints(i));
        }
    }

}
