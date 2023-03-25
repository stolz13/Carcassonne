package com.example.carcassonne;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

public class View {
    //For Attachment and Placement of Nodes
     BorderPane bPane;
     Scene scene;
     Stage stage;
    BorderPane gridPaneContainer;
     GridPane gridPane;
     HBox hBox;
     VBox vBox;

     //GUI Elements for hBox
    Button newTile;
    Button newGame;
    Button zoomIn;
    Button zoomOut;
    Label akteureAnzahl;
    Button rotate;

    // Rectangle - required type for animated rotation
    Rectangle nextTileImg;

    //GUI Elements for vBox
    Label playerScore;
    Label computerScore;

    //Scrollbar
    ScrollPane scrollbar;

    public int rectangleSide = 100;

    //methods
     public void init(){

         //Initialise attachment to tree nodes
         this.bPane=new BorderPane();
         this.gridPane=new GridPane();
         this.hBox=new HBox(20);
         this.vBox=new VBox(10);

         //Initialise GUI elements for hBox
         this.newTile=new Button("New Tile");
         this.newGame=new Button("New Game");
         this.zoomIn=new Button("zoom in");
         this.zoomOut=new Button("zoom out");
         this.akteureAnzahl=new Label("Akteureanzahl:");
         this.rotate= new Button("rotate");

         // nextTileImg
         this.nextTileImg = new Rectangle(80,80);
         this.nextTileImg.setFill(Color.GRAY);
         this.nextTileImg.setId("0");

         //Initialise GUI elements for vBox

         // Player Icon & his score
         Image player=new Image(getClass().getResource("images/Tile_A.png").toExternalForm());
         ImageView imgPlayer=new ImageView();
         imgPlayer.setImage(player);
         imgPlayer.setFitWidth(120);
         imgPlayer.setFitHeight(120);
         this.playerScore=new Label("Playerscore:");

         // Computer Icon & his score
         Image computer=new Image(getClass().getResource("images/Tile_B.png").toExternalForm());
         ImageView imgComputer=new ImageView(computer);
         imgComputer.setFitHeight(120);
         imgComputer.setFitWidth(120);
         this.computerScore=new Label("Computerscore:");

         //Scrollbar
         this.scrollbar=new ScrollPane();
         scrollbar.setContent(this.gridPane);
         scrollbar.setFitToHeight(true);


         //test
//         for (int i=0;i<30;i++){
//             for (int j=0;j<30;j++){
//                 Rectangle rectangle=new Rectangle(50,50);
//                 rectangle.setFill(Color.GRAY);
//                 rectangle.setStroke(Color.WHITE);
//                 rectangle.setId(i + "_" + j);
//                 gridPane.add(rectangle, i, j);
//                 gridPane.setAlignment(Pos.CENTER);
//             }
//         }

         hBox.getChildren().addAll(newTile,newGame,zoomIn,zoomOut,akteureAnzahl,nextTileImg,rotate);
         hBox.setAlignment(Pos.CENTER);
         hBox.setPrefHeight(100);
         hBox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,CornerRadii.EMPTY, Insets.EMPTY)));

         vBox.getChildren().addAll(imgPlayer,playerScore,imgComputer, computerScore);
         vBox.setPrefWidth(150);
         vBox.setAlignment(Pos.CENTER);
         vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW,CornerRadii.EMPTY, Insets.EMPTY)));

//         gridPane.setMinSize(400,400);
         gridPane.setAlignment(Pos.CENTER);
         gridPane.setBackground(new Background(new BackgroundFill(Color.RED,
                 CornerRadii.EMPTY,
                 Insets.EMPTY)));
        VBox paddingTop = new VBox(10);
        HBox paddingRight = new HBox(20);
         paddingTop.setPrefWidth(150);
         paddingRight.setPrefWidth(150);
         paddingTop.setAlignment(Pos.CENTER);
         paddingRight.setAlignment(Pos.CENTER);


         bPane.setCenter(gridPane);
         /////////////////////
//         bPane.setRight(paddingRight);
//         bPane.setTop(paddingTop);
         ////////////////////
         bPane.setLeft(vBox);
//         bPane.setBottom(scrollbar);
         bPane.setBottom(hBox);


         Scene scene = new Scene(bPane, 1000, 600);

         this.stage = new Stage();
         scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
         stage.setTitle("Vikings play Carcassone!");
         stage.setScene(scene);
         stage.show();
     }

     //TODO updateView()
     public void updateView(List<List<Board.Field>> board){
        this.gridPane.getChildren().clear();
         int x = 0;
         int y = 0;
         for (List<Board.Field> row : board){
             for (Board.Field field : row){
                 Rectangle rect = new Rectangle(rectangleSide, rectangleSide);
                 rect.setId(Integer.toString(x) + "_" + Integer.toString(y));
                 Tile tile = field.getTile();
                 if (tile != null) {
                     String path = tile.getImgPath();

                     // preparing a rotated image as a Fill for the Rectangle in gridPane
                     ImageView imgViewTmp = new ImageView(new Image(getClass().getResource(path).toExternalForm()));
                     imgViewTmp.setRotate(tile.getRotation());
                     SnapshotParameters params = new SnapshotParameters();
                     params.setFill(Color.TRANSPARENT);
                     Canvas canvas = new Canvas(100, 100);
                     Image rotatedImage = imgViewTmp.snapshot(params, null);
                     ImagePattern imgPattern = new ImagePattern(rotatedImage);

                     rect.setFill(imgPattern);
                     gridPane.add(rect, x, y);
                 } else {
                     rect.setFill(Color.GRAY);
                     gridPane.add(rect, x, y);
                 }
                 x++;
             }
             y++;
             x = 0;
         }
     }

    //Gridpane

    public GridPane getGridPane() {
        return gridPane;
    }

    //Button
    public Button getNewGame() {
        return newGame;
    }
    public Button getNewTile() {
        return newTile;
    }
    public Button getRotate() {
        return rotate;
    }
    public Button getZoomIn() {
        return zoomIn;
    }
    public Button getZoomOut() {
        return zoomOut;
    }
    //Label
    public Label getAkteureAnzahl() {
        return akteureAnzahl;
    }
    public void setAkteureAnzahl(Label akteureAnzahl) {
        this.akteureAnzahl = akteureAnzahl;
    }
    public Label getComputerScore() {
        return computerScore;
    }
    public void setComputerScore(Label computerScore) {
        this.computerScore = computerScore;
    }
    public Label getPlayerScore() {
        return playerScore;
    }
    public void setPlayerScore(Label playerScore) {
        this.playerScore = playerScore;
    }
    //Image
    public Rectangle getNextTileNode() {
        return nextTileImg;
    }

    public void setNextTileImg(String imgPath) {
        this.nextTileImg.setRotate(0);
        this.nextTileImg.setFill(new ImagePattern(new Image(getClass().getResource(imgPath).toExternalForm())));
    }
}
