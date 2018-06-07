package window;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import game_logic.Game;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private boolean computerCardsHidden = false;
    private boolean ready = false;
    private Game game = new Game(0, 10);
    
    @FXML private ComboBox cmbStrategy;
    @FXML private ScrollPane scrollCardsComputer;
    @FXML private Text txtCardComputer;
    @FXML private Text txtInfo;
    @FXML private Text txtPointsComputer;
    @FXML private Text txtPointsPlayer;
    @FXML private Text nowRound;
    @FXML private ListView lstCardsComputer;
    @FXML private ListView lstCardsPlayer;
    @FXML private CheckBox checkHide;
    @FXML private ImageView boximage;
    
    String cmbCardsChosen;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbStrategy.setItems(FXCollections.observableArrayList(findScript()));
        Image image = new Image("http://www.milefoot.com/math/discrete/counting/images/cards.png");
        this.boximage.setImage(image);
        updateGUI();
    }
    
    @FXML protected void handleBtnGotowe(ActionEvent event) {
        txtInfo.setText("");
        txtInfo.setFill(Color.BLACK);
        if (game.computerPlayer.cards.size() < 1 || game.humanPlayer.cards.size() < 1) return;
        if (ready) return;
        if (game.round %2 == 0){
            //najpierw człowiek
            if (lstCardsPlayer.getSelectionModel().getSelectedItem() == null) {
                txtInfo.setFill(Color.RED);
                txtInfo.setText("Nie wybrano karty");
                return;
            }
            if (cmbStrategy.getSelectionModel().getSelectedItem() == null) {
                txtInfo.setFill(Color.RED);
                txtInfo.setText("Nie wybrano strategii");
                return;
            }
            int chosenCard = game.computerPlayer.chooseCard((int) lstCardsPlayer.getSelectionModel().getSelectedItem(),
                    (String) cmbStrategy.getSelectionModel().getSelectedItem());
            txtCardComputer.setText(chosenCard + "");
        }
        lstCardsPlayer.setDisable(true);
        ready = true;
    }
    
    @FXML protected void handleBtnUkryj(ActionEvent event) {
        if (!checkHide.isSelected()){
            computerCardsHidden = true;
            scrollCardsComputer.setVisible(true);
           // ukryjButton.setText("Ukryj");
        }
        else {
            computerCardsHidden = false;
            scrollCardsComputer.setVisible(false);
          //  ukryjButton.setText("Pokaz");
        }
    }
    
    @FXML protected void handleBtnDalej(ActionEvent event) {
        if (game.computerPlayer.cards.size() < 1 || game.humanPlayer.cards.size() < 1) return;
        if (!ready) return;
        if (game.round == game.numberOfCards-1){
            if (game.humanPlayer.getPoints() > game.computerPlayer.getPoints()){
                txtInfo.setFill(Color.BLACK);
                txtInfo.setText("Wygrales " + game.humanPlayer.getPoints() + ":" + game.computerPlayer.getPoints());
            } else if (game.humanPlayer.getPoints() == game.computerPlayer.getPoints()){
                txtInfo.setFill(Color.BLACK);
                txtInfo.setText("Remis "  + game.humanPlayer.getPoints() + ":" + game.computerPlayer.getPoints());
            } else {
                txtInfo.setFill(Color.BLACK);
                txtInfo.setText("Przegrales " + game.humanPlayer.getPoints() + ":" + game.computerPlayer.getPoints());
            }
            game = new Game(0, 10);
            updateGUI();
            lstCardsPlayer.setDisable(false);
            txtCardComputer.setText("");
            ready = false;
            return;
        }
        txtInfo.setText("Gra rozpoczeta");
        try {
            if (lstCardsPlayer.getSelectionModel().getSelectedItem() == null) {
                txtInfo.setFill(Color.RED);
                txtInfo.setText("Nie wybrano karty");
                return;
            }
            game.nextRound((int) lstCardsPlayer.getSelectionModel().getSelectedItem(),
                    Integer.parseInt(txtCardComputer.getText()));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (game.round % 2 != 0){
            //najpierw komputer
            txtCardComputer.setText(game.computerPlayer.chooseCard(-1, (String) cmbStrategy.getSelectionModel().getSelectedItem()) + "");
        }
        else {
            //najpierw człowiek
            txtCardComputer.setText("");
        }
        lstCardsPlayer.setDisable(false);
        ready = false;
        updateGUI();
    }
    
    @FXML void closeApp(){
        Platform.exit();
    }
    
    @FXML void aboutAuthor(){
        Label secondLabel = new Label("This app was written by Edyta Rogula");
    
        StackPane newLayout = new StackPane();
        newLayout.getChildren().add(secondLabel);
    
        Scene secondScene = new Scene(newLayout, 230, 100);
    
        Stage newWindow = new Stage();
        newWindow.setTitle("About Author");
        newWindow.setScene(secondScene);
        newWindow.show();
    }
    
    private void updateGUI() {
        game.humanPlayer.cards.sort((o1, o2) -> o1 - o2);
        game.computerPlayer.cards.sort((o1, o2) -> o1 - o2);
        lstCardsComputer.setItems(FXCollections.observableArrayList(game.computerPlayer.cards));
        lstCardsPlayer.setItems(FXCollections.observableArrayList(game.humanPlayer.cards));
        txtPointsPlayer.setText(game.humanPlayer.getPoints() + "");
        txtPointsComputer.setText(game.computerPlayer.getPoints() + "");
        nowRound.setText("Aktualna runda: " + game.round);
    }
    
    private ArrayList<String> findScript(){
        File folder = new File("src/game_logic/scripts/");
        File[] listOfFiles = folder.listFiles();
        
        String name = "";
        ArrayList<String> scripts = new ArrayList <>();
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".js")) {
                scripts.add(file.getName());
            }
        }
        return scripts;
    }
}
