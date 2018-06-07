package game_logic;
import java.util.ArrayList;

public class HumanPlayer extends Player{
    
    HumanPlayer(int min, int max, int numberOfCards) {
        super(min, max, numberOfCards);
    }
    
    HumanPlayer(ArrayList<Integer> otherCards) {
        super(otherCards);
    }
    
    
}
