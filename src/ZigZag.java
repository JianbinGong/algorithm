import javax.swing.tree.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ZigZag {
    class TreeNode {
        int key;
        TreeNode left;
        TreeNode right;

        public TreeNode(int key) {
            this.key = key;
        }
    }
    public List<Integer> zigZag(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerFirst(root);
        int level = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            if (level % 2 == 0) {
                while (size > 0) {
                    TreeNode cur = deque.pollFirst();
                    result.add(cur.key);
                    if (cur.right != null) {
                        deque.offerLast(cur.right);
                    }
                    if (cur.left != null) {
                        deque.offerLast(cur.left);
                    }
                    size--;
                }
            } else {
                while (size > 0) {
                    TreeNode cur = deque.pollLast();
                    result.add(cur.key);
                    if (cur.left != null) {
                        deque.offerFirst(cur.left);
                    }
                    if (cur.right != null) {
                        deque.offerFirst(cur.right);
                    }
                    size--;
                }
            }
            level++;
        }
        return result;
    }
}
