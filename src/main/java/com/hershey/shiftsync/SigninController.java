package com.hershey.shiftsync;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.common.hash.Hashing;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class SigninController {
    @FXML
    private TextField emailText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Label resultText;

    private String email, password, uID;

    @FXML
    protected void onRegisterButtonClick() throws IOException { ShiftSync.changeScene("Register.fxml", "Registration"); }

    @FXML
    protected void onSignInButtonClick() { signIn(); }

    public void signIn() {
        email = emailText.getText();
        password = passwordText.getText();

        if (validate() && checkDuplicate()) {
            try {
                // get all users
                ApiFuture<QuerySnapshot> future = ShiftSync.fStore.collection("USERS").get();
                List<QueryDocumentSnapshot> documents = future.get().getDocuments();

                String storedPassword = null;
                // find user based off UI
                for (QueryDocumentSnapshot document : documents) {
                    String docUID = document.getData().get("uid").toString();
                    if (docUID.equals(uID)) {
                        storedPassword = document.getData().get("password").toString();
                    }
                }

                if (storedPassword != null) {
                    // compare hashes to verify if password is correct
                    String hashedPass = Hashing
                            .sha256()
                            .hashString(password, StandardCharsets.UTF_8)
                            .toString()
                            .toUpperCase();
                    if (hashedPass.equals(storedPassword)) {
                        IO.println("Successful login");
                        resultText.setText("Successful login");
                        ShiftSync.changeScene("Dashboard.fxml", "Dashboard");
                    }
                    else {
                        IO.println("Unsuccessful login");
                        resultText.setText("Password incorrect");
                    }
                }
                else {
                    IO.println("not found?");
                }

            }
            catch (Exception e) {
                IO.println("Error signing in; " + e.getMessage());
                resultText.setText(e.getMessage());
            }
        }
        else  {
            IO.println("Invalid email or password");
            resultText.setText("Invalid email. Please register first");
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
            IO.println("Email or password is empty");
            resultText.setText("Password is too short");
            return false;
        }
        // Check EMAIL contains '@' and '.' (validate EMAIL)
        if (!email.contains("@")) {
            IO.println("Email or password is empty");
            resultText.setText("Email or password is invalid");
            return false;
        }
        if (!email.contains(".")) {
            IO.println("Email or password is empty");
            resultText.setText("Email or password is invalid");
            return false;
        }
        return true;
    }

    private boolean checkDuplicate(){
        // Pull USER login information from Firebase, then check to see if email already in use
        String storedEmail = "";
        try {
            ApiFuture<QuerySnapshot> future = ShiftSync.fStore.collection("USERS").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            if (!documents.isEmpty()) {
                for (QueryDocumentSnapshot document : documents) {
                    storedEmail = document.getData().get("email").toString();
                    if (storedEmail != null && storedEmail.equals(email)) {
                        uID = document.getData().get("uid").toString();
                        IO.println("Email already in use");
                        resultText.setText("Email already exists");
                        return true;
                    }
                }
            }
            else {
                resultText.setText("Email not registered, please register first");
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
