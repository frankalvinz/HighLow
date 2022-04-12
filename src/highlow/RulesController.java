package highlow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Faisal, Franklyn, Akhil
 */
public class RulesController implements Initializable {

    //Loading FXML fields
    @FXML
    public TextArea rulesTxt;
    public Button mainMenuBtn;
    
    //Going back to the main menu screen when the button is clicked
    public void MainMenuBtnOnAction(ActionEvent event) throws IOException{
        //Closing current stage
        Stage stage = (Stage) mainMenuBtn.getScene().getWindow();
        stage.close();
        //Opening the menu screen
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/highlow/Menu.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage1 = new Stage();
        stage1.setTitle("Main Menu");
        stage1.setScene(new Scene(root));  
        stage1.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Setting the rules text when the page is loaded
        rulesTxt.setText("1. An initial card will be displayed on your screen. \n" +
                         "2. You must guess wether the card after it will be higher or lower than the current card. \n" +
                         "3. If guessed correctly, your score will increase! \n" +
                         "4. If the next card happens to be the same card, a point will be awarded to the player regardless of the guess! \n" +
                         "5. If you guess incorrect, your highest score will be tallied. \n" +
                         "6. The goal of the game is to get the highest score possible and try to beat that highscore on your next turn!");
    }    
    
}
