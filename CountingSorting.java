import java.util.Arrays;
public class CountingSorting {
    public static int[] sort(int[] array, int k){
        int[] count = new int[k+1];
        Arrays.fill(count, 0);
        int[] output = new int[array.length];
        int size = array.length;

        for(int i = 0; i < size; i++) {
            int j = array[i];
            count[j] = count[j] + 1;
        }

        for(int i = 1; i < k + 1; i++){
            count[i] = count[i] + count[i-1];
        }

        for(int i = size - 1; i >= 0 ; i--){
            int j = array[i];
            count[j] = count[j] - 1;
            output[count[j]] = array[i];
        }

        return output;
    }
}

