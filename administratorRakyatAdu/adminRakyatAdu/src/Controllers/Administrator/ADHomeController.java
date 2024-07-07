package Controllers.Administrator;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Models.Menu.DisplayLaporan;
import Views.ViewFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class ADHomeController {

    @FXML
    private Button idDataDinasTerkait;

    @FXML
    private Button idDataWargaKota;

    @FXML
    private Button idAddPetugas;

    @FXML
    private Button idDashboard;

    @FXML
    private Button idLogout;

    @FXML
    private Button idUmpanBalik;

    @FXML
    private Label lbLaporanMasuk;

    @FXML
    private Label lbLaporanSelesai;

    @FXML
    private Label lbLaporanTindakan;

    @FXML
    private Button idLaporanMasuk;

    @FXML
    private Button idLaporanSelesai;

    @FXML
    private Button idTindakan;

    private ScheduledExecutorService scheduler;

    @FXML
    private Label namaStaff;

    @FXML
    private LineChart<String, Number> tblLineGraph;

    @FXML
    private PieChart tblPieChart;

    ViewFactory m = new ViewFactory();

    @FXML
    void btnDataDinasTerkait(ActionEvent event) {
        m.navigateToHomeDinasData(idDataDinasTerkait);
    }

    @FXML
    void btnDataWargaKota(ActionEvent event) {
        m.navigateToHomeWargaData(idDataWargaKota);
    }

    @FXML
    void btnAddPetugas(ActionEvent event) {
        m.navigateToTambahDinas(idAddPetugas);
    }

    @FXML
    void btnDashboard(ActionEvent event) {

    }

    @FXML
    void btnUmpanBalik(ActionEvent event) {
        m.navigateToADUmpanBalik(idUmpanBalik);
    }

    @FXML
    void offMouse(MouseEvent event) {
        idDashboard.setFont(new Font(17));
        idUmpanBalik.setFont(new Font(17));
        idAddPetugas.setFont(new Font(17));
        idLogout.setFont(new Font(13));
    }

    @FXML
    void onMouse(MouseEvent event) {
        idDashboard.setFont(new Font(17));
        idUmpanBalik.setFont(new Font(17));
        idAddPetugas.setFont(new Font(17));
        idLogout.setFont(new Font(13));
    }

    @FXML
    void tblLogout(ActionEvent event) {
        m.navigateToLoginPage(idLogout);
    }

    @FXML
    public void initialize() {
        DisplayLaporan displayLaporan = new DisplayLaporan();
        Map<String, Integer> statusCounts = displayLaporan.countReportsByStatus();

        lbLaporanMasuk.setText(String.valueOf(statusCounts.getOrDefault("Proses Verifikasi", 0)));
        lbLaporanSelesai.setText(String.valueOf(statusCounts.getOrDefault("Laporan Selesai", 0)));
        lbLaporanTindakan.setText(String.valueOf(statusCounts.getOrDefault("Ditindak Lanjut", 0)));

        updateLineChart(statusCounts);
        updatePieChart(statusCounts);
        startAutoUpdate();
    }

    private void startAutoUpdate() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            DisplayLaporan displayLaporan = new DisplayLaporan();
            Map<String, Integer> statusCounts = displayLaporan.countReportsByStatus();

            Platform.runLater(() -> {
                lbLaporanMasuk.setText(String.valueOf(statusCounts.getOrDefault("Proses Verifikasi", 0)));
                lbLaporanSelesai.setText(String.valueOf(statusCounts.getOrDefault("Laporan Selesai", 0)));
                lbLaporanTindakan.setText(String.valueOf(statusCounts.getOrDefault("Ditindak Lanjut", 0)));

                updateLineChart(statusCounts);
                updatePieChart(statusCounts);
            });
        }, 0, 10, TimeUnit.SECONDS);
    }

    private void updateLineChart(Map<String, Integer> statusCounts) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Status Laporan");

        for (Map.Entry<String, Integer> entry : statusCounts.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        tblLineGraph.getData().clear();
        tblLineGraph.getData().add(series);
    }

    private void updatePieChart(Map<String, Integer> statusCounts) {
        tblPieChart.getData().clear();

        for (Map.Entry<String, Integer> entry : statusCounts.entrySet()) {
            PieChart.Data slice = new PieChart.Data(entry.getKey(), entry.getValue());
            tblPieChart.getData().add(slice);
        }
    }

    @FXML
    void clickLaporanMasuk(ActionEvent event) {
        m.navigateToLaporanMasuk(idLaporanMasuk);
    }

    @FXML
    void clickLaporanSelesai(ActionEvent event) {
        m.navigateToLaporanSelesai(idLaporanSelesai);
    }

    @FXML
    void clickTindakan(ActionEvent event) {
        m.navigateToLaporanTindakan(idTindakan);
    }

}
