package org.atdp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/** 
 * Tests for the Graph class.
 * You might want to add more tests; while there will be no hidden
 * tests for this section it's not guaranteed that the given tests
 * are 100% comprehensive.
*/
public class GraphTest {

    @Test
    public void testGraphInit() {
        Graph g = new Graph(10);
        assertEquals("New graph has correct number of vertices", 10, g.vertices.size());
        for (int i = 0; i < 10; i++) {
            assertEquals(i, g.vertices.get(i).id);
        }
    }

    @Test
    public void testGraphGetVertex() {
        Graph g = new Graph(10);
        assertEquals(g.vertices.get(6), g.getVertex(6));
        assertEquals(g.vertices.get(0), g.getVertex(0));
        assertNull(g.getVertex(-1));
        assertNull(g.getVertex(10293));
    }

    @Test
    public void testGraphAddEdges() {
        Graph g = new Graph(10);
        g.addEdge(0, 1);
        assertTrue(g.getVertex(0).edges.contains(g.getVertex(1)));
        assertTrue(g.getVertex(1).edges.contains(g.getVertex(0)));
        g.addEdge(0, 2);
        assertTrue(g.getVertex(0).edges.contains(g.getVertex(1)));
        assertTrue(g.getVertex(1).edges.contains(g.getVertex(0)));
        assertTrue(g.getVertex(0).edges.contains(g.getVertex(2)));
        assertTrue(g.getVertex(2).edges.contains(g.getVertex(0)));
        g.addEdge(5, 5);
        assertTrue(g.getVertex(5).edges.isEmpty());
    }
}
