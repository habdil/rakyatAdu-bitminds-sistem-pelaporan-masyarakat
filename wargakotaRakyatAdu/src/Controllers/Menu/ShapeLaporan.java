package Controllers.Menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ShapeLaporan {

    @FXML
    private Label labelLocation;

    @FXML
    private Label labelStatus;

    @FXML
    private Label labelTime;

    @FXML
    private Label labelTitle;

    @FXML
    private AnchorPane laporanPane;

    private String username;
    private int reportId;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLaporanDetails(String title, String location, String time, String status, int id) {
        this.reportId = id;
        labelTitle.setText(title);
        labelLocation.setText("Lokasi: " + location);
        labelTime.setText("Waktu: " + time);
        labelStatus.setText(status);

        labelStatus.getStyleClass().clear();
        labelStatus.getStyleClass().add("label");

        switch (status) {
            case "Proses Verifikasi":
                labelStatus.getStyleClass().add("status-verifikasi");
                break;
            case "Ditindak Lanjut":
                labelStatus.getStyleClass().add("status-ditindak-lanjut");
                break;
            case "Laporan Ditolak":
                labelStatus.getStyleClass().add("status-ditolak");
                break;
            case "Laporan Selesai":
                labelStatus.getStyleClass().add("status-selesai");
                break;
        }
    }

    @FXML
    void offMouse(MouseEvent event) {
        laporanPane.getStyleClass().clear();
        laporanPane.getStyleClass().add("laporan-box");
    }

    @FXML
    void onMouse(MouseEvent event) {
        laporanPane.getStyleClass().clear();
        laporanPane.getStyleClass().add("laporan-box-hover");
    }

    @FXML
    void clickDetail(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/Menu/DetailLaporanView.fxml"));
            AnchorPane detailPane = loader.load();

            DetailLaporanController detailController = loader.getController();
            detailController.detaiLaporan(username, reportId);

            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(detailPane));
            stage.show();

        } catch (Exception e) {
            System.err.println("Kesalahan menampilkan detail laporan: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
