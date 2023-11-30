package EfforLogConsole;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import PlanningPokerApplication.Crypto;



public class LogManager {

	private String file_path;
	private Crypto crypto;


	
	/**
	 * This constructor is responsible for instantiating a Crypto object to do the reads and writes and storing the the key
	 * and file path. Any write and read will be with this file path
	 * @author Emmanuel Bastidas
	 * @param file_path this is where you want to read or write to
	 * @param content this is the content you want to write
	 * @param key this is your encryption key so you are able to encrypt and decrypt your work
	 */
	public LogManager(String file_path, String key) {
		this.file_path = file_path;
		this.crypto = new Crypto(key);		
	}
	

	/**
	 * converts log into csv form then writes to the file in that form
	 * Appends to file does not overwrite
	 * @author Emmanuel Bastidas
	 * @param log A log to write to a csv file
	 * @throws Exception 
	 */
	// TODO make excpetions more precise
	public void write(Log log) throws Exception {
		// must convert log to csv before writing to file
		String log_csv_format = String.join(",", 
    			log.get_project_type(),
            	log.get_life_cycle_step(),
            	log.get_effort_category(),
            	log.get_deliverable(),
            	log.get_date_created().toString(), 
            	log.get_start_time().toString(),
            	log.get_stop_time().toString(),
            	log.get_elapsed_time().toString()
    			);
		String encrypted_log_csv_format = this.crypto.encryptString(log_csv_format);
		
		// set true flag so it appends to file instead of overwriting it
        try (FileWriter writer = new FileWriter(this.file_path, true)) {
        	writer.append(encrypted_log_csv_format);
        	writer.append("\n"); // this makes sure that when we write again to append it will be on new line. 
        	writer.flush();      // this writes to the file
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	/**
	 * Reads from a csv file with Log objects parses them all into Log objects and returns the array list of them
	 * @author Emmanuel Bastidas
	 * @return Array List of all Log objects in csv
	 * @throws Exception 
	 */
	public ArrayList<Log> read() throws Exception {
        ArrayList<Log> logs = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(this.file_path))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	line = this.crypto.decryptString(line); // decrypt the content
                Log log = parse_log(line);
                logs.add(log);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    
		return logs;
	}
	
	/**
	 * Parses csv representation of Log to actual Log object
	 * @author Emmanuel Bastidas
	 * @param log_csv_format string csv version of log object
	 * @return the parsed Log object
	 */
	private Log parse_log(String log_csv_format) {
		// extract Log object values from csv string
	    String[] data = log_csv_format.split(",");
	    String projectType = data[0];
	    String lifeCycleStep = data[1];
	    String effortCategory = data[2];
	    String deliverable = data[3];
	    // These values are not plain strings so we need their built in parse method
	    LocalDate dateCreated = LocalDate.parse(data[4]);
	    LocalTime startTime = LocalTime.parse(data[5]);
	    LocalTime stopTime = LocalTime.parse(data[6]);
	    Duration elapsedTime = Duration.parse(data[7]);

	    return new Log(projectType, lifeCycleStep, effortCategory, deliverable, dateCreated, startTime, stopTime, elapsedTime);
	}

}

