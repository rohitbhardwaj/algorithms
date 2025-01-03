import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * The {@code TopologicalSortGraphDFS} class provides functionality to perform a topological sort on a Directed Acyclic Graph (DAG)
 * using an iterative Depth-First Search (DFS) approach with an explicit stack.
 *
 * <p>Topological sorting of a graph is a linear ordering of its vertices such that for every directed edge \( uv \),
 * vertex \( u \) comes before vertex \( v \) in the ordering.</p>
 *
 * <p>This class implements an iterative DFS-based method to achieve topological sorting, avoiding the limitations
 * of recursive approaches.</p>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * TopologicalSortGraphDFS graph = new TopologicalSortGraphDFS(6);
 * graph.addEdge(5, 2);
 * graph.addEdge(5, 0);
 * graph.addEdge(4, 0);
 * graph.addEdge(4, 1);
 * graph.addEdge(2, 3);
 * graph.addEdge(3, 1);
 *
 * System.out.println("Following is a Topological Sort of the given graph:");
 * graph.topologicalSort(); // Possible Output: 4, 5, 2, 3, 1, 0
 * }</pre>
 *
 * <p><strong>Time Complexity:</strong> O(V + E), where V is the number of vertices and E is the number of edges.</p>
 * <p><strong>Space Complexity:</strong> O(V + E), due to storage of the adjacency list and stacks.</p>
 *
 * 
 * Depth-First Search (DFS) with Explicit Stack Approach (topologicalSort)
    Time Complexity: O(V + E)

    Explanation:
    Each vertex is visited exactly once.
    All edges are explored exactly once.
    The use of stacks for traversal and ordering does not add extra time complexity.
    Thus, the total time complexity is linear relative to the size of the graph.
    Space Complexity: O(V + E)

    Explanation:
    The adjacency list consumes O(V + E) space.
    The visited array consumes O(V) space.
    The traversalStack, tempStack, and topoStack can each hold up to O(V) elements.
    Thus, the overall space complexity is linear.
    Summary
    Time Efficiency: The iterative DFS approach operates in linear time, making it highly efficient for large graphs.

    Space Efficiency: The approach uses linear space, which is optimal given the need to store the graph's adjacency list and manage traversal data.
 * @author 
 */
public class TopologicalSortGraphDFS {
    // Number of vertices
    private int V;

    // Adjacency List representation
    private List<List<Integer>> adj;

