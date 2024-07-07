package Controllers.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Models.Menu.HomeModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import service.config;

public class EditProfileController {

    @FXML
    private Button beranda;

    @FXML
    private Button editProfile;

    @FXML
    private Button logout;

    @FXML
    private Label tfNamaLengkap;

    @FXML
    private Button statusLaporan;

    @FXML
    private TextField tfUbahNama;

    @FXML
    private Label tfUserType;

    @FXML
    private Button umpanBalik;

    private String username;

    @FXML
    private void initialize() {
        if (username != null) {
            String namaLengkapStr = HomeModel.getNamaLengkap(username);
            if (namaLengkapStr != null) {
                tfNamaLengkap.setText(namaLengkapStr);
            } else {
                tfNamaLengkap.setText("Nama tidak ditemukan");
            }
        }
    }

    @FXML
    void offMouse(MouseEvent event) {
        beranda.setFont(new Font(14));
        editProfile.setFont(new Font(8));
        statusLaporan.setFont(new Font(14));
        umpanBalik.setFont(new Font(14));
        logout.setFont(new Font(14));
    }

    @FXML
    void onMouse(MouseEvent event) {
        beranda.setFont(new Font(15));
        editProfile.setFont(new Font(11));
        statusLaporan.setFont(new Font(15));
        umpanBalik.setFont(new Font(15));
        logout.setFont(new Font(13));
    }

    @FXML
    void tbEditProfile(ActionEvent event) {

    }

    @FXML
    void tblBeranda(ActionEvent event) {
        navigateToPage(event, "/resource/fxml/Menu/HomeView.fxml");
    }

    @FXML
    void tblLogout(ActionEvent event) {
        navigateToPage(event, "/resource/fxml/LoginView.fxml");
    }

    @FXML
    void tblSave(ActionEvent event) {
        String query = "UPDATE tbllogin SET namaLengkap = ? WHERE username = ?";
    
        try (Connection conn = config.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, tfUbahNama.getText());
            pstmt.setString(2, username); 

    
            pstmt.executeUpdate();
            System.out.println("Data berhasil diupdate.");
            tfNamaLengkap.setText(tfUbahNama.getText());
            tfUbahNama.clear();
        } catch (SQLException e) {
            System.out.println("Error di Klik Save: " + e.getMessage());
        }

    }

        public void setUsername(String username) {
        this.username = username;
        initialize();
    }

    @FXML
    void tblStatusLaporan(ActionEvent event) {
        navigateToPage(event, "/resource/fxml/Menu/StatusLaporanView.fxml");
    }

    @FXML
    void tblUmpanBalik(ActionEvent event) {
        navigateToPage(event, "/resource/fxml/Menu/UmpanBalikView.fxml");
    }

        private void navigateToPage(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent nextPage = loader.load();

            // Pass the username to the next controller
            if (fxmlFile.contains("HomeView.fxml")) {
                HomeController homeController = loader.getController();
                homeController.setUsername(username);
            } else if (fxmlFile.contains("StatusLaporanView.fxml")) {
                StatusLaporanController statusLaporanController = loader.getController();
                statusLaporanController.setUsername(username);
            } else if (fxmlFile.contains("UmpanBalikView.fxml")) {
                UmpanBalikController umpanBalikController = loader.getController();
                umpanBalikController.setUsername(username);
            } else if (fxmlFile.contains("EditProfileView.fxml")) {
                EditProfileController editProfileController = loader.getController();
                editProfileController.setUsername(username);
            }

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Create a new scene with the loaded page
            Scene nextScene = new Scene(nextPage);
            stage.setScene(nextScene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Bermasalah: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

