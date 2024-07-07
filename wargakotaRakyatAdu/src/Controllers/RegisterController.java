package Controllers;

import Models.RegisterModel;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    private AnchorPane register;

    @FXML
    private TextField tfNamaLengkap;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private PasswordField tfPasswordConfirm;

    @FXML
    private TextField tfusernameRegis;

    AlertController err = new AlertController();
    AlertController2 scc = new AlertController2();

    @FXML
    void tblLogin(ActionEvent event) {
        navigateToLogin(event);
    }

    @FXML
    void handleSignUp(ActionEvent event) {
        String namaLengkap = tfNamaLengkap.getText();
        String username = tfusernameRegis.getText();
        String password = tfPassword.getText();
        String confirmPassword = tfPasswordConfirm.getText();
    
        if (namaLengkap.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            err.showAlert("Registrasi Gagal", "Silahkan isi semua data diri anda");
        } else if (!password.equals(confirmPassword)) {
            err.showAlert("Password Tidak Sesuai", "Mohon disesuaikan kembali passwordnya");
        } else {
            ProgressIndicator progressIndicator = new ProgressIndicator();
            progressIndicator.getStyleClass().add("elegant-progress-indicator");
            
            // Center the progress indicator in the register pane
            AnchorPane.setTopAnchor(progressIndicator, (register.getHeight() - progressIndicator.getPrefHeight()) / 2);
            AnchorPane.setLeftAnchor(progressIndicator, (register.getWidth() - progressIndicator.getPrefWidth()) / 2);
    
            register.getChildren().add(progressIndicator);
    
            Task<Boolean> registrationTask = new Task<>() {
                @Override
                protected Boolean call() {
                    return RegisterModel.insertData(namaLengkap, username, password, confirmPassword);
                }
            };
    
            registrationTask.setOnSucceeded(e -> {
                register.getChildren().remove(progressIndicator); // Remove the progress indicator
                if (registrationTask.getValue()) {
                    navigateToLogin(event);
                    scc.showAlert("Registrasi Berhasil", "Registrasi berhasil, Silahkan Anda Login");
                } else {
                    err.showAlert("Registrasi Gagal", "Terjadi kesalahan saat registrasi, silahkan coba lagi");
                }
            });
    
            registrationTask.setOnFailed(e -> {
                register.getChildren().remove(progressIndicator); // Remove the progress indicator
                err.showAlert("Registrasi Gagal", "Terjadi kesalahan saat registrasi, silahkan coba lagi");
            });
    
            new Thread(registrationTask).start();
        }
    }
    
    

    private void navigateToLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/LoginView.fxml"));
            Parent nextPage = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene nextScene = new Scene(nextPage);
            stage.setScene(nextScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Failed to load the login page.", "/resource/imageassets/error-alert.png");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message, String iconPath) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Load the icon image
        Image icon = new Image(getClass().getResourceAsStream(iconPath));
        ImageView iconView = new ImageView(icon);
        iconView.setFitHeight(30); 
        iconView.setFitWidth(30);   

        alert.getDialogPane().setGraphic(iconView);

        alert.showAndWait();
    }
}
