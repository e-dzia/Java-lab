import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameWindow extends JFrame {
    private JComboBox cmbCardsPlayer;
    private JTextField txtCardComputer;
    private JList lstCardsComputer;
    private JList lstCardsPlayer;
    private JButton kolejnaRundaButton;
    private JButton ukryjButton;
    private JTextField txtPointsPlayer;
    private JTextField txtPointsComputer;
    private JPanel panel1;
    private JLabel nowRound;
    private JScrollPane scrollCardsComputer;
    private JButton gotoweButton;
    
    private boolean computerCardsHidden = false;
    private boolean ready = false;
    private Game game = new Game(0, 10);
    
    public GameWindow() {
        createGUI();
        txtCardComputer.setEditable(false);
        txtPointsComputer.setEditable(false);
        txtPointsPlayer.setEditable(false);
        ukryjButton.addActionListener(e -> {
            if (!computerCardsHidden){
                computerCardsHidden = true;
                scrollCardsComputer.setVisible(true);
                ukryjButton.setText("Ukryj");
            }
            else {
                computerCardsHidden = false;
                scrollCardsComputer.setVisible(false);
                ukryjButton.setText("Pokaz");
            }
        });
    
        kolejnaRundaButton.addActionListener(e -> {
            if (game.computerPlayer.cards.size() < 1 || game.humanPlayer.cards.size() < 1) return;
            if (!ready) return;
            if (game.round == game.numberOfCards-1) {
                if (game.humanPlayer.getPoints() > game.computerPlayer.getPoints()){
                    JOptionPane.showMessageDialog(null, "Wygrales!", "HURRA!", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (game.humanPlayer.getPoints() == game.computerPlayer.getPoints()) {
                    JOptionPane.showMessageDialog(null, "Remis", "REMIS", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Przegrales", "SPROBUJ JESZCZE RAZ", JOptionPane.INFORMATION_MESSAGE);
                }
                game = new Game(0, 10);
                updateGUI();
                cmbCardsPlayer.setEnabled(true);
                txtCardComputer.setText("");
                ready = false;
                return;
            }
            try {
                game.nextRound((int) cmbCardsPlayer.getSelectedItem(),
                        Integer.parseInt(txtCardComputer.getText()));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (game.round % 2 != 0){
                //najpierw komputer
                txtCardComputer.setText(game.computerPlayer.chooseCard(-1) + "");
            }
            else {
                //najpierw człowiek
                txtCardComputer.setText("");
            }
            cmbCardsPlayer.setEnabled(true);
            ready = false;
            updateGUI();
        });
        
        gotoweButton.addActionListener(e -> {
            if (game.computerPlayer.cards.size() < 1 || game.humanPlayer.cards.size() < 1) return;
            if (ready) return;
            if (game.round %2 == 0){
                //najpierw człowiek
                txtCardComputer.setText(game.computerPlayer.chooseCard((int) cmbCardsPlayer.getSelectedItem()) + "");
            }
            cmbCardsPlayer.setEnabled(false);
            ready = true;
        });
        
        updateGUI();
    }
    
    private void updateGUI(){
        lstCardsComputer.setListData(game.computerPlayer.cards.toArray());
        lstCardsPlayer.setListData(game.humanPlayer.cards.toArray());
        DefaultComboBoxModel model = new DefaultComboBoxModel(game.humanPlayer.cards.toArray());
        cmbCardsPlayer.setModel(model);
        txtPointsPlayer.setText(game.humanPlayer.getPoints() + "");
        txtPointsComputer.setText(game.computerPlayer.getPoints() + "");
        nowRound.setText("Aktualna runda: " + game.round);
    }
    
    private void createGUI(){
        this.setTitle("GRA");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.setVisible(true);
        this.pack();
    }
}
