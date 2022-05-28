//Given a directed graph, check whether the graph contains a cycle or not. 
//Your function should return true if the given graph contains at least one 
//cycle, else return false.

//example:
// Input: n = 4, e = 6
// 0 -> 1, 0 -> 2, 1 -> 2, 2 -> 0, 2 -> 3, 3 -> 3
// Output: Yes

import java.util.*;

public class Cycle {
    private final int V;
    private final List<List<Integer>> adj;
     
        public Cycle(int V)
        {
            this.V = V;
            adj = new ArrayList<>(V);
             
            for (int i = 0; i < V; i++)
                adj.add(new LinkedList<>());
        }

    private boolean isCyclicUtil(int i, boolean[] visited,
                                      boolean[] recStack)
    {
         
        // Mark the current node as visited and
        // part of recursion stack
        if (recStack[i])
            return true;
 
        if (visited[i])
            return false;
             
        visited[i] = true;
 
        recStack[i] = true;
        List<Integer> children = adj.get(i);
         
        for (Integer c: children)
            if (isCyclicUtil(c, visited, recStack))
                return true;
                 
        recStack[i] = false;
 
        return false;
    }
 
    private void addEdge(int source, int dest) {
        adj.get(source).add(dest);
    }
 
    // Returns true if the graph contains a
    // cycle, else false.
    private boolean isCyclic()
    {
         
        // Mark all the vertices as not visited and
        // not part of recursion stack
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];
         
         
        // Call the recursive helper function to
        // detect cycle in different DFS trees
        for (int i = 0; i < V; i++)
            if (isCyclicUtil(i, visited, recStack))
                return true;
 
        return false;
    }
 
    // Driver code
    public static void main(String[] args)
    {
        Cycle graph = new Cycle(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);
         
        if(graph.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't "
                                    + "contain cycle");
    }
}