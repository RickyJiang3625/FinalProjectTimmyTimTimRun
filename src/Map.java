import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Map {
    private Tile[][] map;
    public Map(){
        generateMap();

    }
    private int[][] getMap(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        int rows = fileData.size();
        int cols = fileData.get(0).length();

        int[][] worldData = new int[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++) {
                if (d.charAt(j) == 'P')
                    worldData[i][j] = 1;
                if (d.charAt(j) == 'A')
                    worldData[i][j] = 0;
            }
        }
        return worldData;
    }

    public Tile[][] getMap() {
        return map;
    }

    public void generateMap(){
        int[][] mapData=getMap("map/map1");
        map=new Tile[4][4];
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                Tile t = new Tile(mapData[r][c]);
                map[r][c] = t;
            }
        }

    }
}



