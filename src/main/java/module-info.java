module com.example.earthdefenders {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.earthdefenders to javafx.fxml;
    exports com.example.earthdefenders;
}