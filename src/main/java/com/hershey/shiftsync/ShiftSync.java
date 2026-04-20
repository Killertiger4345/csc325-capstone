package com.hershey.shiftsync;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.*;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.Service;

import com.google.firebase.cloud.FirestoreClient;

public class ShiftSync extends Application {
    public static Firestore fStore;
    public static FirebaseAuth fAuth;
    public static Stage stage;
    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/com/hershey/shiftsync/key.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);

        fStore = FirestoreClient.getFirestore();
        fAuth = FirebaseAuth.getInstance();

        System.out.println(fStore.toString());
        ShiftSync.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(ShiftSync.class.getResource("Authentication.fxml"));
        scene = new Scene(fxmlLoader.load(), 425, 360);
        stage.setTitle("Authentication Page");
        stage.setScene(scene);
        stage.show();
    }

    public static void changeScene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShiftSync.class.getResource(fxml));
        scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}
