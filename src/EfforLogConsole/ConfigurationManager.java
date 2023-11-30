package EfforLogConsole;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ConfigurationManager {
	private String project_name;
	private String life_cycle_stepString;
	private String effort_category;
	
	public ConfigurationManager() {
		
	}
	
	/**
	 * 
	 * Create a new project 
	 * @author Emmanuel Bastidas
	 * @param project_name the name of the project you want to create
	 */
	public void create_project(String project_name) {
        try (FileWriter writer = new FileWriter("src/configurationFiles/projectType.txt", true)) {
        	writer.append(project_name);
        	writer.append("\n"); // this makes sure that when we write again to append it will be on new line. 
        	writer.flush();      // this writes to the file
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public ArrayList<String> load_projects(){
        ArrayList<String> projects = new ArrayList<>();
        
		try (BufferedReader reader = new BufferedReader(new FileReader("src/configurationFiles/projectType.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	projects.add(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
		
		return projects;
	}
	
	/**
	 * TODO in order to make this more modular I need to reformat how other parts of the code interact with projects
	 * This will mean reformatting the development life cycle file for now we will only use the values that exist
	 * 
	 * For now however this returns all life cycle steps for the current project
	 * @param project
	 * @return
	 */
	public ArrayList<String> load_life_cycles(String project){
		String file_path;
		ArrayList<String> life_cycles = new ArrayList<>();
		
		if(project.equals("Business Project")) {
			file_path = "src/configurationFiles/businessProjectLifeCycleSteps.txt";
		}
		
		else {
			file_path = "src/configurationFiles/developmentProjectLifeCycleSteps.txt";
		}
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file_path))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	life_cycles.add(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
		
		return life_cycles;
	}
	
	
	/**
	 * Load all effort categories
	 * @return array_list of effort categories
	 * @author Emmanuel Bastidas
	 */
	public ArrayList<String> load_effort_category(){
		ArrayList<String> effort_categories = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader("src/configurationFiles/effortCategory.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	effort_categories.add(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
		
		return effort_categories;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> load_deliverable(){
		ArrayList<String> deliverables = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader("src/configurationFiles/deliverables.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	deliverables.add(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
		
		return deliverables;
	}
}
