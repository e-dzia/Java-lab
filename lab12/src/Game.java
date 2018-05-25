
public class Game {
    public int round = 0;
    HumanPlayer humanPlayer;
    ComputerPlayer computerPlayer;
    
    Game(int min, int max){
        computerPlayer = new ComputerPlayer(min, max);
        humanPlayer = new HumanPlayer(computerPlayer.cards);
    }
    
    void nextRound(int humanCard, int computerCard) throws Exception {
        if (computerPlayer.cards.size() < 1 || humanPlayer.cards.size() < 1) return;
        round++;
        computerPlayer.playCard(computerCard);
        humanPlayer.playCard(humanCard);
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
    
    //TODO: kolejność kładzenia kart - najpierw H, potem C w parzystych i na odwrót w nieparzystych
}
