module com.example.carcassonne {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.carcassonne to javafx.fxml;
    exports com.example.carcassonne;
}