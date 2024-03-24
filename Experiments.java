import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Experiments {
    public static int[] experimentsSorting(int size, int[] array, String mode, String algorithm, ArrayList<ArrayList<Double> >times){
        double timeDifference =0;
        ArrayList<Double> timeList = new ArrayList<>();
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

        timeList.add(timeDifference/10);
        times.add(timeList);

        return copyArray;
    }
    public static double experimentsSearching(int size,int[] copyArray, String algorithm, ArrayList<ArrayList<Double>> times){
        double timeDifference =0;
        Random random = new Random();
        ArrayList<Double> timeList = new ArrayList<>();

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

        timeList.add(timeDifference/1000);
        times.add(timeList);

        return timeDifference/1000;
    }
}
