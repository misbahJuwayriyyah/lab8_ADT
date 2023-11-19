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

    // Helper method to get a vertex by label
    private Vertex getVertex(String label) {
        for (Vertex vertex : vertices) {
            if (vertex.getLabel().equals(label)) {
                return vertex;
            }
        }
        return null;
    }
    
    @Override
    public boolean add(String vertex) {
        checkRep();
        
        vertices.add(new Vertex(vertex));
        checkRep();
        return true;
    }
    
    @Override
    public int set(String source, String target, int weight) {
        checkRep();
        Vertex sourceVertex = getVertex(source);
        Vertex targetVertex = getVertex(target);

        if (sourceVertex == null || targetVertex == null) {
            throw new IllegalArgumentException("Source or target vertex does not exist.");
        }

        int oldWeight = sourceVertex.getOutgoingEdges().getOrDefault(target, 0);
        sourceVertex.addOutgoingEdge(target, weight);
        targetVertex.addIncomingEdge(source, weight);

        checkRep();
        return oldWeight;
    }
    
    @Override
    public boolean remove(String vertex) {
        checkRep();
        Vertex vertexToRemove = getVertex(vertex);

        if (vertexToRemove == null) {
            return false; // Vertex does not exist
        }

        vertices.remove(vertexToRemove);

        // Remove incoming and outgoing edges
        for (Vertex v : vertices) {
            v.removeOutgoingEdge(vertex);
            v.removeIncomingEdge(vertex);
        }

        checkRep();
        return true;
    }
    
    @Override
    public Set<String> vertices() {
        checkRep();
        Set<String> vertexSet = new HashSet<>();
        for (Vertex vertex : vertices) {
            vertexSet.add(vertex.getLabel());
        }
        return Collections.unmodifiableSet(vertexSet);
    }
    
    @Override
    public Map<String, Integer> sources(String target) {
        checkRep();
        Vertex targetVertex = getVertex(target);
        if (targetVertex == null) {
            throw new IllegalArgumentException("Target vertex does not exist.");
        }

        return Collections.unmodifiableMap(targetVertex.getIncomingEdges());
    }
    
    @Override
    public Map<String, Integer> targets(String source) {
        checkRep();
        Vertex sourceVertex = getVertex(source);
        if (sourceVertex == null) {
            throw new IllegalArgumentException("Source vertex does not exist.");
        }

        return Collections.unmodifiableMap(sourceVertex.getOutgoingEdges());
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
        checkRep();
        outgoingEdges.put(target, weight);
        checkRep();
    }
    
    public void addIncomingEdge(String source, int weight) {
        checkRep();
        incomingEdges.put(source, weight);
        checkRep();
    }
    
    public void removeOutgoingEdge(String target) {
        checkRep();
        outgoingEdges.remove(target);
        checkRep();
    }
    
    public void removeIncomingEdge(String source) {
        checkRep();
        incomingEdges.remove(source);
        checkRep();
    }
    
    // TODO toString()
}
