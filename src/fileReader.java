import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;

public class fileReader {

    private EdgeWeightedDigraph graph;
    private LocalTime currentArrivalTime;
    private LocalTime nextArrivalTime;
    private LocalTime currentDepartureTime;
    private LocalTime nextDepartureTime;

    fileReader(){
        try
        {
            // reads stops.txt and gives no of vertices to create a new graph
            int lineNum=0;
            BufferedReader reader = new BufferedReader(new FileReader("src/stops.txt"));
            reader.readLine();
            while(reader.readLine()!=null) lineNum++;
            graph= new EdgeWeightedDigraph(lineNum);

            int i=0;
            reader = new BufferedReader(new FileReader("src/stops.txt"));
            reader.readLine();
            String currLine=reader.readLine();
            while(currLine!=null)
            {
                String[] splitLine = currLine.trim().split("[,]");
                graph.mapStop(Integer.parseInt(splitLine[0]),i);
                i++;
                currLine=reader.readLine();
            }

            // reads transfers.txt and adds edges to the graph
            reader= new BufferedReader(new FileReader("src/transfers.txt"));
            reader.readLine();
            currLine=reader.readLine();
            while (currLine!=null)
            {
                String[] splitLine = currLine.trim().split("[,]");
                int fromID= Integer.parseInt(splitLine[0]);
                int toID= Integer.parseInt(splitLine[1]);
                int transferType= Integer.parseInt(splitLine[2]);
                int weight;
                if(transferType==0)
                {
                    weight=2;
                }
                else
                {
                    int transferTime= Integer.parseInt(splitLine[3]);
                    weight=transferTime/100;
                }
                graph.createEdge(fromID,toID,weight);
                currLine=reader.readLine();
            }

            // reads stop_times.txt and adds edges to the graph
            reader= new BufferedReader(new FileReader("src/stop_times.txt"));
            BufferedReader reader1 = new BufferedReader(new FileReader("src/stop_times.txt"));
            reader.readLine();      // skips the first line
            reader1.readLine();     // Skips the first line
            reader1.readLine();     // skips the second line
            currLine = reader.readLine();
            String nextLine = reader1.readLine();
            while (currLine!=null&&nextLine!=null)
            {
                String[] splitLine = currLine.trim().split("[,]");
                String[] splitNextLine = nextLine.trim().split("[,]");
                int currentTripID = Integer.parseInt(splitLine[0]);
                int nextTripID = Integer.parseInt(splitNextLine[0]);
                int currentStopId= Integer.parseInt(splitLine[3]);
                int nextStopId= Integer.parseInt(splitNextLine[3]);
                try {
                    currentArrivalTime = LocalTime.parse(splitLine[1].replaceAll("\\s", "0"));
                    nextArrivalTime = LocalTime.parse(splitNextLine[1].replaceAll("\\s", "0"));
                    currentDepartureTime = LocalTime.parse(splitLine[2].replaceAll("\\s", "0"));
                    nextDepartureTime = LocalTime.parse(splitNextLine[2].replaceAll("\\s", "0"));
                }
                catch(Exception e)
                {
                    currentArrivalTime= null;
                    nextArrivalTime= null;
                    currentDepartureTime= null;
                    nextDepartureTime=null;
                }

                if(currentTripID==nextTripID && currentArrivalTime!=null && currentDepartureTime!=null &&
                        nextArrivalTime!=null && nextDepartureTime!=null)
                {
                    graph.createEdge(currentStopId,nextStopId,1);
                }
                currLine=reader.readLine();
                nextLine=reader1.readLine();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    EdgeWeightedDigraph returnGraph()
    {
        return graph;
    }
}
