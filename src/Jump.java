public class Jump {
    /**
     * @param a: A list of integers
     * @return: An integer
     */
    public int jump(int[] a) {
        // positive integers
        if (a.length == 0) return 0;
        int[] canJump = new int[a.length];
        canJump[0] = 0;
        for (int i = 1; i < a.length; i++) {
            canJump[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (a[j] + j >= i) {
                    canJump[i] = Math.min(canJump[i], canJump[j] + 1);
                }
            }
        }
        return canJump[a.length - 1];
    }

    public static void main(String[] args) {
        Jump test = new Jump();
        int[] data = {2, 3};
        System.out.println(test.jump(data));
    }
}
