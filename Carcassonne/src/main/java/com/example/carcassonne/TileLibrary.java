package com.example.carcassonne;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;
import java.util.*;


public class TileLibrary {


    private final HashMap<String, LibraryEntry> mapOfEntries = new HashMap<>();


    public TileLibrary()

            // TODO Every card should exist in predefined number of copies
            // (if not already implemented)

    {
        mapOfEntries.put("A", new LibraryEntry("images/Tile_A.png", getFeatures('A')));
        mapOfEntries.put("B", new LibraryEntry("images/Tile_B.png", getFeatures('B')));
        mapOfEntries.put("C", new LibraryEntry("images/Tile_C.png", getFeatures('C')));
        mapOfEntries.put("D", new LibraryEntry("images/Tile_D.png", getFeatures('D')));
        mapOfEntries.put("E", new LibraryEntry("images/Tile_E.png", getFeatures('E')));
        mapOfEntries.put("F", new LibraryEntry("images/Tile_F.png", getFeatures('F')));
        mapOfEntries.put("G", new LibraryEntry("images/Tile_G.png", getFeatures('G')));
        mapOfEntries.put("H", new LibraryEntry("images/Tile_H.png", getFeatures('H')));
        mapOfEntries.put("I", new LibraryEntry("images/Tile_I.png", getFeatures('I')));
        mapOfEntries.put("J", new LibraryEntry("images/Tile_J.png", getFeatures('J')));
        mapOfEntries.put("K", new LibraryEntry("images/Tile_K.png", getFeatures('K')));
        mapOfEntries.put("L", new LibraryEntry("images/Tile_L.png", getFeatures('L')));
        mapOfEntries.put("M", new LibraryEntry("images/Tile_M.png", getFeatures('M')));
        mapOfEntries.put("N", new LibraryEntry("images/Tile_N.png", getFeatures('N')));
        mapOfEntries.put("O", new LibraryEntry("images/Tile_O.png", getFeatures('O')));
        mapOfEntries.put("P", new LibraryEntry("images/Tile_P.png", getFeatures('P')));
        mapOfEntries.put("Q", new LibraryEntry("images/Tile_Q.png", getFeatures('Q')));
        mapOfEntries.put("R", new LibraryEntry("images/Tile_R.png", getFeatures('R')));
        mapOfEntries.put("S", new LibraryEntry("images/Tile_S.png", getFeatures('S')));
        mapOfEntries.put("T", new LibraryEntry("images/Tile_T.png", getFeatures('T')));
        mapOfEntries.put("U", new LibraryEntry("images/Tile_U.png", getFeatures('U')));
        mapOfEntries.put("V", new LibraryEntry("images/Tile_V.png", getFeatures('V')));
        mapOfEntries.put("W", new LibraryEntry("images/Tile_W.png", getFeatures('W')));
        mapOfEntries.put("X", new LibraryEntry("images/Tile_X.png", getFeatures('X')));

    }


