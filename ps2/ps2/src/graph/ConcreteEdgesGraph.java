package graph;

import java.util.*;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph implements Graph<String> {
    
    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();
    
    // Abstraction function:
    //   Represents a directed graph where each vertex is a string, and edges have weights.
    // Representation invariant:
    //   Vertices in the graph are unique.
    //   Edges in the graph are unique based on source and target vertices.
    // Safety from rep exposure:
    //   The set of vertices and the list of edges are private, and defensive copying is used when needed.
    
    // TODO constructor
    public ConcreteEdgesGraph() {
        // Constructor can be empty if no additional setup is needed.
    }
    
    // TODO checkRep
    private void checkRep() {
        Set<String> uniqueVertices = new HashSet<>();
        for (Edge edge : edges) {
            uniqueVertices.add(edge.getSource());
            uniqueVertices.add(edge.getTarget());
        }
        assert vertices.equals(uniqueVertices) : "Vertices and edges are not consistent.";
    }
    
    @Override
    public boolean add(String vertex) {
        // TODO
        throw new RuntimeException("not implemented");
    }
    
    @Override
    public int set(String source, String target, int weight) {
        // TODO
        throw new RuntimeException("not implemented");
    }
    
    @Override
    public boolean remove(String vertex) {
        // TODO
        throw new RuntimeException("not implemented");
    }
    
    @Override
    public Set<String> vertices() {
        // TODO
        throw new RuntimeException("not implemented");
    }
    
    @Override
    public Map<String, Integer> sources(String target) {
        // TODO
        throw new RuntimeException("not implemented");
    }
    
    @Override
    public Map<String, Integer> targets(String source) {
        // TODO
        throw new RuntimeException("not implemented");
    }
    
    // TODO toString()
}

/**
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class are
 * up to you.
 */
class Edge {
    
    // TODO fields
    private final String source;
    private final String target;
    private final int weight;
    
    // Abstraction function:
    //   Represents a directed edge in the graph with a source vertex, a target vertex, and a weight.
    
    // Representation invariant:
    //   source and target are not null.
    //   weight is non-negative.
    
    // Safety from rep exposure:
    //   Fields are private and final.
    
    // TODO constructor
    public Edge(String source, String target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }
    
    // TODO checkRep
    private void checkRep() {
        assert source != null : "Source vertex cannot be null.";
        assert target != null : "Target vertex cannot be null.";
        assert weight >= 0 : "Weight of the edge cannot be negative.";
    }
    
    // TODO methods
    public String getSource() {
        return source;
    }
    
    public String getTarget() {
        return target;
    }
    
    public int getWeight() {
        return weight;
    }
    
    // TODO toString()
}
