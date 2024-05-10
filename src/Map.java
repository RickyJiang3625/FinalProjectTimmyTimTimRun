import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Map {
    private Player player;
    private Tile[][] map;
    private int[][] worldData;
    public Map()  {
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

         worldData = new int[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++) {
                if (d.charAt(j) == 'P')
                    worldData[i][j] = 2;

                if (d.charAt(j) == 'D'){
                    worldData[i][j] = 1;
                }
                if (d.charAt(j) == 'A')
                    worldData[i][j] = 0;
                if (d.charAt(j) == 'Y'){
                this.player= new Player(i,j);
                }
            }
        }
        return worldData;
    }

    public Tile[][] getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }
    public int[][] getWorldData(){
        return worldData;
    }

    public void generateMap()  {
        int[][] mapData = getMap("map/map1");
        map=new Tile[54][96];
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                Tile t = new Tile(mapData[r][c]);
                map[r][c] = t;
            }
        }
    }
    public void movePlayer(String nesw){
        int playerRow=player.getRow();
        int playerCol=player.getCol();

        if(player.isFalling()){
            player.setRow(playerRow+1);
            if(worldData[playerRow+1][playerCol]==1 || worldData[playerRow+1][playerCol]==2){
                player.setFalling(false);
                player.setRow(playerRow);
            }
            }
        if(worldData[playerRow+1][playerCol]==0){
            player.setFalling(true);
        }

        if(nesw.equals("W")){
            if(canMoveUp()){
            player.setRow((playerRow-1));
            }
            else{
                player.setRow((playerRow));
            }
        }
        if(nesw.equals("S")){
            if(canMoveDown()){
            player.setRow((playerRow+1));
            }
            else{
                player.setRow(playerRow);
            }
        }
        if(nesw.equals("D")){
            if(canMoveRight()){
            player.setCol((playerCol+1));

            }


            else{
                player.setCol(playerCol);
            }
        }
        if (nesw.equals("A")) {
            if(canMoveLeft()){
        player.setCol((playerCol-1));
        }
            else{
                player.setCol(playerCol);
            }
        }
        }
    public boolean canMoveUp(){

        int playerX=getPlayer().getCol();
        int playerY=getPlayer().getRow();
        if(playerY==0){
            return false;
        }
        int tile=worldData[playerY-1][playerX];
        return tile != 2 && tile != 1;
    }



    public boolean canMoveDown(){
        int playerX=getPlayer().getCol();
        int playerY=getPlayer().getRow();
        if(playerY==54){
            return false;
        }
        int tile=worldData[playerY+1][playerX];
        return tile != 2 && tile != 1;
    }
    public boolean canMoveLeft(){
        int playerX=getPlayer().getCol();
        int playerY=getPlayer().getRow();
        if(playerX==0){
            return false;
        }
        int tile=worldData[playerY][playerX-1];
        return tile != 2 && tile != 1;
    }
    public boolean canMoveRight(){
        int playerX=getPlayer().getCol();
        int playerY=getPlayer().getRow();
        if(playerX==95){
            return false;
        }
        int tile=worldData[playerY][playerX+1];
        return tile != 2 && tile != 1;
    }


    }




