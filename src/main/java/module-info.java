module com.deca.diffequationscomputationalassignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.deca.diffequationscomputationalassignment to javafx.fxml;
    exports com.deca.diffequationscomputationalassignment;
}