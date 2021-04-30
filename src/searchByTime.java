import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class searchByTime {

	//read from file into arraylist?, and copy all the trip IDS for the user inputted arrival time into a seperate list
	
	//error checking for invalid times happens at the same time

	
	//go back through the arraylist and push all the entries with matching trip IDS into some other data format
	
	//or just print them for now because I'm lazy
	
	
	
	
	//method to get data in the format I want.
	//should merge this with fileReader class later
	
	

	
	//input String should be in format dd:hh:mm like in the spec sheet
	public void getAllTrips(String userInput) {	
		
		ArrayList<String> parsed_stop_times = new ArrayList<String>();
		ArrayList<String> tripIDlist = new ArrayList<String>();
		
		try
		{
			BufferedReader reader= new BufferedReader(new FileReader("src/stop_times.txt"));
	        reader.readLine();      // skips the first line
	        
	        
	        String currLine = reader.readLine();
	        
	        //N array accesses
	        while (currLine!=null)
	        {
	        	
	            String[] splitLine = currLine.trim().split("[,]");          
	            // check if it matches the user inputed time
	            //if it does then off the trip ID goes into another arrayList.
	            if(splitLine[1].trim().equals(userInput.trim())) {
	            	tripIDlist.add(splitLine[0].trim());
	            }
	            	
	            	
	            
	            
	           	//I think i'm meant to do something here but I forget :)
	            
	            currLine=reader.readLine();
	            
	        }
	        
	        //do it all again this time shoving all the relevant data into an array based on trip ID,
	        reader= new BufferedReader(new FileReader("src/stop_times.txt"));      
	        reader.readLine();      // skips the first line
	        currLine = reader.readLine();
	        while (currLine!=null)
	        {
	        	String[] splitLine = currLine.split("[,]");
        		String[] splitDate = splitLine[1].trim().split("[:]");
        		//checking to make sure valid times are only displayed
        		if (Integer.parseInt(splitDate[0]) < 24  && Integer.parseInt(splitDate[1]) < 60 && Integer.parseInt(splitDate[2]) < 60)
        		{
		        	for(int i = 0; i < tripIDlist.size(); i++)
		        	{  
		        		
		        		if (splitLine[0].equals(tripIDlist.get(i)))
		        		{
		        			//System.out.println(currLine);
		        			parsed_stop_times.add(currLine);
		        		}
		        	}
        		}
	            currLine=reader.readLine();
        		
	        }
	        


	        if (parsed_stop_times.size() != 0)
	        {
	        	
	        	
		        
		        
		        Collections.sort(parsed_stop_times);
                for(String s: parsed_stop_times)
                    System.out.println(s);
	        }
	        else
	        	System.out.println("There are no stop times matching the input.");
		}
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }			
	}
}


