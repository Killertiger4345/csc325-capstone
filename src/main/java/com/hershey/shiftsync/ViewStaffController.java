package com.hershey.shiftsync;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ViewStaffController {
    @FXML
    private TableView<Person> managerTableView;

    @FXML
    private TableColumn<Person, String> managerEmailTableColumn;

    @FXML
    private TableColumn<Person, String> managerFirstNameTableColumn;

    @FXML
    private TableColumn<Person, String> managerLastNameTableColumn;

    @FXML
    private TableColumn<Person, String> managerPhoneTableColumn;

    @FXML
    private TableView<Person> supervisorTableView;

    @FXML
    private TableColumn<Person, String> supervisorEmailTableColumn;

    @FXML
    private TableColumn<Person, String> supervisorFirstNameTableColumn;

    @FXML
    private TableColumn<Person, String> supervisorLastNameTableColumn;

    @FXML
    private TableColumn<Person, String> supervisorPhoneTableColumn;

    @FXML
    private TableView<Person> employeeTableView;

    @FXML
    private TableColumn<Person, String> employeeEmailTableColumn;

    @FXML
    private TableColumn<Person, String> employeeFirstNameTableColumn;

    @FXML
    private TableColumn<Person, String> employeeLastNameTableColumn;

    @FXML
    private TableColumn<Person, String> employeePhoneTableColumn;

    @FXML
    protected void onBackButtonClick() throws IOException {
        ShiftSync.changeScene("Dashboard.fxml", "Dashboard");
    }

    @FXML
    public void initialize() {
        //Set cell factories for loading Person objects to tables
        managerEmailTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        managerFirstNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        managerLastNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        managerPhoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));

        supervisorEmailTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        supervisorFirstNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        supervisorLastNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        supervisorPhoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));

        employeeEmailTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        employeeFirstNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        employeeLastNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        employeePhoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));

        //Fill supervisor and employee tables
        try {
            ApiFuture<QuerySnapshot> future = ShiftSync.fStore.collection("USERS").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            if (!documents.isEmpty()) {
                for (QueryDocumentSnapshot document : documents) {
                    //Map of person info
                    Map<String, Object> info = document.getData();

                    String userType = (String) info.get("userType");

                    Person person = new Person(
                            (String) info.get("email"),
                            (String) info.get("password"),
                            (String) info.get("phone"),
                            (String) info.get("firstName"),
                            (String) info.get("lastName")
                    );

                    if(userType.equals("Manager")) {
                        managerTableView.getItems().add(person);
                    }
                    else if(userType.equals("Supervisor")) {
                        supervisorTableView.getItems().add(person);
                    }
                    else if(userType.equals("Employee")) {
                        employeeTableView.getItems().add(person);
                    }
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            System.err.println("Error accessing database: " + e);
        }
    }
}
