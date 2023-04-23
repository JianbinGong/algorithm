
//Given a target integer T and an integer array A sorted in
//ascending order, find the index i such that A[i] == T
// or return -1 if there is no such index.
// int: target, the num we are looking for
// int[]: array, sorted, ascending, range, the array we are searching

// General case: Sorted, Ascending, Valid
// Corner case: null, zero, 1, two

// output: the index of the target, or -1 if not found
// multiple solution: any one

//Solution:
// 1 2 3 4 7 10 30
// left          right
//        mid

public class BinarySearch {
    public int binarySearch(int[] array, int target) {
        //corner case
        if (array == null || array.length == 0) return -1;

        int left = 0;
        int right = array.length - 1;
        while(left <= right) {
            int mid = left + (right - left)/2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
