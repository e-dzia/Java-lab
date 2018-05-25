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
        kolejnaRundaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.computerPlayer.cards.size() < 1 || game.humanPlayer.cards.size() < 1) return;
                if (!ready) return;
                try {
                    game.nextRound((int) cmbCardsPlayer.getSelectedItem(),
//                            game.computerPlayer.chooseCard());
                            Integer.parseInt(txtCardComputer.getText()));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                updateGUI();
                if (game.round % 2 != 0){
                    //najpierw komputer
                    txtCardComputer.setText(game.computerPlayer.chooseCard() + "");
                }
                else {
                    //najpierw człowiek
                    txtCardComputer.setText("");
                }
                cmbCardsPlayer.setEnabled(true);
                ready = false;
            }
        });
    
        ukryjButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });
    
        updateGUI();
        gotoweButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.round %2 == 0){
                    //najpierw człowiek
                    txtCardComputer.setText(game.computerPlayer.chooseCard() + "");
                }
                cmbCardsPlayer.setEnabled(false);
                ready = true;
            }
        });
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
