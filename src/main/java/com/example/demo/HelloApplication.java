package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/demo/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 650);

        String css = HelloApplication.class.getResource("/com/example/demo/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("ShiftSync - Work Shift Management");
        stage.setMinWidth(800);
        stage.setMinHeight(520);
        stage.setScene(scene);
        stage.show();
    }
}