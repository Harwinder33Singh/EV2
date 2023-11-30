package EfforLogConsole;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
	private Button stop_log_button;

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
	private LogManager manager;
	private ConfigurationManager config_manager;
	
	private ArrayList<String> projects;
	private ArrayList<String> life_cycles;
	private ArrayList<String> effort_categories;
	private ArrayList<String> deliverables;
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	/**
	 * This is essentially a constructor for the controller
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// load all configurations
		this.config_manager = new ConfigurationManager();
		this.projects = config_manager.load_projects();
		this.effort_categories  = config_manager.load_effort_category();
		this.deliverables = config_manager.load_deliverable();
		
		// set drop menus to loaded values
		// we can't load life cycle since it is dependent on the project 
		this.project_type_dropdown_menu.setItems(FXCollections.observableArrayList(projects));
		this.effort_category_dropdown_menu.setItems(FXCollections.observableArrayList(effort_categories));
		this.deliverable_drop_down_menu.setItems(FXCollections.observableArrayList(deliverables));
		
		// set initial state
		this.project_type_dropdown_menu.setValue(this.projects.get(0));
		populate_life_cycle_step_dropdown_menu(null); // this must be set in order to set correct life cycle step
		this.life_cycle_step_dropdown_menu.setValue(this.life_cycles.get(0));
		this.effort_category_dropdown_menu.setValue(this.effort_categories.get(0));
		this.deliverable_drop_down_menu.setValue(this.deliverables.get(0));
	}
	
	/**
	 * Create a new log and start keeping track of the time
	 * @param event
	 */
	@FXML
	public void start_log(ActionEvent event) {
		this.log = new Log();
		logTimer = new LogTimer();
		logTimer.start();
		
		// build the new log
		this.log.set_date_created(LocalDate.now());
		this.log.set_start_time(logTimer.get_start_time());
		this.log.set_deliverable(deliverable_drop_down_menu.getValue());
		this.log.set_project_type(project_type_dropdown_menu.getValue());
		this.log.set_life_cycle_step(life_cycle_step_dropdown_menu.getValue());
		this.log.set_effort_category(effort_category_dropdown_menu.getValue());
		

		clock_state_label.setText("Clock is running");
	}
	
	/**
	 * Stop the logging and store the log
	 * @param event
	 */
	@FXML
	public void stop_log(ActionEvent event) {
		// stop LogTimer and set times to Log
		logTimer.stop();
		log.set_stop_time(logTimer.get_end_time());
		log.set_elapsed_time();
		clock_state_label.setText("Clock is stopped");
		
		// TODO maybe move this to initialize?
		manager = new LogManager("src/EfforLogConsole/Logs.txt", "YourSecretKey123");
		
		try {
			manager.write(log);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void switchToHome(ActionEvent event) throws IOException {
	    root = FXMLLoader.load(getClass().getResource("/PlanningPokerApplication/homepage.fxml")); //add the name of the effortloger fxml
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	@FXML
	public void populate_life_cycle_step_dropdown_menu(ActionEvent event) {		
		this.life_cycles = this.config_manager.load_life_cycles(project_type_dropdown_menu.getValue());	
		this.life_cycle_step_dropdown_menu.setItems(FXCollections.observableArrayList(this.life_cycles));
	}
	
	
	
}