    private Feature[] getFeatures(char letter)
    {
        ArrayList<Feature> features = new ArrayList<>();
        switch(letter)
        {

            case 'A' ->
            {
                int[] indexes = {0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11};
                Feature field = new Feature(Component.FIELD, indexes,     "IsMonastery=true");
                Feature road  = new Feature(Component.ROAD, new int[]{7}, "IsMonastery=true");
                Collections.addAll(features, field, road);
            }
            case 'B' ->
            {
                int[] indexes = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
                Feature field = new Feature(Component.FIELD, indexes, "IsMonastery=true");
                Collections.addAll(features, field);
            }
            case 'C' ->
            {
                int[] indexes = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
                Feature city = new Feature(Component.CITY, indexes,"HasShield=true");
                Collections.addAll(features, city);
            }

            case 'D' ->
            {
                int[] indexesField0 = {0, 8, 9, 10, 11};
                int[] indexesField1 = {2, 6};
                int[] indexesRoad = {1, 7};
                int[] indexesCity = {3, 4, 5};

                Feature field0 = new Feature(Component.FIELD, indexesField0);
                Feature field1 = new Feature(Component.FIELD, indexesField1);
                Feature road   = new Feature(Component.ROAD, indexesRoad);
                Feature city   = new Feature(Component.CITY, indexesCity);

                Collections.addAll(features, field0, field1, road, city);
            }

            case 'E' ->
            {
                int[] indexesCity = {0, 1, 2};
                int[] indexesField0 = {3, 4, 5, 6, 7, 8, 9, 10, 11};
                Feature field0 = new Feature(Component.FIELD, indexesField0);
                Feature city   = new Feature(Component.CITY,  indexesCity);

                Collections.addAll(features, field0, city);
            }
            case 'F' ->
            {
                int[] indexesField0 = {0, 1, 2};
                int[] indexesField1 = {6, 7, 8};
                int[] indexesCity   = {3, 4, 5, 9, 10, 11};

                Feature field0 = new Feature(Component.FIELD, indexesField0);
                Feature field1 = new Feature(Component.FIELD, indexesField1);
                Feature city   = new Feature(Component.CITY, indexesCity, "HasShield");

                Collections.addAll(features, field0, field1, city);
            }
            case 'G' ->
            {
                int[] indexesField0 = {3, 4, 5};
                int[] indexesField1 = {9, 10, 11};
                int[] indexesCity   = {0, 1, 2, 6, 7, 8};

                Feature field0 = new Feature(Component.FIELD, indexesField0);
                Feature field1 = new Feature(Component.FIELD, indexesField1);
                Feature city   = new Feature(Component.CITY, indexesCity);

                Collections.addAll(features, field0, field1, city);
            }
            case 'H' ->
            {
                int[] indexesField0 = {0, 1, 2, 6, 7, 8};
                int[] indexesCity1  = {3, 4, 5}; // todo: check whether it can be one city
                int[] indexesCity2  = {9, 10, 11};

                Feature field0  = new Feature(Component.FIELD, indexesField0);
                Feature city1   = new Feature(Component.CITY, indexesCity1);
                Feature city2   = new Feature(Component.CITY,  indexesCity2);

                Collections.addAll(features, field0, city1, city2);
            }
            case 'I' ->
            {
                int[] indexesField0 = {0, 1, 2, 9, 10, 11};
                int[] indexesCity1  = {3, 4, 5, 6, 7, 8}; // todo: check whether it can be two separate cities

                Feature field0 = new Feature(Component.FIELD, indexesField0);
                Feature city1  = new Feature(Component.CITY, indexesCity1);

                Collections.addAll(features, field0, city1);

            }
            case 'J' ->
            {
                int[] indexesCity0  = {0, 1, 2};
                int[] indexesField0 = {3, 8, 9, 10, 11};
                int[] indexesRoad   = {4, 7};
                int[] indexesField1 = {5, 6};

                Feature field0 = new Feature(Component.FIELD, indexesField0);
                Feature field1 = new Feature(Component.FIELD, indexesField1);
                Feature city1 = new Feature(Component.CITY, indexesCity0);
                Feature road1 = new Feature(Component.ROAD, indexesRoad);

                Collections.addAll(features, field0, field1, city1, road1);

            }
            case 'K' ->
            {
                int[] indexesCity0  = {3, 4, 5};
                int[] indexesField0 = {0, 11};
                int[] indexesRoad   = {1, 10};
                int[] indexesField1 = {2, 6, 7, 8, 9};

                Feature field0 = new Feature(Component.FIELD, indexesField0);
                Feature field1 = new Feature(Component.FIELD, indexesField1);
                Feature city1 = new Feature(Component.CITY, indexesCity0);
                Feature road1 = new Feature(Component.ROAD, indexesRoad);

                Collections.addAll(features, field0, field1, city1, road1);
            }

            case 'L' ->
            {
                int[] indexesCity0 = {3, 4, 5};
                int[] indexesRoad0 = {1};
                int[] indexesRoad1 = {7};
                int[] indexesRoad2 = {10};

                int[] indexesField0 = {0, 11};
                int[] indexesField1 = {2, 6};
                int[] indexesField2 = {8, 9};


                Feature field0 = new Feature(Component.FIELD, indexesField0);
                Feature field1 = new Feature(Component.FIELD, indexesField1);
                Feature field2 = new Feature(Component.FIELD, indexesField2);

                Feature city1 = new Feature(Component.CITY, indexesCity0);

                Feature road0 = new Feature(Component.ROAD, indexesRoad0);
                Feature road1 = new Feature(Component.ROAD, indexesRoad1);
                Feature road2 = new Feature(Component.ROAD, indexesRoad2);


                Collections.addAll(features, field0, field1, field2, road0, road1, road2, city1);
            }
            case 'M' ->
            {
                int[] indexesCity0  = {0, 1, 2, 9, 10, 11};
                int[] indexesField0 = {3, 4, 5, 6, 7, 8};

                Feature field0 = new Feature(Component.FIELD, indexesField0);
                Feature city1  = new Feature(Component.CITY, indexesCity0,"HasShield=true");

                Collections.addAll(features, field0, city1);

            }
            case 'N' ->
            {
                int[] indexesCity0  = {0, 1, 2, 9, 10, 11};
                int[] indexesField0 = {3, 4, 5, 6, 7, 8};

                Feature field0 = new Feature(Component.FIELD, indexesField0);
                Feature city1  = new Feature(Component.CITY, indexesCity0);

                Collections.addAll(features, field0, city1);
            }
            case 'O' ->
            {
                int[] indexesCity0  = {0, 1, 2, 9, 10, 11};
                int[] indexesField0 = {3, 8};
                int[] indexesField1 = {5, 6};
                int[] indexesRoad0  = {4, 7};

                Feature field0 = new Feature(Component.FIELD, indexesField0);
                Feature field1 = new Feature(Component.FIELD, indexesField1);
                Feature city1  = new Feature(Component.CITY, indexesCity0,"HasShield=true");
                Feature road1  = new Feature(Component.ROAD, indexesRoad0);

                Collections.addAll(features, field0, field1, city1, road1);
            }
            case 'P' ->
            {
                int[] indexesCity0  = {0, 1, 2, 9, 10, 11};
                int[] indexesField0 = {3, 8};
                int[] indexesField1 = {5, 6};
                int[] indexesRoad0  = {4, 7};

                Feature field0 = new Feature(Component.FIELD, indexesField0);
                Feature field1 = new Feature(Component.FIELD, indexesField1);
                Feature city1  = new Feature(Component.CITY, indexesCity0);
                Feature road1  = new Feature(Component.ROAD, indexesRoad0);

                Collections.addAll(features, field0, field1, city1, road1);
            }
            case 'Q' ->
            {
                int[] indexesCity0  = {0, 1, 2, 3, 4, 5, 9, 10, 11};
                int[] indexesField0 = {6, 7, 8};

                Feature field0 = new Feature(Component.FIELD, indexesField0);
                Feature city1  = new Feature(Component.CITY, indexesCity0, "HasShield=true");

                Collections.addAll(features, field0, city1);
            }
            case 'R' ->
            {
                int[] indexesCity0  = {0, 1, 2, 3, 4, 5, 9, 10, 11};
                int[] indexesField0 = {6, 7, 8};

                Feature field0 = new Feature(Component.FIELD, indexesField0);
                Feature city1  = new Feature(Component.CITY, indexesCity0);

                Collections.addAll(features, field0, city1);
            }
            case 'S' ->
            {
                int[] indexesCity0  = {0, 1, 2, 3, 4, 5, 9, 10, 11};
                int[] indexesField0 = {6};
                int[] indexesField1 = {8};
                int[] indexesRoad0  = {7};

                Feature field0 = new Feature(Component.FIELD, indexesField0);
                Feature field1 = new Feature(Component.FIELD, indexesField1);
                Feature road1  = new Feature(Component.ROAD, indexesRoad0);
                Feature city1  = new Feature(Component.CITY, indexesCity0,"HasShield=true");

                Collections.addAll(features, field0, field1, road1, city1);
            }
            case 'T' ->
            {
                int[] indexesCity0  = {0, 1, 2, 3, 4, 5, 9, 10, 11};
                int[] indexesField0 = {6};
                int[] indexesField1 = {8};
                int[] indexesRoad0  = {7};

                Feature field0 = new Feature(Component.FIELD, indexesField0);
                Feature field1 = new Feature(Component.FIELD, indexesField1);
                Feature road1  = new Feature(Component.ROAD, indexesRoad0);
                Feature city1  = new Feature(Component.CITY, indexesCity0);

                Collections.addAll(features, field0, field1, road1, city1);
            }
            case 'U' ->
            {
                int[] indexesField0 = {0, 8, 9, 10, 11};
                int[] indexesField1 = {2, 3, 4, 5, 6};
                int[] indexesRoad0  = {1, 7};

                Feature field0 = new Feature(Component.FIELD, indexesField0);
                Feature field1 = new Feature(Component.FIELD, indexesField1);
                Feature road1  = new Feature(Component.ROAD, indexesRoad0);

                Collections.addAll(features, field0, field1, road1);
            }
            case 'V' ->
            {
                int[] indexesField0 = {0, 1, 2, 3, 4, 5, 6, 11};
                int[] indexesField1 = {8, 9};
                int[] indexesRoad0  = {7, 10};

                Feature field0 = new Feature(Component.FIELD, indexesField0);
                Feature field1 = new Feature(Component.FIELD, indexesField1);
                Feature road1  = new Feature(Component.ROAD, indexesRoad0);

                Collections.addAll(features, field0, field1, road1);
            }
            case 'W' ->
            {
                int[] indexesField0 = {0, 1, 2, 3, 11};
                int[] indexesField1 = {5, 6};
                int[] indexesField2 = {8, 9};

                int[] indexesRoad0 = {4};
                int[] indexesRoad1 = {7};
                int[] indexesRoad2 = {10};


                Feature field0 = new Feature(Component.FIELD, indexesField0);
                Feature field1 = new Feature(Component.FIELD, indexesField1);
                Feature field2 = new Feature(Component.FIELD, indexesField2);

                Feature road0 = new Feature(Component.ROAD, indexesRoad0);
                Feature road1 = new Feature(Component.ROAD, indexesRoad1);
                Feature road2 = new Feature(Component.ROAD, indexesRoad2);

                Collections.addAll(features, field0, field1, field2, road0, road1, road2);
            }
            case 'X' ->
            {
                int[] indexesField0 = {0, 11};
                int[] indexesField1 = {2, 3};
                int[] indexesField2 = {5, 6};
                int[] indexesField3 = {8, 9};

                int[] indexesRoad0 = {1};
                int[] indexesRoad1 = {4};
                int[] indexesRoad2 = {7};
                int[] indexesRoad3 = {10};


                Feature field0 = new Feature(Component.FIELD, indexesField0);
                Feature field1 = new Feature(Component.FIELD, indexesField1);
                Feature field2 = new Feature(Component.FIELD, indexesField2);
                Feature field3 = new Feature(Component.FIELD, indexesField3);

                Feature road0 = new Feature(Component.ROAD, indexesRoad0);
                Feature road1 = new Feature(Component.ROAD, indexesRoad1);
                Feature road2 = new Feature(Component.ROAD, indexesRoad2);
                Feature road3 = new Feature(Component.ROAD, indexesRoad3);

                Collections.addAll(features, field0, field1, field2, field3,  road0, road1, road2, road3);
            }
            default  -> features.add(null);
        }
        Feature[] arr = new Feature[features.size()];
        arr = features.toArray(arr);
        return arr;
    }

