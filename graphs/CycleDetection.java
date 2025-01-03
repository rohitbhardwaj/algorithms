import java.util.*;

/**
 * The {@code CycleDetection} class provides functionality to detect cycles in a directed graph.
 *
 * <p>It uses Depth-First Search (DFS) traversal along with a recursion stack to identify cycles.
 * The class allows adding edges to the graph and checking for the presence of cycles.</p>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * // Initialize the graph with 4 vertices
 * CycleDetection graph = new CycleDetection(4);
 * graph.addEdge(0, 1);
 * graph.addEdge(0, 2);
 * graph.addEdge(1, 2);
 * graph.addEdge(2, 0);
 * graph.addEdge(2, 3);
 * graph.addEdge(3, 3);
 *
 * if (graph.isCyclic()) {
 *     System.out.println("Graph contains cycle");
 * } else {
 *     System.out.println("Graph doesn't contain cycle");
 * }
 * }</pre>
 *
 * <p>Output:</p>
 * <pre>
 * Graph contains cycle
 * </pre>
 *
 * @author 
 */
public class CycleDetection {
    private final int V; // Number of vertices
    private final List<List<Integer>> adj; // Adjacency list representation

    /**
     * Constructs a directed graph with the specified number of vertices.
     *
     * @param V The number of vertices in the graph.
     */
    public CycleDetection(int V) {
        this.V = V;
        adj = new ArrayList<>(V);

        // Initialize adjacency list for each vertex
        for (int i = 0; i < V; i++)
            adj.add(new LinkedList<>());
    }

    /**
     * Adds a directed edge from source to destination in the graph.
     *
     * @param source The source vertex.
     * @param dest   The destination vertex.
     */
    public void addEdge(int source, int dest) {
        adj.get(source).add(dest);
    }

    /**
     * Utilizes DFS to detect a cycle in the graph starting from vertex 'i'.
     *
     * @param i         The current vertex.
     * @param visited   Boolean array tracking visited vertices.
     * @param recStack  Boolean array tracking vertices in the current recursion stack.
     * @return {@code true} if a cycle is detected; {@code false} otherwise.
     */
    private boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack) {
        // Mark the current node as visited and part of recursion stack
        if (recStack[i])
            return true;

        if (visited[i])
            return false;

        visited[i] = true;
        recStack[i] = true;

        // Recur for all the vertices adjacent to this vertex
        List<Integer> children = adj.get(i);
        for (Integer c : children)
            if (isCyclicUtil(c, visited, recStack))
                return true;

        // Remove the vertex from recursion stack
        recStack[i] = false;

        return false;
    }

    /**
     * Determines if the graph contains any cycles.
     *
     * @return {@code true} if the graph contains at least one cycle; {@code false} otherwise.
     */
    public boolean isCyclic() {
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];

        // Call the recursive helper function to detect cycle in different DFS trees
        for (int i = 0; i < V; i++)
            if (isCyclicUtil(i, visited, recStack))
                return true;

        return false;
    }

    /**
     * The {@code main} method serves as an entry point to test the {@code CycleDetection} class.
     *
     * <p>It constructs a graph, adds edges, and checks for cycles.</p>
     *
     * @param args Command-line arguments (not utilized in this program).
     */
    public static void main(String[] args) {
        // Example 1:
        // Input: n = 4, e = 6
        // Edges: 0 -> 1, 0 -> 2, 1 -> 2, 2 -> 0, 2 -> 3, 3 -> 3
        // Output: Yes (Graph contains cycles)
        System.out.println("Example 1:");
        CycleDetection graph1 = new CycleDetection(4);
        graph1.addEdge(0, 1);
        graph1.addEdge(0, 2);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 0);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 3);

        if (graph1.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contain cycle");
        // Expected Output: Graph contains cycle

        // Example 2:
        // Input: n = 3, e = 3
        // Edges: 0 -> 1, 1 -> 2, 2 -> 0
        // Output: Yes (Graph contains cycle)
        System.out.println("\nExample 2:");
        CycleDetection graph2 = new CycleDetection(3);
        graph2.addEdge(0, 1);
        graph2.addEdge(1, 2);
        graph2.addEdge(2, 0);

        if (graph2.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contain cycle");
        // Expected Output: Graph contains cycle

        // Example 3:
        // Input: n = 3, e = 2
        // Edges: 0 -> 1, 1 -> 2
        // Output: No (Graph doesn't contain cycle)
        System.out.println("\nExample 3:");
        CycleDetection graph3 = new CycleDetection(3);
        graph3.addEdge(0, 1);
        graph3.addEdge(1, 2);

        if (graph3.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contain cycle");
        // Expected Output: Graph doesn't contain cycle
    }
}
