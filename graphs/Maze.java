// There is a ball in a maze with empty spaces and walls. The ball can go through 
// empty spaces by rolling up, down, left or right, but it won't stop rolling 
// until hitting a wall. When the ball stops, it could choose the next direction.

// Given the ball's start position, the destination and the maze, determine 
// whether the ball could stop at the destination.

// The maze is represented by a binary 2D array. 1 means the wall and 0 means 
// the empty space. You may assume that the borders of the maze are all walls. 
// The start and destination coordinates are represented by row and column indexes.

//example:
//Input 1: a maze represented by a 2D array
// 0 0 1 0 0
// 0 0 0 0 0
// 0 0 0 1 0
// 1 1 0 1 1
// 0 0 0 0 0

// Input 2: start coordinate (rowStart, colStart) = (0, 4)
// Input 3: destination coordinate (rowDest, colDest) = (4, 4)

// Output: true


public class Maze {
    public static boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return reach(maze, start[0], start[1] , destination, visited);
    }
    private static boolean reach(int[][] maze, int i, int j, int[] dest, boolean[][] visited) {
        if (i == dest[0] && j == dest[1]) {
            return true;
        }
        if (visited[i][j] == true) {
            return false;
        }
        visited[i][j] = true;
        int[][] dirs = new int[4][2];
        dirs[0] = new int[]{0, 1};
        dirs[1] = new int[]{1, 0};
        dirs[2] = new int[]{0, -1};
        dirs[3] = new int[]{-1, 0};
        for (int k = 0; k < dirs.length; k ++) {
            int x = i, y = j;
            while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
                x = x + dirs[k][0];
                y = y + dirs[k][1];
            }
            x = x - dirs[k][0];
            y = y - dirs[k][1];
            if (!visited[x][y] && reach(maze, x, y, dest, visited)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        int arr[][] = { {0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1},
            {0,0, 0, 0, 0}
        };

        int start[] = {0, 4};
        int dest1[] = {4, 4};
        int dest2[] = {3, 2};
        
        System.out.println(hasPath(arr, start, dest1));
        System.out.println(hasPath(arr, start, dest2));


    }
}

