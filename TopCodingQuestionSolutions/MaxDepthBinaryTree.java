//Maximum Depth of a Binary Tree
//Given the root of a binary tree, return its maximum depth.
// A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
// example: root = [3,9,20,null,null,15,7] --> 3
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

public class MaxDepthBinaryTree {
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String args[]) {

        TreeNode cc1 = new TreeNode(15);
        TreeNode cc2 = new TreeNode(7);
        TreeNode c1 = new TreeNode(9);
        TreeNode c2 = new TreeNode(20, cc1, cc2);
        TreeNode t = new TreeNode(3, c1, c2);

        System.out.println(maxDepth(t));

    }

}