    public HashMap<String, LibraryEntry> getMapOfEntries() { return this.mapOfEntries;             }
    public LibraryEntry getEntry(String letter)            { return this.mapOfEntries.get(letter); }

    public LibraryEntry getRandomEntry()
//    public Pair<String, LibraryEntry> getRandomEntry()
    {
        List<LibraryEntry> valuesList = new ArrayList<>(this.mapOfEntries.values());
        int randomIndex = new Random().nextInt(valuesList.size());
        return valuesList.get(randomIndex);
//        List<String> valuesList = new ArrayList<>(this.mapOfEntries.keySet());
//        String randomKey = valuesList.get(new Random().nextInt(valuesList.size()));
//        return new Pair<String, LibraryEntry>(randomKey, this.mapOfEntries.get(randomKey));
    }

}




class LibraryEntry
{

    private final String url;
    private Socket[] sockets = new Socket[12];

    public LibraryEntry(String url, Feature ... features)
    {
        this.url = url;

        int cityID  = 0;
        int fieldID = 0;
        int roadID  = 0;
        int counter = 0;
        for (Feature f : features) {
            // welche sockets und typen es hat (component typ)
            if (f.type == Component.CITY)
            {
//                Socket socket = new Socket(f.type, cityID);
                setSockets(f, cityID);
//                sockets[counter] = socket;
                cityID++;
            }

            else if (f.type == Component.ROAD)
            {
//                Socket socket = new Socket(f.type, roadID);
//                sockets[counter] = socket;
                setSockets(f, roadID);
                roadID++;
            }

            else if (f.type == Component.FIELD)
            {
//                Socket socket = new Socket(f.type, fieldID);
//                sockets[counter] = socket;
                setSockets(f, fieldID);
                fieldID++;
            }

            else
                sockets[counter] = null;
            counter++;
        }
    }

    private void setSockets(Feature feature, int id)
    {
        for (int socketIndex : feature.socketIndexes)
        {
            this.sockets[socketIndex] = new Socket(feature.type, id);
        }
    }

    public Socket[] getSockets() { return  this.sockets; }
    public String getUrl()       { return  this.url; }

}
