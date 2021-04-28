import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;


public class searchByTime {

	//read from file into arraylist?, and copy all the trip IDS for the user inputted arrival time into a seperate list
	
	//error checking for invalid times happens at the same time
	
	//sort the trip IDS ascending order
	
	//go back through the arraylist and push all the entries with matching trip IDS into some other data format
	
	//or just print them for now because I'm lazy
	
	
	
	
	//method to get data in the format I want.
	//should merge this with fileReader class later
	
	
	
	static ArrayList<String> parsed_stop_times;
	
	
	//input String should be in format dd:hh:mm like in the spec sheet
	//there is no output because I'm simply going to print out the results to the console for now.
	void mufat(String userInput) {	
		
		
		Time arrivalTarget = Time.valueOf(userInput);
		try
		{
			BufferedReader reader= new BufferedReader(new FileReader("src/stop_times.txt"));
	        reader.readLine();      // skips the first line
	        
	        
	        String currLine = reader.readLine();
	        Time convertedTime = new Time(0);
	        while (currLine!=null)
	        {
	            String[] splitLine = currLine.trim().split("[,]");
	            String[] splitDate = splitLine[2].split("[:]");
	            if (Integer.parseInt(splitDate[0]) < 24  && Integer.parseInt(splitDate[1]) < 60 && Integer.parseInt(splitDate[2]) < 60)
	            {
	            	//add this line to the array of valid dates
	            	parsed_stop_times.add(currLine);
	            	
	            	//and check if it matches the user inputted time
	            }
	            else
	            	//do nothing
	            	//I think i'm meant to do something here but I forget :)
	            
	            currLine=reader.readLine();
	        }
		}
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
		
		
		//
	}
}


