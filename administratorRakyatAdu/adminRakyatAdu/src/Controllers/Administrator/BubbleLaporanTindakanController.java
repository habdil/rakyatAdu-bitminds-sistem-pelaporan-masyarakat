package Controllers.Administrator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Models.Menu.Report;

import java.io.IOException;

public class BubbleLaporanTindakanController {

    @FXML
    private Button btnDetail;

    @FXML
    private Label lbJudul;

    @FXML
    private Label lbNamaLengkap;

    @FXML
    private Label lbStatus;

    @FXML
    private AnchorPane idAnchorPane;

    private Report report;

    public void setReportData(Report report) {
        this.report = report;
        lbNamaLengkap.setText(report.getNamaLengkap());
        lbJudul.setText(report.getJudulLaporan());
        lbStatus.setText(report.getStatus());
    }

    @FXML
    void clickDetail(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/Administrator/DetailLaporanMasuk.fxml"));
            Parent root = loader.load();


            DetailLaporanTindakanController controller = loader.getController();
            controller.setReportData(report);



            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("Detail Laporan Tindakan");
            Image image = new Image("/Resource/Imageassets/logo-app.png");
            stage.getIcons().add(image);
            stage.showAndWait(); 


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
