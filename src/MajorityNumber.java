import java.util.ArrayList;
import java.util.List;

public class MajorityNumber {
    static class State {
        int candidate;
        int count;
    }
    // can be zero
    public static List<Integer> majority(int[] array, int k) {
        List<Integer> result = new ArrayList<>();
        if (array.length == 0) return result;
        State[] candidates = new State[k - 1];
        for (int i = 0; i < candidates.length; i++) {
            candidates[i] = new State();
        }
        for (int i = 0; i < array.length; i++) {
            boolean flag = false;
            for (int j = 0; j < candidates.length; j++) {
                if (candidates[j].candidate == array[i]) {
                    candidates[j].count++;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                for (int j = 0; j < candidates.length; j++) {
                    if (candidates[j].count == 0) {
                        candidates[j].candidate = array[i];
                        candidates[j].count = 1;
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                for (int j = 0; j < candidates.length; j++) {
                    candidates[j].count--;
                }
            }
        }
        int[] count = new int[candidates.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < candidates.length; j++) {
                if (candidates[j].candidate == array[i]) {
                    count[j]++;
                }
            }
        }
        for (int i = 0; i < candidates.length; i++) {
            if (count[i] * k > array.length) {
                result.add(candidates[i].candidate);
            }
        }
        return result;
    }

    public static void main (String[] args) {
        int[] input = new int[]{1,2};
        majority(input, 3);
    }
}
