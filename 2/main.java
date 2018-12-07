import java.io.*;
import java.util.*;

class main {
    public static ArrayList<Integer> previousFreqs = new ArrayList<Integer>();
    public static Boolean found = false;
    public static int count = 0;

    public static void main(String[] args) throws Exception{
        int rf = 0;
        while(!found){
            rf = findResultFrequency("./input.txt", rf);   
        }

        System.out.println(rf);
        System.out.println(count);
    }

    public static int findResultFrequency(String file, int startingFreq) throws IOException {
        count++;
        int rf = startingFreq;
        try (BufferedReader br = new BufferedReader(new FileReader("./input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                rf = rf + Integer.parseInt(line);

                if (previousFreqs.indexOf(rf) == -1){ 
                    previousFreqs.add(rf);
                }
                else{
                    found = true;
                    return rf;
                }
            }
        }
        return rf;
    }
}