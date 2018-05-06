import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Scanner;
import java.util.TreeMap;

public class ShowStats {
    static Statistics stats;
    static String directory;
    static Scanner in = new Scanner(System.in);

    /**
     * Funkcja glowna programu
     */
    public static void main(String[] args) {
        chooseADirectory();
        stats = new Statistics(directory);
        menu();
        System.out.println("Czy chcesz sprawdzic statystyki dla innego folderu? T/N");
        String choice = in.nextLine();
        if (choice.equals("T") || choice.equals("t")) main(new String[]{});
    }

    /**
     * Funkcja jest menu do wyboru odpowiedniej statystyki do wyswietlenia
     */
    static void menu()
    {
        System.out.println("Prosze wybrac do wyswietlenia odpowiednia statystyke z podanych:\n" +
                            "1 - srednia liczba zdobytych punktow przez studenta\n" +
                            "2 - histogram liczby zdobytych punktow (ile osob zdobylo dana liczbe punktow)\n" +
                            "3 - histogram pytan (ile osob dobrze odpowiedzialo na dane pytanie)\n" +
                            "4 - wyjdz\n");

        String choice = in.nextLine();
        switch(choice){
            case "1":
                System.out.println("Srednia liczba zdobytych punktow przez studenta: " + stats.averageNumberOfPointsPerStudent());
                break;
            case "2":
                printHistogram(stats.histogramOfNumberOfPoints());
                break;
            case "3":
                printHistogram(stats.histogramOfQuestions());
                break;
            case "4":
                return;
            default:
                System.out.println("Bledny numer. Prosze wybrac ponownie.");
        }
        menu();
    }

    /**
     * Funkcja rysuje histogram od najmniejszej wartości przekazanej w parametrze do największej
     * @param histogram mapa klucz-wartość, która ma być wyświetlona w postaci histogramu
     */
    private static void printHistogram(TreeMap<Integer, Integer> histogram) {
        Integer key = histogram.firstKey();
        for (int i = key; i <= histogram.lastKey(); i++){
            System.out.print(i + " ");
            if (histogram.containsKey(i)){
                for (int j = 0; j < histogram.get(i); j++){
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }

    /**
     * Funkcja sluzy do wyboru przez uzytkownika folderu z danymi do analizy
     */
    static void chooseADirectory(){
        System.out.println("Prosze podac nazwe folderu z danymi lub sciezke do niego");
        directory = in.nextLine();
    }
}
