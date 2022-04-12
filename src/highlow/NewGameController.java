package highlow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Faisal, Franklyn, Akhil
 */
public class NewGameController implements Initializable {
    
    //Initializing variables to start the game
    int highestScore = 0;
    int gameScore = 0;
    //Initiazling new card and pulling the card from images folder based on card value
    Card card = new Card();
    String currentCard = card.getCard();
    Image image = new Image(getClass().getResource("/highlow/images/" + currentCard +".png").toString());
    
    //Loading FXML fields
    @FXML
    public TextField highScore;
    public TextField score;
    public ImageView cardImg;
    public CheckBox high;
    public CheckBox low;
    public Button confirmBtn;
    
    //If high checkbox is selected, unselect low checkbox
    public void highChecked(ActionEvent event){
        if(high.isSelected()){
            low.setSelected(false);
        }
    }
    
    //If low checkbox is selected, unselect high checkbox
    public void lowChecked(ActionEvent event){
        if(low.isSelected()){
            high.setSelected(false);
        }
    }
    
    //Calculate the result when the confirm button is clicked
    public void confirmBtnOnAction(ActionEvent event) throws IOException{
        //Getting a value for a new card and displaying it once confirm button is clicked
        String newCard = card.getCard();
        Image newImage = new Image(getClass().getResource("/highlow/images/" + newCard +".png").toString());
        cardImg.setImage(newImage);
        //Converting card value to integer to be used for boolean checking in if statements
        int currentCardInt = Integer. parseInt(currentCard);
        int newCardInt = Integer. parseInt(newCard);
        
        //Checking if high checkbox is selected to calculate result
        if(high.isSelected()){
            //If the user guesses correctly, it will add to the users score and change the value of the current card to the new card
            if(currentCardInt <= newCardInt){
                currentCard = newCard;
                gameScore++;
                score.setText(String.valueOf(gameScore));
                high.setSelected(false);
                //If the user has beat the current highscore, the highest score will be updated with the current score
                if (highestScore < gameScore)
                    highestScore = gameScore; 
            }
            //If the user guesses wrong
            else{
                //Saving current score to the score file
                try {
                    FileWriter myWriter = new FileWriter("score.txt");
                    myWriter.write(String.valueOf(gameScore));
                    myWriter.close();
                    System.out.println("Successfully saved new score.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                //Saving highest score to the score file
                try {
                    FileWriter myWriter2 = new FileWriter("highscore.txt");
                    myWriter2.write(String.valueOf(highestScore));
                    myWriter2.close();
                    System.out.println("Successfully saved new highscore.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                //Closing current stage
                Stage stage = (Stage) confirmBtn.getScene().getWindow();
                stage.close();
                //Opening the retry/quit screen
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/highlow/Incorrect.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage1 = new Stage();
                stage1.setTitle("Incorrect, it was low!");
                stage1.setScene(new Scene(root));  
                stage1.show();
            }
        }
        
        //Checking if low checkbox is selected to calculate result
        if(low.isSelected()){
            //If the user guesses correctly, it will add to the users score and change the value of the current card to the new card
            if(currentCardInt >= newCardInt){
                currentCard = newCard;
                gameScore++;
                score.setText(String.valueOf(gameScore));
                low.setSelected(false);
                //If the user has beat the current highscore, the highest score will be updated with the current score
                if (highestScore < gameScore)
                    highestScore = gameScore; 
            }
            //If the user guesses wrong
            else{
                //Saving current score to the score file
                try {
                    FileWriter myWriter = new FileWriter("score.txt");
                    myWriter.write(String.valueOf(gameScore));
                    myWriter.close();
                    System.out.println("Successfully saved new score.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

                //Saving highest score to the score file
                try {
                    FileWriter myWriter = new FileWriter("highscore.txt");
                    myWriter.write(String.valueOf(highestScore));
                    myWriter.close();
                    System.out.println("Successfully saved new highscore.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                //Closing current stage
                Stage stage = (Stage) confirmBtn.getScene().getWindow();
                stage.close();
                //Opening the retry/quit screen
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/highlow/Incorrect.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage1 = new Stage();
                stage1.setTitle("Incorrect, it was high!");
                stage1.setScene(new Scene(root));  
                stage1.show();
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Setting the inital image when new game scene is opened
        cardImg.setImage(image);
        
        //Reading from the highscore file to display the current highest score
        try {
            File myObj = new File("highscore.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              highScore.setText(data);
              highestScore = Integer. parseInt(data);
            }
        myReader.close();
        System.out.println("Successfully read fom highscore.");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }    
    
}
