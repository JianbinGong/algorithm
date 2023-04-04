import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ValidPermutationsOfParentheses {
    private char[] PS = {'(', ')', '<', '>', '{', '}'};
    public List<String> validParentheses(int l, int m, int n) {
        int targetLen = 2 * l + 2 * m + 2 * n;
        int[] remain = {l, l, m, m, n, n};
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        helper(sb, result, stack, remain, targetLen);
        return result;
    }

    private void helper(StringBuilder sb, List<String> result, Deque<Character> stack, int[] remain, int targetLen) {
        if (sb.length() == targetLen) {
            result.add(sb.toString());
            return;
        }

        for (int i = 0; i < remain.length; i++) {
            if (i % 2 == 0) {
                if (remain[i] > 0) {
                    sb.append(PS[i]);
                    stack.offerFirst(PS[i]);
                    remain[i]--;
                    helper(sb, result, stack, remain, targetLen);
                    sb.deleteCharAt(sb.length() - 1);
                    stack.pollFirst();
                    remain[i]++;
                }
            } else {
                if (!stack.isEmpty() && stack.peekFirst() == PS[i - 1]) {
                    sb.append(PS[i]);
                    stack.pollFirst();
                    remain[i]--;
                    helper(sb, result, stack, remain, targetLen);
                    sb.deleteCharAt(sb.length() - 1);
                    stack.offerFirst(PS[i - 1]);
                    remain[i]++;
                }
            }
        }
    }
}
