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
    
}