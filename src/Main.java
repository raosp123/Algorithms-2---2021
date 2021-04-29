import java.io.IOException;
import java.time.LocalTime;

public class Main {

	public static void main(String[] args) throws IOException {

		if(args[0].equalsIgnoreCase("SP")) {
			try {
				int sourceStop = Integer.parseInt(args[1]);
				int targetStop = Integer.parseInt(args[2]);

				fileReader reader = new fileReader();
				EdgeWeightedDigraph G = reader.returnGraph();
				
				if(G.getStopMap(sourceStop)==-1) {
					throw new IllegalArgumentException("Please enter valid stop IDs");
				}

				DijkstraSP sp = new DijkstraSP(G,sourceStop);
				String path = sp.shortestPath(sourceStop, targetStop);

				System.out.println(path);
			}
			catch(ArrayIndexOutOfBoundsException e) {
				throw new IllegalArgumentException("Please enter valid stop IDs");
			}
		}
		else if (args[0].equalsIgnoreCase("TST")) {
			try {
				TST<Integer> tree = new TST<Integer>();
				String input = args[1];
				for (int i = 2; i < args.length; i++) {
					input = input + " " + args[i];
				}
				tree.activateSearchSystem(input.toUpperCase());
			}
			catch(ArrayIndexOutOfBoundsException e) {
				throw new IllegalArgumentException("Please enter valid stop name");
			}
		}
		else if(args[0].equalsIgnoreCase("SBAT")) {
			try {
				LocalTime.parse(args[1]);
				searchByTime search = new searchByTime();
				search.getAllTrips(args[1]);
			}
			catch(Exception e) {
				throw new IllegalArgumentException("Please enter valid time");
			}
		}
		else {
			throw new IllegalArgumentException("Invalid Command-Line Inputs, Please Enter SP or TST or SBAT");
		}
	}
}