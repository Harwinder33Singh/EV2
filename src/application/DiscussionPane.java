package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import HarwinderPart.Card;
import HarwinderPart.CardHolder;
import HarwinderPart.PlanningPokerApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
public class DiscussionPane implements Initializable{
	
    private ArrayList<String> textList = new ArrayList<>();
    @FXML
    private TextArea textArea;
    @FXML
    private Button load_button;
    @FXML
    private Button review_hd;
    @FXML
    private Button new_round;
    @FXML
    private Button end_session;
    @FXML
    private TextArea discussion_area;
    @FXML
    private Label player_label;
    @FXML
    private Label role_label;
    @FXML
    private TextField discussion_field;
    @FXML 
    private Button enter_button;
    
    
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    private List<Card> cardList;
    private List<DiscussionThoughts> discussionList;
    
    private int currentIndex;
    
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//-----------------START----------------------//
        // Initialize your data
//        cardList = new ArrayList<Card>();
//        cardList.add(new Card(1, "Hi", 10));
//        cardList.add(new Card(2, "Hi", 5));
//        cardList.add(new Card(3, "Hi", 10));
//		cardList = (List<Card>) stage.getUserData();
		CardHolder holder = CardHolder.getInstance();
		System.out.println(holder.toString());
		cardList = holder.getCard();
		System.out.println(cardList);
        // Add Card objects to the cardList
        discussion_area.clear();
        discussion_area.setEditable(false);
        
        discussionList = new ArrayList<>();
        
        currentIndex = 0;
        updateUI();

		//-----------------END----------------------//
		textArea.clear();
		textArea.setEditable(false);
		textArea.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		
        for (Card cards : cardList) {
        	textList.add("Player " + cards.getPlayerNumber() + "(" + cards.getRole() + "): " + cards.getDifficulty());
        }
        
        for (int i = 0; i < textList.size(); i++) {
            textArea.appendText(textList.get(i) + "\n\n");
        }
	}
	
	@FXML
	public void enter_button(ActionEvent event) throws IOException {
		onNextPlayerButtonClick();
	}
	
    private void onNextPlayerButtonClick() {
        // Get input from the text field
        String input = discussion_field.getText();
        Card currCard = cardList.get(currentIndex);

        // Store input in the inputList
        discussionList.add(new DiscussionThoughts(currCard.getPlayerNumber(), currCard.getRole(), input));

        // Move to the next Card in the list
        currentIndex = (currentIndex + 1) % cardList.size();

        // Update the UI with the new Card
        updateUI();
    }
    @FXML
    private void updateUI() {
        Card currentCard = cardList.get(currentIndex);

        // Update labels with current Card information
        player_label.setText("Player: " + currentCard.getPlayerNumber());
        role_label.setText("Role: " + currentCard.getRole());

        // Clear the input text field
        discussion_field.clear();

        // Display the inputList in the VBox
        displayInputList();
    }

    private void displayInputList() {
        discussion_area.clear();
        for (int i = 0; i < discussionList.size(); i++) {
        	DiscussionThoughts input = discussionList.get(i);
        	discussion_area.appendText("Player " + input.getnumber() + " (" + input.getRole() + "):  " + input.getDiscussion() + "\n\n");
        }
    }

	@FXML
	public void switchToReviewHD(ActionEvent event) throws IOException {
	    root = FXMLLoader.load(getClass().getResource("/LogSearcher/LogSearcherView.fxml")); //add the name of the effortloger fxml
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	@FXML
	public void switchToNewRound(ActionEvent event) throws IOException {
		System.out.println("Hi");
		PlanningPokerApp planningPokerApp = new PlanningPokerApp();
        Scene planningPokerScene = planningPokerApp.createInitialScene();
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(planningPokerScene);
        currentStage.show();
        
        planningPokerApp.start(currentStage);
	}
	
	@FXML
	public void switchToEndSession(ActionEvent event) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("/PlanningPokerApplication/homepage.fxml"));
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}

//	public void setCardList(List<Card> playerCards) {
//		this.cardList = playerCards;
//		System.out.println("Hi");
//	}
	
} 
