package Controllers.Administrator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import Models.Menu.Report;

public class DetailLaporanSelesaiController {

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
    private Button lblImage;

    private Report report;

    public void setReportData(Report report) {
        this.report = report;
        lbNamaPelapor.setText(report.getNamaLengkap()); 
        lbJudulLaporan.setText(report.getJudulLaporan());
        lbDeskripsi.setText(report.getIsiLaporan());
        lbKategori.setText(report.getKategori());
        lbLokasi.setText(report.getLokasi());
        lbInstansi.setText(report.getInstansi());
        lbTgl.setText(report.getTanggal().toString()); 
    }

    @FXML
    void clickImage(ActionEvent event) {

    }

}
