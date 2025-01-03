import java.util.Stack;

/**
 * The {@code NumberOfIslands} class provides functionality to determine the number
 * of islands in a 2D grid. An island is defined as a group of '1's (land) connected
 * horizontally or vertically. Diagonal connections do not count.
 *
 * <p>The class implements two primary methods:
 * <ul>
 *     <li><strong>DFS Recursive Approach:</strong> Utilizes recursion to traverse and mark connected lands.</li>
 *     <li><strong>DFS Iterative Approach:</strong> Uses a stack to traverse and mark connected lands without recursion.</li>
 * </ul>
 * </p>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * NumberOfIslands solver = new NumberOfIslands();
 * char[][] grid1 = {
 *   {'1','1','1','1','0'},
 *   {'1','1','0','1','0'},
 *   {'1','1','0','0','0'},
 *   {'0','0','0','0','0'}
 * };
 * System.out.println("DFS Recursive Output: " + solver.numIslandsDFSRecursive(grid1)); // Output: 1
 * System.out.println("DFS Iterative Output: " + solver.numIslandsDFSIterative(grid1)); // Output: 1
 *
 * char[][] grid2 = {
 *   {'1','1','0','0','0'},
 *   {'1','1','0','0','0'},
 *   {'0','0','1','0','0'},
 *   {'0','0','0','1','1'}
 * };
 * System.out.println("DFS Recursive Output: " + solver.numIslandsDFSRecursive(grid2)); // Output: 3
 * System.out.println("DFS Iterative Output: " + solver.numIslandsDFSIterative(grid2)); // Output: 3
 * }</pre>
 *
 * <p><strong>Time Complexity:</strong> O(m*n)</p>
 * <p><strong>Space Complexity:</strong> O(m*n)</p>
 * 
 * Time and Space Complexity Analysis
    Understanding the efficiency of the implemented algorithms is crucial for optimizing performance, especially with large input sizes.

    Depth-First Search (DFS) Recursive Approach (numIslandsDFSRecursive)
    Time Complexity: O(m*n)

    Explanation:
    Each cell is visited at most once.
    For each land cell ('1'), the DFS explores all connected land cells.
    Total operations are proportional to the number of cells in the grid.
    Space Complexity: O(m*n)

    Explanation:
    In the worst-case scenario (all cells are land), the recursion stack can go as deep as m*n.
    Auxiliary space used by the recursion stack dominates the space complexity.
    Depth-First Search (DFS) Iterative Approach (numIslandsDFSIterative)
    Time Complexity: O(m*n)

    Explanation:
    Similar to the recursive approach, each cell is visited at most once.
    The iterative DFS explores all connected land cells efficiently.
    Space Complexity: O(m*n)

    Explanation:
    In the worst case, the stack can hold m*n cells.
    Auxiliary space used by the stack dominates the space complexity.
    Summary
    Both DFS approaches have the same time and space complexities.
    The Iterative DFS avoids potential stack overflow issues inherent in the Recursive DFS by using an explicit stack, making it more robust for large inputs.
 *
 * @author 
 */
public class NumberOfIslands {
    
