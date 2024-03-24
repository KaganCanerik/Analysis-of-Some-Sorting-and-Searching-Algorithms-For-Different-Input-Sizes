import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;






/*public class ReadFromFile {
    public static Integer[] readFile(String filePath){

        try{
            URL csvURL = new URL(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(csvURL.openStream()));
            String line;
            List<Integer> listFlowDuration = new ArrayList<>();
            int a = 0;
            while((line = reader.readLine()) != null){
                if(a==0){
                    a++;
                    continue;
                }
                String[] lineArray = line.split(",");
                int valueFlowDuration = Integer.parseInt(lineArray[6]);
                listFlowDuration.add(valueFlowDuration);
            }

            Integer[] finalArray = listFlowDuration.stream().toArray(Integer[]::new);


            reader.close();
            return finalArray;
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ReadFromFile {
    public static int[] readFile(String filePath){
        try{
            //URL csvURL = new URL(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            List<Integer> listFlowDuration = new ArrayList<>();
            int a = 0;
            while((line = reader.readLine()) != null){
                if(a==0){
                    a++;
                    continue;
                }
                String[] lineArray = line.split(",");
                int valueFlowDuration = Integer.parseInt(lineArray[6]);
                listFlowDuration.add(valueFlowDuration);
            }

            int[] finalArray = new int[listFlowDuration.size()];
            for (int i = 0; i < finalArray.length; i++) {
                finalArray[i] = listFlowDuration.get(i);
            }

            reader.close();
            return finalArray;
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}


