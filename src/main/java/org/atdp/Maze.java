package org.atdp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

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
        this.height = height;
        this.width = width;
        gen = new Random(seed);


        // Generate maze

        generateMaze();






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
        boolean[] visited = new boolean[this.height * this.width]; // Creates a new boolean length of the width and height.
        Stack<Vertex> newStack = new Stack<>(); // creates a new stack of Vertexes
        Vertex vert = vertices.get(0); // Stores the first index of the given vertices
        while (vert != vertices.get(this.height * this.width - 1)) { // While the current vert is not equal to the last vert, continue.
            newStack.add(vert); // adds the vert to the stack
            visited[vert.id] = true; // adds true to the boolean array because it has been visited.
            while ((vert = addNextEdge(vert, visited)) == null) { //new added edge is the current vert
                vert = newStack.pop(); // pop when all neighbors have been visited.
            }
            newStack.add(vert); // adds vert to stack
            visited[vert.id] = true; // marks as visited
        }
        // Make sure all tiles are connected by checking if it's visited or not and random
        // select a neighbor
        for(int i = 0; i < this.height*this.width; i++) { // for all the vertices in maze
            if(visited[i] == false) { // if it is not visited
                vert = vertices.get(i); //scan for one that is not visited
                List<Vertex> neighbors = getNeighbors(vert);// getting all the neighbors of the given vertex
                int next = gen.nextInt(neighbors.size()); // choose next random neighbor
                Vertex v = neighbors.get(next); // getting next neighbor
                if(visited[v.id] == true) { // if has been visited
                    addEdge(vert.id, v.id); // add new edge
                    visited[vert.id] = true;
                    continue;
                }
                else {
                    for (Vertex x : neighbors) {
                        if (visited[x.id] == true) {
                            addEdge(vert.id, x.id);
                            visited[vert.id] = true;
                            break;
                        }
                    }
                }
            }
        }


    }
    Vertex addNextEdge(Vertex u, boolean visited[])
    {
        boolean isThere = false;
        List<Vertex> neighbors = getNeighbors(u); //Gets the Neighbors of Given Vertex
        int next = gen.nextInt(neighbors.size()); //generates the next id randomly

        Vertex v = neighbors.get(next); // Stores the next id to go to in Vertex V
        if(visited[v.id] == false) { // if it hasn't visited
            isThere = true; //found a node to go to
        }
        else {

            for (Vertex x : neighbors) {
                if (visited[x.id] == false) { // if neighbor hasn't been visited
                    v = x; // moving to a spot that has not been visited
                    isThere = true; // found a neighbor that isn't visited
                    break;
                }
            }
        }
        if(isThere == false) {
            return null; // if nothing is found return null and now other method has to backtrack
        }
        visited[v.id] = true; // visit turns to true
        addEdge(u.id, v.id); // creates and edge between the two.

        return v; // moving to new neighbor
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



       // for(int i =0; i < capacity; i++){
     //       neighbors.add(new Vertex(i));
     //   }

        List<Vertex> temp = new ArrayList<>();
    /*
        // check for x axis for cornering
        // check for y axis for cornering
        if((v.id % width != 0) && (v.id % width != (width-1)) && (v.id > width) && (v.id < width * (height - 1))){
            temp.add(new Vertex(v.id-width));
            temp.add(new Vertex (v.id-1));
            temp.add(new Vertex (v.id+1));
            temp.add(new Vertex(v.id+width));
        }

        // v.id == width-1 || v.id == width*2 || v.id == (width*height-1)

        //Top Left corner
        if(v.id==0){
            temp.add(new Vertex(1));
            temp.add(new Vertex(width));

        }



        //Left Bottom corner
        if(v.id==width *(height-1)){
            temp.add(new Vertex(v.id+1));
            temp.add(new Vertex(v.id-1));
        }

        //Right Bottom  corner
        if(v.id== width*height - 1){
            temp.add(new Vertex(v.id-width));
            temp.add(new Vertex(v.id-1));

        }


        */

        //west
        if(v.id % width != 0){
            temp.add(new Vertex(v.id-1));  // left Node
        }

        if((v.id + 1) % width != 0){
            temp.add(new Vertex(v.id+1)); //Right Node
        }

        if(v.id - width > 0){
            temp.add(new Vertex(v.id-width)); // TOp
        }

        if(v.id + width > (width*height)-1){
            temp.add(new Vertex(v.id+width)); // Down
        }





        return temp;
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
