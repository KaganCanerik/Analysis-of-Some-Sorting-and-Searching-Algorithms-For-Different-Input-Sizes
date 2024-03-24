public class LinearSearching {
    public static int search(int[] array, int x){
        //int size = array.length;
        for(int i=0; i< array.length-1;i++){
            if(array[i] == x){
                return i;
            }
        }
        return -1;
    }
}
