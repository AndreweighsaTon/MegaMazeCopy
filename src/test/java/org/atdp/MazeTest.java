package org.atdp;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MazeTest {
    /**
     * Basic test to ensure mazes have the correct
     * number of vertices when instantiated.
     */
    @Test
    public void testCreateMaze() {
        Maze m = new Maze(10, 10);
        assertEquals(m.vertices.size(), 100);
    }

    // Write additional tests below. Make sure to check that generated mazes are valid
    // (all tiles connected to at least one of its neighbors, and none of its non-neighbors).

}
