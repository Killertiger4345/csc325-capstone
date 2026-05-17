package com.hershey.shiftsync;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class DashboardController {

    @FXML private VBox pageHome;
    @FXML private VBox pageAbout;
    @FXML private VBox pageCalendar;
    @FXML private VBox pageManagers;
    @FXML private VBox pageShifts;

    @FXML private Button btnHome;
    @FXML private Button btnAbout;
    @FXML private Button btnCalendar;
    @FXML private Button btnManagers;
    @FXML private Button btnShifts;

    @FXML
    public void initialize() {}

    @FXML private void showHome()     { showPage(pageHome,     btnHome);     }
    @FXML private void showAbout()    { showPage(pageAbout,    btnAbout);    }
    @FXML private void showCalendar() { showPage(pageCalendar, btnCalendar); }
    @FXML private void showManagers() { showPage(pageManagers, btnManagers); }
    @FXML private void showShifts()   { showPage(pageShifts,   btnShifts);   }

    private void showPage(VBox pageToShow, Button activeButton) {
        pageHome.setVisible(false);
        pageAbout.setVisible(false);
        pageCalendar.setVisible(false);
        pageManagers.setVisible(false);
        pageShifts.setVisible(false);

        pageToShow.setVisible(true);

        btnHome.getStyleClass().remove("nav-active");
        btnAbout.getStyleClass().remove("nav-active");
        btnCalendar.getStyleClass().remove("nav-active");
        btnManagers.getStyleClass().remove("nav-active");
        btnShifts.getStyleClass().remove("nav-active");

        if (!activeButton.getStyleClass().contains("nav-active")) {
            activeButton.getStyleClass().add("nav-active");
        }
    }
}