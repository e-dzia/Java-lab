
public class Game {
    static final int numberOfCards = 10;
    int round = 0;
    HumanPlayer humanPlayer;
    ComputerPlayer computerPlayer;
    
    Game(int min, int max){
        computerPlayer = new ComputerPlayer(min, max, numberOfCards);
        humanPlayer = new HumanPlayer(computerPlayer.cards);
    }
    
    void nextRound(int humanCard, int computerCard) throws Exception {
        if (computerPlayer.cards.size() < 1 || humanPlayer.cards.size() < 1) return;
        
        round++;
        
        computerPlayer.playCard(computerCard, humanCard);
        humanPlayer.playCard(humanCard, computerCard);
        
        if (computerCard > humanCard){
            computerPlayer.addPoints(2);
        }
        else if (computerCard == humanCard){
            computerPlayer.addPoints(1);
            humanPlayer.addPoints(1);
        }
        else {
            humanPlayer.addPoints(2);
        }
    }
    
}
