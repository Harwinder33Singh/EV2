package EfforLogConsole;
/**
Deprecated use Log Manager class
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.WriteAbortedException;
import java.util.ArrayList;

import PlanningPokerApplication.Crypto;
/**
Deprecated use Log Manager class
*/

public class LogWriter {
	private String encrypted_content;
	private String content;
	private String file_path;
	private String key;
	private Crypto crypto;
	
	public  LogWriter(String file_path, String content, String key) {
		this.content = content;
		this.file_path = file_path;
		this.key = key;
		crypto = new Crypto(this.key);
		
		try {
			encrypted_content = crypto.encryptString(content);
		} catch (Exception e) {
			System.out.println("couldnt ecrypt string");
			e.printStackTrace();
		}
	}
	
	public void write() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_path, true))) {
			writer.write(content);
			writer.newLine();
	    } 
		catch (IOException e) {
	            e.printStackTrace();
	    }
	}
	
	public void write_encrypted() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_path, true))) {
			writer.write(encrypted_content);
			writer.newLine();
	    } 
		catch (IOException e) {
	            e.printStackTrace();
	    }
	}
	
	public ArrayList<String> read_decrypted() {
		ArrayList<String> file_contents = new ArrayList<>(); 
		
        try (BufferedReader reader = new BufferedReader(new FileReader(file_path))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                file_contents.add(crypto.decryptString(line));
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
        catch (Exception e) {
			e.printStackTrace();
		}
        
        return file_contents;
    }
}
