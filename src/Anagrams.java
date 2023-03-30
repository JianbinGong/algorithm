import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anagrams {
    public List<Integer> allAnagrams(String sh, String lo) {
        List<Integer> result = new ArrayList<>();
        Map<Character, Integer> map = getMap(sh);
        int match = 0;
        for (int i = 0; i < lo.length(); i++) {
            Integer count = map.get(lo.charAt(i));
            if (count != null) {
                map.put(lo.charAt(i), count - 1);
                if (count == 1) {
                    match++;
                }
            }
            if (i >= sh.length()) {
                count = map.get(lo.charAt(i - sh.length()));
                if (count != null) {
                    if (count == 0) {
                        match--;
                    }
                    map.put(lo.charAt(i - sh.length()), count + 1);
                }
            }
            if (match == map.size()) {
                result.add(i - sh.length() + 1);
            }
        }
        return result;
    }

    private Map<Character, Integer> getMap(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (map.get(str.charAt(i)) == null) {
                map.put(str.charAt(i), 1);
            } else {
                map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
            }
        }
        return map;
    }
}
