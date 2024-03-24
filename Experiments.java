import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Experiments {
    /**
     * This method will be used at the main class.
     * This method calls the related algorithm process from related class and measure the running time of algorithms.
     * The process mentioned above will be evaluated according to the parameters.
     * @param size By using this parameter, "array" will be restricted as "first size(parameter) element" of "array".
     * @param array the input array which will be constraint by using the size(paramter) value.
     * @param mode is the checking parameter for the "RANDOM" situations. If it is equal to "RANDOM", shuffling the array which will be used will done.
     * @param algorithm name of algorithm.
     * @param times the list which is stored the durations of running times of relating algorithm.
     */
    public static int[] experimentsSorting(int size, int[] array, String mode, String algorithm, ArrayList<Double>times){
        double timeDifference =0;
        int[] copyArray = Arrays.copyOfRange(array ,0, size);

        for(int i=0; i<10; i++){
            if(mode.equals("RANDOM")  ){
                List<Integer> listOfArray = IntStream.of(copyArray).boxed().collect(Collectors.toList());
                Collections.shuffle(listOfArray);
                for (int a = 0; a < copyArray.length; a++) {
                    copyArray[a] = listOfArray.get(a);
                }
            }

            if(algorithm.equals("Insertion")) {
                long timeBefore = System.currentTimeMillis();
                InsertionSorting.sort(copyArray);
                long timeAfter = System.currentTimeMillis();
                timeDifference += (double) (timeAfter - timeBefore);
            }
            else if(algorithm.equals("Merge")){
                long timeBefore = System.currentTimeMillis();
                MergeSorting.sort(copyArray);
                long timeAfter = System.currentTimeMillis();
                timeDifference += (double) (timeAfter - timeBefore);
            }
            else if(algorithm.equals("Counting")){
                long timeBefore = System.currentTimeMillis();
                int maxValue = Arrays.stream(copyArray).max().getAsInt();
                CountingSorting.sort(copyArray, maxValue);
                long timeAfter = System.currentTimeMillis();
                timeDifference += (double) (timeAfter - timeBefore);
            }

        }

        times.add(timeDifference/10);

        return copyArray;
    }
    /**
     * This method will be used at the main class.
     * This method calls the related algorithm process from related class and measure the running time of algorithms.
     * The process mentioned above will be evaluated according to the parameters.
     * @param copyArray the array which is restricted array of input array according to the restricting size
     * @param algorithm name of algorithm.
     * @param times the list which is stored the durations of running times of relating algorithm.
     */
    public static double experimentsSearching(int[] copyArray, String algorithm, ArrayList<Double> times){
        double timeDifference =0;
        Random random = new Random();

        for(int i=0; i<1000; i++){
            int randomIndex = random.nextInt(copyArray.length);
            int searchingNumber = copyArray[randomIndex];

            if(algorithm.equals("Binary_search")){
                long timeBefore = System.nanoTime();
                BinarySearching.search(copyArray,searchingNumber);
                long timeAfter = System.nanoTime();
                timeDifference += (double) (timeAfter - timeBefore);
            }
            else if(algorithm.equals("Linear_searching")){
                long timeBefore = System.nanoTime();
                LinearSearching.search(copyArray,searchingNumber);
                long timeAfter = System.nanoTime();
                timeDifference += (double) (timeAfter - timeBefore);
            }

        }

        times.add(timeDifference/1000);

        return timeDifference/1000;
    }
}
