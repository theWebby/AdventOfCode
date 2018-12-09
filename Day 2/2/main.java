import java.io.*;
import java.util.*;

class main {
    public static void main(String[] args) throws Exception{
        String a = findCode();   
        System.out.println(a);
    }

    public static String findCode() throws IOException {
        String line, code = "";
        ArrayList<String> allCodes = new ArrayList<String>();
        int diffCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("./input.txt"))) {
            while ((line = br.readLine()) != null) {
                allCodes.add(line);
            }
        }

        for (int i=0; i < allCodes.size()-1; i++){
            for (int j=i+1; j < allCodes.size(); j++){
                // compare all codes to each other
                diffCount = 0;
                for (int charIndex=0; charIndex < allCodes.get(i).length(); charIndex++){
                    if (allCodes.get(i).charAt(charIndex) != allCodes.get(j).charAt(charIndex)){
                        diffCount++;

                        if (diffCount < 2){
                            code = allCodes.get(i).substring(0, charIndex) + allCodes.get(i).substring(charIndex+1, allCodes.get(i).length());
                        }
                        else{
                            break;
                        }
                    } 
                }

                if (diffCount == 1){
                    return code;
                }
            }
        }

        return "failed";
    }
}