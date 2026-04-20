package com.hershey.shiftsync;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.UserRecord;
import com.google.api.core.ApiFuture;
import com.google.common.hash.Hashing;

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


public class DashboardController {
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


}