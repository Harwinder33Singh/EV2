// Auther: Chandler Thomas
// Security Implementation

package PlanningPokerApplication;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputValidation {

//    @Override
//    public void start(Stage primaryStage) {
//        // Create TextFields for input and character limit
//        TextField inputTextField = new TextField();
//        TextField limitTextField = new TextField();
//        TextField enterDateField = new TextField();
//        
//        // Create Labels to describe the purpose of each TextField
//        Label inputLabel = new Label("Enter text within the first box and add a date in the second box:");
//        Label limitLabel = new Label("Character Limit:");
//        Label dateLabel = new Label("Enter a date with this format MM/DD/YYYY: ");
//        Label validationMessageLabel = new Label("You entered in a correct date!"); // Initialization of the validation message label
//        // Create a VBox to arrange the Labels and TextFields vertically
//        VBox vbox = new VBox(10);
//        vbox.getChildren().addAll(inputLabel, inputTextField, enterDateField, validationMessageLabel, limitLabel, limitTextField, dateLabel);
//
//        // Create a TextFormatter for the character limit
//        TextFormatter<Integer> limitTextFormatter = new TextFormatter<>(new IntegerStringConverter(), 0);
//        
//        // Handler to check the date when focus is lost from the date text field
//        enterDateField.focusedProperty().addListener((observable, oldValue, newValue) -> {
//            if (!newValue) { // Focus lost
//                checkValidDate(enterDateField, validationMessageLabel);
//            }
//        });
//        // Add a listener to update the character limit when the user types in the limitTextField
//        limitTextFormatter.valueProperty().addListener((obs, oldVal, newVal) -> {
//            int newLimit = newVal != null ? newVal : 0;
//            setCharacterLimit(inputTextField, newLimit);
//        });
//
//        // Apply the TextFormatter to the limitTextField
//        limitTextField.setTextFormatter(limitTextFormatter);
//
//        // Create a JavaFX scene
//        Scene scene = new Scene(vbox, 400, 300);
//
//        primaryStage.setTitle("Character Limit TextField Example");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

    // Method to set the character limit for the inputTextField
    public static void setCharacterLimit(TextField textField, int characterLimit) {
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            if (change.getControlNewText().length() > characterLimit) {
                return null;
            }
            return change;
        });

        textField.setTextFormatter(textFormatter);
        textField.setPromptText("Max " + characterLimit + " characters");
    }
    
    public static void checkValidDate(TextField enterDateField, Label dateLabel) {
        String dateText = enterDateField.getText();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        try {
            // Try to parse the date
            LocalDate date = LocalDate.parse(dateText, dateFormatter);
            // If the date is successfully parsed, set a success message
            dateLabel.setText("Valid date: " + date);
        } catch (DateTimeParseException e) {
            // If parsing fails, set an error message
            dateLabel.setText("Invalid date format. Please use Month/Day/Year.");
        }    
        enterDateField.setPromptText("Month/Day/Year");
    }
    
//    public static void main(String[] args) {
//        launch(args);
//    }
}