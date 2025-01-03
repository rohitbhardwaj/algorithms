import java.util.*;

/**
 * The {@code CourseSchedule} class provides functionality to determine if it's possible
 * to finish all courses given the prerequisites.
 *
 * <p>The problem is modeled using a directed graph where each course is a node, and
 * prerequisites are represented as directed edges. The class employs Kahn's Algorithm
 * (BFS-based Topological Sort) to detect cycles in the graph. If a cycle is detected,
 * it implies that it's impossible to complete all courses.</p>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * CourseSchedule scheduler = new CourseSchedule();
 * int numCourses1 = 2;
 * int[][] prerequisites1 = {{1,0}};
 * boolean canFinish1 = scheduler.canFinish(numCourses1, prerequisites1);
 * System.out.println(canFinish1); // Output: true
 *
 * int numCourses2 = 2;
 * int[][] prerequisites2 = {{1,0}, {0,1}};
 * boolean canFinish2 = scheduler.canFinish(numCourses2, prerequisites2);
 * System.out.println(canFinish2); // Output: false
 * }</pre>
 *
 * <p><strong>Time Complexity:</strong> O(V + E)</p>
 * <p><strong>Space Complexity:</strong> O(V + E)</p>
 * Time and Space Complexity Analysis
    Time Complexity
    Graph Construction:

    Building the adjacency list takes O(E) time, where E is the number of prerequisite pairs.
    Initializing the in-degree array takes O(V) time, where V is the number of courses.
    BFS Traversal:

    Each course is enqueued and dequeued at most once, resulting in O(V) time.
    For each course dequeued, we iterate through its neighbors (dependent courses). Across all courses, this sums up to O(E) time.
    Total Time Complexity: O(V + E)

    Space Complexity
    Adjacency List:

    Stores all edges, resulting in O(E) space.
    In-degree Array:

    Stores the in-degree for each course, resulting in O(V) space.
    Queue:

    In the worst case, all courses might be enqueued, resulting in O(V) space.
    Total Space Complexity: O(V + E)
 *
 * @author 
 */
public class CourseSchedule {
    
    /**
     * Determines if it's possible to finish all courses given the prerequisites.
     *
     * <p>This method models the courses and their prerequisites as a directed graph and
     * performs a BFS-based Topological Sort (Kahn's Algorithm) to detect cycles. If a cycle
     * exists, it returns {@code false}; otherwise, {@code true}.</p>
     *
     * @param numCourses    The total number of courses, labeled from 0 to numCourses - 1.
     * @param prerequisites An array where prerequisites[i] = [ai, bi] indicates that
     *                      you must take course bi before course ai.
     * @return {@code true} if you can finish all courses, otherwise {@code false}.
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Initialize adjacency list for the graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Array to hold the in-degree (number of prerequisites) for each course
        int[] inDegree = new int[numCourses];
        
        // Build the graph and populate in-degree array
        for (int[] pair : prerequisites) {
            int course = pair[0];
            int prereq = pair[1];
            adj.get(prereq).add(course);
            inDegree[course]++;
        }
        
        // Queue to manage courses with no prerequisites
        Queue<Integer> queue = new LinkedList<>();
        
        // Enqueue all courses with in-degree 0
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // Counter for the number of courses that have been processed
        int count = 0;
        
        // Perform BFS
        while (!queue.isEmpty()) {
            int current = queue.poll();
            count++;
            
            // Iterate through all courses dependent on the current course
            for (int neighbor : adj.get(current)) {
                inDegree[neighbor]--; // Remove the prerequisite
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor); // If no more prerequisites, enqueue
                }
            }
        }
        
        // If all courses have been processed, it's possible to finish
        return count == numCourses;
    }
    
    /**
     * The {@code main} method serves as an entry point to test the {@code CourseSchedule} class.
     *
     * <p>It demonstrates the usage of the canFinish method with sample inputs.</p>
     *
     * @param args Command-line arguments (not utilized in this program).
     */
    public static void main(String[] args) {
        CourseSchedule scheduler = new CourseSchedule();
        
        // Example 1:
        // Input: numCourses = 2, prerequisites = [[1,0]]
        // Output: true
        int numCourses1 = 2;
        int[][] prerequisites1 = { {1, 0} };
        boolean canFinish1 = scheduler.canFinish(numCourses1, prerequisites1);
        System.out.println("Example 1 Output: " + canFinish1); // Expected Output: true
        
        // Example 2:
        // Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
        // Output: false
        int numCourses2 = 2;
        int[][] prerequisites2 = { {1, 0}, {0, 1} };
        boolean canFinish2 = scheduler.canFinish(numCourses2, prerequisites2);
        System.out.println("Example 2 Output: " + canFinish2); // Expected Output: false
        
        // Additional Example 3:
        // Input: numCourses = 4, prerequisites = [[1,0],[2,1],[3,2]]
        // Output: true
        int numCourses3 = 4;
        int[][] prerequisites3 = { {1, 0}, {2, 1}, {3, 2} };
        boolean canFinish3 = scheduler.canFinish(numCourses3, prerequisites3);
        System.out.println("Example 3 Output: " + canFinish3); // Expected Output: true
        
        // Additional Example 4:
        // Input: numCourses = 3, prerequisites = [[1,0],[2,0],[2,1]]
        // Output: true
        int numCourses4 = 3;
        int[][] prerequisites4 = { {1, 0}, {2, 0}, {2, 1} };
        boolean canFinish4 = scheduler.canFinish(numCourses4, prerequisites4);
        System.out.println("Example 4 Output: " + canFinish4); // Expected Output: true
        
        // Additional Example 5:
        // Input: numCourses = 5, prerequisites = [[1,0],[2,1],[3,2],[1,3],[4,1]]
        // Output: false (Cycle exists: 1 -> 3 -> 2 -> 1)
        int numCourses5 = 5;
        int[][] prerequisites5 = { {1, 0}, {2, 1}, {3, 2}, {1, 3}, {4, 1} };
        boolean canFinish5 = scheduler.canFinish(numCourses5, prerequisites5);
        System.out.println("Example 5 Output: " + canFinish5); // Expected Output: false
    }
}
