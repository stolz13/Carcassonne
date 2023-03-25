package com.example.carcassonne;



enum Component
    {
        CITY,
        ROAD,
        FIELD,
        UNKNOWN
    }


enum TileSide
{
    UP,
    BOTTOM,
    LEFT,
    RIGHT
}

    // enum player


public class Socket
{

    private final Component component;
    private final int id;
    private Model.Player player;

    public Socket(Component component, int id)
    {
        this.component = component;
        this.id        = id;
        this.player    = null;
    }


    public Component getComponent() { return this.component; }
    public int       getId()        { return this.id; }

    public void setPlayer(Model.Player player) { this.player = player; }
    public void removePlayer()                 { this.player = null; }



    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Socket)) {
            return false;
        }
        Socket that = (Socket) other;
        return this.component == that.component && this.id == that.id; // && this.player == that.player;
    }
}




class Feature
{
    Component type;
    int[] socketIndexes;
    boolean isMonastery;
    boolean hasShield;


    public Feature(Component type, int[] socketIndexes)
    {
        this(type, socketIndexes, "");
    }

    public Feature(Component type, int[] socketIndexes, String additionalInfos)
    {
        this.socketIndexes = socketIndexes;
        this.type = type;
        if (additionalInfos.contains("IsMonastery="))
            isMonastery = additionalInfos.contains("IsMonastery=true");
        if (additionalInfos.contains("HasShield="))
            hasShield = additionalInfos.contains("HasShield=true");
    }
}


class Side
{
    TileSide tileSide;
    Socket[] sockets;

    public Side(TileSide tileSide, Socket[] sockets)
    {
        this.tileSide = tileSide;
        this.sockets  = sockets;
    }

    public TileSide getTileSide() { return tileSide; }
    public Socket[] getSockets()  { return sockets;  }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Side)) {
            return false;
        }
        Side that = (Side) other;
        return  this.sockets[0].equals(that.sockets[0]) &&
                this.sockets[1].equals(that.sockets[1]) &&
                this.sockets[2].equals(that.sockets[2]);

    }
}




