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
        checkRep();
        return vertices.add(vertex);
    }
    
    @Override
    public int set(String source, String target, int weight) {
        checkRep();
        if (!vertices.contains(source) || !vertices.contains(target)) {
            throw new IllegalArgumentException("Source or target vertex does not exist.");
        }
        
        Edge newEdge = new Edge(source, target, weight);
        int index = edges.indexOf(newEdge);
        
        if (index == -1) {
            edges.add(newEdge);
            checkRep();
            return 0; // Edge was added
        } else {
            Edge existingEdge = edges.get(index);
            int oldWeight = existingEdge.getWeight();
            existingEdge.setWeight(weight);
            checkRep();
            return oldWeight; // Edge already existed, return old weight
        }
    }
    
    @Override
    public boolean remove(String vertex) {
        checkRep();
        if (!vertices.contains(vertex)) {
            return false; // Vertex does not exist
        }
        
        vertices.remove(vertex);
        edges.removeIf(edge -> edge.getSource().equals(vertex) || edge.getTarget().equals(vertex));
        
        checkRep();
        return true;
    }
    
    @Override
    public Set<String> vertices() {
        checkRep();
        return new HashSet<>(vertices);
    }
    
    @Override
    public Map<String, Integer> sources(String target) {
        checkRep();
        if (!vertices.contains(target)) {
            throw new IllegalArgumentException("Target vertex does not exist.");
        }
        
        Map<String, Integer> sourceMap = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.getTarget().equals(target)) {
                sourceMap.put(edge.getSource(), edge.getWeight());
            }
        }
        
        return sourceMap;
    }
    
    @Override
    public Map<String, Integer> targets(String source) {
        checkRep();
        if (!vertices.contains(source)) {
            throw new IllegalArgumentException("Source vertex does not exist.");
        }
        
        Map<String, Integer> targetMap = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(source)) {
                targetMap.put(edge.getTarget(), edge.getWeight());
            }
        }
        
        return targetMap;
    }
    
    // TODO toString()
    
    /**
     * Immutable.
     * This class is internal to the rep of ConcreteEdgesGraph.
     * 
     * <p>PS2 instructions: the specification and implementation of this class are
     * up to you.
     */
    private static class Edge {
        
        // TODO fields
        private final String source;
        private final String target;
        private int weight;
        
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
        
        public void setWeight(int weight) {
            this.weight = weight;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Edge edge = (Edge) obj;
            return source.equals(edge.source) && target.equals(edge.target);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(source, target);
        }
        
        // TODO toString()
    }
}
