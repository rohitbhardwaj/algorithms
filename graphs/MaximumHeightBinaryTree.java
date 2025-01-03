import java.util.*;

/**
 * The {@code MaximumHeightBinaryTree} class provides functionality to determine
 * the maximum height (or depth) of a binary tree.
 *
 * <p>The height of a binary tree is defined as the number of edges on the longest
 * path from the root node to the deepest leaf node.</p>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * // Constructing the binary tree for Example 1:
 * //     1
 * //    / \
 * //   2   3
 * //  /
 * // 4
 *
 * TreeNode root = new TreeNode(1);
 * root.left = new TreeNode(2);
 * root.right = new TreeNode(3);
 * root.left.left = new TreeNode(4);
 *
 * MaximumHeightBinaryTree solver = new MaximumHeightBinaryTree();
 * int height = solver.maxHeight(root);
 * System.out.println("Height of the tree: " + height); // Output: 2
 * }</pre>
 *
 * Time and Space Complexity Analysis
    Time Complexity
    Recursive Calls: Each node in the binary tree is visited exactly once.

    Operations per Node: For each node, the algorithm performs constant-time operations (calculating max).

    Total Time Complexity: O(n), where n is the number of nodes in the binary tree.

    Space Complexity
    Recursion Stack: The maximum depth of the recursion stack is equal to the height of the tree.

    Best Case (Balanced Tree): O(log n)
    Worst Case (Skewed Tree): O(n)
    Auxiliary Space: O(1), as no additional data structures are used besides the recursion stack.

    Total Space Complexity: O(h), where h is the height of the tree. In the worst case, this becomes O(n).
 * 
 * @author 
 */
public class MaximumHeightBinaryTree {
    
    /**
     * The {@code TreeNode} class represents a node in the binary tree.
     *
     * <p>Each node contains an integer value and references to its left and right child nodes.</p>
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        /**
         * Constructs a new {@code TreeNode} with the specified value.
         *
         * @param val The integer value to be stored in the node.
         */
        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Calculates the maximum height of the binary tree.
     *
     * <p>The height is defined as the number of edges on the longest path from the root node
     * to the deepest leaf node.</p>
     *
     * @param root The root node of the binary tree.
     * @return The maximum height of the binary tree. Returns -1 if the tree is empty.
     */
    public int maxHeight(TreeNode root) {
        // Base case: If the current node is null, return -1 (no edges)
        if (root == null) {
            return -1;
        }

        // Recursively find the height of the left subtree
        int leftHeight = maxHeight(root.left);

        // Recursively find the height of the right subtree
        int rightHeight = maxHeight(root.right);

        // The height of the current node is 1 + maximum of left and right subtree heights
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * The {@code main} method serves as an entry point to test the {@code MaximumHeightBinaryTree} class.
     *
     * <p>It demonstrates the construction of binary trees based on the provided examples and
     * calculates their maximum heights.</p>
     *
     * @param args Command-line arguments (not utilized in this program).
     */
    public static void main(String[] args) {
        MaximumHeightBinaryTree solver = new MaximumHeightBinaryTree();

        // Example 1:
        // The height of the below binary tree is 2.
        //     1
        //    / \
        //   2   3
        //  /
        // 4

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);

        int height1 = solver.maxHeight(root1);
        System.out.println("Example 1 Output: " + height1); // Expected Output: 2

        // Example 2:
        // The height of the below binary tree is 3.
        //     1
        //    /
        //   2
        //  /
        // 3
        ///
        //4

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        root2.left.left.left = new TreeNode(4);

        int height2 = solver.maxHeight(root2);
        System.out.println("Example 2 Output: " + height2); // Expected Output: 3

        // Additional Example 3:
        // The height of the below binary tree is 1.
        //     5
        //    / \
        //   6   7

        TreeNode root3 = new TreeNode(5);
        root3.left = new TreeNode(6);
        root3.right = new TreeNode(7);

        int height3 = solver.maxHeight(root3);
        System.out.println("Example 3 Output: " + height3); // Expected Output: 1

        // Edge Case Example 4:
        // The height of an empty binary tree is -1.

        TreeNode root4 = null;
        int height4 = solver.maxHeight(root4);
        System.out.println("Example 4 Output: " + height4); // Expected Output: -1
    }
}
