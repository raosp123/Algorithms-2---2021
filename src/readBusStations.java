import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
     * fileToLinesArray, what the function does:
     * this function takes stop.txt file, and breaks it down line by line and stores it in the linesArray.
     * 
     * parameters: explain the input parameters
     * 1) fileName: which is "stop.txt", name of the file which will be broken down into lines.
     * 2) arraySize: int number which represents the size of the array (8759 in our case)
     *
     * returns: linesArray with all the lines, beggining with the second one (1888,51874,WB HASTINGS ST FS HOLDOM AVE-,....)
     
     * linesArrToTST, what the function does:
     * this function takes the linesArray, produced by the function above, and breaks it down further. However, unlike the previous function, this one 
     * only takes part of the text, stop id and stop name in particular. These values are then stored in the 2d array.
     * parameters: explain the input parameters
     * 1) linesArray: array with all the stop.txt lines.
     * 2) arraySize: int number which represents the size of the array (8759 in our case)
     *
     * returns: linesArray with all the lines, beggining with the second one (1888,51874,WB HASTINGS ST FS HOLDOM AVE-,....)
     */



public class readBusStations {

	private static final int ARR_SIZE = 8759;

	public static void main(String[] args) throws IOException {

		String fnStops = "src/stops.txt";

		//stationsArray is a 2D array [bus stop id of type int][bus name of type string].
		Object[][] stationsArray = new Object[ARR_SIZE][2];
		String[] linesArray = new String[ARR_SIZE]; //array which contains each line of the stops text file.

		stationsArray[0][1] = "a";
		stationsArray[0][0] = 1;

		linesArray = fileToLinesArray(fnStops, ARR_SIZE);
		stationsArray = linesArrToTST(linesArray, ARR_SIZE);
	}

	static String[] fileToLinesArray (String fileName, int arraySize) throws IOException {

		String[] arrayOutput = new String[arraySize];

		int i = 0;
		String strLine = "";
		BufferedReader br = new BufferedReader(new FileReader(fileName));

		while (strLine != null) {

			strLine = br.readLine();
			arrayOutput[i] = strLine;
			i = i + 1;
		}
		System.out.println(arrayOutput[1]);
		br.close();
		return arrayOutput;     
	}

	static Object[][] linesArrToTST (String[] linesArray, int arraySize) {

		Object[][] arrayOutput = new Object[arraySize][2];

		String line = "";
		String[] tokens = null;

		arrayOutput[0][1] = "a";
		arrayOutput[0][0] = 1;

		for (int i = 1; i < ARR_SIZE; i++) {

			line = linesArray[i];
			if (line != null) {
				tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			}
			arrayOutput[i][0] = tokens[0]; //filling first column in the array with stop ids
			arrayOutput[i][1] = tokens[2]; //filling the second column in the array with stops names, associated with the stop id form the frist column.
		}
		System.out.println(arrayOutput[8756][0]);
		System.out.println(arrayOutput[8756][1]);

		return arrayOutput;     
	}
}
