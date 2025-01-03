import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The {@code TopologicalSortGraph} class provides functionality to perform a topological sort on a Directed Acyclic Graph (DAG).
 *
 * <p>Topological sorting of a graph is a linear ordering of its vertices such that for every directed edge \( uv \),
 * vertex \( u \) comes before vertex \( v \) in the ordering.</p>
 *
 * <p>This class implements Kahn's Algorithm (BFS Approach) to achieve topological sorting.</p>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * TopologicalSortGraph graph = new TopologicalSortGraph(6);
 * graph.addEdge(5, 2);
 * graph.addEdge(5, 0);
 * graph.addEdge(4, 0);
 * graph.addEdge(4, 1);
 * graph.addEdge(2, 3);
 * graph.addEdge(3, 1);
 * 
 * System.out.println("Following is a Topological Sort of the given graph:");
 * graph.topologicalSort(); // Possible Output: 4, 5, 0, 1, 2, 3
 * }</pre>
 *
 * <p><strong>Time Complexity:</strong> O(V + E), where V is the number of vertices and E is the number of edges.</p>
 * <p><strong>Space Complexity:</strong> O(V + E), due to storage of the adjacency list and in-degree counts.</p>
 *
 * Time and Space Complexity Analysis
    Understanding the efficiency of the implemented algorithms is crucial for optimizing performance, especially with large input sizes.

    Kahn's Algorithm (BFS Approach)
    Time Complexity: O(V + E)

    Explanation:
    Computing in-degrees takes O(E) time as it involves traversing all edges.
    Initializing the queue with vertices having in-degree 0 takes O(V) time.
    Processing each vertex and its adjacent vertices takes O(V + E) time in total.
    Thus, the overall time complexity is linear relative to the size of the graph.
    Space Complexity: O(V + E)

    Explanation:
    The adjacency list consumes O(V + E) space.
    The in-degree array consumes O(V) space.
    The queue can hold up to O(V) vertices in the worst case.
    The topological order list consumes O(V) space.
    Hence, the overall space complexity is linear.
    Summary
    Kahn's Algorithm efficiently performs topological sorting with linear time and space complexities.
    It is suitable for large graphs within the given constraints.
 * @author 
 */
public class TopologicalSortGraph {
    // Number of vertices
    private int V;

    // Adjacency List representation
    private List<List<Integer>> adj;

    /**
     * Constructor to initialize the graph with a specified number of vertices.
     *
     * @param v The number of vertices in the graph.
     */
    public TopologicalSortGraph(int v) {
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
     * Performs a topological sort of the graph using Kahn's Algorithm (BFS Approach).
     *
     * <p>The method computes the in-degree of each vertex and enqueues vertices with zero in-degree.
     * It then processes each vertex, updating the in-degrees of adjacent vertices and enqueuing
     * them if their in-degree becomes zero. The resulting order is a valid topological sort.</p>
     *
     * <p>If the graph contains a cycle, the method will detect it and indicate that a topological
     * sort is not possible.</p>
     */
    public void topologicalSort() {
        // Initialize in-degree array
        int[] inDegree = new int[V];

        // Compute in-degree of each vertex
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                inDegree[v]++;
            }
        }

        // Initialize queue and enqueue all vertices with in-degree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0)
                queue.offer(i);
        }

        // List to store the topological order
        List<Integer> topoOrder = new ArrayList<>();

        // Process until the queue is empty
        while (!queue.isEmpty()) {
            int u = queue.poll();
            topoOrder.add(u);

            // Iterate through all the neighbors of dequeued vertex u
            for (int v : adj.get(u)) {
                // Decrement in-degree by 1
                inDegree[v]--;

                // If in-degree becomes 0, enqueue it
                if (inDegree[v] == 0)
                    queue.offer(v);
            }
        }

        // Check if topological sort is possible (i.e., graph has no cycles)
        if (topoOrder.size() != V) {
            System.out.println("Cycle detected. Topological sort not possible.");
            return;
        }

        // Print the topological order
        for (int vertex : topoOrder)
            System.out.print(vertex + " ");
    }

    /**
     * The {@code main} method serves as an entry point to test the {@code TopologicalSortGraph} class.
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
        TopologicalSortGraph g1 = new TopologicalSortGraph(6);
        g1.addEdge(5, 2);
        g1.addEdge(5, 0);
        g1.addEdge(4, 0);
        g1.addEdge(4, 1);
        g1.addEdge(2, 3);
        g1.addEdge(3, 1);

        System.out.println("Example 1 Topological Sort:");
        g1.topologicalSort(); // Possible Output: 4, 5, 0, 1, 2, 3
        System.out.println("\n");

        // Example 2:
        // Input:
        // 3 vertices (0 to 2)
        // Edges:
        // 0 -> 1
        // 1 -> 2
        // 2 -> 0 (forms a cycle)
        TopologicalSortGraph g2 = new TopologicalSortGraph(3);
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
        TopologicalSortGraph g3 = new TopologicalSortGraph(4);
        g3.addEdge(0, 1);
        g3.addEdge(0, 2);
        g3.addEdge(1, 2);
        g3.addEdge(2, 3);

        System.out.println("Example 3 Topological Sort:");
        g3.topologicalSort(); // Possible Output: 0, 1, 2, 3
        System.out.println();
    }
}
