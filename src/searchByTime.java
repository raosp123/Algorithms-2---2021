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

	
	//go back through the arraylist and push all the entries with matching trip IDS into some other data format
	
	//or just print them for now because I'm lazy
	
	
	
	
	//method to get data in the format I want.
	//should merge this with fileReader class later
	
	
	
	private ArrayList<String> parsed_stop_times = new ArrayList<String>();
	private ArrayList<String> tripIDlist = new ArrayList<String>();
	
	//input String should be in format dd:hh:mm like in the spec sheet
	//there is no output because I'm simply going to print out the results to the console for now.
	public String[] mufat(String userInput) {	
		
		
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

	            String[] splitDate = splitLine[1].trim().split("[:]");
	            if (Integer.parseInt(splitDate[0]) < 24  && Integer.parseInt(splitDate[1]) < 60 && Integer.parseInt(splitDate[2]) < 60)
	            {
	            	// check if it matches the user inputed time
	            	//if it does then off the trip ID goes into another arrayList.
	            	if(Time.valueOf(splitLine[1].trim()).equals(arrivalTarget)) {
	            		tripIDlist.add(splitLine[0]);
	            		System.out.println(tripIDlist.size());
	            	}
	            	
	            
	            }
	            
	           	//I think i'm meant to do something here but I forget :)
	            
	            currLine=reader.readLine();
	            
	        }
	        
	        
	        //do it all again this time shoving all the relevant data into an array based on trip ID,
	        reader= new BufferedReader(new FileReader("src/stop_times.txt"));      
	        reader.readLine();      // skips the first line
	        
	        while (currLine!=null)
	        {
	        	for(int i = 0; i < tripIDlist.size(); i++)
	        	{
	        		String[] splitLine = currLine.split("[,]");
	        		if (splitLine[0].equals(tripIDlist.get(i) ))
	        		{
	        			parsed_stop_times.add(currLine);
	        		}
	        	}
	            currLine=reader.readLine();
	        }
	        
	        
	        String[] output = new String[parsed_stop_times.size()];
	        		
	        		
	        parsed_stop_times.toArray(output);
	        
	        
	        return output;
	        
	        
	    	
		}
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
		
		
		
		
		
		
	}
}


