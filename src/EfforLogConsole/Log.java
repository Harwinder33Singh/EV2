package EfforLogConsole;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;



/**
 * This is an entity class for the log object only meant to keep the data
 */
public class Log{
	private String project_type;
	private String life_cycle_step;
	private String effort_category;
	private String deliverable;
	private LocalDate date_created;
	private LocalTime start_time;
	private LocalTime stop_time;
	private Duration elapsed_time;
	
	/**
	 * @author Emmanuel Bastidas
	 */
	public Log() {
		this.project_type = null;
		this.life_cycle_step = null;
		this.effort_category = null;
		this.deliverable = null;
		this.date_created = null;
		this.start_time = null;
		this.stop_time = null;
		this.elapsed_time = null;
	}
	
	
	/**
	 * @author Emmanuel Bastidas
	 */
	public Log(String project_type, String life_cycle_step, String effort_catgeory, String deliverable, 
			LocalDate date_created, LocalTime start_time, LocalTime stop_time, Duration elapsed_time) {
		this.project_type = project_type;
		this.life_cycle_step = life_cycle_step;
		this.effort_category = effort_catgeory;
		this.deliverable = deliverable;
		this.date_created = date_created;
		this.start_time = start_time;
		this.stop_time = stop_time;
		this.elapsed_time = elapsed_time;
	}
	

	/**
	 * @author Emmanuel Bastidas
	 */
	public String get_project_type() {
		return this.project_type;
	}

	/**
	 * @author Emmanuel Bastidas
	 */
	public void set_project_type(String project_type) {
		this.project_type = project_type;
	}

	/**
	 * @author Emmanuel Bastidas
	 */
	public String get_life_cycle_step() {
		return this.life_cycle_step;
	}

	/**
	 * @author Emmanuel Bastidas
	 */
	public void set_life_cycle_step(String life_cycle_step) {
		this.life_cycle_step = life_cycle_step;
	}

	/**
	 * @author Emmanuel Bastidas
	 */
	public String get_effort_category() {
		return this.effort_category;
	}

	/**
	 * @author Emmanuel Bastidas
	 */
	public void set_effort_category(String effort_category) {
		this.effort_category = effort_category;
	}

	/**
	 * @author Emmanuel Bastidas
	 */
	public String get_deliverable() {
		return this.deliverable;
	}

	/**
	 * @author Emmanuel Bastidas
	 */
	public void set_deliverable(String deliverable) {
		this.deliverable = deliverable;
	}

	/**
	 * @author Emmanuel Bastidas
	 */
	public LocalDate get_date_created() {
		return this.date_created;
	}

	/**
	 * @author Emmanuel Bastidas
	 */
	public void set_date_created(LocalDate date_created) {
		this.date_created = date_created;
	}

	/**
	 * @author Emmanuel Bastidas
	 */
	public LocalTime get_start_time() {
		return this.start_time;
	}

	/**
	 * @author Emmanuel Bastidas
	 */
	public void set_start_time(LocalTime start_time) {
		this.start_time = start_time;
	}

	/**
	 * @author Emmanuel Bastidas
	 */
	public LocalTime get_stop_time() {
		return this.stop_time;
	}

	/**
	 * @author Emmanuel Bastidas
	 */
	public void set_stop_time(LocalTime stop_time) {
		this.stop_time = stop_time;
	}
	
	/**
	 * @author Emmanuel Bastidas
	 */
	public void set_elapsed_time() {
		this.elapsed_time = Duration.between(this.start_time, this.stop_time);
	}
	
	/**
	 * @author Emmanuel Bastidas
	 */
	public Duration get_elapsed_time() {
		return this.elapsed_time;
	}
	
	
	/**
	 * this exists because duration is easy to store the data but very unappealing when presenting the data
	 * This should be used when you are trying to present a log to a user
	 * @author Emmanuel Bastidas
	 * @return a formatted string
	 */
	public String formatted_display() {
		int elapsed_days = (int) this.elapsed_time.toDaysPart();
		int elapsed_hours = this.elapsed_time.toHoursPart();
		int elapsed_minutes = this.elapsed_time.toMinutesPart();
		int elapsed_seconds = this.elapsed_time.toSecondsPart();
		
		return "Start Date: " + this.date_created + ", Start Time: " + this.start_time + ", End Time: " + 
				this.stop_time +  ", Elapsed Time: Days " + elapsed_days + elapsed_hours + " Hours " +
				elapsed_minutes + " Minutes "   + elapsed_seconds + " Seconds ";
				
	}
	
	/**
	 * @author Emmanuel Bastidas
	 */
	public String toString() {
		return "Start Date: " + this.date_created + ", Start Time: " + this.start_time + ", End Time: " + this.stop_time +  ", Elapsed Time: " + this.elapsed_time +", Project Type: " + this.project_type +
				", Life cycle Step: " + this.life_cycle_step + ", effort category: " + this.effort_category + ", deliverable: "+ this.deliverable;
	}
}