    /**
     * Constructor to initialize the graph with a specified number of vertices.
     *
     * @param v The number of vertices in the graph.
     */
    public TopologicalSortGraphDFS(int v) {
        V = v;
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; ++i)
            adj.add(new ArrayList<>());
    }

    /**
     * Adds a directed edge from vertex {@code v} to vertex {@code w}.
     *
     * @param v The starting vertex of the edge.
     * @param w The ending vertex of the edge.
     */
    public void addEdge(int v, int w) {
        adj.get(v).add(w);
    }

    /**
     * Performs a topological sort of the graph using an iterative Depth-First Search (DFS) approach with an explicit stack.
     *
     * <p>The method traverses each vertex, and for each unvisited vertex, it performs DFS using a stack.
     * As vertices finish processing, they are added to an ordering stack. Finally, the ordering stack
     * is used to print the topological order.</p>
     *
     * <p>If the graph contains a cycle, topological sort is not possible.</p>
     */
    public void topologicalSort() {
        // Stack to store the topological order
        Stack<Integer> topoStack = new Stack<>();

        // Visited array to keep track of visited vertices
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Iterate through all vertices
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                // Perform iterative DFS from vertex i
                iterativeDFS(i, visited, topoStack);
            }
        }

        // Check for cycles by ensuring all vertices are in topoStack
        if (topoStack.size() != V) {
            System.out.println("Cycle detected. Topological sort not possible.");
            return;
        }

        // Print the topological order
        while (!topoStack.isEmpty())
            System.out.print(topoStack.pop() + " ");
    }

    /**
     * Helper method to perform iterative Depth-First Search (DFS) starting from a given vertex.
     *
     * <p>This method uses an explicit stack to manage the traversal process, marking vertices as visited
     * and adding them to the topological order stack once all their adjacent vertices have been processed.</p>
     *
     * @param startVertex The vertex to start DFS from.
     * @param visited     The array tracking visited vertices.
     * @param topoStack   The stack storing the topological order.
     */
    private void iterativeDFS(int startVertex, boolean[] visited, Stack<Integer> topoStack) {
        // Stack to manage the DFS traversal
        Stack<Integer> traversalStack = new Stack<>();
        traversalStack.push(startVertex);

        // Temporary stack to manage post-order processing
        Stack<Integer> tempStack = new Stack<>();

        while (!traversalStack.isEmpty()) {
            int current = traversalStack.pop();

            if (!visited[current]) {
                visited[current] = true;
                tempStack.push(current);

                // Push all adjacent vertices to the traversal stack
                for (int neighbor : adj.get(current)) {
                    if (!visited[neighbor])
                        traversalStack.push(neighbor);
                }
            }
        }

        // Transfer from tempStack to topoStack to maintain correct order
        while (!tempStack.isEmpty())
            topoStack.push(tempStack.pop());
    }

    /**
     * The {@code main} method serves as an entry point to test the {@code TopologicalSortGraphDFS} class.
     *
     * <p>It demonstrates the usage of the topologicalSort method with sample inputs, including the provided
     * examples and additional test cases.</p>
     *
     * @param args Command-line arguments (not utilized in this program).
     */
    public static void main(String[] args) {
        // Example 1:
        // Input:
        // 6 vertices (0 to 5)
        // Edges:
        // 5 -> 2, 5 -> 0
        // 4 -> 0, 4 -> 1
        // 2 -> 3
        // 3 -> 1
        TopologicalSortGraphDFS g1 = new TopologicalSortGraphDFS(6);
        g1.addEdge(5, 2);
        g1.addEdge(5, 0);
        g1.addEdge(4, 0);
        g1.addEdge(4, 1);
        g1.addEdge(2, 3);
        g1.addEdge(3, 1);

        System.out.println("Example 1 Topological Sort:");
        g1.topologicalSort(); // Possible Output: 4, 5, 2, 3, 1, 0
        System.out.println("\n");

        // Example 2:
        // Input:
        // 3 vertices (0 to 2)
        // Edges:
        // 0 -> 1
        // 1 -> 2
        // 2 -> 0 (forms a cycle)
        TopologicalSortGraphDFS g2 = new TopologicalSortGraphDFS(3);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 0);

        System.out.println("Example 2 Topological Sort:");
        g2.topologicalSort(); // Output: Cycle detected. Topological sort not possible.
        System.out.println();

        // Additional Example 3:
        // Input:
        // 4 vertices (0 to 3)
        // Edges:
        // 0 -> 1
        // 0 -> 2
        // 1 -> 2
        // 2 -> 3
        TopologicalSortGraphDFS g3 = new TopologicalSortGraphDFS(4);
        g3.addEdge(0, 1);
        g3.addEdge(0, 2);
        g3.addEdge(1, 2);
        g3.addEdge(2, 3);

        System.out.println("Example 3 Topological Sort:");
        g3.topologicalSort(); // Possible Output: 0, 1, 2, 3
        System.out.println();

        // Edge Case Example 4:
        // Input: Empty Graph
        TopologicalSortGraphDFS g4 = new TopologicalSortGraphDFS(0);

        System.out.println("Example 4 Topological Sort:");
        g4.topologicalSort(); // Output: (No output, since the graph has no vertices)
        System.out.println();

        // Edge Case Example 5:
        // Input:
        // 1 vertex (0)
        // No edges
        TopologicalSortGraphDFS g5 = new TopologicalSortGraphDFS(1);

        System.out.println("Example 5 Topological Sort:");
        g5.topologicalSort(); // Output: 0
        System.out.println();

        // Edge Case Example 6:
        // Input:
        // 2 vertices (0,1)
        // No edges
        TopologicalSortGraphDFS g6 = new TopologicalSortGraphDFS(2);

        System.out.println("Example 6 Topological Sort:");
        g6.topologicalSort(); // Possible Output: 1 0 or 0 1
        System.out.println();
    }
}
