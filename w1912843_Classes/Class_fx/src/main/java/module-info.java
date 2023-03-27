module com.example.class_fx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.class_fx to javafx.fxml;
    exports com.example.class_fx;
}