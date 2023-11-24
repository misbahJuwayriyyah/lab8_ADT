package graph;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Collections;
import java.util.Set;

public abstract class GraphInstanceTest {
    
    // Testing strategy for Graph<String>
    //   add()
    //     add a vertex
    //     add multiple vertices
    //     add duplicate vertices
    //   set()
    //     set an edge
    //     set multiple edges
    //     set duplicate edges
    //   remove()
    //     remove a vertex
    //     remove an edge
    //     remove multiple vertices
    //     remove multiple edges
    //   vertices()
    //     get vertices from an empty graph
    //     get vertices from a non-empty graph
    //   sources()
    //     get sources from an empty graph
    //     get sources from a non-empty graph
    //   targets()
    //     get targets from an empty graph
    //     get targets from a non-empty graph

    public abstract Graph<String> emptyInstance();

    @Test
    public void testAddVertex() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.add("A"));
        assertTrue(graph.vertices().contains("A"));
    }

    @Test
    public void testAddMultipleVertices() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.add("A"));
        assertTrue(graph.add("B"));
        assertTrue(graph.vertices().contains("A"));
        assertTrue(graph.vertices().contains("B"));
    }

    @Test
    public void testAddDuplicateVertices() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.add("A"));
        assertFalse(graph.add("A")); // Adding the same vertex again should return false
    }

    @Test
    public void testSetEdge() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        assertEquals(1, graph.targets("A").get("B").intValue());
    }

    @Test
    public void testSetMultipleEdges() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.set("A", "B", 1);
        graph.set("A", "C", 2);
        assertEquals(1, graph.targets("A").get("B").intValue());
        assertEquals(2, graph.targets("A").get("C").intValue());
    }

    @Test
    public void testRemoveVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        assertTrue(graph.remove("A"));
        assertFalse(graph.vertices().contains("A"));
    }

    @Test
    public void testRemoveEdge() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 1);
        assertTrue(graph.remove("A"));
        assertNull(graph.targets("A").get("B"));
    }

    @Test
    public void testRemoveMultipleVertices() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        assertTrue(graph.remove("A"));
        assertFalse(graph.vertices().contains("A"));
        assertFalse(graph.vertices().contains("B"));
    }

    @Test
    public void testRemoveMultipleEdges() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.set("A", "B", 1);
        graph.set("A", "C", 2);
        assertTrue(graph.remove("A"));
        assertNull(graph.targets("A").get("B"));
        assertNull(graph.targets("A").get("C"));
    }

    @Test
    public void testVerticesEmptyGraph() {
        Graph<String> graph = emptyInstance();
        assertEquals(Collections.emptySet(), graph.vertices());
    }

    @Test
    public void testVerticesNonEmptyGraph() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        Set<String> vertices = graph.vertices();
        assertEquals(2, vertices.size());
        assertTrue(vertices.contains("A"));
        assertTrue(vertices.contains("B"));
    }


    @Test
    public void testSourcesNonEmptyGraph() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("B", "A", 1);
        assertEquals(1, graph.sources("A").get("B").intValue());
    }

    @Test
    public void testTargetsNonEmptyGraph() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 1);
        assertEquals(1, graph.targets("A").get("B").intValue());
    }


}