    /**
     * Finds the number of islands in the grid using the DFS Recursive approach.
     *
     * <p>This method traverses the grid, and upon encountering a '1', it initiates a recursive
     * DFS to mark all connected '1's as visited. Each initiation signifies a distinct island.</p>
     *
     * @param grid A 2D array representing the grid map where '1' is land and '0' is water.
     * @return The number of distinct islands.
     */
    public int numIslandsDFSRecursive(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int numIslands = 0;
        int m = grid.length;
        int n = grid[0].length;
        
        // Iterate through each cell in the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If a '1' is found, it's part of an island
                if (grid[i][j] == '1') {
                    numIslands++;
                    // Perform DFS to mark all connected '1's
                    dfsRecursive(grid, i, j, m, n);
                }
            }
        }
        
        return numIslands;
    }
    
    /**
     * Helper method to perform DFS recursively.
     *
     * <p>This method marks the current cell as visited and recursively explores all
     * adjacent (up, down, left, right) cells that are part of the island.</p>
     *
     * @param grid The grid map.
     * @param i The current row index.
     * @param j The current column index.
     * @param m Total number of rows in the grid.
     * @param n Total number of columns in the grid.
     */
    private void dfsRecursive(char[][] grid, int i, int j, int m, int n) {
        // Base cases: check for out-of-bounds or water cells
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1') return;
        
        // Mark the cell as visited by setting it to '0'
        grid[i][j] = '0';
        
        // Explore all four adjacent directions
        dfsRecursive(grid, i + 1, j, m, n); // Down
        dfsRecursive(grid, i - 1, j, m, n); // Up
        dfsRecursive(grid, i, j + 1, m, n); // Right
        dfsRecursive(grid, i, j - 1, m, n); // Left
    }
    
    /**
     * Finds the number of islands in the grid using the DFS Iterative approach.
     *
     * <p>This method traverses the grid, and upon encountering a '1', it initiates an iterative
     * DFS using a stack to mark all connected '1's as visited. Each initiation signifies a distinct island.</p>
     *
     * @param grid A 2D array representing the grid map where '1' is land and '0' is water.
     * @return The number of distinct islands.
     */
    public int numIslandsDFSIterative(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int numIslands = 0;
        int m = grid.length;
        int n = grid[0].length;
        
        // Iterate through each cell in the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If a '1' is found, it's part of an island
                if (grid[i][j] == '1') {
                    numIslands++;
                    // Perform iterative DFS to mark all connected '1's
                    dfsIterative(grid, i, j, m, n);
                }
            }
        }
        
        return numIslands;
    }
    
    /**
     * Helper method to perform DFS iteratively using a stack.
     *
     * <p>This method marks the current cell as visited and uses a stack to explore all
     * adjacent (up, down, left, right) cells that are part of the island.</p>
     *
     * @param grid The grid map.
     * @param i The starting row index.
     * @param j The starting column index.
     * @param m Total number of rows in the grid.
     * @param n Total number of columns in the grid.
     */
    private void dfsIterative(char[][] grid, int i, int j, int m, int n) {
        // Initialize a stack for DFS
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{i, j});
        
        // Mark the starting cell as visited
        grid[i][j] = '0';
        
        // Define directions: up, down, left, right
        int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        
        // Iterate until the stack is empty
        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int row = current[0];
            int col = current[1];
            
            // Explore all adjacent directions
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                
                // Check boundaries and if the cell is land
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && grid[newRow][newCol] == '1') {
                    stack.push(new int[]{newRow, newCol});
                    grid[newRow][newCol] = '0'; // Mark as visited
                }
            }
        }
    }
    
    /**
     * The {@code main} method serves as an entry point to test the {@code NumberOfIslands} class.
     *
     * <p>It demonstrates the usage of both the DFS Recursive and DFS Iterative approaches
     * with sample inputs, including the provided examples and additional test cases.</p>
     *
     * @param args Command-line arguments (not utilized in this program).
     */
    public static void main(String[] args) {
        NumberOfIslands solver = new NumberOfIslands();
        
        // Example 1:
        // Input:
        // [
        //   ["1","1","1","1","0"],
        //   ["1","1","0","1","0"],
        //   ["1","1","0","0","0"],
        //   ["0","0","0","0","0"]
        // ]
        char[][] grid1 = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        System.out.println("DFS Recursive Output (Example 1): " + solver.numIslandsDFSRecursive(cloneGrid(grid1))); // Output: 1
        System.out.println("DFS Iterative Output (Example 1): " + solver.numIslandsDFSIterative(cloneGrid(grid1))); // Output: 1
        
        // Example 2:
        // Input:
        // [
        //   ["1","1","0","0","0"],
        //   ["1","1","0","0","0"],
        //   ["0","0","1","0","0"],
        //   ["0","0","0","1","1"]
        // ]
        char[][] grid2 = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        System.out.println("DFS Recursive Output (Example 2): " + solver.numIslandsDFSRecursive(cloneGrid(grid2))); // Output: 3
        System.out.println("DFS Iterative Output (Example 2): " + solver.numIslandsDFSIterative(cloneGrid(grid2))); // Output: 3
        
        // Additional Example 3:
        // Input:
        // [
        //   ["1","1","0"],
        //   ["1","0","0"],
        //   ["0","0","1"]
        // ]
        char[][] grid3 = {
            {'1','1','0'},
            {'1','0','0'},
            {'0','0','1'}
        };
        System.out.println("DFS Recursive Output (Example 3): " + solver.numIslandsDFSRecursive(cloneGrid(grid3))); // Output: 2
        System.out.println("DFS Iterative Output (Example 3): " + solver.numIslandsDFSIterative(cloneGrid(grid3))); // Output: 2
        
        // Edge Case Example 4:
        // Input: []
        char[][] grid4 = {};
        System.out.println("DFS Recursive Output (Example 4): " + solver.numIslandsDFSRecursive(cloneGrid(grid4))); // Output: 0
        System.out.println("DFS Iterative Output (Example 4): " + solver.numIslandsDFSIterative(cloneGrid(grid4))); // Output: 0
        
        // Edge Case Example 5:
        // Input:
        // [
        //   ["0"]
        // ]
        char[][] grid5 = {
            {'0'}
        };
        System.out.println("DFS Recursive Output (Example 5): " + solver.numIslandsDFSRecursive(cloneGrid(grid5))); // Output: 0
        System.out.println("DFS Iterative Output (Example 5): " + solver.numIslandsDFSIterative(cloneGrid(grid5))); // Output: 0
        
        // Edge Case Example 6:
        // Input:
        // [
        //   ["1"]
        // ]
        char[][] grid6 = {
            {'1'}
        };
        System.out.println("DFS Recursive Output (Example 6): " + solver.numIslandsDFSRecursive(cloneGrid(grid6))); // Output: 1
        System.out.println("DFS Iterative Output (Example 6): " + solver.numIslandsDFSIterative(cloneGrid(grid6))); // Output: 1
    }
    
    /**
     * Utility method to clone a 2D char array.
     *
     * <p>This ensures that each method receives a fresh copy of the grid to prevent
     * unintended side-effects between different method calls.</p>
     *
     * @param grid The original 2D char array.
     * @return A deep copy of the original 2D char array.
     */
    private static char[][] cloneGrid(char[][] grid) {
        if (grid == null) return null;
        char[][] clone = new char[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            clone[i] = grid[i].clone();
        }
        return clone;
    }
}
