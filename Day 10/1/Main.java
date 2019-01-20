import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception{
        String[] vectors = readFileAsArray("./input.txt");   
        Point[] points = processInput(vectors);
        Grid grid;
        boolean pause = false;

        while(true){
            try{
                grid = createGrid(points);
                grid.placePoints(points);
                grid.show();
                pause = true;

            } catch(Exception e){
                pause = false;
            }

            points = advancePoints(points);
            pauseForInput(pause);
        }
    }

    public static Point[] advancePoints(Point[] points){
        for (Point point : points){
            point.posX += point.velX;
            point.posY += point.velY;
        }
        return points;
    }

    public static void pauseForInput(boolean pause){
        if (pause){
            Scanner sc = new Scanner(System.in);
            String pauseForInput = sc.nextLine();
        }
    }

    public static Grid createGrid(Point[] points) throws Exception{
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE, minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        for (Point p : points){
            if(p.posX > maxX) maxX = p.posX;
            if(p.posX < minX) minX = p.posX;
            if(p.posY > maxY) maxY = p.posY;
            if(p.posY < minY) minY = p.posY;
        }

        System.out.println(maxX);
        System.out.println(minX);
        return new Grid(minX, maxX, minY, maxY);
    }

    public static String[] readFileAsArray(String file) throws IOException {
        ArrayList<String> lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines.toArray(new String[0]);
    }

    public static Point[] processInput(String[] input) {
        int posX, posY, velX, velY, count = 0;    
        Point[] points = new Point[input.length];

        for (String line : input){
            posX = Integer.parseInt(line.split("<")[1].split(",")[0].trim());
            posY = Integer.parseInt(line.split(",")[1].split(">")[0].trim());
            velX = Integer.parseInt(line.split("<")[2].split(",")[0].trim());
            velY = Integer.parseInt(line.split(",")[2].split(">")[0].trim());
            points[count] = new Point(posX, posY, velX, velY);
            count++;
        }
        return points;
    }
}

class Grid {
    public String[][] grid;
    int sizeX, sizeY, minX, minY, borderWidth = 2;

    public Grid(int minX, int maxX, int minY, int maxY) throws Exception{
        this.sizeX = (maxX - minX) + 1;
        this.sizeY = (maxY - minY) + 1;
        this.minX = minX;
        this.minY = minY;
        createGrid();
    }

    public void placePoints(Point[] points) throws Exception{
        createGrid(); //clear
        for (Point point : points){
            placePoint(point);
        }
    }

    public void placePoint(Point point){
        grid[point.posY - this.minY + borderWidth][point.posX - this.minX + borderWidth] = "#";
    }

    private void createGrid() throws Exception{
        System.out.println(this.sizeY);
        System.out.println(this.sizeX);
        
        if (this.sizeX > 300 || this.sizeY > 300){
            throw new Exception("grid too large");
        }

        this.grid = new String[this.sizeY + 2*borderWidth][this.sizeX + 2*borderWidth];
        for(String[] row : grid){
            Arrays.fill(row, ".");
        }
    }

    public void show(){
        for (int y = 0; y < grid.length; y++){
            for (int x = 0; x < grid[y].length; x++){
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
    }
}

class Point {
    public int posX, posY, velX, velY;

    public Point(int posX, int posY, int velX, int velY){
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
    }
}