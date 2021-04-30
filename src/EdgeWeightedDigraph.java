
public class EdgeWeightedDigraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;                // number of vertices in this digraph
    private int E;                      // number of edges in this digraph
    private Bag<DirectedEdge>[] adj;    // adj[v] = adjacency list for vertex v
    private int[] indegree;             // indegree[v] = indegree of vertex v
    private int[] stopMapping;			// tells you which node represents which stop id
    
    //Initialises a graph with V vertices and 0 edges
    @SuppressWarnings("unchecked")
	public EdgeWeightedDigraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative");
        this.V = V;
        this.E = 0;
        this.stopMapping = new int[V];
        this.indegree = new int[V];
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge>();
    }
    
    //maps stopId to adj node
    public void mapStop(int stopId, int adjIndex) {
    	stopMapping[adjIndex] = stopId;
    }
    
    //returns which node in the graph corresponds to which stopId
    public int getStopMap(int stopId) {
    	
    	for(int index = 0; index < stopMapping.length; index++) {
    		if (stopMapping[index]==stopId) {
    			return index;
    		}
    	}
    	return -1;
    }
    
	public int[] getStopMaps () {
		return stopMapping;
	}
	
	
    //creates a new edge in the graph
    public void createEdge(int from, int to, int weight) {
    	
    	from = getStopMap(from); 
    	to = getStopMap(to);
    	
    	DirectedEdge newEdge = new DirectedEdge(from, to, weight);
    	addEdge(newEdge);
    	
    }

    //returns number of vertices in graph
    public int V() {
        return V;
    }

    //returns number of edges in graph
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    //Adds a directed edge to this edge-weighted digraph. 
    public void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        indegree[w]++;
        E++;
    }


    //returns an iterable of directed edges from node v
    public Iterable<DirectedEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

   //number of outward directed edges
    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

   //number of inward directed edges
    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    //returns all directed edges in the graph
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> list = new Bag<DirectedEdge>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    } 

    //returns string representation
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }


}
