package LogSearcher;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import EfforLogConsole.Log;
import EfforLogConsole.LogManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class LogSearcherController implements Initializable{
	@FXML
	private ComboBox<String> project_type_dropdown_menu;
	@FXML
	private ComboBox<String> life_cycle_step_dropdown_menu;
	@FXML 
	private ComboBox<String> effort_category_dropdown_menu;
	@FXML
	private ComboBox<String> deliverable_drop_down_menu;
	@FXML
	private ListView<String> my_list_view;
	@FXML
	private Button back_button;
	
	private ObservableList<String> project_type_options;
	private ObservableList<String> life_cycle_step_options;
	private ObservableList<String> effort_category_options;
	private ObservableList<String> deliverable_options;
	private LogManager manager;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	/**
	 * Initialize combo boxes with values and automatically set a default stage once page is initialized
	 * @author Emmanuel Bastidas
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Populate combo boxes with option
		populate_dropdown_menu(effort_category_dropdown_menu, "src/configurationFiles/effortCategory.txt"); // populate the project type drop down menu
		populate_dropdown_menu(project_type_dropdown_menu, "src/configurationFiles/projectType.txt"); // populate effort category drop down menu
		populate_dropdown_menu(deliverable_drop_down_menu, "src/configurationFiles/deliverables.txt");
		
		// store current options of combo box
		project_type_options = project_type_dropdown_menu.getItems(); // get all the values in the drop down
		life_cycle_step_options = life_cycle_step_dropdown_menu.getItems();
		effort_category_options = effort_category_dropdown_menu.getItems();
		deliverable_options = deliverable_drop_down_menu.getItems();
		
		// set the combo boxes to a default state without user interaction
		project_type_dropdown_menu.setValue(project_type_options.get(0)); // set a default value to this drop down
		populate_life_cycle_step_dropdown_menu(null);					  // populate life cycle based off combo box	
		life_cycle_step_dropdown_menu.setValue(life_cycle_step_options.get(0)); // set a default value for life cycle
		effort_category_dropdown_menu.setValue(effort_category_options.get(0)); // set a default value for effort category
		deliverable_drop_down_menu.setValue(deliverable_options.get(0));
		
		// Create a new Log manager to facilitate interaction between logs and the rest of the system
		manager = new LogManager("src/EfforLogConsole/Logs.txt", "YourSecretKey123");
	}
	
	
	/**
	 * When the search button in the view is pressed this updates the view with a list view
	 * Of historical logs that match the search parameters. If none exist then list view will be empty
	 * @author Emmanuel Bastidas
	 * @param event is the event when the search button in our view is pressed
	 */
	@FXML
	public void search_logs(ActionEvent event) {
		SearchEngine searcher;
		
		try {
			// pass all the logs to be searched along with search parameters
			searcher = new SearchEngine(manager.read(), project_type_dropdown_menu.getValue(), life_cycle_step_dropdown_menu.getValue(), effort_category_dropdown_menu.getValue(), deliverable_drop_down_menu.getValue());
			ArrayList<Log> found_items = searcher.search();
			ArrayList<String> formatted_found_log = new ArrayList<>();
			
			// format our logs to look nicer
			for(Log found_log : found_items) {
				formatted_found_log.add(found_log.formatted_display());
			}
			
			// passing a null value will give exception try to not let happen
			if(found_items != null) {
				// display all items that matched the search parameters
				my_list_view.setItems(FXCollections.observableArrayList(formatted_found_log));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This function is called whenever we interact with the life_cycle_step_dropdown_menu populates the 
	 * combo box life_cycle_step_dropdown_menu based off the value selected by the project_type_dropdown_menu
	 * @author Emmanuel Bastidas
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
			life_cycle_step_options = life_cycle_step_dropdown_menu.getItems(); // update the model of life cycle options available
			life_cycle_step_dropdown_menu.setValue(life_cycle_step_options.get(0)); // set appropriate default value to drop down
		}
	}	
	
	
	/**
	 * 
	 * @param event
	 * @throws IOException
	 * @author Minh??
	 */
	@FXML
	public void go_back_to_planning_poker(ActionEvent event) throws IOException {
	    root = FXMLLoader.load(getClass().getResource("/application/Sample.fxml")); //add the name of the effortloger fxml
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	
	/**
	 * This populates the options of a combo box based off values in a configuration file
	 * @author Emmanuel Bastidas
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
	 * @author Emmanuel Bastidas
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


