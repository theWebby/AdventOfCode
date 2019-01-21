import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception{
        Map map = readFileAsMap("./input.txt");
        
        while(!map.crash){
            map.print();
            map.advanceCarts();
        }
        pauseForInput(true);
        
        System.out.println(map.carts.length);
    }
    
    public static Map readFileAsMap(String file) throws IOException {
        ArrayList<String> lines = new ArrayList<String>();
        int maxX, maxY = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            maxX = lines.get(0).length();
            maxY = lines.size();
        }
        
        char[][] map = new char[maxY + 2][maxX + 2];
        for (int i = 1; i < lines.size() + 1; i++){
            map[i] = (" " + lines.get(i - 1) + " ").toCharArray();
        }
        
        return new Map(map);
    }

    public static void pauseForInput(boolean pause){
        if (pause){
            Scanner sc = new Scanner(System.in);
            System.out.println("Press enter until the message appears");
            String pauseForInput = sc.nextLine();
        }
    }
}



