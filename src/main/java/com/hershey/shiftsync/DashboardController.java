package com.hershey.shiftsync;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DashboardController {

    @FXML private VBox pageHome;
    @FXML private VBox pageAbout;

    @FXML private Button btnHome;
    @FXML private Button btnAbout;
    @FXML private Button btnCalendar;
    @FXML private Button btnStaff;

    @FXML
    public void initialize() {}

    @FXML private void showHome()     { showPage(pageHome,     btnHome);     }
    @FXML private void showAbout()    { showPage(pageAbout,    btnAbout);    }
    @FXML private void showCalendar() throws IOException { ShiftSync.changeScene("Calendar.fxml", "Calendar View"); }
    @FXML private void showStaff() throws IOException { ShiftSync.changeScene("ViewStaff.fxml", "Staff View") ; }

    private void showPage(VBox pageToShow, Button activeButton) {
        pageHome.setVisible(false);
        pageAbout.setVisible(false);

        pageToShow.setVisible(true);

        btnHome.getStyleClass().remove("nav-active");
        btnAbout.getStyleClass().remove("nav-active");
        btnCalendar.getStyleClass().remove("nav-active");
        btnStaff.getStyleClass().remove("nav-active");

        if (!activeButton.getStyleClass().contains("nav-active")) {
            activeButton.getStyleClass().add("nav-active");
        }
    }
}