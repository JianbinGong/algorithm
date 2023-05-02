//Given a 2D matrix that contains integers only, which each row is sorted in an ascending order.
//The first element of next row is larger than (or equal to) the last element of previous row.
// Given a target number, returning the position that the target locates within the matrix.
// If the target number does not exist in the matrix, return {-1, -1}.

// Clarification:
// input: int[][] matrix, int target
// sorted in each row, ascending, and the first element in the next row is larger or equal than the largest one in the
// previous row
// corner case: null, empty

//output:
//1. -1,
// multiple solutions: anyone

// 1  3   6 9
// 10 11 13 14

// left = 0
// right = row * col - 1;
// mid = left + (right - left)/2;
// row = mid/cols
// col = mid%cols

public class gitSearchInSortedMatrix {
}
