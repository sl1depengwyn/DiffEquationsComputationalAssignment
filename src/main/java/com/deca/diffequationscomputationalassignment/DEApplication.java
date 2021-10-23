package com.deca.diffequationscomputationalassignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DEApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DEApplication.class.getResource("main-scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("y'=y(y-1)/x");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}