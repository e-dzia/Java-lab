import java.util.ArrayList;

public class HumanPlayer extends Player{
    
    HumanPlayer(int min, int max) {
        super(min, max);
    }
    
    HumanPlayer(ArrayList<Integer> otherCards) {
        super(otherCards);
    }
    
    
}
