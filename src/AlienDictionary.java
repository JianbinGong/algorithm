import java.util.*;

public class AlienDictionary {
    public String alienOrder(String[] words) {
        // build a graph:
        int[] ingress = new int[26];
        Map<Character, Set<Character>> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String curWord = words[i];
            boolean findFirstDiff = false;
            for (int j = 0; j < curWord.length(); j++) {
                char curChar = curWord.charAt(j);
                if (!map.containsKey(curChar)) {
                    map.put(curChar, new HashSet<Character>());
                }
                if (!findFirstDiff && i < words.length - 1 && j < words[i + 1].length()) {
                    char nextChar = words[i + 1].charAt(j);
                    findFirstDiff = nextChar != curChar;
                    if (findFirstDiff && map.get(curChar).add(nextChar)) {
                        ingress[nextChar - 'a']++;
                    }
                }
            }
        }
        Queue<Character> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        Set<Character> visited = new HashSet<>();
        for (Map.Entry<Character, Set<Character>> e : map.entrySet()) {
            if (ingress[e.getKey() - 'a'] == 0) {
                queue.offer(e.getKey());
                sb.append(e.getKey());
                visited.add(e.getKey());
            }
        }
        while (!queue.isEmpty()) {
            Character c = queue.poll();
            for (Character nei : map.get(c)) {
                if (visited.contains(nei)) return "";
                ingress[nei - 'a']--;
                if (ingress[nei - 'a'] == 0) {
                    queue.offer(nei);
                    sb.append(nei);
                    visited.add(nei);
                }
            }
        }
        return sb.length() == map.size() ? sb.toString() : "";
    }
}
