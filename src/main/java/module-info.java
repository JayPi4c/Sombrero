module com.jaypi4c.mexicanhat {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.jaypi4c.mexicanhat to javafx.fxml;
    exports com.jaypi4c.mexicanhat;
}