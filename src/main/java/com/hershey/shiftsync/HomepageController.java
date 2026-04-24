package com.hershey.shiftsync;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomepageController {

    @FXML
    private Button registerButton;

    @FXML
    private Button signInButton;

    @FXML
    public void toRegisterPage() throws IOException {
        ShiftSync.changeScene("Register.fxml", "Registration");
    }

    @FXML
    public void toSigninPage() throws IOException {
        ShiftSync.changeScene("Signin.fxml", "Sign In");
    }
}