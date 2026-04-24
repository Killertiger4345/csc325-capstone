package com.hershey.shiftsync;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.common.hash.Hashing;
import com.google.firebase.auth.UserRecord;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RegisterController {
    @FXML
    private TextField emailText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private TextField firstNameText;

    @FXML
    private TextField lastNameText;

    @FXML
    private TextField phoneText;

    @FXML
    private ChoiceBox userTypeText;

    @FXML
    public Label resultText;

    private static String email, password, fname, lname, phone, userType;
    private String uID;

    @FXML
    protected void onSignInButtonClick() throws IOException { ShiftSync.changeScene("Signin.fxml", "Sign In"); }

    @FXML
    protected void onRegisterButtonClick() { register(); }

    public void register() {
        email = emailText.getText();
        password = passwordText.getText();
        fname = firstNameText.getText();
        lname = lastNameText.getText();
        phone = phoneText.getText();
        userType = userTypeText.getValue().toString();

        if (validate() && !checkDuplicate()) {
            try {
                // Adding User as Authenticated User
                UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                        .setEmail(email)
                        .setEmailVerified(false)
                        .setPassword(password)
                        .setDisabled(false);

                UserRecord userRecord;
                userRecord = ShiftSync.fAuth.createUser(request);
                IO.println("Successfully created new user with Firebase Uid: " + userRecord.getUid());
                resultText.setText("Successfully created new authenticated user");

                // Adding User to USERS Collection
                DocumentReference docRef = ShiftSync.fStore.collection("USERS").document(UUID.randomUUID().toString());
                // Hashing password using Google Guava SHA-256 before storing
                String hashedPass = Hashing
                        .sha256()
                        .hashString(password, StandardCharsets.UTF_8)
                        .toString()
                        .toUpperCase();
                Map<String, Object> data = new HashMap<>();
                data.put("uid", userRecord.getUid());
                data.put("email", email);
                data.put("password", hashedPass);
                data.put("firstName", fname);
                data.put("lastName", lname);
                data.put("phone", phone);
                data.put("userType", userType);

                IO.println("Successfully created new user document: " + userRecord.getUid());
                resultText.setText("Successfully created new user!");

                ApiFuture<WriteResult> result = docRef.set(data);

                resultText.setText("Successfully created new user! Please sign in.");
            }
            catch (Exception e) {
                IO.println("Error creating user; " + e.getMessage());
                resultText.setText("Error creating a new user in Firebase");
            }
        }
    }

    private boolean validate() {
        // Check EMAIL and PASSWORD aren't empty
        if (email.isEmpty() || password.isEmpty()) {
            IO.println("Email or password is empty");
            resultText.setText("Email or password is empty");
            return false;
        }
        // Check PASSWORD >= 6 characters
        if (password.length() < 6) {
            IO.println("Password is too short");
            resultText.setText("Password is too short");
            return false;
        }
        // Check EMAIL contains '@' and '.' (validate EMAIL)
        if (!email.contains("@")) {
            IO.println("Email is empty");
            resultText.setText("Email is invalid");
            return false;
        }
        if (!email.contains(".")) {
            IO.println("Email is invalid");
            resultText.setText("Email is invalid");
            return false;
        }
        return true;
    }

    private boolean checkDuplicate(){
        // Pull USER login information from Firebase, then check to see if email already in use
        try {
            ApiFuture<QuerySnapshot> future = ShiftSync.fStore.collection("USERS").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            if (!documents.isEmpty()) {
                for (QueryDocumentSnapshot document : documents) {
                    String storedEmail = document.getData().get("email").toString();
                    if (storedEmail != null && storedEmail.equals(email)) {
                        uID = document.getData().get("uid").toString();
                        resultText.setText("Email already exists");
                        return true;
                    }
                }
            }
        }
        catch (Exception e) {
            IO.println("Error getting duplicate email" + e.getMessage());
            resultText.setText("Error reading database");
            return false;
        }
        return false;
    }
}
