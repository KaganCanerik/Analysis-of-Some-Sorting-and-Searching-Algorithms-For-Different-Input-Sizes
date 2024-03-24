import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

class Main {
    static int[]  sizeArray = {500,1000,2000,4000,8000,16000,32000,64000,128000,250000};
    public static void main(String args[]) throws IOException {

        // X axis data
        int[] inputAxis = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 251282};

        // Create sample data for linear runtime
        double[][] yAxis = new double[2][10];
        yAxis[0] = new double[]{512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 251282};
        yAxis[1] = new double[]{300, 800, 1800, 3000, 7000, 15000, 31000, 64000, 121000, 231000};

        // Save the char as .png and show it
        String filePath = args[0];
        int[] fileDuration = ReadFromFile.readFile(filePath);

        double[][] timesAxisForInsertion = new double[3][10];
        double[][] timesAxisForMerge = new double[3][10];
        double[][] timesAxisForCounting = new double[3][10];
        double[][] timesAxisForSearching = new double[3][10];
        ArrayList<ArrayList<Double>> timeList = new ArrayList<>();

        doSortings(fileDuration,timeList,timesAxisForInsertion,"Insertion",0);
        doSortings(fileDuration,timeList,timesAxisForMerge,"Merge",30);
        doSortings(fileDuration,timeList,timesAxisForCounting,"Counting",60);

        doSearchs(fileDuration,timeList,timesAxisForSearching);




        String[] sortingAlgorithms = {"Insertion Sort","Merge Sort","Counting Sort"};
        String[] searchingAlgorithms = {"Linear Search(Random Input Data)","Linear Search(Sorted Input Data)","Binary Search(Sorted Input Data)"};
        showAndSaveChart("Test on Random Data",sizeArray,timesAxisForInsertion[0],timesAxisForMerge[0],timesAxisForCounting[0],sortingAlgorithms);
        showAndSaveChart("Test on Sorted Data",sizeArray,timesAxisForInsertion[1],timesAxisForMerge[1],timesAxisForCounting[1],sortingAlgorithms);
        showAndSaveChart("Test on Reverse Sorted Data",sizeArray,timesAxisForInsertion[2],timesAxisForMerge[2],timesAxisForCounting[2],sortingAlgorithms);
        showAndSaveChart("Test on Searching",sizeArray,timesAxisForSearching[0],timesAxisForSearching[1],timesAxisForSearching[2],searchingAlgorithms);
        String[] modeArray = {"Sorting Times with Random Data","Sorting Times with Sorted Data","Sorting Times with Reverse Sorted Data"};

        for(int i=0; i<timesAxisForInsertion.length; i++){
            System.out.println(modeArray[i]);
            System.out.println("insertion time :"+Arrays.toString(timesAxisForInsertion[i]));
            System.out.println("merge time :"+Arrays.toString(timesAxisForMerge[i]));
            System.out.println("counting time :"+Arrays.toString(timesAxisForCounting[i]));

        }
        System.out.println("Random linear searching :"+Arrays.toString(timesAxisForSearching[0]));
        System.out.println("Sorted linear searching :"+Arrays.toString(timesAxisForSearching[1]));
        System.out.println("Sorted binary searching :"+Arrays.toString(timesAxisForSearching[2]));



    }

    public static void showAndSaveChart(String title, int[] xAxis, double[] yAxis1, double[] yAxis2, double[] yAxis3,String[] algoNameArray) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);




        // Add a plot for a sorting algorithm
        chart.addSeries(algoNameArray[0], doubleX, yAxis1);
        chart.addSeries(algoNameArray[1], doubleX, yAxis2);
        chart.addSeries(algoNameArray[2], doubleX,yAxis3);
        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        new SwingWrapper(chart).displayChart();
    }
    public static void doSortings(int[] fileDuration, ArrayList<ArrayList<Double>> timeList,double[][] timeAxisArray,String algorithmName ,int initialIndex){
        for(int i=0; i<sizeArray.length; i++){
            int[] sortedArray =Experiments.experimentsSorting(sizeArray[i],fileDuration,"RANDOM",algorithmName,timeList);

            Experiments.experimentsSorting(sizeArray[i],sortedArray,"SORTED",algorithmName,timeList );

            int[] reversedArray = IntStream.range(0, sortedArray.length)
                    .map(a -> sortedArray[sortedArray.length - 1 - a])
                    .toArray();
            Experiments.experimentsSorting(sizeArray[i],reversedArray,"REVERSE_SORTED",algorithmName,timeList );
        }

        int indexOfAxis = 0;
        int keyValue=initialIndex;
        for(int i=0; i<3; i++){
            while(initialIndex<timeList.size()){
                timeAxisArray[i][indexOfAxis] = timeList.get(initialIndex).get(0);
                initialIndex+=3;
                indexOfAxis++;
            }
            indexOfAxis=0;
            initialIndex = keyValue +1;
            keyValue++;
        }
    }
    public static void doSearchs(int[] fileDuration,ArrayList<ArrayList<Double>> timeList,double[][] timesAxisForSearching){
        for(int i=0; i< sizeArray.length; i++){

            int[] copyArray = Arrays.copyOfRange(fileDuration ,0, sizeArray[i]);
            timesAxisForSearching[0][i]=Experiments.experimentsSearching(sizeArray[i],copyArray,"Linear_searching",timeList );
            int[] sortedArray = Experiments.experimentsSorting(sizeArray[i],fileDuration,"RANDOM","Merge", timeList);
            int lastIndex = timeList.size()-1;
            timeList.remove(lastIndex);
            timesAxisForSearching[1][i]=Experiments.experimentsSearching(sizeArray[i],sortedArray,"Linear_searching",timeList);
            timesAxisForSearching[2][i]=Experiments.experimentsSearching(sizeArray[i],sortedArray,"Binary_search",timeList);
        }
    }

}
