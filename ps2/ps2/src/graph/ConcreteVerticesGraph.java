package graph;

import java.util.*;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph implements Graph<String> {
    
    private final List<Vertex> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   Represents a directed graph where each vertex is a string.
    // Representation invariant:
    //   Vertices in the graph are unique.
    // Safety from rep exposure:
    //   The list of vertices is private, and defensive copying is used when needed.
    
    // TODO constructor
    public ConcreteVerticesGraph() {
        // Constructor can be empty if no additional setup is needed.
    }
    
    // TODO checkRep
    private void checkRep() {
        Set<String> uniqueVertices = new HashSet<>();
        for (Vertex vertex : vertices) {
            uniqueVertices.add(vertex.getLabel());
        }
        assert vertices.size() == uniqueVertices.size() : "Duplicate vertices found.";
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
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex {
    
    // TODO fields
    private final String label;
    private final Map<String, Integer> outgoingEdges = new HashMap<>();
    private final Map<String, Integer> incomingEdges = new HashMap<>();
    
    // Abstraction function:
    //   Represents a vertex in the graph with a unique label.
    //   The outgoingEdges map represents outgoing edges where the key is the target vertex label and the value is the weight.
    //   The incomingEdges map represents incoming edges where the key is the source vertex label and the value is the weight.
    
    // Representation invariant:
    //   label is not null.
    //   All weights are non-negative.
    // Safety from rep exposure:
    //   Fields are private and defensively copied when needed.
    
    // TODO constructor
    public Vertex(String label) {
        this.label = label;
    }
    
    // TODO checkRep
    private void checkRep() {
        assert label != null : "Vertex label cannot be null.";
        for (int weight : outgoingEdges.values()) {
            assert weight >= 0 : "Weight of outgoing edge cannot be negative.";
        }
        for (int weight : incomingEdges.values()) {
            assert weight >= 0 : "Weight of incoming edge cannot be negative.";
        }
    }
    
    // TODO methods
    
    public String getLabel() {
        return label;
    }
    
    public Map<String, Integer> getOutgoingEdges() {
        return new HashMap<>(outgoingEdges);
    }
    
    public Map<String, Integer> getIncomingEdges() {
        return new HashMap<>(incomingEdges);
    }
    
    public void addOutgoingEdge(String target, int weight) {
        outgoingEdges.put(target, weight);
    }
    
    public void addIncomingEdge(String source, int weight) {
        incomingEdges.put(source, weight);
    }
    
    public void removeOutgoingEdge(String target) {
        outgoingEdges.remove(target);
    }
    
    public void removeIncomingEdge(String source) {
        incomingEdges.remove(source);
    }
    
    // TODO toString()
}
