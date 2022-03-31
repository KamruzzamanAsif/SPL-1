module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens GUI to javafx.fxml;
    exports GUI;
}