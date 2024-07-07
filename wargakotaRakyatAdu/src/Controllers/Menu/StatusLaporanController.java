package Controllers.Menu;

import Models.Menu.HomeModel;
import Models.Menu.Report;
import Models.Menu.ReportModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;

import java.util.LinkedHashSet;

public class StatusLaporanController {

    @FXML
    private TextField lbSearch;

    @FXML
    private Button beranda;

    @FXML
    private Button editProfile;

    @FXML
    private Button statusLaporan;

    @FXML
    private Button logout;

    @FXML
    private Button umpanBalik;

    @FXML
    private Label tfNamaLengkap;

    @FXML
    private Label tfUserType;

    @FXML
    private VBox laporanContainer;

    private String username;

    private Timeline timeline;

    private ReportModel reportModel = new ReportModel();

    private boolean isSearching = false;

    public void setUsername(String username) {
        this.username = username;
        initialize();
    }

    @FXML
    public void initialize() {
        if (username != null) {
            String namaLengkapStr = HomeModel.getNamaLengkap(username);
            if (namaLengkapStr != null) {
                tfNamaLengkap.setText(namaLengkapStr);
            } else {
                tfNamaLengkap.setText("Nama tidak ditemukan");
            }
        }

        if (timeline != null) {
            timeline.stop();
        }
        timeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
            if (!isSearching) {
                loadLaporan();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        loadLaporan();
    }

    @FXML
    void tblBeranda(ActionEvent event) {
        navigateToPage(event, "/resource/fxml/Menu/HomeView.fxml");
    }

    @FXML
    void tblLogout(ActionEvent event) {
        navigateToPage(event, "/resource/fxml/LoginView.fxml");
    }

    @FXML
    void tblStatusLaporan(ActionEvent event) {
        navigateToPage(event, "/resource/fxml/Menu/StatusLaporanView.fxml");
    }

    @FXML
    void tblUmpanBalik(ActionEvent event) {
        navigateToPage(event, "/resource/fxml/Menu/UmpanBalikView.fxml");
    }

    @FXML
    void offMouse(MouseEvent event) {
        beranda.setFont(new Font(14));
        editProfile.setFont(new Font(8));
        statusLaporan.setFont(new Font(14));
        umpanBalik.setFont(new Font(14));
        logout.setFont(new Font(14));
    }

    @FXML
    void onMouse(MouseEvent event) {
        beranda.setFont(new Font(15));
        editProfile.setFont(new Font(11));
        statusLaporan.setFont(new Font(15));
        umpanBalik.setFont(new Font(15));
        logout.setFont(new Font(13));
    }

    private void navigateToPage(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent nextPage = loader.load();

            // Pass the username to the next controller
            if (fxmlFile.contains("HomeView.fxml")) {
                HomeController homeController = loader.getController();
                homeController.setUsername(username);
            } else if (fxmlFile.contains("StatusLaporanView.fxml")) {
                StatusLaporanController statusLaporanController = loader.getController();
                statusLaporanController.setUsername(username);
            } else if (fxmlFile.contains("UmpanBalikView.fxml")) {
                UmpanBalikController umpanBalikController = loader.getController();
                umpanBalikController.setUsername(username);
            } else if (fxmlFile.contains("EditProfileView.fxml")) {
                EditProfileController editProfileController = loader.getController();
                editProfileController.setUsername(username);
            }

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Create a new scene with the loaded page
            Scene nextScene = new Scene(nextPage);
            stage.setScene(nextScene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Bermasalah: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadLaporan() {
        try {
            reportModel.loadReports(username);
            displayReports(reportModel.searchReports(""));
        } catch (Exception e) {
            System.err.println("Error loading laporan: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void displayReports(LinkedHashSet<Report> reports) {
        laporanContainer.getChildren().clear();
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);

        for (Report report : reports) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/Menu/LaporanView.fxml"));
                AnchorPane laporanPane = loader.load();
                ShapeLaporan controller = loader.getController();
                controller.setUsername(username);
                controller.setLaporanDetails(report.getJudulLaporan(), report.getLokasi(), report.getTanggal(), report.getStatus(), report.getId());

                vbox.getChildren().add(laporanPane);
            } catch (Exception e) {
                System.err.println("Error displaying report: " + e.getMessage());
                e.printStackTrace();
            }
        }
        laporanContainer.getChildren().add(vbox);
    }

    @FXML
    void tbEditProfile(ActionEvent event) {
        navigateToPage(event, "/resource/fxml/Menu/EditProfileView.fxml");
    }

    @FXML
    void clickSearch(ActionEvent event) {
        isSearching = true;
        timeline.stop();
        String keyword = lbSearch.getText();
        LinkedHashSet<Report> searchResults = reportModel.searchReports(keyword);
        displayReports(searchResults);
        isSearching = false;
        timeline.play();
    }
}
