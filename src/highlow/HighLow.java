package highlow;

import java.io.IOException;
import java.util.Random;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Faisal, Franklyn, Akhil
 */
public class HighLow extends Application{

    @Override
    public void start(Stage stage) throws IOException {
        //Opening the menu scene when the program is loaded
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
}

//Card class to randomize and pick card values from a saved array
class Card{

    public String[] cards = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
    
    public Card(){
    }
    
    //Method to randomize card value from array and return it as a string
    public String getCard(){
        int random = new Random().nextInt(cards.length);
        return cards[random];
    }
}