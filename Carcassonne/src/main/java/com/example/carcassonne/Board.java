package com.example.carcassonne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {


    int width;
    int height;

    List<List<Field>> boardArray;

    // relative coordinates are always 0;0
    // absolute Coordinates are constantly changing, so to get them there is a
    // method getActualAbsoluteCoordinatesOfStart(), which returns an array with x and y
    // of the origin Tile

    int originTileX;
    int originTileY;


    // Constructor
    public Board() {
        this.boardArray = new ArrayList<>();
        for (int y = 0; y < 3; y++) {
            List<Field> row = new ArrayList<Field>();
            for (int x = 0; x < 3; x++) {
                row.add(new Field(null));
            }
            boardArray.add(row);
        }
        Tile startTile = new Tile(0, 0);
        startTile.setImgPath("images/Tile_D.png");
        System.out.println("Creating board");
        setTileByAbsoluteCoordinates(1, 1, startTile);
    }

    public List<List<Field>> getBoardArray(){
        return boardArray;
    }

    // get position of the start cell at the actual moment
    public void updateAbsoluteCoordinatesOfStart(){
        int x = 0;
        int y = 0;
        for (List<Field> fieldsList: this.boardArray){
            for (Field field : fieldsList){
                if (field.getTile() != null && field.getTile().getRelativeX()==0 && field.getTile().getRelativeY()==0){
//                    System.out.println("Start absolute coordinates: " + x + "; " + y);
                    this.originTileX = x;
                    this.originTileY = y;
//                    return new int[]{x, y};
                }
                x++;
            }
            y++;
            x = 0;
        }
        // Start Cell not found
//        return null;
    }

    public Tile getTileByRelativeCoordinates(int x, int y){
        int[] absoluteCoordinates = getActualAbsoluteCoordinates(x,y);
            return this.boardArray.get(absoluteCoordinates[1]).get(absoluteCoordinates[0]).getTile();
            
        // OR:
//        int width = 0;
//        int height = 0;
//        for (List<Tile> tileList: board){
//            for (Tile t : tileList){
//                if (t.getRelativeX()==x && t.getRelativeY()==y){
//                    return t;
//                }
//                width++;
//            }
//            height++;
//            width = 0;
//        }
//        // Cell not found
//        return null;
    }

    public Tile getTileByAbsoluteCoordinates(int x, int y){
        return this.boardArray.get(y).get(x).getTile();
    }

    public Tile setTileByAbsoluteCoordinates(int x, int y, Tile tile){
        // first check if Coordinates of the new tile are not out of absolute bound
        int maxActualWidth = this.boardArray.get(0).size();
        int maxActualHeight = this.boardArray.size();

        if (x >= 0 && x < maxActualWidth && y >= 0 && y < maxActualHeight){
            // checks if Coordinates of the new Tile are on the border

            // at the upper border
            if (y == 0 && this.boardArray.get(y).get(x).getTile() == null){
                this.boardArray.get(y).set(x, new Field(tile));
//                board.get(y).set(x, new Tile(x, y));
                this.addUpperRow();
//                System.out.println("at the upper border");
            }
            // at the lower border
            else if (y == (maxActualHeight - 1) && this.boardArray.get(y).get(x).getTile() == null) {
                this.boardArray.get(y).set(x, new Field(tile));
//                board.get(y).set(x, new Tile(x, y));
                this.addBottomRow();
//                System.out.println("at the lower border");
            }
            // at the left border
            else if (x == 0 && this.boardArray.get(y).get(x).getTile() == null) {
                this.boardArray.get(y).set(x, new Field(tile));
//                board.get(y).set(x, new Tile(x, y));
                this.addLeftColumn();
//                System.out.println("at the left border");
            }
            // at the right border
            else if (x == (maxActualWidth - 1) && this.boardArray.get(y).get(x).getTile() == null) {
                this.boardArray.get(y).set(x, new Field(tile));
//                board.get(y).set(x, new Tile(x, y));
                this.addRightColumn();
//                System.out.println("at the right border");
            }
            // normal case (no need to extend the board)
            else if (this.boardArray.get(y).get(x).getTile()==null){
                this.boardArray.get(y).set(x, new Field(tile));
//                System.out.println("normal case");
            }
        }
        return null;

    }

    public void setTileByRelativeCoordinates(int x, int y, Tile tile){
        int[] absoluteCoordinates = getActualAbsoluteCoordinates(x, y);
        setTileByAbsoluteCoordinates(absoluteCoordinates[0], absoluteCoordinates[1], tile);
    }

    public int[] getActualAbsoluteCoordinates(int relativeX, int relativeY){
        // check if coordinates are inside the board
//        int[] startCoordinates = updateActualAbsoluteCoordinatesOfStart();
        this.updateAbsoluteCoordinatesOfStart();
//        int absoluteX = relativeX + startCoordinates[0];
        int absoluteX = relativeX + this.originTileX;
//        int absoluteY = relativeY + startCoordinates[1];
        int absoluteY = relativeY + this.originTileY;

        int maxActualWidth = this.boardArray.get(1).size();
        int maxActualHeight = this.boardArray.size();
        // check if absolute coordinates are valid
        if (absoluteX >= 0 && absoluteX <= maxActualWidth && absoluteY >= 0 && absoluteY <= maxActualHeight){
            return new int [] {absoluteX, absoluteY};
        }
        return null;
    }
    
    public int[] getRelativeCoordinates(int absoluteX, int absoluteY){

        int maxActualWidth = this.boardArray.get(1).size();
        int maxActualHeight = this.boardArray.size();
        // check if absolute coordinates are valid
        if (absoluteX >= 0 && absoluteX < maxActualWidth && absoluteY >= 0 && absoluteY < maxActualHeight) {
//            int[] startCoordinates = updateAbsoluteCoordinatesOfStart();
            this.updateAbsoluteCoordinatesOfStart();
//            int relativeX = absoluteX - startCoordinates[0];
            int relativeX = absoluteX - this.originTileX;
//            int relativeY = absoluteX - startCoordinates[1];
            int relativeY = absoluteY - this.originTileY;
            return new int [] {relativeX, relativeY};
        }
        return null;
    }

    public void addUpperRow(){
        List<Field> newRow = new ArrayList<Field>();
        // creating a new empty row
//        newRow.forEach(t -> {
//            t = null;
//        });
        for (int i = 0; i < this.boardArray.get(0).size(); i++){
            newRow.add(i, new Field(null));
        }
        this.boardArray.add(0, newRow);

    }

    public void addBottomRow(){
        List<Field> newRow = new ArrayList<Field>();
        // creating a new empty row
//        newRow.forEach(t -> {
//            t = null;
//        });
        for (int i = 0; i < this.boardArray.get(0).size(); i++){
            newRow.add(i, new Field(null));
        }
        this.boardArray.add(newRow);

    }

    public void addLeftColumn(){
//        for (int i = 0; i < boardArray.size(); i++){
//            boardArray.get(i).add(0, new Field(null));
//        }
        for (List<Field> row : this.boardArray){
            row.add(0, new Field(null));
        }
    }

    public void addRightColumn(){
//        for (int i = 0; i < boardArray.size(); i++){
//            boardArray.get(i).add(new Field(null));
//        }
        for (List<Field> row : this.boardArray){
            row.add(new Field(null));
        }
    }

    public int countDirectNeighbours(Tile tile){
        return this.getDirectNeighbours(tile).size();
    }

    public List<Tile> getDirectNeighbours(Tile tile){
        int x = tile.getRelativeX();
        int y = tile.getRelativeY();
        List<Tile> neighbours = new ArrayList<>();
        neighbours.add(getTileByRelativeCoordinates(x, y - 1));
        neighbours.add(getTileByRelativeCoordinates(x + 1, y));
        neighbours.add(getTileByRelativeCoordinates(x, y + 1));
        neighbours.add(getTileByRelativeCoordinates(x - 1, y));
        return neighbours.stream().filter(i -> i != null).toList();
    }


    // class field needed to clearly identify each cell on the play board
    // in the case of filling empty cells with "null" it is impossible to proccess, as null == null (any kind on nullS)
    class Field{
        private Tile tile;
//        private int relX, relY;

        public Field(Tile tile) {
            this.tile = tile;
        }

        public Tile getTile() {
            return tile;
        }

        public void setTile(Tile tile) {
            this.tile = tile;
        }

    }

}
