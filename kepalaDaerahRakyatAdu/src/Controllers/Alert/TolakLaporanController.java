package Controllers.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Controllers.Menu.DetailLaporanController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.config;

public class TolakLaporanController {

    private String username;
    private String judulLaporan;
    private Stage parentStage;

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
    private Button idBatal;

    @FXML
    private Button idKirim;

    @FXML
    private TextField tfAlasan;

    AlertController2 scc = new AlertController2();

    @FXML
    void clickBatal(ActionEvent event) {
        // Tutup jendela saat tombol Batal diklik
        ((Stage) idBatal.getScene().getWindow()).close();
    }

    @FXML
    void clickKirim(ActionEvent event) {
        if (judulLaporan == null || username == null) {
            System.err.println("Judul laporan atau username tidak ditemukan.");
            return;
        }

        String alasan = tfAlasan.getText();
        if (alasan == null || alasan.trim().isEmpty()) {
            System.err.println("Alasan penolakan tidak boleh kosong.");
            return;
        }

        try {
            updateStatusLaporan(username, judulLaporan, "Laporan Ditolak", alasan);
            if (parentStage != null) {
                parentStage.close();
                
            }
            scc.showAlert("Terkirim", "Alasan berhasil dikirim");
            ((Stage) idKirim.getScene().getWindow()).close();

        } catch (Exception e) {
            System.err.println("Kesalahan saat memperbarui status laporan: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateStatusLaporan(String username, String judulLaporan, String status, String keterangan) {
        String query = "UPDATE " + username + "_reports SET status = ?, keterangan = ? WHERE judulLaporan = ?";
        try (Connection connection = config.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, keterangan);
            preparedStatement.setString(3, judulLaporan);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Status laporan berhasil diperbarui menjadi '" + status + "' dengan keterangan '" + keterangan + "'");
            } else {
                System.err.println("Laporan dengan judul '" + judulLaporan + "' tidak ditemukan.");
            }
        } catch (Exception e) {
            System.err.println("Kesalahan saat memperbarui status laporan: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
