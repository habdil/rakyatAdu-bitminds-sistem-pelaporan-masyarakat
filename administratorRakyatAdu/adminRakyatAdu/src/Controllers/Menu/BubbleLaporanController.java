package Controllers.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Models.Menu.DisplayLaporan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.config;

import java.util.List;

public class BubbleLaporanController {

    @FXML
    private Button btnDetail;

    @FXML
    private Label lbJudul;

    @FXML
    private Label lbStatus;

    @FXML
    private Label lbUsername;

    @FXML
    private AnchorPane laporanContainer;

    private DisplayLaporan displayLaporan;
    private Timeline timeline;

    public Label getLbJudul() {
        return lbJudul;
    }

    public void setLbJudul(Label lbJudul) {
        this.lbJudul = lbJudul;
    }

    public Label getLbUsername() {
        return lbUsername;
    }

    public void setLbUsername(Label lbUsername) {
        this.lbUsername = lbUsername;
    }

    public BubbleLaporanController() {
        this.displayLaporan = new DisplayLaporan();
    }

    public void setBubbleLaporan(String username, String judul, String status) {
        lbUsername.setText(username);
        lbJudul.setText(judul);
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
            case "Laporan Selesai":
                lbStatus.getStyleClass().add("status-verifikasi");
                break;
        }
    }

    @FXML
    private void initialize() {
        List<String> usernames = displayLaporan.ambilUsername();
        if (!usernames.isEmpty()) {
            loadLaporan(usernames);
        } else {
            System.err.println("Tidak ditemukan username.");
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> loadLaporan(usernames)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void loadLaporan(List<String> usernames) {
        // Implement the logic to load the report details for the given usernames.
        // This method should update the UI with the report details.
    }

    @FXML
    void clickDetail(ActionEvent event) {
        String judulLaporan = lbJudul.getText().trim();
        if (judulLaporan.isEmpty()) {
            System.err.println("Judul laporan tidak diset. Tidak dapat memuat laporan.");
            return;
        }

        List<String> usernames = displayLaporan.ambilUsername();
        boolean reportFound = false;

        for (String username : usernames) {
            if (searchReport(username, judulLaporan)) {
                reportFound = true;
                break;
            }
        }

        if (!reportFound) {
            System.err.println("Laporan dengan judul '" + judulLaporan + "' tidak ditemukan di semua tabel.");
        }
    }

    private boolean searchReport(String username, String judulLaporan) {
        try (Connection connection = config.getConnection()) {
            String query = "SELECT id, namaLengkap FROM " + username + "_reports WHERE judulLaporan = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, judulLaporan);

            System.out.println("Executing query: " + query + " with judulLaporan: " + judulLaporan);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String namaLengkap = resultSet.getString("namaLengkap");
                System.out.println("Report found for username '" + username + "': " + namaLengkap);
                displayDetailLaporan(username, id);
                return true;
            }
        } catch (Exception e) {
            System.err.println("Kesalahan memuat laporan untuk username '" + username + "': " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    private void displayDetailLaporan(String username, int id) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/DetailLaporan.fxml"));
            AnchorPane detailPane = loader.load();

            DetailLaporanController detailController = loader.getController();
            detailController.loadDetailLaporan(username, id);

            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(detailPane));
            stage.show();

        } catch (Exception e) {
            System.err.println("Kesalahan menampilkan detail laporan: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void clickClose(ActionEvent event) {
        Stage stage = (Stage) laporanContainer.getScene().getWindow();
        stage.close();
    }
}
