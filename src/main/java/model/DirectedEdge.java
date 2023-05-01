package model;

public class DirectedEdge {
    public int source;
    public int destination;
    public int weight;

    // Constructor
    public DirectedEdge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}