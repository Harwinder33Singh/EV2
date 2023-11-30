package EfforLogConsole;
import java.util.Timer;
import java.util.TimerTask;
import java.time.Duration;
import java.time.LocalTime;

public class LogTimer {
	private boolean timer_running;
	private LocalTime start_time;
	private LocalTime end_time;
	private Duration elapsed_time;
	
	
	public LogTimer() {
	}
	
	public void start() {
		if(!timer_running) {
			start_time = LocalTime.now();
		}
	}

	
	public void stop() {
		end_time = LocalTime.now();
		elapsed_time = Duration.between(start_time, end_time);
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
