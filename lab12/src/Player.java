import java.util.ArrayList;
import java.util.Random;

public class Player {
    private static final int numberOfCards = 10;
    private int points = 0;
    ArrayList<Integer> cards = new ArrayList <>();
    
    Player(int min, int max){
        Random random = new Random();
        for (int i = 0; i < numberOfCards; i++){
            cards.add(random.nextInt(max - min + 1) + min);
        }
    }
    
    Player(ArrayList<Integer> otherCards){
        cards = (ArrayList <Integer>) otherCards.clone();
    }
    
    void playCard(Integer i) throws Exception {
        if (!cards.contains(i)){
            throw new Exception("Gracz nie posiada karty.");
        }
        cards.remove(i);
    }
    
    ArrayList<Integer> getAllCards(){
        return cards;
    }
    
    void addPoints(int points){
        this.points += points;
    }
    
    public int getPoints() {
        return points;
    }
}
