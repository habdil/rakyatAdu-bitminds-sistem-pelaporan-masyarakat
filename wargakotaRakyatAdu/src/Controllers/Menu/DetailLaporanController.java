package Controllers.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.AnchorPane;
import service.config;
import java.io.File;

public class DetailLaporanController {

    @FXML
    private Button image;

    @FXML
    private Button lbClose;

    @FXML
    private Label lbDeskripsi;

    @FXML
    private Label lbInstansi;

    @FXML
    private Label lbJudul;

    @FXML
    private Label lbKategori;

    @FXML
    private Label lbLokasi;

    @FXML
    private Label lbStatus;

    @FXML
    private Label lbTgl;

    @FXML
    private Label alasan;

    private String imagePath;

    @FXML
    void clickClose(ActionEvent event) {
        // Handle close action
        Stage stage = (Stage) lbClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void clickImage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/Menu/ImageView.fxml"));
            AnchorPane imagePane = loader.load();
            ImageController controller = loader.getController();

            if (imagePath != null && !imagePath.isEmpty()) {
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                controller.setImage(image);
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(imagePane));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (Exception e) {
            System.err.println("Kesalahan menampilkan gambar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void detaiLaporan(String username, int id) {
        try (Connection conn = config.getConnection()) {
            String query = "SELECT judulLaporan, isiLaporan, lokasi, timestamp AS tanggal , instansi, kategori, status, file_path, keterangan FROM " + username + "_reports WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String judulLaporan = rs.getString("judulLaporan");
                String isiLaporan = rs.getString("isiLaporan");
                String lokasi = rs.getString("lokasi");
                String tanggal = rs.getString("tanggal");
                String instansi = rs.getString("instansi");
                String kategori = rs.getString("kategori");
                String status = rs.getString("status");
                imagePath = rs.getString("file_path");
                String alasan = rs.getString("keterangan");

                setDetailLaporan(judulLaporan, isiLaporan, lokasi, tanggal, instansi, kategori, status, alasan);
                setLaporanDetails(status);
            }
        } catch (Exception e) {
            System.err.println("Kesalahan memuat detail laporan: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setDetailLaporan(String judulLaporan, String isiLaporan, String lokasi, String tanggal, String instansi, String kategori, String status, String keterangan) {
        lbJudul.setText(judulLaporan);
        lbDeskripsi.setText(isiLaporan);
        lbTgl.setText(tanggal);
        lbLokasi.setText(lokasi);
        lbInstansi.setText(instansi);
        lbKategori.setText(kategori);
        lbStatus.setText(status);
        alasan.setText(" : " + keterangan);
    }

    public void setLaporanDetails(String status) {
        lbStatus.setText(status);

        lbStatus.getStyleClass().clear();
        lbStatus.getStyleClass().add("label");

        switch (status) {
            case "Proses Verifikasi":
                lbStatus.getStyleClass().add("status-verifikasi");
                break;
            case "Ditindak Lanjut":
                lbStatus.getStyleClass().add("status-ditindak-lanjut");
                break;
            case "Laporan Ditolak":
                lbStatus.getStyleClass().add("status-ditolak");
                break;
        }
    }

    @FXML
    void offMouse(MouseEvent event) {

    }

    @FXML
    void onMouse(MouseEvent event) {

    }
}
