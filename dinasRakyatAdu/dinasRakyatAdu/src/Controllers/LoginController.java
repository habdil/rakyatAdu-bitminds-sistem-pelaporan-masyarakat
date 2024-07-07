package Controllers;

import Models.LoginModel;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Controllers.Alert.*;
import Controllers.Menu.HomeController;
import javafx.scene.Node;

public class LoginController {

    AlertController err = new AlertController();
    AlertController2 scc = new AlertController2();

    @FXML
    private PasswordField tfPassword;

    @FXML
    private TextField tfUsername;

    @FXML
    private Button idLogin;

    @FXML
    void handleLogin(ActionEvent event) {
        String username = tfUsername.getText();
        String password = tfPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            err.showAlert("Gagal Masuk", "Silahkan masukkan Username dan Password Anda");
        } else if (LoginModel.login(username, password)) {
            ProgressIndicator progressIndicator = new ProgressIndicator();
            progressIndicator.getStyleClass().add("elegant-progress-indicator");

            StackPane loadingPane = new StackPane(progressIndicator);
            loadingPane.getStyleClass().add("loading-pane");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene currentScene = stage.getScene();
            currentScene.setRoot(loadingPane);

            Task<Parent> loadTask = new Task<Parent>() {
                @Override
                protected Parent call() throws Exception {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/HomeView.fxml"));
                    Parent nextPage = loader.load();
                    HomeController homeController = loader.getController();
                    homeController.setUsername(username);
                    return nextPage;
                }

                @Override
                protected void succeeded() {
                    Parent nextPage = getValue();
                    Scene nextScene = new Scene(nextPage);
                    stage.setScene(nextScene);
                    stage.show();
                }

                @Override
                protected void failed() {
                    Throwable exception = getException();
                    System.err.println("Bermasalah: " + exception.getMessage());
                    exception.printStackTrace();
                    err.showAlert("Gagal Memuat", "Terjadi kesalahan saat memuat halaman. Silakan coba lagi.");
                    currentScene.setRoot(stage.getScene().getRoot());
                }
            };

            currentScene.getStylesheets().add(getClass().getResource("/Resource/Style/loading.css").toExternalForm());
            new Thread(loadTask).start();
        } else {
            err.showAlert("Gagal Masuk", "Username atau Password salah");
        }
    }

    @FXML
    void tblForgot(ActionEvent event) {
        err.showAlert("Lupa Password", "Silahkan hubungi admin untuk mereset password anda (085975360990)");
    }
}
