package EfforLogConsole;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Log {
	private String project_type;
	private String life_cycle_step;
	private String effort_category;
	private String deliverable;
	private LocalDate date_created;
	private LocalTime start_time;
	private LocalTime stop_time;
	
	
	public Log() {
		this.project_type = null;
		this.life_cycle_step = null;
		this.effort_category = null;
		this.deliverable = null;
		this.date_created = null;
		this.start_time = null;
		this.stop_time = null;
	}
	
	
	public Log(String project_type, String life_cycle_step, String effort_catgeory, String deliverable, 
			LocalDate date_created, LocalTime start_time, LocalTime stop_time) {
		this.project_type = project_type;
		this.life_cycle_step = life_cycle_step;
		this.effort_category = effort_catgeory;
		this.deliverable = deliverable;
		this.date_created = date_created;
		this.start_time = start_time;
		this.stop_time = stop_time;
	}
	

	public String get_project_type() {
		return this.project_type;
	}

	public void set_project_type(String project_type) {
		this.project_type = project_type;
	}

	public String get_life_cycle_step() {
		return this.life_cycle_step;
	}

	public void set_life_cycle_step(String life_cycle_step) {
		this.life_cycle_step = life_cycle_step;
	}

	public String get_effort_category() {
		return this.effort_category;
	}

	public void set_effort_category(String effort_category) {
		this.effort_category = effort_category;
	}

	public String get_deliverable() {
		return this.deliverable;
	}

	public void set_deliverable(String deliverable) {
		this.deliverable = deliverable;
	}

	public LocalDate get_date_created() {
		return this.date_created;
	}

	public void set_date_created(LocalDate date_created) {
		this.date_created = date_created;
	}

	public LocalTime get_start_time() {
		return this.start_time;
	}

	public void set_start_time(LocalTime start_time) {
		this.start_time = start_time;
	}

	public LocalTime get_stop_time() {
		return this.stop_time;
	}

	public void set_stop_time(LocalTime stop_time) {
		this.stop_time = stop_time;
	}
	
	public String toString() {
		return "Start Date: " + this.date_created + ",  Start Time: " + this.start_time + ", End Time: " + this.stop_time + " Project Type: " + this.project_type +
				", Life cycle Step: " + this.life_cycle_step + ", effort category: " + this.effort_category + ", deliverable: dummy deliverable";
	}
}
