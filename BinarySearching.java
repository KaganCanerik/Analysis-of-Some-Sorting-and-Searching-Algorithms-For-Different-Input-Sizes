public class BinarySearching {
    public static int search(int[] array, int x){
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (array[mid] == x) {
                return mid;
            }
            else if (array[mid] < x) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        return -1;
    }
}
