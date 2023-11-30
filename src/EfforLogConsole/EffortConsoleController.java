package EfforLogConsole;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EffortConsoleController implements Initializable{
	@FXML
	private Button start_log_button;
	@FXML
	private ComboBox<String> project_type_dropdown_menu;
	@FXML
	private ComboBox<String> life_cycle_step_dropdown_menu;
	@FXML 
	private ComboBox<String> effort_category_dropdown_menu;
	@FXML
	private ComboBox<String> deliverable_drop_down_menu;
	@FXML
	private Label clock_state_label;
	private LogTimer logTimer;
	private Log log;
	private LogWriter writer;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	/**
	 * This is essentially a constructor for the controller
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		populate_dropdown_menu(effort_category_dropdown_menu, "src/configurationFiles/effortCategory.txt"); // populate the project type drop down menu
		populate_dropdown_menu(project_type_dropdown_menu, "src/configurationFiles/projectType.txt"); // populate effort category drop down menu
	}
	
	/**
	 * Create a new log and start keeping track of the time
	 * @param event
	 */
	@FXML
	public void start_log(ActionEvent event) {
		log = new Log();
		logTimer = new LogTimer();
		logTimer.start();
		
		// build the new log
		log.set_date_created(LocalDate.now());
		log.set_start_time(logTimer.get_start_time());
		log.set_deliverable("Dummy Deliverable");
		log.set_project_type(project_type_dropdown_menu.getValue());
		log.set_life_cycle_step(life_cycle_step_dropdown_menu.getValue());
		log.set_effort_category(effort_category_dropdown_menu.getValue());
		
		System.out.println(log.get_date_created());
		System.out.println(log.get_start_time());
		
		clock_state_label.setText("Clock is running");
	}
	
	/**
	 * Stop the logging and store the log
	 * @param event
	 */
	@FXML
	public void stop_log(ActionEvent event) {
		logTimer.stop();
		log.set_stop_time(logTimer.get_end_time());
		System.out.println(log.get_stop_time());
		clock_state_label.setText("Clock is stopped");
		
		LogWriter writer = new LogWriter("src/EfforLogConsole/logs.txt", log.toString(), "YourSecretKey123");
		//writer.write();
		writer.write_encrypted();
		
		System.out.println(writer.read_decrypted());
	}
	
	@FXML
	public void switchToHome(ActionEvent event) throws IOException {
	    root = FXMLLoader.load(getClass().getResource("/PlanningPokerApplication/homepage.fxml")); //add the name of the effortloger fxml
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	/**
	 * This function is called whenever we interact with the life_cycle_step_dropdown_menu populates the 
	 * combo box life_cycle_step_dropdown_menu based off the value selected by the project_type_dropdown_menu
	 * @param event 
	 */
	@FXML
	public void populate_life_cycle_step_dropdown_menu(ActionEvent event) {
		if(project_type_dropdown_menu.getValue() != null) {
			String project_type = project_type_dropdown_menu.getValue().strip();
			
			if (project_type.equals("Business Project")) {
				populate_dropdown_menu(life_cycle_step_dropdown_menu, "src/configurationFiles/businessProjectLifeCycleSteps.txt");
			}
			
			else if(project_type.equals("Development Project")) {
				populate_dropdown_menu(life_cycle_step_dropdown_menu, "src/configurationFiles/developmentProjectLifeCycleSteps.txt");
			}
		}
	}	
	
	
	/**
	 * This populates the options of a combo box based off values in a configuration file
	 * @param dropdown_menu is the combo box we want to populate the options for
	 * @param file_directory this is the location of our configuration file holding the values we want for
	 */
	private void populate_dropdown_menu(ComboBox<String> dropdown_menu, String file_directory) {
		ArrayList<String> drop_down_menu_items = get_dropdown_values(dropdown_menu, file_directory); // get the values from the file
		dropdown_menu.setItems(FXCollections.observableArrayList(drop_down_menu_items)); // set the values of the drop down combo box
    }
	
	
	/**
	 * This is a helper function for the populate_dropdown_menu function
	 * This reads the values from a file line by line
	 * @param dropdown_menu
	 * @param file_directory
	 * @return
	 */
	private ArrayList<String> get_dropdown_values(ComboBox<String> dropdown_menu, String file_directory){
        ArrayList<String> dropdown_values = new ArrayList<>();
      
        try (BufferedReader reader = new BufferedReader(new FileReader(file_directory))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	dropdown_values.add(line.strip());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        return dropdown_values;
	}
	
	
	
}
