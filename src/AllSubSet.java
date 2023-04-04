import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllSubSet {
    public List<String> subSets(String set) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Set<String> visited = new HashSet<>();
        dfs(result, sb, set, 0, visited);
        return result;
    }

    private void dfs(List<String> result, StringBuilder sb, String set, int index, Set<String> visited) {
        if (index == set.length()) {
            if (visited.add(sb.toString())) {
                result.add(sb.toString());
            }
            return;
        }
        sb.append(set.charAt(index));
        dfs(result,sb, set, index + 1, visited);
        sb.deleteCharAt(sb.length() - 1);
        dfs(result,sb, set, index + 1, visited);
    }

    public static void main(String[] args) {
        AllSubSet test = new AllSubSet();
        List<String> result = test.subSets("abab");
        for (String str : result) {
            System.out.println(str);
        }
    }
}
