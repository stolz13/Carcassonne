package com.example.carcassonne;

import java.util.Arrays;





public class Tile {


    private int relativeX, relativeY;
    private int rotation;
    private Socket[] sockets = new Socket[12];
    private Side[] sides = new Side[4];
    // player ?
    // image - at ZÜ discussed, maybe it will be better to put image into view
    String imgPath;
    private Side up, down, left, right;


    public void printSockets(){
            System.out.println();
        for (Socket s : this.sockets){
            if (s != null) {
                System.out.print(s.getId() + " " + s.getComponent() + " ");
            }
            else {
                System.out.print("null ");
            }
        }
        System.out.println();
    }

    public Tile(int x, int y)
    {
        this.relativeX = x;
        this.relativeY = y;
        Arrays.fill(this.sockets, null);
        this.rotation = 0;
    }
    public Tile(int x, int y, int rotation)
    {
        this.relativeX = x;
        this.relativeY = y;
        this.rotation = rotation;
        Arrays.fill(this.sockets, null);
    }

    public Tile(int x, int y, Socket[] sockets)
    {
        this.relativeX = x;
        this.relativeY = y;
        this.sockets   = sockets;

        // Err: Cannot resolve method 'setSides' in 'Tile'
//        this.sides     = setSides(sockets);
    }

    public Tile(int x, int y, Socket[] sockets, int rotation)
    {
        this.relativeX = x;
        this.relativeY = y;
        this.rotation  = rotation;
        this.sockets   = sockets;
        this.sides     = setSides(sockets);
    }


    private Side[] setSides(Socket[] sockets)
    {
        Side[] sides = new Side[4];
        for (int i = 0; i < 12 - 2; i = i + 3)
        {
            if (i < 3)
                sides[0] = new Side(TileSide.UP,     new Socket[]{sockets[i], sockets[i+1], sockets[i+2]});
            else if (i >= 3 && i < 6)
                sides[1] = new Side(TileSide.RIGHT,  new Socket[]{sockets[i], sockets[i+1], sockets[i+2]});
            else if (i >= 6 && i < 9)
                sides[2] = new Side(TileSide.BOTTOM, new Socket[]{sockets[i], sockets[i+1], sockets[i+2]});
            else
                sides[3] = new Side(TileSide.LEFT,   new Socket[]{sockets[i], sockets[i+1], sockets[i+2]});
        }
        return sides;
    }

    public Socket[] getSideSockets(TileSide tileSide)
    {
        // todo
        return sides[tileSide.ordinal()].getSockets();
    }

    public Side[] getSides()
    {
        return this.sides;
    }

    public com.example.carcassonne.Side getSide(com.example.carcassonne.TileSide tileSide)
    {
        if      (tileSide.ordinal() == 0) return this.sides[0];
        else if (tileSide.ordinal() == 1) return this.sides[1];
        else if (tileSide.ordinal() == 2) return this.sides[2];
        else                              return this.sides[3];
    }


    public void setImgPath(String path){
        this.imgPath = path;
    }

    public String getImgPath(){
        return this.imgPath;
    }


    public void rotateLeft(int rotationAngle)
    {
        for (int i = 0; i < rotationAngle; i = i + 45)
            rotateLeft();
    }

    public void rotateRight(int rotationAngle)
    {
        for (int i = 0; i < rotationAngle; i = i + 45)
            rotateRight();
    }

    public void rotateLeft()
    {
        Socket[] tmpSockets = new Socket[this.sockets.length];
        tmpSockets[9]  = this.sockets[0];
        tmpSockets[10] = this.sockets[1];
        tmpSockets[11] = this.sockets[2];

        System.arraycopy(this.sockets, 3, tmpSockets, 0, this.sockets.length - 3);
        this.sockets = tmpSockets;
    }


    public void rotateRight()
    {
        Socket[] tmpSockets = new Socket[this.sockets.length];
        if (this.sockets.length - 1 - 2 >= 0)
            System.arraycopy(this.sockets, 0, tmpSockets, 3, this.sockets.length - 1 - 2);
        this.sockets = tmpSockets;
    }

    public boolean areSidesMatched(Side thatSide)
    {
        int indexOfSide = thatSide.getTileSide().ordinal();
        return this.sides[indexOfSide].equals(thatSide);
    }

    public void setRelativePosition(int x, int y)
    {
        this.relativeX = x;
        this.relativeY = y;
    }


    public int getRelativeX()    { return this.relativeX; }
    public int getRelativeY()    { return this.relativeY; }
    public Socket[] getSockets() { return this.sockets;   }
    public int getRotation()    { return this.rotation;  }

    public void setSockets (Socket[] sockets) { this.sockets  = sockets;  }
    public void setRotation(int rotation)     { this.rotation = rotation; }

}

