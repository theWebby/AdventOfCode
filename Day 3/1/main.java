import java.io.*;
import java.util.*;

class main {
    public static void main(String[] args) throws Exception{   
        ArrayList<Claim> claims = processClaims();
        int[][] fabric = createFabric(claims);
        System.out.println(findOverlap(claims, fabric));
        saveToFile(fabric);
    }

    public static int findOverlap(ArrayList<Claim> claims, int[][] fabric){
        Claim claim;
        int overlapCount = 0;
        for (int claimIndex = 0; claimIndex < claims.size(); claimIndex++){   
            claim = claims.get(claimIndex);

            for (int h=claim.marginTop; h<claim.marginTop + claim.height; h++){
                for (int w=claim.marginLeft; w<claim.marginLeft + claim.width; w++){
                    if (fabric[h][w] == 0){
                        fabric[h][w] = claim.id;
                    }
                    else if (fabric[h][w] > 0){
                        overlapCount++;
                        fabric[h][w] = -1;
                    }
                }
            }
        }
        
        return overlapCount;
    }

    public static int[][] createFabric(ArrayList<Claim> claims){
        int height=0, width=0;

        for(int i=0; i < claims.size(); i++){
            if (claims.get(i).marginTop + claims.get(i).height > height){ 
                height = claims.get(i).marginTop + claims.get(i).height;
            }
            if (claims.get(i).marginLeft + claims.get(i).width > width){
                width = claims.get(i).marginLeft + claims.get(i).width;
            }
        }
        
        return new int[height][width];
    }

    public static ArrayList<Claim> processClaims() throws IOException {
        ArrayList<Claim> claims = new ArrayList<Claim>();    
        try (BufferedReader br = new BufferedReader(new FileReader("./input.txt"))) {
            String line;
            int id=-1, marginLeft=-1, marginTop=-1, width=-1, height=-1, indexOfAt=-1, indexOfComma=-1, indexOfColon=-1, indexOfX=-1;
            while ((line = br.readLine()) != null) {
                indexOfAt = line.indexOf('@');
                indexOfComma = line.indexOf(',');
                indexOfColon = line.indexOf(':');
                indexOfX = line.indexOf('x');

                id = Integer.parseInt(line.substring(1, indexOfAt - 1));
                marginLeft = Integer.parseInt(line.substring(indexOfAt + 2, indexOfComma));
                marginTop = Integer.parseInt(line.substring(indexOfComma + 1, indexOfColon));
                width = Integer.parseInt(line.substring(indexOfColon + 2, indexOfX));
                height = Integer.parseInt(line.substring(indexOfX + 1, line.length()));
                

                claims.add(new Claim(id, marginLeft, marginTop, width, height));
            }
        }
        return claims;
    }

    public static void saveToFile(int[][] board) throws IOException{
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < board.length; i++)//for each row
        {
            for(int j = 0; j < board[i].length; j++)//for each column
            {
                builder.append(board[i][j]+"");//append to the output string
                if(j < board.length - 1)//if this is not the last row element
                    builder.append(",");//then add comma (if you don't like commas you can use spaces)
            }
            builder.append("\n");//append new line at the end of the row
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("./fabric.txt"));
        writer.write(builder.toString());//save the string representation of the board
        writer.close();
            }
        }

class Claim {
    int id, marginLeft, marginTop, width, height;

    public Claim (int id, int marginLeft, int marginTop, int width, int height){
        this.id = id;
        this.marginLeft = marginLeft;
        this.marginTop = marginTop;
        this.width = width;
        this.height = height;    
    }
}