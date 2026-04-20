package com.hershey.shiftsync;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.api.core.ApiFuture;
import com.google.common.hash.Hashing;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
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
    protected void onRegisterButtonClick() { addUser();
    }

    @FXML
    protected void onSignInButtonClick() {
        try {

        }
        catch (Exception e) {
            errorText.setText(e.getMessage());
        }
    }

    private void addUser() {
        if ((!checkDuplicate()) && validate()) {
            try {
                // Adding User as Authenticated User
                UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                        .setEmail(emailText.getText())
                        .setEmailVerified(false)
                        .setPassword(passwordText.getText())
                        .setDisabled(false);

                UserRecord userRecord;
                userRecord = ShiftSync.fAuth.createUser(request);
                IO.println("Successfully created new user with Firebase Uid: " + userRecord.getUid()
                            + " check Firebase > Authentication > Users tab");
                errorText.setText("Successfully created new user!");

                // Adding User to USERS Database
                DocumentReference docRef = ShiftSync.fStore.collection("Users").document(UUID.randomUUID().toString());
                // Hashing password using Google Guava SHA-256 before storing
                String pass = Hashing
                        .sha256()
                        .hashString(passwordText.getText(), StandardCharsets.UTF_8)
                        .toString()
                        .toUpperCase();
                Map<String, Object> data = new HashMap<>();
                data.put("uid", userRecord.getUid());
                data.put("email", emailText.getText());
                data.put("password", pass);


                ApiFuture<WriteResult> result = docRef.set(data);
            } catch (Exception e) {
                IO.println("Error creating user; " + e);
                errorText.setText("Error creating a new user in Firebase");
            }
        }
    }

    private boolean validate() {
        String email = emailText.getText();
        String password = passwordText.getText();

        // Check EMAIL and PASSWORD aren't empty
        if (email.isEmpty() || password.isEmpty()) {
            IO.println("Email or password is empty");
            errorText.setText("Email or password is empty");
            return false;
        }
        // Check PASSWORD >= 6 characters
        if (password.length() < 6) {
            IO.println("Email or password is empty");
            errorText.setText("Password is too short");
            return false;
        }
        // Check EMAIL contains '@' and '.' (validate EMAIL)
        if (!email.contains("@")) {
            IO.println("Email or password is empty");
            errorText.setText("Email or password is invalid");
            return false;
        }
        if (!email.contains(".")) {
            IO.println("Email or password is empty");
            errorText.setText("Email or password is invalid");
            return false;
        }
        return true;
    }

    public boolean checkDuplicate(){
        String email = emailText.getText();
        String password = passwordText.getText();

        // Pull USER login information from Firebase, then check to see if email already in use
        try {
            ApiFuture<QuerySnapshot> future = ShiftSync.fStore.collection("Users").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            if (!documents.isEmpty()) {
                for (QueryDocumentSnapshot document : documents) {
                    String storedEmail = document.getData().get("email").toString();
                    if (storedEmail != null && storedEmail.equals(email)) {
                        errorText.setText("Email already exists");
                        return true;
                    }
                }
            }
        }
        catch (Exception e) {
            errorText.setText("Error reading database");
            return false;
        }

        return false;
    }

}
