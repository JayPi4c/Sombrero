module com.jaypi4c.sombrero {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.jaypi4c.sombrero to javafx.fxml;
    exports com.jaypi4c.sombrero;
}
