package org.atdp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Contains the basic structure and utilities for generating mazes up to 100x100 in size.
 * Two mazes with the same seed and dimensions are guaranteed to have the same layout.
 * 
 * Mazes are rectangular, with tiles numbering from 0 (top left) to (height*width)-1 (bottom right).
 * Tiles are vertices identified in row-major order. For example, a 3x3 maze would be represented
 * as a 9-vertex graph labeled as such:
 * 
 * 0 1 2
 * 3 4 5
 * 6 7 8
 * 
 * Maze is a child class of Graph. It will be helpful to review the Graph class before you
 * begin writing code.
 * 
 */
public class Maze extends Graph {
    private int height, width;
    /**
     * Use this to generate random numbers given a seed!
     * See https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Random.html
     */
    private Random gen;

    /**
     * Takes in a height (# rows), width (# columns), and a seed, and generates a new maze.
     */
    public Maze(int height, int width, int seed) {
        // Call Graph constructor
        super(height * width);

        // Instantiate variables

        // Generate maze
    }

    /**
     * Creates a maze with a random seed from 0 to 32767.
     */
    public Maze(int height, int width) {
        this(height, width, (int)(Math.random() * 32768));
    }

    // Getter functions
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    /**
     * Generates this maze given the height, width, and seed specified
     * in the constructor. You will probably need to use getNeighbors().
     * 
     * HINT: Try to figure out a way to modify BFS or DFS to create connections
     * as you perform a graph traversal of some sort!
     */
    void generateMaze() {
        // YOUR CODE HERE
    }

    /**
     * Returns the immediate neighbors (top, bottom, left, and right) of a particular vertex V.
     * IMPORTANT: Make sure to handle edge and corner cases! For example, in the 3x3 maze:
     * 0 1 2
     * 3 4 5
     * 6 7 8
     * the neighbors of 4 should be [1, 3, 5, 7], 
     * but the neighbors of 6 are only [3, 7].
     */
    List<Vertex> getNeighbors(Vertex v) {
        List<Vertex> neighbors = new ArrayList<>();
        
        // YOUR CODE HERE

        return neighbors;
    }

    /** 
     * Use this during testing to compare mazes:
     * m1.equals(m2) where m1, m2 are Maze objects
     * Two mazes are equal if they have the same layout and dimensions, 
     * even though they may not be exactly the same object.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Maze)) {
            return false;
        }
        CleanedMaze c1 = new CleanedMaze(this); 
        CleanedMaze c2 = new CleanedMaze((Maze)(other)); 

        if (c1.rows != c2.rows || c1.cols != c2.cols) {
            return false;
        }

        for (int i = 0; i < height * width; i++) {
            for (Integer vInt : c1.connections.get(i)) {
                if (!c2.connections.get(i).contains(vInt)) {
                    return false;
                }
            }
        }

        return true;
    }
}
