import java.io.*;

class main {
    public static void main(String[] args) throws Exception{
        int rf = findResultFrequency("./input.txt", 0);   
        System.out.println(rf);
    }

    public static int findResultFrequency(String file, int startingFreq) throws IOException {
        int rf = startingFreq;
        try (BufferedReader br = new BufferedReader(new FileReader("./input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                rf = rf + Integer.parseInt(line);
            }
        }
        return rf;
    }
}