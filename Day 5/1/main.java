import java.io.*;
import java.util.*;

class main {
    public static void main(String[] args) throws Exception{   
        String inputString = readInput();
        Character[] alphabet = new Character[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        int[] results = new int[alphabet.length];
        
        int highScore = 999999;
        for (int i = 0; i < alphabet.length; i++){
            results[i] = getReactedLength(removeChar(inputString, alphabet[i]));
            if (results[i] < highScore){
                highScore = results[i];
            }
        }
        // getReactedLength(inputString);
        System.out.println(highScore);
    }

    public static String removeChar(String s, char targetChar){
        ArrayList<Character> al = new ArrayList<Character>();
        for (char c : s.toCharArray()) {
            al.add(c);
        }

        for (int i = 0; i < al.size(); i++){
            if ((int) al.get(i) == (int) targetChar | (int) al.get(i) == (int) targetChar + 32){
                al.remove(i);
                i--;
            } 
        }

        StringBuilder result = new StringBuilder(al.size());
        for(Character ch: al){
            result.append(ch);
        }

        return result.toString();
    }

    public static int getReactedLength(String inputString){
        ArrayList<Character> input = new ArrayList<Character>();
        for (char c : inputString.toCharArray()) {
            input.add(c);
        }
        
        Boolean found = true, foundPair = false;
        while(found){
            found = false;
            for (int i = 0; i <input.size() - 1; i++){
                foundPair = false;
                if ((int) input.get(i) < 91){
                    if ((int) input.get(i) + 32 == (int) input.get(i + 1)){
                        foundPair = true;
                    }
                }
                else{
                    if ((int) input.get(i) - 32 == (int) input.get(i + 1)){
                        foundPair = true;       
                    }
                }

                if (foundPair){
                    found = true;
                    input.remove(i);
                    input.remove(i);
                }
            }
        }
        return input.size();
    }

    public static String readInput() throws IOException {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader("./input.txt"))) {
            line = br.readLine();
        }
        return line;
    }
}