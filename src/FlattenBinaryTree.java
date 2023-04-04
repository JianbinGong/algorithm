public class FlattenBinaryTree {
    static class TreeNode {
        int key;
        TreeNode left;
        TreeNode right;
        public TreeNode (int key) {
            this.key = key;
        }
    }
    public TreeNode flatten(TreeNode root) {
        if (root == null) return null;
        TreeNode[] prev = new TreeNode[1];
        return flatten(root, prev);
    }

    private TreeNode flatten(TreeNode root, TreeNode[] prev) {
        if (root == null) return root;
        flatten(root.right, prev);
        flatten(root.left, prev);
        root.right = prev[0];
        root.left = null;
        prev[0] = root;
        return root;
    }
}
