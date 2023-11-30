package EfforLogConsole;
import java.time.Duration;
import java.time.LocalTime;


/**
 * @author Emmanuel Bastidas
 *  This class keeps track of the times associated with logging
 */
public class LogTimer {
	private boolean timer_running;
	private LocalTime start_time;
	private LocalTime end_time;
	private Duration elapsed_time;
	
	
	public LogTimer() {
		this.timer_running = false;
	}
	
	
	/**
	 * @author Emmanuel Bastidas
	 * Takes note of the time that we start logging
	 * will not do anything if the user is currently running a task
	 */
	public void start() {
		if(!this.timer_running) {
			this.start_time = LocalTime.now();
			this.timer_running = true;
		}
	}

	/**
	 * @author Emmanuel Bastidas
	 * Takes note of the time when we stop logging
	 * This will not do anything if the user is not running a task
	 */
	public void stop() {
		if(this.timer_running) {
			this.end_time = LocalTime.now();
			this.elapsed_time = Duration.between(start_time, end_time);
			this.timer_running = false;
		}
	}
	

	public LocalTime get_start_time() {
		return this.start_time;
	}
	
	
	public LocalTime get_end_time() {
		return this.end_time;
	}
	
	
	public Duration get_elapsed_time() {
		return this.elapsed_time;
	}
}
