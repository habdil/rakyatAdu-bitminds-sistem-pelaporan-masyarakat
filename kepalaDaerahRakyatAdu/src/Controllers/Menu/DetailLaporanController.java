package Controllers.Menu;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.config;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Controllers.Alert.AlertController2;
import Controllers.Alert.TerimaLaporanController;
import Controllers.Alert.TolakLaporanController;

public class DetailLaporanController {
    AlertController2 scc = new AlertController2();

    @FXML
    private Label lbDeskripsi;

    @FXML
    private Label lbInstansi;

    @FXML
    private Label lbJudulLaporan;

    @FXML
    private Label lbKategori;

    @FXML
    private Label lbLokasi;

    @FXML
    private Label lbNamaPelapor;

    @FXML
    private Label lbTgl;

    @FXML
    private Label lbStatus;

    @FXML
    private Label lbKeterangan;

    @FXML
    private Button lblBtnClose;

    @FXML
    private Button lblBtnTerima;

    @FXML
    private Button lblBtnTolak;

    @FXML
    private Button lblImage;

    @FXML
    private Button selesai;

    private String username;
    private int reportId;
    private Stage stage;

    private String imagePath;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void clickClose(ActionEvent event) {
        ((Stage) lblBtnClose.getScene().getWindow()).close();
    }

    @FXML
    void clickSelesai(ActionEvent event) {
        String judulLaporan = lbJudulLaporan.getText();
        if (judulLaporan == null || username == null) {
            System.err.println("Judul laporan atau username tidak ditemukan.");
            return;
        }

        try {
            // Call your method to update the report status
            updateStatusLaporan(username, judulLaporan, "Laporan Selesai");

            // Close the parent stage if it's not null
            if (stage != null) {
                stage.close();
            }

            // Show success alert
            // Assuming you have a method to show alerts in your class
            scc.showAlert("Terkirim", "Laporan berhasil dikirim");

            // Close the current stage
            ((Stage) selesai.getScene().getWindow()).close();
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

@FXML
void clickImage(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/ImageView.fxml"));
        AnchorPane imagePane = loader.load();
        ImageController controller = loader.getController();

        if (imagePath != null && !imagePath.isEmpty()) {
            ProgressIndicator progressIndicator = new ProgressIndicator();
            progressIndicator.getStyleClass().add("elegant-progress-indicator");
            imagePane.getChildren().add(progressIndicator); 

            Task<Image> loadImageTask = new Task<>() {
                @Override
                protected Image call() {
                    File file = new File(imagePath);
                    return new Image(file.toURI().toString());
                }
            };

            loadImageTask.setOnSucceeded(e -> {
                controller.setImage(loadImageTask.getValue());
                imagePane.getChildren().remove(progressIndicator); 
            });
            loadImageTask.setOnFailed(e -> {
                System.err.println("Kesalahan menampilkan gambar: " + loadImageTask.getException().getMessage());
                loadImageTask.getException().printStackTrace();
                imagePane.getChildren().remove(progressIndicator);
            });

            new Thread(loadImageTask).start();
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

    @FXML
    void clickTolak(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/TolakLaporanView.fxml"));
            AnchorPane detailPane = loader.load();

            TolakLaporanController tolak = loader.getController();
            tolak.setUsername(this.username);
            tolak.setJudulLaporan(lbJudulLaporan.getText());
            tolak.setParentStage(this.stage);

            Stage stage = new Stage();
            stage.setScene(new Scene(detailPane));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (Exception e) {
            System.err.println("Kesalahan menampilkan detail laporan: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void clickTerima(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/TerimaLaporanView.fxml"));
            AnchorPane detailPane = loader.load();

            TerimaLaporanController terimaLaporanController = loader.getController();
            terimaLaporanController.setUsername(this.username);
            terimaLaporanController.setJudulLaporan(lbJudulLaporan.getText());
            terimaLaporanController.setParentStage(this.stage);

            Stage stage = new Stage();
            stage.setScene(new Scene(detailPane));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (Exception e) {
            System.err.println("Kesalahan menampilkan detail laporan: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadDetailLaporan(String username, int id) {
        this.username = username;
        this.reportId = id;

        try (Connection connection = config.getConnection()) {
            String query = "SELECT namaLengkap, judulLaporan, isiLaporan, timestamp AS tanggal, lokasi, kategori, instansi, status, keterangan, file_path FROM " + username + "_reports WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String namaLengkap = resultSet.getString("namaLengkap");
                String judulLaporan = resultSet.getString("judulLaporan");
                String deskripsi = resultSet.getString("isiLaporan");
                String tanggal = resultSet.getString("tanggal");
                String lokasi = resultSet.getString("lokasi");
                String kategori = resultSet.getString("kategori");
                String instansi = resultSet.getString("instansi");
                String status = resultSet.getString("status");
                String keterangan = resultSet.getString("keterangan");
                imagePath = resultSet.getString("file_path");

                setDetailLaporan(judulLaporan, namaLengkap, deskripsi, tanggal, lokasi, kategori, instansi, status, keterangan);
            }
        } catch (Exception e) {
            System.err.println("Kesalahan memuat detail laporan: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setDetailLaporan(String judulLaporan, String namaPelapor, String isiLaporan, String tanggal, String lokasi, String kategori, String instansi, String status, String keterangan) {
        lbJudulLaporan.setText(judulLaporan);
        lbNamaPelapor.setText(namaPelapor);
        lbDeskripsi.setText(isiLaporan);
        lbTgl.setText(tanggal);
        lbLokasi.setText(lokasi);
        lbKategori.setText(kategori);
        lbInstansi.setText(instansi);
        lbStatus.setText(status);
        lbKeterangan.setText(keterangan);
    }

}
