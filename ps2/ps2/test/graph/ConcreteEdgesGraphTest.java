package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {

    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }

    /*
     * Testing ConcreteEdgesGraph...
     */

    // Testing strategy for ConcreteEdgesGraph.toString()
    //   Test with an empty graph.
    //   Test with a graph containing edges.
    //   Ensure that the output format is correct.

    @Test
    public void testToStringEmptyGraph() {
        Graph<String> graph = emptyInstance();
        assertEquals("Graph: {}", graph.toString());
    }

    @Test
    public void testToStringNonEmptyGraph() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 1);
        assertEquals("Graph: {(A, B, 1)}", graph.toString());
    }

    /*
     * Testing Edge...
     */

    // Testing strategy for Edge
    //   Test creating an edge.
    //   Test adding edges to the graph.
    //   Test removing edges from the graph.
    //   Test updating the weight of an existing edge.
    //   Test removing vertices that are part of edges.
    //   Test operations with non-existing edges.

    @Test
    public void testCreateEdge() {
        Graph<String> graph = emptyInstance();
        assertNotNull(graph.targets("A").get("B"));
        assertEquals(1, graph.targets("A").get("B").intValue());
    }

    @Test
    public void testAddEdgesToGraph() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        assertNotNull(graph.targets("A").get("B"));
        assertEquals(1, graph.targets("A").get("B").intValue());
    }

    @Test
    public void testRemoveEdgesFromGraph() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        assertTrue(graph.remove("A"));
        assertNull(graph.targets("A").get("B"));
    }

    @Test
    public void testUpdateEdgeWeight() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        assertNotNull(graph.targets("A").get("B"));
        assertEquals(2, graph.targets("A").get("B").intValue());
    }

    @Test
    public void testRemoveVerticesWithEdges() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        assertTrue(graph.remove("A"));
        assertFalse(graph.vertices().contains("A"));
        assertNull(graph.targets("B").get("A"));
    }

    @Test
    public void testOperationsWithNonExistingEdges() {
        Graph<String> graph = emptyInstance();
        assertFalse(graph.remove("A")); // Removing a non-existing edge should return false
        assertNull(graph.targets("A").get("B")); // Non-existing edge should return null for targets
    }
}
