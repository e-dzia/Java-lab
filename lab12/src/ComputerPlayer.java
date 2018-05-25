import java.util.ArrayList;

public class ComputerPlayer extends Player{
    
    ComputerPlayer(int min, int max) {
        super(min, max);
    }
    
    ComputerPlayer(ArrayList<Integer> otherCards) {
        super(otherCards);
    }
    
    int chooseCard(){
        if (cards.size() < 1) return -1;
        return cards.get(0);
    }
}
