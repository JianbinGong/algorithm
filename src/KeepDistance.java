public class KeepDistance {
    public int[] keepDistance(int k) {
        int[] array = new int[2 * k];
        for (int i = 0; i < k; i++) {
            array[2 * i] = i + 1;
            array[2 * i + 1] = i + 1;
        }
        boolean[] used = new boolean[k + 1];
        return helper(array, 0, used) ? array : null;
    }

    private boolean helper(int[] array, int index, boolean[] used) {
        if (index == array.length) return true;
        for (int i = index; i < array.length; i++) {
            int cur = array[i];
            if (!used[cur] || (index > cur && array[index - cur - 1] == cur)) {
                swap(array, i, index);
                used[cur] = !used[cur];
                if (helper(array, index + 1, used)) return true;
                swap(array, i, index);
                used[cur] = !used[cur];
            }
        }
        return false;
    }

    private void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
