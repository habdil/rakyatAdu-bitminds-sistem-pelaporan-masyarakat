package Controllers.Alert;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import service.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import Controllers.Menu.DetailLaporanController;

public class TerimaLaporanController {

    @FXML
    private Button idBatal;

    @FXML
    private Button idSetuju;

    private String username;
    private String judulLaporan;
    private Stage parentStage; 

    AlertController2 scc = new AlertController2();

    DetailLaporanController c = new DetailLaporanController();

    public void setUsername(String username) {
        this.username = username;
    }

    public void setJudulLaporan(String judulLaporan) {
        this.judulLaporan = judulLaporan;
    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }

    @FXML
    void clickBatal(ActionEvent event) {
        ((Stage) idBatal.getScene().getWindow()).close();
    }

    @FXML
    void clickSetuju(ActionEvent event) {
        if (judulLaporan == null || username == null) {
            System.err.println("Judul laporan atau username tidak ditemukan.");
            return;
        }

        try {
            updateStatusLaporan(username, judulLaporan, "Ditindak Lanjut");
            if (parentStage != null) {
                parentStage.close();  
            }
            scc.showAlert("Terkirim", "Berhasil menerima laporan");
            ((Stage) idSetuju.getScene().getWindow()).close(); 
        } catch (Exception e) {
            System.err.println("Kesalahan saat memperbarui status laporan: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateStatusLaporan(String username, String judulLaporan, String status) {
        String query = "UPDATE " + username + "_reports SET status = ? WHERE judulLaporan = ?";
        try (Connection connection = config.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, judulLaporan);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Status laporan berhasil diperbarui menjadi '" + status + "'");
            } else {
                System.err.println("Laporan dengan judul '" + judulLaporan + "' tidak ditemukan.");
            }
        } catch (Exception e) {
            System.err.println("Kesalahan saat memperbarui status laporan: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
