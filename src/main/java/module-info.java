module com.hershey.shiftsync {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.auth.oauth2;
    requires firebase.admin;
    requires google.cloud.firestore;
    requires com.google.auth;
    requires com.google.api.apicommon;
    requires com.google.common;
    requires google.cloud.core;

    opens com.hershey.shiftsync to javafx.fxml;
    exports com.hershey.shiftsync;
}