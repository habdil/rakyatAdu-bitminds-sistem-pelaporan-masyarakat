package Controllers.Administrator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import Models.Menu.DisplayLaporan;
import Models.Menu.Report;
import Views.ViewFactory;

import java.io.IOException;
import java.util.List;

public class PaneLaporanMasukController {

    ViewFactory m = new ViewFactory();

    @FXML
    private Button lbKembali;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox reportContainer;

    private DisplayLaporan displayLaporan;

    @FXML
    public void initialize() {
        displayLaporan = new DisplayLaporan();
        List<Report> verifikasiReports = displayLaporan.getReportsByStatusProsesVerifikasi();
        
        for (Report report : verifikasiReports) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/Administrator/BubbleLaporanMasuk.fxml"));
                AnchorPane reportBubble = loader.load(); 
                
                BubbleLaporanMasukController controller = loader.getController();
                controller.setReportData(report);

                reportContainer.getChildren().add(reportBubble);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void clickKembali(ActionEvent event) {
        m.navigateToHomeAdmin(lbKembali);
    }
}
