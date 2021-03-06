package org.atdp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * A basic undirected, unweighted graph implementation.
 */
public class Graph {
    /**
     * List of vertices in this graph.
     */
    ArrayList<Vertex> vertices;

    /**
     * Initializes a new Graph with a give number of vertices.
     * For each vertex, set vertex labels consecutively.
     * 
     * For example, a new Graph(3) would have 3 vertices
     * with labels 0, 1, and 2.
     */
    public Graph(int numVertices) {
        // YOUR CODE HERE
        vertices = new ArrayList<>(numVertices);
        for(int i =0; i < numVertices;i++){
            Vertex newVertex = new Vertex(i);
            vertices.add(newVertex);
        }
    }

    /**
     * Creates an edge between the vertex with label U and vertex with label V.
     * Remember to add the edge to both vertices since the graph is undirected!
     * 
     * In the case that u == v, do nothing (simple graphs only).
     */
    public void addEdge(int u, int v) {
        // YOUR CODE HERE
        if(u==v){
            return;
        }
        Vertex temp1 = vertices.get(u);
        Vertex temp2 = vertices.get(v);

        temp1.edges.add(temp2);
        temp2.edges.add(temp1);
    }

    /**
     * Returns the Vertex with label ID.
     * Returns null if the ID does not exist in the graph.
     */
    public Vertex getVertex(int id) {
        // YOUR CODE HERE
        if (id > vertices.size() || id < 0) {
            return null;
        }
        return vertices.get(id);
    
}


/**
 * A Vertex class: every vertex has a set of edges.
 * Don't worry too much about what a HashSet is for now;
 * what you need to know is that you can call `edges.add(other_vertex)`
 * to add to the set, and that there are no duplicates.
 */
class Vertex {
    int id;
    Set<Vertex> edges;
    
    Vertex(int id) {
        edges = new HashSet<>();
        this.id = id;
    }
}
