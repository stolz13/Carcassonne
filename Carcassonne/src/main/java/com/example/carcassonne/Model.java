package com.example.carcassonne;


import javafx.geometry.Side;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Model {

    //Board Instanz
    private Board board;
    //Tile Objekt
    private LibraryEntry nextTile;
    private Player currentPlayer;
    private Computer computer;
    TileLibrary tileLibrary;



    //Konstruktor
    public Model(){
        board = new Board();
        nextTile = null;
        currentPlayer = new Player();
        computer = new Computer();
        this.tileLibrary = new TileLibrary();
    }

    //Getter
    public Board getBoard(){
        return board;
    }

    public void chooseNextTile(){
        this.nextTile = tileLibrary.getRandomEntry();
    }

    public LibraryEntry getNextTile(){
        return this.nextTile;
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public Computer getComputer(){
        return computer;
    }

    //Setter
//    public void setCurrentTile(Tile tile){
//        nextTile = tile;
//    }

    public void setCurrentPlayer(Player player){
        currentPlayer = player;
    }

    //Spielerinformationen
    class Player{
        private int score;

        public Player(){
            score = 0;
        }

        public int getScore(int score){
            return score;
        }
        public void setScore(){
            this.score = score;
        }

        public void putTile(int x, int y, Tile tile){
            board.setTileByAbsoluteCoordinates(x, y, tile);
        }

        public void updateScore(int points){
        score += points;
        }

    }

    class Computer{
        private int score;
        public Computer(){
            score = 0;
        }

        public int getScore(int score){
            return score;
        }
        public void setScore(){
            this.score = score;
        }

        public void putTile(int x, int y, Tile tile){
            board.setTileByAbsoluteCoordinates(x, y, tile);
        }

        public void updateScore(int points) {
            score += points;
        }
    }

    //Methoden
    //Lesen
    public Tile readTile(int x, int y) {
        return board.getTileByAbsoluteCoordinates(x, y);
    }

    //Ausführen/Prüfen

    public boolean checkIfPlacedCorrectly(Tile thisTile)
    {
//     _________
//        we can need this if we want to find possible rotations:
//        ArrayList<Integer> possibleRotations = new ArrayList<>();
//     _________

        com.example.carcassonne.Side[] neighbourSides = getNeighbours(thisTile);
        int rotation = 0;

//        if (Arrays.stream(neighbourSides).allMatch(element -> element != null))

        if (Arrays.stream(neighbourSides).allMatch(element -> element != null)) // check if there is at least one neighbour
        {
        for (int i = 0; i < 4; i++)
        {
            boolean isMatchUp     = neighbourSides[0] == null || thisTile.areSidesMatched(neighbourSides[0]);
            boolean isMatchRight  = neighbourSides[1] == null || thisTile.areSidesMatched(neighbourSides[1]);
            boolean isMatchBottom = neighbourSides[2] == null || thisTile.areSidesMatched(neighbourSides[2]);
            boolean isMatchLeft   = neighbourSides[3] == null || thisTile.areSidesMatched(neighbourSides[3]);

            if (isMatchUp && isMatchRight && isMatchBottom && isMatchLeft)
//              possibleRotations.add(rotation);
                return true;

            rotation += 45;
            thisTile.rotateRight(rotation);
        }
        }
        return false; // return rotation // possibleRotations
    }



    com.example.carcassonne.Side[] getNeighbours(Tile tileForCheck)
    {
        Side[] sidesToCheck = new Side[4]; // up right bottom left
        int x = tileForCheck.getRelativeX();
        int y = tileForCheck.getRelativeY();
        if (x - 1 > 0)
        {
            Tile neighbour = board.getTileByRelativeCoordinates(x - 1, y);
//            sidesToCheck[3] = (neighbour != null) ? neighbour.getSide(TileSide.RIGHT) : null;
        }
        if (x + 1 < board.width)
        {
            Tile neighbour = board.getTileByRelativeCoordinates(x + 1, y);
//            sidesToCheck[1] = (neighbour != null) ? neighbour.getSide(TileSide.LEFT) : null;
        }
        if (y - 1 > 0)
        {
            Tile neighbour = board.getTileByRelativeCoordinates(x, y - 1);
//            sidesToCheck[0] = (neighbour != null) ? neighbour.getSide(TileSide.BOTTOM) : null;
        }
        if (y + 1 < board.height)
        {
            Tile neighbour = board.getTileByRelativeCoordinates(x, y + 1);
//            sidesToCheck[2] = (neighbour != null) ? neighbour.getSide(TileSide.UP) : null;
        }
//        return sidesToCheck;
        return null;
    }

    public boolean validTarget(){
        return false;
    }

    public void printBoard(){
        List<List<Board.Field>> arr = this.board.getBoardArray();
        int maxActualWidth = arr.get(1).size();
        int maxActualHeight = arr.size();
//        System.out.println("maxActualHeight: " + maxActualHeight);
//        System.out.println("maxActualWidth: " + maxActualWidth);
        System.out.println("\n===========================================================\n");
        for (List<Board.Field> row : arr){
            for (Board.Field t : row){
                if (t.getTile() == null) {
                    System.out.print(" |       aX:" + row.indexOf(t) + " aY:" + arr.indexOf(row) + "         | ");
                } else {
                    System.out.print(" | aX:" + row.indexOf(t) + " aY:" + arr.indexOf(row) +" rX:" + t.getTile().getRelativeX() + " rY:" + t.getTile().getRelativeY() + " N:" + this.board.countDirectNeighbours(t.getTile())  + " | ");
                }
            }
            System.out.println();
        }


//        for(List<Board.Field> row : arr)
        System.out.println("\n===========================================================\n");

    }

}