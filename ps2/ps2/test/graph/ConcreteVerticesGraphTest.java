package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {

    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }

    /*
     * Testing ConcreteVerticesGraph...
     */

    // Testing strategy for ConcreteVerticesGraph.toString()
    //   Test with an empty graph.
    //   Test with a graph containing vertices and edges.
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
        assertEquals("Graph: {A=[B=1], B=[]}", graph.toString());
    }

    /*
     * Testing Vertex...
     */

    // Testing strategy for Vertex
    //   Test creating a vertex.
    //   Test adding edges to a vertex.
    //   Test removing edges from a vertex.
    //   Test removing the vertex itself.
    //   Test operations with a non-existing vertex.

    @Test
    public void testVertexCreationAndOperations() {
        Graph<String> graph = emptyInstance();

        // Add a vertex using the add method
        assertTrue(graph.add("A"));

        // Test operations involving the added vertex
        assertEquals(0, graph.sources("A").size());  // Assuming the sources method returns an empty map for a new vertex
        assertEquals(0, graph.targets("A").size());  // Assuming the targets method returns an empty map for a new vertex

        // Try to set an edge involving the added vertex
        assertEquals(0, graph.set("A", "B", 1));  // Assuming set returns 0 if the source vertex doesn't exist

        // Remove the added vertex
        assertTrue(graph.remove("A"));

        // Check that the vertex is removed
        assertFalse(graph.vertices().contains("A"));
    }




    @Test
    public void testAddEdgesToVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 1);
        assertEquals(1, graph.targets("A").get("B").intValue());
    }

    @Test
    public void testRemoveEdgesFromVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 1);
        graph.remove("A");
        assertNull(graph.targets("A").get("B"));
    }

    @Test
    public void testRemoveVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 1);
        graph.remove("A");
        assertFalse(graph.vertices().contains("A"));
        assertNull(graph.targets("B").get("A"));
    }

    @Test
    public void testOperationsWithNonExistingVertex() {
        Graph<String> graph = emptyInstance();
        assertFalse(graph.remove("A")); // Removing a non-existing vertex should return false
        assertFalse(graph.vertices().contains("A")); // Non-existing vertex should not be in vertices
        assertNull(graph.targets("A")); // Non-existing vertex should return null for targets
    }
}
