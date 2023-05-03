import java.util.ArrayList;
import java.util.List;

public class AllSubSets {
    // “abcdefg"
    //    a      ""
    // ab.  a.  b   ""
    // Time complexity: O(2^n * n)
    // Space complexity: O(n)
    public List<String> subSets(String set) {
        if (set == null) {
            return new ArrayList<String>();
        }
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(set, 0, sb, result);
        return result;
    }

    private void helper(String set, int index ,StringBuilder sb, List<String> result) {
        if (index == set.length()) {
            result.add(sb.toString());
            return;
        }
        sb.append(set.charAt(index));
        helper(set, index + 1, sb, result);
        sb.deleteCharAt(sb.length() - 1);
        helper(set, index + 1, sb, result);
    }
}
