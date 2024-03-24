import java.util.Arrays;


public class MergeSorting {
    public static int[] sort(int[] array){
        int n = array.length;
        if (n <= 1) {
            return array;
        }
        int[] left = Arrays.copyOfRange(array, 0, n / 2);
        int[] right = Arrays.copyOfRange(array, n / 2, n);
        sort(left);
        sort(right);
        return merge(left, right);
    }

    public static int[] merge(int[] left, int[] right){
        int[] mergedArray = new int[left.length + right.length];
        int leftIndex = 0, rightIndex = 0, mergedIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                mergedArray[mergedIndex++] = left[leftIndex++];
            } else {
                mergedArray[mergedIndex++] = right[rightIndex++];
            }
        }

        while (leftIndex < left.length) {
            mergedArray[mergedIndex++] = left[leftIndex++];
        }

        while (rightIndex < right.length) {
            mergedArray[mergedIndex++] = right[rightIndex++];
        }

        return mergedArray;
    }
}

