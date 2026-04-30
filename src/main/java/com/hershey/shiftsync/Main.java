package com.hershey.shiftsync;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
<<<<<<<< HEAD:src/main/java/com/hershey/shiftsync/Main.java
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Calendar.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
========
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Calander.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
>>>>>>>> origin/Calendar:src/main/java/com/hershey/shiftsync/HelloApplication.java
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}