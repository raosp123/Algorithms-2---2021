
public class DirectedEdge { 
    private final int v;
    private final int w;
    private final double weight;

    //creates edge from v to w, with weight weight
    public DirectedEdge(int v, int w, double weight) {
        if (v < 0) throw new IllegalArgumentException("Vertex names must be non-negative integers");
        if (w < 0) throw new IllegalArgumentException("Vertex names must be non-negative integers");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    //returns from vertex
    public int from() {
        return v;
    }

    //returns to vertex
    public int to() {
        return w;
    }

    //returns weight of edge
    public double weight() {
        return weight;
    }

    //string representation
    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }
}
