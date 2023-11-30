package HarwinderPart;
import java.util.ArrayList;
import java.util.List;

import application.DiscussionPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PlanningPokerApp extends Application {

    private Stage primaryStage;
    private int totalPlayers;
    private int currentPlayer = 1;
    private List<Card> playerCards;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Planning Poker");
        createInitialScene();
    }

    public Scene createInitialScene() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        TextField rangeInput = new TextField();
        rangeInput.setPromptText("Enter range");

        TextField playersInput = new TextField();
        playersInput.setPromptText("Enter number of players");

        Button nextButton = new Button("Next");
        nextButton.setOnAction(e -> {
            try {
                int range = Integer.parseInt(rangeInput.getText());
                totalPlayers = Integer.parseInt(playersInput.getText());
                playerCards = new ArrayList<>();
                createPlayerScene(range);
            } catch (NumberFormatException ex) {
                showAlert("Invalid input. Please enter valid numbers.");
            }
        });

        grid.add(new Label("Range:"), 0, 0);
        grid.add(rangeInput, 1, 0);
        grid.add(new Label("Number of Players:"), 0, 1);
        grid.add(playersInput, 1, 1);
        grid.add(nextButton, 1, 2);

        Scene scene = new Scene(grid, 400, 400);
        return scene;
    }

    private void createPlayerScene(int range) {
        if (currentPlayer <= totalPlayers) {
            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10, 10, 10, 10));
            grid.setVgap(8);
            grid.setHgap(10);

           
            Label playerIdLabel = new Label("Player ID: " + currentPlayer);

            
            ComboBox<String> roleComboBox = new ComboBox<>();
            roleComboBox.getItems().addAll("Developer", "Tester", "Scrum Master", "Product Owner");
            roleComboBox.setPromptText("Select role");

            
            ComboBox<Integer> difficultyComboBox = new ComboBox<>();
            for (int i = 1; i <= range; i++) {
                difficultyComboBox.getItems().add(i);
            }
            difficultyComboBox.setPromptText("Select difficulty");

            Button nextButton = new Button("Next");
            nextButton.setOnAction(e -> {
                String selectedRole = roleComboBox.getValue();
                Integer selectedDifficulty = difficultyComboBox.getValue();

                if (selectedRole != null && selectedDifficulty != null) {
                    Card card = new Card(currentPlayer, selectedRole, selectedDifficulty);
                    playerCards.add(card);

                    currentPlayer++;
                    createPlayerScene(range);
                } else {
                    showAlert("Please select role and difficulty for the player.");
                }
            });

            grid.add(playerIdLabel, 0, 0);
            grid.add(new Label("Role:"), 0, 1);
            grid.add(roleComboBox, 1, 1);
            grid.add(new Label("Difficulty:"), 0, 2);
            grid.add(difficultyComboBox, 1, 2);
            grid.add(nextButton, 1, 3);

            Scene scene = new Scene(grid, 300, 200);
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
        	// Call Minh's Scene
            start2(primaryStage);
//            showSummary();
        }
    }
    
    private void showSummary() {
        for (Card card : playerCards) {
            System.out.println("Player " + card.getPlayerNumber() + ": Role - " + card.getRole() +
                    ", Difficulty - " + card.getDifficulty());
        }
        showAlert("All players are finished. Proceed to the next step.");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void start2(Stage primaryStage) {
		try {
			CardHolder holder = CardHolder.getInstance();
			System.out.println(holder.toString());
			holder.setCard(playerCards);
			Parent root = FXMLLoader.load(getClass().getResource("/application/Sample.fxml"));
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
