package com.hershey.shiftsync;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.UserRecord;
import com.google.api.core.ApiFuture;
import com.google.common.hash.Hashing;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class ShiftSyncController {
    @FXML
    private TextField emailText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Label errorText;

    @FXML
    private Button register;

    @FXML
    private Button signIn;

    @FXML
    protected void onRegisterButtonClick() {
        addUser();
    }

    @FXML
    protected void onSignInButtonClick() {

    }


    private void addUser() {
        if (emailText.getText().isEmpty() || passwordText.getText().isEmpty()) {
            errorText.setText("ERROR: Email or password empty");
        } else {
            /* Add a try/catch block to check for email and password requirements */
            System.out.println("Register button clicked");

            System.out.println("TODO: Register with email " + emailText.getText());
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(emailText.getText())
                    .setPassword(passwordText.getText());

            UserRecord userRecord;
            try {
                /* Adding User as Firebase User in Firebase Authentication */
                userRecord = ShiftSync.fAuth.createUser(request);
                errorText.setText("Successfully created a new user with Uid: " + userRecord.getUid());
                System.out.println("Successfully created a new user with Uid: " + userRecord.getUid());
                System.out.println(userRecord.toString());

                /* Adding User to Users Database */
                DocumentReference docRef = ShiftSync.fStore.collection("Users").document(UUID.randomUUID().toString());

                Map<String, Object> data = new HashMap<>();
                data.put("email", emailText.getText());
                data.put("password", passwordText.getText());

                ApiFuture<WriteResult> result = docRef.set(data);
            } catch (Exception e) {
                System.out.println("Error creating user: " + e);
            }

            System.out.println("TODO: Register with password " + passwordText.getText());
        }
    }
}