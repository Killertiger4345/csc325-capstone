package com.hershey.shiftsync;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
        if (emailText.getText().isEmpty() || passwordText.getText().isEmpty()) {
            errorText.setText("ERROR: Email or password empty");
        }
        else {
            /* Add a try/catch block to check for email and password requirements */
            System.out.println("Register button clicked");
            System.out.println("TODO: Register with email " + emailText.getText());
            System.out.println("TODO: Register with password " + passwordText.getText());
        }
    }

    @FXML
    protected void onSignInButtonClick() {

    }

}
