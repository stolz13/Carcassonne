package com.example.carcassonne;
import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// TODO посчитать очки после каждого хода
// TODO в модели добавить метод, показывать клетки, в которые можно положить nextTile


public class HelloController {
    Model model;
    View view;
    public HelloController(Model model, View view){
        this.model = model;
        this.view = view;
    }
    void init(){
//    model.getNextTile()
        view.init();
        // choosing first nextTile
        model.chooseNextTile();
        LibraryEntry nextTile = model.getNextTile();
        String nextTileImgPath = nextTile.getUrl();
        view.setNextTileImg(nextTileImgPath);
        view.getRotate().setOnMouseClicked(e -> {
//            view.getRotate().setDisable(true);
            Rectangle nextTileImg = view.getNextTileNode();
            RotateTransition trans = new RotateTransition(Duration.millis(500), nextTileImg);
            trans.setByAngle(90);
            System.out.println(nextTileImg.getRotate());
            view.getRotate().setDisable(true);

            trans.play();

            switch (view.getNextTileNode().getId()) {
                case "0" -> {
                    view.getNextTileNode().setId("1");
                }
                case "1" -> {
                    view.getNextTileNode().setId("2");
                }
                case "2" -> {
                    view.getNextTileNode().setId("3");
                }
                case "3" -> {
                    view.getNextTileNode().setId("0");
                }

            }
        });



        //Zoom In
        Button zoomInBtn = this.view.getZoomIn();
        GridPane gridPane = this.view.getGridPane();
        zoomInBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                gridPane.setScaleX(gridPane.getScaleX() + 0.1);
                gridPane.setScaleY(gridPane.getScaleY() + 0.1);
//        gridPane.setPadding(new Insets(gridPane.getPadding().getLeft() * 0.1, gridPane.getPadding().getTop() * 0.1, gridPane.getPadding().getRight() * 0.1, gridPane.getPadding().getBottom() * 0.1));
            }
        });
        // Zoom Out
        Button zoomOutBtn = this.view.getZoomOut();
        zoomOutBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                gridPane.setScaleX(gridPane.getScaleX() - 0.1);
                gridPane.setScaleY(gridPane.getScaleY() - 0.1);
//        gridPane.setPadding(new Insets(gridPane.getPadding().getLeft() * 0.1, gridPane.getPadding().getTop() * 0.1, gridPane.getPadding().getRight() * 0.1, gridPane.getPadding().getBottom() * 0.1));
            }
        });

        this.view.updateView(model.getBoard().getBoardArray());
        // Adding Drag Actions
        Rectangle nextTileImg = view.getNextTileNode();
        nextTileImg.setOnDragDetected(e ->{
            Double rotateNumber = nextTileImg.getRotate();

            Dragboard dragboard = nextTileImg.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            String rotateNumberString = Double.toString(nextTileImg.getRotate());
            String nextTileImgPathTMP = model.getNextTile().getUrl();

            // creating a rotated image for dragView

            ImageView imgViewTmp = new ImageView(new Image(getClass().getResource(nextTileImgPathTMP).toExternalForm()));
            imgViewTmp.setRotate(rotateNumber);
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            Canvas canvas = new Canvas(100, 100);
            Image rotatedImage = imgViewTmp.snapshot(params, null);


            String dataTransfer = rotateNumberString + "===" + nextTileImgPathTMP;
            content.putString(dataTransfer);
            dragboard.setContent(content);

//            System.out.println("dataTransferString: " + dataTransfer);
            dragboard.setDragView(rotatedImage, -500, 500);
            e.consume();
        });

        this.view.getNewGame().setOnMouseClicked(e -> {
//            this.view.init();
            // choosing first nextTile
            this.model = new Model();
            this.model.chooseNextTile();
            this.view.updateView(model.getBoard().getBoardArray());
            view.setNextTileImg(model.getNextTile().getUrl());
            addEventsToAllTiles();
            model.printBoard();

        });
        addEventsToAllTiles();
        model.printBoard();
    }
    public void addEventsToAllTiles(){
        // Adds dradDropped to all null Rectangles
        List<Rectangle> allTiles = view.getGridPane().getChildren().stream().map(e -> (Rectangle) e).collect(Collectors.toList());
        for (Rectangle r : allTiles){

            // get absolute coordinates of field
            String[] absoluteCoordinates = r.getId().split("_");
            int absoluteX = Integer.parseInt(absoluteCoordinates[0]);
            int absoluteY = Integer.parseInt(absoluteCoordinates[1]);

            Tile actualTile = model.readTile(absoluteX, absoluteY);

            r.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    if (dragEvent.getGestureSource() != r && dragEvent.getDragboard().hasString()){

                        if (actualTile == null) {
                            dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                            r.setFill(Color.SALMON);
                        }
                    }
                    dragEvent.consume();
                }
            });

            r.setOnDragExited(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    if (actualTile == null) {
                        r.setFill(Color.GRAY);
                    }
                }
            });


            r.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {



//                    Tile actualTile = model.readTile(absoluteX, absoluteY);
                    Dragboard db = dragEvent.getDragboard();
                    // so only if field is empty
                    if(db.hasString()){
                        boolean success = false;
                        if (actualTile == null){
                            String[] dataTransfer = db.getString().split("===");
                            int rotateNumberNumber = (int) Double.parseDouble(dataTransfer[0]);
                            String nextTileImgPathTMP = dataTransfer[1];
                            int[] relativeCoordinates = model.getBoard().getRelativeCoordinates(absoluteX, absoluteY);

                            Tile newTile = new Tile(relativeCoordinates[0], relativeCoordinates[1], model.getNextTile().getSockets(), rotateNumberNumber);
                            newTile.setImgPath(nextTileImgPathTMP);


                            System.out.println("Sockets of currently placed tile before rotation: ");
                            newTile.printSockets();
                            for (int i = 0; i < Integer.parseInt(view.getNextTileNode().getId()); i++) {
                                newTile.rotateRight();
                            }
                            System.out.println("Sockets of currently placed tile after rotation: ");
                            newTile.printSockets();
                            model.getBoard().setTileByAbsoluteCoordinates(absoluteX, absoluteY, newTile);
//                            model.getBoard().setTileByRelativeCoordinates(relativeCoordinates[0], relativeCoordinates[1], newTile);
                            view.updateView(model.getBoard().getBoardArray());




                            model.chooseNextTile();
                            view.setNextTileImg(model.getNextTile().getUrl());
                            success = true;
//                            System.out.println("dropped success");
                            model.printBoard();

                        }
                        dragEvent.setDropCompleted(success);
                        dragEvent.consume();
                        addEventsToAllTiles();
                    }
                }
            });
        }
    }
}
