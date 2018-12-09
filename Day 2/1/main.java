import java.io.*;
import java.util.*;

class main {
    public static void main(String[] args) throws Exception{
        int a = countExactOccurances(2);   
        int b = countExactOccurances(3);   
        System.out.println(a * b);
    }

    public static int countExactOccurances(int n) throws IOException {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("./input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                char[] chars = line.toCharArray();
                ArrayList<Character> seenChars = new ArrayList<Character>();
                ArrayList<Integer> seenCharsCounts = new ArrayList<Integer>();                

                for(int i = 0; i < chars.length; i++){
                    char currentChar = chars[i];
                    if (seenChars.indexOf(currentChar) == -1){
                        //if we have not seen this char yet
                        int charCount = 1;
                        seenChars.add(currentChar);
                        for (int y = i + 1; y < chars.length; y++){
                            // System.out.println("Comparing " + currentChar + " with " + chars[y]);
                            if (currentChar == chars[y]){
                                // System.out.println("match");
                                charCount++;
                            }
                        }
                        seenCharsCounts.add(charCount);
                    }                    
                }

                if (seenCharsCounts.indexOf(n) != -1){
                    count++;
                }
            }
        }
        return count;
    }
}