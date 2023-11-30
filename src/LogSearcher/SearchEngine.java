package LogSearcher;

import java.util.ArrayList;

import EfforLogConsole.Log;

public class SearchEngine {
	private ArrayList<Log> data_set;
	private String search_param_project;
	private String search_param_life;
	private String search_param_effort;
	private String search_param_deliverable;


	//TODO consider putting all params in a set and just checking the set.
	//TODO find out how to filter by less search options
	/**
	 * @author Emmanuel Bastidas
	 * @param data_set
	 * @param search_param_project
	 * @param search_param_life
	 * @param search_param_effort
	 * @param search_param_deliverable
	 */
	public SearchEngine(ArrayList<Log> data_set, String search_param_project, String search_param_life, String search_param_effort, String search_param_deliverable) {
		this.data_set = data_set;
		this.search_param_project = search_param_project;
		this.search_param_life = search_param_life;
		this.search_param_effort = search_param_effort;
		this.search_param_deliverable = search_param_deliverable;
	}
	
	/**
	 * Returns an ArrayList with Logs that meet search result parameters
	 * @author Emmanuel Bastidas
	 * @return ArrayList of logs that meet search results
	 */
	public ArrayList<Log> search(){
		ArrayList<Log> found_logs = new ArrayList<>();
		
		for(Log log: this.data_set) {
			if(log.get_project_type().strip().equals(search_param_project.strip()) && log.get_life_cycle_step().strip().equals(search_param_life.strip()) && log.get_effort_category().strip().equals(search_param_effort.strip()) && log.get_deliverable().strip().equals(search_param_deliverable.strip())) {
				found_logs.add(log);
			}
		}
		return found_logs;
	}
}
