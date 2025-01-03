import java.util.*;

/**
 * The {@code InformAllEmployees} class provides a method to determine the minimum number of minutes
 * needed to inform all employees in a company about an urgent piece of news.
 *
 * <p>The company hierarchy is represented as a tree with each employee having a unique ID. The head
 * of the company initiates the information spread, and each employee takes a certain amount of
 * time to inform their direct subordinates.</p>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * InformAllEmployees solver = new InformAllEmployees();
 * int n = 6;
 * int headID = 2;
 * int[] manager = {2, 2, -1, 2, 2, 2};
 * int[] informTime = {0, 0, 1, 0, 0, 0};
 * int totalTime = solver.numOfMinutes(n, headID, manager, informTime);
 * System.out.println(totalTime); // Output: 1
 * }</pre>
 *
 * @author 
 */
public class InformAllEmployees {
    
    /**
     * Calculates the minimum number of minutes needed to inform all employees about an urgent news.
     *
     * @param n           The total number of employees in the company.
     * @param headID      The unique ID of the head of the company.
     * @param manager     An array where manager[i] is the direct manager of the i-th employee.
     * @param informTime  An array where informTime[i] is the time needed by the i-th employee to inform all their direct subordinates.
     * @return The minimum number of minutes needed to inform all employees, or 0 if no employees need to be informed.
     */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        // Edge case: Only the head exists
        if (n == 1) return 0;
        
        // Build the adjacency list representing the tree
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            if (manager[i] != -1) {
                adj.get(manager[i]).add(i);
            }
        }
        
        // Initialize BFS
        Queue<EmployeeInfo> queue = new LinkedList<>();
        queue.offer(new EmployeeInfo(headID, 0));
        
        int totalTime = 0;
        
        while (!queue.isEmpty()) {
            EmployeeInfo current = queue.poll();
            int currentID = current.id;
            int currentTime = current.time;
            
            // Update the total time if current path is longer
            totalTime = Math.max(totalTime, currentTime);
            
            // Iterate through all direct subordinates
            for (int subordinate : adj.get(currentID)) {
                // The time to inform the subordinate is currentTime + informTime[currentID]
                queue.offer(new EmployeeInfo(subordinate, currentTime + informTime[currentID]));
            }
        }
        
        return totalTime;
    }
    
    /**
     * A helper class to store employee information during BFS traversal.
     */
    private static class EmployeeInfo {
        int id;     // Employee ID
        int time;   // Time taken to reach this employee
        
        EmployeeInfo(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }
    
    /**
     * The {@code main} method serves as an entry point to test the {@code InformAllEmployees} class.
     *
     * <p>It demonstrates the usage of the numOfMinutes method with sample inputs.</p>
     *
     * @param args Command-line arguments (not utilized in this program).
     */
    public static void main(String[] args) {
        InformAllEmployees solver = new InformAllEmployees();
        
        // Example 1
        int n1 = 1;
        int headID1 = 0;
        int[] manager1 = {-1};
        int[] informTime1 = {0};
        int totalTime1 = solver.numOfMinutes(n1, headID1, manager1, informTime1);
        System.out.println("Example 1 Output: " + totalTime1); // Expected Output: 0
        
        // Example 2
        int n2 = 6;
        int headID2 = 2;
        int[] manager2 = {2, 2, -1, 2, 2, 2};
        int[] informTime2 = {0, 0, 1, 0, 0, 0};
        int totalTime2 = solver.numOfMinutes(n2, headID2, manager2, informTime2);
        System.out.println("Example 2 Output: " + totalTime2); // Expected Output: 1
        
        // Additional Example 3
        int n3 = 4;
        int headID3 = 0;
        int[] manager3 = {-1, 0, 0, 1};
        int[] informTime3 = {1, 2, 3, 4};
        int totalTime3 = solver.numOfMinutes(n3, headID3, manager3, informTime3);
        System.out.println("Example 3 Output: " + totalTime3); // Expected Output: 7 (0->1->3: 1+2+4=7)
    }
}
