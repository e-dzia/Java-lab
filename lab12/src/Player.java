import java.util.ArrayList;
import java.util.Random;

public class Player {
    private int points = 0;
    ArrayList<Integer> cards = new ArrayList <>();
    ArrayList<Integer> opponentCards = new ArrayList <>();
    
    Player(int min, int max, int numberOfCards){
        Random random = new Random();
        for (int i = 0; i < numberOfCards; i++){
            cards.add(random.nextInt(max - min + 1) + min);
        }
        opponentCards = (ArrayList <Integer>) cards.clone();
    }
    
    Player(ArrayList<Integer> otherCards){
        cards = (ArrayList <Integer>) otherCards.clone();
        opponentCards = (ArrayList <Integer>) cards.clone();
    }
    
    void playCard(Integer myCard, Integer opponentCard) throws Exception {
        if (!cards.contains(myCard) || !opponentCards.contains(opponentCard)){
            throw new Exception("Gracz nie posiada karty.");
        }
        cards.remove(myCard);
        opponentCards.remove(opponentCard);
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
